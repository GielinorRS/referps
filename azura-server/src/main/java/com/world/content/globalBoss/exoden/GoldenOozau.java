package com.world.content.globalBoss.exoden;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.concurrent.TimeUnit;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;

public class GoldenOozau extends NPC {

    private static long massMessageTimer = 0;

    public static int[] COMMONLOOT = {18686, 13996,15290, 13913, 13919,15290, 4411, 19887, 19123, 3909, 3318};
    public static int[] RARELOOT = {20549, 20173, 8809,15289,15290};
    public static int[] SUPERRARELOOT = {8800,10946, 8801,15289,15289, 8802, 8803, 8804, 8805, 8806, 8807, 8808};
    public static int[] LEGENDARYLOOT = {17646, 17648,6769, 17650, 17684, 17676, 17672, 17600, 17660};
    /**
     *
     */
    public static boolean alive = false;
    public static int rng = 0,
    // testINTERVAL = 1*1000,
    INTERVAL = 2 * 60 * 60_000, NPC_ID = 12239;
    /**
     *
     */

    public static final ExodenSpawnLocation[] LOCATIONS = {
            new ExodenSpawnLocation(2579,4503 , 0, "::oozau", "::oozau"),
            new ExodenSpawnLocation(2579,4503, 0, "::oozau", "::oozau"),
            new ExodenSpawnLocation(2579,4503, 0, "::oozau", "::oozau"),
            new ExodenSpawnLocation(2579,4503, 0, "::oozau", "::oozau")};

    /**
     *
     */
    private static GoldenOozau current;
    public static String playerPanelFrame;

    /**
     * @param position
     */
    public GoldenOozau(Position position) {

        super(NPC_ID, position);

        // setConstitution(96500/3); //7,650

    }

    public static String getTimeLeft() {
        long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
        return String.format("%dhr %dmin %ds", TimeUnit.MILLISECONDS.toHours(ms),
                TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
                TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
    }

    /**
     *
     */
    public static void initialize() {

        if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
            // // System.out.println("massMessageTimer = "+massMessageTimer);
            // // System.out.println("currentTimeMs = "+System.currentTimeMillis());
            // // System.out.println("spawn wyrm called");
            massMessageTimer = System.currentTimeMillis();
            spawn();

        }

    }

    public static void sendHint(Player player) {
        // player.getPacketSender().sendMessage("<col=1e56ad><img=5> [Exoden]@bla@ The
        // Exoden is roaming "+LOCATIONS[rng].getLocation()+"!");
    }

    public static String getPlayerPanelHint() {
        return LOCATIONS[rng].getPlayerPanelHint();
    }

    /**
     *
     */
    public static void spawncommand() {
        rng = Misc.randomMinusOne(LOCATIONS.length);
        ExodenSpawnLocation location = LOCATIONS[rng];
        GoldenOozau instance = new GoldenOozau(location.copy());
        World.register(instance);
        setCurrent(instance);
        World.sendBroadcastMessage("The Golden Oozau has appeared "+ location.getLocation(), 100);
    }

    public static void spawn() {
        if (alive == true) { // checks if its already alive to avoid duplicates
            // // System.out.println("spawn failed - wyrm is already alive");
            World.sendBroadcastMessage("The Golden Oozau has appeared "+  LOCATIONS[rng].getLocation(), 100);
            return;
        }

        /*
         * if(getCurrent() != null) { // System.out.print("spawn failed"); return; }
         */

        rng = Misc.randomMinusOne(LOCATIONS.length);
        ExodenSpawnLocation location = LOCATIONS[rng];
        GoldenOozau instance = new GoldenOozau(location.copy());
        World.register(instance);
        setCurrent(instance);
        alive = true;
        World.sendBroadcastMessage("The Golden Oozau has appeared "+ location.getLocation(), 100);

    }

    /**
     * @param npc
     */
    public static void handleDrop(NPC npc) {

        setCurrent(null);
    }


    /**
     * @return
     */
    public static GoldenOozau getCurrent() {
        return current;
    }

    /**
     * @param current
     */
    public static void setCurrent(GoldenOozau current) {
        GoldenOozau.current = current;
    }

    /**
     * @author Nick Hartskeerl <apachenick@hotmail.com>
     */
    public static class ExodenSpawnLocation extends Position {

        /**
         *
         */
        private String location, hint;

        /**
         * @param x
         * @param y
         * @param z
         * @param location
         */
        public ExodenSpawnLocation(int x, int y, int z, String location, String hint) {
            super(x, y, z);
            setLocation(location);
            setHint(hint);
        }

        /**
         * @return
         */

        String getLocation() {
            return location;
        }

        String getPlayerPanelHint() {
            return hint;
        }

        /**
         * @param location
         */
        public void setLocation(String location) {
            this.location = location;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

    }

}