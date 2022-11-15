package com.ruse.world.content.collectionlog;

import com.ruse.model.definitions.NPCDrops;
import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.KillsTracker;
import com.ruse.world.content.TeleportInterface;
import com.ruse.world.entity.impl.player.Player;
import lombok.RequiredArgsConstructor;
import lombok.var;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//import com.sun.scenario.effect.DropShadow;

@RequiredArgsConstructor
public class CollectionLogInterface {
    private final int[] NPC_LIST = new int[]{};
    private final Player player;
    private List<Integer> currentlyViewing = new ArrayList<>();

    public void open() {
        initialiseCurrentlyViewing();
        sendBossNames();
        sendNpcData(0);
        player.getPA().sendInterface(30360);
    }

    private void sendBossNames() {
        int[] startingLine = new int[]{30560};
        currentlyViewing.forEach(entry -> {
            player.getPA().sendString(startingLine[0]++, "" + NpcDefinition.forId(entry).getName());
        });
    }

    private void initialiseCurrentlyViewing() {
        currentlyViewing.clear();

		for (TeleportInterface.Bosses data : TeleportInterface.Bosses.values())
			currentlyViewing.add(data.getNpcId());

		for (int entry : NPC_LIST) {
            currentlyViewing.add(entry);
        }
    }

    public void search(String name) {
        initialiseCurrentlyViewing();
        var tempList = new ArrayList<Integer>();
        for (int data : currentlyViewing) {
            if (Objects.nonNull(NpcDefinition.forId(data))) {
                if (!NpcDefinition.forId(data).getName().toLowerCase().contains(name.toLowerCase()))
                    tempList.add(data);
            } else {
                tempList.add(data);
            }
        }
        currentlyViewing.removeAll(tempList);

        for (int i = 0; i < 100; i++) {
            player.getPacketSender().sendString(30560 + i, "");
        }
        sendBossNames();
    }

    private boolean hasObtainedItem(int npc, int item) {
        return player.getCollectionLogData().stream().filter(data -> data.getNpcId() == npc && data.getItem() == item).findFirst().isPresent();
    }

    private void sendNpcData(int index) {
        int received = 0;
        int total = 0;
        var definition = NpcDefinition.forId(currentlyViewing.get(index));
        player.getPacketSender().resetItemsOnInterface(30375, 6 * 10);
        player.getPacketSender().sendString(30368, "" + definition.getName() + "");
        player.getPacketSender().sendString(30369, "Killcount: " +
                Misc.insertCommasToNumber(String.valueOf(KillsTracker.getTotalKillsForNpc(definition.getId(), player))));
        var drops = NPCDrops.forId(definition.getId());
        var slot = 0;
        if (drops != null && drops.getDropList() != null) {
            for (var npcDrop : drops.getDropList()) { //smaller and equal too means very common, and always,
                if (npcDrop.getChance() <= 1) {
                    continue;
                }
                if (hasObtainedItem(definition.getId(), npcDrop.getId())) {
                    var item = player.getCollectionLogData().stream().filter(data -> data.getNpcId() == definition.getId() && data.getItem() == npcDrop.getId()).findFirst().get();
                    player.getPacketSender().sendItemOnInterface(30375, item.getItem(), slot++, item.getAmount());
                    received++;
                } else {
                    player.getPacketSender().sendItemOnInterface(30375, npcDrop.getId(), slot++, 0);
                }
                total++;
            }
        }
        int lines = total / 6;
        if (total % 6 > 0)
            lines++;
        player.getPacketSender().setScrollMax(30385, lines * 40);
        player.getPacketSender().sendString(30367, "Obtained: @gre@" + received + "/" + total + "");
    }

    public boolean handleButton(int buttonId) {
        if (!(buttonId >= 30560 && buttonId <= 30760)) {
            return false;
        }
        int index = -30560 + buttonId;
        if (currentlyViewing.size() > index) { //i do though, has to be as clean as possible ; here are some free lines
            sendNpcData(index);
        }
        return true;
    }
}