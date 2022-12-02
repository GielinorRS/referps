package com.ruse.world.content.combat;

import com.ruse.model.Position;
import com.ruse.model.Skill;
import com.ruse.model.container.impl.Costumes;
import com.ruse.model.container.impl.Equipment;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.content.SetBonuses;
import com.ruse.world.content.ammo.SpecialAmmo;
import com.ruse.world.content.combat.effect.EquipmentBonus;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.weapon.FightStyle;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.text.DecimalFormat;

public class Maxhits {

    public static double universal(Player player, Character victim, CombatType combatType, double maxHit) {

        double dmgBoost = (100 + getMaxHit(player, victim, combatType, false)) / 100D;

        maxHit *= dmgBoost;


        if (victim.isNpc()) {
            if (((NPC) victim).getId() == player.getSlayer().getSlayerTask().getNpcId()
                    && victim.getConstitution() >= ((NPC) victim).getDefaultConstitution()) {
                int percent = -1;
                if (player.getEquipment().contains(22000)) {
                    percent = 1;
                } else if (player.getEquipment().contains(22001)) {
                    percent = 2;
                } else if (player.getEquipment().contains(22002)) {
                    percent = 3;
                } else if (player.getEquipment().contains(22003)) {
                    percent = 4;
                } else if (player.getEquipment().contains(22004)) {
                    percent = 5;
                }
                if (Misc.random(99) + 1 <= percent) {
                    maxHit = victim.getConstitution();
                }
            }

            if (SpecialAmmo.isAmmo(player, SpecialAmmo.DEATH)) {
                if (victim.isNpc()) {
                    NPC npc = (NPC) victim;
                    if (!NPCDrops.globalNpcs.contains(npc.getId())) {
                        if (Misc.random(99) + 1 <= SpecialAmmo.getAmmo(player).getBoost()) {
                            maxHit = victim.getConstitution() * 10;
                        }
                    }
                }
            }
        }

/*
        if (victim.isNpc()){
            NPC npc = (NPC) victim;
            if (NPCDrops.globalNpcs.contains(npc.getId())){
                if (maxHit >= npc.getDefaultConstitution() / 100)
                    maxHit = npc.getDefaultConstitution() / 100;
            }
        }*/

        maxHit *= getDamageBoost(player,victim, combatType);

        return maxHit;
    }

    
    public static NPC baseNPC = new NPC(1, new Position(1,1,0));

    public static double getDamageBoost(Player player, Character victim, CombatType combatType) {
        double maxHit = 1;

        if (player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 20592 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23688 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23827 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23883 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23512 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23459 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23403 ||
                player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 15916) {
            maxHit *= 2;
        }

        if (player.getInventory().contains(23781)) {
            maxHit *=  1.75;
        } else if (player.getInventory().contains(4442)) {
            maxHit *=  1.5;
        }

        if (player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23544
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23627
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23628) {
            maxHit *=  1.1;
        }
        if (player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23013) {
            maxHit *=  1.05;
        }
        if (player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23014
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23015
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23016
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23017) {
            maxHit *=  1.1;
        }
        if (player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23814
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 1037
                || player.getEquipment().getItems()[Equipment.HEAD_SLOT].getId() == 23621) {
            maxHit *=  1.2;
        }

        maxHit *= SetBonuses.getDamageBoost(player);


        return maxHit;
    }

