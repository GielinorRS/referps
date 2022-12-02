package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.world.entity.impl.player.Player;

public class ConvertCoins {

	public static void Bills(Player player) {
		if (player.getInventory().getAmount(995) >= 1_000_000_000) {
			player.getInventory().delete(new Item(995, 1_000_000_000));
			player.getInventory().add(new Item(10835, 1));
			player.sendMessage("You have sucessfully converted your 1b coins to 1b bag");
		} else {
			player.sendMessage("You don't have enough coins to convert to 1b bag");
		}
	}
	
	public static void Mills(Player player) {
		if (player.getInventory().getAmount(995) >= 1_000_000) {
			int amount = player.getInventory().getAmount(995) / 1_000_000;
			player.getInventory().delete(new Item(995, player.getInventory().getAmount(995)));
			//player.getInventory().add(new Item(5021, amount));
			player.sendMessage("You have sucessfully converted your 1m coins to 1m bag");
		} else {
			player.sendMessage("You don't have enough coins to convert to 1m bag");
		}
	}

}
