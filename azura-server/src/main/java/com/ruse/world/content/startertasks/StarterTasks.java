package com.ruse.world.content.startertasks;

import com.ruse.model.Item;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

public class StarterTasks {

    public static void doProgress(Player player, StarterTask startertask) {
        doProgress(player, startertask, 1);
    }

    public static void doProgress(Player player, StarterTask startertask, int amount) {
            int currentAmount = player.getStarterTasks().getAmountRemaining(startertask.ordinal());
            if (currentAmount < startertask.getAmount() && !player.getStarterTasks().isComplete( startertask.ordinal())) {
                player.getStarterTasks().setAmountRemaining(startertask.ordinal(), currentAmount + amount);
                if ((currentAmount + amount) >= startertask.getAmount()) {
                    player.getPacketSender().sendMessage(
                            "[Starter Task] @blu@" + startertask.getDescription()+ " @bla@completed, claim your reward!");
                }
            }
    }

    public static void claimReward(Player player, StarterTask startertask) {
            if (!player.getStarterTasks().isComplete(startertask.ordinal())) {
                int currentAmount = player.getStarterTasks().getAmountRemaining(startertask.ordinal());

                if (currentAmount >= startertask.getAmount()) {
                    if (startertask.getRewards() != null) {
                        player.getPacketSender().sendMessage("Your task reward(s) has been added to your account.");
                        player.getStarterTasks().setComplete(startertask.ordinal(), true);
                        for (Item item : startertask.getRewards())
                            player.getInventory().add(item.getId(), item.getAmount());
                        player.getStarterTasks().openInterface();
                    }
                } else {
                    player.getPacketSender().sendMessage("You have yet to complete this task!");
                }
            } else {
                player.getPacketSender().sendMessage("You have already claimed this reward.");
            }
    }

    public static void reset(Player player, StarterTask startertask) {
            if (!player.getStarterTasks().isComplete(startertask.ordinal())) {
                player.getStarterTasks().setAmountRemaining(startertask.ordinal(), 0);
            }
    }


    public static int getMaxTasks() {
        return StarterTask.values().length;
    }

    @Getter
    public enum StarterTask {
        //
        CLAIM_VOTES("Claim vote scrolls", 5, new Item(23784, 1),  new Item(23020, 1), new Item(12855, 50000)),
        //
        COMPLETE_STARTER_ZONE("Complete Starter zone", 1,  new Item(23784, 1), new Item(20489, 3)),
        //
        EASY_TASKS("Complete Easy Slayer tasks", 5,new Item(23784, 1),   new Item(12855, 50000)),
        //
        USE_LAMPS("Rub Experience lamps", 20, new Item(23784, 1),  new Item(11137, 5),   new Item(23516, 2)),
        //
        OPEN_BOXES("Open boxes of any kind", 500, new Item(23784, 1),  new Item(19114, 50)),
        //
        UPGRADE_ITEMS("Upgrade any item", 100,new Item(23784, 1),   new Item(23516, 5)),
        //
        KILL_GLOBALS("Kill Global bosses", 5, new Item(23784, 1),  new Item(23215, 5), new Item(12855, 50000)),
        //
        ISLAND_RAIDS("Complete Island Raids", 1,  new Item(23784, 1), new Item(18404, 1),   new Item(23516, 1)),
        //
        PEST_CONTROL("Complete run of Pest control", 3,  new Item(23784, 1), new Item(15288, 1)),
        //
        CONTRIBUTE_PERKS("Contribute towards Server perks", 100000, new Item(23784, 1),  new Item(12855, 50000)),

        ;

        private final String description;
        private final int amount;
        private final Item[] rewards;

        StarterTask( String description, int amount,
                    Item... rewards) {
            this.description = description;
            this.amount = amount;
            this.rewards = new Item[3];

            for (int i = 0 ; i < 3; i ++)
                this.rewards[i] = i < rewards.length ? rewards[i] : new Item(-1, 1);

        }


        public static StarterTask getTask(int ordinal) {
            for (StarterTask startertask : values())
                if (startertask.ordinal() == ordinal)
                    return startertask;
            return null;
        }

    }
}
