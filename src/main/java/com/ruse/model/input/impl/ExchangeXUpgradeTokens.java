package com.ruse.model.input.impl;

import com.ruse.model.input.EnterAmount;
import com.ruse.world.content.skill.impl.summoning.PouchMaking;
import com.ruse.world.entity.impl.player.Player;

public class ExchangeXUpgradeTokens extends EnterAmount {

    @Override
    public void handleAmount(Player player, int amount) {
        if (amount < 0) {
            player.sendMessage("This is an invalid amount.");
            return;
        }
        if (amount >= 2147000)
            amount = 2147000;

        int tokensToDelete = amount * 1000;

        if (player.getInventory().getAmount(12855) < tokensToDelete) {
            tokensToDelete = player.getInventory().getAmount(12855) / 1000 * 1000;
        }

        if (tokensToDelete > 0 && player.getInventory().getAmount(12855) >= tokensToDelete) {
            player.getInventory().delete(12855, tokensToDelete);
            player.getInventory().add(23794, tokensToDelete / 1000);
            player.sendMessage("You successfully exchange " + tokensToDelete + " upgrade tokens for " + tokensToDelete / 1000 + " 1k tokens.");
        } else {
            player.sendMessage("You don't have enough Upgrade tokens.");
        }
    }

}
