package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;

import java.util.concurrent.TimeUnit;

public class HydraSpawn extends NPC {

    private static long massMessageTimer = 0;

    public static boolean bossAlive = false;
    public static int rng = 0, NPC_ID = 28615;
    public static long INTERVAL = TimeUnit.HOURS.toMillis(4);

    public static final MassBossLocation LOCATION =
            new MassBossLocation(2719, 4850, 5, "::hydra", "::hydra");

    private static HydraSpawn current;

    public HydraSpawn(Position position) {
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


    public static void spawn() {
        if (bossAlive == true) {
            World.sendMessage("@mag@<img=5><shad=1>[Hydrix Donators]@blu@ The Alchemical Hydra has appeared " + LOCATION.getLocation() + "!");
            return;
        }
        HydraSpawn instance = new HydraSpawn(LOCATION.copy());
        World.register(instance);
        setCurrent(instance);
        bossAlive = true;

        World.sendMessage("@mag@<img=5><shad=1>[Hydrix Donators]@blu@ The Alchemical Hydra has appeared " + LOCATION.getLocation() + "!");

    }


    public static void handleDrop(NPC npc) {
        bossAlive = false;
        setCurrent(null);
    }


    public static HydraSpawn getCurrent() {
        return current;
    }

    public static void setCurrent(HydraSpawn current) {
        HydraSpawn.current = current;
    }


}