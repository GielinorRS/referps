package com.ruse.world.content.skill.impl.slayer;

import com.ruse.GameSettings;
import com.ruse.model.Position;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.NpcRequirements;
import com.ruse.world.entity.impl.player.Player;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Gabriel Hannason
 */

@Getter
@AllArgsConstructor
public enum SlayerTasks {

    NO_TASK(null, -1, null, -1, -1, null),


    GRANITE_CRAB(TaskType.EASY, 1700, "Granite crab can be found at the betrayed dungeon.", 60, 120,
            new Position(1865, 5233, 0)),
    HALLUCINAITON_TOAD(TaskType.EASY, 1738, "Hallucination toad can be found at the betrayed dungeon.", 60, 120,
            new Position(1909, 5232, 0)),
    LAVANNOTH(TaskType.EASY, 1702, "Lavannoth can be found at the betrayed dungeon.", 60, 120,
            new Position(1874, 5200, 0)),
    EVIL_CHINCHOMPA(TaskType.EASY, 1723, "Evil chinchompa can be found at the betrayed dungeon.", 60, 120,
            new Position(1903, 5197, 0)),
    GHOULORD(TaskType.EASY, 1708, "Ghoulord can be found at the betrayed dungeon.", 60, 120,
            new Position(1886, 5222, 0)),

    LORD(TaskType.EASY, 603, "Lord can be found by using the Zones interface", 100, 200,
            GameSettings.LORDS),
    FRACTITE_DEMON(TaskType.EASY, 12843, "Fractite Demon can be found by using the Zones interface", 100, 200,
            GameSettings.DEMON),
    JOYX_DRAGON(TaskType.EASY, 53, "Joyx Dragon can be found by using the Zones interface", 100, 200,
            GameSettings.DRAGON),
    MAGE_BEAST(TaskType.EASY, 8018, "Mage Beast can be found by using the Zones interface", 200, 300,
            GameSettings.BEAST),

    //MEDIUM

    JELLO(TaskType.MEDIUM, 1729, "Jello can be found at the cursed dungeon.", 100, 200,
            new Position(1865, 5233, 1)),
    ELEMENTAL_FIRE(TaskType.MEDIUM, 1741, "Elemental fire can be found at the cursed dungeon.", 100, 200,
            new Position(1909, 5232, 1)),
    RUNITE_TURTLE(TaskType.MEDIUM, 1745, "Runite turtle can be found at the cursed dungeon.", 100, 200,
            new Position(1874, 5200, 1)),
    BLAST_CLOUD(TaskType.MEDIUM, 1739, "Blast cloud can be found at the cursed dungeon.", 100, 200,
            new Position(1903, 5197, 1)),
    SABERTOOTH(TaskType.MEDIUM, 1731, "Sabertooth can be found at the cursed dungeon.", 100, 200,
            new Position(1886, 5222, 1)),

    TROLL_KING(TaskType.MEDIUM, 13635, "Troll King can be found by using the Zones interface", 200, 300,
            GameSettings.KING),
    AVATAR_TITAN(TaskType.MEDIUM, 8008, "Avatar Titan can be found by using the Zones interface", 200, 300,
            new Position(3301, 3289, 0)),
    ANGEL_LUGIA(TaskType.MEDIUM, 3308, "Angel Lugia can be found by using the Zones interface", 200, 300,
            new Position(3322, 3309, 0)),
    LUCIEN(TaskType.MEDIUM, 3117, "Lucien can be found by using the Zones interface", 200, 300,
            new Position(2907, 5455, 0)),

    //HARD

    ICE_DEMON(TaskType.HARD, 13747, "Ice demon can be found by using the Teleport interface", 200, 300,
            new Position(2764, 9191, 0)),
    PREDATOR(TaskType.HARD, 12343, "Predator can be found by using the Teleport interface", 200, 300,
            new Position(2911, 3613, 0)),
    CYANTRIX(TaskType.HARD, 12886, "Cyantrix can be found by using the Teleport interface", 200, 300,
            new Position(2397, 3487, 0)),
    BULWARK(TaskType.HARD, 10103, "Bulwark can be found by using the Teleport interface", 200, 300,
            new Position(2422, 3523, 0)),


    DRAGONSTONE_DRAGON(TaskType.HARD, 9892, "Dragonstone Dragons can be found in the Gemstone dungeon", 100, 250,
            new Position(2273, 5019, 0)),
    ONYX_DRAGON(TaskType.HARD, 9893, "Onyx Dragons can be found in the Gemstone dungeon", 100, 250,
            new Position(2273, 5019, 0)),
    HYDRIX_DRAGON(TaskType.HARD, 9894, "Hydrix Dragons can be found in the Gemstone dungeon", 100, 250,
            new Position(2273, 5019, 0)),

    HERCULES(TaskType.HARD, 201, "Hercules can be found by using the Zones interface", 200, 300,
            new Position(2931, 5469, 0)),
    SATAN(TaskType.HARD, 202, "Satan can be found by using the Zones interface", 200, 300,
            new Position(2910, 5491, 0)),
    ZEUS(TaskType.HARD, 203, "Zeus can be found by using the Zones interface", 200, 300,
            new Position(2893, 5469, 0)),
    GROUDON(TaskType.HARD, 8010, "Groudon can be found by using the Zones interface", 200, 300,
            new Position(2996, 3116, 0)),


