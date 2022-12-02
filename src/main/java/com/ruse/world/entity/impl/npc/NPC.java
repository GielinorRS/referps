package com.ruse.world.entity.impl.npc;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.NPCDeathTask;
import com.ruse.model.DamageDealer;
import com.ruse.model.Direction;
import com.ruse.model.Item;
import com.ruse.model.Locations.Location;
import com.ruse.model.Position;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.JsonLoader;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.effect.CombatPoisonEffect.PoisonType;
import com.ruse.world.content.combat.strategy.CombatStrategies;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.combat.strategy.impl.KalphiteQueen;
import com.ruse.world.content.combat.strategy.impl.Nex;
import com.ruse.world.content.skill.impl.hunter.Hunter;
import com.ruse.world.content.skill.impl.hunter.PuroPuro;
import com.ruse.world.content.skill.impl.runecrafting.DesoSpan;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator.Coordinator;
import com.ruse.world.entity.impl.player.Player;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a non-playable character, which players can interact with.
 *
 * @author Gabriel Hannason
 */

public class NPC extends Character {

    public int totalAttacks;
   /* @Getter
    private List<Position> tiles = new ArrayList<>();*/

    /**
     * INSTANCES
     **/
    private final Position defaultPosition;
    /**
     * INTS
     **/
    private final int id;
    public boolean maxHitDummy;
    private int distance;
    private boolean attackable = true;
    private NPCMovementCoordinator movementCoordinator = new NPCMovementCoordinator(this);
    private Player spawnedFor;
    private NpcDefinition definition;
    /**
     * LISTS
     **/
    private List<DamageDealer> damageDealerMap = new ArrayList<DamageDealer>();
    private List<Item> evenBossRewards = new ArrayList<>();
    private long constitution = 100;
    private long defaultConstitution;
    private int transformationId = -1;
    /**
     * BOOLEANS
     **/
    private boolean[] attackWeakened = new boolean[3], strengthWeakened = new boolean[3];
    private boolean summoningNpc, summoningCombat;
    private boolean isDying;
    private boolean eventBoss;
    private boolean visible = true;
    private boolean healed, chargingAttack;
    private boolean findNewTarget;
    private boolean fetchNewDamageMap;

    @Getter
    @Setter
    private int instanceID;

    public NPC(int id, Position position) {
        super(position);
        NpcDefinition definition = NpcDefinition.forId(id);
        if (definition == null){
            //definition = NpcDefinition.forId(1);
              throw new NullPointerException("NPC " + id + " is not defined!");
        }
        this.defaultPosition = position;
        this.id = id;
        this.definition = definition;
        this.defaultConstitution = definition.getHitpoints() < 100 ? 100 : definition.getHitpoints();
        this.constitution = defaultConstitution;
        setLocation(Location.getLocation(this));
    }

    /**
     * Prepares the dynamic json loader for loading world npcs.
     *
     * @return the dynamic json loader.
     * @throws Exception if any errors occur while preparing for load.
     */
    public static List<NPC> spawnedWorldsNpcs = new ArrayList<>();
    public static List<NPCSpawn> NPCSpawns = new ArrayList<>();

    static class NPCSpawn {
        int id;
        Position position;
        Direction direction;
        Coordinator coordinator;

        public NPCSpawn(int id, Position position, Direction direction, Coordinator coordinator) {
            this.id = id;
            this.position = position;
            this.direction = direction;
            this.coordinator = coordinator;
        }
    }

