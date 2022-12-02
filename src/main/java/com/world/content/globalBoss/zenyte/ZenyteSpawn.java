package com.world.content.globalBoss.zenyte;

import com.ruse.model.Position;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


public class ZenyteSpawn extends NPC {

    private static long massMessageTimer = 0;

    public static boolean bossAlive = false;
    public static int rng = 0, NPC_ID = 9017;
    public static long INTERVAL = TimeUnit.HOURS.toMillis(3);

    public static final ZenyteSpawnLocation[] LOCATIONS = {
            new ZenyteSpawnLocation(2783, 4849, 0, "::zenyte", "::zenyte"),
            new ZenyteSpawnLocation(2783, 4849, 0, "::zenyte", "::zenyte"),
            new ZenyteSpawnLocation(2783, 4849, 0, "::zenyte", "::zenyte"),
            new ZenyteSpawnLocation(2783, 4849, 0, "::zenyte", "::zenyte")};

    private static ZenyteSpawn current;

    public ZenyteSpawn(Position position) {
        super(NPC_ID, position);
    }

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%d hrs, %d mins, %d secs", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    /**
     *
     */
    public static void initialize() {
        if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
            massMessageTimer = System.currentTimeMillis();
            spawn();
        }

    }

    public static void spawncommand() {
        rng = Misc.randomMinusOne(LOCATIONS.length);
        ZenyteSpawnLocation location = LOCATIONS[rng];
        ZenyteSpawn instance = new ZenyteSpawn(location.copy());
        World.register(instance);
        setCurrent(instance);
        World.sendMessage(
                "<col=1e56ad><img=5><shad=1> [Zenyte Event]@gre@ The Zenyte has appeared " + location.getLocation() + "!");
    }

    public static void spawn() {
        if (bossAlive) {
            World.sendMessage("<col=1e56ad><img=5><shad=1> [Zenyte]@gre@ The Zenyte is roaming " + LOCATIONS[rng].getLocation() + "!");
            return;
        }
        rng = Misc.randomMinusOne(LOCATIONS.length);
        ZenyteSpawnLocation location = LOCATIONS[rng];
        ZenyteSpawn instance = new ZenyteSpawn(location.copy());
        World.register(instance);
        setCurrent(instance);
        bossAlive = true;
        World.sendMessage("<col=1e56ad><img=5><shad=1> [Zenyte]@gre@ The Zenyte has appeared " + location.getLocation() + "!");

    }

    public static void handleDrop(NPC npc) {

        setCurrent(null);

        if (npc.getCombatBuilder().getDamageMap().size() == 0) {
            return;
        }

        Map<Player, Long> killers = new HashMap<>();

        for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

            if (entry == null) {
                continue;
            }

            long timeout = entry.getValue().getStopwatch().elapsed();

            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                continue;
            }

            Player player = entry.getKey();

            if (player.getConstitution() <= 0 || !player.isRegistered()) {
                continue;
            }

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Entry<Player, Long>> result = sortEntries(killers);
        int count = 0;

        for (Iterator<Entry<Player, Long>> iterator = result.iterator(); iterator.hasNext(); ) {
            Entry<Player, Long> entry = iterator.next();

            Player killer = entry.getKey();
            long damage = entry.getValue();

            killer.getDailyTaskManager().submitProgressToIdentifier(41, 1);
            NPCDrops.dropItems(killer, npc);
            iterator.remove();
            if (++count >= 25) {
                break;
            }

        }

    }

    static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {

        List<Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());

        Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return sortedEntries;

    }

    public static ZenyteSpawn getCurrent() {
        return current;
    }

    public static void setCurrent(ZenyteSpawn current) {
        ZenyteSpawn.current = current;
    }

    public static class ZenyteSpawnLocation extends Position {

        private String location, hint;

        public ZenyteSpawnLocation(int x, int y, int z, String location, String hint) {
            super(x, y, z);
            setLocation(location);
            setHint(hint);
        }

        String getLocation() {
            return location;
        }

        String getPlayerPanelHint() {
            return hint;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

    }

}