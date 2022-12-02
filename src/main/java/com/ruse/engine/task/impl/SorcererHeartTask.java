package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.model.Graphic;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.entity.impl.player.Player;

public class SorcererHeartTask extends Task {
	
	final Player player;
	
	public SorcererHeartTask(Player player) {
		super(1, player, true);
		this.player = player;
	}
	
	@Override
	public void execute() {
		if(player == null || !player.isRegistered()) {
			stop();
			return;
		}

		if (player.getSorcererHeartTimer() == 1000)
			player.getPacketSender().sendEffectTimerSeconds(600, EffectTimer.MAGIC_HEART);

		player.setSorcererHeartTimer(player.getSorcererHeartTimer() - 1);
		
		if(player.getSorcererHeartTimer() <= 0) {
			player.getImbuedHeartCooldown().reset();
			player.getPacketSender().sendMessage("@red@Your Heart of the Sorcerer effect has ended.");
			player.setSorcererHeartTimer(0);
			stop();
		}
	}
}
