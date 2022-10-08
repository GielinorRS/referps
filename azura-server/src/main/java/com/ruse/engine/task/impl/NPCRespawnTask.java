package com.ruse.engine.task.impl;

import com.ruse.engine.task.Task;
import com.ruse.model.Locations.Location;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.skill.impl.hunter.Hunter;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class NPCRespawnTask extends Task {

	public NPCRespawnTask(NPC npc, int respawn, Player killer) {
		super(respawn);
		this.npc = npc;
		this.killer = killer;
	}
	public NPCRespawnTask(NPC npc, int respawn, Player killer, boolean instanceNPC) {
		super(respawn);
		this.npc = npc;
		this.killer = killer;
		this.instanceNPC = instanceNPC;
	}

	final Player killer;
	final NPC npc;
	boolean instanceNPC = false;

	@Override
	public void execute() {
		NPC npc_ = new NPC(npc.getId(), npc.getDefaultPosition());
		if (killer != null && killer.getLocation() != null &&
				(killer.getLocation()  == Location.INSTANCE1
						|| killer.getLocation()  == Location.INSTANCE2) && killer.getCurrentInstanceAmount() == -1) {
			World.deregister(npc_);
			stop();
			return;
		}

		if (killer != null && instanceNPC && (!(killer.getLocation()  == Location.INSTANCE1
				|| killer.getLocation()  == Location.INSTANCE2) ||
				killer.getCurrentInstanceAmount() == (killer.getInstanceManager().ticketID == 23211 ? 120 : 60))){

			World.deregister(npc_);
			stop();
			return;
		}
		npc_.setInstanceID(npc.getInstanceID());

		if (killer != null && instanceNPC && killer.getInstanceID() != npc_.getInstanceID()){
			World.deregister(npc_);
			stop();
			return;
		}

		npc_.getMovementCoordinator().setCoordinator(npc.getMovementCoordinator().getCoordinator());

		if (npc_.getId() == 8022 || npc_.getId() == 8028) { // Desospan, respawn at random locations
			npc_.moveTo(new Position(2595 + Misc.getRandom(12), 4772 + Misc.getRandom(8)));
		} else if (npc_.getId() > 5070 && npc_.getId() < 5081) {
			Hunter.HUNTER_NPC_LIST.add(npc_);
		}
		if (killer != null) {
			if (killer.getRegionInstance() != null) {
				if ((killer.getLocation()  == Location.INSTANCE1 || killer.getLocation()  == Location.INSTANCE2)
						&& npc_.getLocation() == killer.getLocation()) {
					killer.getRegionInstance().getNpcsList().add(npc_);
				}
			}
		}

		World.register(npc_);
		stop();
	}

}
