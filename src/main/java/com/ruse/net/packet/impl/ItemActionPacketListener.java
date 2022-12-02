package com.ruse.net.packet.impl;

import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.*;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Shop.ShopManager;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.input.impl.ExchangeX1kTokens;
import com.ruse.model.input.impl.ExchangeXUpgradeTokens;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.ammo.SpecialAmmo;
import com.ruse.world.content.boxes.*;
import com.ruse.world.content.casketopening.CasketOpening;
import com.ruse.world.content.cluescrolls.CLUESCROLL;
import com.ruse.world.content.dialogue.Dialogue;
import com.ruse.world.content.dialogue.DialogueExpression;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.DialogueType;
import com.ruse.world.content.holidayevents.easter2017;
import com.ruse.world.content.instanceMananger.InstanceData;
import com.ruse.world.content.instanceMananger.InstanceInterfaceHandler;
import com.ruse.world.content.skill.impl.dungeoneering.ItemBinding;
import com.ruse.world.content.skill.impl.herblore.Herblore;
import com.ruse.world.content.skill.impl.herblore.ingredientsBook;
import com.ruse.world.content.skill.impl.hunter.*;
import com.ruse.world.content.skill.impl.hunter.Trap.TrapState;
import com.ruse.world.content.skill.impl.prayer.Prayer;
import com.ruse.world.content.skill.impl.runecrafting.Runecrafting;
import com.ruse.world.content.skill.impl.runecrafting.RunecraftingPouches;
import com.ruse.world.content.skill.impl.runecrafting.RunecraftingPouches.RunecraftingPouch;
import com.ruse.world.content.skill.impl.slayer.SlayerDialogues;
import com.ruse.world.content.skill.impl.slayer.SlayerTasks;
import com.ruse.world.content.skill.impl.summoning.BossPets.BossPet;
import com.ruse.world.content.skill.impl.summoning.CharmingImp;
import com.ruse.world.content.skill.impl.summoning.SummoningData;
import com.ruse.world.content.skill.impl.woodcutting.BirdNests;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.content.transportation.*;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.HantoWarrior;

import java.text.NumberFormat;
import java.util.Locale;

public class ItemActionPacketListener implements PacketListener {

    public static final int THIRD_ITEM_ACTION_OPCODE = 75;
    public static final int FIRST_ITEM_ACTION_OPCODE = 122;
    public static final int SECOND_ITEM_ACTION_OPCODE = 16;
    public static int count = 0;
    private static String[] ROCK_CAKE = {"Oww!", "Ouch!", "Owwwy!", "I nearly broke a tooth!", "My teeth!",
            "Who would eat this?", "*grunt*", ":'("};

