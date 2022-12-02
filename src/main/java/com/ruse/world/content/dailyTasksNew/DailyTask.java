package com.ruse.world.content.dailyTasksNew;

import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public enum DailyTask {

        DEFEAT_1K_ZONE_NPCS(0,"Defeat 1k \\nZone Mobs", new Item[] {new Item(19114, 3)}, 1000, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        CLAIM_4_VOTE_SCROLLS(1,"Claim 4 \\nVote Scrolls", new Item[] {new Item(20488, 3)}, 4, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        COMPLETE_5_MEDIUM_SLAYER_TASKS(2,"Complete 5 \\nMedium Slayer Tasks", new Item[] {new Item(23479, 10)}, 5, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY, new Position(2664, 4016)),
        DEFEAT_100_SUPREME_BOSS(3,"Defeat 100 \\nSupreme Bosses", new Item[] {new Item(15288, 1)}, 100, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        DISSOLVE_100_ITEMS(4,"Dissolve \\n100 Items", new Item[] {new Item(5022, 15000)}, 100, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        OPEN_10_BOXES_OR_CASKETS(5,"Open Any 10 \\nBoxes Or Caskets", new Item[] {new Item(19114, 5)}, 10, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        DEAL_100K_DAMAGE(6,"Deal 100K Damage \\non any monster", new Item[] {new Item(20488, 1)}, 100000, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        PURCHASE_ITEM_FROM_TRAVELING_MERCHANT(7,"Purchase an Item from \\nTravelling Merchant", new Item[] {new Item(19114, 5)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY, new Position(2652, 4017)),
        CLAIM_A_PET_RETURN(8,"Claim a Pet Return \\nfrom Zookeeper", new Item[] {new Item(12855, 10000)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        SUMMON_A_PET(9,"Summon \\nAny Pet", new Item[] {new Item(12855, 10000)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        OPEN_A_CLUE_CASKET(10,"Open a \\nClue Casket", new Item[] {new Item(19114, 3)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        COMPLETE_THE_VOTING_MINIGAME(11,"Complete the \\nVoting Minigame", new Item[] {new Item(20488, 5)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY, new Position(2550, 9953)),
        ENTER_AN_INSTANCE(12,"Enter an Instance \\nusing an instance token", new Item[] {new Item(19115, 3)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),
        EQUIP_COLLECTOR_NECKLACE_AND_CREEPER_CAPE(13,"Equip Collector Necklace \\n& Creeper Cape at Once", new Item[] {new Item(5022, 25000)}, 1, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty.EASY),


        COMPLETE_5_ISLAND_RAIDS(14,"Complete 5 \\nIsland Raids", new Item[] {new Item(18404, 5)}, 5, DailyTaskDifficulty.MEDIUM, new Position(2553, 3717)),
        REACH_WAVE_10_GOLD_GRINDER(15,"Reach Wave 10 in \\nGold Grinder", new Item[] {new Item(12855, 500000), new Item(23835, 2)}, 10, DailyTaskDifficulty.MEDIUM, new Position(2420, 4681)),
        DEFEAT_2500_ZONE_NPCS(0,"Defeat 2500 \\nZone Mobs", new Item[] {new Item(19114, 5)}, 2500, DailyTaskDifficulty.MEDIUM),
        PARTICIPATE_IN_15_GLOBAL_BOSSES(16,"Participate in \\n15 Global Bosses", new Item[] {new Item(23215, 50)}, 15, DailyTaskDifficulty.MEDIUM),
        DEFEAT_100_ELITE_DRAGONS(17,"Defeat 100 \\nElite Dragons", new Item[] {new Item(5022, 100000)}, 100, DailyTaskDifficulty.MEDIUM),
        COMPLETE_5_BOSS_SLAYER_TASKS(18,"Complete 5 \\nBoss Slayer Tasks", new Item[] {new Item(621, 25)}, 5, DailyTaskDifficulty.MEDIUM, new Position(2664, 4013)),
        DEFEAT_250_DBZ_BOSSES(19,"Defeat 250 \\nDBZ Bosses", new Item[] {new Item(19114, 10)}, 250, DailyTaskDifficulty.MEDIUM),
        SIP_FROM_AN_INFINITE_OVERLOAD_POT_OR_STRONGER(20,"Sip from an Infinite \\nOverload Pot or Stronger", new Item[] {new Item(5022, 10000)}, 1, DailyTaskDifficulty.MEDIUM),
        SUMMON_A_VOTE_GOKU_OR_RAICHU_PET(21,"Summon a Vote, Goku \\nor Raichu Pet", new Item[] {new Item(19114, 5)}, 1, DailyTaskDifficulty.MEDIUM),
        COMPLETE_5_HALLS_OF_AMMO_RUNS(22,"Complete 5 \\nHalls of Ammo Runs", new Item[] {new Item(19114, 10)}, 5, DailyTaskDifficulty.MEDIUM,  new Position(2195, 5037)),
        WIN_A_GAME_OF_PEST_CONTROL(23,"Win a Game of \\nPest Control", new Item[] {new Item(10946, 1)}, 1, DailyTaskDifficulty.MEDIUM, new Position(2657, 2639)),


        COMPLETE_5_HARD_SOD_RUNS(24,"Complete 5 Hard \\nSanctum of Death Runs", new Item[] {new Item(23464, 5)}, 5, DailyTaskDifficulty.HARD, new Position(1740, 5236)),
        DONATE_5M_TO_SERVER_PERKS(25,"Donate 5M to \\nServer Perks", new Item[] {new Item(6769, 1)}, 5_000_000, DailyTaskDifficulty.HARD),
        DEFEAT_750_LUCIFER_MOBS(26,"Defeat 750 \\nLucifer Mobs", new Item[] {new Item(22106, 1)}, 750, DailyTaskDifficulty.HARD),
        CLAIM_10_IN_BONDS(27,"Claim $10 \\nin Bonds", new Item[] {new Item(5022, 500000)}, 10, DailyTaskDifficulty.HARD),
        OPEN_ANY_CASKET(28,"Open \\nAny Casket", new Item[] {new Item(10946, 2)}, 1, DailyTaskDifficulty.HARD),
        DEAL_10M_DAMAGE(6,"Deal 10M Damage \\nto any monster", new Item[] {new Item(10946, 4)}, 10_000_000, DailyTaskDifficulty.HARD),
        DEFEAT_500_MEGA_AVATAR_MOBS(29,"Defeat 500 \\nMega Avatar Mobs", new Item[] {new Item(5022, 500_000)}, 500, DailyTaskDifficulty.HARD),
        RECEIVE_A_UNIQUE_DROP_FROM_VASA_NISTIRIO(30,"Receive a Unique Drop \\nfrom Vasa Nistirio", new Item[] {new Item(23224, 1)}, 1, DailyTaskDifficulty.HARD),
        EQUIP_A_COSTUME_WITH_AT_LEAST_10_DAMAGE_BONUS(31,"Equip a Costume With at \\nLeast 10% Damage Bonus", new Item[] {new Item(19114, 10)}, 1, DailyTaskDifficulty.HARD),
        CREATE_ANY_120_INVENTION_ITEM(32,"Create any \\n120 Invention Item", new Item[] {new Item(15288, 5)}, 1, DailyTaskDifficulty.HARD),
        EQUIP_A_WEAPON_WITH_AT_LEAST_40_DR_BONUS(33,"Equip a Weapon With at \\nLeast 40% DR Bonus", new Item[] {new Item(20488, 7)}, 1, DailyTaskDifficulty.HARD),
        PARTICIPATE_IN_AN_ONYX_PANTHER_KILL(34,"Participate in an \\nOnyx Panther Kill", new Item[] {new Item(19114, 50)}, 1, DailyTaskDifficulty.HARD, new Position(2470, 5431)),


        DONATE_10M_TO_SERVER_PERKS(25,"Donate 10M to \\nServer Perks", new Item[] {new Item(10934, 1)}, 10_000_000, DailyTaskDifficulty.ELITE),
        COMPLETE_5_HARD_ISLES_OF_THE_GODS_RUNS(35,"Complete 5 Hard Isles \\nof the Gods Runs", new Item[] {new Item(6769, 1), new Item(23752, 3)}, 5, DailyTaskDifficulty.ELITE, new Position(2655, 4507)),
        RECEIVE_A_UNIQUE_BLOOD_ODIN_DROP(36,"Receive a Unique \\nBlood Odin Drop", new Item[] {new Item(23518, 1)}, 1, DailyTaskDifficulty.ELITE),
        DEFEAT_500_RAMMERNAUT_MOBS(37,"Defeat 500 \\nRammernaut Mobs", new Item[] {new Item(23759, 25), new Item(5022, 25000)}, 500, DailyTaskDifficulty.ELITE),
        DEFEAT_100_SEASON_PASS_MOBS(38,"Defeat 100 \\nSeason Pass Mobs", new Item[] {new Item(10946, 1)}, 100, DailyTaskDifficulty.ELITE),
        OPEN_A_MONEY_ANGELIC_DIVINE_CASE(39,"Open a Money, Angelic \\nor Divine Case", new Item[] {new Item(5022, 100000)}, 1, DailyTaskDifficulty.ELITE),
        RECEIVE_AN_ANNOUNCED_DROP(40,"Receive an \\nAnnounced Drop", new Item[] {new Item(10946, 2)}, 1, DailyTaskDifficulty.ELITE),
        PARTICIPATE_IN_A_ZENYTE_OR_TANZANITE_KILL(41,"Participate in a Zenyte \\nor Tanzanite Kill", new Item[] {new Item(20489, 5)}, 1, DailyTaskDifficulty.ELITE),
        DEAL_250K_DAMAGE(42,"Deal over 250k Damage \\nin One Hit", new Item[] {new Item(10946, 3)}, 1, DailyTaskDifficulty.ELITE),
        OPEN_A_SEASON_BOX(43,"Open a \\nSeason Box", new Item[] {new Item(19114, 50)}, 1, DailyTaskDifficulty.ELITE),
        //CHECK_YOUR_ASSASSIN_RANK(44,"Check Your Assassin Rank with Assassin Master", new Item[] {new Item(23569, 5)}, 1, DailyTaskDifficulty.ELITE),

        // COMPLETE_5_RAIDS_3_RUNS(45,"Complete 5 Raids 3 Runs", new Item[] {new Item(995, 1000)}, 5, DailyTaskDifficulty.MASTER),
        COMPLETE_25_HARD_SOD_RUNS(24,"Complete 25 Hard \\nSanctum of Death Runs", new Item[] {new Item(23464, 25)}, 25, DailyTaskDifficulty.MASTER, new Position(1740, 5236)),
        COMPLETE_25_HARD_ISLES_OF_THE_GODS(35,"Complete 25 Hard Isle \\nof the Gods Runs", new Item[] {new Item(5022, 5000000)}, 25, DailyTaskDifficulty.MASTER, new Position(2655, 4507)),
        COMPLETE_A_SANCTUM_OF_DEATH_RUN_IN_UNDER_3_MINS(46,"Complete Sanctum of Death \\nin Under 3 Minutes", new Item[] {new Item(23753, 5)}, 1, DailyTaskDifficulty.MASTER, new Position(1740, 5236)),
        // EQUIP_3_CELESTIAL_ITEMS_AT_ONCE(47,"Equip 3 Celestial Items \\nat Once", new Item[] {new Item(6769, 1, new Item(23736, 500)}, 1, DailyTaskDifficulty.MASTER),
        RECEIVE_1_PIECE_OF_SACRIFICIAL_ARMOUR_AS_A_DROP(48,"Receive a Sacrificial \\nArmour Piece Drop", new Item[] {new Item(23736, 1000)}, 1, DailyTaskDifficulty.MASTER),
        WIN_10_GAMES_OF_PEST_CONTROL(23,"Win 10 Games of \\nPest Control", new Item[] {new Item(10946, 5)}, 10, DailyTaskDifficulty.MASTER, new Position(2657, 2639)),
            // ACHIEVE_A_DR_BONUS_OF_1K_WITHOUT_SCROLLS_BOOSTERS_PERKS(49,"Achieve a DR bonus of 1k% without scrolls, boosters or perks", new Item[] {new Item(6769, 1, new Item(23569, 15)}, 1, DailyTaskDifficulty.MASTER),

    ;


    DailyTask(int taskIdentifier, String name, Item[] rewards, int amountNeeded, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty difficulty) {
        this.taskIdentifier = taskIdentifier;
        this.rewards = rewards;
        this.name = name;
        this.amountNeeded = amountNeeded;
        this.difficulty = difficulty;
        this.position = null;
    }

    DailyTask(int taskIdentifier, String name, Item[] rewards, int amountNeeded, com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty difficulty, Position position) {
        this.taskIdentifier = taskIdentifier;
        this.rewards = rewards;
        this.name = name;
        this.amountNeeded = amountNeeded;
        this.difficulty = difficulty;
        this.position = position;
    }

    public static DailyTask getRandomTaskByDifficulty(DailyTaskDifficulty difficulty) {
        List<DailyTask> taskList = new ArrayList<>();
        for (DailyTask task : values()) {
            if (task.difficulty == difficulty)
                taskList.add(task);
        }
        return taskList.size() > 0 ? Misc.randomElement(taskList) : null;
    }

    private final int taskIdentifier;
    private final Item[] rewards;
    private final String name;
    private final int amountNeeded;
    private final com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty difficulty;
    private final Position position;

    public Item[] getRewards() {
        return rewards;
    }

    public String getName() {
        return name;
    }

    public Position getPosition() {
        if (this == PARTICIPATE_IN_AN_ONYX_PANTHER_KILL)
            return null;
        return position;
    }
}