    //BOSS
    FRIEZA(TaskType.BOSS_SLAYER, 252, "Frieza can be found by using the Teleport interface", 200, 400,
            new Position(2516, 3042, 0)),
    PERFECT_CELL(TaskType.BOSS_SLAYER, 449, "Perfect cell can be found by using the Teleport interface.", 200, 400,
            new Position(3000, 2511, 0)),
    SUPER_BUU(TaskType.BOSS_SLAYER, 452, "Super Buu can be found by using the Teleport interface.", 200, 400,
            new Position(3342, 3022, 0)),
    SCARLET_FALCON(TaskType.BOSS_SLAYER, 2949, "Scarlet Falcon can be found by using the Teleport interface.", 200, 400,
            new Position(3869, 2776, 0)),
    HERBAL_ROGUE(TaskType.BOSS_SLAYER, 2342, "Herbal Rogue can be found by using the Teleport interface.", 200, 400,
            new Position(3044, 2969, 0)),
    AZURE_BEAST(TaskType.BOSS_SLAYER, 3831, "Azure Beast can be found by using the Teleport interface.", 200, 400,
            new Position(2924, 2842, 0)),
    JOKER(TaskType.BOSS_SLAYER, 185, "Joker can be found by using the Teleport interface.", 200, 400,
            new Position(1807, 3211, 0)),
    CRYSTAL_QUEEN(TaskType.BOSS_SLAYER, 6430, "Crystal Queen can be found by using the Teleport interface.", 200, 400,
            new Position(2867, 9950, 0)),

    SUPREME_BOSS(TaskType.BOSS_SLAYER, 440, "Supreme Boss can be found by using the Teleport interface.", 75, 150,
            new Position(2403, 5082, 1000)),
    VASA_NISTIRIO(TaskType.BOSS_SLAYER, 1120, "Vasa Nistirio can be found by using the Teleport interface.", 75, 150,
            new Position(2910, 2593, 0)),
    ELITE_DRAGON(TaskType.BOSS_SLAYER, 8015, "Elite Dragon can be found by using the Teleport interface.", 75, 150,
            new Position(2719, 9442, 0)),
    MEGA_AVATAR(TaskType.BOSS_SLAYER, 4540, "Mega Avatar can be found by using the Teleport interface.", 75, 150,
            new Position(2360, 4952, 0)),
    INFERNAL_DEMON(TaskType.BOSS_SLAYER, 12810, "Infernal Demons can be found by using the Teleport interface.", 75, 150,
            new Position(2357, 9904, 0)),
    RAMMERNAUT(TaskType.BOSS_SLAYER, 9767, "Rammernaut can be found by using the Teleport interface.", 75, 150,
            new Position(2326, 9831, 4)),
    LUCIFER(TaskType.BOSS_SLAYER, 9012, "Lucifer can be found by using the Teleport interface.", 75, 150,
            new Position(1950, 5155, 1)),
    DARK_SUPREME(TaskType.BOSS_SLAYER, 438, "Dark Supreme can be found by using the Teleport interface.", 75, 150,
            new Position(3038, 4505, 0)),
    BLOOD_ODIN(TaskType.BOSS_SLAYER, 9813, "Blood Odin can be found by using the Teleport interface.", 75, 150,
            new Position(3023, 5243, 0)),
    ;

    private TaskType taskType;
    private int npcId;
    private String npcLocation;
    private int min;
    private int max;
    private Position taskPosition;

    public static SlayerTasks forId(int id) {
        for (SlayerTasks tasks : SlayerTasks.values()) {
            if (tasks.ordinal() == id) {
                return tasks;
            }
        }
        return null;
    }

