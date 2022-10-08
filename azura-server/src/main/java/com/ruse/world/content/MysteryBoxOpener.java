package com.ruse.world.content;

import com.ruse.model.GameMode;
import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.World;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.entity.impl.player.Player;

import java.util.HashMap;
import java.util.Map;

public class MysteryBoxOpener {

    private Player player;

    public MysteryBoxOpener(Player player) {
        this.player = player;
    }

    private int openBox = -1;

    public int getOpenBox() {
        return openBox;
    }

    private int[] common, uncommon, rare;

    private int commonRate = 60,uncommonRate = 35,rareRate = 5;

    public void display(int boxId, String name, int[] common, int[] uncommon, int[] rare) {
        player.getPacketSender().sendInterface(48030);
        openBox = boxId;
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;

        this.commonRate = 60;
        this.uncommonRate = 35;
        this.rareRate = 5;

        updateInterface(boxId, name, common, uncommon, rare);
    }

    public void display(int boxId, String name, int[] common, int[] uncommon, int[] rare, int[] rates) {
        player.getPacketSender().sendInterface(48030);
        openBox = boxId;
        this.common = common;
        this.uncommon = uncommon;
        this.rare = rare;

        this.commonRate = rates[0];
        this.uncommonRate = rates[1];
        this.rareRate = rates[2];

        updateInterface(boxId, name, common, uncommon, rare);
    }

    private void updateInterface(int boxId, String name, int[] common, int[] uncommon, int[] rare) {

        for (int i = 0; i < 3; i++) {
            player.getPacketSender().resetItemsOnInterface(48051 + i * 10, 150);
        }
        player.getPacketSender().sendString(48035, name);
        player.getPacketSender().sendItemOnInterface(48045, boxId, 0, 1);

        for (int i = 0; i < common.length; i++) {
            player.getPacketSender().sendItemOnInterface(48051, common[i], i, 1);
        }

        for (int i = 0; i < uncommon.length; i++) {
            player.getPacketSender().sendItemOnInterface(48061, uncommon[i], i, 1);
        }

        for (int i = 0; i < rare.length; i++) {
            player.getPacketSender().sendItemOnInterface(48071, rare[i], i, 1);
        }

        player.getPacketSender().sendString(48032, "Common ("+ commonRate+"%)" );
        player.getPacketSender().sendString(48033, "Uncommon (" + uncommonRate+"%)");
        player.getPacketSender().sendString(48034, "Rare (" + rareRate+"%)");

    }



    public void open(int boxId) {
        int reward = -1;
        int chance = RandomUtility.inclusiveRandom(1, 100);
        StarterTasks.doProgress(player, StarterTasks.StarterTask.OPEN_BOXES);
        if (chance > 100 - rareRate) { // OH LOL oh lol
            reward = rare[RandomUtility.exclusiveRandom(0, rare.length)];
            if (boxId != 6199 && boxId != 989&& (boxId != 20488 && reward != 6769 && reward != 10942)  && boxId != 7956 &&  boxId != 23480 && boxId != 10025 && boxId != 19116 && boxId != 19115) {
                if ((!(reward >= 8323 && reward<= 8332) && reward != 22083 && reward!= 22092 && reward!= 22084&& reward!= 10946&& boxId != 20488
                ) || (boxId == 20488 && (reward == 6769 || reward == 10942))) {
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                            + ItemDefinition.forId(reward).getName() + "@blu@ from a @red@" + ItemDefinition.forId(boxId).getName();
                    World.sendMessage1(message);
                }
            }
        } else if (chance > 100 - uncommonRate - rareRate) {
            reward = uncommon[RandomUtility.exclusiveRandom(0, uncommon.length)];
            if (boxId == 23308 ||boxId == 23376 ) {
                String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                        + ItemDefinition.forId(reward).getName() + "@blu@ from a @red@"+ItemDefinition.forId(boxId).getName();
                World.sendMessage1(message);
            }
        } else{
            reward = common[RandomUtility.exclusiveRandom(0, common.length)];
        }
        player.getCollectionLogManager().addItem(boxId, new Item(reward));
        player.getProgressionManager().getProgressionTask(13).incrementProgress(0, 1);
        player.getProgressionManager().getProgressionTask(16).incrementProgress(0, 1);
        player.getDailyTaskManager().submitProgressToIdentifier(5, 1);
        player.getInventory().delete(boxId, 1);
        player.getInventory().add(reward, 1);
        if (boxId == 11795) {
            TeleportHandler.teleportPlayer(player, new Position(2579, 2566),
                    player.getSpellbook().getTeleportType());
        }
    }


    public void openAll(int boxId) {
       /* if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
            player.sendMessage("You cannot do this.");
            return;
        }*/

        int amount = player.getInventory().getAmount(boxId);
        StarterTasks.doProgress(player, StarterTasks.StarterTask.OPEN_BOXES, amount);
        Map<Integer, Integer> rewards = new HashMap<>();
        for (int i = 0; i < amount; i++) {
            int reward = -1;
            int chance = RandomUtility.inclusiveRandom(1, 100);
            String name = ItemDefinition.forId(boxId).getName();
            if (chance > 100 - rareRate) { //90% of 100% = 10%
                reward = rare[RandomUtility.exclusiveRandom(0, rare.length)];
                if ((reward == 6769 || reward == 10942) && boxId == 19114 && Misc.getRandom(3) == 0)
                    reward = 10946;


                if (boxId != 6199 && boxId != 989  && boxId != 7956 &&  boxId != 23480  && boxId != 10025 && boxId != 19116 && boxId != 19115 ) {
                    if ((!(reward >= 8323 && reward<= 8332) && reward != 22083 && reward!= 22092 && reward!= 22084&& reward!= 10946&& boxId != 20488
                    ) || (boxId == 20488 && (reward == 6769 || reward == 10942))) {
                        String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                                + ItemDefinition.forId(reward).getName() + "@blu@ from a @red@" + ItemDefinition.forId(boxId).getName();
                        World.sendMessage1(message);
                    }
                }
            } else if (chance > 100 - uncommonRate - rareRate) { //65% of 100% = 35% (remainder)
                reward = uncommon[RandomUtility.exclusiveRandom(0, uncommon.length)];

                if (boxId == 23308 ||boxId == 23376 ) {
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has received @red@"
                            + ItemDefinition.forId(reward).getName() + "@blu@ from a @red@"+ItemDefinition.forId(boxId).getName();
                    World.sendMessage1(message);
                }

            } else if (chance >= 0) { //0% of 100% = 55% (remainder)
                reward = common[RandomUtility.exclusiveRandom(0, common.length)]; // ye its correct.
            }

            rewards.merge(reward, 1, Integer::sum);

        }
        player.getInventory().delete(boxId, amount);
        player.getProgressionManager().getProgressionTask(13).incrementProgress(0, amount);
        player.getDailyTaskManager().submitProgressToIdentifier(5, amount);
        player.getProgressionManager().getProgressionTask(16).incrementProgress(0, amount);
        boolean bank = amount <= player.getInventory().getFreeSlots();
        rewards.forEach((key, value) -> {
            player.getCollectionLogManager().addItem(boxId, new Item(key, value), value);
            if (bank) {
                player.getInventory().add(new Item(key, value), false);
            } else {
                Item item = new Item(key, value);
                player.depositItemBank(item, false);
            }
        });
        player.getInventory().refreshItems();

        if (!bank) {
            player.sendMessage("@blu@Your rewards have been added to your bank.");
        }
        if (boxId == 11795) {
            TeleportHandler.teleportPlayer(player, new Position(2579, 2566),
                    player.getSpellbook().getTeleportType());
        }
    }

}
