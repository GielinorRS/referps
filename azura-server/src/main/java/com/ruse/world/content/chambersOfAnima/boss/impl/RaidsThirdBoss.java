package com.ruse.world.content.chambersOfAnima.boss.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaParty;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.chambersOfAnima.boss.RaidBoss;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.BaseAttacks;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;

public class RaidsThirdBoss extends RaidBoss {

    public RaidsThirdBoss(ChambersOfAnimaParty party) {
        super(party, 27527, 25000000);
    }


    @Override
    public void spawn() {
        NPC boss = new NPC(getNpcId(), startPosition().copy().setX(startPosition().getX()+2).setZ(getParty().getHeight()));
        boss.getDefinition().setMulti(true);

        double hp = (boss.getConstitution() + ((getParty().getPlayers().size() - 1) * boss.getConstitution()));
        if (getParty().getDifficulty() == ChambersOfAnimaParty.ChambersDifficulty.HARD)
            hp *= 2.5D;

        boss.setConstitution((long) hp);
        boss.setDefaultConstitution((long) hp);
        World.register(boss);
        boss.setCombatStrategy(strategy());
        getParty().getNpcs().add(boss);
    }

    @Override
    public CombatStrategy strategy() {
        return new CombatStrategy() {
            @Override
            public boolean canAttack(Character entity, Character victim) {
                return true;
            }

            @Override
            public CombatContainer attack(Character entity, Character victim) {
                return null;
            }

            @Override
            public boolean customContainerAttack(Character entity, Character victim) {
                NPC npc = (NPC) entity;
                Player player = (Player) victim;
                if (player.getChambersOfAnimaParty() == null)
                    return false;

                if (npc.totalAttacks == 9) {
                    groupUp(npc, player);
                    npc.totalAttacks = 0;
                } else {
                    npc.totalAttacks++;
                    if (Misc.getRandom(1) == 0)
                        BaseAttacks.rangedAttack(npc, player);
                    else
                        BaseAttacks.magicAttack(npc, player);

                }

                return true;
            }

            @Override
            public int attackDelay(Character entity) {
                return 3;
            }

            @Override
            public int attackDistance(Character entity) {
                return 40;
            }

            @Override
            public CombatType getCombatType() {
                return CombatType.MELEE;
            }
        };
    }


    public void groupUp(NPC npc, Player player) {
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));
        npc.forceChat("Feel my wrath, "+player.getUsername()+"!");
        getParty().sendMessage("Stand under " + player.getUsername() + " to reduce their damage taken.");
        TaskManager.submit(new Task(4, false) {
            @Override
            protected void execute() {
                List<Player> attackedPlayers = new ArrayList<>();
                attackedPlayers.add(player);

                for (Player member : getParty().getPlayers()) {
                    System.out.println("member: " + member.getUsername());
                    if (member.equals(player))
                        continue;

                        if (member.getPosition().equals(player.getPosition())) {
                            attackedPlayers.add(member);
                    }
                }

                System.out.println("player: " + player.getUsername());
                System.out.println("attackedPlayers: " + attackedPlayers.size());

                int damage = 1200/attackedPlayers.size();
                for (Player toAttack : attackedPlayers) {
                    toAttack.dealDamage(new Hit(damage, Hitmask.CRITICAL, CombatIcon.NONE));
                }
                stop();
            }
        });

    }

    @Override
    public void onDeath(NPC npc) {
        if (npc.getId() == getNpcId()) {
            getParty().proceed();
            getParty().getNpcs().remove(npc);
        }
    }

    @Override
    public Position startPosition() {
        return new Position(3222, 2906, getParty().getHeight());
    }
}
