package com.ruse.world.content.raids.impl.gods;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class GodCombat {


    public static void sequence(GodsRaidsParty party){

        if (party.getSaradomin().getConstitution() <= party.getSaradomin().getDefaultConstitution() * 3 / 4
                && party.getSaradominHP() == 0){
            party.getSaradomin().setConstitution(party.getSaradomin().getDefaultConstitution() * 3 / 4);
            party.sendMessage("@blu@Saradomin now has a shield.");
            party.setSaradominHP(1);
            party.setSaradominAttackable(false);
        }
        if (party.getZamorak().getConstitution() <= party.getZamorak().getDefaultConstitution() * 3 / 4
                && party.getZamorakHP() == 0){
            party.getZamorak().setConstitution(party.getZamorak().getDefaultConstitution() * 3 / 4);
            party.sendMessage("@red@Zamorak now has a shield.");
            party.setZamorakHP(1);
            party.setSaradominAttackable(true);
        }


        if (party.getSaradomin().getConstitution() <= party.getSaradomin().getDefaultConstitution() /2
                && party.getSaradominHP() == 1){
            party.getSaradomin().setConstitution(party.getSaradomin().getDefaultConstitution() / 2);
            party.sendMessage("@blu@Saradomin now has a shield.");
            party.setSaradominHP(2);
            party.setSaradominAttackable(false);
        }
        if (party.getZamorak().getConstitution() <= party.getZamorak().getDefaultConstitution() /2
                && party.getZamorakHP() == 1){
            party.getZamorak().setConstitution(party.getZamorak().getDefaultConstitution() / 2);
            party.sendMessage("@red@Zamorak now has a shield.");
            party.setZamorakHP(2);
            party.setSaradominAttackable(true);
        }


        if (party.getSaradomin().getConstitution() <= party.getSaradomin().getDefaultConstitution()  / 4
                && party.getSaradominHP() == 2) {
            party.getSaradomin().setConstitution(party.getSaradomin().getDefaultConstitution() / 4);
            party.sendMessage("@blu@Saradomin now has a shield.");
            party.setSaradominHP(3);
            party.setSaradominAttackable(false);
        }
        if (party.getZamorak().getConstitution() <= party.getZamorak().getDefaultConstitution() / 4
                && party.getZamorakHP() == 2) {
            party.getZamorak().setConstitution(party.getZamorak().getDefaultConstitution() / 4);
            party.sendMessage("@red@Zamorak now has a shield.");
            party.setZamorakHP(3);
            party.setSaradominAttackable(true);
        }


        if (party.getSaradomin().getConstitution() <= 0
                && party.getSaradominHP() == 3) {
            party.sendMessage("@blu@Saradomin has been killed.");
            party.setSaradominHP(4);
            party.setSaradominAttackable(false);
        }
        if (party.getZamorak().getConstitution() <= 0
                && party.getZamorakHP() == 3) {
            party.sendMessage("@red@Zamorak has been killed.");
            party.setZamorakHP(4);
            party.setSaradominAttackable(true);
        }
        updateHitpoints(party);
    }

    public static void updateHitpoints(GodsRaidsParty party){

        int percentSaradomin = (int) (100D * (double)party.getSaradomin().getConstitution() / (double)party.getSaradomin().getDefaultConstitution());
        int percentZamorak = (int) (100D * (double)party.getZamorak().getConstitution() / (double)party.getZamorak().getDefaultConstitution());

        if (percentSaradomin <= 0 && party.getSaradomin().getConstitution() > 0)
            percentSaradomin  =1;

        if (percentZamorak <= 0 && party.getZamorak().getConstitution() > 0)
            percentZamorak  =1;
        party.sendMessage("GODSHP##" + percentSaradomin + "##" + percentZamorak);

    }


    public static void doAttack(GodsRaidsParty party){
        party.setAttack(party.getAttack() + 1);
        if ((party.isSaradominAttacking() || party.getZamorak().getConstitution() <= 0) && party.getSaradomin().getConstitution() > 0) {
            party.getSaradomin().setPositionToFace(party.getZamorak().getPosition());
            randomSaradomin(party);
            party.setSaradominAttacking(false);
        } else {
            party.getZamorak().setPositionToFace(party.getSaradomin().getPosition());
            randomZamorak(party);
            party.setSaradominAttacking(true);
        }
    }

    public static void randomSaradomin(GodsRaidsParty party){
        int random = Misc.getRandom(2);
        oneHit(party , party.getSaradomin(), 3000, CombatType.MAGIC, CombatIcon.MAGIC);

        if (party.getAttack() % 3 == 0) {
        if (random == 0)
            saradominWall(party, party.getSaradomin());
        else
            saradominWall(party, party.getSaradomin());
        }
    }

    public static void randomZamorak(GodsRaidsParty party) {
        int random = Misc.getRandom(2);
        oneHit(party , party.getZamorak(), 1627, CombatType.RANGED, CombatIcon.RANGED);

        if (party.getAttack() % 3 == 0) {
            if (random == 0)
                zamorakWall(party, party.getZamorak());
            else
                zamorakWall(party, party.getZamorak());
        }
    }

    public static void oneHit(GodsRaidsParty party, NPC npc, int graphic, CombatType combatType, CombatIcon combatIcon) {
        npc.performAnimation(new Animation(2779));
        for (final Player near : party.getPlayers()) {
            if (near == null || near.isTeleporting() || near.isDying() || near.getConstitution() <= 0)
                continue;
            if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                continue;
            new Projectile(npc, near, graphic, 150, 3, 90, 30, 0).sendProjectile();
        }

        TaskManager.submit(new Task(6, npc, false) {

            @Override
            public void execute() {
                for (final Player near : party.getPlayers()) {
                    if (near == null || near.isTeleporting() || near.isDying() || near.getConstitution() <= 0)
                        continue;
                    if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                        continue;
                    if (!isProtecting(near, combatType)) {
                        near.dealDamage(new Hit(near.getConstitution(), Hitmask.RED, combatIcon));
                    }else{
                        near.dealDamage(new Hit(0, Hitmask.RED, combatIcon));
                    }
                }
                stop();
            }
        });
    }


    public static void StandInSpot(GodsRaidsParty party, NPC npc) {
        npc.performAnimation(new Animation(2779));
        List<Position> tiles = new ArrayList<>();
        List<Position> portals = new ArrayList<>();
        int xStart = 2589;
        int xEnd = 2598;
        int yStart = 4440;
        int yEnd = 4447;

        for (int y =  yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                tiles.add(new Position(x, y, npc.getPosition().getZ()));
            }
        }

        for (int x =  0; x <= 4; x++){
            Position pos = tiles.get(Misc.getRandom(tiles.size() - 1));
            portals.add(pos);
            party.getOwner().getPacketSender().sendGlobalGraphic(new Graphic(626, GraphicHeight.LOW), pos);
            TaskManager.submit(new Task(4,  false) {
                int tick = 0;
                @Override
                public void execute() {
                    party.getOwner().getPacketSender().sendGlobalGraphic(new Graphic(626, GraphicHeight.LOW), pos);
                    if (tick == 1) {
                        stop();
                    }
                    tick ++;
                }

            });
        }

        TaskManager.submit(new Task(12, npc, false) {

            @Override
            public void execute() {
                for (final Player near : party.getPlayers()) {
                    if (near == null || near.isTeleporting() || near.isDying() || near.getConstitution() <= 0)
                        continue;
                    if (near.getPosition().distanceToPoint(npc.getPosition().getX(), npc.getPosition().getY()) > 20)
                        continue;
                    boolean getHit = true;

                    for (Position position : portals){
                        if (near.getPosition().equals(position)){
                            getHit = false;
                        }
                    }
                    if (getHit) {
                        int damage = Misc.exclusiveRandom(750, 1190);
                        near.dealDamage(new Hit(damage, Hitmask.RED, CombatIcon.NONE));
                    }

                }
                stop();
            }
        });
    }

        public static boolean isProtecting(Player player, CombatType combatType){
            if (PrayerHandler.isActivated(player,
                    PrayerHandler.getProtectingPrayer(combatType))
                    || CurseHandler.isActivated(player,
                    CurseHandler.getProtectingPrayer(combatType))) {
                return true;
            }
            return false;
        }

    public static void saradominWall(GodsRaidsParty party, NPC npc){
        npc.performAnimation(new Animation(810));
        List<Position> tiles = new ArrayList<>();
        int xStart = 2588;
        int xEnd = 2599;
        int yStart = 4439;
        int yEnd = 4448;

        for (int y =  yStart; y <= yEnd; y++) {
            for (int x = xStart; x <= xEnd; x++) {
                tiles.add(new Position(x, y, npc.getPosition().getZ()));
            }
        }

        if (party != null) {
            party.sendMessage("Saradomin sends a wall of fire!");
        }
        npc.forceChat("You will be burned!");

        npc.performAnimation(new Animation(811));

        npc.setChargingAttack(true);
        TaskManager.submit(new Task(3, npc, false) {

            @Override
            public void execute() {
                getWavesNorthSouth(npc, party.getOwner());
            }

            public void getWavesNorthSouth(NPC npc, Player player) {
                if (tiles.size() <= 0) {
                    npc.setChargingAttack(false);
                    this.stop();
                    return;
                }

                TaskManager.submit(new Task(1, npc, false) {
                    final int[] x = {xStart};
                    final int hole1 = 1 + Misc.getRandom(6);
                    final int hole2 = 1 + Misc.getRandom(6);
                    int tick = 0;
                    public void execute() {
                        final List<Position> currentYTiles = tiles.stream()
                                .filter(position -> position.getX() == x[0])
                                .collect(Collectors.toList());
                        removeExitPoints(currentYTiles, hole1, hole2);
                        for (Position p : currentYTiles) {
                            player.getPacketSender().sendGlobalGraphic(new Graphic(78), new Position(p.getX(), p.getY(), p.getZ()));
                            for (Player players : npc.getClosePlayers(32)) {
                                if (players.getPosition().getX() == p.getX() &&
                                        players.getPosition().getY() == p.getY() &&
                                        players.getPosition().getZ() == npc.getPosition().getZ()) {
                                    int damage = Misc.exclusiveRandom(750, 1190);
                                    players.dealDamage(new Hit(damage, Hitmask.RED, CombatIcon.NONE));
                                    players.sendMessage("@blu@You were burnt by the flames!");
                                    players.performGraphic(new Graphic(1640));
                                }
                            }
                            if (p.getY() == yEnd) {
                                x[0]++;
                            }

                            if (tick == 5) {
                                npc.setChargingAttack(false);
                            }
                            tick++;
                            if (p.getY() == yEnd && x[0] == xEnd) {
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


    public static void zamorakWall(GodsRaidsParty party, NPC npc){
        npc.performAnimation(new Animation(810));
        List<Position> tiles = new ArrayList<>();
        int xStart = 2598;
        int xEnd = 2587;
        int yStart = 4439;
        int yEnd = 4448;

        for (int y =  yStart; y <= yEnd; y++) {
            for (int x = xStart; x >= xEnd; x--) {
                tiles.add(new Position(x, y, npc.getPosition().getZ()));
            }
        }

        if (party != null) {
            party.sendMessage("Zamorak sends a wall of fire!");
        }
        npc.forceChat("You will be burned!");

        npc.performAnimation(new Animation(811));

        npc.setChargingAttack(true);
        TaskManager.submit(new Task(3, npc, false) {

            @Override
            public void execute() {
                getWavesNorthSouth(npc, party.getOwner());
            }

            public void getWavesNorthSouth(NPC npc, Player player) {
                if (tiles.size() <= 0) {
                    npc.setChargingAttack(false);
                    this.stop();
                    return;
                }

                TaskManager.submit(new Task(1, npc, false) {
                    final int[] x = {xStart};
                    final int hole1 =1 + Misc.getRandom(6);
                    final int hole2 = 1 + Misc.getRandom(6);
                    int tick = 0;
                    public void execute() {
                        final List<Position> currentYTiles = tiles.stream()
                                .filter(position -> position.getX() == x[0])
                                .collect(Collectors.toList());

                        removeExitPoints(currentYTiles, hole1, hole2);
                        for (Position p : currentYTiles) {
                            player.getPacketSender().sendGlobalGraphic(new Graphic(78), new Position(p.getX(), p.getY(), p.getZ()));
                            for (Player players : npc.getClosePlayers(32)) {
                                if (players.getPosition().getX() == p.getX() &&
                                        players.getPosition().getY() == p.getY() &&
                                        players.getPosition().getZ() == npc.getPosition().getZ()) {
                                    int damage = Misc.exclusiveRandom(750, 1190);
                                    players.dealDamage(new Hit(damage, Hitmask.RED, CombatIcon.NONE));
                                    players.sendMessage("@blu@You were burnt by the flames!");
                                    players.performGraphic(new Graphic(1640));
                                }
                            }
                            if (p.getY() == yEnd) {
                                x[0]--;
                            }

                            if (tick == 5) {
                                npc.setChargingAttack(false);
                            }
                            tick++;
                            if (p.getY() == yEnd && x[0] == xEnd) {
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

    public static void removeExitPoints(List<Position> tiles, int hole1, int hole2) {
        if (tiles.isEmpty()) {
            return;
        }
        if (hole1 < tiles.size()) {
            tiles.remove(hole1);
        }
        if (hole2 < tiles.size()) {
            tiles.remove(hole2);
        }
    }

}
