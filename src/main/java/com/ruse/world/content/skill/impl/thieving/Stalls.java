package com.ruse.world.content.skill.impl.thieving;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.Skill;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.AchievementsOLD;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.AfkStallSystem;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.CasketOpening;
import com.ruse.world.entity.impl.player.Player;

public class Stalls {

    public static Box[] loot1 = new Box[]{
            new Box(5022, 1, 2, 100),  //Pvm tickets
            new Box(5022, 3, 4, 75),  //Pvm tickets
            new Box(5020, 1, 2, 100),  //Afk tickets
            new Box(5020, 3, 4, 70),  //Afk tickets
            new Box(12855, 2, 4, 100),  //Orbs
            new Box(12855, 3, 6, 60),  //Orbs
            new Box(6199, 1, 0.35D),  //mystery box
            new Box(7956, 1, 0.25D),  //Pvm box
            new Box(19116, 0.1D), //Super mbox
            new Box(15003, 0.0007D, true), //silver chest
    };
    public static Box[] loot2 = new Box[]{
            new Box(5022, 2, 4, 100),  //Pvm tickets
            new Box(5022, 6, 8, 75),  //Pvm tickets
            new Box(5020, 2, 4, 100),  //Afk tickets
            new Box(5020, 6, 8, 70),  //Afk tickets
            new Box(12855, 3, 6, 100),  //Orbs
            new Box(12855, 6, 12, 60),  //Orbs
            new Box(6199, 3, 0.35D),  //mystery box
            new Box(7956, 3, 0.25D),  //Pvm box
            new Box(19115, 0.1D), //Ext mbox
            new Box(10946, 0.0025D, true), //$1 scroll
            new Box(15002, 0.0007D, true), //ruby chest
    };
    public static Box[] loot3 = new Box[]{
            new Box(5022, 4, 8, 100),  //Pvm tickets
            new Box(5022, 12, 16, 75),  //Pvm tickets
            new Box(5020, 4, 8, 100),  //Afk tickets
            new Box(5020, 12, 16, 70),  //Afk tickets
            new Box(12855, 6, 12, 100),  //Orbs
            new Box(12855, 12, 24, 60),  //Orbs
            new Box(6199, 7, 0.5D),  //mystery box
            new Box(7956, 7, 0.25D),  //Pvm box
            new Box(19114, 0.1D), //Grand mbox
            new Box(10946, 0.01D, true), //$1 scroll
            new Box(6769, 0.002D, true), //$5 scroll
            new Box(15004, 0.0006D, true), //diamond chest
    };
    public static Box[] loot4 = new Box[]{
            new Box(5022, 8, 16, 100),  //Pvm tickets
            new Box(5022, 24, 32, 75),  //Pvm tickets
            new Box(5020, 8, 16, 100),  //Afk tickets
            new Box(5020, 24, 32, 70),  //Afk tickets
            new Box(12855, 8, 16, 100),  //Orbs
            new Box(12855, 16, 32, 60),  //Orbs
            new Box(6199, 7, 0.9D),  //mystery box
            new Box(7956, 7, 0.6D),  //Pvm box
            new Box(19114, 0.25D), //Grand mbox
            new Box(10946, 0.025D, true), //$1 scroll
            new Box(6769, 0.006D, true), //$5 scroll
            new Box(15004, 0.002D, true), //diamond chest
    };

