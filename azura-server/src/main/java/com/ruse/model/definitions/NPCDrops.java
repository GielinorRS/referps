package com.ruse.model.definitions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.ruse.GameSettings;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.model.container.impl.Bank;
import com.ruse.model.container.impl.Equipment;
import com.ruse.util.JsonLoader;
import com.ruse.util.Misc;
import com.ruse.util.RandomUtility;
import com.ruse.world.World;
import com.ruse.world.content.*;
import com.ruse.world.content.AchievementsOLD.AchievementDataOLD;
import com.ruse.world.content.DropLog.DropLogEntry;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.cluescrolls.CLUESCROLL;
import com.ruse.world.content.combat.CombatBuilder.CombatDamageCache;
import com.ruse.world.content.combat.CombatFactory;
import com.ruse.world.content.combat.effect.EquipmentBonus;
import com.ruse.world.content.minigames.impl.TreasureHunter;
import com.ruse.world.content.minigames.impl.WarriorsGuild;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.impl.prayer.BonesData;
import com.ruse.world.content.skill.impl.summoning.BossPets;
import com.ruse.world.content.skill.impl.summoning.CharmingImp;
import com.ruse.world.content.skill.impl.summoning.Familiar;
import com.ruse.world.content.skill.impl.summoning.LootDevice;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.TanzaniteSpawn;
import lombok.Getter;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.CopyOnWriteArrayList;

//import com.ruse.tools.discord.DiscordConstant;
//import com.ruse.tools.discord.DiscordManager;

/**
 * Controls the npc drops
 *
 * @author 2012 <http://www.rune-server.org/members/dexter+morgan/>, Gabbe &
 * Samy
 */
public class NPCDrops {

    public static final HashSet<Integer> multiKillNpcs = new HashSet<Integer>(Arrays.asList(6222, 6260, 6203, 6247,
            8133, 13447, 4972, 3334, 2949, 2342, 3831, 185, 6430));

    public static final HashSet<Integer> globalNpcs = new HashSet<Integer>(Arrays.asList(
            3779, 8013, 8009, 12239, 3830, 187, 8499, 9871, 9880, 9010, 9895,
            6142, 9020, 10014, 10022, 28615
    ));
    /**
     * The map containing all the npc drops.
     */
    private static Map<Integer, NPCDrops> dropControllers = new HashMap<Integer, NPCDrops>();
    /**
     * The id's of the NPC's that "owns" this class.
     */
    private int[] npcIds;

    /**
     * All the drops that belongs to this class.
     */
    private NpcDropItem[] drops;

    public static JsonLoader parseDrops() {

        ItemDropAnnouncer.init();

        return new JsonLoader() {

            @Override
            public void load(JsonObject reader, Gson builder) {
                int[] npcIds = builder.fromJson(reader.get("npcIds"), int[].class);
                NpcDropItem[] drops = builder.fromJson(reader.get("drops"), NpcDropItem[].class);

                NPCDrops d = new NPCDrops();
                d.npcIds = npcIds;
                d.drops = drops;
                for (int id : npcIds) {
                    dropControllers.put(id, d);
                }
            }

            @Override
            public String filePath() {
                return "./data/def/json/drops.json";
            }
        };


    }

    /**
     * Gets the NPC drop controller by an id.
     *
     * @return The NPC drops associated with this id.
     */
    public static NPCDrops forId(int id) {
        return dropControllers.get(id);
    }

    public static Map<Integer, NPCDrops> getDrops() {
        return dropControllers;
    }

    private static void resetInterface(Player player) {
        for (int i = 8145; i < 8196; i++)
            player.getPacketSender().sendString(i, "");
        for (int i = 12174; i < 12224; i++)
            player.getPacketSender().sendString(i, "");
        player.getPacketSender().sendString(8136, "Close window");
    }

