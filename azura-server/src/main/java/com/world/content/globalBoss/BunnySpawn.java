package com.world.content.globalBoss;

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


public class BunnySpawn extends NPC {

    private static long massMessageTimer = 0;

    public static boolean bossAlive = false;
    public static int NPC_ID = 9020;
    public static long INTERVAL = TimeUnit.MINUTES.toMillis(45);

    public BunnySpawn(Position position) {
        super(NPC_ID, position);
    }

    public static String getTimeLeft() {
        long ms = ((INTERVAL) - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%dmin %ds",
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static void initialize() {
        if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= (INTERVAL))) {
            massMessageTimer = System.currentTimeMillis();
            spawn();
        }
    }

    public static void spawn() {
        if (bossAlive) {
            World.sendBroadcastMessage("<col=1e56ad>The Armoured Bunny has appeared ::easter", 100);
            return;
        }
        BunnySpawn instance = new BunnySpawn(new Position(2909, 4707, 0));
        World.register(instance);
        bossAlive = true;


        World.sendBroadcastMessage("<col=1e56ad>The Armoured Bunny has appeared ::easter", 100);
        World.sendMessage("<col=d90c9e>[Easter] <col=1e56ad>The Armoured Bunny has appeared <shad=1>@gre@::easter" );

    }


    public static void handleDrop(NPC npc) {
        bossAlive = false;
        EggSpawns.spawn();
    }


}