    public static Box[] tanzaniteLoot = new Box[]{
            new Box(5022, 8, 20, 100),  //Pvm tickets
            new Box(5022, 24, 40, 75),  //Pvm tickets
            new Box(5020, 8, 20, 100),  //Afk tickets
            new Box(5020, 24, 40, 70),  //Afk tickets
            new Box(12855, 8, 20, 100),  //Orbs
            new Box(12855, 16, 40, 60),  //Orbs
            new Box(6199, 9, 0.9D),  //mystery box
            new Box(7956, 9, 0.6D),  //Pvm box
            new Box(20488, 0.2D), //Grand mbox
            new Box(6769, 0.01D, true), //$5 scroll
            new Box(10942, 0.004D, true), //$10 scroll
            new Box(23210, 0.0015D, true), //Onyx chest
    };
    public static Box[] hydrixLoot = new Box[]{
            new Box(5022, 8, 22, 100),  //Pvm tickets
            new Box(5022, 24, 45, 75),  //Pvm tickets
            new Box(5020, 8, 25, 100),  //Afk tickets
            new Box(5020, 24, 45, 70),  //Afk tickets
            new Box(12855, 8, 25, 100),  //Orbs
            new Box(12855, 16, 45, 60),  //Orbs
            new Box(6199, 9, 0.9D),  //mystery box
            new Box(7956, 9, 0.8D),  //Pvm box
            new Box(20488, 0.5D), //Grand mbox
            new Box(6769, 0.015D, true), //$5 scroll
            new Box(10942, 0.005D, true), //$10 scroll
            new Box(23210, 0.0025D, true), //Onyx chest
            new Box(10935, 0.0015D, true), //50 bond
    };

    public static void stealFromStall(Player player, int lvlreq, int xp, int reward, String message) {
        if (player.getInventory().getFreeSlots() < 1) {
            player.getPacketSender().sendMessage("You need some more inventory space to do this.");
            return;
        }
        if (player.getCombatBuilder().isBeingAttacked()) {
            player.getPacketSender()
                    .sendMessage("You must wait a few seconds after being out of combat before doing this.");
            return;
        }
        if (!player.getClickDelay().elapsed(2500))
            return;
        if (player.getSkillManager().getMaxLevel(Skill.THIEVING) < lvlreq) {
            player.getPacketSender()
                    .sendMessage("You need a Thieving level of at least " + lvlreq + " to steal from this stall.");
            return;
        }
        Achievements.doProgress(player, Achievements.Achievement.THIEVING);
        Achievements.doProgress(player, Achievements.Achievement.THIEVER);
        player.performAnimation(new Animation(881));
        player.getPacketSender().sendInterfaceRemoval();
        player.getSkillManager().addExperience(Skill.THIEVING, xp);
        player.getClickDelay().reset();
		/*if (player.getSkillManager().skillCape(Skill.THIEVING)) {
			player.getPacketSender().sendMessage("Your cape quietly converts the stolen item into cash.");
			if (reward == 18199) {// banana
				player.getInventory().add(995, 6000);
			} else if (reward == 15009) {// gold ring
				player.getInventory().add(995, 11000);
			} else if (reward == 17401) {// hammer
				player.getInventory().add(995, 16000);
			} else if (reward == 1389) {// staff
				player.getInventory().add(995, 19000);
			} else if (reward == 11998) {// scimitar
				player.getInventory().add(995, 24500);
			} else if (reward == 13003) {// rune gaunts
				player.getInventory().add(995, 6900);
			} else if (reward == 4131) {// rune boots
				player.getInventory().add(995, 7400);
			} else if (reward == 1113) {// chain
				player.getInventory().add(995, 17000);
			} else if (reward == 1147) {// med helm
				player.getInventory().add(995, 10500);
			} else if (reward == 1163) {// full helm
				player.getInventory().add(995, 23000);
			} else if (reward == 1079) {// legs
				player.getInventory().add(995, 38000);
			} else if (reward == 1201) {// kite
				player.getInventory().add(995, 32000);
			} else if (reward == 1127) {// platebody
				player.getInventory().add(995, 42000);
			} else {
				player.getPacketSender().sendMessage(message);
				player.getInventory().add(reward, 1);
			}
		} else */
        {
            player.getPacketSender().sendMessage(message);
            player.getInventory().add(reward, 1);
        }
        player.getSkillManager().stopSkilling();
        if (reward == 15009)
            AchievementsOLD.finishAchievement(player, AchievementDataOLD.STEAL_A_RING);
        else if (reward == 11998) {
            AchievementsOLD.doProgress(player, AchievementDataOLD.STEAL_140_SCIMITARS);
            AchievementsOLD.doProgress(player, AchievementDataOLD.STEAL_5000_SCIMITARS);
        }
    }

