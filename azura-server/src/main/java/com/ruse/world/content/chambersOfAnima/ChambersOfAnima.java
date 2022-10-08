package com.ruse.world.content.chambersOfAnima;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.GameObject;
import com.ruse.model.Locations;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Cases;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.combat.weapon.CombatSpecial;
import com.ruse.world.content.chambersOfAnima.boss.impl.RaidsFirstBoss;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class ChambersOfAnima {

    public static void start(ChambersOfAnimaParty party, ChambersOfAnimaParty.ChambersDifficulty difficulty) {

        Player p = party.getOwner();
        p.getPacketSender().sendInterfaceRemoval();

        if (party.isHasEnteredRaids()) {
            p.getPacketSender().sendMessage("Your party is already in a raids!");
            return;
        }

        if (party.getOwner() != p) {
            p.getPacketSender().sendMessage("Only the party leader can start the fight.");
            return;
        }
        if (party.getPlayers().size() < 2) {
            p.getPacketSender().sendMessage("You need at least 2 party members to start Chambers of Anima.");
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

                if (member.getSanctumKeysOpened() < 1000 && member.getRights() != PlayerRights.DEVELOPER){
                    member.getPacketSender().sendInterfaceRemoval();
                    member.getPacketSender()
                            .sendMessage("You need to have opened at least 1,000 Sanctum keys before doing Chambers of Anima.");
                    p.getPacketSender().sendMessage(
                            "" + member.getUsername() + "  has not opened at least 1,000 Sanctum keys.");
                    return;
                }
            }
        }

        party.setAcceptableType(CombatType.values()[Misc.getRandom(2)]);

        party.setHasEnteredRaids(true);
        final int height = p.getIndex() * 4;
        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.ANIMA_CHAMBERS, height, null));

        for (Player member : party.getPlayers()) {
            member.getPacketSender().sendInterfaceRemoval();
            member.setRegionInstance(null);
            member.getMovementQueue().reset();
            member.getClickDelay().reset();
            member.moveTo(new Position(3216, 2887, height));
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
            member.setChambersOfAnimaParty(party);
            member.setRaidsDamage(0);

            member.setSpecialPercentage(100);
            CombatSpecial.updateBar(member);
            member.getPacketSender().sendWalkableInterface(156000, true);

            member.getPacketSender().sendSpriteChange(156001, party.getAcceptableType() == CombatType.MELEE ? 498
                    : party.getAcceptableType() == CombatType.RANGED ? 502 : 504);
            member.getPacketSender().sendString(156006, "00:00");
            member.getPacketSender().sendString(156007, "0");
            member.getPacketSender().sendString(156008, "0");
            member.getPacketSender().sendString(156009, "0");

        }
        party.setDeathCount(0);
        party.setKills(0);
        party.sendMessage("@red@Welcome to the Chambers of Anima! (" + difficulty.name() + ")");
        party.setCurrentPhase(1);
        party.setHeight(height);
        party.setCompletedCurrent(true);
        party.setWave(0);
        party.setDifficulty(difficulty);
        party.getTimer().reset();

        party.setCurrentBoss(new RaidsFirstBoss(party));

        party.sendMessage("Vespula will spawn shortly.");

        TaskManager.submit(new Task(5, false) {
            @Override
            protected void execute() {
                party.getCurrentBoss().spawn();
                stop();
            }
        });

    }


    public static void refreshInterface(Player member, ChambersOfAnimaParty party) {
        long timerElapsed = party.getTimer().elapsed();
        String timeString = Misc.formatTime(timerElapsed);

        member.getPacketSender().sendSpriteChange(156001, party.getAcceptableType() == CombatType.MELEE ? 498
                : party.getAcceptableType() == CombatType.RANGED ? 502 : 504);

        member.getPacketSender().sendString(156006, timeString);
        member.getPacketSender().sendString(156007, Misc.formatNumberRaids(party.getTotalDamage() / 10));
        member.getPacketSender().sendString(156008, Misc.formatNumberRaids(member.getRaidsDamage() / 10));
        member.getPacketSender().sendString(156009, "" + party.getDeathCount());


        member.getPacketSender().sendString(111709, "Invite");

        int start = 111716;
        for (int i = 0; i < party.getPlayers().size(); i++) {
            member.getPacketSender().sendString(start++, "" + party.getPlayers().get(i).getUsername());
            member.getPacketSender().sendString(start++,
                    "" + party.getPlayers().get(i).getSkillManager().getTotalLevel());
            member.getPacketSender().sendString(start++,
                    "" + party.getPlayers().get(i).getSkillManager().getCombatLevel());
        }

        for (int i = start; i < 111737; i++) {
            member.getPacketSender().sendString(start++, "---");
            member.getPacketSender().sendString(start++, "--");
            member.getPacketSender().sendString(start++, "-");
        }

        member.getPacketSender().sendString(111702, "Raiding Party: @whi@" + party.getPlayers().size());
    }

    public static int getBarrier(int y) {
        int index = 1;
        for (int yCoord : ChambersOfAnimaData.portalYPos) {
            if (y == yCoord || y - 1 == yCoord)
                return index;
            index++;
        }
        return index;
    }

    public static void handlePortal(ChambersOfAnimaParty party, Player player, GameObject gameObject) {
        int wave = party.getWave() - 1;

        if (wave == -1) {
            player.sendMessage("You must complete the current room to progress.");
        } else {
            if (wave == 1) {
                if ( (gameObject.getPosition().getY() == ChambersOfAnimaData.portalYPos[wave]
                                || gameObject.getPosition().getY() + 1 == ChambersOfAnimaData.portalYPos[wave]) &&
                        party.isCompletedCurrent()) {
                    passPortal(player, gameObject);
                } else if (gameObject.getPosition().getY() < ChambersOfAnimaData.portalYPos[wave]) {
                    passPortal(player, gameObject);
                }
            } else if (wave == 0 || wave == 2) {
                if ((gameObject.getPosition().getY() == ChambersOfAnimaData.portalYPos[wave]
                                || gameObject.getPosition().getY() - 1 == ChambersOfAnimaData.portalYPos[wave]
                                || gameObject.getPosition().getY() - 2 == ChambersOfAnimaData.portalYPos[wave]) &&
                        party.isCompletedCurrent()) {
                    passPortal(player, gameObject);
                } else if (gameObject.getPosition().getY() < ChambersOfAnimaData.portalYPos[wave]) {
                    passPortal(player, gameObject);
                }
            } else {
                player.sendMessage("You must complete the current room to progress.");
            }
        }
    }

    public static void passPortal(Player player, GameObject gameObject) {
        int barrier = getBarrier(gameObject.getPosition().getY());
        if (barrier == 2) {
            if (player.getPosition().getX() == gameObject.getPosition().getX() ||
                    player.getPosition().getX() == gameObject.getPosition().getX() + 1 ||
                    player.getPosition().getX() == gameObject.getPosition().getX() + 2) {
                if (player.getPosition().getY() > gameObject.getPosition().getY()) {
                    player.moveTo(new Position(player.getPosition().getX(), gameObject.getPosition().getY() - 1, player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(player.getPosition().getX(), gameObject.getPosition().getY() + 1, player.getPosition().getZ()));
                }
            }
        } else if (barrier == 3) {
            if (player.getPosition().getY() == gameObject.getPosition().getY() ||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 1 ||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 2) {
                if (player.getPosition().getX() >= gameObject.getPosition().getX()) {
                    player.moveTo(new Position(gameObject.getPosition().getX() - 1, player.getPosition().getY(), player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(gameObject.getPosition().getX() + 1, player.getPosition().getY(), player.getPosition().getZ()));
                }
            }
        } else {
            if (player.getPosition().getY() == gameObject.getPosition().getY() ||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 1||
                    player.getPosition().getY() == gameObject.getPosition().getY() + 2) {
                if (player.getPosition().getX() > gameObject.getPosition().getX()) {
                    player.moveTo(new Position(gameObject.getPosition().getX() - 1, player.getPosition().getY(), player.getPosition().getZ()));
                } else {
                    player.moveTo(new Position(gameObject.getPosition().getX() + 1, player.getPosition().getY(), player.getPosition().getZ()));
                }
            }
        }
    }


    public static void handleDeath(ChambersOfAnimaParty party, Player player) {
        //party.getPlayers().remove(player);
        // party.remove(player, true);
        // player.sendMessage("@red@You died.");
        player.moveTo(new Position(3216, 2887, party.getHeight()));
        party.setDeathCount(party.getDeathCount() + 1);
    }

    public static Player randomPlayer(ChambersOfAnimaParty party) {
        return party.getPlayers().get(Misc.getRandom(party.getPlayers().size() - 1));
    }

    public static void finishRaid(ChambersOfAnimaParty party) {
        party.setHasEnteredRaids(false);


        for (Player member : party.getPlayers()) {
            member.getPacketSender().sendInterfaceRemoval();
        }


        int totalShards = party.getPlayers().size() == 1 ? 125 : (125 + ((party.getPlayers().size() - 1) * 125) + 1);
        if (party.getDifficulty() == ChambersOfAnimaParty.ChambersDifficulty.HARD) {
            totalShards *= 3;
        }

        //totalShards /= (party.getDeathCount() + 1);

        long finalTotalDamage = party.getTotalDamage();
        int finalTotalShards = totalShards;
        TaskManager.submit(new Task(6, false) {

            @Override
            public void execute() {
                party.sendMessage("@red@Your party has completed the Chambers of Anima");
                party.moveTo(ChambersOfAnimaData.lobbyPosition);

                String prefix = "Hard";
                if (party.getDifficulty() == ChambersOfAnimaParty.ChambersDifficulty.EASY) {
                    prefix = "Easy";
                }

                long timerElapsed = party.getTimer().elapsed();
                String timeString = Misc.formatTime(timerElapsed);
                party.sendMessage("Chambers of Anima (" + prefix + ") completion time: @red@" + timeString);

                for (Player player : party.getPlayers()) {

                    if (party.getDifficulty() == ChambersOfAnimaParty.ChambersDifficulty.EASY) {
                        if (player.getAnimaEasyTimer() <= 0
                                || timerElapsed < player.getAnimaEasyTimer()) {
                            player.setAnimaEasyTimer(timerElapsed);
                            player.sendMessage("Personal Best (" + prefix + "): " + Misc.formatTime(player.getAnimaEasyTimer()));
                        }
                    } else {
                        if (player.getAnimaHardTimer() <= 0
                                || timerElapsed < player.getAnimaHardTimer()) {
                            player.setAnimaHardTimer(timerElapsed);
                        }
                        player.sendMessage("Personal Best (" + prefix + "): " + Misc.formatTime(player.getAnimaHardTimer()));
                    }

                    player.setAnimaChambersKC(player.getAnimaChambersKC() + 1);

                    double shards = ((double) player.getRaidsDamage() / (double) finalTotalDamage) * finalTotalShards;

                    if (ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.RAIDS_LOOT) {
                        shards *= 2;
                    }

                    if (player.getInventory().contains(23753)) {
                        player.getInventory().delete(23753, 1);
                        shards *= 1.1D;
                    }

                    player.getInventory().add(23858, (int) shards);
                    player.sendMessage("@red@You received " + (int) shards + " Anima Key Shards for your contribution.");

                    player.getSeasonPass().addExperience(2);
                    Cases.grantCasket(player, 5);
                    player.setRaidsDamage(0);

                    if (timerElapsed / 1000 <= 180)
                        player.getDailyTaskManager().submitProgressToIdentifier(46, 1);

                    player.getDailyTaskManager().submitProgressToIdentifier(24, 1);

                }
                party.setTotalDamage(0);
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

    public static void destroyInstance(ChambersOfAnimaParty party) {
        party.moveTo(ChambersOfAnimaData.lobbyPosition);
        party.setHasEnteredRaids(false);
        party.getPlayers().clear();
        for (NPC npc : party.getNpcs()) {
            if (npc != null && npc.getPosition().getZ() == party.getHeight())
                World.deregister(npc);
        }
    }

}
