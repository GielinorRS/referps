package com.ruse.world.content.combat.strategy.impl.deathsanctum;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Animation;
import com.ruse.model.Locations;
import com.ruse.model.Projectile;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.HitQueue;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.List;

public class BaseAttacks {

    public static List<Player> getPlayers(Player p) {
        if (p.getSanctumOfDeathParty() != null && p.getLocation() == Locations.Location.DEATH_SANCTUM) {
            return p.getSanctumOfDeathParty().getPlayers();
        }
        if (p.getSanctumOfDeathParty() != null && p.getLocation() == Locations.Location.ANIMA_CHAMBERS) {
            return p.getSanctumOfDeathParty().getPlayers();
        }
        return Misc.getCombinedPlayerList(p);
    }

    public static void magicAttack(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));

        for (final Player near : getPlayers(player)) {
            if (near == null || near.isTeleporting())
                continue;
            if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                continue;
            new HitQueue.CombatHit(npc.getCombatBuilder(),
                    new CombatContainer(npc, near, 1, 2, CombatType.MAGIC, true)).handleAttack();
            new Projectile(npc, near, 1067, 44, 1, 90, 43, 0).sendProjectile();
        }

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;

            @Override
            protected void execute() {
                if (tick == 2) {
                    npc.setChargingAttack(false);
                    stop();
                }
                tick++;

            }

        });
    }
    public static void meleeAttack(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));

        for (final Player near : getPlayers(player)) {
            if (near == null || near.isTeleporting())
                continue;
            if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                continue;
            new HitQueue.CombatHit(npc.getCombatBuilder(),
                    new CombatContainer(npc, near, 1, 2, CombatType.MELEE, true)).handleAttack();
        }

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;
            @Override
            protected void execute() {
                if (tick == 2) {
                    npc.setChargingAttack(false);
                    stop();
                }
                tick++;
            }
        });
    }

    public static void rangedAttack(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));

        for (final Player near : getPlayers(player)) {
            if (near == null || near.isTeleporting())
                continue;
            if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                continue;
            new HitQueue.CombatHit(npc.getCombatBuilder(),
                    new CombatContainer(npc, near, 1, 2, CombatType.RANGED, true)).handleAttack();
            new Projectile(npc, near, 570, 44, 3, 90, 43, 0).sendProjectile();
        }

        TaskManager.submit(new Task(1, npc, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (tick == 2) {
                    npc.setChargingAttack(false);
                    stop();
                }
                tick++;
            }
        });
    }

}