    public static void init() {
        new JsonLoader() {
            @Override
            public void load(JsonObject reader, Gson builder) {

                int id = reader.get("npc-id").getAsInt();
                Position position = builder.fromJson(reader.get("position").getAsJsonObject(), Position.class);
                Coordinator coordinator = builder.fromJson(reader.get("walking-policy").getAsJsonObject(),
                        Coordinator.class);
                Direction direction = Direction.valueOf(reader.get("face").getAsString());
                NPCSpawns.add(new NPCSpawn(id, position, direction, coordinator));

                NPC npc = new NPC(id, position);
                    npc.movementCoordinator.setCoordinator(coordinator);
                    npc.setDirection(direction);
                    World.register(npc);
                    spawnedWorldsNpcs.add(npc);
                    if (id == 14207 || id == 14208 || id == 14209) {
                        for (int i = 0; i < 10; i++) {
                            Position newPos = position.copy().setZ((i * 4) + 4);
                            NPC newnpc = new NPC(id, newPos);
                            newnpc.movementCoordinator.setCoordinator(coordinator);
                            newnpc.setDirection(direction);
                            World.register(newnpc);
                            spawnedWorldsNpcs.add(npc);
                        }
                    }
                    if ((position.getX() >= 1848 && position.getX() <= 1923 &&
                            position.getY() >= 5179 && position.getY() <= 5253)
                            ||
                            (position.getX() >= 3008 && position.getX() <= 3040 &&
                                    position.getY() >= 5214 && position.getY() <= 5248)
                            ||
                            (position.getX() >= 2345 && position.getX() <= 2395 &&
                                    position.getY() >= 4937 && position.getY() <= 4982)
                            ||
                            (position.getX() >= 2241 && position.getX() <= 2304 &&
                                    position.getY() >= 4991 && position.getY() <= 5055)
                            ||
                            (position.getX() >= 2891 && position.getX() <= 2931 &&
                                    position.getY() >= 2577 && position.getY() <= 2607)
                            ||
                            Location.inLocation(position, Location.LUCIFER)
                            ||
                            Location.inLocation(position, Location.DARK_SUPREME)
                            ||
                            Location.inLocation(position, Location.KRIL)
                    ) {
                        for (int i = 0; i < 6; i++) {
                            Position newPos = position.copy().setZ(position.copy().getZ() + (i * 4) + 4);
                            NPC newnpc = new NPC(id, newPos);
                            newnpc.movementCoordinator.setCoordinator(coordinator);
                            newnpc.setDirection(direction);
                            World.register(newnpc);
                        }
                    }

                if (                            Location.inLocation(position, Location.HOLIDAY_EVENTS)                    ) {
                    for (int i = 0; i < 10; i++) {
                        Position newPos = position.copy().setZ(position.copy().getZ() + (i * 4) + 4);
                        NPC newnpc = new NPC(id, newPos);
                        newnpc.movementCoordinator.setCoordinator(coordinator);
                        newnpc.setDirection(direction);
                        World.register(newnpc);
                    }
                }
                if (                            Location.inLocation(position, Location.TANZANITE)                    ) {
                    for (int i = 1; i < 3; i++) {
                        Position newPos = position.copy().setZ(position.copy().getZ() + (i * 4));
                        NPC newnpc = new NPC(id, newPos);
                        newnpc.movementCoordinator.setCoordinator(coordinator);
                        newnpc.setDirection(direction);
                        World.register(newnpc);
                    }
                }

                    if (id > 5070 && id < 5081) {
                        Hunter.HUNTER_NPC_LIST.add(npc);
                    }
                    position = null;
                    coordinator = null;
                    direction = null;
                }

            @Override
            public String filePath() {
                return "./data/def/json/world_npcs.json";
            }
        }.load();

        Nex.spawn();
        PuroPuro.spawn();
        DesoSpan.spawn();
        KalphiteQueen.spawn(1158, new Position(3485, 9509));
    }

