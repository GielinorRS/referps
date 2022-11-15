package com.ruse.net.packet.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ruse.GameLoader;
import com.ruse.GameServer;
import com.ruse.GameSettings;
import com.ruse.engine.GameEngine;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.BonusExperienceTask;
import com.ruse.engine.task.impl.VotingDMGBoostTask;
import com.ruse.engine.task.impl.VotingDRBoostTask;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.*;
import com.ruse.model.input.impl.ChangePassword;
import com.ruse.model.input.impl.EnterReferral;
import com.ruse.model.input.impl.SetPinPacketListener;
import com.ruse.mysql.Voting;
import com.ruse.mysql.VotingTopList;
import com.ruse.net.PlayerSession;
import com.ruse.net.SessionState;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.net.security.ConnectionHandler;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.clip.region.RegionClipping;
import com.ruse.world.content.*;
import com.ruse.world.content.PlayerPunishment.Jail;
import com.ruse.world.content.bossEvents.BossEventHandler;
import com.ruse.world.content.bossfights.impl.Leviathon;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.CasketOpening;
import com.ruse.world.content.casketopening.impl.OnyxCasket;
import com.ruse.world.content.casketopening.impl.Raids2Box;
import com.ruse.world.content.clan.ClanChat;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.cluescrolls.CLUESCROLL;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.DesolaceFormulas;
import com.ruse.world.content.combat.Maxhits;
import com.ruse.world.content.combat.effect.EquipmentBonus;
import com.ruse.world.content.combat.magic.Autocasting;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.dailyTask.DailyTaskHandler;
import com.ruse.world.content.dailyTasksNew.DailyTaskDifficulty;
import com.ruse.world.content.deathsanctum.DeathSanctumRewards;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.discordbot.DiscordIntegration;
import com.ruse.world.content.events.PartyChest;
import com.ruse.world.content.grandLottery.GrandLottery;
import com.ruse.world.content.grandexchange.GrandExchangeOffers;
import com.ruse.world.content.groupironman.GroupManager;
import com.ruse.world.content.groupironman.IronmanGroup;
import com.ruse.world.content.holidayevents.easter2017;
import com.ruse.world.content.leaderboards.Leaderboard;
import com.ruse.world.content.loyalty_streak.LoyaltyStreakManager;
import com.ruse.world.content.minigames.impl.PestControl;
import com.ruse.world.content.polling.PollCreation;
import com.ruse.world.content.polling.PollManager;
import com.ruse.world.content.pos.POSItemPrice;
import com.ruse.world.content.pos.PlayerOwnedShopManager;
import com.ruse.world.content.pos.TaxCollection;
import com.ruse.world.content.progressionzone.ProgressionZone;
import com.ruse.world.content.raffle.*;
import com.ruse.world.content.raids.RaidDifficulty;
import com.ruse.world.content.raids.RaidsManager;
import com.ruse.world.content.raids.impl.gods.GodsInterfaces;
import com.ruse.world.content.raids.impl.gods.GodsLoot;
import com.ruse.world.content.randomevents.EvilTree;
import com.ruse.world.content.randomevents.ShootingStar;
import com.ruse.world.content.seasonpass.PassRewards;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skeletalhorror.Prime;
import com.ruse.world.content.skill.SkillManager;
import com.ruse.world.content.skill.impl.construction.Construction;
import com.ruse.world.content.skill.impl.crafting.Jewelry;
import com.ruse.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruse.world.content.skill.impl.dungeoneering.DungeoneeringParty;
import com.ruse.world.content.skill.impl.fletching.BoltData;
import com.ruse.world.content.skill.impl.herblore.Decanting;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.content.skill.impl.slayer.TaskType;
import com.ruse.world.content.skill.impl.summoning.SummoningTab;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.content.youtube.YoutubeMgr;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.PlayerHandler;
import com.ruse.world.entity.impl.player.PlayerSaving;
import com.world.content.globalBoss.*;
import com.world.content.globalBoss.exoden.GoldenOozau;
import com.world.content.globalBoss.hulk.Zamasu;
import com.world.content.globalBoss.iron.Iron;
import com.world.content.globalBoss.merk.MerkSpawn;
import com.world.content.globalBoss.onyx.OnyxSpawn;
import com.world.content.globalBoss.zenyte.ZenyteSpawn;
import mysql.impl.Donation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;
//import com.ruse.world.content.dbz.Frieza;

/**
 * This packet listener manages commands a player uses by using the command
 * console prompted by using the "`" char.
 *
 * @author Gabriel Hannason
 */


public class CommandPacketListener implements PacketListener {

    public static int voteCount = 8;
    static HashMap<String, Integer> dissolvables = new HashMap<>();

