package com.ruse.net.packet.impl;

import com.ruse.GameSettings;
import com.ruse.engine.task.impl.WalkToTask;
import com.ruse.engine.task.impl.WalkToTask.FinalizedMovementTask;
import com.ruse.model.GameMode;
import com.ruse.model.Graphic;
import com.ruse.model.Locations.Location;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.magic.CombatSpells;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.EnterLotteryTicketAmount;
import com.ruse.world.content.discordbot.DiscordIntegration;
import com.ruse.world.content.grandLottery.GrandLottery;
import com.ruse.world.content.grandexchange.GrandExchange;
import com.ruse.world.content.groupironman.GroupConfig;
import com.ruse.world.content.groupironman.GroupManager;
import com.ruse.world.content.holidayevents.christmas2016;
import com.ruse.world.content.minigames.impl.WarriorsGuild;
import com.ruse.world.content.minigames.impl.trioMinigame;
import com.ruse.world.content.pos.PlayerOwnedShopManager;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.impl.construction.ConstructionActions;
import com.ruse.world.content.skill.impl.crafting.Tanning;
import com.ruse.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruse.world.content.skill.impl.dungeoneering.UltimateIronmanHandler;
import com.ruse.world.content.skill.impl.fishing.Fishing;
import com.ruse.world.content.skill.impl.herblore.Decanting;
import com.ruse.world.content.skill.impl.hunter.PuroPuro;
import com.ruse.world.content.skill.impl.runecrafting.DesoSpan;
import com.ruse.world.content.skill.impl.slayer.BossSlayerDialogues;
import com.ruse.world.content.skill.impl.slayer.SlayerDialogues;
import com.ruse.world.content.skill.impl.slayer.TaskType;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.content.skill.impl.summoning.BossPets;
import com.ruse.world.content.skill.impl.summoning.Summoning;
import com.ruse.world.content.skill.impl.summoning.SummoningData;
import com.ruse.world.content.skill.impl.thieving.Pickpocket;
import com.ruse.world.content.skill.impl.thieving.PickpocketData;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.TurkeySpawns;

import static com.ruse.world.content.combat.CombatType.RANGED;

public class NPCOptionPacketListener implements PacketListener {

