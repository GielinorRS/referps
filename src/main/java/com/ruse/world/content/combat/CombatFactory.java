package com.ruse.world.content.combat;

import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.engine.task.impl.CombatSkullEffect;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.model.movement.MovementQueue;
import com.ruse.util.Misc;
import com.ruse.world.clip.region.DirectionFace;
import com.ruse.world.clip.region.RegionClipping;
import com.ruse.world.content.BonusManager;
import com.ruse.world.content.ItemDegrading;
import com.ruse.world.content.ItemDegrading.DegradingItem;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.Kraken.KrakenInstance;
import com.ruse.world.content.NpcRequirements;
import com.ruse.world.content.combat.effect.CombatPoisonEffect;
import com.ruse.world.content.combat.effect.CombatPoisonEffect.PoisonType;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.combat.strategy.impl.Nex;
import com.ruse.world.content.combat.strategy.impl.Scorpia;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.combat.weapon.FightStyle;
import com.ruse.world.content.minigames.impl.PestControl;
import com.ruse.world.content.seasonpass.SeasonPass;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator.CoordinateState;
import com.ruse.world.entity.impl.npc.impl.HoVMobs.Heimdall;
import com.ruse.world.entity.impl.npc.impl.HoVMobs.Kiljaeden;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.route.RouteFinder;
import com.ruse.world.entity.impl.player.route.strategy.EntityStrategy;
import com.world.content.globalBoss.CelestialZone;
import com.world.content.globalBoss.VoteBoss;

import java.util.Optional;

import static com.ruse.world.content.combat.CombatType.MAGIC;
import static com.ruse.world.content.combat.CombatType.RANGED;

/**
 * A static factory class containing all miscellaneous methods related to, and
 * used for combat.
 *
 * @author lare96
 * @author Scu11
 * @author Graham
 */
public final class CombatFactory {

    /**
     * The amount of time it takes for cached damage to timeout.
     */
    // Damage cached for currently 60 seconds will not be accounted for.
    public static final long DAMAGE_CACHE_TIMEOUT = 60000;

    /**
     * The amount of damage that will be drained by combat protection prayer.
     */
    // Currently at .20 meaning 20% of damage drained when using the right
    // protection prayer.
    public static final double PRAYER_DAMAGE_REDUCTION = .20;

    /**
     * The rate at which accuracy will be reduced by combat protection prayer.
     */
    // Currently at .255 meaning 25.5% percent chance of canceling damage when
    // using the right protection prayer.
    public static final double PRAYER_ACCURACY_REDUCTION = .255;

    /**
     * The amount of hitpoints the redemption prayer will heal.
     */
    // Currently at .25 meaning hitpoints will be healed by 25% of the remaining
    // prayer points when using redemption.
    public static final double REDEMPTION_PRAYER_HEAL = .25;

    /**
     * The maximum amount of damage inflicted by retribution.
     */
    // Damage between currently 0-15 will be inflicted if in the specified
    // radius when the retribution prayer effect is activated.
    public static final int MAXIMUM_RETRIBUTION_DAMAGE = 150;

    /**
     * The radius that retribution will hit players in.
     */
    // All players within currently 5 squares will get hit by the retribution
    // effect.
    public static final int RETRIBUTION_RADIUS = 5;

    /**
     * The default constructor, will throw an {@link UnsupportedOperationException}
     * if instantiated.
     */
    private CombatFactory() {
        throw new UnsupportedOperationException("This class cannot be instantiated!");
    }

    /**
     * Determines if the entity is wearing full veracs.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full veracs.
     */
    public static boolean fullVeracs(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Verac the Defiled")
                : ((Player) entity).getEquipment().containsAll(4753, 4757, 4759, 4755);
    }

    /**
     * Determines if the entity is wearing full dharoks.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full dharoks.
     */
    public static boolean fullDharoks(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Dharok the Wretched")
                : ((Player) entity).getEquipment().containsAll(4716, 4720, 4722, 4718);
    }

    /**
     * Determines if the entity is wearing full karils.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full karils.
     */
    public static boolean fullKarils(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Karil the Tainted")
                : ((Player) entity).getEquipment().containsAll(4732, 4736, 4738, 4734);
    }

    /**
     * Determines if the entity is wearing full ahrims.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full ahrims.
     */
    public static boolean fullAhrims(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Ahrim the Blighted")
                : ((Player) entity).getEquipment().containsAll(4708, 4712, 4714, 4710);
    }

    /**
     * Determines if the entity is wearing full torags.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full torags.
     */
    public static boolean fullTorags(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Torag the Corrupted")
                : ((Player) entity).getEquipment().containsAll(4745, 4749, 4751, 4747);
    }

    /**
     * Determines if the entity is wearing full guthans.
     *
     * @param entity the entity to determine this for.
     * @return true if the player is wearing full guthans.
     */
    public static boolean fullGuthans(Character entity) {
        return entity.isNpc() ? ((NPC) entity).getDefinition().getName().equals("Guthan the Infested")
                : ((Player) entity).getEquipment().containsAll(4724, 4728, 4730, 4726);
    }

    /**
     * Determines if the player is wielding a crystal bow.
     *
     * @param player the player to determine for.
     * @return true if the player is wielding a crystal bow.
     */
    public static boolean crystalBow(Player player) {
        Item item = player.getEquipment().get(Equipment.WEAPON_SLOT);
        if (item == null)
            return false;
        return item.getDefinition().getName().toLowerCase().contains("crystal bow");
    }

    public static boolean toxicblowpipe(Player p) {
        Item item = p.getEquipment().get(Equipment.WEAPON_SLOT);
        if (item == null)
            return false;
        return item.getDefinition().getName().toLowerCase().equalsIgnoreCase("toxic blowpipe");
    }

    public static boolean zarytebow(Player p) {
        Item item = p.getEquipment().get(Equipment.WEAPON_SLOT);
        if (item == null)
            return false;
        return item.getDefinition().getName().toLowerCase().equalsIgnoreCase("zaryte bow");
    }

    /**
     * Determines if the player is wielding a dark bow.
     *
     * @param player the player to determine for.
     * @return true if the player is wielding a dark bow.
     */
    public static boolean darkBow(Player player) {
        Item item = player.getEquipment().get(Equipment.WEAPON_SLOT);
        if (item == null)
            return false;
        return item.getDefinition().getName().toLowerCase().contains("dark bow");
    }

    /**
     * Determines if the player has arrows equipped.
     *
     * @param player the player to determine for.
     * @return true if the player has arrows equipped.
     */
    public static boolean arrowsEquipped(Player player) {
        Item item;
        if ((item = player.getEquipment().get(Equipment.AMMUNITION_SLOT)) == null) {
            return false;
        }

        return !(!item.getDefinition().getName().endsWith("arrow") && !item.getDefinition().getName().endsWith("arrowp")
                && !item.getDefinition().getName().endsWith("arrow(p+)")
                && !item.getDefinition().getName().endsWith("arrow(p++)"));
    }

    /**
     * Determines if the player has bolts equipped.
     *
     * @param player the player to determine for.
     * @return true if the player has bolts equipped.
     */
    public static boolean boltsEquipped(Player player) {
        Item item;
        if ((item = player.getEquipment().get(Equipment.AMMUNITION_SLOT)) == null) {
            return false;
        }
        return item.getDefinition().getName().toLowerCase().contains("bolts");
    }

    /**
     * Attempts to poison the argued {@link Character} with the argued
     * {@link PoisonType}. This method will have no effect if the entity is already
     * poisoned.
     *
     * @param entity     the entity that will be poisoned, if not already.
     * @param poisonType the poison type that this entity is being inflicted with.
     */
    public static void poisonEntity(Character entity, Optional<PoisonType> poisonType) {

        // We are already poisoned or the poison type is invalid, do nothing.
        if (entity.isPoisoned() || !poisonType.isPresent()) {
            return;
        }

        // If the entity is a player, we check for poison immunity. If they have
        // no immunity then we send them a message telling them that they are
        // poisoned.
        if (entity.isPlayer()) {
            Player player = (Player) entity;
            if (player.getPoisonImmunity() > 0)
                return;
            player.getPacketSender().sendMessage("You have been poisoned!");
        }

        entity.setPoisonDamage(poisonType.get().getDamage());
        TaskManager.submit(new CombatPoisonEffect(entity));
    }

    /**
     * Attempts to poison the argued {@link Character} with the argued
     * {@link PoisonType}. This method will have no effect if the entity is already
     * poisoned.
     *
     * @param entity     the entity that will be poisoned, if not already.
     * @param poisonType the poison type that this entity is being inflicted with.
     */
    public static void poisonEntity(Character entity, PoisonType poisonType) {
        poisonEntity(entity, Optional.ofNullable(poisonType));
    }

    /**
     * Attempts to put the skull icon on the argued player, including the effect
     * where the player loses all item upon death. This method will have no effect
     * if the argued player is already skulled.
     *
     * @param player the player to attempt to skull to.
     */
    public static void skullPlayer(Player player) {

        // We are already skulled, return.
        if (player.getSkullTimer() > 0) {
            return;
        }

        // Otherwise skull the player as normal.
        player.setSkullTimer(1200);
        player.setSkullIcon(1);
        player.getPacketSender().sendMessage("@red@You have been skulled!");
        TaskManager.submit(new CombatSkullEffect(player));
        player.getUpdateFlag().flag(Flag.APPEARANCE);
    }

    /**
     * Calculates the combat level difference for wilderness player vs. player
     * combat.
     *
     * @param combatLevel      the combat level of the first person.
     * @param otherCombatLevel the combat level of the other person.
     * @return the combat level difference.
     */
    public static int combatLevelDifference(int combatLevel, int otherCombatLevel) {
        if (combatLevel > otherCombatLevel) {
            return (combatLevel - otherCombatLevel);
        } else if (otherCombatLevel > combatLevel) {
            return (otherCombatLevel - combatLevel);
        } else {
            return 0;
        }
    }

    public static int getLevelDifference(Player player, boolean up) {
        int max = player.getLocation() == Location.WILDERNESS ? 126 : 138;
        int wildLevel = player.getWildernessLevel() + 5; // + 5 to make wild more active
        int combatLevel = player.getSkillManager().getCombatLevel();
        int difference = up ? combatLevel + wildLevel : combatLevel - wildLevel;
        return difference < 3 ? 3 : difference > max && up ? max : difference;
    }

