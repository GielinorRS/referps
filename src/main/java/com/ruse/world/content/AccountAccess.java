package com.ruse.world.content;

import com.google.gson.*;
import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.collectionlogs.CollectionLogManager;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.leaderboards.Leaderboard;
import com.ruse.world.content.leaderboards.LeaderboardData;
import com.ruse.world.content.titles.Titles;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class AccountAccess {

    //	public static void main(String[] params) {
    //
    //		//PASSWORDS
    //
    //
    //		HashMap<String, String> itemss = AccountAccess.searchAccountsPasswords();
    //
    //		for (String d : itemss.keySet()) {
    //			try {
    //				FileWriter fw = new FileWriter("./data/saves/passes" + ".txt", true);
    //				if (fw != null) {
    //					if (itemss.get(d) != null) {
    //						fw.write("Name: " + d + ", Password: " + itemss.get(d).toString());
    //						fw.write(System.lineSeparator());
    //					}
    //					fw.close();
    //				}
    //			} catch (Exception e) {
    //				e.printStackTrace();
    //			}
    //		}
    //	}

    //	public static void main(String[] params) {
    //
    //		// ACC VALUES
    //
    //		HashMap<String, Long> objects1 = AccountAccess.searchAccounts();
    //		HashMap<String, Long> objects = new HashMap<String, Long>();
    //
    //		for (String d : objects1.keySet()) {
    //			if (!objects.containsValue(objects1.get(d)))
    //				objects.put(d, objects1.get(d));
    //		}
    //
    //		List<Long> wealths = new ArrayList<Long>(objects.values());
    //
    //		Collections.sort(wealths, new Comparator<Long>() {
    //
    //			public int compare(Long o1, Long o2) {
    //				return (int) (o2 - o1);
    //			}
    //		});
    //
    //		int offset = 0;
    //		for (String d : objects.keySet()) {
    //			final int offset1 = offset;
    //			System.out.println(Misc.insertCommasToNumber(wealths.get(offset1) + "") + " - "
    //					+ Utils.getKeysByValue(objects, wealths.get(offset1)).toString().replace("[", "").replace("]", ""));
    //			offset++;
    //		}
    //
    //	}

    //	public static void main(String[] params) {
    //
    //		//MIN ITEM VALUE
    //		HashMap<String, Item> itemss = AccountAccess.searchAccountsItems(1_000_000_000);
    //
    //		for (String d : itemss.keySet()) {
    //			System.out.println(d + " - Name: " + ItemDefinition.forId(itemss.get(d).getId()).getName() + ", Id: "
    //					+ itemss.get(d).getId() + ", Amount: " + Misc.insertCommasToNumber(itemss.get(d).getAmount() + ""));
    //		}
    //	}


    @AllArgsConstructor
    @Getter
    public static class ItemThing {
        private String name;
        private Item item;
    }

    public static void check(Player player, int item) {
        // SEARCH ITEM
        HashMap<String, Item> itemss = AccountAccess.searchAccountsItemId(item);
        player.sendMessage("itemss: " + itemss.size());

        ArrayList<ItemThing> listts = new ArrayList<>();

        for (String name : itemss.keySet()) {
            listts.add(new ItemThing(name, itemss.get(name)));
        }

        Collections.sort(listts, (p, p1) -> {
            if (p.getItem().getAmount() == p1.getItem().getAmount()) {
                return 0;
            } else if (p.getItem().getAmount() > p1.getItem().getAmount()) {
                return -1;
            } else {
                return 1;
            }
        });

        long total = 0;
        for (ItemThing d : listts) {
            if (d != null && d.getItem() != null && d.getItem().getAmount() >= 1) {
                total += d.getItem().getAmount();
                player.sendMessage(d.getName() + " - Name: " + ItemDefinition.forId(d.getItem().getId()).getName() + ", Id: " + d.getItem().getId() +
                        ", Amount: " + Misc.insertCommasToNumber(d.getItem().getAmount() + ""));
                System.out.println(d.getName() + " - Name: " + ItemDefinition.forId(d.getItem().getId()).getName() + ", Id: " + d.getItem().getId() +
                        ", Amount: " + Misc.insertCommasToNumber(d.getItem().getAmount() + ""));
            }
        }
        player.sendMessage("total: " + total);
        System.out.println("total: " + total);

    }

    public static ArrayList<ItemThing> check(int item) {
        // SEARCH ITEM
        HashMap<String, Item> itemss = AccountAccess.searchAccountsItemId(item);

        ArrayList<ItemThing> listts = new ArrayList<>();

        for (String name : itemss.keySet()) {
            listts.add(new ItemThing(name, itemss.get(name)));
        }

        Collections.sort(listts, (p, p1) -> {
            if (p.getItem().getAmount() == p1.getItem().getAmount()) {
                return 0;
            } else if (p.getItem().getAmount() > p1.getItem().getAmount()) {
                return -1;
            } else {
                return 1;
            }
        });
        return listts;
    }

    public static HashMap<String, Long> searchAccounts() {
        HashMap<String, Long> values = new HashMap<String, Long>();
        File[] files = new File("./data/saves/charactersTest/").listFiles();
        for (File file : files) {
            values.put(file.getName().replace(".json", ""), getWealth(file.getName().replace(".json", "")));
        }


        int total = 0;
        for (String d : values.keySet()) {
            if (values.get(d) != null && values.get(d) >= 1) {
                total += values.get(d);
                System.out.println(d + ", Amount: " + Misc.insertCommasToNumber(values.get(d) + ""));
            }
        }
        System.out.println("total: " + total);
        return values;
    }

    public static HashMap<String, Item> searchAccountsItems(int amount) {
        HashMap<String, Item> values = new HashMap<String, Item>();
        File[] files = new File("./data/saves/characters/").listFiles();
        for (File file : files) {
            values.put(file.getName().replace(".json", ""), getItems(file.getName().replace(".json", ""), amount));
        }
        return values;
    }

    public static HashMap<String, Item> searchAccountsItemId(int id) {
        HashMap<String, Item> values = new HashMap<String, Item>();
        File[] files = new File("./data/saves/characters/").listFiles();
        for (File file : files) {
            if (getItemId(file.getName().replace(".json", ""), id) != null)
                values.put(file.getName().replace(".json", ""), getItemId(file.getName().replace(".json", ""), id));
        }
        return values;
    }

    public static HashMap<String, String> searchAccountsPasswords() {
        HashMap<String, String> values = new HashMap<String, String>();
        File[] files = new File("./data/saves/characters/").listFiles();
        for (File file : files) {
            values.put(file.getName().replace(".json", ""), getPassword(file.getName().replace(".json", "")));
        }
        return values;
    }

    public static String getPassword(String name) {

        // Create the path and file objects.
        Path path = Paths.get("./data/saves/characters/", name + ".json");
        File file = path.toFile();

        // If the file doesn't exist, we're logging in for the first
        // time and can skip all of this.
        if (!file.exists()) {
            return null;
        }

        // Now read the properties from the json parser.
        try (FileReader fileReader = new FileReader(file)) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            Object object = fileParser.parse(fileReader);

            if (object instanceof JsonNull) {
                return null;
            }

            JsonObject reader = (JsonObject) object;

            String pass = null;

            if (reader.has("password")) {
                String password = reader.get("password").getAsString();
                pass = password;
            }
            return pass;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static long getWealth(String name) {

        // Create the path and file objects.
        Path path = Paths.get("./data/saves/charactersTest/", name + ".json");
        File file = path.toFile();

        // If the file doesn't exist, we're logging in for the first
        // time and can skip all of this.
        if (!file.exists()) {
            return -1;
        }

        // Now read the properties from the json parser.
        try (FileReader fileReader = new FileReader(file)) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            Object object = fileParser.parse(fileReader);

            if (object instanceof JsonNull) {
                return -1;
            }

            JsonObject reader = (JsonObject) object;

            long money = 0;

            if (reader.has("money-pouch")) {
                money += reader.get("money-pouch").getAsLong() / 1_000_000_000L;
            }
            if (reader.has("inventory")) {
                Item[] items = (builder.fromJson(reader.get("inventory").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("equipment")) {
                Item[] items = (builder.fromJson(reader.get("equipment").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-0")) {
                Item[] items = (builder.fromJson(reader.get("bank-0").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-1")) {
                Item[] items = (builder.fromJson(reader.get("bank-1").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-2")) {
                Item[] items = (builder.fromJson(reader.get("bank-2").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-3")) {
                Item[] items = (builder.fromJson(reader.get("bank-3").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-4")) {
                Item[] items = (builder.fromJson(reader.get("bank-4").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-5")) {
                Item[] items = (builder.fromJson(reader.get("bank-5").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-6")) {
                Item[] items = (builder.fromJson(reader.get("bank-6").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-7")) {
                Item[] items = (builder.fromJson(reader.get("bank-7").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            if (reader.has("bank-8")) {
                Item[] items = (builder.fromJson(reader.get("bank-8").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == 13664)
                        money += item.getAmount();
                }
            }
            return money;

        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Item getItems(String name, int amount) {

        Path path = Paths.get("./data/saves/characters/", name + ".json");
        File file = path.toFile();

        if (!file.exists()) {
            return null;
        }

        // Now read the properties from the json parser.
        try (FileReader fileReader = new FileReader(file)) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            Object object = fileParser.parse(fileReader);

            if (object instanceof JsonNull) {
                return null;
            }

            JsonObject reader = (JsonObject) object;

            if (reader.has("inventory")) {
                Item[] items = (builder.fromJson(reader.get("inventory").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("equipment")) {
                Item[] items = (builder.fromJson(reader.get("equipment").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }

            if (reader.has("bank-0")) {
                Item[] items = (builder.fromJson(reader.get("bank-0").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-1")) {
                Item[] items = (builder.fromJson(reader.get("bank-1").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-2")) {
                Item[] items = (builder.fromJson(reader.get("bank-2").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-3")) {
                Item[] items = (builder.fromJson(reader.get("bank-3").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-4")) {
                Item[] items = (builder.fromJson(reader.get("bank-4").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-5")) {
                Item[] items = (builder.fromJson(reader.get("bank-5").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-6")) {
                Item[] items = (builder.fromJson(reader.get("bank-6").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-7")) {
                Item[] items = (builder.fromJson(reader.get("bank-7").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }
            if (reader.has("bank-8")) {
                Item[] items = (builder.fromJson(reader.get("bank-8").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getAmount() * ItemDefinition.forId(item.getId()).getValue() >= amount || item.getAmount() * ItemDefinition.forId(item.getId()).getValue() <= -1)
                        return item;
                }
            }

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Item getItemId(String name, int id) {

        Path path = Paths.get("./data/saves/characters/", name + ".json");
        File file = path.toFile();

        if (!file.exists()) {
            return null;
        }

        // Now read the properties from the json parser.
        try (FileReader fileReader = new FileReader(file)) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            Object object = fileParser.parse(fileReader);

            if (object instanceof JsonNull) {
                return null;
            }

            JsonObject reader = (JsonObject) object;

            int amount = 0;

            if (reader.has("staff-rights")) {
                String rights = reader.get("staff-rights").getAsString();
                if (rights.equalsIgnoreCase("youtuber")
                        || rights.equalsIgnoreCase("developer"))
                    return null;
            }


            if (reader.has("inventory")) {
                Item[] items = (builder.fromJson(reader.get("inventory").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("equipment")) {
                Item[] items = (builder.fromJson(reader.get("equipment").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }

            if (reader.has("bank-0")) {
                Item[] items = (builder.fromJson(reader.get("bank-0").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-1")) {
                Item[] items = (builder.fromJson(reader.get("bank-1").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-2")) {
                Item[] items = (builder.fromJson(reader.get("bank-2").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-3")) {
                Item[] items = (builder.fromJson(reader.get("bank-3").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-4")) {
                Item[] items = (builder.fromJson(reader.get("bank-4").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-5")) {
                Item[] items = (builder.fromJson(reader.get("bank-5").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-6")) {
                Item[] items = (builder.fromJson(reader.get("bank-6").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-7")) {
                Item[] items = (builder.fromJson(reader.get("bank-7").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }
            if (reader.has("bank-8")) {
                Item[] items = (builder.fromJson(reader.get("bank-8").getAsJsonArray(), Item[].class));
                for (Item item : items) {
                    if (item.getId() == id)
                        amount += item.getAmount();
                }
            }


            if (reader.has("kills")) {
                KillsTracker.KillsEntry[] kills = builder.fromJson(reader.get("kills").getAsJsonArray(), KillsTracker.KillsEntry[].class);
                int total = 0;
                for (KillsTracker.KillsEntry kill : kills) {
                    if (kill != null) {
                        total += kill.getRunningTotal();
                        if (LeaderboardData.forNpcId(kill.getNpcId()) != null) {
                            Leaderboard.DATA.get(LeaderboardData.forNpcId(kill.getNpcId())).put(name, (long) kill.getAmount());
                        }
                    }
                }
                Leaderboard.DATA.get(LeaderboardData.NPC_KILLCOUNT).put(name, (long) total);
            }

            if (reader.has("total-play-time-ms")) {
                long total = reader.get("total-play-time-ms").getAsLong();
                if (total >= 1_000_000_000_000L)
                    total = 0;
                Leaderboard.DATA.get(LeaderboardData.TIME_PLAYED).put(name, total);
            }
            if (reader.has("donated")) {
                Leaderboard.DATA.get(LeaderboardData.DONATED).put(name, (long) reader.get("donated").getAsInt());
            }
            if (reader.has("collection-log")) {
                CollectionLogManager.LogProgress[] logs = builder.fromJson(reader.get("collection-log").getAsJsonArray(), CollectionLogManager.LogProgress[].class);
                int total = 0;
                for (CollectionLogManager.LogProgress log : logs) {
                    if (log != null) {
                        if (log.getName().equalsIgnoreCase("TREASURE_HUNTER")) {
                            Leaderboard.DATA.get(LeaderboardData.TREASURE_HUNTER).put(name, (long) log.getCompleted());
                        }
                        if (CollectionLogs.forName(log.getName()) != null)
                            if (log.getObtainedItems().size()
                                    == CollectionLogs.forName(log.getName()).getUniqueItems().size()) {
                                total++;
                            }
                    }
                }
                Leaderboard.DATA.get(LeaderboardData.COLLECTION_LOGS_COMPLETED).put(name, (long) total);
            }


            if (reader.has("unlocked-titles")) {
                Titles titles[] = builder.fromJson(reader.get("unlocked-titles").getAsJsonArray(), Titles[].class);
                Leaderboard.DATA.get(LeaderboardData.TITLES_UNLOCKED).put(name, (long) titles.length);
            }

            if (reader.has("easy-task-kc")) {
                Leaderboard.DATA.get(LeaderboardData.EASY_SLAYER_TASKS).put(name, (long) reader.get("easy-task-kc").getAsInt());
            }
            if (reader.has("med-task-kc")) {
                Leaderboard.DATA.get(LeaderboardData.MED_SLAYER_TASKS).put(name, (long) reader.get("med-task-kc").getAsInt());
            }
            if (reader.has("hard-task-kc")) {
                Leaderboard.DATA.get(LeaderboardData.HARD_SLAYER_TASKS).put(name, (long) reader.get("hard-task-kc").getAsInt());
            }
            if (reader.has("boss-task-kc")) {
                Leaderboard.DATA.get(LeaderboardData.BOSS_SLAYER_TASKS).put(name, (long) reader.get("boss-task-kc").getAsInt());
            }
            if (reader.has("sanctum-kc")) {
                Leaderboard.DATA.get(LeaderboardData.SANCTUM_OF_DEATH).put(name, (long) reader.get("sanctum-kc").getAsInt());
            }


            if (amount >= 1)
                return new Item(id, amount);

            return null;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
