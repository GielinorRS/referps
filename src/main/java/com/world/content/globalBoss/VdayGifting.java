package com.world.content.globalBoss;

import com.ruse.model.Locations;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

import java.util.concurrent.TimeUnit;

public class VdayGifting {
    private static long massMessageTimer = 0;
    public static int rng = 0,
            INTERVAL = 1 * 15 * 60_000;

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        if (amountSpawned == 0)
            ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer) - (5 * 60_000));

        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static int amountSpawned = 0;

    public static void initialize() {
        // massMessageTimer = System.currentTimeMillis();
        if (massMessageTimer == 0) {
            massMessageTimer = System.currentTimeMillis();
        }
        if (amountSpawned == 0 && (System.currentTimeMillis() - massMessageTimer >= (5 * 60_000))) {
            givePresent();
            amountSpawned++;
        }
        if ((System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
            givePresent();
        }
    }

    public static void givePresent() {
        Player player = null;

        int i = 0;

        while (player == null && i < 500) {
            player = World.getPlayers().get(Misc.getRandom(1 + World.getPlayers().size() - 1));
            if ( player != null && player.getLocation() == Locations.Location.AFK) {
                player = null;
                continue;
            }
            i++;
        }

        if (player != null) {
            player.getInventory().add(23547, 1);
            World.sendMessage("<img=1572><col=d90c9e>[Valentines]<img=1572> <col=bb2528>Cupid has given <col=d90c9e>"
                    + player.getUsername() + "<col=bb2528> a <col=d90c9e>Valentine's gift!");
        }
        massMessageTimer = System.currentTimeMillis();
    }

}