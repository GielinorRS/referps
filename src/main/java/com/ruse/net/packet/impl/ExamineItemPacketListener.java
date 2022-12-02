package com.ruse.net.packet.impl;

import com.ruse.model.Item;
import com.ruse.model.Skill;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.util.Misc;
import com.ruse.world.content.CustomDropUtils;
import com.ruse.world.content.pos.POSItemPrice;
import com.ruse.world.content.priceGuide.PriceGuideData;
import com.ruse.world.content.skill.impl.herblore.FinishedPotions;
import com.ruse.world.entity.impl.player.Player;

public class ExamineItemPacketListener implements PacketListener {

    private static final PriceGuideData values[] = PriceGuideData.values();

    @Override
    public void handleMessage(Player player, Packet packet) {
        int item = packet.readShort();
        if (item == 995 || item == 18201) {
            player.getPacketSender().sendMessage("Mhmm... Shining coins...");
            return;
        }
        if (item == 5022) {
            int amount;
            if ((amount = player.getInventory().getAmount(5022)) > 0) {
                player.sendMessage(amount+" PVM Tickets");
                return;
            }
        }
        if (item == 12855) {
            player.getPacketSender().sendMessage("Upgrade tokens in inventory: " +Misc.insertCommasToNumber( player.getInventory().getAmount(12855)));
            return;
        }

        if (ItemDefinition.forId(item) != null && ItemDefinition.forId(item).getName() != null
                && ItemDefinition.forId(item).getName().toLowerCase().contains("(unf)")) {
            for (int i = 0; i < FinishedPotions.values().length; i++) {
                if (item == FinishedPotions.values()[i].getUnfinishedPotion()) {
                    player.getPacketSender().sendMessage("Finish this potion with a "
                            + ItemDefinition.forId(FinishedPotions.values()[i].getItemNeeded()).getName() + ".");
                    return;
                }
            }
        }
        if (item == 12926 || item == 12934) {
            ItemDefinition itemDef = ItemDefinition.forId(item);
            if (itemDef != null) {
                player.getPacketSender().sendMessage("@gre@<shad=0>You currently have "
                        + Misc.format(player.getBlowpipeCharges()) + " Zulrah scales stored.");
            }
        }
        ItemDefinition itemDef = ItemDefinition.forId(item);
        if (itemDef != null) {

            if (!itemDef.getDescription().equalsIgnoreCase("null"))
             player.getPacketSender().sendMessage(itemDef.getDescription());

            for (Skill skill : Skill.values()) {
                if (itemDef.getRequirement()[skill.ordinal()] > player.getSkillManager().getMaxLevel(skill)) {
                    player.getPacketSender().sendMessage("@red@Attention: You need "
                            + new StringBuilder()
                            .append(skill.getName().startsWith("a") || skill.getName().startsWith("e")
                                    || skill.getName().startsWith("i") || skill.getName().startsWith("o")
                                    || skill.getName().startsWith("u") ? "an " : "a ")
                            .toString()
                            + Misc.formatText(skill.getName()) + " level of at least "
                            + itemDef.getRequirement()[skill.ordinal()] + " to wear this.");
                }
            }
        }
        handleExaminationInterface(player, item);

        int dropRate = CustomDropUtils.getDropRate(item);

        if (dropRate > 0) {
            player.sendMessage("@blu@" + itemDef.getName() + " drop rate: " + dropRate + "%");
        }

    }

    public void handleExaminationInterface(Player player, int itemId) {
        ItemDefinition itemDef = ItemDefinition.forId(itemId);
        int price = POSItemPrice.getPrice(itemId);
        if (Item.tradeable(itemId)){
        if (price != 0) {
            player.sendMessage("<img=30><col=06195e>[Price Check] <col=5e0606>" + itemDef.getName()
                    + ": <col=06195e>Value: <col=065e16> <shad>" + Misc.insertCommasToNumber(POSItemPrice.getPrice(itemId)) + "</shad> Upgrade tokens.");
        } else {
            player.sendMessage("<img=30><col=06195e>[Price Check] <col=5e0606>" + itemDef.getName()
                    + ": <col=06195e>Value: <col=065e16>Never been sold in the POS.");
        }}else{
            player.sendMessage("<img=30><col=06195e>[Price Check] <col=5e0606>" + itemDef.getName()
                    + ": <col=065e16>Untradeable.");
        }
    }

    public static String formatCoins(int amount) {
        if (amount > 9999 && amount <= 9999999) {
            return (amount / 1000) + "K";
        } else if (amount > 9999999 && amount <= 999999999) {
            return (amount / 1000000) + "M";
        } else if (amount > 999999999) {
            return (amount / 1000000000) + "B";
        }
        return String.valueOf(amount);
    }

}