    public static void stealFromAFKStall(Player player, int objectId, int tier) {
        if (!player.getClickDelay().elapsed(2000))
            return;
        player.getSkillManager().stopSkilling();
        player.getPacketSender().sendInterfaceRemoval();
        if (player.getInventory().getFreeSlots() <= 0) {
            player.getPacketSender().sendMessage("You need some more inventory space to do this.");
            return;
        }
        if (player.busy() || player.getCombatBuilder().isBeingAttacked() || player.getCombatBuilder().isAttacking()) {
            player.getPacketSender().sendMessage("You cannot do that right now.");
            return;
        }
        if (tier == 2 && (player.getAfkStallCount1() < 20000)) {
            player.sendMessage("@red@You need to have stole from the Afk stall (1)" + "("
                    + player.getAfkStallCount1() + "/20,000) times");
            return;
        } else if (tier == 3 && (player.getAfkStallCount2() < 50000)) {
            player.sendMessage("@red@You need to have stole from the Afk stall (2)" + "("
                    + player.getAfkStallCount2() + "/50,000) times");
            return;
        }

        int accounts = 1;
        for (Player p : World.getPlayers()) {
            if (p == null)
                continue;
            if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                if (p.getInteractingObject() != null && (p.getInteractingObject().getId() == 52601
                        || p.getInteractingObject().getId() == 53654 || p.getInteractingObject().getId() == 30035)) {
                    accounts++;
                    continue;
                }
            }
        }
        if (accounts > 2){
            player.getPacketSender().sendMessage("You already have two accounts stealing from the AFK Zone.");
            return;
        }

        player.setCurrentTask(new Task(4, player, true) {

            @Override
            public void execute() {
                if (player.getInventory().getFreeSlots() <= 0) {
                    player.getPacketSender().sendMessage("You do not have any free inventory space left.");
                    this.stop();
                    return;
                }
                player.performAnimation(new Animation(881));
                Box[] loot = loot1;
                if (tier == 1) {
                    player.setAfkStallCount1(player.getAfkStallCount1() + 1);
                } else if (tier == 2) {
                    loot = loot2;
                    player.setAfkStallCount2(player.getAfkStallCount2() + 1);
                } else if (tier == 3) {
                    loot = loot3;
                    player.setAfkStallCount3(player.getAfkStallCount3() + 1);
                } else if (tier == 4) {
                    loot = loot4;
                }else if (tier == 5) {
                    loot = tanzaniteLoot;
                }
                else if (tier == 6) {
                    loot = hydrixLoot;
                }

                AfkStallSystem.stoleAmount++;
                Achievements.doProgress(player, Achievements.Achievement.THIEVING);
                Achievements.doProgress(player, Achievements.Achievement.THIEVER);
                Achievements.doProgress(player, Achievements.Achievement.KLEPTOMANIAC);
                AfkStallSystem.spawnBoss();


                Box reward = CasketOpening.getLoot(loot);
                player.getInventory().add(reward.getId(), reward.getMin() + Misc.getRandom(reward.getMax() - reward.getMin()));

                if (reward.isAnnounce()) {
                    String message = "@blu@News: @red@" + player.getUsername() + " @blu@has just received @red@"
                            + ItemDefinition.forId(reward.getId()).getName() + "@blu@ from @red@Afk Stall (" + tier + ")";
                    World.sendMessage1(message);
                }

                player.getInventory().refreshItems();
            }

            @Override
            public void stop() {
                setEventRunning(false);
                player.performAnimation(new Animation(65535));
            }
        });
        TaskManager.submit(player.getCurrentTask());
        player.getClickDelay().reset();
    }
}
