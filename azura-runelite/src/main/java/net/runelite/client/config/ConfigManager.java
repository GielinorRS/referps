/*
 * Copyright (c) 2017, Adam <Adam@sigterm.info>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package net.runelite.client.config;

import com.google.common.base.Strings;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import net.runelite.client.RuneLite;
import net.runelite.client.eventbus.EventBus;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.events.ClientShutdown;
import net.runelite.client.events.ConfigChanged;
import net.runelite.client.util.ColorUtil;

import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.awt.*;
import java.io.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Singleton
@Slf4j
public class ConfigManager {
    private static final DateFormat TIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private final ScheduledExecutorService executor;
    private final ConfigInvocationHandler handler = new ConfigInvocationHandler(this);
    private final Properties properties = new Properties();
    private final Map<String, String> pendingChanges = new HashMap<>();
    private final File settingsFileInput;
    @Inject
    EventBus eventBus;
    private File propertiesFile;

    @Inject
    public ConfigManager(
            @Named("config") File config,
            ScheduledExecutorService scheduledExecutorService) {
        this.executor = scheduledExecutorService;
        this.settingsFileInput = config;
        this.propertiesFile = getPropertiesFile();

        executor.scheduleWithFixedDelay(this::sendConfig, 30, 30, TimeUnit.SECONDS);
    }

    static Object stringToObject(String str, Class<?> type) {
        if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(str);
        }
        if (type == int.class) {
            return Integer.parseInt(str);
        }
        if (type == Color.class) {
            return ColorUtil.fromString(str);
        }
        if (type == Dimension.class) {
            String[] splitStr = str.split("x");
            int width = Integer.parseInt(splitStr[0]);
            int height = Integer.parseInt(splitStr[1]);
            return new Dimension(width, height);
        }
        if (type == Point.class) {
            String[] splitStr = str.split(":");
            int width = Integer.parseInt(splitStr[0]);
            int height = Integer.parseInt(splitStr[1]);
            return new Point(width, height);
        }
        if (type == Rectangle.class) {
            String[] splitStr = str.split(":");
            int x = Integer.parseInt(splitStr[0]);
            int y = Integer.parseInt(splitStr[1]);
            int width = Integer.parseInt(splitStr[2]);
            int height = Integer.parseInt(splitStr[3]);
            return new Rectangle(x, y, width, height);
        }
        if (type.isEnum()) {
            return Enum.valueOf((Class<? extends Enum>) type, str);
        }
        if (type == Instant.class) {
            return Instant.parse(str);
        }
        if (type == Keybind.class || type == ModifierlessKeybind.class) {
            String[] splitStr = str.split(":");
            int code = Integer.parseInt(splitStr[0]);
            int mods = Integer.parseInt(splitStr[1]);
            if (type == ModifierlessKeybind.class) {
                return new ModifierlessKeybind(code, mods);
            }
            return new Keybind(code, mods);
        }
		/*if (type == WorldPoint.class)
		{
			String[] splitStr = str.split(":");
			int x = Integer.parseInt(splitStr[0]);
			int y = Integer.parseInt(splitStr[1]);
			int plane = Integer.parseInt(splitStr[2]);
			return new WorldPoint(x, y, plane);
		}*/
        if (type == Duration.class) {
            return Duration.ofMillis(Long.parseLong(str));
        }
        return str;
    }

    static String objectToString(Object object) {
        if (object instanceof Color) {
            return String.valueOf(((Color) object).getRGB());
        }
        if (object instanceof Enum) {
            return ((Enum) object).name();
        }
        if (object instanceof Dimension) {
            Dimension d = (Dimension) object;
            return d.width + "x" + d.height;
        }
        if (object instanceof Point) {
            Point p = (Point) object;
            return p.x + ":" + p.y;
        }
        if (object instanceof Rectangle) {
            Rectangle r = (Rectangle) object;
            return r.x + ":" + r.y + ":" + r.width + ":" + r.height;
        }
        if (object instanceof Instant) {
            return ((Instant) object).toString();
        }
        if (object instanceof Keybind) {
            Keybind k = (Keybind) object;
            return k.getKeyCode() + ":" + k.getModifiers();
        }
		/*if (object instanceof WorldPoint)
		{
			WorldPoint wp = (WorldPoint) object;
			return wp.getX() + ":" + wp.getY() + ":" + wp.getPlane();
		}*/
        if (object instanceof Duration) {
            return Long.toString(((Duration) object).toMillis());
        }
        return object.toString();
    }

    private File getLocalPropertiesFile() {
        return settingsFileInput;
    }

    private File getPropertiesFile() {
        return getLocalPropertiesFile();
    }

    public void load() {
        loadFromFile();
    }

    private synchronized void syncPropertiesFromFile(File propertiesFile) {
        final Properties properties = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            properties.load(new InputStreamReader(in, Charset.forName("UTF-8")));
        } catch (Exception e) {
            log.debug("Malformed properties, skipping update");
            return;
        }

        final Map<String, String> copy = (Map) ImmutableMap.copyOf(this.properties);
        copy.forEach((groupAndKey, value) ->
        {
            if (!properties.containsKey(groupAndKey)) {
                final String[] split = groupAndKey.split("\\.", 2);
                if (split.length != 2) {
                    return;
                }

                final String groupName = split[0];
                final String key = split[1];
                unsetConfiguration(groupName, key);
            }
        });

        properties.forEach((objGroupAndKey, objValue) ->
        {
            final String groupAndKey = String.valueOf(objGroupAndKey);
            final String[] split = groupAndKey.split("\\.", 2);
            if (split.length != 2) {
                return;
            }

            final String groupName = split[0];
            final String key = split[1];
            final String value = String.valueOf(objValue);
            setConfiguration(groupName, key, value);
        });
    }

    public void importLocal() {
        final File file = new File(propertiesFile.getParent(), propertiesFile.getName() + "." + TIME_FORMAT.format(new Date()));

        try {
            saveToFile(file);
        } catch (IOException e) {
            log.warn("Backup failed, skipping import", e);
            return;
        }

        syncPropertiesFromFile(getLocalPropertiesFile());
    }

    private synchronized void loadFromFile() {
        handler.invalidate();
        properties.clear();

        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            properties.load(new InputStreamReader(in, Charset.forName("UTF-8")));
        } catch (FileNotFoundException ex) {
            log.debug("Unable to load settings - no such file");
        } catch (IllegalArgumentException | IOException ex) {
            log.warn("Unable to load settings", ex);
        }

        try {
            Map<String, String> copy = (Map) ImmutableMap.copyOf(properties);
            copy.forEach((groupAndKey, value) ->
            {
                final String[] split = groupAndKey.split("\\.", 2);
                if (split.length != 2) {
                    log.debug("Properties key malformed!: {}", groupAndKey);
                    properties.remove(groupAndKey);
                    return;
                }

                final String groupName = split[0];
                final String key = split[1];

                ConfigChanged configChanged = new ConfigChanged();
                configChanged.setGroup(groupName);
                configChanged.setKey(key);
                configChanged.setOldValue(null);
                configChanged.setNewValue(value);
                eventBus.post(configChanged);
            });
        } catch (Exception ex) {
            log.warn("Error posting config events", ex);
        }
    }

    private void saveToFile(final File propertiesFile) throws IOException {
        File parent = propertiesFile.getParentFile();

        parent.mkdirs();

        File tempFile = new File(parent, RuneLite.DEFAULT_CONFIG_FILE.getName() + ".tmp");

        try (FileOutputStream out = new FileOutputStream(tempFile)) {
            out.getChannel().lock();
            properties.store(new OutputStreamWriter(out, StandardCharsets.UTF_8), "RuneLite configuration");
            // FileOutputStream.close() closes the associated channel, which frees the lock
        }

        try {
            Files.move(tempFile.toPath(), propertiesFile.toPath(), StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
        } catch (AtomicMoveNotSupportedException ex) {
            log.debug("atomic move not supported", ex);
            Files.move(tempFile.toPath(), propertiesFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
    }

    public <T extends Config> T getConfig(Class<T> clazz) {
        if (!Modifier.isPublic(clazz.getModifiers())) {
            throw new RuntimeException("Non-public configuration classes can't have default methods invoked");
        }

        T t = (T) Proxy.newProxyInstance(clazz.getClassLoader(), new Class<?>[]
                {
                        clazz
                }, handler);

        return t;
    }

    public List<String> getConfigurationKeys(String prefix) {
        return properties.keySet().stream().filter(v -> ((String) v).startsWith(prefix)).map(String.class::cast).collect(Collectors.toList());
    }

    public String getConfiguration(String groupName, String key) {
        return properties.getProperty(groupName + "." + key);
    }

    public <T> T getConfiguration(String groupName, String key, Class<T> clazz) {
        String value = getConfiguration(groupName, key);
        if (!Strings.isNullOrEmpty(value)) {
            try {
                return (T) stringToObject(value, clazz);
            } catch (Exception e) {
                log.warn("Unable to unmarshal {}.{} ", groupName, key, e);
            }
        }
        return null;
    }

    public void setConfiguration(String groupName, String key, String value) {
        String oldValue = (String) properties.setProperty(groupName + "." + key, value);

        if (Objects.equals(oldValue, value)) {
            return;
        }

        log.debug("Setting configuration value for {}.{} to {}", groupName, key, value);
        handler.invalidate();

        synchronized (pendingChanges) {
            pendingChanges.put(groupName + "." + key, value);
        }

        ConfigChanged configChanged = new ConfigChanged();
        configChanged.setGroup(groupName);
        configChanged.setKey(key);
        configChanged.setOldValue(oldValue);
        configChanged.setNewValue(value);

        eventBus.post(configChanged);
    }

    public void setConfiguration(String groupName, String key, Object value) {
        setConfiguration(groupName, key, objectToString(value));
    }

    public void unsetConfiguration(String groupName, String key) {
        String oldValue = (String) properties.remove(groupName + "." + key);

        if (oldValue == null) {
            return;
        }

        log.debug("Unsetting configuration value for {}.{}", groupName, key);
        handler.invalidate();

        synchronized (pendingChanges) {
            pendingChanges.put(groupName + "." + key, null);
        }

        ConfigChanged configChanged = new ConfigChanged();
        configChanged.setGroup(groupName);
        configChanged.setKey(key);
        configChanged.setOldValue(oldValue);

        eventBus.post(configChanged);
    }

    public ConfigDescriptor getConfigDescriptor(Config configurationProxy) {
        Class<?> inter = configurationProxy.getClass().getInterfaces()[0];
        ConfigGroup group = inter.getAnnotation(ConfigGroup.class);

        if (group == null) {
            throw new IllegalArgumentException("Not a config group");
        }

        final List<ConfigItemDescriptor> items = Arrays.stream(inter.getMethods())
                .filter(m -> m.getParameterCount() == 0)
                .map(m -> new ConfigItemDescriptor(
                        m.getDeclaredAnnotation(ConfigItem.class),
                        m.getReturnType(),
                        m.getDeclaredAnnotation(Range.class),
                        m.getDeclaredAnnotation(Alpha.class),
                        m.getDeclaredAnnotation(Units.class)
                ))
                .sorted((a, b) -> ComparisonChain.start()
                        .compare(a.getItem().position(), b.getItem().position())
                        .compare(a.getItem().name(), b.getItem().name())
                        .result())
                .collect(Collectors.toList());

        return new ConfigDescriptor(group, items);
    }

    /**
     * Initialize the configuration from the default settings
     *
     * @param proxy
     */
    public void setDefaultConfiguration(Object proxy, boolean override) {
        Class<?> clazz = proxy.getClass().getInterfaces()[0];
        ConfigGroup group = clazz.getAnnotation(ConfigGroup.class);

        if (group == null) {
            return;
        }

        for (Method method : clazz.getDeclaredMethods()) {
            ConfigItem item = method.getAnnotation(ConfigItem.class);

            // only apply default configuration for methods which read configuration (0 args)
            if (item == null || method.getParameterCount() != 0) {
                continue;
            }

            if (!method.isDefault()) {
                if (override) {
                    String current = getConfiguration(group.value(), item.keyName());
                    // only unset if already set
                    if (current != null) {
                        unsetConfiguration(group.value(), item.keyName());
                    }
                }
                continue;
            }

            if (!override) {
                // This checks if it is set and is also unmarshallable to the correct type; so
                // we will overwrite invalid config values with the default
                Object current = getConfiguration(group.value(), item.keyName(), method.getReturnType());
                if (current != null) {
                    continue; // something else is already set
                }
            }

            Object defaultValue;
            try {
                defaultValue = ConfigInvocationHandler.callDefaultMethod(proxy, method, null);
            } catch (Throwable ex) {
                log.warn(null, ex);
                continue;
            }

            String current = getConfiguration(group.value(), item.keyName());
            String valueString = objectToString(defaultValue);
            // null and the empty string are treated identically in sendConfig and treated as an unset
            // If a config value defaults to "" and the current value is null, it will cause an extra
            // unset to be sent, so treat them as equal
            if (Objects.equals(current, valueString) || (Strings.isNullOrEmpty(current) && Strings.isNullOrEmpty(valueString))) {
                continue; // already set to the default value
            }

            log.debug("Setting default configuration value for {}.{} to {}", group.value(), item.keyName(), defaultValue);

            setConfiguration(group.value(), item.keyName(), valueString);
        }
    }

    @Subscribe(priority = 100)
    private void onClientShutdown(ClientShutdown e) {
        Future<Void> f = sendConfig();
        if (f != null) {
            e.waitFor(f);
        }
    }

    @Nullable
    private CompletableFuture<Void> sendConfig() {
        boolean changed;
        synchronized (pendingChanges) {
            changed = !pendingChanges.isEmpty();
            pendingChanges.clear();
        }

        if (changed) {
            try {
                saveToFile(propertiesFile);
            } catch (IOException ex) {
                log.warn("unable to save configuration file", ex);
            }
        }

        return null;
    }
}
