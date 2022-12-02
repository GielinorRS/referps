package com.ruse.world.content.chambersOfAnima.boss.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.clip.region.RegionClipping;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaParty;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.HitQueue;
import com.ruse.world.content.combat.effect.CombatPoisonEffect;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.chambersOfAnima.boss.RaidBoss;
import com.ruse.world.content.combat.strategy.impl.deathsanctum.BaseAttacks;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;

public class RaidsFirstBoss extends RaidBoss {

    public RaidsFirstBoss(ChambersOfAnimaParty party) {
        super(party,27530, 25000000);
    }

    @Override
    public void spawn() {
        NPC boss = new NPC(getNpcId(), startPosition().copy().setX(startPosition().getX()-2).setZ(getParty().getHeight()));
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

                int random = Misc.random(3);
                if (random == 1) {
                    randomFire(npc, player);
                } else {
                    BaseAttacks.rangedAttack(npc, player);
                }
                return true;
            }

            @Override
            public int attackDelay(Character entity) {
                return 2;
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
    public static void randomFire(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));
        List<Position> positionList = new ArrayList<>();

        for (int i = 0 ; i < 20; i ++){
            positionList.add(getRandom().setZ(npc.getPosition().getZ()));
        }
        TaskManager.submit(new Task(1, true) {
            int tick = 0;

            @Override
            protected void execute() {
                if (tick == 0) {
                    positionList.forEach(position -> player.getPacketSender()
                            .sendGraphic(new Graphic(197), position));
                }
                if (tick == 1) {
                    for (Player partyMember : player.getChambersOfAnimaParty().getPlayers()) {
                        if (positionList.contains(partyMember.getPosition())) {
                            partyMember.dealDamage(new Hit(Misc.random(200, 600), Hitmask.DARK_PURPLE, CombatIcon.NONE));
                        }
                    }
                    npc.setChargingAttack(false);
                    stop();
                }
                tick++;
            }
        });
    }

    public static Position getRandom(){
        int x = 3212 + Misc.getRandom(14);
        int y = 2887 + Misc.getRandom(10);
        if (RegionClipping.getClipping(x, y,0) != 0){
            return getRandom();
        }
        return new Position(x, y);
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
        return new Position(3217, 2891, getParty().getHeight());
    }
}
