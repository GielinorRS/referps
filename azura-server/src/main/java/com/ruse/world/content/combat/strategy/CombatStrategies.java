package com.ruse.world.content.combat.strategy;

import java.util.HashMap;
import java.util.Map;

import com.ruse.world.content.combat.strategy.impl.*;
import com.ruse.world.content.AfkBossCombatScript;
import com.ruse.world.content.GokuBossCombatScript;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.DemonSpawn;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.HellishBull;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.MidnightOrk;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.MysticWerewolf;


public class CombatStrategies {

	private static final DefaultMeleeCombatStrategy defaultMeleeCombatStrategy = new DefaultMeleeCombatStrategy();
	private static final DefaultMagicCombatStrategy defaultMagicCombatStrategy = new DefaultMagicCombatStrategy();
	private static final DefaultRangedCombatStrategy defaultRangedCombatStrategy = new DefaultRangedCombatStrategy();
	private static final Map<Integer, CombatStrategy> STRATEGIES = new HashMap<Integer, CombatStrategy>();
	private static final EmptyCombatStrategy emptyCombatStrategy = new EmptyCombatStrategy();

	public static void init() {
		DefaultMagicCombatStrategy defaultMagicStrategy = new DefaultMagicCombatStrategy();
		STRATEGIES.put(14209, defaultMagicStrategy);
		STRATEGIES.put(13, defaultMagicStrategy);
		STRATEGIES.put(172, defaultMagicStrategy);
		STRATEGIES.put(174, defaultMagicStrategy);
		STRATEGIES.put(2025, defaultMagicStrategy);
		STRATEGIES.put(3495, defaultMagicStrategy);
		STRATEGIES.put(3496, defaultMagicStrategy);
		STRATEGIES.put(3491, defaultMagicStrategy);
		STRATEGIES.put(2882, defaultMagicStrategy);
		STRATEGIES.put(13451, defaultMagicStrategy);
		STRATEGIES.put(13452, defaultMagicStrategy);
		STRATEGIES.put(13453, defaultMagicStrategy);
		STRATEGIES.put(13454, defaultMagicStrategy);
		STRATEGIES.put(1643, defaultMagicStrategy);
		STRATEGIES.put(6254, defaultMagicStrategy);
		STRATEGIES.put(6257, defaultMagicStrategy);
		STRATEGIES.put(6278, defaultMagicStrategy);
		STRATEGIES.put(6221, defaultMagicStrategy);
		STRATEGIES.put(109, defaultMagicStrategy);
		STRATEGIES.put(3580, defaultMagicStrategy);
		STRATEGIES.put(9031, defaultMagicStrategy);
		STRATEGIES.put(2007, defaultMagicStrategy);
		STRATEGIES.put(1234, defaultMagicStrategy);
		STRATEGIES.put(10021, defaultMagicStrategy);
		DefaultRangedCombatStrategy defaultRangedStrategy = new DefaultRangedCombatStrategy();
		STRATEGIES.put(14207, defaultRangedStrategy);
		STRATEGIES.put(688, defaultRangedStrategy);
		STRATEGIES.put(2028, defaultRangedStrategy);
		STRATEGIES.put(6220, defaultRangedStrategy);
		STRATEGIES.put(6256, defaultRangedStrategy);
		STRATEGIES.put(6276, defaultRangedStrategy);
		STRATEGIES.put(6252, defaultRangedStrategy);
		STRATEGIES.put(1233, defaultRangedStrategy);
		STRATEGIES.put(27, defaultRangedStrategy);
		STRATEGIES.put(10020, defaultRangedStrategy);
		STRATEGIES.put(7553, new TheGeneral());
		STRATEGIES.put(9176, new SkeletalHorror());
		STRATEGIES.put(8000, new Galvek());
		STRATEGIES.put(8002, new Galvek());
		STRATEGIES.put(2745, new Jad());
		STRATEGIES.put(3112, new Jad());
		STRATEGIES.put(8528, new Nomad());
		STRATEGIES.put(252, new FriezaCombatScript());
		STRATEGIES.put(449, new FriezaCombatScript());
		STRATEGIES.put(452, new FriezaCombatScript());
		STRATEGIES.put(8349, new TormentedDemon());
		STRATEGIES.put(3200, new ChaosElemental());
		STRATEGIES.put(4540, new BandosAvatar());
		STRATEGIES.put(8133, new CorporealBeast());
		STRATEGIES.put(13447, new Nex());
		STRATEGIES.put(2896, new Spinolyp());
		STRATEGIES.put(2881, new DagannothSupreme());
		STRATEGIES.put(6260, new Graardor());
		STRATEGIES.put(6263, new Steelwill());
		STRATEGIES.put(6265, new Grimspike());
		STRATEGIES.put(6222, new KreeArra());
		STRATEGIES.put(6223, new WingmanSkree());
		STRATEGIES.put(6225, new Geerin());
		STRATEGIES.put(6206, new Gritch());
		STRATEGIES.put(6247, new Zilyana());
		STRATEGIES.put(6250, new Growler());
		STRATEGIES.put(1382, new Glacor());
		STRATEGIES.put(9939, new PlaneFreezer());
		STRATEGIES.put(2010, new Kilik());
		STRATEGIES.put(199, new Brandon());
		STRATEGIES.put(200, new Crimson());
		STRATEGIES.put(2042, new ZulrahLogic());
		STRATEGIES.put(2043, new ZulrahLogic());
		STRATEGIES.put(2044, new ZulrahLogic());
		STRATEGIES.put(795, new IceQueen());
		// STRATEGIES.put(286, new MutantKFC());
		Dragon dragonStrategy = new Dragon();
		STRATEGIES.put(8015, new Dragon2());

		STRATEGIES.put(9892, dragonStrategy);
		STRATEGIES.put(9893, dragonStrategy);
		STRATEGIES.put(9894, dragonStrategy);
		STRATEGIES.put(50, dragonStrategy);
		STRATEGIES.put(941, dragonStrategy);
		STRATEGIES.put(55, dragonStrategy);
		STRATEGIES.put(53, dragonStrategy);
		STRATEGIES.put(54, dragonStrategy);
		STRATEGIES.put(51, dragonStrategy);
		STRATEGIES.put(1590, dragonStrategy);
		STRATEGIES.put(1591, dragonStrategy);
		STRATEGIES.put(2012, dragonStrategy);
		STRATEGIES.put(1592, dragonStrategy);
		STRATEGIES.put(5362, dragonStrategy);
		STRATEGIES.put(5363, dragonStrategy);

		Aviansie aviansieStrategy = new Aviansie();
		STRATEGIES.put(6246, aviansieStrategy);
		STRATEGIES.put(6230, aviansieStrategy);
		STRATEGIES.put(6231, aviansieStrategy);

		KalphiteQueen kalphiteQueenStrategy = new KalphiteQueen();
		STRATEGIES.put(1158, kalphiteQueenStrategy);
		STRATEGIES.put(1160, kalphiteQueenStrategy);

		Revenant revenantStrategy = new Revenant();
		STRATEGIES.put(13465, revenantStrategy);
		STRATEGIES.put(13469, revenantStrategy);
		STRATEGIES.put(13474, revenantStrategy);
		STRATEGIES.put(13478, revenantStrategy);
		STRATEGIES.put(13479, revenantStrategy);
		STRATEGIES.put(6692, dragonStrategy);
		STRATEGIES.put(408, dragonStrategy);
		STRATEGIES.put(401, dragonStrategy);
		STRATEGIES.put(4972, new Eternal());
		STRATEGIES.put(2009, new Callisto());
		STRATEGIES.put(2000, new Venenatis());
		STRATEGIES.put(2006, new Vetion());
		STRATEGIES.put(2001, new Scorpia());
		STRATEGIES.put(12239, new Exoden());
		STRATEGIES.put(3975, new MaxHitStone());
		STRATEGIES.put(8009, new NazastaroolCombatStrategy());
		STRATEGIES.put(2, new Tekton());
		STRATEGIES.put(3831, new AzureBeast());
		STRATEGIES.put(2949, new ScarletFalcon());
		STRATEGIES.put(2342, new HerbalRogue());
		STRATEGIES.put(3830, new OptimusPrime());
		STRATEGIES.put(1120,new Vasa());
		STRATEGIES.put(NazastaroolCombatStrategy.NAZASTAROOL, new NazastaroolCombatStrategy());
		STRATEGIES.put(DrakeCombatStrategy.DRAKE, new DrakeCombatStrategy());
		STRATEGIES.put(3779, new AfkBossCombatScript());
		STRATEGIES.put(187, new GokuBossCombatScript());
		STRATEGIES.put(12810, new InfernalDemon());
		STRATEGIES.put(9012, new LuciferCombatScript());
		STRATEGIES.put(438, new DarkSupremeCombatScript());
		STRATEGIES.put(9813, new DarkSupremeCombatScript());
		STRATEGIES.put(9014, new IronBossCombatScript());
		STRATEGIES.put(9017, new ZenyteBossCombatScript());
	//	STRATEGIES.put(9020, new ArmouredBunnyCombatScript());



		STRATEGIES.put(9815, new SimpleScript());
		STRATEGIES.put(9816, new SimpleScript());
		STRATEGIES.put(9817, new SimpleScript());
		STRATEGIES.put(9818, new SimpleScript());


		STRATEGIES.put(9882, new MidnightOrk());
		STRATEGIES.put(9884, new HellishBull());
		STRATEGIES.put(9883, new DemonSpawn());
		STRATEGIES.put(9881, new MysticWerewolf());

	}

	public static CombatStrategy getStrategy(int npc) {
		if (STRATEGIES.get(npc) != null) {
			return STRATEGIES.get(npc);
		}
		return defaultMeleeCombatStrategy;
	}

	public static CombatStrategy getDefaultMeleeStrategy() {
		return defaultMeleeCombatStrategy;
	}

	public static CombatStrategy getDefaultMagicStrategy() {
		return defaultMagicCombatStrategy;
	}

	public static CombatStrategy getDefaultRangedStrategy() {
		return defaultRangedCombatStrategy;
	}

	public static CombatStrategy getEmptyCombatStrategy() {
		return emptyCombatStrategy;
	}
}
