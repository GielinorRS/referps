package com.ruse.world.content.skill.impl.slayer;

import com.ruse.engine.task.Task;
import com.ruse.model.*;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.impl.summoning.Familiar;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.weightedRandom.WeightedSystem;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class Slayer {

    private Player player;

    public Slayer(Player p) {
        this.player = p;
    }

    private SlayerTasks slayerTask = SlayerTasks.NO_TASK, lastTask = SlayerTasks.NO_TASK;
    private TaskType taskType = TaskType.EASY;
    private int amountToSlay, taskStreak;
    private String duoPartner, duoInvitation;

    @Getter
    @Setter
    private int easyTaskKC = 0;
    @Getter
    @Setter
    private int medTaskKC = 0;
    @Getter
    @Setter
    private int hardTaskKC = 0;
    @Getter
    @Setter
    private int bossTaskKC = 0;


    public static int medSlayerReq = 5;
    public static int hardSlayerReq = 10;
    public static int bossSlayerReq = 25;

    public void assignTask() {

        if (easyTaskKC < Slayer.medSlayerReq
                && taskType == TaskType.MEDIUM) {
            DialogueManager.sendStatement(player, "You need to complete " + Slayer.medSlayerReq + " easy slayer tasks to do this. (" +
                    easyTaskKC + "/" + medSlayerReq + ")");
            return;
        }
        if (medTaskKC < Slayer.hardSlayerReq
                && taskType == TaskType.HARD) {
            DialogueManager.sendStatement(player, "You need to complete " + Slayer.hardSlayerReq + " medium slayer tasks to do this. (" +
                    medTaskKC + "/" + hardSlayerReq + ")");
            return;
        }
        if (hardTaskKC < Slayer.bossSlayerReq
                && taskType == TaskType.BOSS_SLAYER) {
            DialogueManager.sendStatement(player, "You need to complete " + Slayer.bossSlayerReq + " hard slayer tasks to do this. (" +
                    hardTaskKC + "/" + bossSlayerReq + ")");
            return;
        }


        boolean hasTask = getSlayerTask() != SlayerTasks.NO_TASK && player.getSlayer().getLastTask() != getSlayerTask();
        boolean duoSlayer = duoPartner != null;
        if (duoSlayer && !player.getSlayer().assignDuoSlayerTask())
            return;
        if (hasTask) {
            player.getPacketSender().sendInterfaceRemoval();
            return;
        }

        ArrayList<SlayerTasks> tasks = SlayerTasks.forId(player, taskType);

        slayerTasksWeightedSystem.clear();
        for (SlayerTasks task : tasks) {
            if (player.getSlayerFavourites().isFavourite(task.getNpcId()))
                slayerTasksWeightedSystem.add(1.25, task);
            else
                slayerTasksWeightedSystem.add(1, task);
        }
        SlayerTasks taskToSet = slayerTasksWeightedSystem.next();
        int slayerTaskAmount = taskToSet.getMin() + Misc.getRandom(taskToSet.getMax() - taskToSet.getMin());

        if (taskToSet == player.getSlayer().getLastTask() || NpcDefinition.forId(taskToSet.getNpcId())
                .getSlayerLevel() > player.getSkillManager().getMaxLevel(Skill.SLAYER) || player.getSlayerFavourites().isBlocked(taskType.getNpcId())) {
            assignTask();
            return;
        }
        if (duoSlayer){
            if (!hasRequirements(player, taskToSet) || !hasRequirements( World.getPlayerByName(duoPartner), taskToSet)){
               assignTask();
                return;
           }
        }else{
            if (!hasRequirements(player, taskToSet)){
                assignTask();
                return;
            }
        }

        player.getPacketSender().sendInterfaceRemoval();
        this.amountToSlay = slayerTaskAmount;
        this.slayerTask = taskToSet;
        DialogueManager.start(player, SlayerDialogues.receivedTask(player, getTaskType(), getSlayerTask()));
        PlayerPanel.refreshPanel(player);
        if (duoSlayer) {
            Player duo = World.getPlayerByName(duoPartner);
            duo.getSlayer().setSlayerTask(taskToSet);
            duo.getSlayer().setAmountToSlay(slayerTaskAmount);
            duo.getPacketSender().sendInterfaceRemoval();
            DialogueManager.start(duo, SlayerDialogues.receivedTask(duo, taskType, taskToSet));
            PlayerPanel.refreshPanel(duo);
        }
    }

    public WeightedSystem<SlayerTasks> slayerTasksWeightedSystem = new WeightedSystem<>();

    public static boolean hasRequirements(Player p, SlayerTasks taskToSet) {
        for (NpcRequirements req : NpcRequirements.values()) {
            if (taskToSet.getNpcId() == req.getNpcId()) {
                if (req.getKillCount() > 0) {
                    if (KillsTracker.getTotalKills(p) < req.getKillCount() && KillsTracker.getTotalKills(p )< req.getKillCount()) {
                        return false;
                    }
                } else {
                    int total = KillsTracker.getTotalKillsForNpc(req.getRequireNpcId(), p);
                    if (total < req.getAmountRequired()) {
                        return false;
                    }
                }
                break;
            }
        }
        if (taskToSet == SlayerTasks.LUCIFER && !p.isUnlockedLucifers()) {
            return false;
        }
        if (taskToSet == SlayerTasks.DARK_SUPREME && !p.isUnlockedDarkSupreme()) {
            return false;
        }
        return true;
    }
        public void resetSlayerTask() {
        SlayerTasks task = getSlayerTask();
            if (task == SlayerTasks.NO_TASK) {
                player.sendMessage("You do not currently have a slayer task!");
                return;
            }
        if (getTaskType() == TaskType.BOSS_SLAYER) {
            if (player.getInventory().getAmount(12855) < 25000) {
                player.getPacketSender().sendMessage("You must have 25,000 Upgrade Tokens to reset a task.");
                return;
            }
        } else {
            if (player.getPointsHandler().getSlayerPoints() < 5
                    && player.getSkillManager().getCurrentLevel(Skill.SLAYER) > 20) {
                player.getPacketSender().sendMessage("You must have 5 Slayer Points, or level 20 Slayer to reset a task.");
                return;
            }
        }
        this.slayerTask = SlayerTasks.NO_TASK;
        this.amountToSlay = 0;
        if (player.getSkillManager().skillCape(Skill.SLAYER)) {
            player.getPacketSender().sendMessage("Your cape allows you to keep your slayer streak.");
        } else {
            this.taskStreak = 0;
        }
        if (getTaskType() == TaskType.BOSS_SLAYER) {
            player.getInventory().delete(12855, 25000);
        } else {
            if (player.getSkillManager().getCurrentLevel(Skill.SLAYER) == 1) {
                player.getPacketSender().sendMessage("At level 1 Slayer, you can reset your task for free.");
            } else {
                player.getPointsHandler().setSlayerPoints(player.getPointsHandler().getSlayerPoints() - 5, false);
            }
        }
        PlayerPanel.refreshPanel(player);
        Player duo = duoPartner == null ? null : World.getPlayerByName(duoPartner);
        if (duo != null) {
            if (duo.getSkillManager().skillCape(Skill.SLAYER)) {
                duo.getPacketSender().sendMessage("Your cape allows you to keep your slayer streak.");
            } else {
                duo.getSlayer().setTaskStreak(0);
            }
            duo.getSlayer().setSlayerTask(SlayerTasks.NO_TASK).setAmountToSlay(0);
            duo.getPacketSender()
                    .sendMessage("Your partner exchanged 5 Slayer points to reset your team's Slayer task.");
            PlayerPanel.refreshPanel(duo);
            player.getPacketSender().sendMessage("You've successfully reset your team's Slayer task.");
        } else {
            player.getPacketSender().sendMessage("Your Slayer task has been reset.");
        }
    }

    public void killedNpc(NPC npc) {
        if (slayerTask != SlayerTasks.NO_TASK) {
            if (slayerTask.getNpcId() == npc.getId()) {
                handleSlayerTaskDeath(true);
                if (player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)) {
                    long hp = npc.getDefinition().getHitpoints();

                    double green = (double) hp / 1_000_000;
                    if (green >= 4)
                        green = 4;

                    if (Misc.getRandomDouble() <= green / 100) {
                        player.sendMessage("@cya@You received a Slayer Box (u) for killing your boss slayer task!");
                        player.getInventory().add(23479, 1);
                    }
                } else {
                    int chance = 200;
                    if (slayerTask.getTaskType() == TaskType.MEDIUM) {
                        chance = 100;
                    } else if (slayerTask.getTaskType() == TaskType.HARD) {
                        chance = 50;
                    }
                    if (Misc.getRandom(chance) <= 0) {
                        player.sendMessage("@cya@You received a Slayer Box for killing your slayer task monster!");
                        player.getInventory().add(7120, 1);
                    }
                }
                if (duoPartner != null) {
                    Player duo = World.getPlayerByName(duoPartner);
                    if (duo != null) {
                        if (checkDuoSlayer(player, false)) {
                            duo.getSlayer().handleSlayerTaskDeath(
                                    Locations.goodDistance(player.getPosition(), duo.getPosition(), 20));
                        } else {
                            resetDuo(player, duo);
                        }
                    }
                }
            }
        }
    }

    public void handleSlayerTaskDeath(boolean giveXp) {
        int xp = (int) (NpcDefinition.forId(slayerTask.getNpcId()).getHitpoints() / 2);

        if (slayerTask.getNpcId() == 1160) {
            xp += NpcDefinition.forId(1158).getHitpoints();
        }

        if (amountToSlay > 1) {
            player.getProgressionManager().getProgressionTask(1).incrementProgress(2, 1);
            player.getProgressionManager().getProgressionTask(4).incrementProgress(2, 1);
            amountToSlay--;
        } else {
            player.getPacketSender().sendMessage("")
                    .sendMessage("You've completed your Slayer task! Return to a Slayer master for another one.");
            taskStreak++;
            player.getPointsHandler().incrementSlayerSpree(1);
            player.getProgressionManager().getProgressionTask(0).incrementProgress(0, 1);
            player.getProgressionManager().getProgressionTask(1).incrementProgress(1, 1);
            player.getProgressionManager().getProgressionTask(3).incrementProgress(0, 1);
            player.getProgressionManager().getProgressionTask(6).incrementProgress(1, 1);
            player.getProgressionManager().getProgressionTask(7).incrementProgress(2, 1);
            player.getProgressionManager().getProgressionTask(9).incrementProgress(2, 1);
            player.getProgressionManager().getProgressionTask(10).incrementProgress(0, 1);
            player.getProgressionManager().getProgressionTask(11).incrementProgress(0, 1);
            player.getProgressionManager().getProgressionTask(12).incrementProgress(0, 1);
            player.getProgressionManager().getProgressionTask(13).incrementProgress(1, 1);
            player.getProgressionManager().getProgressionTask(16).incrementProgress(1, 1);

            Achievements.doProgress(player, Achievements.Achievement.SLAYER);
            Achievements.doProgress(player, Achievements.Achievement.MODERATE_SLAYER);

            lastTask = slayerTask;
            slayerTask = SlayerTasks.NO_TASK;
            amountToSlay = 0;

            if (player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)) {
                player.getDailyTaskManager().submitProgressToIdentifier(18, 1);
                Achievements.doProgress(player, Achievements.Achievement.COMPLETE_5_BOSS_TASKS);
                player.getSeasonPass().addExperience(4);

                    Cases.grantCasket(player, 10);


                if (player.getSlayer().getTaskStreak() % 10 == 0) {
                    player.getInventory().add(621, ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.SLAYER_POINTS ? 50 : 25);
                    player.sendMessage("You received 10 extra boss slayer tickets for your slayer streak.");
                } else if (player.getSlayer().getTaskStreak() % 5 == 0) {
                    player.getInventory().add(621, ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.SLAYER_POINTS ? 40 : 20);
                    player.sendMessage("You received 5 extra boss slayer tickets for your slayer streak.");
                } else {
                    player.getInventory().add(621, ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.SLAYER_POINTS ? 30 : 15);
                }
                setBossTaskKC(getBossTaskKC() + 1);

            } else {
                givePoints(player.getSlayer().getLastTask().getTaskType());
            }
        }

        if (giveXp) {
            player.getSkillManager().addExperience(Skill.SLAYER, doubleSlayerXP ? xp * 2 : xp);
        }

        PlayerPanel.refreshPanel(player);
    }

    @SuppressWarnings("incomplete-switch")
    public void givePoints(TaskType master) {
        int pointsReceived = 10;
        switch (master) {
            case EASY:
                pointsReceived = 10;
                setEasyTaskKC(getEasyTaskKC() + 1);
                player.getSeasonPass().addExperience(1);
                Cases.grantCasket(player, 25);
                StarterTasks.doProgress(player, StarterTasks.StarterTask.EASY_TASKS);
                break;
            case MEDIUM:
                pointsReceived = 15;
                setMedTaskKC(getMedTaskKC() + 1);
                player.getSeasonPass().addExperience(2);
                Cases.grantCasket(player, 20);
                player.getDailyTaskManager().submitProgressToIdentifier(2, 1);
                break;
            case HARD:
                pointsReceived = 25;
                setHardTaskKC(getHardTaskKC() + 1);
                player.getSeasonPass().addExperience(3);
                Cases.grantCasket(player, 15);
                break;
        }
        Familiar playerFamiliar = player.getSummoning().getFamiliar();

        if (playerFamiliar != null && playerFamiliar.getSummonNpc() != null) {
           if (playerFamiliar.getSummonNpc().getId() == 22030)
               pointsReceived = (int)((double)pointsReceived*1.5d);
        }
        int per5 = pointsReceived * 2;
        int per10 = pointsReceived * 3;


        if(ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.SLAYER_POINTS) {
            pointsReceived *= 2;
        }

            if (player.getSlayer().getTaskStreak() % 10 == 0) {
            player.getPointsHandler().setSlayerPoints(per10, true);
            player.getPacketSender().sendMessage("You received " + per5 + " Slayer points.");
        } else if (player.getSlayer().getTaskStreak() % 5 == 0) {
            player.getPointsHandler().setSlayerPoints(per5, true);
            player.getPacketSender().sendMessage("You received " + per5 + " Slayer points.");
        } else {
            player.getPointsHandler().setSlayerPoints(pointsReceived, true);
            player.getPacketSender().sendMessage("You received " + pointsReceived + " Slayer points.");
        }

        PlayerPanel.refreshPanel(player);
    }

    public boolean assignDuoSlayerTask() {
        player.getPacketSender().sendInterfaceRemoval();
        if (player.getSlayer().getSlayerTask() != SlayerTasks.NO_TASK) {
            player.getPacketSender().sendMessage("You already have a Slayer task.");
            return false;
        }
        Player partner = World.getPlayerByName(duoPartner);
        if (partner == null) {
            player.getPacketSender().sendMessage("");
            player.getPacketSender().sendMessage("You can only get a new Slayer task when your duo partner is online.");
            return false;
        }
        if (partner.getSlayer().getDuoPartner() == null
                || !partner.getSlayer().getDuoPartner().equals(player.getUsername())) {
            resetDuo(player, null);
            return false;
        }
        if (partner.getSlayer().getSlayerTask() != SlayerTasks.NO_TASK) {
            player.getPacketSender().sendMessage("Your partner already has a Slayer task, head-ass.");
            return false;
        }
        if (partner.getSlayer().getTaskType() != player.getSlayer().getTaskType()) {
            player.getPacketSender().sendMessage("You and your partner need to have the same Slayer master.");
            return false;
        }
        if (partner.getInterfaceId() > 0) {
            player.getPacketSender().sendMessage("Your partner must close all their open interfaces.");
            return false;
        }
        return true;
    }

    public static boolean checkDuoSlayer(Player p, boolean login) {
        if (p.getSlayer().getDuoPartner() == null) {
            return false;
        }
        Player partner = World.getPlayerByName(p.getSlayer().getDuoPartner());
        if (partner == null) {
            return false;
        }
        if (partner.getSlayer().getDuoPartner() == null
                || !partner.getSlayer().getDuoPartner().equals(p.getUsername())) {
            resetDuo(p, null);
            return false;
        }
        if (partner.getSlayer().getTaskType() != p.getSlayer().getTaskType()) {
            resetDuo(p, partner);
            return false;
        }
        if (login) {
            p.getSlayer().setSlayerTask(partner.getSlayer().getSlayerTask());
            p.getSlayer().setAmountToSlay(partner.getSlayer().getAmountToSlay());
        }
        return true;
    }

    public static void resetDuo(Player player, Player partner) {
        if (partner != null) {
            if (partner.getSlayer().getDuoPartner() != null
                    && partner.getSlayer().getDuoPartner().equals(player.getUsername())) {
                partner.getSlayer().setDuoPartner(null);
                partner.getPacketSender().sendMessage("Your Slayer duo team has been disbanded.");
                PlayerPanel.refreshPanel(partner);
            }
        }
        player.getSlayer().setDuoPartner(null);
        player.getPacketSender().sendMessage("Your Slayer duo team has been disbanded.");
        PlayerPanel.refreshPanel(player);
    }

    public void handleInvitation(boolean accept) {
        if (duoInvitation != null) {
            Player inviteOwner = World.getPlayerByName(duoInvitation);
            if (inviteOwner != null) {
                if (accept) {
                    if (duoPartner != null) {
                        player.getPacketSender().sendMessage("You already have a Slayer duo partner.");
                        inviteOwner.getPacketSender()
                                .sendMessage("" + player.getUsername() + " already has a Slayer duo partner.");
                        return;
                    }
                    inviteOwner.getPacketSender()
                            .sendMessage("" + player.getUsername() + " has joined your duo Slayer team.")
                            .sendMessage("Seek respective Slayer master for a task.");
                    inviteOwner.getSlayer().setDuoPartner(player.getUsername());
                    PlayerPanel.refreshPanel(inviteOwner);
                    player.getPacketSender()
                            .sendMessage("You have joined " + inviteOwner.getUsername() + "'s duo Slayer team.");
                    player.getSlayer().setDuoPartner(inviteOwner.getUsername());
                    PlayerPanel.refreshPanel(player);
                } else {
                    player.getPacketSender().sendMessage("You've declined the invitation.");
                    inviteOwner.getPacketSender()
                            .sendMessage("" + player.getUsername() + " has declined your invitation.");
                }
            } else
                player.getPacketSender().sendMessage("Failed to handle the invitation.");
        }
    }

    public void handleSlayerRingTP2(int itemId) {
        if (!player.getClickDelay().elapsed(4500))
            return;
        if (player.getMovementQueue().isLockMovement())
            return;
        SlayerTasks task = getSlayerTask();
        if (task == SlayerTasks.NO_TASK)
            return;
        Position slayerTaskPos = new Position(task.getTaskPosition().getX(), task.getTaskPosition().getY(),
                task.getTaskPosition().getZ());
        if (!TeleportHandler.checkReqs(player, slayerTaskPos))
            return;
        player.getProgressionManager().getProgressionTask(7).incrementProgress(0, 1);
        TeleportHandler.teleportPlayer(player, slayerTaskPos, player.getSpellbook().getTeleportType());
    }

    public void handleSlayerRingTP(int itemId) {
        if (!player.getClickDelay().elapsed(4500))
            return;
        if (player.getMovementQueue().isLockMovement())
            return;
        SlayerTasks task = getSlayerTask();
        if (task == SlayerTasks.NO_TASK)
            return;
        Position slayerTaskPos = new Position(task.getTaskPosition().getX(), task.getTaskPosition().getY(),
                task.getTaskPosition().getZ());
        if (!TeleportHandler.checkReqs(player, slayerTaskPos))
            return;
        player.getProgressionManager().getProgressionTask(7).incrementProgress(0, 1);
        TeleportHandler.teleportPlayer(player, slayerTaskPos, player.getSpellbook().getTeleportType());
        Item slayerRing = new Item(itemId);
        player.getInventory().delete(slayerRing);
        if (slayerRing.getId() < 13288)
            player.getInventory().add(slayerRing.getId() + 1, 1);
        if (slayerRing.getId() == 27)
            player.getInventory().delete(28, 1).add(27, 1);


        else
            player.getPacketSender().sendMessage("Your Ring of Slaying degrades.");
    }

    public int getAmountToSlay() {
        return this.amountToSlay;
    }

    public Slayer setAmountToSlay(int amountToSlay) {
        this.amountToSlay = amountToSlay;
        return this;
    }

    public int getTaskStreak() {
        return this.taskStreak;
    }

    public Slayer setTaskStreak(int taskStreak) {
        this.taskStreak = taskStreak;
        return this;
    }

    public SlayerTasks getLastTask() {
        return this.lastTask;
    }

    public void setLastTask(SlayerTasks lastTask) {
        this.lastTask = lastTask;
    }

    public boolean doubleSlayerXP = false;

    public Slayer setDuoPartner(String duoPartner) {
        this.duoPartner = duoPartner;
        return this;
    }

    public String getDuoPartner() {
        return duoPartner;
    }

    public SlayerTasks getSlayerTask() {
        return slayerTask;
    }

    public Slayer setSlayerTask(SlayerTasks slayerTask) {
        this.slayerTask = slayerTask;
        return this;
    }

    public TaskType getTaskType() {
        return taskType;
    }

    public void setTaskType(TaskType master) {
        this.taskType = master;
    }

    public void setDuoInvitation(String player) {
        this.duoInvitation = player;
    }

    public static boolean handleRewardsInterface(Player player, int button) {
        if (player.getInterfaceId() == 36000) {
            switch (button) {
                case -29534:
                    player.getPacketSender().sendInterfaceRemoval();
                    break;
                case -29522:
                    if (player.getPointsHandler().getSlayerPoints() < 10) {
                        player.getPacketSender().sendMessage("You do not have 10 Slayer points.");
                        return true;
                    }
                    PlayerPanel.refreshPanel(player);
                    player.getPointsHandler().setSlayerPoints(-10, true);
                    int num = 10000 / Difficulty.getDifficultyModifier(player, Skill.SLAYER);
                    player.getSkillManager().addExperience(Skill.SLAYER, num);
                    player.getPacketSender().sendMessage("You've bought " + Misc.format(Misc.applyBonusExp(10000, player))
                            + " Slayer XP for 10 Slayer points.");
                    break;
                case -29519:
                    if (player.getPointsHandler().getSlayerPoints() < 300) {
                        player.getPacketSender().sendMessage("You do not have 300 Slayer points.");
                        return true;
                    }
                    if (player.getSlayer().doubleSlayerXP) {
                        player.getPacketSender().sendMessage("You already have Double Slayer Points.");
                        return true;
                    }
                    player.getPointsHandler().setSlayerPoints(-300, true);
                    player.getSlayer().doubleSlayerXP = true;
                    PlayerPanel.refreshPanel(player);
                    player.getPacketSender().sendMessage("You will now permanently receive double Slayer experience.");
                    break;
                case -29531:
                    ShopManager.getShops().get(47).open(player);
                    break;
            }
            player.getPacketSender().sendString(36030,
                    "Current Points:   " + player.getPointsHandler().getSlayerPoints());
            return true;
        }
        return false;
    }
}
