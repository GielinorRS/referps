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
package net.runelite.client;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class RuneLiteProperties {
    private static final String RUNELITE_TITLE = "runelite.title";
    private static final String RUNELITE_VERSION = "runelite.version";
    private static final String DISCORD_APP_ID = "runelite.discord.appid";
    private static final String DISCORD_INVITE = "runelite.discord.invite";
    private static final String IMGUR_CLIENT_ID = "runelite.imgur.client.id";

    private static final Properties properties = new Properties();

    static {
        try (InputStream in = RuneLiteProperties.class.getResourceAsStream("runelite.properties")) {
            properties.load(in);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String getTitle() {
        return properties.getProperty(RUNELITE_TITLE);
    }

    public static String getVersion() {
        return properties.getProperty(RUNELITE_VERSION);
    }

    public static String getDiscordAppId() {
        return properties.getProperty(DISCORD_APP_ID);
    }

    public static String getDiscordInvite() {
        return properties.getProperty(DISCORD_INVITE);
    }

    public static String getImgurClientId() {
        return properties.getProperty(IMGUR_CLIENT_ID);
    }
}