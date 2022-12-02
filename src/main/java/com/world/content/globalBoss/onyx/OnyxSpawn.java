package com.world.content.globalBoss.onyx;

import com.ruse.model.GroundItem;
import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;

public class OnyxSpawn extends NPC {

	private static long massMessageTimer = 0;

	public static int[] COMMONLOOT = { 19115 , 19116 ,15290,15289,15288 };
	public static int[] RARELOOT = { 19115 , 19114,20488 ,15290,15289,15288 };
	public static int[] SUPERRARELOOT = { 19115 , 19114,20488 ,15290,15289,18404,20488,15359,15358  };
	public static int[] LEGENDARYLOOT = { 20489, 20488, 10949, 10947,18404,19001,4446,19886, 15288, 15289,10946,6769 };
	/**
	 *
	 */
	public static boolean wyrmAlive = false;
	public static int rng = 0,

	INTERVAL = 50 * 108 * 2000, NPC_ID = 3305;
	/**
	 *
	 *
	 *
	 */

	public static final OnyxSpawnLocation[] LOCATIONS = {
			new OnyxSpawnLocation(2470, 5431, 0, "::Onyx", "::Onyx"),
			new OnyxSpawnLocation(2470, 5431, 0, "::Onyx", "::Onyx"),
			new OnyxSpawnLocation(2470, 5431, 0, "::Onyx", "::Onyx"),
			new OnyxSpawnLocation(2470, 5431, 0, "::Onyx", "::Onyx") };

	/**
	 *
	 */
	private static OnyxSpawn current;
	public static String playerPanelFrame;

	/**
	 *
	 * @param position
	 */
	public OnyxSpawn(Position position) {

		super(NPC_ID, position);

		// setConstitution(96500/3); //7,650

	}

	public static String getTimeLeft() {
		long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
		return String.format("%d hrs, %d mins, %d secs", TimeUnit.MILLISECONDS.toHours(ms),
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
	}

	public static String getPlayerPanelHint() {
		return LOCATIONS[rng].getPlayerPanelHint();
	}

	/**
	 *
	 */
	public static void spawncommand() {
		rng = Misc.randomMinusOne(LOCATIONS.length);
		OnyxSpawnLocation location = LOCATIONS[rng];
		OnyxSpawn instance = new OnyxSpawn(location.copy());
		World.register(instance);
		setCurrent(instance);
		World.sendMessage(
				"<col=1e56ad><img=5><shad=1> [Onyx Event]@gre@ The Onyx has appeared " + location.getLocation() + "!");
	}

	public static void spawn() {
		if (wyrmAlive == true) { // checks if its already alive to avoid duplicates
			// // System.out.println("spawn failed - wyrm is already alive");
			World.sendMessage(
					"<col=1e56ad><img=5><shad=1> [Onyx]@gre@ The Onyx is roaming " + LOCATIONS[rng].getLocation() + "!");
			return;
		}

		/*
		 * if(getCurrent() != null) { // System.out.print("spawn failed"); return; }
		 */

		rng = Misc.randomMinusOne(LOCATIONS.length);
		OnyxSpawnLocation location = LOCATIONS[rng];
		OnyxSpawn instance = new OnyxSpawn(location.copy());
		World.register(instance);
		setCurrent(instance);
		wyrmAlive = true;
		World.sendMessage("<col=1e56ad><img=5><shad=1> [Onyx]@gre@ The Onyx has appeared " + location.getLocation() + "!");

	}

	/**
	 *
	 * @param npc
	 */
	public static void handleDrop(NPC npc) {

		setCurrent(null);

		if (npc.getCombatBuilder().getDamageMap().size() == 0) {
			return;
		}

		Map<Player, Long> killers = new HashMap<>();

		for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

			if (entry == null) {
				continue;
			}

			long timeout = entry.getValue().getStopwatch().elapsed();

			if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
				continue;
			}

			Player player = entry.getKey();

			if (player.getConstitution() <= 0 || !player.isRegistered()) {
				continue;
			}

			killers.put(player, entry.getValue().getDamage());
		}

		npc.getCombatBuilder().getDamageMap().clear();

		List<Entry<Player, Long>> result = sortEntries(killers);
		int count = 0;

