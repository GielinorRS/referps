package com.world.content.globalBoss;

import com.ruse.GameSettings;
import com.ruse.model.GameObject;
import com.ruse.model.Locations;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class EggSpawns {

    public static final TurkeySpawnLocation[] LOCATIONS = {
            new TurkeySpawnLocation("Middle",
                    new int[]{2900, 4700},
                    new int[]{2920, 4713}),
            new TurkeySpawnLocation("Middle",
                    new int[]{2900, 4700},
                    new int[]{2920, 4713}),

            new TurkeySpawnLocation("1",
                    new int[]{2885, 4713},
                    new int[]{2891, 4721}),

            new TurkeySpawnLocation("2",
                    new int[]{2902, 4726},
                    new int[]{2910, 4729}),

            new TurkeySpawnLocation("3",
                    new int[]{2924, 4720},
                    new int[]{2935, 4728}),

            new TurkeySpawnLocation("4",
                    new int[]{2930, 4689},
                    new int[]{2936, 4707}),

            new TurkeySpawnLocation("5",
                    new int[]{2907, 4679},
                    new int[]{2916, 4685}),

            new TurkeySpawnLocation("6",
                    new int[]{2886, 4682},
                    new int[]{2893, 4693}),

            new TurkeySpawnLocation("7",
                    new int[]{2909, 4686},
                    new int[]{2914, 4694}),

            new TurkeySpawnLocation("8",
                    new int[]{2904, 4714},
                    new int[]{2906, 4725}),

    };

    private static long massMessageTimer = System.currentTimeMillis();

    public static long INTERVAL = TimeUnit.MINUTES.toMillis(5);

    public static String getTimeLeft() {
        long ms = ((INTERVAL) - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%dmin %ds",
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    public static void initialize() {
        if ((System.currentTimeMillis() - massMessageTimer >= (INTERVAL))) {
            massMessageTimer = System.currentTimeMillis();
            //spawn();
            INTERVAL = TimeUnit.MINUTES.toMillis(15 + Misc.getRandom(15));
        }
    }


    public static void loadEggs(Player player) {
        for (GameObject object : objects){
            if (object != null)
                player.getPacketSender().sendObject(object);
        }
    }


    public static ArrayList<GameObject> objects = new ArrayList<>();
    public static ArrayList<Position> positions = new ArrayList<>();

    public static void spawn() {
        if (objects.size() > 0){
            for (GameObject object : objects){
                if (object != null)
                    CustomObjects.deleteGlobalObject(object);
            }
        }
        objects.clear();
        positions.clear();

        while (objects.size() < 300) {
            int random = Misc.getRandom(LOCATIONS.length - 1);
            TurkeySpawnLocation location = LOCATIONS[random];

            int x = Misc.getRandom(location.getCoords()[1][0] - location.getCoords()[0][0]);
            int y = Misc.getRandom(location.getCoords()[1][1] - location.getCoords()[0][1]);

            Position pos = new Position(location.getCoords()[0][0] + x, location.getCoords()[0][1] + y);
            if (!positions.contains(pos)) {
                positions.add(pos);
                GameObject gameObject = new GameObject(42650, pos, 10, 0);
                objects.add(gameObject);
                CustomObjects.spawnGlobalObject(gameObject);
            }
        }

        DiscordMessager.sendGlobal("Easter eggs have spawned around ::easter", Color.PINK);
        World.sendBroadcastMessage("Easter eggs have spawned around ::easter - Find them for rewards!", 300);
        World.sendMessage("<img=862>@blu@[Easter]<img=862> <col=674ea7>" + "Easter eggs have spawned around @blu@::easter"
                + "<col=674ea7> - Find them for rewards!");


        for (Player p : World.getPlayers()) {
            if (p == null)
                continue;
            p.setSoulInPouch(0);
            if (p.getLocation().equals(Locations.Location.EASTER)) {
                p.sendMessage("You have been moved to the middle to collect eggs!");
                p.moveTo(new Position(2910, 4704, 0));
                loadEggs(p);
            }
        }
    }


    public static void claimEgg(Player player, GameObject gameObject) {
        if (!player.getEasterDelay().elapsed(2500)){
            player.sendMessage("You must wait three seconds before collecting more easter eggs.");
            return;
        }
        if (player.getSoulInPouch() >= 5){
            player.sendMessage("You have already gathered five Easter eggs this batch.");
            return;
        }

        player.getEasterDelay().reset();

        boolean contains = false;
        for (GameObject object : objects) {
            if (object != null && object.getPosition().equals(gameObject.getPosition())) {
                player.sendMessage("@blu@You found an Easter egg!");

                player.getInventory().add(11027, 1);

                CustomObjects.deleteGlobalObject(object);
                player.setSoulInPouch(player.getSoulInPouch()  + 1);
                objects.remove(object);
                contains = true;
                break;
            }
        }

        if (!contains) {
            CustomObjects.deleteGlobalObject(gameObject);
        }

        if (objects.size() <= 0){
            World.sendMessage("<img=862>@blu@[Easter]<img=862> <col=674ea7>All of the Easter eggs have been found!");
        }
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

    }

}