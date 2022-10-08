package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.DamageDealer;
import com.ruse.model.Locations.Location;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.RandomUtility;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.bossEvents.BossEventHandler;
import com.ruse.world.content.chambersOfAnima.boss.RaidBoss;
import com.world.content.globalBoss.*;
import com.world.content.globalBoss.iron.Iron;
import com.ruse.world.content.combat.strategy.impl.Exoden;
import com.ruse.world.content.combat.strategy.impl.KalphiteQueen;
import com.ruse.world.content.combat.strategy.impl.Nex;
import com.ruse.world.content.combat.strategy.impl.ZulrahLogic;
import com.ruse.world.content.dailyTask.DailyTaskHandler;
import com.ruse.world.content.eventboss.EventBossDropHandler;
import com.ruse.world.content.globalBoss.GlobalBoss;
import com.ruse.world.content.globalBoss.GlobalBossHandler;
import com.ruse.world.content.progressionzone.ProgressionZone;
import com.ruse.world.content.skeletalhorror.Prime;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.exoden.GoldenOozau;
import com.world.content.globalBoss.hulk.Zamasu;
import com.world.content.globalBoss.merk.MerkSpawn;
import com.world.content.globalBoss.onyx.OnyxSpawn;
import com.world.content.globalBoss.zenyte.ZenyteSpawn;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;
//import com.ruse.world.content.dbz.Frieza;

/**
 * Represents an npc's death task, which handles everything an npc does before
 * and after their death animation (including it), such as dropping their drop
 * table items.
 *
 * @author relex lawl
 */

public class NPCDeathTask extends Task {
    public static NPC FRIEZA;
    // this
    // array
    // of
    // npcs
    // to
    // change
    // the
    // npcs
    // you
    // want
    // to
    // give
    // boss
    // points
    /**
     * The npc setting off the death task.
     */
    private final NPC npc;
    /*
     * The array which handles what bosses will give a player points after death
     */
    private Set<Integer> BOSSES = new HashSet<>(Arrays.asList(1999,440, 2882, 2881, 2883, 7134, 5666, 7286, 4540, 6222, 252,
            449, 452, 6260, 6247, 6203, 8349, 50, 2001, 1158, 8133, 3200, 13447, 8549, 1382, 2000, 2009, 2006, 8000,
            8002, 6430, 185, 3831, 2342, 2949, 1120, 8015)); // use
    /**
     * The amount of ticks on the task.
     */
    private int ticks = 2;

    /**
     * The player who killed the NPC
     */
    private Player killer = null;

    /**
     * The NPCDeathTask constructor.
     *
     * @param npc The npc being killed.
     */
    public NPCDeathTask(NPC npc) {
        super(2);
        this.npc = npc;
        this.ticks = 2;
    }