    /**
     * Generates a random {@link Hit} based on the argued entity's stats.
     *
     * @param entity the entity to generate the random hit for.
     * @param victim the victim being attacked.
     * @param type   the combat type being used.
     * @return the melee hit.
     */
    public static Hit getHit(Character entity, Character victim, CombatType type) {
        int maxhit = 0;
        switch (type) {
            case MELEE:
                maxhit = Maxhits.melee(entity, victim) / 10;
                int damage = Misc.inclusiveRandom(0, maxhit) * 10;
                return new Hit(damage, Hitmask.RED, CombatIcon.MELEE);
            case RANGED:
                maxhit = Maxhits.ranged(entity, victim) / 10;
                long calc = Misc.inclusiveRandom(0, maxhit) * 10;
                if (entity.isPlayer() && victim.isNpc()) {
                    Player player = (Player) entity;
                    if (player.getEquipment().contains(22006) && player.getLastCombatType() == RANGED) {
                        NPC npc = (NPC) victim;
                        if (!npcsDeathDartDontWork(npc)) {
                            calc = victim.getConstitution();
                        } else {
                            player.sendMessage("The Death-touch dart didn't work on this.");
                        }
                    }
                }
                return new Hit(calc, Hitmask.RED, CombatIcon.RANGED);
            case MAGIC:
                maxhit = Maxhits.magic(entity, victim) / 10;
                return new Hit(Misc.inclusiveRandom(0, maxhit) * 10, Hitmask.RED, CombatIcon.MAGIC);
            case DRAGON_FIRE:
                return new Hit(Misc.inclusiveRandom(0, CombatFactory.calculateMaxDragonFireHit(entity, victim)),
                        Hitmask.RED, CombatIcon.MAGIC);
            default:
                throw new IllegalArgumentException("Invalid combat type: " + type);
        }
    }

    public static boolean npcsDeathDartDontWork(NPC npc) {
        int id = npc.getId();
        return id == 8013 || id == 8009 || id == 3830 || id == 187 || id == 3779 || id == 12239 || id == 7553 || id == 3305 || id == 9017
                || id == 9014 || id == 9020 || id == 10022;

    }

