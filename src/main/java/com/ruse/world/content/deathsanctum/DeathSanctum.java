package com.ruse.world.content.deathsanctum;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.GameObject;
import com.ruse.model.Locations;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Cases;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;

public class DeathSanctum {

    public static void start(DeathSanctumParty party, DeathSanctumParty.SanctumDifficulty difficulty) {

        Player p = party.getOwner();
        p.getPacketSender().sendInterfaceRemoval();

        if (party.isHasEnteredRaids()) {
            p.getPacketSender().sendMessage("your party is already in a raids!");
            return;
        }

        if (party.getOwner() != p) {
            p.getPacketSender().sendMessage("Only the party leader can start the fight.");
            return;
        }

        for (Player member : party.getPlayers()) {
            if (member != null) {
                member.getPacketSender().sendInterfaceRemoval();
                if (member.getSummoning().getFamiliar() != null) {
                    member.getPacketSender()
                            .sendMessage("You must dismiss your familiar before being allowed to enter a dungeon.");
                    p.getPacketSender().sendMessage(
                            "" + member.getUsername() + " has to dismiss their familiar before you can enter the dungeon.");
                    return;
                }
            }
        }

        party.setHasEnteredRaids(true);
        final int height = p.getIndex() * 4;
        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.DEATH_SANCTUM, height, null));

        for (Player member : party.getPlayers()) {
            member.getPacketSender().sendInterfaceRemoval();
            member.setRegionInstance(null);
            member.getMovementQueue().reset();
            member.getClickDelay().reset();
            member.moveTo(new Position(1773, 5132, height));
            PrayerHandler.deactivateAll(member);
            CurseHandler.deactivateAll(member);
            TaskManager.submit(new Task(2, false) {

                @Override
                public void execute() {
                    PrayerHandler.deactivateAll(member);
                    CurseHandler.deactivateAll(member);
                    stop();
                }
            });
            member.getSkillManager().stopSkilling();
            member.getPacketSender().sendClientRightClickRemoval();
            member.getPacketSender().sendCameraNeutrality();
            member.setInsideRaids(false);
            member.setSanctumOfDeathParty(party);
            member.setRaidsDamage(0);

            member.setSpecialPercentage(100);
            CombatSpecial.updateBar(member);
        }
        party.setDeathCount(0);
        party.setKills(0);
        party.sendMessage("@red@Welcome to the Sanctum of Death! (" + difficulty.name() + ")");
        party.setCurrentPhase(1);
        party.setHeight(height);
        party.setCompletedCurrent(true);
        party.setWave(1);
        party.setDifficulty(difficulty);
        party.getTimer().reset();

    }

    public static NPC addNpc(DeathSanctumParty party, NPC npc) {
        npc.getPosition().setZ(party.getHeight());

        double hp = (npc.getConstitution() + ((party.getPlayers().size() - 1) * npc.getConstitution()));
        if (party.getDifficulty() == DeathSanctumParty.SanctumDifficulty.HARD)
            hp *= 2.5D;

        npc.setDefaultConstitution((int) hp);
        npc.setConstitution((int) hp);
        return npc;
    }

    public static void startWave(DeathSanctumParty party) {
        ArrayList<NPC> npcs = new ArrayList<>();
        NPC npc = (NPC) DeathSanctumData.npcs[party.getWave() - 1].copy();

        npcs.add(addNpc(party, new NPC(npc.getId(), npc.getPosition().copy())));

        party.setCompletedCurrent(false);
        party.setWave(party.getWave() + 1);

        TaskManager.submit(new Task(2, false) {
            @Override
            public void execute() {
                startTask(party, npcs);
                stop();
            }
        });
    }

    public static int getBarrier(int y) {
        int index = 1;
        for (int yCoord : DeathSanctumData.portalYPos) {
            if (y == yCoord)
                return index;
            index++;
        }
        return index;
    }

    public static void handlePortal(DeathSanctumParty party, Player player, GameObject gameObject) {
        int wave = party.getWave() - 1;
        if (wave != 4 && gameObject.getPosition().getY() == DeathSanctumData.portalYPos[wave] &&
                party.isCompletedCurrent()) {
            passPortal(player, gameObject);
            startWave(party);
        } else if (gameObject.getPosition().getY() < DeathSanctumData.portalYPos[wave]) {
            passPortal(player, gameObject);
        } else {
            player.sendMessage("You must complete the current room to progress.");
        }
    }

    public static void passPortal(Player player, GameObject gameObject) {
        int barrier = getBarrier(gameObject.getPosition().getY());
        if (barrier == 1) {
            if (player.getPosition().getY() == gameObject.getPosition().getY() ||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 1) {
                if (player.getPosition().getX() > gameObject.getPosition().getX()) {
                    player.moveTo(new Position(gameObject.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(gameObject.getPosition().getX() + 1, player.getPosition().getY(), player.getPosition().getZ()));
                }
            }
        } else if (barrier == 3) {
            if (player.getPosition().getY() == gameObject.getPosition().getY() ||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 1) {
                if (player.getPosition().getX() >= gameObject.getPosition().getX()) {
                    player.moveTo(new Position(gameObject.getPosition().getX() - 1, player.getPosition().getY(), player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(gameObject.getPosition().getX(), player.getPosition().getY(), player.getPosition().getZ()));
                }
            }
        } else {
            if (player.getPosition().getX() == gameObject.getPosition().getX() ||
                    player.getPosition().getX() == gameObject.getPosition().getX() + 1) {
                if (player.getPosition().getY() >= gameObject.getPosition().getY()) {
                    player.moveTo(new Position(player.getPosition().getX(), gameObject.getPosition().getY() - 1, player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(player.getPosition().getX(), gameObject.getPosition().getY() + 1, player.getPosition().getZ()));
                }
            }
        }
    }

    public static void startTask(DeathSanctumParty party, ArrayList<NPC> npcs) {
        TaskManager.submit(new Task(1, false) {
            int tick = 0;

            @Override
            protected void execute() {
                if ((party.getOwner().getLocation() != Locations.Location.DEATH_SANCTUM)
                        || party.getPlayers().size() <= 0) {
                    party.sendMessage("@red@Your party has failed to defeat the Sanctum of Death");
                    destroyInstance(party);
                    stop();
                }

                if (!party.isHasEnteredRaids())
                    stop();

                if (tick > 4) {
                    int count = 0;
                    for (NPC npc : npcs) {
                        if (npc.getConstitution() <= 0)
                            count++;
                    }
                    if (count >= npcs.size()) {
                        party.setCompletedCurrent(true);
                        if (party.getWave() == 5) {
                            finishRaid(party);
                        }
                        stop();
                    }
                }
                if (tick == 4) {
                    for (NPC npc : npcs) {
                        party.sendMessage("@blu@The " + npc.getDefinition().getName() + " has spawned!");
                        spawnNpc(party, npc);
                    }
                }
                tick++;
            }
        });
    }

    public static void spawnNpc(DeathSanctumParty party, NPC npc) {
        World.register(npc);
        Player player = randomPlayer(party);
        npc.getMovementQueue().setFollowCharacter(player);
        npc.getCombatBuilder().attack(player);
    }

    public static void handleDeath(DeathSanctumParty party, Player player) {
        //party.getPlayers().remove(player);
        // party.remove(player, true);
        // player.sendMessage("@red@You died.");
        player.moveTo(new Position(1773, 5132, party.getHeight()));
    }

    public static Player randomPlayer(DeathSanctumParty party) {
        return party.getPlayers().get(Misc.getRandom(party.getPlayers().size() - 1));
    }

    public static void finishRaid(DeathSanctumParty party) {
        party.setHasEnteredRaids(false);

        long totalDamage = 0;

        for (Player member : party.getPlayers()) {
            member.getPacketSender().sendInterfaceRemoval();
            totalDamage += member.getRaidsDamage();
        }


        int totalShards = party.getPlayers().size() == 1 ? 125 : (125 + ((party.getPlayers().size() - 1) * 125) + 1);
        if (party.getDifficulty() == DeathSanctumParty.SanctumDifficulty.HARD) {
            totalShards *= 3;
        }
        long finalTotalDamage = totalDamage;
        int finalTotalShards = totalShards;
        TaskManager.submit(new Task(6, false) {

            @Override
            public void execute() {
                party.sendMessage("@red@Your party has completed the Sanctum of Death");
                party.moveTo(DeathSanctumData.lobbyPosition);

                String prefix = "Hard";
                if (party.getDifficulty() == DeathSanctumParty.SanctumDifficulty.EASY) {
                    prefix = "Easy";
                }

                long timerElapsed = party.getTimer().elapsed();
                String timeString = Misc.formatTime(timerElapsed);
                party.sendMessage("Sanctum of Death (" + prefix + ") completion time: @red@" + timeString);

                for (Player player : party.getPlayers()) {

                    if (party.getDifficulty() == DeathSanctumParty.SanctumDifficulty.EASY) {
                        if (player.getSanctumEasyTimer() <= 0
                                || timerElapsed < player.getSanctumEasyTimer()) {
                            player.setSanctumEasyTimer(timerElapsed);
                            player.sendMessage("Personal Best (" + prefix + "): " + Misc.formatTime(player.getSanctumEasyTimer()));
                        }
                    } else {
                        if (player.getSanctumHardTimer() <= 0
                                || timerElapsed < player.getSanctumHardTimer()) {
                            player.setSanctumHardTimer(timerElapsed);
                        }
                        player.sendMessage("Personal Best (" + prefix + "): " + Misc.formatTime(player.getSanctumHardTimer()));
                    }

                    player.setDeathSanctumKC(player.getDeathSanctumKC() + 1);

                    double shards = ((double) player.getRaidsDamage() / (double) finalTotalDamage) * finalTotalShards;

                    if (ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.RAIDS_LOOT) {
                        shards *= 2;
                    }

                    if (player.getInventory().contains(23753)) {
                        player.getInventory().delete(23753, 1);
                        shards *= 1.1D;
                    }

                    player.getInventory().add(23463, (int) shards);
                    player.sendMessage("@red@You received " + (int) shards + " Sanctum Key Shards for your contribution.");

                    player.getSeasonPass().addExperience(2);
                    Cases.grantCasket(player, 5);
                    player.setRaidsDamage(0);

                    if (timerElapsed / 1000 <= 180)
                        player.getDailyTaskManager().submitProgressToIdentifier(46, 1);

                    player.getDailyTaskManager().submitProgressToIdentifier(24, 1);

                }
                party.setDeathCount(0);
                party.setKills(0);
                party.setCurrentPhase(1);
                stop();
            }
        });
    }

    public static Box getLoot(Box[] loot, int size) {
        Box[] possibleDrops = new Box[loot.length];
        int possibleDropsCount = 0;
        for (Box drop : loot) {
            if (Misc.getRandom(100 * size) <= drop.getRate()) {
                possibleDrops[possibleDropsCount++] = drop;
            }
        }
        if (possibleDropsCount > 0) {
            return possibleDrops[Misc.getRandom((possibleDropsCount - 1))];
        } else {
            return loot[Misc.getRandom((possibleDropsCount - 1))];
        }
    }

    public static void destroyInstance(DeathSanctumParty party) {
        party.moveTo(DeathSanctumData.lobbyPosition);
        party.setHasEnteredRaids(false);
        party.getPlayers().clear();
        for (NPC npc : party.getNpcs()) {
            if (npc != null && npc.getPosition().getZ() == party.getHeight())
                World.deregister(npc);
        }
    }

}
