package com.ruse.world.content;

import com.ruse.model.Position;
import com.ruse.world.World;
import com.ruse.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruse.world.entity.impl.npc.NPC;

public class GokuSystem {
	public static int getKillsLeft() {
		return 25000 - npckills;
	}
	public static final int totalCount = 10000;
	public static int npckills = 0;
	public static boolean alive = true;


	public static void spawnBoss() {
		if(npckills < 25000) {
			return;
		}
		NPC npc = new NPC(187, new Position(2868, 2867));
		World.register(npc);
		Dungeoneering.raidCount = 0;
		World.sendMessage("<img=1354><col=92370f><shad=1>GOKU HAS ARRIVED!@bla@ go kill him before he destroys the planet at@blu@ ::goku@bla@");
		npckills = 0;
		alive = true;
	}
	public static void spawn() {
		NPC npc = new NPC(187, new Position(2868, 2867));
		World.register(npc);
		World.sendMessage("<img=1354><col=92370f><shad=1>[GOKU EVENT] Goku has just spawned! type @or2@::goku!");
		alive = true;
	}




	public static void callBoss() {

	}
}
