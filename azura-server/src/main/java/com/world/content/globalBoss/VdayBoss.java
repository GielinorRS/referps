package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;

import java.util.concurrent.TimeUnit;

public class VdayBoss extends NPC {
    private static long massMessageTimer = 0;
    public static boolean alive = false;
    public static int rng = 0,
            INTERVAL = 1 * 30 * 60_000, NPC_ID = 9010;

    public static final MassBossLocation[] LOCATIONS = {
            new MassBossLocation(2079, 5022, 0, "::vday", "::vday")};

    private static VdayBoss current;

    public VdayBoss(Position position) {
        super(NPC_ID, position);
    }

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        if (amountSpawned == 0)
            ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer)) - (15 * 60_000);

        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static int amountSpawned = 0;

    public static void initialize() {
        if (massMessageTimer == 0) {
            massMessageTimer = System.currentTimeMillis();
        }
        if (amountSpawned == 0 && (System.currentTimeMillis() - massMessageTimer >= (15 * 60_000))) {
            massMessageTimer = System.currentTimeMillis();
            spawn();
            amountSpawned++;
        }
        if ((System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
            massMessageTimer = System.currentTimeMillis();
            spawn();
        }
    }

    public static void spawn() {
        if (alive == true) {
            World.sendBroadcastMessage("<col=d90c9e>The Valentine Boss has appeared <col=bb2528>" +LOCATIONS[rng].getLocation(), 100);
            return;
        }
        rng = Misc.randomMinusOne(LOCATIONS.length);
        MassBossLocation location = LOCATIONS[rng];
        VdayBoss instance = new VdayBoss(location.copy());
        World.register(instance);
        setCurrent(instance);
        alive = true;

        World.sendBroadcastMessage("<col=d90c9e>The Valentine Boss has appeared <col=bb2528>" + location.getLocation(), 100);
        World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=B56FA1>The Valentine Boss has appeared <col=bb2528>" + location.getLocation());
    }


    public static void handleDrop(NPC npc) {
        alive = false;
        setCurrent(null);
    }


    public static VdayBoss getCurrent() {
        return current;
    }

    public static void setCurrent(VdayBoss current) {
        VdayBoss.current = current;
    }

}