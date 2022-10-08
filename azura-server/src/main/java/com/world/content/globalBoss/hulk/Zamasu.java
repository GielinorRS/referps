package com.world.content.globalBoss.hulk;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.concurrent.TimeUnit;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;

public class Zamasu extends NPC {

	private static long massMessageTimer = 0;

	public static int[] COMMONLOOT = { 18686, 6199, 7956, 4411, 19887, 19123, 3909, 3318 };
	public static int[] RARELOOT = { 20549, 20173, 8809,15290 };
	public static int[] SUPERRARELOOT = { 19115 , 10946, 19116 ,15290,15289  };
	public static int[] LEGENDARYLOOT = { 20488, 19114,6769, 19115, 10947, 15288, 15289, 15290,18719,7587 };
	/**
	 *
	 */
	public static boolean alive = false;
	public static int rng = 0,	
			INTERVAL =  4 * 60 * 60_000, NPC_ID = 8009;
	/**
	 *
	 *
	 *
	 */

	public static final HulkSpawnLocation[] LOCATIONS = {
			new HulkSpawnLocation(3486, 3617, 0, "::zamasu", "::zamasu"),
			new HulkSpawnLocation(3486, 3617, 0, "::zamasu", "::zamasu"),
			new HulkSpawnLocation(3486, 3617, 0, "::zamasu", "::zamasu"),
			new HulkSpawnLocation(3486, 3617, 0, "::zamasu", "::zamasu") };

	/**
	 *
	 */
	private static Zamasu current;
	public static String playerPanelFrame;

	/**
	 *
	 * @param position
	 */
	public Zamasu(Position position) {

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
		// player.getPacketSender().sendMessage("<col=1e56ad><img=5> [Hulk]@bla@ The
		// Hulk is roaming "+LOCATIONS[rng].getLocation()+"!");
	}

	public static String getPlayerPanelHint() {
		return LOCATIONS[rng].getPlayerPanelHint();
	}

	/**
	 *
	 */
	public static void spawncommand() {
		rng = Misc.randomMinusOne(LOCATIONS.length);
		HulkSpawnLocation location = LOCATIONS[rng];
		Zamasu instance = new Zamasu(location.copy());
		World.register(instance);
		setCurrent(instance);
		World.sendBroadcastMessage("The Fused Zamasu has appeared "+ location.getLocation(), 100);
	}

	public static void spawn() {
		if (alive == true) { // checks if its already alive to avoid duplicates
			// // System.out.println("spawn failed - wyrm is already alive");
			World.sendBroadcastMessage("The Fused Zamasu has appeared "+ LOCATIONS[rng].getLocation(), 100);
			return;
		}

		/*
		 * if(getCurrent() != null) { // System.out.print("spawn failed"); return; }
		 */

		rng = Misc.randomMinusOne(LOCATIONS.length);
		HulkSpawnLocation location = LOCATIONS[rng];
		Zamasu instance = new Zamasu(location.copy());
		World.register(instance);
		setCurrent(instance);
		alive = true;

		World.sendBroadcastMessage("The Fused Zamasu has appeared "+ location.getLocation(), 100);

	}

	/**
	 *
	 * @param npc
	 */
	public static void handleDrop(NPC npc) {
		setCurrent(null);
	}


	/**
	 *
	 * @return
	 */
	public static Zamasu getCurrent() {
		return current;
	}

	/**
	 *
	 * @param current
	 */
	public static void setCurrent(Zamasu current) {
		Zamasu.current = current;
	}

	/**
	 *
	 * @author Nick Hartskeerl <apachenick@hotmail.com>
	 *
	 */
	public static class HulkSpawnLocation extends Position {

		/**
		 *
		 */
		private String location, hint;

		/**
		 *
		 * @param x
		 * @param y
		 * @param z
		 * @param location
		 */
		public HulkSpawnLocation(int x, int y, int z, String location, String hint) {
			super(x, y, z);
			setLocation(location);
			setHint(hint);
		}

		/**
		 *
		 * @return
		 */

		String getLocation() {
			return location;
		}

		String getPlayerPanelHint() {
			return hint;
		}

		/**
		 *
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