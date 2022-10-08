package com.ruse.world.content.instanceMananger;

import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;

public class InstanceInterfaceHandler {

    private Player player;

    private static final InstanceData data[] = InstanceData.values();

    public InstanceInterfaceHandler(Player player) {
        this.player = player;
    }

    public void open() {
        if (player.getInstanceManager().selectedInstance == null)
            player.getInstanceManager().selectedInstance = InstanceData.values()[0];
        sendBossNames();
        player.getPA().sendInterface(35000);
    }

    public void sendBossNames() {
        int startID = 35071;
        for (InstanceData data : data) {
            player.getPA().sendString(startID++,(player.getInstanceManager().selectedInstance == data ? "@whi@" : "") +  data.getName() );
        }
        player.getPacketSender().setScrollBar(35070, data.length * 18);
        player.getPacketSender().sendNpcOnInterface(35017, player.getInstanceManager().selectedInstance.getNpcId(), player.getInstanceManager().selectedInstance.getZoom());
        sendInfo();
    }

    public void sendInfo() {
        player.getPacketSender().sendItemOnInterface(35008, player.getInstanceManager().ticketID, 1);
        player.getPacketSender().sendString(35009, "@yel@" + ItemDefinition.forId(player.getInstanceManager().ticketID).getName());
        player.getPacketSender().sendString(35010, "Spawns: @whi@" + (player.getInstanceManager().ticketID == 23211 ? 120 : 60));
        player.getPacketSender().sendString(35011, "Cost: @whi@" + Misc.sendCashToString(player.getInstanceManager().getCost()) + " Upgrade tokens");

        if (player.getInstanceManager().grid == 3) {
            player.getPacketSender().sendConfig(1355, 0);
        }else {
            player.getPacketSender().sendConfig(1355, 1);
        }
    }

    public void handleButtons(int id) {
        if (id == -30524) {
            player.getInstanceManager().grid = (3);
            sendInfo();
        }
        if (id == -30523) {
            player.getInstanceManager().grid = (4);
            sendInfo();
        }

        for (InstanceData data : data) {
            if (id == -30465 + data.ordinal()) {
                player.getInstanceManager().selectedInstance = (data);
                sendBossNames();
            }
        }
    }

}
