package com.ruse.net.packet.impl;

import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.Sounds;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.skill.impl.dungeoneering.ItemBinding;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.player.Player;

/**
 * This packet listener is called when a player drops an item they have placed
 * in their inventory.
 *
 * @author relex lawl
 */

public class DropItemPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		int id = packet.readUnsignedShortA();
		int interfaceIndex = packet.readUnsignedShort();
		int itemSlot = packet.readUnsignedShortA();
		if (player.getConstitution() <= 0 || player.getInterfaceId() > 0)
			return;
		if (itemSlot < 0 || itemSlot > player.getInventory().capacity())
			return;
		if (player.getConstitution() <= 0 || player.isTeleporting())
			return;
		if (player.isPlayerLocked())
			return;
		Item item = player.getInventory().getItems()[itemSlot];
		if (item == null)
			return;
		if (item.getId() != id) {
			return;
		}
		if (player.getRights() != PlayerRights.DEVELOPER && player.getGameMode() != GameMode.ULTIMATE_IRONMAN){
			player.getPacketSender().sendMessage("You cannot drop items at the moment.");
			return;
		}
		player.getPacketSender().sendInterfaceRemoval();
		player.getCombatBuilder().cooldown(false);
		switch (item.getId()) {
			case 6769:
			case 10942:
			case 10934:
			case 10935:
			case 10943:
			case 10946:
				player.getPacketSender().sendMessage("@red@You can not drop bonds, you can only trade via player owned shops!");
				return;
		}
		if (item.getId() != -1 && item.getAmount() >= 1) {
			destroyItemInterface(player, item);
		}
	}

	public static void destroyItemInterface(Player player, Item item) {// Destroy item created by Remco
		player.setUntradeableDropItem(item);
		String[][] info = { // The info the dialogue gives
				{ "Are you sure you want to discard this item?", "14174" }, { "Yes.", "14175" }, { "No.", "14176" },
				{ "", "14177" }, { "This item will vanish once it hits the floor.", "14182" },
				{ "You cannot get it back if discarded.", "14183" }, { item.getDefinition().getName(), "14184" } };
		player.getPacketSender().sendItemOnInterface(14171, item.getId(), 0, item.getAmount());
		for (int i = 0; i < info.length; i++)
			player.getPacketSender().sendString(Integer.parseInt(info[i][1]), info[i][0]);
		player.getPacketSender().sendChatboxInterface(14170);
	}
}