    /**
     * A flag that determines if the entity's attack will be successful based on the
     * argued attacker's and victim's stats.
     *
     * @param attacker the attacker who's hit is being calculated for accuracy.
     * @param victim   the victim who's awaiting to either be hit or dealt no
     *                 damage.
     * @param type     the type of combat being used to deal the hit.
     * @return true if the hit was successful, or in other words accurate.
     */
    @SuppressWarnings("incomplete-switch")
    public static boolean rollAccuracy(Character attacker, Character victim, CombatType type) {

        if (attacker.isPlayer() && victim.isPlayer()) {
            Player p1 = (Player) attacker;
            Player p2 = (Player) victim;
            switch (type) {
                case MAGIC:
                    int mageAttk = DesolaceFormulas.getMagicAttack(p1);
                    return Misc.getRandom(DesolaceFormulas.getMagicDefence(p2)) < Misc.getRandom((mageAttk / 2))
                            + Misc.getRandom((int) (mageAttk / 2.1));
                case MELEE:
                    int def = 1 + DesolaceFormulas.getMeleeDefence(p2);
                    return Misc.getRandom(def) < Misc.getRandom(1 + DesolaceFormulas.getMeleeAttack(p1)) + (def / 4.5);
                case RANGED:
                    return Misc.getRandom(10 + DesolaceFormulas.getRangedDefence(p2)) < Misc
                            .getRandom(15 + DesolaceFormulas.getRangedAttack(p1));
            }
        } else if (attacker.isPlayer() && victim.isNpc() && type != CombatType.MAGIC) {
            Player p1 = (Player) attacker;
            NPC n = (NPC) victim;
            switch (type) {
                /*
                 * case MAGIC: case KORASI: int mageAttk = DesolaceFormulas.getMagicAttack(p1);
                 * return Misc.getRandom(n.getDefinition().getDefenceMage()) <
                 * Misc.getRandom((mageAttk / 2)) + Misc.getRandom((int) (mageAttk/2.1));
                 */
                case MELEE:
                    int def = 1 + n.getDefinition().getDefenceMelee();
                    return Misc.getRandom(def) < Misc.getRandom(5 + DesolaceFormulas.getMeleeAttack(p1)) + (def / 4);
                case RANGED:
                    return Misc.getRandom(5 + n.getDefinition().getDefenceRange()) < Misc
                            .getRandom(5 + DesolaceFormulas.getRangedAttack(p1));
            }
        }

        boolean veracEffect = false;

        if (type == CombatType.MELEE) {
            if (CombatFactory.fullVeracs(attacker)) {
                if (Misc.RANDOM.nextInt(8) == 3) {
                    veracEffect = true;
                }
            }
        }

        if (type == CombatType.DRAGON_FIRE)
            type = CombatType.MAGIC;

        double prayerMod = 1;
        double equipmentBonus = 1;
        double specialBonus = 1;
        int styleBonus = 0;
        int bonusType = -1;
        if (attacker.isPlayer()) {
            Player player = (Player) attacker;

            equipmentBonus = type == CombatType.MAGIC
                    ? player.getBonusManager().getAttackBonus()[BonusManager.ATTACK_MAGIC]
                    : player.getBonusManager().getAttackBonus()[player.getFightType().getBonusType()];
            bonusType = player.getFightType().getCorrespondingBonus();

            if (type == CombatType.MELEE) {
                if (PrayerHandler.isActivated(player, PrayerHandler.CLARITY_OF_THOUGHT)) {
                    prayerMod = 1.05;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.IMPROVED_REFLEXES)) {
                    prayerMod = 1.10;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.INCREDIBLE_REFLEXES)) {
                    prayerMod = 1.15;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.CHIVALRY)) {
                    prayerMod = 1.15;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.PIETY)) {
                    prayerMod = 1.20;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                    prayerMod = 1.20;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                    prayerMod = 1.20;
                } else if (CurseHandler.isActivated(player, CurseHandler.LEECH_ATTACK)) {
                    prayerMod = 1.05 + +(player.getLeechedBonuses()[0] * 0.01);
                } else if (CurseHandler.isActivated(player, CurseHandler.TURMOIL)) {
                    prayerMod = 1.15 + +(player.getLeechedBonuses()[2] * 0.01);
                }
            } else if (type == CombatType.RANGED) {
                if (PrayerHandler.isActivated(player, PrayerHandler.SHARP_EYE)) {
                    prayerMod = 1.05;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.HAWK_EYE)) {
                    prayerMod = 1.10;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.EAGLE_EYE)) {
                    prayerMod = 1.15;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                    prayerMod = 1.22;
                } else if (CurseHandler.isActivated(player, CurseHandler.LEECH_RANGED)) {
                    prayerMod = 1.05 + +(player.getLeechedBonuses()[4] * 0.01);
                }
            } else if (type == CombatType.MAGIC) {
                if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_WILL)) {
                    prayerMod = 1.05;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_LORE)) {
                    prayerMod = 1.10;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_MIGHT)) {
                    prayerMod = 1.15;
                } else if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                    prayerMod = 1.22;
                } else if (CurseHandler.isActivated(player, CurseHandler.LEECH_MAGIC)) {
                    prayerMod = 1.05 + +(player.getLeechedBonuses()[6] * 0.01);
                }
            }

            if (player.getFightType().getStyle() == FightStyle.ACCURATE) {
                styleBonus = 3;
            } else if (player.getFightType().getStyle() == FightStyle.CONTROLLED) {
                styleBonus = 1;
            }

            if (player.isSpecialActivated()) {
                specialBonus = player.getCombatSpecial().getAccuracyBonus();
            }
        }

        double attackCalc = Math.floor(equipmentBonus + attacker.getBaseAttack(type)) + 8;

        attackCalc *= prayerMod;
        attackCalc += styleBonus;

        if (equipmentBonus < -67) {
            attackCalc = Misc.exclusiveRandom(8) == 0 ? attackCalc : 0;
        }
        attackCalc *= specialBonus;

        equipmentBonus = 1;
        prayerMod = 1;
        styleBonus = 0;
        if (victim.isPlayer()) {
            Player player = (Player) victim;

            if (bonusType == -1) {
                equipmentBonus = type == CombatType.MAGIC
                        ? player.getBonusManager().getDefenceBonus()[BonusManager.DEFENCE_MAGIC]
                        : player.getSkillManager().getCurrentLevel(Skill.DEFENCE);
            } else {
                equipmentBonus = type == CombatType.MAGIC
                        ? player.getBonusManager().getDefenceBonus()[BonusManager.DEFENCE_MAGIC]
                        : player.getBonusManager().getDefenceBonus()[bonusType];
            }

            if (PrayerHandler.isActivated(player, PrayerHandler.THICK_SKIN)) {
                prayerMod = 1.05;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.ROCK_SKIN)) {
                prayerMod = 1.10;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.STEEL_SKIN)) {
                prayerMod = 1.15;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.CHIVALRY)) {
                prayerMod = 1.20;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.PIETY)) {
                prayerMod = 1.25;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                prayerMod = 1.25;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                prayerMod = 1.25;
            } else if (CurseHandler.isActivated(player, CurseHandler.LEECH_DEFENCE)) {
                prayerMod = 1.05 + +(player.getLeechedBonuses()[1] * 0.01);
            } else if (CurseHandler.isActivated(player, CurseHandler.TURMOIL)) {
                prayerMod = 1.15 + +(player.getLeechedBonuses()[1] * 0.01);
            }

            if (player.getFightType().getStyle() == FightStyle.DEFENSIVE) {
                styleBonus = 3;
            } else if (player.getFightType().getStyle() == FightStyle.CONTROLLED) {
                styleBonus = 1;
            }
        }

        double defenceCalc = Math.floor(equipmentBonus + victim.getBaseDefence(type)) + 8;
        defenceCalc *= prayerMod;
        defenceCalc += styleBonus;

        if (equipmentBonus < -67) {
            defenceCalc = Misc.exclusiveRandom(8) == 0 ? defenceCalc : 0;
        }
        if (veracEffect) {
            defenceCalc = 0;
        }
        double A = Math.floor(attackCalc);
        double D = Math.floor(defenceCalc);
        double hitSucceed = A < D ? (A - 1.0) / (2.0 * D) : 1.0 - (D + 1.0) / (2.0 * A);
        hitSucceed = hitSucceed >= 1.0 ? 0.99 : hitSucceed <= 0.0 ? 0.01 : hitSucceed;

        if (attacker.isNpc() && attacker.getLocation() == Location.WAVE_MINIGAME){
            return true;
        }
        if (attacker.isNpc() && attacker.getLocation() == Location.ANIMA_CHAMBERS){
            return true;
        }
        if (attacker.isNpc() && attacker.getLocation() == Location.VORKATH){
            return true;
        }
        if (attacker.isNpc() && attacker.getLocation() == Location.ZEMOUREGAL){
            return true;
        }
        return hitSucceed >= Misc.RANDOM.nextDouble();
    }


    public static int calculateMaxDragonFireHit(Character e, Character v) {
        int baseMax = 900;
        if (e.isNpc() && v.isPlayer()) {
            Player victim = (Player) v;
            NPC npc = (NPC) e;
            baseMax = (int) (npc.getDefinition().getMaxHit() * 3);
            if (victim.getFireImmunity() > 0 || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 1540
                    || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 11283
                    || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 13655) {

                if ((victim.getFireDamageModifier() >= 1)
                        && (victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 1540
                        || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 13655
                        || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 11283)) {
                    /*
                     * if(victim.getClanChatName().equalsIgnoreCase("Debug")) {
                     * victim.getPacketSender().
                     * sendMessage("You block 100% of the fire from potion + shield"); }
                     */
                    return 0;
                } else if (victim.getFireDamageModifier() >= 1) {
                    /*
                     * if(victim.getClanChatName().equalsIgnoreCase("Debug")) {
                     * victim.getPacketSender().sendMessage("The potion sets fire's max hit to 120."
                     * ); }
                     */
                    victim.getPacketSender().sendMessage("Your potion protects against some of the dragon's fire.");
                    return 120;
                } else if (victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 1540
                        || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 13655
                        || victim.getEquipment().getItems()[Equipment.SHIELD_SLOT].getId() == 11283) {
                    /*
                     * if(victim.getClanChatName().equalsIgnoreCase("Debug")) {
                     * victim.getPacketSender().
                     * sendMessage("Your shield sets the max fire hit to 120."); }
                     */
                    return 120;
                }
            }
        }
        if (baseMax > 450) {
            baseMax = 450 + Misc.getRandom(700);
        }
        if (v.isNpc()) {
            baseMax = (int) NpcMaxHitLimit.limit((NPC) v, baseMax, CombatType.DRAGON_FIRE);
        }
        return baseMax;
    }

    // /**
    // * The percentage of the hit reducted by antifire.
    // */
    // protected static double dragonfireReduction(Mob mob) {
    // boolean dragonfireShield = mob.getEquipment() != null
    // && (mob.getEquipment().contains(1540)
    // || mob.getEquipment().contains(11283)
    // || mob.getEquipment().contains(11284) || mob
    // .getEquipment().contains(11285));
    // boolean dragonfirePotion = false;
    // boolean protectPrayer = mob.getCombatState().getPrayer(
    // CombatPrayer.PROTECT_FROM_MAGIC);
    // if (dragonfireShield && dragonfirePotion) {
    // if (mob.getActionSender() != null) {
    // mob.getActionSender().sendMessage(
    // "You shield absorbs most of the dragon fire!");
    // mob.getActionSender()
    // .sendMessage(
    // "Your potion protects you from the heat of the dragon's breath!");
    // }
    // return 1;
    // } else if (dragonfireShield) {
    // if (mob.getActionSender() != null) {
    // mob.getActionSender().sendMessage(
    // "You shield absorbs most of the dragon fire!");
    // }
    // return 0.8; // 80%
    // } else if (dragonfirePotion) {
    // if (mob.getActionSender() != null) {
    // mob.getActionSender()
    // .sendMessage(
    // "Your potion protects you from the heat of the dragon's breath!");
    // }
    // return 0.8; // 80%
    // } else if (protectPrayer) {
    // if (mob.getActionSender() != null) {
    // mob.getActionSender().sendMessage(
    // "Your prayers resist some of the dragon fire.");
    // }
    // return 0.6; // 60%
    // }
    // return /* mob.getEquipment() != null */0;
    // }s

    /**
     * A series of checks performed before the entity attacks the victim.
     * <p>
     * <p>
     * the builder to perform the checks with.
     *
     * @return true if the entity passed the checks, false if they did not.
     */
    public static boolean checkHook(Character entity, Character victim) {

        // Check if we need to reset the combat session.
        if (!victim.isRegistered() || !entity.isRegistered() || entity.getConstitution() <= 0
                || victim.getConstitution() <= 0) {
            entity.getCombatBuilder().reset(true);
            return false;
        }

        // Here we check if the victim has teleported away.
        if (victim.isPlayer()) {
            if (((Player) victim).isTeleporting()
                    || !Location.ignoreFollowDistance(entity)
                    && !Locations.goodDistance(victim.getPosition(), entity.getPosition(), 40)
                    || (((Player) victim).isPlayerLocked() || ((Player) victim).isGroupIronmanLocked())) {
                entity.getCombatBuilder().cooldown = 10;
                entity.getMovementQueue().setFollowCharacter(null);
                return false;
            }
        }

        if (victim.isPlayer() && entity.isPlayer() && CombatFactory.zarytebow((Player) entity) && victim != null
                && entity != null && ((Player) entity).getLocation() != Location.FREE_FOR_ALL_ARENA) {
            // ((Player)entity).getPacketSender().sendMessage("Zaryte bow is disabled in
            // PvP");
            // entity.getCombatBuilder().testReset(true);
            return false;
        }

        if (victim.isNpc() && entity.isPlayer()) {
            NPC npc = (NPC) victim;
            if (npc.getSpawnedFor() != null && npc.getSpawnedFor().getIndex() != ((Player) entity).getIndex()) {
                ((Player) entity).getPacketSender().sendMessage("That's not your enemy to fight.");
                entity.getCombatBuilder().reset(true);
                return false;
            }
            //if (!npc.isAttackable()) {
            //    return false;
            //  }
            if (npc.isSummoningNpc()) {
                Player player = ((Player) entity);
                if (player.getLocation() != Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("You can only attack familiars in the wilderness.");
                    player.getCombatBuilder().reset(true);
                    return false;
                } else if (npc.getLocation() != Location.WILDERNESS) {
                    player.getPacketSender().sendMessage("That familiar is not in the wilderness.");
                    player.getCombatBuilder().reset(true);
                    return false;
                }
                /** DEALING DMG TO THEIR OWN FAMILIAR **/
                if (player.getSummoning().getFamiliar() != null
                        && player.getSummoning().getFamiliar().getSummonNpc() != null
                        && player.getSummoning().getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
                    return false;
                }
            }
            /*
             * if(Nex.nexMob(npc.getId())) {
             * if(!((Player)entity).getMinigameAttributes().getGodwarsDungeonAttributes().
             * hasEnteredRoom()) { ((Player)entity).getPacketSender().
             * sendMessage("You must enter the room before being able to attack.");
             * entity.getCombatBuilder().reset(true); return false; } }
             */
            if (((Player) entity).getRights() != PlayerRights.DEVELOPER) {
                if (npc.getId() == 12810) {
                    if (((Player) entity).getSlayer().getSlayerTask().getNpcId() != npc.getId()) {
                        return false;
                    }
                }

                if (npc.getId() == 9767) {
                    if (!((Player) entity).isUnlockedRammernaut()
                    && ((Player) entity).getSlayer().getSlayerTask().getNpcId() != npc.getId()) {
                        ((Player) entity).sendMessage("You need to claim a Rammernaut scroll or an assigned task to do this.");
                        return false;
                    }
                }
            }

            if (npc.getId() == 3) {
                int total = KillsTracker.getTotalKillsForNpc(npc.getId(), ((Player) entity));
                if (total >= 10000) {
                    ((Player) entity).sendMessage("You have reached your 10,000 kill limit for Dan's presents.");
                    return false;
                }
            }

            if (npc.getId() == 8501) {
                int total = KillsTracker.getTotalKillsForNpc(npc.getId(), ((Player) entity));
                if (total >= 25000) {
                    ((Player) entity).sendMessage("You have reached your 20,000 kill limit for Turkeys.");
                    return false;
                }
                ((Player) entity).sendMessage("The thanksgiving event has ended.");
                return false;
            }
            if (npc.getId() == 9019) {
                ((Player) entity).sendMessage("The St. Patricks event has ended.");
                return false;
            }



            if (npc.getId() == 10007) {
                int total = KillsTracker.getTotalKillsForNpc(npc.getId(), ((Player) entity));
                if (total >= 50000) {
                    ((Player) entity).sendMessage("You have reached your 50,000 kill limit for Faceless Assassins.");
                    return false;
                }
            }
            if (npc.getId() == 10008) {
                int total = KillsTracker.getTotalKillsForNpc(npc.getId(), ((Player) entity));
                if (total >= 100000) {
                    ((Player) entity).sendMessage("You have reached your 100,000 kill limit for Lotus Warriors.");
                    return false;
                }
            }
            if (npc.getId() == 10009) {
                int total = KillsTracker.getTotalKillsForNpc(npc.getId(), ((Player) entity));
                if (total >= 150000) {
                    ((Player) entity).sendMessage("You have reached your 150,000 kill limit for Shadow Hunters.");
                    return false;
                }
            }


            /*if (npc.getId() >= 14207 && npc.getId() <= 14209){
                int total = KillsTracker.getTotalKillsForNpc(14210,  ((Player) entity));
                if (total >= 50) {
                    ((Player) entity).sendMessage("You have reached your 50,000 kill limit for Snowmen.");
                    return false;
                }
            }*/

            Player player = ((Player) entity);
            if (player.getRights() != PlayerRights.DEVELOPER) {

                if (npc.getId() == 10015) {
                    if (!CelestialZone.gameActive) {
                        player.getPacketSender().sendMessage("The Celestial Zone is not open.");
                        return false;
                    }
                }

                if (npc.getId() == 9870) {
                    if (!(SeasonPass.SEASON == 2 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 2 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 9885) {
                    if (!(SeasonPass.SEASON == 3 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 3 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 9890) {
                    if (!(SeasonPass.SEASON == 4 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 4 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 9898) {
                    if (!(SeasonPass.SEASON == 5 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 5 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 9897) {
                    if (!(SeasonPass.SEASON == 6 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 6 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 10120) {
                    if (!(SeasonPass.SEASON == 7 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 7 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 6203) {
                    if (!(SeasonPass.SEASON == 8 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 8 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 6208) {
                    if (!(SeasonPass.SEASON == 8 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 8 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 11872 || npc.getId() == 11874) {
                    if (!(SeasonPass.SEASON == 11 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 11 gold pass to do this.");
                        return false;
                    }
                }
                if (npc.getId() == 10030 || npc.getId() == 10032) {
                    if (!(SeasonPass.SEASON == 12 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 12 gold pass to do this.");
                        return false;
                    }
                }

                if (npc.getId() == 9767){
                    if (!player.isUnlockedRammernaut()){
                        player.sendMessage("You don't have Rammernaut unlocked.");
                        return false;
                    }
                }

                if (npc.getId() == 10023) {
                    if (!(SeasonPass.SEASON == 9 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51)) {
                        player.sendMessage("You need to have completed the season 9 gold pass to do this.");
                        return false;
                    }
                }

                if (npc.getId() == 7134 && KillsTracker.getFenrirKills(player) < 50000) {
                    player.getPacketSender().sendMessage("You need 50,000 Fenrir kills. You currently have @red@"
                            + KillsTracker.getFenrirKills(player) + "@bla@ kills.");
                    return false;
                }

                if (npc.getId() == 28060 && KillsTracker.getGemstoneKills(player) < 30000 && player.getRights() != PlayerRights.YOUTUBER) {
                    player.getPacketSender().sendMessage("You need 30,000 Gemstone Dragon kills. You currently have @red@"
                            + KillsTracker.getGemstoneKills(player) + "@bla@ kills.");
                    return false;
                }

                for (NpcRequirements req : NpcRequirements.values()) {
                    if (npc.getId() == req.getNpcId()) {
                        if (req.getKillCount() > 0) {
                            if (KillsTracker.getTotalKills(((Player) entity)) < req.getKillCount()) {
                                return false;
                            }
                        } else {
                            int npc1 = req.getRequireNpcId();
                            if (req.getNpcId() == 3308)
                                npc1 = 8008;
                            else if (req.getNpcId() == 3117)
                                npc1 = 3308;
                            else if (req.getNpcId() == 201)
                                npc1 = 3117;
                            else if (req.getNpcId() == 202)
                                npc1 = 201;
                            else if (req.getNpcId() == 203)
                                npc1 = 202;
                            int total = KillsTracker.getTotalKillsForNpc(npc1, ((Player) entity));


                            if (npc1 == 603)
                                total = player.getPointsHandler().getLORDKILLCount();
                            if (npc1 == 12843)
                                total = player.getPointsHandler().getDEMONKILLCount();
                            if (npc1 == 53)
                                total = player.getPointsHandler().getDRAGONKILLCount();
                            if (npc1 == 8018)
                                total = player.getPointsHandler().getBEASTKILLCount();
                            if (npc1 == 13635)
                                total = player.getPointsHandler().getKINGKILLCount();
                            if (npc1 == 8008)
                                total = player.getPointsHandler().getAVATARKILLCount();
                            if (npc1 == 3308)
                                total = player.getPointsHandler().getANGELKILLCount();
                            if (npc1 == 3117)
                                total = player.getPointsHandler().getLUCIENKILLCount();
                            if (npc1 == 201)
                                total = player.getPointsHandler().getHERCULESKILLCount();
                            if (npc1 == 202)
                                total = player.getPointsHandler().getSATANKILLCount();
                            if (npc1 == 203)
                                total = player.getPointsHandler().getZEUSKILLCount();

                            if (total < req.getAmountRequired()) {
                                return false;
                            }
                        }
                        break;

                    }
                }
            }

            if (npc.getId() == 2001) {
                if (!Scorpia.attackable()) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("Scorpia cannot be attacked until its babies are dead.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            } else if (npc.getId() == 2891) {
                for (int i = 0; i < 4; i++) {
                    if (!((KrakenInstance) ((Player) entity).getRegionInstance()).disturbedPool(i)) {
                        ((Player) entity).getPacketSender()
                                .sendMessage("You need to disturb all the small whirpools first.");
                        entity.getCombatBuilder().reset(true);
                        return false;
                    }
                }
            }

            if (Nex.nexMob(npc.getId())) {
                if (!Nex.checkAttack(((Player) entity), npc.getId())) {
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            } else if (npc.getId() == 130) { // wiz
                if (entity.getCombatBuilder().getStrategy().getCombatType() == CombatType.MELEE) {
                    ((Player) entity).getPacketSender().sendMessage("Kree'arra is resistant to melee attacks.");
                    entity.getCombatBuilder().testReset(true);
                    return false;
                }

            } else if (npc.getId() == 1614) { // spawnn 1614
                if (KillsTracker.getTotalKills(((Player) entity)) <= 49) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 50 npc kill Count. You currently have @red@"
                                    + KillsTracker.getTotalKills(((Player) entity)) + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 4540) { //NEWBOSS
                if (KillsTracker.getTotalKills(((Player) entity)) <= 249_999) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 250K npc kill Count. You currently have @red@"
                                    + KillsTracker.getTotalKills(((Player) entity)) + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 603) { // spawnn lord
                if (((Player) entity).getPointsHandler().getSPAWNKILLCount() <= 99) {
                    ((Player) entity).getPacketSender().sendMessage("You need 100 Imp kills. You currently have @red@"
                            + ((Player) entity).getPointsHandler().getSPAWNKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            } else if (npc.getId() == 12843) { // demon dragon
                if (((Player) entity).getPointsHandler().getLORDKILLCount() <= 199) {
                    ((Player) entity).getPacketSender().sendMessage("You need 200 lord kills. You currently have @red@"
                            + ((Player) entity).getPointsHandler().getLORDKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 53) { // spawnn dragon
                if (((Player) entity).getPointsHandler().getDEMONKILLCount() <= 299) {
                    ((Player) entity).getPacketSender().sendMessage("You need 300 demon kills. You currently have @red@"
                            + ((Player) entity).getPointsHandler().getDEMONKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 8013) { // Voting
                if (VoteBoss.getVoteCount() <= 59) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("Players need to reach a total of 60 votes. We're currently at @red@"
                                    + VoteBoss.getVoteCount() + "@bla@ votes.");
                    ((Player) entity).getPacketSender()
                            .sendMessage("Everyone should @red@::vote@bla@ to contribute towards Vote Boss.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            } else if (npc.getId() == 8018) { // spawnn beast
                if (((Player) entity).getPointsHandler().getDRAGONKILLCount() <= 399) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 400 Dragon kill Count. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getDRAGONKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 8008) {
                if (((Player) entity).getPointsHandler().getKINGKILLCount() <= 999) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 1000 king kill Count. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getKINGKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;


                }
            } else if (npc.getId() == 9012 && player.getRights() != PlayerRights.DEVELOPER) {
                if (((Player) entity).getPointsHandler().getMiniLuciferkillcount() < 5_000) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 5k Mini Lucifer killcount. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getMiniLuciferkillcount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            } else if (npc.getId() == 1265) { // spawnn beast
                if (KillsTracker.getTotalKills(((Player) entity)) > 5000 && KillsTracker.getTotalKillsForNpc(1265, (Player) entity) > 500) {
                    ((Player) entity).sendMessage("You need to have less than 5K Npc kills to attack this NPC");
                    ((Player) entity).sendMessage("If this is a slayer task. Type ::removetask to reset your slayer task for free!");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 1023) { // Alari Lions
                if (KillsTracker.getTotalKills(((Player) entity))> 5000 && KillsTracker.getTotalKillsForNpc(1023, (Player) entity) > 500) {
                    ((Player) entity).sendMessage("You need to have less than 5K Npc kills to attack this NPC");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 1233) { // spawnn beast
                if (KillsTracker.getTotalKills(((Player) entity)) > 5000 && KillsTracker.getTotalKillsForNpc(1233, (Player) entity) > 500) {
                    ((Player) entity).sendMessage("You need to have less than 5K Npc kills to attack this NPC");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 1234) { // spawnn beast
                if (KillsTracker.getTotalKills(((Player) entity)) > 5000 && KillsTracker.getTotalKillsForNpc(1234, (Player) entity) > 500) {
                    ((Player) entity).sendMessage("You need to have less than 5K Npc kills to attack this NPC");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 3308) { // spawnn beast
                if (((Player) entity).getPointsHandler().getAVATARKILLCount() <= 1199) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 1200 avatar kill Count. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getAVATARKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 3117) { // spawnn beast
                if (((Player) entity).getPointsHandler().getANGELKILLCount() <= 1499) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 1.5K angel kill Count. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getANGELKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 13635) { // Troll king
                if (((Player) entity).getPointsHandler().getBEASTKILLCount() <= 499) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 500 Beast kill Count. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getBEASTKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 2050) { // Swoodoo
                if (((Player) entity).getAmountDonated() <= 249) {
                    ((Player) entity).getPacketSender().sendMessage(
                            "You need to be a Ruby donator. You can try @red@::donate @bla@ check it out now.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 201) { //
                if (((Player) entity).getPointsHandler().getLUCIENKILLCount() <= 2499) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 2500 lucien kills. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getLUCIENKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }

            } else if (npc.getId() == 202) { //
                if (((Player) entity).getPointsHandler().getHERCULESKILLCount() <= 3499) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 3500 hercules kills. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getHERCULESKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }

            } else if (npc.getId() == 1120) { //
                if (((Player) entity).getPointsHandler().getSATANKILLCount() < 25000 && player.getRights() != PlayerRights.DEVELOPER) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 25000 Satan kills. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getSATANKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }


            } else if (npc.getId() == 203) { //
                if (((Player) entity).getPointsHandler().getSATANKILLCount() <= 4999) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 5,000 satan kills. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getSATANKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 1120) { //

                if (!((Player) entity).getEquipment().containsAny(5012, 5011, 5010, 22113, 23455)) {
                    ((Player) entity).getPacketSender().sendMessage("<shad=1>@red@Vasa Nistirio has a crystal that can only be broken by a Twisted Bow");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            } else if (npc.getId() == 8010) { //
                if (((Player) entity).getPointsHandler().getZEUSKILLCount() <= 14999) {
                    ((Player) entity).getPacketSender()
                            .sendMessage("You need 15,000 zeus kills. You currently have @red@"
                                    + ((Player) entity).getPointsHandler().getZEUSKILLCount() + "@bla@ kills.");
                    entity.getCombatBuilder().reset(true);
                    return false;

                }
            }

            if (npc.getId() >= 6142 && npc.getId()<= 6145){
                if (!PestControl.canAttack(((Player) entity), npc.getId())) {
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            }

            if (npc.getId() == 6715 || npc.getId() == 6716 || npc.getId() == 6701 || npc.getId() == 6725
                    || npc.getId() == 6691) {
                if (entity.getLocation() != Location.WILDERNESS) {
                    ((Player) entity).getPacketSender().sendMessage("You cannot reach that.");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            }
            if (npc.getId() == 4291 && entity.getPosition().getZ() == 2
                    && !((Player) entity).getMinigameAttributes().getWarriorsGuildAttributes().enteredTokenRoom()) {
                ((Player) entity).getPacketSender().sendMessage("You cannot reach that.");
                entity.getCombatBuilder().reset(true);
                return false;
            }
        }

        // Here we check if we are already in combat with another entity.
       /* if (entity.getCombatBuilder().getLastAttacker() != null && !Location.inMulti(entity)
                && entity.getCombatBuilder().isBeingAttacked()
                && !victim.equals(entity.getCombatBuilder().getLastAttacker())) {
            if (entity.isPlayer())
                ((Player) entity).getPacketSender().sendMessage("You are already under attack!");
            entity.getCombatBuilder().reset(true);
            return false;
        }*/

        // Here we check if the entity we are attacking is already in
        // combat.
        if (!(entity.isNpc() && ((NPC) entity).isSummoningNpc())) {
            boolean allowAttack = false;
            boolean isMultiNPC = false;
            if (victim.isNpc()) {
                isMultiNPC = (((NPC) victim).getDefinition().isMulti());
            }
            if (victim.getCombatBuilder().getLastAttacker() != null && !Location.inMulti(entity)
                    && victim.getCombatBuilder().isBeingAttacked()
                    && !victim.getCombatBuilder().getLastAttacker().equals(entity)) {

                if (victim.getCombatBuilder().getLastAttacker().isNpc()) {
                    NPC npc = (NPC) victim.getCombatBuilder().getLastAttacker();
                    if (npc.isSummoningNpc()) {
                        if (entity.isPlayer()) {
                            Player player = (Player) entity;
                            if (player.getSummoning().getFamiliar() != null
                                    && player.getSummoning().getFamiliar().getSummonNpc() != null && player
                                    .getSummoning().getFamiliar().getSummonNpc().getIndex() == npc.getIndex()) {
                                player.getPacketSender().sendMessage("Summoning only works in multi for now...");
                                allowAttack = false;
                                // getting source tree to detect this zzz.
                            }
                        }
                    }
                }

                if (!allowAttack && !isMultiNPC) {
                    if (entity.isPlayer())
                        ((Player) entity).getPacketSender().sendMessage("They are already under attack!");
                    entity.getCombatBuilder().reset(true);
                    return false;
                }
            }
        }

        // Check if the victim is still in the wilderness, and check if the
        if (entity.isPlayer()) {
            if (victim.isPlayer()) {
                if (!properLocation((Player) entity, (Player) victim)) {
                    entity.getCombatBuilder().reset(true);
                    entity.setPositionToFace(victim.getPosition());
                    return false;
                }
            }
            if (((Player) entity).isCrossingObstacle()) {
                entity.getCombatBuilder().reset(true);
                return false;
            }
        }

        // Check if the npc needs to retreat.
        if (entity.isNpc()) {
            NPC n = (NPC) entity;
            if (!Location.ignoreFollowDistance(n) && !Nex.nexMob(n.getId()) && !n.isSummoningNpc()) { // Stops combat
                // for npcs if
                // too far away
                if (n.getPosition().isWithinDistance(victim.getPosition(), 1)) {
                    return true;
                }
                if (!n.getPosition().isWithinDistance(n.getDefaultPosition(),
                        10 + n.getMovementCoordinator().getCoordinator().getRadius())) {
                    n.getMovementQueue().reset();
                    n.getMovementCoordinator().setCoordinateState(CoordinateState.AWAY);
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Checks if the entity is close enough to attack.
     *
     * @param builder the builder used to perform the check.
     * @return true if the entity is close enough to attack, false otherwise.
     */
    public static boolean checkAttackDistance(CombatBuilder builder) {
        return checkAttackDistance(builder.getCharacter(), builder.getVictim());
    }

    public static boolean checkAttackDistance(Character a, Character b) {

        Position attacker = a.getPosition();
        Position victim = b.getPosition();

        Position attackerPosition = a.getPosition();
        Position victimPosition = b.getPosition();

        if (a.isNpc() && ((NPC) a).isSummoningNpc()) {
            return Locations.goodDistance(attacker, victim, a.getSize());
        }

        if (a.isNpc() && a.getLocation() == Location.WAVE_MINIGAME)
            return true;

        if (a.getCombatBuilder().getStrategy() == null)
            a.getCombatBuilder().determineStrategy();
        CombatStrategy strategy = a.getCombatBuilder().getStrategy();
        int distance = strategy.getCombatType() == MAGIC ? strategy.attackDistance(a)+ 1 : strategy.attackDistance(a) ;
        if (a.isPlayer() && strategy.getCombatType() != CombatType.MELEE) {
            if (b.getSize() >= 2)
                distance += b.getSize() - 1;
        }

        MovementQueue movement = a.getMovementQueue();
        MovementQueue otherMovement = b.getMovementQueue();
        boolean isMeleeCombatStrategy = strategy.getCombatType() == CombatType.MELEE;
        boolean meleeMoving = false;

        // We're moving so increase the distance.
        if (isMeleeCombatStrategy) {
            if (a.getMovementQueue().getFollowCharacter() != null && movement.isMoving() && !a.isFrozen() && !a.isStunned() && !a.getMovementQueue().isLockMovement()) {
                if (!Locations.goodDistance(a.getPosition(), a.getSize(), a.getMovementQueue().getFollowCharacter().getPosition(), a.getMovementQueue().getFollowCharacter().getSize(), 1)) {
                    //distance += movement.isRunToggled() ? 2 : 1;
                    meleeMoving = true;
                }
            }
        } else {
            if (movement.isMoving()) {
                // distance += movement.isRunToggled() ? 2 : 1;
            }
        }


        if (Locations.inside(attackerPosition, a.getSize(), victimPosition, b.getSize())) {
            a.getMovementQueue().setFollowCharacter(null);
            a.getMovementQueue().surround(b);
            /*if (a instanceof Player) {
                ((Player) a).sendMessage("[CombatFactory] inside victim -> started following victim");
            }*/
            return false;
        }
       /* if (a instanceof Player) {
            ((Player) a).sendMessage(strategy.getCombatType() + " cast spell: " + ((Player) a).getCastSpell());
        }*/


        if (a.isPlayer() && (strategy.getCombatType() == CombatType.RANGED
                || strategy.getCombatType() == CombatType.MAGIC) || a.isNpc()
                && (strategy.getCombatType() == CombatType.RANGED || strategy.getCombatType() == CombatType.MAGIC)) {
            if (a.getLocation() != Location.ISLE_GODS){
                if (!RegionClipping.canProjectileAttack(a, b)) {
               /* if (a instanceof Player) {
                    ((Player) a).sendMessage("[CombatFactory] could not traverse to victim");
                }*/
                return false;
            } else {
                /*if (a instanceof Player) {
                    ((Player) a).sendMessage("[CombatFactory] PASSED traverse to victim");
                }*/
            }}
        }
        boolean goodDistance = Locations.goodDistance(a, b, distance);
        if (!goodDistance) {
            a.getMovementQueue().setFollowCharacter(b);
           /* if (a instanceof Player)
                ((Player) a).sendMessage("[CombatFactory] not a good distance -> started following victim");*/
            return false;
        }


        if (strategy.getCombatType() == CombatType.MELEE) {
            if (a.isFrozen()) {
                if (a.getPosition().equals(b.getPosition()) || MovementQueue.isInDiagonalBlock(a, b)) {
                    return false;
                }
            }


            Position closestPosition = Position.closestPoint(attackerPosition, victimPosition, a.getSize());
            Position closestPositionTarget = Position.closestPoint(victimPosition, closestPosition, b.getSize());

            DirectionFace direction = DirectionFace.Companion.fromDeltas(Position.delta(closestPosition, closestPositionTarget));

            if (b.isPlayer() || (b.isNpc() && (((NPC) b).getId() != 28060 && ((NPC) b).getId() != 27555 &&
                    ((NPC) b).getId() != 27553 && !(((NPC) b).getId() >= 6142 && ((NPC) b).getId() <= 6145)))) {

                boolean pathBlocked = false;
                if (meleeMoving) {
                    pathBlocked = RouteFinder.findRoute(attackerPosition.getZ(), attackerPosition.getX(), attackerPosition.getY(),
                            a.getSize(), new EntityStrategy(b), false) < 1;
                } else {
                    if (!RegionClipping.canWalk(closestPosition.getZ(), closestPosition.getX(), closestPosition.getY(), direction, 1)) {
                        pathBlocked = true;
                    }
                }
                if (pathBlocked) {
                    a.getMovementQueue().setFollowCharacter(b);
                   /* if (a instanceof Player) {
                        ((Player) a).sendMessage("[CombatFactory] not traversable 2 -> started following victim " + direction);
                    }*/
                    return false;
                }
            }
        }
        return true;

        /*
        boolean sameSpot = attacker.equals(victim) && !a.getMovementQueue().isMoving()
                && !b.getMovementQueue().isMoving();
        boolean goodDistance = !sameSpot
                && Locations.goodDistance(attacker.getX(), attacker.getY(), victim.getX(), victim.getY(), distance);
        boolean projectilePathBlocked = false;
        if (a.isPlayer()
                && (strategy.getCombatType() == CombatType.RANGED
                || strategy.getCombatType() == CombatType.MAGIC && ((Player) a).getCastSpell() != null
                && !(((Player) a).getCastSpell() instanceof CombatAncientSpell))
                || a.isNpc() && strategy.getCombatType() == CombatType.MELEE) {
            if (!RegionClipping.canProjectileAttack(b, a))
                projectilePathBlocked = true;
        }
        if (!projectilePathBlocked && goodDistance) {
            if (strategy.getCombatType() == CombatType.MELEE && RegionClipping.isInDiagonalBlock(b, a)) {
                PathFinder.findPath(a, victim.getX(), victim.getY() + 1, true, 1, 1);
                return false;
            } else
                a.getMovementQueue().reset();
            return true;
        } else if (projectilePathBlocked || !goodDistance) {
            a.getMovementQueue().setFollowCharacter(b);
            return false;
        }
        // Check if we're within the required distance.
        return attacker.isWithinDistance(victim, distance);*/
    }

    /**
     * Applies combat prayer effects to the calculated hits.
     *
     * @param container the combat container that holds the hits.
     * @param builder   the builder to apply prayer effects to.
     */
    protected static void applyPrayerProtection(CombatContainer container, CombatBuilder builder) {

        // If we aren't checking the accuracy, then don't bother doing any of
        // this.
        if (!container.isCheckAccuracy() || builder.getVictim() == null) {
            return;
        }

        // The attacker is an npc, and the victim is a player so we completely
        // cancel the hits if the right prayer is active.

        if (builder.getVictim().isPlayer()) {
            Player victim = (Player) builder.getVictim();
            if (victim.getEquipment().containsAny(13740, 13742, 23312, 19810, 23538, 23539, 18889, 17696,   22117, 23361, 23399,23718, 23856,
                    23638,
                    23639,
                    23640,
                    23641,
                    23642,
                    23788,
                    23643,
                    23807,
                    23831,
                    23820,

                    23840, 23841, 23842, 23839, 23832, 23891


                    )) {
                if (PrayerHandler.isActivated(victim, PrayerHandler.getProtectingPrayer(container.getCombatType()))
                        || CurseHandler.isActivated(victim,
                        CurseHandler.getProtectingPrayer(container.getCombatType()))) {
                    container.allHits(context -> {
                        long hit = context.getHit().getDamage();
                        context.setAccurate(false);
                        context.getHit().incrementAbsorbedDamage(hit);
                    });
                } else {
                    if (Misc.getRandom(10) <= 7) {
                        container.allHits(context -> {
                            if (PrayerHandler.isActivated(victim,
                                    PrayerHandler.getProtectingPrayer(container.getCombatType()))
                                    || CurseHandler.isActivated(victim,
                                    CurseHandler.getProtectingPrayer(container.getCombatType()))) {
                                return; // we don't want to do the calculation now if they are praying against the right
                                // style.
                            }
                            if (context.getHit().getDamage() > 10) {
                                context.getHit().incrementAbsorbedDamage((int) (context.getHit().getDamage()
                                        - (context.getHit().getDamage() * 0.75)));
                            }
                        });
                    } else {
                    }
                }
            }
            if (builder.getCharacter().isNpc()) {
                NPC attacker = (NPC) builder.getCharacter();
                // Except for verac of course :)
                if (attacker.getId() == 2030) {
                    return;
                }
                // It's not verac so we cancel all of the hits.
                if (PrayerHandler.isActivated(victim, PrayerHandler.getProtectingPrayer(container.getCombatType()))
                        || CurseHandler.isActivated(victim,
                        CurseHandler.getProtectingPrayer(container.getCombatType()))) {
                    container.allHits(context -> {
                        long hit = context.getHit().getDamage();
                        if (attacker.getId() == 2745) { // Jad
                            context.setAccurate(false);
                            context.getHit().incrementAbsorbedDamage(hit);
                        } else {
                            // now that we know they're praying, check if they also have the spirit shield.
                            if (victim.getEquipment().containsAny(13740, 13742, 23312, 19810, 23538, 23539, 18889, 17696, 22117, 23361, 23399,23718,23856,
                                    23638,
                                    23639,
                                    23640,
                                    23641,
                                    23642,
                                    23788,
                                    23643,
                                    23807,
                                    23831,
                                    23820,
                                    23840, 23841, 23842, 23839, 23832, 23891
                            )) {
                                double reduceRatio = attacker.getId() == 1158 || attacker.getId() == 1160 ? 0.4 : 0.8;
                                double mod = Math.abs(1 - reduceRatio);
                                context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                                mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                                if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                                    context.setAccurate(false);
                                }
                                if (Misc.getRandom(10) <= 7) {
                                    if (context.getHit().getDamage() > 10) {
                                        context.getHit()
                                                .incrementAbsorbedDamage((int) (context.getHit().getDamage()
                                                        - (context.getHit().getDamage() * 0.75)));
                                    }
                                }
                            } else {
                                double reduceRatio = attacker.getId() == 1158 || attacker.getId() == 1160 ? 0.4 : 0.8;
                                double mod = Math.abs(1 - reduceRatio);
                                context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                                mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                                if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                                    context.setAccurate(false);
                                }
                            }
                        }
                    });
                }
            } else if (builder.getCharacter().isPlayer()) {
                Player attacker = (Player) builder.getCharacter();
                // If wearing veracs, the attacker will hit through prayer
                // protection.
                if (CombatFactory.fullVeracs(attacker)) {
                    return;
                }

                // They aren't wearing veracs so lets reduce the accuracy and hits.
                if (PrayerHandler.isActivated(victim, PrayerHandler.getProtectingPrayer(container.getCombatType()))
                        || CurseHandler.isActivated(victim,
                        CurseHandler.getProtectingPrayer(container.getCombatType()))) {
                    // PLAYER TO PLAYER EVENTS
                    container.allHits(context -> {
                        // First reduce the damage.
                        long hit = context.getHit().getDamage();
                        double mod = Math.abs(1 - 0.5);
                        context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                        // Then reduce the accuracy.
                        mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                        if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                            context.setAccurate(false);
                        }
                    });
                }
            }
        } else if (builder.getVictim().isNpc() && builder.getCharacter().isPlayer()) {
            Player attacker = (Player) builder.getCharacter();
            NPC npc = (NPC) builder.getVictim();
            if (npc.getId() == 10000
                    || npc.getId() == 10001) {
                if (attacker.getGodsRaidsParty() != null){
                    if ((!attacker.getGodsRaidsParty().isSaradominAttackable() && npc.getId() == 10000 )
                    || (attacker.getGodsRaidsParty().isSaradominAttackable() && npc.getId() == 10001)) {
                        attacker.sendMessage(npc.getDefinition().getName() + " has a shield currently.");
                        container.allHits(context -> {
                            long hit = context.getHit().getDamage();
                            context.getHit().incrementAbsorbedDamage((int) (hit));
                            context.setAccurate(false);
                        });
                    }
                }
            } else if ((npc.getId() == 9818 && container.getCombatType() == CombatType.MAGIC)
                    || (npc.getId() == 9817 && container.getCombatType() == CombatType.RANGED)
                    || (npc.getId() == 9815 && container.getCombatType() == CombatType.MELEE)
                    || (npc.getId() == 10019 && container.getCombatType() == CombatType.MELEE)
                    || (npc.getId() == 10020 && container.getCombatType() == RANGED)
                    || (npc.getId() == 10021 && container.getCombatType() == CombatType.MAGIC)) {
                container.allHits(context -> {
                    long hit = context.getHit().getDamage();
                    context.getHit().incrementAbsorbedDamage((int) (hit));
                    context.setAccurate(false);
                });
            } else if (npc.getId() == 9881 || npc.getId() == 9886 || npc.getId() == 9887) {
                if ((npc.getTransformationId() == 9887 && container.getCombatType() == CombatType.MAGIC)
                        || (npc.getTransformationId() == 9886 && container.getCombatType() == CombatType.RANGED)
                        || ((npc.getTransformationId() == 9881 || npc.getTransformationId() == -1) && container.getCombatType() == CombatType.MELEE)) {
                    container.allHits(context -> {
                        long hit = context.getHit().getDamage();
                        context.getHit().incrementAbsorbedDamage((int) (hit));
                        context.setAccurate(false);
                    });
                }
            } else if ((npc.getId() == 9884 && container.getCombatType() == CombatType.MAGIC)
                    || (npc.getId() == 9883 && container.getCombatType() == CombatType.RANGED)
                    || (npc.getId() == 9882 && container.getCombatType() == CombatType.MELEE)) {
                container.allHits(context -> {
                    long hit = context.getHit().getDamage();
                    context.getHit().incrementAbsorbedDamage(hit);
                    context.setAccurate(false);
                });
            } else if ((npc.getId() == 9026 && container.getCombatType() == CombatType.RANGED)
                    || (npc.getId() == 9027 && container.getCombatType() == CombatType.MAGIC)
                    || (npc.getId() == 9025 && container.getCombatType() == CombatType.MELEE)) {
                attacker.sendMessage("The beast seems to not be affected by your current Combat Type.");
                container.allHits(context -> {
                    context.setAccurate(false);
                });
            } else if ((npc.getId() == 9024 && container.getCombatType() == ((Kiljaeden) npc).avoiding)
                    || (npc.getId() == 9814 && container.getCombatType() == ((Heimdall) npc).avoiding)) {
                attacker.sendMessage("Your current combat type is being deflected entirely.");
                container.allHits(context -> {
                    context.setAccurate(false);
                });
            } else if (npc.getId() == 8349 && container.getCombatType() == CombatType.MELEE) {
                container.allHits(context -> {
                    long hit = context.getHit().getDamage();
                    double mod = Math.abs(1 - 0.5);
                    context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                    mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                    if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                        context.setAccurate(false);
                    }
                });
            } else if (npc.getId() == 1158
                    && (container.getCombatType() == CombatType.MAGIC || container.getCombatType() == CombatType.RANGED)
                    || npc.getId() == 1160 && container.getCombatType() == CombatType.MELEE) {
                container.allHits(context -> {
                    if (CombatFactory.fullVeracs(attacker)) {
                        return;
                    }
                    long hit = context.getHit().getDamage();
                    double mod = Math.abs(1 - 0.95);
                    context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                    mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                    if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                        context.setAccurate(false);
                    }

                });
                if (!CombatFactory.fullVeracs(attacker)) {
                    attacker.getPacketSender()
                            .sendMessage("Your "
                                    + (container.getCombatType() == CombatType.MAGIC ? "magic"
                                    : container.getCombatType() == CombatType.RANGED ? "ranged" : "melee")
                                    + " attack has" + (!container.getHits()[0].isAccurate() ? "" : " close to")
                                    + " no effect on the queen.");
                }

            } else if (npc.getId() == 13347 && Nex.zarosStage()) {
                container.allHits(context -> {
                    long hit = context.getHit().getDamage();
                    double mod = Math.abs(1 - 0.4);
                    context.getHit().incrementAbsorbedDamage((int) (hit - (hit * mod)));
                    mod = Math.round(Misc.RANDOM.nextDouble() * 100.0) / 100.0;
                    if (mod <= CombatFactory.PRAYER_ACCURACY_REDUCTION) {
                        context.setAccurate(false);
                    }
                });
            }
        }
    }

    /**
     * Gives experience for the total amount of damage dealt in a combat hit.
     *
     * @param builder   the attacker's combat builder.
     * @param container the attacker's combat container.
     * @param damage    the total amount of damage dealt.
     */
    protected static void giveExperience(CombatBuilder builder, CombatContainer container, int damage) {

        // This attack does not give any experience.
        if (container.getExperience().length == 0 && container.getCombatType() != CombatType.MAGIC) {
            return;
        }

        // Otherwise we give experience as normal.
        if (builder.getCharacter().isPlayer()) {
            Player player = (Player) builder.getCharacter();

            if (container.getCombatType() == CombatType.MAGIC) {
                if (player.getCurrentlyCasting() != null)
                    player.getSkillManager().addExperience(Skill.MAGIC,
                            (int) (((damage * .90)) / container.getExperience().length)
                                    + builder.getCharacter().getCurrentlyCasting().baseExperience());
            } else {
                for (int i : container.getExperience()) {
                    Skill skill = Skill.forId(i);
                    player.getSkillManager().addExperience(skill,
                            (int) (((damage * .90)) / container.getExperience().length));
                }
            }

            player.getSkillManager().addExperience(Skill.CONSTITUTION, (int) ((damage * 0.7)));
        }
    }

    /**
     * @param attacker the person who's attacking with a degradable weapon
     * @author Crimson Jul 23, 2017
     */
    protected static void handleDegradingWeapons(Player attacker) {
        // // System.out.println("Called handleDegradingWeapons at
        // "+System.currentTimeMillis());
        if (attacker == null)
            return;

        if (attacker.getLocation() == Location.FREE_FOR_ALL_ARENA || attacker.getLocation() == Location.DUEL_ARENA) {
            return;
        }

        for (DegradingItem DI : DegradingItem.getWeapons()) {
            if (!DI.degradeWhenHit()) {
                continue;
            }
            if (attacker.checkItem(DI.getSlot(), DI.getDeg()) || attacker.checkItem(DI.getSlot(), DI.getNonDeg())) {
                ItemDegrading.handleItemDegrading(attacker, DI);
            }
        }

    }

    /**
     * @param victim the person who's being attacked with degradable non-weapons
     * @author Crimson Jul 23, 2017
     */
    protected static void handleDegradingArmor(Player victim) {
        // // System.out.println("Called handleDegradingArmor at
        // "+System.currentTimeMillis());
        if (victim == null)
            return;

        if (victim.getLocation() == Location.FREE_FOR_ALL_ARENA || victim.getLocation() == Location.DUEL_ARENA) {
            return;
        }

        for (DegradingItem DI : DegradingItem.getNonWeapons()) {
            if (!DI.degradeWhenHit()) {
                continue;
            }
            if (victim.checkItem(DI.getSlot(), DI.getDeg()) || victim.checkItem(DI.getSlot(), DI.getNonDeg())) {
                ItemDegrading.handleItemDegrading(victim, DI);
            }
        }
    }

    /**
     * Handles various armor effects for the attacker and victim.
     *
     * @param builder   the attacker's combat builder.
     * @param container the attacker's combat container.
     * @param damage    the total amount of damage dealt.
     */
    // TODO: Use abstraction for this, will need it when more effects are added.
    protected static void handleArmorEffects(Character attacker, Character target, int damage, CombatType combatType) {
        if (attacker.getConstitution() > 0 && damage > 0) {
            if (target != null && target.isPlayer()) {
                Player t2 = (Player) target;
                /** RECOIL **/
                if (t2.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 2550) {
                    long recDamage = Math.round((float) (damage * 0.10));
                    if (recDamage < 1) {
                        recDamage = 1;
                    }
                    if (recDamage > t2.getConstitution())
                        recDamage = t2.getConstitution();
                    attacker.dealDamage(new Hit(recDamage, Hitmask.RED, CombatIcon.DEFLECT));
                    ItemDegrading.handleItemDegrading(t2, DegradingItem.RING_OF_RECOIL);

                    /*
                     * if (t.getEquipment().contains(2550) && t.isHandleRecoil()) { //ring of recoil
                     * if (t.getRingOfRecoilCharges() == 1) { t.getEquipment().delete(2550, 1);
                     * t.getPacketSender().
                     * sendMessage("<img=5> @blu@Your Ring of Recoil has shattered.");
                     * t.setRingOfRecoilCharges(400); return; } t.setHandleRecoil(false); int
                     * returnDamage = Math.round((float) (damage * 0.1)); if (returnDamage < 1) {
                     * returnDamage = 1; } if(attacker.getConstitution() < returnDamage)
                     * returnDamage = attacker.getConstitution(); attacker.dealDamage(new
                     * Hit(returnDamage, Hitmask.RED, CombatIcon.DEFLECT));
                     * t.set(t.getRecoilCharges()-1); t.setHandleRecoil(true); }
                     */
                }

                /** PHOENIX NECK **/
                if (t2.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 11090
                        && t2.getLocation() != Location.DUEL_ARENA) {
                    int restore = (int) (t2.getSkillManager().getMaxLevel(Skill.CONSTITUTION) * .3);
                    if (t2.getSkillManager().getCurrentLevel(
                            Skill.CONSTITUTION) <= t2.getSkillManager().getMaxLevel(Skill.CONSTITUTION) * .2) {
                        t2.performGraphic(new Graphic(1690));
                        t2.getEquipment().delete(t2.getEquipment().getItems()[Equipment.AMULET_SLOT]);
                        t2.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                                t2.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) + restore);
                        t2.getPacketSender().sendMessage(
                                "Your Phoenix Necklace restored your Constitution, but was destroyed in the process.");
                        t2.getUpdateFlag().flag(Flag.APPEARANCE);
                    }
                }
                /** RING OF LIFE **/
                else if ((t2.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 2570
                        || t2.getSkillManager().skillCape(Skill.DEFENCE)) && t2.getLocation() != Location.DUEL_ARENA
                        && t2.getLocation() != Location.WILDERNESS && t2.getLocation() != Location.ZULRAH
                        && t2.getLocation() != Location.GRAVEYARD && t2.getLocation() != Location.FREE_FOR_ALL_ARENA) {
                    if (t2.getSkillManager().getCurrentLevel(
                            Skill.CONSTITUTION) <= t2.getSkillManager().getMaxLevel(Skill.CONSTITUTION) * .1) {
                        if ((t2.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 2570)) {
                            t2.getPacketSender().sendMessage(
                                    "Your Ring of Life tried to teleport you away, and was destroyed in the process.");
                            t2.getEquipment().delete(t2.getEquipment().getItems()[Equipment.RING_SLOT]);
                        }
                        if (t2.getSkillManager().skillCape(Skill.DEFENCE)) {
                            t2.getPacketSender()
                                    .sendMessage("Your Defence Cape effect activated, and tried to teleport you away.");
                        }
                        TeleportHandler.teleportPlayer(t2, GameSettings.DEFAULT_POSITION.copy(),
                                TeleportType.RING_TELE);
                    }
                }

                /*
                 * need loop for enum - .forid()?
                 */

                // WeaponPoison.handleWeaponPoison(((Player)attacker), t2);

            }
        }

        // 25% chance of these barrows armor effects happening.
        if (Misc.exclusiveRandom(4) == 0) {

            // The guthans effect is here.
            if (CombatFactory.fullGuthans(attacker)) {
                target.performGraphic(new Graphic(398));
                attacker.heal(damage);
                return;
            }
            // The rest of the effects only apply to victims that are players.
            /*
             * if (builder.getVictim().isPlayer()) { Player victim = (Player)
             * builder.getVictim();
             *
             * // The torags effect is here. if
             * (CombatFactory.fullTorags(builder.getEntity())) {
             * victim.decrementRunEnergy(Misc.inclusiveRandom(1, 100));
             * victim.performGraphic(new Graphic(399)); return; }
             *
             * // The ahrims effect is here. if
             * (CombatFactory.fullAhrims(builder.getEntity()) &&
             * victim.getSkills()[Skills.STRENGTH].getLevel() >=
             * victim.getSkills()[Skills.STRENGTH].getLevelForExperience()) {
             * victim.getSkills()[Skills.STRENGTH].decreaseLevel(Utility.inclusiveRandom( 1,
             * 10)); Skills.refresh(victim, Skills.STRENGTH); victim.performGraphic(new
             * Graphic(400)); return; }
             *
             * // The karils effect is here. if
             * (CombatFactory.fullKarils(builder.getEntity()) &&
             * victim.getSkills()[Skills.AGILITY].getLevel() >=
             * victim.getSkills()[Skills.AGILITY].getLevelForExperience()) {
             * victim.performGraphic(new Graphic(401));
             * victim.getSkills()[Skills.AGILITY].decreaseLevel(Utility.inclusiveRandom( 1,
             * 10)); Skills.refresh(victim, Skills.AGILITY); return; } }
             */
        }
    }

    /**
     * Handles various prayer effects for the attacker and victim.
     *
     * @param builder   the attacker's combat builder.
     * @param container the attacker's combat container.
     * @param damage    the total amount of damage dealt.
     */
    protected static void handlePrayerEffects(Character attacker, Character target, int damage, CombatType combatType) {
        if (attacker == null || target == null)
            return;
        // Prayer effects can only be done with victims that are players.
        if (target.isPlayer() && damage > 0) {
            Player victim = (Player) target;

            // The redemption prayer effect.
            if (PrayerHandler.isActivated(victim, PrayerHandler.REDEMPTION)
                    && victim.getConstitution() <= (victim.getSkillManager().getMaxLevel(Skill.CONSTITUTION) / 10)) {
                int amountToHeal = (int) (victim.getSkillManager().getMaxLevel(Skill.PRAYER) * .25);
                victim.performGraphic(new Graphic(436));
                victim.getSkillManager().setCurrentLevel(Skill.PRAYER, 0);
                victim.getSkillManager().updateSkill(Skill.PRAYER);
                victim.getSkillManager().setCurrentLevel(Skill.CONSTITUTION, (int) (victim.getConstitution() + amountToHeal));
                victim.getSkillManager().updateSkill(Skill.CONSTITUTION);
                victim.getPacketSender().sendMessage("You've run out of prayer points!");
                PrayerHandler.deactivateAll(victim);
                return;
            }

            // These last prayers can only be done with player attackers.
            if (attacker.isPlayer()) {

                Player p = (Player) attacker;
                // The retribution prayer effect.
                if (PrayerHandler.isActivated(victim, PrayerHandler.RETRIBUTION) && victim.getConstitution() < 1) {
                    victim.performGraphic(new Graphic(437));
                    if (p.getPosition().isWithinDistance(victim.getPosition(), CombatFactory.RETRIBUTION_RADIUS)) {
                        p.dealDamage(new Hit(Misc.inclusiveRandom(CombatFactory.MAXIMUM_RETRIBUTION_DAMAGE),
                                Hitmask.RED, CombatIcon.DEFLECT));
                    }
                } else if (CurseHandler.isActivated(victim, CurseHandler.WRATH) && victim.getConstitution() < 1) {
                    victim.performGraphic(new Graphic(2259));
                    victim.performAnimation(new Animation(12583));
                    if (p.getPosition().isWithinDistance(victim.getPosition(), CombatFactory.RETRIBUTION_RADIUS)) {
                        p.performGraphic(new Graphic(2260));
                        p.dealDamage(new Hit(Misc.inclusiveRandom(CombatFactory.MAXIMUM_RETRIBUTION_DAMAGE),
                                Hitmask.RED, CombatIcon.DEFLECT));
                    }
                }

                if (PrayerHandler.isActivated((Player) attacker, PrayerHandler.SMITE)) {
                    victim.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            victim.getSkillManager().getCurrentLevel(Skill.PRAYER) - damage / 4);
                    if (victim.getSkillManager().getCurrentLevel(Skill.PRAYER) < 0)
                        victim.getSkillManager().setCurrentLevel(Skill.PRAYER, 0);
                    victim.getSkillManager().updateSkill(Skill.PRAYER);
                }
            }
        }

        if (attacker.isPlayer()) {

            Player p = (Player) attacker;
            if (CurseHandler.isActivated(p, CurseHandler.TURMOIL)) {
                if (Misc.getRandom(5) >= 3) {
                    int increase = Misc.getRandom(2);
                    if (p.getLeechedBonuses()[increase] + 1 < 30) {
                        p.getLeechedBonuses()[increase] += 1;
                        BonusManager.sendCurseBonuses(p);
                    }
                }
            }
            if (CurseHandler.isActivated(p, CurseHandler.SOUL_SPLIT) && damage > 0) {
                int form = damage / 50;
                if (form >= 300)
                    form = 300;
                new Projectile(attacker, target, 2263, 44, 3, 43, 31, 0).sendProjectile();
                int finalForm = form;
                TaskManager.submit(new Task(1, p, false) {
                    @Override
                    public void execute() {
                        if (!(attacker == null || target == null || attacker.getConstitution() <= 0)) {
                            target.performGraphic(new Graphic(2264, GraphicHeight.LOW));
                            p.heal(finalForm);
                            if (target.isPlayer()) {
                                Player victim = (Player) target;
                                victim.getSkillManager().setCurrentLevel(Skill.PRAYER,
                                        victim.getSkillManager().getCurrentLevel(Skill.PRAYER) - finalForm);
                                if (victim.getSkillManager().getCurrentLevel(Skill.PRAYER) < 0) {
                                    victim.getSkillManager().setCurrentLevel(Skill.PRAYER, 0);
                                    CurseHandler.deactivateCurses(victim);
                                    PrayerHandler.deactivatePrayers(victim);
                                }
                                victim.getSkillManager().updateSkill(Skill.PRAYER);
                            }
                        }
                        stop();
                    }
                });
            }
            if (p.getCurseActive()[CurseHandler.LEECH_ATTACK] || p.getCurseActive()[CurseHandler.LEECH_DEFENCE]
                    || p.getCurseActive()[CurseHandler.LEECH_STRENGTH] || p.getCurseActive()[CurseHandler.LEECH_MAGIC]
                    || p.getCurseActive()[CurseHandler.LEECH_RANGED]
                    || p.getCurseActive()[CurseHandler.LEECH_SPECIAL_ATTACK]
                    || p.getCurseActive()[CurseHandler.LEECH_ENERGY]) {
                int i, gfx, projectileGfx;
                i = gfx = projectileGfx = -1;
                if (Misc.getRandom(10) >= 7 && p.getCurseActive()[CurseHandler.LEECH_ATTACK]) {
                    i = 0;
                    projectileGfx = 2252;
                    gfx = 2253;
                } else if (Misc.getRandom(15) >= 11 && p.getCurseActive()[CurseHandler.LEECH_DEFENCE]) {
                    i = 1;
                    projectileGfx = 2248;
                    gfx = 2250;
                } else if (Misc.getRandom(11) <= 3 && p.getCurseActive()[CurseHandler.LEECH_STRENGTH]) {
                    i = 2;
                    projectileGfx = 2236;
                    gfx = 2238;
                } else if (Misc.getRandom(20) >= 16 && p.getCurseActive()[CurseHandler.LEECH_RANGED]) {
                    i = 4;
                    projectileGfx = 2236;
                    gfx = 2238;
                } else if (Misc.getRandom(30) >= 24 && p.getCurseActive()[CurseHandler.LEECH_MAGIC]) {
                    i = 6;
                    projectileGfx = 2244;
                    gfx = 2242;
                } else if (Misc.getRandom(30) <= 4 && p.getCurseActive()[CurseHandler.LEECH_SPECIAL_ATTACK]) {
                    i = 7;
                    projectileGfx = 2256;
                    gfx = 2257;
                } else if (Misc.getRandom(30) <= 4 && p.getCurseActive()[CurseHandler.LEECH_ENERGY]) {
                    i = 8;
                    projectileGfx = 2256;
                    gfx = 2257;
                }
                if (i != -1) {
                    p.performAnimation(new Animation(12575));
                    if (i != 7 && i != 8) {
                        if (p.getLeechedBonuses()[i] < 2)
                            p.getLeechedBonuses()[i] += Misc.getRandom(2);
                        BonusManager.sendCurseBonuses(p);
                    }
                    if (target.isPlayer()) {
                        Player victim = (Player) target;
                        new Projectile(attacker, target, projectileGfx, 44, 3, 43, 31, 0).sendProjectile();
                        victim.performGraphic(new Graphic(gfx));
                        if (i != 7 && i != 8) {
                            CurseHandler.handleLeech(victim, i, 2, -25, true);
                            BonusManager.sendCurseBonuses((Player) victim);
                        } else if (i == 7) {
                            // Leech spec
                            boolean leeched = false;
                            if ((victim.getSpecialPercentage() - 10) >= 0) {
                                victim.setSpecialPercentage(victim.getSpecialPercentage() - 10);
                                CombatSpecial.updateBar(victim);
                                victim.getPacketSender()
                                        .sendMessage("Your Special Attack has been leeched by an enemy curse!");
                                leeched = true;
                            }
                            if (leeched) {
                                p.setSpecialPercentage(p.getSpecialPercentage() + 10);
                                if (p.getSpecialPercentage() > 100)
                                    p.setSpecialPercentage(100);
                            }
                        } else if (i == 8) {
                            // Leech energy
                            boolean leeched = false;
                            if ((victim.getRunEnergy() - 30) >= 0) {
                                victim.setRunEnergy(victim.getRunEnergy() - 30);
                                victim.getPacketSender().sendMessage("Your energy has been leeched by an enemy curse!");
                                leeched = true;
                            }
                            if (leeched) {
                                p.setRunEnergy(p.getRunEnergy() + 30);
                                if (p.getRunEnergy() > 100)
                                    p.setRunEnergy(100);
                            }
                        }
                    }
                    // p.getPacketSender().sendMessage("You manage to leech your target's "+(i == 8
                    // ? ("energy") : i == 7 ? ("Special Attack") :
                    // Misc.formatText(Skill.forId(i).toString().toLowerCase()))+".");
                }
            } else {
                boolean sapWarrior = p.getCurseActive()[CurseHandler.SAP_WARRIOR];
                boolean sapRanger = p.getCurseActive()[CurseHandler.SAP_RANGER];
                boolean sapMage = p.getCurseActive()[CurseHandler.SAP_MAGE];
                if (sapWarrior || sapRanger || sapMage) {
                    if (sapWarrior && Misc.getRandom(8) <= 2) {
                        CurseHandler.handleLeech(target, 0, 1, -10, true);
                        CurseHandler.handleLeech(target, 1, 1, -10, true);
                        CurseHandler.handleLeech(target, 2, 1, -10, true);
                        p.performGraphic(new Graphic(2214));
                        p.performAnimation(new Animation(12575));
                        new Projectile(p, target, 2215, 44, 3, 43, 31, 0).sendProjectile();
                        p.getPacketSender().sendMessage("You decrease the your Attack, Strength and Defence level..");
                    } else if (sapRanger && Misc.getRandom(16) >= 9) {
                        CurseHandler.handleLeech(target, 4, 1, -10, true);
                        CurseHandler.handleLeech(target, 1, 1, -10, true);
                        p.performGraphic(new Graphic(2217));
                        p.performAnimation(new Animation(12575));
                        new Projectile(p, target, 2218, 44, 3, 43, 31, 0).sendProjectile();
                        p.getPacketSender().sendMessage("You decrease your target's Ranged and Defence level..");
                    } else if (sapMage && Misc.getRandom(15) >= 10) {
                        CurseHandler.handleLeech(target, 6, 1, -10, true);
                        CurseHandler.handleLeech(target, 1, 1, -10, true);
                        p.performGraphic(new Graphic(2220));
                        p.performAnimation(new Animation(12575));
                        new Projectile(p, target, 2221, 44, 3, 43, 31, 0).sendProjectile();
                        p.getPacketSender().sendMessage("You decrease your target's Magic and Defence level..");
                    }
                }
            }
        }
        if (target.isPlayer()) {
            Player victim = (Player) target;
            if (damage > 0 && Misc.getRandom(10) <= 4) {
                long deflectDamage = -1;
                if (CurseHandler.isActivated(victim, CurseHandler.DEFLECT_MAGIC) && combatType == CombatType.MAGIC) {
                    victim.performGraphic(new Graphic(2228, GraphicHeight.MIDDLE));
                    victim.performAnimation(new Animation(12573));
                    deflectDamage = (int) (damage * 0.20);
                } else if (CurseHandler.isActivated(victim, CurseHandler.DEFLECT_MISSILES)
                        && combatType == CombatType.RANGED) {
                    victim.performGraphic(new Graphic(2229, GraphicHeight.MIDDLE));
                    victim.performAnimation(new Animation(12573));
                    deflectDamage = (int) (damage * 0.20);
                } else if (CurseHandler.isActivated(victim, CurseHandler.DEFLECT_MELEE)
                        && combatType == CombatType.MELEE) {
                    victim.performGraphic(new Graphic(2230, GraphicHeight.MIDDLE));
                    victim.performAnimation(new Animation(12573));
                    deflectDamage = (int) (damage * 0.20);
                }
                if (deflectDamage > 0) {
                    if (deflectDamage > attacker.getConstitution())
                        deflectDamage = attacker.getConstitution();
                    final long toDeflect = deflectDamage;
                    TaskManager.submit(new Task(1, victim, false) {
                        @Override
                        public void execute() {
                            if (attacker == null || attacker.getConstitution() <= 0) {
                                stop();
                            } else
                                attacker.dealDamage(new Hit(toDeflect, Hitmask.RED, CombatIcon.DEFLECT));
                            stop();
                        }
                    });
                }
            }
        }

    }

    protected static void handleSpellEffects(Character attacker, Character target, int damage, CombatType combatType) {
        if (damage <= 0)
            return;
        if (target.isPlayer()) {
            Player t = (Player) target;
            if (t.hasVengeance()) {
                t.setHasVengeance(false);
                t.forceChat("Taste Vengeance!");
                long returnDamage = (int) (damage * 0.75);
                if (attacker.getConstitution() < returnDamage)
                    returnDamage = attacker.getConstitution();
                attacker.dealDamage(new Hit(returnDamage, Hitmask.RED, CombatIcon.MAGIC));
            }
        }
        if (target.isNpc() && attacker.isPlayer()) {
            Player player = (Player) attacker;
            NPC npc = (NPC) target;
            if (npc.getId() == 2043) { // zulrah red form
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(damage, true);
                // // System.out.println("Added "+damage+" to player's zulrah attributes. Current
                // total:
                // "+player.getMinigameAttributes().getZulrahAttributes().getRedFormDamage());
            }
        }
    }

    public static void chargeDragonFireShield(Player player) {
        if (player.getDfsCharges() >= 20) {
            // player.getPacketSender().sendMessage("Your Dragonfire shield is fully charged
            // and can be operated.");
            player.performGraphic(new Graphic(1168));
            player.performAnimation(new Animation(6700));
        } else {
            player.performAnimation(new Animation(6695));
            player.performGraphic(new Graphic(1164));
            player.incrementDfsCharges(1);
            player.getPacketSender().sendMessage("Your shield absorbs some of the dragon's fire, and now has "
                    + player.getDfsCharges() + " " + (player.getDfsCharges() > 1 ? "charges" : "charge") + ".");
        }
        BonusManager.update(player);
    }

    public static void sendFireMessage(Player player) {
        player.getPacketSender().sendMessage("Your shield protects against some of the dragon's fire.");
    }

    public static void handleDragonFireShield(final Player player, final Character target) {
        if (player == null || target == null || target.getConstitution() <= 0 || player.getConstitution() <= 0)
            return;
        if (!player.getLastDfsTimer().elapsed(120000)) {
            player.getPacketSender().sendMessage("Your shield is not ready yet.");
            return;
        }
        player.getCombatBuilder().cooldown(false);
        player.setEntityInteraction(target);
        player.performAnimation(new Animation(6696));
        player.performGraphic(new Graphic(1165));
        TaskManager.submit(new Task(1, player, false) {
            int ticks = 0;

            @Override
            public void execute() {
                switch (ticks) {
                    case 3:
                        new Projectile(player, target, 1166, 44, 3, 43, 31, 0).sendProjectile();
                        break;
                    case 4:
                        Hit h = new Hit(50 + (Misc.getRandom(20) * 10), Hitmask.RED, CombatIcon.MAGIC);
                        target.dealDamage(h);
                        target.performGraphic(new Graphic(1167, GraphicHeight.HIGH));
                        target.getCombatBuilder().addDamage(player, h.getDamage());
                        target.getLastCombat().reset();
                        stop();
                        break;
                }
                ticks++;
            }
        });
        player.getLastDfsTimer().reset();
        player.setDfsCharges(player.getDfsCharges() - 1);
        player.getPacketSender().sendMessage("Your shield has " + player.getDfsCharges() + "/20 charges remaining.");
        BonusManager.update(player);
    }

    public static boolean properLocation(Player player, Player player2) {
        return player.getLocation().canAttack(player, player2);
    }
}