		for (Iterator<Entry<Player, Long>> iterator = result.iterator(); iterator.hasNext(); ) {
			Entry<Player, Long> entry = iterator.next();

			Player killer = entry.getKey();
			long damage = entry.getValue();

			handleDrop(npc, killer, damage);
			killer.getDailyTaskManager().submitProgressToIdentifier(34, 1);

			iterator.remove();
			if (++count >= 25) {
				break;
			}

		}
		List<Entry<Player, Long>> sublist = Misc.randomSubList(result, 3);
		for (Entry<Player, Long> entry : sublist) {
			handleDrop(npc, entry.getKey(), entry.getValue());
			entry.getKey().sendMessage("You were picked randomly to receive a reward.");
		}

	}

	public static void giveLoot(Player player, NPC npc, Position pos) {
		int chance = Misc.exclusiveRandom(1, 1000);
		int common = COMMONLOOT[Misc.getRandom(COMMONLOOT.length - 1)];
		int rare = RARELOOT[Misc.getRandom(RARELOOT.length - 1)];
		int superrare = SUPERRARELOOT[Misc.getRandom(SUPERRARELOOT.length - 1)];
		int legendaryrare = LEGENDARYLOOT[Misc.getRandom(LEGENDARYLOOT.length - 1)];

		GroundItemManager.spawnGroundItem(player,
				new GroundItem(new Item(5022, 1000), pos, player.getUsername(), false, 150, true, 200));
		// legendaryrare
		if (chance <= 5) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(legendaryrare), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(legendaryrare).getDefinition().getName());
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;
			World.sendMessage("<img=5><col=FF0000>[LEGENDARY]" + player.getUsername() + " received " + itemMessage
					+ " from the Onyx Panther!");
			return;
		}
		// superrare
		if (chance <= 50) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(superrare), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(superrare).getDefinition().getName());
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;
			World.sendMessage("<img=5><col=FF0000>[Super Rare]" + player.getUsername() + " received " + itemMessage
					+ " from the Onyx Panther!");
			return;
		}
		// rare
		if (chance <= 100) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(rare), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(rare).getDefinition().getName());
			String itemMessage = Misc.anOrA(itemName) + " " + itemName;
			World.sendMessage("<img=5><col=FF0000>[Rare]" + player.getUsername() + " received " + itemMessage
					+ " from the Onyx Panther!");
			return;
		}
		// common
		if (chance >= 0) {
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(common), pos, player.getUsername(), false, 150, true, 200));
			String itemName = (new Item(common).getDefinition().getName());
			World.sendMessage(
					"<img=5><col=FF0000>" + player.getUsername() + " received " + itemName + " from the Onyx Panther!");
			return;
		}

	}

	/**
	 *
	 * @param npc
	 * @param player
	 * @param damage
	 */
	private static void handleDrop(NPC npc, Player player, long damage) {
		Position pos = npc.getPosition();
		giveLoot(player, npc, pos);
		player.getInventory().add(5022, 250);
		player.getInventory().add(12855, 250);


		if (Misc.getRandom(10) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 2.5K Upgrade Tokens");
			player.sendMessage("@gre@Bonus reward:@yel@ 2.5K PVM Tickets");
			player.getInventory().add(12855, 2500);
			player.getInventory().add(5022, 2500);
		}
		if (Misc.getRandom(50) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 10K PVM Tickets");
			player.getInventory().add(5022, 10000);
		}
		if (Misc.getRandom(50) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 10K Upgrade Tokens");
			player.getInventory().add(12855, 10000);
		}
		if (Misc.getRandom(250) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 25K Upgrade Tokens");
			player.getInventory().add(12855, 25000);
		}
		if (Misc.getRandom(250) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 25K PVM Tickets");
			player.getInventory().add(5022, 25000);
		}
		if (Misc.getRandom(5) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 3 Mystery Box");
			player.getInventory().add(6199, 3);
		}
		if (Misc.getRandom(25) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 5 Mystery Box");
			player.getInventory().add(6199, 5);
		}
		if (Misc.getRandom(150) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 10 Mystery Box");
			player.getInventory().add(6199, 10);
		}
		if (Misc.getRandom(150) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 5 Super Mystery Box");
			player.getInventory().add(19116, 5);
		}
		if (Misc.getRandom(300) <= 1) {
			player.sendMessage("@gre@Bonus reward:@yel@ 5 Extreme Mystery Box");
			player.getInventory().add(19115, 5);
		}
	}

	/**
	 *
	 * @param map
	 * @return
	 */
	static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

		Collections.sort(sortedEntries, new Comparator<Entry<K, V>>() {

			@Override
			public int compare(Entry<K, V> e1, Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}

		});

		return sortedEntries;

	}

	/**
	 *
	 * @return
	 */
	public static OnyxSpawn getCurrent() {
		return current;
	}

	/**
	 *
	 * @param current
	 */
	public static void setCurrent(OnyxSpawn current) {
		OnyxSpawn.current = current;
	}

	/**
	 *
	 * @author Nick Hartskeerl <apachenick@hotmail.com>
	 *
	 */
	public static class OnyxSpawnLocation extends Position {

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
		public OnyxSpawnLocation(int x, int y, int z, String location, String hint) {
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