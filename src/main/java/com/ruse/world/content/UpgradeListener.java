package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.entity.impl.player.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;

public class UpgradeListener {

    int[] rares = new int[]{18750, 18753, 18752, 18751, 18636, 18749, 18748, 18638, 18629, 18631, 18637, 18623,
            20488, 17700, 17698, 8087, 11320, 11321, 11322, 8088, 11340, 11341, 11342, 8089, 11421, 11422, 11423, 19888,
            18823, 23048, 23049, 12630};
    private final Player player;
    private final SecureRandom random = new SecureRandom();
    private int openTab;

    public UpgradeListener(Player player) {
        this.player = player;
    }

    public void handleButton(int buttonId) {
        if (buttonId == -3295) {
            openTab(0);
        } else if (buttonId == -3301) {
            openTab(1);
        } else if (buttonId == -3300) {
            openTab(2);
        } else if (buttonId == -3296) {
            openTab(3);
        } else if (buttonId == -3293) {
            openTab(4);
        } else if (buttonId == -3292) {
            openTab(5);
        } else if (buttonId == -3332) {
            if (player.getSkillManager().getMaxLevel(Skill.INVENTION) < 120) {
                player.getPacketSender().sendMessage(
                        "You need a Invention Level of at least @blu@120 Invention@bla@ to view this Tier.");
                return;
            }
            openTab = 6;
            player.getCustomCombiner().open();
        }
    }

    public static int[] skillReqs = new int[]{1, 15, 35, 55, 80, 90, 120};

    public void openTab() {
        if (openTab == 6){
            player.getCustomCombiner().open();
        } else {
            if (player.getUpgradeSelection() != null) {
                player.getUpgradeListener().openTab(openTab);
                openUpgrade(player.getUpgradeSelection());
            } else {
                player.getUpgradeListener().openTab(openTab);
            }
        }
    }

    public void openUpgrade(Item item) {
        for (UpgradeData data : UpgradeData.values()) {
            if (data.getRequired().getId() == item.getId()) {
                player.setUpgradeSelection(item);
                player.getCustomCombiner().setCombinerOpen(false);
                player.getPacketSender().sendItemOnInterface(62210, data.getReward().getId(),
                        data.getReward().getAmount());

                player.getPacketSender().sendString(62231,
                        "Tokens required: @whi@" + Misc.sendCashToString(data.getDustRequired().getAmount()));
                player.getPacketSender().sendString(62234, "Success rate: @whi@" + data.getChance() + "%");
            }
        }
    }

    public void openTab(int tab) {

        if (player.getSkillManager().getMaxLevel(Skill.INVENTION) < skillReqs[tab]) {
            player.getPacketSender()
                    .sendMessage("You need a Invention Level of at least @blu@"+skillReqs[tab]+" Invention@bla@ to view this Tier.");
            player.getPacketSender().sendConfig(1355, openTab);
            return;
        }

        if (tab != openTab)
            player.setUpgradeSelection(null);

        openTab = tab;

        player.getPacketSender().sendItemOnInterface(62210, -1,1);
        ArrayList<Item> itemList0 = UpgradeData.getItems(tab);

        for (int i = 0; i < 100; i++) {
            Item item = itemList0.size() > i ? new Item (itemList0.get(i).getId(), itemList0.get(i).getAmount()) : new Item (-1);
            player.getPacketSender().sendItemOnInterface(62209, item.getId(), i, item.getAmount());
        }

        player.getPacketSender().sendString(62231,
                "Tokens required: @whi@");
        player.getPacketSender().sendString(62234, "Success rate: @whi@");
        player.getPacketSender().sendConfig(1355, openTab);

        player.getPacketSender().sendInterface(62200);

    }

