package com.ruse.net.packet.impl;

import com.ruse.GameSettings;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Bank.BankSearchAttributes;
import com.ruse.model.container.impl.GroupIronmanBank;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.WeaponInterfaces.WeaponInterface;
import com.ruse.model.input.impl.*;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.World;
import com.ruse.world.allornothing.DoubleOrNothing;
import com.ruse.world.content.*;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.bossEvents.BossEventInterfaceHandler;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaRewards;
import com.ruse.world.content.clan.ClanChat;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.clan.Guild;
import com.ruse.world.content.combat.magic.Autocasting;
import com.ruse.world.content.combat.magic.MagicSpells;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.combat.weapon.FightType;
import com.ruse.world.content.dailyTask.DailyTaskInterface;
import com.ruse.world.content.deathsanctum.DeathSanctumRewards;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueOptions;
import com.ruse.world.content.discordbot.DiscordIntegration;
import com.ruse.world.content.events.EventManager;
import com.ruse.world.content.goldenscratch.ScratchCard;
import com.ruse.world.content.grandLottery.GrandLottery;
import com.ruse.world.content.grandexchange.GrandExchange;
import com.ruse.world.content.groupironman.GroupManager;
import com.ruse.world.content.instanceMananger.InstanceInterfaceHandler;
import com.ruse.world.content.loyalty_streak.LoyaltyStreakManager;
import com.ruse.world.content.minigames.impl.Dueling;
import com.ruse.world.content.minigames.impl.PestControl;
import com.ruse.world.content.polling.PollCreation;
import com.ruse.world.content.polling.PollManager;
import com.ruse.world.content.raffle.RaffleInterface;
import com.ruse.world.content.raids.RaidsManager;
import com.ruse.world.content.serverperks.ServerPerkContributionInput;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.ChatboxInterfaceSkillAction;
import com.ruse.world.content.skill.impl.construction.Construction;
import com.ruse.world.content.skill.impl.crafting.LeatherMaking;
import com.ruse.world.content.skill.impl.crafting.Tanning;
import com.ruse.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruse.world.content.skill.impl.dungeoneering.DungeoneeringParty;
import com.ruse.world.content.skill.impl.dungeoneering.ItemBinding;
import com.ruse.world.content.skill.impl.fletching.Fletching;
import com.ruse.world.content.skill.impl.herblore.ingredientsBook;
import com.ruse.world.content.skill.impl.slayer.Slayer;
import com.ruse.world.content.skill.impl.smithing.SmithingData;
import com.ruse.world.content.skill.impl.summoning.PouchMaking;
import com.ruse.world.content.skill.impl.summoning.SummoningTab;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.content.wellForGlobalBosses.WellForGlobalBossesInterface;
import com.ruse.world.content.youtube.YoutubeMgr;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.StartScreen;

import java.util.Arrays;
import java.util.List;

/**
 * This packet listener manages a button that the player has clicked upon.
 *
 * @author Gabriel Hannason
 */

public class ButtonClickPacketListener implements PacketListener {

    public static final int OPCODE = 185;
    private static List<Integer> NEW_TELEPORT_BUTTONS_TAB = Arrays.asList(28215, 28216, 28217, 28218, 28219, 28220);
    private static List<Integer> NEW_TELEPORT_BUTTONS = Arrays.asList(
            28231, 28232, 28233, 28234, 28235, 28236, 28237, 28238,
            28239, 28240, 28241, 28242,
            28243, 28244, 28245, 28246, 28247, 28248, 28249, 28250, 28251, 28252, 28253, 28254,
            28307, 28308, 28309, 28310, 28311, 28312, 28313, 28314, 28315, 28316, 28317, 28318, 28319,
            28320, 28321, 28322, 28323, 28324, 28325, 28326, 28327, 28328, 28329, 28330,
            28331, 28332, 28333, 28334, 28335, 28336, 28337, 28338, 28339);

