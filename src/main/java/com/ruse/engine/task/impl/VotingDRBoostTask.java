package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;

public class VotingDRBoostTask extends Task {

	public VotingDRBoostTask(final Player player) {
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
		player.setMinutesVotingDR(-1, true);
		int newMinutes = player.getMinutesVotingDR();
		if (newMinutes <= 0) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>Your bonus +100 Drop Rate boost has run out.");
			player.setMinutesVotingDR(-1, false);
			stop();
		} else if (msg == 2 && newMinutes < 10) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>You have "
					+ Misc.format(player.getMinutesVotingDR()) + " minutes of +100 Drop Rate boost left.");
		} else if (msg == 4) {
			player.getPacketSender().sendMessage("<img=5> <col=330099>You have "
					+ Misc.format(player.getMinutesVotingDR()) + " minutes of +100 Drop Rate boost left.");
			msg = 0;
		}
		msg++;
	}

	public static void addBonusDR(final Player p, int minutes) {
		boolean startEvent = p.getMinutesVotingDR()  <= 0;
		p.setMinutesVotingDR(startEvent ? (minutes + 1) : minutes, true);
		p.getPacketSender().sendMessage("<img=5> <col=330099>You have " + Misc.format(p.getMinutesVotingDR())
				+ " minutes of +100 Drop Rate boost left.");
		if (startEvent) {
			TaskManager.submit(new VotingDRBoostTask(p));
		}
	}
}
