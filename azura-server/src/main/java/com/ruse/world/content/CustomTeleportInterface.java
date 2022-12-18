package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.model.Locations;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.model.container.impl.Shop;
import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.bossfights.impl.Leviathon;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.chambersOfAnima.ChambersOfAnimaRewards;
import com.ruse.world.content.deathsanctum.DeathSanctumRewards;
import com.ruse.world.content.minigames.impl.HallsOfAmmo;
import com.ruse.world.content.minigames.impl.TreasureHunter;
import com.ruse.world.content.progressionzone.ProgressionZone;
import com.ruse.world.content.raids.impl.gods.GodsLoot;
import com.ruse.world.content.seasonpass.SeasonPass;
import com.ruse.world.content.skeletalhorror.Prime;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.*;
import com.world.content.globalBoss.exoden.GoldenOozau;
import com.world.content.globalBoss.hulk.Zamasu;
import com.world.content.globalBoss.iron.Iron;
import com.world.content.globalBoss.onyx.OnyxSpawn;
import com.world.content.globalBoss.zenyte.ZenyteSpawn;
import lombok.AllArgsConstructor;
import lombok.Getter;

public class CustomTeleportInterface {

    private static final int ID_START = 60151;
    public int selectedTab = 0;
    private Player player;
    private int selectedIndex = -1;

    public CustomTeleportInterface(Player player) {
        this.player = player;
    }

