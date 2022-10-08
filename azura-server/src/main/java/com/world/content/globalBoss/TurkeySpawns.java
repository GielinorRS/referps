package com.world.content.globalBoss;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.TimeUnit;

public class TurkeySpawns extends NPC {
    private static long massMessageTimer = 0;
    public static boolean alive = false;
    public static int rng = 0,
            INTERVAL = 1 * 60 * 60_000, NPC_ID = 8499;

    public static final TurkeySpawnLocation[] LOCATIONS = {
            new TurkeySpawnLocation("Varrock", new int[]{3214, 3425},
                    new int[]{3244, 3413}, new int[]{3264, 3428},
                    new int[]{3245, 3464}, new int[]{3181, 3408},
                    new int[]{3209, 3388}, new int[]{3250, 3390},
                    new int[]{3211, 3463}),

            new TurkeySpawnLocation("Lumbridge", new int[]{3207, 3234},
                    new int[]{3202, 3214}, new int[]{3218, 3205},
                    new int[]{3244, 3198}, new int[]{3239, 3181},
                    new int[]{3234, 3232}, new int[]{3260, 3229},
                    new int[]{3216, 3255}),

            new TurkeySpawnLocation("Falador", new int[]{2964, 3383},
                    new int[]{2941, 3345}, new int[]{2953, 3318},
                    new int[]{3048, 3335}, new int[]{3062, 3371},
                    new int[]{3010, 3382}, new int[]{3003, 3356},
                    new int[]{3028, 3361}),

            new TurkeySpawnLocation("Ardougne", new int[]{2681, 3309},
                    new int[]{2645, 3332}, new int[]{2680, 3285},
                    new int[]{2647, 3273}, new int[]{2621, 3297},
                    new int[]{2587, 3321}, new int[]{2562, 3300},
                    new int[]{2579, 3278}, new int[]{2617, 3270}),

            new TurkeySpawnLocation("Yanille", new int[]{2616, 3106},
                    new int[]{2616, 3084}, new int[]{2598, 3087},
                    new int[]{2578, 3099}, new int[]{2547, 3107},
                    new int[]{2547, 3084}, new int[]{2565, 3076},
                    new int[]{2600, 3107}),

            new TurkeySpawnLocation("Camelot", new int[]{2776, 3480},
                    new int[]{2776, 3459}, new int[]{2742, 3476},
                    new int[]{2716, 3507}, new int[]{2687, 3473},
                    new int[]{2728, 3453}, new int[]{2728, 3476}),
    };

    public static int[] NPC_IDS = new int[]{8504, 8505, 8506, 8508, 8510};

    @Getter
    @Setter
    private static TurkeySpawns current;
    @Getter
    private static TurkeySpawnLocation currentLocation;

    public TurkeySpawns(Position position) {
        super(NPC_IDS[Misc.getRandom(NPC_IDS.length - 1)], position);
        getMovementCoordinator().setCoordinator(new NPCMovementCoordinator.Coordinator(true, 10));
    }


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
        if (massMessageTimer == 0) {
            massMessageTimer = System.currentTimeMillis();
        }
        if (amountSpawned == 0 && (System.currentTimeMillis() - massMessageTimer >= (45 * 60_000))) {
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
            World.sendBroadcastMessage("The Hidden Turkey is roaming around <col=ff0000>" + LOCATIONS[rng].getLocation(), 300);
            return;
        }
        rng = Misc.randomMinusOne(LOCATIONS.length);
        TurkeySpawnLocation location = LOCATIONS[rng];
        TurkeySpawns instance = new TurkeySpawns(location.getRandomLocation());
        World.register(instance);
        setCurrent(instance);
        alive = true;

        currentLocation = location;

        World.sendBroadcastMessage("The Hidden Turkey is roaming around <col=ff0000>" + location.getLocation()
                + " - Find it for a reward!" , 300);
        World.sendMessage("<img=862>@blu@[Thanksgiving]<img=862> @or2@" + "The Hidden Turkey is roaming around <col=ff0000>"
                + location.getLocation()
                + "@or2@ - @red@Find it for a reward!");
    }

    public static void findTurkey(NPC npc, Player player) {
        if (!alive) {
            player.sendMessage("The Hidden turkey has already been found.");
            return;
        }
        if (!npc.equals(getCurrent())) {
            player.sendMessage("This is not the correct turkey.");
            return;
        }

        World.deregister(npc);
        alive = false;
        setCurrent(null);
        World.sendMessage("<img=862>@blu@[Thanksgiving]<img=862> @mag@" + player.getUsername() + "@or2@ has found the Hidden Turkey!");
        World.sendMessage("<img=862>@blu@[Thanksgiving]<img=862> @mag@" + player.getUsername()
                + "@or2@ got: @red@$1 Scroll, x20 Turkey Feathers, x2 Vote scrolls");
        World.sendBroadcastMessage("The Hidden Turkey has been found!" , 10);

        player.getInventory().add(10946, 1);
        player.getInventory().add(23307, 20);
        player.getInventory().add(23020, 2);
    }

    public static void handleDrop(NPC npc) {
        alive = false;
        setCurrent(null);
    }


    @Getter
    @Setter
    public static class TurkeySpawnLocation {

        private String location;
        private int[][] coords;

        public TurkeySpawnLocation(String location, int[]... coords) {
            this.location = location;
            this.coords = coords;
        }

        public Position getRandomLocation() {
            int[] coord = coords[Misc.getRandom(coords.length)];
            return new Position(coord[0], coord[1], 0);
        }

    }

}