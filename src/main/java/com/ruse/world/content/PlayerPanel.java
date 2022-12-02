package com.ruse.world.content;

import com.ruse.GameSettings;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.motivote3.doMotivote;
import com.ruse.util.Misc;
import com.ruse.util.StringUtils;
import com.ruse.world.World;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skeletalhorror.Prime;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.*;
import com.world.content.globalBoss.exoden.GoldenOozau;
import com.world.content.globalBoss.hulk.Zamasu;

public class PlayerPanel {

    public static void refreshCurrentTab(Player player) {
        refreshPanel(player);
    }

    public static void refreshPanel(Player player) {
        if (player == null)
            return;
        int interfaceID = 111201;
        int players = (int) World.getPlayers().size() + GameSettings.players;
        String[] Messages = new String[]{

                "Main",
                "Players Online: @whi@" + ((players)),
                "Server Time: @whi@" + Misc.getCurrentServerTime(),

                "Events",
                "Server Perk: @whi@" + (ServerPerks.getInstance().getActivePerk() == null ? "N/A" : ServerPerks.getInstance().getActivePerk().getName()),
                "Bonus Skill: @whi@" + StringUtils.capitalizeFirst(DoubleXPSkillEvent.currentSkill.toString()),
                "Next Bonus Skill: @whi@" + DoubleXPSkillEvent.getTimeLeft(),
                (WellOfGoodwill.isActive() ? "Well of Goodwill: @whi@On" : "Well of Goodwill: @whi@Off"),

                "Globals",

                "EarthQuake: @whi@" + AfkStallSystem.getLeft() + " steals left.",
                (VoteBoss.getVoteCount() <= 59
                        ? "Vote Boss: @whi@" + VoteBoss.getVoteCount() + "/60 please vote!"
                        : "Vote Boss: @whi@Alive ::vboss"),

                "Fused Zamasu: @whi@" + (!Zamasu.alive ? Zamasu.getTimeLeft() : "Alive ::zamasu"),
                "Golden Oozau: @whi@" + (!GoldenOozau.alive ? GoldenOozau.getTimeLeft() : "Alive ::oozau"),
                "Optimus Prime: @whi@" + (!Prime.alive ? Prime.getTimeLeft() : "Alive ::prime"),
                "Goku Boss: @whi@" + (!GokuSystem.alive ? GokuSystem.getKillsLeft() + " kills left" : "Alive ::goku"),

                "Lunite Guard: @whi@" + LuniteGuardian.getRemaining(),
                "Hanto: @whi@" + HantoWarrior.getRemaining(),
             //   "Uncle Sam: @whi@" + (!UncleSamSpawn.bossAlive ? UncleSamSpawn.getTimeLeft() : "Alive ::sam"),
        };

        for (int i = 0; i < Messages.length; i++) {
            player.getPacketSender().sendString(interfaceID++, Messages[i]);
        }


        interfaceID = 111401;

        Messages = new String[]{"Main",
                "Time Played: @whi@"
                        + Misc.getHoursPlayed((player.getTotalPlayTime() + player.getRecordedLogin().elapsed())),
                "Username: @whi@" + player.getUsername(),
                "Claimed: @whi@$" + player.getAmountDonated(),
                "Mode: @whi@"
                        + Misc.capitalizeString(player.getGameMode().toString().toLowerCase().replace("_", " ")),
                "Rank: @whi@" + Misc.formatText(player.getRights().toString().toLowerCase()),

                "Droprate bonus: @whi@" + NPCDrops.dropRateBoost(player) + "%",
                "X2 Drop Chance: @whi@" + CustomDropUtils.getDoubleDropChance(player) + "/1000",
                //"Triple drop bonus: @whi@" + CustomDropUtils.getTripleDropChance(player) + "%",
                "Exp Lock: " + (player.experienceLocked() ? "Locked" : "@gre@Unlocked") + "",
                "Bonus Xp: @whi@" + Misc.format(player.getMinutesBonusExp()) + " minutes left",
                "Voting +100% DR Boost: @whi@" + (player.getMinutesVotingDR() <= 0 ? "Inactive" : player.getMinutesVotingDR() + "min"),
                "Voting +100% DMG Boost: @whi@" + (player.getMinutesVotingDMG() <= 0 ? "Inactive" : player.getMinutesVotingDMG() + "min"),


                "Slayer Information",
                "Slayer Points: @whi@" + player.getPointsHandler().getSlayerPoints(),

                "Task Type: @whi@" + (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK ? "N/A" : Misc
                        .formatText(player.getSlayer().getTaskType().toString().toLowerCase().replaceAll("_", " ")))
                ,
                (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK
                        ? "Task: @whi@" + Misc.formatText(
                        player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                        : "Task: @whi@" + Misc.formatText(
                        player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                        + "s"),

                "Task Amount: @whi@" + player.getSlayer().getAmountToSlay(),
                "Task Streak: @whi@" + player.getSlayer().getTaskStreak(),


                (player.getSlayer().getDuoPartner() != null
                        ? "Duo Partner: @whi@" + player.getSlayer().getDuoPartner()
                        : "Duo Partner: @whi@N/A"),
                "Slayer Spree: @whi@" + player.getPointsHandler().getSlayerSpree(),
                "Slayer Multiplier: @whi@" + player.getPointsHandler().getSlayerRate() + "%",

                //
                "Points & Statistics",
                "NPC kill Count: @whi@ " + KillsTracker.getTotalKills(player),
                "Boss Points: @whi@ " + player.getPointsHandler().getBossPoints(),
                "Member Points: @whi@" + player.getPointsHandler().getMemberPoints(),
                "Voting Points: @whi@ " + player.getPointsHandler().getVotingPoints(),
                "Slayer Points: @whi@" + player.getPointsHandler().getSlayerPoints(),
                "Loyalty Points: @whi@" +  player.getPointsHandler().getLoyaltyPoints(),
                "Prestige Points: @whi@" + player.getPointsHandler().getPrestigePoints(),
                "Total Prestige: @whi@" + player.getPointsHandler().getTotalPrestiges(),
                "Pest Control Points: @whi@ " + player.getPointsHandler().getCommendations(),
                "Discord Points: @whi@ " + player.getDiscordPoints(),


                "Quests",
        };

        for (int i = 0; i < Messages.length; i++) {
            player.getPacketSender().sendString(interfaceID++, Messages[i]);
        }

        player.getPacketSender().sendString(111434,
                ( player.getCurseOfArrav().getQuestProgress() == 0 ? "@red@":
                        player.getCurseOfArrav().getQuestProgress() == 4 ? "@gre@"                : "@yel@") +
                        "Curse of Arrav"        );


        interfaceID = 111601;
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Commands");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Achievements");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Drop Table");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Collection Log");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Kill Tracker");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Possible Loot");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Item Stats");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Drop Rate Items");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Season Pass");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Starter Tasks");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Voting Streak");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Youtube Interface");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Discord Integration");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Weekly Raffles");
        player.getPacketSender().sendString(interfaceID++, "@whi@View @yel@Titles Manager");


    }

}