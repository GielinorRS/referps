package com.ruse.world.content.leaderboards;

import lombok.Getter;

import java.util.ArrayList;

import static com.ruse.world.content.leaderboards.LeaderboardData.LeaderboardType.MAIN;
import static com.ruse.world.content.leaderboards.LeaderboardData.LeaderboardType.MONSTER;

@Getter
public enum LeaderboardData {

    NPC_KILLCOUNT(MAIN,  "NPC Killcount"),
    TIME_PLAYED(MAIN, "Time Played"),
    DONATED(MAIN, "Donator Rank"),
    COLLECTION_LOGS_COMPLETED(MAIN, "Collection Logs Completed"),
    DAILY_TASKS_COMPLETED(MAIN, "Daily Tasks Completed"),
    TITLES_UNLOCKED(MAIN, "Titles Unlocked"),
    EASY_SLAYER_TASKS(MAIN, "Easy Slayer Tasks"),
    MED_SLAYER_TASKS(MAIN, "Medium Slayer Tasks"),
    HARD_SLAYER_TASKS(MAIN, "Hard Slayer Tasks"),
    BOSS_SLAYER_TASKS(MAIN, "Boss Slayer Tasks"),
    SANCTUM_OF_DEATH(MAIN, "Sanctum of Death Runs"),
    TREASURE_HUNTER(MAIN, "Treasure Hunter Runs"),


    DRAGONSTONE_DRAGON(MONSTER, 9892, "Dragonstone dragon"),
    ONYX_DRAGON(MONSTER, 9893, "Onyx Dragon"),
    HYDRIX_DRAGON(MONSTER, 9894, "Hydrix Dragon"),
    FRIEZA(MONSTER, 252, "Frieza"),
    PERFECT_CELL(MONSTER, 449, "Perfect Cell"),
    SUPER_BUU(MONSTER, 452, "Super Buu"),
    ETERNAL(MONSTER, 4972, "Eternal Dragon"),
    SCARLET(MONSTER, 2949, "Scarlet Falcon"),
    HERBAL(MONSTER, 2342, "Herbal Rogue"),
    AZURE(MONSTER, 3831, "Azure Beast"),
    JOKER(MONSTER, 185, "Joker"),
    CRYSTAL(MONSTER, 6430, "Crystal Queen"),
    SUPREME(MONSTER, 440, "Supreme Boss"),
    VASA(MONSTER, 1120, "Vasa Nistirio"),
    ELITE(MONSTER, 8015, "Elite Dragon"),
    MEGA(MONSTER, 4540, "Mega Avatar"),
    INERNAL(MONSTER, 12810, "Infernal Demon"),
    RAMMERNAUT(MONSTER, 9767, "Rammernaut"),
    LUCIFER(MONSTER, 9012, "Lucifer"),
    DARK_SUPREME(MONSTER, 438, "Dark Supreme"),
    BLOOD_ODIN(MONSTER, 9813, "Blood Odin"),
    GODS(MONSTER, 10000, "Isle of the Gods"),

    ;

    private LeaderboardType type;
    private String name;
    private int npcId;

    LeaderboardData(LeaderboardType type, String name) {
        this.type = type;
        this.name = name;
        this.npcId = -1;
    }

    LeaderboardData(LeaderboardType type, int npcId, String name) {
        this.type = type;
        this.name = name;
        this.npcId = npcId;
    }



    public static ArrayList<LeaderboardData> getItems(LeaderboardType type) {
        ArrayList<LeaderboardData> items = new ArrayList<>();
        for (LeaderboardData s : LeaderboardData.values()) {
            if (s.type == type) {
                items.add(s);
            }
        }
        return items;
    }
    public static LeaderboardData forNpcId(int npcId) {
        for (LeaderboardData s : LeaderboardData.values()) {
            if (s.npcId == npcId) {
               return s;
            }
        }
        return null;
    }

    public enum LeaderboardType{
        MAIN, MONSTER;
    }



}
