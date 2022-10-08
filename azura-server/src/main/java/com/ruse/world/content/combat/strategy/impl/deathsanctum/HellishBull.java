package com.ruse.world.content.combat.strategy.impl.deathsanctum;


import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;


public class HellishBull implements CombatStrategy {

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


        if (npc.totalAttacks == 3) {
            throwOrbs(npc, player);
            npc.totalAttacks = 0;
        } else {
            npc.totalAttacks++;
            BaseAttacks.rangedAttack(npc, player);
        }

        return true;
    }


    private static void throwOrbs(NPC npc, Player player) {
        ArrayList<Position> orbs = new ArrayList<>();

        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(811));
        for (final Player t : BaseAttacks.getPlayers(player)) {
            if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                continue;
            new Projectile(npc, t, 346, 44, 3, 41, 20, 0).sendProjectile();
        }

        for (Player t : BaseAttacks.getPlayers(player)) {
            if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                continue;
            orbs.add(t.getPosition());
        }

        npc.forceChat("Explode!");

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (npc == null || npc.isDying() || npc.getConstitution() <= 0) {
                    stop();
                    return;
                }
                if (tick == 0) {
                    player.sendMessage("@blu@The Hellish Bull sent a blast your way.");
                } else if (tick == 1) {
                    for (Position pos : orbs) {
                        for (Player t : BaseAttacks.getPlayers(player)) {
                            if (t == null || t.isTeleporting() || npc.getPosition().distanceToPoint(t.getPosition().getX(), t.getPosition().getY()) > 25)
                                continue;
                            handleOrb(npc, t, pos);
                        }
                    }

                    npc.setChargingAttack(false);
                    stop();
                }
                tick++;
            }
        });
    }

    private static void handleOrb(NPC npc, Player player, Position position) {
        player.getPacketSender().sendGlobalGraphic(new Graphic(346, GraphicHeight.LOW), position);

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (npc == null || npc.isDying() || npc.getConstitution() <= 0) {
                    stop();
                    return;
                }
                if (tick % 2 == 0)
                player.getPacketSender().sendGlobalGraphic(new Graphic(346, GraphicHeight.LOW), position);
                if (tick == 4) {
                    for (int c = -2; c <= 2; c++) {
                        for (int r = -2; r <= 2; r++) {
                            player.getPacketSender().sendGlobalGraphic(new Graphic(346, GraphicHeight.LOW), position.copy().add(r, c));
                        }
                    }
                    if (Locations.goodDistance(position, player.getPosition(), 2)) {
                        player.dealDamage(new Hit(990 + Misc.getRandom(700), Hitmask.RED, CombatIcon.BLOCK));
                        player.sendMessage("The blast exploded and damaged you.");
                    }
                    stop();
                }
                tick++;
            }
        });
    }

    @Override
    public int attackDelay(Character entity) {
        return entity.getAttackSpeed();
    }

    @Override
    public int attackDistance(Character entity) {
        return 40;
    }

    @Override
    public CombatType getCombatType() {
        return CombatType.MIXED;
    }

}