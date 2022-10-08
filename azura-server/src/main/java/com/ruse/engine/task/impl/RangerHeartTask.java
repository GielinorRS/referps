package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.model.Graphic;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.entity.impl.player.Player;

public class RangerHeartTask extends Task {
	
	final Player player;
	
	public RangerHeartTask(Player player) {
		super(1, player, true);
		this.player = player;
	}
	
	@Override
	public void execute() {
		if(player == null || !player.isRegistered()) {
			stop();
			return;
		}

		if (player.getRangerHeartTimer() == 1000)
			player.getPacketSender().sendEffectTimerSeconds(600, EffectTimer.RANGED_HEART);

		player.setRangerHeartTimer(player.getRangerHeartTimer() - 1);
		
		if(player.getRangerHeartTimer() <= 0) {
			player.getImbuedHeartCooldown().reset();
			player.getPacketSender().sendMessage("@red@Your Heart of the Ranger effect has ended.");
			player.setRangerHeartTimer(0);
			stop();
		}
	}
}