    public void upgrade(boolean all) {
        if (player.getUpgradeSelection() == null) {
            player.sendMessage("Choose an item to upgrade.");
            return;
        }
        Arrays.stream(UpgradeData.values()).forEach(val -> {
            if (val.getRequired().getId() == player.getUpgradeSelection().getId()) {
                if (getRestrictions(val, all)) {
                    ItemDefinition definition = ItemDefinition.forId(val.getRequired().getId() + 1);
                    boolean noted = false;

                    if (all) {
                        if (definition.isNoted() && definition.isStackable()) {
                            String name = definition.getName();
                            definition = ItemDefinition.forId(val.getRequired().getId());
                            String originalName = definition.getName();
                            noted = name.equals(originalName);
                        }
                        int amountSuccess = 0;
                        int amount = player.getInventory().getAmount(player.getUpgradeSelection().getId()) +
                                (noted ? player.getInventory().getAmount(player.getUpgradeSelection().getId() + 1) : 0);
                        int failed = 0, success = 0;
                        int passedThrough = 0;
                        int tab = Bank.getTabForItem(player, val.getReward().getId());
                        for (int i = 0; i < amount; i++) {
                            if (player.getInventory().contains(val.getDustRequired().getId(),
                                    val.getDustRequired().getAmount()) &&
                                    (noted ? true : player.getInventory().contains(val.getRequired().getId(), val.getRequired().getAmount()))                                    ) {
                                if (noted) {
                                    if (player.getInventory().contains(val.getRequired().getId())) {
                                        player.getInventory().delete(val.getRequired().getId(), val.getRequired().getAmount(), false);
                                    } else if (player.getInventory().contains(val.getRequired().getId() + 1)) {
                                        player.getInventory().delete(val.getRequired().getId() + 1, val.getRequired().getAmount(), false);
                                    }
                                } else {
                                    player.getInventory().delete(val.getRequired().getId(), val.getRequired().getAmount(), false);
                                }
                                player.getInventory().delete(val.getDustRequired().getId(),
                                        val.getDustRequired().getAmount(), false);

                                boolean random = 1 + Misc.getRandom(99) <= val.getChance();
                                if (random) {
                                    success++;

                                    if (noted) {
                                        player.getBank(tab).add(val.getReward(), false);
                                    } else {
                                        player.getInventory().add(new Item(val.getReward().getId(), val.getReward().getAmount() < 1 ? 1 : val.getReward().getAmount()), false);
                                    }

                                } else {
                                    failed++;
                                }
                                passedThrough++;
                            } else {
                                break;
                            }
                        }
                        StarterTasks.doProgress(player, StarterTasks.StarterTask.UPGRADE_ITEMS, success);

                        Achievements.doProgress(player, Achievements.Achievement.UPGRADE_TWICE, success);
                        PlayerLogs.logUpgrades(player.getUsername(), "UPGRADED : " + val.getRequired().getDefinition().getName()
                                + ", id: " + val.getRequired().getId() + ", amount: " + (val.getRequired().getAmount() * success) + " --into-- " +
                                val.getReward().getDefinition().getName() + ", id: " + val.getReward().getId() + ", amount: " + (val.getReward().getAmount() * success));
                        player.getSkillManager().addExperience(Skill.INVENTION, 1000 * passedThrough);

                        player.getInventory().refreshItems();
                        player.getPacketSender().sendMessage("You successfully upgraded " + success
                                + " items and failed to upgrade " + failed + " items.");

                        if (gotRare(val.getReward()) && success > 0 && openTab == 6) {
                            World.sendMessage("<img=5>@blu@[Invention]<img=5> @red@" + player.getUsername() + "@blu@ has created @red@x" + success + " @red@"
                                    + val.getReward().getDefinition().getName());
                        }

                        Achievements.doProgress(player, Achievements.Achievement.UPGRADE_AN_ITEM);
                        player.getProgressionManager().getProgressionTask(9).incrementProgress(0, 1);
                    } else {
                        if (definition.isNoted() && definition.isStackable()) {
                            String name = definition.getName();
                            definition = ItemDefinition.forId(val.getRequired().getId());
                            String originalName = definition.getName();
                            noted = name.equals(originalName);
                        }
                        Achievements.doProgress(player, Achievements.Achievement.UPGRADE_AN_ITEM);
                        player.getProgressionManager().getProgressionTask(9).incrementProgress(0, 1);
                        if (noted) {
                            if (player.getInventory().contains(val.getRequired().getId())) {
                                player.getInventory().delete(val.getRequired());
                            } else if (player.getInventory().contains(val.getRequired().getId() + 1)) {
                                player.getInventory().delete(new Item(val.getRequired().getId() + 1,
                                        val.getRequired().getAmount()));
                            }
                        } else {
                            player.getInventory().delete(val.getRequired());
                        }
                        player.getInventory().delete(val.getDustRequired().getId(), val.getDustRequired().getAmount());

                        TimerTask task = new TimerTask() {
                            int tick = 0;

                            @Override
                            public void run() {
                                if (tick == 0) {
                                    player.getPacketSender().sendMessage("You try to upgrade....");
                                } else if (tick == 3) {
                                    boolean success = random.nextInt(100) <= val.getChance();
                                    if (success) {
                                        player.getSkillManager().addExperience(Skill.INVENTION, 1000);
                                        player.getPacketSender().sendMessage("You successfully upgraded your item!");
                                        player.getInventory().add(val.getReward().getId(), val.getReward().getAmount() < 1 ? 1 : val.getReward().getAmount());
                                        AchievementsOLD.doProgress(player, AchievementDataOLD.FIRE_500_CANNON_BALLS);
                                        AchievementsOLD.doProgress(player, AchievementDataOLD.FIRE_5000_CANNON_BALLS);
                                        if (gotRare(val.getReward()) && openTab == 6) {
                                            World.sendMessage("<img=5>@blu@[Invention]<img=5> @red@" + player.getUsername() + "@blu@ has created a @red@"
                                                    + val.getReward().getDefinition().getName());
                                        }
                                        Achievements.doProgress(player, Achievements.Achievement.UPGRADE_TWICE);

                                    } else {
                                        player.getPacketSender().sendMessage("You failed to upgrade!");
                                    }
                                    cancel();
                                }
                                tick++;
                            }

                        };

                        Timer timer = new Timer();
                        timer.schedule(task, 500, 500);
                    }
                }
            }
        });
    }