    private static void firstClick(Player player, Packet packet) {
        int index = packet.readLEShort();
        if (index < 0 || index > World.getNpcs().capacity())
            return;
        final NPC npc = World.getNpcs().get(index);
        if (npc == null)
            return;
        player.setEntityInteraction(npc);
        if (player.getRights() == PlayerRights.DEVELOPER)
            player.getPacketSender().sendMessage("First click npc id: " + npc.getId());
        if (BossPets.pickup(player, npc)) {
            player.getMovementQueue().reset();
            return;
        }
        player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
            @Override
            public void execute() {
                if (SummoningData.beastOfBurden(npc.getId())) {
                    Summoning summoning = player.getSummoning();
                    if (summoning.getBeastOfBurden() != null && summoning.getFamiliar() != null
                            && summoning.getFamiliar().getSummonNpc() != null
                            && summoning.getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
                        summoning.store();
                        player.getMovementQueue().reset();
                    } else {
                        player.getPacketSender().sendMessage("That familiar is not yours!");
                    }
                    return;
                }
                if (ConstructionActions.handleFirstClickNpc(player, npc)) {
                    return;
                }
                switch (npc.getId()) {
                    case 8168:
                        if (player.getCurseOfArrav().getQuestProgress() == 4){
                            player.sendMessage("@red@You already finished this quest.");
                        }else{
                        if ((player.getCurseOfArrav().getQuestProgress() == 3 && player.getInventory().contains(15113))) {
                            DialogueManager.start(player, 20015);
                            player.setDialogueActionId(20015);
                            player.getCurseOfArrav().setQuestProgress(4);

                            player.getInventory().delete(15113, 1);
                            player.getInventory().add(23809, 1);
                            player.getInventory().add(23800, 1);
                            player.getInventory().add(23801, 1);
                        } else if ((player.getCurseOfArrav().getQuestProgress() == 1 && player.getInventory().contains(15111))
                                || player.getCurseOfArrav().getQuestProgress() == 2) {
                            DialogueManager.start(player, 20013);
                            player.setDialogueActionId(20013);
                            player.getCurseOfArrav().setQuestProgress(2);
                        } else if (player.getCurseOfArrav().getQuestProgress() == 1 && !player.getInventory().contains(15111)) {
                            DialogueManager.start(player, 20003);
                            player.setDialogueActionId(20003);
                        } else {
                            DialogueManager.start(player, 20000);
                            player.setDialogueActionId(20000);
                        }}
                        break;
                    case 7447:
                        if (player.getCurseOfArrav().getQuestProgress() == 1 && !player.getInventory().contains(15112)) {
                            DialogueManager.start(player, 20009);
                            player.setDialogueActionId(20009);
                        }else if (player.getCurseOfArrav().getQuestProgress() == 0) {
                            player.sendMessage("@red@You must speak to Arrav first.");
                        }else{
                            player.sendMessage("@red@You already got an empty Canopic jar.");
                        }
                        break;
                    case 638:
                        if (player.getCurseOfArrav().getQuestProgress() == 1 && !player.getInventory().contains(3430)) {
                            DialogueManager.start(player, 20011);
                            player.setDialogueActionId(20011);
                        }else if (player.getCurseOfArrav().getQuestProgress() == 0) {
                            player.sendMessage("@red@You must speak to Arrav first.");
                        }else{
                            player.sendMessage("@red@You already got Sacred oil.");
                        }
                        break;
                    case 10017:
                        TravellingMerchant.openShop(player);
                        break;
                    case 10016:
                        DialogueManager.start(player, 8023);
                        player.setDialogueActionId(8023);
                        break;
                    case 10010:
                        player.getAssassinsGuild().openMainInterface();
                        break;
                    case 10006:
                        player.getPA().sendInterface(19130);
                        DiscordIntegration.updateDiscordInterface(player);
                        break;
                    case 5604:
                        ShopManager.getShops().get(102).open(player);
                        player.sendMessage(
                                "<img=99>You have @red@" + player.getPointsHandler().getBossPoints() + " Boss Points!");

                        break;
                    case 8504:
                    case 8505:
                    case 8506:
                    case 8508:
                    case 8510:
                        TurkeySpawns.findTurkey(npc, player);
                        break;
                    case 3709:
                        ShopManager.getShops().get(155).open(player);
                        break;
                    case 648:
                        ShopManager.getShops().get(156).open(player);
                        break;
                    case 9022:
                        ServerPerks.getInstance().open(player);
                        break;
                    case 10005:
                        player.moveTo(new Position(3025, 5231));
                        player.getPacketSender().sendMessage("Teleporting to Cryptos Minigame...");
                        break;
                    case GroupConfig.NPC_ID:
                        if (player.getGameMode() == GameMode.GROUP_IRONMAN) {
                            if (GroupManager.isInGroup(player)) {
                                GroupManager.openInterface(player);
                            } else {
                                DialogueManager.start(player, 8001);
                                player.setDialogueActionId(8001);
                            }
                        } else {
                            player.sendMessage("You must be a group ironman to do this.");
                        }
                        break;
                    case 9000:
                        if (!player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)
                                && player.getSlayer().getSlayerTask().equals(SlayerTasks.NO_TASK)) {
                            TaskType.changeSlayerMaster(player, TaskType.BOSS_SLAYER);
                        }
                        if (!player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER))
                            DialogueManager.sendStatement(player, "You do not have a boss slayer task.");
                        else
                            DialogueManager.start(player, BossSlayerDialogues.dialogue(player));

                        break;
                    case PlayerOwnedShopManager.NPC_ID:
                        player.getPlayerOwnedShopManager().options();
                        break;
                    case 4652:
                        ShopManager.getShops().get(106).open(player);
                        npc.forceChat("Billbag Exchange");
                        break;
                    case 4601:
                        player.setDialogueActionId(8);
                        DialogueManager.start(player, 13);
                        break;
                    case 5382:
                        if (player.getGameMode().equals(GameMode.ULTIMATE_IRONMAN)) {
                            DialogueManager.start(player, 192);
                        } else {
                            DialogueManager.start(player, 195);
                        }
                        break;

                    case 788:
                        player.setEntityInteraction(npc);
                        player.setDialogueActionId(831);
                        EnterLotteryTicketAmount.lotteryNPC = npc;
                        DialogueManager.start(player, 218);
                        break;

                   /* case 1988:
                        player.getProgressionManager().open();
                        break;*/
                    case 662:
                        GrandLottery.open(player);
                        break;

                    case 1394:
                        ShopManager.getShops().get(119).open(player);
                        break;

                    case 10018:
                        ShopManager.getShops().get(1200).open(player);
                        break;


                    case 1050:
                        player.moveTo(new Position(2793, 3276));
                        npc.forceChat("Deep sea fishing!");
                        break;
                    case 3306:
                    case 130:
                        player.getCombiner().openInterface();
                        player.getPacketSender().sendMessage("Collect the required items to craft your items.");
                        break;
                    case 198:
                        npc.forceChat("talk to me for the npc tier list!");
                        player.getPacketSender().sendInterfaceReset();
                        player.getPacketSender().sendInterface(28002);
                        break;
                    case 783:
                        npc.forceChat("talk to me for starter tasks!");
                        int[] ids = {22074, 6570, 7462, 17273, 19153, 19142, 19141, 19115, 11137, 20000, 6769};
                        for (int i = 0; i < ids.length; i++) {
                            player.getPacketSender().sendItemOnInterface(53205, ids[i], i, 1);
                        }
                        player.getPacketSender().sendInterfaceReset();
                        player.getPacketSender().sendInterface(53200);
                        break;
                    case 3089:
                        ShopManager.getShops().get(104).open(player);
                        npc.forceChat("PvM ticket shop!");
                        break;
                    case 659:
                        if (GameSettings.newYear2017) {
                            if (player.getNewYear2017() == 0) {
                                DialogueManager.start(player, 189);
                                player.setDialogueActionId(189);
                            } else {
                                DialogueManager.start(player, 190);
                            }
                        } else {
                            npc.forceChat("I love a good party!");
                            ShopManager.getShops().get(81).open(player);
                        }
                        break;
                    case 13651:
                        ShopManager.getShops().get(150).open(player);
                        break;
                    case 1835:
                        ShopManager.getShops().get(151).open(player);
                        break;
                    case 436:
                        ShopManager.getShops().get(103).open(player);
                        npc.forceChat("Get back to afking you lazy cat");
                        break;
                    case 4653:
                        DialogueManager.start(player, 178);
                        player.setDialogueActionId(178);
                        break;
                    case 1872:
                        if (player.getLocation() == Location.ZULRAH_WAITING) {
                            DialogueManager.start(player, 200);
                        }
                        break;
                    case 1552:
                        if (christmas2016.isChristmas()) {
                            if (player.getChristmas2016() == 0) {
                                DialogueManager.start(player, 169);
                                player.setDialogueActionId(171);
                            } else if (player.getChristmas2016() == 1) {
                                player.getPacketSender().sendMessage("Santa wants me to talk to Explorer Jack at home.");
                            } else if (player.getChristmas2016() == 2) {
                                DialogueManager.start(player, 181);
                            } else if (player.getChristmas2016() > 2 && player.getChristmas2016() < 5) {
                                DialogueManager.start(player, 182);
                                player.getPacketSender().sendMessage("The Reindeer need Law, Cosmic, and Nature runes.");
                            } else if (player.getChristmas2016() == 5) {
                                DialogueManager.start(player, 183);
                                player.getPacketSender().sendMessage("I should \"use\" the Mind Bomb on Santa.");
                            } else if (player.getChristmas2016() == 6) {
                                DialogueManager.start(player, 184);
                                player.setDialogueActionId(187);
                            } else if (player.getChristmas2016() == 7) {
                                DialogueManager.start(player, 188);
                            } else {
                                npc.forceChat("Ho ho ho!");
                            }
                        } else {
                            npc.forceChat("Ho ho ho!");
                        }
                        break;
                    case 1084:
                        ShopManager.getShops().get(111).open(player);
                        break;
                    case 1085:
                        ShopManager.getShops().get(112).open(player);
                        break;
                    case 1086:
                        ShopManager.getShops().get(113).open(player);
                        break;
                    case 736:
                        player.forceChat("Ban emily!");
                        npc.forceChat("Mods! Help! They're harassing me again!");
                        break;
                    case 3777:
                        DialogueManager.start(player, 141);
                        player.setDialogueActionId(88);
                        break;
                    case 5:
                    case 4:
                        npc.setPositionToFace(player.getPosition());
                        DialogueManager.start(player, 167);
                        break;
                    case 1:
                    case 2:
                    case 3:
                        npc.setPositionToFace(player.getPosition());
                        DialogueManager.start(player, 165);
                        break;
                    case 2238:
                        npc.setPositionToFace(player.getPosition());
                        DialogueManager.start(player, 155);
                        break;
                    case 1152:
                        DialogueManager.start(player, 127);
                        player.setDialogueActionId(79);
                        break;
                    case 1837:
                        DialogueManager.start(player, 144);
                        player.setDialogueActionId(99);
                        break;
                    case 457:
                        DialogueManager.start(player, 117);
                        player.setDialogueActionId(74);
                        break;
                    case 8710:
                    case 8707:
                    case 8706:
                    case 8705:
                        EnergyHandler.rest(player);
                        break;
                    case 534:
                        ShopManager.getShops().get(78).open(player);
                        break;
                    case 947:
                        if (player.getPosition().getX() >= 3092) {
                            player.getMovementQueue().reset();
                            GrandExchange.open(player);
                        }
                        break;
                    case 11226:
                        if (Dungeoneering.doingDungeoneering(player)) {
                            ShopManager.getShops().get(45).open(player);
                        }
                        break;
                    case 9713:
                        DialogueManager.start(player, 107);
                        player.setDialogueActionId(69);
                        break;
                    case 2622:
                        ShopManager.getShops().get(43).open(player);
                        break;
                    case 3101:
                        DialogueManager.start(player, 90);
                        player.setDialogueActionId(57);
                        break;
                    case 7969:
                        // player.getPacketSender().sendMessage("yayayaya i am lord");
                        if (christmas2016.isChristmas() == false || player.getChristmas2016() == 0) {
                            ShopManager.getShops().get(28).open(player);
                            return;
                        } else if (player.getChristmas2016() == 1) {
                            // player.getPacketSender().sendMessage("dialogue 173");
                            DialogueManager.start(player, 173);
                            player.setDialogueActionId(173);
                        } else if (player.getChristmas2016() == 2) {
                            DialogueManager.start(player, 173);
                            player.setDialogueActionId(505050);
                        }
                        // DialogueManager.start(player, ExplorerJack.getDialogue(player));
                        break;
                 /*   case 1597:
                        TaskType.changeSlayerMaster(player, TaskType.VANNAKA);
                        DialogueManager.start(player, SlayerDialogues.dialogue(player));
                        break;
                    case 8275:
                        TaskType.changeSlayerMaster(player, TaskType.DURADEL);
                        DialogueManager.start(player, SlayerDialogues.dialogue(player));
                        break;
                    case 9085:
                        TaskType.changeSlayerMaster(player, TaskType.KURADEL);
                        DialogueManager.start(player, SlayerDialogues.dialogue(player));
                        break;

                    case 7780:
                        TaskType.changeSlayerMaster(player, TaskType.SUMONA);
                        DialogueManager.start(player, SlayerDialogues.dialogue(player));
                        break;*/
                    case 925:
                       /* if (player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)
                                && !player.getSlayer().getSlayerTask().equals(SlayerTasks.NO_TASK)) {
                            DialogueManager.sendStatement(player, "You currently have an assignment with another master.");
                        }else{*/
                        DialogueManager.start(player, SlayerDialogues.dialogue(player));
                        // }

                        break;
                    case 437:
                        DialogueManager.start(player, 99);
                        player.setDialogueActionId(58);
                        break;
                    case 5112:
                        ShopManager.getShops().get(38).open(player);
                        break;
                    case 8591:
                        // player.nomadQuest[0] = player.nomadQuest[1] = player.nomadQuest[2] = false;
                        if (!player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(0)) {
                            DialogueManager.start(player, 48);
                            player.setDialogueActionId(23);
                        } else if (player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(0)
                                && !player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1)) {
                            DialogueManager.start(player, 50);
                            player.setDialogueActionId(24);
                        } else if (player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1))
                            DialogueManager.start(player, 53);
                        break;
                    case 273:
                        DialogueManager.start(player, 61);
                        player.setDialogueActionId(28);
                        break;
                    case 3385:
                        if (player.getMinigameAttributes().getRecipeForDisasterAttributes().hasFinishedPart(0) && player
                                .getMinigameAttributes().getRecipeForDisasterAttributes().getWavesCompleted() < 6) {
                            DialogueManager.start(player, 39);
                            return;
                        }
                        if (player.getMinigameAttributes().getRecipeForDisasterAttributes().getWavesCompleted() == 6) {
                            DialogueManager.start(player, 46);
                            return;
                        }
                        DialogueManager.start(player, 38);
                        player.setDialogueActionId(20);
                        break;
                   /* case 6139:
                        DialogueManager.start(player, 29);
                        player.setDialogueActionId(17);
                        break;*/
                    case 3789:
                        ShopManager.getShops().get(2506).open(player);
                        break;
                    case 2948:
                        DialogueManager.start(player, WarriorsGuild.warriorsGuildDialogue(player));
                        break;
                    case 650:
                        ShopManager.getShops().get(35).open(player);
                        break;
                    case 6055:
                    case 6056:
                    case 6057:
                    case 6058:
                    case 6059:
                    case 6060:
                    case 6061:
                    case 6062:
                    case 6063:
                    case 6064:
                    case 7903:
                        if (npc.getId() == 7903 && player.getLocation() == Location.MEMBER_ZONE) {
                            if (!player.getRights().isMember()) {
                                player.getPacketSender().sendMessage("You must be a Member to use this.");
                                return;
                            }
                        }
                        PuroPuro.catchImpling(player, npc);
                        break;
                    case 8022:
                    case 8028:
                        DesoSpan.siphon(player, npc);
                        break;
                    case 2579:
                        player.setDialogueActionId(13);
                        DialogueManager.start(player, 24);
                        break;
                    case 6537:
                        player.setDialogueActionId(10);
                        DialogueManager.start(player, 19);
                        break;
                    case 4249:
                        player.setDialogueActionId(9);
                        DialogueManager.start(player, 64);
                        break;
                    case 6807:
                    case 6994:
                    case 6995:
                    case 6867:
                    case 6868:
                    case 6794:
                    case 6795:
                    case 6815:
                    case 6816:
                    case 6874:
                    case 6873:
                    case 3594:
                    case 3590:
                    case 3596:
                        if (player.getSummoning().getFamiliar() == null
                                || player.getSummoning().getFamiliar().getSummonNpc() == null
                                || player.getSummoning().getFamiliar().getSummonNpc().getIndex() != npc.getIndex()) {
                            player.getPacketSender().sendMessage("That is not your familiar.");
                            return;
                        }
                        player.getSummoning().store();
                        break;
                    case 605:
                        player.getPacketSender().sendMessage("")
                                .sendMessage("You currently have " + player.getPointsHandler().getVotingPoints()
                                        + " Voting points.")
                                .sendMessage(
                                        "You can earn points and coins by voting. To do so, simply use the ::vote command.");
                        ;
                        ShopManager.getShops().get(90).open(player);
                        // player.setDialogueActionId(8);
                        // DialogueManager.start(player, 13);
                        break;
                    case 6970:
                        player.setDialogueActionId(3);
                        DialogueManager.start(player, 3);
                        break;
                    case 318:
                    case 316:
                    case 313:
                    case 312:
                    case 5748:
                    case 2067:
                        player.setEntityInteraction(npc);
                        Fishing.setupFishing(player, Fishing.forSpot(npc.getId(), false));
                        break;
                    case 805:
                        ShopManager.getShops().get(34).open(player);
                        break;
                    case 462:
                        ShopManager.getShops().get(33).open(player);
                        break;
                    case 461:
                        ShopManager.getShops().get(32).open(player);
                        break;
                    case 8444:

                        ShopManager.getShops().get(31).open(player);
                        break;
                    case 400:

                        ShopManager.getShops().get(101).open(player);
                        break;
                    case 8459:
                        ShopManager.getShops().get(30).open(player);
                        break;
                    case 3299:
                        ShopManager.getShops().get(21).open(player);
                        break;
                    case 548:
                        ShopManager.getShops().get(20).open(player);
                        break;
                    case 1685:
                        ShopManager.getShops().get(19).open(player);
                        break;
                    case 308:
                        ShopManager.getShops().get(18).open(player);
                        break;
                    case 802:
                        ShopManager.getShops().get(17).open(player);
                        break;
                    case 970:
                        ShopManager.getShops().get(81).open(player);
                        break;
                    // case 12241:
                    case 8405:
                        ShopManager.getShops().get(99).open(player);
                        break;
                    case 278:
                        ShopManager.getShops().get(16).open(player);
                        break;
                    case 4946:
                        ShopManager.getShops().get(15).open(player);
                        break;
                    case 948:
                        ShopManager.getShops().get(13).open(player);
                        break;
                    case 4906:
                        ShopManager.getShops().get(14).open(player);
                        break;
                   /* case 520:
                    case 521:
                        ShopManager.getShops().get(12).open(player);
                        break;*/
                    case 2292:
                        ShopManager.getShops().get(11).open(player);
                        break;
                    case 28:
                        ShopManager.getShops().get(83).open(player);
                        break;
                    case 2676:
                        player.getPacketSender().sendInterface(3559);
                        player.getAppearance().setCanChangeAppearance(true);
                        break;
                    case 519:
                        ShopManager.getShops().get(84).open(player);
                        break;
                    case 494:
                    case 1360:
                        player.getBank(player.getCurrentBankTab()).open();
                        break;
                }
                if (!(npc.getId() >= 8705 && npc.getId() <= 8710)) {
                    npc.setPositionToFace(player.getPosition());
                }
                player.setPositionToFace(npc.getPosition());
            }
        }));
    }

    private static void attackNPC(Player player, Packet packet) {
        int index = packet.readShortA();
        if (index < 0 || index > World.getNpcs().capacity())
            return;
        final NPC interact = World.getNpcs().get(index);
        if (interact == null)
            return;

        if (!NpcDefinition.getDefinitions()[interact.getId()].isAttackable()) {
            return;
        }
        //if (!interact.isAttackable() && interact.getId() == 12810) {
        //     player.getMovementQueue().reset();
        //     player.sendMessage("You cannot attack him while his minions are still alive.");
        //      return;
        //  }



        if (interact.getConstitution() <= 0 && !interact.isDying()){
            //    player.sendMessage("This npc was glitched");
            interact.setConstitution(interact.getDefinition().getHitpoints());
            //TaskManager.submit(new NPCRespawnTask(interact,  2, player));
            // World.deregister(interact);

        }

        if (interact.getConstitution() <= 0) {
            player.getMovementQueue().reset();
            return;
        }

        if (player.getUsername().equalsIgnoreCase("test")){
            // World.deregister(interact);
        }
        if (!player.getAfkCombatChecker().isAnsweringQuestions())
            player.getAfkCombatChecker().getAfkCombatTimer().reset();

        if (player.getEquipment().contains(22006) && player.getLastCombatType() == RANGED) {
            if (CombatFactory.npcsDeathDartDontWork(interact)) {
                player.getMovementQueue().reset();
                return;
            }
        }
        if (player.getCombatBuilder().getStrategy() == null) {
            player.getCombatBuilder().determineStrategy();
        }
        if (CombatFactory.checkAttackDistance(player, interact)) {
            player.getMovementQueue().reset();
        }
        if (UltimateIronmanHandler.hasItemsStored(player) && player.getLocation() != Location.DUNGEONEERING) {
            player.getPacketSender().sendMessage("You must claim your stored items at Dungeoneering first.");
            player.getMovementQueue().reset();
            return;
        }

        if (player.getRights() != PlayerRights.DEVELOPER) {
            if (interact.getId() == 12810) {
                if (player.getSlayer().getSlayerTask().getNpcId() != interact.getId()) {
                    player.sendMessage("This can only be attacked whilst he his your assigned boss slayer task.");
                    return;
                }
            }

            if (interact.getId() == 9892 || interact.getId() == 9893 || interact.getId() == 9894) {
                if (player.getSlayer().getSlayerTask().getNpcId() != interact.getId()) {
                    player.sendMessage("This can only be attacked whilst he his your assigned hard slayer task.");
                    return;
                }
            }
        }


        if (interact.getId() == 3){
            player.sendMessage("The Dan Event has ended.");
            player.getCombatBuilder().reset(true);
            return;
            /*int total = KillsTracker.getTotalKillsForNpc(interact.getId(), player);
            if (total >= 10000){
                player.sendMessage("You have reached your 10,000 kill limit for Dan's presents.");
                player.getCombatBuilder().reset(true);
                return;
            }*/
        }

        if (interact.getId() == 9019){
            /*player.sendMessage("The St. Patrick's Event has ended.");
            player.getCombatBuilder().reset(true);
            return;*/
            int total = KillsTracker.getTotalKillsForNpc(interact.getId(), player);
            if (total >= 50000){
                player.sendMessage("You have reached your 50,000 kill limit for St. Patrick Leprechauns.");
                player.getCombatBuilder().reset(true);
                return;
            }
        }


        if (interact.getId() == 9890){
            int total = KillsTracker.getTotalKillsForNpc(interact.getId(), player);
            if (total >= 100000){
                player.sendMessage("You have reached your 100,000 kill limit for Prysm's");
                player.getCombatBuilder().reset(true);
                return;
            }
        }
        if (interact.getId() == 9898){
            int total = KillsTracker.getTotalKillsForNpc(interact.getId(), player);
            if (total >= 100000){
                player.sendMessage("You have reached your 100,000 kill limit for Mutated Sharks");
                player.getCombatBuilder().reset(true);
                return;
            }
        }

        if (interact.getId() == 10009){
            if (player.getAssassinsGuild().getTier()< 3){
                player.sendMessage("You need to be a Tier 3 Assassin to do this.");
                player.getCombatBuilder().reset(true);
                return;
            }
            if (player.getLocation() == Location.ASSASSINS_GUILD && player.getPosition().getY() <= 5274 && interact.getPosition().getY() >= 5275){
                player.getCombatBuilder().reset(true);
                return;
            }
        }
        if (interact.getId() == 10008){
            if (player.getAssassinsGuild().getTier()< 2){
                player.sendMessage("You need to be a Tier 2 Assassin to do this.");
                player.getCombatBuilder().reset(true);
                return;
            }
            if (player.getLocation() == Location.ASSASSINS_GUILD && player.getPosition().getY() <= 5274 && interact.getPosition().getY() >= 5275){
                player.getCombatBuilder().reset(true);
                return;
            }
        }
        if (interact.getId() == 10007){
            if (player.getAssassinsGuild().getTier()< 1){
                player.sendMessage("You need to be a Tier 1 Assassin to do this.");
                player.getCombatBuilder().reset(true);
                return;
            }
            if (player.getLocation() == Location.ASSASSINS_GUILD && player.getPosition().getY() <= 5274 && interact.getPosition().getY() >= 5275){
                player.getCombatBuilder().reset(true);
                return;
            }
        }


        if (interact.getId() == 9020 || interact.getId() ==  10022) {
            int accounts = 0;
            for (Player p : World.getPlayers()) {
                if (p == null)
                    continue;
                if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                    if (p.getCombatBuilder() != null && p.getCombatBuilder().getVictim() != null
                            && p.getCombatBuilder().getVictim().isNpc() && ((NPC)p.getCombatBuilder().getVictim()).getId() == interact.getId()) {
                        accounts++;
                        continue;
                    }
                }
            }
            if (accounts == 1) {
                player.getPacketSender().sendMessage("You already have an account attacking this!");
                player.getCombatBuilder().reset(true);
                return;
            }
        }


        if (interact.getId() == 7134 && KillsTracker.getFenrirKills(player) < 50000) {
            player.getPacketSender().sendMessage("You need 50,000 Fenrir kills. You currently have @red@"
                    + KillsTracker.getFenrirKills(player) + "@bla@ kills.");
            player.getCombatBuilder().reset(true);
            return;
        }
        if (interact.getId() == 28060 && KillsTracker.getGemstoneKills(player) < 30000 && player.getRights() != PlayerRights.YOUTUBER) {
            player.getPacketSender().sendMessage("You need 30,000 Gemstone Dragon kills. You currently have @red@"
                    + KillsTracker.getGemstoneKills(player) + "@bla@ kills.");
            player.getCombatBuilder().reset(true);
            return;
        }

        if (player.getRights() != PlayerRights.DEVELOPER) {
            for (NpcRequirements req : NpcRequirements.values()) {
                if (interact.getId() == req.getNpcId()) {
                    if (req.getKillCount() > 0){
                        if (KillsTracker.getTotalKills(player) < req.getKillCount()) {
                            player.sendMessage("You need atleast " + req.getKillCount() + " NPC kills to attack this. (" + KillsTracker.getTotalKills(player)  + "/"
                                    + req.getKillCount() + ")");
                            return;
                        }
                    }else {
                        int npc = req.getRequireNpcId();
                        if (req.getNpcId() == 3308)
                            npc = 8008;
                        else  if (req.getNpcId() == 3117)
                            npc = 3308;
                        else  if (req.getNpcId() == 201)
                            npc = 3117;
                        else  if (req.getNpcId() == 202)
                            npc = 201;
                        else  if (req.getNpcId() == 203)
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
                            player.sendMessage("You need atleast " + req.getAmountRequired() + " "
                                    + NpcDefinition.forId(npc).getName() + " kills to attack this. (" + total + "/"
                                    + req.getAmountRequired() + ")");
                            return;
                        }
                    }
                    break;
                }
            }
        }


        player.getCombatBuilder().attack(interact);

    }// this is on the attack option btw. i wasnt able to make it work on "show drop"
    // ill pay you

    public void handleSecondClick(Player player, Packet packet) {
        int index = packet.readLEShortA();
        if (index < 0 || index > World.getNpcs().capacity())
            return;
        final NPC npc = World.getNpcs().get(index);
        if (npc == null)
            return;
        player.setEntityInteraction(npc);
        final int npcId = npc.getId();
        if (player.getRights() == PlayerRights.DEVELOPER)
            player.getPacketSender().sendMessage("Second click npc id: " + npcId);
        player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
            @Override
            public void execute() {
                if (PickpocketData.forNpc(npc.getId()) != null) {
                    Pickpocket.handleNpc(player, npc);
                    return;
                }
                // if ()
                switch (npc.getId()) {
                    case 10017:
                        PossibleLootInterface.openInterface(player, PossibleLootInterface.LootData.TRAVELLING_MERCHANT);
                        break;
                    case 10006:
                        ShopManager.getShops().get(2507).open(player);
                        break;
                    case PlayerOwnedShopManager.NPC_ID:

                        player.getPlayerOwnedShopManager().openMain();
                        // player.getPlayerOwnedShopManager().open();
                        break;
                    case 8459:
                        Decanting.notedDecanting(player);
                        break;
                    case 788:
                        player.getPacketSender().sendEnterInputPrompt(
                                "How many would you like to buy (1 Lottery ticket costs 1 Upgrade Tokens)");
                        player.setInputHandling(new EnterLotteryTicketAmount());
                        break;
                    case 3306:
                    case 130:
                        player.getPacketSender().sendMessage("<col=255>You currently have "
                                + player.getPointsHandler().getEventPoints() + " Event points!");
                        ShopManager.getShops().get(81).open(player);
                        break;
                    case 5382:
                        if (player.getGameMode().equals(GameMode.ULTIMATE_IRONMAN)) {
                            UltimateIronmanHandler.handleQuickStore(player);
                        } else {
                            DialogueManager.start(player, 195);
                        }

                        player.getClickDelay().reset();
                        break;
                    case 4601:
                        ShopManager.getShops().get(110).open(player);
                        player.getPacketSender().sendMessage("").sendMessage(
                                "You currently have " + player.getPointsHandler().getLoyaltyPoints() + " Loyalty Points.");
                        break;
                    case 1394:
                        int[] items = {1053, 1057, 1055, 1038, 1040, 1042, 1044, 1046, 1048, 1050, 14008, 14009, 14010,
                                14484, 19115, 19114, 13736, 13744, 13738, 13742, 13740, 6293, 18754, 11694, 11696, 11698, 11700,
                                15018, 15019, 15020, 15220, 12601, 12603, 12605, 20000, 20001, 20002, 6769, 10942, 10934,
                                455};
                        player.getPacketSender().sendInterface(52300);
                        for (int i = 0; i < items.length; i++) {
                          /*  System.out.println(items[i]+", // " + ItemDefinition.forId(items[i]).getName());
                            System.out.println((items[i] + 1)+", // " + ItemDefinition.forId(items[i]+ 1).getName());
                            player.getInventory().add(items[i], 1);*/
                            player.getPacketSender().sendItemOnInterface(52302, items[i], i, 1);
                        }
                        break;

                    case 4653:
                        player.getPacketSender().sendInterfaceRemoval();
                        ShopManager.getShops().get(85).open(player);
                        break;
                    case 736:
                        npc.forceChat("Thanx for the follow :)");
                        break;
                    case 1837:
                        player.getPacketSender().sendInterfaceRemoval();
                        if (player.getInventory().getAmount(11180) < 1) {
                            player.getPacketSender().sendMessage("You do not have enough tokens.");
                            return;
                        } else
                            player.getInventory().delete(11180, 1);
                        // So we grab the players pID too determine what Z they will be getting. Not
                        // sure how kraken handles it, but this is how we'll handle it.
                        player.moveTo(new Position(3025, 5231));
                        // player.getPacketSender().sendMessage("Index: " + player.getIndex());
                        // player.getPacketSender().sendMessage("Z: " + player.getIndex() * 4);
                        player.getPacketSender().sendMessage("Teleporting to Trio...");
                        player.getPacketSender()
                                .sendMessage("@red@Warning:@bla@ you @red@will@bla@ lose your items on death here!");
                        // Will sumbit a task to handle token remove, once they leave the minigame the
                        // task will be removed.
                        // trioMinigame.failsafe(player);
                        // trioMinigame.handleNPCSpawning(player);
                        trioMinigame.handleTokenRemoval(player);

                        break;
                    case 3777:
                        player.getPacketSender().sendMessage("")
                                .sendMessage("<shad=1>@gre@You currently have " + player.getPointsHandler().getMemberPoints()
                                        + " Donator points.")
                                .sendMessage("<shad=1>@gre@You can get more points by donating at ::donate");
                        ShopManager.getShops().get(80).open(player);
                        break;
                    case 550:
                        ShopManager.getShops().get(2).open(player);
                        break;
                    case 2579:
                        ShopManager.getShops().get(46).open(player);
                        player.getPacketSender().sendMessage("<col=255>You currently have "
                                + player.getPointsHandler().getPrestigePoints() + " Prestige points!");
                        break;
                    case 457:
                        player.getPacketSender().sendMessage("The ghost teleports you away.");
                        player.getPacketSender().sendInterfaceRemoval();
                        player.moveTo(new Position(3651, 3486));
                        break;
                    case 2622:
                        ShopManager.getShops().get(43).open(player);
                        break;
                    case 462:
                        npc.performAnimation(CombatSpells.CONFUSE.getSpell().castAnimation().get());
                        npc.forceChat("Off you go!");
                        TeleportHandler.teleportPlayer(player, new Position(2911, 4832),
                                player.getSpellbook().getTeleportType());
                        break;
                    case 3101:
                        DialogueManager.start(player, 95);
                        player.setDialogueActionId(57);
                        break;
                    case 7969:
                        ShopManager.getShops().get(28).open(player);
                        break;
                    case 605:
                        player.getPacketSender().sendMessage("")
                                .sendMessage("You currently have " + player.getPointsHandler().getVotingPoints()
                                        + " Voting points.")
                                .sendMessage(
                                        "You can earn points and coins by voting. To do so, simply use the ::vote command.");
                        ;
                        ShopManager.getShops().get(90).open(player);
                        break;
                 /*   case 1597:
                        TaskType.changeSlayerMaster(player, TaskType.VANNAKA);
                        if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
                            player.getSlayer().assignTask();
                        else
                            DialogueManager.start(player, SlayerDialogues.findAssignment(player));
                        break;
                    case 8275:
                        TaskType.changeSlayerMaster(player, TaskType.DURADEL);
                        if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
                            player.getSlayer().assignTask();
                        else
                            DialogueManager.start(player, SlayerDialogues.findAssignment(player));
                        // System.out.println("CLALED!<");
                        break;
                    case 9085:

                        TaskType.changeSlayerMaster(player, TaskType.KURADEL);
                        if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
                            player.getSlayer().assignTask();
                        else
                            DialogueManager.start(player, SlayerDialogues.findAssignment(player));

                        break;
                        break;*/

                    case 925:
                        if (player.getSlayer().getTaskType() == null) {
                            TaskType.changeSlayerMaster(player, TaskType.EASY);
                        }
                        player.getProgressionManager().getProgressionTask(0).incrementProgress(0, 1);
                        if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK){
                            DialogueManager.start(player, 8010);
                            player.setDialogueActionId(8010);
                        }
                        else {
                            DialogueManager.start(player, SlayerDialogues.findAssignment(player));
                        }
                        break;
                    case 9000:

                        if (!player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)
                                && player.getSlayer().getSlayerTask().equals(SlayerTasks.NO_TASK)) {
                            TaskType.changeSlayerMaster(player, TaskType.BOSS_SLAYER);
                        }
                        if (player.getSlayer().getTaskType().equals(TaskType.BOSS_SLAYER)) {

                            TaskType.changeSlayerMaster(player, TaskType.BOSS_SLAYER);
                            player.getProgressionManager().getProgressionTask(0).incrementProgress(0, 1);
                            if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
                                player.getSlayer().assignTask();

                            else
                                DialogueManager.start(player, SlayerDialogues.findAssignment(player));
                        } else
                            DialogueManager.sendStatement(player, "You already have a slayer task.");


                        break;
                    case 7780:
                        /*TaskType.changeSlayerMaster(player, TaskType.SUMONA);
                        if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK)
                            player.getSlayer().assignTask();
                        else
                            DialogueManager.start(player, SlayerDialogues.findAssignment(player));*/
                        break;
                    case 8591:
                        if (!player.getMinigameAttributes().getNomadAttributes().hasFinishedPart(1)) {
                            player.getPacketSender()
                                    .sendMessage("You must complete Nomad's quest before being able to use this shop.");
                            return;
                        }
                        ShopManager.getShops().get(37).open(player);
                        break;
                    case 805:
                        Tanning.selectionInterface(player);
                        break;
                    case 318:
                    case 316:
                    case 313:
                    case 312:
                    case 5748:
                    case 2067:
                        player.setEntityInteraction(npc);
                        Fishing.setupFishing(player, Fishing.forSpot(npc.getId(), true));
                        break;
                    case 4946:
                        ShopManager.getShops().get(15).open(player);
                        break;
                    case 946:
                        ShopManager.getShops().get(1).open(player);
                        break;
                    case 961:
                        ShopManager.getShops().get(6).open(player);
                        break;
                    case 1861:
                        ShopManager.getShops().get(3).open(player);
                        break;
                    case 705:
                        ShopManager.getShops().get(4).open(player);
                        break;
                    case 2253:
                        ShopManager.getShops().get(9).open(player);
                        break;
                    case 6970:
                        player.setDialogueActionId(35);
                        DialogueManager.start(player, 63);
                        break;

                    // begin ironman second click handles

                }
                npc.setPositionToFace(player.getPosition());
                player.setPositionToFace(npc.getPosition());
            }
        }));
    }

    public void handleThirdClick(Player player, Packet packet) {
        int index = packet.readShort();
        if (index < 0 || index > World.getNpcs().capacity())
            return;
        final NPC npc = World.getNpcs().get(index);
        if (npc == null)
            return;
        player.setEntityInteraction(npc).setPositionToFace(npc.getPosition().copy());
        npc.setPositionToFace(player.getPosition());
        if (player.getRights() == PlayerRights.DEVELOPER)
            player.getPacketSender().sendMessage("Third click npc id: " + npc.getId());
        player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
            @Override
            public void execute() {
                switch (npc.getId()) {

                    case 9000:
                        ShopManager.getShops().get(117).open(player);
                        break;

                    case PlayerOwnedShopManager.NPC_ID:
                        player.getPlayerOwnedShopManager().openEditor();
                        break;
                    case 788:
                        player.sendMessage("@bla@There are currently @red@" + LotterySystem.getCurrentTicketAmount()
                                + " @bla@Lottery tickets- Winner pot is@red@: " + LotterySystem.getTotalPrizepool()
                                + "@bla@ Upgrade Tokens");
                        break;
                    case 5382:
                        if (player.getGameMode().equals(GameMode.ULTIMATE_IRONMAN)) {
                            UltimateIronmanHandler.handleQuickRetrieve(player);
                        } else {
                            DialogueManager.start(player, 195);
                        }

                        player.getClickDelay().reset();
                        break;
                    case 4653:
                        player.getPacketSender()
                                .sendMessage("Unfortunately, ship charters are still being established. Check back soon.");
                        break;
                    case 736:
                        player.forceChat("Nah. I don't want to feed the cancer.");
                        break;
                    case 3777:
                        ShopManager.getShops().get(25).open(player);
                        break;
                    case 3101:
                        ShopManager.getShops().get(42).open(player);
                        break;
                    case 1597:
                    case 8275:
                    case 9085:
                    case 7780:
                        ShopManager.getShops().get(40).open(player);
                        break;
                    case 925:
                        ShopManager.getShops().get(47).open(player);
                        player.getPacketSender().sendMessage("<img=15>You currently have @red@ "
                                + player.getPointsHandler().getSlayerPoints() + "@bla@ slayer points");
                        break;
                    case 946:
                        ShopManager.getShops().get(0).open(player);
                        break;
                    case 1861:
                        ShopManager.getShops().get(2).open(player);
                        break;
                    // case 597:
                    // ShopManager.getShops().get(54).open(player);
                    // break;
                    case 961:
                        if (player.getRights() == PlayerRights.PLAYER) {
                            player.getPacketSender().sendMessage("This feature is currently only available for members.");
                            return;
                        }
                        boolean restore = player.getSpecialPercentage() < 100;
                        if (restore) {
                            player.setSpecialPercentage(100);
                            CombatSpecial.updateBar(player);
                            player.getPacketSender().sendMessage("Your special attack energy has been restored.");
                        }
                        for (Skill skill : Skill.values()) {
                            if (player.getSkillManager().getCurrentLevel(skill) < player.getSkillManager()
                                    .getMaxLevel(skill)) {
                                player.getSkillManager().setCurrentLevel(skill,
                                        player.getSkillManager().getMaxLevel(skill));
                                restore = true;
                            }
                        }
                        if (restore) {
                            player.performGraphic(new Graphic(1302));
                            player.getPacketSender().sendMessage("Your stats have been restored.");
                        } else
                            player.getPacketSender().sendMessage("Your stats do not need to be restored at the moment.");
                        break;
                    case 705:
                        ShopManager.getShops().get(5).open(player);
                        break;
                    case 605:
                        player.getPacketSender().sendMessage("Coming soon!");
                        // player.getPacketSender().sendMessage("").sendMessage("You currently have
                        // "+player.getPointsHandler().getVotingPoints()+" Voting
                        // points.").sendMessage("You can earn points and coins by voting. To do so,
                        // simply use the ::vote command.");;
                        // ShopManager.getShops().get(90).open(player);
                        break;
                    case 2253:
                        ShopManager.getShops().get(10).open(player);
                        break;
                    case 5913:
                        ShopManager.getShops().get(0).open(player);
                        break;
                }
                npc.setPositionToFace(player.getPosition());
                player.setPositionToFace(npc.getPosition());
            }
        }));
    }

    public void handleFourthClick(Player player, Packet packet) {
        int index = packet.readLEShort();
        if (index < 0 || index > World.getNpcs().capacity())
            return;
        final NPC npc = World.getNpcs().get(index);
        if (npc == null)
            return;

        // put your own condition here
        if (npc.getDefinition().isAttackable() && NPCDrops.forId(npc.getId()) == null && npc.getDefinition().getId() == 1880) {
            PossibleLootInterface.openInterface(player, PossibleLootInterface.LootData.FPK_SOLDIER);
            return;
        }
        if (npc.getDefinition().isAttackable()) {
            if (NPCDrops.forId(npc.getId()) == null && npc.getDefinition().getId() != 1880) {
                player.sendMessage("This NPC doesn't have drops.");
                return;
            }
            DropsInterface.open(player);
            DropsInterface.buildRightSide(player, npc.getId());

            player.getPacketSender().sendMessage(npc.getDefinition().getName() + " kills: @blu@" + KillsTracker.getTotalKillsForNpc(npc.getId(), player));

            return;
        }
        if (BossPets.pickup(player, npc)) { // done in ur NPCDef just change pick up option index from 1 to 3 and ur
            // fine (or if it was 0 before change to 3 )
            player.getMovementQueue().reset();
            return;
        }
        player.setEntityInteraction(npc);
        if (player.getRights() == PlayerRights.DEVELOPER)
            player.getPacketSender().sendMessage("Fourth click npc id: " + npc.getId());
        player.setWalkToTask(new WalkToTask(player, npc.getPosition(), npc.getSize(), new FinalizedMovementTask() {
            @Override
            public void execute() {
                switch (npc.getId()) {
                    case 9000:
                    case 925:
                        DialogueManager.start(player, SlayerDialogues.resetTaskDialogue(player));
                        break;

                    case 961:
                        ShopManager.getShops().get(118).open(player);
                        break;
                    case PlayerOwnedShopManager.NPC_ID:
                        player.getPlayerOwnedShopManager().claimEarnings();
                        // player.getPlayerOwnedShopManager().claimEarnings();
                        break;
                    case 946:
                        ShopManager.getShops().get(82).open(player);
                        break;
                    case 3777:

                        player.sendMessage("<shad=1>@yel@<img=14>Please check out the donation deals in our ::Discord - #Donation-deals");
                        player.sendMessage("<shad=1>@yel@<img=14>Please check out the donation deals in our ::Discord - #Donation-deals");
                        break;
                    case 705:
                        ShopManager.getShops().get(7).open(player);
                        break;
                    case 2253:
                        ShopManager.getShops().get(8).open(player);
                        break;
                    case 605:
                        LoyaltyProgramme.open(player);
                        break;
                    case 4601:
                        LoyaltyProgramme.open(player);
                        break;
                    case 1597:
                    case 9085:
                    case 8275:
                    case 7780:
                        ShopManager.getShops().get(47).open(player);
                        player.getPacketSender().sendMessage("<img=15>You currently have @red@ "
                                + player.getPointsHandler().getSlayerPoints() + "@bla@ slayer points");

                        break;
                }
                npc.setPositionToFace(player.getPosition());
                player.setPositionToFace(npc.getPosition());
                // DropsInterface.open(player);
                // DropsInterface.getList(NpcDefinition.getDefinitions().getClass().getName());

            }
        }));
    }

    @Override
    public void handleMessage(Player player, Packet packet) {
        if (player.isTeleporting() || player.isPlayerLocked() || player.getMovementQueue().isLockMovement())
            return;
        player.afkTicks = 0;
        player.afk = false;
        switch (packet.getOpcode()) {
            case ATTACK_NPC:
                attackNPC(player, packet);
                break;
            case FIRST_CLICK_OPCODE:
                firstClick(player, packet);
                break;
            case SECOND_CLICK_OPCODE:
                handleSecondClick(player, packet);
                break;
            case THIRD_CLICK_OPCODE:
                handleThirdClick(player, packet);
                break;
            case FOURTH_CLICK_OPCODE:
                handleFourthClick(player, packet);
                break;
            case MAGE_NPC:
                int npcIndex = packet.readLEShortA();
                int spellId = packet.readShortA();

             /*   if (npcIndex < 0 || spellId < 0 || npcIndex > World.getNpcs().capacity()) {
                    return;
                }

                NPC n = World.getNpcs().get(npcIndex);
                player.setEntityInteraction(n);

                if (player != null && n != null && player.getRights().isHighStaff()) {
                    player.getPacketSender().sendMessage("Used spell id: " + spellId + " on npc: " + n.getId());
                }

                CombatSpell spell = CombatSpells.getSpell(spellId);

                if (n == null || spell == null) {
                    player.getMovementQueue().reset();
                    return;
                }

                if (!NpcDefinition.getDefinitions()[n.getId()].isAttackable()) {
                    player.getMovementQueue().reset();
                    return;
                }

                if (n.getConstitution() <= 0) {
                    player.getMovementQueue().reset();
                    return;
                }

                player.setPositionToFace(n.getPosition());
                player.setCastSpell(spell);
                if (player.getCombatBuilder().getStrategy() == null) {
                    player.getCombatBuilder().determineStrategy();
                }
                if (CombatFactory.checkAttackDistance(player, n)) {
                    player.getMovementQueue().reset();
                }
                player.getCombatBuilder().resetCooldown();
                player.getCombatBuilder().attack(n);*/
                break;
        }
    }

    public static final int ATTACK_NPC = 72, FIRST_CLICK_OPCODE = 155, MAGE_NPC = 131, SECOND_CLICK_OPCODE = 17,
            THIRD_CLICK_OPCODE = 21, FOURTH_CLICK_OPCODE = 18;
}