        public static double getMaxHit(Player player, Character victim, CombatType combatType, boolean slayerNPC) {
        int percent = 0;

        if (victim.isNpc()) {
            if (((NPC) victim).getId() == player.getSlayer().getSlayerTask().getNpcId()
            || slayerNPC) {
                if (player.getEquipment().contains(22005)) {
                    percent += 5;
                }
                if (player.getEquipment().contains(23352)) {
                    percent += 5;
                }
                if (player.getEquipment().contains(23354)) {
                    percent += 5;
                }

                if (player.getEquipment().contains(22000)) {
                    percent += 5;
                }
                if (player.getEquipment().contains(22001)) {
                    percent += 10;
                }
                if (player.getEquipment().contains(22002)) {
                    percent += 15;
                }
                if (player.getEquipment().contains(22003)) {
                    percent += 20;
                }
                if (player.getEquipment().contains(22004)) {
                    percent += 25;
                }


                if (player.getGodsHeartTimer() > 0) {
                    percent += 40;
                } else if (player.getWarriorHeartTimer() > 0 && combatType == CombatType.MELEE) {
                    percent += 20;
                } else if (player.getSorcererHeartTimer() > 0 && combatType == CombatType.MAGIC) {
                    percent += 20;
                } else if (player.getRangerHeartTimer() > 0 && combatType == CombatType.RANGED) {
                    percent += 20;
                }


                if (player.getEquipment().contains(23509)) {
                    percent += 10;
                }
                if (player.getEquipment().contains(23507)) {
                    percent += 25;
                }
                if (player.getEquipment().contains(23554)) {
                    percent += 40;
                }
            }

            if (player.getEquipment().contains(23520)
                    && ((NPC) victim).getId() == 440)
                percent += 50;

            if (player.getEquipment().contains(23522)
                    && ((NPC) victim).getId() == 438)
                percent += 50;

        }

        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 1906) {
            percent += 10;
        }
        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 1801) {// admin pet
            percent += 15;
        }

        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9013) {// admin pet
            percent += 25;
        }
        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9016) {// admin pet
            percent += 25;
        }

        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9833) {// admin pet
            percent += 30;
        }

        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9861) {// admin pet
            percent += 10;
        }
        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() >= 9862
                && player.getSummoning().getFamiliar().getSummonNpc().getId() <= 9868) {// admin pet
            percent += 20;
        }


        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() >= 9872
                && player.getSummoning().getFamiliar().getSummonNpc().getId() <= 9874) {
            percent += 10;
        }
        if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9875) {
            percent += 20;
        }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9876) {
                percent += 35;
            }


            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 9021) {
                percent += 25;
            }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10013) {
                percent += 35;
            }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10025) {
                percent += 30;
            }


            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10002) {
                percent += 50;
            }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10003) {
                percent += 50;
            }


            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 28025) {
                percent += 60;
            }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 28492) {
                percent += 50;
            }
            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 11873) {
                percent += 25;
            }

            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10031) {
                percent += 25;
            }

            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10004) {// admin pet
                percent += 25;
            }

            if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                    && player.getSummoning().getFamiliar().getSummonNpc().getId() == 10004) {// admin pet
                percent += 25;
            }

        for (Costumes costume : Costumes.values()) {
            if (player.getEquipment().contains(costume.getItemId()))
                percent += costume.getDamageBoost();
        }


        if (player.getDoubleDMGTimer() > 0) {
            percent += 50;
        }
        if (player.getMinutesVotingDMG() > 0) {
            percent += 100;
        }



        if ((EquipmentBonus.voidMelee(player) && combatType == CombatType.MELEE)
                || (EquipmentBonus.voidMage(player) && combatType == CombatType.MAGIC)
                || (EquipmentBonus.voidRange(player) && combatType == CombatType.RANGED)) {
            percent += EquipmentBonus.voidElite(player) ? 15 : 7.5;
        }


            if (player.getAmountDonated() >= 25000) {
                percent += 90;
            } else   if (player.getAmountDonated() >= 10000) {
                percent += 70;
            } else  if (player.getAmountDonated() >= 5000) {
                percent += 50;
            } else if (player.getAmountDonated() >= 1000) {
                percent += 35;
            } else if (player.getAmountDonated() >= 500) {
                percent += 25;
            } else if (player.getAmountDonated() >= 250) {
                percent += 15;
            } else if (player.getAmountDonated() >= 50) {
                percent += 10;
            } else if (player.getAmountDonated() >= 10) {
                percent += 5;
            }       // percent += 50;

        return percent;
    }


    public static int melee(Character entity, Character victim) {
        double maxHit = 0;
        if (entity.isNpc()) {
            NPC npc = (NPC) entity;
            maxHit = npc.getDefinition().getMaxHit();
            if (npc.getStrengthWeakened()[0]) {
                maxHit -= (int) (0.10 * maxHit);
            } else if (npc.getStrengthWeakened()[1]) {
                maxHit -= (int) (0.20 * maxHit);
            } else if (npc.getStrengthWeakened()[2]) {
                maxHit -= (int) (0.30 * maxHit);
            }
        } else if (entity.isPlayer()) {
            Player player = (Player) entity;

            double effective = getEffectiveStr(player);
            double strengthBonus = player.getBonusManager().getOtherBonus()[0];
            double specialBonus = 1;

            // Use our multipliers to adjust the maxhit...

            maxHit = 1.3 + effective / 10 + strengthBonus / 80 + effective * strengthBonus / 640;

            // Special effects also affect maxhit
            if (player.isSpecialActivated() && player.getCombatSpecial().getCombatType() == CombatType.MELEE) {
                specialBonus = player.getCombatSpecial().getStrengthBonus();
            }

            if (specialBonus > 1) {
                maxHit = Math.round(maxHit) * specialBonus;
            } else {
                maxHit = (int) maxHit;
            }

            maxHit *= 10;

            maxHit = universal(player, victim, CombatType.MELEE, maxHit);

        }

        // Dharoks effect
        if (CombatFactory.fullDharoks(entity)) {
            long hitpoints = entity.getConstitution() / 10;
            if (entity.isNpc()) {
                long missingHealth = ((NPC) entity).getDefinition().getHitpoints() - hitpoints;
                double addToHit = missingHealth * 0.01 + 1;
                maxHit *= addToHit;
            } else {
                long missingHealth = ((Player) entity).getSkillManager().getMaxLevel(Skill.CONSTITUTION) - hitpoints;
                double addToHit = missingHealth * 0.01 + 1;
                maxHit *= addToHit;
                if (maxHit >= 990)
                    maxHit = 990;
            }
        }

        if (victim != null && victim.isNpc()) {
            maxHit = NpcMaxHitLimit.limit((NPC) victim, maxHit, CombatType.MELEE);
        }


        return (int) Math.floor(maxHit);
    }

    public static int ranged(Character entity, Character victim) {
        double maxHit = 0;

        if (entity.isNpc()) {
            NPC npc = (NPC) entity;
            maxHit = npc.getDefinition().getMaxHit();
        } else if (entity.isPlayer()) {
            Player player = (Player) entity;

            double rangedStrength = player.getBonusManager().getOtherBonus()[1];
            double rangeLevel = player.getSkillManager().getCurrentLevel(Skill.RANGED);

            // Prayers
            double prayerMod = 1.0;
            if (PrayerHandler.isActivated(player, PrayerHandler.SHARP_EYE) || CurseHandler.isActivated(player, CurseHandler.LEECH_RANGED)) {
                prayerMod = 1.05;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.HAWK_EYE)) {
                prayerMod = 1.10;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.EAGLE_EYE)) {
                prayerMod = 1.15;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.RIGOUR)) {
                prayerMod = 1.23;
            } else if (CurseHandler.isActivated(player, CurseHandler.TURMOIL)) {
                prayerMod = 1.25;
            }

            double otherBonuses = 1;


            // Do calculations of maxhit...
            double effectiveRangeDamage = (int) (rangeLevel * prayerMod * otherBonuses);

            double baseDamage = 1.3 + effectiveRangeDamage / 10 + rangedStrength / 80 + effectiveRangeDamage * rangedStrength / 640;

            double specialBonus = 1;
            // Special attacks!
            if (player.isSpecialActivated() && player.getCombatSpecial().getCombatType() == CombatType.RANGED) {
                specialBonus = player.getCombatSpecial().getStrengthBonus();
            }

            maxHit = (int) baseDamage * specialBonus;

            maxHit *= 10;
            maxHit = universal(player, victim, CombatType.RANGED, maxHit);

        }


        if (victim != null && victim.isNpc()) {
            maxHit = (int) NpcMaxHitLimit.limit((NPC) victim, maxHit, CombatType.RANGED);
        }
        return (int) Math.floor(maxHit);
    }

    public static int magic(Character entity, Character victim) {
        double maxHit = 0;

        if (entity.isNpc()) {
            NPC npc = (NPC) entity;
            maxHit = npc.getDefinition().getMaxHit();
        } else if (entity.isPlayer()) {
            Player player = (Player) entity;

            double magicStrength = player.getBonusManager().getOtherBonus()[3];
            double magicLevel = player.getSkillManager().getCurrentLevel(Skill.MAGIC);

            // Prayers
            double prayerMod = 1.0;
            if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_WILL) || CurseHandler.isActivated(player, CurseHandler.LEECH_MAGIC)) {
                prayerMod = 1.05;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_LORE)) {
                prayerMod = 1.10;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.MYSTIC_MIGHT)) {
                prayerMod = 1.15;
            } else if (PrayerHandler.isActivated(player, PrayerHandler.AUGURY)) {
                prayerMod = 1.23;
            } else if (CurseHandler.isActivated(player, CurseHandler.TURMOIL)) {
                prayerMod = 1.25;
            }

            double otherBonuses = 1;

            // Void hits 10% more
            // Do calculations of maxhit...
            double effectiveMagicDamage = (int) (magicLevel * prayerMod * otherBonuses);


            double baseDamage = 1.3 + effectiveMagicDamage / 10 + magicStrength / 80 + effectiveMagicDamage * magicStrength / 640;

            double specialBonus = 1;
            // Special attacks!
            if (player.isSpecialActivated() && player.getCombatSpecial().getCombatType() == CombatType.MAGIC) {
                specialBonus = player.getCombatSpecial().getStrengthBonus();
            }

            maxHit = (int) baseDamage * specialBonus;

            maxHit *= 10;
            maxHit = universal(player, victim, CombatType.MAGIC, maxHit);
        }


        if (victim != null && victim.isNpc()) {
            maxHit = (int) NpcMaxHitLimit.limit((NPC) victim, maxHit, CombatType.RANGED);
        }
        return (int) Math.floor(maxHit);
    }

    public static double getEffectiveStr(Player plr) {
        double styleBonus = 0;
        FightStyle style = plr.getFightType().getStyle();

        double otherBonus = 1;

        double prayerMod = 1.0;

        if (PrayerHandler.isActivated(plr, PrayerHandler.BURST_OF_STRENGTH) || CurseHandler.isActivated(plr, CurseHandler.LEECH_STRENGTH)) {
            prayerMod = 1.05;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.SUPERHUMAN_STRENGTH)) {
            prayerMod = 1.1;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.ULTIMATE_STRENGTH)) {
            prayerMod = 1.15;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.CHIVALRY)) {
            prayerMod = 1.18;
        } else if (PrayerHandler.isActivated(plr, PrayerHandler.PIETY)) {
            prayerMod = 1.23;
        } else if (CurseHandler.isActivated(plr, CurseHandler.TURMOIL)) {
            prayerMod = 1.25;
        }

        int number = (int) (plr.getSkillManager().getCurrentLevel(Skill.STRENGTH) * prayerMod * otherBonus + styleBonus);
        return number;
    }

}