    public static void sendDrops(Player player, int npcId) {
        player.getPacketSender().resetItemsOnInterface(60301, 100);
        try {
            NPCDrops drops = NPCDrops.forId(npcId);
            if (drops == null) {
                // System.out.println("Was null");
                return;
            }
            for (int i = 0; i < drops.getDropList().length; i++) {
                player.getPacketSender().sendItemOnInterface(60301, drops.getDropList()[i].getId(), i,
                        drops.getDropList()[i].getMax());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendDrops(Player player, Box[] loot) {
        player.getPacketSender().resetItemsOnInterface(60301, 100);
        try {
            if (loot == null) {
                return;
            }
            for (int i = 0; i < loot.length; i++) {
                player.getPacketSender().sendItemOnInterface(60301, loot[i].getId(), i,
                        loot[i].getMax());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void open() {
        player.getPacketSender().sendInterface(60000);
        if (selectedIndex == -1) {
            selectedIndex = 0;
            player.getPacketSender().sendConfig(2388, 0);
        }
        updateInterface(true);
    }

    private void updateInterface(boolean onClick) {
        if (selectedIndex == -1 && onClick) {
            return;
        }

        int length = 0;
        int interfaceId = 60151;
        if (selectedTab == 0) {
            for (Bosses npc : Bosses.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Bosses.values().length * 18;
        } else if (selectedTab == 1) {
            for (Monsters npc : Monsters.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Monsters.values().length * 18;
        } else if (selectedTab == 2) {
            for (Minigames npc : Minigames.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Minigames.values().length * 18;
        } else if (selectedTab == 3) {
            for (Dungeons npc : Dungeons.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Dungeons.values().length * 18;
        } else if (selectedTab == 4) {
            for (Cities npc : Cities.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Cities.values().length * 18;
        } else if (selectedTab == 5) {
            for (Globals npc : Globals.values()) {
                player.getPacketSender().sendString(interfaceId++, (selectedIndex == npc.ordinal() ? "@whi@" : "") + npc.name);
            }
            length = Globals.values().length * 18;
        }
        if (length <= 229)
            length = 229;
        player.getPacketSender().setScrollBar(60100, length);

        if (!onClick) {
            for (int i = ID_START; i < ID_START + 16; i++) {
                player.getPacketSender().sendString(i, "");
            }
            return;
        } else {
            if (selectedTab == 0) {
                Bosses npc = Bosses.values()[selectedIndex];
                displayInfo(npc.name, npc.npcId, npc.adjustedZoom, npc.location);
                if (npc.loot != null)
                    sendDrops(player, npc.loot);
                else
                    sendDrops(player, npc.npcId);
            } else if (selectedTab == 1) {
                if (Monsters.values().length <= selectedIndex)
                    return;
                Monsters npc = Monsters.values()[selectedIndex];
                displayInfo(npc.name, npc.npcId, npc.adjustedZoom, npc.location);
                sendDrops(player, npc.npcId);
            } else if (selectedTab == 2) {
                if (Minigames.values().length <= selectedIndex)
                    return;
                Minigames npc = Minigames.values()[selectedIndex];
                displayInfo(npc.name, npc.npcId, npc.adjustedZoom, npc.location);
                if (npc.loot != null)
                    sendDrops(player, npc.loot);
                else
                    sendDrops(player, npc.npcId);
            } else if (selectedTab == 3) {
                if (Dungeons.values().length <= selectedIndex)
                    return;
                Dungeons npc = Dungeons.values()[selectedIndex];
                displayInfo( npc.name, npc.npcId, npc.adjustedZoom, npc.location);
                sendDrops(player, npc.npcId);
            } else if (selectedTab == 4) {
                if (Cities.values().length <= selectedIndex)
                    return;
                Cities npc = Cities.values()[selectedIndex];
                displayInfo(npc.name, npc.npcId, npc.adjustedZoom, npc.location);
            } else if (selectedTab == 5) {
                if (Globals.values().length <= selectedIndex)
                    return;
                Globals npc = Globals.values()[selectedIndex];
                String timeLeft = "None";
                if (npc == Globals.GOKU) {
                    timeLeft = "To Spawn:@whi@ " + (!GokuSystem.alive ? GokuSystem.getKillsLeft() + " kills left" : "Alive");
                } else if (npc == Globals.EQ) {
                    timeLeft = "To Spawn:@whi@ " + AfkStallSystem.getLeft() + " steals left";
                } else if (npc == Globals.VBOSS) {
                    timeLeft = "To Spawn:@whi@ " + (VoteBoss.getVoteCount() <= 59
                            ? VoteBoss.getVoteCount() + "/60 Votes"
                            : "Alive");
                } else if (npc == Globals.ZAMASU) {
                    timeLeft = "To Spawn:@whi@ " + (!Zamasu.alive ? Zamasu.getTimeLeft() : "Alive");
                } else if (npc == Globals.OOZAU) {
                    timeLeft = "To Spawn:@whi@ " + (!GoldenOozau.alive ? GoldenOozau.getTimeLeft() : "Alive");
                } else if (npc == Globals.PRIME) {
                    timeLeft = "To Spawn:@whi@ " + (!Prime.alive ? Prime.getTimeLeft() : "Alive");
                } else if (npc == Globals.GUARDIAN) {
                    timeLeft = "To Spawn:@whi@ " + LuniteGuardian.getRemaining();
                }  else if (npc == Globals.HANTO_WARRIOR) {
                    timeLeft = "To Spawn:@whi@ " + HantoWarrior.getRemaining();
                } else if (npc == Globals.IRON) {
                    timeLeft = "To Spawn:@whi@ " + (!Iron.bossAlive ? Iron.getTimeLeft() : "Alive");
                } else if (npc == Globals.ONYX) {
                    timeLeft = "To Spawn:@whi@ " + (!OnyxSpawn.wyrmAlive ? OnyxSpawn.getTimeLeft() : "Alive");
                } else if (npc == Globals.ZENYTE) {
                    timeLeft = "To Spawn:@whi@ " + (!ZenyteSpawn.bossAlive ? ZenyteSpawn.getTimeLeft() : "Alive");
                } else if (npc == Globals.TANZANITE) {
                    timeLeft = "To Spawn:@whi@ " + (!TanzaniteSpawn.bossAlive ? TanzaniteSpawn.getTimeLeft() : "Alive");
                } else if (npc == Globals.HYDRA) {
                    timeLeft = "To Spawn:@whi@ " + (!HydraSpawn.bossAlive ? HydraSpawn.getTimeLeft() : "Alive");
                }
                displayInfo( npc.name, npc.npcId, npc.adjustedZoom, npc.location, timeLeft);
                sendDrops(player, npc.npcId);
            }
        }

    }

    public void displayInfo(String name, int npcId, int adjustedZoom, Locations.Location location) {
        displayInfo( name, npcId, adjustedZoom, location, "None");
    }


    public void displayInfo(String name, int npcId, int adjustedZoom, Locations.Location location, String timeLeft) {
        player.getPacketSender().sendString(60019, name);
        player.getPacketSender().sendString(60024, "Health: @whi@" + Misc.insertCommasToNumber(NpcDefinition.forId(npcId).getHitpoints()));

        player.getPacketSender().sendNpcOnInterface(60023, npcId, adjustedZoom != 0 ? adjustedZoom : 0);

        int playerCount = Locations.locationPopulations.containsKey(location) ? Locations.locationPopulations.get(location) : 0;

        if (location == Locations.Location.DEATH_SANCTUM){
            playerCount += Locations.locationPopulations.containsKey(Locations.Location.DEATH_SANCTUM_LOBBY) ? Locations.locationPopulations.get(Locations.Location.DEATH_SANCTUM_LOBBY) : 0;
        }else  if (location == Locations.Location.ZOMBIE){
            playerCount += Locations.locationPopulations.containsKey(Locations.Location.ZOMBIE_LOBBY) ? Locations.locationPopulations.get(Locations.Location.ZOMBIE_LOBBY) : 0;
        }
        player.getPacketSender().sendString(60025, "");
        if (timeLeft.equalsIgnoreCase("None"))
            player.getPacketSender().sendString(60026, "");
        else
            player.getPacketSender().sendString(60026, timeLeft);
    }

    public void teleport() {
        if (selectedIndex == -1) {
            player.sendMessage("@red@You must choose a teleport first.");
            return;
        }
        if (selectedTab == 0) {
            Bosses npc = Bosses.values()[selectedIndex];

            if (npc == Bosses.LUCIFER) {
                if ((player.isUnlockedLucifers() &&
                        player.getPointsHandler().getMiniLuciferkillcount() >= 5_000) || player.getRights() == PlayerRights.DEVELOPER) {
                    TeleportHandler.teleportPlayer(player, npc.position.copy(), player.getSpellbook().getTeleportType());
                } else {
                    player.sendMessage("You need to have killed 5k Mini Lucifers to go here.");
                }
                return;
            } else if (npc == Bosses.DARK_SUPREME) {
                if (!player.isUnlockedDarkSupreme() && !player.getRights().isDeveloperOnly()) {
                    Item[] requirements = new Item[]{new Item(5011), new Item(12537), new Item(17013)};
                    if (player.getInventory().containsAll(requirements)) {
                        player.getInventory().deleteItemSet(requirements);
                        player.setUnlockedDarkSupreme(true);
                        player.sendMessage("@red@Congratulations, you have unlocked dark supreme's zone!");
                    } else {
                        player.sendMessage("You do not have the requirements to unlock dark supreme's!");
                        player.sendMessage("You need to sacrifice a Light twisted bow, vitur scythe and sang. staff!!");
                        player.sendMessage("@red@Try again with these items in your inventory!");
                    }
                } else {
                    TeleportHandler.teleportPlayer(player, npc.position.copy(), player.getSpellbook().getTeleportType());
                }
            } else if (npc == Bosses.BLOOD_ODIN) {
                if (player.getRights() == PlayerRights.YOUTUBER) {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
                int total = KillsTracker.getTotalKillsForNpc(438, player);

                if (total < 10000 && !player.getRights().isDeveloperOnly()) {
                    player.sendMessage("@red@You need 10k Dark Supreme kills to go here!");
                } else {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
            } else if (npc == Bosses.RAMMERNAUT) {
                if (player.getRights() == PlayerRights.YOUTUBER) {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
                if (!player.isUnlockedRammernaut() && !player.getRights().isDeveloperOnly()) {
                    player.sendMessage("@red@You need to use a Rammernaut Scroll to go here!");
                } else {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
            } else if (npc == Bosses.SILVERHAWK) {
                if (player.getRights() == PlayerRights.YOUTUBER) {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
                int total = KillsTracker.getTotalKillsForNpc(10032, player);

                if (total < 75000 && !player.getRights().isDeveloperOnly()) {
                    player.sendMessage("@red@You need 75k Avianse kills to go here!");
                } else {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
            }  else if (npc == Bosses.LEVIATHON) {
                int total = KillsTracker.getGemstoneKills(player);

                if (total < 30000 && !player.getRights().isDeveloperOnly() && player.getRights() != PlayerRights.YOUTUBER) {
                    player.getPacketSender().sendMessage("You need 30,000 Gemstone Dragon kills. You currently have @red@"
                            + KillsTracker.getGemstoneKills(player) + "@bla@ kills.");
                } else {
                    if (player.getBossFight() != null)
                        player.getBossFight().endFight();

                    player.setBossFight(new Leviathon(player));
                    player.getBossFight().begin();


                }
            } else {
                TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
            }
        } else if (selectedTab == 1) {
            Monsters npc = Monsters.values()[selectedIndex];
            if (npc == Monsters.MINI_LUCIFER) {
                if (!player.isUnlockedLucifers() && !player.getRights().isDeveloperOnly()) {
                    Item[] requirements = new Item[]{new Item(12855, 10_000_000), new Item(20591, 1),
                            new Item(18823, 2), new Item(19888, 2)};
                    if (player.getInventory().containsAll(requirements)) {
                        player.getInventory().deleteItemSet(requirements);
                        player.setUnlockedLucifers(true);
                        player.sendMessage("@red@Congratulations, you have unlocked lucifer's zone!");
                    } else {
                        player.sendMessage("You do not have the requirements to unlock lucifer's!");
                        player.sendMessage("Must sacrifice 10m upgrade tokens, a rage cape, 2 coll rings II, 2 coll necks II!");
                        player.sendMessage("@red@Try again with these items in your inventory!");
                    }
                } else {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                }
            } else if (npc == Monsters.AVIANSE) {
                if (SeasonPass.SEASON == 12 && player.getSeasonPass().isMember() && player.getSeasonPass().getTier() >= 51) {
                    TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
                } else {
                    player.sendMessage("You need to have completed the season 12 gold pass to do this.");
                }
            } else {
                TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
            }
        } else if (selectedTab == 2) {
            Minigames npc = Minigames.values()[selectedIndex];
            if (npc == Minigames.PROGRESSION_ZONE) {
                ProgressionZone.teleport(player);
                return;
            }
            if (npc == Minigames.HOV) {
                player.hov.initArea();
                player.hov.start();
                player.getPacketSender().sendInterfaceRemoval();
                return;
            }
            if (npc == Minigames.ASSASSINS_GUILD) {
                if (!player.getAssassinsGuild().teleport())
                    return;
            }

            if (npc == Minigames.ANIMA_CHAMBERS){
                if (player.getSanctumKeysOpened() < 1000){
                    player.getPacketSender()
                            .sendMessage("You need to have opened at least 1,000 Sanctum keys before doing Chambers of Anima.");
                    return;
                }
            }

            if (npc == Minigames.VOTING_MINIGAME) {
                if (!TeleportHandler.checkReqs(player, null)) {
                    return;
                }
                player.getVotingMinigame().startMinigame();
                return;
            }

            if (npc == Minigames.TREASURE_HUNTER) {
                TeleportHandler.teleportPlayer(player, npc.position.copy().setZ(player.getIndex() * 4), player.getSpellbook().getTeleportType());
                return;
            }
            TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
        } else if (selectedTab == 3) {
            Dungeons npc = Dungeons.values()[selectedIndex];
            TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
        } else if (selectedTab == 4) {
            Cities npc = Cities.values()[selectedIndex];
            TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
        } else if (selectedTab == 5) {
            Globals npc = Globals.values()[selectedIndex];
            if (npc == Globals.ONYX && player.getAmountDonated() < 1000) {
                player.sendMessage("You need to be an Onyx donator to go here.");
                return;
            }
            if (npc == Globals.ZENYTE && player.getAmountDonated() < 5000) {
                player.sendMessage("You need to be a Zenyte donator to go here.");
                return;
            }
            if (npc == Globals.TANZANITE && player.getAmountDonated() < 10000) {
                player.sendMessage("You need to be a Tanzanite donator to go here.");
                return;
            }
            if (npc == Globals.HYDRA && player.getAmountDonated() < 25000) {
                player.sendMessage("You need to be a Hydrix donator to go here.");
                return;
            }
            if (npc == Globals.IRON && !player.getGameMode().isIronman()) {
                player.sendMessage("You need to be an Ironman to go here.");
                return;
            }

            TeleportHandler.teleportPlayer(player, npc.position, player.getSpellbook().getTeleportType());
        }


    }

    public void switchTab(int index) {
        selectedTab = index;
        selectedIndex = 0;
        player.getPacketSender().sendString(60018, getTabName(index));
        player.getPacketSender().sendConfig(2388, index);
        updateInterface(false);
        updateInterface(true);
    }

    private String getTabName(int index) {

        if (index == 1) {
            return "Monsters";
        } else if (index == 2) {
            return "Minigames";
        } else if (index == 3) {
            return "Dungeons";
        } else if (index == 4) {
            return "Cities";
        } else if (index == 5) {
            return "Globals";
        } else {
            return "Bosses";
        }

    }

    public boolean handleButton(int buttonId) {
        // System.out.println("here");
        if (!(buttonId <= -5360 && buttonId >= -5385)) {
            return false;
        }
        int index = buttonId + 5385;


        selectedIndex = index;
        updateInterface(true);

        return true;
    } public enum Bosses {

        FRIEZA(1000, 252, "Frieza", "Melee", "Range/Melee", new Position(2516, 3042, 0), Locations.Location.FRIEZA, 6),
        PERFECT_CELL(1000, 449, "Perfect Cell", "Range", "Melee", new Position(3000, 2511, 0), Locations.Location.PERFECT_CELL, 6),
        SUPER_BUU(1100, 452, "Super Buu", "Magic", "Range", new Position(3342, 3022, 0), Locations.Location.SUPER_BUU, 6),
        ETERNAL(3500, 4972, "Eternal Dragon", "Magic", "Melee/Range", new Position(2075, 3230, 0), Locations.Location.ETERNAL, 10),
        SCARLET(3800, 2949, "Scarlet Falcon", "Melee", "Magic", new Position(3869, 2776, 0), Locations.Location.SCARLET_FALCON, 7),
        HERBAL(4200, 2342, "Herbal Rogue", "Range", "Magic", new Position(3044, 2969, 0), Locations.Location.HERBAL_ROGUE, 7),
        AZURE(3700, 3831, "Azure Beast", "Magic", "Range", new Position(2924, 2842, 0), Locations.Location.AOE, 7),

        JOKER(1000, 185, "Joker", "Melee", "Magic/Range", new Position(1807, 3211, 0), Locations.Location.JOKER_LAND, 7),

        CRYSTAL(1250, 6430, "Crystal Queen", "Melee", "Magic/Range", new Position(2867, 9950, 0), Locations.Location.CRYSTALQUEEN, 7),

        SUPREME(1250, 440, "Supreme Boss", "Melee", "Magic/Range", new Position(2403, 5082, 1000), Locations.Location.SUPREME, 9),

        VASA(3500, 1120, "Vasa Nistirio", "Magic", "Range", new Position(2910, 2593, 0), Locations.Location.VASA, 10),

        ELITE(2000, 8015, "Elite Dragon", "Magic", "Melee/Range", new Position(2719, 9442, 0), Locations.Location.ELITE_DRAGON, 10),
        MEGA(1850, 4540, "Mega Avatar", "Melee", "Range", new Position(2360, 4952, 0), Locations.Location.BANDOS_AVATAR, 11),
        INERNAL(3000, 12810, "Infernal Demon", "Magic/Melee", "Range", new Position(2357, 9904, 0), Locations.Location.INFERNAL_DEMON, 11),
        RAMMERNAUT(1400, 9767, "Rammernaut", "Magic/Melee", "Range", new Position(2326, 9831, 4), Locations.Location.INFERNAL_DEMON, 11),
        LUCIFER(1000, 9012, "Lucifer", "Magic/Melee", "Range", new Position(1950, 5155, 1), Locations.Location.LUCIFER, 12),
        DARK_SUPREME(1400, 438, "Dark Supreme", "Magic/Melee/Range", "All", new Position(3038, 4505, 0), Locations.Location.DARK_SUPREME, 12),
        BLOOD_ODIN(1100, 9813, "Blood Odin", "Magic/Melee/Range", "All", new Position(3023, 5243, 0), Locations.Location.TRIO_ZONE, 13),

        GODS(1200, 10000, "Isle of the Gods", "Melee", "Melee/Range", new Position(2655, 4507, 3), Locations.Location.GODS_LOBBY, GodsLoot.ALL_LOOT, 6),

        //   KRIL(2700, 6203, "K'ril Tsutsaroth", "Melee", "Melee/Range", new Position(2915, 2663, 0), Locations.Location.KRIL, 6),

        LEVIATHON(4500, 28060, "Leviathan", "Melee", "Melee/Range", new Position(2915, 2663, 0), Locations.Location.VORKATH, 6),
        SILVERHAWK(5000, 10030, "Silverhawk", "Melee", "Melee/Range", new Position(2915, 2663, 0), Locations.Location.KRIL, 6),

        ;

        private final int npcId, tier, adjustedZoom;
        private final String name, attack, weakness;
        private final Position position;
        private final Locations.Location location;
        private Box[] loot;

        Bosses(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, int tier) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.tier = tier;
        }

        Bosses(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, Box[] loot, int tier) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.loot = loot;
            this.tier = tier;
        }

    }



    public enum Monsters {


        LION(1250, 1265, "Lunite Lion", "Melee", "Mage", new Position(2733, 4765, 0), Locations.Location.TRAINING_ROCK_CRAB, 1),
        BLOODVELD(1000, 1023, "Dark Bloodveld", "Melee", "Mage", new Position(2397, 2844, 0), Locations.Location.TRAINING_MELEE, 1),
        GARG(2000, 1233, "Rusted Gargoyle", "Range", "Melee", new Position(2401, 2914, 0), Locations.Location.TRAINING_RANGED, 1),
        WITCH(1300, 1234, "Crazy Witch", "Magic", "Range", new Position(2463, 2913, 0), Locations.Location.TRAINING_MAGIC, 1),
        ICE_DEMON(2500, 13747, "Ice Demon", "Melee", "Range/Mage", new Position(2764, 9191, 0), Locations.Location.ICE_DEMON, 2),
        PREDATOR(700, 12343, "Predator", "Magic", "Melee", new Position(2911, 3613, 0), Locations.Location.PREDATOR, 3),
        CYANTRIX(2700, 12886, "Cyantrix", "Melee", "Melee/Range", new Position(2397, 3487, 0), Locations.Location.CYANTRIX, 3),
        BULWARK(2700, 10103, "Bulwark", "Melee", "Range/Mage", new Position(2422, 3523, 0), Locations.Location.BULWARK, 4),
        MINI_LUCIFER(1150, 9011, "Mini Lucifers", "Melee", "Melee/Range", new Position(1950, 5155, 0), Locations.Location.LUCIFER, 5),

        AVIANSE(2500, 10032, "Avianse", "Melee", "Melee/Range", new Position(2900, 4449, 4), Locations.Location.DAGANNOTH_DUNGEON, 6),

        ;


        private final int npcId;
        private final int tier;
        private final int adjustedZoom;
        private final String name, attack, weakness;
        private final Position position;
        private final Locations.Location location;

        Monsters(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, int tier) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.tier = tier;
        }
    }

    public enum Minigames {
        VOTING_MINIGAME(2500, 9839, "Voting Minigame", "Melee", "Any", new Position(2649, 4021, 0), Locations.Location.VOTING_MINIGAME, VotingMinigame.loot),
        PROGRESSION_ZONE(1250, 9001, "Starter Progression", "Melee", "Any", new Position(2649, 4021, 0), Locations.Location.PROGRESSION_ZONES, 1),
        RAIDS2(3500, 585, "Island Raid", "Melee/Magic", "Magic/Range/Melee", new Position(2553, 3717, 0), Locations.Location.ZOMBIE, 10),
        DEATH_SANCTUM(1600, 9881, "Sanctum of Death", "Melee/Magic", "Magic/Range/Melee", new Position(1740, 5236, 0), Locations.Location.DEATH_SANCTUM, DeathSanctumRewards.rare),
        //CASHZONE(1000, 1851, "Key Minigame", "Melee/Range/Magic", "Melee/Range", new Position(2579, 2566, 0), Locations.Location.CUSTOMINI, 4),
      //  CRYPTO_MINIGAME(1000, 4499, "Crypto Minigame", "N/A", "N/A", new Position(2518, 2515, 4), Locations.Location.TRAININZONEMULTI),
        TREASURE_HUNTER(1200, 9818, "Treasure Hunter", "N/A", "N/A", new Position(2015, 5022, 0), Locations.Location.TREASURE_HUNTER, TreasureHunter.loot),
        HOV(2000, 9024, "Halls of Ammo", "N/A", "N/A", new Position(2195, 5037, 0), Locations.Location.HALLS_OF_VALOR, HallsOfAmmo.loot),
        PEST_CONTROL(1200, 3782, "Pest Control", "N/A", "N/A", new Position(2657, 2648, 0), Locations.Location.PEST_CONTROL_BOAT, Shop.ShopManager.getShops().get(2506).getItems()),
        ASSASSINS_GUILD(1200, 10010, "Assassins Guild", "N/A", "N/A", new Position(2589, 5269, 1), Locations.Location.PEST_CONTROL_BOAT, AssassinsGuild.loot),

        SOMETHING(1200, 10019, "Gold Grinder", "N/A", "N/A", new Position(2420, 4681, 0), Locations.Location.WAVE_MINIGAME, new Item[]{new Item(12855, 1000000)}),

        ANIMA_CHAMBERS(1900, 28263, "Chambers of Anima @cya@[NEW]", "Melee/Magic", "Magic/Range/Melee", new Position(3253, 2931, 0),
                Locations.Location.ANIMA_CHAMBERS_LOBBY, ChambersOfAnimaRewards.rare),

        ;


        private int npcId, tier, adjustedZoom;
        private String name, attack, weakness;
        private Position position;
        private Locations.Location location;
        private Box[] loot;

        Minigames(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
        }

        Minigames(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, Box[] loot) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.loot = loot;
        }

        Minigames(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, Item[] loot) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.loot = Misc.convertItemsToBox(loot);
        }

        Minigames(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location, int tier) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
            this.tier = tier;
        }
    }

    public enum Dungeons {

        Betrayed(1500, 1708, "Betrayed Dungeon", "Melee", "Magic", new Position(1886, 5222, 0), Locations.Location.SLAYER_DUNG),
        Damned(1000, 1731, "Cursed Dungeon", "Melee", "Magic", new Position(1886, 5222, 1), Locations.Location.SLAYER_DUNG),
        Gemstone(2000, 9892, "Gemstone Dungeon", "Melee", "Magic", new Position(2273, 5019, 0), Locations.Location.GEMSTONE_DUNG),

        ;
        private int npcId, adjustedZoom;
        private String name, attack, weakness;
        private Position position;
        private Locations.Location location;

        Dungeons(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
        }

    }

    public enum Cities {

        Varrock(1000, 1, "Varrock", "N/A", "N/A", new Position(3210, 3424, 0), Locations.Location.VARROCK),
        LUMBRIDGE(1000, 1, "Lumbridge", "N/A", "N/A", new Position(3222, 3218, 0), Locations.Location.LUMBRIDGE),
        EDGEVILLE(1000, 1, "Edgeville", "N/A", "N/A", new Position(3087, 3495, 0), Locations.Location.EDGEVILLE),
        FALADOR(1000, 1, "Falador", "N/A", "N/A", new Position(2964, 3378, 0), Locations.Location.FALADOR),
        CAMELOT(1000, 1, "Camelot", "N/A", "N/A", new Position(2757, 3477, 0), Locations.Location.CAMELOT),
        ARDOUGNE(1000, 1, "Ardougne", "N/A", "N/A", new Position(2662, 3305, 0), Locations.Location.ARDOUGNE),
        YANILLE(1000, 1, "Yanille", "N/A", "N/A", new Position(2614, 3102, 0), Locations.Location.YANILLE);

        private int npcId, tier, adjustedZoom;
        private String name, attack, weakness;
        private Position position;
        private Locations.Location location;

        Cities(int adjustedZoom, int npcId, String name, String attack, String weakness, Position position, Locations.Location location) {
            this.adjustedZoom = adjustedZoom;
            this.npcId = npcId;
            this.name = name;
            this.attack = attack;
            this.weakness = weakness;
            this.position = position;
            this.location = location;
        }
    }

    @AllArgsConstructor
    @Getter
    public enum Globals {

        GOKU(1000, 187, "Goku", 111215, new Position(2867, 2862, 0), Locations.Location.GOKU),
        EQ(3800, 3779, "Earthquake", 111210, new Position(2014, 4516, 0), Locations.Location.EARTHQUAKE),
        VBOSS(1700, 8013, "Vote Boss", 111211, new Position(2980, 2771, 0), Locations.Location.VBOSS),
        ZAMASU(1000, 8009, "Zamasu", 111212, new Position(3486, 3623, 0), Locations.Location.ZAMASU),
        OOZAU(1900, 12239, "Oozau", 111213, new Position(2581, 4510, 0), Locations.Location.EXODEN),
        PRIME(2300, 3830, "Prime", 111214, new Position(2466, 10156, 0), Locations.Location.PRIME),
        GUARDIAN(1000, 9880, "Lunite Guardian", 111216, new Position(2997, 2761, 1), Locations.Location.GUARDIAN),
        IRON(1400, 9014, "Iron", 111210, new Position(3811, 2839, 0), Locations.Location.IRON),
        ONYX(1000, 3305, "Onyx Panther", 111210, new Position(2462, 5408), Locations.Location.ONYX),
        ZENYTE(2800, 9017, "Zenyte", 111210, new Position(2778, 4834), Locations.Location.ZENYTE),
        TANZANITE(2800, 10014, "Tanzanite", 111210, new Position(2721, 4841, 4), Locations.Location.TANZANITE),

        HYDRA(2800, 28615, "Alchemical Hydra", 111210, new Position(2721, 4841, 4), Locations.Location.TANZANITE),
        HANTO_WARRIOR(1100, 9895, "Hanto Warrior", 111217, new Position(2849, 4571, 0), Locations.Location.HANTO),

        //ARMOURED_BUNNY(2200, 9020, "Armoured Bunny", 111218, new Position(2910, 4704, 0), Locations.Location.EASTER),

        ;

        private int adjustedZoom, npcId;
        private String name;
        private int buttonClick;
        private Position position;
        private Locations.Location location;

    }
}
