package com.ruse.model;

import com.ruse.GameSettings;
import com.ruse.model.RegionInstance.RegionInstanceType;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.EffectTimer;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.PlayerPunishment.Jail;
import com.ruse.world.content.Zulrah;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnima;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaData;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.pvp.BountyHunter;
import com.ruse.world.content.combat.strategy.impl.Scorpia;
import com.ruse.world.content.deathsanctum.DeathSanctum;
import com.ruse.world.content.deathsanctum.DeathSanctumData;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.events.PartyChest;
import com.ruse.world.content.events.EventManager;
import com.ruse.world.content.minigames.impl.*;
import com.ruse.world.content.progressionzone.ProgressionZone;
import com.ruse.world.content.raids.RaidsManager;
import com.ruse.world.content.raids.impl.gods.GodsRaids;
import com.ruse.world.content.raids.impl.gods.GodsRaidsData;
import com.ruse.world.content.skill.impl.dungeoneering.Dungeoneering;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.zombie.ZombieRaids;
import com.ruse.world.entity.Entity;
import com.ruse.world.entity.impl.Character;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.CelestialZone;
import com.world.content.globalBoss.EggSpawns;

import java.util.HashMap;

public class Locations {

    public static void login(Player player) {
        player.setLocation(Location.getLocation(player));
        player.getLocation().login(player);
        player.getLocation().enter(player);

        if (player.getLocation() == Location.EASTER)
            player.moveTo(GameSettings.DEFAULT_POSITION);

        if (!player.isDefaultTeleporter()){
            player.setDefaultTeleporter(true);
            if (player.getLocation() == Location.DEFAULT && !player.newPlayer())
            player.moveTo(GameSettings.DEFAULT_POSITION);
        }


        if (locationPopulations.containsKey(player.getLocation()))
            locationPopulations.put(player.getLocation(), locationPopulations.get(player.getLocation()) + 1);
        else
            locationPopulations.put(player.getLocation(), 1);
    }

    public static void logout(Player player) {
        player.getLocation().logout(player);
        if (player.getRegionInstance() != null)
            player.getRegionInstance().destruct();
        if (player.getLocation() != Location.GODWARS_DUNGEON) {
            player.getLocation().leave(player);
            if (locationPopulations.containsKey(player.getLocation()))
                locationPopulations.put(player.getLocation(), locationPopulations.get(player.getLocation()) - 1);
            else
                locationPopulations.put(player.getLocation(), 0);
        }
    }

    public static int PLAYERS_IN_WILD;
    public static int PLAYERS_IN_DUEL_ARENA;

    public static HashMap<Location, Integer> locationPopulations = new HashMap<>();

