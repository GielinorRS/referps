package com.ruse.world.content.combat.strategy.impl.deathsanctum;


import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.BaseAttacks;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;


public class MidnightOrk implements CombatStrategy {

    @Override
    public boolean canAttack(Character entity, Character victim) {
        return victim.isPlayer();
    }

    @Override
    public CombatContainer attack(Character entity, Character victim) {
        return null;
    }

    @Override
    public boolean customContainerAttack(Character entity, Character victim) {
        NPC npc = (NPC) entity;
        if (victim.getConstitution() <= 0) {
            return true;
        }
        if (npc.isChargingAttack()) {
            return true;
        }
        if (!victim.isPlayer()) {
            return true;
        }
        Player player = (Player) victim;


        if (npc.totalAttacks == 5) {
            drip(npc, player);
            npc.totalAttacks = 0;
        } else {
            npc.totalAttacks++;
            BaseAttacks.magicAttack(npc, player);
        }

        return true;
    }


    private static void drip(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(811));

        for (final Player t : BaseAttacks.getPlayers(player)) {
            if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                continue;
            dripPlayer(npc, t);
        }

        npc.setChargingAttack(false);
    }

    private static void dripPlayer(NPC npc, Player player) {
        ArrayList<Position> orbs = new ArrayList<>();
        Player target = player;

        final Position[] lastPosition = {player.getPosition()};

        new Projectile(npc, target, 1013, 44, 3, 41, 43, 0).sendProjectile();
        npc.forceChat("Dodge this!");

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (npc == null || npc.isDying() || npc.getConstitution() <= 0 || player.getLocation() != Locations.Location.DEATH_SANCTUM) {
                    stop();
                    return;
                }

                if (tick >= 3 && tick % 2 == 1) {
                    for (final Player t :  BaseAttacks.getPlayers(player)) {
                        if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                            continue;
                        for (Position pos : orbs) {
                            player.getPacketSender().sendGlobalGraphic(new Graphic(1013, GraphicHeight.LOW), pos);
                            if (t.getPosition().sameAs(pos)) {
                                t.dealDamage(new Hit(30 + Misc.getRandom(30), Hitmask.RED, CombatIcon.BLOCK));
                            }
                        }
                    }
                } else {
                    for (final Player t :  BaseAttacks.getPlayers(player)) {
                        if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                            continue;
                        for (Position pos : orbs) {
                            player.getPacketSender().sendGlobalGraphic(new Graphic(1013, GraphicHeight.LOW), pos);
                            if (t.getPosition().sameAs(pos)) {
                                t.dealDamage(new Hit(30 + Misc.getRandom(30), Hitmask.RED, CombatIcon.BLOCK));
                            }
                        }
                    }
                }

                if (tick == 2) {
                    lastPosition[0] = player.getPosition();
                    player.sendMessage("@blu@The Midnight Ork has thrown a following orb towards you.");
                }

                if (tick >= 3) {
                    player.getPacketSender().sendGlobalGraphic(new Graphic(1013, GraphicHeight.LOW), lastPosition[0]);
                    orbs.add(lastPosition[0]);
                    lastPosition[0] = player.getPosition();
                }

                tick++;
                if (tick == 15) {
                    stop();
                }
            }
        });

    }


    @Override
    public int attackDelay(Character entity) {
        return entity.getAttackSpeed();
    }

    @Override
    public int attackDistance(Character entity) {
        return 30;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }

}