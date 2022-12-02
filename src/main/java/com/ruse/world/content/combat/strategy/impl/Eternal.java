package com.ruse.world.content.combat.strategy.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.Graphic;
import com.ruse.model.Locations;
import com.ruse.model.MessageType;
import com.ruse.model.Position;
import com.ruse.model.Projectile;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class Eternal implements CombatStrategy {

	@Override
	public boolean canAttack(Character entity, Character victim) {
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		return null;
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC Eternal = (NPC) entity;
		if (Eternal.isChargingAttack() || victim.getConstitution() <= 0) {
			return true;
		}
		if (Misc.getRandom(15) <= 2) {
			int hitAmount = 2;
			Eternal.performGraphic(new Graphic(2549));
			Eternal.setConstitution(Eternal.getConstitution() + hitAmount);
			Eternal.getCombatBuilder().setContainer(new CombatContainer(Eternal, victim, 1, 3, CombatType.MAGIC, true));
			((Player) victim).getPacketSender().sendMessage(MessageType.NPC_ALERT,
					"Eternal absorbs his next attack, healing himself a bit.");
		}
		if (Locations.goodDistance(Eternal.getPosition().copy(), victim.getPosition().copy(), 3)
				&& Misc.getRandom(5) <= 3) {
			Eternal.performAnimation(new Animation(Eternal.getDefinition().getAttackAnimation()));
			// Eternal.performAnimation(new Animation(5026));
			Eternal.getCombatBuilder().setContainer(new CombatContainer(Eternal, victim, 1, 1, CombatType.MELEE, true));
			if (Misc.getRandom(10) <= 2) {
				victim.moveTo(new Position(2075 + Misc.getRandom(3), 3226 + Misc.getRandom(3)));
				victim.performAnimation(new Animation(534));
				Eternal.getCombatBuilder()
						.setContainer(new CombatContainer(Eternal, victim, 1, 3, CombatType.MAGIC, true));
				((Player) victim).getPacketSender().sendMessage(MessageType.NPC_ALERT, "You have been knocked back!");
			}
		} else {
			Eternal.setChargingAttack(true);
			Eternal.performAnimation(new Animation(5026));
			Eternal.getCombatBuilder().setContainer(new CombatContainer(Eternal, victim, 1, 3, CombatType.MAGIC, true));
			TaskManager.submit(new Task(1, Eternal, false) {
				int tick = 0;

				@Override
				public void execute() {
					if (tick == 0) {
						new Projectile(Eternal, victim, 1194, 44, 3, 41, 31, 0).sendProjectile();
					} else if (tick == 1) {
						Eternal.setChargingAttack(false);
						stop();
					}
					tick++;
				}
			});
		}
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 3;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MIXED;
	}
}
