package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;

public class VotingDMGBoostTask extends Task {

	public VotingDMGBoostTask(final Player player) {
		super(100, player, false);
		this.player = player;
	}

	final Player player;
	int msg;

	@Override
	public void execute() {
		if (player == null || !player.isRegistered()) {
			stop();
			return;
		}
		player.setMinutesVotingDMG(-1, true);
		int newMinutes = player.getMinutesVotingDMG();
		if (newMinutes <= 0) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>Your +100% Damage boost has run out.");
			player.setMinutesVotingDMG(-1, false);
			stop();
		} else if (msg == 2 && newMinutes < 10) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>You have "
					+ Misc.format(player.getMinutesVotingDMG()) + " minutes of +100% Damage boost left.");
		} else if (msg == 4) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>You have "
					+ Misc.format(player.getMinutesVotingDMG()) + " minutes of +100% Damage boost left.");
			msg = 0;
		}
		msg++;
	}

	public static void addBonusDMG(final Player p, int minutes) {
		boolean startEvent = p.getMinutesVotingDMG() <= 0;
		p.setMinutesVotingDMG(startEvent ? (minutes + 1) : minutes, true);
		p.getPacketSender().sendMessage("<img=5> <col=330099>You have " + Misc.format(p.getMinutesVotingDMG())
				+ " minutes of 100% Damage boost left.");
		if (startEvent) {
			TaskManager.submit(new VotingDMGBoostTask(p));
		}
	}
}