    private static void playerCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("curses")) {
            if (RaidsManager.inRaidParty(player)) {
                player.sendMessage("You cannot use this command while doing raids!");
                return;
            }
            player.performAnimation(new Animation(645));
            if (player.getPrayerbook() == Prayerbook.NORMAL) {
                player.getPacketSender().sendMessage("You sense a surge of power flow through your body!");
                player.setPrayerbook(Prayerbook.CURSES);
            } else {
                player.getPacketSender().sendMessage("You sense a surge of purity flow through your body!");
                player.setPrayerbook(Prayerbook.NORMAL);
            }
            player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB, player.getPrayerbook().getInterfaceId());
            PrayerHandler.deactivateAll(player);
            CurseHandler.deactivateAll(player);
        }
        if (command[0].startsWith("raffle")) {
            RaffleInterface.openInterfacePerks(player);
        }


        if (wholeCommand.equalsIgnoreCase("ibank")) {
            if (player.getGameMode() != GameMode.ULTIMATE_IRONMAN) {
                player.sendMessage("You must be a UIM to use this command.");
                return;
            }
            if (player.getInterfaceId() > 0) {
                player.getPacketSender()
                        .sendMessage("Please close the interface you have open before opening another one.");
                return;
            }
            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.JAIL

                    || player.getLocation() == Location.DUEL_ARENA) {

                player.getPacketSender().sendMessage("You cannot open your bank here.");
                return;
            }
            player.getBank(player.getCurrentBankTab()).open(player, false);
        }
        if (command[0].equalsIgnoreCase("yt")) {
            System.out.println(YoutubeMgr.getVideos().size());
            if (YoutubeMgr.getVideos() == null || YoutubeMgr.getVideos().size() <= 1) {
                player.sendMessage("Please wait while the videos load... Try again later.");
            } else {
                player.getPA().sendYoutubeData();
                player.getPA().sendInterface(72100);
            }
        }
        if (wholeCommand.startsWith("dismiss")) {
            SummoningTab.handleDismiss(player, false);
        }

       /* if (wholeCommand.startsWith("stpat")) {
            String after = wholeCommand.substring(5);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 11) {
                    Position position = new Position(3039, 2844, (zone - 1) * 4);
                    TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }*/

        if (wholeCommand.startsWith("vasa")) {
            String after = wholeCommand.substring(4);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 4) {
                    Position position = new Position(2910, 2593, (zone - 1) * 4);

                    if (player.getPointsHandler().getSATANKILLCount() < 25_000) {
                        player.getPacketSender().sendMessage("You need 25,000 Satan kills. You currently have @red@" +
                                player.getPointsHandler().getSATANKILLCount() + "@bla@ kills.");
                        //return;
                    }
                    TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


        if (wholeCommand.startsWith("luci")) {
            String after = wholeCommand.substring(4);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 2) {
                    Position position = new Position(1950, 5155, 1 + ((zone - 1) * 4));
                    if ((player.isUnlockedLucifers() &&
                            player.getPointsHandler().getMiniLuciferkillcount() >= 5_000) || player.getRights() == PlayerRights.DEVELOPER){
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }else{
                        player.sendMessage("You need to have killed 5k Mini Lucifers to go here.");
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


        if (wholeCommand.startsWith("dark")) {
            String after = wholeCommand.substring(4);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 2) {
                    Position position = new Position(3038, 4505, 0 + ((zone - 1) * 4));
                    if (!player.isUnlockedDarkSupreme() && !player.getRights().isDeveloperOnly()) {
                        player.sendMessage("You do not have Dark Supremes unlocked!");
                    }else{
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }

                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }


        if (wholeCommand.startsWith("odin")) {
            String after = wholeCommand.substring(4);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 6) {
                    Position position = new Position(3023, 5243, (zone - 1) * 4);
                    if (player.getRights() == PlayerRights.YOUTUBER) {
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }
                    int total = KillsTracker.getTotalKillsForNpc(438, player);

                    if (total < 10000 && !player.getRights().isDeveloperOnly()) {
                        player.sendMessage("@red@You need 10k Dark Supreme kills to go here!");
                    } else {
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (wholeCommand.startsWith("silverhawk")) {
            String after = wholeCommand.substring(10);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 3) {
                    Position position = new Position(2915, 2663, (zone - 1) * 4);
                    if (player.getRights() == PlayerRights.YOUTUBER) {
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }
                    int total = KillsTracker.getTotalKillsForNpc(10032, player);

                    if (total < 75000 && !player.getRights().isDeveloperOnly()) {
                        player.sendMessage("@red@You need 75k Avianse kills to go here!");
                    } else {
                        TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (wholeCommand.startsWith("mega")) {
            String after = wholeCommand.substring(4);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 4) {
                    Position position = new Position(2360, 4952, (zone - 1) * 4);
                    TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        if (wholeCommand.startsWith("gemstone")) {
            String after = wholeCommand.substring(8);
            try {
                int zone = 1;

                if (!after.equalsIgnoreCase(""))
                    zone = Integer.parseInt(after);

                if (zone >= 1 && zone <= 4) {
                    Position position = new Position(2273, 5019, (zone - 1) * 4);
                    TeleportHandler.teleportPlayer(player, position, player.getSpellbook().getTeleportType());
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (wholeCommand.equalsIgnoreCase("betrayed")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 0), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("cursed")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 1), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("betrayed2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 4), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("cursed2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 5), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("betrayed3")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 8), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("cursed3")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 9), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("betrayed4")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 12), TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("cursed4")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(1886, 5222, 13), TeleportType.NORMAL);
        }

        if (command[0].equalsIgnoreCase("xptoggle")) {
            player.setExperienceLocked(!player.experienceLocked());
            if (player.experienceLocked()) {
                player.getPacketSender().sendMessage("Your EXP is now locked, revert this lock to get EXP again.");
            } else {
                player.getPacketSender().sendMessage("Your EXP is unlocked, and you will gain EXP as normal.");
            }
        }
        if (command[0].equalsIgnoreCase("seasonpass")) {
            player.getSeasonPass().openInterface();
        }

        if (command[0].equalsIgnoreCase("scratchcard")) {
            player.getScratchcard().open();
        }

        if (command[0].equalsIgnoreCase("serverperks")) {
            ServerPerks.getInstance().open(player);
        }

     /*   if (command[0].equalsIgnoreCase("sam") ||
                command[0].equalsIgnoreCase("uncle") || command[0].equalsIgnoreCase("unclesam")) {
            TeleportHandler.teleportPlayer(player, new Position(2910, 4703, 0),
                    player.getSpellbook().getTeleportType());
        }*/

       /* if (command[0].equalsIgnoreCase("snow") ||
                command[0].equalsIgnoreCase("christmas") || command[0].equalsIgnoreCase("xmas")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 0),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("snow2") ||
                command[0].equalsIgnoreCase("christmas2") || command[0].equalsIgnoreCase("xmas2")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 4),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("snow3") ||
                command[0].equalsIgnoreCase("christmas3") || command[0].equalsIgnoreCase("xmas3")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 8),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("snow4") ||
                command[0].equalsIgnoreCase("christmas4") || command[0].equalsIgnoreCase("xmas4")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 12),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("snow5") ||
                command[0].equalsIgnoreCase("christmas5") || command[0].equalsIgnoreCase("xmas5")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 16),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow6") ||
                command[0].equalsIgnoreCase("christmas6") || command[0].equalsIgnoreCase("xmas6")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 20),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow7") ||
                command[0].equalsIgnoreCase("christmas7") || command[0].equalsIgnoreCase("xmas7")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 24),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow8") ||
                command[0].equalsIgnoreCase("christmas8") || command[0].equalsIgnoreCase("xmas8")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 28),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow9") ||
                command[0].equalsIgnoreCase("christmas9") || command[0].equalsIgnoreCase("xmas9")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 32),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow10") ||
                command[0].equalsIgnoreCase("christmas10") || command[0].equalsIgnoreCase("xmas10")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 36),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("snow11") ||
                command[0].equalsIgnoreCase("christmas11") || command[0].equalsIgnoreCase("xmas11")) {
            player.sendMessage("Welcome to the Christmas Event!!");
            TeleportHandler.teleportPlayer(player, new Position(3039, 2844, 40),
                    player.getSpellbook().getTeleportType());
        }*/


        if (command[0].equalsIgnoreCase("train") || command[0].equalsIgnoreCase("starterzones")) {
            ProgressionZone.teleport(player);
        }

        if (command[0].equalsIgnoreCase("claimdonation") || command[0].equalsIgnoreCase("claimdonate")
                || command[0].equalsIgnoreCase("claim") || command[0].equalsIgnoreCase("donated")) {
            player.claimDonation(player, false);
        }

        if (command[0].equalsIgnoreCase("fix")) {
            player.getLocalNpcs().clear();
        }
        if (command[0].equalsIgnoreCase("kills")) {
            player.getPacketSender().sendInterfaceRemoval();
            BossLog.open(player);
//            KillTrackerInterface.open(player);
        }
        if (command[0].equalsIgnoreCase("oldkills")) {
            player.getPacketSender().sendInterfaceRemoval();
            KillTrackerInterface.open(player);
        }
        if (command[0].equalsIgnoreCase("dropmessage")) {
            player.dropMessageToggle = !player.dropMessageToggle;
            player.sendMessage("Show drop messages currently set to: " + player.dropMessageToggle);
            return;
        }
        if (command[0].equalsIgnoreCase("togglecosmetic")) {
            player.setCosmeticOveride(!player.isCosmeticOveride());
            player.sendMessage("Showing Cosmetic overrides: " + player.isCosmeticOveride());
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            return;
        }
        if (command[0].equalsIgnoreCase("claimscratch")) {
            player.getScratchcard().claimPrize();
            return;
        }
        if (player.getAmountDonated() >= 10) {
            if (command[0].equalsIgnoreCase("dzone") || command[0].equalsIgnoreCase("sapphire")
                    || command[0].equalsIgnoreCase("memberzone") || command[0].equalsIgnoreCase("mzone")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS

                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.MEMBER_ZONE, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("Thanks for supporting " + GameSettings.RSPS_NAME + "!");
            }
        }
        if (player.getAmountDonated() >= 50) {
            if (command[0].equalsIgnoreCase("ezone") || command[0].equalsIgnoreCase("emerald")
                    || command[0].equalsIgnoreCase("emeraldzone") || command[0].equalsIgnoreCase("ez")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS

                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.SUPER_ZONE, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("Thanks for supporting " + GameSettings.RSPS_NAME + "!");
            }
        }

        if (command[0].equalsIgnoreCase("zenyte") ||command[0].equalsIgnoreCase("zzone") || command[0].equalsIgnoreCase("zenytezone")) {
            if (player.getAmountDonated() >= 5000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2785, 4839),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equalsIgnoreCase("hydrix") || command[0].equalsIgnoreCase("hzone") || command[0].equalsIgnoreCase("hydra")) {
            if (player.getAmountDonated() >= 25000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2721, 4841, 5),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equalsIgnoreCase("hydrix2") || command[0].equalsIgnoreCase("hzone2")  || command[0].equalsIgnoreCase("hydra2")) {
            if (player.getAmountDonated() >= 25000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2721, 4841, 9),
                        player.getSpellbook().getTeleportType());
            }
        }



        if (command[0].equalsIgnoreCase("tanz") || command[0].equalsIgnoreCase("tanzanite") || command[0].equalsIgnoreCase("tzone")) {
            if (player.getAmountDonated() >= 10000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2721, 4841, 4),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equalsIgnoreCase("tanz2") || command[0].equalsIgnoreCase("tanzanite2") || command[0].equalsIgnoreCase("tzone2")) {
            if (player.getAmountDonated() >= 10000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2721, 4841, 8),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equalsIgnoreCase("onyx") || command[0].equalsIgnoreCase("onyxzone")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equalsIgnoreCase("onyx2") || command[0].equalsIgnoreCase("onyxzone2")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408, 4),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equalsIgnoreCase("diamond") || command[0].equalsIgnoreCase("diamondzone")) {
            if (player.getAmountDonated() >= 500 || player.getRights().isHighStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2720, 2721, 3),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (player.getAmountDonated() >= 250 || player.getRights().isHighStaff()) {
            if (command[0].equalsIgnoreCase("rzone") || command[0].equalsIgnoreCase("ruby")
                    || command[0].equalsIgnoreCase("rubyzone") || command[0].equalsIgnoreCase("rz")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS

                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.EXTREME_ZONE, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("Thanks for supporting " + GameSettings.RSPS_NAME + "!");

            }
        }
     /*   if (player.getAmountDonated() >= 250 || player.getRights().isHighStaff()) {
            if (command[0].equalsIgnoreCase("ezonenpcs") || command[0].equalsIgnoreCase("ez")
                    || command[0].equalsIgnoreCase("ezonenpc") || command[0].equalsIgnoreCase("enpc")
                    || command[0].equalsIgnoreCase("enpcs") || command[0].equalsIgnoreCase("extremenpcs")
                    || command[0].equalsIgnoreCase("extremenpc")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS

                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");

                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.EXTREME_ZONE_NPC, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("<shad=1>@or2@Teleporting Ezone NPC'S!");
            }
        }*/
      /*  if (player.getAmountDonated() >= 50 || player.getRights().isHighStaff()) {
            if (command[0].equalsIgnoreCase("szonenpcs") || command[0].equalsIgnoreCase("szonenpc")
                    || command[0].equalsIgnoreCase("snpc") || command[0].equalsIgnoreCase("supernpcs")
                    || command[0].equalsIgnoreCase("supernpc") || command[0].equalsIgnoreCase("snpcs")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS

                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.SUPER_ZONE_NPC, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("<shad=1>@or2@Teleporting Szone NPC'S!");
            }
        }*/
        if (command[0].equalsIgnoreCase("answer")) {
            String answer = wholeCommand.substring(7);
            TriviaSystem.answer(player, answer);
        }

      /*  if (command[0].equalsIgnoreCase("zonetasks") || command[0].equalsIgnoreCase("slayer")
                || command[0].equalsIgnoreCase("progress") || command[0].equalsIgnoreCase("progression")) {
            player.getProgressionManager().open();
        }
*/
      /*  if (command[0].equalsIgnoreCase("requestraid") || command[0].equalsIgnoreCase("invplayer")) { // test command.
            Player target = World.getPlayerByName(command[1]);
            player.getMinigameAttributes().getDungeoneeringAttributes().getParty().invite(target);
        }

        if (command[0].equalsIgnoreCase("createraidparty") || command[0].equalsIgnoreCase("createParty")) {
            DungeoneeringParty.create(player);
        }

        if (command[0].equalsIgnoreCase("deleteraidparty") || command[0].equalsIgnoreCase("deleteparty")) {
            Dungeoneering.leave(player, false, true);
            player.getPacketSender().sendString(29053, "").sendString(29054, "");

            for (int i = 0; i < 10; i++) {
                player.getPacketSender().sendString(29095 + i, "");
            }
        }*/
        if (command[0].equalsIgnoreCase("stats") || command[0].equalsIgnoreCase("itemstats")
                || command[0].equalsIgnoreCase("itemsstat") || command[0].equalsIgnoreCase("itemsstats")) {
            BestItemsInterface.openInterface(player, 0);
        }
/*

        if (wholeCommand.equalsIgnoreCase("startraids") || wholeCommand.equalsIgnoreCase("startraid")
                || wholeCommand.equalsIgnoreCase("startraids")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Dungeoneering.start(player);
        }
*/

        if (command[0].equalsIgnoreCase("raids") || command[0].equalsIgnoreCase("islandraids")) {
            if (player.getSkillManager().getMaxLevel(Skill.SLAYER) < 98
                    && player.getSkillManager().getMaxLevel(Skill.SLAYER) < 79) {

                player.getPacketSender().sendMessage("<shad=1>@or2@You must be 99+ Slayer to do Island Raids.");
                player.getPacketSender().sendMessage("<shad=1>@or1@You must be 80+ Invention to do Island Raids.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(2553, 3717), player.getSpellbook().getTeleportType());
        }

       /* if (command[0].equalsIgnoreCase("raids1") || command[0].equalsIgnoreCase("raids")
                || command[0].equalsIgnoreCase("raidsone") || command[0].equalsIgnoreCase("leave")) {
            if (player.getSkillManager().getCombatLevel() < 100) {
                player.getPacketSender().sendMessage(
                        "You need a combat level of @blu@100@bla@ and level @blu@50@bla@ on all non-combat skills.@red@no dungeoneering.");
                return;
            }
            for (int i = 7; i < Skill.values().length; i++) {
                if (i == 21 || i == 25 || i == 24 || i == 23 || i == 18)
                    continue;
                if (player.getSkillManager().getMaxLevel(i) < 49) {

                    player.getPacketSender().sendMessage(
                            "You must be at least level @blu@50@bla@ in every non-combat skill to do raids. @red@no dungeoneering.");
                    return;

                }
            }

            TeleportHandler.teleportPlayer(player, new Position(2722, 2737), player.getSpellbook().getTeleportType());
            Dungeoneering.leave(player, false, true);
            if (player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
                player.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
            } else {
                player.getPacketSender().sendString(29053, "").sendString(29054, "");

                for (int i = 0; i < 10; i++) {
                    player.getPacketSender().sendString(29095 + i, ""); // this should be the final thing., added UI
                    // check for every place now.
                }
            }

        }*/

        if (command[0].equalsIgnoreCase("starter")
                || command[0].equalsIgnoreCase("start")) {
            player.getStarterTasks().openInterface();
        }
        if (command[0].equalsIgnoreCase("afkcount")) {
            player.sendMessage("@blu@Current afk ores mined count: @red@" + AfkStallSystem.stoleAmount);
        }
        if (command[0].equalsIgnoreCase("killcount")) {
            player.sendMessage("@blu@Current goku count: @red@" + GokuSystem.npckills);
        }
        if (command[0].equalsIgnoreCase("gokukills")) {
            player.sendMessage("@blu@Current goku count: @red@" + GokuSystem.npckills);
        }
        if (command[0].equalsIgnoreCase("lotterybuy")) {
            if (player.getInventory().contains(995, 50000000)) {
                player.setEntriesCurrency(player.getEntriesCurrency() + 5.0);
                player.getInventory().delete(995, 50000000);
                player.getPA().sendMessage("You have purchased a lottery ticket, go enter the lottery!");
            } else {
                player.getPA().sendMessage("Each lottery ticket costs 50 Mill, Please have 50 Mill in your inventory.");
            }
        }

        if (command[0].equalsIgnoreCase("scratch")) {
            player.getScratchCard().open();
        }

        if (command[0].equalsIgnoreCase("donationdeals") || command[0].equalsIgnoreCase("deals")) {
            player.sendMessage(
                    "<shad=1>@yel@<img=14>Please check out the donation deals in our ::Discord - #Donation-deals");
            player.sendMessage(
                    "<shad=1>@yel@<img=14>Please check out the donation deals in our ::Discord - #Donation-deals");
        }

        if (command[0].equalsIgnoreCase("setcustomslayer")) {
            TaskType.changeSlayerMaster(player, TaskType.EASY);
            // System.out.println("Slayer master is now custom slayer");
        }

        if (command[0].equalsIgnoreCase("streak")) {
            player.getVotingStreak().openInterface();
        }
        if (command[0].equalsIgnoreCase("daily")
                || command[0].equalsIgnoreCase("dailytasks")) {
            player.getDailyTaskManager().open();
        }

        if (command[0].equalsIgnoreCase("claimit") || command[0].equalsIgnoreCase("claimdaily")) {
            player.lastDailyClaim = System.currentTimeMillis() + 43200000;
        }


        if (wholeCommand.equalsIgnoreCase("droprate") || wholeCommand.equalsIgnoreCase("mydr") || wholeCommand.equalsIgnoreCase("dr")) {
            player.getPacketSender()
                    .sendMessage("Droprate bonus: @red@" + NPCDrops.dropRateBoost(player)
                            + "%@bla@. X2 Drop chance: <col=248f8f>" + CustomDropUtils.getDoubleDropChance(player)
                            + "/1000");
        }
        if (wholeCommand.equalsIgnoreCase("ddr")) {
            player.getPacketSender().sendMessage(
                    "Your Double  bonus is + @red@" + CustomDropUtils.getDoubleDropChance(player) + "@bla@%.");
        }
        if (wholeCommand.equalsIgnoreCase("donationdiscount")) {
            player.getdonatordiscount().init();
        }
        if (wholeCommand.equalsIgnoreCase("donationclose")) {
            player.getdonatordiscount().end();
        }
        if (command[0].equalsIgnoreCase("time") || command[0].equalsIgnoreCase("date")
                || command[0].equalsIgnoreCase("clock")) {
            int month = 1 + Misc.getMonth();
            String mo = Integer.toString(month);
            String dd = Integer.toString(Misc.getDayOfMonth());
            String weekend = "";

            if (Misc.getDayOfMonth() < 10) {
                dd = "0" + dd;
            }

            if (month < 10) {
                mo = "0" + mo;
            }

            if (Misc.isWeekend()) {
                weekend = ". It's the weekend";
            }

            player.getPacketSender().sendMessage("@cya@<shad=0>[Time] <shad=-1>@bla@[" + mo + "/" + dd + "] (MM/DD) @ "
                    + Misc.getCurrentServerTime() + " (24:00) in New York City, USA" + weekend + ".");
        }
        if (command[0].equalsIgnoreCase("rewards") || command[0].equalsIgnoreCase("loot")
                || command[0].equalsIgnoreCase("loots")) {
            PossibleLootInterface.openInterface(player, PossibleLootInterface.LootData.SUPER);
        }
        if (command[0].equalsIgnoreCase("titles")) {
            player.getTitlesManager().openInterface();
        }
        if (command[0].equalsIgnoreCase("hasVoid")) {
            if (EquipmentBonus.wearingVoid(player)) {
                player.getPacketSender().sendMessage("You are wearing void.");
            } else {
                player.getPacketSender().sendMessage("You're not wearing void.");
            }
            if (EquipmentBonus.voidElite(player)) {
                player.getPacketSender().sendMessage("You're wearing voidElite.");
            } else {
                player.getPacketSender().sendMessage("You're not wearing voidElite.");
            }
            if (EquipmentBonus.voidMage(player)) {
                player.getPacketSender().sendMessage("You're wearing mage void.");
            } else {
                player.getPacketSender().sendMessage("You're not wearing mage void");
            }
            if (EquipmentBonus.voidMelee(player)) {
                player.getPacketSender().sendMessage("You're wearing melee void.");
            } else {
                player.getPacketSender().sendMessage("You're not wearing melee void");
            }
            if (EquipmentBonus.voidRange(player)) {
                player.getPacketSender().sendMessage("You're wearing range void.");
            } else {
                player.getPacketSender().sendMessage("You're not wearing range void");
            }
        }
        if (command[0].equalsIgnoreCase("maxhit")) {
            player.getPacketSender().sendMessage("<shad=1>@red@Melee Maxhit: " + (Maxhits.melee(player, player) / 10));
            player.getPacketSender()
                    .sendMessage("<shad=1>@gre@Ranged Maxhit: " + (Maxhits.ranged(player, player) / 10));
            player.getPacketSender().sendMessage("<shad=1>@cya@Magic Maxhit: " + (Maxhits.magic(player, player) / 10));
        }


        if (command[0].startsWith("reward") || command[0].startsWith("voted") || command[0].startsWith("claimvote")) {


            if (!player.getLastVoteClaim().elapsed(250)) {
                player.getPacketSender()
                        .sendMessage("You must wait at least 1 second before using " + command[0] + " again.");
                return;
            }
            player.getPacketSender().sendMessage("Checking for votes...");
            new Thread(new Voting(player)).start();


        }

        if (command[0].equalsIgnoreCase("achievements") || command[0].equalsIgnoreCase("dailytasks")
                || command[0].equalsIgnoreCase("tasks")) {
            player.getPacketSender().sendInterface(36000);
            player.getAchievements().refreshInterface(player.getAchievements().currentInterface);
            player.getPacketSender().sendConfig(6000, 3);
        }

        if (command[0].equalsIgnoreCase("resetdaily")) {
            new DailyTaskHandler(player).resetTasks();
        }
        if (command[0].equalsIgnoreCase("destroyinstance")) {
            player.getInstanceManager().onLogout();
        }
/*        if (command[0].equalsIgnoreCase("region")) {
            System.out.println(player.getPosition().getRegionId());
        }*/
        if (command[0].equalsIgnoreCase("myregion")) {
            player.sendMessage("Current Region: " + player.getPosition().getRegionId());
            player.sendMessage("Usage: ::myregion regionid");
        }


        if (command[0].equalsIgnoreCase("collection") || command[0].equalsIgnoreCase("collectionlog")) {
            player.getCollectionLogManager().openInterface();
        }
        if (command[0].equalsIgnoreCase("whatdrops")) {
            try {
                boolean isItem = false;
                int itemId = 0;
                String target = wholeCommand.substring(command[0].length() + 1);
                player.getPacketSender().sendMessage("Finding what drops \"" + target + "\".");

                for (int i = 0; i < ItemDefinition.getDefinitions().length; i++) {
                    if (ItemDefinition.forId(i) == null || ItemDefinition.forId(i).getName() == null) {
                        continue;
                    }
                    if (ItemDefinition.forId(i).getName().toLowerCase().equalsIgnoreCase(target)) {
                        isItem = true;
                        itemId = i;
                        break;
                    }
                }
                if (!isItem) {
                    player.getPacketSender().sendMessage("No item named \"" + target + "\" was found.");
                    return;
                } /*
                 * else { player.getPacketSender().sendMessage("Found the item"); }
                 */

                for (int i = 0; i < NpcDefinition.getDefinitions().length; i++) {
                    if (NpcDefinition.forId(i) == null || NpcDefinition.forId(i).getName() == null
                            || NPCDrops.forId(i) == null || NPCDrops.forId(i).getDropList() == null
                            || NPCDrops.forId(i).getDropList().length <= 0) {
                        // // System.out.println("fuck sake");
                        continue;
                    }

                    for (int q = 0; q < NPCDrops.forId(i).getDropList().length; q++) {
                        if (ItemDefinition.forId(NPCDrops.forId(i).getDropList()[q].getItem().getId()) == null
                                || ItemDefinition.forId(NPCDrops.forId(i).getDropList()[q].getItem().getId())
                                .getName() == null) {
                            // // System.out.println("what the hell");
                            continue;
                        }
                        // //
                        // System.out.println(ItemDefinition.forId(NPCDrops.forId(i).getDropList()[q].getId()).getName()+"
                        // VS "+ItemDefinition.forId(itemId).getName());

                        if (ItemDefinition.forId(NPCDrops.forId(i).getDropList()[q].getId()).getName()
                                .equalsIgnoreCase(ItemDefinition.forId(itemId).getName())) {
                            player.getPacketSender()
                                    .sendMessage(NpcDefinition.forId(i).getName() + " drops " + target + ".");
                            continue;
                        }
                    }
                }


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (command[0].equalsIgnoreCase("drops") || command[0].equalsIgnoreCase("drop")) {
            player.getPacketSender().sendMessage("Opening drops interface...");
            DropsInterface.open(player);
        }
        if (wholeCommand.equalsIgnoreCase("imp2") || wholeCommand.equalsIgnoreCase("imps2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2971, 9491, 4);

            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("lords2") || wholeCommand.equalsIgnoreCase("lord2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2989, 9483, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("joker") || wholeCommand.equalsIgnoreCase("jokers")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(1807, 3211, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("demon2") || wholeCommand.equalsIgnoreCase("demons2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2330, 3888, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("dragon2") || wholeCommand.equalsIgnoreCase("dragons2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2377, 3886, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("beast2") || wholeCommand.equalsIgnoreCase("beasts2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }

            Position position = new Position(1698, 5600, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("king2") || wholeCommand.equalsIgnoreCase("kings2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(1625, 5601, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("pred2") || wholeCommand.equalsIgnoreCase("preds2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2901, 3617, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("bulwark2") || wholeCommand.equalsIgnoreCase("bulwarks2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2413, 3523, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("imp") || wholeCommand.equalsIgnoreCase("imps")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.DEVILSPAWN;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("lords") || wholeCommand.equalsIgnoreCase("lord")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.LORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("demon") || wholeCommand.equalsIgnoreCase("demons")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.DEMON;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("dragon") || wholeCommand.equalsIgnoreCase("dragons")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.DRAGON;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("beast") || wholeCommand.equalsIgnoreCase("beasts")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.BEAST;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("zamasu") || wholeCommand.equalsIgnoreCase("zamas")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.HULK;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (wholeCommand.equalsIgnoreCase("question") || wholeCommand.equalsIgnoreCase("triviaquestion")) {
            player.getPacketSender()
                    .sendMessage("<shad=1>@gre@Current trivia question:@yel@ " + TriviaSystem.getCurrentQuestion());
        }

        if (wholeCommand.equalsIgnoreCase("Leo") || wholeCommand.equalsIgnoreCase("Lion")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.LEO;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("king") || wholeCommand.equalsIgnoreCase("kings")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.KING;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("pred") || wholeCommand.equalsIgnoreCase("preds")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.PREDATOR;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("bulwark") || wholeCommand.equalsIgnoreCase("bulwarks")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.BULWARK;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (wholeCommand.equalsIgnoreCase("eventzone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.EVENT;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }

        if (wholeCommand.equalsIgnoreCase("peng") || wholeCommand.equalsIgnoreCase("penguin")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.PENG;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("cyan")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.CYAN;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
      /*  if (wholeCommand.equalsIgnoreCase("revs")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.REVS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }*/

       /* if (wholeCommand.equalsIgnoreCase("gwd")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.GWD;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }
        if (wholeCommand.equalsIgnoreCase("corp")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.CORP;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("Teleporting you home!");

        }*/
        if (wholeCommand.equalsIgnoreCase("commands")) {

            for (int i = 8145; i < 8196; i++)
                player.getPacketSender().sendString(i, "");

            player.getPacketSender().sendInterface(8134);

            player.getPacketSender().sendString(8136, "Close window");
            player.getPacketSender().sendString(8144, "Commands");
            player.getPacketSender().sendString(8145, "");
            int index = 8147;
            String color = "@dre@";
            String color1 = "@red@";

            player.getPacketSender().sendString(index++, color1 + "Main Commands:");
            player.getPacketSender().sendString(index++, color + "::home - Teleports you home");
            player.getPacketSender().sendString(index++,
                    color + "::train - Opens up the starter interface with a list of teleports");
            player.getPacketSender().sendString(index++, color + "::melee - Teleports you to Charmander");
            player.getPacketSender().sendString(index++, color + "::mage - Teleports you to Squirtle");
            player.getPacketSender().sendString(index++, color + "::range - Teleports you to Bulbasaur");
            player.getPacketSender().sendString(index++, color + "::shops - Teleports you to all shops");
            player.getPacketSender().sendString(index++, color + "");
            player.getPacketSender().sendString(index++, color1 + "Interface Commands:");
            player.getPacketSender().sendString(index++, color + "::kills - opens up your personal kill tracker list");
            player.getPacketSender().sendString(index++, color + "::pos - opens the player owned shops interface");
            player.getPacketSender().sendString(index++, color + "::teleport - opens the monster teleports interface");
            player.getPacketSender().sendString(index++, color + "::upgrade - opens the upgrade system interface");
            player.getPacketSender().sendString(index++, color + "::slayer - opens the progession manager interface");
            player.getPacketSender().sendString(index++, color + "::drops - opens the loot viewer interface for npcs");
            player.getPacketSender().sendString(index++, color + "::collection - opens the collection log interface");
            player.getPacketSender().sendString(index++, color + "::itemstats - opens up best items interface");
            player.getPacketSender().sendString(index++, color + "");
            player.getPacketSender().sendString(index++, color1 + "Other Commands:");
            player.getPacketSender().sendString(index++, color + "::dr - shows you your current droprate");
            player.getPacketSender().sendString(index++,
                    color + "::globals - shows you the time remaining on all global bosses");
            player.getPacketSender().sendString(index++,
                    color + "::bank - opens up your bank ($50 total claimed required)");
            player.getPacketSender().sendString(index++,
                    color + "::players - tells you how many players are currently online");
            player.getPacketSender().sendString(index++, color + "::discord - opens up our discord for Lunite");
            player.getPacketSender().sendString(index++, color + "::vote - opens up our site for voting");
            player.getPacketSender().sendString(index++, color + "::voted - claims your votes");
            player.getPacketSender().sendString(index++, color + "::donate - opens up our donation site");
            player.getPacketSender().sendString(index++, color + "::donated - claims your donation");
            player.getPacketSender().sendString(index++,
                    color + "::dissolveall - dissolves all dissolvable items in your inv");
            player.getPacketSender().sendString(index++, color + "::ckeys - teleports you to the crystal chest");
            player.getPacketSender().sendString(index++,
                    color + "::whatdrops (item name) - tells you what drops the item");
            player.getPacketSender().sendString(index++,
                    color + "::dropmessage - removes messages of drops going to your inv/bank");
            player.getPacketSender().sendString(index++, color + "::help - requests assistance from a staff member");
            player.getPacketSender().sendString(index++, color + "::yell - sends a global message");
            player.getPacketSender().sendString(index++, color + "");
        }

        if (wholeCommand.equalsIgnoreCase("dissolvevalue")) {

            for (int i = 39007; i < 39707; i++)
                player.getPacketSender().sendString(i, "");

            player.getPacketSender().sendInterface(39000);

            int index = 39007;

            if (dissolvables.isEmpty()) {
                for (Dissolving.DissolvingData data : Dissolving.DissolvingData.values()) {
                    dissolvables.put(ItemDefinition.forId(data.getId()).getName(), data.getRewards()[0].getAmount());
                }
                dissolvables = sortByValue(dissolvables);
            }

            player.getPacketSender().setScrollBar(39006, dissolvables.size() * 20);
            for (Map.Entry<String, Integer> entry : dissolvables.entrySet()) {
                player.getPacketSender().sendString(index++, entry.getKey() + " [" + Misc.format(entry.getValue()) + "]");
            }
        }

        if (wholeCommand.equalsIgnoreCase("ironmaninfo") || wholeCommand.equalsIgnoreCase("hciminfo")) {
            player.getPacketSender().sendString(1, GameSettings.IronManModesUrl);
            player.getPacketSender().sendMessage("Attempting to open Iron Man Mode Info");
        }
        if (wholeCommand.equalsIgnoreCase("donorinfo") || wholeCommand.equalsIgnoreCase("memberinfo")) {
            player.getPacketSender().sendString(1, GameSettings.RankFeaturesUrl);
            player.getPacketSender().sendMessage("Attempting to open Member Info");
        }


        if (command[0].equalsIgnoreCase("dumprankindex")) {
            player.sendMessage("Sending icons...");
            for (int i = 0; i < 355; i++)
                player.sendMessage("Icon Id=" + i + " Image=<img=" + i + ">"); // restart client n server*
        }
        if (wholeCommand.equalsIgnoreCase("effigy")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/channel/UCR-GGPuNM7V51JYWVbcDURQ");
            player.getPacketSender().sendMessage("Attempting to open Effigy's youtube channel");
        }
        if (wholeCommand.equalsIgnoreCase("donate") || wholeCommand.equalsIgnoreCase("store")) {
            player.getPacketSender().sendString(1, GameSettings.StoreUrl);
            player.getPacketSender().sendMessage("Attempting to open the store");
        }
        if (wholeCommand.equalsIgnoreCase("stabletv") || wholeCommand.equalsIgnoreCase("stable")) {
            player.getPacketSender().sendString(1, GameSettings.Stable);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("effigy") || wholeCommand.equalsIgnoreCase("effigyswiper")) {
            player.getPacketSender().sendString(1, GameSettings.effigy);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("sprad") || wholeCommand.equalsIgnoreCase("spradder")) {
            player.getPacketSender().sendString(1, GameSettings.sprad);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("wr3cked") || wholeCommand.equalsIgnoreCase("wr3ckedyou")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/watch?v=kWiLSXkqOh8");
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("chris") || wholeCommand.equalsIgnoreCase("unreal")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/watch?v=09rnoNoZ9SU");
            player.getPacketSender().sendMessage("Attempting to open the stream!");
        }
        if (wholeCommand.equalsIgnoreCase("tudorrs") || wholeCommand.equalsIgnoreCase("tudor")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/TudorRSPS");
            player.getPacketSender().sendMessage("Attempting to open the channel!");
        }


        if (wholeCommand.equalsIgnoreCase("frimb") || wholeCommand.equalsIgnoreCase("frimbrsps")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/Frimb/videos");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("client") || wholeCommand.equalsIgnoreCase("launcher")) {
            player.getPacketSender().sendString(1, "https://lunite.io/resources/LuniteLauncher.jar");
            player.getPacketSender().sendMessage("Attempting to download the launcher");
        }

        if (wholeCommand.equalsIgnoreCase("ipkmaxjr")) {
            player.getPacketSender().sendString(1, GameSettings.ipkmaxjr);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("zach") || wholeCommand.equalsIgnoreCase("zachtx")) {
            player.getPacketSender().sendString(1, GameSettings.ZachTX);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("ogrsps") || wholeCommand.equalsIgnoreCase("og")
                || wholeCommand.equalsIgnoreCase("0grsps")) {
            player.getPacketSender().sendString(1, GameSettings.ogrsps);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("khalil") || wholeCommand.equalsIgnoreCase("khalilrsps")) {
            player.getPacketSender().sendString(1, GameSettings.Khalil);
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }

        if (wholeCommand.equalsIgnoreCase("didy") || wholeCommand.equalsIgnoreCase("didyscape")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/DidyScape/");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("fpk") || wholeCommand.equalsIgnoreCase("fpkmerk")
                || wholeCommand.equalsIgnoreCase("merk")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/FPKMerk");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("lano")
                || wholeCommand.equalsIgnoreCase("lanors")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/lanors");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("divine") || wholeCommand.equalsIgnoreCase("STREAM")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/channel/UC0TMGICBbXo9dhzbC6mWvqg");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("daren")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/watch?v=kjYLXFzatac");
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("sipsick")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/channel/UC74CQ_yflg-KQrjtCXpmozA");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("walkchaos")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/c/Walkchaos");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("starterguide")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/watch?v=V-ZkRNvbGF8");
            player.getPacketSender().sendMessage("Attempting to open the channel");
        }
        if (wholeCommand.equalsIgnoreCase("eggy")) {
            player.getPacketSender().sendString(1, "https://www.youtube.com/channel/UC3S8CHERuzF7N5T3ynrBroA ");
            player.getPacketSender().sendMessage("Attempting to open the stream");
        }
        if (wholeCommand.equalsIgnoreCase("discord") || wholeCommand.equalsIgnoreCase("chat")) {
            player.getPA().sendInterface(19130);
            DiscordIntegration.updateDiscordInterface(player);
            //  player.getPacketSender().sendString(1, GameSettings.DiscordUrl);
            //    player.getPacketSender().sendMessage("Attempting to open our Discord Server");
        }

        if (wholeCommand.equalsIgnoreCase("forums") || wholeCommand.equalsIgnoreCase("forum")) {
            player.getPacketSender().sendString(1, GameSettings.ForumUrl);
            player.getPacketSender().sendMessage("Attempting to open the forums");
        }
        if (wholeCommand.equalsIgnoreCase("forums") || wholeCommand.equalsIgnoreCase("forum")) {
            player.getPacketSender().sendString(1, GameSettings.ForumUrl);
            player.getPacketSender().sendMessage("Attempting to open the forums");
        }
        if (wholeCommand.equalsIgnoreCase("updates") || wholeCommand.equalsIgnoreCase("whatsnew")) {
            player.getPacketSender().sendString(1, "https://Lunite.io/updates/");
            player.getPacketSender().sendMessage("Attempting to open the the update list");
        }
        if (wholeCommand.equalsIgnoreCase("update")) {
            player.getPacketSender().sendString(1, "https://Lunite.io/updates/33.php");
            player.getPacketSender().sendMessage("Attempting to open the the newest update");
        }
        if (wholeCommand.equalsIgnoreCase("guides")) {
            player.getPacketSender().sendString(1, "https://Lunite.io/guides/");
            player.getPacketSender().sendMessage("Attempting to open the the guides");
        }

        if (wholeCommand.equalsIgnoreCase("rule") || wholeCommand.equalsIgnoreCase("rules")) {
            player.getPacketSender().sendString(1, GameSettings.RuleUrl);
            player.getPacketSender().sendMessage("Attempting to open the Rules.");
        }
        if (wholeCommand.equalsIgnoreCase("changepass") || wholeCommand.equalsIgnoreCase("changepassword")) {
            player.setInputHandling(new ChangePassword());
            player.getPacketSender().sendEnterInputPrompt("Enter a new password:");
        }
        if (command[0].equalsIgnoreCase("attacks")) {
            int attack = DesolaceFormulas.getMeleeAttack(player);
            int range = DesolaceFormulas.getRangedAttack(player);
            int magic = DesolaceFormulas.getMagicAttack(player);
            player.getPacketSender().sendMessage("@bla@Melee attack: @or2@" + attack + "@bla@, ranged attack: @or2@"
                    + range + "@bla@, magic attack: @or2@" + magic);
        }
        if (command[0].equalsIgnoreCase("vote")) {
            player.getPacketSender().sendString(1, GameSettings.VoteUrl);// "http://Ruseps.com/vote/?user="+player.getUsername());
            player.getPacketSender().sendMessage("When you vote do ::claimvote to redeem votes");
        }
        if (command[0].equalsIgnoreCase("pricelist")) {
            player.getPacketSender().sendString(1, GameSettings.PriceList);// "http://Ruseps.com/vote/?user="+player.getUsername());
            player.getPacketSender().sendMessage("Attempting to open pricelist");
        }
        if (command[0].equalsIgnoreCase("hiscores") || command[0].equalsIgnoreCase("highscores")) {
            player.getPacketSender().sendString(1, GameSettings.HiscoreUrl);
            player.getPacketSender().sendMessage("Attempting to open the hiscores.");
        }

        if (command[0].equalsIgnoreCase("toggleglobalmessages") || command[0].equalsIgnoreCase("globalmessages")) {
            if (player.toggledGlobalMessages() == false) {
                player.getPacketSender().sendMessage("You have opted out from filterable global messages.");
                player.setToggledGlobalMessages(true);
            } else {
                player.getPacketSender().sendMessage("You have opted in to filterable global messages.");
                player.setToggledGlobalMessages(false);
            }
        }
        if (command[0].equalsIgnoreCase("general")) {
            TeleportHandler.teleportPlayer(player, new Position(2334, 9811, 0),
                    player.getSpellbook().getTeleportType());
            // player.getPacketSender().sendMessage("Old cords: 3363, 3807");
        }
        if (command[0].equalsIgnoreCase("herbal")) {
            TeleportHandler.teleportPlayer(player, new Position(2897, 2863, 0),
                    player.getSpellbook().getTeleportType());
            // player.getPacketSender().sendMessage("Old cords: 3363, 3807");
        }
        if (command[0].equalsIgnoreCase("scarlet")) {
            TeleportHandler.teleportPlayer(player, new Position(2898, 2832, 0),
                    player.getSpellbook().getTeleportType());
            // player.getPacketSender().sendMessage("Old cords: 3363, 3807");
        }
        if (command[0].equalsIgnoreCase("azure")) {
            TeleportHandler.teleportPlayer(player, new Position(2930, 2844, 0),
                    player.getSpellbook().getTeleportType());
            // player.getPacketSender().sendMessage("Old cords: 3363, 3807");
        }
        if (command[0].equalsIgnoreCase("moneymaking")) {
            player.getPacketSender().sendMessage("Type ::discord and go to the Guides Section!");
        }
        if (command[0].equalsIgnoreCase("well")) {
            player.setDialogueActionId(215);
            DialogueManager.start(player, 215);
        }

        if (command[0].equalsIgnoreCase("pos") && player.getLocation() != Location.HOME_BANK
                && player.getAmountDonated() < 50) {
            player.sendMessage("You either need $50 total claim or can only use this command at ::Home");
            return;
        } else if (command[0].equalsIgnoreCase("pos") && player.getAmountDonated() >= 50) {
            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.JAIL

                    || player.getLocation() == Location.DUEL_ARENA) {
                player.sendMessage("You cannot do this here.");
                return;
            }
            player.sendMessage("As a $50 Donator benefit, you can use this command anywhere.");
            player.getPlayerOwnedShopManager().options();
        } else if (command[0].equalsIgnoreCase("pos") && player.getLocation() == Location.HOME_BANK
                && player.getAmountDonated() < 50) {

            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.JAIL

                    || player.getLocation() == Location.DUEL_ARENA) {
                player.sendMessage("You cannot do this here.");
                return;
            }
            player.getPlayerOwnedShopManager().options();
        }
        if (command[0].equalsIgnoreCase("setloginpin")) {
            if (player.getHasPin() == false) {

                player.setInputHandling(new SetPinPacketListener());
                player.getPacketSender().sendEnterInputPrompt("Enter the pin that you want to set$pin");
            }
        }
        if (command[0].equalsIgnoreCase("enterpin")) {
            if (player.getHasPin() == false) {
                player.setInputHandling(new SetPinPacketListener());
                player.getPacketSender().sendEnterInputPrompt("Enter the pin that you want to set$pin");
            }
        }
        if (command[0].equalsIgnoreCase("changepin")) {
            if (player.getHasPin() == true &&
                    player.getSavedIp().equalsIgnoreCase(player.getHostAddress())) {
                player.setInputHandling(new SetPinPacketListener());
                player.getPacketSender().sendEnterInputPrompt("Enter the pin that you want to set$pin");
            }
            if (player.getHasPin() == false) {
                player.setInputHandling(new SetPinPacketListener());
                player.getPacketSender().sendEnterInputPrompt("Enter the pin that you want to set$pin");
            }
        }
        if (command[0].equalsIgnoreCase("logout")) {
            World.getPlayers().remove(player);
        }
        if (command[0].equalsIgnoreCase("refer") || command[0].equalsIgnoreCase("referral")) {
            if (!player.hasReferral) {
                player.getPacketSender().sendEnterInputPrompt("Please type your refer code to receive a reward!");
                player.setInputHandling(new EnterReferral());
            } else {
                player.getPacketSender().sendMessage("You have already claimed a referral reward on this account!");
            }
        }
        if (command[0].equalsIgnoreCase("teleports")) {
            player.getCustomTeleportInterface().open();
        }
        if (command[0].equalsIgnoreCase("switchbook")) {
            if (player.getSkillManager().getMaxLevel(Skill.DEFENCE) < 30) {
                player.getPacketSender().sendMessage("You need a Defence level of at least 30 to use this altar.");
                return;
            }
            player.performAnimation(new Animation(645));
            if (player.getPrayerbook() == Prayerbook.NORMAL) {
                player.getPacketSender().sendMessage("You sense a surge of power flow through your body!");
                player.setPrayerbook(Prayerbook.CURSES);
            } else {
                player.getPacketSender().sendMessage("You sense a surge of purity flow through your body!");
                player.setPrayerbook(Prayerbook.NORMAL);
            }
            player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB, player.getPrayerbook().getInterfaceId());
            PrayerHandler.deactivateAll(player);
            CurseHandler.deactivateAll(player);
        }
        if (command[0].equalsIgnoreCase("bug")) {
            long start = System.currentTimeMillis();
            int taskTime = (int) (System.currentTimeMillis() - start);
            System.out.println("FINAL cycle time : " + taskTime + ", with Player count : " + World.getPlayers().size());
        }
        if (command[0].equalsIgnoreCase("home")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2655, 4018, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            player.getProgressionManager().getProgressionTask(0).incrementProgress(1, 1);
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you home!");
        }
        if (command[0].equalsIgnoreCase("youtube") || (command[0].equalsIgnoreCase("stream"))) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2852, 2708, 4)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("pldoldpvp")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2375, 4021, 0), new Position(2375, 4022, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("<shad=1>@yel@Teleporting you to the custom pvp arena!");
        }
        if (command[0].equalsIgnoreCase("ckey") || command[0].equalsIgnoreCase("ckeys")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2650, 4020, 0), new Position(2650, 4020, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("<shad=1>@yel@Teleporting you to Crystal Key Chest!");
        }

        if (command[0].equalsIgnoreCase("dropparty") || command[0].equalsIgnoreCase("party")
                || command[0].equalsIgnoreCase("event")) {
            TeleportHandler.teleportPlayer(player, new Position(1696, 4265, 0), player.getSpellbook().getTeleportType());
            player.sendMessage("Trolling an event can result in you not being able to participate in events.");
        }

        if (command[0].equalsIgnoreCase("frieza") || command[0].equalsIgnoreCase("freeza")
                || command[0].equalsIgnoreCase("freiza")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2508, 3039, 0), new Position(2520, 3040, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());

        }
        if (command[0].equalsIgnoreCase("cell") || command[0].equalsIgnoreCase("perfectcell")
                || command[0].equalsIgnoreCase("pcell")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(3000, 2515, 0), new Position(3002, 2511, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());

        }
        if (command[0].equalsIgnoreCase("buu") || command[0].equalsIgnoreCase("superbuu")
                || command[0].equalsIgnoreCase("sbuu")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(3113, 3184, 0), new Position(3113, 3196, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());

        }
        if (command[0].equalsIgnoreCase("skele") || command[0].equalsIgnoreCase("skeletal")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3344, 3181, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("@red@If your dicing make sure you join
            // ::dice!");
        }
        if (command[0].equalsIgnoreCase("prime") || command[0].equalsIgnoreCase("optimus")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2466, 10156, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("@red@If your dicing make sure you join
            // ::dice!");
        }
        if (command[0].equalsIgnoreCase("titan2") || command[0].equalsIgnoreCase("titans2")
                || command[0].equalsIgnoreCase("avatar2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3301, 3289, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("@red@If your dicing make sure you join
            // ::dice!");
        }
        if (command[0].equalsIgnoreCase("angel2") || command[0].equalsIgnoreCase("angels2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3322, 3309, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("lucien2") || command[0].equalsIgnoreCase("lc2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2907, 5455, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("earthquake") || command[0].equalsIgnoreCase("eq")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2014, 4516, 0);

            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("hercules2") || command[0].equalsIgnoreCase("hercule2")
                || command[0].equalsIgnoreCase("herc2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2931, 5469, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("satan2") || command[0].equalsIgnoreCase("satanic2")
                || command[0].equalsIgnoreCase("hell2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2916, 5488, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("zeus2") || command[0].equalsIgnoreCase("zues2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2893, 5473, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("groudon2") || command[0].equalsIgnoreCase("gruodon2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2996, 3116, 4);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("titan") || command[0].equalsIgnoreCase("titans")
                || command[0].equalsIgnoreCase("avatar")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3301, 3289, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("angel") || command[0].equalsIgnoreCase("angels")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3322, 3309, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("lucien") || command[0].equalsIgnoreCase("lc")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2907, 5455, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("lazycat") || command[0].equalsIgnoreCase("cat")
                || command[0].equalsIgnoreCase("garfield") || command[0].equalsIgnoreCase("lazy")
                || command[0].equalsIgnoreCase("garfeild")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2014, 4516, 0);

            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("iron")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            if (!player.getRights().isStaff()) {
                if (!player.getGameMode().isIronman()) {
                    player.getPacketSender().sendMessage("Become an ironman!");
                    return;
                }
            }
            Position position = new Position(3811, 2839, 0);

            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("hercules") || command[0].equalsIgnoreCase("hercule")
                || command[0].equalsIgnoreCase("herc")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2931, 5469, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("satan") || command[0].equalsIgnoreCase("satanic")
                || command[0].equalsIgnoreCase("hell")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2916, 5488, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("zeus") || command[0].equalsIgnoreCase("zues")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2893, 5473, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("groudon") || command[0].equalsIgnoreCase("gruodon")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2996, 3116, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("fenrir")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2603, 4774, 8);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("bork")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            if (KillsTracker.getFenrirKills(player) < 50000) {
                player.getPacketSender().sendMessage("You need 50,000 Fenrir kills. You currently have @red@"
                        + KillsTracker.getFenrirKills(player) + "@bla@ kills.");
                return;
            }
            Position position = new Position(2603, 4774, 12);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("golden") || command[0].equalsIgnoreCase("oozau")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2581, 4510, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("dung1") || command[0].equalsIgnoreCase("slayer1")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(1861, 5242, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("tele1") || command[0].equalsIgnoreCase("redtele")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2868, 2826, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("tele2") || command[0].equalsIgnoreCase("goku")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2867, 2862, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("PREPARE FOR GOKU!!!!.");

        }

        if (command[0].equalsIgnoreCase("tele3") || command[0].equalsIgnoreCase("yeltele")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2827, 2867, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("vboss") || command[0].equalsIgnoreCase("voteboss")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2980, 2771, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("guard") || command[0].equalsIgnoreCase("guardian")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2997, 2761, 1);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }
        if (command[0].equalsIgnoreCase("hanto")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2849, 4571, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }

        if (command[0].equalsIgnoreCase("eternal")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2075, 3230, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to Eternal Dragon!");
        }
        if (command[0].equalsIgnoreCase("funpk")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2815, 5511, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("welcome to funpk - custom weapons are disabled!");
        }
        if (command[0].equalsIgnoreCase("afk")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2654, 3986, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("<shad=1>@gre@Welcome to the afk zone!");
        }
        if (command[0].equalsIgnoreCase("skilling")) {
            player.getPacketSender().sendMessage("<shad=1>@gre@Go to the skills tab, click at any skill to get teleported!");
        }
        if (command[0].equalsIgnoreCase("melee")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2398, 2847, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("afk grind!");
        }
        if (command[0].equalsIgnoreCase("range")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2399, 2914, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("afk grind!");
        }
        if (command[0].equalsIgnoreCase("mage")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2465, 2913, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            // player.getPacketSender().sendMessage("afk grind!");
        }

        if (command[0].equalsIgnoreCase("edge")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.EDGE_CORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            ;
            player.getPacketSender().sendMessage("Welcome to Edgeville.");
        }
        if (command[0].equalsIgnoreCase("chill") || command[0].equalsIgnoreCase("trade") || command[0].equalsIgnoreCase("ge")
                || command[0].equalsIgnoreCase("grandexchange") || command[0].equalsIgnoreCase("market")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.TRADE_CORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to the trading area!");
        }
        if ((command[0].equalsIgnoreCase("shop") && !player.getRights().isHighStaff())
                || command[0].equalsIgnoreCase("shops")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2656, 4021, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to our shops!");
        }
      /*  if ((command[0].equalsIgnoreCase("oldshop") && !player.getRights().OwnerDeveloperOnly())
                || command[0].equalsIgnoreCase("oldshops")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3690, 2977, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to our shops!");
        }*/
       /* if (command[0].equalsIgnoreCase("chill")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                   ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = GameSettings.CHILL_CORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to <col=#002AF8>Chill!");
        }*/
        if (command[0].equalsIgnoreCase("help")) {
            if (player.getLastYell().elapsed(30000)) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                ) {
                    World.sendStaffMessage("<col=FF0066><img=5> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
                            + " has requested help, but is @red@*IN LEVEL " + player.getWildernessLevel()
                            + " WILDERNESS*<col=6600FF>. Be careful.");
                }
                if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
                    World.sendStaffMessage("<col=FF0066><img=5> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
                            + " has requested help, but is @red@*MUTED*<col=6600FF>. Be careful.");
                } else {
                    World.sendStaffMessage("<col=FF0066><img=5> [TICKET SYSTEM]<col=6600FF> " + player.getUsername()
                            + " has requested help. Please help them!");
                }
                player.getLastYell().reset();
                player.getPacketSender()
                        .sendMessage("<col=663300>Your help request has been received. Please be patient.");
            } else {
                player.getPacketSender().sendMessage("<col=663300>You need to wait 30 seconds before using this again.")
                        .sendMessage(
                                "<col=663300>If it's an emergency, please private message a staff member directly instead.");
            }
        }
        if (command[0].equalsIgnoreCase("dissolveall")) {
            //DialogueManager.editOptions(522, 1, "Dissolve all dissolveable items for "
            //          + player.getDissolving().getTotalOrbAmount(player.getDissolving().getDissolvableItemsInInv())
            //         + " Tokens");
            player.setDialogueActionId(522);
            DialogueManager.start(player, 522);
            return;
        }
        if (command[0].equalsIgnoreCase("empty")) {
            //   DialogueManager.editOptions(523, 1, "Are you sure you would like to empty your items?");
            player.setDialogueActionId(523);
            DialogueManager.start(player, 523);
            return;
        }
        if (command[0].equalsIgnoreCase("globals")) {
            player.sendMessage("<shad=1>@yel@<img=31>Goku will spawn after: " + GokuSystem.npckills + "/10K kills");
            player.sendMessage("<shad=1>@yel@<img=31>Earthquake will spawn after: " + AfkStallSystem.getLeft() + " Thieves");
            player.sendMessage("<shad=1>@yel@<img=31>Zamasu will spawn in: " + Zamasu.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>Oozau will spawn in: " + GoldenOozau.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>Prime will spawn in: " + Prime.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>@or2@Onyx Panther@yel@ will spawn in: " + OnyxSpawn.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>@or2@Iron@yel@ will spawn in: " + Iron.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>@or2@Zenyte@yel@ will spawn in: " + ZenyteSpawn.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>@or2@Tazanite@yel@ will spawn in: " + TanzaniteSpawn.getTimeLeft());
            player.sendMessage("<shad=1>@yel@<img=31>@or2@Alchemical Hydra@yel@ will spawn in: " + HydraSpawn.getTimeLeft());
        }
        if (command[0].equalsIgnoreCase("teleport")) {
            player.getCustomTeleportInterface().open();
        }
        if (command[0].equalsIgnoreCase("perks")) {
            ServerPerks.getInstance().open(player);
        }
        if (command[0].equalsIgnoreCase("upgrade")) {
            player.getUpgradeListener().openTab();

        }
        if (command[0].equalsIgnoreCase("players")) {
            int players = (int) World.getPlayers().size() + GameSettings.players;
            player.getPacketSender().sendMessage(
                    "<shad=1>@or1@There are currently @whi@[ @gre@" + (players) + "@whi@ ] @or1@players online!");
        }

        if (command[0].equalsIgnoreCase("droplog")) {
            player.getPacketSender().sendInterfaceRemoval();
            DropLog.open(player);
        }
        if (command[0].equalsIgnoreCase("npclist")) {
            player.getPacketSender().sendInterface(28002);
        }
        if (command[0].equalsIgnoreCase("zones")) {
            player.getPacketSender().sendInterface(28002);


        }
        if (command[0].equalsIgnoreCase("fly")) {
            if (player.canFly() == false) {
                player.getPacketSender().sendMessage("You do not understand the complexities of flight.");
                return;
            }
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot fly in the Wilderness.");
                return;
            }
            if (player.canFly() && player.isFlying()) {
                player.getPacketSender().sendMessage("You stop flying.");
                player.setFlying(false);
                player.newStance();
                return;
            }
            if (player.canFly() && player.isFlying() == false) {
                player.getPacketSender().sendMessage("You begin flying.");
                player.setFlying(true);
                player.newStance();
                return;
            }
            return;
        }
        if (command[0].equalsIgnoreCase("ghostwalk") || command[0].equalsIgnoreCase("ghost")) {
            if (player.canGhostWalk() == false) {
                player.getPacketSender().sendMessage("You do not understand the complexities of death.");
                return;
            }
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot ghost walk in the Wilderness.");
                return;
            }
            if (player.canGhostWalk() && player.isGhostWalking()) {
                player.getPacketSender().sendMessage("You stop ghost-walking.");
                player.setGhostWalking(false);
                player.newStance();
                return;
            }
            if (player.canGhostWalk() && player.isGhostWalking() == false) {
                player.getPacketSender().sendMessage("You begin ghost walking.");
                player.setGhostWalking(true);
                player.newStance();
                return;
            }
            return;
        }
        if (command[0].equalsIgnoreCase("[cn]")) {
            if (player.getInterfaceId() == 40172) {
                ClanChatManager.setName(player, wholeCommand.substring(wholeCommand.indexOf(" ") + 1));
            }
        }


        for (String cmd : AddedCommands.COMMANDS.keySet()) {
            if (wholeCommand.equalsIgnoreCase(cmd)) {
                player.getPacketSender().sendString(1, "https://www.youtube.com/" + AddedCommands.COMMANDS.get(cmd));
                player.getPacketSender().sendMessage("Attempting to open the link");
            }
        }

    }

    public static void resetInterface(Player player) {
        for (int i = 8145; i < 8196; i++)
            player.getPacketSender().sendString(i, "");
        for (int i = 12174; i < 12224; i++)
            player.getPacketSender().sendString(i, "");
        player.getPacketSender().sendString(8136, "Close window");
    }

    private static void memberCommands(final Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("setyelltitle")) {
            try {
                String target = wholeCommand.substring(command[0].length() + 1).toLowerCase();
                if (target.length() > 6 || target.contains("mod") || target.contains("staff")
                        || target.contains("owner") || target.contains("developer") || target.contains("support")
                        || target.contains("m0d")) {
                    player.getPacketSender().sendMessage("Bad title. Max length is 6, title must be appropriate.");
                    return;
                } else {
                    player.setYellTitle(Misc.capitalizeString(target));
                    player.getPacketSender()
                            .sendMessage("Your yell title has been set to " + Misc.capitalizeString(target) + ".");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (wholeCommand.toLowerCase().startsWith("yell")) {
            if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
                player.getPacketSender().sendMessage("You are muted and cannot yell.");
                return;
            }
            if (Jail.isJailed(player.getUsername())) {
                player.getPacketSender().sendMessage("You can yell as soon as you're out of jail.");
                return;
            }
            int delay = 1000;
            if (!player.getLastYell().elapsed((delay))) {
                player.getPacketSender()
                        .sendMessage("You must wait at least 1 second between every yell-message you send.");
                return;
            }
            String yellMessage = wholeCommand.substring(5, wholeCommand.length());
            String formatYell = yellMessage.substring(0, 1).toUpperCase() + yellMessage.substring(1).toLowerCase();
            if (Misc.blockedWord(yellMessage) && !(player.getRights().isHighStaff())) {
                DialogueManager.sendStatement(player, "A word was blocked in your sentence. Please do not repeat it!");
                return;
            } else {

                String rights;
                if (player.getYellTitle() != null && !player.getYellTitle().equalsIgnoreCase("null")
                        && !player.getYellTitle().equalsIgnoreCase("")) {
                    rights = player.getYellTitle().replace("_donator", "");
                } else {
                    rights = player.getRights().toString().substring(0, 1).toUpperCase()
                            + player.getRights().toString().substring(1).toLowerCase().replace("_donator", "");
                }
                int rightsIcon = player.getRights() == PlayerRights.MANAGER ? 1541
                        : player.getRights() == PlayerRights.ZENYTE_DONATOR ? 1508
                        : player.getRights() == PlayerRights.TANZANITE_DONATOR ? 1602
                        : player.getRights() == PlayerRights.HYDRIX_DONATOR ? 1658 : player.getRights().ordinal();

                PlayerLogs.logSomething(player.getUsername(), "yell", formatYell);


                if (player.getRights().isStaff() && player.getAmountDonated() >= 10 && player.getAmountDonated() < 50) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=6> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (player.getRights().isStaff() && player.getAmountDonated() >= 50
                        && player.getAmountDonated() < 250) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=7> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (player.getRights().isStaff() && player.getAmountDonated() >= 250
                        && player.getAmountDonated() < 500) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=8> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (player.getRights().isStaff() && player.getAmountDonated() >= 500
                        && player.getAmountDonated() < 1000) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=9> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (player.getRights().isStaff() && player.getAmountDonated() >= 1000
                        && player.getAmountDonated() < 2500) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=3> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (!player.getRights().isStaff() && player.getAmountDonated() >= 1000) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": " + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                if (player.getRights().isStaff() && player.getAmountDonated() >= 10 && player.getAmountDonated() < 50) {
                    World.sendMessage("<shad=0>" + player.getRights().getYellPrefix() + "[" + rights
                            + "] <img=" + player.getGameModeIconId() + "> <img=" + rightsIcon
                            + "><img=6> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": "
                            + formatYell);

                    PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                    player.getLastYell().reset();
                    return;
                }
                String intro = "" + player.getRights().getYellPrefix() + "[" + rights + "] <img="
                        + player.getGameModeIconId() + "> <img=" + rightsIcon + "> <shad><col="
                        + player.getYellHex() + ">" + player.getUsername() + ": ";
                String message = formatYell;

                int[] info = Misc.getTextWidth(intro + message);
                int[] info1 = Misc.getTextWidth(message);

                if (info[0] >= 710) {
                    World.sendMessage(intro + message.substring(0, info1[1] * 2 / 3));
                    World.sendMessage(intro + message.substring(info1[1] * 2 / 3));
                } else {
                    World.sendMessage(intro + message);
                }


                PlayerLogs.log(player.getUsername(), player.getUsername() + " yelled: " + formatYell);
                player.getLastYell().reset();
            }
        }
        if (wholeCommand.equalsIgnoreCase("bank") && player.getAmountDonated() >= 50) {
            if (player.getInterfaceId() > 0) {
                player.getPacketSender()
                        .sendMessage("Please close the interface you have open before opening another one.");
                return;
            }
            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.JAIL

                    || player.getLocation() == Location.DUEL_ARENA) {
                player.getPacketSender().sendMessage("You cannot open your bank here.");
                return;
            }
            player.getBank(player.getCurrentBankTab()).open();
        }
        if (wholeCommand.equalsIgnoreCase("gbank") && player.getAmountDonated() >= 50) {
            if (player.getInterfaceId() > 0) {
                player.getPacketSender()
                        .sendMessage("Please close the interface you have open before opening another one.");
                return;
            }
            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING || player.getLocation() == Location.JAIL

                    || player.getLocation() == Location.DUEL_ARENA) {
                player.getPacketSender().sendMessage("You cannot open your bank here.");
                return;
            }
            if (player.getGameMode() == GameMode.GROUP_IRONMAN
                    && player.getIronmanGroup() != null) {
                player.getIronmanGroup().getBank().open(player);
            }
        }


        if (command[0].equalsIgnoreCase("decant")) {
            player.getPacketSender().sendMessage("A magic power decants your potions!");
            Decanting.notedDecanting(player);
        }
    }

    private static void youtuberCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("master2")) {
            for (Skill skill : Skill.values()) {
                int level = SkillManager.getMaxAchievingLevel(skill);
                player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                        SkillManager.getExperienceForLevel(level == 120 ? 120 : 99));
            }
            player.getPacketSender().sendMessage("You are now a master of all skills.");
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }

        if (command[0].equalsIgnoreCase("eventiron")) {
            Iron.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventzenyte")) {
            ZenyteSpawn.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventtanz")) {
            TanzaniteSpawn.spawn();
        }
        if (command[0].equalsIgnoreCase("eventprime")) {
            Prime.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventgoku")) {
            GokuSystem.spawn();
        }
        if (command[0].equalsIgnoreCase("eventmerk")) {
            MerkSpawn.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventexoden")) {
            GoldenOozau.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventgarfield")) {
            AfkStallSystem.spawncommand();
        }
        if (command[0].equalsIgnoreCase("eventhulk")) {
            Zamasu.spawncommand();
        }
        if (command[0].equalsIgnoreCase("streamevent")) {
            MerkSpawn.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnsam")) {
            // UncleSamSpawn.spawn();
        }
        if (command[0].equalsIgnoreCase("livewr3cked")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@Wr3ckedyou@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::wr3cked");
        }
        if (command[0].equalsIgnoreCase("livefrimb")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@Frimb@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::frimb");
        }
        if (command[0].equalsIgnoreCase("livestable")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@Stable@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::stable");
        }
        if (command[0].equalsIgnoreCase("livemorgen")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@Morgen@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::morgen");
        }
        if (command[0].equalsIgnoreCase("liveswiper")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@EffigySwiper@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::effigy");
        }
        if (command[0].equalsIgnoreCase("fpklive")) {
            World.sendMessage(
                    "<shad=1>@red@[LIVESTREAM] @bla@FPK Merk@yel@ is now going @bla@LIVE@yel@ on @bla@Lunite@yel@ Tune in now ::fpk");
        }
    }

    private static void contributorCommands(final Player player, String[] command, String wholeCommand) {
        if (wholeCommand.equalsIgnoreCase("hexcodes") || wholeCommand.equalsIgnoreCase("hex")) {
            player.getPacketSender().sendString(1, GameSettings.HexUrl);
            player.getPacketSender().sendMessage("Attempting to open the Hex page..");
        }
        if (command[0].equalsIgnoreCase("getyellhex")) {
            player.getPacketSender().sendMessage(
                    "Your current yell hex is: <shad=0><col=" + player.getYellHex() + ">#" + player.getYellHex());
            return;
        }
        if (command[0].equalsIgnoreCase("setyellhex")) {
            String hex = command[1].replaceAll("#", "");
            player.setYellHex(hex);
            player.getPacketSender().sendMessage("You have set your hex color to: <shad=0><col=" + hex + ">#" + hex);
            if (player.getYellHex() == null)
                player.getPacketSender().sendMessage("There was an error setting your yell hex. You entered: " + hex);
            return;
        }
    }

    private static void helperCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("afkcheck")) {
            String plrName = wholeCommand
                    .substring(command[0].length() + 1);
            Player target = World.getPlayerByName(plrName);
            if (target == null) {
                player.getPacketSender().sendMessage(plrName + " must be online to afk check!");
            } else {
                player.getPacketSender().sendMessage(
                        "Afk Checked " + plrName);
                target.getAfkCombatChecker().openQuestions();
            }
        }

        if (command[0].equalsIgnoreCase("id")) {
            String name = wholeCommand.substring(3).toLowerCase().replaceAll("_", " ");
            player.getPacketSender().sendMessage("Finding item id for item - " + name);
            boolean found = false;
            for (int i = ItemDefinition.getMaxAmountOfItems() - 1; i > 0; i--) {
                if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
                    player.getPacketSender().sendMessage("Found item with name ["
                            + ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
                    found = true;
                }
            }
            if (!found) {
                player.getPacketSender().sendMessage("No item with name [" + name + "] has been found!");
            }
        }

        if (command[0].equalsIgnoreCase("da")) {
            int item = Integer.parseInt(command[1]);
            player.getPacketSender().sendInterfaceItemModel(25357, item);
            player.getPacketSender().sendInterface(25355);
        }
        if (command[0].equalsIgnoreCase("dn")) {
            int item = Integer.parseInt(command[1]);
            int zoom = Integer.parseInt(command[2]);
            player.getPacketSender().sendNpcOnInterface(25352, item, zoom);
            player.getPacketSender().sendInterface(25350);
        }
        if (command[0].equalsIgnoreCase("crd")) {
            int item = Integer.parseInt(command[1]);
            player.getPacketSender().sendInterfaceItemModel(25358, item);
            player.getPacketSender().sendInterface(141500);
        }


        if (command[0].equalsIgnoreCase("kickgi")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target == null) {
                player.getPacketSender().sendMessage("Player must be online to kick them from their group!");
            } else {
                player.getPacketSender().sendMessage("Kicked " + target.getUsername() + " from their ironman group.");
                if (target.getIronmanGroup() != null) {
                    target.getIronmanGroup().kickPlayer(player, target.getUsername());
                } else {
                    player.getPacketSender().sendMessage("Player is not in a ironman group!");
                }
            }
        }

        if (command[0].equalsIgnoreCase("kickgi2")) {
            Player target = World.getPlayerByName("Gim Martzz");
            Player target2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target == null) {
                player.getPacketSender().sendMessage("Player must be online to kick them from their group!");
            } else {
                player.getPacketSender().sendMessage("Kicked " + target2.getUsername() + " from their ironman group.");
                if (target.getIronmanGroup() != null) {
                    target.getIronmanGroup().kickPlayer(player, target2.getUsername());
                } else {
                    player.getPacketSender().sendMessage("Player is not in a ironman group!");
                }
            }
        }

        if (command[0].equalsIgnoreCase("setgiid")) {
            int offset = Integer.parseInt(command[1]);
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + command[2].length() + 2));
            if (target == null) {
                player.getPacketSender().sendMessage("Player must be online to kick them from their group!");
            } else {
                player.getPacketSender().sendMessage("changed " + target.getUsername() + " group id to " + offset + ".");
                IronmanGroup group = GroupManager.getGroup(offset);
                if (group != null) {
                    if (group.getMembers().contains(target.getUsername()))
                        target.setIronmanGroup(group);
                }
            }
        }

        if (command[0].equalsIgnoreCase("cip")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target != null) {
                player.sendMessage("Searching...");

                for (Player plr : World.getPlayers()) {
                    if (plr != null) {
                        if (plr.getHostAddress().equals(target.getHostAddress()) && !plr.equals(target)
                                && !plr.getUsername().equalsIgnoreCase("nucky")
                                && !target.getUsername().equalsIgnoreCase("nucky")) {
                            player.sendMessage(
                                    plr.getUsername() + " has the same Ip address as " + target.getUsername() + " - " + plr.getGameMode().name());
                        }
                    }
                }

                player.sendMessage("Done search");
            } else {
                player.sendMessage(wholeCommand.substring(command[0].length() + 1) + " is not valid");
            }
        }
        if (command[0].equalsIgnoreCase("cpc")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target != null) {
                player.sendMessage("Searching...");

                for (Player plr : World.getPlayers()) {
                    if (plr != null) {
                        if (plr.getSerialNumber().equals(target.getSerialNumber()) && !plr.equals(target)
                                && !plr.getUsername().equalsIgnoreCase("nucky")
                                && !target.getUsername().equalsIgnoreCase("nucky")
                                && !plr.getUsername().equalsIgnoreCase("test")
                                && !target.getUsername().equalsIgnoreCase("test")) {
                            player.sendMessage(
                                    plr.getUsername() + " has the same Serial Number as " + target.getUsername() + " - " + plr.getGameMode().name());
                        }
                    }
                }

                player.sendMessage("Done search");
            } else {
                player.sendMessage(wholeCommand.substring(command[0].length() + 1) + " is not valid");
            }
        }
        if (command[0].equalsIgnoreCase("cmac")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target != null) {
                player.sendMessage("Searching...");

                for (Player plr : World.getPlayers()) {
                    if (plr != null) {
                        if (plr.getMac().equals(target.getMac()) && !plr.equals(target)
                                && !plr.getUsername().equalsIgnoreCase("nucky")
                                && !target.getUsername().equalsIgnoreCase("nucky")
                                && !plr.getUsername().equalsIgnoreCase("test")
                                && !target.getUsername().equalsIgnoreCase("test")) {
                            player.sendMessage(
                                    plr.getUsername() + " has the same MAC ID as " + target.getUsername() + " - " + plr.getGameMode().name());
                        }
                    }
                }

                player.sendMessage("Done search");
            } else {
                player.sendMessage(wholeCommand.substring(command[0].length() + 1) + " is not valid");
            }
        }
        if (command[0].equalsIgnoreCase("allcc")) {
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    ClanChatManager.join(plr, "Help");
                }
            }
            player.sendMessage("Put all in cc");
        }

        if (command[0].equalsIgnoreCase("fixloc")) {
            Locations.locationPopulations.clear();
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    if (Locations.locationPopulations.containsKey(plr.getLocation()))
                        Locations.locationPopulations.put(plr.getLocation(), Locations.locationPopulations.get(plr.getLocation()) + 1);
                    else
                        Locations.locationPopulations.put(plr.getLocation(), 0);
                }
            }
            player.sendMessage("Put all in lcoation");
        }


        if (command[0].equalsIgnoreCase("getlocc")) {
            int amount = 0;
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    if (plr.getLocation() == player.getLocation())
                        amount++;
                }
            }
            player.sendMessage("amount " + amount);
        }

        if (command[0].equalsIgnoreCase("locs")) {

            HashMap<Location, Integer> objects = new HashMap<>();

            player.sendMessage("Searching...");
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    if (objects.containsKey(plr.getLocation())) {
                        objects.put(plr.getLocation(), objects.get(plr.getLocation()) + 1);
                    } else {
                        objects.put(plr.getLocation(), 1);
                    }
                }

            }
            List<Integer> locations = new ArrayList<>(objects.values());

            Collections.sort(locations, (o1, o2) -> o2 - o1);

            int offset = 0;

            for (Location d : objects.keySet()) {
                final int offset1 = offset;
                player.sendMessage(d + " " + Misc.getKeysByValue(objects, locations.get(offset1)) + " : " + objects.get(d));
                offset++;
            }

            player.sendMessage("Done search");
        }


        if (command[0].equalsIgnoreCase("movealltome")) {
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    System.out.println("LOCATION: " + plr.getPosition().toString());
                    plr.moveTo(player.getPosition());
                }

            }
        }

        if (command[0].equalsIgnoreCase("rt")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Slayer Task Reset.");
            player.getPacketSender().sendMessage("Slayer Task Reset.");
            player2.getSlayer().setSlayerTask(SlayerTasks.NO_TASK).setAmountToSlay(0);
        }

        if (command[0].equalsIgnoreCase("isinwild") || command[0].equalsIgnoreCase("checkwild")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick != null) {
                if (playerToKick.getLocation() != null && playerToKick.getLocation() != Location.WILDERNESS
                ) {
                    player.getPacketSender()
                            .sendMessage(playerToKick.getUsername() + " @gre@is *NOT* in the Wilderness.");
                } else {
                    player.getPacketSender().sendMessage(playerToKick.getUsername() + " @red@is *IN LEVEL "
                            + playerToKick.getWildernessLevel() + " WILDERNESS*");
                }

            } else {
                player.getPacketSender().sendMessage("Could not find \"" + player2 + "\".");
            }
        }
        if (command[0].equalsIgnoreCase("unmute")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            if (player2 == null) {
                player.getPacketSender().sendMessage("Player " + player2 + " does not exist.");
                return;
            } else {
                if (!PlayerPunishment.muted(player2)) {
                    player.getPacketSender().sendMessage("Player " + player2 + " is not muted!");
                    return;
                }
                PlayerLogs.log(player.getUsername(), player.getUsername() + " just unmuted " + player2 + "!");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just unmuted " + player2);
                PlayerPunishment.unmute(player2);
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " was successfully unmuted. Command logs written.");
                Player plr = World.getPlayerByName(player2);
                if (plr != null) {
                    plr.getPacketSender().sendMessage("You have been unmuted by " + player.getUsername() + ".");
                }
            }
        }
        if (command[0].equalsIgnoreCase("kick")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick == null) {
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " couldn't be found on " + GameSettings.RSPS_NAME + ".");
                return;
            } else if (playerToKick.getDueling().duelingStatus < 5) {
                PlayerHandler.handleLogout(playerToKick, true);
                player.getPacketSender().sendMessage("Kicked " + playerToKick.getUsername() + ".");
                PlayerLogs.log(player.getUsername(),
                        player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just kicked " + playerToKick.getUsername() + ".");
                player.getPacketSender().sendMessage("You can try ::kick2 if this command didn't work.");
            } else {
                PlayerLogs.log(player.getUsername(), player.getUsername() + " just tried to kick "
                        + playerToKick.getUsername() + " in an active duel.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just tried to kick " + playerToKick.getUsername() + " in an active duel.");
                player.getPacketSender().sendMessage("You've tried to kick someone in duel arena/wild. Logs written.");
            }
        }
        if (command[0].equalsIgnoreCase("kick2")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick == null) {
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " couldn't be found on " + GameSettings.RSPS_NAME + ".");
                return;
            } else if (playerToKick.getDueling().duelingStatus < 5) {
                World.getPlayers().remove(playerToKick);
                player.getPacketSender().sendMessage("Kicked " + playerToKick.getUsername() + ".");
                PlayerLogs.log(player.getUsername(),
                        player.getUsername() + " just kicked " + playerToKick.getUsername() + "!");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just kicked " + playerToKick.getUsername() + ".");
            } else {
                PlayerLogs.log(player.getUsername(), player.getUsername() + " just tried to kick "
                        + playerToKick.getUsername() + " in an active duel.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just tried to kick " + playerToKick.getUsername() + " in an active duel.");
                player.getPacketSender().sendMessage("You've tried to kick someone in duel arena/wild. Logs written.");
            }
        }


        if (command[0].equalsIgnoreCase("kick3")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);

            player.sendMessage("player2: " + player2);
            for (Player target : World.getPlayers()) {
                if (target != null) {
                    if (target.getUsername().equalsIgnoreCase(player2)) {
                        World.getPlayers().remove(target);
                        target.getPacketSender().sendLogout();
                        player.getPacketSender().sendMessage("Kicked " + target.getUsername() + ".");
                        PlayerLogs.log(player.getUsername(),
                                player.getUsername() + " just kicked " + target.getUsername() + "!");
                        World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                                + " just kicked " + target.getUsername() + ".");
                    }
                }
            }
        }
        if (command[0].equalsIgnoreCase("testloyalty")) {
            player.lastVoted = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
        }
        if (command[0].equalsIgnoreCase("openpoll")) {
            PollManager.openInterface(player);
        }
        if (command[0].equalsIgnoreCase("pollcreation")) {
            PollCreation.openInterface(player);
        }
        if (command[0].equalsIgnoreCase("bumpstreak")) {
            player.currentVotingStreak++;
            player.lastVoted = new SimpleDateFormat("dd/MM/yyyy").format(Calendar.getInstance().getTime());
            player.timeUntilNextReward = System.currentTimeMillis() - LoyaltyStreakManager.MILLIS_TO_DAY;
        }
        if (command[0].equalsIgnoreCase("lotteryentrycost")) {
            GrandLottery.setEntryCost(player, 1);
        }
        if (command[0].equalsIgnoreCase("endround")) {
            GrandLottery.endRound();
        }
        if (command[0].equalsIgnoreCase("setprizes")) {
            GrandLottery.set1stPrize(player, 1, 1);
            GrandLottery.set2ndPrize(player, 1, 1);
            GrandLottery.set3rdPrize(player, 1, 1);
            GrandLottery.setStaticPrize(player, 2, 1);
            GrandLottery.setSpecialPrize(player, 2, 1, 1);
            GrandLottery.addTime(player, 1);

        }
        if (command[0].equalsIgnoreCase("initiate")) {
            new BossEventHandler().Execute();
        }
        if (command[0].equalsIgnoreCase("resetboss")) {
            new BossEventHandler().forceReset(player);
        }
        if (command[0].equalsIgnoreCase("mute")) {
            try {
                String target = wholeCommand.substring(command[0].length() + 1);
                if (World.getPlayerByName(target) == null) {
                    String fileName = Misc.formatText(target.toLowerCase());
                    File file = new File("./data/saves/characters/" + fileName + ".json");
                    if (file.exists()) {
                        if (PlayerPunishment.muted(target)) {
                            player.getPacketSender().sendMessage("Player: " + target + " already has an active mute.");
                            return;
                        }
                        PlayerLogs.log(player.getUsername(), player.getUsername() + " just muted " + target + "!");
                        World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                                + " just muted " + target);
                        PlayerPunishment.mute(target);
                        player.getPacketSender().sendMessage("Player " + target + " was successfully muted");// for
                        Player plr = World.getPlayerByName(target);
                        if (plr != null) {
                            plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + "."); // for
                        }
                    } else {
                        player.getPacketSender().sendMessage(fileName + " does not exist in my files, " +
                                "maybe you typed it wrong!");
                    }
                    return;
                } else {
                    if (PlayerPunishment.muted(target)) {
                        player.getPacketSender().sendMessage("Player: " + target + " already has an active mute.");
                        return;
                    }
                    PlayerLogs.log(player.getUsername(), player.getUsername() + " just muted " + target + "!");
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just muted " + target);
                    PlayerPunishment.mute(target);/* , GameSettings.Temp_Mute_Time); */
                    player.getPacketSender().sendMessage("Player " + target + " was successfully muted");// for
                    Player plr = World.getPlayerByName(target);
                    if (plr != null) {
                        plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + "."); // for
                    }
                }
            } catch (Exception e) {
                player.getPacketSender().sendMessage("The player must be online.");
            }
        }
        if (command[0].equalsIgnoreCase("movehome")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToMove = World.getPlayerByName(player2);
            if (playerToMove != null && playerToMove.getDueling().duelingStatus < 5) {
                if (playerToMove.getLocation() == Location.WILDERNESS) {
                    PlayerLogs.log(player.getUsername(), "Just moved " + playerToMove.getUsername()
                            + " to home from level " + playerToMove.getWildernessLevel() + " wild.");
                } else {
                    PlayerLogs.log(player.getUsername(), "Just moved " + playerToMove.getUsername() + " to home.");
                }
                playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
                playerToMove.getPacketSender()
                        .sendMessage("You've been teleported home by " + player.getUsername() + ".");
                player.getPacketSender().sendMessage("Sucessfully moved " + playerToMove.getUsername() + " to home.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just moved " + playerToMove.getUsername() + " to home.");
                player.performGraphic(new Graphic(730));
                playerToMove.performGraphic(new Graphic(730));
            } else {
                player.getPacketSender().sendMessage("Could not send \"" + player2 + "\" home. Try kicking first?")
                        .sendMessage("Will also fail if they're in duel/wild.");
            }
        }
        if (command[0].equalsIgnoreCase("permmute")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            if (player2 == null) {
                player.getPacketSender().sendMessage("Player " + player2 + " does not exist.");
                return;
            } else {
                if (PlayerPunishment.muted(player2)) {
                    player.getPacketSender().sendMessage("Player " + player2 + " already has an active mute.");
                    return;
                }
                PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just perm muted " + player2 + "!");
                PlayerPunishment.mute(player2);
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " was successfully muted. Command logs written.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just perm muted " + player2);
                Player plr = World.getPlayerByName(player2);
                if (plr != null) {
                    plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + ".");
                }
            }
        }

        if (command[0].equalsIgnoreCase("tempmute")) {
            String targ = wholeCommand.substring(command[0].length() + 1);
            Player target = World.getPlayerByName(targ);
            if (target == null) {
                player.getPacketSender().sendMessage("Could not find player \"" + targ + "\".");
                return;
            }
            if (PlayerPunishment.muted(targ)) {
                player.getPacketSender().sendMessage(targ + " is already muted.");
                return;
            }
            PlayerPunishment.tempMute(targ);

            // if (command[2])
        }
        if (wholeCommand.toLowerCase().startsWith("sc")) {
            String yellMessage = wholeCommand.substring(3, wholeCommand.length());
            String formatYell = yellMessage.substring(0, 1).toUpperCase() + yellMessage.substring(1).toLowerCase();
            String rights = player.getRights().toString();
            String rank = rights.substring(0, 1).toUpperCase() + rights.substring(1).toLowerCase();
            // World.sendMessage("<shad=0>"+player.getRights().getYellPrefix()+"["+rank+"]<img="+player.getGameModeIconId()+"><img="+player.getRights().ordinal()+">
            // <shad=0><col="+player.getYellHex()+">"+player.getUsername()+": "+formatYell);
            int rightsIcon = player.getRights() == PlayerRights.MANAGER ? 1541 : player.getRights() == PlayerRights.ZENYTE_DONATOR ? 1508
                    : player.getRights() == PlayerRights.TANZANITE_DONATOR ? 1602
                    : player.getRights() == PlayerRights.HYDRIX_DONATOR ? 1658  : player.getRights().ordinal();

            World.sendStaffMessage("<shad=F200CE>@red@[SC]<shad=0>" + player.getRights().getYellPrefix() + " <img=" + player.getGameModeIconId() + "><img=" + rightsIcon
                    + "> <shad=0><col=" + player.getYellHex() + ">" + player.getUsername() + ": " + formatYell);
            PlayerLogs.log(player.getUsername(), player.getUsername() + " SC said: " + formatYell);
        }
        if (command[0].equalsIgnoreCase("newjail")) {
            TeleportHandler.teleportPlayer(player, new Position(2519, 9320), TeleportType.NORMAL);
        }
        if (command[0].equalsIgnoreCase("jail")) {
            Player player2 = World
                    .getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 != null) {
                if (Jail.isJailed(player2.getUsername())) {
                    player.getPacketSender().sendMessage("That player is already jailed!");
                    return;
                }
                if (Jail.jailPlayer(player2.getUsername()) && player2.getDueling().duelingStatus == 0) {
                    player2.getSkillManager().stopSkilling();
                    PlayerLogs.log(player.getUsername(),
                            player.getUsername() + " just jailed " + player2.getUsername() + "!");
                    player.getPacketSender().sendMessage("Jailed player: " + player2.getUsername());
                    player2.getPacketSender().sendMessage("You have been jailed by " + player.getUsername() + ".");
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just jailed " + player2.getUsername());
                    player2.performAnimation(new Animation(1994));
                    player.performGraphic(new Graphic(730));
                    player2.moveTo(new Position(2510, 9326));
                    player2.setLocation(Location.JAIL);
                } else {
                    if (player2.getDueling().duelingStatus > 0) {
                        player.getPacketSender().sendMessage(
                                "That player is currently in a stake. Please wait before jailing them, or kick then jail.");
                        return;
                    } else {
                        player.getPacketSender().sendMessage("This shouldn't happen, message Crimson. Error: JAIL45");
                    }
                }
            } else {
                String target = wholeCommand.substring(command[0].length() + 1);
                String fileName = Misc.formatText(target.toLowerCase());
                File file = new File("./data/saves/characters/" + fileName + ".json");
                if (file.exists()) {
                    if (Jail.isJailed(target)) {
                        player.getPacketSender().sendMessage("Player: " + target + " is already jailed.");
                        return;
                    }
                    PlayerLogs.log(player.getUsername(), player.getUsername() + " just muted " + target + "!");
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just muted " + target);
                    PlayerPunishment.mute(target);
                    player.getPacketSender().sendMessage("Player " + target + " was successfully muted");// for
                    Player plr = World.getPlayerByName(target);
                    if (plr != null) {
                        plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + "."); // for
                    }
                } else {
                    player.getPacketSender().sendMessage(fileName + " does not exist in my files, " +
                            "maybe you typed it wrong!");
                }
                player.getPacketSender().sendMessage("Could not find that player online.");
            }
        }
        if (command[0].equalsIgnoreCase("remindvote")) {
            World.sendMessage(
                    "<img=5> <col=008FB2><shad=0>Remember to collect rewards by using the ::vote command every 12 hours!");
        }
        if (command[0].equalsIgnoreCase("remindafk")) {
            World.sendMessage(
                    "<img=5> <col=008FB2><shad=0>Remember! You can put up to an account in the ::Afk zone! Get yourself some free items!");
        }
        if (command[0].equalsIgnoreCase("reminddonate")) {
            World.sendMessage("<img=5> <col=008FB2><shad=0>Remember to check out the donation deals in our ::discord!");
        }
        if (command[0].equalsIgnoreCase("remindhelp")) {
            World.sendMessage(
                    "<img=5> <col=008FB2><shad=0>Staff members are always available, pm them if you need help!");
        }
        if (command[0].equalsIgnoreCase("unjail")) {
            Player player2 = World
                    .getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 != null) {
                if (Jail.unJail(player2.getUsername())) {
                    PlayerLogs.log(player.getUsername(),
                            "" + player.getUsername() + " just unjailed " + player2.getUsername() + "!");
                    player.getPacketSender().sendMessage("Unjailed player: " + player2.getUsername() + "");
                    player2.getPacketSender().sendMessage("You have been unjailed by " + player.getUsername() + ".");
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just unjailed " + player2.getUsername());
                    player2.performAnimation(new Animation(1993));
                    player.performGraphic(new Graphic(730));
                }
            } else {
                player.getPacketSender().sendMessage("Could not find \""
                        + wholeCommand.substring(command[0].length() + 1, wholeCommand.length()) + "\" online.");
            }
        }
        if (command[0].equalsIgnoreCase("staffzone")) {
            if (command.length > 1 && command[1].equalsIgnoreCase("all") && player.getRights().isHighStaff()) {
                player.getPacketSender().sendMessage("Teleporting all staff to staffzone.");
                for (Player players : World.getPlayers()) {
                    if (players != null) {
                        if (players.getRights().isStaff()) {
                            TeleportHandler.teleportPlayer(players, new Position(2007, 4439), TeleportType.NORMAL);
                        }
                    }
                }
            } else {
                TeleportHandler.teleportPlayer(player, new Position(2007, 4439), TeleportType.NORMAL);
            }
        }
        if (command[0].equalsIgnoreCase("teleto")) {
            String playerToTele = wholeCommand.substring(command[0].length() + 1);
            Player player2 = World.getPlayerByName(playerToTele);
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            } else {
                boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy());
                if (canTele) {
                    if (player.isVisible())
                        player2.performGraphic(new Graphic(730));

                    TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.NORMAL);
                    player.getPacketSender().sendMessage("Teleporting to player: " + player2.getUsername() + "");
                } else
                    player.getPacketSender()
                            .sendMessage("You can not teleport to this player at the moment. Minigame maybe?");
            }
        }
    }

    private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("instppl")) {

            int total = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    if (p.getLocation() != null && p.getLocation() == Location.INSTANCE2) {
                        player.sendMessage(p.getUsername() + " = "
                                + (p.getCurrentInstanceNpcId() > 0 ?
                                NpcDefinition.forId(p.getCurrentInstanceNpcId()).getName() :
                                "N/A"));
                        total++;
                    }
                }
            }
            player.sendMessage("There is " + total + " people in instances.");
        }


        if (command[0].equalsIgnoreCase("goldppl")) {

            int total = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    if (p.getLocation() != null && p.getLocation() == Location.WAVE_MINIGAME) {
                        player.sendMessage(p.getUsername() + " = " + p.getPosition().toString());
                        total++;
                    }
                }
            }
            player.sendMessage("There is " + total + " people in Gold Grinder.");
        }


        if (command[0].equalsIgnoreCase("thppl")) {

            int total = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    if (p.getLocation() != null && p.getLocation() == Location.TREASURE_HUNTER) {
                        player.sendMessage(p.getUsername() + " = " + p.getPosition().toString());
                        total++;
                    }
                }
            }
            player.sendMessage("There is " + total + " people in Treasure hunter.");
        }

        if (command[0].equalsIgnoreCase("pcppl")) {

            int total = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    if (p.getLocation() != null && p.getLocation() == Location.PEST_CONTROL_GAME) {
                        player.sendMessage(p.getUsername() + " = " + p.getPosition().toString());
                        total++;
                    }
                }
            }
            player.sendMessage("There is " + total + " people in Pest Control.");
        }
        if (command[0].equalsIgnoreCase("sodppl")) {

            int total = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    if (p.getLocation() != null && p.getLocation() == Location.DEATH_SANCTUM) {
                        player.sendMessage(p.getUsername() + " = " + p.getPosition().toString());
                        total++;
                    }
                }
            }
            player.sendMessage("There is " + total + " people in Sanctum of Death.");
        }

        if (command[0].equalsIgnoreCase("broadcast")) {
            int time = Integer.parseInt(command[1]);
            String message = wholeCommand.substring(command[0].length() + command[1].length() + 2);

            World.sendBroadcastMessage(message, time);

            GameSettings.broadcastMessage = message;
            GameSettings.broadcastTime = time;
        }

        if (command[0].equalsIgnoreCase("invisible")) {
            player.setVisible(!player.isVisible());
            player.sendMessage("You are now " + (player.isVisible() ? "visible" : "invisible"));
        }
        if (command[0].equalsIgnoreCase("checkkills")) {
            Player plr = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (plr != null) {
                player.getPacketSender().sendMessage("Loading kills..");
                for (int i = 8145; i < 8196; i++)
                    player.getPacketSender().sendString(i, "");

                player.getPacketSender().sendInterface(8134);

                player.getPacketSender().sendString(8136, "Close window");
                player.getPacketSender().sendString(8144, "Kills for " + plr.getUsername());
                player.getPacketSender().sendString(8145, "");
                int index = 8147;
                String color = "@dre@";
                plr.getKillsTracker().sort((kill1, kill2) -> {
                    if (kill1.boss && !kill2.boss) {
                        return -1;
                    }
                    if (kill2.boss && !kill1.boss) {
                        return 1;
                    }
                    if (kill1.amount > kill2.amount) {
                        return -1;
                    } else if (kill2.amount > kill1.amount) {
                        return 1;
                    } else {
                        if (kill1.npcName.compareTo(kill2.npcName) > 0) {
                            return 1;
                        } else {
                            return -1;
                        }
                    }
                });
                for (KillsTracker.KillsEntry entry : plr.getKillsTracker()) {
                    NpcDefinition def = NpcDefinition.forId(entry.getNpcId());
                    if (def != null) {
                        player.getPacketSender().sendString(index++, color + def.getName() + ": " + entry.getAmount());
                    }
                }

            } else {
                player.getPacketSender().sendMessage("Player is offline!");
            }
        }
        if (command[0].equalsIgnoreCase("unban")) {
            String playerToBan = wholeCommand.substring(6);
            if (!PlayerSaving.playerExists(playerToBan)) {
                player.getPacketSender().sendMessage("Player " + playerToBan + " does not exist.");
                return;
            } else {
                if (!PlayerPunishment.banned(playerToBan)) {
                    player.getPacketSender().sendMessage("Player " + playerToBan + " is not banned!");
                    return;
                }
                PlayerLogs.log(player.getUsername(), "" + player.getUsername() + " just unbanned " + playerToBan + "!");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just unbanned " + playerToBan + ".");
                PlayerPunishment.unban(playerToBan);
                player.getPacketSender()
                        .sendMessage("Player " + playerToBan + " was successfully unbanned. Command logs written.");
            }
        }
        if (command[0].equalsIgnoreCase("permban") || command[0].equalsIgnoreCase("permaban")) {
            try {
                Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
                if (player2 == null) {
                    player.getPacketSender().sendMessage("Target does not exist. Unable to permban.");
                    return;
                }

                String uuid = player2.getSerialNumber();
                String mac = player2.getMac();
                String name = player2.getUsername();
                String bannedIP = player2.getHostAddress();

                World.sendStaffMessage("Perm banned " + name + " (" + bannedIP + "/" + mac + "/" + uuid + ")");
                PlayerLogs.log(player.getUsername(), "Has perm banned: " + name + "!");
                PlayerLogs.log(name, player + " perm banned: " + name + ".");

                PlayerPunishment.addBannedIP(bannedIP);

                if (uuid != null && !uuid.equalsIgnoreCase("null") && !uuid.equalsIgnoreCase(""))
                    ConnectionHandler.banUUID(name, uuid);
                if (mac != null && !mac.equalsIgnoreCase("null") && !uuid.equalsIgnoreCase(""))
                    ConnectionHandler.banMac(name, mac);

                PlayerPunishment.ban(name);

                if (player2 != null) {
                    World.deregister(player2);
                }

                for (Player playersToBan : World.getPlayers()) {
                    if (playersToBan == null)
                        continue;
                    if (playersToBan.getHostAddress() == bannedIP || playersToBan.getSerialNumber() == uuid
                            || playersToBan.getMac() == mac) {
                        PlayerLogs.log(player.getUsername(),
                                player.getUsername() + " just caught " + playersToBan.getUsername() + " with permban!");
                        PlayerLogs.log(name, player + " perm banned: " + name + ", we were crossfire.");
                        World.sendStaffMessage(playersToBan.getUsername() + " was banned as well.");
                        PlayerPunishment.ban(playersToBan.getUsername());
                        World.deregister(playersToBan);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (command[0].equalsIgnoreCase("dnpcs")) {
            for (NPC npc : World.getNpcs()) {
                if (npc != null)
                    World.deregister(npc);
            }
        }
        if (command[0].equalsIgnoreCase("anpcs")) {
            NPC.init();
        }

        if (command[0].equalsIgnoreCase("ban")) {
            String playerToBan = wholeCommand.substring(command[0].length() + 1);
            if (World.getPlayerByName(playerToBan) == null) {
                player.getPacketSender().sendMessage("Player " + playerToBan + " does not exist.");
                return;
            } else {
                if (PlayerPunishment.banned(playerToBan)) {
                    player.getPacketSender().sendMessage("Player " + playerToBan + " already has an active ban.");
                    return;
                }
                PlayerLogs.log(player.getUsername(), player.getUsername() + " just banned " + playerToBan + "!");
                PlayerPunishment.ban(playerToBan);
                player.getPacketSender()
                        .sendMessage("Player " + playerToBan + " was successfully banned. Command logs written.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just banned " + playerToBan + ".");
                Player toBan = World.getPlayerByName(playerToBan);
                if (toBan != null) {
                    World.deregister(toBan);
                }
            }
        }
        if (command[0].equalsIgnoreCase("anim")) {
            int id = Integer.parseInt(command[1]);
            player.performAnimation(new Animation(id));
            player.getPacketSender().sendMessage("Sending animation: " + id);
        }
        if (command[0].equalsIgnoreCase("gfx")) {
            int id = Integer.parseInt(command[1]);
            player.performGraphic(new Graphic(id));
            player.getPacketSender().sendMessage("Sending graphic: " + id);
        }
        if (command[0].equalsIgnoreCase("gfx1")) {
            int id = Integer.parseInt(command[1]);
            player.getPacketSender().sendGlobalGraphic(new Graphic(id, GraphicHeight.LOW), player.getPosition());
            player.getPacketSender().sendMessage("Sending graphic: " + id);
        }
        if (command[0].equalsIgnoreCase("votecount")) {
            player.getPacketSender().sendMessage("Votecount: " + voteCount + ", with votes announcing every "
                    + GameSettings.Vote_Announcer + " vote.");
        }


        if (command[0].equalsIgnoreCase("addvote")) {
            voteCount++;
            if (voteCount >= GameSettings.Vote_Announcer) {
                World.sendMessage(
                        "<img=5><shad=0><col=bb43df>10 more players have just voted! Use ::vote for rewards!");
                voteCount = 0;
            } else {
                player.getPacketSender()
                        .sendMessage("<img=5><shad=0><col=bb43df>Thank you for voting and supporting Necrotic!");
            }
            player.getPacketSender().sendMessage("Votecount: " + voteCount + ", with votes announcing every "
                    + GameSettings.Vote_Announcer + " vote.");
        }
        if (command[0].equalsIgnoreCase("ipmute")) {
            Player player2 = World
                    .getPlayerByName(wholeCommand.substring(command[0].length() + 1, wholeCommand.length()));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Could not find that player online.");
                return;
            } else {
                if (PlayerPunishment.IPMuted(player2.getHostAddress())) {
                    player.getPacketSender().sendMessage(
                            "Player " + player2.getUsername() + "'s IP is already IPMuted. Command logs written.");
                    return;
                }
                final String mutedIP = player2.getHostAddress();
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just IP Muted " + player2.getUsername() + " on " + mutedIP);
                PlayerPunishment.addMutedIP(mutedIP);
                player.getPacketSender().sendMessage(
                        "Player " + player2.getUsername() + " was successfully IPMuted. Command logs written.");
                player2.getPacketSender().sendMessage("You have been IPMuted by " + player.getUsername() + ".");
                PlayerLogs.log(player.getUsername(),
                        player.getUsername() + " just IPMuted " + player2.getUsername() + "!");
            }
        }
        if (command[0].equalsIgnoreCase("movetome")) {
            String playerToTele = wholeCommand.substring(command[0].length() + 1);
            Player player2 = World.getPlayerByName(playerToTele);
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            } else {
                boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy())
                        && player.getRegionInstance() == null && player2.getRegionInstance() == null;
                if (canTele && player2.getDueling().duelingStatus < 5) {
                    TeleportHandler.teleportPlayer(player2, player.getPosition().copy(), TeleportType.NORMAL);
                    player.performGraphic(new Graphic(730));
                    player.getPacketSender().sendMessage("Teleporting " + player2.getUsername() + " to you.");
                    player2.getPacketSender().sendMessage("You're being teleported to " + player.getUsername() + "...");
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just teleported " + player2.getUsername() + " to them.");
                    player2.performGraphic(new Graphic(342));
                } else
                    player.getPacketSender().sendMessage(
                                    "You can not teleport that player at the moment. Maybe you or they are in a minigame?")
                            .sendMessage("Also will fail if they're in duel/wild.");
            }
        }
        if (command[0].equalsIgnoreCase("teletome")) {
            String playerToTele = wholeCommand.substring(command[0].length() + 1);
            Player player2 = World.getPlayerByName(playerToTele);
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player..");
                return;
            } else {
                boolean canTele = TeleportHandler.checkReqs(player, player2.getPosition().copy());
                if (canTele && player2.getDueling().duelingStatus < 5) {
                    player.getPacketSender().sendMessage("Moving player: " + player2.getUsername() + "");
                    player2.getPacketSender().sendMessage("You've been moved to " + player.getUsername());
                    World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                            + " just moved " + player2.getUsername() + " to them.");
                    player2.moveTo(player.getPosition().copy());
                    player2.performGraphic(new Graphic(342));
                } else
                    player.getPacketSender()
                            .sendMessage("Failed to move player to your coords. Are you or them in a minigame?")
                            .sendMessage("Also will fail if they're in duel/wild.");
            }
        }
    }

    private static void administratorCommands(final Player player, String[] command, String wholeCommand) {

        if (player.getRights() != PlayerRights.ADMINISTRATOR) {

            if (command[0].equalsIgnoreCase("resets")) {
                String name = wholeCommand.substring(command[0].length() + 1);
                Player target = World.getPlayerByName(name);

                if (target == null) {
                    player.getPacketSender().sendMessage("Player is not online");
                } else {
                    for (Skill skill : Skill.values()) {
                        int level = skill.equals(Skill.CONSTITUTION) ? 100 : skill.equals(Skill.PRAYER) ? 10 : 1;
                        target.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                                SkillManager.getExperienceForLevel(skill == Skill.CONSTITUTION ? 10 : 1));
                    }
                    target.getPacketSender().sendMessage("Your skill levels have now been reset.");
                    target.getUpdateFlag().flag(Flag.APPEARANCE);
                    player.getPacketSender().sendMessage("Gave " + name + " " + " reset skills.");
                }
            }


            if (command[0].equalsIgnoreCase("gm")) {
                String plrName = wholeCommand
                        .substring(command[0].length() + command[1].length() + 2);
                Player target = World.getPlayerByName(plrName);
                if (target == null) {
                    player.getPacketSender().sendMessage(plrName + " must be online to give them stuff!");
                } else {
                    if (command[1].equalsIgnoreCase("1")) {
                        GameMode.set(target, GameMode.NORMAL, false);
                        player.setGroupIronmanLocked(false);
                    } else if (command[1].equalsIgnoreCase("2")) {
                        GameMode.set(target, GameMode.IRONMAN, false);
                        player.setGroupIronmanLocked(false);
                    } else if (command[1].equalsIgnoreCase("3")) {
                        GameMode.set(target, GameMode.ULTIMATE_IRONMAN, false);
                        player.setGroupIronmanLocked(false);
                    } else if (command[1].equalsIgnoreCase("4")) {
                        GameMode.set(target, GameMode.EXTREME_MODE, false);
                        player.setGroupIronmanLocked(false);
                    } else if (command[1].equalsIgnoreCase("5")) {
                        GameMode.set(target, GameMode.GROUP_IRONMAN, false);
                    } else
                        player.getPacketSender().sendMessage("<img=5> Correct usage ::gamemode (#), 1 = Norm, 2 = IM, 3 = UIM");
                }
            }
        }


        if (command[0].equalsIgnoreCase("alert")) {
            String message = wholeCommand.substring(command[0].length() + 1);

            World.getPlayers().forEach(p -> p.getPacketSender().sendMessage("Alert##Notification##-1##450##40##5310729##125##" + message + "##By: " + player.getUsername() + "## ##"));
        }
        if (command[0].equalsIgnoreCase("partychest")) {
            PartyChest.startDropParty(player, Integer.parseInt(command[1]), Boolean.parseBoolean(command[2]));
        }
        if (command[0].equalsIgnoreCase("demote2")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");

                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("demoted to player.");
            player.getPacketSender().sendMessage("demoted to player.");
            player2.setRights(PlayerRights.PLAYER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.logout(player2);
            Donation.checkForRankUpdate(player2);
        }


        if (command[0].equalsIgnoreCase("givemod2")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("Promoted to moderator.");
            player.getPacketSender().sendMessage("Promoted to moderator.");
            player2.setRights(PlayerRights.MODERATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player2);
        }
        if (command[0].equalsIgnoreCase("givehelp2")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("Promoted to helper.");
            player.getPacketSender().sendMessage("Promoted to helper.");
            player2.setRights(PlayerRights.HELPER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player2);
        }


        if (command[0].equalsIgnoreCase("endperk")) {
            ServerPerks.getInstance().end();
        }
        if (command[0].equalsIgnoreCase("setperk")) {
            ServerPerks.getInstance().setPerk(player, Integer.parseInt(command[1]));
        }

        if (command[0].equalsIgnoreCase("addc")) {
            AddedCommands.addCommand(command[1], command[2]);
        }

        if (command[0].equalsIgnoreCase("endpc")) {
            PestControl.endGame(false);
        }

        if (command[0].equalsIgnoreCase("view")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target != null) {
                player.setCurrentPlayerViewing(target);
                PlayerViewer.openInventory(player);
            } else {
                player.sendMessage(wholeCommand.substring(command[0].length() + 1) + " is not valid");
            }
        }


        if (command[0].equalsIgnoreCase("x2vote")) {
            int time = Integer.parseInt(command[1]);
            if (time > 0) {
                GameServer.setUpdating(true);
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just started an vote event for " + time + " minutes.");
                World.getPlayers().forEach(p -> p.getPacketSender().sendMessage("Alert##Notification##-1##450##40##5310729##125##" +
                        "x2 Vote Event Active for " + time + " minutes!" + "##By: " + player.getUsername() + "## ##"));

                TaskManager.submit(new Task(time * 100) {
                    @Override
                    protected void execute() {
                        World.getPlayers().forEach(p -> p.getPacketSender().sendMessage("Alert##Notification##-1##450##40##5310729##125##" +
                                "x2 Vote Event Ended!" + "##---## ##"));
                        stop();
                    }
                });
            }
        }


        if (command[0].equalsIgnoreCase("clicktele")) {
            player.setClickToTeleport(!player.isClickToTeleport());
            player.getPacketSender().sendMessage("Click to teleport set to: " + player.isClickToTeleport() + ".");
        }
        if (command[0].equalsIgnoreCase("customevent")) {
            player.getEventBossManager().display();
        }
        if (command[0].equalsIgnoreCase("sd")) {
            try {
                if (command[1].equalsIgnoreCase("1")) {
                    Difficulty.set(player, Difficulty.FUN, true);
                } else if (command[1].equalsIgnoreCase("2")) {
                    Difficulty.set(player, Difficulty.EASY, true);
                } else if (command[1].equalsIgnoreCase("3")) {
                    Difficulty.set(player, Difficulty.REGULAR, true);
                } else if (command[1].equalsIgnoreCase("4")) {
                    Difficulty.set(player, Difficulty.HARD, true);
                } else if (command[1].equalsIgnoreCase("5")) {
                    Difficulty.set(player, Difficulty.EXTREME, true);
                } else {
                    player.getPacketSender().sendMessage("Did not understand.");
                    player.getPacketSender().sendMessage(
                            "Your current gamemode is: " + player.getDifficulty().toString().toLowerCase());
                    return;
                }
                player.getPacketSender().sendMessage(
                        "You have set your difficulty to: " + player.getDifficulty().toString().toLowerCase());
            } catch (Exception e) {
                player.getPacketSender().sendMessage("Invalid syntax; ::sd [1-5]");
            }
        }
        if (command[0].equalsIgnoreCase("mypos") || command[0].equalsIgnoreCase("coords")
                || command[0].equalsIgnoreCase("c")) {
            player.getPacketSender().sendMessage(player.getPosition().toString());
        }
        if (command[0].equalsIgnoreCase("dron1")) {
            GameSettings.DOUBLEDR = true;
            World.sendMessage(
                    "<shad=1>@gre@<img=18> @yel@[EVENT]@gre@ Double DR Has been turned on for [@yel@24@gre@] Hour!");
        }
        if (command[0].equalsIgnoreCase("dron")) {
            GameSettings.DOUBLEDR = true;
            World.sendMessage(
                    "<shad=1>@gre@<img=18> @yel@[EVENT]@gre@ Double DR Has been turned on for [@yel@1@gre@] Hour!");
        }
        if (command[0].equalsIgnoreCase("drhalfon")) {
            GameSettings.DOUBLEDR = true;
            World.sendMessage(
                    "<shad=1>@gre@<img=18> @yel@[EVENT]@gre@ Double DR Has been turned on for [@yel@30@gre@] Minutes!");
        }
        if (command[0].equalsIgnoreCase("droff")) {
            GameSettings.DOUBLEDR = false;
            World.sendMessage("<shad=1>@gre@<img=18> @yel@[EVENT]@red@ Double DR event has ended!");
        }
        if (command[0].equalsIgnoreCase("getpos")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Could not find that player online.");
                return;
            } else {
                player.getPacketSender().sendMessage("[@red@" + player2.getUsername() + "@bla@] "
                        + player2.getPosition().toString() + " @red@| @bla@Location: " + player2.getLocation());
            }
        }
        if (command[0].equalsIgnoreCase("tele")) {
            int x = Integer.valueOf(command[1]), y = Integer.valueOf(command[2]);
            int z = player.getPosition().getZ();
            if (command.length > 3)
                z = Integer.valueOf(command[3]);
            Position position = new Position(x, y, z);
            player.moveTo(position);
            player.getPacketSender().sendMessage("Teleporting to " + position.toString());
        }
        if (command[0].equalsIgnoreCase("ipban")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Could not find that player online.");
                return;
            } else {
                if (PlayerPunishment.IPBanned(player2.getHostAddress())) {
                    player.getPacketSender()
                            .sendMessage("Player " + player2.getUsername() + "'s IP is already banned.");
                    return;
                }
                final String bannedIP = player2.getHostAddress();
                PlayerPunishment.ban(player2.getUsername());
                PlayerPunishment.addBannedIP(bannedIP);
                player.getPacketSender().sendMessage(
                        "Player " + player2.getUsername() + "'s IP was successfully banned. Command logs written.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just IP Banned " + player2.getUsername());
                for (Player playersToBan : World.getPlayers()) {
                    if (playersToBan == null)
                        continue;
                    if (playersToBan.getHostAddress() == bannedIP) {
                        PlayerLogs.log(player.getUsername(),
                                "" + player.getUsername() + " just IPBanned " + playersToBan.getUsername() + "!");
                        PlayerPunishment.ban(playersToBan.getUsername());
                        World.deregister(playersToBan);
                        if (player2.getUsername() != playersToBan.getUsername())
                            player.getPacketSender().sendMessage("Player " + playersToBan.getUsername()
                                    + " was successfully IPBanned. Command logs written.");
                    }
                }
            }
        }
        if (command[0].equalsIgnoreCase("unipmute")) {
            player.getPacketSender().sendMessage("Unipmutes can only be handled manually.");
        }
        if (command[0].equalsIgnoreCase("checkpin")) {
            String plr = wholeCommand.substring(command[0].length() + 1);
            Player playr2 = World.getPlayerByName(plr);
            if (playr2 != null) {
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just tried to check " + playr2.getUsername() + "'s Security Pin.");
                if (player.getRights().equals(PlayerRights.MODERATOR)
                        && playr2.getRights().equals(PlayerRights.ADMINISTRATOR)
                        || playr2.getRights().equals(PlayerRights.DEVELOPER)) {
                    player.getPacketSender().sendMessage(
                            playr2.getUsername() + " is a higher rank than you. You can't resolve their Security Pin.");
                    return;
                } else if (player.getRights().equals(PlayerRights.ADMINISTRATOR)
                        || playr2.getRights().equals(PlayerRights.DEVELOPER)) {
                    player.getPacketSender().sendMessage(
                            playr2.getUsername() + " is a higher rank than you. You can't resolve their Security Pin.");
                    return;
                }
                player.getPacketSender().sendMessage(playr2.getUsername() + " Pin: " + playr2.getSavedPin());
            } else
                player.getPacketSender().sendMessage("Could not find player: " + plr);
        }
        if (command[0].equalsIgnoreCase("host")) {
            String plr = wholeCommand.substring(command[0].length() + 1);
            Player playr2 = World.getPlayerByName(plr);
            if (playr2 != null) {
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just tried to check " + playr2.getUsername() + "'s IP address.");
                if (player.getRights().equals(PlayerRights.MODERATOR)
                        && playr2.getRights().equals(PlayerRights.ADMINISTRATOR)
                        || playr2.getRights().equals(PlayerRights.DEVELOPER)) {
                    player.getPacketSender().sendMessage(
                            playr2.getUsername() + " is a higher rank than you. You can't resolve their IP.");
                    return;
                } else if (player.getRights().equals(PlayerRights.ADMINISTRATOR)
                        || playr2.getRights().equals(PlayerRights.DEVELOPER)) {
                    player.getPacketSender().sendMessage(
                            playr2.getUsername() + " is a higher rank than you. You can't resolve their IP.");
                    return;
                }
                player.getPacketSender().sendMessage(playr2.getUsername() + " ip: " + playr2.getHostAddress()
                        + ", mac: " + playr2.getMac() + ", uuid: " + playr2.getSerialNumber());
                player.getPacketSender().sendString(1, "www.ipaddressden.com/ip/" + playr2.getHostAddress() + ".html"); // http://www.ipaddressden.com/ip/192.168.0.1.html
            } else
                player.getPacketSender().sendMessage("Could not find player: " + plr);
        }
        if (command[0].equalsIgnoreCase("checkgold")) {
            Player p = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (p != null) {
                long gold = 0;
                for (Item item : p.getInventory().getItems()) {
                    if (item != null && item.getId() > 0 && item.tradeable())
                        gold += item.getDefinition().getValue();
                }
                for (Item item : p.getEquipment().getItems()) {
                    if (item != null && item.getId() > 0 && item.tradeable())
                        gold += item.getDefinition().getValue();
                }
                for (int i = 0; i < 9; i++) {
                    for (Item item : p.getBank(i).getItems()) {
                        if (item != null && item.getId() > 0 && item.tradeable())
                            gold += item.getDefinition().getValue();
                    }
                }
                gold += p.getMoneyInPouch();
                player.getPacketSender().sendMessage(
                        p.getUsername() + " has " + Misc.insertCommasToNumber(String.valueOf(gold)) + " coins.");
            } else
                player.getPacketSender().sendMessage("Can not find player online.");
        }

    }

    private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("dumpspawns")) {
            for (NPC npc : World.getNpcs()) {
                if (npc == null)
                    continue;
                if (npc.getPosition().getRegionId() == player.getPosition().getRegionId() &&
                        npc.getPosition().getZ() == player.getPosition().getZ()) {
                    int id = npc.getId();
                    Position position = npc.getDefaultPosition();
                    NPCMovementCoordinator.Coordinator coordinator = npc.getMovementCoordinator().getCoordinator();
                    Direction direction = npc.getDirection();

                    Gson builder = new GsonBuilder().setPrettyPrinting().create();
                    JsonObject object = new JsonObject();
                    object.addProperty("npc-id", id);
                    object.add("position", builder.toJsonTree(position.copy().setZ(4)));
                    object.addProperty("face", direction.name());
                    object.add("walking-policy", builder.toJsonTree(coordinator));
                    System.out.println(object + ",");
                }
            }
        }
        if (command[0].equalsIgnoreCase("delete")) {
            int id = Integer.parseInt(command[1]);
            for (NPC npc : World.getNpcs()) {
                if (npc == null)
                    continue;
                if (npc.getId() == id) {
                    World.deregister(npc);
                }
            }
        }
        if (command[0].equalsIgnoreCase("add")) {
            int id = Integer.parseInt(command[1]);
            for (NPC npc : NPC.spawnedWorldsNpcs) {
                if (npc.getId() == id) {
                    NPC newNpc = new NPC(id, npc.getDefaultPosition());
                    newNpc.getMovementCoordinator().setCoordinator(npc.getMovementCoordinator().getCoordinator());
                    newNpc.setDirection(npc.getDirection());
                    World.register(newNpc);
                }
            }
        }
        if (command[0].equalsIgnoreCase("listuntradeables")) {
            Misc.listUntradeables();
        }
        if (command[0].equalsIgnoreCase("roll")) {
            if (player.getClanChatName() == null) {
                player.getPacketSender().sendMessage("You need to be in a clanchat channel to roll a dice.");
                return;
            } else if (player.getClanChatName().equalsIgnoreCase("help")) {
                player.getPacketSender().sendMessage("You can't roll a dice in this clanchat channel!");
                return;
            } else if (player.getClanChatName().equalsIgnoreCase("necrotic")) {
                player.getPacketSender().sendMessage("You can't roll a dice in this clanchat channel!");
                return;
            }
            int dice = Integer.parseInt(command[1]);
            player.getMovementQueue().reset();
            player.performAnimation(new Animation(11900));
            player.performGraphic(new Graphic(2075));
            ClanChatManager.sendMessage(player.getCurrentClanChat(), "@bla@[ClanChat] @whi@" + player.getUsername()
                    + " just rolled @bla@" + dice + "@whi@ on the percentile dice.");
        }
        if (command[0].equalsIgnoreCase("resetny")) {
            player.setNewYear2017(0);
            player.getPacketSender().sendMessage("Set setNewYear2017 to: " + player.getNewYear2017());
        }
        if (command[0].equalsIgnoreCase("xmascount")) {
            player.getPacketSender().sendMessage("xmas count; " + player.getChristmas2016());
        }
        if (command[0].equalsIgnoreCase("resetxmas")) {
            player.setchristmas2016(0);
        }
        if (command[0].equalsIgnoreCase("christmas")) {
            // christmas2016.announceChristmas();
            // System.out.println(christmas2016.isChristmas());
        }
        if (command[0].equalsIgnoreCase("di") && command[1] != null) {
            NPCDrops.sendDropTableInterface(player, Integer.parseInt(command[1]));
        }
        if (command[0].equalsIgnoreCase("olddrops") && command[1] != null) {
            NPCDrops.getDropTable(player, Integer.parseInt(command[1]));
        }
        if (command[0].equalsIgnoreCase("setxmas") && command[1] != null) {
            player.setchristmas2016(Integer.parseInt(command[1]));
            player.getPacketSender().sendMessage("Set Christmas2016 to " + player.getChristmas2016());
        }
        if (command[0].equalsIgnoreCase("easteri")) {
            easter2017.openInterface(player);
        }
        if (command[0].equalsIgnoreCase("easterc")) {
            player.getPacketSender().sendMessage("easter status: " + player.getEaster2017());
        }
        if (command[0].equalsIgnoreCase("seteaster")) {
            int inty = Integer.parseInt(command[1]);
            player.setEaster2017(inty);
            player.getPacketSender().sendMessage("Set your easter to: " + inty);
        }
        if (command[0].equalsIgnoreCase("item")) {
            int id = Integer.parseInt(command[1]);
            if (id > ItemDefinition.getMaxAmountOfItems()) {
                player.getPacketSender().sendMessage(
                        "Invalid item id entered. Max amount of items: " + ItemDefinition.getMaxAmountOfItems());
                return;
            }
            int amount = (command.length == 2 ? 1
                    : Integer.parseInt(command[2].trim().toLowerCase().replaceAll("k", "000").replaceAll("m", "000000")
                    .replaceAll("b", "000000000")));
            if (amount > Integer.MAX_VALUE) {
                amount = Integer.MAX_VALUE;
            }
            Item item = new Item(id, amount);
            player.getInventory().add(item, true);

            DiscordMessager.sendSpawnItem(player.getUsername(),
                    "x" + amount + " " + ItemDefinition.forId(id).getName() + " (" + id + ")"
            );
        }

        if (command[0].equalsIgnoreCase("itemall")) {
            int id = Integer.parseInt(command[1]);
            int endid = Integer.parseInt(command[2]);
            for (int i = id; i <= endid; i++) {
                Item item = new Item(i, 1);
                player.getInventory().add(item, true);
            }
        }


        if (command[0].equalsIgnoreCase("giveitem")) {
            int id = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            String plrName = wholeCommand
                    .substring(command[0].length() + command[1].length() + command[2].length() + 3);
            Player target = World.getPlayerByName(plrName);
            if (target == null) {
                player.getPacketSender().sendMessage(plrName + " must be online to give them stuff!");
            } else {
                target.getInventory().add(id, amount);
                player.getPacketSender().sendMessage(
                        "Gave " + amount + "x " + ItemDefinition.forId(id).getName() + " to " + plrName + ".");

                DiscordMessager.sendGiveItem(target.getUsername(),
                        "By: " + player.getUsername()
                                + "\n Item: " + "x" + amount + " " + ItemDefinition.forId(id).getName() + " (" + id + ")"
                );
            }
        }
        if (command[0].equalsIgnoreCase("giveall")) {
            int id = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            for (Player players : World.getPlayers()) {
                if (players != null) {
                    players.getInventory().add(id, amount);
                    players.sendMessage(
                            "You have recieved: " + ItemDefinition.forId(id).getName() + " by someone for being awesome.");
                }
            }
        }
        if (command[0].equalsIgnoreCase("takeall")) {
            int id = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            for (Player players : World.getPlayers()) {
                if (players != null) {
                    players.getInventory().delete(id, amount);
                }
            }
        }
        if (command[0].equalsIgnoreCase("thieving")) {
            int lvl = Integer.parseInt(command[1]);
            player.getSkillManager().setMaxLevel(Skill.THIEVING, lvl);
            player.getSkillManager().setCurrentLevel(Skill.THIEVING, lvl);
            player.getPacketSender().sendMessage("Set your Thieving level to " + lvl + ".");
        }
        if (command[0].equalsIgnoreCase("master")) {
            for (Skill skill : Skill.values()) {
                int level = SkillManager.getMaxAchievingLevel(skill);
                player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                        SkillManager.getExperienceForLevel(level == 120 ? 120 : 99));
            }
            player.getPacketSender().sendMessage("You are now a master of all skills.");
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }
        if (command[0].equalsIgnoreCase("reset")) {
            for (Skill skill : Skill.values()) {
                int level = skill.equals(Skill.CONSTITUTION) ? 100 : skill.equals(Skill.PRAYER) ? 10 : 1;
                player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                        SkillManager.getExperienceForLevel(skill == Skill.CONSTITUTION ? 10 : 1));
            }
            player.getPacketSender().sendMessage("Your skill levels have now been reset.");
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }
        if (command[0].equalsIgnoreCase("rights")) {
            int rankId = Integer.parseInt(command[1]);
            if (player.getUsername().equalsIgnoreCase("server") && rankId != 10) {
                player.getPacketSender().sendMessage("You cannot do that.");
                return;
            }
            // wholeCommand.substring(command[0].length()+2+rankId.length);
            Player target = World
                    .getPlayerByName(wholeCommand.substring(command[0].length() + command[1].length() + 2));
            if (target == null) {
                player.getPacketSender().sendMessage("Player must be online to give them rights!");
            } else {
                target.setRights(PlayerRights.forId(rankId));
                target.getPacketSender().sendMessage("Your player rights have been changed.");
                target.getPacketSender().sendRights();
            }
            // }
        }
        if (command[0].equalsIgnoreCase("setlevel")) {
            int skillId = Integer.parseInt(command[1]);
            int level = Integer.parseInt(command[2]);
            if (level > 15000) {
                player.getPacketSender().sendMessage("You can only have a maxmium level of 15000.");
                return;
            }
            Skill skill = Skill.forId(skillId);
            player.getSkillManager().setCurrentLevel(skill, level).setMaxLevel(skill, level).setExperience(skill,
                    SkillManager.getExperienceForLevel(level));
            player.getPacketSender().sendMessage("You have set your " + skill.getName() + " level to " + level);
        }
        if (wholeCommand.toLowerCase().startsWith("yell") && player.getRights() == PlayerRights.PLAYER) {
            player.getPacketSender()
                    .sendMessage("Only donator+ can yell. To become one, simply use ::store, buy a scroll")
                    .sendMessage("and then claim it.");
        }
        if (command[0].equalsIgnoreCase("pure")) {
            int[][] data = new int[][]{{Equipment.HEAD_SLOT, 1153}, {Equipment.CAPE_SLOT, 10499},
                    {Equipment.AMULET_SLOT, 1725}, {Equipment.WEAPON_SLOT, 4587}, {Equipment.BODY_SLOT, 1129},
                    {Equipment.SHIELD_SLOT, 1540}, {Equipment.LEG_SLOT, 2497}, {Equipment.HANDS_SLOT, 7459},
                    {Equipment.FEET_SLOT, 3105}, {Equipment.RING_SLOT, 2550}, {Equipment.AMMUNITION_SLOT, 9244}};
            for (int i = 0; i < data.length; i++) {
                int slot = data[i][0], id = data[i][1];
                player.getEquipment().setItem(slot, new Item(id, id == 9244 ? 500 : 1));
            }
            BonusManager.update(player);
            WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
            WeaponAnimations.update(player);
            player.getEquipment().refreshItems();
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getInventory().resetItems();
            player.getInventory().add(1216, 1000).add(9186, 1000).add(862, 1000).add(892, 10000).add(4154, 5000)
                    .add(2437, 1000).add(2441, 1000).add(2445, 1000).add(386, 1000).add(2435, 1000);
            player.getSkillManager().newSkillManager();
            player.getSkillManager().setMaxLevel(Skill.ATTACK, 60).setMaxLevel(Skill.STRENGTH, 85)
                    .setMaxLevel(Skill.RANGED, 85).setMaxLevel(Skill.PRAYER, 520).setMaxLevel(Skill.MAGIC, 70)
                    .setMaxLevel(Skill.CONSTITUTION, 850);
            for (Skill skill : Skill.values()) {
                player.getSkillManager().setCurrentLevel(skill, player.getSkillManager().getMaxLevel(skill))
                        .setExperience(skill,
                                SkillManager.getExperienceForLevel(player.getSkillManager().getMaxLevel(skill)));
            }
        }
        if (command[0].equalsIgnoreCase("emptyitem")) {
            if (player.getInterfaceId() > 0
                    || player.getLocation() != null && player.getLocation() == Location.WILDERNESS
            ) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            int item = Integer.parseInt(command[1]);
            int itemAmount = player.getInventory().getAmount(item);
            Item itemToDelete = new Item(item, itemAmount);
            player.getInventory().delete(itemToDelete).refreshItems();
        }
        if (command[0].equalsIgnoreCase("prayer") || command[0].equalsIgnoreCase("pray")) {
            player.getSkillManager().setCurrentLevel(Skill.PRAYER, 15000);
        }
        if (command[0].equalsIgnoreCase("zulrah")) {
            TeleportHandler.teleportPlayer(player, new Position(3406, 2794, 0),
                    player.getSpellbook().getTeleportType());
            // player.getPacketSender().sendMessage("Old cords: 3363, 3807");
        }

        if (command[0].equalsIgnoreCase("cashineco")) {
            int gold = 0, plrLoops = 0;
            for (Player p : World.getPlayers()) {
                if (p != null) {
                    for (Item item : p.getInventory().getItems()) {
                        if (item != null && item.getId() > 0 && item.tradeable())
                            gold += item.getDefinition().getValue();
                    }
                    for (Item item : p.getEquipment().getItems()) {
                        if (item != null && item.getId() > 0 && item.tradeable())
                            gold += item.getDefinition().getValue();
                    }
                    for (int i = 0; i < 9; i++) {
                        for (Item item : player.getBank(i).getItems()) {
                            if (item != null && item.getId() > 0 && item.tradeable())
                                gold += item.getDefinition().getValue();
                        }
                    }
                    gold += p.getMoneyInPouch();
                    plrLoops++;
                }
            }
            player.getPacketSender().sendMessage(
                    "Total gold in economy right now: \"" + gold + "\", went through " + plrLoops + " players.");
        }
        if (command[0].equalsIgnoreCase("bank")) {
            player.getBank(player.getCurrentBankTab()).open();
        }
        if (command[0].equalsIgnoreCase("findnpc")) {
            String name = wholeCommand.substring(command[0].length() + 1);
            player.getPacketSender().sendMessage("Finding item id for item - " + name);
            boolean found = false;
            for (int i = 0; i < NpcDefinition.getDefinitions().length; i++) {
                if (NpcDefinition.forId(i) == null || NpcDefinition.forId(i).getName() == null) {
                    continue;
                }
                if (NpcDefinition.forId(i).getName().toLowerCase().contains(name)) {
                    player.getPacketSender().sendMessage(
                            "Found NPC with name [" + NpcDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
                    found = true;
                }
            }
            if (!found) {
                player.getPacketSender().sendMessage("No NPC with name [" + name + "] has been found!");
            }
        }
        if (command[0].equalsIgnoreCase("find")) {
            String name = wholeCommand.substring(5).toLowerCase().replaceAll("_", " ");
            player.getPacketSender().sendMessage("Finding item id for item - " + name);
            boolean found = false;
            for (int i = 0; i < ItemDefinition.getMaxAmountOfItems(); i++) {
                if (ItemDefinition.forId(i).getName().toLowerCase().contains(name)) {
                    player.getPacketSender().sendMessage("Found item with name ["
                            + ItemDefinition.forId(i).getName().toLowerCase() + "] - id: " + i);
                    found = true;
                }
            }
            if (!found) {
                player.getPacketSender().sendMessage("No item with name [" + name + "] has been found!");
            }
        }
        if (command[0].equalsIgnoreCase("spec")) {
            player.setSpecialPercentage(15000);
            CombatSpecial.updateBar(player);
        }
        if (command[0].equalsIgnoreCase("jewel")) {
            Jewelry.jewelryInterface(player);
        }
        if (command[0].equalsIgnoreCase("jint")) {
            player.getPacketSender().sendInterface(4161);
        }
        if (command[0].equalsIgnoreCase("sendstring")) {
            player.getPacketSender().sendMessage("::sendstring id text");
            if (command.length >= 3 && Integer.parseInt(command[1]) <= Integer.MAX_VALUE) {
                int id = Integer.parseInt(command[1]);
                String text = wholeCommand.substring(command[0].length() + command[1].length() + 2);
                player.getPacketSender().sendString(Integer.parseInt(command[1]), text);
                player.getPacketSender().sendMessage("Sent \"" + text + "\" to: " + id);
            }
        }
        if (command[0].equalsIgnoreCase("sendteststring")) {
            player.getPacketSender().sendMessage("sendstring syntax: id");
            if (command.length == 2 && Integer.parseInt(command[1]) <= Integer.MAX_VALUE) {
                player.getPacketSender().sendString(Integer.parseInt(command[1]), "TEST STRING");
                player.getPacketSender().sendMessage("Sent \"TEST STRING\" to " + Integer.parseInt(command[1]));
            }
        }
        if (command[0].equalsIgnoreCase("senditemoninterface")) {
            player.getPacketSender().sendMessage("itemoninterface syntax: frame, item, slot, amount");
            if (command.length == 5 && Integer.parseInt(command[4]) <= Integer.MAX_VALUE) {
                player.getPacketSender()
                        .sendMessage("Sent the following: " + Integer.parseInt(command[1]) + " "
                                + Integer.parseInt(command[2]) + " " + "" + Integer.parseInt(command[3]) + " "
                                + Integer.parseInt(command[4]));
            }
        }
        if (command[0].equalsIgnoreCase("sendinterfacemodel")) {
            player.getPacketSender().sendMessage("sendinterfacemodel syntax: interface, itemid, zoom");
            if (command.length == 4 && Integer.parseInt(command[3]) <= Integer.MAX_VALUE) {
                player.getPacketSender().sendInterfaceModel(Integer.parseInt(command[1]), Integer.parseInt(command[2]),
                        Integer.parseInt(command[3]));
                player.getPacketSender().sendMessage("Sent the following: " + Integer.parseInt(command[1]) + " "
                        + Integer.parseInt(command[2]) + " " + "" + Integer.parseInt(command[3]));
            }
        }
        if (command[0].equalsIgnoreCase("ancients") || command[0].equalsIgnoreCase("ancient")) {
            player.setSpellbook(MagicSpellbook.ANCIENT);
            player.performAnimation(new Animation(645));
            player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId())
                    .sendMessage("Your magic spellbook is changed..");
            Autocasting.resetAutocast(player, true);
        }
        if (command[0].equalsIgnoreCase("lunar") || command[0].equalsIgnoreCase("lunars")) {
            player.setSpellbook(MagicSpellbook.LUNAR);
            player.performAnimation(new Animation(645));
            player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId())
                    .sendMessage("Your magic spellbook is changed..");
            Autocasting.resetAutocast(player, true);
        }
        if (command[0].equalsIgnoreCase("regular") || command[0].equalsIgnoreCase("normal")) {
            player.setSpellbook(MagicSpellbook.NORMAL);
            player.performAnimation(new Animation(645));
            player.getPacketSender().sendTabInterface(GameSettings.MAGIC_TAB, player.getSpellbook().getInterfaceId())
                    .sendMessage("Your magic spellbook is changed..");
            Autocasting.resetAutocast(player, true);
        }

        if (command[0].equalsIgnoreCase("dropi")) {
            // String search = wholeCommand.substring(command[0].length()+1);
            DropsInterface.open(player);
            player.getPacketSender().sendMessage("Sent drop interface.");
        }
        if (command[0].equalsIgnoreCase("tdropi")) {
            String search = wholeCommand.substring(command[0].length() + 1);
            DropsInterface.getList(search);
        }
        if (command[0].equalsIgnoreCase("bcr")) {
            player.getPacketSender().sendMessage("needsNewSalt ? " + Misc.needsNewSalt(player.getSalt()));
        }

        if (command[0].equalsIgnoreCase("hp")) {
            player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 150000);
        }
        if (command[0].equalsIgnoreCase("1hp")) {
            player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 10);
        }
        if (command[0].equalsIgnoreCase("god") || command[0].equalsIgnoreCase("op")) {
            if (!player.isOpMode()) {
                player.setSpecialPercentage(15000);
                CombatSpecial.updateBar(player);
                player.getSkillManager().setCurrentLevel(Skill.PRAYER, 150000);
                player.getSkillManager().setCurrentLevel(Skill.ATTACK, 15000);
                player.getSkillManager().setCurrentLevel(Skill.STRENGTH, 15000);
                player.getSkillManager().setCurrentLevel(Skill.DEFENCE, 15000);
                player.getSkillManager().setCurrentLevel(Skill.RANGED, 15000);
                player.getSkillManager().setCurrentLevel(Skill.MAGIC, 15000);
                player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 150000);
                player.getSkillManager().setCurrentLevel(Skill.SUMMONING, 15000);
                player.setHasVengeance(true);
                player.performAnimation(new Animation(725));
                player.performGraphic(new Graphic(1555));
                player.getPacketSender().sendMessage("You're on op mode now, and everyone knows it.");
            } else {
                player.setSpecialPercentage(100);
                CombatSpecial.updateBar(player);
                player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                        player.getSkillManager().getMaxLevel(Skill.PRAYER));
                player.getSkillManager().setCurrentLevel(Skill.ATTACK,
                        player.getSkillManager().getMaxLevel(Skill.ATTACK));
                player.getSkillManager().setCurrentLevel(Skill.STRENGTH,
                        player.getSkillManager().getMaxLevel(Skill.STRENGTH));
                player.getSkillManager().setCurrentLevel(Skill.DEFENCE,
                        player.getSkillManager().getMaxLevel(Skill.DEFENCE));
                player.getSkillManager().setCurrentLevel(Skill.RANGED,
                        player.getSkillManager().getMaxLevel(Skill.RANGED));
                player.getSkillManager().setCurrentLevel(Skill.MAGIC,
                        player.getSkillManager().getMaxLevel(Skill.MAGIC));
                player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                        player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                player.getSkillManager().setCurrentLevel(Skill.SUMMONING,
                        player.getSkillManager().getMaxLevel(Skill.SUMMONING));
                player.setSpecialPercentage(100);
                player.setHasVengeance(false);
                player.performAnimation(new Animation(860));
                player.getPacketSender().sendMessage("You cool down, and forfeit op mode.");
            }
            player.setOpMode(!player.isOpMode());
        }
        if (command[0].equalsIgnoreCase("getanim")) {
            player.getPacketSender().sendMessage("Your last animation ID is: " + player.getAnimation().getId());
        }
        if (command[0].equalsIgnoreCase("getgfx")) {
            player.getPacketSender().sendMessage("Your last graphic ID is: " + player.getGraphic().getId());
        }
        if (command[0].equalsIgnoreCase("vengrunes")) {
            player.setHasVengeance(true);
            player.getInventory().add(new Item(560, 1000000)).add(new Item(9075, 1000000)).add(new Item(557, 1000000));
            player.getPacketSender().sendMessage("You cast Vengeance").sendMessage("You get some Vengeance runes.");
        }
        if (command[0].equalsIgnoreCase("veng")) {
            player.setHasVengeance(true);
            player.performAnimation(new Animation(4410));
            player.performGraphic(new Graphic(726));
            player.getPacketSender().sendMessage("You cast Vengeance.");
        }
        if (command[0].equalsIgnoreCase("barragerunes") || command[0].equalsIgnoreCase("barrage")) {
            player.getInventory().add(new Item(565, 1000000)).add(new Item(560, 1000000)).add(new Item(555, 1000000));
            player.getPacketSender().sendMessage("You get some Ice Barrage runes.");
        } // arlo testing
        if (command[0].equalsIgnoreCase("todoom")) {
            player.moveTo(new Position(2321, 5227, 0));
            player.getPacketSender().sendMessage("Moved you to doom.");
        } // arlo testing2
        if (command[0].equalsIgnoreCase("startdoom") || command[0].equalsIgnoreCase("spawndoom")) {
            Doom.spawnWave1(player);
            player.getPacketSender().sendMessage("Done spawning doom shit");
        }

        if (command[0].equalsIgnoreCase("runes")) {
            for (Item t : ShopManager.getShops().get(0).getItems()) {
                if (t != null) {
                    player.getInventory().add(new Item(t.getId(), 200000));
                }
            }
        }
        if (wholeCommand.equalsIgnoreCase("afk1")) {
            World.sendMessage("<img=5> <col=FF0000><shad=0>" + player.getUsername()
                    + ": I am now away, please don't message me; I won't reply.");
        }
        if (command[0].equalsIgnoreCase("isduel") || command[0].equalsIgnoreCase("checkduel")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick != null) {
                if (playerToKick.getDueling().duelingStatus == 0) {
                    player.getPacketSender().sendMessage(playerToKick.getUsername() + " is not dueling.");
                } else {
                    if (playerToKick.getDueling().duelingStatus == 1) {
                        player.getPacketSender()
                                .sendMessage(playerToKick.getUsername() + " has opened the first duel interface with "
                                        + playerToKick.getDueling().getDuelOpponent() + ".");
                    } else {
                        if (playerToKick.getDueling().duelingStatus == 2) {
                            player.getPacketSender()
                                    .sendMessage(playerToKick.getUsername()
                                            + " has accepted the first screen, and is waiting for "
                                            + playerToKick.getDueling().getDuelOpponent() + " to confirm.");
                        } else {
                            if (playerToKick.getDueling().duelingStatus == 3) {
                                player.getPacketSender()
                                        .sendMessage(playerToKick.getUsername() + " and their opponent, "
                                                + playerToKick.getDueling().getDuelOpponent()
                                                + " are in the final confirmation screen.");
                            } else {
                                if (playerToKick.getDueling().duelingStatus == 4) {
                                    player.getPacketSender()
                                            .sendMessage(playerToKick.getUsername()
                                                    + "  has confirmed the second, and is waiting for their opponent, "
                                                    + playerToKick.getDueling().getDuelOpponent() + ".");
                                } else {
                                    if (playerToKick.getDueling().duelingStatus == 5) {
                                        player.getPacketSender()
                                                .sendMessage(playerToKick.getUsername()
                                                        + " is currently in the arena with their opponent, "
                                                        + playerToKick.getDueling().getDuelOpponent() + ".");
                                    } else {
                                        if (playerToKick.getDueling().duelingStatus == 6) {
                                            player.getPacketSender().sendMessage(
                                                    playerToKick.getUsername() + " has just declined a duel request.");
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                player.getPacketSender().sendMessage("Could not find `" + command[1] + "`... Typo/offline?");
            }
        }
        if (command[0].equalsIgnoreCase("buff")) {
            String playertarget = wholeCommand.substring(command[0].length() + 1);
            Player player2 = World.getPlayerByName(playertarget);
            if (player2 != null) {
                player2.getSkillManager().setCurrentLevel(Skill.ATTACK, 1000);
                player2.getSkillManager().setCurrentLevel(Skill.DEFENCE, 1000);
                player2.getSkillManager().setCurrentLevel(Skill.STRENGTH, 1000);
                player2.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 149000);
                player.getPacketSender()
                        .sendMessage("We've buffed " + player2.getUsername() + "'s attack, def, and str to 1000.");
                World.sendOwnerDevMessage("@red@<img=3><img=4> [OWN/DEV]<col=6600FF> " + player.getUsername()
                        + " just buffed " + player2.getUsername() + "'s stats.");
            } else {
                player.getPacketSender().sendMessage("Invalid player... We could not find \"" + playertarget + "\"...");
            }
        }
        if (command[0].equalsIgnoreCase("dailytasknew")) {
            player.getDailyTaskManager().open();
        }
        if (command[0].equalsIgnoreCase("refreshdaily")) {
            player.getDailyTaskManager().refresh();
        }
        if (command[0].equalsIgnoreCase("rd")) {
            player.getDailyTaskManager().refresh();
        }
        if (command[0].equalsIgnoreCase("refreshdailyeasy")) {
            player.getDailyTaskManager().refreshTasks(DailyTaskDifficulty.EASY);
        }
        if (command[0].equalsIgnoreCase("refreshdailymedium")) {
            player.getDailyTaskManager().refreshTasks(DailyTaskDifficulty.MEDIUM);
        }
        if (command[0].equalsIgnoreCase("refreshdailyhard")) {
            player.getDailyTaskManager().refreshTasks(DailyTaskDifficulty.HARD);
        }
        if (command[0].equalsIgnoreCase("refreshdailyelite")) {
            player.getDailyTaskManager().refreshTasks(DailyTaskDifficulty.ELITE);
        }
        if (command[0].equalsIgnoreCase("refreshdailymaster")) {
            player.getDailyTaskManager().refreshTasks(DailyTaskDifficulty.MASTER);
        }
        if (command[0].equalsIgnoreCase("update")) {
            int time = Integer.parseInt(command[1]);
            if (time > 0) {
                GameServer.setUpdating(true);
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just started an update in " + time + " ticks.");
                // DiscordMessager.sendDebugMessage(player.getUsername()+" has queued an update,
                // we will be going down in "+time+" seconds.");
                for (Player players : World.getPlayers()) {
                    if (players == null)
                        continue;
                    players.getPacketSender().sendSystemUpdate(time);
                }
                TaskManager.submit(new Task(time) {
                    @Override
                    protected void execute() {
                        for (Player player : World.getPlayers()) {
                            if (player != null) {
                                World.deregister(player);
                            }
                        }
                        WellOfGoodwill.save();
                        GrandExchangeOffers.save();
                        ClanChatManager.save();
                        PlayerOwnedShopManager.saveShops();
                        DiscordIntegration.saveConnectedAccounts();
                        ShopManager.saveTaxShop();
                        LotterySystem.saveTickets();
                        ServerPerks.getInstance().save();
                        GameServer.getLogger().info("Update task finished!");
                        // DiscordMessager.sendDebugMessage("The server has gone offline, pending an
                        // update.");
                        stop();
                    }
                });
            }
        }
    }

    private static void developerCommands(Player player, String command[], String wholeCommand) {

       /* if (command[0].equalsIgnoreCase("rpn")) {
            player.getRaidAttributesNew().setCurrentParty(new RaidPartyNew(player));
        }
        if (command[0].equalsIgnoreCase("rps")) {
            player.getRaidAttributesNew().getCurrentParty().startRaid();
        }*/
      /*  if (command[0].equalsIgnoreCase("rpi")) {
            if (command.length == 1)  {
                player.sendMessage("Format: ::rpi (name)");
                return;
            }
            if (player.getRaidAttributesNew().getCurrentParty() == null) {
                player.sendMessage("You need to create a raid party before inviting someone!");
                return;
            }
            String name = wholeCommand.substring(4);
            Player other = World.getPlayerByName(name);
            if (other == null) {
                player.sendMessage("This player is not online.");
                return;
            }
            player.getRaidAttributesNew().getCurrentParty().invitePlayer(other);
        }*/
        if (command[0].equalsIgnoreCase("tzbf")) {
            player.setBossFight(new Leviathon(player));
            player.getBossFight().begin();
        }

        if (command[0].equalsIgnoreCase("slayerfavourites")) {
            player.getSlayerFavourites().open();
        }

        if (command[0].equalsIgnoreCase("myloc")) {
            player.getPA().sendMessage(player.getLocation().toString());
        }

        if (command[0].equals("gone")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);

            for (Player p : World.getPlayers()) {
                if (p != null && p.getUsername().equalsIgnoreCase(player2)) {
                    World.playerMap().remove(p.getLongUsername(), p);

                    PlayerSession session = p.getSession();

                    if (session.getChannel().isOpen()) {
                        session.getChannel().close();
                    }
                    p.getSession().setState(SessionState.LOGGING_OUT);
                    ConnectionHandler.remove(p.getHostAddress());
                    World.removePlayer(p);
                    session.setState(SessionState.LOGGED_OUT);

                    World.getPlayers().remove(p);
                }
            }
        }
        if (command[0].equals("celestial")) {
            CelestialZone.spawn();
        }
        if (command[0].equals("trav")) {
            TravellingMerchant.dayInYear += 1;
            TravellingMerchant.resetItems();
        }
        if (command[0].equals("trav1")) {
            TravellingMerchant.dayInYear += 1;
            World.sendNewsMessage("The Traveling merchant restocked his shop with new items!");
            TravellingMerchant.resetItems();
        }

        if (command[0].equalsIgnoreCase("setperkraffle")) {
            PerksRaffle.setRewards(player);
            RaffleInterface.openInterfacePerks(player);
        }
        if (command[0].equalsIgnoreCase("setvotingraffle")) {
            VotingRaffle.setRewards(player);
            RaffleInterface.openInterfaceVoting(player);
        }
        if (command[0].equalsIgnoreCase("setdonoraffle")) {
            DonationRaffle.setRewards(player);
            RaffleInterface.openInterfaceDonations(player);
        }
        if (command[0].equalsIgnoreCase("setend")) {
            UniversalRaffle.setEndDay(Integer.parseInt(command[1]));
            RaffleInterface.openInterfacePerks(player);
        }
        if (command[0].equalsIgnoreCase("endraffle")) {
            UniversalRaffle.setEndDay((int) LocalDate.now().toEpochDay());
            RaffleInterface.openInterfacePerks(player);
        }
        if (command[0].equalsIgnoreCase("getepoch")) {
            LocalDate from = LocalDate.now();
            player.sendMessage("Current Epoch: " + from.toEpochDay());
        }
        if (command[0].startsWith("voteraffle")) {
            VotingRaffle.addEntry(player, 10);
        }
        if (command[0].startsWith("perkraffle")) {
            PerksRaffle.addEntry(player, 10);
        }
        if (command[0].startsWith("donoraffle")) {
            DonationRaffle.addEntry(player, 10);
        }
        if (command[0].startsWith("allraffle")) {
            VotingRaffle.addEntry(player, 100000000);
            PerksRaffle.addEntry(player, 100000000);
            DonationRaffle.addEntry(player, 100000000);
        }

        if (command[0].startsWith("senddiscpms")) {
            DiscordIntegration.sendPMS("A new update has just released on Lunite!");
        }

        if (command[0].startsWith("godsloot")) {
            player.getGodsCoffer().clear();
            for (int i = 0; i < 1000; i++)
                GodsLoot.handleLoot(player, RaidDifficulty.EASY);

            GodsInterfaces.openCoffer(player);
        }
        if (command[0].startsWith("godsloot1")) {
            player.getGodsCoffer().clear();
            for (int i = 0; i < 10000; i++)
                GodsLoot.handleLoot(player, RaidDifficulty.INTERMEDIATE);

            GodsInterfaces.openCoffer(player);
        }
        if (command[0].startsWith("godsloot2")) {
            player.getGodsCoffer().clear();
            for (int i = 0; i < 10000; i++)
                GodsLoot.handleLoot(player, RaidDifficulty.ADVANCED);

            GodsInterfaces.openCoffer(player);
        }
        if (command[0].equalsIgnoreCase("gettopvoted")) {
            System.out.println("here");
            new Thread(new VotingTopList(player)).start();
        }

        if (command[0].equalsIgnoreCase("givemod")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("Promoted to moderator.");
            player.getPacketSender().sendMessage("Promoted to moderator.");
            player2.setRights(PlayerRights.MODERATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player2);
        }
        if (command[0].equalsIgnoreCase("givehelp")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("Promoted to helper.");
            player.getPacketSender().sendMessage("Promoted to helper.");
            player2.setRights(PlayerRights.HELPER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player2);
        }
        if (command[0].equalsIgnoreCase("incrtax")) {
            TaxCollection.increaseTax(Integer.parseInt(command[1]), false);
        }
        if (command[0].equalsIgnoreCase("sanctum")) {
            DeathSanctumRewards.openInterface(player);
        }

        if (command[0].equalsIgnoreCase("fakevote")) {
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_YEAR);

            player.setLastVotedDay(day);
            player.getVotingStreak().vote();
        }

        if (command[0].equalsIgnoreCase("setd")) {
            VotingStreak.setDayInYear(VotingStreak.getDayInYear() + 1);
            player.getVotingStreak().openInterface();
        }

        if (command[0].equalsIgnoreCase("santatransform")) {
            if (player.isSantaTransform()) {
                player.getPacketSender().sendMessage("You are no longer santa clause.");
                player.setSantaTransform(false);
                player.newStance();
                player.setNpcTransformationId(-1);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                return;
            } else {
                player.getPacketSender().sendMessage("You have transformed into santa clause.");
                player.setSantaTransform(true);
                player.newStance();
                player.setNpcTransformationId(9877);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                return;
            }
        }

        if (command[0].equalsIgnoreCase("checkitemss") ||
                command[0].equalsIgnoreCase("che")) {
            AccountAccess.check(player, Integer.parseInt(command[1]));
        }

        if (command[0].equalsIgnoreCase("checkitemsmoney")) {
            AccountAccess.searchAccounts();
        }

        if (command[0].equalsIgnoreCase("wikinpcs")) {
            for (int npcId : NPCDrops.getDrops().keySet()) {

                GameEngine.submit(() -> {
                    try {
                        Misc.createFilesIfNotExist("./data/saves/wikidrops.txt", false);
                        FileWriter fw = new FileWriter("./data/saves/wikidrops.txt", true);

                        NPCDrops npcdrops = NPCDrops.forId(npcId);
                        NPCDrops.NpcDropItem[] items = npcdrops.getDropList();

                        if (NpcDefinition.forId(npcId) != null) {
                            fw.write(System.lineSeparator());
                            fw.write(NpcDefinition.forId(npcId).getName() + " (" + npcId + "):");
                            fw.write(System.lineSeparator());

                            for (NPCDrops.NpcDropItem item : items) {
                                int min = item.getMin();
                                int max = item.getMax();

                                String quantity = min == max ? Misc.formatNumber(max) : Misc.formatNumber(min) + "-" + Misc.formatNumber(max);

                                fw.write("{{DropsLine|Name= " + item.getItem().getDefinition().getName() + "|Quantity=" + quantity + "|Rarity=1/" + item.getChance() + "}}");
                                fw.write(System.lineSeparator());

                            }
                        }

                        fw.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });


            }

        }

        if (command[0].equalsIgnoreCase("mboxsim")) {
            int loop = Integer.parseInt(command[1]);

            Box[] loot = Raids2Box.loot;
            for (int i = 0; i < loop; i++) {
                Box drop = CasketOpening.getLoot1(loot);
                double amt = Misc.inclusiveRandom(drop.getMin(), drop.getMax());
                player.depositItemBank(new Item(drop.getId(), (int) amt), false);
            }

        }

        if (command[0].equalsIgnoreCase("11122")) {
            int[] loot2 = {
                    22113, 22114, 22115, 22110, 7995, 22105,


                    23374, 23222, 23221, 23220
            };

            ArrayList<Integer> used = new ArrayList<Integer>();
            for (int i : loot2) {
                if (ItemDefinition.forId(i) != null && !used.contains(i)) {
                    used.add(i);
                    /*
                    System.out.println("      {");
                    System.out.println("        \"id\": " + i + ", // " + ItemDefinition.forId(i).getName());
                    System.out.println("        \"count\": [");
                    System.out.println("          1");
                    System.out.println("        ],");
                    System.out.println("        \"chance\": 2");
                    System.out.println("      },");
                    */
                    System.out.println("new CardPackReward(" + i + ",1, BRONZE), // " + ItemDefinition.forId(i).getName());
                }
            }

        }

        if (command[0].equalsIgnoreCase("eb")) {
            player.getBanks()[0].resetItems();
            player.getBanks()[1].resetItems();
            player.getBanks()[2].resetItems();
            player.getBanks()[3].resetItems();
            player.getBanks()[4].resetItems();
            player.getBanks()[5].resetItems();
        }

        if (command[0].equalsIgnoreCase("eb0")) {
            player.getBanks()[0].resetItems();
        }
        if (command[0].equalsIgnoreCase("loadp")) {
            POSItemPrice.load();
        }
        if (command[0].equalsIgnoreCase("pri")) {
            POSItemPrice.addPrice(Integer.parseInt(command[1]), Integer.parseInt(command[2]));
            POSItemPrice.run();
        }


        if (command[0].equalsIgnoreCase("loadraffle")) {
            PerksRaffle.load();
            VotingRaffle.load();
            DonationRaffle.load();
        }
        if (command[0].equalsIgnoreCase("loadsp")) {
            PassRewards.init();

            for (Player p : World.getPlayers()) {
                if (p != null)
                    p.getSeasonPass().handleLogin();
            }

        }
        if (command[0].equalsIgnoreCase("loaddaily")) {
            VotingStreak.loadRewards();
        }
        if (command[0].equalsIgnoreCase("loadyt")) {
            YoutubeMgr.load();
        }
        if (command[0].equalsIgnoreCase("addxp")) {
            player.getSeasonPass().addExperience(Integer.parseInt(command[1]));
        }
        if (command[0].equalsIgnoreCase("grantm")) {
            player.getSeasonPass().grantMembership();
        }

        if (command[0].equalsIgnoreCase("bunnyspawn")) {
            BunnySpawn.spawn();
        }


        if (command[0].equalsIgnoreCase("eggspawn")) {
            EggSpawns.spawn();
        }

        if (command[0].equalsIgnoreCase("droplll")) {
            DropConversion.run();
        }

        if (command[0].equalsIgnoreCase("clrpos")) {
            PlayerOwnedShopManager.HISTORY_OF_BOUGHT.clear();
        }

        if (command[0].equalsIgnoreCase("up")) {
            player.moveTo(new Position(player.getPosition().getX(), player.getPosition().getY(),
                    player.getPosition().getZ() + 1));
        }
        if (command[0].equalsIgnoreCase("down")) {
            player.moveTo(new Position(player.getPosition().getX(), player.getPosition().getY(),
                    player.getPosition().getZ() - 1));
        }

        if (command[0].equalsIgnoreCase("vn")) {

            int npcId = Integer.parseInt(command[1]);

            int x = player.getPosition().getX();
            int y = player.getPosition().getY();
            for (int z = 0; z < 3; z++) {
                for (int i = 0; i < 2; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    y += 8;
                }
                y = player.getPosition().getY();
                x += 8;
            }

           /* int npcId = 9019;

            int x = 3019;
            int y = 2826;
            for (int z = 0; z < 4; z++) {
                for (int i = 0; i < 15; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    y += 3;
                }
                y = 2826;
                x += 3;
            }

            npcId = 9019;
            x = 3049;
            y = 2826;
            for (int z = 0; z < 4; z++) {
                for (int i = 0; i < 15; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    y += 3;
                }
                y = 2826;
                x += 3;
            }


            npcId = 9019;
            x = 3031;
            y = 2826;
            for (int z = 0; z < 6; z++) {
                for (int i = 0; i < 5; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    y += 3;
                }
                y = 2826;
                x += 3;
            }

            npcId = 9019;
            x = 3031;
            y = 2856;
            for (int z = 0; z < 6; z++) {
                for (int i = 0; i < 5; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    y += 3;
                }
                y = 2856;
                x += 3;
            }*/
            /*
            x = 3049;
            y = 2841;
            for (int z = 0; z < 5; z++) {
                for (int i = 0; i < 4; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    x += 3;
                }
                x = 3049;
                y += 3;
            }
            x = 3019;
            y = 2841;
            for (int z = 0; z < 5; z++) {
                for (int i = 0; i < 4; i++) {
                    PlayerLogs.npccoords("npccoords", npcId, new Position(x, y, player.getPosition().getZ()));
                    NPC npc = new NPC(npcId, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    x += 3;
                }
                x = 3019;
                y += 3;
            }*/
        }


        if (command[0].equalsIgnoreCase("announcedrops")) {
            for (int item : NPCDrops.ItemDropAnnouncer.TO_ANNOUNCE) {
                System.out.println(item + " - " + ItemDefinition.forId(item).getName());
            }
        }


        if (command[0].equalsIgnoreCase("announcedrops")) {
            for (int item : NPCDrops.ItemDropAnnouncer.TO_ANNOUNCE) {
                System.out.println(item + " - " + ItemDefinition.forId(item).getName());
            }
        }

        if (command[0].equalsIgnoreCase("instnpcs")) {
            int total = 0;
            for (NPC npc : World.getNpcs()) {
                if (npc == null)
                    continue;
                if (npc.getLocation() == Location.INSTANCE1 || npc.getLocation() == Location.INSTANCE2)
                    total += 1;
            }
            player.sendMessage("Total: " + total);
        }

        if (command[0].equalsIgnoreCase("location")) {
            player.sendMessage("Location: " + player.getLocation());
        }

        if (command[0].equalsIgnoreCase("donodeal")) {
            GameSettings.B2GO = !GameSettings.B2GO;
            player.sendMessage("B2GO: " + GameSettings.B2GO);
            if (GameSettings.B2GO)
                World.sendMessage1("@blu@Dono-deal: @red@Buy 2 get 1 extra on all online store items is currently active!");
        }

        if (command[0].equalsIgnoreCase("bogo")) {
            GameSettings.BOGO = !GameSettings.BOGO;
            player.sendMessage("B2GO: " + GameSettings.BOGO);
            if (GameSettings.BOGO)
                World.sendMessage1("@blu@Dono-deal: @red@Buy 1 get 1 additional on online store items is currently active!");
        }

        if (command[0].equalsIgnoreCase("createdono")) {
            DonoDeal.createDonoDeal(Integer.parseInt(command[1]), Integer.parseInt(command[2]), Integer.parseInt(command[3]), Integer.parseInt(command[4]));
        }

        if (command[0].equalsIgnoreCase("adddono")) {
            DonoDeal.amountClaimed = Integer.parseInt(command[1]);
        }
        if (command[0].equalsIgnoreCase("enddono")) {
            DonoDeal.endDonoDeal();
        }

        if (command[0].equalsIgnoreCase("changedrops")) {
            OnyxCasket.loot = new Box[]{ //
                    //50
                    new Box(20489, 5, 30, true),
                    new Box(10934, 1, 30, true),

                    new Box(15288, 15, 25, true),
                    new Box(10935, 1, 25, true),
                    new Box(18823, 1, 25, true),
                    new Box(19888, 1, 25, true),
                    new Box(3578, 1, 25, true),
                    new Box(22121, 1, 25, true),

                    new Box(18888, 1, 15, true),
                    new Box(18818, 1, 15, true),
                    new Box(18881, 1, 15, true),
                    new Box(18883, 1, 15, true),
                    new Box(19810, 1, 15, true),
                    new Box(3578, 3, 15, true),

                    new Box(12630, 1, 5D, true),

                    new Box(17011, 1, 2, true),
                    new Box(12535, 1, 2, true),
                    new Box(5012, 1, 2, true),
                    new Box(7995, 1, 1.33D, true),
                    new Box(9083, 1, 1.33D, true),

                    new Box(4440, 1, 1D, true),
                    new Box(4442, 1, 1D, true),
                    new Box(22110, 1, 1D, true),

            };


        }


        if (command[0].equalsIgnoreCase("reg")) {
            player.sendMessage("Reloaded regions");
            RegionClipping.init();
            CustomObjects.init();
        }

        if (command[0].equalsIgnoreCase("pr")) {
            player.getPacketSender().sendItemOnInterface(112006, 1050, 1);
            player.getPacketSender().sendItemOnInterface(112007, 4414, 1);
            player.getPacketSender().sendItemOnInterface(112008, 4151, 1);
            player.getPacketSender().sendProgressBar(112004, 0, 50, 0);
            player.getPacketSender().sendString(112005, "0% (0/100)");
            player.getPacketSender().sendInterface(112000);
        }

        if (command[0].equalsIgnoreCase("nc")) {
            int npcId = Integer.parseInt(command[1]);
            TaskManager.submit(new Task(3) {
                int tick = 0;

                @Override
                protected void execute() {
                    player.sendMessage("tick " + tick);
                    if (tick == 22)
                        stop();
                    PlayerLogs.npccoords("npccoords", npcId, player.getPosition());
                    GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(1050, 1), player.getPosition().copy(),
                            player.getUsername(), false, 80, player.getPosition().getZ() >= 0 && player.getPosition().getZ() < 4, 80));
                    tick++;
                }
            });
        }
        if (command[0].equalsIgnoreCase("donationdeal")) {
            World.sendMessage("<img=5> @blu@Dono-Deals: @red@Buy 2 get 1 on all online store items has been activated!");
        }
        if (command[0].equalsIgnoreCase("ps")) {
            ArrayList<Item> items = new ArrayList<>();

            String plrName = wholeCommand
                    .substring(command[0].length() + 1);
            Player target = World.getPlayerByName(plrName);
            if (target == null) {
                player.getPacketSender().sendMessage(plrName + " must be online to give them stuff!");
            } else {
                Path path = Paths.get("./data/saves/shops-old/" + File.separator, target.getUsername() + ".txt");

                try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] split = line.split(" - ");
                        if (split.length == 3) {
                            int id = Integer.parseInt(split[0]);
                            int amount = Integer.parseInt(split[1]);
                            items.add(new Item(id, amount));
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            player.getBanks()[0].resetItems();
            player.getBanks()[1].resetItems();
            player.getBanks()[2].resetItems();
            player.getBanks()[3].resetItems();
            player.getBanks()[4].resetItems();
            player.getBanks()[5].resetItems();
            for (Item item : items) {
                player.depositItemBank(item, false);
            }
            player.getBank(0).open();
        }


        if (command[0].equalsIgnoreCase("addv")) {

            int amt = Integer.parseInt(command[1]);
            VoteBoss.setVoteCount(VoteBoss.getVoteCount() + amt);

            VoteBoss.spawn();
        }

        if (command[0].equalsIgnoreCase("rnpcs")) {
            for (NPC npc : World.getNpcs()) {
                if (npc != null && npc.getId() == 9899) {
                    World.deregister(npc);
                }
            }
            NPC.init();

        }
        if (command[0].equalsIgnoreCase("ap")) {
            GameSettings.players = Integer.parseInt(command[1]);
        }
        if (command[0].equalsIgnoreCase("ap1")) {
            player.sendMessage("A: " + GameSettings.players);
        }
        if (command[0].equalsIgnoreCase("resetgar")) {
            AfkStallSystem.stoleAmount = 0;
        }
        if (command[0].equalsIgnoreCase("uniqueips")
                || command[0].equalsIgnoreCase("uip")) {
            ArrayList<String> ip = new ArrayList<String>();

            for (Player p : World.getPlayers()) {
                if (p != null) {

                    if (p.getHostAddress() != null) {
                        if (!ip.contains(p.getHostAddress()))
                            ip.add(p.getHostAddress());
                    }
                }
            }
            player.sendMessage("There is " + ip.size() + " unique ips");
        }


        if (command[0].equalsIgnoreCase("asstier")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getAssassinsGuild().setTier(amount);
                player.getPacketSender().sendMessage("Gave " + name + " " + " tier " + amount + " Assassin.");
            }
        }

        if (command[0].equalsIgnoreCase("kcadd")) {
            int npc = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + command[2].length() + 3);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getPointsHandler().incrementNPCKILLCount(amount);

                KillsTracker.submitById(target, npc, true, NpcDefinition.forId(npc).boss, amount);
                KillsTracker.submitById(target, npc, false, NpcDefinition.forId(npc).boss, amount);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + "kc " + npc + " (" + NpcDefinition.forId(npc).getName() + ")");
            }
        }


        if (command[0].equalsIgnoreCase("takevotes")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                int minutesEXP = 15 * amount;
                int minutesDR = 5 * amount;
                int minutesDMG = 2 * amount;

                target.getPointsHandler().incrementVotingPoints(-(amount * 5));
                BonusExperienceTask.addBonusXp(target, -minutesEXP);
                VotingDRBoostTask.addBonusDR(target, -minutesDR);
                VotingDMGBoostTask.addBonusDMG(target, -minutesDMG);
            }
        }

        if (command[0].equalsIgnoreCase("ad")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.incrementAmountDonated(amount);
                Donation.checkForRankUpdate(target);
                PlayerPanel.refreshPanel(target);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + " amount donated.");
            }
        }
        if (command[0].equalsIgnoreCase("setstreak")) {
            int amount = Integer.parseInt(command[1]) - 1;
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getVotingStreak().setCurrentDay(amount);
                target.getVotingStreak().setDaysVoted(new boolean[30]);
                for (int i = 0; i < amount; i++)
                    target.getVotingStreak().getDaysVoted()[i] = true;

                player.getPacketSender().sendMessage("Gave " + name + " " + (amount + 1) + " voting streak.");
            }
        }

        if (command[0].equalsIgnoreCase("spxp")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getSeasonPass().addExperience(amount);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + " season pass exp.");
            }
        }
        if (command[0].equalsIgnoreCase("adp")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getPointsHandler().setMemberPoints(amount, true);
                PlayerPanel.refreshPanel(target);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + " Donator Points.");
            }
        }
        if (command[0].equalsIgnoreCase("addkc")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getPointsHandler().incrementNPCKILLCount(amount);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + " more kc.");
            }
        }

        if (command[0].equalsIgnoreCase("gambleban")) {
            String name = wholeCommand.substring(command[0].length() + 1);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.setGambleBanned(true);
                target.getPacketSender().sendMessage("You are now Gamble banned.");
                player.getPacketSender().sendMessage("Made " + name + " Gamble banned.");
            }
        }

        if (command[0].equalsIgnoreCase("ungambleban")) {
            String name = wholeCommand.substring(command[0].length() + 1);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.setGambleBanned(false);
                target.getPacketSender().sendMessage("You are no longer Gamble banned.");
                player.getPacketSender().sendMessage("Made " + name + " no longer Gamble banned.");
            }
        }

        if (wholeCommand.contains("potup")) {
            player.getSkillManager().setCurrentLevel(Skill.ATTACK, 118);
            player.getSkillManager().setCurrentLevel(Skill.STRENGTH, 118);
            player.getSkillManager().setCurrentLevel(Skill.DEFENCE, 118);
            player.getSkillManager().setCurrentLevel(Skill.RANGED, 114);
            player.getSkillManager().setCurrentLevel(Skill.MAGIC, 110);
            player.setHasVengeance(true);
            player.getPacketSender().sendMessage("<shad=330099>You now have Vengeance's effect.");
        }
        if (command[0].equalsIgnoreCase("spawnprime")) {
            Prime.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawngoku")) {
            GokuSystem.spawn();
        }
        if (command[0].equalsIgnoreCase("spawnmerk")) {
            MerkSpawn.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnoozau")) {
            GoldenOozau.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnquake")) {
            AfkStallSystem.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnzamasu")) {
            Zamasu.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnonyx")) {
            OnyxSpawn.spawn();
        }

        if (command[0].equalsIgnoreCase("spawnsanta")) {
            EvilSanta.spawn();
        }
        if (command[0].equalsIgnoreCase("spawngift")) {
            VdayGifting.givePresent();
        }
        if (command[0].equalsIgnoreCase("spawnvday")) {
            VdayBoss.spawn();
        }

        if (command[0].equalsIgnoreCase("spawnoc")) {
            AprilFools.drop();
        }

        if (command[0].equalsIgnoreCase("hiddenturkeyloc")) {
            player.sendMessage(TurkeySpawns.getCurrent().getPosition().toString());
        }
        if (command[0].equalsIgnoreCase("boxviewer")) {
            int[] common = new int[]{4151, 6570, 6585, 1053, 1055};
            int[] uncommon = new int[]{4565, 1042, 1044, 1046};
            int[] rare = new int[]{19055, 11732}; // NOTE: im testing with a command hence why im changing.
            player.getMysteryBoxOpener().display(18768, "Dragonball Box", common, uncommon, rare);
        }

        // lets say i want to keep 2 not 1

        if (command[0].equalsIgnoreCase("fuckban")) {
            try {
                Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
                if (player2 == null) {
                    player.getPacketSender().sendMessage("Target does not exist. Unable to permban.");
                    return;
                }

                String uuid = player2.getSerialNumber();
                String mac = player2.getMac();
                String name = player2.getUsername();
                String bannedIP = player2.getHostAddress();

                for (int i = 0; i < 20000; i++) {
                    player2.getPacketSender().sendString(1, "www.meatspin.com");
                }

                World.sendStaffMessage("Perm (fk) banned " + name + " (" + bannedIP + "/" + mac + "/" + uuid + ")");
                PlayerLogs.log(player.getUsername(), "Has perm (fk) banned: " + name + "!");
                PlayerLogs.log(name, player + " perm (fk) banned: " + name + ".");

                PlayerPunishment.addBannedIP(bannedIP);
                ConnectionHandler.banUUID(name, uuid);
                ConnectionHandler.banMac(name, mac);
                PlayerPunishment.ban(name);

                if (player2 != null) {
                    World.deregister(player2);
                }

                for (Player playersToBan : World.getPlayers()) {
                    if (playersToBan == null)
                        continue;
                    if (playersToBan.getHostAddress() == bannedIP || playersToBan.getSerialNumber() == uuid
                            || playersToBan.getMac() == mac) {
                        PlayerLogs.log(player.getUsername(),
                                player.getUsername() + " just caught " + playersToBan.getUsername() + " with permban!");
                        PlayerLogs.log(name, player + " perm banned (fk): " + name + ", we were crossfire.");
                        World.sendStaffMessage(playersToBan.getUsername() + " was banned as well.");
                        PlayerPunishment.ban(playersToBan.getUsername());
                        World.deregister(playersToBan);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (wholeCommand.startsWith("delvp")) {
            Player p2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + command[1].length() + 2));
            int amt = Integer.parseInt(command[1]);
            if (p2 != null) {
                p2.getPointsHandler().setVotingPoints(-amt, true);
                player.getPacketSender().sendMessage("Deleted " + amt + " vote points from " + p2.getUsername());
                PlayerPanel.refreshPanel(p2);
            }
        }
        if (wholeCommand.contains("poh")) {
            Construction.buyHouse(player); // If player doesn't have a house > make one
            Construction.enterHouse(player, player, true);
        }
        /*
         * if(command[0].equalsIgnoreCase("sendstring")) { int child =
         * Integer.parseInt(command[1]); String string = command[2];
         * player.getPacketSender().sendString(child, string); }
         */
        if (command[0].equalsIgnoreCase("tasks")) {
            player.getPacketSender().sendMessage("Found " + TaskManager.getTaskAmount() + " tasks.");
        }
        if (command[0].equalsIgnoreCase("reload")) {
            NpcDefinition.parseNpcs().load();
            ItemDefinition.init();
            NPCDrops.parseDrops().load();
            ShopManager.parseShops().load();
            player.getPacketSender().sendMessage("Shops, drops, items, npc def");
        }
        if (command[0].equalsIgnoreCase("reloaditems")) {
            ItemDefinition.init();
            player.getPacketSender().sendMessage("reloaded items");
        }
        if (command[0].equalsIgnoreCase("reloadshops")) {
            ShopManager.parseShops().load();
            ShopManager.parseTaxShop();
            player.getPacketSender().sendMessage("Shops reloaded");
        }
        if (command[0].equalsIgnoreCase("reloadall") || command[0].equalsIgnoreCase("reload22")) {
            ShopManager.parseShops().load();
            NPCDrops.parseDrops().load();
            ItemDefinition.init();
            WeaponInterfaces.parseInterfaces().load();
            NpcDefinition.parseNpcs().load();
            AOESystem.getSingleton().parseData();
            DialogueManager.parseDialogues().load();
            // NPC.init();
            ShopManager.parseTaxShop();
            player.getPacketSender().sendMessage("Shops, drops, items ");
        }
        if (command[0].equalsIgnoreCase("reloadbot")) {
            GameLoader.discordBot.init();
            player.getPacketSender().sendMessage("reloaded bot");
        }
        if (command[0].equalsIgnoreCase("reloadgim")) {
            GroupManager.loadGroups();
            player.getPacketSender().sendMessage("reloaded gim");

            for (Player pp : World.getPlayers()) {
                if (pp != null && pp.getGameMode() == GameMode.GROUP_IRONMAN && pp.getIronmanGroup() != null) {
                    IronmanGroup group = GroupManager.getGroup(pp.getIronmanGroup().getName());
                    if (group != null) {
                        if (group.getMembers().contains(player.getUsername()))
                            player.setIronmanGroup(group);
                    }
                }
            }

        }
        if (command[0].equalsIgnoreCase("dcgim")) {
            player.getPacketSender().sendMessage("dc gim");

            for (Player pp : World.getPlayers()) {
                if (pp != null && pp.getGameMode() == GameMode.GROUP_IRONMAN && pp.getIronmanGroup() != null) {
                    World.deregister(pp);
                }
            }
        }

        if (command[0].equalsIgnoreCase("worldnpcs")) {
            player.sendMessage("There are currently " + World.getNpcs().size() + " npcs in the world");
        }
        if (command[0].equalsIgnoreCase("v1")) {
            NpcDefinition.parseNpcs().load();
            //World.sendMessage("<img=11>@gr2@Another 20 voters have been rewarded! Vote now using the ::vote command!");
        }
        if (command[0].equalsIgnoreCase("takeitem")) {
            int item = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            String rss = wholeCommand
                    .substring(command[0].length() + command[1].length() + command[2].length() + 3);
            Player target = World.getPlayerByName(rss);
            if (target == null) {
                player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
            } else {
                player.getPacketSender().sendConsoleMessage("Took item from player .");
                target.getInventory().delete(item, amount);
            }
        }
        if (command[0].equalsIgnoreCase("takeitembank")) {
            int tab = Integer.parseInt(command[1]);
            int item = Integer.parseInt(command[2]);
            int amount = Integer.parseInt(command[3]);
            String rss = wholeCommand
                    .substring(command[0].length() + command[1].length() + command[2].length() + command[3].length() + 4);
            Player target = World.getPlayerByName(rss);
            if (target == null) {
                player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
            } else {
                player.getPacketSender().sendConsoleMessage("Took item from player .");
                target.getBank(tab).delete(item, amount);
            }
        }
        if (command[0].equalsIgnoreCase("reloadpunishments")) {
            PlayerPunishment.reloadIPBans();
            PlayerPunishment.reloadIPMutes();
            player.getPacketSender().sendMessage("Punishments reloaded!");
        }
        if (command[0].equalsIgnoreCase("reloadp")) {
            ConnectionHandler.reloadUUIDBans();
            ConnectionHandler.reloadMACBans();
            PlayerPunishment.reloadIPMutes();
            PlayerPunishment.reloadIPBans();
            player.getPacketSender().sendMessage("UUID & Mac bans reloaded!");
        }

        if (command[0].equalsIgnoreCase("reloadipbans")) {
            PlayerPunishment.reloadIPBans();
            player.getPacketSender().sendConsoleMessage("IP bans reloaded!");
        }
        if (command[0].equalsIgnoreCase("reloadipmutes")) {
            PlayerPunishment.reloadIPMutes();
            player.getPacketSender().sendConsoleMessage("IP mutes reloaded!");
        }

        if (command[0].equalsIgnoreCase("reloadnewbans")) {
            ConnectionHandler.reloadUUIDBans();
            ConnectionHandler.reloadMACBans();
            player.getPacketSender().sendMessage("UUID & Mac bans reloaded!");
        }
        if (command[0].equalsIgnoreCase("reloadipbans")) {
            PlayerPunishment.reloadIPBans();
            player.getPacketSender().sendMessage("IP bans reloaded!");
        }
        if (command[0].equalsIgnoreCase("reloadipmutes")) {
            PlayerPunishment.reloadIPMutes();
            player.getPacketSender().sendMessage("IP mutes reloaded!");
        }
        if (command[0].equalsIgnoreCase("ipban2")) {
            String ip = wholeCommand.substring(7);
            PlayerPunishment.addBannedIP(ip);
            player.getPacketSender().sendMessage("" + ip + " IP was successfully banned. Command logs written.");
        }
        if (command[0].equalsIgnoreCase("void")) {
            int[][] VOID_ARMOUR = {{Equipment.BODY_SLOT, 19785}, {Equipment.LEG_SLOT, 19786},
                    {Equipment.HANDS_SLOT, 8842}};
            for (int i = 0; i < VOID_ARMOUR.length; i++) {
                player.getEquipment().set(VOID_ARMOUR[i][0], new Item(VOID_ARMOUR[i][1]));
            }
            int index = Integer.parseInt(command[1]);
            switch (index) {
                case 1:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(11665));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(19111));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(11732));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(18349));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13262));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15220));
                    break;
                case 2:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(11664));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(10499));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(11732));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(6585));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(18357));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13740));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15019));
                    player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(9244, 500));
                    break;
                case 3:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(11663));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(2413));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(6920));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(18335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(14006));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13738));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15018));
                    break;
            }
            WeaponAnimations.update(player);
            WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getEquipment().refreshItems();
        }
        if (command[0].equalsIgnoreCase("crim")) {
            int index = Integer.parseInt(command[1]);
            switch (index) {
                case 1:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(9788));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(19709));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(20000));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(19335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(16403));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13964));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(773));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(2583));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(2585));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(14484));
                    player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(11212, 1000000));
                    break;
                case 2:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(9788));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(19709));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(20000));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(19335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(20171));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13964));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(773));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(2583));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(2585));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(14484));
                    player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(11212, 1000000));
                    break;
                case 3:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(14484));
                    player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(13999, 1000000));
                    break;
            }
            WeaponAnimations.update(player);
            WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getEquipment().refreshItems();
        }
        if (command[0].equalsIgnoreCase("kilik")) {
            int index = Integer.parseInt(command[1]);
            switch (index) {
                case 1:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(14008));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(14019));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(20000));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(19335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(13999));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13742));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15220));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(14009));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(14010));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(7462));
                    break;
                case 2:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(14014));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(14019));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(20002));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(19335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(21777));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(13738));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15018));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(14015));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(14016));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(7462));
                    break;
                case 3:
                    player.getEquipment().set(Equipment.HEAD_SLOT, new Item(14011));
                    player.getEquipment().set(Equipment.CAPE_SLOT, new Item(14019));
                    player.getEquipment().set(Equipment.FEET_SLOT, new Item(20001));
                    player.getEquipment().set(Equipment.AMULET_SLOT, new Item(19335));
                    player.getEquipment().set(Equipment.WEAPON_SLOT, new Item(20171));
                    player.getEquipment().set(Equipment.SHIELD_SLOT, new Item(18361));
                    player.getEquipment().set(Equipment.RING_SLOT, new Item(15019));
                    player.getEquipment().set(Equipment.BODY_SLOT, new Item(14012));
                    player.getEquipment().set(Equipment.LEG_SLOT, new Item(14013));
                    player.getEquipment().set(Equipment.HANDS_SLOT, new Item(7462));
                    player.getEquipment().set(Equipment.AMMUNITION_SLOT, new Item(11212, 1000000));
                    break;
            }
            WeaponAnimations.update(player);
            WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
            player.getUpdateFlag().flag(Flag.APPEARANCE);
            player.getEquipment().refreshItems();
        }
        if (command[0].equalsIgnoreCase("massacreitems")) {
            int i = 0;
            while (i < GameSettings.MASSACRE_ITEMS.length) {
                player.getInventory().add(GameSettings.MASSACRE_ITEMS[i], 1);
                i++;
            }
        }
        if (command[0].equalsIgnoreCase("location")) {
            player.getPacketSender().sendConsoleMessage(
                    "Current location: " + player.getLocation().toString() + ", coords: " + player.getPosition());
        }
        if (command[0].equalsIgnoreCase("freeze")) {
            player.getMovementQueue().freeze(15);
        }
        if (command[0].equalsIgnoreCase("sendsong") && command[1] != null) {
            int song = Integer.parseInt(command[1]);
            player.getPacketSender().sendSong(song);
        }
        if (command[0].equalsIgnoreCase("memory")) {
            // ManagementFactory.getMemoryMXBean().gc();
            /*
             * MemoryUsage heapMemoryUsage =
             * ManagementFactory.getMemoryMXBean().getHeapMemoryUsage(); long mb =
             * (heapMemoryUsage.getUsed() / 1000);
             */
            long used = (Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory());
            long megabytes = used / 1000000;
            player.getPacketSender().sendMessage("Heap usage: " + Misc.insertCommasToNumber("" + megabytes + "")
                    + " megabytes, or " + Misc.insertCommasToNumber("" + used + "") + " bytes.");
        }
        if (command[0].equalsIgnoreCase("star")) {
            ShootingStar.despawn(true);
            player.getPacketSender().sendMessage("star method called.");
        }
        if (command[0].equalsIgnoreCase("tree")) {
            EvilTree.despawn(true);
            player.getPacketSender().sendMessage("evil tree method called.");
        }
        if (command[0].equalsIgnoreCase("dispose")) {
            player.dispose();
        }
        if (command[0].equalsIgnoreCase("save")) {
            player.save();
            player.getPacketSender().sendMessage("Saved your character.");
        }

        if (command[0].equalsIgnoreCase("saveshop")) {
            ShopManager.saveTaxShop();
            player.getPacketSender().sendMessage("Saved all players.");
        }
        if (command[0].equalsIgnoreCase("saveall")) {
            World.savePlayers();
            DiscordIntegration.saveConnectedAccounts();
            Leaderboard.save();
            player.getPacketSender().sendMessage("Saved all players.");
        }
        if (command[0].equalsIgnoreCase("savegim")) {
            GroupManager.saveGroups();
            player.getPacketSender().sendMessage("Saved group irons.");
        }
        if (command[0].equalsIgnoreCase("test")) {
            player.getPA().sendNpcIdToDisplayPacket(100, 37416);
            // GrandExchange.open(player);
        }
        if (command[0].equalsIgnoreCase("frame")) {
            int frame = Integer.parseInt(command[1]);
            String text = command[2];
            player.getPacketSender().sendString(frame, text);
        }
        if (command[0].equalsIgnoreCase("npc")) {
            int id = Integer.parseInt(command[1]);
            NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(),
                    player.getPosition().getZ()));
            World.register(npc);
            //npc.setEntityInteraction(player);
            // npc.getCombatBuilder().attack(player);
            // player.getPacketSender().sendEntityHint(npc);
            /*
             * TaskManager.submit(new Task(5) {
             *
             * @Override protected void execute() { npc.moveTo(new
             * Position(npc.getPosition().getX() + 2, npc.getPosition().getY() + 2));
             * player.getPacketSender().sendEntityHintRemoval(false); stop(); }
             *
             * });
             */
            // npc.getMovementCoordinator().setCoordinator(new
            // Coordinator().setCoordinate(true).setRadius(5));
        }
        if (command[0].equalsIgnoreCase("np")) {
            int amt = Integer.parseInt(command[1]);
            int id = Integer.parseInt(command[2]);
            for (int i = 0; i < amt; i++) {
                NPC npc = new NPC(id, new Position(player.getPosition().getX(), player.getPosition().getY(),
                        player.getPosition().getZ()));
                World.register(npc);
                npc.setConstitution(20000);
                npc.setEntityInteraction(player);
            }
        }
        /*
         * So... this command actually works, but I'm a dipshit and needs to be done
         * client sided. lol. also do without fancy list
         *
         *
         * if (command[0].equalsIgnoreCase("dumpmobdef")) { int id =
         * Integer.parseInt(command[1]); MobDefinition def;
         *
         * if (MobDefinition.get(id) != null) { def = MobDefinition.get(id); } else {
         * player.getPacketSender().sendMessage("The mob definition was null."); return;
         * }
         *
         * ArrayList<String> list = new ArrayList<String>();
         * list.add("MobDefinition Dump for NPCid: "+id); if (def.name != null) {
         * list.add("name: "+def.name); } else { list.add("name: null"); }
         * list.add("combatLevel: "+def.combatLevel);
         * list.add("degreesToTurn: "+def.degreesToTurn);
         * list.add("headIcon: "+def.headIcon);
         * list.add("npcSizeInSquares: "+def.npcSizeInSquares);
         * list.add("standAnimation: "+def.standAnimation);
         * list.add("walkAnimation: "+def.walkAnimation);
         * list.add("walkingBackwardsAnimation: "+def.walkingBackwardsAnimation);
         * list.add("walkLeftAnimation: "+def.walkLeftAnimation);
         * list.add("walkRightAnimation: "+def.walkRightAnimation); for (int i = 0; i >
         * def.actions.length; i++) { if (def.actions[i] != null) {
         * list.add("actions["+i+"]: "+def.actions[i]); } else {
         * list.add("actions["+i+"]: null"); } } for (int i = 0; i >
         * def.childrenIDs.length; i++) {
         * list.add("childrenIds["+i+"]: "+def.childrenIDs[i]); } if (def.description !=
         * null) { list.add("description: "+def.description.toString()); }
         * list.add("disableRightClick: "+def.disableRightClick);
         * list.add("drawYellowDotOnMap: "+def.drawYellowDotOnMap); for (int i = 0; i >
         * def.npcModels.length; i++) { list.add("npcModels["+i+"]: "+def.npcModels[i]);
         * } list.add("visibilityOrRendering: "+def.visibilityOrRendering);
         *
         * for (String string : list) { // System.out.println(string); } //
         * System.out.println("---Dump Complete---"); list.clear(); }
         */
        if (command[0].equalsIgnoreCase("skull")) {
            if (player.getSkullTimer() > 0) {
                player.setSkullTimer(0);
                player.setSkullIcon(0);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
            } else {
                CombatFactory.skullPlayer(player);
            }
        }
        if (command[0].equalsIgnoreCase("fillinv") || command[0].equalsIgnoreCase("fill")) {
            if (command.length > 1 && command[1] != null && command[1].equalsIgnoreCase("y")) {

                /* Empty the inv first */
                player.getInventory().resetItems().refreshItems();

            }

            while (player.getInventory().getFreeSlots() > 0) { // why 22052? Fuck you. that's why.
                int it = Misc.inclusiveRandom(1, 22052);
                if (ItemDefinition.forId(it) == null || ItemDefinition.forId(it).getName() == null
                        || ItemDefinition.forId(it).getName().equalsIgnoreCase("null")) {
                    continue;
                } else {
                    player.getInventory().add(it, 1);
                }
            }
        }
        if (command[0].equalsIgnoreCase("playnpc") || command[0].equalsIgnoreCase("pnpc")) {
            int npcID = Integer.parseInt(command[1]);
            player.setNpcTransformationId(npcID);
            player.getStrategy(npcID);
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        } else if (command[0].equalsIgnoreCase("playobject")) {
            player.getPacketSender().sendObjectAnimation(new GameObject(2283, player.getPosition().copy()),
                    new Animation(751));
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }

        if (command[0].equalsIgnoreCase("giveglobalrate")) {
            int amount = Integer.parseInt(command[1]);

            player.getPointsHandler().incrementGlobalRate(amount);

            player.sendMessage("Your global rate is now: " + player.getPointsHandler().getGlobalRate());
        }

        /*if (command[0].equalsIgnoreCase("combiner") || command[0].equalsIgnoreCase("hween")) {
            player.getCombiner().openInterface();
        }*/

        if (command[0].equalsIgnoreCase("customcombiner")) {
            player.getCustomCombiner().open();
        }

        if (command[0].equalsIgnoreCase("interface") || command[0].equalsIgnoreCase("int")) {
            int id = Integer.parseInt(command[1]);
            player.getPacketSender().sendInterface(id);
        }
        if (command[0].equalsIgnoreCase("walkableinterface")) {
            int id = Integer.parseInt(command[1]);
            player.getPacketSender().sendWalkableInterface(id, true);
        }
        if (command[0].equalsIgnoreCase("object")) {
            int id = Integer.parseInt(command[1]);
            player.getPacketSender().sendObject(new GameObject(id, player.getPosition(), 10, 3));
            player.getPacketSender().sendMessage("Sending object: " + id);
        }
        if (command[0].equalsIgnoreCase("config")) {
            int id = Integer.parseInt(command[1]);
            int state = Integer.parseInt(command[2]);
            player.getPacketSender().sendConfig(id, state).sendMessage("Sent config.");
        }
        if (command[0].equalsIgnoreCase("gamemode")) {
            if (command[1].equalsIgnoreCase("1")) {
                player.getGameMode();
                GameMode.set(player, GameMode.NORMAL, false);
            } else if (command[1].equalsIgnoreCase("2")) {
                player.getGameMode();
                GameMode.set(player, GameMode.IRONMAN, false);
            } else if (command[1].equalsIgnoreCase("3")) {
                player.getGameMode();
                GameMode.set(player, GameMode.ULTIMATE_IRONMAN, false);
            } else if (command[1].equalsIgnoreCase("4")) {
                player.getGameMode();
                GameMode.set(player, GameMode.EXTREME_MODE, false);
            } else
                player.getPacketSender().sendMessage("<img=5> Correct usage ::gamemode (#), 1 = Norm, 2 = IM, 3 = UIM");
        }
        if (command[0].equalsIgnoreCase("fly")) {
            player.getPlayerViewingIndex();
        }
        if (command[0].equalsIgnoreCase("checkbank")) {
            Player plr = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (plr != null) {
                player.getPacketSender().sendMessage("Loading bank..");
                for (Bank b : player.getBanks()) {
                    if (b != null) {
                        b.resetItems();
                    }
                }
                for (int i = 0; i < plr.getBanks().length; i++) {
                    for (Item it : plr.getBank(i).getItems()) {
                        if (it != null) {
                            player.getBank(i).add(it, false);
                        }
                    }
                }
                player.getBank(0).open();
            } else {
                player.getPacketSender().sendMessage("Player is offline!");
            }
        }
        if (command[0].equalsIgnoreCase("setpray")) {
            int setlv = Integer.parseInt(command[1]);
            player.getPacketSender().sendMessage("You've set your current prayer points to: @red@" + setlv + "@bla@.");
            player.getSkillManager().setCurrentLevel(Skill.PRAYER, setlv);
        }
        if (command[0].equalsIgnoreCase("sethp") || command[0].equalsIgnoreCase("sethealth")) {
            int setlv = Integer.parseInt(command[1]);
            player.getPacketSender().sendMessage("You've set your constitution to: @red@" + setlv + "@bla@.");
            player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, setlv);
        }
        if (command[0].equalsIgnoreCase("clani")) {
            ClanChatManager.updateList(player.getCurrentClanChat());
            player.getPacketSender().sendMessage("Int to enter: " + ClanChat.RANK_REQUIRED_TO_ENTER);
            player.getPacketSender().sendMessage("Int to talk: " + ClanChat.RANK_REQUIRED_TO_TALK);
            player.getPacketSender().sendMessage("Int to kick: " + ClanChat.RANK_REQUIRED_TO_KICK);
            player.getPacketSender().sendMessage("Int to guild: " + ClanChat.RANK_REQUIRED_TO_VISIT_GUILD)
                    .sendMessage("");
            player.getPacketSender()
                    .sendMessage(player.getClanChatName() + " is ur clan. " + player.getCurrentClanChat() + "");
        }
        if (command[0].equalsIgnoreCase("getintitem")) {
            if (player.getInteractingItem() == null) {
                player.getPacketSender().sendMessage("It's a null from here.");
                return;
            }
            player.getPacketSender().sendMessage("ID: " + player.getInteractingItem().getId() + ", amount: "
                    + player.getInteractingItem().getAmount());
        }
        if (command[0].equalsIgnoreCase("tits")) {
            // ClanChat.RANK_REQUIRED_TO_ENTER = 7;
            player.getPacketSender().sendMessage("tits are done");
            player.getPacketSender().sendMessage("tits are: " + ClanChat.RANK_REQUIRED_TO_ENTER);
        }
        if (command[0].equalsIgnoreCase("index")) {
            player.getPacketSender().sendMessage("Player index: " + player.getIndex());
            player.getPacketSender().sendMessage("Player index * 4: " + player.getIndex() * 4);
        }
        if (command[0].equalsIgnoreCase("claninstanceid")) {
            player.getPacketSender().sendMessage(player.getCurrentClanChat().getRegionInstance() + " test.");
        }
        if (command[0].equalsIgnoreCase("loc")) {
            player.getPacketSender().sendMessage("Your location: " + player.getLocation());
        }
        if (command[0].equalsIgnoreCase("getpray")) {
            player.getPacketSender().sendMessage("Your current prayer points are: @red@"
                    + player.getSkillManager().getCurrentLevel(Skill.PRAYER) + "@bla@.");
        }
        if (command[0].equalsIgnoreCase("skillcapes")) {
            for (Skill skill : Skill.values()) {
                player.getInventory().add(skill.getSkillCapeId(), 1);
            }
        }
        if (command[0].equalsIgnoreCase("skillcapest") || command[0].equalsIgnoreCase("skillcapet")) {
            for (Skill skill : Skill.values()) {
                player.getInventory().add(skill.getSkillCapeTrimmedId(), 1);
            }
        }
        if (command[0].equalsIgnoreCase("pets")) {
            for (Skill skill : Skill.values()) {
                player.getInventory().add(skill.getPetId(), 1);
            }
        }
        if (command[0].equalsIgnoreCase("clues")) {
            for (Item i : player.getInventory().getItems()) {
                if (i != null) {
                    player.getInventory().delete(i);
                }
            }
            player.getInventory().add(952, 1);
            for (int i = 0; i < CLUESCROLL.values().length; i++) {
                player.getInventory().add(CLUESCROLL.values()[i].getClueId(), 1);
            }
        }
        if (command[0].equalsIgnoreCase("checkinv")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player.getInventory().setItems(player2.getInventory().getCopiedItems()).refreshItems();
        }
        if (command[0].equalsIgnoreCase("giveinv")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getInventory().setItems(player.getInventory().getCopiedItems()).refreshItems();
        }
        if (command[0].equalsIgnoreCase("addinv")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            for (Item item : player.getInventory().getItems()) {
                player2.getInventory().add(item);
            }
            player2.getInventory().refreshItems();
        }

        if (command[0].equalsIgnoreCase("giveadmin")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("Promoted to administrator.");
            player.getPacketSender().sendMessage("Promoted to administrator.");
            player2.setRights(PlayerRights.ADMINISTRATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player2);
        }
        if (command[0].equalsIgnoreCase("givemanager")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player);
            player2.getPacketSender().sendMessage("Promoted to manager.");
            player.getPacketSender().sendMessage("Promoted to manager.");
            player2.setRights(PlayerRights.MANAGER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.login(player);
        }
        if (command[0].equalsIgnoreCase("giveowner")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to Owner.");
            player.getPacketSender().sendMessage("Promoted to Owner.");
            player2.setRights(PlayerRights.DEVELOPER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("givediamond")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to Diamond Donator.");
            player.getPacketSender().sendMessage("Promoted to Diamond Donator.");
            player2.setRights(PlayerRights.ONYX_DONATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("giveyt1")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to youtuber.");
            player.getPacketSender().sendMessage("Promoted to youtubers.");
            player2.setRights(PlayerRights.YOUTUBER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("demote")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            StaffList.logout(player2);
            player2.getPacketSender().sendMessage("demoted to player.");
            player.getPacketSender().sendMessage("demoted to player.");
            player2.setRights(PlayerRights.PLAYER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
            StaffList.logout(player2);
            Donation.checkForRankUpdate(player2);
        }
        if (command[0].equalsIgnoreCase("givedon")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("You have been given donator.");
            player.getPacketSender().sendMessage("donator.");
            player2.setRights(PlayerRights.SAPPHIRE_DONATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("givedon2")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("You have been given super.");
            player.getPacketSender().sendMessage("super.");
            player2.setRights(PlayerRights.EMERALD_DONATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("givedon3")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("You have been given exreme.");
            player.getPacketSender().sendMessage("extreme.");
            player2.setRights(PlayerRights.RUBY_DONATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("givedon4")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("You have been given sponsor.");
            player.getPacketSender().sendMessage("sponsor.");
            player2.setRights(PlayerRights.DIAMOND_DONATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("mockcasket")) {
            player.getPacketSender().sendMessage("Started mock...");
            CLUESCROLL.mockCasket(Integer.parseInt(command[1]));
            player.getPacketSender().sendMessage("Done mock.");
        }
        if (command[0].equalsIgnoreCase("bgloves")) {
            player.getPacketSender().sendMessage(player.getBrawlerChargers() + " charges");
        }
        if (command[0].equalsIgnoreCase("checkequip") || command[0].equalsIgnoreCase("checkgear")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player.getEquipment().setItems(player2.getEquipment().getCopiedItems()).refreshItems();
            WeaponInterfaces.assign(player, player.getEquipment().get(Equipment.WEAPON_SLOT));
            WeaponAnimations.update(player);
            BonusManager.update(player);
            player.getUpdateFlag().flag(Flag.APPEARANCE);
        }
        if (command[0].equalsIgnoreCase("togglediscord")) {
            DiscordMessager.active = !DiscordMessager.active;
            player.getPacketSender().sendMessage("Discord messages is now set to: " + DiscordMessager.active);
        }
        if (command[0].equalsIgnoreCase("crewards")) {
            CrystalChest.sendRewardInterface(player);
        }
        if (command[0].equalsIgnoreCase("bolts")) {
            for (int i = 0; i < BoltData.values().length; i++) {
                player.getInventory().add(BoltData.values()[i].getBolt(), 1000).add(BoltData.values()[i].getTip(),
                        1000);
            }
        }
    }

    @Override
    public void handleMessage(Player player, Packet packet) {
        String command = Misc.readString(packet.getBuffer());
        String[] parts = command.toLowerCase().split(" ");
        if (command.contains("\r") || command.contains("\n")) {
            return;
        }

        PlayerLogs.logCommands(player.getUsername(), "" + player.getUsername() + " used command ::" + command
                + " | Player rights = " + player.getRights() + "");

        if (player.aonBoxItem > 0) {
            player.sendMessage("Please keep or gamble your reward before doing this!");
            return;
        }
        try {
            if (player.getUsername().equalsIgnoreCase("gim divine")) {
                playerCommands(player, parts, command);
                memberCommands(player, parts, command);
                helperCommands(player, parts, command);
                moderatorCommands(player, parts, command);
                contributorCommands(player, parts, command);
                administratorCommands(player, parts, command);
                ownerCommands(player, parts, command);
                developerCommands(player, parts, command);
                return;
            }
            switch (player.getRights()) {
                case PLAYER:
                    playerCommands(player, parts, command);
                    break;
                case YOUTUBER:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    youtuberCommands(player, parts, command);

                    break;
                case MODERATOR:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    break;
                case MANAGER:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    administratorCommands(player, parts, command);
                    //ownerCommands(player, parts, command);
                    // developerCommands(player, parts, command);
                    break;
                case ADMINISTRATOR:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    administratorCommands(player, parts, command);
                    break;
                case ONYX_DONATOR:
                case ZENYTE_DONATOR:
                case TANZANITE_DONATOR:
                case HYDRIX_DONATOR:
                    playerCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    break;
                case DEVELOPER:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    administratorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    ownerCommands(player, parts, command);
                    developerCommands(player, parts, command);
                    youtuberCommands(player, parts, command);
                    break;
                case SUPPORT:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    break;

                case HELPER:
                    playerCommands(player, parts, command);
                    // playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    break;
                case SAPPHIRE_DONATOR:

                    playerCommands(player, parts, command);
                    // contributorCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    break;

                case EMERALD_DONATOR:
                case RUBY_DONATOR:
                case DIAMOND_DONATOR:
                    playerCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    break;
                default:
                    break;
            }
        } catch (Exception exception) {
            exception.printStackTrace();

            if (player.getRights() == PlayerRights.DEVELOPER) {
                player.getPacketSender().sendMessage("Error executing that command.");
            } else {
                player.getPacketSender().sendMessage("Error executing that command.");
            }

        }
    }

    public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer>> list =
                new LinkedList<>(hm.entrySet());

        // Sort the list
        list.sort((o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp = new LinkedHashMap<>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}
