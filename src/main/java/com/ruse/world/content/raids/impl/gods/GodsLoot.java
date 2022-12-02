package com.ruse.world.content.raids.impl.gods;


import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Cases;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.raids.RaidDifficulty;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class GodsLoot {


    public static void handleLoot(Player player, RaidDifficulty difficulty) {

        Box reward = getLoot(player, difficulty == RaidDifficulty.ADVANCED ? HARD :
                difficulty == RaidDifficulty.INTERMEDIATE ? MEDIUM : EASY);

        player.getCollectionLogManager().addItem(CollectionLogs.SARADOMIN, new Item(reward.getId(), reward.getAmount()));
        player.getCollectionLogManager().addItem(CollectionLogs.ZAMORAK, new Item(reward.getId(), reward.getAmount()));

        if (reward.isAnnounce()) {
            String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                    + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from the @red@Isle of the Gods"
                    + "@cya@ - <col=ff4f4f>KC: " + KillsTracker.getTotalKillsForNpc(10000, player);
            World.sendMessage1(message);
        }

        Cases.grantCasket(player, 5);

        addToCoffer(player, new Item(23631 , (difficulty.ordinal() + 1) * 2));
        addToCoffer(player, new Item(reward.getId(), reward.getAmount()));

    }

    public static void addToCoffer(Player player, Item item) {
        boolean add = true;
        for (Item item1 : player.getGodsCoffer()) {
            if (item1.getId() == item.getId() && item.getDefinition().isStackable()) {
                item1.setAmount(item1.getAmount() + item.getAmount());
                add = false;
            }
        }
        if (add)
            player.getGodsCoffer().add(item);
    }


    public static Box[] SARADOMIN = {
            new Box(23629, 1, 0.2, true),
            new Box(23627, 1, 0.1, true),
            new Box(23632, 1, 0.02, true),
            new Box(23634, 1, 0.02, true),
            new Box(23636, 1, 0.02, true),
            new Box(23658, 1, 0.01, true),
    };
    public static Box[] ZAMORAK = {
            new Box(23630, 1, 0.2, true),
            new Box(23628, 1, 0.1, true),
            new Box(23633, 1, 0.02, true),
            new Box(23635, 1, 0.02, true),
            new Box(23637, 1, 0.02, true),
            new Box(23659, 1, 0.01, true),
    };

    public static Box[] ALL_LOOT = {
            new Box(23631, 1, 100),

            new Box(23629, 1, 0.2, true),
            new Box(23630, 1, 0.2, true),

            new Box(23627, 1, 0.1, true),
            new Box(23628, 1, 0.1, true),
            new Box(23644, 1, 100),

            new Box(23632, 1, 0.02, true),
            new Box(23634, 1, 0.02, true),
            new Box(23636, 1, 0.02, true),
            new Box(23633, 1, 0.02, true),
            new Box(23635, 1, 0.02, true),
            new Box(23637, 1, 0.02, true),
            new Box(23658, 1, 0.01, true),
            new Box(23659, 1, 0.01, true),
    };


    public static Box[] EASY = {
            new Box(23631, 2, 100),

            new Box(12855, 15000, 50),
            new Box(15289, 1, 50),
            new Box(15290, 5, 50),
            new Box(5022, 10000, 50),
            new Box(7956, 200, 50),
            new Box(23480, 50, 50),
            new Box(19115, 20, 50),
            new Box(19114, 2, 50),
            new Box(20488, 1, 50),
            new Box(23515, 2, 50),

            new Box(11137, 10, 50),
            new Box(15288, 1, 5),

            new Box(22006, 5, 5),

            new Box(10946, 1, 1, true),

            new Box(15003, 1, 0.4, true),

            new Box(23629, 1, 0.2, true),
            new Box(23630, 1, 0.2, true),
    };


    public static Box[] MEDIUM = {
            new Box(23631, 4, 100),

            new Box(12855, 40000, 50),
            new Box(15289, 2, 50),
            new Box(15290, 10, 50),
            new Box(5022, 20000, 50),
            new Box(7956, 500, 50),
            new Box(23480, 100, 50),
            new Box(19115, 50, 50),
            new Box(19114, 5, 50),
            new Box(20488, 2, 50),
            new Box(23515, 4, 50),

            new Box(23516, 10, 10),
            new Box(11137, 20, 10),
            new Box(15358, 1, 10),
            new Box(15359, 1, 10),

            new Box(15288, 2, 5),

            new Box(22006, 10, 5),

            new Box(10946, 3, 1, true),

            new Box(15002, 1, 0.4, true),

            new Box(23627, 1, 0.2, true),
            new Box(23628, 1, 0.2, true),
    };


    public static Box[] HARD = {
            new Box(23631, 6, 100),

            new Box(12855, 75000, 50),
            new Box(15290, 1, 50),
            new Box(15289, 4, 50),
            new Box(5022, 50000, 50),
            new Box(7956, 1000, 50),
            new Box(23480, 250, 50),
            new Box(19115, 100, 50),
            new Box(19114, 10, 50),
            new Box(20488, 3, 50),
            new Box(23515, 8, 50),


            new Box(23516, 20, 10),
            new Box(11137, 30, 10),
            new Box(15358, 2, 10),
            new Box(15359, 2, 10),
            new Box(15356, 1, 10),


            new Box(15288, 3, 5),
            new Box(22006, 20, 5),


            new Box(6769, 1, 1, true),

            new Box(23644, 1, 1, true),

            new Box(15004, 1, 0.25, true),

            new Box(23632, 1, 0.05, true),
            new Box(23634, 1, 0.05, true),
            new Box(23636, 1, 0.05, true),
            new Box(23633, 1, 0.05, true),
            new Box(23635, 1, 0.05, true),
            new Box(23637, 1, 0.05, true),


            new Box(23658, 1, 0.02, true),
            new Box(23659, 1, 0.02, true),
    };


    public static Box getLoot(Player player, Box[] loot) {

        HashMap<Double, ArrayList<Box>> dropRates = new HashMap<>();
        ArrayList<Box> potentialDrops = new ArrayList<>();

        for (Box drop : loot) {
            if (drop == null)
                continue;
            double divisor = drop.getRate();

            if (divisor <= 1){
                double drBoost = (100 + player.getIsleDropRate()) / 100D;
                divisor *= drBoost;
            }

            if (divisor != 100) {
                if (!dropRates.containsKey(divisor)) {
                    ArrayList<Box> items = new ArrayList<>();
                    items.add(drop);
                    dropRates.put(divisor, items);
                } else {
                    dropRates.get(divisor).add(drop);
                }
            }

        }
        for (double dropRate : dropRates.keySet()) {
            double rate = dropRate * 1000;
            if (Misc.getRandom(100000) <= rate) {
                potentialDrops.add(dropRates.get(dropRate).get(Misc.getRandom(dropRates.get(dropRate).size() - 1)));
            }
        }

        if (potentialDrops.size() > 0) {
            Box reward = potentialDrops.get(Misc.getRandom((potentialDrops.size() - 1)));
            if ((reward.getId() == 15358
                    || reward.getId() == 15359
                    || reward.getId() == 15356)  && Misc.getRandom(1) == 0){
                reward = getLoot(player, loot);
            }

            return reward;
        } else {
            return getLoot(player, loot);
        }
    }

}