    public static ArrayList<SlayerTasks> forId(Player player, TaskType taskType) {
        ArrayList<SlayerTasks> list = new ArrayList<>();
        for (SlayerTasks tasks : SlayerTasks.values()) {
            if (tasks.getTaskType() == taskType) {
                list.add(tasks);

                if (tasks == LUCIFER) {
                    if (!player.isUnlockedLucifers() ||
                            player.getPointsHandler().getMiniLuciferkillcount() < 5_000) {
                        if (list.contains(tasks))
                            list.remove(tasks);
                    }
                } else if (tasks == DARK_SUPREME) {
                    if (!player.isUnlockedDarkSupreme() && !player.getRights().isDeveloperOnly()) {
                        if (list.contains(tasks))
                            list.remove(tasks);
                    }
                } else if (tasks == BLOOD_ODIN) {
                    if (KillsTracker.getTotalKillsForNpc(438, player) < 10000 && !player.getRights().isDeveloperOnly()) {
                        if (list.contains(tasks))
                            list.remove(tasks);
                    }
                }

                for (NpcRequirements req : NpcRequirements.values()) {
                    if (tasks.getNpcId() == req.getNpcId()) {
                        if (req.getKillCount() > 0) {
                            if (KillsTracker.getTotalKills(player) < req.getKillCount()) {
                                if (list.contains(tasks))
                                    list.remove(tasks);
                            }
                        } else {
                            int npc = req.getRequireNpcId();
                            if (req.getNpcId() == 3308)
                                npc = 8008;
                            else if (req.getNpcId() == 3117)
                                npc = 3308;
                            else if (req.getNpcId() == 201)
                                npc = 3117;
                            else if (req.getNpcId() == 202)
                                npc = 201;
                            else if (req.getNpcId() == 203)
                                npc = 202;
                            int total = KillsTracker.getTotalKillsForNpc(npc, player);

                            if (npc == 603)
                                total = player.getPointsHandler().getLORDKILLCount();
                            if (npc == 12843)
                                total = player.getPointsHandler().getDEMONKILLCount();
                            if (npc == 53)
                                total = player.getPointsHandler().getDRAGONKILLCount();
                            if (npc == 8018)
                                total = player.getPointsHandler().getBEASTKILLCount();
                            if (npc == 13635)
                                total = player.getPointsHandler().getKINGKILLCount();
                            if (npc == 8008)
                                total = player.getPointsHandler().getAVATARKILLCount();
                            if (npc == 3308)
                                total = player.getPointsHandler().getANGELKILLCount();
                            if (npc == 3117)
                                total = player.getPointsHandler().getLUCIENKILLCount();
                            if (npc == 201)
                                total = player.getPointsHandler().getHERCULESKILLCount();
                            if (npc == 202)
                                total = player.getPointsHandler().getSATANKILLCount();
                            if (npc == 203)
                                total = player.getPointsHandler().getZEUSKILLCount();

                            if (total < req.getAmountRequired()) {
                                if (list.contains(tasks))
                                    list.remove(tasks);
                            }
                        }
                    }
                }


                if (player.getSlayerFavourites().isBlocked(tasks.getNpcId())) {
                    if (list.contains(tasks))
                        list.remove(tasks);
                }
                if (player.getSlayerFavourites().isFavourite(tasks.getNpcId())) {
                    if (list.contains(tasks))
                        list.add(tasks);
                }

            }
        }

        return list;
    }
    public static List<SlayerTasks> tasksForType(TaskType type) {
        List<SlayerTasks> toReturn = new ArrayList<>();
        for (SlayerTasks task : values())
            if (task.taskType == type)
                toReturn.add(task);
        return toReturn;
    }

    public static int[] getNewTaskData(TaskType master) {
        int slayerTaskId = 1, slayerTaskAmount = 20;
        int easyTasks = 0, mediumTasks = 0, hardTasks = 0, eliteTasks = 0, customTasks = 0, bossTasks = 0;

        /*	*//*
         * Calculating amount of tasks
         *//*
		for (SlayerTasks task : SlayerTasks.values()) {
			if (task.getTaskMaster() == TaskType.VANNAKA)
				easyTasks++;
			else if (task.getTaskMaster() == TaskType.DURADEL)
				mediumTasks++;
			else if (task.getTaskMaster() == TaskType.KURADEL)
				hardTasks++;
			else if (task.getTaskMaster() == TaskType.SUMONA)
				eliteTasks++;
			else if (task.getTaskMaster() == TaskType.EASY)
				customTasks++;
			else if (task.getTaskMaster() == TaskType.BOSS_SLAYER)
				bossTasks++;

			// // System.out.println("TAsk master: " + task.getTaskMaster().toString());
			// back, did u figure it out or na.no, k ill rewrite it then.
		}

		*//*
         * Getting a task
         *//*
		if (master == TaskType.VANNAKA) {
			slayerTaskId = 1 + Misc.getRandom(easyTasks);
			if (slayerTaskId > easyTasks)
				slayerTaskId = easyTasks;
			slayerTaskAmount = 15 + Misc.getRandom(15);
		} else if (master == TaskType.DURADEL) {
			slayerTaskId = easyTasks - 1 + Misc.getRandom(mediumTasks);
			slayerTaskAmount = 12 + Misc.getRandom(13);
		} else if (master == TaskType.KURADEL) {
			slayerTaskId = 1 + easyTasks + mediumTasks + Misc.getRandom(hardTasks - 1);
			slayerTaskAmount = 10 + Misc.getRandom(15);
		} else if (master == TaskType.SUMONA) {
			slayerTaskId = 1 + easyTasks + mediumTasks + hardTasks + Misc.getRandom(eliteTasks - 1);
			slayerTaskAmount = 2 + Misc.getRandom(7);
		} else if (master == TaskType.EASY) {
			slayerTaskId = 1 + easyTasks + mediumTasks + hardTasks + eliteTasks + Misc.getRandom(customTasks - 1);
			slayerTaskAmount = 50 + Misc.getRandom(30);
		} else if (master == TaskType.BOSS_SLAYER) {
			slayerTaskId = 1 + easyTasks + mediumTasks + hardTasks + eliteTasks + customTasks + Misc.getRandom(bossTasks - 1);
			slayerTaskAmount = 40 + Misc.getRandom(40);
		}*/
        return new int[]{slayerTaskId, slayerTaskAmount};
    }
}
