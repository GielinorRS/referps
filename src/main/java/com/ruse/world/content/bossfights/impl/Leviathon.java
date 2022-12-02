package com.ruse.world.content.bossfights.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.bossfights.BossFight;
import com.ruse.world.content.combat.CombatContainer;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.strategy.CombatStrategy;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Leviathon extends BossFight {

    private static final long HEALTH = 200_000_000;

    @Getter
    @Setter
    private boolean spawnedMinions = false;

    @Getter
    @Setter
    private int killedMinions = 0;

    ArrayList<Position> stalgamitePositions = new ArrayList<>();

    public Leviathon(Player player) {
        super(player);
    }

    @Override
    public int npcId() {
        return 28060;
    }

    @Override
    public void begin() {
        setGlobalHeight(getGlobalHeight() + 4);
        setMyHeight(getGlobalHeight());
        setCurrentStage(1);
        setCurrentNpc(new NPC(npcId(), new Position(1817, 4252, getMyHeight())));
        getCurrentNpc().setConstitution(HEALTH);
        getCurrentNpc().setDefaultConstitution(HEALTH);
        World.register(getCurrentNpc());
        getNpcList().add(getCurrentNpc());
        TeleportHandler.teleportPlayer(getPlayer(), new Position(1820, 4248, getMyHeight()), TeleportType.NORMAL);
        getCurrentNpc().setCombatStrategy(bossStrategy());
    }

    @Override
    public void onDeath(NPC npc) {
        if (!getNpcList().contains(npc))
            return;
        switch (npc.getId()) {
            case 10029:
                killedMinions++;
                getNpcList().remove(npc);
                break;
            case 28060:
                //Loot Here
                endFight();
                break;
        }
    }

    @Override
    public void onPlayerDeath() {
        endFight();
    }

    @Override
    public void endFight() {
        for (NPC n : getNpcList()) {
            if (n == null)
                continue;
            if (n.isRegistered())
                World.deregister(n);
        }
        getNpcList().clear();
        setSpawnedMinions(false);
        setCurrentNpc(null);
        getPlayer().setBossFight(null);
    }

    @Override
    public CombatStrategy bossStrategy() {
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
                NPC boss = (NPC) entity;
                Player player = (Player) victim;
                if (player.getBossFight() == null || !(player.getBossFight() instanceof Leviathon))
                    return false;

                int currentStage = getCurrentStage();
                int healthPercentage = (int) (((double) boss.getConstitution() / (double) boss.getDefaultConstitution()) * 100D);
                if (healthPercentage <= 75 && currentStage == 1) {
                    player.getBossFight().setCurrentStage(2);
                    currentStage = 2;
                } else if (healthPercentage <= 50 && currentStage == 2) {
                    player.getBossFight().setCurrentStage(3);
                    currentStage = 3;
                }

                int random = Misc.random(0, 4);
                switch (currentStage) {
                    case 1:
                        if (random == 0) {
                            prayersAttack(player, boss);
                        } else {
                            flamesAttack(player, boss);

                        }
                        break;
                    case 2:
                        if (random == 0) {
                            begoneDragonslayer(player, boss);
                        } else {
                            flamesAttack(player, boss);
                        }
                        break;
                    case 3:
                        if (!isSpawnedMinions()) {
                            spawnMinions();
                            setSpawnedMinions(true);
                        } else {
                            if (random == 0) {
                                prayersAttack(player, boss);
                            } else if (random == 1) {
                                begoneDragonslayer(player, boss);
                            } else {
                                flamesAttack(player, boss);

                            }
                        }
                        break;
                }
                return true;
            }

            @Override
            public int attackDelay(Character entity) {
                return 4;
            }

            @Override
            public int attackDistance(Character entity) {
                return 40;
            }

            @Override
            public CombatType getCombatType() {
                return CombatType.MAGIC;
            }

            private void flamesAttack(Player player, NPC boss) {
                boss.performAnimation(new Animation(27960));
                boss.getCombatBuilder()
                        .setContainer(new CombatContainer(boss, player, 1, 3, CombatType.MAGIC, true));

                TaskManager.submit(new Task(1) {
                    int ticks = 0;

                    @Override
                    protected void execute() {
                        if (ticks == 1) {
                            new Projectile(boss, player, 1155, 44, 1, 40, 40, 500).sendProjectile();
                            stop();
                        }
                        ticks++;
                    }

                    @Override
                    public void stop() {
                        ticks = 0;
                        setDelay(0);
                        setEventRunning(false);
                    }
                });
            }

            private void prayersAttack(Player player, NPC boss) {
                boss.performAnimation(new Animation(27960));

                TaskManager.submit(new Task(1) {
                    int ticks = 0;

                    @Override
                    protected void execute() {
                        if (ticks == 1) {
                            new Projectile(boss, player, 393, 44, 1, 40, 40, 500).sendProjectile();
                        }
                        if (ticks == 3) {
                            player.sendMessage("@red@Your prayers have been disabled!");
                            player.dealDamage(new Hit(Misc.random(400, 700), Hitmask.CRITICAL, CombatIcon.NONE));
                            CurseHandler.deactivateAll(player);
                            PrayerHandler.deactivateAll(player);
                            stop();
                        }
                        ticks++;
                    }

                    @Override
                    public void stop() {
                        ticks = 0;
                        setDelay(0);
                        setEventRunning(false);
                    }
                });
            }


            private void begoneDragonslayer(Player player, NPC boss) {
                boss.forceChat("Be gone, dragon slayer!");
                stalgamitePositions.clear();
                int playerX = player.getPosition().getX();
                int playerY = player.getPosition().getY();
                stalgamitePositions.add(new Position(playerX, playerY, getMyHeight()));
                stalgamitePositions.add(new Position(playerX + 1, playerY, getMyHeight()));
                stalgamitePositions.add(new Position(playerX - 1, playerY, getMyHeight()));
                stalgamitePositions.add(new Position(playerX, playerY + 1, getMyHeight()));
                stalgamitePositions.add(new Position(playerX, playerY - 1, getMyHeight()));
                stalgamitePositions.add(new Position(playerX + 1, playerY + 1, getMyHeight()));
                stalgamitePositions.add(new Position(playerX - 1, playerY + 1, getMyHeight()));
                stalgamitePositions.add(new Position(playerX + 1, playerY - 1, getMyHeight()));
                stalgamitePositions.add(new Position(playerX - 1, playerY - 1, getMyHeight()));

                TaskManager.submit(new Task(1) {
                    int ticks = 0;

                    @Override
                    protected void execute() {
                        if (ticks == 5) {
                            stalgamitePositions.forEach(position -> player.getPacketSender()
                                    .sendGraphic(new Graphic(197), position));
                            if (stalgamitePositions.contains(player.getPosition())) {
                                player.dealDamage(new Hit(1200, Hitmask.DARK_CRIT, CombatIcon.NONE));
                            }
                            stop();
                        }
                        ticks++;
                    }

                    @Override
                    public void stop() {
                        ticks = 0;
                        setDelay(0);
                        stalgamitePositions.clear();
                        setEventRunning(false);
                    }
                });


            }

            private void spawnMinions() {
                Position[] pos = new Position[]{new Position(1812, 4250), new Position(1826, 4250),
                        new Position(1812, 4258), new Position(1826, 4258)};
                for (int i = 0; i < 4; i++) {
                    NPC dragon = new NPC(10029, pos[i].setZ(getMyHeight()));
                    World.register(dragon);
                    getNpcList().add(dragon);
                    dragon.getCombatBuilder().attack(getPlayer());
                }
            }
        };
    }
}