package com.ruse.world.content.combat.strategy.impl;

import com.ruse.model.Animation;
import com.ruse.model.Flag;
import com.ruse.model.Item;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.WeaponAnimations;
import com.ruse.model.definitions.WeaponInterfaces.WeaponInterface;
import com.ruse.util.Misc;
import com.ruse.world.content.ammo.SpecialAmmo;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.globalBoss.GlobalBoss;
import com.ruse.world.content.minigames.impl.Dueling;
import com.ruse.world.content.minigames.impl.Dueling.DuelRule;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

/**
 * The default combat strategy assigned to an {@link Character} during a melee
 * based combat session. This is the combat strategy used by all {@link Npc}s by
 * default.
 * 
 * @author lare96
 */
public class DefaultMeleeCombatStrategy implements CombatStrategy {

	@Override
	public boolean canAttack(Character entity, Character victim) {

		if (entity.isPlayer()) {
			Player player = (Player) entity;
			if (Dueling.checkRule(player, DuelRule.NO_MELEE)) {
				player.getPacketSender().sendMessage("Melee-attacks have been turned off in this duel!");
				player.getCombatBuilder().reset(true);
				return false;
			}
		}

		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {

		// Start the performAnimation for this attack.
		startAnimation(entity);

		if (entity.isPlayer()) {
			Player player = (Player) entity;
			int slot = Equipment.AMMUNITION_SLOT;
			if (SpecialAmmo.isAmmo(player)) {
				boolean avas = player.getEquipment().containsAny(20591, 20400, 7995, 23739,22105, 23689,  23717,22109,23595,  23535);

				if (!(avas && Misc.getRandom(4) == 0))
					player.getEquipment().get(slot).decrementAmount();

				if (player.getEquipment().get(slot).getAmount() == 0) {
					player.getPacketSender().sendMessage("You have run out of ammunition!");
					player.getEquipment().set(slot, new Item(-1));
					player.getUpdateFlag().flag(Flag.APPEARANCE);
				}
				player.getEquipment().refreshItems();
			}
		}

		// Create the combat container for this hook.
		return new CombatContainer(entity, victim, 1, CombatType.MELEE, true);
	}

	@Override
	public int attackDelay(Character entity) {

		// The attack speed for the weapon being used.
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		// The default distance for all npcs using melee is 1.
		if (entity.isNpc() || entity instanceof GlobalBoss) {
			return 1;//((NPC) entity).getDefinition().getSize();
		}

		// The default distance for all players is 1, or 2 if they are using a
		// halberd.
		Player player = (Player) entity;
		return 1;
	}

	/**
	 * Starts the performAnimation for the argued entity in the current combat hook.
	 * 
	 * @param entity the entity to start the performAnimation for.
	 */
	private void startAnimation(Character entity) {
		if (entity.isNpc()) {
			NPC npc = (NPC) entity;
			npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));
		} else if (entity.isPlayer()) {
			Player player = (Player) entity;
			if (!player.isSpecialActivated()) {
				player.performAnimation(new Animation(WeaponAnimations.getAttackAnimation(player)));
			} else {
				player.performAnimation(new Animation(player.getFightType().getAnimation()));
			}
		}
	}

	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		return false;
	}

	@Override
	public CombatType getCombatType() {
		return CombatType.MELEE;
	}
}
