package com.world.content.globalBoss;

import com.ruse.model.GameObject;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.CustomObjects;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.concurrent.TimeUnit;


public class JulyBalloons {

    private static long massMessageTimer = 0;

    public static long INTERVAL = TimeUnit.MINUTES.toMillis(2);


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

        Player player = World.getPlayers().get(Misc.getRandom(World.getPlayers().size() - 1));

        if (player != null){
            GameObject gameObject = new GameObject(115, player.getPosition(), 10, 0);
            CustomObjects.spawnGlobalObject(gameObject);

        }

    }

}