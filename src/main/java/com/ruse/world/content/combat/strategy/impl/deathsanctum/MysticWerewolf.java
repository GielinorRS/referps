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


public class MysticWerewolf implements CombatStrategy {

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


        if (npc.totalAttacks % 4 == 0) {
            if (Misc.getRandom(0) == 0) {
                int random1 = Misc.getRandom(2);
                if (random1 == 0)
                    npc.setTransformationId(9881);
                if (random1 == 1)
                    npc.setTransformationId(9886);
                else
                    npc.setTransformationId(9887);

                npc.getUpdateFlag().flag(Flag.TRANSFORM);
            }
        }

        if (npc.totalAttacks == 4) {
            int random = Misc.getRandom(2);
            if (random == 0)
                drip(npc, player);
            else if (random == 1)
                throwOrbs(npc, player);
            else
                fireWall(npc, player);
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
                    player.sendMessage("@blu@The Mystic Werewolf sent a blast your way.");
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
                if (tick == 5) {
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
                if (npc == null || npc.isDying() || npc.getConstitution() <= 0) {
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
                    player.sendMessage("@blu@The Mystic Werewolf has thrown a following orb towards you.");
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


    private static void fireWall(NPC npc, Player player) {
        List<Position> tiles = new ArrayList<>();
        int xStart = 1753;
        int xEnd = 1776;
        int yStart = 5210;
        int yEnd = 5183;

        for (int y =  yStart; y >= yEnd; y--) {
            for (int x = xStart; x <= xEnd; x++) {
                tiles.add(new Position(x, y, npc.getPosition().getZ()));
            }
        }

        if (player.getSanctumOfDeathParty() != null) {
            player.getSanctumOfDeathParty().sendMessage("The Mystic Werewolf sends a wall of fire!");
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
                    final int hole1 = Misc.exclusiveRandom(5, 17);
                    final int hole2 = hole1 + 1;
                    final int hole3 = Misc.exclusiveRandom(3, 20);
                    int tick = 0;
                    public void execute() {
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