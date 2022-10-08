package com.ruse.world.content.skeletalhorror;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.concurrent.TimeUnit;

///import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;

public class Prime extends NPC {

	private static long massMessageTimer = 0;

	/**
	 *
	 */
	public static boolean alive = false;
	public static int rng = 0,
			// testINTERVAL = 1*1000,
			INTERVAL = 1 * 60 * 60_000, NPC_ID = 3830;
	/**
	 *
	 *
	 *
	 */

	public static final SkeletalHorrorLocation[] LOCATIONS = {//2466, 10156
			new SkeletalHorrorLocation(2464, 10146, 0, "::Prime", "::Prime"),
			new SkeletalHorrorLocation(2464, 10146, 0, "::Prime", "::Prime"),
			new SkeletalHorrorLocation(2464, 10146, 0, "::Prime", "::Prime"),
			new SkeletalHorrorLocation(2464, 10146, 0, "::Prime", "::Prime") };

	/**
	 *
	 */
	private static Prime current;
	public static String playerPanelFrame;

	/**
	 *
	 * @param position
	 */
	public Prime(Position position) {

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
		// player.getPacketSender().sendMessage("<col=1e56ad><img=5> [Skeletal
		// Horror]@bla@ The Skeletal Horror is roaming
		// "+LOCATIONS[rng].getLocation()+"!");
	}

	public static String getPlayerPanelHint() {
		return LOCATIONS[rng].getPlayerPanelHint();
	}

	/**
	 *
	 */
	
	public static void spawncommand() {
		rng = Misc.randomMinusOne(LOCATIONS.length);
		SkeletalHorrorLocation location = LOCATIONS[rng];
		Prime instance = new Prime(location.copy());
		World.register(instance);
		setCurrent(instance);
		World.sendBroadcastMessage("Prime has appeared "+ location.getLocation()  , 100);
			return;
		}
	public static void spawn() {
		if (alive == true) { // checks if its already alive to avoid duplicates
			// // System.out.println("spawn failed - wyrm is already alive");
			World.sendBroadcastMessage("Prime has appeared "+ LOCATIONS[rng].getLocation() , 100);
			return;
		}
		rng = Misc.randomMinusOne(LOCATIONS.length);
		SkeletalHorrorLocation location = LOCATIONS[rng];
		Prime instance = new Prime(location.copy());

		World.register(instance);
		setCurrent(instance);
		alive = true;
		World.sendBroadcastMessage("Prime has appeared "+ location.getLocation()  , 100);

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
	public static Prime getCurrent() {
		return current;
	}

	/**
	 *
	 * @param current
	 */
	public static void setCurrent(Prime current) {
		Prime.current = current;
	}

	/**
	 *
	 * @author Nick Hartskeerl <apachenick@hotmail.com>
	 *
	 */
	public static class SkeletalHorrorLocation extends Position {

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
		public SkeletalHorrorLocation(int x, int y, int z, String location, String hint) {
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