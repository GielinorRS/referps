package com.ruse.world.content.combat.strategy.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class LuciferCombatScript implements CombatStrategy {
	static List<Position> tiles = new ArrayList<>();
	private final int[] meleeAnims = { 1658,2400 };

	private int getRandomAnimationMelee() {
		return meleeAnims[RandomUtility.exclusiveRandom(0, meleeAnims.length)];
	}

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
		Player player = (Player) victim;
		NPC lucifer = (NPC) entity;

		if (player.getConstitution() <= 0 || entity.getConstitution() <= 0) {
			return true;
		}

		if (lucifer.isChargingAttack() || player.getConstitution() <= 0) {
			return true;
		}

		Animation anim = new Animation(getRandomAnimationMelee());
		Graphic graphics = new Graphic(194);

			lucifer.performAnimation(anim);
			new Projectile(entity, victim, graphics.getId(), 44, 3, 43, 31, 0).sendProjectile();
			lucifer.getCombatBuilder().setContainer(new CombatContainer(lucifer, victim, 1, 1, CombatType.MAGIC, true));
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		return 8;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MELEE;
	}

	@Override
	public boolean ignoreAttackDistance() {
		return true;
	}

	public void removeExitPoints(List<Position> tiles, int hole1, int hole2, int hole3, int hole4) {
		if (tiles.isEmpty()) {
			return;
		}
		if (hole1 < tiles.size()) {
			tiles.remove(hole1);
		}
		if (hole2 < tiles.size()) {
			tiles.remove(hole2);
		}
		if (hole3 < tiles.size()) {
			tiles.remove(hole3);
		}
		if (hole4 < tiles.size()) {
			tiles.remove(hole4);
		}
	}

	static {
		for (int y = 5154; y >= 5139; y--) {
			for (int x = 1945; x <= 1958; x++) {
				tiles.add(new Position(x, y));
			}
		}
	}

}
