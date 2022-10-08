package com.ruse.world.content.chambersOfAnima;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.RandomUtility;
import com.ruse.world.World;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.CasketOpening;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.entity.impl.player.Player;

import java.util.HashMap;
import java.util.Map;

public class ChambersOfAnimaRewards {

    public static int KEY_ID = 23859;
    public static int DEFAULT_RARE_RATE = 1000;
    public static int INTERVAL = 50;
    public static int MAX = 10;

    public static Box[] loot = {
            new Box(15288, 4, 100),
            new Box(5022, 250000, 100),
            new Box(7956, 7500, 70),
            new Box(19114, 75, 100),
            new Box(20488, 10, 100),

            new Box(11137, 75, 70),
            new Box(20489, 1, 70),
            new Box(15358, 1, 70),
            new Box(15359, 1, 70),
            new Box(15288, 10, 70),

            new Box(10946, 1, 15),
            new Box(19886, 1, 15),
            new Box(4446, 1, 15),
            new Box(8087, 1, 15),
            new Box(8088, 1, 15),
            new Box(8089, 1, 15),
            new Box(22006, 50, 15),

            new Box(15003, 1, 1, true),
            new Box(6769, 1, 1, true),

    };

    public static Box[] rare = {
            new Box(23860, 1, 100),
            new Box(23861, 1, 100),
            new Box(23862, 1, 100),
            new Box(23870, 1, 100),
            new Box(23871, 1, 100),
            new Box(23872, 1, 100),
            new Box(23876, 1, 100),
            new Box(23877, 1, 100),
            new Box(23878, 1, 100),

            new Box(23885, 1, 100),
            new Box(23886, 1, 100),
            new Box(23887, 1, 100),

            new Box(23314, 1, 100),
            new Box(23477, 1, 100),
            new Box(22110, 1, 100)
    };

    public static void openInterface(Player player) {
        player.getPacketSender().sendString(118002, "Chambers of Anima");

        player.getPacketSender().sendItemOnInterface(118008, KEY_ID, 1);
        player.getPacketSender().sendString(118009, "Anima Key");

        player.getPacketSender().sendString(118007, "Rare: (1/" + getRareRate(player) + ")");

        int inter = 118101;
        for (int i = 0; i < 60; i++) {
            Box box = loot.length > i ? loot[i] : new Box(-1, 1);
            player.getPacketSender().sendItemOnInterface(inter++, box.getId(), box.getAmount());
        }

        inter = 118501;
        for (int i = 0; i < 15; i++) {
            Box box = rare.length > i ? rare[i] : new Box(-1, 1);
            player.getPacketSender().sendItemOnInterface(inter++, box.getId(), box.getAmount());
        }
        player.getPacketSender().setScrollBar(118500, 188);

        player.getPacketSender().sendInterface(118000);
    }

    public static int getRareRate(Player player) {
        return DEFAULT_RARE_RATE - (player.getAnimaRareDropBoost() * INTERVAL);
    }

    public static void open(Player player) {
        if (player.getInventory().contains(KEY_ID)) {
            Box reward;
            int chance = RandomUtility.inclusiveRandom(1, getRareRate(player));
            if (chance == 1) {
                reward = CasketOpening.getLoot1(rare);
                String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                        + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from the @red@Chambers of Anima"
                        + "@cya@ - <col=ff4f4f>KC: " +player.getAnimaKeysOpened();
                World.sendMessage1(message);
            } else {
                reward = CasketOpening.getLoot1(loot);
            }
            player.setAnimaKeysOpened(player.getAnimaKeysOpened() + 1);
            player.getInventory().delete(KEY_ID, 1);

            boolean bank = 1 <= player.getInventory().getFreeSlots();
                if (bank) {
                    player.getInventory().add(reward.getId(), reward.getAmount());
                } else {
                    player.depositItemBank(new Item(reward.getId(), reward.getAmount()), false);
                }
            player.getCollectionLogManager().addItem(CollectionLogs.CHAMBERS_OF_ANIMA, new Item(reward.getId(), reward.getAmount()));
        } else {
            player.sendMessage("You need a " + ItemDefinition.forId(KEY_ID).getName() + " to do this.");
        }
    }


    public static void openAll(Player player) {
        if (player.getInventory().contains(KEY_ID)) {
            int amount = player.getInventory().getAmount(KEY_ID);
            Map<Box, Integer> rewards = new HashMap<>();
            for (int i = 0; i < amount; i++) {
                player.setAnimaKeysOpened(player.getAnimaKeysOpened() + 1);
                Box reward;
                int chance = RandomUtility.inclusiveRandom(1, getRareRate(player));
                if (chance == 1) {
                    reward = CasketOpening.getLoot1(rare);
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                            + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from the @red@Chambers of Anima"
                            + "@cya@ - <col=ff4f4f>KC: " +player.getAnimaKeysOpened();
                    World.sendMessage1(message);
                } else {
                    reward = CasketOpening.getLoot1(loot);
                }
                rewards.merge(reward, 1, Integer::sum);
            }
            player.getInventory().delete(KEY_ID, amount);
            boolean bank = amount <= player.getInventory().getFreeSlots();
            rewards.forEach((key, value) -> {
                player.getCollectionLogManager().addItem(CollectionLogs.CHAMBERS_OF_ANIMA, new Item(key.getId(), key.getAmount() * value), value);
                if (bank) {
                    player.getInventory().add(new Item(key.getId(), key.getAmount() * value), false);
                } else {
                    Item item = new Item(key.getId(), key.getAmount() * value);
                    player.depositItemBank(item, false);
                }
            });
            player.getInventory().refreshItems();

            if (!bank) {
                player.sendMessage("@blu@Your rewards have been added to your bank.");
            }
        } else {
            player.sendMessage("You need a " + ItemDefinition.forId(KEY_ID).getName() + " to do this.");
        }
    }

}
