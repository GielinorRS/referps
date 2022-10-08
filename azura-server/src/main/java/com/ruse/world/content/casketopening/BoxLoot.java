package com.ruse.world.content.casketopening;

import com.ruse.util.Misc;

import java.util.ArrayList;
import java.util.HashMap;

public class BoxLoot {
	public static Box getLoot(Box[] loot) {

		Box[] possibleDrops = new Box[loot.length];
		int possibleDropsCount = 0;
		for (Box drop : loot) {
			double rate = drop.getRate() * 100;
			if (Misc.getRandom(10000) <= rate) {
				possibleDrops[possibleDropsCount++] = drop;
			}
		}

		if (possibleDropsCount > 0) {
			return possibleDrops[Misc.getRandom((possibleDropsCount - 1))];
		} else {
			return loot[Misc.getRandom((possibleDropsCount - 1))];
		}
	}


	public static Box getLootDropTables(Box[] loot) {
		HashMap<Double, ArrayList<Box>> dropRates = new HashMap<>();
		ArrayList<Box> potentialDrops = new ArrayList<>();

		for (Box drop : loot) {
			if (drop == null)
				continue;
			double divisor = drop.getRate();
			if (!dropRates.containsKey(divisor)) {
				ArrayList<Box> items = new ArrayList<>();
				items.add(drop);
				dropRates.put(divisor, items);
			} else {
				dropRates.get(divisor).add(drop);
			}
		}
		for (double dropRate : dropRates.keySet()) {
			double rate = dropRate * 1000;
			if (Misc.getRandom(100000) <= rate) {
				potentialDrops.add(dropRates.get(dropRate).get(Misc.getRandom(dropRates.get(dropRate).size() - 1)));
			}
		}

		if (potentialDrops.size() > 0) {
			return potentialDrops.get(Misc.getRandom((potentialDrops.size() - 1)));
		} else {
			return getLoot(loot);
		}
	}

}
