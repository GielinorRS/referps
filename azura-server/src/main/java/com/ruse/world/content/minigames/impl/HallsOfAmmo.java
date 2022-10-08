package com.ruse.world.content.minigames.impl;

import com.ruse.GameSettings;
import com.ruse.model.Item;
import com.ruse.model.Locations;
import com.ruse.model.Position;
import com.ruse.model.RegionInstance;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.World;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.BoxLoot;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.content.transportation.TeleportType;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.impl.HoVMobs.*;
import com.ruse.world.entity.impl.player.Player;

public class HallsOfAmmo {

    public static Box[] loot = { //
            new Box(23315, 10 * 40, 10 * 100, 100),
            new Box(23316, 10 * 40, 10 * 100, 100),
            new Box(23317, 10 * 40, 10 * 100, 100),

            new Box(23342, 10 * 30, 10 * 80, 100),
            new Box(23343, 10 * 30, 10 * 80, 100),
            new Box(23344, 10 * 30, 10 * 80, 100),

            new Box(23333, 10 * 20, 10 * 60, 100),
            new Box(23334, 10 * 20, 10 * 60, 100),
            new Box(23335, 10 * 20, 10 * 60, 100),

            new Box(23324, 10 * 10, 10 * 40, 100),
            new Box(23325, 10 * 10, 10 * 40, 100),
            new Box(23326, 10 * 10, 10 * 40, 100),
    };

    public static final Position TELEPORT_AREA = new Position(2195, 5037, 0);
    public static final Position TELEPORT_OUT = GameSettings.DEFAULT_POSITION;

    public static final Item KEY_REWARD = new Item(23086);
    public static final int CHEST_ID = 41206;
    public static final Position[] NPC_SPAWNS = {
            new Position(2216, 5031, 0),
            new Position(2196, 5015, 0),
            new Position(2216, 5014, 0),
            new Position(2184, 5046, 0),
            new Position(2229, 5001, 0),
    };
    public static final NPC[] HoVNPC = {
            new Skreeg(9025, NPC_SPAWNS[0]),
            new Orix(9026, NPC_SPAWNS[1]),
            new CrystalOrc(9027, NPC_SPAWNS[2]),
            new Kiljaeden(9024, NPC_SPAWNS[3]),
            new Heimdall(9814, NPC_SPAWNS[4])};

    private final Player player;

    public HallsOfAmmo(Player player) {
        this.player = player;
    }

    public static void handleObject(Player player) {
        if (player.getInventory().contains(KEY_REWARD.getId())) {
            Box box = BoxLoot.getLoot(loot);
            player.getInventory().delete(KEY_REWARD.getId(), 1);
            player.getInventory().add(box.getId(), box.getAmount());

            PlayerLogs.log(player.getUsername(), "Opened Halls of Valor chest");
        } else {
            player.getPacketSender().sendMessage("You do not have the Halls of Valor Key!");
        }
    }

    public void initArea() {
        TeleportHandler.teleportPlayer(player, HallsOfAmmo.TELEPORT_AREA.copy().setZ(player.getIndex() * 4), TeleportType.NORMAL);
        if (player.getRegionInstance() != null && player.getRegionInstance().equals(RegionInstance.RegionInstanceType.HALLS_OF_VALOR)) {
            player.getRegionInstance().destruct();
            World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.HALLS_OF_VALOR, player.getIndex() * 4));
        }
        resetBarrows(player);
    }

    public void start() {
        player.setRegionInstance(new RegionInstance(player, RegionInstance.RegionInstanceType.HALLS_OF_VALOR));
        for (NPC newNpc : HoVNPC) {
            NPC npc = new NPC(newNpc.getId(), newNpc.getPosition().copy());
            if (newNpc.getId() == 9025){
                npc = new Skreeg(newNpc.getId(), newNpc.getPosition().copy());
            }else if (newNpc.getId() == 9026){
                npc = new Orix(newNpc.getId(), newNpc.getPosition().copy());
            }else if (newNpc.getId() == 9027){
                npc = new CrystalOrc(newNpc.getId(), newNpc.getPosition().copy());
            }else if (newNpc.getId() == 9024){
                npc = new Kiljaeden(newNpc.getId(), newNpc.getPosition().copy());
            }else if (newNpc.getId() == 9814){
                npc = new Heimdall(newNpc.getId(), newNpc.getPosition().copy());
            }
            // npc.setForceAggressive(true);
            //  npc.setAggressive(true);
            npc.getPosition().setZ(player.getPosition().getZ());
            npc.getCombatBuilder().setAttackTimer(3);
            npc.setSpawnedFor(player);
            npc.getDefinition().setRespawnTime(-1);
            World.register(player, npc);
            // player.getRegionInstance().getNpcsList().add(newNpc);
        }
    }

    public static void resetBarrows(Player player) {
        player.getMinigameAttributes().getHallsOfValorAttributes().setKillcount(0);
    }

    public void killBarrowsNpc(NPC n, boolean killed) {
        if (killed) {
            player.getMinigameAttributes().getHallsOfValorAttributes().setKillcount(player.getMinigameAttributes().getHallsOfValorAttributes().getKillcount() + 1);
            if (player.getRegionInstance() != null) {
                player.getRegionInstance().getNpcsList().remove(n);
            }
            player.setHovKilled(player.getHovKilled() + 1);
            if (player.getMinigameAttributes().getHallsOfValorAttributes().getKillcount() > 4) {
                resetBarrows(player);

                Box box = BoxLoot.getLoot(loot);
                int boxAmount = ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.DOUBLE_DROPS ?
                        box.getAmount() * 4 :   box.getAmount() * 2;
                player.getInventory().add(box.getId(), boxAmount);
                Box box1 = BoxLoot.getLoot(loot);
                int box1Amount = ServerPerks.getInstance().getActivePerk() == ServerPerks.Perk.DOUBLE_DROPS ?
                        box1.getAmount() * 4 :   box1.getAmount() * 2;
                player.getInventory().add(box1.getId(), box1Amount);

                player.getCollectionLogManager().addItem(CollectionLogs.HALLS_OF_AMMO, new Item(box.getId(), boxAmount));
                player.getCollectionLogManager().addItem(CollectionLogs.HALLS_OF_AMMO, new Item(box1.getId(), box1Amount));


                player.getDailyTaskManager().submitProgressToIdentifier(22, 1);


                player.sendMessage("You received x" + boxAmount + " " + ItemDefinition.forId(box.getId()).getName()
                        + " and x" + box1Amount + " " + ItemDefinition.forId(box1.getId()).getName());

                if (player.getRegionInstance() != null) {
                    player.getRegionInstance().getNpcsList().remove(player);
                }
                player.moveTo(HallsOfAmmo.TELEPORT_OUT);
            }
        }
    }
}