    @Override
    public void handleMessage(Player player, Packet packet) {
        int bankid = 0;
        int id = packet.readInt();
        if (id >= 32768 && id < 65535) {
            id -= 65536;
        }

        if (player.getRights().isDeveloperOnly()) {
            player.getPacketSender().sendMessage("Clicked button: " + id);
        }

        if (id == -23477) {
            player.getPacketSender().sendEnterAmountPrompt("How much would you like to contribute?");
            player.setInputHandling(new ServerPerkContributionInput());
            return;
        }

        if (player.getAchievements().handleButtonClick(id)) {
            return;
        }

        if (player.getStarterTasks().handleButtonClick(id)) {
            return;
        }
        if (checkHandlers(player, id))
            return;

        player.getGambling().handleChoice(id);

        player.getAssassinsGuild().handleButton(id);
        player.getRaffleInterface().handleButton(id);

        ClanChatManager.buttonClick(player, id);
        EventManager.buttonClick(player, id);

        if (PlayerViewer.handleButton(player, id)) {
            return;
        }

        if (player.getCardPack().handleButton(id)) {
            return;
        }

        if (player.getVDayEvent().handleButton(id)) {
            return;
        }

        if (player.getPlayerOwnedShopManager().handleButton(id)) {
            return;
        }
        if (player.getLeaderboardManager().handleButton(id)) {
            return;
        }

        if (player.getGoodieBag().handleClick(id)) {
            return;
        }
        if (PossibleLootInterface.handleButton(player, id)) {
            return;
        }

        if (player.getAfkCombatChecker().handleButton(id)) {
            return;
        }

        if (player.getCollectionLogManager().handleButton(id)) {
            return;
        }

        if (player.getTitlesManager().handleButton(id)) {
            return;
        }


        if (RaidsManager.handleButton(player, id)) {
            return;
        }

        player.getUpgradeListener().handleButton(id);

        new ScratchCard(player).reveal(id);
        new DailyTaskInterface(player).button(id);
        new DailyTaskInterface(player).tabClicking(id);
        new InstanceInterfaceHandler(player).handleButtons(id);
        new WellForGlobalBossesInterface(player).button(id);


        if (id >= 111200 && id <= 111230){
            for (CustomTeleportInterface.Globals global : CustomTeleportInterface.Globals.values()){
                if (id == global.getButtonClick()){
                    TeleportHandler.teleportPlayer(player, global.getPosition(), player.getSpellbook().getTeleportType());
                }
            }
        }
        switch (id) {
            case 111434:
                player.getCurseOfArrav().openInterface();
                break;
            case 151006:
                player.getCurseOfArrav().acceptQuest();
                break;
            case 19137:
                DiscordIntegration.buttonClick(player);
                break;
            case 19125:
                player.getPacketSender().sendString(1, "https://discord.gg/kXapMuT2XW");
                player.getPacketSender().sendMessage("Attempting to open our Discord Server");
                break;
            case 19122:
                if (player.getDiscordUser() <= 0) {
                    player.setInputHandling(new DiscordIntegrationPrompt());
                    player.getPA().sendEnterInputPrompt("Please enter the unique code you received on discord.");
                } else {
                    player.setDiscordTag(null);
                    player.setDiscordUser(-1);
                    player.sendMessage("You have successfully diconnected your discord account!");
                    DiscordIntegration.updateDiscordInterface(player);
                    if (DiscordIntegration.connectedAccounts.containsKey(player.getUsername()))
                        DiscordIntegration.connectedAccounts.remove(player.getUsername());
                }
                break;
            case -23689:
                player.setRenderPlayerEquipment(true);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                player.getLocalPlayers().clear();
                return;
            case -23688:
                player.setRenderPlayerEquipment(false);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                player.getLocalPlayers().clear();
                return;

            case -23686:
                player.setRenderPets(true);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                player.getLocalPlayers().clear();
                return;
            case -23685:
                player.setRenderPets(false);
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                player.getLocalPlayers().clear();
                return;


            case -23781:
                player.getPacketSender().sendTabInterface(GameSettings.OPTIONS_TAB, 904);
                return;
            case 15009:
                player.setCosmeticOveride(!player.isCosmeticOveride());
                player.sendMessage("Showing Cosmetic overrides: " + player.isCosmeticOveride());
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                break;
            case -32235:
            case -32285:
                player.setIncludeDR(!player.isIncludeDR());
                player.getPacketSender().sendConfig(2451, player.isIncludeDR() ? 1 : 0);
                player.sendMessage("Include Drop rate: " + (player.isIncludeDR() ? "Enabled" : "Disabled"));
                player.getPacketSender().sendString(33300, "Include DR Bonus @or1@(@whi@"+ NPCDrops.dropRateBoost(player)+"%@or1@)");
                player.getPacketSender().sendString(33250, "Include DR Bonus @or1@(@whi@"+ NPCDrops.dropRateBoost(player)+"%@or1@)");
                if (player.getDropInterfaceNPC() > 0)
                    DropsInterface.buildRightSide(player, player.getDropInterfaceNPC());
                break;
            case 118010:
                if (player.getLocation() == Location.DEATH_SANCTUM_LOBBY)
                DeathSanctumRewards.open(player);
                else
                    ChambersOfAnimaRewards.open(player);

                break;
            case 118011:
                if (player.getLocation() == Location.DEATH_SANCTUM_LOBBY)
                DeathSanctumRewards.openAll(player);
                else
                    ChambersOfAnimaRewards.openAll(player);
                break;
            case 118012:
                if (player.getLocation() == Location.DEATH_SANCTUM_LOBBY)
                    player.sendMessage("You can sacrifice Lucifer armour pieces to the chest to increase your chances.");
                else
                     player.sendMessage("You can sacrifice SOD or SOD (u) armour pieces to the chest to increase your chances.");
                break;
            case 105011:
                player.getSeasonPass().information();
                break;
            case 21353:
                player.getWheelOfFortune().start();
                break;

            case 21372:
                player.getWheelOfFortune().open();
                break;

            case 21360:
                player.getPacketSender().sendInterfaceRemoval();
                break;

            case 21375:
                player.getWheelOfFortune().start();
                player.getPacketSender().updateInterfaceVisibility(21370, false);
                player.getPacketSender().updateInterfaceVisibility(21362, false);
                break;

            case 25408:
                player.getScratchcard().claimPrize();
                break;

            case 106009:
                if (!player.isBanking() || player.getInterfaceId() != 106000)
                    return;
                GroupIronmanBank.depositItems(player, player.getInventory(), false);
                break;
            case 106012:
                if (!player.isBanking() || player.getInterfaceId() != 106000)
                    return;
                GroupIronmanBank.depositItems(player, player.getEquipment(), false);
                break;

            case 27656:
                if (player.busy() || player.getTrading().inTrade()) {
                    player.getPacketSender().sendMessage("You cannot do that right now.");
                    return;
                }
                player.setInputHandling(new WithdrawBucks());
                player.getPacketSender().sendEnterAmountPrompt("How many 1m Tickets would you like to withdraw?");
                break;
            case 110007:
                player.getCasketOpening().spin();
                break;
            case 110008:
                player.getCasketOpening().quickSpin();
                break;

            case 78395:
                // player.sendMessage("@red@This feature is currently disabled.");
                player.setPlaceholders(!player.isPlaceholders());
                player.getPacketSender().sendConfig(111, player.isPlaceholders() ? 0 : 1);
                break;


            case -29536:
                player.getAchievements().clickedAchievement = true;
                player.getAchievements().drawInterface(0);
                break;
            case -26365:
                player.getAchievements().clickedAchievement = true;
                player.getAchievements().drawInterface(player.getAchievements().currentInterface);
                break;

            case 111603:
                DropsInterface.open(player);
                break;
            case 111604:
                player.getCollectionLogManager().openInterface();
                break;
            case 111605:
                BossLog.open(player);
               // KillTrackerInterface.open(player);
                break;
            case 111606:
                PossibleLootInterface.openInterface(player, PossibleLootInterface.LootData.SUPER);
                break;
            case 111607:
                BestItemsInterface.openInterface(player, 0);
                break;
            case 117007:
                player.getPA().sendInterfaceRemoval();
                break;
            case 111608:
                BestDRInterface.openInterface(player, 0);
                break;
            case 111609:
                player.getSeasonPass().openInterface();
                break;
            case 111610:
                player.getStarterTasks().openInterface();
                break;
            case 111611:
                player.getVotingStreak().openInterface();
                break;
            case 111612:
                if (YoutubeMgr.getVideos()== null ||YoutubeMgr.getVideos().size() <= 1){
                    player.sendMessage("Please wait while the videos load... Try again later.");
                }else {
                    player.getPA().sendYoutubeData();
                    player.getPA().sendInterface(72100);
                }
                break;
            case 111613:
                player.getPA().sendInterface(19130);
                DiscordIntegration.updateDiscordInterface(player);
                break;
            case 111614:
                RaffleInterface.openInterfacePerks(player);
                break;
            case 111615:
                player.getTitlesManager().openInterface();
                break;
            case -5514:
                player.getCustomTeleportInterface().teleport();
                break;

            case -5530:
                player.getCustomTeleportInterface().switchTab(0);
                break;
            case -5528:
                player.getCustomTeleportInterface().switchTab(1);
                break;
            case -5526:
                player.getCustomTeleportInterface().switchTab(2);
                break;
            case -5524:
                player.getCustomTeleportInterface().switchTab(3);
                break;
            case -5522:
                player.getCustomTeleportInterface().switchTab(4);
                break;
            case -5520:
                player.getCustomTeleportInterface().switchTab(5);
                break;

            case 22062:
                player.getCustomTeleportInterface().switchTab(2);
                break;
            case 22067:
                player.getCustomTeleportInterface().switchTab(3);
                break;
            case 22072:
                player.getCustomTeleportInterface().switchTab(4);
                break;
            case 111101:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111000);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111000);
                player.getPacketSender().sendConfig(6000, 0);
                break;
            case 111102:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111300);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111300);
                player.getPacketSender().sendConfig(6000, 1);
                break;
            case 111103:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111500);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111500);
                player.getPacketSender().sendConfig(6000, 2);
                break;
            case 111104:
            case 111602:
                player.getPacketSender().sendInterface(36000);
                player.getAchievements().refreshInterface(player.getAchievements().currentInterface);
                player.getPacketSender().sendConfig(6000, 3);
                break;
            case 111105:
                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111700);
                player.getPacketSender().sendConfig(6000, 4);
                break;
            case 111601:
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
                player.getPacketSender().sendString(index++, color + "::train - Opens up the starter interface with a list of teleports");
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
                player.getPacketSender().sendString(index++, color + "::globals - shows you the time remaining on all global bosses");
                player.getPacketSender().sendString(index++, color + "::bank - opens up your bank ($50 total claimed required)");
                player.getPacketSender().sendString(index++, color + "::players - tells you how many players are currently online");
                player.getPacketSender().sendString(index++, color + "::discord - opens up our discord for Lunite");
                player.getPacketSender().sendString(index++, color + "::vote - opens up our site for voting");
                player.getPacketSender().sendString(index++, color + "::voted - claims your votes");
                player.getPacketSender().sendString(index++, color + "::donate - opens up our donation site");
                player.getPacketSender().sendString(index++, color + "::donated - claims your donation");
                player.getPacketSender().sendString(index++, color + "::dissolveall - dissolves all dissolvable items in your inv");
                player.getPacketSender().sendString(index++, color + "::ckeys - teleports you to the crystal chest");
                player.getPacketSender().sendString(index++, color + "::whatdrops (item name) - tells you what drops the item");
                player.getPacketSender().sendString(index++, color + "::dropmessage - removes messages of drops going to your inv/bank");
                player.getPacketSender().sendString(index++, color + "::help - requests assistance from a staff member");
                player.getPacketSender().sendString(index++, color + "::yell - sends a global message");
                player.getPacketSender().sendString(index++, color + "");
                break;
            case 31508:
                if (player.getRights().isStaff())
                    player.getEventBossManager().updateNpcIdentification();
                break;

            case 31509:
                if (player.getRights().isStaff())
                    player.getEventBossManager().updateNpcHealth();
                break;

            case 31513:
                if (player.getRights().isStaff())
                    player.getEventBossManager().initiateSpawn();
                break;

            case -16332:
                player.getGoodieBag().claim();
                break;


            case 23638:
            case 23636:
                player.getScratchCard().scratch();
                break;


            /**
             * Combiner
             */

            case 19706:
                player.getCombiner().craftItem(0);
                break;

            case 19707:
                player.getCombiner().craftItem(1);
                break;

            case 19708:
                player.getCombiner().craftItem(2);
                break;

            case 19709:
                player.getCombiner().craftItem(3);
                break;

            case 30332:
                player.getCustomCombiner().combine();
                break;

            case -17500:
                if (player.getInventory().contains(player.getMysteryBoxOpener().getOpenBox())) { // example for mbox with random data.
                    player.getMysteryBoxOpener().open(player.getMysteryBoxOpener().getOpenBox());
                }


                break;

            case -17497: // example for mbox with random data. - open all
                /*if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
                    player.sendMessage("@red@As an Ultimate ironman you can't do this.");
                    return;
                }*/
                if (player.getInventory().contains(player.getMysteryBoxOpener().getOpenBox())) {
                    player.getMysteryBoxOpener().openAll(player.getMysteryBoxOpener().getOpenBox());
                }

                break;

            case 30902:
                GrandLottery.buy(player, 1);
                break;
            case -8384:
                player.sendMessage("Auto gambling has been disabled.");
                player.sendMessage("In order to gamble, you have to purchase the dice bag or seeds via ::Store");
                break;

            case -8383:
                if (player.getGambling().inGamble())
                    player.getGambling().declineGamble(true);
                break;
            case 30935:

                if (player.getInventory().contains(995, 50000000)) {
                    player.setEntriesCurrency(player.getEntriesCurrency() + 5.0);
                    player.getInventory().delete(995, 50000000);
                    player.getPA().sendMessage("<img=30>You have purchased a lottery ticket, go enter the lottery!");
                } else {
                    player.getPA().sendMessage("Each lottery ticket costs 50 Mill, Please have 50 Mill in your inventory.");
                }
                break;
            case 22095:
                DoubleOrNothing.handleGamble(player);
                break;
            case 22090:
            case 22098:
                DoubleOrNothing.handleKeep(player);
                break;
            case 19633:
                player.setInputHandling(new PollCreationTitle());
                player.getPacketSender().sendEnterInputPrompt("What Title would you like to apply?");
                break;
            case 19637:
                player.setInputHandling(new PollCreationOptionOne());
                player.getPacketSender().sendEnterInputPrompt("What First Option would you like to poll?");
                break;
            case 19641:
                player.setInputHandling(new PollCreationOptionTwo());
                player.getPacketSender().sendEnterInputPrompt("What Second Option would you like to poll?");
                break;
            case 19645:
                player.setInputHandling(new PollCreationTimeForPoll());
                player.getPacketSender().sendEnterInputPrompt("How long would you like the poll to stay active?P");
                break;
            case 19649:
                PollCreation.launchPoll(player);
                break;
            case 19654:
                PollCreation.resetPoll(player);
                break;
            case -30520:
                if (player.getInstanceManager().selectedInstance != null) {
                    if (player.getInstanceManager().selectedInstance.getNpcId() == 1265) {
                        player.sendMessage("Lunite lions can only be killed at ::train");
                        return;
                    }

                    if (!player.getLastRunRecovery().elapsed(5000)) {
                        player.sendMessage("Please wait 5 seconds before doing this again.");
                        return;
                    }

                    if (player.getInstanceManager().selectedInstance.getNpcId() == 1265
                            || player.getInstanceManager().selectedInstance.getNpcId() == 1023
                            || player.getInstanceManager().selectedInstance.getNpcId() == 1233
                            || player.getInstanceManager().selectedInstance.getNpcId() == 1234) {
                        if (player.getPointsHandler().getNPCKILLCount() > 5000 && KillsTracker.getTotalKillsForNpc(player.getInstanceManager().selectedInstance.getNpcId(), player) > 500) {
                            player.sendMessage("This place is for new players with less than 5k npc kills.");
                            return;
                        }
                    }
                    player.getInstanceManager().createInstance(player.getInstanceManager().selectedInstance.getNpcId(), RegionInstance.RegionInstanceType.INSTANCE);
                } else {
                    player.getPA().sendMessage("Select the boss you'd like to instance.");
                }
                break;
            case -8254:
                player.getPacketSender().sendString(1, GameSettings.StoreUrl);
                player.getPacketSender().sendMessage("Attempting to open the store");
                break;
            case -16952:
            case 16952:
                player.getPacketSender().sendString(1, GameSettings.STARTGUIDE);
                player.getPacketSender().sendMessage("Attempting to open starter guide.");
                break;
            case -16948:
                player.getPacketSender().sendString(1, GameSettings.PRICEGUIDE);
                player.getPacketSender().sendMessage("Attempting to open Price guide.");
                break;
            case -16944:
                player.getPacketSender().sendString(1, GameSettings.SLAYERGUIDE);
                player.getPacketSender().sendMessage("Attempting to open Slayer guide");
                break;
            case -16976:
                Position[] locations = new Position[]{new Position(2784, 9496, 0), new Position(2787, 9494, 0)};
                Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];

                TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
                break;
            case -16972:

                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }
                Position position = new Position(2398, 2847, 0);
                TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
                //	player.getPacketSender().sendMessage("afk grind!");

                break;
            case -16968:


                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }
                Position position1 = new Position(2399, 2914, 0);
                TeleportHandler.teleportPlayer(player, position1, TeleportType.NORMAL);
                //	player.getPacketSender().sendMessage("afk grind!");

                break;
            case -16964:


                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }
                Position psotionmad = new Position(2465, 2913, 0);
                TeleportHandler.teleportPlayer(player, psotionmad, TeleportType.NORMAL);
                //	player.getPacketSender().sendMessage("afk grind!");

                break;
            case -16960:
                player.getPacketSender().sendInterfaceReset();
                player.getPacketSender().sendInterface(28002);
                break;
            case -16956:

                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }
                Position pospengion = GameSettings.PENG;
                TeleportHandler.teleportPlayer(player, pospengion, TeleportType.NORMAL);
                // player.getPacketSender().sendMessage("Teleporting you home!");


                break;
            case -11438:
                player.getPlayerOwnedShopManager().openEditor();
                break;

            case 106005:
            case -8305:
                player.getPacketSender().sendInterfaceRemoval();
                break;


            case -13233:
                player.getPacketSender().sendInterfaceRemoval();
                break;

            case -3332:
                player.getPacketSender().sendInterfaceRemoval();
                if (player.getSkillManager().getMaxLevel(Skill.INVENTION) < 120) {
                    player.getPacketSender().sendMessage(
                            "You need a Invention Level of atleast @blu@120 Invention@bla@ to view this Tier.");
                    return;
                }

                player.getCustomCombiner().open();
                break;

            case 28177:
                player.getUpgradeListener().openTab(0);
                break;
            case -27454:
            case -27534:
            case -16534:
            case 36002:
            case 32606:
            case -31929:
            case -8255:
            case 19705:
            case 28005:
            case 26003:
            case 5384:

                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 29133:
                player.getPacketSender().sendMessage("::leave to leave room");
                //player.getPacketSender().sendMessage("@red@Please re-group!");
                player.getPacketSender().sendInterfaceRemoval();

                //TeleportHandler.teleportPlayer(player, new Position(2722, 2737), player.getSpellbook().getTeleportType());

                player.getPacketSender().sendString(29053, "").sendString(29054, "");

                for (int i = 0; i < 10; i++) {
                    player.getPacketSender().sendString(29095 + i, ""); // this should be the final thing., added UI check for every place now.

                }


                break;

            // BATTLE BRAWL TELEPORT (NPC TIER LIST)
            case 28011:// imp
                if (player.getPointsHandler().getNPCKILLCount() <= 49) {
                    player.getPacketSender().sendMessage("You need 50 npc kill Count. You currently have @red@"
                            + player.getPointsHandler().getNPCKILLCount() + "@bla@ kills.");
                    player.getPacketSender().sendMessage("@blu@To get Fast NPC KILLS go to ::Starter");

                    return;
                }

                Position positionspawns = GameSettings.DEVILSPAWN;
                TeleportHandler.teleportPlayer(player, positionspawns, TeleportType.NORMAL);

                break;

            case 28015:// lord
                if (player.getPointsHandler().getSPAWNKILLCount() <= 99) {
                    player.getPacketSender().sendMessage("You need 100 Imp kills. You currently have @red@"
                            + player.getPointsHandler().getSPAWNKILLCount() + "@bla@ kills.");

                    return;
                }

                Position positionlord = GameSettings.LORDS;
                TeleportHandler.teleportPlayer(player, positionlord, TeleportType.NORMAL);
                break;

            case 28019:// demon
                if (player.getPointsHandler().getLORDKILLCount() <= 199) {
                    player.getPacketSender().sendMessage("You need 200 Lord kills. You currently have @red@"
                            + player.getPointsHandler().getLORDKILLCount() + "@bla@ kills.");

                    return;
                }

                Position position2 = GameSettings.DEMON;
                TeleportHandler.teleportPlayer(player, position2, TeleportType.NORMAL);
                break;
            case 28023:// dragon
                if (player.getPointsHandler().getDEMONKILLCount() <= 299) {
                    player.getPacketSender().sendMessage("You need 300 Demon kills. You currently have @red@"
                            + player.getPointsHandler().getDEMONKILLCount() + "@bla@ kills.");

                    return;
                }

                Position position3 = GameSettings.DRAGON;
                TeleportHandler.teleportPlayer(player, position3, TeleportType.NORMAL);
                break;
            case 28027:// beast
                if (player.getPointsHandler().getDRAGONKILLCount() <= 399) {
                    player.getPacketSender().sendMessage("You need 400 Dragon kills. You currently have @red@"
                            + player.getPointsHandler().getDRAGONKILLCount() + "@bla@ kills.");

                    return;
                }

                Position dragon = GameSettings.BEAST;
                TeleportHandler.teleportPlayer(player, dragon, TeleportType.NORMAL);
                break;
            case 28031:// king
                if (player.getPointsHandler().getBEASTKILLCount() <= 499) {
                    player.getPacketSender().sendMessage("You need 500 Beast kills. You currently have @red@"
                            + player.getPointsHandler().getBEASTKILLCount() + "@bla@ kills.");

                    return;
                }

                Position king = GameSettings.KING;
                TeleportHandler.teleportPlayer(player, king, TeleportType.NORMAL);
                break;
            case 28035:// avatar
                if (player.getPointsHandler().getKINGKILLCount() <= 999) {
                    player.getPacketSender().sendMessage("You need 1000 King kills. You currently have @red@"
                            + player.getPointsHandler().getKINGKILLCount() + "@bla@ kills.");

                    return;
                }

                Position avatar = new Position(3301, 3289, 0);
                TeleportHandler.teleportPlayer(player, avatar, TeleportType.NORMAL);
                break;
            case 28039:// angel
                if (player.getPointsHandler().getAVATARKILLCount() <= 1199) {
                    player.getPacketSender().sendMessage("You need 1200 Avatar kills. You currently have @red@"
                            + player.getPointsHandler().getAVATARKILLCount() + "@bla@ kills.");

                    return;
                }
                Position angel = new Position(3322, 3309, 0);
                TeleportHandler.teleportPlayer(player, angel, TeleportType.NORMAL);
                //player.getPacketSender().sendMessage("@blu@coming soon");

                break;
            case 28043:// lucien
                if (player.getPointsHandler().getANGELKILLCount() <= 1499) {
                    player.getPacketSender().sendMessage("You need 1500 Angel kills. You currently have @red@"
                            + player.getPointsHandler().getANGELKILLCount() + "@bla@ kills.");

                    return;
                }

                Position lucien = new Position(2907, 5455, 0);
                TeleportHandler.teleportPlayer(player, lucien, TeleportType.NORMAL);

                break;
            case 28047:// hercules
                if (player.getPointsHandler().getLUCIENKILLCount() <= 2499) {
                    player.getPacketSender().sendMessage("You need 2500 Lucien kills. You currently have @red@"
                            + player.getPointsHandler().getLUCIENKILLCount() + "@bla@ kills.");

                    return;
                }

                Position herc = new Position(2931, 5469, 0);
                TeleportHandler.teleportPlayer(player, herc, TeleportType.NORMAL);

                break;
            case 28051:// satan
                if (player.getPointsHandler().getHERCULESKILLCount() <= 3499) {
                    player.getPacketSender().sendMessage("You need 3500 Hercules kills. You currently have @red@"
                            + player.getPointsHandler().getHERCULESKILLCount() + "@bla@ kills.");

                    return;
                }

                Position sait = new Position(2910, 5491, 0);
                TeleportHandler.teleportPlayer(player, sait, TeleportType.NORMAL);

                break;
            case 28055:// zeus
                if (player.getPointsHandler().getSATANKILLCount() <= 4999) {
                    player.getPacketSender().sendMessage("You need 5000 Satan kills. You currently have @red@"
                            + player.getPointsHandler().getSATANKILLCount() + "@bla@ kills.");

                    return;
                }

                Position zzeus = new Position(2893, 5469, 0);
                TeleportHandler.teleportPlayer(player, zzeus, TeleportType.NORMAL);

                break;
            case 28059:// groudon
                if (player.getPointsHandler().getZEUSKILLCount() <= 4999) {
                    player.getPacketSender().sendMessage("You need 15,000 Zeus kills. You currently have @red@"
                            + player.getPointsHandler().getZEUSKILLCount() + "@bla@ kills.");
                    return;
                }

                Position groudon = new Position(2996, 3116, 0);
                TeleportHandler.teleportPlayer(player, groudon, TeleportType.NORMAL);
                break;
            case 28063:// fenrir
                if (KillsTracker.getTotalKillsForNpc(8010, player) < 25000) {
                    player.getPacketSender().sendMessage("You need 25,000 Groudon kills. You currently have @red@"
                            + KillsTracker.getTotalKillsForNpc(8010, player) + "@bla@ kills.");
                    return;
                }

                Position fenrir = new Position(2603, 4774, 8);
                TeleportHandler.teleportPlayer(player, fenrir, TeleportType.NORMAL);
                break;
            case 28067:// fenrir
                if (KillsTracker.getFenrirKills(player) < 50000) {
                    player.getPacketSender().sendMessage("You need 50,000 Fenrir kills. You currently have @red@"
                            + KillsTracker.getFenrirKills(player) + "@bla@ kills.");
                    return;
                }
                TeleportHandler.teleportPlayer(player, new Position(2603, 4774, 12), TeleportType.NORMAL);
                break;


            case -3334:

                if (!player.getClickDelay().elapsed(3000)) {
                    player.getPacketSender().sendMessage("<shad=1>@red@Please wait a few seconds before trying to upgrade again.");
                    return;
                }
                new UpgradeListener(player).upgrade(false);
                player.getClickDelay().reset();
                break;

            case -3289:

                if (!player.getClickDelay().elapsed(3000)) {
                    player.getPacketSender().sendMessage("<shad=1>@red@Please wait a few seconds before trying to upgrade again.");
                    return;
                }
                new UpgradeListener(player).upgrade(true);
                player.getClickDelay().reset();
                break;
            case -3306:
            case 30905:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 1036:
                EnergyHandler.rest(player);
                break;
            case -26376:
                PlayersOnlineInterface.showInterface(player);
                break;
            case -26386:
                player.getPacketSender().sendTabInterface(GameSettings.STAFF_TAB, 46343);
                StaffList.updateInterface(player);
                break;
            case 26250:
            case 27229:
                DungeoneeringParty.create(player);
                for (Bank b : player.getBanks()) {
                    if (b.contains(15707)) {
                        player.getPacketSender().sendMessage("You already have a Ring of Kinship in your bank.");
                        return;
                    }
                }
                if (player.getInventory().contains(15707)) {
                    player.getPacketSender().sendMessage("Use your Ring of Kinship to invite players!");
                    return;
                } else {
                    player.getInventory().add(15707, 1);
                    player.getPacketSender()
                            .sendMessage("You can use your Ring of Kinship to invite others to your party!");
                }
                break;
            case 26226:
            case 26229:
                if (Dungeoneering.doingDungeoneering(player)) {
                    DialogueManager.start(player, 114);
                    player.setDialogueActionId(71);
                } else {
                    Dungeoneering.leave(player, false, true);
                }
                break;
            case 26244:
            case 26247:
                if (player.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
                    if (player.getMinigameAttributes().getDungeoneeringAttributes().getParty().getOwner().getUsername()
                            .equals(player.getUsername())) {
                        DialogueManager.start(player, id == 26247 ? 106 : 105);
                        player.setDialogueActionId(id == 26247 ? 68 : 67);
                    } else {
                        player.getPacketSender().sendMessage("Only the party owner can change this setting.");
                    }
                }
                break;
            case 14176:
                player.setUntradeableDropItem(null);
                player.getPacketSender().sendInterfaceRemoval();
                break;

            /**
             * Player panel tab buttons
             */

            case 23835:
            case 23836:
            case 23837:
            case 23838:
                PlayerPanel.refreshPanel(player);
                break;

            case 14175:
                player.getPacketSender().sendInterfaceRemoval();
                if (player.getUntradeableDropItem() != null
                        && player.getInventory().contains(player.getUntradeableDropItem().getId())) {
                    if (player.getUntradeableDropItem().getId() == 4045) {
                        player.dealDamage(new Hit((player.getConstitution() - 1) == 0 ? 1 : player.getConstitution() - 1,
                                Hitmask.CRITICAL, CombatIcon.BLUE_SHIELD));
                        player.performGraphic(new Graphic(1750));
                        player.getPacketSender().sendMessage("The potion explodes in your face as you drop it!");
                    }
                    ItemBinding.unbindItem(player, player.getUntradeableDropItem().getId());
                    player.getInventory().delete(player.getUntradeableDropItem());
                    PlayerLogs.log(player.getUsername(),
                            "Player destroying item: " + player.getUntradeableDropItem().getId() + ", amount: "
                                    + player.getUntradeableDropItem().getAmount());
                    player.getPacketSender().sendMessage("Your item vanishes as it hits the floor.");
                    Sounds.sendSound(player, Sound.DROP_ITEM);
                }
                player.setUntradeableDropItem(null);
                break;
            case 1013:
                player.getSkillManager().setTotalGainedExp(0);
                break;
            case -26373:
                if (WellOfGoodwill.isActive()) {
                    player.getPacketSender().sendMessage(
                            "<img=5> <col=008FB2>The Well of Goodwill is granting 30% bonus experience for another "
                                    + WellOfGoodwill.getMinutesRemaining() + " minutes.");
                } else {
                    player.getPacketSender()
                            .sendMessage("<img=5> <col=008FB2>The Well of Goodwill needs another "
                                    + Misc.insertCommasToNumber("" + WellOfGoodwill.getMissingAmount())
                                    + " coins before becoming full.");
                }
                break;
            case -26349:
                KillsTracker.open(player);
                break;
            case -26348:
                DropLog.open(player);
                break;
            case -10531:
                if (player.isKillsTrackerOpen()) {
                    player.setKillsTrackerOpen(false);
                    player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111000);
                    PlayerPanel.refreshPanel(player);
                }
                break;
            case -26337:
                player.getPacketSender().sendString(1, GameSettings.StoreUrl);
                player.getPacketSender().sendMessage("Attempting to open the forums");
                break;
            case -26338:
                player.getPacketSender().sendString(1, GameSettings.RuleUrl);
                player.getPacketSender().sendMessage("Attempting to open the rules");
                break;
            case -26339:
                player.getPacketSender().sendString(1, GameSettings.ForumUrl);
                player.getPacketSender().sendMessage("Attempting to open the store");
                break;
            case -26336:
                player.getPacketSender().sendString(1, GameSettings.VoteUrl);
                player.getPacketSender().sendMessage("Attempting to open the Vote page");
                break;
            case -26335:
                player.getPacketSender().sendString(1, GameSettings.HiscoreUrl);
                player.getPacketSender().sendMessage("Attempting to open the Hiscore page");
                break;
            case -26334:
                player.getPacketSender().sendString(1, GameSettings.ReportUrl);
                player.getPacketSender().sendMessage("Attempting to open the report page");
                break;
            case 350:
                player.getPacketSender()
                        .sendMessage("To autocast a spell, please right-click it and choose the autocast option.")
                        .sendTab(GameSettings.MAGIC_TAB).sendConfig(108, player.getAutocastSpell() == null ? 3 : 1);
                break;
            case 12162:
                DialogueManager.start(player, 212);
                player.setDialogueActionId(100000);
                // TeleportHandler.teleportPlayer(player, new Position(3674, 2966),
                // TeleportType.NORMAL);
                // player.getPacketSender().sendMessage("Get a task from a Slayer Master here.
                // (Slayer tower is now in Dungeon teleports)");
                break;
            case 29335:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                DialogueManager.start(player, 60);
                player.setDialogueActionId(27);
                break;
            case -13390:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 29455:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                ClanChatManager.toggleLootShare(player);
                break;
            case 29456:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                player.getPacketSender().sendInterfaceRemoval();
                PlayersOnlineInterface.showInterface(player);
                // player.getPacketSender().sendMessage("<shad=1>@or1@There are currently @whi@[ @gre@" + (17 + World.getPlayers().size()) + "@whi@ ] @or1@players online!");
                break;
            case 8658:
                DialogueManager.start(player, 55);
                player.setDialogueActionId(26);
                break;
            case 11001:
                TeleportHandler.teleportPlayer(player, GameSettings.DEFAULT_POSITION.copy(),
                        player.getSpellbook().getTeleportType());
                player.getPacketSender().sendInterfaceRemoval();
                //player.setDialogueActionId(217);
                //DialogueManager.start(player, 217);

                break;
            case 28211:
                TeleportHandler.teleportPlayer(player, GameSettings.DEFAULT_POSITION.copy(),
                        player.getSpellbook().getTeleportType());
                break;
            case 8667:
                TeleportHandler.teleportPlayer(player, new Position(2742, 3443), player.getSpellbook().getTeleportType());
                break;
            case 8672:
                TeleportHandler.teleportPlayer(player, new Position(2135, 5533, 3),
                        player.getSpellbook().getTeleportType());
                // player.getPacketSender().sendMessage("<img=5> To get started with
                // Runecrafting, buy a talisman and use the locate option on it.");
                break;
            case 8861:
                TeleportHandler.teleportPlayer(player, new Position(2914, 3450), player.getSpellbook().getTeleportType());
                break;
            case 8656:
                player.setDialogueActionId(47);
                DialogueManager.start(player, 86);
                break;
            case 8659:
                TeleportHandler.teleportPlayer(player, new Position(3079, 9499), player.getSpellbook().getTeleportType());
                break;
            case 8664:
                TeleportHandler.teleportPlayer(player, new Position(3079, 3485), player.getSpellbook().getTeleportType());
                break;
            case 8666:
                TeleportHandler.teleportPlayer(player, new Position(2647, 4010), player.getSpellbook().getTeleportType());
                break;
            case 8671:
                player.setDialogueActionId(56);
                DialogueManager.start(player, 89);
                break;
            case 8670:
                TeleportHandler.teleportPlayer(player, new Position(2561, 3866), player.getSpellbook().getTeleportType());
                break;
            case 8668:
                TeleportHandler.teleportPlayer(player, new Position(2538, 3890), player.getSpellbook().getTeleportType());
                break;
            case 8665:
                TeleportHandler.teleportPlayer(player, new Position(3143, 3446), player.getSpellbook().getTeleportType());
                // player.getPacketSender().sendMessage("Welcome to the new Cooking zone!");
                break;
            case 8662:
                TeleportHandler.teleportPlayer(player, new Position(2594, 3404), player.getSpellbook().getTeleportType());
                break;
            case 13928:
                TeleportHandler.teleportPlayer(player, new Position(3052, 3304), player.getSpellbook().getTeleportType());
                break;
            case 28179:
                TeleportHandler.teleportPlayer(player, new Position(2209, 5348), player.getSpellbook().getTeleportType());
                break;
            case 28178:
                DialogueManager.start(player, 54);
                player.setDialogueActionId(25);
                break;
            case 1159: // Bones to Bananas
            case 15877:// Bones to peaches
            case 30306:
                MagicSpells.handleMagicSpells(player, id);
                break;
            case 10001:
                if (player.getInterfaceId() == -1) {
                    Consumables.handleHealAction(player);
                } else {
                    player.getPacketSender().sendMessage("You cannot heal yourself right now.");
                }
                break;
            case 18025:
                if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                    PrayerHandler.deactivatePrayer(player, PrayerHandler.AUGURY);
                } else {
                    PrayerHandler.activatePrayer(player, PrayerHandler.AUGURY);
                }
                break;
            case 18018:
                if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                    PrayerHandler.deactivatePrayer(player, PrayerHandler.RIGOUR);
                } else {
                    PrayerHandler.activatePrayer(player, PrayerHandler.RIGOUR);
                }
                break;
            case 10000:
            case 950:
                if (player.getInterfaceId() < 0)
                    player.getPacketSender().sendInterface(40030);
                else
                    player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
                break;
            case 3546:
            case 3420:
                if (System.currentTimeMillis() - player.getTrading().lastAction <= 300)
                    return;
                player.getTrading().lastAction = System.currentTimeMillis();
                if (player.getTrading().inTrade()) {
                    player.getTrading().acceptTrade(id == 3546 ? 2 : 1);
                } else {
                    player.getPacketSender().sendInterfaceRemoval();
                }
                break;
            case 10162:
            case -18269:
                player.getPacketSender().sendInterfaceRemoval();
                break;
            case 841:
                ingredientsBook.readBook(player, player.getCurrentBookPage() + 2, true);
                break;
            case 839:
                ingredientsBook.readBook(player, player.getCurrentBookPage() - 2, true);
                break;
            case 14922:
                player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
                break;
            case 14921:
                player.getPacketSender().sendMessage("Please visit the forums and ask for help in the support section.");
                break;
            case 5294:
                player.getPacketSender().sendClientRightClickRemoval().sendInterfaceRemoval();
                player.setDialogueActionId(player.getBankPinAttributes().hasBankPin() ? 8 : 7);
                DialogueManager.start(player,
                        DialogueManager.getDialogues().get(player.getBankPinAttributes().hasBankPin() ? 12 : 9));
                break;
            case 15002:

                if (!player.busy() && !player.getCombatBuilder().isBeingAttacked()
                        && !Dungeoneering.doingDungeoneering(player)) {
                    player.getSkillManager().stopSkilling();
                    player.getPriceChecker().open();
                } else {
                    player.getPacketSender().sendMessage("You cannot open this right now.");
                }
                break;
            case 2735:
            case 1511:
                if (player.getSummoning().getBeastOfBurden() != null) {
                    player.getSummoning().toInventory();
                    player.getPacketSender().sendInterfaceRemoval();
                } else {
                    player.getPacketSender().sendMessage("You do not have a familiar who can hold items.");
                }
                break;
            case -11501:

            case -11498:
            case -11507:
            case 1020:
            case 1021:
            case 1019:
            case 1018:
                if (id == 1020 || id == -11504)
                    SummoningTab.renewFamiliar(player);
                else if (id == 1019 || id == -11501)
                    SummoningTab.callFollower(player);
                else if (id == 1021 || id == -11498)
                    SummoningTab.handleDismiss(player, false);
                else if (id == -11507) // TODO swagyolo
                    player.getSummoning().store();
                else if (id == 1018)
                    player.getSummoning().toInventory();
                break;
            case 1042:
                player.getPacketSender().sendInterface(57350);
                break;

            case 1716:
                player.getCustomTeleportInterface().open();
                break;
            case 1037:
                SummoningTab.callFollower(player);
                break;
            case 1038:
                SummoningTab.renewFamiliar(player);
                break;
            case 1039:
                SummoningTab.handleDismiss(player, false);
                break;
            case 1040:
                player.getSummoning().toInventory();
                break;
            case 1041:
                player.getSummoning().store();
                break;
            case 1095:
                player.setExperienceLocked(!player.experienceLocked());
                if (player.experienceLocked()) {
                    player.getPacketSender().sendMessage("Your EXP is now locked, revert this lock to get EXP again.");
                } else {
                    player.getPacketSender().sendMessage("Your EXP is unlocked, and you will gain EXP as normal.");
                }
                break;
            case 11004:
			/*player.getPacketSender().sendTab(GameSettings.SKILLS_TAB);
			DialogueManager.sendStatement(player, "Simply press on the skill you want to train in the skills tab.");
			player.setDialogueActionId(-1);*/
                player.getCustomTeleportInterface().open();
                break;
            case 8654:
            case 8657:
            case 8655:
            case 8663:
            case 8669:
            case 8660:
                player.getCustomTeleportInterface().open();
                break;
            /*
             * player.setDialogueActionId(0); DialogueManager.start(player, 0);
             */

            /*
             * case 11008: player.setDialogueActionId(0); DialogueManager.start(player, 0);
             * break; case 11017: DialogueManager.start(player, 34);
             * player.setDialogueActionId(15); break; case 11011:
             * DialogueManager.start(player, 22); player.setDialogueActionId(14); break;
             * case 11020: DialogueManager.start(player, 21);
             * player.setDialogueActionId(12); break; case 11014:
             * player.setDialogueActionId(36); DialogueManager.start(player, 136); break;
             */
            case 2799:
            case 2798:
            case 1747:
            case 1748:
            case 8890:
            case 8886:
            case 8875:
            case 8871:
            case 8894:
                ChatboxInterfaceSkillAction.handleChatboxInterfaceButtons(player, id);
                break;
            case 14873:
            case 14874:
            case 14875:
            case 14876:
            case 14877:
            case 14878:
            case 14879:
            case 14880:
            case 14881:
            case 14882:
                BankPin.clickedButton(player, id);
                break;
            case -12307:
                player.getInventory().delete(757, 1);
                player.getPacketSender().sendInterfaceRemoval();
                // player.sendMessage("Enjoy your reward");
                break;
            case -12286:
            case 28133:
            case 27133:
            case 26133:
            case -16938:
            case -17492:
            case 31502:
            case 142402:
                player.getPacketSender().sendInterfaceRemoval();
                //CLOSE INTERFACE
                break;

            case 27005:
            case 22012:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                Bank.depositItems(player, id == 27005 ? player.getEquipment() : player.getInventory(), false);
                break;
            case 27023:
                if (!player.isBanking() || player.getInterfaceId() != 5292)
                    return;
                if (player.getSummoning().getBeastOfBurden() == null) {
                    player.getPacketSender().sendMessage("You do not have a familiar which can hold items.");
                    return;
                }
                Bank.depositItems(player, player.getSummoning().getBeastOfBurden(), false);
                break;
            case 22008:
                if (!player.isBanking() || (player.getInterfaceId() != 106000 && player.getInterfaceId() != 5292))
                    return;
                player.setNoteWithdrawal(!player.withdrawAsNote());
                player.getPacketSender().sendConfig(115, player.withdrawAsNote() ? 0 : 1);
                break;
            case 21000:
                if (!player.isBanking() || (player.getInterfaceId() != 106000 && player.getInterfaceId() != 5292))
                    return;
                player.setSwapMode(!player.swapMode());
                player.getPacketSender().sendConfig(304, player.swapMode() ? 1 : 0);
                break;
            case 27009:
                player.sendMessage("This option has been disabled");
                break;
            case 27014:
            case 27015:
            case 27016:
            case 27017:
            case 27018:
            case 27019:
            case 27020:
            case 27021:
            case 27022:
                if (!player.isBanking())
                    return;
                if (player.getBankSearchingAttribtues().isSearchingBank())
                    BankSearchAttributes.stopSearch(player, true);
                int bankId = id - 27014;
                boolean empty = bankId > 0 ? Bank.isEmpty(player.getBank(bankId)) : false;
                if (!empty || bankId == 0) {
                    player.setCurrentBankTab(bankId);
                    player.getPacketSender().sendString(5385, "scrollreset");
                    player.getPacketSender().sendString(27002, Integer.toString(player.getCurrentBankTab()));
                    player.getPacketSender().sendString(27000, "1");
                    player.getBank(bankId).open();
                } else
                    player.getPacketSender().sendMessage("To create a new tab, please drag an item here.");
                break;
            case 22004:
                if (!player.isBanking())
                    return;
                if (!player.getBankSearchingAttribtues().isSearchingBank()) {
                    player.getBankSearchingAttribtues().setSearchingBank(true);
                    player.setInputHandling(new EnterSyntaxToBankSearchFor());
                    player.getPacketSender().sendEnterInputPrompt("What would you like to search for?");
                } else {
                    BankSearchAttributes.stopSearch(player, true);
                }
                break;
            case 22845:
            case 24115:
            case 24010:
            case 24041:
            case 150:
                player.setAutoRetaliate(!player.isAutoRetaliate());
                break;
            case 29332:
                ClanChat clan = player.getCurrentClanChat();
                if (clan == null) {
                    player.getPacketSender().sendMessage("You are not in a clanchat channel.");
                    return;
                }
                ClanChatManager.leave(player, false, true);
                player.setClanChatName(null);
                break;
            case 29329:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                player.setInputHandling(new EnterClanChatToJoin());
                player.getPacketSender().sendEnterInputPrompt("Enter the name of the clanchat channel you wish to join:");
                break;
            case 19158:
            case 152:
                if (player.getRunEnergy() <= 1) {
                    player.getPacketSender().sendMessage("You do not have enough energy to do this.");
                    player.setRunning(false);
                } else
                    player.setRunning(!player.isRunning());
                player.getPacketSender().sendRunStatus();
                break;
            case 15004:
                player.setExperienceLocked(!player.experienceLocked());
                String type = player.experienceLocked() ? "locked" : "unlocked";
                player.getPacketSender().sendMessage("Your experience is now " + type + ".");
                PlayerPanel.refreshPanel(player);
                break;
            case 27651:
            case 15001:
                if (player.getInterfaceId() == -1) {
                    player.getSkillManager().stopSkilling();
                    BonusManager.update(player);
                    player.getPacketSender().sendInterface(21172);
                } else
                    player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
                break;
            case 15003:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender()
                            .sendMessage("Please close the interface you have open before opening another one.");
                    return;
                }
                player.getSkillManager().stopSkilling();
                ItemsKeptOnDeath.sendInterface(player);
                break;
            case 2458: // Logout
                if (player.logout()) {
                    World.removePlayer(player);
                }
                break;
            // case 10003:
            case 29138:
            case 29038:
            case 29063:
            case 29113:
            case 29163:
            case 29188:
            case 29213:
            case 29238:
            case 30007:
            case 48023:
            case 33033:
            case 30108:
            case 7473:
            case 7562:
            case 7487:
            case 7788:
            case 8481:
            case 7612:
            case 7587:
            case 7662:
            case 7462:
            case 7548:
            case 7687:
            case 7537:
            case 12322:
            case 7637:
            case 12311:
                CombatSpecial.activate(player);
                break;
            case 1772: // shortbow & longbow & blowpipe & crossbow
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.BLOWPIPE) {
                    player.setFightType(FightType.BLOWPIPE_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.BSOAT) {
                    player.setFightType(FightType.BSOAT_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.ARMADYLXBOW) {
                    player.setFightType(FightType.ARMADYLXBOW_ACCURATE);
                }
                break;
            case -11504:
                SummoningTab.renewFamiliar(player);
            case 1771:
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_RAPID);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_RAPID);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_RAPID);
                } else if (player.getWeapon() == WeaponInterface.BLOWPIPE) {
                    player.setFightType(FightType.BLOWPIPE_RAPID);
                } else if (player.getWeapon() == WeaponInterface.BSOAT) {
                    player.setFightType(FightType.BSOAT_RAPID);
                } else if (player.getWeapon() == WeaponInterface.ARMADYLXBOW) {
                    player.setFightType(FightType.ARMADYLXBOW_RAPID);
                }
                break;
            case 1770:
                if (player.getWeapon() == WeaponInterface.SHORTBOW) {
                    player.setFightType(FightType.SHORTBOW_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.LONGBOW) {
                    player.setFightType(FightType.LONGBOW_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.CROSSBOW) {
                    player.setFightType(FightType.CROSSBOW_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.BLOWPIPE) {
                    player.setFightType(FightType.BLOWPIPE_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.BSOAT) {
                    player.setFightType(FightType.BSOAT_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.ARMADYLXBOW) {
                    player.setFightType(FightType.ARMADYLXBOW_LONGRANGE);
                }
                break;
            case 2282: // dagger & sword
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_STAB);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_STAB);
                }
                break;
            case 2285:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_LUNGE);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_LUNGE);
                }
                break;
            case 2284:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_SLASH);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_SLASH);
                }
                break;
            case 2283:
                if (player.getWeapon() == WeaponInterface.DAGGER) {
                    player.setFightType(FightType.DAGGER_BLOCK);
                } else if (player.getWeapon() == WeaponInterface.SWORD) {
                    player.setFightType(FightType.SWORD_BLOCK);
                }
                break;
            case 2429: // scimitar & longsword
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_CHOP);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_CHOP);
                }
                break;
            case 2432:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_SLASH);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_SLASH);
                }
                break;
            case 2431:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_LUNGE);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_LUNGE);
                }
                break;
            case 2430:
                if (player.getWeapon() == WeaponInterface.SCIMITAR) {
                    player.setFightType(FightType.SCIMITAR_BLOCK);
                } else if (player.getWeapon() == WeaponInterface.LONGSWORD) {
                    player.setFightType(FightType.LONGSWORD_BLOCK);
                }
                break;
            case 3802: // mace
                player.setFightType(FightType.MACE_POUND);
                break;
            case 3805:
                player.setFightType(FightType.MACE_PUMMEL);
                break;
            case 3804:
                player.setFightType(FightType.MACE_SPIKE);
                break;
            case 3803:
                player.setFightType(FightType.MACE_BLOCK);
                break;
            case 4454: // knife, thrownaxe, dart & javelin
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_ACCURATE);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_ACCURATE);
                }
                break;
            case 4453:
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_RAPID);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_RAPID);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_RAPID);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_RAPID);
                }
                break;
            case 4452:
                if (player.getWeapon() == WeaponInterface.KNIFE) {
                    player.setFightType(FightType.KNIFE_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.THROWNAXE) {
                    player.setFightType(FightType.THROWNAXE_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.DART) {
                    player.setFightType(FightType.DART_LONGRANGE);
                } else if (player.getWeapon() == WeaponInterface.JAVELIN) {
                    player.setFightType(FightType.JAVELIN_LONGRANGE);
                }
                break;
            case 4685: // spear
                player.setFightType(FightType.SPEAR_LUNGE);
                break;
            case 4688:
                player.setFightType(FightType.SPEAR_SWIPE);
                break;
            case 4687:
                player.setFightType(FightType.SPEAR_POUND);
                break;
            case 4686:
                player.setFightType(FightType.SPEAR_BLOCK);
                break;
            case 4711: // 2h sword
                player.setFightType(FightType.TWOHANDEDSWORD_CHOP);
                break;
            case 4714:
                player.setFightType(FightType.TWOHANDEDSWORD_SLASH);
                break;
            case 4713:
                player.setFightType(FightType.TWOHANDEDSWORD_SMASH);
                break;
            case 4712:
                player.setFightType(FightType.TWOHANDEDSWORD_BLOCK);
                break;
            case 5576: // pickaxe
                player.setFightType(FightType.PICKAXE_SPIKE);
                break;
            case 5579:
                player.setFightType(FightType.PICKAXE_IMPALE);
                break;
            case 5578:
                player.setFightType(FightType.PICKAXE_SMASH);
                break;
            case 5577:
                player.setFightType(FightType.PICKAXE_BLOCK);
                break;
            case 7768: // claws
                player.setFightType(FightType.CLAWS_CHOP);
                break;
            case 7771:
                player.setFightType(FightType.CLAWS_SLASH);
                break;
            case 7770:
                player.setFightType(FightType.CLAWS_LUNGE);
                break;
            case 7769:
                player.setFightType(FightType.CLAWS_BLOCK);
                break;
            case 8466: // halberd
                player.setFightType(FightType.HALBERD_JAB);
                break;
            case 8468:
                player.setFightType(FightType.HALBERD_SWIPE);
                break;
            case 8467:
                player.setFightType(FightType.HALBERD_FEND);
                break;
            case 5862: // unarmed
                player.setFightType(FightType.UNARMED_PUNCH);
                break;
            case 5861:
                player.setFightType(FightType.UNARMED_KICK);
                break;
            case 5860:
                player.setFightType(FightType.UNARMED_BLOCK);
                break;
            case 12298: // whip
                player.setFightType(FightType.WHIP_FLICK);
                break;
            case 12297:
                player.setFightType(FightType.WHIP_LASH);
                break;
            case 12296:
                player.setFightType(FightType.WHIP_DEFLECT);
                break;
            case 336: // staff
                player.setFightType(FightType.STAFF_BASH);
                break;
            case 335:
                player.setFightType(FightType.STAFF_POUND);
                break;
            case 334:
                player.setFightType(FightType.STAFF_FOCUS);
                break;
            case 433: // warhammer
                player.setFightType(FightType.WARHAMMER_POUND);
                break;
            case 432:
                player.setFightType(FightType.WARHAMMER_PUMMEL);
                break;
            case 431:
                player.setFightType(FightType.WARHAMMER_BLOCK);
                break;
            case 782: // scythe
                player.setFightType(FightType.SCYTHE_REAP);
                break;
            case 784:
                player.setFightType(FightType.SCYTHE_CHOP);
                break;
            case 785:
                player.setFightType(FightType.SCYTHE_JAB);
                break;
            case 783:
                player.setFightType(FightType.SCYTHE_BLOCK);
                break;
            case 1704: // battle axe
                player.setFightType(FightType.BATTLEAXE_CHOP);
                break;
            case 1707:
                player.setFightType(FightType.BATTLEAXE_HACK);
                break;
            case 1706:
                player.setFightType(FightType.BATTLEAXE_SMASH);
                break;
            case 1705:
                player.setFightType(FightType.BATTLEAXE_BLOCK);
                break;
        }
    }

    private boolean checkHandlers(Player player, int id) {
        if (player.getDailyTaskManager().handleButton(id))
            return true;
        if (player.getSlayerFavourites().handleButton(id))
            return true;
        if (ServerPerks.getInstance().handleButton(player, id)) {
            return true;
        }
        if (player.isDrInterface()) {
            if (BestDRInterface.buttonClicked(player, id)) {
                return true;
            }
        } else {
            if (BestItemsInterface.buttonClicked(player, id)) {
                return true;
            }
        }
        if (KillTrackerInterface.handleButton(player, id))
            return true;
        if (Construction.handleButtonClick(id, player)) {
            return true;
        }
        if (player.getCustomTeleportInterface().handleButton(id)) {
            return true;
        }
        switch (id) {
            case 14650:
                player.getPacketSender().removeInterface();
                return true;
            case 2494:
            case 2495:
            case 2496:
            case 2497:
            case 2498:
            case 2471:
            case 2472:
            case 2473:
            case 2461:
            case 2462:
            case 2482:
            case 2483:
            case 2484:
            case 2485:
                DialogueOptions.handle(player, id);
                return true;


        }

        if (GroupManager.handleButton(player, id)) {
            return true;
        }

        if ((player.isPlayerLocked() || player.isGroupIronmanLocked()) && id != 2458 && id != -12780 && id != -12779 && id != -12778 && id != -12763 && id != -12760 && id != -12767) {
            return true;
        }

        if (StartScreen.handleButton(player, id)) {
            return true;
        }

        if (DropsInterface.handleButton(id)) {
            DropsInterface.handleButtonClick(player, id);
            return true;
        }
        if (player.isPlayerLocked() && id != 2458) {
            return true;
        }
        if (AchievementsOLD.handleButton(player, id)) {
            return true;
        }
        if (Sounds.handleButton(player, id)) {
            return true;
        }
        if (PrayerHandler.isButton(id)) {
            PrayerHandler.togglePrayerWithActionButton(player, id);
            return true;
        }
        if (CurseHandler.isButton(player, id)) {
            return true;
        }
        if (Autocasting.handleAutocast(player, id)) {
            return true;
        }
        if (SmithingData.handleButtons(player, id)) {
            return true;
        }
        if (PouchMaking.pouchInterface(player, id)) {
            return true;
        }
        if (LoyaltyProgramme.handleButton(player, id)) {
            return true;
        }
        if (Fletching.fletchingButton(player, id)) {
            return true;
        }
        if (LeatherMaking.handleButton(player, id) || Tanning.handleButton(player, id)) {
            return true;
        }
        if (Emotes.doEmote(player, id)) {
            return true;
        }
        if (PestControl.handleInterface(player, id)) {
            return true;
        }
        if (player.getLocation() == Location.DUEL_ARENA && Dueling.handleDuelingButtons(player, id)) {
            return true;
        }
        if (Slayer.handleRewardsInterface(player, id)) {
            return true;
        }
        if (ExperienceLamps.handleButton(player, id)) {
            return true;
        }
        if (PlayersOnlineInterface.handleButton(player, id)) {
            return true;
        }
        if (GrandExchange.handleButton(player, id)) {
            return true;
        }
        if (ClanChatManager.handleClanChatSetupButton(player, id)) {
            return true;
        }
        if (Guild.handleClanButtons(player, id)) {
            return true;
        }
        if (new BossEventInterfaceHandler(player).button(id)) {
            return true;
        }


        if (player.getProgressionManager().handleButton(id)) {
            return true;
        }

        if (LoyaltyStreakManager.handleButtonClick(player, id)) {
            return true;
        }
        if (PollManager.handlePollClick(player, id)) {
            return true;
        }
        return false;
    }
}
