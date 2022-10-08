package com.ruse.world.content.combat;

import com.ruse.model.Graphic;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.effect.EquipmentBonus;
import com.ruse.world.content.combat.magic.CombatSpell;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.range.CombatRangedAmmo.RangedWeaponData;
import com.ruse.world.content.combat.weapon.FightType;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.DropUtils;
import com.ruse.world.content.skill.impl.summoning.Familiar;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class DesolaceFormulas {

    /*
     * =============================================================================
     * =
     */
    /*
     * ===================================MELEE=====================================
     */

    public static int calculateMaxMeleeHit(Character entity, Character victim) {
        double maxHit = 0;
        if (entity.isNpc()) {
            NPC npc = (NPC) entity;
            maxHit = npc.getDefinition().getMaxHit();
            if (npc.getStrengthWeakened()[0]) {
                maxHit -= (int) ((0.10) * (maxHit));
            } else if (npc.getStrengthWeakened()[1]) {
                maxHit -= (int) ((0.20) * (maxHit));
            } else if (npc.getStrengthWeakened()[2]) {
                maxHit -= (int) ((0.30) * (maxHit));
            }
            /** CUSTOM NPCS **/
            if (npc.getId() == 2026) { // Dharok the wretched
                maxHit += (int) ((int) (npc.getDefaultConstitution() - npc.getConstitution()) * 0.2);
            }
        } else {
            Player plr = (Player) entity;

            double base = 0;
            double effective = getEffectiveStr(plr);
            double specialBonus = 1;
            if (plr.isSpecialActivated()) {
                specialBonus = plr.getCombatSpecial().getStrengthBonus();
            }
            double strengthBonus = plr.getBonusManager().getOtherBonus()[0];
            base = (13 + effective + (strengthBonus / 8) + ((effective * strengthBonus) / 65)) / 11;
            if (plr.getEquipment().getItems()[3].getId() == 4718 && plr.getEquipment().getItems()[0].getId() == 4716
                    && plr.getEquipment().getItems()[4].getId() == 4720
                    && plr.getEquipment().getItems()[7].getId() == 4722)
                base += ((plr.getSkillManager().getMaxLevel(Skill.CONSTITUTION) - plr.getConstitution()) * .045) + 1;
            if (specialBonus > 1)
                base = (base * specialBonus);
            /*
             * if (hasObsidianEffect(plr))// || EquipmentBonus.wearingVoid(plr))//,
             * CombatType.MELEE)) base = (base * 1.2);
             */

            if (victim.isNpc()) {
                NPC npc = (NPC) victim;
                if (npc.getDefenceWeakened()[0]) {
                    base += (int) ((0.10) * (base));
                } else if (npc.getDefenceWeakened()[1]) {
                    base += (int) ((0.20) * (base));
                } else if (npc.getDefenceWeakened()[2]) {
                    base += (int) ((0.30) * (base));
                }

                /** SLAYER HELMET **/
                if (npc.getId() == plr.getSlayer().getSlayerTask().getNpcId()) {
                    if (plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 13263) {
                        base *= 1.12;
                    }
                }

                Familiar playerFamiliar = plr.getSummoning().getFamiliar();

                if (playerFamiliar != null) {
                    double bonus = DropUtils.getDamageBonus(playerFamiliar.getSummonNpc().getId());
                    base *= bonus;
                }
                
            }

            maxHit = (base *= 10);
            if (plr.getInventory().contains(4442)) {
            	maxHit *=1.5;
            }
        }
        if (victim.isPlayer()) {
            Player p = (Player) victim;
            if (p.hasStaffOfLightEffect()) {
                maxHit = maxHit / 2;
                p.performGraphic(new Graphic(2319));
            }
        }
        if (victim != null && victim.isNpc()) {
            maxHit = NpcMaxHitLimit.limit((NPC) victim, maxHit, CombatType.MELEE);
        }
        
        return (int) Math.floor(maxHit);
    }

    /**
     * Calculates a player's Melee attack level (how likely that they're going to
     * hit through defence)
     *
     * @param plr The player's Meelee attack level
     * @return The player's Melee attack level
     */
    @SuppressWarnings("incomplete-switch")
    public static int getMeleeAttack(Player plr) {
        int attackLevel = plr.getSkillManager().getCurrentLevel(Skill.ATTACK);
        switch (plr.getFightType().getStyle()) {
            case AGGRESSIVE:
                attackLevel += 3;
                break;
            case CONTROLLED:
                attackLevel += 1;
                break;
        }
        // boolean hasVoid = EquipmentBonus.wearingVoid(plr);//, CombatType.MELEE);

        if (PrayerHandler.isActivated(plr, PrayerHandler.CLARITY_OF_THOUGHT)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.05;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.IMPROVED_REFLEXES)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.1;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.INCREDIBLE_REFLEXES)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.15;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.CHIVALRY)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.15;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.PIETY)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.2;
        } else if (CurseHandler.isActivated(plr, CurseHandler.LEECH_ATTACK)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.05 + plr.getLeechedBonuses()[2];
        } else if (CurseHandler.isActivated(plr, CurseHandler.TURMOIL)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.3 + plr.getLeechedBonuses()[2];
        }

        if (EquipmentBonus.voidMelee(plr)) {
            attackLevel += plr.getSkillManager().getMaxLevel(Skill.ATTACK) * 0.1;
            if (plr.getCurrentClanChat() != null && plr.getCurrentClanChat().getName().equalsIgnoreCase("Debug")) {
                plr.getPacketSender()
                        .sendMessage("Void Melee accuracy buff applied. Is " + attackLevel + ", pre-equip bonuses");
            }
        }
        attackLevel *= plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;
        int i = (int) plr.getBonusManager().getAttackBonus()[bestMeleeAtk(plr)];

        // // System.out.println("hello world");
        return (int) (attackLevel + (attackLevel * 0.15) + (i + i * 0.04));
    }

    /**
     * Calculates a player's Melee Defence level
     *
     * @param plr The player to calculate Melee defence for
     * @return The player's Melee defence level
     */
    public static int getMeleeDefence(Player plr) {
        int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE);
        int i = (int) plr.getBonusManager().getDefenceBonus()[bestMeleeDef(plr)];
        if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
        } else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
        } else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
        } else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
        } else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
        }
        return (int) (defenceLevel + (defenceLevel * 0.15) + (i + i * 0.05));
    }

    public static int bestMeleeDef(Player p) {
        if (p.getBonusManager().getDefenceBonus()[0] > p.getBonusManager().getDefenceBonus()[1]
                && p.getBonusManager().getDefenceBonus()[0] > p.getBonusManager().getDefenceBonus()[2]) {
            return 0;
        }
        if (p.getBonusManager().getDefenceBonus()[1] > p.getBonusManager().getDefenceBonus()[0]
                && p.getBonusManager().getDefenceBonus()[1] > p.getBonusManager().getDefenceBonus()[2]) {
            return 1;
        }
        return p.getBonusManager().getDefenceBonus()[2] <= p.getBonusManager().getDefenceBonus()[0]
                || p.getBonusManager().getDefenceBonus()[2] <= p.getBonusManager().getDefenceBonus()[1] ? 0 : 2;
    }

    public static int bestMeleeAtk(Player p) {
        if (p.getBonusManager().getAttackBonus()[0] > p.getBonusManager().getAttackBonus()[1]
                && p.getBonusManager().getAttackBonus()[0] > p.getBonusManager().getAttackBonus()[2]) {
            return 0;
        }
        if (p.getBonusManager().getAttackBonus()[1] > p.getBonusManager().getAttackBonus()[0]
                && p.getBonusManager().getAttackBonus()[1] > p.getBonusManager().getAttackBonus()[2]) {
            return 1;
        }
        return p.getBonusManager().getAttackBonus()[2] <= p.getBonusManager().getAttackBonus()[1]
                || p.getBonusManager().getAttackBonus()[2] <= p.getBonusManager().getAttackBonus()[0] ? 0 : 2;
    }

    /**
     * Obsidian items
     */

    @SuppressWarnings("incomplete-switch")
    public static int getStyleBonus(Player plr) {
        switch (plr.getFightType().getStyle()) {
            case AGGRESSIVE:
            case ACCURATE:
                return 3;
            case CONTROLLED:
                return 1;
        }
        return 0;
    }

    public static double getEffectiveStr(Player plr) {
        return ((plr.getSkillManager().getCurrentLevel(Skill.STRENGTH)) * getPrayerStr(plr)) + getStyleBonus(plr);
    }

    public static double getPrayerStr(Player plr) {
        if (plr.getPrayerActive()[1] || plr.getCurseActive()[CurseHandler.LEECH_STRENGTH])
            return 1.05;
        else if (plr.getPrayerActive()[6])
            return 1.1;
        else if (plr.getPrayerActive()[14])
            return 1.15;
        else if (plr.getPrayerActive()[24])
            return 1.18;
        else if (plr.getPrayerActive()[25])
            return 1.23;
        else if (plr.getCurseActive()[CurseHandler.TURMOIL])
            return 1.24;
        return 1;
    }

    /**
     * Calculates a player's Ranged attack (level). Credits: Dexter Morgan
     *
     * @param plr The player to calculate Ranged attack level for
     * @return The player's Ranged attack level
     */
    public static int getRangedAttack(Player plr) {
        int rangeLevel = plr.getSkillManager().getCurrentLevel(Skill.RANGED);
        // boolean hasVoid = EquipmentBonus.wearingVoid(plr);//, CombatType.RANGED);
        double accuracy = plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;
        rangeLevel *= accuracy;
        if (plr.getCurseActive()[PrayerHandler.SHARP_EYE] || plr.getCurseActive()[CurseHandler.SAP_RANGER]) {
            rangeLevel *= 1.05;
        } else if (plr.getPrayerActive()[PrayerHandler.HAWK_EYE]) {
            rangeLevel *= 1.10;
        } else if (plr.getPrayerActive()[PrayerHandler.EAGLE_EYE]) {
            rangeLevel *= 1.15;
        } else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
            rangeLevel *= 1.22;
        } else if (plr.getCurseActive()[CurseHandler.LEECH_RANGED]) {
            rangeLevel *= 1.10;
        }
        if (EquipmentBonus.voidRange(plr)) {
            rangeLevel *= 1.10;
            if (plr.getCurrentClanChat() != null && plr.getCurrentClanChat().getName().equalsIgnoreCase("Debug")) {
                plr.getPacketSender()
                        .sendMessage("Void Range accuracy buff applied. Is " + rangeLevel + ", pre-equip bonuses");
            }
            if (EquipmentBonus.voidRange(plr)) {
                rangeLevel *= 1.10;
            }
            if (plr.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId() == 16879) {
                rangeLevel *= 1.10;
            }
            if (plr.getEquipment().getItems()[Equipment.WEAPON_SLOT].getId() == 18332) {
                rangeLevel *= 1.90;
            }
        }

        Familiar playerFamiliar = plr.getSummoning().getFamiliar();

        if (playerFamiliar != null) {
            double bonus = DropUtils.getDamageBonus(playerFamiliar.getSummonNpc().getId());
            rangeLevel *= bonus;
        }
        /*
         * if (hasVoid && accuracy > 1.15) rangeLevel *= 1.68;
         */
        /*
         * Slay helm
         *
         * if(plr.getAdvancedSkills().getSlayer().getSlayerTask() != null &&
         * plr.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 15492) {
         * if(plr.getCombatAttributes().getCurrentEnemy() != null &&
         * plr.getCombatAttributes().getCurrentEnemy().isNpc()) { NPC n =
         * (NPC)plr.getCombatAttributes().getCurrentEnemy(); if(n != null && n.getId()
         * == plr.getAdvancedSkills().getSlayer().getSlayerTask().getNpcId()) rangeLevel
         * *= 1.12; } }
         */
        return (int) (rangeLevel + (plr.getBonusManager().getAttackBonus()[4] * 2));
    }

    /**
     * Calculates a player's Ranged defence level.
     *
     * @param plr The player to calculate the Ranged defence level for
     * @return The player's Ranged defence level
     */
    public static int getRangedDefence(Player plr) {
        int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE);
        if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
        } else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
        } else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
        } else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
        } else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.20 + plr.getLeechedBonuses()[0];
        }
        return (int) (defenceLevel + plr.getBonusManager().getDefenceBonus()[4]
                + (plr.getBonusManager().getDefenceBonus()[4] / 2));
    }

    public static int getMagicAttack(Player plr) {
        // boolean voidEquipment = EquipmentBonus.wearingVoid(plr);//,
        // CombatType.MAGIC);
        int attackLevel = plr.getSkillManager().getCurrentLevel(Skill.MAGIC);
        attackLevel *= plr.isSpecialActivated() ? plr.getCombatSpecial().getAccuracyBonus() : 1;
        if (plr.getPrayerActive()[PrayerHandler.MYSTIC_WILL] || plr.getCurseActive()[CurseHandler.SAP_MAGE]) {
            attackLevel *= 1.05;
        } else if (plr.getPrayerActive()[PrayerHandler.MYSTIC_LORE]) {
            attackLevel *= 1.10;
        } else if (plr.getPrayerActive()[PrayerHandler.MYSTIC_MIGHT]) {
            attackLevel *= 1.15;
        } else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
            attackLevel *= 1.22;
        } else if (plr.getCurseActive()[CurseHandler.LEECH_MAGIC]) {
            attackLevel *= 1.18;
        }
        if (EquipmentBonus.voidMage(plr)) {
            attackLevel *= 1.3;
            if (plr.getCurrentClanChat() != null && plr.getCurrentClanChat().getName().equalsIgnoreCase("Debug")) {
                plr.getPacketSender()
                        .sendMessage("Void Mage accuracy buff applied. Is " + attackLevel + ", pre-equip bonuses");
            }
        }

        Familiar playerFamiliar = plr.getSummoning().getFamiliar();

        if (playerFamiliar != null) {
            double bonus = DropUtils.getDamageBonus(playerFamiliar.getSummonNpc().getId());
            attackLevel *= bonus;
        }

        return (int) (attackLevel + (plr.getBonusManager().getAttackBonus()[3] * 2));
    }

    /**
     * Calculates a player's magic defence level
     *
     * @param player The player to calculate magic defence level for
     * @return The player's magic defence level
     */
    public static int getMagicDefence(Player plr) {

        int defenceLevel = plr.getSkillManager().getCurrentLevel(Skill.DEFENCE) / 2
                + plr.getSkillManager().getCurrentLevel(Skill.MAGIC) / 2;

        if (plr.getPrayerActive()[PrayerHandler.THICK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.05;
        } else if (plr.getPrayerActive()[PrayerHandler.ROCK_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.1;
        } else if (plr.getPrayerActive()[PrayerHandler.STEEL_SKIN]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.15;
        } else if (plr.getPrayerActive()[PrayerHandler.CHIVALRY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.2;
        } else if (plr.getPrayerActive()[PrayerHandler.PIETY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.RIGOUR]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getPrayerActive()[PrayerHandler.AUGURY]) {
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.25;
        } else if (plr.getCurseActive()[CurseHandler.TURMOIL]) { // turmoil
            defenceLevel += plr.getSkillManager().getMaxLevel(Skill.DEFENCE) * 0.20 + plr.getLeechedBonuses()[0];
        }

        return (int) (defenceLevel + plr.getBonusManager().getDefenceBonus()[3]
                + (plr.getBonusManager().getDefenceBonus()[3] / 3));
    }


}
