package com.ruse.model.input.impl;

import com.ruse.model.Item;
import com.ruse.model.container.ItemContainer;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.input.EnterAmount;
import com.ruse.world.content.events.PartyChest;
import com.ruse.world.entity.impl.player.Player;

public class EnterAmountToBank extends EnterAmount {

	public EnterAmountToBank(int item, int slot) {
		super(item, slot);
	}

	@Override
	public void handleAmount(Player player, int amount) {
		if (!player.isBanking()) {
				if (player.getInterfaceId() == 146000) {
					int item = player.getInventory().getItems()[getSlot()].getId();
					if (item != getItem())
						return;
					int invAmount = player.getInventory().getAmount(item);
					if (amount > invAmount)
						amount = invAmount;

					PartyChest.addItem(player, new Item(item, amount));
					player.getPacketSender().sendItemContainer(5064, player.getInventory());
					PartyChest.refreshInterface(player);
				}
			return;
		}
		int item = player.getInventory().getItems()[getSlot()].getId();
		if (item != getItem())
			return;
		int invAmount = player.getInventory().getAmount(item);
		if (amount > invAmount)
			amount = invAmount;
		if (amount <= 0)
			return;
		ItemContainer container = player.getInterfaceId() == 106000 ? player.getGroupIronmanBank() : player.getBank(Bank.getTabForItem(player, item));
		player.getInventory().switchItem(container, new Item(item, amount),
				getSlot(), true, true);
		if (player.getGroupIronmanBank() != null)
			player.getGroupIronmanBank().refreshItems();
		player.getInventory().refreshItems();
	}
}
