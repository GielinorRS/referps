package com.ruse.world.content;

import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;

public class AfkStallSystem {
	public static int getLeft() {
		return totalCount - stoleAmount;
		}
	public static String getFormattedStealsLeft() {
		return Misc.insertCommasToNumber(String.valueOf(totalCount - stoleAmount));
	}
	public static final int totalCount = 80000;
	public static int stoleAmount = 0;
		
	public static void spawncommand() {
		NPC npc = new NPC(3779, new Position(2014,4517));
		World.register(npc);
		World.sendMessage("<shad=1>@blu@[EVENT]<img=368>EARTHQUAKE<img=368>@red@ has awoken! teleport to ::earthquake to fight him");
		stoleAmount = 0;
	}
	
	public static void spawnBoss() {
		if(stoleAmount < 80000) {
			return;
		}
		NPC npc = new NPC(3779, new Position(2014,4517));
		World.register(npc);
		World.sendMessage("@blu@<img=368>EARTHQUAKE<img=368>@red@ has awoken! teleport to ::earthquake to fight him");
		stoleAmount = 0;
	}
 }