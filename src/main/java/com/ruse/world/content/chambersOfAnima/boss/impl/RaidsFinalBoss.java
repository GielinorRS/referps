package com.ruse.world.content.chambersOfAnima.boss.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.clip.region.RegionClipping;
import com.ruse.world.content.CustomObjects;
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
import java.util.Arrays;
import java.util.List;

public class RaidsFinalBoss extends RaidBoss {

    public RaidsFinalBoss(ChambersOfAnimaParty party) {
        super(party, 28263, 75000000);
    }

    private List<String> frozenPlayers = new ArrayList<>();
    private List<GameObject> roots = new ArrayList<>();

    private int specialAttacks = 0;


    private int currentPhase = 1;

    @Override
    public void spawn() {
        NPC boss = new NPC(getNpcId(), startPosition().copy().setY(startPosition().getY() + 2).setZ(getParty().getHeight()));
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

                if (Misc.getRandom(7) == 0) {
                    npc.totalAttacks++;
                    randomFire(npc, player);
                } else if (npc.totalAttacks >= 9) {
                    int random = Misc.getRandom(1);
                    if (random == 0) {
                        groupUp(npc, player);
                    } else {
                        RaidsSecondBoss.drip(npc, player);
                    }
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
        npc.forceChat("Feel my wrath, " + player.getUsername() + "!");
        TaskManager.submit(new Task(3, false) {
            @Override
            protected void execute() {
                List<Player> attackedPlayers = new ArrayList<>();
                attackedPlayers.add(player);

                for (Player member : getParty().getPlayers()) {
                    if (member.equals(player))
                        continue;
                    if (member.getPosition().equals(player.getPosition())) {
                        attackedPlayers.add(member);
                    }
                }


                int damage = 1200 / attackedPlayers.size();
                for (Player toAttack : attackedPlayers) {
                    toAttack.dealDamage(new Hit(damage, Hitmask.CRITICAL, CombatIcon.NONE));
                }
                stop();
            }
        });

    }

    public static void randomFire(NPC npc, Player player) {
        npc.setChargingAttack(true);
        npc.performAnimation(new Animation(npc.getDefinition().getAttackAnimation()));
        List<Position> positionList = new ArrayList<>();

        for (int i = 0; i < 20; i++) {
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

    public static Position getRandom() {
        int x = 3202 + Misc.getRandom(15);
        int y = 2906 + Misc.getRandom(14);
        if (RegionClipping.getClipping(x, y, 0) != 0) {
            return getRandom();
        }
        return new Position(x, y);
    }


    @Override
    public void onPlayerDeath(Player player) {
        if (frozenPlayers.contains(player.getUsername()))
            frozenPlayers.remove(player.getUsername());
    }

    @Override
    public void onDeath(NPC npc) {
        for (GameObject gameObject : roots) {
            CustomObjects.deleteGlobalObject(gameObject);
        }
        roots.clear();
        if (npc.getId() == getNpcId()) {
            getParty().proceed();
            getParty().getNpcs().remove(npc);
        }
    }

    @Override
    public Position startPosition() {
        return new Position(3206, 2912, getParty().getHeight());
    }
}
