package org.necrotic.client;

import java.util.HashMap;
import java.util.Map;

public class OldschoolMaps {

	private static final Map<Integer, Integer> OLDSCHOOL_REGIONS = new HashMap<>();

	public static int[] OLDSCHOOL_REGION_IDS = {};
	static {
		for (Integer region : OLDSCHOOL_REGION_IDS) {
			OLDSCHOOL_REGIONS.put(region, -1);
		}
	}

	public static boolean isOldschoolRegion(int regionId) {
		return OLDSCHOOL_REGIONS.get(regionId) != null;
	}
}