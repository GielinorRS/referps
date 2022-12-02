package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;

import java.util.concurrent.TimeUnit;

public class EvilSanta extends NPC {
    private static long massMessageTimer = 0;
    public static boolean alive = false;
    public static int rng = 0,
            INTERVAL = 1 * 60 * 60_000, NPC_ID = 9871;

    public static final MassBossLocation[] LOCATIONS = {
            new MassBossLocation(3037, 2847, 0, "::xmas", "::xmas")};

    private static EvilSanta current;

    public EvilSanta(Position position) {
        super(NPC_ID, position);
    }

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        if (amountSpawned == 0)
            ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer)) - (30 * 60_000);

        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static int amountSpawned = 0;

    public static void initialize() {
        if (massMessageTimer == 0) {
            massMessageTimer = System.currentTimeMillis();
        }
        if (amountSpawned == 0 && (System.currentTimeMillis() - massMessageTimer >= (30 * 60_000))) {
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
            World.sendBroadcastMessage("<col=bb2528>Evil Santa Claus has appeared <col=1c3c0d>" + LOCATIONS[rng].getLocation(), 100);
            return;
        }
        rng = Misc.randomMinusOne(LOCATIONS.length);
        MassBossLocation location = LOCATIONS[rng];
        EvilSanta instance = new EvilSanta(location.copy());
        World.register(instance);
        setCurrent(instance);
        alive = true;

        World.sendBroadcastMessage("<col=bb2528>Evil Santa Claus has appeared <col=1c3c0d>" + location.getLocation(), 100);
        World.sendMessage("<img=862><col=146b3a>[Christmas]<img=862> <col=bb2528>" + "Evil Santa Claus has appeared <col=1c3c0d>" + location.getLocation());

    }


    public static void handleDrop(NPC npc) {
        alive = false;
        setCurrent(null);
    }


    public static EvilSanta getCurrent() {
        return current;
    }

    public static void setCurrent(EvilSanta current) {
        EvilSanta.current = current;
    }

}