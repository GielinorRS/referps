package com.ruse.world.content.skill.impl.slayer;

import com.ruse.model.Position;
import com.ruse.model.Skill;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

@Getter
public enum TaskType {

	EASY(1, 925, new Position(2598, 2521)),
	MEDIUM(1, 925, new Position(2598, 2521)),
	HARD(1, 925, new Position(2598, 2521)),
	BOSS_SLAYER(1, 9000, new Position(2667, 4015))
	;

	TaskType(int slayerReq, int npcId, Position telePosition) {
		this.slayerReq = slayerReq;
		this.npcId = npcId;
		this.position = telePosition;
	}

	private int slayerReq;
	private int npcId;
	private Position position;

	public static TaskType forId(int id) {
		for (TaskType master : TaskType.values()) {
			if (master.ordinal() == id) {
				return master;
			}
		}
		return null;
	}

	public static void changeSlayerMaster(Player player, TaskType master) {
		player.getPacketSender().sendInterfaceRemoval();

		int level = master.getSlayerReq();
		if (master == TaskType.EASY)
			level = 1;

		if (player.getSkillManager().getCurrentLevel(Skill.SLAYER) < level) {
			player.getPacketSender().sendMessage("You need a Slayer level of at least " + master.getSlayerReq()
					+ " to use " + master.toString().toLowerCase() + ".");
			player.getPacketSender().sendInterfaceRemoval();
			return;
		}


		player.getSlayer().setTaskType(master);
		PlayerPanel.refreshPanel(player);
	}
}
