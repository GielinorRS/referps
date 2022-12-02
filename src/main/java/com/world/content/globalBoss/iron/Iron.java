package com.world.content.globalBoss.iron;

import com.ruse.model.Position;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;


public class Iron extends NPC {

	private static long massMessageTimer = 0;

	public static boolean bossAlive = false;
	public static int rng = 0, NPC_ID = 9014;
	public static long INTERVAL = TimeUnit.HOURS.toMillis(2);

	public static final IronLocation[] LOCATIONS = {
			new IronLocation(3808, 2849, 0, "::iron", "::iron"),
			new IronLocation(3808, 2849, 0, "::iron", "::iron"),
			new IronLocation(3808, 2849, 0, "::iron", "::iron"),
			new IronLocation(3808, 2849, 0, "::iron", "::iron") };


	private static Iron current;

	public Iron(Position position) {
		super(NPC_ID, position);
	}

	public static String getTimeLeft() {
		long ms = (INTERVAL - (System.currentTimeMillis() - massMessageTimer));
		return String.format("%d hrs, %d mins, %d secs", TimeUnit.MILLISECONDS.toHours(ms),
				TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
				TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
	}

	public static void initialize() {
		if (massMessageTimer == 0 || (System.currentTimeMillis() - massMessageTimer >= INTERVAL)) {
			massMessageTimer = System.currentTimeMillis();
			spawn();
		}

	}


	public static void spawncommand() {
		rng = Misc.randomMinusOne(LOCATIONS.length);
		IronLocation location = LOCATIONS[rng];
		Iron instance = new Iron(location.copy());
		World.register(instance);
		setCurrent(instance);
			World.sendMessage("<col=1e56ad><img=5> [Iron Event]@bla@ Iron is roaming "
					+ LOCATIONS[rng].getLocation() + "!");
	}

	public static void spawn() {
		if (bossAlive) {
			World.sendMessage("<col=1e56ad><img=5> [Iron Event]@bla@ Iron is roaming "
					+ LOCATIONS[rng].getLocation() + "!");
			return;
		}
		rng = Misc.randomMinusOne(LOCATIONS.length);
		IronLocation location = LOCATIONS[rng];
		Iron instance = new Iron(location.copy());

		World.register(instance);
		setCurrent(instance);
		bossAlive = true;
		World.sendMessage("<col=1e56ad><img=5> [Iron Event]@bla@ Iron is alive "
				+ location.getLocation() + "!");

	}

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

		for (Entry<Player, Long> entry : result) {

			Player killer = entry.getKey();

			NPCDrops.dropItems(killer, npc);

			

		}

	}

	static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {

		List<Entry<K, V>> sortedEntries = new ArrayList<>(map.entrySet());

		sortedEntries.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

		return sortedEntries;

	}

	public static Iron getCurrent() {
		return current;
	}

	public static void setCurrent(Iron current) {
		Iron.current = current;
	}


	public static class IronLocation extends Position {

		private String location, hint;

		public IronLocation(int x, int y, int z, String location, String hint) {
			super(x, y, z);
			setLocation(location);
			setHint(hint);
		}

		String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public void setHint(String hint) {
			this.hint = hint;
		}

	}

}