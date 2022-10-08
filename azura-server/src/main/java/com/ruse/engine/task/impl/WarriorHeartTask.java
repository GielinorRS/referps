package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.model.Graphic;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.entity.impl.player.Player;

public class WarriorHeartTask extends Task {
	
	final Player player;
	
	public WarriorHeartTask(Player player) {
		super(1, player, true);
		this.player = player;
	}
	
	@Override
	public void execute() {
		if(player == null || !player.isRegistered()) {
			stop();
			return;
		}

		if (player.getWarriorHeartTimer() == 1000)
			player.getPacketSender().sendEffectTimerSeconds(600, EffectTimer.MELEE_HEART);

		player.setWarriorHeartTimer(player.getWarriorHeartTimer() - 1);
		
		if(player.getWarriorHeartTimer() <= 0) {
			player.getImbuedHeartCooldown().reset();
			player.getPacketSender().sendMessage("@red@Your Heart of the Warrior effect has ended.");
			player.setWarriorHeartTimer(0);
			stop();
		}
	}
}