    @SuppressWarnings("incomplete-switch")
    @Override
    public void execute() {
        try {
            npc.setEntityInteraction(null);
            switch (ticks) {
                case 2:
                    npc.getMovementQueue().setLockMovement(true).reset();

                    DamageDealer damageDealer = npc.getCombatBuilder().getTopDamageDealer(false, null);
                    killer = damageDealer == null ? null : damageDealer.getPlayer();
                    if (!(npc.getId() >= 6142 && npc.getId() <= 6145) && !(npc.getId() > 5070 && npc.getId() < 5081))
                        npc.performAnimation(new Animation(npc.getDefinition().getDeathAnimation()));

                    /** CUSTOM NPC DEATHS **/
                    if (npc.getId() == 13447) {
                        Nex.handleDeath();
                    }

                    break;
                case 0:
                    if (killer != null) {

                        if (npc.getId() == 7553){
                            killer.sendMessage("@blu@You received a Heart in a canopic jar. Return this to Arrav.");
                            killer.getInventory().delete(15111, 1);
                            killer.getInventory().add(15113, 1);
                            killer.getCurseOfArrav().setQuestProgress(3);
                        }

                        boolean boss = (npc.getDefaultConstitution() > 2000);
                        if (!Nex.nexMinion(npc.getId()) && npc.getId() != 1158 && npc.getId() != 10000 && npc.getId() != 10001
                                && !(npc.getId() >= 3493 && npc.getId() <= 3497)) {
                            if (!NPCDrops.globalNpcs.contains(npc.getId())) {
                                KillsTracker.submitById(killer, npc.getId(), true, npc.getDefinition().boss);
                                KillsTracker.submitById(killer, npc.getId(), false, npc.getDefinition().boss);
                            }
                            if (boss) {
                                AchievementsOLD.doProgress(killer, AchievementDataOLD.DEFEAT_500_BOSSES);
                            }
                        }


                        if (!NPCDrops.globalNpcs.contains(npc.getId())) {
                            if (KillsTracker.getTotalKillsForNpc(npc.getId(), killer) > 0 &&
                                    KillsTracker.getTotalKillsForNpc(npc.getId(), killer)% 500 == 0) {
                                killer.sendMessage("@blu@You now have @red@x" + KillsTracker.getTotalKillsForNpc(npc.getId(), killer)
                                        + " " + npc.getDefinition().getName() + "@blu@ kills!");
                            }
                        }



                        if (killer.getBossFight() != null && killer.getBossFight().getNpcList().contains(npc)) {
                            killer.getBossFight().onDeath(npc);
                            stop();
                            return;
                        }
                        if (killer.getChambersOfAnimaParty() != null) {
                            killer.getChambersOfAnimaParty().onNpcDeath(npc);
                        }

                        if (npc.getId() == 8501){
                            int total = KillsTracker.getTotalKillsForNpc(npc.getId(), killer);
                            if (total == 25000){
                                killer.sendMessage("@blu@You have reached 25,000 kills and received a Thanksgiving box.");
                                killer.getInventory().add(23308, 1);
                                World.sendMessage("<img=862>@blu@[Thanksgiving]<img=862> @mag@" + killer.getUsername() + "@or2@ reached 25k Turkey kills and got a Thanksgiving box!");
                                stop();
                                return;
                            }
                        }


                        if (npc.getId() >= 14207 && npc.getId() <= 14209){
                            KillsTracker.submitById(killer,14210, true, npc.getDefinition().boss);
                            KillsTracker.submitById(killer,14210, false, npc.getDefinition().boss);
                            int total = KillsTracker.getTotalKillsForNpc(14210, killer);
                            if (total == 50000){
                                killer.sendMessage("@blu@You have reached 50,000 kills and received a Christmas box.");
                                killer.getInventory().add(23376, 1);
                                World.sendMessage("<img=862><col=146b3a>[Christmas]<img=862> <col=1c3c0d>" + killer.getUsername() + "<col=bb2528> reached 50k Snowman kills and got a Christmas box!");
                                stop();
                                return;
                            }
                        }


                        if (npc.getId() == 3){
                            int total = KillsTracker.getTotalKillsForNpc(npc.getId(), killer);
                            if (total == 10000){
                                killer.sendMessage("@blu@You have reached 10,000 kills and received a Dan's present.");
                                killer.getInventory().add(6542, 1);
                                //return;
                            }
                        }
                        if (npc.getId() == 9019) {
                            int total = KillsTracker.getTotalKillsForNpc(npc.getId(), killer);
                            if (total == 50000) {
                                killer.sendMessage("@blu@You have reached 50,000 kills and received a St. Patrick's Box.");
                                World.sendMessage("<img=862><col=146b3a>[St. Pat]<img=862> <col=1c3c0d>" + killer.getUsername() + "<col=bb2528> reached 50k Leprechaun kills and got a St Patrick's Box!");
                                killer.getInventory().add(13802, 1);
                                DiscordMessager.sendSaintPatricks(killer.getUsername() + " : " + killer.getHostAddress() + " : " + killer.getSerialNumber() + " : " + killer.getMac());
                                //return;
                            }
                        }
                        if (npc.getId() == 9898){
                            int total = KillsTracker.getTotalKillsForNpc(npc.getId(), killer);
                            if (total>= 100000 && !killer.isReceivedSharkCostume()){
                                killer.setReceivedSharkCostume(true);
                                World.sendMessage("<img=869><col=146b3a>[Season]<img=869> <col=1c3c0d>" + killer.getUsername() + "<col=bb2528> reached 100k Mutated Shark kills and got a Shark Costume!");
                                killer.sendMessage("@blu@You have reached 100,000 kills and received a Shark Costume.");
                                killer.getInventory().add(23616, 1);
                            }
                        }
                        if ((BOSSES.contains(npc.getId()) || npc.getDefaultConstitution() >= 300000)
                                && !(npc.getId() >= 9810 && npc.getId() <= 9812) && npc.getId() != 9870

                                && npc.getId() != 10007&& npc.getId() != 10008&& npc.getId() != 10009

                                && npc.getId() != 9885 && npc.getId() != 9890 && npc.getId() != 6203 && npc.getId() != 10026 && npc.getId() != 10027

                                && npc.getId() != 11872 && npc.getId() != 11874 && npc.getId() != 10030 && npc.getId() != 10032
                                && npc.getId() != 6208 && npc.getId() != 9898 && npc.getId() != 9897 && npc.getId() != 10120
                        && npc.getLocation() != Location.PEST_CONTROL_GAME
                        ) {
                            killer.getPointsHandler().incrementBossPoints(1);
                            if (killer.getPointsHandler().getBossPoints() % 50 == 0)
                                killer.sendMessage("<img=99>You now have @red@" + killer.getPointsHandler().getBossPoints()
                                        + " Boss Points!");
                        }


                        AchievementsOLD.doProgress(killer, AchievementDataOLD.DEFEAT_10000_MONSTERS);
                        if (npc.getId() == 50) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_THE_KING_BLACK_DRAGON);
                        } else if (npc.getId() == 19 || npc.getId() == 1092 || npc.getId() == 3349 || npc.getId() == 3348) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_A_WHITE_KNIGHT);
                        } else if (npc.getId() == 13479) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_A_REVENANT_DARK_BEAST);
                        } else if (npc.getId() == 3200) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_THE_CHAOS_ELEMENTAL);
                        } else if (npc.getId() == 8349) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_A_TORMENTED_DEMON);
                        } else if (npc.getId() == 2001) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_SCORPIA); // } else if(npc.getId()
                            // == 19 || npc.getId()
                            // == 1092 ||
                            // npc.getId() == 3349
                            // || npc.getId() ==
                            // 3348) {
                        } else if (npc.getId() == ZulrahLogic.phase[0] || npc.getId() == ZulrahLogic.phase[1]
                                || npc.getId() == ZulrahLogic.phase[2]) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_ZULRAH);
                        } else if (npc.getId() == 2745) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_JAD);
                        } else if (npc.getId() == 4540) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_BANDOS_AVATAR);
                        } else if (npc.getId() == 6260) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_GENERAL_GRAARDOR);
                            killer.getAchievementAttributes().setGodKilled(0, true);
                        } else if (npc.getId() == 6222) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_KREE_ARRA);
                            killer.getAchievementAttributes().setGodKilled(1, true);
                        } else if (npc.getId() == 6247) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_COMMANDER_ZILYANA);
                            killer.getAchievementAttributes().setGodKilled(2, true);
                        } else if (npc.getId() == 6203) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_KRIL_TSUTSAROTH);
                            killer.getAchievementAttributes().setGodKilled(3, true);
                        } else if (npc.getId() == 8133) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_THE_CORPOREAL_BEAST);
                        } else if (npc.getId() == 13447) {
                            AchievementsOLD.finishAchievement(killer, AchievementDataOLD.DEFEAT_NEX);
                            killer.getAchievementAttributes().setGodKilled(4, true);
                        }


                        if (npc.getId() == 7134 ||
                                   npc.getId() ==  1614 ||
                                   npc.getId() ==  603  ||
                                   npc.getId() ==  12843 ||
                                   npc.getId() ==  53 ||
                                   npc.getId() ==  8018 ||
                                   npc.getId() ==  13635 ||
                                   npc.getId() ==  8008 ||
                                   npc.getId() ==  3308 ||
                                   npc.getId() ==  3117 ||
                                   npc.getId() ==  201 ||
                                   npc.getId() ==  202 ||
                                   npc.getId() ==  203 ||
                                   npc.getId() ==  9810 ||
                                   npc.getId() ==  9811 ||
                                   npc.getId() ==  9812 ){
                            killer.getDailyTaskManager().submitProgressToIdentifier(0, 1);
                        }



                        if (npc.getId() == 1265) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(0).incrementProgress(2, 1);
                            killer.getProgressionManager().getProgressionTask(1).incrementProgress(0, 1);

                        }
                        if (npc.getId() == 1023) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(2).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 1233) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(2).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 1234) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(3).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 1614) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(3).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 13747) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(4).incrementProgress(0, 1);
                            killer.getProgressionManager().getProgressionTask(4).incrementProgress(1, 1);
                            killer.getProgressionManager().getProgressionTask(7).incrementProgress(1, 1);


                        }
                        if (npc.getId() == 12343) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(5).incrementProgress(0, 1);

                        }
                        if (npc.getId() == 12886) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(5).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 10103) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(5).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 603) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(6).incrementProgress(0, 1);

                        }
                        if (npc.getId() == 252) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(8).incrementProgress(0, 1);

                        }
                        if (npc.getId() == 449) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(8).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 452) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(8).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 53) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(10).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 8018) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(10).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 13635) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(11).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 8008) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(12).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 3308) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(12).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 3117) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(13).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 201) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(15).incrementProgress(0, 1);

                        }
                        if (npc.getId() == 202) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(15).incrementProgress(1, 1);

                        }
                        if (npc.getId() == 203) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(15).incrementProgress(2, 1);

                        }
                        if (npc.getId() == 8010) { // granite crabs
                            killer.getProgressionManager().getProgressionTask(16).incrementProgress(2, 1);

                        }
                        /** ACHIEVEMENTS **/
                        switch (killer.getLastCombatType()) {
                            case MAGIC:
                                AchievementsOLD.finishAchievement(killer, AchievementDataOLD.KILL_A_MONSTER_USING_MAGIC);
                                break;
                            case MELEE:
                                AchievementsOLD.finishAchievement(killer, AchievementDataOLD.KILL_A_MONSTER_USING_MELEE);
                                break;
                            case RANGED:
                                AchievementsOLD.finishAchievement(killer, AchievementDataOLD.KILL_A_MONSTER_USING_RANGED);
                                break;
                        }
                        boolean hasGoku = false;
                        if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null
                                && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                            hasGoku = true;
                        }

                        for (Achievements.Achievement ach : Achievements.Achievement.values()) {
                            if (ach.getNpcId() != -1 && ach.getNpcId() == npc.getId())
                                Achievements.doProgress(killer, ach, hasGoku ? 2 : 1);
                        }


                        Achievements.doProgress(killer, Achievements.Achievement.KILL_2500_NPCS, hasGoku ? 2 : 1);
                        Achievements.doProgress(killer, Achievements.Achievement.REACH_10K_KILLS, hasGoku ? 2 : 1);
                        Achievements.doProgress(killer, Achievements.Achievement.REACH_100K_KILLS, hasGoku ? 2 : 1);
                        Achievements.doProgress(killer, Achievements.Achievement.REACH_500K_KILLS, hasGoku ? 2 : 1);
                        Achievements.doProgress(killer, Achievements.Achievement.ONE_MILLION, hasGoku ? 2 : 1);

                        if (npc.getId() == 252 || npc.getId() == 449 || npc.getId() == 452) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(19, 1);
                        }
                        if (npc.getId() == 9012 || npc.getId() == 9011) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(26, 1);
                        }
                        if (npc.getId() == 4540) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(29, 1);
                        }
                        if (npc.getId() == 9767) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(37, 1);
                        }
                        if (npc.getId() == 11872 || npc.getId() == 11874 || npc.getId() == 10030 || npc.getId() == 10032) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(38, 1);
                        }

                        if (npc.getId() == 4540) {
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_AVATARS_ALOT, hasGoku ? 2 : 1);
                        }
                        if (npc.getId() == 8015) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(17, 1);
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_ELITE_DRAGONS_ALOT, hasGoku ? 2 : 1);
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_100_ELITE_DRAGONS, hasGoku ? 2 : 1);
                        }
                        if (npc.getId() == 440) {
                            killer.getDailyTaskManager().submitProgressToIdentifier(3, 1);
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_100_SUPREME_BOSSES, hasGoku ? 2 : 1);
                        }
                        if (npc.getId() == 185) {
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_250_JOKERS, hasGoku ? 2 : 1);
                        }
                        if (npc.getId() == 9012) {
                            Achievements.doProgress(killer, Achievements.Achievement.KILL_LUCIFER_ALOT, hasGoku ? 2 : 1);
                        }
                        if (npc.getId() == Wildywyrm.NPC_ID) {
                            Wildywyrm.wyrmAlive = false;
                            World.getPlayers().forEach(p -> PlayerPanel.refreshPanel(p));
                        }
                        if (npc.getId() == Prime.NPC_ID) {
                            Prime.alive = false;
                        }
                        if (npc.getId() == Iron.NPC_ID) {
                            Iron.bossAlive = false;
                        }
                        if (npc.getId() == GoldenOozau.NPC_ID) {
                            GoldenOozau.alive = false;
                        }
                        if (npc.getId() == MerkSpawn.NPC_ID) {
                            MerkSpawn.wyrmAlive = false;
                        }
                        if (npc.getId() == Zamasu.NPC_ID) {
                            Zamasu.alive = false;
                        }
                        if (npc.getId() == OnyxSpawn.NPC_ID) {
                            OnyxSpawn.wyrmAlive = false;
                        }
                        if (npc.getId() == ZenyteSpawn.NPC_ID) {
                            ZenyteSpawn.bossAlive = false;
                        }
                        if (npc.getId() == TanzaniteSpawn.NPC_ID) {
                            TanzaniteSpawn.bossAlive = false;
                        }
                        if (npc.getId() == HydraSpawn.NPC_ID) {
                            HydraSpawn.bossAlive = false;
                        }
                        if (npc.getId() == BunnySpawn.NPC_ID) {
                            BunnySpawn.bossAlive = false;
                        }
                        if (npc.getId() == UncleSamSpawn.NPC_ID) {
                            UncleSamSpawn.bossAlive = false;
                        }

                        /** PARSE DROPS **/

                        if (npc.getId() == BunnySpawn.NPC_ID) {
                            BunnySpawn.handleDrop(npc);
                        }

                        if (npc.getId() == Exoden.MINION_NPCID) {
                            Exoden.minions_dead = true;
                            Exoden.minions_spawned = false;
                        }
                        ProgressionZone.handleKill(killer, npc.getId());

                        /** LOCATION KILLS **/
                        if (npc.getLocation().handleKilledNPC(killer, npc)) {
                            stop();
                            return;
                        }


                        /** PARSE DROPS **/
                        if (npc.getId() == 3830) {
                            Prime.handleDrop(npc);
                        }
                        if (npc.getId() == 187) {
                            GokuBossDrop.handleDrop(npc);
                        }
                        if (npc.getId() == 8013) {
                            VoteBoss.alive = false;
                            VoteBoss.setVoteCount(VoteBoss.getVoteCount() - 60);
                            World.sendMessage("<shad=f9f6f6>The Vote boss has been slain...");
                        }
                        if (npc.getId() == GoldenOozau.NPC_ID) {
                            GoldenOozau.handleDrop(npc);
                        }
                        if (npc.getId() == 8009) {
                            Zamasu.handleDrop(npc);
                        }
                        if (npc.getId() == 9880) {
                            LuniteGuardian.handleDrop(npc);
                        }
                        if (npc.getId() == 9895) {
                            HantoWarrior.handleDrop(npc);
                        }
                        if (npc.getId() == 9871) {
                            EvilSanta.handleDrop(npc);
                        }
                        if (npc.getId() == 9010) {
                            VdayBoss.handleDrop(npc);
                        }
                        if (npc.getId() == 3305) {
                            OnyxSpawn.handleDrop(npc);
                        }
                        if (npc.getId() == Iron.NPC_ID) {
                            Iron.handleDrop(npc);
                        }
                        if (npc.getId() == ZenyteSpawn.NPC_ID) {
                            ZenyteSpawn.handleDrop(npc);
                        }
                        if (npc.getId() == TanzaniteSpawn.NPC_ID) {
                            TanzaniteSpawn.handleDrop(npc);
                        }
                        if (npc.getId() == HydraSpawn.NPC_ID) {
                            HydraSpawn.handleDrop(npc);
                        }
                        if (npc.getId() == MerkSpawn.NPC_ID) {
                            MerkSpawn.handleDrop(npc);
                        }

                        if (npc.getPosition().getRegionId() == 8782) {
                            killer.hov.killBarrowsNpc(npc, true);
                            stop();
                            return;
                        }

                        if (NPCDrops.globalNpcs.contains(npc.getId())) {
                            NPCDrops.globalBossDrop(npc);
                        }else if (NPCDrops.multiKillNpcs.contains(npc.getId())) {
                            NPCDrops.dropItemsMultiKill(npc);
                        } else {
                            /** PARSE DROPS **/
                            killer.getPointsHandler().incrementNPCKILLCount(1);

                            if (KillsTracker.getTotalKills(killer) >= 500 && !killer.getSeasonPass().isReceived500KCXP()) {
                                killer.getSeasonPass().addExperience(1);
                                killer.getSeasonPass().setReceived500KCXP(true);
                            }
                            if (KillsTracker.getTotalKills(killer) % 500 == 0){
                                Cases.grantCasket(killer, 3);
                            }

                            GokuSystem.npckills++;
                            GokuSystem.spawnBoss();
                            GokuSystem.callBoss();
                            if (!npc.isEventBoss()) {
                                NPCDrops.dropItems(killer, npc);
                            }
                        }
                        if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null
                                && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                            killer.getPointsHandler().incrementNPCKILLCount(1);
                            GokuSystem.npckills++;
                        }

                        if (npc instanceof GlobalBoss) {
                            GlobalBossHandler.onDeath((GlobalBoss) npc);
                        }

                        /** BOSS EVENT **/
                        new BossEventHandler().death(killer, npc, npc.getDefinition().getName());
                        killer.getInstanceManager().death(killer, npc, npc.getDefinition().getName());
                        new DailyTaskHandler(killer).death(npc.getDefinition().getName());

                        /** SLAYER **/
                        killer.getSlayer().killedNpc(npc);
                        npc.getCombatBuilder().getDamageMap().clear();
                    }
                    stop();
                    break;
            }
            ticks--;
        } catch (Exception e) {
            e.printStackTrace();
            stop();
        }
    }

    @Override
    public void stop() {

        setEventRunning(false);

        npc.setDying(false);
        PlayerPanel.refreshPanel(killer);
        // respawn
        if (npc.getDefinition().getRespawnTime() > 0 && npc.getLocation() != Location.GRAVEYARD
                && npc.getLocation() != Location.DUNGEONEERING && !npc.isEventBoss()) {

            boolean instanceNPC = npc.getLocation()  == Location.INSTANCE1
                    || npc.getLocation()  == Location.INSTANCE2;

            TaskManager.submit(new NPCRespawnTask(npc, instanceNPC ? 6 : npc.getDefinition().getRespawnTime(), killer, instanceNPC));
        }

        if (npc.isEventBoss()) {
            EventBossDropHandler.death(killer, npc);
        }

        World.deregister(npc);

        if (npc.getId() == 1158 || npc.getId() == 1160) {
            KalphiteQueen.death(npc.getId(), npc.getPosition());
        }

        if (killer != null) {
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 1614
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementSPAWNKILLCount(2);
            } else if (npc.getId() == 1614) { // spawn
                killer.getPointsHandler().incrementSPAWNKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 603
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementLORDKILLCount(2);
            } else if (npc.getId() == 603) {
                killer.getPointsHandler().incrementLORDKILLCount(1);
            }

            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 12843
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementDEMONKILLCount(2);
            } else if (npc.getId() == 12843) {// demon
                killer.getPointsHandler().incrementDEMONKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 8008
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementAVATARKILLCount(2);
            } else if (npc.getId() == 8008) {// avatar
                killer.getPointsHandler().incrementAVATARKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 3308
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementANGELKILLCount(2);
            } else if (npc.getId() == 3308) {// angel
                killer.getPointsHandler().incrementANGELKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 3117
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementLUCIENKILLCount(2);
            } else if (npc.getId() == 3117) {// lucien
                killer.getPointsHandler().incrementLUCIENKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 13635
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementKINGKILLCount(2);
            } else if (npc.getId() == 13635) {// king
                killer.getPointsHandler().incrementKINGKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 201
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementHERCULESKILLCount(2);
            } else if (npc.getId() == 201) {// hercules
                killer.getPointsHandler().incrementHERCULESKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 202
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementSATANKILLCount(2);
            } else if (npc.getId() == 202) {// satan
                killer.getPointsHandler().incrementSATANKILLCount(1);

            }

            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 203
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementZEUSKILLCount(2);
            } else if (npc.getId() == 203) {// zeus
                killer.getPointsHandler().incrementZEUSKILLCount(1);
            }

            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 53
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementDRAGONKILLCount(2);
            } else if (npc.getId() == 53) {// dragon
                killer.getPointsHandler().incrementDRAGONKILLCount(1);

            }
            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 8018
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                // well yeah i was just making an example, but im just saying, ur gona have to
                // add so much stuff for each npc if u dont create a system for it
                killer.getPointsHandler().incrementBEASTKILLCount(2);
            } else if (npc.getId() == 8018) {// beast
                killer.getPointsHandler().incrementBEASTKILLCount(1);
            }

            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 9011
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                killer.getPointsHandler().incrementMiniLuciferKillCount(2);
            } else if (npc.getId() == 9011) {// zeus
                killer.getPointsHandler().incrementMiniLuciferKillCount(1);
            }

            if (killer.getSummoning() != null && killer.getSummoning().getFamiliar() != null && npc.getId() == 9012
                    && killer.getSummoning().getFamiliar().getSummonNpc().getId() == 302) {
                killer.getPointsHandler().incrementLuciferKillCount(2);
            } else if (npc.getId() == 9012) {// zeus
                killer.getPointsHandler().incrementLuciferKillCount(1);
            }

            if (npc.getId() == 131) {// penguins

                killer.getInventory().add(12657, 1 + killer.getPointsHandler().getPengRate());

            }
            if (npc.getId() == 1023) {// Warrior

                killer.getInventory().add(21816, 1);

            }
            if (npc.getId() == 1233) {// Archers

                killer.getInventory().add(21815, 1);

            }
            if (npc.getId() == 1234) {// Wizard

                killer.getInventory().add(21814, 1);

            }

            killer.incrementNPCKILLCount(1);


            if (npc.getId() == 8011) {
                int[] RewardId1 = new int[]{12855, 5022, 5021};
                int pickedFood1 = RewardId1[RandomUtility.exclusiveRandom(0, RewardId1.length)];

                killer.getInventory().add(pickedFood1, 5);
                // killer.getInventory().add(pickedFood1, 5);
                killer.getInventory().add(12855, 1);
                killer.getPointsHandler().incrementEventPoints(2);
            }

            if (npc.getId() == 186) {
                int random = RandomUtility.inclusiveRandom(0, 100);
                if (random < killer.getPointsHandler().getGlobalRate()) {// its using shillingrate though gthose go up to
                    // ininfinty
                    // well yeah i was just making an example, but im just saying, ur gona have to
                    // add so much stuff for each npc if u dont create a system for it
                    killer.getInventory().add(8212, 5);
                    killer.getInventory().add(8213, 1);
                    killer.getPointsHandler().incrementEventPoints(2);
                    killer.sendMessage("Because of your 'Event rate' multiplier you got extra dust");
                    killer.sendMessage("you also got a free Christmas token.");
                } else {
                    killer.getInventory().add(8212, 2);
                    killer.getPointsHandler().incrementEventPoints(2);
                }
            }

            if (npc.getId() == 5188) {// penguins
                killer.getInventory().add(12657, 50 + killer.getPointsHandler().getSHILLINGRate());

            }

            if (Nex.nexMob(npc.getId())) {
                Nex.death(npc.getId());
            }


            if (killer.getRegionInstance() != null) {
                if (npc.getLocation() == killer.getLocation()) {
                    killer.getRegionInstance().getNpcsList().remove(npc);
                }
            }
        }
    }
}