    private boolean gotRare(Item reward) {
        return Arrays.stream(rares).anyMatch(x -> reward.getId() == x);
    }

    private boolean getRestrictions(UpgradeData data, boolean all) {
        ItemDefinition definition = ItemDefinition.forId(data.getRequired().getId() + 1);
        boolean noted = false;
        if (definition.isNoted() && definition.isStackable()) {
            String name = definition.getName();
            definition = ItemDefinition.forId(data.getRequired().getId());
            String originalName = definition.getName();
            noted = name.equals(originalName);
        }
        if (noted && player.getInventory().contains(data.getRequired().getId() + 1, data.getRequired().getAmount())) {
            int amount = all ? player.getInventory().getAmount(data.getRequired().getId() + 1) : 1;
            if (!player.getInventory().contains(data.getRequired().getId() + 1, amount) ||
                    !player.getInventory().contains(data.getDustRequired().getId(), amount *
                            data.getDustRequired().getAmount())) {
                player.getPacketSender().sendMessage("You do not have the required items!");
                return false;
            }
        } else {
            if ( !player.getInventory().contains(data.getDustRequired().getId(), data.getDustRequired().getAmount())) {
                player.getPacketSender().sendMessage("You do not have the required items!");
                return false;
            }
            if ( !player.getInventory().contains(data.getRequired().getId(), data.getRequired().getAmount())) {
                player.getPacketSender().sendMessage("You do not have the required items!");
                return false;
            }
        }
        return true;
    }
}