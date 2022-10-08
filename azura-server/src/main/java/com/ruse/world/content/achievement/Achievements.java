package com.ruse.world.content.achievement;

import com.ruse.model.Item;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Cases;
import com.ruse.world.entity.impl.player.Player;

import java.util.EnumSet;
import java.util.Set;

public class Achievements {

    public static void doProgress(Player player, Achievement achievement) {
        doProgress(player, achievement, 1);
    }

    public static void doProgress(Player player, Achievement achievement, int amount) {
        if (achievement.getDifficulty() != AchievementDifficulty.DAILY) {
            if (achievement.getRequirement() == null || achievement.getRequirement().isAble(player)) {
                int currentAmount = player.getAchievements().getAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId());
                int tier = achievement.getDifficulty().ordinal();
                if (currentAmount <= achievement.getAmount() && !player.getAchievements().isComplete(achievement.getDifficulty().ordinal(), achievement.getId())) {
                    player.getAchievements().setAmountRemaining(tier, achievement.getId(), currentAmount + amount);
                    if ((currentAmount + amount) >= achievement.getAmount()) {
                        String name = achievement.name().replaceAll("_", " ");
                        player.getPacketSender().sendMessage(
                                "[ACHIEVEMENT] @blu@" + Misc.capitalizeJustFirst(achievement.name().replaceAll("_", " ") + " @bla@completed, claim your reward!"));

                        if (achievement.getDifficulty().ordinal() == 2 && achievement != Achievement.KLEPTOMANIAC) {
                            World.sendNewsMessage("<col=ff0000>" + Misc.capitalizeJustFirst(player.getUsername())
                                    + " <col=a72800>completed the achievement <col=ff0000>" + Misc.capitalizeJustFirst(achievement.name().replaceAll("_", " ") + "!"));
                        }
                    }
                }
            }
        }
    }

    public static void claimReward(Player player, Achievement achievement) {
        if (achievement.getRequirement() == null || achievement.getRequirement().isAble(player)) {
            if (!player.getAchievements().isComplete(achievement.getDifficulty().ordinal(), achievement.getId())) {
                int currentAmount = player.getAchievements().getAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId());
                int tier = achievement.getDifficulty().ordinal();

                if (currentAmount >= achievement.getAmount()) {
                    if (achievement.getRewards() != null) {

                        if (achievement.getDifficulty() != AchievementDifficulty.DAILY) {
                            player.getPacketSender().sendMessage("Your achievement reward(s) has been added to your account.");
                            player.getAchievements().setComplete(tier, achievement.getId(), true);
                            for (Item item : achievement.getRewards())
                                player.getInventory().add(item.getId(), item.getAmount());

                            for (int x = 0; x < achievement.getPoints().length; x++) {
                                if (achievement.getPoints()[x][0].equalsIgnoreCase("achievement")) {
                                    player.getPointsHandler().setAchievementPoints(Integer.parseInt(achievement.getPoints()[x][1]), true);
                                } else if (achievement.getPoints()[x][0].equalsIgnoreCase("skill")) {
                                    player.getPointsHandler().setSkillingPoints(Integer.parseInt(achievement.getPoints()[x][1]), true);
                                } else if (achievement.getPoints()[x][0].equalsIgnoreCase("voting")) {
                                    player.getPointsHandler().setVotingPoints(Integer.parseInt(achievement.getPoints()[x][1]), true);
                                } else if (achievement.getPoints()[x][0].equalsIgnoreCase("slayer")) {
                                    player.getPointsHandler().setSlayerPoints(Integer.parseInt(achievement.getPoints()[x][1]), true);
                                }
                            }
                        }
                    }
                } else {
                    player.getPacketSender().sendMessage("You have yet to complete this achievement!");
                }
            } else {
                player.getPacketSender().sendMessage("You have already claimed this reward.");
            }
        }
    }

    public static void reset(Player player, Achievement achievement) {
        if (achievement.getRequirement() == null || achievement.getRequirement().isAble(player)) {
            if (!player.getAchievements().isComplete(achievement.getDifficulty().ordinal(), achievement.getId())) {
                player.getAchievements().setAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId(), 0);
            }
        }
    }

    public static void resetDailys(Player player) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getDifficulty().equals(AchievementDifficulty.DAILY)) {
                player.getAchievements().setAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId(), 0);
                player.getAchievements().setComplete(3, achievement.getId(), false);
            }
        }
        player.getAchievements().setDailyTaskDate(player.getAchievements().getTodayDate());
    }

    public static void resetEasys(Player player) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getDifficulty().equals(AchievementDifficulty.EASY)) {
                player.getAchievements().setAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId(), 0);
                player.getAchievements().setComplete(0, achievement.getId(), false);
            }
        }
    }

    public static void resetMediums(Player player) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getDifficulty().equals(AchievementDifficulty.MEDIUM)) {
                player.getAchievements().setAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId(), 0);
                player.getAchievements().setComplete(1, achievement.getId(), false);
            }
        }
    }

    public static void resetHards(Player player) {
        for (Achievement achievement : Achievement.ACHIEVEMENTS) {
            if (achievement.getDifficulty().equals(AchievementDifficulty.HARD)) {
                player.getAchievements().setAmountRemaining(achievement.getDifficulty().ordinal(), achievement.getId(), 0);
                player.getAchievements().setComplete(2, achievement.getId(), false);
            }
        }
    }

    public static int getMaximumAchievements() {
        return Achievement.ACHIEVEMENTS.size();
    }

    public enum Achievement {
        /*
         * EASY
         */
        //
        SUPPORT_LUNITE(0, AchievementDifficulty.EASY, "Vote for Lunite  by using the ::vote command!", 1, new String[][]{{"loyalty", "2000"}, {"voting", "1"}}, new Item(19116, 1)),
        //
        UPGRADE_AN_ITEM(1, AchievementDifficulty.EASY, "Attempt to upgrade  any item using the upgrade chest!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 2500)),
        //
        DEAL_100K_MELEE_DMG(2, AchievementDifficulty.EASY, "Deal 100,000 melee  damage to any npc!", 100_000, new String[][]{{"loyalty", "2000"}}, new Item(5022, 1_000)),
        //
        DEAL_100K_RANGE_DMG(3, AchievementDifficulty.EASY, "Deal 100,000 ranged damage to any npc!", 100_000, new String[][]{{"loyalty", "2000"}}, new Item(5022, 1_000)),
        //
        DEAL_100K_MAGIC_DMG(4, AchievementDifficulty.EASY, "Deal 100,000 magic  damage to any npc!", 100_000, new String[][]{{"loyalty", "2000"}}, new Item(5022, 1_000)),
        //
        TRAIN_NPCS(5, AchievementDifficulty.EASY, 1265, " Kill x50 Lunite Lions", 50, new String[][]{{"loyalty", "2000"}}, new Item(19116, 1)),
        //
        RANGE_NPCS(6, AchievementDifficulty.EASY, 1233, " Kill x50 ::range npc's", 50, new String[][]{{"loyalty", "2000"}}, new Item(19116, 1)),
        //
        MAGIC_NPCS(7, AchievementDifficulty.EASY, 1234, " Kill x50 ::mage npc's", 50, new String[][]{{"loyalty", "2000"}}, new Item(19116, 1)),
        //
        MELEE_NPCS(8, AchievementDifficulty.EASY, 1023, " Kill x50 ::melee npcs", 50, new String[][]{{"loyalty", "2000"}}, new Item(19116, 1)),
        //
        ICE_DEMON(9, AchievementDifficulty.EASY, 13747, " Kill x100 Ice Demons", 100, new String[][]{{"loyalty", "2000"}}, new Item(19116, 1)),
        //
        PREDATOR(10, AchievementDifficulty.EASY, 12343, " Kill x100 Predaotrs", 100, new String[][]{{"loyalty", "2000"}}, new Item(5022, 2_500)),
        //
        CYANTRIX(11, AchievementDifficulty.EASY, 12886, " Kill x100 Cyantrix", 100, new String[][]{{"loyalty", "2000"}}, new Item(5022, 2_500)),
        //
        BULWARK(12, AchievementDifficulty.EASY, 10103, " Kill x100 Bulwark", 100, new String[][]{{"loyalty", "2000"}}, new Item(5022, 2_500)),
        //
        IMPS(13, AchievementDifficulty.EASY, 1614, " Kill x100 Imps", 100, new String[][]{{"loyalty", "2000"}}, new Item(5022, 1_500)),
        //
        SLAYER(14, AchievementDifficulty.EASY, "Complete a slayer   task on any difficulty!", 1, new String[][]{{"loyalty", "2000"}, {"slayer", "25"}}, new Item(7120, 1)),
        //
        WOODCUTTING(15, AchievementDifficulty.EASY, "Chop some wood of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        FISHING(16, AchievementDifficulty.EASY, "Catch some fish of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        COOKING(17, AchievementDifficulty.EASY, "Cook anything at all without burning it!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        MINING(18, AchievementDifficulty.EASY, "Mine some ore of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        SMITHING(19, AchievementDifficulty.EASY, "Make some bars of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        RUNECRAFTING(20, AchievementDifficulty.EASY, "Craft some runes of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        PRAYER(21, AchievementDifficulty.EASY, "Bury some bones of any kind or use on altar!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        FLETCHING(22, AchievementDifficulty.EASY, "Fletch some arrows of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        FARMING(23, AchievementDifficulty.EASY, "Harvest a crop of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        HERBLORE(24, AchievementDifficulty.EASY, "Clean a herb of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        THIEVING(25, AchievementDifficulty.EASY, "Steal from any thieving stall!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        AGILITY(26, AchievementDifficulty.EASY, "Climb an obstacle of any kind!", 1, new String[][]{{"loyalty", "2000"}}, new Item(12855, 1_000)),
        //
        UPGRADE_TWICE(27, AchievementDifficulty.EASY, "Successfully upgrade 2 Items!", 2, new String[][]{{"loyalty", "2000"}}, new Item(12855, 3_000)),
        //
        REACH_10K_KILLS(28, AchievementDifficulty.EASY, "Reach 10K Npc Kills", 10_000, new String[][]{{"loyalty", "3000"}, {"slayer", "100"}}, new Item(15288, 1), new Item(6769, 1), new Item(20488, 3)),

        //		 * MEDIUM

        //
        LORD(0, AchievementDifficulty.MEDIUM, 603, " Kill x250 Lords", 250, new String[][]{{"loyalty", "3000"}}, new Item(6199, 2), new Item(10025, 1)),
        //
        DEMON(1, AchievementDifficulty.MEDIUM, 12843, " Kill x300 Demon", 300, new String[][]{{"loyalty", "3000"}}, new Item(6199, 2), new Item(10025, 1)),
        //
        DRAGON(2, AchievementDifficulty.MEDIUM, 53, " Kill x400 Dragon", 400, new String[][]{{"loyalty", "3000"}}, new Item(6199, 2), new Item(10025, 2)),
        //
        BEAST(3, AchievementDifficulty.MEDIUM, 8018, " Kill x500 Beast", 500, new String[][]{{"loyalty", "3000"}}, new Item(6199, 3), new Item(10025, 2)),
        //
        KING(4, AchievementDifficulty.MEDIUM, 13635, " Kill x750 King", 750, new String[][]{{"loyalty", "3000"}}, new Item(6199, 3), new Item(10025, 3)),
        //
        AVATAR(5, AchievementDifficulty.MEDIUM, 8008, " Kill x1000 Avatar", 1000, new String[][]{{"loyalty", "3000"}}, new Item(6199, 3), new Item(10025, 4)),
        //
        ANGEL(6, AchievementDifficulty.MEDIUM, 3308, " Kill x1000 Angel", 1000, new String[][]{{"loyalty", "3000"}}, new Item(19116, 2), new Item(10025, 5)),
        //
        DEAL_10M_MELEE_DMG(7, AchievementDifficulty.MEDIUM, "Deal 10,000,000     melee damage to your opponents!", 10_000_000, new String[][]{{"loyalty", "3000"}}, new Item(12855, 10_000)),
        //
        DEAL_10M_RANGED_DMG(8, AchievementDifficulty.MEDIUM, "Deal 10,000,000      ranged damage to your opponents!", 10_000_000, new String[][]{{"loyalty", "3000"}}, new Item(12855, 10_000)),
        //
        DEAL_10M_MAGE_DMG(9, AchievementDifficulty.MEDIUM, "Deal 10,000,000      magic damage to your opponents!", 10_000_000, new String[][]{{"loyalty", "3000"}}, new Item(12855, 10_000)),
        //
        PRAYER_RITUAL(10, AchievementDifficulty.MEDIUM, "Bury a total of 50 bones of any kind or use on altar!", 50, new String[][]{{"loyalty", "3000"}}, new Item(18830, 100)),
        //
        THIEVER(11, AchievementDifficulty.MEDIUM, "Steal from any stall a total of 50 times!", 50, new String[][]{{"loyalty", "3000"}}, new Item(12855, 10_000)),
        //
        MODERATE_SLAYER(12, AchievementDifficulty.MEDIUM, "Complete 10 slayer  tasks of any difficulty", 10, new String[][]{{"loyalty", "3000"}, {"slayer", "100"}}, new Item(7120, 5)),
        //
        SUPPORTER(13, AchievementDifficulty.MEDIUM, "Vote for Lunite  10 times by using the ::vote command!", 10, new String[][]{{"loyalty", "3000"}, {"voting", "5"}}, new Item(12855, 10_000)),
        //
        REACH_100K_KILLS(14, AchievementDifficulty.MEDIUM, "Reach 100K Npc Kills", 100_000, new String[][]{{"loyalty", "4000"}, {"slayer", "250"}}, new Item(15288, 3), new Item(10942, 1), new Item(20488, 5)),

        //		 * HARD

        //
        FRIEZA(0, AchievementDifficulty.HARD, 252, " Kill x500 Frieza", 500, new String[][]{{"loyalty", "5000"}}, new Item(12855, 50_000), new Item(20488, 1)),
        //
        PERFECT_CELL(1, AchievementDifficulty.HARD, 449, " Kill x600 Perfect Cell", 600, new String[][]{{"loyalty", "5000"}}, new Item(12855, 50_000), new Item(20488, 1)),
        //
        SUPER_BUU(2, AchievementDifficulty.HARD, 452, " Kill x750 Super Buu", 750, new String[][]{{"loyalty", "5000"}}, new Item(12855, 50_000), new Item(20488, 1)),
        //
        JOKER(3, AchievementDifficulty.HARD, 185, " Kill x1000 Joker!", 1000, new String[][]{{"loyalty", "5000"}}, new Item(12855, 50_000), new Item(20488, 1)),
        //
        DEAL_100M_MELEE_DMG(4, AchievementDifficulty.HARD, "Deal 100,000,000     melee damage to your opponents!", 100_000_000, new String[][]{{"loyalty", "5000"}}, new Item(19114, 4)),
        //
        DEAL_100M_RANGED_DMG(5, AchievementDifficulty.HARD, "Deal 100,000,000     ranged damage to your opponents!", 100_000_000, new String[][]{{"loyalty", "5000"}}, new Item(19114, 4)),
        //
        DEAL_100M_MAGE_DMG(6, AchievementDifficulty.HARD, "Deal 100,000,000     magic damage to your opponents!", 100_000_000, new String[][]{{"loyalty", "5000"}}, new Item(19114, 4)),
        //
        PRAYER_DEVOTION(7, AchievementDifficulty.HARD, "Bury a total of 500 bones of any kind or use on altar!", 500, new String[][]{{"loyalty", "5000"}}, new Item(12855, 30_000), new Item(19115, 2), new Item(19114, 1)),
        //
        KLEPTOMANIAC(8, AchievementDifficulty.HARD, "Steal from any stall a total of 500 times!", 500, new String[][]{{"loyalty", "5000"}}, new Item(19114, 3)),
        //
        MEGA_SUPPORTER(9, AchievementDifficulty.HARD, "Vote 50 times using the ::vote command!", 50, new String[][]{{"loyalty", "5000"}, {"voting", "10"}}, new Item(15288, 1), new Item(10946, 3)),
        //
        SKILL_GRINDER(10, AchievementDifficulty.HARD, "Reach level 99 in all skills!", 1, new String[][]{{"loyalty", "5000"}}, new Item(15288, 5), new Item(12608, 1), new Item(10934, 1), new Item(19886, 1)),
        
        REACH_500K_KILLS(11, AchievementDifficulty.HARD, "Reach 500K Npc Kills", 500_000, new String[][]{{"loyalty", "5000"}, {"slayer", "500"}}, new Item(15288, 5), new Item(10934, 1), new Item(20488, 10), new Item(4446, 1)),

        //
        DEAL_1B_MELEE_DMG(12, AchievementDifficulty.HARD, "Deal 1,000,000,000     melee damage to your opponents!", 1_000_000_000, new String[][]{{"loyalty", "5000"}, {"slayer", "500"}}, new Item(12855, 100_000), new Item(20488, 2)),
        //
        DEAL_1B_RANGED_DMG(13, AchievementDifficulty.HARD, "Deal 1,000,000,000      ranged damage to your opponents!", 1_000_000_000, new String[][]{{"loyalty", "5000"}, {"slayer", "500"}}, new Item(12855, 100_000), new Item(20488, 2)),
        //
        DEAL_1B_MAGE_DMG(14, AchievementDifficulty.HARD, "Deal 1,000,000,000      magic damage to your opponents!", 1_000_000_000, new String[][]{{"loyalty", "5000"}, {"slayer", "500"}}, new Item(12855, 100_000), new Item(20488, 2)),


        MAX_OUT(15, AchievementDifficulty.HARD, "Maxing (120 all stats)", 1, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1), new Item(15004, 1)),
        TWO_BILLION_INVENTION(16, AchievementDifficulty.HARD, "2,000,000,000 Invention XP", 1, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1), new Item(15002, 1), new Item(15288, 10)),
        TWO_BILLION_FARMING(17, AchievementDifficulty.HARD, "2,000,000,000 Farm XP", 1, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1), new Item(15004, 1)),
        TWO_BILLION_HUNTER(18, AchievementDifficulty.HARD, "2,000,000,000 Hunter XP", 1, new String[][]{{"loyalty", "5000"}},new Item(10935, 1), new Item(15004, 1)),
        BURY_ALOT(19, AchievementDifficulty.HARD, "Bury 200,000 Elite Dragonbones", 200_000, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1)),
        CHOP_ALOT(20, AchievementDifficulty.HARD, "Chop 25,000 Trees", 25_000, new String[][]{{"loyalty", "5000"}}, new Item(10934, 1), new Item(10947, 2)),
        COOK_ALOT(21, AchievementDifficulty.HARD, "Cook 25,000 Times", 25_000, new String[][]{{"loyalty", "5000"}}, new Item(10934, 1), new Item(10947, 2)),
        FISH_ALOT(22, AchievementDifficulty.HARD, "Fish 25,000 Times", 25_000, new String[][]{{"loyalty", "5000"}}, new Item(10934, 1), new Item(10947, 2)),
        ONE_MILLION(23, AchievementDifficulty.HARD, "Gain 1,000,000 NPC Kills", 1_000_000, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1), new Item(15004, 1)),
        KILL_AVATARS_ALOT(24, AchievementDifficulty.HARD, "Kill 10K Mega Avatars", 10_000, new String[][]{{"loyalty", "5000"}},new Item(10935, 1), new Item(22106, 2), new Item(5022, 3_000_000)),
        KILL_ELITE_DRAGONS_ALOT(25, AchievementDifficulty.HARD, "Kill 10K Elite Dragons", 10_000, new String[][]{{"loyalty", "5000"}}, new Item(10935, 1), new Item(22106, 2), new Item(5022, 3_000_000)),
        KILL_LUCIFER_ALOT(26, AchievementDifficulty.HARD, "Kill 10K Lucifers", 10_000, new String[][]{{"loyalty", "5000"}}, new Item(22107, 1), new Item(12855, 5_000_000)),


        //
        KILL_2500_NPCS(0, AchievementDifficulty.DAILY, " Kill x2500 Npcs!", 2500, new String[][]{{"slayer", "50"}}, new Item(20488, 1),
                new Item(12855, 50_000)),
        //
        COMPLETE_5_BOSS_TASKS(1, AchievementDifficulty.DAILY, " Complete 5 Boss Slayer Tasks!", 5, new String[][]{{"slayer", "60"}}, new Item(20488, 2),
                new Item(621, 25), new Item(12855, 50_000)),
        //
        KILL_100_SUPREME_BOSSES(2, AchievementDifficulty.DAILY, 440, " Kill x100 Supreme Boss!", 50, new String[][]{{"slayer", "75"}}, new Item(20488, 2),
                new Item(19114, 3), new Item(12855, 50_000)),
        //
        KILL_100_ELITE_DRAGONS(3, AchievementDifficulty.DAILY, 8015, " Kill x100 Elite Dragon!", 50, new String[][]{{"slayer", "85"}}, new Item(20488, 2),
                new Item(19114, 3), new Item(12855, 50_000)),
        //
        KILL_250_JOKERS(4, AchievementDifficulty.DAILY, 185, " Kill x250 Jokers!", 250, new String[][]{{"slayer", "100"}}, new Item(20488, 3),
                new Item(19114, 5), new Item(12855, 50_000)),
        //
        DAILY_MINING(5, AchievementDifficulty.DAILY, "Mine x100 Runite ore!", 100, new String[][]{{"loyalty", "2000"}}, new Item(19114, 4),
                new Item(19115, 5)),
        //
        DAILY_CHOPPING(6, AchievementDifficulty.DAILY, "Chop x100 Magic logs!", 100, new String[][]{{"loyalty", "2000"}}, new Item(19114, 4),
                new Item(19115, 5)),
        //
        DAILY_FISHING(7, AchievementDifficulty.DAILY, "Fish x100 Sharks!", 100, new String[][]{{"loyalty", "2000"}}, new Item(19114, 4),
                new Item(19115, 5)),
        //
        DAILY_DINNER(8, AchievementDifficulty.DAILY, "Cook x100 Sharks!", 100, new String[][]{{"loyalty", "2000"}}, new Item(19114, 4),
                new Item(19115, 5)),


        ;

        public static final Set<Achievement> ACHIEVEMENTS = EnumSet.allOf(Achievement.class);
        final String[][] points;
        private final AchievementDifficulty difficulty;
        private final AchievementRequirement requirement;
        private final String description;
        private final int amount;
        private final int npcId;
        private final int identification;
        private final Item[] rewards;

        Achievement(int identification, AchievementDifficulty difficulty, int npcId, String description, int amount, String[][] points,
                    Item... rewards) {
            this.identification = identification;
            this.difficulty = difficulty;
            this.requirement = null;
            this.npcId = npcId;
            this.description = description;
            this.amount = amount;
            this.points = points;
            this.rewards = rewards;

            for (Item b : rewards)
                if (b.getAmount() == 0)
                    b.setAmount(1);

        }

        Achievement(int identification, AchievementDifficulty difficulty, String description, int amount, String[][] points,
                    Item... rewards) {
            this.identification = identification;
            this.difficulty = difficulty;
            this.requirement = null;
            this.npcId = -1;
            this.description = description;
            this.amount = amount;
            this.points = points;
            this.rewards = rewards;

            for (Item b : rewards)
                if (b.getAmount() == 0)
                    b.setAmount(1);

        }

        public static Achievement getAchievement(AchievementDifficulty tier, int ordinal) {
            for (Achievement achievement : ACHIEVEMENTS)
                if (achievement.getDifficulty() == tier && achievement.ordinal() == ordinal)
                    return achievement;
            return null;
        }

        public static boolean hasRequirement(Player player, AchievementDifficulty tier, int ordinal) {
            for (Achievement achievement : ACHIEVEMENTS) {
                if (achievement.getDifficulty() == tier && achievement.ordinal() == ordinal) {
                    if (achievement.getRequirement() == null)
                        return true;
                    if (achievement.getRequirement().isAble(player))
                        return true;
                }
            }
            return false;
        }

        public int getNpcId() {
            return npcId;
        }

        public int getId() {
            return identification;
        }

        public AchievementDifficulty getDifficulty() {
            return difficulty;
        }

        AchievementRequirement getRequirement() {
            return requirement;
        }

        public String getDescription() {
            return description;
        }

        public int getAmount() {
            return amount;
        }

        public String[][] getPoints() {
            return points;
        }

        public Item[] getRewards() {
            return rewards;
        }
    }
}