    public static boolean drinkSuperOverload(final Player player, int slot, int replacePotion) {
        if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUEL_ARENA) {
            player.getPacketSender().sendMessage("You cannot use this potion here.");
            return false;
        }
        if (player.getOverloadPotionTimer() > 0 && player.getOverloadPotionTimer() < 750) {
            player.getPacketSender().sendMessage("You already have the effect of an Overload or Super/Infinity Overload potion.");
            return false;
        }
        if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < 500) {
            player.getPacketSender().sendMessage("You need to have at least 500 Hitpoints to drink this potion.");
            return false;
        }
        player.performAnimation(new Animation(829));
        player.getInventory().getItems()[slot] = new Item(replacePotion, 1);
        player.getInventory().refreshItems();
        player.setOverloadPotionTimer(600);
        player.setPotionUsed(replacePotion);
        player.getPacketSender().sendEffectTimer(-500, replacePotion);

        TaskManager.submit(new SuperOverloadPotionTask(player));
        return true;
    }

    public static boolean drinkInfinityRage(final Player player, int slot, int replacePotion) {
        if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUEL_ARENA) {
            player.getPacketSender().sendMessage("You cannot use this potion here.");
            return false;
        }
        if (player.getOverloadPotionTimer() > 0 && player.getOverloadPotionTimer() < 750) {
            player.getPacketSender().sendMessage("You already have the effect of an Overload or Super/Infinity Overload potion.");
            return false;
        }
        if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < 500) {
            player.getPacketSender().sendMessage("You need to have at least 500 Hitpoints to drink this potion.");
            return false;
        }
        player.performAnimation(new Animation(829));
        player.getInventory().getItems()[slot] = new Item(replacePotion, 1);
        player.getInventory().refreshItems();
        player.setOverloadPotionTimer(600);
        player.setPotionUsed(replacePotion);
        player.getPacketSender().sendEffectTimer(-500, replacePotion);
        TaskManager.submit(new InfinityRagePotionTask(player));
        return true;
    }

    public static boolean drinkOwnerPotion(final Player player, int slot, int replacePotion) {
        if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUEL_ARENA) {
            player.getPacketSender().sendMessage("You cannot use this potion here.");
            return false;
        }
        if (player.getOverloadPotionTimer() > 0 && player.getOverloadPotionTimer() < 750) {
            player.getPacketSender().sendMessage("You already have the effect of an Overload or Super/Infinity Overload potion.");
            return false;
        }
        if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < 500) {
            player.getPacketSender().sendMessage("You need to have at least 500 Hitpoints to drink this potion.");
            return false;
        }
        player.performAnimation(new Animation(829));
        player.getInventory().getItems()[slot] = new Item(replacePotion, 1);
        player.getInventory().refreshItems();
        player.setOverloadPotionTimer(600);
        player.setPotionUsed(replacePotion);
        player.getPacketSender().sendEffectTimer(-500, replacePotion);
        TaskManager.submit(new OwnerPotionTask(player));
        return true;
    }


    public static boolean drinkAngelicPotion(final Player player, int slot, int replacePotion) {
        if (player.getLocation() == Location.WILDERNESS || player.getLocation() == Location.DUEL_ARENA) {
            player.getPacketSender().sendMessage("You cannot use this potion here.");
            return false;
        }
        if (player.getOverloadPotionTimer() > 0 && player.getOverloadPotionTimer() < 750) {
            player.getPacketSender().sendMessage("You already have the effect of an Overload potion.");
            return false;
        }
        if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < 500) {
            player.getPacketSender().sendMessage("You need to have at least 500 Hitpoints to drink this potion.");
            return false;
        }
        player.performAnimation(new Animation(829));
        player.getInventory().getItems()[slot] = new Item(replacePotion, 1);
        player.getInventory().refreshItems();
        player.setOverloadPotionTimer(600);
        player.setPotionUsed(replacePotion);
        player.getPacketSender().sendEffectTimer(-500, replacePotion);
        TaskManager.submit(new AngelicPotionTask(player));
        return true;
    }

    private static void firstAction(final Player player, Packet packet) {
        int interfaceId = packet.readUnsignedShort();
        int slot = packet.readShort();
        int itemId = packet.readShort();
        /*
         * if(interfaceId == 38274) { Construction.handleItemClick(itemId, player);
         * return; }
         */
        if (Misc.checkForOwner()) {
            // System.out.println("Slot: " + slot + ", itemId: " + itemId + ", interface: " + interfaceId);
        }
        if (slot < 0 || slot > player.getInventory().capacity())
            return;
        if (player.getInventory().getItems()[slot].getId() != itemId)
            return;
        player.setInteractingItem(player.getInventory().getItems()[slot]);
        if (Prayer.isBone(itemId)) {
            Prayer.buryBone(player, itemId);
            return;
        }
        if (Consumables.isFood(player, itemId, slot))
            return;
        if (Consumables.isPotion(itemId)) {
            Consumables.handlePotion(player, itemId, slot);
            return;
        }

        if (BirdNests.isNest(itemId)) {
            BirdNests.searchNest(player, itemId);
            return;
        }
        if (Herblore.cleanHerb(player, itemId))
            return;
        if (MemberScrolls.handleScroll(player, itemId, false))
            return;
        if (Effigies.isEffigy(itemId)) {
            Effigies.handleEffigy(player, itemId);
            return;
        }
        if (ExperienceLamps.handleLamp(player, itemId)) {
            return;
        }
        if (player.aonBoxItem > 0) {
            player.sendMessage("Please choose to keep or gamble your item before doing this!");
            return;
        }
        if (player.getInventory().containsAny(11846, 11848, 11850, 11852, 11854, 11856)
                && !player.getInventory().containsAny(11846, 11848, 11850, 11852, 11854, 11856)) {
            if (!player.getClickDelay().elapsed(250) || !player.getInventory().contains(itemId))
                return;
            if (player.busy()) {
                player.getPacketSender().sendMessage("You cannot open this right now.");
                return;
            }

            int[] items = itemId == 11858 ? new int[]{10350, 10348, 10346, 10352}
                    : itemId == 19580 ? new int[]{19308, 19311, 19314, 19317, 19320}
                    : itemId == 11860 ? new int[]{10334, 10330, 10332, 10336}
                    : itemId == 11862 ? new int[]{10342, 10338, 10340, 10344}
                    : itemId == 11848 ? new int[]{4716, 4720, 4722, 4718}
                    : itemId == 11856 ? new int[]{4753, 4757, 4759, 4755}
                    : itemId == 11850 ? new int[]{4724, 4728, 4730, 4726}
                    : itemId == 11854
                    ? new int[]{4745, 4749, 4751, 4747}
                    : itemId == 11852
                    ? new int[]{4732, 4734, 4736,
                    4738}
                    : itemId == 11846
                    ? new int[]{4708, 4712,
                    4714, 4710}
                    : new int[]{itemId};

            if (player.getInventory().getFreeSlots() < items.length) {
                player.getPacketSender().sendMessage("You do not have enough space in your inventory.");
                return;
            }
            player.getInventory().delete(itemId, 1);
            for (int i : items) {
                player.getInventory().add(i, 1);
            }
            player.getPacketSender().sendMessage("You open the set and find items inside.");
            player.getClickDelay().reset();
            return;
        }

        if (itemId != 23782 && itemId != 23776 && itemId != 23812 && itemId != 19116){
            if (!player.getInventory().containsAny(11846, 11848, 11850, 11852, 11854, 11856, 10835)) {
                player.getDissolving().handle(itemId, 1, false);
            }
        }


        switch (itemId) {
            case 23794:
                int total = player.getInventory().getAmount(23794) ;
                if (total >= 2147000)
                    total = 2147000;
                if ((total* 1000) + player.getInventory().getAmount(12855) >= Integer.MAX_VALUE || (total* 1000) + player.getInventory().getAmount(12855) <= 0){
                    total = (Integer.MAX_VALUE - player.getInventory().getAmount(12855)) / 1000;
                }
                player.getInventory().delete(23794, total );
                player.getInventory().add(12855, total* 1000);
                player.sendMessage("You just exchanged 1k tokens into Upgrade tokens.");
                break;
            case 15112:
                if (player.getInventory().contains(3430) && player.getInventory().contains(2126) && player.getInventory().contains(15112)){
                    player.getInventory().delete(15112, 1);
                    player.getInventory().delete(3430, 1);
                    player.getInventory().delete(2126, 1);
                    player.getInventory().add(15111, 1);
                    player.sendMessage("You fill the Canopic jar.");
                }else{
                    player.sendMessage("You don't have all the ingredients to fill the jar.");
                }
                break;
            case 23784:
                if (player.getInventory().getAmount(23784) >= 10) {
                    player.getInventory().delete(23784, 10);
                    player.getInventory().add(23783, 1);
                    player.sendMessage("You created a Crystal box!");
                }else{
                    player.sendMessage("You need 10 Crystal seeds to do this.");
                }
                break;
            case 23783:
                if (player.getInventory().getFreeSlots() >= 9) {
                    player.getInventory().delete(23783, 1);
                    for (int i = 0 ; i < 9; i ++)
                        player.getInventory().add(23785 + i, 1);
                    player.sendMessage("You opened a Crystal box!");
                }else{
                    player.sendMessage("You need at least 9 free inventory spaces to do this.");
                }
                break;
            case 23752:
                if (player.getInventory().contains(23752)) {
                    if (player.getIsleDropRate() >= 100){
                        player.sendMessage("@red@Your Isle of the Gods drop rate is already capped at 100%");
                        return;
                    }
                    player.getInventory().delete(23752, 1);
                    player.setIsleDropRate(player.getIsleDropRate() + 20);
                    player.sendMessage("@blu@Your Isle of the Gods drop rate is now " + player.getIsleDropRate() + "%");
                }
                break;
            case 23755:
                if (player.getInventory().contains(23755)) {
                    if (player.isUnlockedRammernaut()){
                        player.sendMessage("@red@You already have Rammernaut unlocked.");
                        return;
                    }
                    player.getInventory().delete(23755, 1);
                    player.setUnlockedRammernaut(true);
                    World.sendMessage("@blu@<img=856>[News]<img=856><col=ff0000>" + player.getUsername() + " has just unlocked Rammernaut!");
                }
                break;
            case 23707:
                player.getAssassinsGuild().openCostumeInterface();
                break;
            case 23546:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                if (!player.getInventory().contains(23546)) {
                    return;
                }
                int amt = 1;
                int minutesEXP1 = 30 * amt;
                int  minutesDR1 = 15 * amt;
                int  minutesDMG1 = 5 * amt;
                player.getInventory().delete(23546, amt);
                player.getPacketSender()
                        .sendMessage("@blu@You received " + minutesEXP1 + " minutes of Bonus Xp, " + minutesDR1 + " minutes of x2 DR, " + minutesDMG1 + " minutes of +100% Damage boost!");
                BonusExperienceTask.addBonusXp(player, minutesEXP1);
                VotingDRBoostTask.addBonusDR(player, minutesDR1);
                VotingDMGBoostTask.addBonusDMG(player, minutesDMG1);
                player.getClickDelay().reset();
                player.performAnimation(new Animation(829));
                break;
            case 23545:
            case 23662:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                if (!player.getInventory().contains(itemId)) {
                    return;
                }
                amt = 1;
                minutesDR1 = 60 * amt;
                minutesDMG1 = 60 * amt;
                player.getInventory().delete(itemId, amt);
                player.getPacketSender()
                        .sendMessage("@blu@You received " + minutesDR1 + " minutes of +100 DR, " + minutesDMG1 + " minutes of +100% DMG boost!");
                VotingDRBoostTask.addBonusDR(player, minutesDR1);
                VotingDMGBoostTask.addBonusDMG(player, minutesDMG1);
                player.getClickDelay().reset();
                player.performAnimation(new Animation(829));
                break;

            case 23644:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                if (!player.getInventory().contains(itemId)) {
                    return;
                }
                amt = 1;
                minutesDR1 = 180 * amt;
                minutesDMG1 = 180 * amt;
                player.getInventory().delete(itemId, amt);
                player.getPacketSender()
                        .sendMessage("@blu@You received " + minutesDR1 + " minutes of +100 DR, " + minutesDMG1 + " minutes of +100% DMG boost!");
                VotingDRBoostTask.addBonusDR(player, minutesDR1);
                VotingDMGBoostTask.addBonusDMG(player, minutesDMG1);
                player.getClickDelay().reset();
                player.performAnimation(new Animation(829));
                break;
            case 23570:
                MassDissolver.itemClick(player);
                break;
            case 23569:
                HantoWarrior.sacrificeTicketsInput(player);
                break;
            case 23547:
                if (player.getInventory().contains(23547)) {
                    player.getInventory().delete(23547, 1);
                    player.getInventory().add(23536, 100);
                    player.getInventory().add(15288, 1);
                    player.sendMessage("You opened a Valentine gift!");
                }
                break;
            case 23536:
                player.getVDayEvent().openInterface(player.getInventory().getAmount(23536));
                break;
            case 23534:
                player.sendMessage("1,000 Sanctum crystals can be used on Sanctum armour to upgrade it.");
                break;
            case 23475:
            case 23476:
            case 23477:
            case 23478:
                player.getCardPack().handleCardClick(itemId);
                break;
            case 23481:
                if (player.getInventory().contains(23481) && !player.isLearnedCrestCreation()) {
                    player.getInventory().delete(23481, 1);
                    player.setLearnedCrestCreation(true);
                    player.sendMessage("@red@You now have the ability to create crests.");
                } else {
                    player.sendMessage("@red@You already have the ability to create crests.");
                }
                break;
            case 5023:
                if (player.getInventory().getAmount(5023) >= 25) {
                    int amount = player.getInventory().getAmount(5023) / 25;
                    player.getInventory().delete(5023, player.getInventory().getAmount(5023));
                    player.getPointsHandler().setSlayerPoints(amount, true);
                    player.sendMessage("You received " + amount + " Slayer points!");
                } else {
                    player.sendMessage("You need at least 25 Slayer tickets to convert to points.");
                }
                break;
            case 23463:
                if (player.getInventory().getAmount(23463) >= 100) {
                    int amount = player.getInventory().getAmount(23463) / 100;
                    player.getInventory().delete(23463, amount * 100);
                    player.getInventory().add(23464, amount * 1);
                    player.sendMessage("You created x" + amount + " Sanctum keys!");
                } else {
                    player.sendMessage("You need at least 100 Sanctum Key shards to make a Sanctum key.");
                }
                break;
            case 23858:
                if (player.getInventory().getAmount(23858) >= 100) {
                    int amount = player.getInventory().getAmount(23858) / 100;
                    player.getInventory().delete(23858, amount * 100);
                    player.getInventory().add(23859, amount * 1);
                    player.sendMessage("You created x" + amount + " Anima keys!");
                } else {
                    player.sendMessage("You need at least 100 Anima Key shards to make an Anima key.");
                }
                break;
            case 23453:
                if (player.isSantaTransform()) {
                    player.getPacketSender().sendMessage("You are no longer santa clause.");
                    player.setSantaTransform(false);
                    player.setEvilSantaTransform(false);
                    player.newStance();
                    player.setNpcTransformationId(-1);
                    player.getUpdateFlag().flag(Flag.APPEARANCE);
                } else {
                    player.getPacketSender().sendMessage("You have transformed into santa clause.");
                    player.setSantaTransform(true);
                    player.setEvilSantaTransform(false);
                    player.newStance();
                    player.setNpcTransformationId(9877);
                    player.getUpdateFlag().flag(Flag.APPEARANCE);
                }
                break;
            case 23454:
                if (player.isEvilSantaTransform()) {
                    player.getPacketSender().sendMessage("You are no longer Evil santa clause.");
                    player.setSantaTransform(false);
                    player.setEvilSantaTransform(false);
                    player.newStance();
                    player.setNpcTransformationId(-1);
                    player.getUpdateFlag().flag(Flag.APPEARANCE);
                } else {
                    player.getPacketSender().sendMessage("You have transformed into Evil santa clause.");
                    player.setEvilSantaTransform(true);
                    player.setSantaTransform(false);
                    player.newStance();
                    player.setNpcTransformationId(9878);
                    player.getUpdateFlag().flag(Flag.APPEARANCE);
                }
                break;
            case 23375:
                player.sendMessage("Auto-dissembler charges: @blu@" + (2500 - player.getDissemblerCharges()) + "/2500");
                break;
            case 23376:
                player.getMysteryBoxOpener().display(23376, "Christmas Box",
                        XmasBox.common, XmasBox.uncommon, XmasBox.rare,
                        new int[]{75, 23, 2});
                break;
            case 23308:
                player.getMysteryBoxOpener().display(23308, "Thanksgiving Box",
                        ThanksgivingBox.common, ThanksgivingBox.uncommon, ThanksgivingBox.rare,
                        new int[]{65, 30, 5});
                break;
            case 10501:
                if (player.getInventory().getAmount(10501) >= 100) {
                    player.getInventory().delete(10501, 100);
                    player.getInventory().add(23376, 1);
                    player.sendMessage("You created a Christmas box!");
                    World.sendMessage("<img=862><col=146b3a>[Christmas]<img=862> <col=1c3c0d>" + player.getUsername() + "<col=bb2528> created a <col=1c3c0d>Christmas box");
                } else {
                    player.sendMessage("You need at least 100 Snowballs to do this.");
                }
                break;
            case 23307:
                if (player.getInventory().getAmount(23307) >= 100) {
                    player.getInventory().delete(23307, 100);
                    player.getInventory().add(23308, 1);
                    player.sendMessage("You created a Thanksgiving box!");
                    World.sendMessage("<img=862>@blu@[Thanksgiving]<img=862> @mag@" + player.getUsername() + "@or2@ created a @red@Thanksgiving box");
                } else {
                    player.sendMessage("You need at least 100 Thanksgiving feathers to do this.");
                }
                break;
            case 23110:
                if (player.getSeasonPass().isMember()) {
                    player.sendMessage("<col=660000>[Season pass]<col=100666>You are already a Season pass gold member.");
                    return;
                }
                if (!player.getInventory().contains(23110))
                    return;


                player.sendMessage("You have claimed a Gold Season Pass!");
                World.sendMessage1("<col=660000>[Season pass]<col=100666>" + player.getUsername() + " has just claimed a Gold Season Pass!");
                player.getInventory().delete(23110, 1);
                player.getSeasonPass().grantMembership();
                break;

            case 23104:
                int tokens = player.getInventory().getAmount(23104);
                player.getInventory().delete(23104, player.getInventory().getAmount(23104));
                player.getInventory().add(12855, tokens * 300);
                player.sendMessage("Your Spooky Kraken tokens have been converted into " + (tokens * 300) + " Upgrade tokens.");
              /*  if (player.isTeleporting())
                    return;
                if (!TeleportHandler.checkReqs(player, new Position(3104, 2913, player.getIndex() * 4))) {
                    player.sendMessage("You cannot do this at the moment.");
                    return;
                }
                if (player.getLocation() == Location.SPOOKY_KRAKEN) {
                    if (player.getRegionInstance() != null && player.getRegionInstance().getNpcsList().size() >= 1) {
                        player.sendMessage("You must kill the spawned Spooky kraken before spawning another.");
                        return;
                    }
                    player.sendMessage("You spawned a Spooky Kraken.");
                    NPC npc = new NPC(9031, new Position(3102, 2917, player.getIndex() * 4)).setSpawnedFor(player);
                    World.register(player, npc);
                } else {
                    TeleportHandler.teleportPlayer(player, new Position(3104, 2913, player.getIndex() * 4), TeleportType.NORMAL);
                    player.sendMessage("You teleport to the Spooky Kraken.");
                }
                player.getInventory().delete(23104, 1);*/

                break;
            case 23002:
                player.getWheelOfFortune().open();
                break;
            case 22108:
                CurrencyPouch.checkBalance(player);
                break;
            case 745:
                if (!player.getClickDelay().elapsed(100)) {
                    player.getPacketSender().sendMessage("Please wait before doing that.");
                    break;
                }

                if (player.getInventory().getFreeSlots() >= 6) {
                    player.getInventory().add(13557, 1);
                    player.getInventory().add(10942, 1);
                    player.getInventory().add(20489, 1);
                    player.getInventory().add(15002, 1);
                    player.getInventory().add(15003, 1);
                    player.getInventory().add(15004, 1);
                    player.getPacketSender().sendMessage("Enjoy your reward!");
                    World.sendMessage("<shad=1>@yel@[<img=18>" + player.getUsername() + "<img=18>] @whi@Killed 10K @red@Valentine's Boxes@whi@ and opened his Heart crystal! Happy Valentine's day!");
                    player.getInventory().delete(745, 1);
                    break;
                } else {
                    player.getPacketSender().sendMessage("You'll need at least 6 free spaces to do that.");
                }

                player.getClickDelay().reset();
                break;
            case 13802:
                if (!player.getClickDelay().elapsed(100)) {
                    player.getPacketSender().sendMessage("Please wait before doing that.");
                    break;
                }

                if (player.getInventory().getFreeSlots() >= 10) {
                    player.getInventory().delete(13802, 1);
                    player.getInventory().add(13800, 1);
                    player.getInventory().add(13801, 1);
                    player.getInventory().add(23660, 1);
                    player.getInventory().add(23661, 1);
                    player.getInventory().add(23662, 5);
                    player.getInventory().add(19114, 10);
                    player.getInventory().add(19115, 25);
                    player.getInventory().add(19116, 50);
                    player.getInventory().add(6769, 1);
                    player.getInventory().add(20489, 1);
                    player.getPacketSender().sendMessage("Enjoy your reward!");
                    break;
                } else {
                    player.getPacketSender().sendMessage("You'll need at least 10 free spaces to do that.");
                }

                player.getClickDelay().reset();
                break;
            case 12856:
                int BTC = player.getInventory().getAmount(12856);
                if (player.getInventory().contains(12856)) {
                    player.getInventory().delete(12856, BTC);
                    player.getInventory().add(12855, BTC * 20000);
                    player.sendMessage("<shad=1>@gre@You have swapped X" + BTC + " BTC for " + BTC * 20000 + " Upgrade Tokens!");
                    break;
                }
            case 12857:
                int Ethereum = player.getInventory().getAmount(12857);
                if (player.getInventory().contains(12857)) {
                    player.getInventory().delete(12857, Ethereum);
                    player.getInventory().add(12855, Ethereum * 2000);
                    player.sendMessage("<shad=1>@gre@You have swapped X" + Ethereum + " Ethereum for " + Ethereum * 2000 + " Upgrade Tokens!");
                    break;
                }
            case 12858:
                int USD = player.getInventory().getAmount(12858);
                if (player.getInventory().contains(12858)) {
                    player.getInventory().delete(12858, USD);
                    player.getInventory().add(12855, USD);
                    player.sendMessage("<shad=1>@gre@You have swapped X" + USD + " USD for " + USD + " Upgrade Tokens!");
                    break;
                }
            case 19000:
                DialogueManager.start(player, PetUpgrading.dialogue(player));
                break;

            case 15355:
                if (player.getDoubleDRTimer() > 0) {
                    player.sendMessage("You already have a double DR scroll active.");
                    return;
                }
                player.getInventory().delete(15355, 1);
                player.setDoubleDRTimer(6000);
                TaskManager.submit(new DoubleDRTask(player));
                break;
            case 15356:
                if (player.getDoubleDDRTimer() > 0) {
                    player.sendMessage("You already have a double DDR scroll active.");
                    return;
                }
                player.getInventory().delete(15356, 1);
                player.setDoubleDDRTimer(6000);
                TaskManager.submit(new DoubleDDRTask(player));
                break;
            case 15357:
                if (player.getDoubleDMGTimer() > 0) {
                    player.sendMessage("You already have a 50% DMG scroll active.");
                    return;
                }
                player.getInventory().delete(15357, 1);
                player.setDoubleDMGTimer(6000);
                TaskManager.submit(new DoubleDMGTask(player));
                break;
            case 15358:
                if (player.getDoubleDRTimer() > 0) {
                    player.sendMessage("You already have a double DR scroll active.");
                    return;
                }
                player.getInventory().delete(15358, 1);
                player.setDoubleDRTimer(3000);
                TaskManager.submit(new DoubleDRTask(player));
                break;
            case 15359:
                if (player.getDoubleDMGTimer() > 0) {
                    player.sendMessage("You already have a 50% DMG scroll active.");
                    return;
                }
                player.getInventory().delete(15359, 1);
                player.setDoubleDMGTimer(3000);
                TaskManager.submit(new DoubleDMGTask(player));
                break;


            case 23491:
                if (!player.getImbuedHeartCooldown().elapsed(300_000)) {
                    player.sendMessage("There is a 5 minute cooldown before using this. (" + Misc.getMinsLeft(300_000 - player.getImbuedHeartCooldown().elapsed()) + " left)");
                    return;
                }
                if (player.getRangerHeartTimer() > 0 || player.getWarriorHeartTimer() > 0
                        || player.getSorcererHeartTimer() > 0 || player.getGodsHeartTimer() > 0) {
                    player.sendMessage("You already have a heart active.");
                    return;
                }
                player.performGraphic(new Graphic(1586));
                player.setWarriorHeartTimer(1000);
                player.sendMessage("You activated the Heart of the Warrior (x1.25 Melee damage against slayer task)");
                TaskManager.submit(new WarriorHeartTask(player));
                break;
            case 23492:
                if (!player.getImbuedHeartCooldown().elapsed(300_000)) {
                    player.sendMessage("There is a 5 minute cooldown before using this. (" + Misc.getMinsLeft(300_000 - player.getImbuedHeartCooldown().elapsed()) + " left)");
                    return;
                }
                if (player.getRangerHeartTimer() > 0 || player.getWarriorHeartTimer() > 0
                        || player.getSorcererHeartTimer() > 0 || player.getGodsHeartTimer() > 0) {
                    player.sendMessage("You already have a heart active.");
                    return;
                }
                player.performGraphic(new Graphic(1586));
                player.setRangerHeartTimer(1000);
                player.sendMessage("You activated the Heart of the Ranger (x1.25 Ranged damage against slayer task)");
                TaskManager.submit(new RangerHeartTask(player));
                break;
            case 23493:
                if (!player.getImbuedHeartCooldown().elapsed(300_000)) {
                    player.sendMessage("There is a 5 minute cooldown before using this. (" + Misc.getMinsLeft(300_000 - player.getImbuedHeartCooldown().elapsed()) + " left)");
                    return;
                }
                if (player.getRangerHeartTimer() > 0 || player.getWarriorHeartTimer() > 0
                        || player.getSorcererHeartTimer() > 0 || player.getGodsHeartTimer() > 0) {
                    player.sendMessage("You already have a heart active.");
                    return;
                }
                player.performGraphic(new Graphic(1586));
                player.setSorcererHeartTimer(1000);
                player.sendMessage("You activated the Heart of the Sorcerer (x1.25 Magic damage against slayer task)");
                TaskManager.submit(new SorcererHeartTask(player));
                break;
            case 23494:
                if (!player.getImbuedHeartCooldown().elapsed(300_000)) {
                    player.sendMessage("There is a 5 minute cooldown before using this. (" + Misc.getMinsLeft(300_000 - player.getImbuedHeartCooldown().elapsed()) + " left)");
                    return;
                }
                if (player.getRangerHeartTimer() > 0 || player.getWarriorHeartTimer() > 0
                        || player.getSorcererHeartTimer() > 0 || player.getGodsHeartTimer() > 0) {
                    player.sendMessage("You already have a heart active.");
                    return;
                }
                player.performGraphic(new Graphic(1586));
                player.setGodsHeartTimer(1000);
                player.sendMessage("You activated the Heart of the Gods (x1.4 Overall damage against slayer task)");
                TaskManager.submit(new GodsHeartTask(player));
                break;

            case 15002:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SEPTEMBER);
                player.getCasketOpening().openInterface();
                break;
            case 15003:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SEPTEMBER2);
                player.getCasketOpening().openInterface();
                break;
            case 15004:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SEPTEMBER3);
                player.getCasketOpening().openInterface();
                break;
            case 23708:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SPRING);
                player.getCasketOpening().openInterface();
                break;
            case 23826:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SUMMER);
                player.getCasketOpening().openInterface();
                break;
            case 23882:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.AUTUMN);
                player.getCasketOpening().openInterface();
                break;
            case 23782:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.DIVINE_CASE);
                player.getCasketOpening().openInterface();
                break;
            case 23776:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.ANGELIC_CASE);
                player.getCasketOpening().openInterface();
                break;
            case 23812:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.MONEY_CASE);
                player.getCasketOpening().openInterface();
                break;
            case 11027:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.EASTER_EGG);
                player.getCasketOpening().openInterface();
                break;
            case 23210:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SEPTEMBER4);
                player.getCasketOpening().openInterface();
                break;
            case 23314:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.ZENYTE);
                player.getCasketOpening().openInterface();
                break;
            case 18404:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.RAIDS2);
                player.getCasketOpening().openInterface();
                break;
            case 11858:
            case 11860:
            case 11862:
            case 11848:
            case 11856:
            case 11850:
            case 11854:
            case 11852:
            case 11846:
            case 19580:
                if (!player.getClickDelay().elapsed(250) || !player.getInventory().contains(itemId))
                    return;
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You cannot open this right now.");
                    return;
                }

                int[] items = itemId == 11858 ? new int[]{10350, 10348, 10346, 10352}
                        : itemId == 19580 ? new int[]{19308, 19311, 19314, 19317, 19320}
                        : itemId == 11860 ? new int[]{10334, 10330, 10332, 10336}
                        : itemId == 11862 ? new int[]{10342, 10338, 10340, 10344}
                        : itemId == 11848 ? new int[]{4716, 4720, 4722, 4718}
                        : itemId == 11856 ? new int[]{4753, 4757, 4759, 4755}
                        : itemId == 11850 ? new int[]{4724, 4728, 4730, 4726}
                        : itemId == 11854
                        ? new int[]{4745, 4749, 4751, 4747}
                        : itemId == 11852
                        ? new int[]{4732, 4734, 4736,
                        4738}
                        : itemId == 11846
                        ? new int[]{4708, 4712,
                        4714, 4710}
                        : new int[]{itemId};

                if (player.getInventory().getFreeSlots() < items.length) {
                    player.getPacketSender().sendMessage("You do not have enough space in your inventory.");
                    return;
                }
                player.getInventory().delete(itemId, 1);
                for (int i : items) {
                    player.getInventory().add(i, 1);
                }
                player.getPacketSender().sendMessage("You open the set and find items inside.");
                player.getClickDelay().reset();
                break;
            case 989:
                if (player.getLocation() != null && player.getLocation() == Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You cannot do this at the moment.");
                    return;
                }
                Position position = new Position(2647, 4019, 0);
                TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
                break;
            case 5021:

                MoneyPouch.depositTickets(player, player.getInventory().getAmount(5021));


               /* int amount1 = player.getInventory().getAmount(5021);
                if (amount1 > 2147 || amount1 + player.getInventory().getAmount(995) > 2147000000) {
                    long amountLeft;
                    if (!player.getInventory().contains(995))
                        amountLeft = (long) (((long) amount1 * (long) 1000000) - (long) 2147000000);
                    else
                        amountLeft = ((long) amount1 * (long) 1000000) - (long) (2147000000 - player.getInventory().getAmount(995));
                    player.getInventory().delete(5021, amount1);
                    player.getInventory().add(995, 2147000000 - (player.getInventory().getAmount(995)));
                    player.setMoneyInPouch(player.getMoneyInPouch() + amountLeft);
                    player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
                    player.sendMessage("<shad=1>@red@The rest of the cash(" + amountLeft / 1000000
                            + "M) has been added to your @blu@pouch@red@!");
                    return;
                }
                player.getInventory().delete(5021, amount1);
                player.getInventory().add(995, 1000000 * amount1);*/

                break;
            case 21816:
                ShopManager.getShops().get(111).open(player);
                break;
            case 21815:
                ShopManager.getShops().get(112).open(player);
                break;
            case 21814:
                ShopManager.getShops().get(113).open(player);
                break;
            case 15328:
                player.getDailyTaskManager().submitProgressToIdentifier(20, 1);
                if (!drinkInfinityRage(player, slot, 15328))
                    return;
                player.getPacketSender().sendInterfaceRemoval();
                player.getCombatBuilder().incrementAttackTimer(1).cooldown(false);
                player.getCombatBuilder().setDistanceSession(null);
                player.setCastSpell(null);
                player.getFoodTimer().reset();
                player.getPotionTimer().reset();
                player.setOverloadPotionTimer(100000);
                if (player.getOverloadPotionTimer() > 0) { // Prevents decreasing stats
                    Consumables.overloadIncrease(player, Skill.ATTACK, 0.67);
                    Consumables.overloadIncrease(player, Skill.STRENGTH, 0.67);
                    Consumables.overloadIncrease(player, Skill.DEFENCE, 0.67);
                    Consumables.overloadIncrease(player, Skill.RANGED, 0.67);
                    Consumables.overloadIncrease(player, Skill.MAGIC, 0.67);
                }
                Sounds.sendSound(player, Sound.DRINK_POTION);
                break;

            case 23374:
                player.getDailyTaskManager().submitProgressToIdentifier(20, 1);
                if (!drinkOwnerPotion(player, slot, 23374))
                    return;
                player.getPacketSender().sendInterfaceRemoval();
                player.getCombatBuilder().incrementAttackTimer(1).cooldown(false);
                player.getCombatBuilder().setDistanceSession(null);
                player.setCastSpell(null);
                player.getFoodTimer().reset();
                player.getPotionTimer().reset();
                player.setOverloadPotionTimer(100000);
                if (player.getOverloadPotionTimer() > 0) { // Prevents decreasing stats
                    Consumables.overloadIncrease(player, Skill.ATTACK, 1);
                    Consumables.overloadIncrease(player, Skill.STRENGTH, 1);
                    Consumables.overloadIncrease(player, Skill.DEFENCE, 1);
                    Consumables.overloadIncrease(player, Skill.RANGED, 1);
                    Consumables.overloadIncrease(player, Skill.MAGIC, 1);
                }
                Sounds.sendSound(player, Sound.DRINK_POTION);
                break;

            case 23779:
                player.getDailyTaskManager().submitProgressToIdentifier(20, 1);
                if (!drinkAngelicPotion(player, slot, 23779))
                    return;
                player.getPacketSender().sendInterfaceRemoval();
                player.getCombatBuilder().incrementAttackTimer(1).cooldown(false);
                player.getCombatBuilder().setDistanceSession(null);
                player.setCastSpell(null);
                player.getFoodTimer().reset();
                player.getPotionTimer().reset();
                player.setOverloadPotionTimer(100000);
                if (player.getOverloadPotionTimer() > 0) { // Prevents decreasing stats
                    Consumables.overloadIncrease(player, Skill.ATTACK, 1.34);
                    Consumables.overloadIncrease(player, Skill.STRENGTH, 1.34);
                    Consumables.overloadIncrease(player, Skill.DEFENCE, 1.34);
                    Consumables.overloadIncrease(player, Skill.RANGED, 1.34);
                    Consumables.overloadIncrease(player, Skill.MAGIC, 1.34);
                }
                Sounds.sendSound(player, Sound.DRINK_POTION);
                break;

            case 15330:
                player.getDailyTaskManager().submitProgressToIdentifier(20, 1);

                if (!drinkSuperOverload(player, slot, 15330))

                    return;
                player.getPacketSender().sendInterfaceRemoval();
                player.getCombatBuilder().incrementAttackTimer(1).cooldown(false);
                player.getCombatBuilder().setDistanceSession(null);
                player.setCastSpell(null);
                player.getFoodTimer().reset();
                player.getPotionTimer().reset();
                player.setOverloadPotionTimer(100000);
                if (player.getOverloadPotionTimer() > 0) { // Prevents decreasing stats
                    Consumables.overloadIncrease(player, Skill.ATTACK, 0.38);
                    Consumables.overloadIncrease(player, Skill.STRENGTH, 0.38);
                    Consumables.overloadIncrease(player, Skill.DEFENCE, 0.38);
                    Consumables.overloadIncrease(player, Skill.RANGED, 0.38);
                    Consumables.overloadIncrease(player, Skill.MAGIC, 0.38);
                }
                Sounds.sendSound(player, Sound.DRINK_POTION);
                break;
            case 12855:
                player.getUpgradeListener().openTab();
                break;
            case 21218:
                player.getInventory().delete(21218, 1);
                int num1 = 60000 / Difficulty.getDifficultyModifier(player, Skill.INVENTION);
                player.getSkillManager().addExperience(Skill.INVENTION, num1);
                player.getPacketSender().sendMessage("You've Been rewarded with some Invention XP.");

                break;
            case 21219:
                player.getInventory().delete(21219, 1);
                int num = 90000 / Difficulty.getDifficultyModifier(player, Skill.SLAYER);
                player.getSkillManager().addExperience(Skill.SLAYER, num);
                player.getPacketSender().sendMessage("You've Been rewarded with some slayer XP.");

                break;
            case 19001:
                if (player.getInventory().contains(19001))
                    player.getInventory().delete(19001, 1).add(19000, 250);
                break;
            case 15290:
                if (player.getInventory().contains(15290))
                    player.getInventory().delete(15290, 1).add(12855, 5000);
                break;
            case 15289:
                if (player.getInventory().contains(15289))
                    player.getInventory().delete(15289, 1).add(12855, 25000);
                break;
            case 15288:
                if (player.getInventory().contains(15288))
                    player.getInventory().delete(15288, 1).add(12855, 100000);
                break;

            case 23514:
                if (player.getInventory().contains(23514))
                    player.getInventory().delete(23514, 1).add(5022, 1000);
                break;
            case 23515:
                if (player.getInventory().contains(23515))
                    player.getInventory().delete(23515, 1).add(5022, 2500);
                break;
            case 23516:
                if (player.getInventory().contains(23516))
                    player.getInventory().delete(23516, 1).add(5022, 10000);
                break;

            case 14822:
                player.getPacketSender().sendMessage("You are now licenced with VIP slayer");
                player.getPacketSender().sendMessage("Here is your Slayer pass to our exclusive VIP SLAYER ZONE.");
                player.getInventory().delete(14822, 1);
                player.getInventory().add(27, 1);
                break;
            case 27:
                player.getSlayer().handleSlayerRingTP(itemId);
                break;
            case 14819:
                if (player.getSlayer().doubleSlayerXP) {
                    player.getPacketSender().sendMessage("You already have Double Slayer Points.");
                    return;
                }
                player.getInventory().delete(14819, 1);
                // player.getPointsHandler().setSlayerPoints(-300, true);
                player.getSlayer().doubleSlayerXP = true;
                PlayerPanel.refreshPanel(player);
                player.getPacketSender().sendMessage("You will now permanently receive double Slayer experience.");
                break;
            case 19775:
                player.getPointsHandler().incrementGlobalRate(5);

                player.sendMessage("Your Event rate is now: " + player.getPointsHandler().getGlobalRate() + " / 100%");
                player.getInventory().delete(19775, 1);
                player.getInventory().add(8212, 50);
                player.sendMessage("you were rewarded free shards");
                break;
            case 19768:
                player.getPointsHandler().incrementGlobalRate(10);
                player.getInventory().add(8212, 100);

                player.sendMessage("Your Event rate is now: " + player.getPointsHandler().getGlobalRate() + " / 100%");
                player.sendMessage("you were rewarded free shards");

                player.getInventory().delete(19768, 1);
                break;
            case 10835:

                player.getInventory().delete(10835, 1);
                // player.getInventory().add(995, 1000000000);
                AchievementsOLD.finishAchievement(player, AchievementDataOLD.SET_UP_A_CANNON);
                break;
            case 17544:
                player.getInventory().delete(17544, 1);
                player.getInventory().add(12855, 30000);
                player.sendMessage("<shad=1>@yel@You swapped your Supreme potion to 30K Tokens!");
                break;
            case 17546:
                player.getInventory().delete(17546, 1);
                player.getInventory().add(12855, 125000);
                player.sendMessage("<shad=1>@yel@You swapped your God potion to 125K Tokens!");
                break;
            case 757:
                player.getStarterTasks().openInterface();
                break;
           /* case 455:
                player.getScratchCard().open();
                break;*/
            case 22121:
                if (player.getLocation() != Location.HOME_BANK) {
                    player.sendMessage("<shad=1>@red@You have to be in ::home to scratch this card!");
                    player.sendMessage("<shad=1>@red@You have to be in ::home to scratch this card!");
                    return;
                }
                player.getScratchcard().open();
                player.getInventory().delete(22121, 1);


                break;
            case 4278:
            case 23211:
                if (player.getLocation() == Location.JAIL) {
                    player.sendMessage("<shad=1>@cya@You can't start an instance while your in jail.");
                    return;
                }
                if (player.currentInstanceAmount >= 1) {
                    player.sendMessage("<shad=1>@red@You can't start a new instance until this one ends");
                    return;
                }
                if (!player.getLastRunRecovery().elapsed(5000)) {
                    player.sendMessage("Please wait 5 seconds before doing this again.");
                    return;
                }
                player.getInstanceManager().ticketID = itemId;
                new InstanceInterfaceHandler(player).open();
                break;

            case 2150:
                player.getInventory().delete(2150, 1);
                player.getInventory().add(2152, 1);
                player.getPacketSender().sendMessage("You remove the Toad's legs.");
                break;
            case 15752:
                player.getInventory().delete(15752, 1);
                Position position55 = new Position(2694, 5109, 0);
                TeleportHandler.teleportPlayer(player, position55, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("You will now be facing @red@Darth Vader@bla@!");
                break;
            case 23464:
                TeleportHandler.teleportPlayer(player, new Position(1735, 5237, 0), TeleportType.NORMAL);
                break;
            case 23859:
                TeleportHandler.teleportPlayer(player, new Position(3253, 2929, 0), TeleportType.NORMAL);
                break;

            case 15750:
                player.getInventory().delete(15750, 1);
                Position position1 = new Position(2868, 3590, 0);
                TeleportHandler.teleportPlayer(player, position1, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("You will now be facing @blu@Luke Skywalker@bla@!");
                break;
            case 15751:
                player.getInventory().delete(15751, 1);
                Position position2 = new Position(2912, 3611, 0);
                TeleportHandler.teleportPlayer(player, position2, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("You will now be facing @gre@Yoda@bla@!");
                break;
            case 16:
                player.getInventory().delete(16, 1);
                Position position3 = new Position(2907, 3289, 0);
                TeleportHandler.teleportPlayer(player, position3, TeleportType.NORMAL);
                player.getPacketSender().sendMessage("You Blew on the whistle and the King summons you!");
                break;

            case 6833:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().setRewards(new int[]{6199, 6199, 6199, 19116, 10946, 15290, 16045, 15785, 19331,
                        18686, 15501, 989, 962, 3318, 3907, 11137, 4151, 12790, 15332, 7956});
                player.getGoodieBag().open();
                break;
            case 3578:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().setRewards(new int[]{10934, 10934, 10934, 7995, 10934, 10934, 10934,
                        10934, 10934, 10934, 10934, 10934, 10934, 10934, 10934, 10934, 10934, 10934, 7995, 10934});
                player.getGoodieBag().open();
                break;
            case 23200:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().rewards = new Item[]{
                        new Item(15004), new Item(10942), new Item(23002), new Item(23210),
                        new Item(10934), new Item(3578), new Item(10934), new Item(10935), new Item(23204),
                        new Item(23110), new Item(22121), new Item(22121), new Item(7995), new Item(10943),
                        new Item(9084), new Item(12630), new Item(9083), new Item(20591), new Item(15002, 2), new Item(23314)};
                player.getGoodieBag().open();
                break;
            case 23201:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().setRewards(new int[]{
                        4446, 19886, 10935, 15330, 15004,
                        1486, 11314, 8087, 8088, 8089,
                        23002, 20489, 20489, 10934, 10942,
                        15004, 15003, 15002, 10943, 22121});
                player.getGoodieBag().open();
                break;
            case 23202:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().setRewards(new int[]{
                        9084, 22110, 20591, 12630, 7995,
                        15004, 22121, 22121, 10943, 10943,
                        10935, 10935, 10935, 10934, 10934,
                        23210, 23210, 3578, 4440, 4442});
                player.getGoodieBag().open();
                break;
            case 23204:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().setRewards(new int[]{
                        23351, 23352, 23353, 3578, 10934,
                        10934, 10934, 15004, 15004, 23210,
                        10934, 10934, 10934, 10934, 10934,
                        10934, 10934, 10934, 10934, 10934,});
                player.getGoodieBag().open();
                break;

            case 23205:
                player.getGoodieBag().boxId = itemId;
                player.getGoodieBag().rewards = new Item[]{
                        new Item(15002, 2), new Item(7995), new Item(23204, 2), new Item(23204), new Item(3578, 1),
                        new Item(22121, 1), new Item(12630), new Item(4440), new Item(4442), new Item(23002, 2),
                        new Item(15004, 2), new Item(23780, 10), new Item(23314), new Item(23780, 25), new Item(23210, 1),
                        new Item(23110), new Item(23110), new Item(10943), new Item(10935), new Item(10934, 3),
                };
                player.getGoodieBag().open();
                break;

            case 7510:
                long plc = player.getConstitution();
                long plcr = 50;

                if (plc <= 50) {
                    plcr = plc - 1;
                }
                if (plc == 1) {
                    plcr = 0;
                }

                if (plcr > 0) {
                    player.performAnimation(new Animation(829));
                    Hit h = new Hit(plcr);
                    player.dealDamage(h);
                    player.forceChat(Misc.randomElement(ROCK_CAKE));
                } else {
                    player.getPacketSender().sendMessage("You'll die if you keep eating this putrid rock!");
                }
                break;
            case 22051:
                if (!player.busy()) {
                    easter2017.openInterface(player);
                } else {
                    player.getPacketSender().sendMessage("You're too busy to do that!");
                }
                break;
            case 9003:
                player.getPacketSender().sendMessage(
                        "<img=5> \"Use\" this Tome on any NPC, then select \"View-drops\" to see it's entire drop table.");
                break;
            case 2946:
                if (!player.getClickDelay().elapsed(1000)) {
                    player.getPacketSender().sendMessage("Please wait 1 second before doing that.");
                    return;
                }
                if (player.getInventory().getFreeSlots() < 4) {
                    player.getPacketSender().sendMessage("You should have at least 4 inventory spaces free.");
                    return;
                }

                player.getInventory().delete(2946, 1);
                player.getInventory().add(7329, 1);
                player.getInventory().add(7330, 1);
                player.getInventory().add(7331, 1);
                player.getInventory().add(10326, 1);
                player.getInventory().add(10327, 1);
                player.getPacketSender().sendMessage("<shad=0>@red@Enjoy your firelighters!");
                player.getClickDelay().reset();
                break;
            case 15367:
                if (!player.getClickDelay().elapsed(1000)) {
                    player.getPacketSender().sendMessage("Please wait 1 second before doing that.");
                    return;
                }
                if (player.getInventory().getFreeSlots() < 11) {
                    player.getPacketSender().sendMessage("You should have at least 11 inventory spaces free.");
                    return;
                }

                player.getInventory().delete(15367, 1);
                player.getInventory().add(2396, 1);
                player.getInventory().add(10593, 1);
                player.getInventory().add(2862, 1);
                player.getInventory().add(19963, 1);
                player.getInventory().add(6851, 5);
                player.getInventory().add(6847, 5);
                player.getInventory().add(6850, 5);
                player.getInventory().add(6849, 5);
                player.getInventory().add(8212, 300);
                player.getInventory().add(8213, 50);
                player.getPacketSender().sendMessage("<shad=0>@red@Enjoy your Christmas pack!");
                World.sendMessage("<img=17>@blu@[Christmas Pack]<img=17>@red@ " + player.getUsername()
                        + " Has just opened a Christmas ingredient pack! ");

                player.getClickDelay().reset();
                break;
            case 3904:
                if (!player.getClickDelay().elapsed(1000)) {
                    player.getPacketSender().sendMessage("Please wait 1 second before doing that.");
                    return;
                }
                if (player.getInventory().getFreeSlots() < 6) {
                    player.getPacketSender().sendMessage("You should have at least 6 inventory spaces free.");
                    return;
                }

                player.getInventory().delete(3904, 1);
                player.getInventory().add(20054, 1);
                player.getInventory().add(20061, 1);
                player.getInventory().add(18365, 1);
                player.getInventory().add(16879, 1);
                player.getInventory().add(13641, 1);
                player.getInventory().add(6199, 1);

                player.getPacketSender().sendMessage("You have claimed your starter gear!");
                player.getClickDelay().reset();
                break;
            case 20061:
                if (!player.getClickDelay().elapsed(5000)) {
                    player.getPacketSender().sendMessage("Please wait 5 seconds before doing that.");
                    return;
                }
                if (player.getInventory().getFreeSlots() < 3) {
                    player.getPacketSender().sendMessage("You should have at least 3 inventory spaces free.");
                    return;
                }

                player.getInventory().add(23020, 1);
                player.getInventory().add(290, 1);
                player.getInventory().add(6198, 1);
                player.getInventory().delete(20061, 1);
                player.getPacketSender().sendMessage("@red@Enjoy a free voting scroll - ::vote for more.");

                player.getClickDelay().reset();
                break;

            case 1561:

                // player.getPacketSender().sendMessage("hi");
                if (!player.getClickDelay().elapsed(10000)) {
                    player.getPacketSender().sendMessage("Please wait 10 seconds before doing that.");
                    return;
                }
                player.getInventory().delete(1561, 1);
                int invSpace = player.getInventory().getFreeSlots();
                int daCount = 0;
                for (int i = 0; i < BossPet.values().length; i++) {
                    // // System.out.println("daCount < invSpace "+(boolean) (daCount < invSpace));
                    // // System.out.println("getBossPet("+i+"),
                    // "+ItemDefinition.forId(BossPet.values()[i].itemId).getName()+" =
                    // "+player.getBossPet(i));
                    if (daCount < invSpace && player.getBossPet(i)) {
                        player.getInventory().add(BossPet.values()[i].itemId, 1);
                        player.getPacketSender().sendMessage("Returned your "
                                + ItemDefinition.forId(BossPet.values()[i].itemId).getName() + " to your inventory.");
                        daCount++;
                    } else if (daCount >= invSpace && player.getBossPet(i) && !player.getBank(0).isFull()) {
                        player.getBank(0).add(BossPet.values()[i].itemId, 1);
                        player.getPacketSender().sendMessage("Returned your "
                                + ItemDefinition.forId(BossPet.values()[i].itemId).getName() + " to your bank.");
                        daCount++;
                    }
                }
                player.getDailyTaskManager().submitProgressToIdentifier(8, 1);
                player.getClickDelay().reset();
                break;

            case 4837:
                player.getPacketSender()
                        .sendMessage("<col=0><shad=ffffff>This tomb has an unmistakable dark energy to it.");
                player.getPacketSender().sendMessage("The Devil's Notebook - by Anton LaVey, Dark Wizard.");
                break;
            case 2424:
                player.getInventory().delete(new Item(2424, 1));
                player.forceChat("It puts the lotion on it's skin...");
                player.performAnimation(new Animation(860));
                player.getInventory().add(new Item(3057, 1));
                break;
            case 8013:
            case 8012:
            case 8011:
            case 8010:
            case 8009:
            case 8008:
            case 8007:
            case 13599:
                // case 13600:
            case 13601:
            case 13602:
            case 13603:
            case 13604:
            case 13605:
            case 13606:
            case 13611:
            case 13607:
            case 13608:
            case 13609:
            case 13610:
                TeleportTabs.teleportTabs(player, itemId);
                break;
            case 2724:
                CLUESCROLL.openCasket(player);
                break;
            case 13663:
                if (player.getInterfaceId() > 0) {
                    player.getPacketSender().sendMessage("Please close the interface you have open before doing this.");
                    return;
                }
                player.setUsableObject(new Object[2]).setUsableObject(0, "reset");
                player.getPacketSender().sendString(38006, "Choose stat to reset!")
                        .sendMessage("@red@Please select a skill you wish to reset and then click on the 'Confim' button.")
                        .sendString(38090, "Which skill would you like to reset?");
                player.getPacketSender().sendInterface(38000);
                break;
            case 19670:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                // StarterTasks.finishTask(player, StarterTaskData.READ_THE_GUIDE_BOOK);
                VoteRewardHandler.voteRewards(player, false);

                break;
            case 23020:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                if (!player.getInventory().contains(23020) || player.getInventory().getAmount(23020) < 1) {
                    return;
                }
                amt = 1;
                int minutesEXP = 15 * amt;
                int minutesDR = 5 * amt;
                int minutesDMG = 2 * amt;

                player.getSeasonPass().addExperience(3 * amt);
                StarterTasks.doProgress(player, StarterTasks.StarterTask.CLAIM_VOTES, amt);

                player.getDailyTaskManager().submitProgressToIdentifier(1, amt);
                player.getInventory().delete(23020, amt);
                player.getInventory().add(12855, 1000 * amt);
                player.getInventory().add(5022, 1000 * amt);
                player.setMoneyInPouch(player.getMoneyInPouch() + (amt * 100_000_000));
                player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
                player.getPacketSender()
                        .sendMessage("@blu@You are rewarded " + (amt * 5) + " vote "
                                + (amt > 1 ? "points, " : "point, ") + (1000 * amt) + " Upgrade Tokens, and " + (1000 * amt) + " PVM Tickets!");
                player.getPacketSender()
                        .sendMessage("@blu@You received " + minutesEXP + " mins of Bonus XP, " + minutesDR + " mins of +100 DR, " + minutesDMG + " mins of +100% DMG!");
                player.getPointsHandler().incrementVotingPoints(amt * 5);
                BonusExperienceTask.addBonusXp(player, minutesEXP);
                VotingDRBoostTask.addBonusDR(player, minutesDR);
                VotingDMGBoostTask.addBonusDMG(player, minutesDMG);
                player.getClickDelay().reset();
                break;
            case 10138:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                VoteRewardHandler.AFKFISH(player, false);
                break;
            case 17634:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                VoteRewardHandler.AFKMINE(player, false);
                break;
            case 9013:
                String[] messages = {"BOO!", "SURPRISE!", "SPOOOKED YA", "Ooooh!", "Spoooooooookyy",
                        "ooooOOoooOOOooOOOOooo", "I'm a ghost!", "I ain't afraid of no ghost",
                        "If there's something strange in your neighborhood...", "Who you gonna call?", "GHOST BUSTERS!",// KEEP
                        // A
                        // COMMA
                        // ON
                        // THE
                        // LAST
                        // ONE!
                };
                int max = messages.length;

                // // System.out.println("Count: " + count + " | Max: " + max);
                player.forceChat(messages[count]);
                player.performAnimation(new Animation(2836));
                player.performGraphic(new Graphic(293));
                count++;

                if (count == max) {
                    // // System.out.println("Resetting count to 0");
                    count = 0;
                }
                break;
            case 20692:
                player.performGraphic(new Graphic(199));
                player.getInventory().delete(20692, 1);
                break;
            case 290:
                player.getInventory().delete(290, 1);
                int[] rewards = {200, 202, 204, 206, 208, 210, 212, 214, 216, 218, 220, 2486, 3052, 1624, 1622, 1620, 1618,
                        1632, 1516, 1514, 454, 448, 450, 452, 378, 372, 7945, 384, 390, 15271, 533, 535, 537, 18830, 556,
                        558, 555, 554, 557, 559, 564, 562, 566, 9075, 563, 561, 560, 565, 888, 890, 892, 11212, 9142, 9143,
                        9144, 9341, 9244, 866, 867, 868, 2, 10589, 10564, 6809, 4131, 15126, 4153, 1704, 1149};
                int[] rewardsAmount = {50, 50, 50, 30, 20, 30, 30, 30, 30, 20, 10, 5, 4, 70, 40, 25, 10, 10, 100, 50, 100,
                        80, 25, 25, 250, 200, 125, 50, 30, 25, 50, 20, 20, 5, 500, 500, 500, 500, 500, 500, 500, 500, 200,
                        200, 200, 200, 200, 200, 1000, 750, 200, 100, 1200, 1200, 120, 50, 20, 1000, 500, 100, 100, 1, 1, 1,
                        1, 1, 1, 1, 1};
                int rewardPos = Misc.getRandom(rewards.length - 1);
                player.getInventory().add(rewards[rewardPos],
                        (int) ((rewardsAmount[rewardPos] * 0.5) + (Misc.getRandom(rewardsAmount[rewardPos]))));
                break;
            case 407:
                player.getInventory().delete(407, 1);
                if (Misc.getRandom(3) < 3) {
                    player.getInventory().add(409, 1);
                } else if (Misc.getRandom(4) < 4) {
                    player.getInventory().add(411, 1);
                } else
                    player.getInventory().add(413, 1);
                break;
            case 405:
                player.getInventory().delete(405, 1);
                if (Misc.getRandom(1) < 1) {
                    int coins = Misc.getRandom(30000);
                    //  player.getInventory().add(995, coins);
                    player.getPacketSender().sendMessage(
                            "The casket contained " + NumberFormat.getInstance(Locale.US).format(coins) + " coins!");
                } else
                    player.getPacketSender().sendMessage("The casket was empty.");
                break;
            case 2714:
                int amount = Misc.getRandom(100000);
                player.getInventory().delete(2714, 1);
                // player.getInventory().add(995, amount);
                player.getPacketSender().sendMessage(
                        "Inside the casket you find " + NumberFormat.getInstance(Locale.US).format(amount) + " coins!");
                break;
            case 15084:
                if (player.getInventory().contains(15084)) {
                    player.getInventory().delete(15084, 1);
                    player.getPointsHandler().setMemberPoints(75, true);
                    player.sendMessage("@red@Dicing has been removed until we add a different implementation!");
                    return;
                }
                if (player.isGambleBanned()) {
                    player.sendMessage("You are gamble banned and cannot do this.");
                    return;
                }
                Gambling.rollDice(player);
                break;

            case 15098:
                Gambling.ScamrollDice(player);
                break;
            case 995:
                MoneyPouch.depositMoney(player, player.getInventory().getAmount(995));
                break;
            case 299:
                Gambling.plantSeed(player);
                break;
            case 15103:
                player.getPacketSender().sendMessage("This came from a Goblin. Kill Nex, or Zulrah for another.");
                break;
            case 15104:
                player.getPacketSender().sendMessage("This came from Nex or Zulrah. Kill Monkey Skeletons for another.");
                break;
            case 15105:
                player.getPacketSender().sendMessage("This came from a Monkey Skeleton. Kill the KBD for another.");
                break;
            case 15106:
                player.getPacketSender().sendMessage("This came from the KBD, kill Goblins for another.");
                break;
            /*
             * player.getPacketSender().
             * sendMessage("What lies in the <shad=f999f7>Antiqua Carcere<shad=-1>?");
             * player.getPacketSender().
             * sendMessage("The <shad=b40404>Nbmfgjdvt<shad=-1> may hold next piece.");
             * player.getPacketSender().sendMessage("The..."); player.getPacketSender().
             * sendMessage("<shad=ffffff>01010101 01101110 01100111");
             * player.getPacketSender().
             * sendMessage("<shad=ffffff>01110101 01101001 01110011 ");
             * player.getPacketSender().sendMessage("holds the next piece.");
             * player.getPacketSender().sendMessage("The..."); player.getPacketSender().
             * sendMessage("<shad=000000>53 61 67 69 74 74 61 72 69 69 73");
             * player.getPacketSender().sendMessage("holds the next piece."); break;
             */
            case 4155:
                if (player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK) {
                    player.getPacketSender().sendInterfaceRemoval();
                    player.getPacketSender().sendMessage("You do not have a Slayer task.");
                    player.getSlayerFavourites().open();
                    return;
                }
                DialogueManager.start(player, 19999);
                player.setDialogueActionId(19999);
                break;
            case 18719: // potion of flight
                if (player.canFly()) {
                    player.getPacketSender().sendMessage("You already know how to fly.");
                } else {
                    player.getInventory().delete(18719, 1);
                    player.getPacketSender().sendMessage("Your mind is filled with the secrets of flight!")
                            .sendMessage("Use ::fly to toggle flight on and off.");
                    player.setCanFly(true);
                    player.setFlying(true);
                    player.newStance();
                }
                break;
            case 7587:
                if (player.canGhostWalk()) {
                    player.getPacketSender().sendMessage("You already know how to ghost walk.");
                } else {
                    player.getInventory().delete(7587, 1);
                    player.getPacketSender().sendMessage("Your mind is filled with the secrets of death!")
                            .sendMessage("Use ::ghostwalk to toggle it on and off.");
                    player.setCanGhostWalk(true);
                    player.setGhostWalking(true);
                    player.newStance();
                }
                break;
            case 952:
                Digging.dig(player);
                break;
            case 11261:
            case 1748:
            case 1750:
            case 1752:
            case 1754:
            case 228:
                int uimint[] = {1748, 1750, 1752, 1754, 228};
                for (int i = 0; i < uimint.length; i++) {
                    if (uimint[i] == itemId && !player.getGameMode().equals(GameMode.ULTIMATE_IRONMAN)) {
                        player.getPacketSender().sendMessage("Only Ultimate Ironman characters can do that.");
                        return;
                    }
                }
                if (!player.getClickDelay().elapsed(100)) {
                    return;
                }
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You're too busy to un-note that.");
                    break;
                }
                if (player.getInventory().isFull()) {
                    player.getPacketSender().sendMessage("You need a free inventory space to un-note that.");
                    break;
                }
                String orig = ItemDefinition.forId(itemId).getName(), cur = ItemDefinition.forId(itemId - 1).getName();
                if (!orig.equalsIgnoreCase(cur)) {
                    player.getPacketSender().sendMessage("Error 21641: a = " + itemId + " &-1");
                    break;
                }
                player.getClickDelay().reset();
                int b = player.getInventory().getAmount(itemId);
                player.getInventory().delete(itemId, 1);
                if (player.getInventory().getAmount(itemId) == (b - 1)) {
                    player.getInventory().add((itemId - 1), 1);
                } else {
                    player.getPacketSender()
                            .sendMessage("Error 41265: b = " + b + ", c = " + player.getInventory().getAmount(itemId));
                    break;
                }
                break;
            case 10006:
                // Hunter.getInstance().laySnare(client);
                Hunter.layTrap(player, new SnareTrap(new GameObject(19175, new Position(player.getPosition().getX(),
                        player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
                break;
            case 10008:
                Hunter.layTrap(player, new BoxTrap(new GameObject(19187, new Position(player.getPosition().getX(),
                        player.getPosition().getY(), player.getPosition().getZ())), TrapState.SET, 200, player));
                break;
            case 5509:
            case 5510:
            case 5512:
            case 5514:
                RunecraftingPouches.fill(player, RunecraftingPouch.forId(itemId));
                break;
            case 292:
                ingredientsBook.readBook(player, 0, false);
                break;
            case 8868:
                player.getInventory().delete(8868, 1);
                player.getInventory().add(19116, 2);
                player.getInventory().add(7956, 5);
                player.getInventory().add(989, 10);
                player.sendMessage(
                        "<shad=1>@blu@You swapped your @red@key@blu@ for @red@X5@blu@ PVM Box, @red@X10@blu@ Ckeys, @red@X2@blu@ Super Mbox");
                break;
            case 14471:
                player.getInventory().delete(14471, 1);
                player.getInventory().add(19116, 2);
                player.getInventory().add(7956, 5);
                player.getInventory().add(989, 10);
                player.sendMessage(
                        "<shad=1>@blu@You swapped your @red@key@blu@ for @red@X5@blu@ PVM Box, @red@X10@blu@ Ckeys, @red@X2@blu@ Super Mbox");
                break;
            case 9662:
                player.getInventory().delete(9662, 1);
                player.getInventory().add(19116, 2);
                player.getInventory().add(7956, 5);
                player.getInventory().add(989, 10);
                player.sendMessage(
                        "<shad=1>@blu@You swapped your @red@key@blu@ for @red@X5@blu@ PVM Box, @red@X10@blu@ Ckeys, @red@X2@blu@ Super Mbox");
                break;
            case 3468:
                player.getInventory().delete(3468, 1);
                player.getInventory().add(19116, 2);
                player.getInventory().add(7956, 5);
                player.getInventory().add(989, 10);
                player.sendMessage(
                        "<shad=1>@blu@You swapped your @red@key@blu@ for @red@X5@blu@ PVM Box, @red@X10@blu@ Ckeys, @red@X2@blu@ Super Mbox");
                break;
            case 6199:

                player.getMysteryBoxOpener().display(6199, "Mystery Box", MBox.common, MBox.uncommon, MBox.rare);
                break;
            case 18768:
                player.getMysteryBoxOpener().display(18768, "Dragonball Box", DragonballBox.common1, DragonballBox.uncommon1, DragonballBox.rare1);
                break;
            case 10025:
                player.getMysteryBoxOpener().display(10025, "Progressive Box", ProgressiveBox.commonpro, ProgressiveBox.uncommonpro, ProgressiveBox.rarepro);
                break;
            case 7120:
                player.getMysteryBoxOpener().display(7120, "Slayer Box", SlayerBox.commonpro2, SlayerBox.uncommonpro2, SlayerBox.rarepro2);
                break;
            case 23109:
                player.getMysteryBoxOpener().display(23109, "Halloween Box", HweenBox.common, HweenBox.uncommon, HweenBox.rare);
                break;
            case 23479:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.SLAYERBOXU);
                player.getCasketOpening().openInterface();
                break;
            /*
             * int progress[][] = { { 671, 16337, 4411, 14415, 14395, 14405, 672, 673,
             * 19887, 681,676,18363,677,678,679,22075,19471,19470,19469,4393, 734, 666,
             * 15424, 674, 22078, 4369, 15877, 16269, 15943, 675, 702, 700, 701, 17708,
             * 17602, 19153, 19142, 19141, 5095, 19140, 19139, 19138, 2572, 15922, 16021,
             * 15933, 18350, 18358,18354,14910,14915,14911,14912,14914,14913}, { 671, 16337,
             * 4411, 14415, 14395, 14405, 672, 673, 19887,
             * 681,676,18363,677,678,679,22075,19471,19470,19469,4393, 734, 666, 15424, 674,
             * 22078, 4369, 15877, 16269, 15943, 675, 702, 700, 701, 17708, 17602, 19153,
             * 19142, 19141, 5095, 19140, 19139, 19138, 2572, 15922, 16021, 15933, 18350,
             * 18358,18354, 18352,18360, 17600, 19944, 703, 704, 705, 19946, 19945, 17712,
             * 17638,17640,15593,16140,2021,12860,12565,10835}, { 671, 16337, 4411, 14415,
             * 14395, 14405, 672, 673, 19887,
             * 681,676,18363,677,678,679,22075,19471,19470,19469,4393, 734, 666, 15424, 674,
             * 22078, 4369, 15877, 16269, 15943, 675, 702, 700, 701, 17708, 17602, 19153,
             * 19142, 19141, 5095, 19140, 19139, 19138, 2572, 15922, 16021, 15933, 18350,
             * 18358,18354, 18352,18360, 17600, 19944, 703, 704, 705, 19946, 19945, 17712,
             * 17638,17640,15593,16140,2021,12860,12565,10835} }; double nugemprofess =
             * Math.random(); /** Chances 50% chance of Common Items - cheap gear, high-end
             * consumables 40% chance of Uncommon Items - various high-end coin-bought gear
             * 10% chance of Rare Items - Highest-end coin-bought gear, some
             * voting-point/pk-point equipment
             *
             * int rewardprogress = nugemprofess >= 0.5 ? 0 : nugemprofess >= 0.20 ? 1 : 2;
             * rewardPos = Misc.getRandom(progress[rewardprogress].length - 1);
             * player.getInventory().delete(10025, 1);
             * player.getInventory().add(progress[rewardprogress][rewardPos],
             * 1).refreshItems(); break;
             */
            case 6198:

                double petNumGen = Math.random();
                /**
                 * Chances 54% chance of Uncommon Items - various high-end coin-bought gear 30%
                 * chance of Rare Items - Highest-end coin-bought gear, Some poor
                 * voting-point/pk-point equipment 11% chance of Epic Items -Better
                 * voting-point/pk-point equipment 5% chance of Legendary Items - Only top-notch
                 * voting-point/pk-point equipment
                 */
                int petRewardGrade = petNumGen >= 0.46 ? 0 : petNumGen >= 0.16 ? 1 : petNumGen >= 0.05 ? 2 : 3;
                int petRewardPos = Misc.getRandom(DRPetBox.petRewards[petRewardGrade].length - 1);
                player.getInventory().delete(6198, 1);
                player.getPacketSender().sendMessage("@blu@Enjoy your pet do ::droprate to check your drop rates.");
                player.getInventory().add(DRPetBox.petRewards[petRewardGrade][petRewardPos], 1).refreshItems();
                break;
            case PVMBox.ITEM_ID:
                player.getMysteryBoxOpener().display(PVMBox.ITEM_ID, "Pvm box", PVMBox.commonpvm, PVMBox.uncommonpvm, PVMBox.rarepvm);
                break;
            case 23480:
                player.getMysteryBoxOpener().display(23480, "Pvm box (t2)", PVMBox.uncommonpvm, PVMBox.rarepvm, PVMBox.ultRare);
                break;

            /*
             * int lootRewards[][] = { { 4716, 4720, 4718, 4722, 4708, 4712, 4714, 4710,
             * 4732, 4736, 4738, 4734, 4753, 4757, 4759, 4755, 4745, 4749, 4751, 4747, 290,
             * 6199 }, // Uncommon, 0 { 18740, 19670, 4153, 1215, 4151, 18684, 18686, 3140,
             * 15332 }, // Rare, 1 { 11852, 11854, 11856, 11846, 11848, 6199, 19670 }, //
             * Epic, 2 { 4882, 4894, 4900, 4888, 20460, 20456, 18747 } // Legendary, 3 };
             * double lootNumGen = Math.random(); /** Chances 54% chance of Uncommon Items -
             * various high-end coin-bought gear 30% chance of Rare Items - Highest-end
             * coin-bought gear, Some poor voting-point/pk-point equipment 11% chance of
             * Epic Items -Better voting-point/pk-point equipment 5% chance of Legendary
             * Items - Only top-notch voting-point/pk-point equipment
             *
             * int lootRewardGrade = lootNumGen >= 0.46 ? 0 : lootNumGen >= 0.16 ? 1 :
             * lootNumGen >= 0.05 ? 2 : 3; int lootRewardPos =
             * Misc.getRandom(lootRewards[lootRewardGrade].length - 1);
             * player.getInventory().delete(7956, 1);
             * player.getPacketSender().sendMessage("looted.");
             * player.getInventory().add(lootRewards[lootRewardGrade][lootRewardPos],
             * 1).refreshItems(); break;
             */
            case 20083:
                int hween[][] = {{22041, 19132, 18405, 18406, 18407}, // Uncommon, 0
                        {22041, 19132, 18405, 18406, 18407}, // Rare, 1
                        {22041, 19132, 18405, 18406, 18407}, // Epic, 2
                        {22041, 19132, 18405, 18406, 18407} // Legendary, 3
                };
                double hweenGen = Math.random();
                /**
                 * Chances 54% chance of Uncommon Items - various high-end coin-bought gear 30%
                 * chance of Rare Items - Highest-end coin-bought gear, Some poor
                 * voting-point/pk-point equipment 11% chance of Epic Items -Better
                 * voting-point/pk-point equipment 5% chance of Legendary Items - Only top-notch
                 * voting-point/pk-point equipment
                 */
                int hweenRewardg = hweenGen >= 0.46 ? 0 : hweenGen >= 0.16 ? 1 : hweenGen >= 0.05 ? 2 : 3;
                int hweenrewardgread = Misc.getRandom(hween[hweenRewardg].length - 1);
                player.getInventory().delete(20083, 1);
                World.sendMessage("<img=5>@blu@[Halloween cracker]<img=5>@red@ " + player.getUsername()
                        + " Has just opened a halloween box! ");

                // player.getPacketSender().sendMessage("looted.");
                player.getInventory().add(hween[hweenRewardg][hweenrewardgread], 1).refreshItems();
                break;
            case 4570:
                int cmascrack[][] = {{1050, 10284, 18411, 18413, 18414, 18412, 18410}, // Uncommon, 0
                        {1050, 10284, 18411, 18413, 18414, 18412, 18410}, // Rare, 1
                        {1050, 10284, 18411, 18413, 18414, 18412, 18410}, // Epic, 2
                        {1050, 10284, 18411, 18413, 18414, 18412, 18410} // Legendary, 3
                };
                double camaksGen2 = Math.random();
                /**
                 * Chances 54% chance of Uncommon Items - various high-end coin-bought gear 30%
                 * chance of Rare Items - Highest-end coin-bought gear, Some poor
                 * voting-point/pk-point equipment 11% chance of Epic Items -Better
                 * voting-point/pk-point equipment 5% chance of Legendary Items - Only top-notch
                 * voting-point/pk-point equipment
                 */
                int cmasrward = camaksGen2 >= 0.46 ? 0 : camaksGen2 >= 0.16 ? 1 : camaksGen2 >= 0.05 ? 2 : 3;
                int refeigenerator = Misc.getRandom(cmascrack[cmasrward].length - 1);
                player.getInventory().delete(4570, 1);
                World.sendMessage("<img=21>@blu@[Chrismas cracker]<img=5>@red@ " + player.getUsername()
                        + " Has just opened a halloween box! ");

                // player.getPacketSender().sendMessage("looted.");
                player.getInventory().add(cmascrack[cmasrward][refeigenerator], 1).refreshItems();
                break;
            case 6183:
                int hween1[][] = {{8857, 8858, 8859, 16835}, // Uncommon, 0
                        {8857, 8858, 8859, 16835}, // Rare, 1
                        {8857, 8858, 8859, 16835}, // Epic, 2
                        {8857, 8858, 8859, 16835} // Legendary, 3
                };
                double hweenGen1 = Math.random();
                /**
                 * Chances 54% chance of Uncommon Items - various high-end coin-bought gear 30%
                 * chance of Rare Items - Highest-end coin-bought gear, Some poor
                 * voting-point/pk-point equipment 11% chance of Epic Items -Better
                 * voting-point/pk-point equipment 5% chance of Legendary Items - Only top-notch
                 * voting-point/pk-point equipment
                 */
                int hweenRewardg1 = hweenGen1 >= 0.46 ? 0 : hweenGen1 >= 0.16 ? 1 : hweenGen1 >= 0.05 ? 2 : 3;
                int hweenrewardgread1 = Misc.getRandom(hween1[hweenRewardg1].length - 1);
                player.getInventory().delete(6183, 1);
                World.sendMessage("<img=5>@blu@[Halloween Box]<img=5>@red@ " + player.getUsername()
                        + " Has just opened a halloween box! ");

                // player.getPacketSender().sendMessage("looted.");
                player.getInventory().add(hween1[hweenRewardg1][hweenrewardgread1], 1).refreshItems();
                break;
            case 6855:
                int xmaswni[][] = {{13025, 13027, 13023, 22043, 13029, 13031}, // Uncommon, 0
                        {13025, 13027, 13023, 22043, 13029, 13031}, // Rare, 1
                        {13025, 13027, 13023, 22043, 13029, 13031}, // Epic, 2
                        {13025, 13027, 13023, 22043, 13029, 13031} // Legendary, 3
                };
                double xmasgen = Math.random();
                /**
                 * Chances 54% chance of Uncommon Items - various high-end coin-bought gear 30%
                 * chance of Rare Items - Highest-end coin-bought gear, Some poor
                 * voting-point/pk-point equipment 11% chance of Epic Items -Better
                 * voting-point/pk-point equipment 5% chance of Legendary Items - Only top-notch
                 * voting-point/pk-point equipment
                 */
                int xmasreward = xmasgen >= 0.46 ? 0 : xmasgen >= 0.16 ? 1 : xmasgen >= 0.05 ? 2 : 3;
                int xmadretin = Misc.getRandom(xmaswni[xmasreward].length - 1);
                player.getInventory().delete(6855, 1);
                World.sendMessage("<img=17>@blu@[Christmas Mystery Box]<img=17>@red@ " + player.getUsername()
                        + " Has just opened a Christmas box! ");

                // player.getPacketSender().sendMessage("looted.");
                player.getInventory().add(xmaswni[xmasreward][xmadretin], 1).refreshItems();
                break;
            case 15501:
                player.getMysteryBoxOpener().display(15501, "Vote Box", VotingMbox.common, VotingMbox.uncommon, VotingMbox.rare);
                break;
            case 11946:
                player.getInventory().delete(11946, 1);
                player.getInventory().add(6137, 1);
                player.getInventory().add(6139, 1);
                player.getInventory().add(6141, 1);
                player.getInventory().add(6147, 1);
                player.getInventory().add(6153, 1);
                player.sendMessage("You have opened a skeletal set!");
                break;
            case 19116:
                player.getMysteryBoxOpener().display(19116, "Super Box", Super.common, Super.uncommon, Super.rare);
                break;
            case 19115:
                player.getMysteryBoxOpener().display(19115, "Extreme Box", Extreme.common, Extreme.uncommon, Extreme.rare);
                break;
            case 19114:// grandmbox
                player.getMysteryBoxOpener().display(19114, "Grand Box", Grand.common, Grand.uncommon, Grand.rare);
                break;
            case 20488:// grandmbox
                player.getMysteryBoxOpener().display(20488, "OP Chest", OP.common, OP.uncommon, OP.rare);
                break;
            case 20489:// grandmbox
                player.getMysteryBoxOpener().display(20489, "@mag@Launch Casket", Launch.common, Launch.uncommon, Launch.rare);
                break;

            case 11884:
                player.getInventory().delete(11884, 1);
                player.getInventory().add(2595, 1).refreshItems();
                player.getInventory().add(2591, 1).refreshItems();
                player.getInventory().add(3473, 1).refreshItems();
                player.getInventory().add(2597, 1).refreshItems();
                break;
            case 11882:
                player.getInventory().delete(11882, 1);
                player.getInventory().add(2595, 1).refreshItems();
                player.getInventory().add(2591, 1).refreshItems();
                player.getInventory().add(2593, 1).refreshItems();
                player.getInventory().add(2597, 1).refreshItems();
                break;
            case 11906:
                player.getInventory().delete(11906, 1);
                player.getInventory().add(7394, 1).refreshItems();
                player.getInventory().add(7390, 1).refreshItems();
                player.getInventory().add(7386, 1).refreshItems();
                break;
            case 15262:
                if (!player.getClickDelay().elapsed(1000))
                    return;
                player.getInventory().delete(15262, 1);
                player.getInventory().add(18016, 10000).refreshItems();
                player.getClickDelay().reset();
                break;
            case 6:
                // DwarfMultiCannon.setupCannon(player);
                player.getPacketSender().sendMessage("Cannon is disabled.");
                break;
            case 2722:
            case 2723:
            case 2725:
            case 2727:
            case 2729:
            case 2731:
            case 2733:
            case 2735:
            case 2737:
            case 2739:
            case 2741:
            case 2743:
            case 2745:
            case 2747:
            case 2773:
            case 2774:
            case 2776:
            case 2778:
            case 2780:
            case 2782:
            case 2783:
            case 2785:
            case 2786:
            case 2788:
            case 2790:
            case 2792:
            case 2793:
            case 2794:
            case 2796:
            case 2797:
            case 2799:
            case 3520:
            case 3522:
            case 3524:
            case 3525:
            case 3526:
            case 3528:
            case 3530:
            case 3532:
            case 3534:
            case 3536:
            case 3538:
            case 3540:
            case 3542:
            case 3544:
            case 3546:
            case 3548:
            case 3550:
                player.getInventory().delete(itemId, 1);
                CLUESCROLL.awardCasket(player);
                /*for (int i = 0; i < CLUESCROLL.values().length; i++) {
                    if (CLUESCROLL.values()[i].getClueId() == itemId) {
                        int steps = player.getPointsHandler().getClueSteps();
                        String s = "steps";
                        if (steps == 1) {
                            s = "step";
                        }
                        player.getPacketSender().sendMessage(CLUESCROLL.values()[i].getHint() + " You've done "
                                + player.getPointsHandler().getClueSteps() + " " + s + ".");
                        break;
                    }
                }*/
        }
    }

    public static void thirdAction(Player player, Packet packet) {
        int interfaceId = packet.readLEShortA();
        int slot = packet.readLEShort();
        int itemId = packet.readShortA();
        if (slot < 0 || slot > player.getInventory().capacity())
            return;
        if (player.getInventory().getItems()[slot].getId() != itemId)
            return;

        player.setInteractingItem(player.getInventory().getItems()[slot]);


        if (itemId != 5504) {
            if (SummoningData.isPouch(player, itemId, 2))
                return;
        }
        player.getDissolving().handle(itemId, 1, false);


        if (PetUpgrading.upgradeable(player, itemId)) {
            return;
        }

        if (MemberScrolls.convert(player, itemId))
            return;

        switch (itemId) {
            case 23514:
                if (player.getInventory().getAmount(23514) >= 5) {
                    int amountInInventory = player.getInventory().getAmount(23514);
                    int convertableAmount = amountInInventory - (amountInInventory % 5);
                    player.getInventory().delete(23514, convertableAmount).add(23515, convertableAmount/5);
                }
                break;
            case 23515:
                if (player.getInventory().getAmount(23515) >= 2) {
                    int amountInInventory = player.getInventory().getAmount(23515);
                    int convertableAmount = amountInInventory - (amountInInventory % 2);
                    player.getInventory().delete(23515, convertableAmount).add(23516, convertableAmount/2);
                }
                break;
            case 23759:
                player.getDisassembling().handle(itemId, player.getInventory().getAmount(23759));
                break;
            case 12855:
                player.setInputHandling(new ExchangeXUpgradeTokens());
                player.getPA().sendEnterAmountPrompt("How many 1k tokens would you like to receive?");
                break;
            case 13591:
                player.getPacketSender().sendMessage("You rub the enchanted key to teleport to chest area.");
                Position position = new Position(2706, 2737, 0);
                TeleportHandler.teleportPlayer(player, position, TeleportType.NORMAL);
                break;
            case 6500:
                if (player.getCombatBuilder().isAttacking() || player.getCombatBuilder().isBeingAttacked()) {
                    player.getPacketSender().sendMessage("You cannot configure this right now.");
                    return;
                }
                player.getPacketSender().sendInterfaceRemoval();
                DialogueManager.start(player, 101);
                player.setDialogueActionId(60);
                break;
            case 4155:
                player.getPacketSender().sendInterfaceRemoval();
                DialogueManager.start(player, 103);
                player.setDialogueActionId(65);
                break;
            case 1712: // glory start
            case 1710:
            case 1708:
            case 1706: // glory end
            case 11118: // cb brace start
            case 11120:
            case 11122:
            case 11124: // cb brace end
            case 2552: // duel start
            case 2554:
            case 2556:
            case 2558:
            case 2560:
            case 2562:
            case 2564:
            case 2566: // duel end
            case 3853: // games start
            case 3855:
            case 3857:
            case 3859:
            case 3861:
            case 3863:
            case 3865:
            case 3867: // games end
            case 11194: // digsite start
            case 11193:
            case 11192:
            case 11191:
            case 11190: // digsite start
                String jewelName = JewelryTeleports.stripName(itemId);
                JewelryTeleports.handleDialogue(player, itemId, JewelryTeleports.jewelIndex(jewelName));
                break;
            case 10362:
                JewelryTeleporting.rub(player, itemId);
                break;
            case 10732:
            case 4566:
                player.performAnimation(new Animation(1835));
                break;
//
            //
            case 13263:
                if (player.getInventory().contains(13263) && player.getInventory().getAmount(5023) >= 1000) {
                    player.getInventory().delete(13263, 1).delete(5023, 1000).add(21075, 1);
                    player.getPacketSender().sendMessage("You have upgraded your slayer helmet");
                } else {
                    player.getPacketSender().sendMessage("You need at least 1K Slayer tickets to upgrade your helmet.");
                }
                break;
            case 21075:
                if (player.getInventory().contains(21075) && player.getInventory().getAmount(5023) >= 3000) {
                    player.getInventory().delete(21075, 1).delete(5023, 3000).add(21076, 1);
                    player.getPacketSender().sendMessage("You have upgraded your slayer helmet");
                } else {
                    player.getPacketSender().sendMessage("You need at least 3K Slayer tickets to upgrade your helmet.");
                }
                break;
            case 21076:
                if (player.getInventory().contains(21076) && player.getInventory().getAmount(5023) >= 6000) {
                    player.getInventory().delete(21076, 1).delete(5023, 6000).add(21077, 1);
                    player.getPacketSender().sendMessage("You have upgraded your slayer helmet");
                } else {
                    player.getPacketSender().sendMessage("You need at least 6K Slayer tickets to upgrade your helmet.");
                }
                break;
            case 21077:
                if (player.getInventory().contains(21077) && player.getInventory().getAmount(5023) >= 10000) {
                    player.getInventory().delete(21077, 1).delete(5023, 10000).add(21078, 1);
                    player.getPacketSender().sendMessage("You have upgraded your slayer helmet");
                } else {
                    player.getPacketSender().sendMessage("You need at least 10K Slayer tickets to upgrade your helmet.");
                }
                break;
            case 21078:
                if (player.getInventory().contains(21077) && player.getInventory().getAmount(5023) >= 20000) {
                    player.getInventory().delete(21077, 1).delete(5023, 20000).add(21079, 1);
                    player.getPacketSender().sendMessage("You have upgraded your slayer helmet");
                } else {
                    player.getPacketSender().sendMessage("You need at least 20K Slayer tickets to upgrade your helmet.");
                }
                break;


            case 11113:
                player.getPacketSender().sendMessage("All skill teleports are available in the skills tab.");
                break;

            case 1704:
                player.getPacketSender().sendMessage("Your amulet has run out of charges.");
                break;
            case 11126:
                player.getPacketSender().sendMessage("Your bracelet has run out of charges.");
                break;
            case 13281:
            case 13282:
            case 13283:
            case 13284:
            case 13285:
            case 13286:
            case 13287:
            case 13288:

                player.getSlayer().handleSlayerRingTP(itemId);
                break;
            case 18819:
                player.getSlayer().handleSlayerRingTP2(itemId);
                break;
            case 5509:
            case 5510:
            case 5512:
            case 5514:
                RunecraftingPouches.check(player, RunecraftingPouch.forId(itemId));
                break;
            case 2550:
                if (!player.getInventory().contains(2550)) {
                    player.getPacketSender().sendMessage("You must have a ring of recoil in your inventory to do this.");
                    return;
                }
                if (ItemDegrading.maxRecoilCharges - player.getRecoilCharges() == ItemDegrading.maxRecoilCharges) {
                    player.getPacketSender().sendMessage("You already have the maximum ring of recoil charges.");
                    return;
                }
                player.getInventory().delete(2550, 1);
                player.setRecoilCharges(0);
                player.getPacketSender().sendMessage("Your ring of recoil turns to dust, and your charges are reset.");
                break;

            case 12926:
                int charges = player.getBlowpipeCharges();
                if (!player.getInventory().contains(12926)) {
                    return;
                }
                if (charges <= 0) {
                    player.getPacketSender().sendMessage("You have no charges!");
                    return;
                }
                if (player.getInventory().contains(12934) || !player.getInventory().isFull()) {
                    player.getInventory().add(12934, charges);
                    player.setBlowpipeCharges(0);
                    player.getPacketSender().sendMessage(
                            "You uncharge your blowpipe and recieve " + Misc.format(charges) + " Zulrah scales");
                } else {
                    player.getPacketSender().sendMessage("You need an inventory space.");
                }
                break;
            case 995:
                ConvertCoins.Bills(player);
                break;
            case 19131:
                if (player.getInventory().contains(19131) && player.getInventory().getAmount(12657) >= 1000) {
                    player.getInventory().delete(19131, 1).delete(12657, 1000).add(19130, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 1000 Pebbles to upgrade your boots.");
                }
                break;
            case 19130:
                if (player.getInventory().contains(19130) && player.getInventory().getAmount(12657) >= 2000) {
                    player.getInventory().delete(19130, 1).delete(12657, 2000).add(19129, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 2000 Pebbles to upgrade your boots.");
                }
                break;
            case 19129:
                if (player.getInventory().contains(19129) && player.getInventory().getAmount(12657) >= 3000) {
                    player.getInventory().delete(19129, 1).delete(12657, 3000).add(19128, 1);
                    player.getPacketSender().sendMessage("You have upgraded your Steel boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 3000 Pebbles to upgrade your boots.");
                }
                break;
            case 19128:
                if (player.getInventory().contains(19128) && player.getInventory().getAmount(12657) >= 4000) {
                    player.getInventory().delete(19128, 1).delete(12657, 4000).add(19127, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 4000 Pebbles to upgrade your boots.");
                }
                break;
            case 19127:
                if (player.getInventory().contains(19127) && player.getInventory().getAmount(12657) >= 5000) {
                    player.getInventory().delete(19127, 1).delete(12657, 5000).add(19126, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 5000 Pebbles to upgrade your boots.");
                }
                break;
            case 19126:
                if (player.getInventory().contains(19126) && player.getInventory().getAmount(12657) >= 6000) {
                    player.getInventory().delete(19126, 1).delete(12657, 6000).add(19125, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 6000 Pebbles to upgrade your boots.");
                }
                break;
            case 19125:
                if (player.getInventory().contains(19125) && player.getInventory().getAmount(12657) >= 7000) {
                    player.getInventory().delete(19125, 1).delete(12657, 7000).add(19124, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 7000 Pebbles to upgrade your boots.");
                }
                break;
            case 19124:
                if (player.getInventory().contains(19124) && player.getInventory().getAmount(12657) >= 8000) {
                    player.getInventory().delete(19124, 1).delete(12657, 8000).add(19123, 1);
                    player.getPacketSender().sendMessage("You have upgraded your boots!");
                } else {
                    player.getPacketSender().sendMessage("You need at least 8000 Pebbles to upgrade your boots.");
                }
                break;

            case 1438:
            case 1448:
            case 1440:
            case 1442:
            case 1444:
            case 1446:
            case 1454:
            case 1452:
            case 1462:
            case 1458:
            case 1456:
            case 1450:
                Runecrafting.handleTalisman(player, itemId);
                break;
        }
    }

    public void secondAction(Player player, Packet packet) {
        int itemId = packet.readShortA();
        int slot = packet.readLEShortA();
        int interfaceId = packet.readLEShortA();
        if (slot < 0 || slot > player.getInventory().capacity())
            return;
        if (player.getInventory().getItems()[slot].getId() != itemId)
            return;
        if (JarData.forJar(itemId) != null) {
            PuroPuro.lootJar(player, new Item(itemId, 1), JarData.forJar(itemId));
            return;
        }
        if (itemId != 5504) {
            if (SummoningData.isPouch(player, itemId, 3)) {
                return;
            }
        }

        player.getDisassembling().handle(itemId, 1);

        if (ItemBinding.isBindable(itemId)) {
            ItemBinding.bindItem(player, itemId);
            return;
        }

        if (!player.getInventory().containsAny(11846, 11848, 11850, 11852, 11854, 11856, 10835)) {
            player.getDissolving().handle(itemId, 1, false);
        }

        if (MemberScrolls.handleScroll(player, itemId, true))
            return;

        if (SpecialAmmo.forId(itemId) != null) {
            SpecialAmmo.upgrade(player, player.getInventory().getItems()[slot]);
        }
        int amount = 0;

        switch (itemId) {
            case 23794:
                player.setInputHandling(new ExchangeX1kTokens());
                player.getPA().sendEnterAmountPrompt("How many 1k tokens would you like to exchange?");
                break;
            case 12855:
                int total = player.getInventory().getAmount(12855) / 1000;
                player.getInventory().delete(12855, total * 1000);
                player.getInventory().add(23794, total);
                player.sendMessage("You just exchanged Upgrade tokens into 1k tokens.");
                break;
            case 11027:
                player.getCasketOpening().setCurrentCasket(CasketOpening.Caskets.EASTER_EGG);
                player.getCasketOpening().openAll(player.getInventory().getAmount(11027));
                break;
            case 23536:
                amount = player.getInventory().getAmount(23536);
                player.getInventory().delete(23536, amount).add(5022, amount * 1000);
                break;
            case 10835:
                if (player.getInventory().contains(10835)) {
                    long amountCash = (long) player.getInventory().getAmount(10835) * 1000000000L;
                    player.getInventory().delete(10835, player.getInventory().getAmount(10835));

                    player.sendMessage(Misc.insertCommasToNumber(amountCash) + " coins have been added to your money pouch.");
                    player.setMoneyInPouch(player.getMoneyInPouch() + amountCash);
                    player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
                }
                break;

            case 15290:
                amount = player.getInventory().getAmount(15290);
                player.getInventory().delete(15290, amount).add(12855, amount * 5000);
                break;
            case 15289:
                amount = player.getInventory().getAmount(15289);
                player.getInventory().delete(15289, amount).add(12855, amount * 25000);
                break;
            case 15288:
                amount = player.getInventory().getAmount(15288);
                player.getInventory().delete(15288, amount).add(12855, amount * 100000);
                break;

            case 23514:
                amount = player.getInventory().getAmount(23514);
                player.getInventory().delete(23514, amount).add(5022, amount * 1000);
                break;
            case 23515:
                amount = player.getInventory().getAmount(23515);
                player.getInventory().delete(23515, amount).add(5022, amount * 5000);
                break;
            case 23516:
                amount = player.getInventory().getAmount(23516);
                player.getInventory().delete(23516, amount).add(5022, amount * 10000);
                break;

            case 4278:
            case 23211:
                if (player.getLocation() == Location.JAIL) {
                    player.sendMessage("<shad=1>@cya@You can't start an instance while your in jail.");
                    return;
                }

                if (player.currentInstanceAmount >= 1) {
                    player.sendMessage("<shad=1>@red@You can't start a new instance until this one ends");
                    return;
                }

                if (player.lastInstanceNpc == -1) {
                    player.sendMessage("<shad=1>@red@You need a valid last instance before doing this!");
                    return;
                }
                if (!player.getLastRunRecovery().elapsed(5000)) {
                    player.sendMessage("Please wait 5 seconds before doing this again.");
                    return;
                }

                boolean validNpc = false;
                for (InstanceData data : InstanceData.values()) {
                    if (data.getNpcId() == player.lastInstanceNpc) {
                        validNpc = true;
                        break;
                    }
                }

                if (!validNpc) {
                    player.sendMessage("<shad=1>@red@You need a valid last instance before doing this!");
                    return;
                }

                player.getDailyTaskManager().submitProgressToIdentifier(12, 1);
                player.getInstanceManager().ticketID = itemId;
                player.getInstanceManager().createInstance(player.lastInstanceNpc, RegionInstance.RegionInstanceType.INSTANCE);
                break;
            case 23014:
            case 23015:
            case 23016:
            case 23017:
                if (player.getInventory().contains(itemId)) {
                    player.getInventory().delete(itemId, 1);
                    player.getInventory().add(23013, 1);
                    player.sendMessage("You reverted your mask.");
                }
                break;
            case 15330:
                if (player.getInventory().contains(15330) &&
                        player.getInventory().getAmount(12855) >= 50_000_000) {
                    DialogueManager.start(player, new Dialogue() {
                        @Override
                        public DialogueType type() {
                            return DialogueType.STATEMENT;
                        }

                        @Override
                        public DialogueExpression animation() {
                            return null;
                        }

                        @Override
                        public String[] dialogue() {
                            return new String[] {"Are you sure you want to upgrade your infinte super overload", "to an infinity rage potion?"};
                        }

                        @Override
                        public Dialogue nextDialogue() {
                            return new Dialogue() {
                                @Override
                                public DialogueType type() {
                                    return DialogueType.OPTION;
                                }

                                @Override
                                public DialogueExpression animation() {
                                    return null;
                                }

                                @Override
                                public String[] dialogue() {
                                    return new String[] {"Yes", "No"};
                                }

                                @Override
                                public void specialAction() {
                                    player.setDialogueActionId(481);
                                }
                            };
                        }
                    });
                } else {
                    player.sendMessage("You need 50m upgrade tokens to upgrade.");
                }
                break;
            case 22108:
                DialogueManager.start(player, 9924);
                player.setDialogueActionId(9924);
                break;
            case 19000:
                if (player.getInventory().contains(19000)) {
                    amount = player.getInventory().getAmount(19000);
                    player.getInventory().delete(19000, amount);
                    player.getInventory().add(12855, amount * 100);
                    player.sendMessage("You have exchanged X" + amount + " Pet fragments for X" + amount * 100 + " Upgrade Tokens!");
                }
                break;
            case 22000:
                if (player.getInventory().contains(22000) && player.getInventory().getAmount(12855) >= 250000 && player.getInventory().getAmount(621) >= 250) {
                    player.getInventory().delete(12855, 250000);
                    player.getInventory().delete(621, 250);
                    player.getInventory().delete(22000, 1);
                    player.getInventory().add(22001, 1);
                    player.sendMessage("Congratulations you have upgraded your helm to t2. ");
                } else {
                    player.sendMessage("You need 250k Tokens and 250 boss slayer tickets to upgrade the helm to t2.");
                }
                break;
            case 22001:
                if (player.getInventory().contains(22001) && player.getInventory().getAmount(12855) >= 500000 && player.getInventory().getAmount(621) >= 500) {
                    player.getInventory().delete(12855, 500000);
                    player.getInventory().delete(621, 500);
                    player.getInventory().delete(22001, 1);
                    player.getInventory().add(22002, 1);
                    player.sendMessage("Congratulations you have upgraded your helm to t2. ");
                } else {
                    player.sendMessage("You need 500k Tokens and 500 boss slayer tickets to upgrade the helm to t3.");
                }
                break;
            case 22002:
                if (player.getInventory().contains(22002) && player.getInventory().getAmount(12855) >= 2500000 && player.getInventory().getAmount(621) >= 2500) {
                    player.getInventory().delete(12855, 2500000);
                    player.getInventory().delete(621, 2500);
                    player.getInventory().delete(22002, 1);
                    player.getInventory().add(22003, 1);
                    player.sendMessage("Congratulations you have upgraded your helm to t4. ");
                } else {
                    player.sendMessage("You need 2.5m Tokens and 2500 boss slayer tickets to upgrade the helm to t4.");
                }
                break;
            case 22003:
                if (player.getInventory().contains(22003) && player.getInventory().getAmount(12855) >= 5000000 && player.getInventory().getAmount(621) >= 5000) {
                    player.getInventory().delete(12855, 5000000);
                    player.getInventory().delete(621, 5000);
                    player.getInventory().delete(22003, 1);
                    player.getInventory().add(22004, 1);
                    player.sendMessage("Congratulations you have upgraded your helm to t5. ");
                } else {
                    player.sendMessage("You need 5m Tokens and 5000 boss slayer tickets to upgrade the helm to t5.");
                }
                break;

            case 5021:

                int amount1 = player.getInventory().getAmount(5021);
                if (amount1 > 2147 || amount1 + player.getInventory().getAmount(995) > 2147000000) {
                    long amountLeft;
                    if (!player.getInventory().contains(995))
                        amountLeft = (long) (((long) amount1 * (long) 1000000) - (long) 2147000000);
                    else
                        amountLeft = ((long) amount1 * (long) 1000000) - (long) (2147000000 - player.getInventory().getAmount(995));
                    player.getInventory().delete(5021, amount1);
                    // player.getInventory().add(995, 2147000000 - (player.getInventory().getAmount(995)));
                    player.setMoneyInPouch(player.getMoneyInPouch() + amountLeft);
                    player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
                    player.sendMessage("<shad=1>@red@The rest of the cash(" + amountLeft / 1000000
                            + "M) has been added to your @blu@pouch@red@!");
                    return;
                }
                player.getInventory().delete(5021, amount1);
                //player.getInventory().add(995, 1000000 * amount1);
                break;

            case 11846:
            case 11848:
            case 11850:
            case 11852:
            case 11854:
            case 11856:
                if (!player.getClickDelay().elapsed(250) || !player.getInventory().contains(itemId))
                    return;
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You cannot open this right now.");
                    return;
                }
                int amountToOpen = player.getInventory().getAmount(itemId);
                /*if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
                    amountToOpen = player.getInventory().getFreeSlots() / 4;
                    if (amountToOpen > player.getInventory().getAmount(itemId))
                        amountToOpen = player.getInventory().getAmount(itemId);
                }*/
                if (amountToOpen == 0) {
                    player.getPacketSender().sendMessage("You do not have enough inventory space to do this.");
                    return;
                }

                int[] items = itemId == 11858 ? new int[]{10350, 10348, 10346, 10352}
                        : itemId == 19580 ? new int[]{19308, 19311, 19314, 19317, 19320}
                        : itemId == 11860 ? new int[]{10334, 10330, 10332, 10336}
                        : itemId == 11862 ? new int[]{10342, 10338, 10340, 10344}
                        : itemId == 11848 ? new int[]{4716, 4720, 4722, 4718}
                        : itemId == 11856 ? new int[]{4753, 4757, 4759, 4755}
                        : itemId == 11850 ? new int[]{4724, 4728, 4730, 4726}
                        : itemId == 11854 ? new int[]{4745, 4749, 4751, 4747}
                        : itemId == 11852 ? new int[]{4732, 4734, 4736, 4738}
                        : itemId == 11846 ? new int[]{4708, 4712, 4714, 4710}
                        : new int[]{itemId};

                int[][] tabs = new int[items.length][2];

                int index = 0;
                for (int z : items) {
                    tabs[index][0] = z;
                    tabs[index][1] = Bank.getTabForItem(player, z);
                    index++;
                }

                for (int i = 0; i < amountToOpen; i++) {
                    player.getInventory().delete(itemId, 1, false);

                    for (int[] z : tabs) {
                       /* if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
                            player.getInventory().add(new Item(z[0], 1), false);
                        } else {*/
                        Item item = new Item(z[0], 1);
                        if (ItemDefinition.forId(item.getId()).isNoted()) {
                            item.setId(Item.getUnNoted(item.getId()));
                        }
                        player.getBank(z[1]).add(item, false);
                        //player.depositItemBank(z[1], new Item(z[0], 1), false);
                        //   }
                    }
                }
                player.getClickDelay().reset();
                player.getInventory().refreshItems();
                player.getPacketSender().sendMessage("You opened " + amountToOpen + " sets.");
                break;
            case 5500:
                if (player.getPointsHandler().getSlayerRate() >= 6) {
                    player.sendMessage("You can't claim more than X6 Multipliers total!");
                    return;
                }

                player.getPointsHandler().incrementSlayerRate(1);
                player.getPacketSender().sendMessage("You have increased your slayer rate. @blu@"
                        + player.getPointsHandler().getSlayerRate() + "@bla@ is now your total multiplier.");
                PlayerPanel.refreshPanel(player);
                player.getInventory().delete(5500, 1);


                break;
            case 995:
                ConvertCoins.Mills(player);
                break;
            case 12845:
                player.getPointsHandler().incrementPengRate(2);
                player.getPacketSender().sendMessage("You have increased your penguin rate. @blu@"
                        + player.getPointsHandler().getPengRate() + "@bla@ is now your total multiplier.");
                PlayerPanel.refreshPanel(player);
                player.getInventory().delete(12845, 1);
                break;
            case 5154:
                player.getPointsHandler().incrementPengRate(100);
                player.getPacketSender().sendMessage("You have increased your penguin rate. @blu@"
                        + player.getPointsHandler().getPengRate() + "@bla@ is now your total multiplier.");
                PlayerPanel.refreshPanel(player);
                player.getInventory().delete(5154, 1);
                break;
            case 5155:
                player.getPointsHandler().incrementPengRate(1000);
                player.getPacketSender().sendMessage("You have increased your penguin rate @blu@"
                        + player.getPointsHandler().getPengRate() + "@bla@ is now your total multiplier.");
                PlayerPanel.refreshPanel(player);
                player.getInventory().delete(5155, 1);
                break;
            case 5156:
                player.getPointsHandler().incrementPengRate(10000);
                player.getPacketSender().sendMessage("You have increased your penguin rate @blu@"
                        + player.getPointsHandler().getPengRate() + "@bla@ is now your total multiplier.");
                PlayerPanel.refreshPanel(player);
                player.getInventory().delete(5156, 1);
                break;
            case 7510:
                long plc = player.getConstitution();
                long plcr = plc - 1;

                if (plc == 1) {
                    plcr = 0;
                }

                if (plcr > 0) {
                    player.performAnimation(new Animation(829));
                    Hit h = new Hit(plcr);
                    player.dealDamage(h);
                    player.forceChat(Misc.randomElement(ROCK_CAKE));
                } else {
                    player.getPacketSender().sendMessage("You'll die if you keep eating this putrid rock!");
                }
                break;
            case 2550:
                int recoilcharges = (ItemDegrading.maxRecoilCharges - player.getRecoilCharges());
                player.getPacketSender().sendMessage("You have " + recoilcharges + " recoil "
                        + (recoilcharges == 1 ? "charge" : "charges") + " remaining.");
                break;
            case 681:
                int recoilcharges1 = (ItemDegrading.maxlordCharges - player.getRecoilCharges());
                player.getPacketSender().sendMessage("You have " + recoilcharges1 + " recoil "
                        + (recoilcharges1 == 1 ? "charge" : "charges") + " remaining.");
                break;
            case 9003:
                if (!player.getClickDelay().elapsed(100)) {
                    return;
                }
                if (player.getLastTomed() == 0) {
                    player.getPacketSender().sendMessage("<img=5> No NPC set in the Tome.");
                    return;
                }
                if (player.busy() || player.getCombatBuilder().isAttacking()
                        || player.getCombatBuilder().isBeingAttacked()) {
                    player.getPacketSender().sendMessage("You're much too busy to do that.");
                    return;
                }
                NPCDrops.sendDropTableInterface(player, player.getLastTomed());
                player.getClickDelay().reset();
                break;
            // case 13738:
            case 13740:
            case 13742:
                // case 13744:
                if (player.isSpiritDebug()) {
                    player.getPacketSender().sendMessage("You toggle your Spirit Shield to not display specific messages.");
                    player.setSpiritDebug(false);
                } else if (player.isSpiritDebug() == false) {
                    player.getPacketSender().sendMessage("You toggle your Spirit Shield to display specific messages.");
                    player.setSpiritDebug(true);
                }
                break;
            case 19670:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                VoteRewardHandler.voteRewards(player, true);
                break;
            case 23020:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                if (!player.getInventory().contains(23020) || player.getInventory().getAmount(23020) < 1) {
                    return;
                }
                int amt = player.getInventory().getAmount(23020);
                int minutesEXP = 15 * amt;
                int minutesDR = 5 * amt;
                int minutesDMG = 2 * amt;
                StarterTasks.doProgress(player, StarterTasks.StarterTask.CLAIM_VOTES, amt);

                player.getSeasonPass().addExperience(3 * amt);

                player.getDailyTaskManager().submitProgressToIdentifier(1, amt);
                player.getInventory().delete(23020, amt);
                player.getInventory().add(12855, 1000 * amt);
                player.getInventory().add(5022, 1000 * amt);
                player.setMoneyInPouch(player.getMoneyInPouch() + (amt * 100_000_000));
                player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
                player.getPacketSender()
                        .sendMessage("@blu@You are rewarded " + (amt * 5) + " vote "
                                + (amt > 1 ? "points, " : "point, ") + (1000 * amt) + " Upgrade Tokens, and " + (1000 * amt) + " PVM Tickets!");
                player.getPacketSender()
                        .sendMessage("@blu@You received " + minutesEXP + " mins of Bonus XP, " + minutesDR + " mins of +100 DR, " + minutesDMG + " mins of +100% DMG!");
                player.getPointsHandler().incrementVotingPoints(amt * 5);
                BonusExperienceTask.addBonusXp(player, minutesEXP);
                VotingDRBoostTask.addBonusDR(player, minutesDR);
                VotingDMGBoostTask.addBonusDMG(player, minutesDMG);
                player.getClickDelay().reset();
                break;


            case 10138:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                VoteRewardHandler.AFKFISH(player, true);
                break;
            case 17634:
                if (player.busy()) {
                    player.getPacketSender().sendMessage("You can not do this right now.");
                    return;
                }
                VoteRewardHandler.AFKMINE(player, true);
                break;
            case 6500:
                CharmingImp.sendConfig(player);
                break;
            case 4155: // kills-remaining
                player.getPacketSender().sendMessage(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK
                        ? ("You do not have a Slayer task.")
                        : ("You're assigned to kill "
                        + Misc.formatText(
                        player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                        + "s, only " + player.getSlayer().getAmountToSlay() + " more to go."));
                break;
            case 13281:
            case 13282:
            case 13283:
            case 13284:
            case 13285:
            case 13286:
            case 13287:
            case 13288:
            case 18819:
                player.getPacketSender().sendInterfaceRemoval();
                player.getPacketSender().sendMessage(player.getSlayer().getSlayerTask() == SlayerTasks.NO_TASK
                        ? ("You do not have a Slayer task.")
                        : ("You're assigned to kill "
                        + Misc.formatText(
                        player.getSlayer().getSlayerTask().toString().toLowerCase().replaceAll("_", " "))
                        + "s, only " + player.getSlayer().getAmountToSlay() + " more to go."));
                break;
            case 6570:
                if (player.getInventory().contains(6570) && player.getInventory().getAmount(6529) >= 50000) {
                    player.getInventory().delete(6570, 1).delete(6529, 50000).add(19111, 1);
                    player.getPacketSender().sendMessage("You have upgraded your Fire cape into a TokHaar-Kal cape!");
                } else {
                    player.getPacketSender().sendMessage(
                            "You need at least 50.000 Tokkul to upgrade your Fire Cape into a TokHaar-Kal cape.");
                }
                break;
            case 15262:
                if (!player.getClickDelay().elapsed(1300))
                    return;
                amt = player.getInventory().getAmount(15262);
                if (amt > 0)
                    player.getInventory().delete(15262, amt).add(18016, 10000 * amt);
                player.getClickDelay().reset();
                break;
            case 5509:
            case 5510:
            case 5512:
            case 5514:
                RunecraftingPouches.empty(player, RunecraftingPouch.forId(itemId));
                break;
            case 11283: // DFS
                player.getPacketSender()
                        .sendMessage("Your Dragonfire shield has " + player.getDfsCharges() + "/20 dragon-fire charges.");
                break;
        }
    }

    @Override
    public void handleMessage(Player player, Packet packet) {
        if (player.getConstitution() <= 0)
            return;

        switch (packet.getOpcode()) {
            case THIRD_ITEM_ACTION_OPCODE:
                thirdAction(player, packet);
                break;
            case FIRST_ITEM_ACTION_OPCODE:
                firstAction(player, packet);
                break;
            case SECOND_ITEM_ACTION_OPCODE:
                secondAction(player, packet);
                break;
        }
    }

}