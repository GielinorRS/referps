package com.ruse.world.content.instanceMananger;

import com.ruse.model.Locations;
import com.ruse.model.Position;
import com.ruse.model.RegionInstance;
import com.ruse.model.RegionInstance.RegionInstanceType;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class InstanceManager {

    private final Player player;

    public InstanceManager(Player player) {
        this.player = player;
    }

    public int pos = 4;
    public int ticketID;
    public int grid = 3;
    public InstanceData selectedInstance;

    private static final InstanceData[] values = InstanceData.values();

    public int getCost() {
        if (selectedInstance != null)
            if (selectedInstance.ordinal() < InstanceData.GROUDON.ordinal()) {
                return (grid == 4 ? 1000 : 500);
            }

        return (grid == 4 ? 10000 : 2500);
    }

    public void createInstance(int npcId, RegionInstanceType type) {

        if (npcId == 9810) {
            int total = KillsTracker.getTotalKillsForNpc(8010, player);

            if (total < 25000 && !player.getRights().isDeveloperOnly()) {
                player.sendMessage("You don't have the requirements to do this.");
                return;
            }
        }
        if (npcId == 7134) {
            int total = KillsTracker.getFenrirKills(player);

            if (total < 50000 && !player.getRights().isDeveloperOnly()) {
                player.sendMessage("You don't have the requirements to do this.");
                return;
            }
        }
        if (npcId == 10007 && player.getAssassinsGuild().getTier() < 1) {
            player.sendMessage("You need to be at least a level 1 Assassin to do this.");
            return;
        }
        if (npcId == 10008 && player.getAssassinsGuild().getTier() < 2) {
            player.sendMessage("You need to be at least a level 2 Assassin to do this.");
            return;
        }
        if (npcId == 10009 && player.getAssassinsGuild().getTier() < 3) {
            player.sendMessage("You need to be at least a level 3 Assassin to do this.");
            return;
        }

        if (player.getInventory().contains(ticketID) && player.getInventory().contains(12855, getCost())) {
            player.getInventory().delete(ticketID, 1);
            player.getInventory().delete(12855, getCost());
        } else {
            player.getPA()
                    .sendMessage("You need an instance token, these can be obtained from killing any npc ingame!");
            player.getPA().sendMessage("You need to have " + Misc.insertCommasToNumber(getCost()) + " Upgrade Tokens in your inventory");
            return;
        }

        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.INSTANCE1, player.getIndex() * pos));
        World.getNpcs().forEach(n -> n.removeInstancedNpcs(Locations.Location.INSTANCE2, player.getIndex() * pos));

        if (player.getRegionInstance() != null) {
            for (NPC n : player.getRegionInstance().getNpcsList()) {
                if (n != null) {
                    World.deregister(n);
                }
            }
            player.getRegionInstance().getNpcsList().clear();
        } else {
            for (NPC n : World.getNpcs()) {
                if (n != null) {
                    if (n.getPosition().getRegionId() == 11082 && n.getPosition().getZ() == (player.getIndex() * pos)) {
                        World.deregister(n);
                    }
                }
            }
        }

        player.setInstanceID(player.getInstanceID() + 1);


        player.setRegionInstance(new RegionInstance(player, type));
        player.lastInstanceNpc = npcId;

        for (GridSpawns gridSpawns : GridSpawns.values()) {
            if (grid == gridSpawns.getGridSize() && NpcDefinition.forId(npcId).getSize() == gridSpawns.getNpcSize()) {
                player.moveTo(new Position(gridSpawns.getPlayerPosition().getX(), gridSpawns.getPlayerPosition().getY(),
                        gridSpawns.getPlayerPosition().getZ() + (player.getIndex() * 4)));
                for (int r = 0; r < gridSpawns.getGridSize(); r++) {
                    for (int c = 0; c < gridSpawns.getGridSize(); c++) {
                        NPC npc_ = new NPC(npcId == 9810 ? npcId + Misc.getRandom(2) : npcId,
                                new Position(gridSpawns.getStart().getX() + (gridSpawns.getOffset() * r)
                                        + (gridSpawns == GridSpawns.THREE_4 && r == 3 ? -1 : 0)
                                        ,
                                        gridSpawns.getStart().getY() + (gridSpawns.getOffset() * c) + (gridSpawns == GridSpawns.THREE_4 ? -1 : 0),
                                        gridSpawns.getStart().getZ() + (player.getIndex() * 4)));
                        npc_.setInstanceID(player.getInstanceID());
                        npc_.setSpawnedFor(player);
                        player.getRegionInstance().getNpcsList().add(npc_);
                        World.register(npc_);
                    }
                }
            }
        }


        for (InstanceData data : values) {
            if (npcId == data.getNpcId() || NpcDefinition.forId(npcId).getName() == data.getName()) {
                player.setCurrentInstanceAmount(ticketID == 23211 ? 120 : 60);
                player.setCurrentInstanceNpcId(data.getNpcId());
                player.setCurrentInstanceNpcName(data.getName());
                if (data.getNpcId() == 6260) {
                    player.getPA().sendMessage(
                            "We have instanced the home area for you, to leave the instance simply teleport out.");
                    player.getLastRunRecovery().reset();
                }
            }
        }
        player.getPA().sendMessage("You have instanced yourself " + player.getCurrentInstanceAmount() + " "
                + player.getCurrentInstanceNpcName());
        player.getPA().sendInterfaceRemoval();

        player.getDailyTaskManager().submitProgressToIdentifier(12, 1);
    }

    public void death(Player player, NPC npc, String NpcName) {
        if (!(player.getCurrentInstanceNpcId() == 9810 && npc.getId() >= 9810 && npc.getId() <= 9812)) {
            if (npc.getId() != player.getCurrentInstanceNpcId()) {
                return;
            }
        }
        if (player.currentInstanceNpcId == -1 || player.currentInstanceNpcName == "") {
            return;
        }
        player.setCurrentInstanceAmount(player.getCurrentInstanceAmount() - 1);


        if (player.getCurrentInstanceAmount() % 15 == 0)
            player.getPA().sendMessage("You need to kill " + player.getCurrentInstanceAmount() + " more " + NpcName);


        if (player.getCurrentInstanceAmount() <= 0) {
            player.getPA().sendMessage("You have used up the total instance count!");
            finish();
            return;
        }
    }

    public void finish() {
        player.getPA().sendMessage("You have used up all your kills inside the instance.");
        player.getPA().sendMessage("to leave the instance simply teleport out.");
        player.getLastRunRecovery().reset();
        if (player != null) {
            onLogout();
        }
    }

    public void onLogout() {
        if (player.getRegionInstance() != null)
            player.getRegionInstance().destruct();
        //player.getInstanceManager().selectedInstance = (null);
        player.setCurrentInstanceAmount(-1);
        player.setCurrentInstanceNpcId(-1);
        player.setCurrentInstanceNpcName("");
    }
}
