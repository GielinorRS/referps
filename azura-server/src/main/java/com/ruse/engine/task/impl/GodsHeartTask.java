package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.entity.impl.player.Player;

public class GodsHeartTask extends Task {

	final Player player;

	public GodsHeartTask(Player player) {
		super(1, player, true);
		this.player = player;
	}
	
	@Override
	public void execute() {
		if(player == null || !player.isRegistered()) {
			stop();
			return;
		}

		if (player.getGodsHeartTimer() == 1000)
			player.getPacketSender().sendEffectTimer(600, 23494);

		player.setGodsHeartTimer(player.getGodsHeartTimer() - 1);
		
		if(player.getGodsHeartTimer() <= 0) {
			player.getImbuedHeartCooldown().reset();
			player.getPacketSender().sendMessage("@red@Your Heart of the Gods effect has ended.");
			player.setGodsHeartTimer(0);
			stop();
		}
	}
}