    public static void sendDropTableInterface(Player player, int npcId) {
        try {

            NPCDrops drops = NPCDrops.forId(npcId);

            if (drops == null || drops.getDropList() == null) {
                player.getPacketSender().sendMessage("That NPC has no drop table. Error 951, " + npcId + ".");
                return;
            }

            if (NpcDefinition.forId(npcId) == null || NpcDefinition.forId(npcId).getName() == null) {
                player.getPacketSender().sendMessage("Error 952, " + npcId + ".");
                return;
            }

            resetInterface(player);

            player.getPacketSender().sendString(8144, NpcDefinition.forId(npcId).getName() + " drop table")
                    .sendInterface(8134);

            int index = 0, start = 8147, cap = 8196, secondstart = 12174, secondcap = 12224, index2 = 0;
            boolean newline = false;

            if (NpcDefinition.forId(npcId).getCombatLevel() >= 70) {
                player.getPacketSender().sendString(8147, "1x @blu@Clue scroll@bla@.");
                index++;
            }

            for (int i = 0; i < drops.getDropList().length; i++) {
                final int dropChance = drops.getDropList()[i].getChance();

                if (drops.getDropList()[i].getItem().getId() <= 0
                        || drops.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems()
                        || drops.getDropList()[i].getItem().getAmount() <= 0) {
                    continue;
                }

                if (ItemDefinition.forId(drops.getDropList()[i].getItem().getId()) == null
                        || ItemDefinition.forId(drops.getDropList()[i].getItem().getId()).getName() == null) {
                    continue;
                }

                int toSend = 8147 + index;

                if (index + start > cap) {
                    newline = true;
                }

                if (newline) {
                    toSend = secondstart + index2;
                }

                if (newline && toSend >= secondcap) {
                    player.getPacketSender().sendMessage("<shad=ffffff>" + drops.getDropList()[i].getItem().getAmount()
                            + "x <shad=0>"
                            + Misc.getColorBasedOnValue(drops.getDropList()[i].getItem().getDefinition().getValue()
                            * drops.getDropList()[i].getItem().getAmount())
                            + drops.getDropList()[i].getItem().getDefinition().getName() + "<shad=-1>@bla@"
                            + " at a drop rate of 1/"
                            + dropChance
                            + "<shad=ffffff>.");
                    continue;
                }

                player.getPacketSender().sendString(toSend, drops.getDropList()[i].getItem().getAmount() + "x "
                        + Misc.getColorBasedOnValue(drops.getDropList()[i].getItem().getDefinition().getValue()
                        * drops.getDropList()[i].getItem().getAmount())
                        + drops.getDropList()[i].getItem().getDefinition().getName() + "@bla@" + " at a drop rate of 1/"
                        + dropChance
                        + ".");
                if (newline) {
                    index2++;
                } else {
                    index++;
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void getDropTable(Player p, int npcId) {
        NPCDrops drops = NPCDrops.forId(npcId);
        if (drops == null) {
            p.getPacketSender().sendMessage("No drops were found. [Error 194510]");
            return;
        }

        if (NpcDefinition.forId(npcId).getCombatLevel() >= 70) {
            // CLUESCROLL
        }
        if (GameSettings.Halloween) {
            // Scythe, Pumpkin
        }
        if (GameSettings.Christmas2016) {
            // sled
        }

        //
        for (int i = 0; i < drops.getDropList().length; i++) {
            if (drops.getDropList()[i].getItem().getId() <= 0
                    || drops.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems()
                    || drops.getDropList()[i].getItem().getAmount() <= 0) {
                continue;
            }

            final int dropChance = drops.getDropList()[i].getChance();
            p.getPacketSender().sendMessage(drops.getDropList()[i].getItem().getAmount() + "x "
                    + ItemDefinition.forId(drops.getDropList()[i].getItem().getId()).getName() + " at a chance of 1/"
                    + dropChance + ".");// +drops.getDropList()[i].getChance());
        }
        //

    }


    public static void dropItemsMultiKill(NPC npc) {
        if (npc.getCombatBuilder().getDamageMap().size() == 0) {
            return;
        }
        Map<Player, Long> killers = new HashMap<>();

        for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {
            if (entry == null) {
                continue;
            }

            long timeout = entry.getValue().getStopwatch().elapsed();
            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                continue;
            }

            Player player = entry.getKey();
            if (player.getConstitution() <= 0 || !player.isRegistered()) {
                continue;
            }

            killers.put(player, entry.getValue().getDamage());
        }

        npc.getCombatBuilder().getDamageMap().clear();

        List<Entry<Player, Long>> result = sortEntries(killers);
        for (Iterator<Entry<Player, Long>> iterator = result.iterator(); iterator.hasNext(); ) {
            Entry<Player, Long> entry = iterator.next();
            Player killer = entry.getKey();
            NPCDrops.dropItems(killer, npc);
            iterator.remove();
        }
    }

    public static void globalBossDrop(NPC npc) {
        if (npc.getCombatBuilder().getDamageMap().isEmpty()) {
            return;
        }

        Map<Player, Long> killers = new HashMap<>();

        for (Entry<Player, CombatDamageCache> entry : npc.getCombatBuilder().getDamageMap().entrySet()) {

            if (entry == null) {
                continue;
            }

            long timeout = entry.getValue().getStopwatch().elapsed();

            if (timeout > CombatFactory.DAMAGE_CACHE_TIMEOUT) {
                continue;
            }
            Player player = entry.getKey();

            if (player.getConstitution() <= 0 || !player.isRegistered()) {
                continue;
            }
            killers.put(player, entry.getValue().getDamage());
        }

        List<Entry<Player, Long>> result = sortEntries(killers);

        for (Entry<Player, Long> entry : result) {

            Player killer = entry.getKey();
            long damage = entry.getValue();
            KillsTracker.submitById(killer, npc.getId(), true, npc.getDefinition().boss);
            KillsTracker.submitById(killer, npc.getId(), false, npc.getDefinition().boss);

            dropItems(killer, npc, true);

            if (npc.getId() == TanzaniteSpawn.NPC_ID) {
                killer.getDailyTaskManager().submitProgressToIdentifier(41, 1);
            }
            StarterTasks.doProgress(killer, StarterTasks.StarterTask.KILL_GLOBALS);
            killer.getDailyTaskManager().submitProgressToIdentifier(16, 1);

            if (npc.getId() == 9895) {
                killer.setHantoDR(0);
            }
        }
    }

    static <K, V extends Comparable<? super V>> List<Entry<K, V>> sortEntries(Map<K, V> map) {

        List<Entry<K, V>> sortedEntries = new ArrayList<Entry<K, V>>(map.entrySet());

        Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return sortedEntries;

    }

    //a lot
    public static int dropRateBoost(Player p) {
        int percentBoost = 0;

        if (p.getUsername().equalsIgnoreCase("nucky")
                || p.getUsername().equalsIgnoreCase("test"))
            percentBoost = 0;

        if (p.getGameMode() == GameMode.IRONMAN) {
            percentBoost += 10;
        }
        if (p.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
            percentBoost += 10;
        }
        if (p.getGameMode() == GameMode.GROUP_IRONMAN) {
            percentBoost += 10;
        }
        if (p.getGameMode() == GameMode.EXTREME_MODE) {
            percentBoost += 15;
        }

        if (p.getAmountDonated() >= 25000) {
            percentBoost += 125;
        } else   if (p.getAmountDonated() >= 10000) {
            percentBoost += 100;
        } else if (p.getAmountDonated() >= 5000) {
            percentBoost += 75;
        } else if (p.getAmountDonated() >= 1000) {
            percentBoost += 45;
        } else if (p.getAmountDonated() >= 500) {
            percentBoost += 30;
        } else if (p.getAmountDonated() >= 250) {
            percentBoost += 20;
        } else if (p.getAmountDonated() >= 50) {
            percentBoost += 15;
        } else if (p.getAmountDonated() >= 10) {
            percentBoost += 5;
        }

        if (p.getDoubleDRTimer() > 0) {
            percentBoost += 100;
        }
        if (p.getMinutesVotingDR() > 0) {
            percentBoost += 100;
        }

        Equipment playerEquip = p.getEquipment();
        for (Item item : playerEquip.getItems()){
            percentBoost += CustomDropUtils.getDropRate(item.getId());
        }
       /* for (int[] item : CustomDropUtils.DRITEMS) {
            if (playerEquip.contains(item[0])) {
                percentBoost += item[1];
            }
        }*/
        if (playerEquip.contains(23621)) {
            percentBoost += 5;
        }
        if (playerEquip.contains(23719)) {
            percentBoost += 40;
        }
        if (playerEquip.contains(21613)) {
            percentBoost += 60;
        }
        if (playerEquip.contains(8001)) {
            percentBoost += 10;
        }

        int head = p.getEquipment().get(Equipment.HEAD_SLOT).getId();
        int legs = p.getEquipment().get(Equipment.LEG_SLOT).getId();
        int body = p.getEquipment().get(Equipment.BODY_SLOT).getId();
        int gloves = p.getEquipment().get(Equipment.HANDS_SLOT).getId();
        int boots = p.getEquipment().get(Equipment.FEET_SLOT).getId();

        if (legs == 4686 && body == 4685 && head == 4684) {
            percentBoost += 10;
        }

        if (EquipmentBonus.voidElite(p)) {
            percentBoost += 100;
        }else if (EquipmentBonus.wearingVoid(p)) {
            percentBoost += 50;
        }

        if (head == 14050 && body == 14051 && legs == 14052 && gloves == 14055 && boots == 14053) {
            percentBoost += 10;
        }

        Familiar playerFamiliar = p.getSummoning().getFamiliar();

        if (playerFamiliar != null && playerFamiliar.getSummonNpc() != null) {
            for (int[] pet : CustomDropUtils.DRPETS)
                if (playerFamiliar.getSummonNpc().getId() == pet[0])
                    percentBoost += pet[1];
        }


        if (p.getInventory().contains(23781)) {
            percentBoost *= 1.75;
        } else if (p.getInventory().contains(4440)) {
            percentBoost *= 1.5;
        }
        if (ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.DR) {
            percentBoost *= 1.5;
        }
        if (GameSettings.DOUBLEDR == true) {
            percentBoost *= 2;
        }

        return percentBoost;
    }

    /**
     * Drops items for a player after killing an npc. A player can max receive one
     * item per drop chance.
     *
     * @param p   Player to receive drop.
     * @param npc NPC to receive drop FROM.
     */

    public static void dropItems(Player p, NPC npc) {
        dropItems(p, npc, false);
    }

    public static void dropItems(Player p, NPC npc, boolean globalBoss) {
        if (npc.getLocation() == Location.WARRIORS_GUILD)
            WarriorsGuild.handleDrop(p, npc);

        NPCDrops drops = NPCDrops.forId(npc.getId());
        final Position npcPos = npc.getPosition().copy();
        if (drops == null) {
            if (Misc.inclusiveRandom(1, 7) == 1) {
                drop(p, new Item(CharmingImp.PVM_TICKET, 1), npc, npcPos, false);
            }
            if (Misc.inclusiveRandom(1, 5) == 1) {
                drop(p, new Item(CharmingImp.PVM_TICKET, 1), npc, npcPos, false);
            }
            return;
        }
        final boolean goGlobal = p.getPosition().getZ() >= 0 && p.getPosition().getZ() < 4;
        final boolean ringOfWealth = p.getEquipment().get(Equipment.RING_SLOT).getId() == 2572;

        HashMap<Integer, Boolean> dropsReceived = new HashMap<>();
        boolean goldCharms = false;
        boolean crimsonCharms = false;
        boolean greenCharms = false;
        boolean blueCharms = false;

        if (npc.getDefinition().getCombatLevel() >= 70 && p.getLocation() != Location.GRAVEYARD) {
            dropClue(p, npcPos, npc);
        }

        if (p.getLocation() != Location.GRAVEYARD && GameSettings.Halloween) {
            dropScythe(p, npcPos, npc.getDefinition().getCombatLevel(), npc.getDefinition().getName());
            dropPumpkin(p, npcPos);
        }

        if (p.getLocation() != Location.GRAVEYARD && GameSettings.Christmas2016) {
            dropSled(p, npcPos, npc.getDefinition().getCombatLevel(), npc.getDefinition().getName());
        }

        for (int i = 0; i < drops.getDropList().length; i++) {
            if (drops.getDropList()[i].getItem().getId() <= 0
                    || drops.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems()
                    || drops.getDropList()[i].getItem().getAmount() <= 0) {
                continue;
            }

            if (drops.getDropList()[i].getItem().getId() == CharmingImp.GOLD_CHARM) {
                goldCharms = false;
            }
            if (drops.getDropList()[i].getItem().getId() == CharmingImp.GREEN_CHARM) {
                greenCharms = false;
            }
            if (drops.getDropList()[i].getItem().getId() == CharmingImp.CRIM_CHARM) {
                crimsonCharms = false;
            }
            if (drops.getDropList()[i].getItem().getId() == CharmingImp.BLUE_CHARM) {
                blueCharms = false;
            }

            int dropChance = drops.getDropList()[i].getChance();


            if (npc.getId() == TreasureHunter.NPC_1 || npc.getId() == TreasureHunter.NPC_2
                    || npc.getId() == TreasureHunter.NPC_3 || npc.getId() == TreasureHunter.NPC_4) {
                Familiar playerFamiliar = p.getSummoning().getFamiliar();
                if (playerFamiliar != null && playerFamiliar.getSummonNpc() != null) {
                    int summonNpc = playerFamiliar.getSummonNpc().getId();
                    if (summonNpc == BossPets.BossPet.GOLEM_PET.npcId || summonNpc == BossPets.BossPet.DRAGON_PET.npcId
                            || summonNpc == BossPets.BossPet.DEMON_PET.npcId || summonNpc == BossPets.BossPet.RAMMUS_PET.npcId) {
                        dropChance = 1;
                    }
                }
            }


            int rate = globalBoss ? 0 : dropRateBoost(p);
            if (npc.getId() == 9895) {
                rate = p.getHantoDR();
            }
            if (dropChance == 1) {
                Item toDrop = drops.getDropList()[i].getItem();
                if (toDrop != null) {
                    drop(p, toDrop, npc, npcPos, goGlobal, false);
                }
            } else if (shouldDrop(dropsReceived, drops.getDropList()[i], rate)) {
                Item toDrop = drops.getDropList()[i].getItem();
                if (toDrop != null) {
                    if (npc.getId() >= 14207 && npc.getId() <= 14209) {
                        int total = KillsTracker.getTotalKillsForNpc(14210, p);
                        if (total >= 50000 && toDrop.getId() != 10501 && toDrop.getId() != 1050 && toDrop.getId() != 23382
                                && toDrop.getId() != 23383
                                && toDrop.getId() != 23384) {
                            continue;
                        }
                    }
                    drop(p, toDrop, npc, npcPos, goGlobal, globalBoss ? true : dropChance >= 4000); // drop rate
                    p.getCollectionLogManager().addDrop(npc, toDrop);
                    dropsReceived.put(dropChance, true);
                }
            }
        }


        if (npc.getId() == 8013) {
            if (p.lastVoteTime >= System.currentTimeMillis() - 86400000) {
                p.sendMessage("You received Double rewards because you voted in the last 12 hours!");
            }
        }


        if (goldCharms && Misc.inclusiveRandom(1, 3) == 1) {
            // // System.out.println("GONNA DROP A GO CHARM");
            drop(p, new Item(CharmingImp.GOLD_CHARM, 1), npc, npcPos, false);
        }
        if (greenCharms && Misc.inclusiveRandom(1, 4) == 1) {
            // // System.out.println("GONNA DROP A GR CHARM");
            drop(p, new Item(CharmingImp.GREEN_CHARM, 1), npc, npcPos, false);
        }
        if (crimsonCharms && Misc.inclusiveRandom(1, 5) == 1) {
            // // System.out.println("GONNA DROP A CR CHARM");
            drop(p, new Item(CharmingImp.CRIM_CHARM, 1), npc, npcPos, false);
        }
        if (blueCharms && Misc.inclusiveRandom(1, 6) == 1) {
            // // System.out.println("GONNA DROP A BL CHARM");
            drop(p, new Item(CharmingImp.BLUE_CHARM, 1), npc, npcPos, false);
        }
        if (Misc.getRandom(49) == 0) {
            NPCDrops.instanceTokenDrop(p, npc);
        }

        if (Misc.getRandom(9) == 0) {
            drop(p, new Item(19000, 1), npc, npcPos, false);
        }

      /*  if (Misc.getRandom(50) == 0) {
            drop(p, new Item(23104, 1), npc, npcPos, false);
        }*/
        /*if (Misc.getRandom(9) == 0) {
            drop(p, new Item(23003, 1), npc, npcPos, false);
        }*/

    }

    //let me get it for you quickly
    public static int enumenicalCount(Player p) { // yo what are the item ids, for the d
        int count = 0;
        for (int i = 0; i < p.getBanks().length; i++) {
            if (p.getBanks()[i].contains(22053)) {
                count = count + p.getBanks()[i].getAmount(22053);
            }
        }
        if (p.getInventory().contains(22053)) {
            count = count + p.getInventory().getAmount(22053);
        }
        return count;
    }

    public static boolean shouldDrop(HashMap<Integer, Boolean> b, NpcDropItem npcDropItem, int dropRateBoost) {
        int chance = npcDropItem.getChance();
        double drBoost = (double) (100 + dropRateBoost) / 100D;

        if (chance >= 1000)
            chance *= 1.075;

        if (npcDropItem.getId() == 10942 ||
                npcDropItem.getId() == 10946 ||
                npcDropItem.getId() == 6769) {
            chance *= 1.5;
        }

        if (npcDropItem.getId() == 15358 ||
                npcDropItem.getId() == 15359 ||
                npcDropItem.getId() == 20489) {
            chance *= 1.175;
        }

        if (npcDropItem.getId() == 23555 ||
                npcDropItem.getId() == 235563    ||
                npcDropItem.getId() == 23557){
            drBoost /= 2.5;
        }


        if (npcDropItem.getId() == 23759){
            drBoost /= 2;
        }


        if (npcDropItem.getId() >= 23690 &&
                npcDropItem.getId() <= 23694){
            drBoost /= 2;
        }


        if (npcDropItem.getId() == 23772){
            drBoost /= 2;
        }
        if (npcDropItem.getId() == 23836 || npcDropItem.getId() == 23837){
            drBoost /= 2;
        }


        if (npcDropItem.getId() >= 23849 &&
                npcDropItem.getId() <= 23853){
            drBoost /= 2;
        }

        int possible = (int) ((chance - 1) / drBoost);

        if (possible <= 1 && chance >= 2) {
            possible = 1;
        }

        int chances = Misc.getRandom(possible);
        boolean inside = chances <= 0;

        return (b.containsKey(chance) ? !b.get(chance) : true) && inside;
    }

    public static void drop(Player player, Item item, NPC npc, Position pos, boolean goGlobal) {
        drop(player, item, npc, pos, goGlobal, false);
    }


    public static void drop(Player player, Item item, NPC npc, Position pos, boolean goGlobal, boolean announce) {
        if (npc.getId() == 9031 || npc.getId() == 2007 || npc.getId() == 2042 || npc.getId() == 2043 || npc.getId() == 2044) {

            pos = player.getPosition().copy();
        }

        if (item.getId() == 22120) {
            boolean iron = player.getGameMode().equals(GameMode.IRONMAN);
            boolean ultimateIron = player.getGameMode().equals(GameMode.ULTIMATE_IRONMAN);
            boolean groupIron = player.getGameMode().equals(GameMode.GROUP_IRONMAN);

            if (iron) {
                item.setId(22120);
            } else if (ultimateIron) {
                item.setId(22119);
            } else if (groupIron) {
                item.setId(22118);
            }
        }
        if ((player.getInventory().contains(3323) || player.getInventory().contains(18337)
                || (player.getSkillManager().skillCape(Skill.PRAYER) && player.getBonecrushEffect()))
                && BonesData.forId(item.getId()) != null) {
            player.getPacketSender().sendGlobalGraphic(new Graphic(777), pos);
            if (BonesData.forId(item.getId()) == BonesData.FROSTDRAGON_BONES) {
                AchievementsOLD.doProgress(player, AchievementDataOLD.BURY_25_FROST_DRAGON_BONES);
                AchievementsOLD.doProgress(player, AchievementDataOLD.BURY_500_FROST_DRAGON_BONES);
            }
            if (player.getRights().isMember()) {
                player.getSkillManager().addExperience(Skill.PRAYER, BonesData.forId(item.getId()).getBuryingXP() * 2);
                return;
            } else {
                player.getSkillManager().addExperience(Skill.PRAYER, BonesData.forId(item.getId()).getBuryingXP());
                return;
            }
        }

        if (ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.DOUBLE_DROPS) {
            item.setAmount(item.getAmount() * 2);
        }

        if (npc.getId() == 8013) {
            if (player.lastVoteTime >= System.currentTimeMillis() - 86400000) {
                item.setAmount(item.getAmount() * 2);
            }
        }



        int itemId = item.getId();
        int amount = item.getAmount();
        // BETRAYED SLAYER KEYS


        if (itemId == 995 && player.getEquipment().get(Equipment.RING_SLOT).getId() == 22045) {
            if (!player.getInventory().contains(itemId) && player.getInventory().getFreeSlots() == 0) {
                player.getPacketSender()
                        .sendMessage("Your inventory is full, your Dragonstone ring (e) is unable to pick up coins!");
            } else {
                player.getInventory().add(itemId, amount);
                return;
            }
        }

        if (itemId == CharmingImp.GOLD_CHARM || itemId == CharmingImp.GREEN_CHARM || itemId == CharmingImp.CRIM_CHARM
                || itemId == CharmingImp.BLUE_CHARM) {
            if ((player.getInventory().contains(6500) || player.getSkillManager().skillCape(Skill.PVP))
                    && CharmingImp.handleCharmDrop(player, itemId, amount)) {
                return;
            }
        }

        if (itemId == LootDevice.GOLD_CHARM || itemId == LootDevice.GREEN_CHARM || itemId == LootDevice.CRIM_CHARM
                || itemId == LootDevice.BLUE_CHARM) {
            if ((player.getInventory().contains(19682) || player.getSkillManager().skillCape(Skill.PVP))
                    && LootDevice.handleCharmDrop(player, itemId, amount)) {
                return;
            }
        }

        Player toGive = player;

        boolean ccAnnounce = false;
        if (Location.inMulti(player)) {
            if (player.getCurrentClanChat() != null && player.getCurrentClanChat().getLootShare()) {
                CopyOnWriteArrayList<Player> playerList = new CopyOnWriteArrayList<Player>();
                for (Player member : player.getCurrentClanChat().getMembers()) {
                    if (member != null) {
                        if (member.getPosition().isWithinDistance(player.getPosition())) {
                            playerList.add(member);
                        }
                    }
                }
                if (playerList.size() > 0) {
                    toGive = playerList.get(Misc.getRandom(playerList.size() - 1));
                    if (toGive == null || toGive.getCurrentClanChat() == null
                            || toGive.getCurrentClanChat() != player.getCurrentClanChat()) {
                        toGive = player;
                    }
                    ccAnnounce = true;
                }
            }
        }

        if (itemId == 18778) { // Effigy, don't drop one if player already has one
            if (toGive.getInventory().contains(18778) || toGive.getInventory().contains(18779)
                    || toGive.getInventory().contains(18780) || toGive.getInventory().contains(18781)) {
                return;
            }
            for (Bank bank : toGive.getBanks()) {
                if (bank == null) {
                    continue;
                }
                if (bank.contains(18778) || bank.contains(18779) || bank.contains(18780) || bank.contains(18781)) {
                    return;
                }
            }
        }

        if (npc.getId() == 1120 && announce) {
            player.getDailyTaskManager().submitProgressToIdentifier(30, 1);
        }
        if (npc.getId() == 9813 && announce) {
            player.getDailyTaskManager().submitProgressToIdentifier(36, 1);
        }
        if (npc.getId() == 10015 && announce) {
            player.getDailyTaskManager().submitProgressToIdentifier(48, 1);
        }


        boolean willAnnounce = ItemDropAnnouncer.announce(itemId) || announce;

        if (ItemDropAnnouncer.exclude(itemId))
            willAnnounce = false;

        if (npcsToExclude.contains(npc.getId()))
            willAnnounce = false;

        if (itemId == 10946 || itemId == 6769)
            willAnnounce = true;


        if (NPCDrops.globalNpcs.contains(npc.getId()) && announce) {
            willAnnounce = true;
        }

        if (itemId == 3905 ||
                itemId == 23514 || itemId == 23515 || itemId == 23516 || itemId == 12855 || itemId == 19115 || itemId == 19114 || itemId == 19116 || itemId == 20488
                || itemId == 22083 || itemId == 22084 || itemId == 22092
                || itemId == 15289 || itemId == 15290 || itemId == 7956 || itemId == 13640 || itemId == 3107
                || itemId == 15448 || itemId == 21934 || itemId == 19918 || itemId == 19913 || itemId == 13964
                || itemId == 8803 || itemId == 8804 || itemId == 8805 || itemId == 20173 || itemId == 10025|| itemId == 8834|| itemId == 8835|| itemId == 15830
                || (itemId >= 8323 && itemId <= 8332))
            willAnnounce = false;


        if (itemId >= 23690 && itemId <= 23694)
            willAnnounce = false;

        if (willAnnounce) {
                player.getDailyTaskManager().submitProgressToIdentifier(40, 1);
            String itemName = item.getDefinition().getName();
            String itemMessage =/* Misc.anOrA(itemName) + */" <col=ff4f4f>" + itemName;
            String npcName = Misc.formatText(npc.getDefinition().getName());


            String message = "<img=101><shad=1><col=ff4f4f>[@cya@DROP<col=ff4f4f>]@cya@ " + toGive.getUsername()
                    + " received <col=ff4f4f>" + (item.getAmount() > 1 ? "x" + item.getAmount() : "") + itemMessage + "@cya@ from <col=ff4f4f>" + npcName
                    + "@cya@ - <col=ff4f4f>KC: " + Misc.insertCommasToNumber(KillsTracker.getTotalKillsForNpc(npc.getId() >= 14207 && npc.getId() <= 14209 ? 14210 : npc.getId(), player));
            World.sendMessage(message);
            message = toGive.getUsername() + " has just recieved " + itemMessage + " from " + npcName + " !";
            // new DiscordManager(DiscordConstant.RARE_DROPS_LOGS, "Rare Drops", "" +
            // message).log1();

            if (ccAnnounce) {
                ClanChatManager.sendMessage(player.getCurrentClanChat(),
                        "<col=16777215>[<col=255>Lootshare<col=16777215>]<col=3300CC> " + toGive.getUsername()
                                + " received " + itemMessage + " from " + npcName + "!");
            }

            PlayerLogs.log(toGive.getUsername(),
                    "" + toGive.getUsername() + " received " + itemMessage + " from " + npcName + "!");

            PlayerLogs.logNpcDrops(player.getUsername(), "Player received drop: " + itemMessage
                    + ", id: " + itemId + ", amount: " + amount + ", from: " + npcName);

        }


        if (player.getInventory().contains(23375)) {
            if (player.getDissolving().isDissolvable(item.getId())) {
                player.getInventory().add(12855, item.getAmount() * player.getDissolving().getTotalOrbAmount(item.getId()));

                for (Dissolving.DissolvingData data : Dissolving.DissolvingData.values()) {
                    if (data.getId() == item.getId()) {
                        player.getSkillManager().addExperience(Skill.INVENTION, (data.getExperience() * amount));
                    }
                }
                player.setDissemblerCharges(player.getDissemblerCharges() + 1);

                if (player.getDissemblerCharges() >= 2500) {
                    player.setDissemblerCharges(0);
                    player.getInventory().delete(23375, 1);
                    player.sendMessage("@blu@Your Auto-dissembler has ran out of charges and disappeared.");
                }

                return;
            }
        }


        if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN) {
            int chance = RandomUtility.exclusiveRandom(0, 1000);
            if (chance <= CustomDropUtils.getDoubleDropChance(player)) { // w8 what are u tryna do make it so like it
                // uses
                // int tab = Bank.getTabForItem(player, item.getId());
                player.performGraphic(new Graphic(436));
                // World.sendMessage("x2!");
                player.getInventory().add(new Item(item.getId(), item.getAmount()));
                player.getInventory().add(new Item(item.getId(), item.getAmount()));

                return;

            }
        }


        int chance = RandomUtility.exclusiveRandom(0, 1500);
        if (!(player.getGameMode() == GameMode.ULTIMATE_IRONMAN)
                && chance <= CustomDropUtils.getDoubleDropChance(player)) {
            int tab = Bank.getTabForItem(player, item.getId());
            player.performGraphic(new Graphic(436));
            // World.sendMessage("x2!");

            if (item.getId() == 995 || item.getId() == 5021 || item.getId() == 20103 || item.getId() == 12855 || item.getId() == 5022
                    || item.getId() == 4278 || item.getId() == 7956 || item.getId() == 23751) {
                player.getInventory().add(item.getId(), item.getAmount());
                player.getInventory().add(item.getId(), item.getAmount());
            } else {
                int tab2 = Bank.getTabForItem(player, item.getId());
                player.getBank(tab2).add(new Item(Item.getUnNoted(item.getId()), item.getAmount()), false);
                player.getBank(tab2).add(new Item(Item.getUnNoted(item.getId()), item.getAmount()), false);
                if (player.dropMessageToggle)
                    player.sendMessage(
                            item.getDefinition().getName() + " X2" + " has been sent to your bank.");
            }
            return;

        }

        if (!(player.getGameMode() == GameMode.ULTIMATE_IRONMAN)) {

            if (!(player.getGameMode() == GameMode.ULTIMATE_IRONMAN)
                    && player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 4446
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23660
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19886
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19888
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 22005
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23704
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23834
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23313
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23351
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23713
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23740
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23604
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23775
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23352
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23714
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23741
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23353
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23540
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23541
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 18888
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 13555
                    || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23000
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18818
                    || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18823 ||
                    (player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18823 && player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19888)) {
                player.performGraphic(new Graphic(385));
                if (item.getId() == 995 || item.getId() == 20104 || item.getId() == 20105 ||
                        item.getId() == 20106 || item.getId() == 20107 || item.getId() == 20108 || item.getId() == 20109 ||
                        item.getId() == 20103 || item.getId() == 5021 || item.getId() == 20103 || item.getId() == 12855 || item.getId() == 5022
                        || item.getId() == 4278 || item.getId() == 7956 || item.getId() == 23751) {
                    player.getInventory().add(item.getId(), item.getAmount());
                } else {
                    int tab = Bank.getTabForItem(player, item.getId());
                    player.getBank(tab).add(new Item(Item.getUnNoted(item.getId()), item.getAmount()), false);
                    if (player.dropMessageToggle)
                        player.sendMessage(item.getDefinition().getName() + " X" + item.getAmount()
                                + " has been sent to your bank.");
                }
                return;

            }

        }
        if (player.getGameMode() == GameMode.ULTIMATE_IRONMAN
                && player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 4446
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23660
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19886
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19888
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 22005
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23704
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23834
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23313
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23351
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23713
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23740
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23604
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23775
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23352
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23714
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23741
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23353
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23540
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23541
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 18888
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 13555
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23000
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18818
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18823) {
            player.performGraphic(new Graphic(385));
            player.getInventory().add(new Item(item.getId(), item.getAmount()));
            DropLog.submit(toGive, new DropLogEntry(itemId, item.getAmount()));

            return;

        } else {

            GroundItemManager.spawnGroundItem(toGive,
                    new GroundItem(item, pos, toGive.getUsername(), false, 150, goGlobal, 200));
            DropLog.submit(toGive, new DropLogEntry(itemId, item.getAmount()));
        }

    }


    public static List<Integer> npcsToExclude = new ArrayList() {
        {

            add(1614);
            add(603);
            add(12843);

            add(53);
            add(8018);
            add(13635);

            add(8008);
            add(3308);
            add(3117);

            add(201);
            add(202);
            add(203);

            add(252);
            add(449);
            add(452);

            add(2050);
        }
    };

    public static void simulateDrops(Player player, int npcid) {// todo: make this consider rarity, cba atm. Works for
        // what i want.
        NPCDrops drop = NPCDrops.forId(npcid);
        for (int i = 0; i < drop.getDropList().length; i++) {
            if (drop.getDropList()[i].getItem().getId() <= 0
                    || drop.getDropList()[i].getItem().getId() > ItemDefinition.getMaxAmountOfItems()
                    || drop.getDropList()[i].getItem().getAmount() <= 0) {
                continue;
            }
            player.getBank(1).add(drop.getDropList()[i].getItem());
        }
    }

    public static void casketDrop(Player player, int combat, Position pos) {
        int chance = (int) (1 + combat);
        if (Misc.getRandom(combat <= 50 ? 1300 : 1000) < chance) {
            GroundItemManager.spawnGroundItem(player,
                    new GroundItem(new Item(7956), pos, player.getUsername(), false, 150, true, 200));
        }
    }

    public static void dropScythe(Player player, Position pos, int combat, String npcName) {
        int chance = (500 - combat);
        if (chance < 24) {
            chance = 24;
        }
        if (Misc.getRandom(chance) == 1
                || (player.getUsername().equalsIgnoreCase("Debug") && player.getRights().isDeveloperOnly())) {
            GroundItemManager.spawnGroundItem(player,
                    new GroundItem(new Item(1419), pos, player.getUsername(), false, 150, true, 200));
            // player.getPacketSender().sendMessage("@or1@<shad=0>A Scythe falls to the
            // ground nearby.");
            World.sendMessage("<img=5>@or1@<shad=0> " + player.getUsername() + " has just received a Scythe from "
                    + Misc.anOrA(npcName) + " " + npcName + "! Happy Halloween!");
        }
        // player.getPacketSender().sendMessage("Chance = "+chance);
    }

    public static void dropSled(Player player, Position pos, int combat, String npcName) {
        int chance = (500 - combat);
        if (chance < 24) {
            chance = 24;
        }
        if (Misc.getRandom(chance) == 1
                || (player.getUsername().equalsIgnoreCase("Debug") && player.getRights().isDeveloperOnly())) {
            GroundItemManager.spawnGroundItem(player,
                    new GroundItem(new Item(4084), pos, player.getUsername(), false, 150, true, 200));
            // player.getPacketSender().sendMessage("@or1@<shad=0>A Scythe falls to the
            // ground nearby.");
            World.sendMessage("<img=5>@red@<shad=0> " + player.getUsername() + " has just received a Sled from "
                    + Misc.anOrA(npcName) + " " + npcName + "! @gre@Merry Christmas!");
        }
        // player.getPacketSender().sendMessage("Chance = "+chance);
    }

    public static void dropPumpkin(Player player, Position pos) {
        // player.getPacketSender().sendMessage("dropPumpkin");
        if (Misc.getRandom(10) == 1) {
            if (player.doneHween2016() && (!player.getInventory().isFull() || player.getInventory().contains(1960))) {
                player.getInventory().add(new Item(1960));
            } else {
                GroundItemManager.spawnGroundItem(player,
                        new GroundItem(new Item(1960), pos, player.getUsername(), false, 150, true, 200));
            }
        }
    }

    public static void dropClue(Player player, Position pos, NPC npc) {
        boolean cont = true;
        for (int q = 0; q < player.getBanks().length; q++) {
            if (player.getBank(q).containsAny(CLUESCROLL.hardClueId)) {
                cont = false;
            }
        }
        if (player.getInventory().containsAny(CLUESCROLL.hardClueId)) {
            cont = false;
        }
        if (cont) {
            if (npc.getId() == 9031 || npc.getId() == 2007 || npc.getId() == 2042 || npc.getId() == 2043 || npc.getId() == 2044) {
                pos = player.getPosition().copy();
            }
            int rand = Misc.getRandom(100);
            if (rand == 1 || (rand == 2 && player.checkItem(Equipment.RING_SLOT, 2572))) {
                if (rand == 2) {
                    player.getPacketSender()
                            .sendMessage("@mag@<shad=0><img=11> Your Ring of Wealth activates, spawning a clue!");
                } else {
                    player.getPacketSender().sendMessage("@red@<shad=0><img=5> A clue scroll has appeared!");
                }
                GroundItemManager.spawnGroundItem(player, new GroundItem(new Item(
                        CLUESCROLL.values()[Misc.getRandom((int) (CLUESCROLL.values().length - 1))].getClueId()), pos,
                        player.getUsername(), false, 150, true, 200));
            }
        }
    }

    public static void goldCasketDrop(Player player, Position pos) {
        if (Misc.getRandom(10) <= 1) {
            GroundItemManager.spawnGroundItem(player,
                    new GroundItem(new Item(2714), pos, player.getUsername(), false, 150, true, 200));
        }
    }

    public static void instanceTokenDrop(Player player, NPC npc) {
        Item item = new Item(Misc.random(10) == 0 ? 23211 : 4278);
        if (!(player.getGameMode() == GameMode.ULTIMATE_IRONMAN)
                && player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 4446
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23660
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19886
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 19888
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 22005
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23704
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23834
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23313
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23351
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23713
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23740
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23604
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23775
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23352
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23714
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 23741
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23353
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23354
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23540
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23541
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 18888
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 13555
                || player.getEquipment().getItems()[Equipment.AMULET_SLOT].getId() == 23000
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18818
                || player.getEquipment().getItems()[Equipment.RING_SLOT].getId() == 18823) {
            player.getInventory().add(item);
            if (player.dropMessageToggle)
                player.sendMessage("@red@"+
                        item.getDefinition().getName() + " X" + item.getAmount() + " has been sent to your inventory.");
        } else
            drop(player, item, npc, npc.getPosition(), false);
//            GroundItemManager.spawnGroundItem(player,
        //      new GroundItem(item, pos, player.getUsername(), false, 150, true, 200));
    }

    /**
     * Gets the drop list
     *
     * @return the list
     */
    public NpcDropItem[] getDropList() {
        return drops;
    }

    /**
     * Gets the npcIds
     *
     * @return the npcIds
     */
    public int[] getNpcIds() {
        return npcIds;
    }

 /*   public enum DropChance {
        ALWAYS(1),
        ALMOST_ALWAYS(3),
        VERY_COMMON(15),
        COMMON(50),
        UNCOMMON(120),
       // NOTTHATRARE(300),
        RARE(500),
        LEGENDARY(700),
        LEGENDARY_2(1200),
        LEGENDARY_3(2000),
        LEGENDARY_4(3000),
        LEGENDARY_5(4000),
        LEGENDARY_6(6000),
        LEGENDARY_7(7000),
        LEGENDARY_8(10000), // 13
        LEGENDARY_10(25), // 14
        LEGENDARY_11(250), // 15
        ;

        private int random;

        DropChance(int randomModifier) {
            this.random = randomModifier;
        }

        public int getRandom() {
            return this.random;
        }
    }*/

    /**
     * Represents a npc drop item
     */
    public static class NpcDropItem {

        /**
         * The id.
         */
        private final int id;

        /**
         * Array holding all the amounts of this item.
         */
        private final int[] count;

        /**
         * The chance of getting this item.
         */
        @Getter //@Setter
        private final int chance;


        /**
         * New npc drop item
         *
         * @param id     the item
         * @param count  the count
         * @param chance the chance
         */
        public NpcDropItem(int id, int[] count, int chance) {
            this.id = id;
            this.count = count;
            this.chance = chance;
        }

        /**
         * Gets the item id.
         *
         * @return The item id.
         */
        public int getId() {
            return id;
        }

        /**
         * Gets the chance.
         *
         * @return The chance.
         */
        public int[] getCount() {
            return count;
        }

        /**
         * Gets the chance.
         *
         * @return The chance.
         */
       /* public DropChance getChance1() {
            switch (chance) {
                case 1:
                    return DropChance.ALMOST_ALWAYS; // 1/3
                case 2:
                    return DropChance.VERY_COMMON; // 1/15
                case 3:
                    return DropChance.COMMON; // 1/50
                case 4:
                    return DropChance.UNCOMMON; // 1/120
                case 5:
                    return DropChance.RARE; // 1/500
                case 6:
                    return DropChance.LEGENDARY; // 1/700
                case 7:
                    return DropChance.LEGENDARY_2; // 1/900
                case 8:
                    return DropChance.LEGENDARY_3; // 1/1100
                case 9:
                    return DropChance.LEGENDARY_4; // 1/2000
                case 10:
                    return DropChance.LEGENDARY_5; // 1/3000
                case 11:
                    return DropChance.LEGENDARY_6; // 1/4000
                case 12:
                    return DropChance.LEGENDARY_7; // 1/5000
                case 13:
                    return DropChance.LEGENDARY_8; // 1/5000
                case 14:
                    return DropChance.LEGENDARY_10; // 1/25
                case 15:
                    return DropChance.LEGENDARY_11; // 1/250
                default:
                    return DropChance.ALWAYS; // 1/1
            }
        }*/

        /**
         * Gets the item
         *
         * @return the item
         */
        public Item getItem() {
            int amount = Misc.inclusiveRandom(count[0], count[count.length - 1]);
            return new Item(id, amount);
        }

        public int getMax() {
            return count[count.length - 1];
        }

        public int getMin() {
            return count[0];
        }
    }

    public static class ItemDropAnnouncer {

        // annouce
        public static final int[] TO_ANNOUNCE = new int[]{20099, // - Cursed cape
                22106, // - Lucifer Attachment
                22112, // - Dark Attachment
                9080, // - Mega Part [1]
                9081, // - Mega Part [2]
                9082, // - Mega Part [3]
                11310, // - Youtube JR Pet
                4373, // - Elite Winged Aura
                10949, // - Light Attachment
                10947, // - Supreme Attachment
                1486, // - Creeper Cape
                9054, // - Creeper helm
                9055, // - Creeper body
                9056, // - Creeper legs
                9057, // - Creeper gloves
                9058, // - Creeper boots
                15288, // - Upgrade Token pack (100k)
                11995, // - Pet Chaos elemental
                11996, // - Pet King black dragon
                11997, // - Pet General graardor
                11978, // - Pet TzTok-Jad
                12001, // - Pet Corporeal beast
                12002, // - Pet Kree'arra
                12003, // - Pet K'ril tsutsaroth
                12004, // - Pet Commander zilyana
                12005, // - Pet Dagannoth supreme
                12006, // - Pet Dagannoth prime
                11990, // - Pet Dagannoth rex
                11991, // - Pet Frost dragon
                11992, // - Pet Tormented demon
                11993, // - Pet Kalphite queen
                11994, // - Pet Slash bash
                11989, // - Pet Phoenix
                11988, // - Pet Bandos avatar
                11987, // - Pet Nex
                11986, // - Pet Jungle strykewyrm
                11985, // - Pet Desert strykewyrm
                11984, // - Pet Ice strykewyrm
                11983, // - Pet Green dragon
                11982, // - Pet Baby blue dragon
                11981, // - Pet Blue dragon
                11979, // - Pet Black dragon
                22012, // - Crimson's Katana
                18719, // - Potion of flight
                20103, // - Boss Key
                6769, // - $5 Scroll
                3905, // - Boss Gloves
                22078, // - Demon maul
                19111, // - TokHaar-Kal
                19114, // - Grand Mystery Box
                19115, // - Extreme Mystery Box
                22091, // - Legion scythe
                17546, // -  God Potion (1)
                20488, // - OVERPOWERED CHEST
                22055, // - Pet WildyWyrm
                17544, // -  Supreme Potion (1)
                15367, // - Event pack
                5074, // - Joker pet
                14064, // - Crystalized boots
                14065, // - Crystalized sword
                14060, // - Crystalized helm
                14061, // - Crystalized body
                14062, // - Crystalized legs
                14066, // - Crystalized wings
                14063, // - Crystalized gloves
                14067, // - Crystal queen pet
                3738, // - Herbal AoE Bow
                3723, // - Herbal helm
                3728, // - Azure body
                3724, // - Herbal body
                3725, // - Herbal legs
                3737, // - Scarlet AoE Sword
                3720, // - Scarlet helm
                3721, // - Scarlet body
                3722, // - Scarlet legs
                3739, // - Azure AoE Staff
                3726, // - Azure helm
                3828, // - Saradomin page 2
                3730, // - Azure legs
                2947, // - Frieza Pet (first form)
                2948, // - Frieza Pet (second form)
                2949, // - Frieza Pet (final form)
                10946, // - $1 Scroll
                15279, // - Majin Buu Pet
                15278, // - Super Buu Pet
                4446, // - Collector ring
                19886, // - Collector necklace


                9054, 9055, 9056, 9057,
                3723, 3724, 3725,
                22117,

        };

        private static List<Integer> ITEM_LIST = new ArrayList<>();
        private static List<Integer> EXCLUDES = new ArrayList<Integer>();

        private static void init() {

            for (int i : TO_ANNOUNCE) {
                ITEM_LIST.add(i);
            }
            ITEM_LIST.add(23013);
            ITEM_LIST.add(23002);

            for (int i : EXCLUDE) {
                EXCLUDES.add(i);
            }
        }

        public static boolean announce(int item) {
            return ITEM_LIST.contains(item) && !EXCLUDES.contains(item);
        }

        public static boolean exclude(int item) {
            return EXCLUDES.contains(item);
        }


        public static final int[] EXCLUDE = new int[]{
                15358, // - Double DR Scroll 30 Minutes
                15359, // - Double Damage Scroll 30 Minutes
                19000,
                7587,
                18719,
                19116,
                989,


                8860, 8861, 8862,
                14060,14061,14062, 14063,14064,14065,14066,14067,
                10947, 3720, 3721, 3722, 5073, 5068, 5069, 5070, 5071, 5072, 8335,
                    15401,
                9054, 9055, 9056, 9057,9058

        };
    }
}