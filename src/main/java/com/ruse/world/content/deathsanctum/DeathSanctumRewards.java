package com.ruse.world.content.deathsanctum;

import com.ruse.model.Item;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.World;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.CasketOpening;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.entity.impl.player.Player;

import java.util.HashMap;
import java.util.Map;

public class DeathSanctumRewards {

    public static int KEY_ID = 23464;
    public static int DEFAULT_RARE_RATE = 1000;
    public static int INTERVAL = 50;
    public static int MAX = 10;

    public static Box[] loot = {
            new Box(15288, 2, 100),
            new Box(5022, 100000, 100),
            new Box(7956, 5000, 70),
            new Box(19114, 50, 100),
            new Box(20488, 5, 100),

            new Box(11137, 75, 70),
            new Box(20489, 1, 70),
            new Box(15358, 1, 70),
            new Box(15359, 1, 70),
            new Box(15288, 5, 70),

            new Box(23534, 20, 12),

            new Box(10946, 1, 15),
            new Box(19886, 1, 15),
            new Box(4446, 1, 15),
            new Box(8087, 1, 15),
            new Box(8088, 1, 15),
            new Box(8089, 1, 15),
            new Box(22006, 25, 15),

            new Box(15003, 1, 1, true),

    };

    public static Box[] rare = {
            new Box(23465, 1, 100),
            new Box(23466, 1, 100),
            new Box(23467, 1, 100),
            new Box(23468, 1, 100),
            new Box(23469, 1, 100),
            new Box(23470, 1, 100),
            new Box(23471, 1, 100),
            new Box(23472, 1, 100),
            new Box(23473, 1, 100),

            new Box(23210, 1, 100),
            new Box(23475, 1, 100),
            new Box(15288, 250, 100),
    };

    public static void openInterface(Player player) {
        player.getPacketSender().sendString(118002, "Sanctum of Death");
        player.getPacketSender().sendItemOnInterface(118008, KEY_ID, 1);
        player.getPacketSender().sendString(118009, "Sanctum Key");

        player.getPacketSender().sendString(118007, "Rare: (1/" + getRareRate(player) + ")");

        int inter = 118101;
        for (int i = 0; i < 60; i++) {
            Box box = loot.length > i ? loot[i] : new Box(-1, 1);
            player.getPacketSender().sendItemOnInterface(inter++, box.getId(), box.getAmount());
        }

        inter = 118501;
        for (int i = 0; i < 12; i++) {
            Box box = rare.length > i ? rare[i] : new Box(-1, 1);
            player.getPacketSender().sendItemOnInterface(inter++, box.getId(), box.getAmount());
        }
        player.getPacketSender().setScrollBar(118500, 148);

        player.getPacketSender().sendInterface(118000);
    }

    public static int getRareRate(Player player) {
        return DEFAULT_RARE_RATE - (player.getSanctumRareDropBoost() * INTERVAL);
    }

    public static void open(Player player) {
        if (player.getInventory().contains(KEY_ID)) {
            Box reward;
            int chance = RandomUtility.inclusiveRandom(1, getRareRate(player));
            if (chance == 1) {
                reward = CasketOpening.getLoot1(rare);
                String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                        + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from the @red@Sanctum of Death"
                        + "@cya@ - <col=ff4f4f>KC: " +player.getSanctumKeysOpened();
                World.sendMessage1(message);
            } else {
                reward = CasketOpening.getLoot1(loot);
            }
            player.setSanctumKeysOpened(player.getSanctumKeysOpened() + 1);
            player.getInventory().delete(KEY_ID, 1);

            boolean bank = 1 <= player.getInventory().getFreeSlots();
                if (bank) {
                    player.getInventory().add(reward.getId(), reward.getAmount());
                } else {
                    player.depositItemBank(new Item(reward.getId(), reward.getAmount()), false);
                }
            player.getCollectionLogManager().addItem(CollectionLogs.SANCTUM_OF_DEATH, new Item(reward.getId(), reward.getAmount()));
        } else {
            player.sendMessage("You need a " + ItemDefinition.forId(KEY_ID).getName() + " to do this.");
        }
    }


    public static void openAll(Player player) {
        if (player.getInventory().contains(KEY_ID)) {
            int amount = player.getInventory().getAmount(KEY_ID);
            Map<Box, Integer> rewards = new HashMap<>();
            for (int i = 0; i < amount; i++) {
                player.setSanctumKeysOpened(player.getSanctumKeysOpened() + 1);
                Box reward;
                int chance = RandomUtility.inclusiveRandom(1, getRareRate(player));
                if (chance == 1) {
                    reward = CasketOpening.getLoot1(rare);
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                            + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from the @red@Sanctum of Death"
                            + "@cya@ - <col=ff4f4f>KC: " +player.getSanctumKeysOpened();
                    World.sendMessage1(message);
                } else {
                    reward = CasketOpening.getLoot1(loot);
                }
                rewards.merge(reward, 1, Integer::sum);
            }
            player.getInventory().delete(KEY_ID, amount);
            boolean bank = amount <= player.getInventory().getFreeSlots();
            rewards.forEach((key, value) -> {
                player.getCollectionLogManager().addItem(CollectionLogs.SANCTUM_OF_DEATH, new Item(key.getId(), key.getAmount() * value), value);
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