    public static NPC of(int id, Position position) {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * Fields
     */

    public void sequence() {
        getCombatBuilder().process();
        /**
         * HP restoration
         */
		/*if (constitution < defaultConstitution) {//btw this is probably a client side visual bug, if u count the hits, its 1200 total but a client side bug where any npc dies at 128 hp even if it had 100m hp
			if (!isDying) {
				if (getLastCombat()
						.elapsed((id == 2042 || id == 2043 || id == 2044 || id == 13447 || id == 3200 ? 50000 : 5000))
						&& !getCombatBuilder().isAttacking() && !getCombatBuilder().isBeingAttacked() && getLocation() != Location.PEST_CONTROL_GAME
						&& getLocation() != Location.DUNGEONEERING && getLocation() != Location.ZULRAH) {
					setConstitution(constitution + (int) (defaultConstitution * 0.1));
					if (constitution > defaultConstitution)// ZULRAH
						setConstitution(defaultConstitution);
				}
			}
		}*/
    }

    @Override
    public void appendDeath() {
        if (!isDying && !summoningNpc) {
            TaskManager.submit(new NPCDeathTask(this));
            isDying = true;
        }
    }

    @Override
    public long getConstitution() {
        return constitution;
    }

    @Override
    public NPC setConstitution(long constitution) {
        this.constitution = constitution;
        if (this.constitution <= 0)
            appendDeath();
        return this;
    }

    @Override
    public void heal(int heal) {
        if ((this.constitution + heal) > getDefaultConstitution()) {
            setConstitution(getDefaultConstitution());
            return;
        }
        setConstitution(this.constitution + heal);
    }

    @Override
    public int getBaseAttack(CombatType type) {
        return getDefinition().getAttackBonus();
    }

    @Override
    public int getAttackSpeed() {
        return this.getDefinition().getAttackSpeed();
    }

    @Override
    public int getBaseDefence(CombatType type) {

        if (type == CombatType.MAGIC)
            return getDefinition().getDefenceMage();
        else if (type == CombatType.RANGED)
            return getDefinition().getDefenceRange();

        return getDefinition().getDefenceMelee();
    }

    @Override
    public boolean isNpc() {
        return true;
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof NPC && ((NPC) other).getIndex() == getIndex();
    }

    @Override
    public int getSize() {
        return getDefinition().getSize();
    }

    @Override
    public void poisonVictim(Character victim, CombatType type) {
        if (getDefinition().isPoisonous()) {
            CombatFactory.poisonEntity(victim,
                    type == CombatType.RANGED || type == CombatType.MAGIC ? PoisonType.MILD : PoisonType.EXTRA);
        }

    }

    public boolean isAttackable() {
        return attackable;
    }

    public void setAttackable(boolean attackable) {
        this.attackable = attackable;
    }


    @Getter
    @Setter
    private CombatStrategy combatStrategy;

    @Override
    public CombatStrategy determineStrategy() {
        if (getCombatStrategy() != null)
            return getCombatStrategy();
        return CombatStrategies.getStrategy(id);
    }

    public boolean switchesVictim() {
        if (getLocation() == Location.DUNGEONEERING) {
            return true;
        }
        return false;
    }

    public int getAggressionDistance() {
        int distance = 7;

        switch (id) {
            case 2030:
            case 2029:
            case 2028:
            case 2027:
            case 2026:
            case 2025:
                return 15;
        }
        if (Nex.nexMob(id)) {
            distance = 60;
        } else if (id == 2896) {
            distance = 50;
        }
        if (getLocation() == Location.WAVE_MINIGAME)
            return 100;
        return distance;
    }

    public int getAttackDistance() {
        return distance;
    }

    /*
     * Getters and setters
     */

    public void setAttackDistance(int distance) {
        this.distance = distance;
    }

    public int getId() {
        return id;
    }

    public Position getDefaultPosition() {
        return defaultPosition;
    }

    public long getDefaultConstitution() {
        return defaultConstitution;
    }

    public void setDefaultConstitution(long defaultConstitution) {
        this.defaultConstitution = defaultConstitution;
    }

    public int getTransformationId() {
        return transformationId;
    }

    public void setTransformationId(int transformationId) {
        this.transformationId = transformationId;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public List<Item> getEventBossRewards() {
        return evenBossRewards;
    }

    public void setEventBossRewards(List<Item> evenBossRewards) {
        this.evenBossRewards = evenBossRewards;
    }

    public boolean isEventBoss() {
        return eventBoss;
    }

    public void setEventBoss(boolean eventBoss) {
        this.eventBoss = eventBoss;
    }

    /**
     * @return the statsWeakened
     */
    public boolean[] getDefenceWeakened() {
        return attackWeakened;
    }

    public boolean isSummoningNpc() {
        return summoningNpc;
    }

    public void setSummoningNpc(boolean summoningNpc) {
        this.summoningNpc = summoningNpc;
    }

    public boolean isDying() {
        return isDying;
    }

    public void setDying(boolean isDying) {
        this.isDying = isDying;
    }

    /**
     * @return the statsBadlyWeakened
     */
    public boolean[] getStrengthWeakened() {
        return strengthWeakened;
    }

    public NPCMovementCoordinator getMovementCoordinator() {
        return movementCoordinator;
    }

    public NpcDefinition getDefinition() {
        return definition;
    }

    public Player getSpawnedFor() {
        return spawnedFor;
    }

    public NPC setSpawnedFor(Player spawnedFor) {
        this.spawnedFor = spawnedFor;
        return this;
    }

    public Character copy() {
        return this;
    }

    public boolean hasHealed() {
        return healed;
    }

    public void setHealed(boolean healed) {
        this.healed = healed;
    }

    public boolean isChargingAttack() {
        return chargingAttack;
    }

    public NPC setChargingAttack(boolean chargingAttack) {
        this.chargingAttack = chargingAttack;
        return this;
    }

    public boolean findNewTarget() {
        return findNewTarget;
    }

    public void setFindNewTarget(boolean findNewTarget) {
        this.findNewTarget = findNewTarget;
    }

    public boolean summoningCombat() {
        return summoningCombat;
    }

    public void setSummoningCombat(boolean summoningCombat) {
        this.summoningCombat = summoningCombat;
    }

    public void setFetchNewDamageMap(boolean fetchNewDamageMap) {
        this.fetchNewDamageMap = fetchNewDamageMap;
    }

    public boolean fetchNewDamageMap() {
        return fetchNewDamageMap;
    }

    public List<DamageDealer> getDamageDealerMap() {
        return damageDealerMap;
    }

    public void setDamageDealerMap(List<DamageDealer> damageDealerMap) {
        this.damageDealerMap = damageDealerMap;
    }

    public void removeInstancedNpcs(Location loc, int height) {
        int checks = loc.getX().length - 1;
        for (int i = 0; i <= checks; i += 2) {
            if (getPosition().getX() >= loc.getX()[i] && getPosition().getX() <= loc.getX()[i + 1]) {
                if (getPosition().getY() >= loc.getY()[i] && getPosition().getY() <= loc.getY()[i + 1]) {
                    if (getPosition().getZ() == height) {
                        World.deregister(this);
                    }
                }
            }
        }
    }

    public void removeInstancedNpcs(Location loc, int height, Player player) {
        int checks = loc.getX().length - 1;
        for (int i = 0; i <= checks; i += 2) {
            if (getPosition().getX() >= loc.getX()[i] && getPosition().getX() <= loc.getX()[i + 1]) {
                if (getPosition().getY() >= loc.getY()[i] && getPosition().getY() <= loc.getY()[i + 1]) {
                    if (getPosition().getZ() == height && (player == null || (getSpawnedFor() != null && getSpawnedFor().getIndex() == player.getIndex()))) {
                        World.deregister(this);
                    }
                }
            }
        }
    }

    public ObjectArrayList<Player> getClosePlayers(int distance) {
        return World.getNearbyPlayers(getPosition(), distance);
    }

    public ObjectArrayList<NPC> getCloseNpcs(int distance) {
        return World.getNearbyNPCs(getPosition(), distance);
    }
}
