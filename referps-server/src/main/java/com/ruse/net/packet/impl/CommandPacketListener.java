package com.ruse.net.packet.impl;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.ruse.GameServer;
import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.globalevents.*;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.*;
import com.ruse.model.input.impl.ChangePassword;
import com.ruse.model.input.impl.EnterReferral;
import com.ruse.model.input.impl.SetPinPacketListener;
import com.ruse.motivote3.doMotivote;
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
import com.ruse.world.content.clan.ClanChat;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.cluescrolls.OLD_ClueScrolls;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.DesolaceFormulas;
import com.ruse.world.content.combat.Maxhits;
import com.ruse.world.content.combat.magic.Autocasting;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.range.ToxicBlowpipe;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.dailyTask.DailyTaskHandler;
import com.ruse.world.content.dailytasks_new.DailyTasks;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.grandexchange.GrandExchangeOffers;
import com.ruse.world.content.groupironman.GroupManager;
import com.ruse.world.content.holidayevents.easter2017;
import com.ruse.world.content.minigames.impl.KeepersOfLight;
import com.ruse.world.content.minigames.impl.TreasureHunter;
import com.ruse.world.content.minigames.impl.VaultOfWar;
import com.ruse.world.content.minigames.impl.dungeoneering.Dungeoneering;
import com.ruse.world.content.minigames.impl.dungeoneering.DungeoneeringParty;
import com.ruse.world.content.pos.PlayerOwnedShopManager;
import com.ruse.world.content.progressionzone.ProgressionZone;
import com.ruse.world.content.randomevents.EvilTree;
import com.ruse.world.content.randomevents.ShootingStar;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skeletalhorror.SkeletalHorror;
import com.ruse.world.content.skill.SkillManager;
import com.ruse.world.content.skill.impl.construction.Construction;
import com.ruse.world.content.skill.impl.crafting.Jewelry;
import com.ruse.world.content.skill.impl.fletching.BoltData;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator;
import com.ruse.world.entity.impl.npc.NPCSpawn;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.PlayerSaving;
import com.ruse.world.instance.TestInstance;
import com.world.content.globalBoss.merk.MerkSpawn;
import mysql.impl.Donation;
import mysql.impl.FoxVote;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

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

        YoutuberLinks.handleCommand(player, command[0]);

        if (command[0].equalsIgnoreCase("gamble")) {
            TeleportHandler.teleportPlayer(player, new Position(2463, 5032, 0),
                    player.getSpellbook().getTeleportType());
        }

        if (command[0].equalsIgnoreCase("perks")) {
            ServerPerks.getInstance().open(player);
        }

      /*  if (command[0].equalsIgnoreCase("bunny") || command[0].equalsIgnoreCase("easter")) {

            int accounts = 0;
            for (Player p : World.getPlayers()) {
                if (p == null)
                    continue;
                if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                    if (p.getLocation() == Location.EASTER) {
                        accounts++;
                        continue;
                    }
                }
            }
            if (accounts == 1) {
                player.getPacketSender().sendMessage("You already have an account there!");
                player.getCombatBuilder().reset(true);
                return;
            }

            player.sendMessage("Armoured Bunny Status: " + (!BunnySpawn.bossAlive ? BunnySpawn.getTimeLeft() + " left" : "Alive"));
            TeleportHandler.teleportPlayer(player, new Position(3132, 3394, 0),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("bunnytime") || command[0].equalsIgnoreCase("eastertime")) {
            player.sendMessage("Armoured Bunny Status: " + (!BunnySpawn.bossAlive ? BunnySpawn.getTimeLeft() + " left" : "Alive"));
        }
        if (command[0].equalsIgnoreCase("stpat") || command[0].equalsIgnoreCase("pat") ||
                command[0].equalsIgnoreCase("patrick")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 1),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat2") || command[0].equalsIgnoreCase("pat2") ||
                command[0].equalsIgnoreCase("patrick2")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 5),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat3") || command[0].equalsIgnoreCase("pat3") ||
                command[0].equalsIgnoreCase("patrick3")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 9),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat4") || command[0].equalsIgnoreCase("pat4") ||
                command[0].equalsIgnoreCase("patrick4")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 13),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat5") || command[0].equalsIgnoreCase("pat5") ||
                command[0].equalsIgnoreCase("patrick5")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 17),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat6") || command[0].equalsIgnoreCase("pat6") ||
                command[0].equalsIgnoreCase("patrick6")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 21),
                    player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("stpat7") || command[0].equalsIgnoreCase("pat7") ||
                command[0].equalsIgnoreCase("patrick7")) {
            TeleportHandler.teleportPlayer(player, new Position(3106, 2904, 25),
                    player.getSpellbook().getTeleportType());
        }*/


        if (command[0].equalsIgnoreCase("starterzones")) {
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
            KillTrackerInterface.open(player);
        }
        if (command[0].equalsIgnoreCase("dropmessage")) {
            player.dropMessageToggle = !player.dropMessageToggle;
            player.sendMessage("Show drop messages currently set to: " + player.dropMessageToggle);
            return;
        }
       /* if (player.getAmountDonated() >= 10) {
            if (command[0].equalsIgnoreCase("dzone") || command[0].equalsIgnoreCase("sapphire")
                    || command[0].equalsIgnoreCase("memberzone") || command[0].equalsIgnoreCase("mzone")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
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
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.SUPER_ZONE, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("Thanks for supporting " + GameSettings.RSPS_NAME + "!");
                player.getPacketSender().sendMessage("<shad=1>@red@Type ::supernpcs to teleport to the npc's!");
            }
        }

        if (command[0].equals("zenyte") || command[0].equalsIgnoreCase("zenytezone")) {
            if (player.getAmountDonated() >= 5000 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2778, 4834),
                        player.getSpellbook().getTeleportType());
            }
        }

        if (command[0].equals("onyx") || command[0].equalsIgnoreCase("onyxzone")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equals("onyx2") || command[0].equalsIgnoreCase("onyxzone2")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408, 4),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equals("onyx3") || command[0].equalsIgnoreCase("onyxzone3")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408, 8),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equals("onyx4") || command[0].equalsIgnoreCase("onyxzone4")) {
            if (player.getAmountDonated() >= 1000 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2462, 5408, 12),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equals("diamond") || command[0].equalsIgnoreCase("diamondzone")) {
            if (player.getAmountDonated() >= 500 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2974, 2845),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (command[0].equals("diamondzone2") || command[0].equalsIgnoreCase("diamond2")) {
            if (player.getAmountDonated() >= 500 || player.getRights().isStaff()) {
                TeleportHandler.teleportPlayer(player, new Position(2974, 2845, 4),
                        player.getSpellbook().getTeleportType());
            }
        }
        if (player.getAmountDonated() >= 250 || player.getRights().isStaff()) {
            if (command[0].equalsIgnoreCase("rzone") || command[0].equalsIgnoreCase("ruby")
                    || command[0].equalsIgnoreCase("rubyzone") || command[0].equalsIgnoreCase("rz")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.EXTREME_ZONE, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("Thanks for supporting " + GameSettings.RSPS_NAME + "!");
                player.getPacketSender().sendMessage("<shad=1>@red@Type ::extremenpcs to teleport to the npc's!");

            }
        }
        if (player.getAmountDonated() >= 250 || player.getRights().isStaff()) {
            if (command[0].equalsIgnoreCase("ezonenpcs") || command[0].equalsIgnoreCase("ez")
                    || command[0].equalsIgnoreCase("ezonenpc") || command[0].equalsIgnoreCase("enpc")
                    || command[0].equalsIgnoreCase("enpcs") || command[0].equalsIgnoreCase("extremenpcs")
                    || command[0].equalsIgnoreCase("extremenpc")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");

                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.EXTREME_ZONE_NPC, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("<shad=1>@or2@Teleporting Ezone NPC'S!");
            }
        }
        if (player.getAmountDonated() >= 50 || player.getRights().isStaff()) {
            if (command[0].equalsIgnoreCase("szonenpcs") || command[0].equalsIgnoreCase("szonenpc")
                    || command[0].equalsIgnoreCase("snpc") || command[0].equalsIgnoreCase("supernpcs")
                    || command[0].equalsIgnoreCase("supernpc") || command[0].equalsIgnoreCase("snpcs")) {
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
                        || player.getLocation() == Location.DUNGEONEERING
                        || player.getLocation() == Location.DUEL_ARENA) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }

                TeleportHandler.teleportPlayer(player, GameSettings.SUPER_ZONE_NPC, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("<shad=1>@or2@Teleporting Szone NPC'S!");
            }
        }
        */
       /* if (command[0].equalsIgnoreCase("answer")) {
            String answer = wholeCommand.substring(7);
            TriviaSystem.answer(player, answer);
        }*/

        if (command[0].equalsIgnoreCase("requestraid") || command[0].equalsIgnoreCase("invplayer")) { // test command.
            Player target = World.getPlayerByName(command[1]);
            player.getMinigameAttributes().getDungeoneeringAttributes().getParty().invite(target);
        }

        if (command[0].equalsIgnoreCase("createraidparty") || command[0].equalsIgnoreCase("createParty")) {
            DungeoneeringParty.create(player);
        }

        if (command[0].equalsIgnoreCase("deleteraidparty") || command[0].equalsIgnoreCase("deleteparty")) {
            com.ruse.world.content.skill.impl.old_dungeoneering.Dungeoneering.leave(player, false, true);
            player.getPacketSender().sendString(29053, "").sendString(29054, "");

            for (int i = 0; i < 10; i++) {
                player.getPacketSender().sendString(29095 + i, "");
            }
        }
        if (wholeCommand.equalsIgnoreCase("startraids") || wholeCommand.equalsIgnoreCase("startraid")
                || wholeCommand.equalsIgnoreCase("startraids")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            com.ruse.world.content.skill.impl.old_dungeoneering.Dungeoneering.start(player);
        }


        if (command[0].equalsIgnoreCase("train") || command[0].equalsIgnoreCase("starter")
                || command[0].equalsIgnoreCase("start") || command[0].equalsIgnoreCase(
                "training")) {
            ProgressionZone.teleport(player);
        }

        if (command[0].equalsIgnoreCase("train2") || command[0].equalsIgnoreCase("starter2")
                || command[0].equalsIgnoreCase("start2") || command[0].equalsIgnoreCase(
                "training")) {
            ProgressionZone.teleport(player, true);
        }

        if (command[0].equalsIgnoreCase("donationdeals") || command[0].equalsIgnoreCase("deals")) {
            player.sendMessage(
                    "<shad=1>@yel@<img=14>Please check out the donation deals in our ::Discord - #Donation-deals");
        }

       /* if (command[0].equalsIgnoreCase("dailyrewards") || command[0].equalsIgnoreCase("daily")) {
            player.getDailyRewards().processTime();
            player.getDailyRewards().displayRewards();
        }*/

        if (wholeCommand.equalsIgnoreCase("droprate") || wholeCommand.equalsIgnoreCase("mydr") || wholeCommand.equalsIgnoreCase("dr")) {
            player.getPacketSender()
                    .sendMessage("Droprate  bonus is + @red@" + CustomDropUtils.drBonus(player, player.getSlayer().getSlayerTask().getNpcId())
                            + "@bla@%. / X2 Drop bonus is + <col=248f8f>" + CustomDropUtils.getDoubleDropChance(player, player.getSlayer().getSlayerTask().getNpcId())
                            + "@bla@%.");
        }

        if (wholeCommand.equalsIgnoreCase("ddr")) {
            player.getPacketSender().sendMessage(
                    "Your Double  bonus is + @red@" + CustomDropUtils.getDoubleDropChance(player, player.getSlayer().getSlayerTask().getNpcId()) + "@bla@%.");
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
            PossibleLootInterface.openInterface(player, PossibleLootInterface.LootData.values()[0]);
        }
        if (command[0].equalsIgnoreCase("maxhit")) {
            player.getPacketSender().sendMessage("<shad=1>@red@Melee Maxhit: " + (Maxhits.melee(player, player) / 10));
            player.getPacketSender()
                    .sendMessage("<shad=1>@gre@Ranged Maxhit: " + (Maxhits.ranged(player, player) / 10));
            player.getPacketSender().sendMessage("<shad=1>@cya@Magic Maxhit: " + (Maxhits.magic(player, player) / 10));
        }


        if (command[0].startsWith("reward") || command[0].startsWith("voted") || command[0].startsWith("claimvote")) {
            new Thread(new FoxVote(player)).start();
        }

        if (command[0].equalsIgnoreCase("achievements") || command[0].equalsIgnoreCase("dailytasks")
                || command[0].equalsIgnoreCase("tasks")) {
            player.getAchievements().refreshInterface(player.getAchievements().currentInterface);
            player.getPacketSender().sendConfig(6000, 3);
        }

        if (command[0].equalsIgnoreCase("resetdaily")) {
            new DailyTaskHandler(player).resetTasks();
        }

        if (command[0].equalsIgnoreCase("collection") || command[0].equalsIgnoreCase("collectionlog")) {
            player.getCollectionLog().open();
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
            player.getPacketSender().sendString(index++, color + "::perks - Opens up the server perks interface");
            player.getPacketSender().sendString(index++, color + "::gamble - Teleports you to the gambling area");
            player.getPacketSender().sendString(index++, color + "::train/train2 - Teleports you to the starter areas");
            player.getPacketSender().sendString(index++, color + "::checkdaily - Checks your daily dask");
            player.getPacketSender().sendString(index++, color + "::shops - Teleports you to all shops");
            player.getPacketSender().sendString(index++, color + "");
            player.getPacketSender().sendString(index++, color1 + "Interface Commands:");
            player.getPacketSender().sendString(index++, color + "::kills - opens up your personal kill tracker list");
            player.getPacketSender().sendString(index++, color + "::pos - opens the player owned shops interface");
            player.getPacketSender().sendString(index++, color + "::tasks - opens the achievements interface");
            player.getPacketSender().sendString(index++, color + "::rewards - opens the possible loot interface");
            player.getPacketSender().sendString(index++, color + "::drops - opens the loot viewer interface for npcs");
            player.getPacketSender().sendString(index++, color + "::collection - opens the collection log interface");
            player.getPacketSender().sendString(index++, color + "::itemstats - opens up best items interface");
            player.getPacketSender().sendString(index++, color + "");
            player.getPacketSender().sendString(index++, color1 + "Other Commands:");
            player.getPacketSender().sendString(index++, color + "::dr/ddr - shows you your current droprate");
            player.getPacketSender().sendString(index++, color + "::maxhit - shows you your current droprate");
            player.getPacketSender().sendString(index++, color + "::changepass - allows you to change your password");


            player.getPacketSender().sendString(index++,
                    color + "::global - teleports to global bosses");
            player.getPacketSender().sendString(index++,
                    color + "::bank - opens up your bank ($50 total claimed required)");
            player.getPacketSender().sendString(index++,
                    color + "::players - tells you how many players are currently online");
            player.getPacketSender().sendString(index++, color + "::forums - opens up our forums for Solak");
            player.getPacketSender().sendString(index++, color + "::client - downloads our client launcher");
            player.getPacketSender().sendString(index++, color + "::rules - opens up our rules");
            player.getPacketSender().sendString(index++, color + "::discord - opens up our discord for Solak");
            player.getPacketSender().sendString(index++, color + "::vote - opens up our site for voting");
            player.getPacketSender().sendString(index++, color + "::voted - claims your votes");
            player.getPacketSender().sendString(index++, color + "::donate - opens up our donation site");
            player.getPacketSender().sendString(index++, color + "::donated - claims your donation");
            player.getPacketSender().sendString(index++, color + "::donationdeals - see if there are any promotions");
            player.getPacketSender().sendString(index++,
                    color + "::whatdrops (item name) - tells you what drops the item");
            player.getPacketSender().sendString(index++,
                    color + "::dropmessage - removes messages of drops going to your inv/bank");
            player.getPacketSender().sendString(index++, color + "::help - requests assistance from a staff member");
            player.getPacketSender().sendString(index++, color + "::yell - sends a global message");
            player.getPacketSender().sendString(index++, color + "");
        }

        if (wholeCommand.equalsIgnoreCase("ironmaninfo") || wholeCommand.equalsIgnoreCase("hciminfo")) {
            player.getPacketSender().sendString(1, GameSettings.IronManModesUrl);
            player.getPacketSender().sendMessage("Attempting to open Iron Man Mode Info");
        }
        if (wholeCommand.equalsIgnoreCase("donorinfo") || wholeCommand.equalsIgnoreCase("memberinfo")) {
            player.getPacketSender().sendString(1, GameSettings.RankFeaturesUrl);
            player.getPacketSender().sendMessage("Attempting to open Member Info");
        }



        if (wholeCommand.equalsIgnoreCase("donate") || wholeCommand.equalsIgnoreCase("store")) {
            player.getPacketSender().sendString(1, GameSettings.StoreUrl);
            player.getPacketSender().sendMessage("Attempting to open the store");
        }

        if (wholeCommand.equalsIgnoreCase("client") || wholeCommand.equalsIgnoreCase("launcher")) {
            player.getPacketSender().sendString(1, "https://solak.io/play/");
            player.getPacketSender().sendMessage("Attempting to download the launcher");
        }
        if (wholeCommand.equalsIgnoreCase("discord") || wholeCommand.equalsIgnoreCase("chat")) {
            player.getPacketSender().sendString(1, GameSettings.DiscordUrl);
            player.getPacketSender().sendMessage("Attempting to open our Discord Server");
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
            player.getPacketSender().sendString(1, GameSettings.UpdateUrl);
            player.getPacketSender().sendMessage("Attempting to open the the update list");
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
        if (command[0].equalsIgnoreCase("moneymaking")) {
            player.getPacketSender().sendMessage("Type ::discord and go to the Guides Section!");
        }
      /*  if (command[0].equalsIgnoreCase("well")) {
            player.setDialogueActionId(215);
            DialogueManager.start(player, 215);
        }*/

        if (command[0].equalsIgnoreCase("pos") && player.getLocation() != Location.HOME_BANK
                && player.getAmountDonated() < Donation.EMERALD_DONATION_AMOUNT) {
            player.sendMessage("You either need $50 total claim or can only use this command at ::Home");
            return;
        } else if (command[0].equalsIgnoreCase("pos") && player.getAmountDonated() >= Donation.EMERALD_DONATION_AMOUNT) {
            player.sendMessage("As a $50 Donator benefit, you can use this command anywhere.");
            player.getPlayerOwnedShopManager().options();
        } else if (command[0].equalsIgnoreCase("pos") && player.getLocation() == Location.HOME_BANK
                && player.getAmountDonated() < Donation.EMERALD_DONATION_AMOUNT) {
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
        if (command[0].equalsIgnoreCase("logout")) {
            World.getPlayers().remove(player);
        }
        if (command[0].equalsIgnoreCase("ref") ||command[0].equalsIgnoreCase("refer") || command[0].equalsIgnoreCase("referral")) {
            if (!player.hasReferral) {
                player.getPacketSender().sendEnterInputPrompt("Please type your refer code to receive a reward!");
                player.setInputHandling(new EnterReferral());
            } else {
                player.getPacketSender().sendMessage("You have already claimed a referral reward on this account!");
            }
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
        if (command[0].equalsIgnoreCase("home")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position pos = new Position(2072 + Misc.getRandom(9), 4454 + Misc.getRandom(6));
            TeleportHandler.teleportPlayer(player, pos, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you home!");
        }
        if (command[0].equalsIgnoreCase("global")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            TeleportHandler.teleportPlayer(player, new Position(2139, 5019, 0), player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to global!");
        }
        if (command[0].equalsIgnoreCase("youtube") || (command[0].equalsIgnoreCase("stream"))) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2852, 2708, 4)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
        }
        if (command[0].equalsIgnoreCase("gamble") || command[0].equalsIgnoreCase("dice")
                || command[0].equalsIgnoreCase("dicezone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }

            if (player.isGambleBanned()) {
                player.sendMessage("You are gamble banned and cannot do this.");
                return;
            }
          /*  if (player.getPointsHandler().getNPCKILLCount() < 50000){
                player.sendMessage("You must have atleast 50k NPC killcount to gamble.");
                return;
            }*/
            Position position = new Position(2463, 5032, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to the Gambling Area!");
        }
        if (command[0].equalsIgnoreCase("iron")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
        if (command[0].equalsIgnoreCase("vboss") || command[0].equalsIgnoreCase("voteboss")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2980, 2771, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);

        }

        if (command[0].equalsIgnoreCase("afk")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3037, 4062, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("<shad=1>@gre@Welcome to the afk zone!");

        }

        if ((command[0].equalsIgnoreCase("shop") && !player.getRights().OwnerDeveloperOnly())
                || command[0].equalsIgnoreCase("shops")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(2071, 4462, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to our shops!");
        }
      /*  if ((command[0].equalsIgnoreCase("oldshop") && !player.getRights().OwnerDeveloperOnly())
                || command[0].equalsIgnoreCase("oldshops")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position position = new Position(3690, 2977, 0);
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to our shops!");
        }*/
       /* if (command[0].equalsIgnoreCase("chill")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
                        || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
        if (command[0].equalsIgnoreCase("empty")) {
            //   DialogueManager.editOptions(523, 1, "Are you sure you would like to empty your items?");
            player.setDialogueActionId(523);
            DialogueManager.start(player, 523);
            return;
        }
        if (command[0].equalsIgnoreCase("players")) {
            int players = World.getPlayers().size() + GameSettings.players;
            player.getPacketSender().sendMessage(
                    "<shad=1>@or1@There are currently @whi@[ @gre@" + (players) + "@whi@ ] @or1@players online!");
        }

        if (command[0].equalsIgnoreCase("droplog")) {
            player.getPacketSender().sendInterfaceRemoval();
            DropLog.open(player);
        }
        if (command[0].equalsIgnoreCase("fly")) {
            if (player.canFly() == false) {
                player.getPacketSender().sendMessage("You do not understand the complexities of flight.");
                return;
            }
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
       /* if (GameSettings.BETA_ENABLED) {
            if (command[0].equalsIgnoreCase("ownerup")) {
                player.setRights(PlayerRights.DEVELOPER);
                player.getPacketSender().sendRights();
                PlayerPanel.refreshPanel(player);
            }
            if (command[0].equalsIgnoreCase("ownerdown")) {
                player.setRights(PlayerRights.PLAYER);
                player.getPacketSender().sendRights();
                PlayerPanel.refreshPanel(player);
            }
        }*/
    }


    public static void resetInterface(Player player) {
        for (int i = 8145; i < 8196; i++)
            player.getPacketSender().sendString(i, "");
        for (int i = 12174; i < 12224; i++)
            player.getPacketSender().sendString(i, "");
        player.getPacketSender().sendString(8136, "Close window");
    }

    private static void memberCommands(final Player player, String[] command, String wholeCommand) {

        if (wholeCommand.toLowerCase().startsWith("yell")) {
            if (PlayerPunishment.muted(player.getUsername()) || PlayerPunishment.IPMuted(player.getHostAddress())) {
                player.getPacketSender().sendMessage("You are muted and cannot yell.");
                return;
            }
            if (player.getAmountDonated() < Donation.SAPPHIRE_DONATION_AMOUNT && !(player.getRights().isStaff() || player.getRights() == PlayerRights.YOUTUBER)) {
                player.getPacketSender().sendMessage("You need to be a Donator to yell.");
                return;
            }
            int delay = player.getRights().isStaff() ? 0 : player.getRights().getYellDelay();
            if (!player.getLastYell().elapsed((delay * 1000))) {
                player.getPacketSender().sendMessage("You must wait at least " + delay + " seconds between every yell-message you send.");
                return;
            }
            String yellMessage = Misc.capitalizeJustFirst(wholeCommand.substring(5));

            String[] invalid = {"<img="};
            for (String s : invalid) {
                if (yellMessage.contains(s)) {
                    player.getPacketSender().sendMessage("Your message contains invalid characters.");
                    return;
                }
            }

            World.sendYellMessage("" + player.getRights().getYellPrefix()
                    + "<img=" + player.getRights().ordinal()
                    + "><col=" + player.getRights().getYellPrefix() +
                    " [" + Misc.ucFirst(player.getRights().name().replaceAll("_", " ")) + "]<shad=0><col=" + player.getYellHex() + "> " + player.getUsername() +
                    ": " + yellMessage);

            // World.sendMessage(":yell:" + yell);
            player.getLastYell().reset();
            return;
        }
    }

    private static void youtuberCommands(final Player player, String[] command, String wholeCommand) {
    }

    private static void contributorCommands(final Player player, String[] command, String wholeCommand) {

    }

    private static void helperCommands(final Player player, String[] command, String wholeCommand) {


    }

    private static void supportCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("checkalt")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target != null) {
                player.sendMessage("Searching...");

                for (Player plr : World.getPlayers()) {
                    if (plr != null) {
                        if (plr.getHostAddress().equals(target.getHostAddress()) && !plr.equals(target)
                                && !plr.getUsername().equalsIgnoreCase("nucky")
                                && !target.getUsername().equalsIgnoreCase("nucky")
                                && !plr.getUsername().equalsIgnoreCase("test")
                                && !target.getUsername().equalsIgnoreCase("test")
                                && !plr.getUsername().equalsIgnoreCase("james")
                                && !target.getUsername().equalsIgnoreCase("james")) {
                            player.sendMessage(
                                    plr.getUsername() + " has the same Ip address as " + target.getUsername());
                        }
                    }
                }

                player.sendMessage("Done search");
            } else {
                player.sendMessage(wholeCommand.substring(command[0].length() + 1) + " is not valid");
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
                    player.performGraphic(new Graphic(342));

                    TeleportHandler.teleportPlayer(player, player2.getPosition().copy(), TeleportType.ZOOM);
                    player.getPacketSender().sendMessage("Teleporting to player: " + player2.getUsername() + "");
                    player2.performGraphic(new Graphic(730));
                    player2.getPacketSender().sendMessage("<img=5> ATTENTION: <col=6600FF>" + player.getRights() + " " + player.getUsername() + " is teleporting to you.");
                } else
                    player.getPacketSender()
                            .sendMessage("You can not teleport to this player at the moment. Minigame maybe?");
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
        if (command[0].equalsIgnoreCase("staffzone")) {
            if (command.length > 1 && command[1].equalsIgnoreCase("all") && player.getRights().OwnerDeveloperOnly()) {
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
                playerToMove.getControllerManager().forceStop();
                playerToMove.moveTo(GameSettings.DEFAULT_POSITION.copy());
                playerToMove.getPacketSender()
                        .sendMessage("You've been teleported home by " + player.getUsername() + ".");
                player.getPacketSender().sendMessage("Successfully moved " + playerToMove.getUsername() + " to home.");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just moved " + playerToMove.getUsername() + " to home.");
                player.performGraphic(new Graphic(730));
                playerToMove.performGraphic(new Graphic(730));
            } else {
                player.getPacketSender().sendMessage("Could not send \"" + player2 + "\" home. Try kicking first?")
                        .sendMessage("Will also fail if they're in duel/wild.");
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
                //PlayerHandler.handleLogout(playerToKick, false);
                playerToKick.save();
                World.getPlayers().remove(playerToKick);
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
        if (command[0].equalsIgnoreCase("kill")) {
            String player2 = wholeCommand.substring(command[0].length() + 1);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick == null) {
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " couldn't be found on " + GameSettings.RSPS_NAME + ".");
                return;
            } else if (playerToKick.getDueling().duelingStatus < 5) {
                playerToKick.setConstitution(0);
                //PlayerHandler.handleLogout(playerToKick, false);
            } else {
                playerToKick.setConstitution(0);
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

        if (command[0].equalsIgnoreCase("mute")) {
            try {
                String[] time = command[1].split("h");
                Long timer = Long.parseLong(time[0]);
                String target = wholeCommand.substring(command[0].length() + command[1].length() + 2);

                String fileName = Misc.formatText(target.toLowerCase());
                File file = new File("./referps-server/data/saves/characters/" + fileName + ".json");
                if (!file.exists()) {
                    Player targetPlayer = World.getPlayerByName(target);
                    if (targetPlayer != null) {
                        targetPlayer.save();
                    } else {
                        player.getPacketSender().sendMessage(fileName + " does not exist in my files, " +
                                "maybe you typed it wrong!");
                        return;
                    }
                }
                if (PlayerPunishment.muted(target)) {
                    player.getPacketSender().sendMessage("Player: " + target + " already has an active mute.");
                    return;
                }
                PlayerLogs.log(player.getUsername(), player.getUsername() + " just muted " + target + " for " + command[2] + "!");
                World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                        + " just muted " + target + " for " + command[2] + ".");
                PlayerPunishment.mute(target, LocalDateTime.now().plusHours(timer));//* , GameSettings.Temp_Mute_Time); *//*
                player.getPacketSender().sendMessage("Player " + target + " was successfully muted for " + command[2] + ".");// for
                Player plr = World.getPlayerByName(target);
                if (plr != null) {
                    plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + " for " + command[2] + "."); // for
                }
                /*if (World.getPlayerByName(target) == null) {
                    String fileName = Misc.formatText(target.toLowerCase());
                    File file = new File("./referps-server/data/saves/characters/" + fileName + ".json");
                    if (file.exists()) {
                        if (PlayerPunishment.muted(target)) {
                            player.getPacketSender().sendMessage("Player: " + target + " already has an active mute.");
                            return;
                        }
                        PlayerLogs.log(player.getUsername(), player.getUsername() + " just muted " + target + "!");
                        World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                                + " just muted " + target);
                        PlayerPunishment.mute(target, LocalDateTime.now().plusHours(timer));
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
                    PlayerPunishment.mute(target, LocalDateTime.now().plusHours(timer));*//* , GameSettings.Temp_Mute_Time); *//*
                    player.getPacketSender().sendMessage("Player " + target + " was successfully muted");// for
                    Player plr = World.getPlayerByName(target);
                    if (plr != null) {
                        plr.getPacketSender().sendMessage("You have been muted by " + player.getUsername() + "."); // for
                    }
                }*/
            } catch (Exception e) {
                player.getPacketSender().sendMessage("Error! Usage ::mute 6h username");
            }
        }


        if (command[0].equalsIgnoreCase("jail")) {
            String target = command[1];//wholeCommand.substring(command[0].length() + 1);
            String[] time = command[2].split("h");
            Long timer = Long.parseLong(time[0]);
            if (Jail.isJailed(target)) {
                player.getPacketSender().sendMessage("Player: " + target + " is already jailed.");
                return;
            }
            Player player2 = World.getPlayerByName(target);
            if (player2 != null) {
                if (player2.getDueling().duelingStatus == 0) {
                    Jail.jailPlayer(target, LocalDateTime.now().plusHours(timer));
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
            }
        }
        if (command[0].equalsIgnoreCase("unjail")) {
            Player player2 = World
                    .getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 != null) {
                if (true) {
                    Jail.unJail(player2.getUsername());
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
                        + wholeCommand.substring(command[0].length() + 1) + "\" online.");
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

    }

    private static void globalModCommands(final Player player, String[] command, String wholeCommand) {


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
                ConnectionHandler.banUUID(name, uuid);
                ConnectionHandler.banMac(name, mac);
                PlayerPunishment.ban(name, LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));

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
                        PlayerPunishment.ban(playersToBan.getUsername(), LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));
                        World.deregister(playersToBan);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void moderatorCommands(final Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("kickgi")) {
            Player target = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (target == null) {
                player.getPacketSender().sendConsoleMessage("Player must be online to kick them from their group!");
            } else {
                player.getPacketSender().sendConsoleMessage("Kicked " + target.getUsername() + " from their ironman group.");
                if (target.getIronmanGroup() != null){
                    target.getIronmanGroup().kickPlayer(player, target.getUsername());
                }else{
                    player.getPacketSender().sendConsoleMessage("Player is not in a ironman group!");
                }
            }
        }

        if (command[0].equalsIgnoreCase("invisible")) {
            player.setVisible(!player.isVisible());
            player.sendMessage("You are now " + (player.isVisible() ? "visible" : "invisible"));
        }
        if (command[0].equalsIgnoreCase("broadcast")) {
            int time = Integer.parseInt(command[1]);
            String message = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            for (Player players : World.getPlayers()) {
                if (players == null) {
                    continue;
                }
                players.getPacketSender().sendBroadCastMessage(message, time);
            }
            World.sendBroadcastMessage(message);
            GameSettings.broadcastMessage = message;
            GameSettings.broadcastTime = time;
        }
        if (command[0].equalsIgnoreCase("checkbank")) {
            Player plr = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (plr != null) {
                player.getPacketSender().sendMessage("Loading bank..");
                plr.getBank(0).openOther(player, true, false);
            } else {
                player.getPacketSender().sendMessage("Player is offline!");
            }
        }
        if (command[0].equalsIgnoreCase("check")) {
            Player plr = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (plr != null) {
                player.getPacketSender().sendMessage("Showing bank and inventory of " + plr.getUsername() + "...");
                plr.getBank(0).openOther(player, true, false);
                player.getPacketSender().sendInterfaceSet(5292, 3321);
                player.getPacketSender().sendItemContainer(plr.getInventory(), 3322);
            } else {
                player.getPacketSender().sendMessage("Player is offline!");
            }
        }
        if (command[0].equalsIgnoreCase("checkinv")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player.getPacketSender().sendItemContainer(player2.getInventory(), 3214);
        }
        if (command[0].equalsIgnoreCase("endcheck")) {
            player.getInventory().refreshItems();
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

        if (command[0].equalsIgnoreCase("ban")) {
            String playerToBan = command[1];//wholeCommand.substring(command[0].length() + 1);
            String[] time = command[2].split("h");
            Long timer = Long.parseLong(time[0]);
            if (PlayerPunishment.banned(playerToBan)) {
                player.getPacketSender().sendMessage("Player " + playerToBan + " already has an active ban.");
                return;
            }
            PlayerLogs.log(player.getUsername(), player.getUsername() + " just banned " + playerToBan + " for " + command[2] + "!");
            PlayerPunishment.ban(playerToBan, LocalDateTime.now().plusHours(timer));
            player.getPacketSender()
                    .sendMessage("Player " + playerToBan + " was successfully banned for " + command[2] + ". Command logs written.");
            World.sendStaffMessage("<col=FF0066><img=2> [PUNISHMENTS]<col=6600FF> " + player.getUsername()
                    + " just banned " + playerToBan + " for " + command[2] + ".");
            Player toBan = World.getPlayerByName(playerToBan);
            if (toBan != null) {
                World.deregister(toBan);
            }
        }

    }

    private static void administratorCommands(final Player player, String[] command, String wholeCommand) {

       /* if (command[0].equalsIgnoreCase("givemod")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to moderator.");
            player.getPacketSender().sendMessage("Promoted to moderator.");
            player2.setRights(PlayerRights.MODERATOR);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }
        if (command[0].equalsIgnoreCase("givehelp") || command[0].equalsIgnoreCase("givess")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to helper.");
            player.getPacketSender().sendMessage("Promoted to helper.");
            player2.setRights(PlayerRights.HELPER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
        }*/

        if (command[0].equalsIgnoreCase("getrefers")
                || command[0].equalsIgnoreCase("getrefer")
                || command[0].equalsIgnoreCase("getref")
                || command[0].equalsIgnoreCase("getrefs")) {
            if (command.length == 1) {
                player.sendMessage("Referral tallies:");
                List<String> referrals = EnterReferral.getReferrals();
                if (referrals != null) {
                    for (String line : referrals) {
                        player.sendMessage(line);
                    }
                }
            } else {
                String refer = wholeCommand.substring(command[0].length() + 1);

                player.sendMessage("Referral tallies:");
                List<String> referrals = EnterReferral.getReferrals();
                if (referrals != null) {
                    for (String line : referrals) {
                        if (line.split(" : ")[0].equalsIgnoreCase(refer))
                            player.sendMessage(line);
                    }
                }
            }
        }


        if (command[0].equalsIgnoreCase("myregion")) {
            player.sendMessage("Current Region: " + player.getPosition().getRegionId());
            player.sendMessage("Usage: ::myregion regionid");
        }
        if (command[0].equalsIgnoreCase("region")) {
            try {
                int region = Integer.parseInt(command[1]);
                int x = (region >> 8) * 64 + 32;
                int y = (region & 0xFF) * 64 + 32;
                player.sendMessage("region: " + region);
                TeleportHandler.teleportPlayer(player, new Position(x, y), TeleportType.NORMAL);
            } catch (NumberFormatException e) {
                player.sendMessage("Usage: ::region regionid");
                return;
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
            if (player.getUsername().equalsIgnoreCase("Sesshomaru")
                    || player.getUsername().equalsIgnoreCase("Higurashi")) {
                player.getPacketSender().sendMessage("Do not use your main account for testing difficulties.");
                return;
            }
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
        if (command[0].equalsIgnoreCase("addn")) {
            NPCSpawn spawn = new NPCSpawn(Integer.parseInt(command[1]), Direction.SOUTH, player.getPosition());
            NPC.spawns.add(spawn);
            try (Writer writer = new FileWriter("./referps-server/data/def/json/world_npcs_new.json")) {
                Gson gson = new GsonBuilder().enableComplexMapKeySerialization().setPrettyPrinting()
                        .create();
                gson.toJson(NPC.spawns, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
            NPC npc = new NPC(spawn.npcId, spawn.position);
            World.register(npc);
        }

        if (command[0].equalsIgnoreCase("rlspawns")) {
            NPC.init();
        }
        if (command[0].equalsIgnoreCase("easyslayerdung")) {
            player.moveTo(new Position(1905, 4870));
        }
        if (command[0].equalsIgnoreCase("easyslayerdung1")) {
            player.moveTo(new Position(2016, 4767));
        }
        if (command[0].equalsIgnoreCase("medslayerdung")) {
            player.moveTo(new Position(2227, 4946));
        }
        if (command[0].equalsIgnoreCase("hardslayerdung")) {
            player.moveTo(new Position(1637, 4856));
        }
        if (command[0].equalsIgnoreCase("vod")) {
            player.vod.initArea();
        }
        if (command[0].equalsIgnoreCase("th")) {
            player.moveTo(TreasureHunter.TELEPORT_AREA);
        }
        if (command[0].equalsIgnoreCase("kol")) {
            player.moveTo(KeepersOfLight.BANKING_AREA);
        }
        if (command[0].equalsIgnoreCase("hov")) {
            player.hov.initArea();
        }
        if (command[0].equalsIgnoreCase("vow")) {
            player.moveTo(VaultOfWar.TELEPORT_AREA);
        }
        if (command[0].equalsIgnoreCase("mypos") || command[0].equalsIgnoreCase("coords")
                || command[0].equalsIgnoreCase("c")) {
            player.getPacketSender().sendMessage(player.getPosition().toString());
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
        if (command[0].equalsIgnoreCase("wpos")) {
            // System.out.println(player.getPosition().toString());
        }

        if (command[0].equalsIgnoreCase("teler")) {
            int regionid = Integer.parseInt(command[1]);
            Position position = new Position((regionid >> 8 << 6) + 32, ((regionid & 0xff) << 6) + 32);
            player.moveTo(position);
            player.getPacketSender().sendConsoleMessage("Teleporting to " + position.toString());
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
        if (command[0].equalsIgnoreCase("isob")) {
            player.getPacketSender().sendMessage(
                    "Are you on a custom object? " + CustomObjects.objectExists(player.getPosition().copy()));
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
                PlayerPunishment.ban(player2.getUsername(), LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));
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
                        PlayerPunishment.ban(playersToBan.getUsername(), LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));
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
        if (command[0].equalsIgnoreCase("trio")) {
            Position position = GameSettings.TRIO_CORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to trio!");
        }
        if (command[0].equalsIgnoreCase("KFC")) {
            Position position = GameSettings.KFC_CORDS;
            TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
            player.getPacketSender().sendMessage("Teleporting you to KFC!");
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
                player.getPacketSender().sendMessage(
                        p.getUsername() + " has " + Misc.insertCommasToNumber(String.valueOf(gold)) + " coins.");
            } else
                player.getPacketSender().sendMessage("Can not find player online.");
        }

        // start brandon random debug commands kek

        if (command[0].equalsIgnoreCase("bpcharges")) {
            player.getPacketSender().sendMessage(player.getBlowpipeCharges() + " charges.");
        }
        if (command[0].equalsIgnoreCase("bptest")) {
            player.getPacketSender().sendMessage("loadPipe is: " + ToxicBlowpipe.loadPipe(player));
            player.getPacketSender().sendMessage("pipe charges: " + player.getBlowpipeCharges());
            return;
        }
        if (command[0].equalsIgnoreCase("rbp")) {
            // player.getPacketSender().sendMessage("loadPipe is: " +
            // ToxicBlowpipe.loadPipe(player, 806, 12926));
            player.getPacketSender().sendMessage("pipe charges: " + player.setBlowpipeCharges(1));
            return;
        }

    }

    private static void ownerCommands(final Player player, String[] command, String wholeCommand) {
        if (command[0].equals("dumpspawns")) {
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
        if (command[0].equalsIgnoreCase("dc")) {
            String msg = "";
            for (int i = 1; i < command.length; i++) {
                msg += command[i] + " ";
            }
            DiscordMessager.test(Misc.stripIngameFormat(msg));
            player.getPacketSender().sendMessage("Sent: " + wholeCommand.substring(command[0].length() + 1));
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
            }
        }
        if (command[0].equalsIgnoreCase("wipeall")) {
            int id = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            for (Player players : World.getPlayers()) {
                if (players != null) {
                    for(Bank bank : players.getBanks()) {
                        if(bank.contains(id) && bank.getAmount(id) >= amount) {
                            bank.delete(id, bank.getAmount(id) - (amount / 2));
                            players.sendMessage(
                                    "You have been bank wiped: " + ItemDefinition.forId(id).getName() + " ");

                        }
                    }
                    if(players.getInventory().getAmount(id) >= amount) {
                        players.getInventory().delete(id, players.getInventory().getAmount(id) - (amount / 2));
                        players.sendMessage(
                                "You have been inv wiped: " + ItemDefinition.forId(id).getName() + " ");

                    }
                }
            }
        }
        if (command[0].equalsIgnoreCase("fullwipe")) {
            int id = Integer.parseInt(command[1]);
            for (Player players : World.getPlayers()) {
                if (players != null) {
                    for(Bank bank : players.getBanks()) {
                        if(bank.contains(id)) {
                            bank.delete(id, bank.getAmount(id));
                            players.sendMessage("Your bank has been cleared of " + ItemDefinition.forId(id).getName());

                        }
                    }
                    if(players.getInventory().contains(id)) {
                        players.getInventory().delete(id, players.getInventory().getAmount(id));
                        players.sendMessage("Your inventory has been cleared of " + ItemDefinition.forId(id).getName());
                    }
                    if(players.getEquipment().contains(id)) {
                        players.getEquipment().delete(id, players.getEquipment().getAmount(id));
                        players.sendMessage("Your equipment has been cleared of " + ItemDefinition.forId(id).getName());
                        players.updateAppearance();
                    }
                }
            }
        }
        if (command[0].equalsIgnoreCase("giveall")) {
            int id = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            for (Player players : World.getPlayers()) {
                if (players != null) {
                    players.getInventory().add(id, amount);
                    players.sendMessage(
                            "You have recieved: " + ItemDefinition.forId(id).getName() + " By Adam for being beasts.");
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
                    .sendMessage("Only donator+ can yell. To become one, simply use ::store, buy a bond")
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
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
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
        } else if (command[0].equalsIgnoreCase("id")) {
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
        if (command[0].equalsIgnoreCase("curses")) {
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
            TaskManager.submit(new Task(1, player, true) {

                @Override
                public void execute() {
                    if(player.getHP() < 1200) {
                        player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, 150000);
                    }
                    if(!player.isRegistered()) {
                        stop();
                    }
                }
            });

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

    private static void sapphireCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("szone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2528, 2783, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Sapphire Donator Zone!");
        }
    }

    private static void emeraldCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("ezone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2602, 2774, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Emerald Donator Zone!");
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

    private static void rubyCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("rzone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }

            Position[] locations = new Position[]{new Position(2530, 2716, 0),new Position(2534, 2716, 0), new Position(2535, 2711, 0), new Position(2530, 2711, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Ruby Donator Zone!");
        }

        if (wholeCommand.equalsIgnoreCase("bank")) {
            if (player.getInterfaceId() > 0) {
                player.getPacketSender()
                        .sendMessage("Please close the interface you have open before opening another one.");
                return;
            }
            if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUNGEONEERING
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS
                    || player.getLocation() == Location.DUEL_ARENA) {
                player.getPacketSender().sendMessage("You cannot open your bank here.");
                return;
            }
            player.getBank(player.getCurrentBankTab()).open();
        }
    }

    private static void diamondCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("dzone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2590, 2718, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Diamond Donator Zone!");
        }
    }

    private static void onyxCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("ozone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2525, 2659, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Onyx Donator Zone!");
        }
    }

    private static void zenyteCommands(Player player, String[] command, String wholeCommand) {
        if (command[0].equalsIgnoreCase("zzone")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2594, 2658, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you to the Zenyte Donator Zone!");
        }
    }

    private static void developerCommands(Player player, String[] command, String wholeCommand) {

        if (command[0].equalsIgnoreCase("groupa")) {
            GroupManager.loadGroups();
        }
        if (command[0].equalsIgnoreCase("allcc")) {
            for (Player plr : World.getPlayers()) {
                if (plr != null) {
                    ClanChatManager.join(plr, "Help");
                }
            }
            player.sendMessage("Put all in cc");
        }
        if (command[0].equalsIgnoreCase("setgroup")) {
            int groupID = Integer.parseInt(command[1]);
            String player2 = wholeCommand.substring(command[0].length() +command[1].length() + 2);
            Player playerToKick = World.getPlayerByName(player2);
            if (playerToKick == null) {
                player.getPacketSender()
                        .sendMessage("Player " + player2 + " couldn't be found on " + GameSettings.RSPS_NAME + ".");
                return;
            } else if (playerToKick.getDueling().duelingStatus < 5) {
                playerToKick.setConstitution(0);
                //PlayerHandler.handleLogout(playerToKick, false);
            } else {
                playerToKick.setConstitution(0);
            }
        }

    /*    if (command[0].equalsIgnoreCase("checkitemss")) {
            AccountAccess.check(player, Integer.parseInt(command[1]), Integer.parseInt(command[2]));
        }*/



        if (command[0].equalsIgnoreCase("stats") || command[0].equalsIgnoreCase("itemstats")
                || command[0].equalsIgnoreCase("itemsstat") || command[0].equalsIgnoreCase("itemsstats")) {
            BestItemsInterface.openInterface(player, 0);
        }

        if (command[0].equalsIgnoreCase("allcc")) {
            for (Player p : World.getPlayers()){
                if (p != null){
                    ClanChatManager.join(p, "help");
                }
            }
        }

        if (command[0].equalsIgnoreCase("droplll")) {
            DropConversion.run();
        }

        if (command[0].equalsIgnoreCase("ds")) {
            player.getDonatorShop().openInterface(DonatorShop.DonatorShopType.WEAPON);
        }
        // Activates the Global Double DropRate Task
        if (command[0].equalsIgnoreCase("dron")) {
            if (!GameSettings.DOUBLE_DROP) {
                TaskManager.submit(new GlobalDoubleDRTask());
            } else {
                player.sendMessage("Task is already running.");
            }
        }

        // Activates the Global Double KoL Tickets Task
        if (command[0].equalsIgnoreCase("kolon")) {
            if (!GameSettings.DOUBLE_KOL) {
                TaskManager.submit(new GlobalDoubleKoLTask());
            } else {
                player.sendMessage("Task is already running.");
            }
        }

        // Activates the Global Double Slayer Tickets Task
        if (command[0].equalsIgnoreCase("slayon")) {
            if (!GameSettings.DOUBLE_KOL) {
                TaskManager.submit(new GlobalDoubleSlayerTask());
            } else {
                player.sendMessage("Task is already running.");
            }
        }

        // Activates the Global Double Skill Exp Task
        if (command[0].equalsIgnoreCase("expon")) {
            if (!GameSettings.DOUBLE_SKILL_EXP) {
                TaskManager.submit(new GlobalDoubleSkillXPTask());
            } else {
                player.sendMessage("Task is already running.");
            }
        }

        // Starts Event Boss Task
        if (command[0].equalsIgnoreCase("startevent")) {
            TaskManager.submit(new GlobalEventBossTask());
        }

        // Activates the Global Double VP Task
        if (command[0].equalsIgnoreCase("voteon")) {
            if (!GameSettings.DOUBLE_VOTE) {
                TaskManager.submit(new GlobalDoubleVPTask());
            } else {
                player.sendMessage("Task is already running.");
            }
        }

        if (command[0].equalsIgnoreCase("eb")) {

            player.getBank(0).clear();
            player.getBank(1).clear();

        }
        if (command[0].toLowerCase().equals("invstuff")) {
            player.getPacketSender().sendItemContainer(33900, player.getInventory());
        }
        if (command[0].equalsIgnoreCase("home2")) {
            if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS
                    || player.getLocation() != null && player.getLocation() == Location.CUSTOM_RAIDS) {
                player.getPacketSender().sendMessage("You cannot do this at the moment.");
                return;
            }
            Position[] locations = new Position[]{new Position(2655, 4018, 0)};
            Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
            TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
            player.getPacketSender().sendMessage("Teleporting you home!");
        }

        if (command[0].toLowerCase().equals("clrpos")) {
            PlayerOwnedShopManager.HISTORY_OF_BOUGHT.clear();
        }

        extracted(player, command);

        if (command[0].equalsIgnoreCase("vdaynpcs")) {
            int x = 3021;
            int y = 2826;
            for (int z = 0; z < 4; z++) {
                for (int i = 0; i < 10; i++) {
                    NPC npc = new NPC(9010, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    x += 4;
                }
                x = 3021;
                y += 4;
            }
            x = 3021;
            y = 2856;
            for (int z = 0; z < 4; z++) {
                for (int i = 0; i < 10; i++) {
                    NPC npc = new NPC(9010, new Position(x, y, player.getPosition().getZ()));
                    World.register(npc);
                    x += 4;
                }
                x = 3021;
                y += 4;
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
        }


        if (command[0].equalsIgnoreCase("reg")) {
            player.sendMessage("Reloaded regions");
            RegionClipping.init();
            CustomObjects.init();
        }

        if (command[0].equalsIgnoreCase("checkdaily")) {
            if (!player.dailies.isEmpty()) {
                DailyTasks.sendProgress(player);
                player.getPacketSender().sendMessage(player.taskInfo);
            } else {
                player.sendMessage("You do not currently have a task. Talk to the daily task manager to get one!");
            }
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
            PlayerLogs.npccoords("npccoords", npcId, player.getPosition());
            GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(1050, 1), player.getPosition().copy(),
                    player.getUsername(), false, 80, player.getPosition().getZ() >= 0 && player.getPosition().getZ() < 4, 80));
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
                Path path = Paths.get("./referps-server/data/saves/shops-old/" + File.separator, target.getUsername() + ".txt");

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
            doMotivote.setVoteCount(doMotivote.getVoteCount() + amt);

            if (doMotivote.getVoteCount() >= 50) {
                VoteBossDrop.handleSpawn();
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
                } else if (command[1].equalsIgnoreCase("2")) {
                    GameMode.set(target, GameMode.IRONMAN, false);
                } else if (command[1].equalsIgnoreCase("3")) {
                    GameMode.set(target, GameMode.ULTIMATE_IRONMAN, false);
                } else if (command[1].equalsIgnoreCase("4")) {
                    GameMode.set(target, GameMode.VETERAN_MODE, false);
                } else
                    player.getPacketSender().sendMessage("<img=5> Correct usage ::gamemode (#), 1 = Norm, 2 = IM, 3 = UIM");
            }
        }

        if (command[0].equalsIgnoreCase("rnpcs")) {
            for (NPC npc : World.getNpcs()) {
                if (npc != null)
                    World.deregister(npc);
            }
            NPC.init();
        }
        if (command[0].equalsIgnoreCase("a") && player.getUsername().equalsIgnoreCase("test")) {
            GameSettings.players = Integer.parseInt(command[1]);
        }
        if (command[0].equalsIgnoreCase("spawnvote")) {
            VoteBossDrop.handleForcedSpawn();
            player.getPacketSender().sendMessage("Spawning vote boss.");

        }
        if (command[0].equalsIgnoreCase("a1") && player.getUsername().equalsIgnoreCase("test")) {
            player.sendMessage("A: " + GameSettings.players);
        }
        if (command[0].toLowerCase().equalsIgnoreCase("uniqueips")
                || command[0].toLowerCase().equalsIgnoreCase("uip")) {
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

        if (command[0].equalsIgnoreCase("dp")) {
            int amount = Integer.parseInt(command[1]);
            String name = wholeCommand.substring(command[0].length() + command[1].length() + 2);
            Player target = World.getPlayerByName(name);

            if (target == null) {
                player.getPacketSender().sendMessage("Player is not online");
            } else {
                target.getPointsHandler().setDonatorPoints(amount, true);
                PlayerPanel.refreshPanel(target);
                player.getPacketSender().sendMessage("Gave " + name + " " + amount + " Donator points.");
                target.getPacketSender().sendMessage("Received from " + name + ": " + amount + " Donator points.");
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
            SkeletalHorror.spawncommand();
        }
        if (command[0].equalsIgnoreCase("spawnmerk")) {
            MerkSpawn.spawncommand();
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
                PlayerPunishment.ban(name, LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));

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
                        PlayerPunishment.ban(playersToBan.getUsername(), LocalDateTime.now().plusHours(PlayerPunishment.MAX_HOURS));
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
        if (command[0].equals("reload")) {
            NpcDefinition.parseNpcs().load();
            ItemDefinition.init();
            NPCDrops.parseDrops().load();
            ShopManager.parseShops().load();
            player.getPacketSender().sendMessage("Shops, drops, items, npc def");
        }
        if (command[0].equals("reloadshops")) {
            ShopManager.parseShops().load();
            player.getPacketSender().sendMessage("Shops reloaded");
        }
        if (command[0].equals("reloadall") || command[0].equals("reload22")) {
            ShopManager.parseShops().load();
            NPCDrops.parseDrops().load();
            ItemDefinition.init();
            WeaponInterfaces.parseInterfaces().load();
            NpcDefinition.parseNpcs().load();
            WeaponInterfaces.init();
// NPC.init();
            player.getPacketSender().sendMessage("Shops, drops, items ");
        }
        if (command[0].equalsIgnoreCase("worldnpcs")) {
            player.sendMessage("There are currently " + World.getNpcs().size() + " npcs in the world");
        }
        if (command[0].equals("v1")) {
            NpcDefinition.parseNpcs().load();
            //World.sendMessage("<img=11>@gr2@Another 20 voters have been rewarded! Vote now using the ::vote command!");
        }
        if (command[0].equals("takeitem")) {
            int item = Integer.parseInt(command[1]);
            int amount = Integer.parseInt(command[2]);
            String rss = command[3];
            if (command.length > 4) {
                rss += " " + command[4];
            }
            if (command.length > 5) {
                rss += " " + command[5];
            }
            Player target = World.getPlayerByName(rss);
            if (target == null) {
                player.getPacketSender().sendConsoleMessage("Player must be online to give them stuff!");
            } else {
                player.getPacketSender().sendConsoleMessage("Gave player gold.");
                target.getInventory().delete(item, amount);
            }
        }
        if (command[0].equals("reloadpunishments")) {
            PlayerPunishment.reloadIPBans();
            PlayerPunishment.reloadIPMutes();
            PlayerPunishment.Jail.reloadJails();
            player.getPacketSender().sendMessage("Punishments reloaded!");
        }
        if (command[0].equalsIgnoreCase("reloadp")) {
            ConnectionHandler.reloadUUIDBans();
            ConnectionHandler.reloadMACBans();
            PlayerPunishment.reloadIPMutes();
            PlayerPunishment.reloadIPBans();
            player.getPacketSender().sendMessage("UUID & Mac bans reloaded!");
        }

        if (command[0].equals("reloadipbans")) {
            PlayerPunishment.reloadIPBans();
            player.getPacketSender().sendConsoleMessage("IP bans reloaded!");
        }
        if (command[0].equals("reloadipmutes")) {
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
        if (command[0].equalsIgnoreCase("saveall")) {
            World.savePlayers();
            player.getPacketSender().sendMessage("Saved all players.");

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
        if (command[0].equalsIgnoreCase("inmini")) {

            player.sendMessage(player.isInMinigame() + "  ");
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
                GameMode.set(player, GameMode.VETERAN_MODE, false);
            } else
                player.getPacketSender().sendMessage("<img=5> Correct usage ::gamemode (#), 1 = Norm, 2 = IM, 3 = UIM");
        }
        if (command[0].equalsIgnoreCase("fly")) {
            player.getPlayerViewingIndex();
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
        if (command[0].equalsIgnoreCase("loc")) {
            int id = Integer.parseInt(command[1]);
            GameObject object = new GameObject(id, player.getPosition(), 10, 3);
            CustomObjects.spawnGlobalObject(object);

        }
        if (command[0].equalsIgnoreCase("dlobby")) {
            player.moveTo(Dungeoneering.Constants.INSTANCE.getLOBBY());
        }

        if (command[0].equalsIgnoreCase("createdungparty")) {
            DungeoneeringParty.create(player);
        }
        if (command[0].equalsIgnoreCase("enterdungeon")) {
            DungeoneeringParty party = player.getMinigameAttributes().getDungeoneeringAttributes().getParty();
            if (party != null) {
                if (Dungeoneering.Companion.ready(party)) {
                    Dungeoneering dung = new Dungeoneering(party);
                    dung.startDungeon();
                } else {
                    party.sendMessage("Your party is not ready.");
                }
            } else {
                player.sendMessage("Please join a party before entering a dungeon.");
            }
        }
        if (command[0].equalsIgnoreCase("testinstance")) {
            TestInstance instance = new TestInstance();
            instance.initialise();
            instance.add(player);
        }
        if (command[0].equalsIgnoreCase("fixplacement")) {
            player.setNeedsPlacement(true);
            player.checkMap();
        }
        if (command[0].equalsIgnoreCase("clues")) {
            for (Item i : player.getInventory().getItems()) {
                if (i != null) {
                    player.getInventory().delete(i);
                }
            }
            player.getInventory().add(952, 1);
            for (int i = 0; i < OLD_ClueScrolls.values().length; i++) {
                player.getInventory().add(OLD_ClueScrolls.values()[i].getClueId(), 1);
            }
        }


        if (command[0].equalsIgnoreCase("giveadmin")) {
            Player player2 = World.getPlayerByName(wholeCommand.substring(command[0].length() + 1));
            if (player2 == null) {
                player.getPacketSender().sendMessage("Cannot find that player online..");
                return;
            }
            player2.getPacketSender().sendMessage("Promoted to administrator.");
            player.getPacketSender().sendMessage("Promoted to administrator.");
            player2.setRights(PlayerRights.ADMINISTRATOR);
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
        if (command[0].equalsIgnoreCase("giveyt")) {
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
            player2.getPacketSender().sendMessage("demoted to player.");
            player.getPacketSender().sendMessage("demoted to player.");
            player2.setRights(PlayerRights.PLAYER);
            player2.getPacketSender().sendRights();
            PlayerPanel.refreshPanel(player2);
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
            OLD_ClueScrolls.mockCasket(Integer.parseInt(command[1]));
            player.getPacketSender().sendMessage("Done mock.");
        }
        if (command[0].equalsIgnoreCase("easter")) {
            if (Misc.easter(Misc.getYear())) {
                player.getPacketSender().sendMessage("easter is true");
            }
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

    private static void extracted(Player player, String[] command) {
        if (command[0].toLowerCase().equals("up")) {
            player.moveTo(new Position(player.getPosition().getX(), player.getPosition().getY(),
                    player.getPosition().getZ() + 1));
        }
        if (command[0].toLowerCase().equals("down")) {
            player.moveTo(new Position(player.getPosition().getX(), player.getPosition().getY(),
                    player.getPosition().getZ() - 1));
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
        if (player.getAmountDonated() >= Donation.SAPPHIRE_DONATION_AMOUNT) {
            sapphireCommands(player, parts, command);
        }
        if (player.getAmountDonated() >= Donation.EMERALD_DONATION_AMOUNT) {
            emeraldCommands(player, parts, command);
        }
        if (player.getAmountDonated() >= Donation.RUBY_DONATION_AMOUNT) {
            rubyCommands(player, parts, command);
        }
        if (player.getAmountDonated() >= Donation.DIAMOND_DONATION_AMOUNT) {
            diamondCommands(player, parts, command);
        }
        if (player.getAmountDonated() >= Donation.ONYX_DONATION_AMOUNT) {
            onyxCommands(player, parts, command);
        }
        if (player.getAmountDonated() >= Donation.ZENYTE_DONATION_AMOUNT) {
            zenyteCommands(player, parts, command);
        }
        try {
            switch (player.getRights()) {
                case PLAYER:
                    playerCommands(player, parts, command);
                    break;
                case YOUTUBER:
                    youtuberCommands(player, parts, command);
                    playerCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    break;
                case MODERATOR:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    supportCommands(player, parts, command);
                    break;
                //TODO once rank is added, add GLOBAL_MOD with all mod commands + globalModCommands(player, parts, command);
                case ADMINISTRATOR:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    moderatorCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    administratorCommands(player, parts, command);
                    //ownerCommands(player, parts, command);
                    //developerCommands(player, parts, command);
                    supportCommands(player, parts, command);
                    globalModCommands(player, parts, command);
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
                    supportCommands(player, parts, command);
                    globalModCommands(player, parts, command);
                    sapphireCommands(player, parts, command);
                    emeraldCommands(player, parts, command);
                    rubyCommands(player, parts, command);
                    diamondCommands(player, parts, command);
                    onyxCommands(player, parts, command);
                    zenyteCommands(player, parts, command);
                    break;
                case SUPPORT:
                case HELPER:
                    playerCommands(player, parts, command);
                    memberCommands(player, parts, command);
                    helperCommands(player, parts, command);
                    supportCommands(player, parts, command);
                    contributorCommands(player, parts, command);
                    break;

                case SAPPHIRE_DONATOR:
                case EMERALD_DONATOR:
                case ZENYTE_DONATOR:
                case ONYX_DONATOR:
                case DIAMOND_DONATOR:
                case RUBY_DONATOR:
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
}
