package org.necrotic.client;

import org.necrotic.client.cache.definition.ItemDefinition;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ItemStats {

	public static final int STAB = 0;
	public static final int SLASH = 1;
	public static final int CRUSH = 2;
	public static final int MAGIC = 3;
	public static final int RANGED = 4;

	public static ItemStats[] itemstats = new ItemStats[ItemDefinition.totalItems > 0 ? ItemDefinition.totalItems : 25000];

	public int itemId;
	public int[] attackBonus;
	public int[] defenceBonus;
	public int prayerBonus;
	public int rangeBonus;
	public int strengthBonus;
	public int magicBonus;
	public int healAmount;
	public int type;
	public int[][] rewards;
	public String information;

	public ItemStats(int id, int typeOfStat) {
		this.itemId = id;
		this.attackBonus = new int[]{0, 0, 0, 0, 0};
		this.defenceBonus = new int[]{0, 0, 0, 0, 0};
		this.prayerBonus = 0;
		this.strengthBonus = 0;
		this.rangeBonus = 0;
		this.magicBonus = 0;
		this.healAmount = 0;
		this.type = typeOfStat;
	}

	private static int readType = 0;

	public static void readDefinitions() {
		try {
			File file = new File(Signlink.getCacheDirectory() + "itemstats.dat");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				if (line.equals("[STATS]")) {
					readType = 1;
					continue;
				}
				if (readType == 1) {
					String[] data = line.split(" ");
					int slot = 0;
					int id = Integer.parseInt(data[slot++]);
					itemstats[id] = new ItemStats(id, readType);
					for (int i = 0; i < 5; ++i) {
						itemstats[id].attackBonus[i] = Integer.parseInt(data[slot++]);
					}
					for (int i = 0; i < 5; ++i) {
						itemstats[id].defenceBonus[i] = Integer.parseInt(data[slot++]);//the problem is that it doesnt print out 12 things
						//like 0 0 0 0 0 0 0 0 0 it has do to that for every single item
					}
					itemstats[id].strengthBonus = Integer.parseInt(data[slot++]);
					itemstats[id].rangeBonus = Integer.parseInt(data[slot++]);
					itemstats[id].prayerBonus = Integer.parseInt(data[slot++]);
					itemstats[id].magicBonus = Integer.parseInt(data[slot++]);
				}
			}
			fileReader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
