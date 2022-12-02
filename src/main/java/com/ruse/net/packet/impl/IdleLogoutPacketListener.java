package com.ruse.net.packet.impl;

import com.ruse.model.PlayerRights;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

//CALLED EVERY 3 MINUTES OF INACTIVITY

public class IdleLogoutPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {
		/*
		if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR
				|| player.getRights() == PlayerRights.MANAGER
			 || player.getRights() == PlayerRights.DEVELOPER)
			return;*/


		if (player.getCombatBuilder().isAttacking()) {
			player.setHouseServant(player.getHouseServant() + 1);
			if (player.getHouseServant() % 2 ==0) {
				World.sendStaffMessage("<col=FF0066><img=2> [IDLE]<col=6600FF> " + player.getUsername()
						+ " has been idled for more than @red@" + (int)(2.5D * player.getHouseServant()) + "<col=6600FF> minutes (IN COMBAT). ");
				if (player.getHouseServant() % 4 == 0){
					player.getAfkCombatChecker().openQuestions();
				}
			}
		}

		/*
		 * if(player.logout() &&
		 * (player.getSkillManager().getSkillAttributes().getCurrentTask() == null ||
		 * !player.getSkillManager().getSkillAttributes().getCurrentTask().isRunning()))
		 * { World.removePlayer(player); }
		 */
		player.setInactive(true);
	}
}
