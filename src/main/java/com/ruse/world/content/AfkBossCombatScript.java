package com.ruse.world.content;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.GroundItem;
import com.ruse.model.Item;
import com.ruse.model.Locations;
import com.ruse.model.Position;
import com.ruse.model.projectile.Projectile;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class AfkBossCombatScript implements CombatStrategy {
	public static final int EARTHQUAKE = 20355;
	private int attack;
	public static Position foodloc = new Position(2908, 5468, 0);
	/**
	 * Attacking melee
	 */
	private static final Animation MELEE = new Animation(430);
	private static final Animation MELEE1 = new Animation(434);
		/**
	 * Attacking mage
	 */
	private static final Animation MAGIC = new Animation(431);
	@Override
	public boolean canAttack(Character entity, Character victim) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public CombatContainer attack(Character entity, Character victim) {
		// TODO Auto-generated method stub
		return null;
	}

	int[] foodIds = new int[] { 5020,17634 };

	private void spawnFood(Player player) {
		/*if (RandomUtility.inclusiveRandom(0, 100) > 65) {
			int pickedFood = foodIds[RandomUtility.exclusiveRandom(0, foodIds.length)];
			
			GroundItemManager.spawnGroundItem(player,
					new GroundItem(new Item(pickedFood,100),
							new Position(foodloc.getX() + Misc.getRandom(10), foodloc.getY() + Misc.getRandom(10), 0),
							"", true, 200, true, 200));
			
		}*/
	}
	//Player player = (Player) victim;
	//spawnFood(player);
	//NPC npc = (NPC) entity;
	//npc.forceChat("IM not dead yet!");
	@Override
	public boolean customContainerAttack(Character entity, Character victim) {
		NPC earthquake = (NPC) entity;
		Player player = (Player) victim;
	//	NPC npc = (NPC) entity;
		//
		
		if (earthquake.isChargingAttack() || earthquake.getConstitution() <= 0) {
			earthquake.getCombatBuilder().setAttackTimer(4);
			return true;
		}
		attack = 0;
		if (attack == 7) {
			Position pos = victim.getPosition().copy();
			earthquake.setChargingAttack(true).getCombatBuilder().setAttackTimer(6);
			new Projectile(earthquake, victim, 1741, 44, 3, 43, 43, 0).sendProjectile();
			spawnFood(player);
			TaskManager.submit(new Task(4, earthquake, false) {

				@Override
				public void execute() {
					if (victim.getPosition().sameAs(pos)) {
						earthquake.getCombatBuilder()
						.setContainer(new CombatContainer(earthquake, victim, 1, 1, CombatType.MAGIC, true));
					}
					earthquake.setChargingAttack(false);
					stop();
					earthquake.performAnimation(MELEE1);
				}
			});
			attack = 0;
		} else {
			if (Locations.goodDistance(earthquake.getPosition().copy(), victim.getPosition().copy(), 1)
					&& Misc.getRandom(5) <= 3) {
				
				spawnFood(player);
				earthquake.getCombatBuilder().setContainer(new CombatContainer(earthquake, victim, 1, 1, CombatType.MELEE, true));
				earthquake.getCombatBuilder().setContainer(new CombatContainer(earthquake, victim, 1, 1, CombatType.MELEE, true));

				earthquake.getCombatBuilder().setContainer(new CombatContainer(earthquake, victim, 1, 1, CombatType.MELEE, true));

				earthquake.performAnimation(MELEE);
			} 
			else {
				spawnFood(player);
				new Projectile(earthquake, victim, 1830 , 44, 3, 43, 43, 0).sendProjectile();
				earthquake.getCombatBuilder()
						.setContainer(new CombatContainer(earthquake, victim, 1, 1, CombatType.MAGIC, true));
				earthquake.performAnimation(MAGIC);
			}
			
		}
		spawnFood(player);
		attack++;
		return true;
	}

	@Override
	public int attackDelay(Character entity) {
		// TODO Auto-generated method stub
		return entity.getAttackSpeed();
	}

	@Override
	public int attackDistance(Character entity) {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public CombatType getCombatType() {
		// TODO Auto-generated method stub
		return CombatType.MELEE;
	}

}
