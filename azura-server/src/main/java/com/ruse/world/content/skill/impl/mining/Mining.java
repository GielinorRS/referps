package com.ruse.world.content.skill.impl.mining;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.model.container.impl.Equipment;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.Sounds.Sound;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.randomevents.ShootingStar;
import com.ruse.world.content.skill.impl.mining.MiningData.Ores;
import com.ruse.world.content.skill.impl.mining.MiningData.Pickaxe;
import com.ruse.world.content.skill.impl.smithing.Smelting;
import com.ruse.world.content.skill.impl.smithing.SmithingData;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.CelestialZone;

public class Mining {

    public static void startMining(final Player player, final GameObject oreObject) {
        player.getSkillManager().stopSkilling();
        player.getPacketSender().sendInterfaceRemoval();
        if (!Locations.goodDistance(player.getPosition().copy(), oreObject.getPosition(), 1)
                && oreObject.getId() != 24444 && oreObject.getId() != 24445 && oreObject.getId() != 38660)
            return;
        if (player.busy() || player.getCombatBuilder().isBeingAttacked() || player.getCombatBuilder().isAttacking()) {
            player.getPacketSender().sendMessage("You cannot do that right now.");
            return;
        }
        if (player.getInventory().getFreeSlots() == 0) {
            player.getPacketSender().sendMessage("You do not have any free inventory space left.");
            return;
        }
        player.setInteractingObject(oreObject);
        player.setPositionToFace(oreObject.getPosition());
        final Ores o = MiningData.forRock(oreObject.getId());
        final boolean giveGem = o != Ores.Rune_essence && o != Ores.Pure_essence;
        int reqCycle = o == Ores.Runite ? 6 + Misc.getRandom(2) : Misc.getRandom(o.getTicks() - 1);



        if (o != null) {
            if (o == Ores.CELESTIAL) {
                if (!CelestialZone.gameActive) {
                    player.getPacketSender().sendMessage("The Celestial Zone is not open.");
                    return;
                }
            }
            final int pickaxe = MiningData.getPickaxe(player);

            if (pickaxe == 23887 && reqCycle >= 1)
                reqCycle -= 1;


            final int miningLevel = player.getSkillManager().getCurrentLevel(Skill.MINING);
            if (pickaxe > 0) {
                if (miningLevel >= o.getLevelReq()) {
                    final Pickaxe p = MiningData.forPick(pickaxe);
                    if (miningLevel >= p.getReq()) {
                        if (MiningData.isHoldingPickaxe(player)) {
                            player.performAnimation(new Animation(12003));
                        } else {
                            player.performAnimation(new Animation(p.getAnim()));
                        }
                        final int delay = o.getTicks() - MiningData.getReducedTimer(player, p);


                        int finalReqCycle = reqCycle;
                        player.setCurrentTask(new Task(delay >= 2 ? delay : 1, player, false) {
                            int cycle = 0;

                            @Override
                            public void execute() {
                                if (player.getInteractingObject() == null
                                        || player.getInteractingObject().getId() != oreObject.getId()) {
                                    player.getSkillManager().stopSkilling();
                                    player.performAnimation(new Animation(65535));
                                    stop();
                                    return;
                                }
                                if (player.getInventory().getFreeSlots() == 0) {
                                    player.performAnimation(new Animation(65535));
                                    stop();
                                    player.getPacketSender()
                                            .sendMessage("You do not have any free inventory space left.");
                                    return;
                                }
                                if (cycle != finalReqCycle) {
                                    cycle++;
                                    if (MiningData.isHoldingPickaxe(player)) {
                                        player.performAnimation(new Animation(12003));
                                    } else {
                                        player.performAnimation(new Animation(p.getAnim()));
                                    }
                                }
                                if (cycle == finalReqCycle) {
                                    Achievements.doProgress(player, Achievements.Achievement.MINING);

                                    if (o == Ores.Iron) {
                                        AchievementsOLD.finishAchievement(player, AchievementDataOLD.MINE_SOME_IRON);
                                    } else if (o == Ores.Runite) {
                                        AchievementsOLD.doProgress(player, AchievementDataOLD.MINE_25_RUNITE_ORES);
                                        AchievementsOLD.doProgress(player, AchievementDataOLD.MINE_2000_RUNITE_ORES);
                                        Achievements.doProgress(player, Achievements.Achievement.DAILY_MINING);

                                    }

                                    if (o == Ores.WAVES_MINIGAME){
                                        if (player.getWaveMinigame().getTask() == WaveMinigame.Tasks.MINE_ORE){
                                            player.getWaveMinigame().progress();
                                        }
                                    }else {
                                        if (o.getItemId() != -1) {
                                            if (o == Ores.Coal && player.getSkillManager().skillCape(Skill.MINING)
                                                    && Misc.getRandom(3) == 1) {
                                                player.getInventory().add(o.getItemId(), 2);
                                                player.getPacketSender()
                                                        .sendMessage("Your cape allows you to mine an additional coal.");
                                            } else {
                                                player.getInventory().add(o.getItemId(), 1);
                                            }
                                        }
                                    }
                                    if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                                            && player.getSummoning().getFamiliar().getSummonNpc().getId() == 4444 && o == Ores.AFKMINE) {
                                        //AfkOreSystem.minedCount++;

                                        player.getInventory().add(17634, 1);

                                    }
                                    if (player.getEquipment().get(Equipment.HEAD_SLOT).getId() == 14068
                                            && player.getEquipment().get(Equipment.AMULET_SLOT).getId() == 14073
                                            && player.getEquipment().get(Equipment.BODY_SLOT).getId() == 14069

                                            && player.getEquipment().get(Equipment.LEG_SLOT).getId() == 14070
                                            && player.getEquipment().get(Equipment.FEET_SLOT).getId() == 14072
                                            && player.getEquipment().get(Equipment.HANDS_SLOT).getId() == 14071 && o == Ores.AFKMINE) {

                                        player.getInventory().add(17634, 1);

                                    }
                                    player.getSkillManager().addExperience(Skill.MINING, (int) (o.getXpAmount()));
                                    if (o == Ores.CRASHED_STAR) {
                                        player.getPacketSender().sendMessage("You mine the crashed star..");
                                    } else {
                                        player.getPacketSender().sendMessage("You mine some ore.");
                                    }
                                    //public static final int totalafkCount = 100000;
                                    if (o == Ores.AFKMINE) {
                                        //AfkOreSystem.minedCount++;

                                        player.sendMessage("You chip a piece of the rock. " + AfkStallSystem.getLeft() + " more for Earthquake to spawn.");
                                        //	player.sendMessage("tYou chip a piece of earthquake. "      + (AfkOreSystem.totalCount -  AfkOreSystem.minedCount) + "chips left") ;
                                        //player.sendMessage("You chip a piece of earthquake. Total count is currently at: @red@" + AfkOreSystem.minedCount + "/100000");
                                        AfkStallSystem.spawnBoss();
                                    }

                                    /** ADZE EFFECT **/
                                    if (pickaxe == Pickaxe.Adze.getId()) {
                                        if (Misc.getRandom(100) >= 75) {
                                            switch (o) {
                                                case Adamantite:
                                                    handleAdze(player, oreObject, 2361);
                                                    break;
                                                case Gold:
                                                    handleAdze(player, oreObject, 2357);
                                                    break;
                                                case Iron:
                                                    handleAdze(player, oreObject, 2351);
                                                    break;
                                                case Mithril:
                                                    handleAdze(player, oreObject, 2359);
                                                    break;
                                                case Runite:
                                                    handleAdze(player, oreObject, 2363);
                                                    break;
                                                case Silver:
                                                    handleAdze(player, oreObject, 2355);
                                                    break;
                                                case Tin:
                                                case Copper:
                                                    handleAdze(player, oreObject, 2349);
                                                    break;
                                            }
                                        }
                                    }

                                    Sounds.sendSound(player, Sound.MINE_ITEM);

                                    cycle = 0;
                                    this.stop();
                                    if (o.getRespawn() > 0) {
                                        player.performAnimation(new Animation(65535));
                                        oreRespawn(player, oreObject, o);
                                    } else {
                                        if (oreObject.getId() == 38660) {
                                            if (ShootingStar.CRASHED_STAR == null
                                                    || ShootingStar.CRASHED_STAR.getStarObject()
                                                    .getPickAmount() >= ShootingStar.MAXIMUM_MINING_AMOUNT) {
                                                player.getPacketSender().sendClientRightClickRemoval();
                                                player.getSkillManager().stopSkilling();
                                                return;
                                            } else {
                                                ShootingStar.CRASHED_STAR.getStarObject().incrementPickAmount();
                                            }
                                        } else {
                                            player.performAnimation(new Animation(65535));
                                        }
                                        startMining(player, oreObject);
                                    }
                                }
                            }
                        });
                        TaskManager.submit(player.getCurrentTask());
                    } else {
                        player.getPacketSender().sendMessage(
                                "You need a Mining level of at least " + p.getReq() + " to use this pickaxe.");
                    }
                } else {
                    player.getPacketSender().sendMessage(
                            "You need a Mining level of at least " + o.getLevelReq() + " to mine this rock.");
                }
            } else {
                player.getPacketSender().sendMessage("You don't have a pickaxe to mine this rock with.");
            }
        }
    }

    public static void handleAdze(final Player player, final GameObject oreObject, final int barId) {
        if (SmithingData.hasOres(player, barId)) {
            Smelting.handleBarCreation(barId, player);
            player.getPacketSender()
                    .sendMessage("The heat from your Inferno adze immediately ignites the ore and smelts it.");
            player.setInteractingObject(oreObject);
            player.setPositionToFace(oreObject.getPosition());
            oreObject.performGraphic(new Graphic(453));
            player.setInteractingObject(oreObject);
            player.setPositionToFace(oreObject.getPosition());
        }
    }

    public static void oreRespawn(final Player player, final GameObject oldOre, Ores o) {
        if (oldOre == null || oldOre.getPickAmount() >= 1)
            return;
        oldOre.setPickAmount(1);
        for (Player players : player.getLocalPlayers()) {
            if (players == null)
                continue;
            if (players.getInteractingObject() != null && players.getInteractingObject().getPosition()
                    .equals(player.getInteractingObject().getPosition().copy())) {
                players.getPacketSender().sendClientRightClickRemoval();
                players.getSkillManager().stopSkilling();
            }
        }
        player.getPacketSender().sendClientRightClickRemoval();
        player.getSkillManager().stopSkilling();
        CustomObjects.globalObjectRespawnTask(new GameObject(452, oldOre.getPosition().copy(), 10, 0), oldOre,
                o.getRespawn());
    }
}
