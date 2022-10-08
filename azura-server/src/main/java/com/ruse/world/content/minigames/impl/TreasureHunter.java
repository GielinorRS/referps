package com.ruse.world.content.minigames.impl;

import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.World;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.BoxLoot;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.entity.impl.player.Player;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

public class TreasureHunter {

    public static final Position TELEPORT_AREA = new Position(2015, 5022, 0);
    public static final int CHEST_ID = 41207;

    public static int NPC_1 = 9815, NPC_2 = 9816, NPC_3 = 9817, NPC_4 = 9818;
    public static int KEY_1 = 23113;
    public static int KEY_2 = 23114;
    public static int KEY_3 = 23115;
    public static int KEY_4 = 23116;
    public static Item MASTER_KEY = new Item(23117);
    private HashMap<Item, Integer> auraDrops = new HashMap<>();

    public static boolean combineKeys(Player player, Item used, Item usedWith) {
        if (used.getId() >= KEY_1 && used.getId() <= KEY_4 && usedWith.getId() >= KEY_1 && usedWith.getId() <= KEY_4) {
            if (player.getInventory().contains(new Item[]{new Item(KEY_1), new Item(KEY_2), new Item(KEY_3), new Item(KEY_4)})) {
                Integer[] amounts = new Integer[]{player.getInventory().getAmount(KEY_1),
                        player.getInventory().getAmount(KEY_2),
                        player.getInventory().getAmount(KEY_3),
                        player.getInventory().getAmount(KEY_4)};

                int min = Collections.min(Arrays.asList(amounts));
                
                player.getInventory().deleteItemSet(new Item[]{new Item(KEY_1, min), new Item(KEY_2, min), new Item(KEY_3, min), new Item(KEY_4, min)});
                player.getInventory().add(MASTER_KEY.getId(), min);
                player.sendMessage("You've successfully created x"+min+" Master Key.");
                return true;
            }
        }
        return false;
    }

    public static void useKeyOnChest(Player player, Item item) {
        if (item.getId() == MASTER_KEY.getId() && player.getInventory().contains(MASTER_KEY.getId())) {
            player.getInventory().delete(MASTER_KEY);

            handleRewards(player);
        }
    }

    /**
     * Returns the Chest Rewards
     *
     * @param player
     */
    public static void handleRewards(Player player) {
      //  for (int i = 0 ; i < 100000; i ++){
            Box box = BoxLoot.getLootDropTables(loot);

                player.getInventory().add(box.getId(), box.getAmount());

        player.getCollectionLogManager().addItem(CollectionLogs.TREASURE_HUNTER, new Item(box.getId(), box.getAmount()));

                if (box.isAnnounce()) {
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has just received @red@"
                            + ItemDefinition.forId(box.getId()).getName() + "@blu@ from @red@Treasure Hunter";
                    World.sendMessage1(message);
                }
      //  }

        /*Achievements.doProgress(player, Achievements.Achievement.OPEN_5_CHESTS_IN_TREASURE_HUNTER);
        Achievements.doProgress(player, Achievements.Achievement.OPEN_25_CHESTS_IN_TREASURE_HUNTER);
        Achievements.doProgress(player, Achievements.Achievement.OPEN_200_CHESTS_IN_TREASURE_HUNTER);
        DailyTask.TREASURE_HUNTER.tryProgress(player);*/
    }


    public static Box[] loot = { //
            new Box(12855, 1000, 20000, 100),
            new Box(5022, 250, 7000, 100),
            new Box(15290, 2, 7, 35),
            new Box(10025, 1, 4, 35),
            new Box(7956, 1, 3, 35),
            new Box(19116, 1, 35),
            new Box(19115, 1, 35),
            new Box(20488, 1, 25),
            new Box(23044, 1, 25, false),
            new Box(6769, 1, 1, true),
            new Box(23162, 1, 0.75, true),
            new Box(23163, 1, 0.75, true),
            new Box(23161, 1, 0.75, true),
            new Box(23164, 1, 0.75, true),
            new Box(22110, 1, 0.005, true),

            /* new Box(23118, 1, 2D, true),
             new Box(23121, 1, 2D, true),
             new Box(23124, 1, 2D, true),*/
    };

}
