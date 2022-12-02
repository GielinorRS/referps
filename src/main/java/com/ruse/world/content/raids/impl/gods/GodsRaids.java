package com.ruse.world.content.raids.impl.gods;


import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.raids.RaidDifficulty;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class GodsRaids {


    public static boolean hasRequirements(GodsRaidsParty party) {
        for (Player player : party.getPlayers()) {

            if (((party.getDifficulty().equals(RaidDifficulty.INTERMEDIATE)
                    && player.getEasyIsleGodKC() < party.getDifficulty().getGodsRequirement().getPreviousDifficultyAmount())

                    || (party.getDifficulty().equals(RaidDifficulty.ADVANCED)
                    && player.getMedIsleGodKC() < 200))

                    && !player.getRights().isDeveloperOnly() && player.getRights() != PlayerRights.YOUTUBER) {
                party.sendMessage(player.getUsername() + " does not have the correct kill requirements to do this.");
                party.sendMessage("Every party member must have at least " + party.getDifficulty().getGodsRequirement().getPreviousDifficultyAmount() + " completions of the previous difficulty.");
                return false;
            }

            int total = KillsTracker.getTotalKillsForNpc(9813, player);

            if (total < 10000 && !player.getRights().isDeveloperOnly() && player.getRights() != PlayerRights.YOUTUBER) {
                party.sendMessage(player.getUsername() + " does not have the Blood Odin kill requirement to do this.");
                player.sendMessage("@red@You need 10,000 Blood Odin kills to do the Isle of the Gods!");
                return false;
            }

            if (player.getInventory().getAmount(12855) < party.getDifficulty().getGodsRequirement().getCost()) {
                party.sendMessage(player.getUsername() + " does not have " + Misc.insertCommasToNumber(party.getDifficulty().getGodsRequirement().getCost()) + " Upgrade tokens.");
                player.sendMessage("@red@You need " + Misc.insertCommasToNumber(party.getDifficulty().getGodsRequirement().getCost()) + " Upgrade tokens to do the Isle of the Gods!");
                return false;
            }

        }
        return true;
    }


    public static void removeTokens(GodsRaidsParty party) {
        for (Player player : party.getPlayers()) {
            player.getInventory().delete(12855, party.getDifficulty().getGodsRequirement().getCost());
        }
    }

    public static boolean canEnter(Player player, GodsRaidsParty party) {

        if (!hasRequirements(party))
            return false;

        if (party.hasTradeOpen())
            return false;

        if (player.getLocation() != Location.GODS_LOBBY) {
            player.sendMessage("You are not in the Isle of the Gods lobby.");
            return false;
        }

        return true;
    }


    public static void start(GodsRaidsParty party, Player player) {
        if (party == null)
            return;
        player.getPacketSender().sendInterfaceRemoval();

        if (!canEnter(player, party))
            return;

        if (party.getOwner() != player) {
            player.getPacketSender().sendMessage("Only the party leader can start the Battle.");
            return;
        }

        if (party.getPlayers().size() <= 0) {
            return;
        }
        if (party.isEnteredRaid()) {
            return;
        }

        removeTokens(party);

        party.setAttack(0);
        party.getDeadPlayers().clear();
        party.setEnteredRaid(true);
        party.setDeathCount(0);
        party.setNpcKills(0);
        party.setFinishedRaid(false);
        party.sendMessage("@red@The God Battle will start shortly");
        party.setCurrentPhase(1);
        party.setSaradominAttackable(Misc.getRandom(1) == 0 ? true : false);
        party.setSaradominHP(0);
        party.setZamorakHP(0);
        party.setSaradominMinionsSpawned(false);
        party.setZamorakMinionsSpawned(false);

        System.out.println(party.getPlayers().size());

        final int height = 3 + (player.getIndex() * 4);
        party.setHeight(height);

        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, height));

        for (Player member : party.getPlayers()) {
            member.setLastDifficulty(party.getDifficulty());
            member.getPacketSender().sendInterfaceRemoval();
            member.setRegionInstance(null);
            member.getMovementQueue().reset();
            member.getClickDelay().reset();

            member.moveTo(new Position(2593, 4443, height));

            PrayerHandler.deactivateAll(member);
            CurseHandler.deactivateAll(member);
            member.getSkillManager().stopSkilling();
            member.getPacketSender().sendClientRightClickRemoval();
            member.getPacketSender().sendCameraNeutrality();
            member.setInsideGodsRaids(true);
            member.setEnteredGodsRaids(true);
            member.setGodsRaidsParty(party);

            if (!member.getGodsRaidsParty().getPlayersInRaid().contains(member))
                member.getGodsRaidsParty().getPlayersInRaid().add(member);

            if (member.getGodsRaidsParty().getPlayersInRaid().size() >= member
                    .getGodsRaidsParty().getPlayers().size()) {

                member.getGodsRaidsParty().startRaid();

            }

            member.getPacketSender().sendInteractionOption("null", 2, true);

            member.getPacketSender().sendWalkableInterface(144900, true);
            member.sendMessage("GODSHP##" + 100 + "##" + 100);

        }

        party.getTimer().reset();
    }

    public static void portalInteraction(Player player, GameObject gameObject) {
        if (player.getLocation() == Location.GODS_LOBBY)
            GodsInterfaces.openStartScreen(player);
    }

    public static void startTask(GodsRaidsParty party) {
        party.removeCagedPlayers();
        TaskManager.submit(new Task(1, false) {

            @Override
            public void execute() {
                spawnNpc(party);

                TaskManager.submit(new Task(1, false) {
                    int tick = 0;

                    @Override
                    protected void execute() {
                        if ((party.getOwner().getLocation() != Location.ISLE_GODS)
                                || party.getPlayersInRaid().size() <= 0) {
                            party.sendMessage("@red@Your party has failed to defeat the Gods.");
                            destroyInstance(party);
                            stop();
                            return;
                        }

                        if ((party.getPlayers().size() <= 0)) {
                            destroyInstance(party);
                            stop();
                            return;
                        }

                        if ((party.getZamorak() == null ||
                                party.getZamorak().getConstitution() <= 0 ||
                                party.getZamorak().isDying())
                                &&
                                (party.getSaradomin() == null ||
                                        party.getSaradomin().getConstitution() <= 0 ||
                                        party.getSaradomin().isDying())) {
                            finishRaid(party);
                            stop();
                            return;
                        }

                        GodCombat.sequence(party);

                        tick++;

                        if (tick % 10 == 0)
                            GodCombat.doAttack(party);
                    }

                });
                stop();
            }
        });
    }

    public static void spawnNpc(GodsRaidsParty party) {

        party.setSaradomin(new NPC(10000, new Position(2584, 4442, party.getHeight())));
        party.setZamorak(new NPC(10001, new Position(2599, 4442, party.getHeight())));

        int hp = (int) ((double) party.getSaradomin().getConstitution() * (party.getDifficulty().ordinal() + 1));
        if (party.getPlayers().size() > 1)
            hp  += ((party.getPlayers().size() - 1) * .65D) * hp;

        party.getSaradomin().setConstitution(hp);
        party.getZamorak().setConstitution(hp);
        party.getSaradomin().setDefaultConstitution(hp);
        party.getZamorak().setDefaultConstitution(hp);

        World.register(party.getSaradomin());
        World.register(party.getZamorak());

        party.getSaradomin().setPositionToFace(party.getZamorak().getPosition());
        party.getZamorak().setPositionToFace(party.getSaradomin().getPosition());
    }

    public static void handleDeath(GodsRaidsParty party, Player player) {
        player.getPacketSender().sendWalkableInterface(144900, false);
        party.getPlayersInRaid().remove(player);
        player.moveTo(GodsRaidsData.LOBBY_POSITION);
        party.leave(player);

        player.sendMessage("@red@Your Isle of the Gods Drop rate has been halved because you died.");
        player.setIsleDropRate( player.getIsleDropRate() / 2);

        if (!party.isFinishedRaid() && party.getPlayersInRaid().size() <= 0) {
            destroyInstance(party);
        }
    }


    public static Player randomPlayer(GodsRaidsParty party) {
        return party.getPlayersInRaid().get(Misc.getRandom(party.getPlayersInRaid().size() - 1));
    }

    public static void finishRaid(GodsRaidsParty party) {
        TaskManager.submit(new Task(1, false) {

            @Override
            public void execute() {
                party.removeCagedPlayers();
                destroyInstance(party);

                party.setEnteredRaid(false);

                party.setFinishedRaid(true);
                party.setDeathCount(0);
                party.setNpcKills(0);
                party.setCurrentPhase(1);
                party.getPlayersInRaid().clear();
                party.incrStreak(1);

                long timerElapsed = party.getTimer().elapsed();
                String timeString = Misc.formatTime(timerElapsed);

                String prefix = "Easy";
                 if (party.getDifficulty() == RaidDifficulty.INTERMEDIATE) {
                     prefix = "Medium";
                } else if (party.getDifficulty() == RaidDifficulty.ADVANCED) {
                     prefix = "Hard";
                }
                party.sendMessage("Isle of the Gods ("+prefix+") completion time: @red@" + timeString);

                for (Player member : party.getPlayers()) {

                    if (party.getDifficulty() == RaidDifficulty.EASY) {
                        if (member.getIsleDropRate() + 0.25D >= 100D)
                            member.setIsleDropRate( 100D);
                        else
                            member.setIsleDropRate(member.getIsleDropRate() + 0.25D);

                        if (member.getIsleEasyTimer() <= 0
                                || timerElapsed < member.getIsleEasyTimer()) {
                            member.setIsleEasyTimer(timerElapsed);
                        }
                        member.sendMessage("Personal Best ("+prefix+"): " + Misc.formatTime(member.getIsleEasyTimer()));
                    } else if (party.getDifficulty() == RaidDifficulty.INTERMEDIATE) {
                        if (member.getIsleDropRate() + 0.5D >= 100D)
                            member.setIsleDropRate( 100D);
                        else
                            member.setIsleDropRate(member.getIsleDropRate() + 0.5D);

                        if (member.getIsleMedTimer() <= 0
                                || timerElapsed < member.getIsleMedTimer()) {
                            member.setIsleMedTimer(timerElapsed);
                        }
                        member.sendMessage("Personal Best ("+prefix+"): " + Misc.formatTime(member.getIsleMedTimer()));
                    } else if (party.getDifficulty() == RaidDifficulty.ADVANCED) {

                        if (member.getIsleDropRate() + 1D >= 100D)
                            member.setIsleDropRate( 100D);
                        else
                            member.setIsleDropRate(member.getIsleDropRate() + 1D);

                        if (member.getIsleHardTimer() <= 0
                                || timerElapsed < member.getIsleHardTimer()) {
                            member.setIsleHardTimer(timerElapsed);
                        }
                        member.sendMessage("Personal Best ("+prefix+"): " + Misc.formatTime(member.getIsleHardTimer()));
                    }
                    member.getDailyTaskManager().submitProgressToIdentifier(35, 1);


                    member.getPacketSender().sendInterfaceRemoval();
                    KillsTracker.submitById(member, 10000, true, true);
                    KillsTracker.submitById(member, 10000, false, true);
                    KillsTracker.submitById(member, 10001, true, true);
                    KillsTracker.submitById(member, 10001, false, true);


                    GodsLoot.handleLoot(member, party.getDifficulty());

                    int random = Misc.inclusiveRandom(1,100);
                    if (member.getAmountDonated() >= 25000 && random <= 20
                            || member.getAmountDonated() >= 10000 && random <= 15
                            || member.getAmountDonated() >= 5000 && random <= 10
                            || member.getAmountDonated() >= 1000 && random <= 5) {
                        member.sendMessage("You received a second drop as a chance from your donator rank.");
                        GodsLoot.handleLoot(member, party.getDifficulty());
                    }

                    if (party.getDifficulty() == RaidDifficulty.EASY) {
                        member.setEasyIsleGodKC(member.getEasyIsleGodKC() + 1);
                        member.sendMessage("@blu@Isle of the Gods (Easy) KC: " + member.getEasyIsleGodKC());
                    } else if (party.getDifficulty() == RaidDifficulty.INTERMEDIATE) {
                        member.setMedIsleGodKC(member.getMedIsleGodKC() + 1);
                        member.sendMessage("@blu@Isle of the Gods (Medium) KC: " + member.getMedIsleGodKC());
                    } else if (party.getDifficulty() == RaidDifficulty.ADVANCED) {
                        member.setHardIsleGodKC(member.getHardIsleGodKC() + 1);
                        member.sendMessage("@blu@Isle of the Gods (Hard) KC: " + member.getHardIsleGodKC());
                    }
                }

                party.moveTo(GodsRaidsData.LOBBY_POSITION);
                stop();
            }
        });
    }


    public static void destroyInstance(GodsRaidsParty party) {

        for (Player member : party.getPlayers()) {
            if (member == null)
                continue;

            member.getPacketSender().sendWalkableInterface(144900, false);
            member.setEnteredGodsRaids(false);
            // member.getRegionInstance().remove(member);
            // member.getRegionInstance().destruct();
            member.setRegionInstance(null);
        }
        party.moveTo(GodsRaidsData.LOBBY_POSITION);
        party.setEnteredRaid(false);
        party.getPlayersInRaid().clear();
        party.resetVariables(false);
        party.setStreak(0);
        party.setFinishedRaid(false);

        if (party.getSaradomin() != null)
            World.deregister(party.getSaradomin());

        if (party.getZamorak() != null)
            World.deregister(party.getZamorak());

        World.getNpcs()
                .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, party.getHeight()));
        party.setSaradomin(null);
        party.setZamorak(null);

        for (GroundItem groundItem : party.getGroundItems()) {
            GroundItemManager.remove(groundItem, true);
        }
        party.getGroundItems().clear();

    }

}
