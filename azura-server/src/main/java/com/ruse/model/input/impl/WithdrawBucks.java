package com.ruse.model.input.impl;

import com.ruse.model.Item;
import com.ruse.model.input.EnterAmount;
import com.ruse.world.World;
import com.ruse.world.content.MoneyPouch;
import com.ruse.world.entity.impl.player.Player;


public class WithdrawBucks extends EnterAmount {

    @Override
    public void handleAmount(Player player, int amount) {
        // player.getPacketSender().sendInterfaceRemoval();

        if (amount <= 0) {
            return;
        }

        long cost = amount * MoneyPouch.BUCKS_VALUE;

        if (player.getInterfaceId() > 0 && player.getInterfaceId() != 33230) {
            player.getPacketSender()
                    .sendMessage("Please close the interface you have open before opening another one.");
            return;
        }

        if (player.getMoneyInPouch() < cost) {
            amount = (int) (player.getMoneyInPouch() / MoneyPouch.BUCKS_VALUE) ;
            cost = (player.getMoneyInPouch() / MoneyPouch.BUCKS_VALUE) * MoneyPouch.BUCKS_VALUE;
            player.getPA().sendMessage("You don't have enough coins to withdraw that many 1m Tickets.");
        }

        if (player.getInventory().getFreeSlots() == 0 && !player.getInventory().contains(MoneyPouch.BUCKS_ID)) {
            player.getPA().sendMessage("Your inventory is full!");
            return;
        }

        player.setMoneyInPouch(player.getMoneyInPouch() - cost);
        player.getInventory().add(MoneyPouch.BUCKS_ID, amount);
        player.getPacketSender().sendString(8135, "" + player.getMoneyInPouch());
    }
}