    public enum Location {
        VORKATH(new int[]{1792, 1855}, new int[]{4224, 4287}, true, false, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.VORKATH)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.VORKATH, player.getIndex() * 4));
                }
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.VORKATH));
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.VORKATH)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.VORKATH, player.getIndex() * 4));
                }
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

        },


        CELESTIAL_ZONE(new int[]{2498, 2557}, new int[]{4544, 4607}, false, true, true, false, false, true) {
            @Override
            public void login(Player player) {
                if (!CelestialZone.gameActive){
                    CelestialZone.movePlayer(player);
                }
            }
            @Override
            public void process(Player player) {
                if (!CelestialZone.gameActive){
                    CelestialZone.movePlayer(player);
                }
            }
            @Override
            public void leave(Player player) {
                if (!CelestialZone.gameActive){
                    CelestialZone.movePlayer(player);
                }
            }
            @Override
            public void enter(Player player) {
               /* if (CelestialZone.gameActive && CelestialZone.gameTicks > 0) {
                    int seconds = (int)((double)CelestialZone.gameTicks*0.6D);
                    player.getPA().sendEffectTimerSeconds(seconds, EffectTimer.CELESTIAL_ZONE);
                }*/
            }

        },


        EVENTS_ROOM(new int[]{1684, 1708}, new int[]{4247, 4269}, true, true, true, false, false, true) {
            @Override
            public void process(Player player) {
                EventManager.locationProcess(player);
            }

            @Override
            public void enter(Player player) {
                if (player.getPosition().getZ() % 4 == 0) {
                    for (Player p : World.getPlayers()) {
                        if (p == null)
                            continue;
                        if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())&& player.getRights() != PlayerRights.DEVELOPER) {
                            if (p.getLocation().equals(EVENTS_ROOM)) {
                                player.getPacketSender().sendMessage("You already have an account at the Event zone.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                return;
                            }
                        }
                    }
                }

                PartyChest.loadBalloons(player);
            }

        },


        ASSASSINS_GUILD(new int[]{2567, 2610}, new int[]{5260, 5291}, true, true, true, false, false, true) {
        },



        GODS_LOBBY(new int[]{2642, 2668}, new int[]{4498, 4515}, true, false, true, false, true, false) {
            @Override
            public void leave(Player player) {
                player.getPacketSender().sendCameraNeutrality();

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().remove(player);

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void enter(Player player) {
                player.getPacketSender().sendWalkableInterface(144900, false);

                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && (player.getSerialNumber() != null && p.getSerialNumber() != null ?
                            player.getSerialNumber().equals(p.getSerialNumber())
                            : player.getHostAddress().equals(p.getHostAddress()))) {
                        if (player.getMac() != null && p.getMac() != null &&
                                !player.getMac().equals(p.getMac())) {
                            continue;
                        } else {
                            if (p.getLocation().equals(GODS_LOBBY) && p.getPosition().getZ() == player.getPosition().getZ()) {
                                player.getPacketSender().sendMessage("You already have an account at Isle of the Gods.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }

                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);

                if (player.getGodsRaidsParty() == null) {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Isle of the Gods Party: @whi@0");
                }

                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111700);
                player.getPacketSender().sendConfig(6000, 4);
                player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
            }

            @Override
            public void login(Player player) {
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);
            }

            @Override
            public void process(Player player) {
                if (player.getGodsRaidsParty() != null) {
                    player.getGodsRaidsParty().refreshInterface();
                } else {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Isle of the Gods Party: @whi@0");
                }
            }
        },
        ISLE_GODS(new int[]{2580, 2605}, new int[]{4435, 4452}, true, true, true,
                false, true, false) {
            @Override
            public void logout(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.KALPHITE_KING)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs()
                            .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, player.getPosition().getZ()));
                }
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();

                if (player.getGodsRaidsParty() != null) {
                    player.getGodsRaidsParty().remove(player);
                }

                player.moveTo(GodsRaidsData.LOBBY_POSITION);

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().getPlayersInRaid().remove(player);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void leave(Player player) {
                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.KALPHITE_KING)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs()
                            .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, player.getPosition().getZ()));
                }

                if (player.getGodsRaidsParty() != null) {
                    if (player.getGodsRaidsParty().getOwner().equals(player)) {
                        World.getNpcs()
                                .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, player.getIndex() * 4));
                    }
                }

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().remove(player);

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().getPlayersInRaid().remove(player);

                player.getMovementQueue().setLockMovement(false);

                int id = 111716;
                for (int i = 111716; i < 111737; i++) {
                    player.getPacketSender().sendString(id++, "---");
                    player.getPacketSender().sendString(id++, "--");
                    player.getPacketSender().sendString(id++, "-");
                }
                player.getPacketSender().sendString(111709, "Create");
                player.getPacketSender().sendString(111702, "---");
            }

            @Override
            public void login(Player player) {

                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.KALPHITE_KING)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs()
                            .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, player.getPosition().getZ()));
                }

                if (player.getGodsRaidsParty() != null) {
                    if (player.getGodsRaidsParty().getOwner().equals(player)) {
                        World.getNpcs()
                                .forEach(n -> n.removeInstancedNpcs(Location.ISLE_GODS, player.getIndex() * 4));
                    }
                }

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().remove(player);

                if (player.getGodsRaidsParty() != null)
                    player.getGodsRaidsParty().getPlayersInRaid().remove(player);

                player.moveTo(GodsRaidsData.LOBBY_POSITION);

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public boolean canTeleport(Player player) {
                if (player.getGodsRaidsParty() != null) {
                    player.sendMessage("You cannot teleport while in a party");
                    return false;
                }
                return true;
            }

            @Override
            public void enter(Player player) {

                int id = 111716;
                for (int i = 151716; i < 111737; i++) {
                    player.getPacketSender().sendString(id++, "---");
                    player.getPacketSender().sendString(id++, "--");
                    player.getPacketSender().sendString(id++, "-");
                }
                player.getPacketSender().sendString(111709, "Create");
                player.getPacketSender().sendString(111702, "Isle of the Gods Party: @whi@0");

                if (GodsRaidsData.LOBBY_AREA.inside(player.getPosition(), player.getPosition().getZ())) {
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);
                }

                player.setRegionInstance(new RegionInstance(player, RegionInstanceType.KALPHITE_KING));
                /*  player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700)
                       .sendTab(GameSettings.QUESTS_TAB);*/
            }

            @Override
            public void onDeath(Player player) {
                if (player.getGodsRaidsParty() != null) {
                    GodsRaids.handleDeath(player.getGodsRaidsParty(), player);
                }
                player.getPacketSender().sendCameraNeutrality();
                player.setInsideGodsRaids(false);
                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void process(Player player) {
                if (player.getGodsRaidsParty() != null) {
                    player.getGodsRaidsParty().refreshInterface();
                }

            }

        },


        AFK(new int[]{2640, 2674}, new int[]{3970, 3994}, false, true, true, false, true, true) {
        },

        VOTING_MINIGAME(new int[]{2543, 2557}, new int[]{9944, 9963}, true, false, true, false, true, false) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.VOTING_MINIGAME)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.VOTING_MINIGAME, player.getIndex() * 4));
                }
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.VOTING_MINIGAME));
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.VOTING_MINIGAME)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.VOTING_MINIGAME, player.getIndex() * 4));
                }
                player.getInventory().delete(23460, player.getInventory().getAmount(23460));
                player.getInventory().delete(23461, player.getInventory().getAmount(23461));
                player.getInventory().delete(23462, player.getInventory().getAmount(23462));

                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
                player.getVotingMinigame().handleDeath();
            }

            @Override
            public boolean handleKilledNPC(Player player, NPC npc) {
                player.getVotingMinigame().killNPC(npc);
                return false;
            }
        },
        VDAY(new int[]{2049, 2110}, new int[]{4993, 5053},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                if (player.getPosition().getZ() % 4 == 0) {
                    for (Player p : World.getPlayers()) {
                        if (p == null)
                            continue;
                        if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                            if (p.getLocation().equals(VDAY)) {
                                player.getPacketSender().sendMessage("You already have an account at the Valentine Boss.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }
            }
        },
        HANTO(new int[]{2830, 2866}, new int[]{4557, 4594},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(HANTO)) {
                            player.getPacketSender().sendMessage("You already have an account at the Hanto Warrior.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
                player.sendMessage("@blu@You will receive a " + player.getHantoDR() + "% Drop rate boost for your next Hanto Warrior kill.");
            }
        },

        HOLIDAY_EVENTS(new int[]{3017, 3061}, new int[]{2824, 2870},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                if (player.getPosition().getZ() % 4 == 0) {
                    for (Player p : World.getPlayers()) {
                        if (p == null)
                            continue;
                        if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                            if (p.getLocation().equals(HOLIDAY_EVENTS)) {
                                player.getPacketSender().sendMessage("You already have an account at the Easter event.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }
            }
        },
        EASTER(new int[]{2880, 2943}, new int[]{4671, 4735},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                if (player.getPosition().getZ() % 4 == 0) {
                    for (Player p : World.getPlayers()) {
                        if (p == null)
                            continue;
                        if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                            if (p.getLocation().equals(EASTER)) {
                                player.getPacketSender().sendMessage("You already have an account at Uncle Sam.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                return;
                            }
                        }
                    }
                }
             //   EggSpawns.loadEggs(player);
            }
        },

        HALLS_OF_VALOR(new int[]{2175, 2239}, new int[]{4998, 5053}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                HallsOfAmmo.resetBarrows(player);
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.HALLS_OF_VALOR)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.HALLS_OF_VALOR, player.getIndex() * 4));
                }
                player.hov.start();
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.HALLS_OF_VALOR)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.HALLS_OF_VALOR, player.getIndex() * 4));
                }
                HallsOfAmmo.resetBarrows(player);
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

        },
        SPOOKY_KRAKEN(new int[]{3089, 3116}, new int[]{2900, 2921}, true, false, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.SPOOKY_KRAKEN)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.SPOOKY_KRAKEN, player.getIndex() * 4));
                }
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.SPOOKY_KRAKEN));
                NPC npc = new NPC(9031, new Position(3102, 2917, player.getIndex() * 4)).setSpawnedFor(player);

                World.register(player, npc);
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.SPOOKY_KRAKEN)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.SPOOKY_KRAKEN, player.getIndex() * 4));
                }
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

        },

        TREASURE_HUNTER(new int[]{1986, 2045}, new int[]{4994, 5052}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.TREASURE_HUNTER)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.TREASURE_HUNTER, player.getIndex() * 4));
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.TREASURE_HUNTER, player.getIndex() * 4));

                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.TREASURE_HUNTER));

                NPC npc = new NPC(9815, new Position(1994, 5022, player.getIndex() * 4)).setSpawnedFor(player);
                NPC npc1 = new NPC(9816, new Position(2013, 5042, player.getIndex() * 4)).setSpawnedFor(player);
                NPC npc2 = new NPC(9817, new Position(2032, 5022, player.getIndex() * 4)).setSpawnedFor(player);
                NPC npc3 = new NPC(9818, new Position(2013, 5003, player.getIndex() * 4)).setSpawnedFor(player);

                World.register(player, npc);
                World.register(player, npc1);
                World.register(player, npc2);
                World.register(player, npc3);
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.TREASURE_HUNTER)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.TREASURE_HUNTER, player.getIndex() * 4));
                }
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

        },

        ZEMOUREGAL(new int[]{3099, 3126}, new int[]{9708, 9729}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ZEMOUREGAL)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZEMOUREGAL, player.getIndex() * 4));
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZEMOUREGAL, player.getIndex() * 4));

                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.TREASURE_HUNTER));

                NPC npc = new NPC(7553, new Position(3111, 9717, player.getIndex() * 4)).setSpawnedFor(player);

                World.register(player, npc);
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ZEMOUREGAL)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZEMOUREGAL, player.getIndex() * 4));
                }
                player.getPacketSender().sendCameraNeutrality();
            }

            @Override
            public void logout(Player player) {
                leave(player);
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

        },

        PATRICK(new int[]{3076, 3133}, new int[]{2886, 2938},
                true, true, true, false, false, true) {},

        ZAMASU(new int[]{3472, 3503}, new int[]{3601, 3632},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(ZAMASU) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at Zamasu.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }

        },
        GOKU(new int[]{2853, 2880}, new int[]{2855, 2880},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(GOKU) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at Goku.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }

        },
        GARFIELD(new int[]{1999, 2028}, new int[]{4495, 4530},
                true, true, true, false, false, true) {
            @Override
        public void enter(Player player) {
            for (Player p : World.getPlayers()) {
                if (p == null)
                    continue;
                if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                    if (p.getLocation().equals(GARFIELD) && p.getPosition().getZ() == player.getPosition().getZ()) {
                        player.getPacketSender().sendMessage("You already have an account at Earthquake.");
                        player.moveTo(GameSettings.DEFAULT_POSITION);
                        continue;
                    }
                }
            }
        }},
        IRON(new int[]{3776, 3839}, new int[]{2816, 2879},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(IRON) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at the Iron Global.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }
        },
        ZENYTE(new int[]{2760, 2792}, new int[]{4834, 4856},
                true, true, true, false, false, true) {},
        TANZANITE(new int[]{2687, 2751}, new int[]{4803, 4863},
                true, true, true, false, false, true) {},
        EXODEN(new int[]{2562, 2600}, new int[]{4485, 4526},
                true, true, true, false, false, true) {},
        ONYX(new int[]{2432, 2494}, new int[]{5371, 5444},
                true, true, true, false, false, true) {},
        FALADOR(new int[]{2936, 3061}, new int[]{3329, 3389},
                true, true, true, false, false, true) {},
        CAMELOT(new int[]{2721, 2775}, new int[]{3453, 3506},
                true, true, true, false, false, true) {},
        ARDOUGNE(new int[]{2626, 2692}, new int[]{3275, 3334},
                true, true, true, false, false, true) {},
        YANILLE(new int[]{2527, 2625}, new int[]{3074, 3111},
                true, true, true, false, false, true) {},


        SLAYER_DUNG(new int[]{1851, 1921}, new int[]{5179, 5249},
                false, true, true, false, false, true) {},

        GEMSTONE_DUNG(new int[]{2241, 2304}, new int[]{4991, 5055},
                false, true, true, false, false, true) {},

        VBOSS(new int[]{2959, 2991}, new int[]{2752, 2800},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(VBOSS) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at Vote boss.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }

        },
        GUARDIAN(new int[]{2990, 3007}, new int[]{2752, 2777},
                true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(GUARDIAN) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at Lunite Guardian.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }

        },
        PRIME(new int[]{2437, 2492}, new int[]{10113, 10171},
                true, true, true, false, false, true) {

            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(PRIME) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at Prime.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }
            }


        },

        PROGRESSION_ZONES(new int[]{2770, 2798}, new int[]{9483, 9624},
                false, true, true, false, false, true) {
            @Override
            public void process(Player player) {
                if (player.getWalkableInterfaceId() != 112000)
                    player.getPacketSender().sendWalkableInterface(112000, true);
            }


            @Override
            public void enter(Player player) {
                ProgressionZone.updateInterface(player);
            }

            @Override
            public void leave(Player player) {
                player.getPacketSender().sendWalkableInterface(112000, false);
            }
        },
        ZOMBIE(new int[]{2496, 2543}, new int[]{3721, 3771}, true, false, true, false, true, false) {
            @Override
            public void logout(Player player) {

                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ZOMBIE)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZOMBIE, player.getPosition().getZ(), player));

                }
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();

                if (player.getZombieParty() != null) {
                    player.getZombieParty().remove(player, true);
                }

                player.moveTo(new Position(3832, 2821, 0));

                if (player.getZombieParty() != null)
                    player.getZombieParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendCameraNeutrality();

            }

            @Override
            public void leave(Player player) {

                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ZOMBIE)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZOMBIE, player.getPosition().getZ(), player));
                }

                if (player.getZombieParty() != null) {
                    if (player.getZombieParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZOMBIE, player.getIndex() * 4, player));
                    }
                }

				/*if (player.getZombieParty() != null)
					player.getZombieParty().remove(player, true);

				if (player.getZombieParty() != null)
					player.getZombieParty().getPlayers()
							.remove(player);*/

                player.moveTo(new Position(2553, 3715, 0));

                player.getMovementQueue().setLockMovement(false);

            }

            @Override
            public void login(Player player) {
                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ZOMBIE)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZOMBIE, player.getPosition().getZ(), player));
                }

                if (player.getZombieParty() != null) {
                    if (player.getZombieParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ZOMBIE, player.getIndex() * 4, player));
                    }
                }

                if (player.getZombieParty() != null)
                    player.getZombieParty().remove(player, true);

                if (player.getZombieParty() != null)
                    player.getZombieParty().getPlayers()
                            .remove(player);

                player.moveTo(new Position(2553, 3715, 0));

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public boolean canTeleport(Player player) {
                player.sendMessage("You cannot teleport while in a raid");
                return false;
            }

            @Override
            public void enter(Player player) {
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.ZOMBIE));
                player.getPacketSender().sendInteractionOption("null", 2, true);
            }

            @Override
            public void onDeath(Player player) {
                if (player.getZombieParty() != null) {
                    ZombieRaids.handleDeath(player.getZombieParty(),
                            player);
                }
                player.getPacketSender().sendCameraNeutrality();
                player.setInsideRaids(false);
                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void process(Player player) {
                //if (player.getZombieParty() != null)
                player.getZombieParty().refreshInterface();
            }

        },

        ZOMBIE_LOBBY(new int[]{2546, 2561}, new int[]{3710, 3726}, true, false, true, false, true, false) {
            @Override
            public void leave(Player player) {
                player.getPacketSender().sendCameraNeutrality();

                if (player.getZombieParty() != null)
                    player.getZombieParty().remove(player, true);

                if (player.getZombieParty() != null)
                    player.getZombieParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;

                    if (!player.equals(p) && (player.getSerialNumber() != null && p.getSerialNumber() != null ?
                            player.getSerialNumber().equals(p.getSerialNumber())
                            : player.getHostAddress().equals(p.getHostAddress()))) {
                        if (player.getMac() != null && p.getMac() != null &&
                                !player.getMac().equals(p.getMac())) {
                            continue;
                        } else {
                            if (p.getLocation().equals(ZOMBIE_LOBBY) && p.getPosition().getZ() == player.getPosition().getZ()) {
                                player.getPacketSender().sendMessage("You already have an account at Island Raids.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);

                if (player.getZombieParty() == null) {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }

                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111700);
                player.getPacketSender().sendConfig(6000, 4);
                player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
            }

            @Override
            public void login(Player player) {
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);
            }

            @Override
            public void process(Player player) {
                if (player.getZombieParty() != null)
                    player.getZombieParty().refreshInterface();
                else {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }
            }
        },


        DEATH_SANCTUM(new int[]{1727, 1793}, new int[]{5119, 5218}, true, false, true, false, true, false) {
            @Override
            public void logout(Player player) {

                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.DEATH_SANCTUM)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.DEATH_SANCTUM, player.getPosition().getZ(), player));

                }
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();

                if (player.getSanctumOfDeathParty() != null) {
                    player.getSanctumOfDeathParty().remove(player, true);
                }

                player.moveTo(DeathSanctumData.lobbyPosition);

                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendCameraNeutrality();

            }

            @Override
            public void leave(Player player) {

                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.DEATH_SANCTUM)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.DEATH_SANCTUM, player.getPosition().getZ(), player));
                }

                if (player.getSanctumOfDeathParty() != null) {
                    if (player.getSanctumOfDeathParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.DEATH_SANCTUM, player.getIndex() * 4, player));
                    }
                }

                player.moveTo(DeathSanctumData.lobbyPosition);

                player.getMovementQueue().setLockMovement(false);

            }

            @Override
            public void login(Player player) {
                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.DEATH_SANCTUM)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.DEATH_SANCTUM, player.getPosition().getZ(), player));
                }

                if (player.getSanctumOfDeathParty() != null) {
                    if (player.getSanctumOfDeathParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.DEATH_SANCTUM, player.getIndex() * 4, player));
                    }
                }

                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().remove(player, true);

                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().getPlayers()
                            .remove(player);

                player.moveTo(DeathSanctumData.lobbyPosition);

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public boolean canTeleport(Player player) {
                player.sendMessage("You cannot teleport while in a raid");
                return false;
            }

            @Override
            public void enter(Player player) {
                /*for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;
                    if (!player.equals(p) && player.getHostAddress().equals(p.getHostAddress())) {
                        if (p.getLocation().equals(DEATH_SANCTUM) && p.getPosition().getZ() == player.getPosition().getZ()) {
                            player.getPacketSender().sendMessage("You already have an account at the Sanctum of Death.");
                            player.moveTo(GameSettings.DEFAULT_POSITION);
                            continue;
                        }
                    }
                }*/
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.DEATH_SANCTUM));
                player.getPacketSender().sendInteractionOption("null", 2, true);
            }

            @Override
            public void onDeath(Player player) {
                if (player.getSanctumOfDeathParty() != null) {
                    DeathSanctum.handleDeath(player.getSanctumOfDeathParty(),
                            player);
                }
                player.getPacketSender().sendCameraNeutrality();
                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void process(Player player) {
                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().refreshInterface();
            }
        },

        DEATH_SANCTUM_LOBBY(new int[]{1729, 1751}, new int[]{5230, 5246}, true, false, true, false, true, false) {
            @Override
            public void leave(Player player) {
                player.getPacketSender().sendCameraNeutrality();

                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().remove(player, true);

                if (player.getSanctumOfDeathParty() != null)
                    player.getSanctumOfDeathParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;

                    if (!player.equals(p) && (player.getSerialNumber() != null && p.getSerialNumber() != null ?
                            player.getSerialNumber().equals(p.getSerialNumber())
                            : player.getHostAddress().equals(p.getHostAddress()))) {
                        if (player.getMac() != null && p.getMac() != null &&
                                !player.getMac().equals(p.getMac())) {
                            continue;
                        } else {
                            if (p.getLocation().equals(DEATH_SANCTUM_LOBBY) && p.getPosition().getZ() == player.getPosition().getZ()) {
                                player.getPacketSender().sendMessage("You already have an account at Sanctum of Death.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }

                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);

                if (player.getSanctumOfDeathParty() == null) {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }

                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111700);
                player.getPacketSender().sendConfig(6000, 4);
                player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
            }

            @Override
            public void login(Player player) {
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);
            }

            @Override
            public void process(Player player) {
                if (player.getSanctumOfDeathParty() != null) {
                    player.getSanctumOfDeathParty().refreshInterface();
                } else {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }
            }
        },




        ANIMA_CHAMBERS(new int[]{3199, 3248}, new int[]{2881, 2921}, true, false, true, false, true, false) {
            @Override
            public void logout(Player player) {

                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ANIMA_CHAMBERS)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ANIMA_CHAMBERS, player.getPosition().getZ(), player));

                }
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();

                if (player.getChambersOfAnimaParty() != null) {
                    player.getChambersOfAnimaParty().remove(player, true);
                }

                player.moveTo(ChambersOfAnimaData.lobbyPosition);

                if (player.getChambersOfAnimaParty() != null)
                    player.getChambersOfAnimaParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendCameraNeutrality();
                player.getPacketSender().sendWalkableInterface(156000, false);

            }

            @Override
            public void leave(Player player) {

                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ANIMA_CHAMBERS)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ANIMA_CHAMBERS, player.getPosition().getZ(), player));
                }

                if (player.getChambersOfAnimaParty() != null) {
                    if (player.getChambersOfAnimaParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ANIMA_CHAMBERS, player.getIndex() * 4, player));
                    }
                }

                player.moveTo(ChambersOfAnimaData.lobbyPosition);

                player.getMovementQueue().setLockMovement(false);

                player.getPacketSender().sendWalkableInterface(156000, false);


            }

            @Override
            public void login(Player player) {
                player.getPacketSender().sendCameraNeutrality();
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.ANIMA_CHAMBERS)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ANIMA_CHAMBERS, player.getPosition().getZ(), player));
                }

                if (player.getChambersOfAnimaParty() != null) {
                    if (player.getChambersOfAnimaParty().getOwner().equals(player)) {
                        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.ANIMA_CHAMBERS, player.getIndex() * 4, player));
                    }
                }

                if (player.getChambersOfAnimaParty() != null)
                    player.getChambersOfAnimaParty().remove(player, true);

                if (player.getChambersOfAnimaParty() != null)
                    player.getChambersOfAnimaParty().getPlayers()
                            .remove(player);

                player.moveTo(ChambersOfAnimaData.lobbyPosition);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendWalkableInterface(156000, false);
            }

            @Override
            public boolean canTeleport(Player player) {
                player.sendMessage("You cannot teleport while in a raid");
                return false;
            }

            @Override
            public void enter(Player player) {
                player.setRegionInstance(new RegionInstance(player, RegionInstanceType.ANIMA_CHAMBERS));
                player.getPacketSender().sendInteractionOption("null", 2, true);
            }

            @Override
            public void onDeath(Player player) {
                if (player.getChambersOfAnimaParty() != null) {
                    ChambersOfAnima.handleDeath(player.getChambersOfAnimaParty(),
                            player);
                }
                player.getPacketSender().sendCameraNeutrality();
                player.getMovementQueue().setLockMovement(false);
            }

            @Override
            public void process(Player player) {
                if (player.getChambersOfAnimaParty() != null)
                    ChambersOfAnima.refreshInterface(player, player.getChambersOfAnimaParty());
            }
        },

        ANIMA_CHAMBERS_LOBBY(new int[]{3242, 3263}, new int[]{2924, 2943}, true, false, true, false, true, false) {
            @Override
            public void leave(Player player) {
                player.getPacketSender().sendCameraNeutrality();

                if (player.getChambersOfAnimaParty() != null)
                    player.getChambersOfAnimaParty().remove(player, true);

                if (player.getChambersOfAnimaParty() != null)
                    player.getChambersOfAnimaParty().getPlayers()
                            .remove(player);

                player.getMovementQueue().setLockMovement(false);
                player.getPacketSender().sendWalkableInterface(156000, false);
            }

            @Override
            public void enter(Player player) {
                for (Player p : World.getPlayers()) {
                    if (p == null)
                        continue;

                    if (!player.equals(p) && (player.getSerialNumber() != null && p.getSerialNumber() != null ?
                            player.getSerialNumber().equals(p.getSerialNumber())
                            : player.getHostAddress().equals(p.getHostAddress()))) {
                        if (player.getMac() != null && p.getMac() != null &&
                                !player.getMac().equals(p.getMac())) {
                            continue;
                        } else {
                            if (p.getLocation().equals(ANIMA_CHAMBERS_LOBBY) && p.getPosition().getZ() == player.getPosition().getZ()) {
                                player.getPacketSender().sendMessage("You already have an account at Chambers of Anima.");
                                player.moveTo(GameSettings.DEFAULT_POSITION);
                                continue;
                            }
                        }
                    }
                }

                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);

                if (player.getChambersOfAnimaParty() == null) {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }

                player.getPacketSender().sendTabInterface(GameSettings.QUESTS_TAB, 111700);
                player.getPacketSender().sendTabInterface(GameSettings.ACHIEVEMENT_TAB, 111700);
                player.getPacketSender().sendConfig(6000, 4);
                player.getPacketSender().sendTab(GameSettings.QUESTS_TAB);
                player.getPacketSender().sendWalkableInterface(156000, false);
            }

            @Override
            public void login(Player player) {
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.INVITE)
                    player.getPacketSender().sendInteractionOption("Invite", 2, false);
                player.getPacketSender().sendWalkableInterface(156000, false);
            }

            @Override
            public void process(Player player) {
                if (player.getChambersOfAnimaParty() != null) {
                    ChambersOfAnima.refreshInterface(player, player.getChambersOfAnimaParty());
                } else {
                    int id = 111716;
                    for (int i = 111716; i < 111737; i++) {
                        player.getPacketSender().sendString(id++, "---");
                        player.getPacketSender().sendString(id++, "--");
                        player.getPacketSender().sendString(id++, "-");
                    }
                    player.getPacketSender().sendString(111709, "Create");
                    player.getPacketSender().sendString(111702, "Raiding Party: @whi@0");
                }
            }
        },
        // Location(int[] x, int[] y, boolean multi, boolean summonAllowed, boolean
        // followingAllowed, boolean cannonAllowed, boolean firemakingAllowed, boolean
        // aidingAllowed) {
        FREIZA(new int[]{2433, 2494}, new int[]{2817, 2878}, false, true, true, false, false, false) {
        },
        // xyyx
        AOE(new int[]{2881, 2949}, new int[]{2820, 2877}, true, true, true, false, false, false) {
        },
        CRYSTALQUEEN(new int[]{2858, 2878}, new int[]{9933, 9959}, true, true, true, false, false, false) {
        },

        // xyyx
        JOKER_LAND(new int[]{1798, 1818}, new int[]{3201, 3221}, true, true, true, false, false, false) {
        },
        // xyyx
        TRANSFORMER(new int[]{3274, 3311}, new int[]{3013, 3036}, true, true, true, false, false, false) {
        },
        MAGEBANK_SAFE(new int[]{2525, 2550}, new int[]{4707, 4727}, true, true, true, false, false, false) {

        },
        ZULRAH(new int[]{3395, 3453}, new int[]{2751, 2785}, false, false, true, false, false, false) {
            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.ZULRAH)) {
                    player.getRegionInstance().destruct();
                }
                player.getPacketSender().sendCameraNeutrality();
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
            }

            @Override
            public void enter(Player player) {
                Zulrah.enter(player);
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
            }

            @Override
            public void onDeath(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.ZULRAH)) {
                    player.getRegionInstance().destruct();
                }
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                killer.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
                return false;
            }

            @Override
            public void logout(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.ZULRAH)) {
                    player.getRegionInstance().destruct();
                }
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
                player.moveTo(GameSettings.DEFAULT_POSITION);
            }

            @Override
            public void login(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.ZULRAH)) {
                    player.getRegionInstance().destruct();
                }
                player.getMinigameAttributes().getZulrahAttributes().setRedFormDamage(0, false);
                player.moveTo(GameSettings.DEFAULT_POSITION);
            }

        },

        // xyyx
        LORDS(new int[]{3349, 3379}, new int[]{9625, 9654}, true, true, true, false, false, false) {
        },
        DOOM(new int[]{2302, 2369}, new int[]{5182, 5250}, true, true, true, false, false, false) {
        },
        // xyyx
        PENGUINZONE(new int[]{3027, 3072}, new int[]{9533, 9558}, true, true, true, false, false, false) {
        },

        // 3340 3351
        // 3364 3333
        GALVEKMULTI(new int[]{3340, 3364}, new int[]{3333, 3351}, true, true, true, false, false, false) {
        },
        BULWARK(new int[]{2409, 2439}, new int[]{3512, 3532}, true, true, true, false, false, false) {
        },
        INSTANCE1(new int[]{2628, 2685}, new int[]{4750, 4790}, true, true, true, false, false, false) {
            @Override
            public void logout(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE1, player.getIndex() * 4, player));
                }
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE1, player.getIndex() * 4, player));
                }
                player.getLastRunRecovery().reset();
            }

            @Override
            public void login(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE1, player.getIndex() * 4, player));
                }
                player.moveTo(GameSettings.DEFAULT_POSITION.copy());
            }

            @Override
            public void onDeath(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE1, player.getIndex() * 4, player));
                }
            }
        },
        INSTANCE2(new int[]{2753, 2814}, new int[]{4736, 4800}, true, true, true, false, false, false) {
            @Override
            public void logout(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE2, player.getIndex() * 4, player));
                player.moveTo(GameSettings.DEFAULT_POSITION.copy());
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE2, player.getIndex() * 4, player));
                player.getLastRunRecovery().reset();
            }

            @Override
            public void login(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE2, player.getIndex() * 4, player));
                player.moveTo(GameSettings.DEFAULT_POSITION.copy());
            }

            @Override
            public void onDeath(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.INSTANCE)) {
                    player.getInstanceManager().onLogout();
                }
                World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.INSTANCE2, player.getIndex() * 4, player));
            }
        },
        ZONES1(new int[]{2948, 3007}, new int[]{9473, 9510}, true, true, true, false, false, false) {
        },
        ZONES2(new int[]{2295, 2410}, new int[]{3876, 3906}, true, true, true, false, false, false) {
        },
        ZONES3(new int[]{1665, 1725}, new int[]{5590, 5611}, true, true, true, false, false, false) {
        },
        ZONES4(new int[]{1597, 1662}, new int[]{5587, 5621}, true, true, true, false, false, false) {
        },
        ZONES5(new int[]{3278, 3334}, new int[]{3293, 3325}, true, true, true, false, false, false) {
        },//2372, 5113, 2427, 5057
        SUPREME(new int[]{2372, 2427}, new int[]{5057, 5113}, true, true, true, false, false, false) {
        },
        ZONES6(new int[]{3278, 3319}, new int[]{3270, 3325}, true, true, true, false, false, false) {
        },
        ZONES7(new int[]{2975, 3018}, new int[]{3100, 3135}, true, true, true, false, false, false) {
        },
        ETERNAL(new int[]{2063, 2098}, new int[]{3221, 3240}, true, true, true, true, false, true) {
        },
        // Location(int[] x, int[] y, boolean multi, boolean summonAllowed, boolean
        // followingAllowed, boolean cannonAllowed, boolean firemakingAllowed, boolean
        // aidingAllowed) {
        GODWARSPLATFORM(new int[]{2506, 2550}, new int[]{2627, 2677}, true, true, true, true, false, true) {
        },
        CUSTOMINI(new int[]{2567, 2597}, new int[]{2564, 2600}, true, true, true, true, false, false) {
        },
        // xyyx
        EVENTBOSS(new int[]{2857, 2879}, new int[]{2858, 2879}, true, true, true, true, false, false) {
        },
        TRAININZONEMULTI(new int[]{2504, 2551}, new int[]{2502, 2562}, true, true, true, false, false, false) {
        },
        SUPREMEE(new int[]{2504, 2551}, new int[]{2502, 2562}, true, true, true, false, false, false) {
        },


        ICE_DEMON(new int[]{2752, 2777}, new int[]{9179, 9211}, false, true, true, false, false, false) {
        },
        PREDATOR(new int[]{2898, 2925}, new int[]{3600, 3626}, false, true, true, false, false, false) {
        },
        CYANTRIX(new int[]{2381, 2409}, new int[]{3480, 3496}, false, true, true, false, false, false) {
        },
        LUCIFER(new int[]{1933, 1974}, new int[]{5130, 5169}, false, true, true, false, false, false) {
        },


        FRIEZA(new int[]{2501, 2533}, new int[]{3017, 3052}, false, true, true, false, false, false) {
        },
        PERFECT_CELL(new int[]{2995, 3006}, new int[]{2505, 2520}, false, true, true, false, false, false) {
        },
        SUPER_BUU(new int[]{3331, 3362}, new int[]{3011, 3039}, false, true, true, false, false, false) {
        },
        SCARLET_FALCON(new int[]{3857, 3879}, new int[]{2762, 2784}, false, true, true, false, false, false) {
        },
        HERBAL_ROGUE(new int[]{3032, 3059}, new int[]{2959, 2978}, false, true, true, false, false, false) {
        },


        VASA(new int[]{2891, 2931}, new int[]{2577, 2607}, false, true, true, false, false, false) {
        },
        ELITE_DRAGON(new int[]{2693, 2749}, new int[]{9402, 9467}, false, true, true, false, false, false) {
        },
        DARK_SUPREME(new int[]{3030, 3055}, new int[]{4502, 4530}, false, true, true, false, false, false) {
        },


        KRIL(new int[]{2884, 2944}, new int[]{2637, 2687}, false, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                int total = KillsTracker.getTotalKillsForNpc(10032, player);
                if (total < 75000 && !player.getRights().isDeveloperOnly() && player.getRights() != PlayerRights.YOUTUBER) {
                    player.moveTo(GameSettings.DEFAULT_POSITION);
                }
            }
        },


        TRAINING_MELEE(new int[]{2389, 2407}, new int[]{2837, 2853}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getPointsHandler().getNPCKILLCount() > 5000 && KillsTracker.getTotalKillsForNpc(1023, player) > 500) {
                    player.moveTo(GameSettings.HOME_CORDS);
                    player.sendMessage("This place is for new players with less than 5k npc kills.");
                    return;
                }
            }
        },
        TRAINING_RANGED(new int[]{2393, 2411}, new int[]{2907, 2923}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getPointsHandler().getNPCKILLCount() > 5000 && KillsTracker.getTotalKillsForNpc(1233, player) > 500) {
                    player.moveTo(GameSettings.HOME_CORDS);
                    player.sendMessage("This place is for new players with less than 5k npc kills.");
                    return;
                }
            }
        },
        TRAINING_MAGIC(new int[]{2454, 2472}, new int[]{2906, 2922}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getPointsHandler().getNPCKILLCount() > 5000 && KillsTracker.getTotalKillsForNpc(1234, player) > 500) {
                    player.moveTo(GameSettings.HOME_CORDS);
                    player.sendMessage("This place is for new players with less than 5k npc kills.");
                    return;
                }
            }
        },
        TRAINING_ROCK_CRAB(new int[]{2688, 2747}, new int[]{4719, 4798}, true, true, true, false, false, false) {
            @Override
            public void enter(Player player) {
                if (player.getPointsHandler().getNPCKILLCount() > 5000 && KillsTracker.getTotalKillsForNpc(1265, player) > 500) {
                    player.moveTo(GameSettings.HOME_CORDS);
                    player.sendMessage("This place is for new players with less than 5k npc kills.");
                    return;
                }
            }
        },
        XMASEVENT2016(new int[]{2747, 2821}, new int[]{3707, 3877}, false, true, true, false, true, true) {
            @Override
            public void process(Player player) {
                if (player.getWalkableInterfaceId() != 11877) {
                    player.getPacketSender().sendWalkableInterface(11877, true);
                }
            }

        },

        DUNGEONEERING(new int[]{3433, 3459, 2421, 2499}, new int[]{3694, 3729, 4915, 4990}, true, false, true,
                false, true, false) {
            @Override
            public void login(Player player) {
                player.getPacketSender().sendDungeoneeringTabIcon(true).sendTabInterface(GameSettings.QUESTS_TAB, 27224)
                        .sendTab(GameSettings.QUESTS_TAB);
            }

            @Override
            public void leave(Player player) {
                Dungeoneering.leave(player, true, true);
            }

            @Override
            public void enter(Player player) {
                player.getPacketSender().sendDungeoneeringTabIcon(true).sendTabInterface(GameSettings.QUESTS_TAB, 27224)
                        .sendTab(GameSettings.QUESTS_TAB);
                if (player.isInDung() == false) {
                    DialogueManager.start(player, 104);
                }
            }

            @Override
            public void onDeath(Player player) {

                Dungeoneering.handlePlayerDeath(player);
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                if (Dungeoneering.doingDungeoneering(killer)) {
                    Dungeoneering.handleNpcDeath(killer, npc);
                    return true;
                }
                return false;
            }

            @Override
            public void process(Player player) {
                if (Dungeoneering.doingDungeoneering(player)) {
                    if (player.getWalkableInterfaceId() != 37500) {
                        player.getPacketSender().sendWalkableInterface(37500, true);
                    }
                } else if (player.getWalkableInterfaceId() == 37500) {
                    player.getPacketSender().sendWalkableInterface(37500, false);
                }
            }
        },
        // Location(int[] x, int[] y, boolean multi, boolean summonAllowed, boolean
        // followingAllowed, boolean cannonAllowed, boolean firemakingAllowed, boolean
        // aidingAllowed) {
        ZULRAH_WAITING(new int[]{3401, 3414}, new int[]{2789, 2801}, false, true, true, false, true, true) {
            @Override
            public void enter(Player player) {
                if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < player.getSkillManager()
                        .getMaxLevel(Skill.CONSTITUTION)) {
                    player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                            player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                    player.getPacketSender().sendMessage("The astounding power of the old pillars heals you.");
                }
                if (player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager()
                        .getMaxLevel(Skill.PRAYER)) {
                    player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            player.getSkillManager().getMaxLevel(Skill.PRAYER));
                    player.getPacketSender().sendMessage("The mystique aura of the pillars restores your prayer.");
                }
            }

            @Override
            public void leave(Player player) {
                if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < player.getSkillManager()
                        .getMaxLevel(Skill.CONSTITUTION)) {
                    player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                            player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                    player.getPacketSender().sendMessage("The astounding power of the old pillars heals you.");
                }
                if (player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager()
                        .getMaxLevel(Skill.PRAYER)) {
                    player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            player.getSkillManager().getMaxLevel(Skill.PRAYER));
                    player.getPacketSender().sendMessage("The mystique aura of the pillars restores your prayer.");
                }
            }
        },
        JAIL(new int[]{2505, 2535}, new int[]{9310, 9330}, false, false, false, false, false, false) {
            @Override
            public boolean canTeleport(Player player) {
                if (player.getRights().isStaff()) {
                    player.getPacketSender().sendMessage("Staff can leave at any time.");
                    return true;
                }
                return !Jail.isJailed(player.getUsername());
            }
        },
        MEMBER_ZONE(new int[]{3415, 3435}, new int[]{2900, 2926}, false, true, true, false, false, true) {
        },
        HOME_BANK(new int[]{2635, 2675}, new int[]{3965, 4031}, false, true, true, false, true, true) {
            @Override
            public void enter(Player player) {
                if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < player.getSkillManager()
                        .getMaxLevel(Skill.CONSTITUTION)) {
                    player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                            player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                    player.getPacketSender()
                            .sendMessage("As you enter the home bank, your health regenerates to full.");
                }
                if (player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager()
                        .getMaxLevel(Skill.PRAYER)) {
                    player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            player.getSkillManager().getMaxLevel(Skill.PRAYER));
                    player.getPacketSender().sendMessage("As you enter the home bank, the gods restore your prayer.");
                }
            }

            @Override
            public void leave(Player player) {
                if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < player.getSkillManager()
                        .getMaxLevel(Skill.CONSTITUTION)) {
                    player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                            player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                    player.getPacketSender()
                            .sendMessage("As you leave the home bank, your health regenerates to full.");
                }
                if (player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager()
                        .getMaxLevel(Skill.PRAYER)) {
                    player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            player.getSkillManager().getMaxLevel(Skill.PRAYER));
                    player.getPacketSender().sendMessage("As you leave the home bank, the gods restore your prayer.");
                }
            }
        },
        NEW_MEMBER_ZONE(new int[]{2792, 2877}, new int[]{3319, 3396}, false, true, true, false, true, true) {
            @Override
            public void process(Player player) {
                if (!player.getRights().isMember() && !player.newPlayer()) {
                    // player.getPacketSender().sendMessage("This area is for Members only.");
                    // player.moveTo(GameSettings.HOME_CORDS);
                }
            }

            @Override
            public void enter(Player player) {
                if (player.getSkillManager().getCurrentLevel(Skill.CONSTITUTION) < player.getSkillManager()
                        .getMaxLevel(Skill.CONSTITUTION)) {
                    player.getSkillManager().setCurrentLevel(Skill.CONSTITUTION,
                            player.getSkillManager().getMaxLevel(Skill.CONSTITUTION));
                    player.getPacketSender()
                            .sendMessage("As you enter the Member Zone, your health regenerates to full.");
                }
                if (player.getSkillManager().getCurrentLevel(Skill.PRAYER) < player.getSkillManager()
                        .getMaxLevel(Skill.PRAYER)) {
                    player.getSkillManager().setCurrentLevel(Skill.PRAYER,
                            player.getSkillManager().getMaxLevel(Skill.PRAYER));
                    player.getPacketSender().sendMessage("As you enter the Member Zone, the gods restore your prayer.");
                }
            }
        },
        TRIO_ZONE(new int[]{3008, 3039}, new int[]{5216, 5247}, false, true, false, false, false, false) {
        },
        // xyyx
        GAMBLE(new int[]{2844, 2867}, new int[]{2696, 2720}, false, true, true, false, true, true) {
        },
        VARROCK(new int[]{3167, 3272}, new int[]{3263, 3504}, false, true, true, true, true, true) {
        },
        /*
         * BANK(new int[]{3090, 3099, 3089, 3090, 3248, 3258, 3179, 3191, 2944, 2948,
         * 2942, 2948, 2944, 2950, 3008, 3019, 3017, 3022, 3203, 3213, 3212, 3215, 3215,
         * 3220, 3220, 3227, 3227, 3230, 3226, 3228, 3227, 3229}, new int[]{3487, 3500,
         * 3492, 3498, 3413, 3428, 3432, 3448, 3365, 3374, 3367, 3374, 3365, 3370, 3352,
         * 3359, 3352, 3357, 3200, 3237, 3200, 3235, 3202, 3235, 3202, 3229, 3208, 3226,
         * 3230, 3211, 3208, 3226}, false, true, true, false, false, true) { },
         */
        // xyyx
        SKELETAL(new int[]{3323, 3357}, new int[]{3162, 3188}, true, true, true, false, false, false) {
        },
        EZONE(new int[]{2763, 2812}, new int[]{3072, 3123}, true, true, true, false, false, false) {
        },
        SZONE(new int[]{1630, 1691}, new int[]{5670, 5728}, true, true, true, false, false, false) {
        },
        SPONSORZONE(new int[]{2944, 3007}, new int[]{2816, 2879}, true, true, true, false, false, false) {
        },
        HULK2(new int[]{3295, 3316}, new int[]{4970, 4990}, true, true, true, false, false, false) {
        },
        EDGEVILLE(new int[]{3073, 3134}, new int[]{3457, 3518}, false, true, true, false, false, true) {
        },
        LUMBRIDGE(new int[]{3175, 3238}, new int[]{3179, 3302}, false, true, true, true, true, true) {
        },
        KING_BLACK_DRAGON(new int[]{2251, 2292}, new int[]{4673, 4717}, true, true, true, true, true, true) {
        },
        SCORPIA(new int[]{2845, 2864}, new int[]{9621, 9649}, true, true, true, true, true, true) {
            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                if (npc.getId() == 109) {
                    Scorpia.killedBaby();
                    return true;
                }
                return false;
            }
        },
        KRAKEN(new int[]{3672, 3690}, new int[]{9875, 9899}, true, true, true, true, true, true) {
            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null
                        && player.getRegionInstance().equals(RegionInstanceType.KRAKEN)) {
                    player.getRegionInstance().destruct();
                }
                player.getPacketSender().sendCameraNeutrality();
            }
        },
        SLASH_BASH(new int[]{2504, 2561}, new int[]{9401, 9473}, true, true, true, true, true, true) {
        },
        BANDOS_AVATAR(new int[]{2340, 2396}, new int[]{4929, 4985}, true, true, true, true, true, true) {
        },
        KALPHITE_QUEEN(new int[]{3464, 3500}, new int[]{9478, 9523}, true, true, true, true, true, true) {
        },
        PHOENIX(new int[]{2824, 2862}, new int[]{9545, 9594}, true, true, true, true, true, true) {
        },
        // BANDIT_CAMP(new int[]{3020, 3150, 3055, 3195}, new int[]{3684, 3711, 2958,
        // 3003}, true, true, true, true, true, true) {
        // },
        ROCK_CRABS(new int[]{2689, 2727}, new int[]{3691, 3730}, true, true, true, true, true, true) {
        },
        CORPOREAL_BEAST(new int[]{2879, 2962}, new int[]{4368, 4413}, true, true, true, false, true, true) {
            @Override
            public void process(Player player) {
                int x1 = 2889;
                int x2 = 2908;
                int y1 = 4381;
                int y2 = 4403;
                int currentx = player.getPosition().getX();
                int currenty = player.getPosition().getY();

                boolean safe = currentx >= x1 && currentx <= x2 && currenty >= y1 && currenty <= y2;
                if (safe) {
                    // player.getPacketSender().sendMessage("You are safe");
                    player.getPacketSender().sendWalkableInterface(16152, false);// .sendMessage("sendwalkint-1");
                    /*
                     * player.setWalkableInterfaceId(-1);
                     * player.getPacketSender().sendMessage("setwalkint-1");
                     * player.getPacketSender().sendInterfaceRemoval().sendMessage("sendintremoval")
                     * ; player.getPacketSender().sendInterfaceReset().sendMessage("sendintreset");
                     */
                } else {
                    // player.getPacketSender().sendMessage("Get out of the gas!");
                    player.dealDamage(new Hit(Misc.getRandom(15) * 10, Hitmask.DARK_PURPLE, CombatIcon.CANNON));
                    if (player.getWalkableInterfaceId() != 16152) {
                        player.getPacketSender().sendWalkableInterface(16152, true);
                    }
                    // player.setWalkableInterfaceId(16152);
                }
            }

        },
        DAGANNOTH_DUNGEON(new int[]{2886, 2938}, new int[]{4431, 4477}, true, true, true, false, true, true) {
        },
        WILDERNESS(new int[]{2940, 3392, 2986, 3012, 3653, 3720, 3650, 3653, 3150, 3199, 2994, 3041},
                new int[]{3523, 3968, 10338, 10366, 3441, 3538, 3457, 3472, 3796, 3869, 3733, 3790}, false, true,
                true, true, true, true) {


            // -- Contains Any requires something to be there, so if you currently do NOT HAVE an actually allowed item everywhere it will crash

            private final int[] ALLOWED_ITEMS = new int[]{1157, 1165, 1161, 1159, 1163, 5574, 5575, 5576, 10828, 1119, 1125, 1121, 1123, 1127, 3140, 4087, 3751, 1069, 1077, 1071, 1079, 1073, 6128, 6129, 6130, 3753, 1193, 1195, 1197, 1199, 1201, 10589, 6809, 10564, 4131, 1321, 1323, 1325, 1327, 1329, 1331, 1333, 4587, 1291, 1293, 1295, 1297, 1299, 1301, 1303, 1422, 1420, 1424, 1426, 1428, 1430, 1432, 6528, 4153, 1307, 1309, 1311, 1313, 1315, 1318, 1319, 14017, 1009, 1796, 1654, 1656, 1658, 1712, 1710, 1708, 1706, 1731, 1725, 1727, 1729, 1478, 1635, 1637, 1639, 1641, 1643, 2550, 2570, 11118, 11113, 325, 339, 329, 361, 379, 373, 7946, 385, 391, 2442, 2436, 2440, 2444, 3040, 6685, 2452, 3024, 2434, 2446, 882, 884, 886, 888, 890, 892, 11212, 810, 811, 11230, 877, 9140, 9144, 9142, 9143, 9144, 9240, 9241, 9243, 9244, 9242, 9341, 9245, 864, 865, 866, 867, 868, 550, 551, 552, 553, 554, 555, 556, 557, 558, 559, 560, 561, 562, 563, 564, 565, 566, 567, 568, 569, 570, 8007, 8008, 8009, 8010, 8011, 8012, 8013, 8012, 10499, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 1167, 1129, 1095, 1063, 9174, 9177, 9179, 9181, 9183, 9185, 10370, 10372, 10368, 6328, 3749, 2499, 2493, 2487, 2501, 2495, 2489, 2503, 2497, 2491, 1381, 1383, 1385, 1387, 4675, 6914, 6889, 6918, 6916, 6924, 6922, 6920, 4089, 4091, 4093, 4095, 4097, 4099, 4101, 4103, 4105, 4107, 4109, 4111, 4113, 4115, 4117, 14499, 14497, 14501, 7400, 2412, 2413, 2414, 7399, 7398};

            @Override
            public void process(Player player) {
                int x = player.getPosition().getX();
                int y = player.getPosition().getY();
                boolean ghostTown = x >= 3650 && y <= 3538;
				/*for (Item item : player.getEquipment().getItems()) {



					if(item.getId()!=-1&&!Arrays.stream(ALLOWED_ITEMS).anyMatch(i -> i == item.getId())) {
					player.sendMessage("<shad=1>@red@<img=18>Important Alert:");
					player.sendMessage("<shad=1>@red@<img=18>You are bringing one of the items that are not allowed!");
					player.sendMessage("<shad=1>@red@<img=18>Please type ::pvpitems to know what items are allowed!");
					Position[] locations = new Position[] { new Position(2375, 4021, 0), new Position(2375, 4022, 0) };
					Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
					TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
					return;
				}}

for (Item item : player.getInventory().getItems()) {



					if(item.getId()!=-1&&!Arrays.stream(ALLOWED_ITEMS).anyMatch(i -> i == item.getId())) {

					player.sendMessage("<shad=1>@red@<img=18>Important Alert:");
					player.sendMessage("<shad=1>@red@<img=18>You are bringing one of the items that are not allowed!");
					player.sendMessage("<shad=1>@red@<img=18>Please type ::pvpitems to know what items are allowed!");
					Position[] locations = new Position[] { new Position(2375, 4021, 0), new Position(2375, 4022, 0) };
					Position teleportLocation = locations[RandomUtility.exclusiveRandom(0, locations.length)];
					TeleportHandler.teleportPlayer(player, teleportLocation, player.getSpellbook().getTeleportType());
					return;
				}}*/

                if (player.isFlying()) {
                    player.getPacketSender().sendMessage("You cannot fly in the Wilderness.");
                    player.setFlying(false);
                    player.newStance();
                }
                if (player.isGhostWalking()) {
                    player.getPacketSender().sendMessage("You cannot ghost walk in the Wilderness.");
                    player.setGhostWalking(false);
                    player.newStance();
                }
                /*
                 * boolean banditCampA = x >= 3020 && x <= 3150 && y >= 3684 && y <= 3711;
                 * boolean banditCampB = x >= 3055 && x <= 3195 && y >= 2958 && y <= 3003;
                 * if(banditCampA || banditCampB) { }
                 */
                if (ghostTown) {
                    player.setWildernessLevel(60);

                } else {
                    player.setWildernessLevel(((((y > 6400 ? y - 6400 : y) - 3520) / 8) + 1));
                }
                player.getPacketSender().sendString(42023, "" + player.getWildernessLevel());
                // player.getPacketSender().sendString(25355, "Levels:
                // "+CombatFactory.getLevelDifference(player, false) +" -
                // "+CombatFactory.getLevelDifference(player, true));
                BountyHunter.process(player);
            }

            @Override
            public void leave(Player player) {
                player.getPacketSender().sendWalkableInterface(42020, false);
                if (player.getLocation() != this) {
                    player.getPacketSender().sendString(19000,
                            "Combat level: " + player.getSkillManager().getCombatLevel());
                    player.getUpdateFlag().flag(Flag.APPEARANCE);
                }
                PLAYERS_IN_WILD--;
            }

            @Override
            public void enter(Player player) {
                player.getPacketSender().sendInteractionOption("Attack", 2, true);
                player.getPacketSender().sendWalkableInterface(42020, true);
                player.getPacketSender().sendString(19000,
                        "Combat level: " + player.getSkillManager().getCombatLevel());
                player.getUpdateFlag().flag(Flag.APPEARANCE);
                PLAYERS_IN_WILD++;
            }

            @Override
            public boolean canTeleport(Player player) {
                if (Jail.isJailed(player.getUsername())) {
                    player.getPacketSender().sendMessage("That'd be convenient.");
                    return false;
                }
                if (player.getWildernessLevel() > 35) {
                    if (player.getRights() == PlayerRights.MODERATOR || player.getRights() == PlayerRights.ADMINISTRATOR
                            || player.getRights() == PlayerRights.MANAGER

                            || player.getRights() == PlayerRights.DEVELOPER) {
                        player.getPacketSender()
                                .sendMessage("@red@You've teleported out of deep Wilderness, logs have been written.");
                        PlayerLogs.log(player.getUsername(), " teleported out of level " + player.getWildernessLevel()
                                + " wildy. Were in combat? " + player.getCombatBuilder().isBeingAttacked());
                        return true;
                    }
                    //	player.getPacketSender().sendMessage("Teleport spells are blocked in this level of Wilderness.");
                    //	player.getPacketSender()
                    //			.sendMessage("You must be below level 20 of Wilderness to use teleportation spells.");
                    return true;
                }
                return true;
            }

            @Override
            public void login(Player player) {
                player.performGraphic(new Graphic(2000, 8));
            }

            @Override
            public boolean canAttack(Player player, Player target) {
                int combatDifference = CombatFactory.combatLevelDifference(player.getSkillManager().getCombatLevel(),
                        target.getSkillManager().getCombatLevel());
                if (combatDifference > player.getWildernessLevel() + 5
                        || combatDifference > target.getWildernessLevel() + 5) {
                    player.getPacketSender()
                            .sendMessage("Your combat level difference is too great to attack that player here.");
                    player.getMovementQueue().reset();
                    return false;
                }
                if (target.getLocation() != Location.WILDERNESS) {
                    player.getPacketSender()
                            .sendMessage("That player cannot be attacked, because they are not in the Wilderness.");
                    player.getMovementQueue().reset();
                    return false;
                }
                if (Jail.isJailed(player.getUsername())) {
                    player.getPacketSender().sendMessage("You cannot do that right now.");
                    return false;
                }
                if (Jail.isJailed(target.getUsername())) {
                    player.getPacketSender().sendMessage("That player cannot be attacked right now.");
                    return false;
                }
                return true;
            }
        },

        INFERNAL_DEMON(new int[]{2337, 2370}, new int[]{9880, 9920}, true, true, true, false, false, true) {
            @Override
            public void enter(Player player) {

                player.sendMessage("You have entered the Infernal demon lair, proceed at your own risk!");
            }

        },


        INVADING_GAME(new int[]{2216, 2223}, new int[]{4936, 4943}, true, true, true, false, true, true) {
            @Override
            public void process(Player player) {
                if (player.getWalkableInterfaceId() != 21005)
                    player.getPacketSender().sendWalkableInterface(21005, true);
            }
        },
        PEST_CONTROL_GAME(new int[]{2624, 2690}, new int[]{2550, 2620}, true, true, true, false, true, true) {
            @Override
            public void process(Player player) {
                if (player.getWalkableInterfaceId() != 21100) {
                    player.getPacketSender().sendWalkableInterface(21100, true);
                }
            }

            @Override
            public void enter(Player player) {
                player.getPacketSender().sendWalkableInterface(21005, false);
            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender()
                        .sendMessage("Teleport spells are blocked on this island. Wait for the game to finish!");
                return false;
            }

            @Override
            public void leave(Player player) {
                PestControl.leave(player, true);
            }

            @Override
            public void logout(Player player) {
                PestControl.leave(player, true);
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC n) {
                return true;
            }

            @Override
            public void onDeath(Player player) {
                player.moveTo(new Position(2657, 2612, 0));
            }
        },
        PEST_CONTROL_BOAT(new int[]{2660, 2663}, new int[]{2638, 2643}, false, false, false, false, false, true) {
            @Override
            public void process(Player player) {
            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender().sendMessage("You must leave the boat before teleporting.");
                return false;
            }

            @Override
            public void enter(Player player) {
                player.getPacketSender().sendWalkableInterface(21005, true);
            }


            @Override
            public void leave(Player player) {
                player.getPacketSender().sendWalkableInterface(21005, false);
                if (player.getLocation() != PEST_CONTROL_GAME) {
                    PestControl.leave(player, true);
                }
            }

            @Override
            public void logout(Player player) {
                PestControl.leave(player, true);
            }
        },
        WAVE_MINIGAME_LOBBY(new int[]{2413, 2427}, new int[]{4674, 4688}, true, false, false, false, false, true) {
        },
        WAVE_MINIGAME(new int[]{2372, 2399}, new int[]{4691, 4721}, true, false, false, false, false, true) {
            @Override
            public void enter(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.WAVE_MINIGAME)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.WAVE_MINIGAME, player.getIndex() * 4));
                }
                player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.WAVE_MINIGAME));
            }

            @Override
            public void logout(Player player) {
                leave(player);
                player.getWaveMinigame().handleDeath();
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.WAVE_MINIGAME)) {
                    player.getRegionInstance().destruct();
                    World.getNpcs().forEach(n -> n.removeInstancedNpcs(Location.WAVE_MINIGAME, player.getIndex() * 4));
                }
                player.getPacketSender().sendCameraNeutrality();
                player.getWaveMinigame().handleDeath();
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
                player.getWaveMinigame().handleDeath();
            }

            @Override
            public boolean handleKilledNPC(Player player, NPC npc) {
                player.getWaveMinigame().killNPC(npc);
                return false;
            }
        },


        SOULWARS(new int[]{-1, -1}, new int[]{-1, -1}, true, true, true, false, true, true) {
            @Override
            public void process(Player player) {

            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender()
                        .sendMessage("If you wish to leave, you must use the portal in your team's lobby.");
                return false;
            }

            @Override
            public void logout(Player player) {

            }

            @Override
            public void onDeath(Player player) {

            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {

                return false;
            }

        },
        SOULWARS_WAIT(new int[]{-1, -1}, new int[]{-1, -1}, false, false, false, false, false, true) {
            @Override
            public void process(Player player) {
            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender().sendMessage("You must leave the waiting room before being able to teleport.");
                return false;
            }

            @Override
            public void logout(Player player) {
            }
        },

        FIGHT_PITS(new int[]{2370, 2425}, new int[]{5133, 5167}, true, true, true, false, false, true) {
            @Override
            public void process(Player player) {
                if (FightPit.inFightPits(player)) {
                    FightPit.updateGame(player);
                    if (player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK)
                        player.getPacketSender().sendInteractionOption("Attack", 2, true);
                }
            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender().sendMessage(
                        "Teleport spells are blocked here. If you'd like to leave, use the northern exit.");
                return false;
            }

            @Override
            public void logout(Player player) {
                FightPit.removePlayer(player, "leave game");
            }

            @Override
            public void leave(Player player) {
                onDeath(player);
            }

            @Override
            public void onDeath(Player player) {
                if (FightPit.getState(player) != null) {
                    FightPit.removePlayer(player, "death");
                }
            }

            @Override
            public boolean canAttack(Player player, Player target) {
                String state1 = FightPit.getState(player);
                String state2 = FightPit.getState(target);
                return state1 != null && state1.equals("PLAYING") && state2 != null && state2.equals("PLAYING");
            }
        },
        FIGHT_PITS_WAIT_ROOM(new int[]{2393, 2404}, new int[]{5168, 5176}, false, false, false, false, false,
                true) {
            @Override
            public void process(Player player) {
                FightPit.updateWaitingRoom(player);
            }

            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender().sendMessage(
                        "Teleport spells are blocked here. If you'd like to leave, use the northern exit.");
                return false;
            }

            @Override
            public void logout(Player player) {
                FightPit.removePlayer(player, "leave room");
            }

            @Override
            public void leave(Player player) {
                if (player.getLocation() != FIGHT_PITS) {
                    FightPit.removePlayer(player, "leave room");
                }
            }

        },
        GRAVEYARD(new int[]{3485, 3517}, new int[]{3559, 3580}, true, true, false, true, false, false) {
            @Override
            public boolean canTeleport(Player player) {
                if (player.getMinigameAttributes().getGraveyardAttributes().hasEntered()) {
                    player.getPacketSender().sendInterfaceRemoval()
                            .sendMessage("A spell teleports you out of the graveyard.");
                    Graveyard.leave(player);
                    return false;
                }
                return true;
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                return killer.getMinigameAttributes().getGraveyardAttributes().hasEntered()
                        && Graveyard.handleDeath(killer, npc);
            }

            @Override
            public void logout(Player player) {
                if (player.getMinigameAttributes().getGraveyardAttributes().hasEntered()) {
                    Graveyard.leave(player);
                }
            }

            @Override
            public void onDeath(Player player) {
                Graveyard.leave(player);
            }

            @Override
            public void process(Player player) {
            }
        },
        DUEL_ARENA(new int[]{3322, 3394, 3311, 3323, 3331, 3391}, new int[]{3195, 3291, 3223, 3248, 3242, 3260},
                false, false, false, false, false, false) {
            @Override
            public void process(Player player) {
                if (player.getWalkableInterfaceId() != 201)
                    player.getPacketSender().sendWalkableInterface(201, true);
                if (player.getDueling().duelingStatus == 0) {
                    if (player.getPlayerInteractingOption() != PlayerInteractingOption.CHALLENGE)
                        player.getPacketSender().sendInteractionOption("Challenge", 2, false);

                } else if (player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK)
                    player.getPacketSender().sendInteractionOption("Attack", 2, true);
            }

            @Override
            public void enter(Player player) {
                PLAYERS_IN_DUEL_ARENA++;
                player.getPacketSender().sendMessage(
                        "<img=5> <col=996633>Warning! Do not stake items which you are not willing to lose.");
            }

            @Override
            public boolean canTeleport(Player player) {
                if (player.getDueling().duelingStatus == 5) {
                    player.getPacketSender().sendMessage("To forfiet a duel, run to the west and use the trapdoor.");
                    return false;
                }
                return true;
            }

            @Override
            public void logout(Player player) {
                boolean dc = false;
                if (player.getDueling().inDuelScreen && player.getDueling().duelingStatus != 5) {
                    player.getDueling().declineDuel(player.getDueling().duelingWith > 0 ? true : false);
                } else if (player.getDueling().duelingStatus == 5) {
                    if (player.getDueling().duelingWith > -1) {
                        Player duelEnemy = World.getPlayers().get(player.getDueling().duelingWith);
                        if (duelEnemy != null) {
                            duelEnemy.getDueling().duelVictory();
                        } else {
                            dc = true;
                        }
                    }
                }
                player.moveTo(new Position(3368, 3268));
                if (dc) {
                    World.removePlayer(player);
                }
            }

            @Override
            public void leave(Player player) {
                if (player.getDueling().duelingStatus == 5) {
                    onDeath(player);
                }
                PLAYERS_IN_DUEL_ARENA--;
            }

            @Override
            public void onDeath(Player player) {
                if (player.getDueling().duelingStatus == 5) {
                    if (player.getDueling().duelingWith > -1) {
                        Player duelEnemy = World.getPlayers().get(player.getDueling().duelingWith);
                        if (duelEnemy != null) {
                            duelEnemy.getDueling().duelVictory();
                            duelEnemy.getPacketSender().sendMessage("You won the duel! Congratulations!");
                        }
                    }
                    PlayerLogs.log(player.getUsername(), "Has lost their duel.");
                    player.getPacketSender().sendMessage("You've lost the duel.");
                    player.getDueling().arenaStats[1]++;
                    player.getDueling().reset();
                }
                player.moveTo(new Position(3368 + Misc.getRandom(5), 3267 + Misc.getRandom(3)));
                player.getDueling().reset();
            }

            @Override
            public boolean canAttack(Player player, Player target) {
                if (target.getIndex() != player.getDueling().duelingWith) {
                    player.getPacketSender().sendMessage("That player is not your opponent!");
                    return false;
                }
                if (player.getDueling().timer != -1) {
                    player.getPacketSender().sendMessage("You cannot attack yet!");
                    return false;
                }
                return player.getDueling().duelingStatus == 5 && target.getDueling().duelingStatus == 5;
            }
        }, // 3340 3351
        // 3364 3333//ok continue sorry. uay
        // GALVEKMULTI(new int[]{3340, 3364
        GODWARS_DUNGEON(new int[]{2800, 2950, 2858, 2943}, new int[]{5200, 5400, 5180, 5230}, true, true, true,
                false, true, true) {
            @Override
            public void process(Player player) {

                if ((player.getPosition().getX() == 2842 && player.getPosition().getY() == 5308) // ARMADYL
                        || (player.getPosition().getX() == 2876 && player.getPosition().getY() == 5369) // BANDOS
                        || (player.getPosition().getX() == 2936 && player.getPosition().getY() == 5331) // ZAMMY
                        || (player.getPosition().getX() == 2907 && player.getPosition().getY() == 5272)) { // NORTH
                    // EAST,
                    // saradomin
                    player.moveTo(new Position(player.getPosition().getX() - 1, player.getPosition().getY() - 1,
                            player.getPosition().getZ()));
                    player.getMovementQueue().reset();
                }
                if ((player.getPosition().getX() == 2842 && player.getPosition().getY() == 5296) // ARMADYL
                        || (player.getPosition().getX() == 2876 && player.getPosition().getY() == 5351) // BANDOS
                        || (player.getPosition().getX() == 2936 && player.getPosition().getY() == 5318) // ZAMMY
                        || (player.getPosition().getX() == 2907 && player.getPosition().getY() == 5258)) { // saradomin,
                    // SOUTH
                    // EAST
                    player.moveTo(new Position(player.getPosition().getX() - 1, player.getPosition().getY() + 1,
                            player.getPosition().getZ()));
                    player.getMovementQueue().reset();
                }
                if ((player.getPosition().getX() == 2824 && player.getPosition().getY() == 5296) // ARMADYL
                        || (player.getPosition().getX() == 2864 && player.getPosition().getY() == 5351) // BANDOS
                        || (player.getPosition().getX() == 2918 && player.getPosition().getY() == 5318) // ZAMMY
                        || (player.getPosition().getX() == 2895 && player.getPosition().getY() == 5258)) { // saradomin,
                    // SOUTH
                    // WEST
                    player.moveTo(new Position(player.getPosition().getX() + 1, player.getPosition().getY() + 1,
                            player.getPosition().getZ()));
                    player.getMovementQueue().reset();
                }
                if ((player.getPosition().getX() == 2824 && player.getPosition().getY() == 5308) // ARMADYL
                        || (player.getPosition().getX() == 2864 && player.getPosition().getY() == 5369) // BANDOS
                        || (player.getPosition().getX() == 2918 && player.getPosition().getY() == 5331) // ZAMMY
                        || (player.getPosition().getX() == 2895 && player.getPosition().getY() == 5272)) { // saradomin,
                    // NORTH
                    // WEST
                    player.moveTo(new Position(player.getPosition().getX() + 1, player.getPosition().getY() - 1,
                            player.getPosition().getZ()));
                    player.getMovementQueue().reset();
                }

                // if(player.getWalkableInterfaceId() != 16210)
                // player.getPacketSender().sendWalkableInterface(16210);
            }

            @Override
            public boolean canTeleport(Player player) {
                return true;
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }

            @Override
            public void leave(Player p) {
                for (int i = 0; i < p.getMinigameAttributes().getGodwarsDungeonAttributes()
                        .getKillcount().length; i++) {
                    // p.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[i] =
                    // 0;
                    // p.getPacketSender().sendString((16216+i), "0");
                }
                // p.getMinigameAttributes().getGodwarsDungeonAttributes().setAltarDelay(0).setHasEnteredRoom(false);
                // p.getPacketSender().sendMessage("Your Godwars dungeon progress has been
                // reset.");
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC n) {
                int index = -1;
                int npc = n.getId();
                if (npc == 6246 || npc == 6229 || npc == 6230 || npc == 6231) // Armadyl
                    index = 0;
                else if (npc == 102 || npc == 3583 || npc == 115 || npc == 113 || npc == 6273 || npc == 6276
                        || npc == 6277 || npc == 6288) // Bandos
                    index = 1;
                else if (npc == 6258 || npc == 6259 || npc == 6254 || npc == 6255 || npc == 6257 || npc == 6256) // Saradomin
                    index = 2;
                else if (npc == 10216 || npc == 6216 || npc == 1220 || npc == 6007 || npc == 6219 || npc == 6220
                        || npc == 6221 || npc == 49 || npc == 4418) // Zamorak
                    index = 3;
                if (index != -1) {
                    // killer.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[index]++;
                    // killer.getPacketSender().sendString((16216+index),
                    // ""+killer.getMinigameAttributes().getGodwarsDungeonAttributes().getKillcount()[index]);
                }
                return false;
            }
        },
        NOMAD(new int[]{3342, 3377}, new int[]{5839, 5877}, true, true, false, true, false, true) {
            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender().sendMessage(
                        "Teleport spells are blocked here. If you'd like to leave, use the southern exit.");
                return false;
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();
                player.moveTo(new Position(1890, 3177));
                player.restart();
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                if (npc.getId() == 8528) {
                    Nomad.endFight(killer, true);
                    return true;
                }
                return false;
            }
        },
        RECIPE_FOR_DISASTER(new int[]{1885, 1913}, new int[]{5340, 5369}, true, true, false, false, false,
                false) {
            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender()
                        .sendMessage("Teleport spells are blocked here. If you'd like to leave, use a portal.");
                return false;
            }

            @Override
            public boolean handleKilledNPC(Player killer, NPC npc) {
                RecipeForDisaster.handleNPCDeath(killer, npc);
                return true;
            }

            @Override
            public void leave(Player player) {
                if (player.getRegionInstance() != null)
                    player.getRegionInstance().destruct();
                player.moveTo(new Position(3081, 3500));
            }

            @Override
            public void onDeath(Player player) {
                leave(player);
            }
        },
        FREE_FOR_ALL_ARENA(new int[]{2755, 2876}, new int[]{5512, 5627}, true, true, true, false, false, true) {
            @Override
            public boolean canTeleport(Player player) {
                return true;
            }

            @Override
            public void onDeath(Player player) {
                player.moveTo(new Position(2375, 4021));
            }

            @Override
            public boolean canAttack(Player player, Player target) {
                if (target.getLocation() != FREE_FOR_ALL_ARENA) {
                    player.getPacketSender().sendMessage("That player has not entered the dangerous zone yet.");
                    player.getMovementQueue().reset();
                    return false;
                }
                return true;
            }

            @Override
            public void enter(Player player) {
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.ATTACK) {
                    player.getPacketSender().sendInteractionOption("Attack", 2, true);
                }
            }

        },
        FREE_FOR_ALL_WAIT(new int[]{2755, 2876}, new int[]{5507, 5627}, false, false, true, false, false, true) {
            @Override
            public boolean canTeleport(Player player) {
                player.getPacketSender()
                        .sendMessage("Teleport spells are blocked here, if you wish to teleport, use the portal.");
                return false;
            }

            @Override
            public void onDeath(Player player) {
                player.moveTo(new Position(2815, 5511));
            }
        },
        WARRIORS_GUILD(new int[]{2833, 2879}, new int[]{3531, 3559}, false, true, true, false, false, true) {
            @Override
            public void logout(Player player) {
                if (player.getMinigameAttributes().getWarriorsGuildAttributes().enteredTokenRoom()) {
                    player.moveTo(new Position(2844, 3540, 2));
                }
            }

            @Override
            public void leave(Player player) {
                player.getMinigameAttributes().getWarriorsGuildAttributes().setEnteredTokenRoom(false);
            }
        },
        PURO_PURO(new int[]{2556, 2630}, new int[]{4281, 4354}, false, true, true, false, false, true) {
        },
        FLESH_CRAWLERS(new int[]{2033, 2049}, new int[]{5178, 5197}, false, true, true, false, true, true) {
        },
        RUNESPAN(new int[]{2122, 2159}, new int[]{5517, 5556}, false, false, true, true, true, false) {
        },
        DICE_ZONE_MULTI(new int[]{2844, 2867}, new int[]{2696, 2720}, true, true, true, false, false, false) {
        },
        //// xyyx
        HOMEISLAND_MULTI(new int[]{2058, 2972}, new int[]{2504, 2512}, true, true, true, false, false, false) {
        },
        //// xyyx
        EARTHQUAKE(new int[]{2883, 2942}, new int[]{5441, 5498}, true, true, true, false, false, false) {
        },
        EXODENLOC(new int[]{2816, 2879}, new int[]{2816, 2879}, true, true, true, false, false, false) {
        },
        DEFAULT(null, null, false, true, true, true, true, true) {
        };

        Location(int[] x, int[] y, boolean multi, boolean summonAllowed, boolean followingAllowed,
                 boolean cannonAllowed, boolean firemakingAllowed, boolean aidingAllowed) {
            this.x = x;
            this.y = y;
            this.multi = multi;
            this.summonAllowed = summonAllowed;
            this.followingAllowed = followingAllowed;
            this.cannonAllowed = cannonAllowed;
            this.firemakingAllowed = firemakingAllowed;
            this.aidingAllowed = aidingAllowed;
        }

        private int[] x, y;
        private boolean multi;
        private boolean summonAllowed;
        private boolean followingAllowed;
        private boolean cannonAllowed;
        private boolean firemakingAllowed;
        private boolean aidingAllowed;

        public int[] getX() {
            return x;
        }

        public int[] getY() {
            return y;
        }

        /**
         * MB_WYLDYWYRM(new int[]{3052, 3083}, new int[]{3929, 3963}, true, true, true,
         * false, false, false) {}, RC_WYLDYWYRM(new int[]{3294, 3315}, new int[]{3919,
         * 3961}, true, true, true, false, false, false) {}, CA_WYLDYWYRM(new
         * int[]{3214, 3253}, new int[]{3594, 3639}, true, true, true, false, false,
         * false) {}, DR_WYLDYWYRM(new int[]{3266, 3306}, new int[]{3868, 3903}, true,
         * true, true, false, false, false) {},
         */
        public static boolean inMulti(Character gc) {
            int x = gc.getPosition().getX(), y = gc.getPosition().getY(), z = gc.getPosition().getZ();

            if (x >= 2702 && x <= 2743 && y >= 4804 && y <= 4828) // hydra
                return false;

            if (x >= 2471 && x <= 2492 && y >= 5392 && y <= 5417) // onyx
                return false;
            if (x >= 2437 && x <= 2455 && y >= 5386 && y <= 5410) // onyx
                return false;

            if (x >= 2691 && x <= 2710 && y >= 2703 && y <= 2740 && (z % 4 == 1 || z % 4 == 2)) // emerald/ruby left side
                return true;

            if (x >= 2731 && x <= 2749 && y >= 2703 && y <= 2740 && ( z % 4 == 2)) // ruby right side
                return true;


            if (x >= 2990 && x <= 3008 && y >= 2752 && y <= 2777) // crypto
                return true;

            if (x >= 2505 && x <= 2532 && y >= 2499 && y <= 2532) // crypto
                return false;


            if (x >= 2844 && x <= 2867 && y >= 2696 && y <= 2720) // merk boss
                return true;
            if (x >= 2434 && x <= 2496 && y >= 5378 && y <= 5440) // onyx
                return true;
            if (gc.getLocation() == WILDERNESS) {
                if (x >= 3250 && x <= 3302 && y >= 3905 && y <= 3925 || x >= 3020 && x <= 3055 && y >= 3684 && y <= 3711
                        || x >= 3150 && x <= 3195 && y >= 2958 && y <= 3003
                        || x >= 3645 && x <= 3715 && y >= 3454 && y <= 3550
                        || x >= 3150 && x <= 3199 && y >= 3796 && y <= 3869
                        || x >= 2994 && x <= 3041 && y >= 3733 && y <= 3790)
                    return true;
                if (x >= 3336 && x <= 3371 && y >= 3792 && y <= 3819) // zulrah pinnensula
                    return true;


                // wyrm multi handler
                if (x >= 3052 && x <= 3083 && y >= 3929 && y <= 3963 || x >= 3294 && x <= 3315 && y >= 3919 && y <= 3961
                        || x >= 3214 && x <= 3253 && y >= 3594 && y <= 3639
                        || x >= 3266 && x <= 3306 && y >= 3868 && y <= 3903
                        || x >= 3169 && x <= 3221 && y >= 3651 && y <= 3700
                        || x >= 3152 && x <= 3190 && y >= 3776 && y <= 3817)
                    return true;
                // z x1: 3336, x2: 3371, y1: 3819, y2: 3792
            }
            if (x >= 3123 && x <= 3138 && y >= 3392 && y <= 3403)
                return true;

            if (gc.getLocation() == TREASURE_HUNTER)
                return false;

            return gc.getLocation().multi;
        }

        public boolean isSummoningAllowed() {
            return summonAllowed;
        }

        public boolean isFollowingAllowed() {
            return followingAllowed;
        }

        public boolean isCannonAllowed() {
            return cannonAllowed;
        }

        public boolean isFiremakingAllowed() {
            return firemakingAllowed;
        }

        public boolean isAidingAllowed() {
            return aidingAllowed;
        }

        public static Location getLocation(Position position) {
            for (Location location : Location.values()) {
                if (location != Location.DEFAULT)
                    if (inLocation(position, location))
                        return location;
            }
            return Location.DEFAULT;
        }

        public static Location getLocation(Entity gc) {
            for (Location location : Location.values()) {
                if (location != Location.DEFAULT)
                    if (inLocation(gc, location))
                        return location;
            }
            return Location.DEFAULT;
        }

        public static boolean inLocation(Entity gc, Location location) {
            if (location == Location.DEFAULT) {
                if (getLocation(gc) == Location.DEFAULT)
                    return true;
                else
                    return false;
            }
            /*
             * if(gc instanceof Player) { Player p = (Player)gc; if(location ==
             * Location.TRAWLER_GAME) { String state = FishingTrawler.getState(p); return
             * (state != null && state.equals("PLAYING")); } else if(location ==
             * FIGHT_PITS_WAIT_ROOM || location == FIGHT_PITS) { String state =
             * FightPit.getState(p), needed = (location == FIGHT_PITS_WAIT_ROOM) ? "WAITING"
             * : "PLAYING"; return (state != null && state.equals(needed)); } else
             * if(location == Location.SOULWARS) { return (SoulWars.redTeam.contains(p) ||
             * SoulWars.blueTeam.contains(p) && SoulWars.gameRunning); } else if(location ==
             * Location.SOULWARS_WAIT) { return SoulWars.isWithin(SoulWars.BLUE_LOBBY, p) ||
             * SoulWars.isWithin(SoulWars.RED_LOBBY, p); } }
             */
            return inLocation(gc.getPosition().getX(), gc.getPosition().getY(), location);
        }


        public static boolean inLocation(Position position, Location location) {
            return inLocation(position.getX(), position.getY(), location);
        }

        public static boolean inLocation(int absX, int absY, Location location) {
            int checks = location.getX().length - 1;
            for (int i = 0; i <= checks; i += 2) {
                if (absX >= location.getX()[i] && absX <= location.getX()[i + 1]) {
                    if (absY >= location.getY()[i] && absY <= location.getY()[i + 1]) {
                        return true;
                    }
                }
            }
            return false;
        }

        public void process(Player player) {

        }

        public boolean canTeleport(Player player) {
            return true;
        }

        public void login(Player player) {

        }

        public void enter(Player player) {
        }

        public void leave(Player player) {
        }

        public void logout(Player player) {

        }

        public void onDeath(Player player) {

        }

        public boolean handleKilledNPC(Player killer, NPC npc) {
            return false;
        }

        public boolean canAttack(Player player, Player target) {
            return false;
        }

        /**
         * SHOULD AN ENTITY FOLLOW ANOTHER ENTITY NO MATTER THE DISTANCE BETWEEN THEM?
         **/
        public static boolean ignoreFollowDistance(Character character) {
            Location location = character.getLocation();
            return location == Location.RECIPE_FOR_DISASTER
                    || location == Location.NOMAD
                    || location == Location.DEATH_SANCTUM
                    || location == Location.ANIMA_CHAMBERS;
        }
    }

    public static void process(Character gc) {
        Location newLocation = Location.getLocation(gc);
        if (gc.getLocation() == newLocation) {
            if (gc.isPlayer()) {
                Player player = (Player) gc;
                gc.getLocation().process(player);
                if (Location.inMulti(player)) {
                    if (player.getMultiIcon() != 1)
                        player.getPacketSender().sendMultiIcon(1);
                } else if (player.getMultiIcon() == 1)
                    player.getPacketSender().sendMultiIcon(0);
            }
        } else {
            Location prev = gc.getLocation();
            if (gc.isPlayer()) {
                Player player = (Player) gc;
                if (player.getMultiIcon() > 0)
                    player.getPacketSender().sendMultiIcon(0);
                if (player.getWalkableInterfaceId() > 0 && player.getWalkableInterfaceId() != 37400
                        && player.getWalkableInterfaceId() != 50000)
                    player.getPacketSender().sendWalkableInterface(50000, false);
                if (player.getPlayerInteractingOption() != PlayerInteractingOption.NONE)
                    player.getPacketSender().sendInteractionOption("null", 2, true);
            }
            gc.setLocation(newLocation);
            if (gc.isPlayer()) {

                if ((prev == Location.ZOMBIE_LOBBY && newLocation == Location.ZOMBIE)
                        || (prev == Location.ZOMBIE && newLocation == Location.ZOMBIE_LOBBY)
                        ||
                        (prev == Location.DEATH_SANCTUM_LOBBY && newLocation == Location.DEATH_SANCTUM)
                        || (prev == Location.DEATH_SANCTUM && newLocation == Location.DEATH_SANCTUM_LOBBY) ||
                        (prev == Location.ANIMA_CHAMBERS_LOBBY && newLocation == Location.ANIMA_CHAMBERS)
                        || (prev == Location.ANIMA_CHAMBERS && newLocation == Location.ANIMA_CHAMBERS_LOBBY)) {

                } else {
                    if (locationPopulations.containsKey(prev))
                        locationPopulations.put(prev, locationPopulations.get(prev) - 1);
                    else
                        locationPopulations.put(prev, 0);

                    if (!RaidsManager.isMovingFromRaidLocation(prev, newLocation)) {
                        prev.leave((Player) gc);
                    }
                }
                gc.getLocation().enter(((Player) gc));
                if (locationPopulations.containsKey(newLocation))
                    locationPopulations.put(newLocation, locationPopulations.get(newLocation) + 1);
                else
                    locationPopulations.put(newLocation, 1);
            }
        }
    }

    public static boolean goodDistance(Position start, int startSize, Position target, int targetSize, int distance) {
        if (target == null) {
            return false;
        }
        if (start.getZ() != target.getZ()) {
            return false;
        }
        final int deltaX1 = start.getX() - (target.getX() + targetSize - 1) - distance;
        final int deltaX2 = start.getX() + startSize - 1 - target.getX() + distance;
        final int deltaY1 = start.getY() + startSize - 1 - target.getY() + distance;
        final int deltaY2 = start.getY() - (target.getY() + targetSize - 1) - distance;

        boolean correctX = !(deltaX1 > 0) && !(deltaX2 < 0);
        boolean correctY = !(deltaY1 < 0) && !(deltaY2 > 0);
        return correctX && correctY;
    }

    public static boolean goodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
        if (playerX == objectX && playerY == objectY)
            return true;
        for (int i = 0; i <= distance; i++) {
            for (int j = 0; j <= distance; j++) {
                if ((objectX + i) == playerX
                        && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
                    return true;
                } else if ((objectX - i) == playerX
                        && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
                    return true;
                } else if (objectX == playerX
                        && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean goodDistance(Position pos1, Position pos2, int distanceReq) {
        if (pos1.getZ() != pos2.getZ())
            return false;
        return goodDistance(pos1.getX(), pos1.getY(), pos2.getX(), pos2.getY(), distanceReq);
    }

    public static boolean goodDistance(Character entity, Character entity2, int distance) {
        return goodDistance(entity.getPosition(), entity.getSize(), entity2.getPosition(), entity2.getSize(), distance);
    }


    public static int distanceTo(Position position, Position destination, int size) {
        final int x = position.getX();
        final int y = position.getY();
        final int otherX = destination.getX();
        final int otherY = destination.getY();
        int distX, distY;
        if (x < otherX)
            distX = otherX - x;
        else if (x > otherX + size)
            distX = x - (otherX + size);
        else
            distX = 0;
        if (y < otherY)
            distY = otherY - y;
        else if (y > otherY + size)
            distY = y - (otherY + size);
        else
            distY = 0;
        if (distX == distY)
            return distX + 1;
        return distX > distY ? distX : distY;
    }


    public static boolean inside(Position start, int startSize, Position target, int targetSize) {
        if (target == null) {
            return false;
        }
        int distanceX = start.getX() - target.getX();
        int distanceY = start.getY() - target.getY();
        if (distanceX < targetSize && distanceX > -startSize && distanceY < targetSize && distanceY > -startSize) {
            return true;
        }
        return false;
    }
}
