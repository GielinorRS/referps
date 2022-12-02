package com.ruse.model.input.impl;

import com.ruse.model.input.EnterAmount;
import com.ruse.world.entity.impl.player.Player;

public class ExchangeX1kTokens extends EnterAmount {

    @Override
    public void handleAmount(Player player, int amount) {
        if (amount < 0) {
            player.sendMessage("This is an invalid amount.");
            return;
        }
        if (amount >= 2147000)
            amount = 2147000;

        int tokensToDelete = amount ;

        if (player.getInventory().getAmount(23794) < tokensToDelete) {
            tokensToDelete = player.getInventory().getAmount(23794) ;
        }
        if ((tokensToDelete * 1000) + player.getInventory().getAmount(12855) >= Integer.MAX_VALUE
        || (tokensToDelete * 1000) + player.getInventory().getAmount(12855) < 0){
            tokensToDelete = (Integer.MAX_VALUE  - player.getInventory().getAmount(12855)) / 1000;
        }

        if (tokensToDelete > 0 && player.getInventory().getAmount(23794) >= tokensToDelete) {
            player.getInventory().delete(23794, tokensToDelete);
            player.getInventory().add(12855, tokensToDelete * 1000);
            player.sendMessage("You successfully exchange " + tokensToDelete + " 1k tokens for " + tokensToDelete * 1000 + " upgrade tokens.");
        } else {
            player.sendMessage("You don't have enough 1k tokens.");
        }
    }

}
