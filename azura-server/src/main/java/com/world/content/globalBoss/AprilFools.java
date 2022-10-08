package com.world.content.globalBoss;

import com.ruse.model.GroundItem;
import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.GroundItemManager;

import java.util.concurrent.TimeUnit;

public class AprilFools {
    private static long massMessageTimer = 0;
    public static int rng = 0,
            INTERVAL = 1 * 60 * 60_000;

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        if (amountSpawned == 0)
            ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer) - (45 * 60_000));

        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static int amountSpawned = 0;

    public static void initialize() {
        if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
            massMessageTimer = System.currentTimeMillis();
            drop();
        }
    }

    public static void drop() {
        GroundItemManager.add(new GroundItem(new Item(23709, 1), new Position(2647 + Misc.getRandom(15), 4015 + Misc.getRandom(4), 0),
                "Test", true, 110, true, 100, true), true);
    }

}