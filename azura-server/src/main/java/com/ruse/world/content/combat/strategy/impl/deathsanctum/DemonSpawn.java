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
import java.util.List;
import java.util.stream.Collectors;


public class DemonSpawn implements CombatStrategy {

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
        if (!npc.isRegistered()) {
            return true;
        }
        if (npc.getConstitution() <= 0) {
            return true;
        }
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
            fireWall(npc, player);
            npc.totalAttacks = 0;
        } else {
            npc.totalAttacks++;
                BaseAttacks.magicAttack(npc, player);
        }

        return true;
    }



    private static void fireWall(NPC npc, Player player) {
        List<Position> tiles = new ArrayList<>();
        int xStart = 1762;
        int xEnd = 1779;
        int yStart = 5178;
        int yEnd = 5154;

        for (int y =  yStart; y >= yEnd; y--) {
            for (int x = xStart; x <= xEnd; x++) {
                tiles.add(new Position(x, y, npc.getPosition().getZ()));
            }
        }
        if (player.getSanctumOfDeathParty() != null) {
            player.getSanctumOfDeathParty().sendMessage("The Demon Spawn sends a wall of fire!");
        }
        npc.forceChat("You will be burned!");

        npc.performAnimation(new Animation(811));

        npc.setChargingAttack(true);
        TaskManager.submit(new Task(2, npc, false) {

            @Override
            public void execute() {
                getWavesNorthSouth(npc, player);
            }

            public void getWavesNorthSouth(NPC npc, Player player) {
                if (tiles.size() <= 0) {
                    npc.setChargingAttack(false);
                    this.stop();
                    return;
                }

                TaskManager.submit(new Task(1, npc, false) {
                    final int[] y = {yStart};
                    final int hole1 = Misc.exclusiveRandom(3, 13);
                    final int hole2 = hole1 + 1;
                    final int hole3 = Misc.exclusiveRandom(1, 15);
                    int tick = 0;
                    public void execute() {
                        if (npc.getConstitution() <= 0) {
                            stop();
                        } else {
                            final List<Position> currentYTiles = tiles.stream()
                                    .filter(position -> position.getY() == y[0])
                                    .collect(Collectors.toList());
                            removeExitPoints(currentYTiles, hole1, hole2, hole3);
                            for (Position p : currentYTiles) {
                                player.getPacketSender().sendGlobalGraphic(new Graphic(78), new Position(p.getX(), p.getY(), p.getZ()));
                                for (Player players : npc.getClosePlayers(32)) {
                                    if (players.getPosition().getX() == p.getX() &&
                                            players.getPosition().getY() == p.getY() &&
                                            players.getPosition().getZ() == npc.getPosition().getZ()) {
                                        int damage = Misc.exclusiveRandom(990, 1500);
                                        players.dealDamage(new Hit(damage, Hitmask.RED, CombatIcon.NONE));
                                        players.sendMessage("@blu@You were burnt by the flames!");
                                        players.performGraphic(new Graphic(1640));
                                    }
                                }
                                if (p.getX() == xEnd) {
                                    y[0]--;
                                }

                                if (tick == 5) {
                                    npc.setChargingAttack(false);
                                }
                                tick++;
                                if (p.getX() == xEnd && y[0] == yEnd) {
                                    tiles.clear();
                                    this.stop();
                                }
                            }
                        }
                    }
                });
                this.stop();
            }
        });
    }


    public static void removeExitPoints(List<Position> tiles, int hole1, int hole2, int hole3) {
        if (tiles.isEmpty()) {
            return;
        }
        if (hole1 < tiles.size()) {
            tiles.remove(hole1);
        }
        if (hole2 < tiles.size()) {
            tiles.remove(hole2);
        }
        if (hole3 < tiles.size()) {
            tiles.remove(hole3);
        }
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