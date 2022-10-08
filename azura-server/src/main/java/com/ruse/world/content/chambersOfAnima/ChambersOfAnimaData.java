package com.ruse.world.content.chambersOfAnima;

import com.ruse.model.Position;
import com.ruse.world.entity.impl.npc.NPC;

public class ChambersOfAnimaData {

	public static final Position lobbyPosition = new Position(3253, 2931, 0);

	public static final NPC[] npcs = new NPC[]{
			new NPC(9882, new Position(1744, 5130)),
			new NPC(9884, new Position(1741, 5162)),
			new NPC(9883, new Position(1773, 5163)),
			new NPC(9881, new Position(1764, 5196))
	};
	public static final int[] portalYPos = new int[]{
			2890,
			2898,
			2906
	};

}
