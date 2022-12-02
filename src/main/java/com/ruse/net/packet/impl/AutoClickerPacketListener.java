package com.ruse.net.packet.impl;

import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketListener;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

public class AutoClickerPacketListener implements PacketListener {

    @Override
    public void handleMessage(Player player, Packet packet) {
        int amount = packet.readShort();

        if (amount % 50 == 0) {
            if (player.getCombatBuilder().isAttacking() && player.getCombatBuilder().getVictim().isNpc()) {
                World.sendStaffMessage("<col=FF0066><img=2> [A-C]<col=6600FF> " + player.getUsername()
                        + " clicked same spot @red@" + (amount) + "<col=6600FF> times (NPC: "
                        +((NPC)player.getCombatBuilder().getVictim()).getId() +
                        " - "+ ((NPC)player.getCombatBuilder().getVictim()).getDefinition().getName()+ ").");
            } else if (player.getInteractingItem() != null) {
                World.sendStaffMessage("<col=FF0066><img=2> [A-C]<col=6600FF> " + player.getUsername()
                        + " clicked same spot @red@" + (amount) + "<col=6600FF> times (ITEM:" + player.getInteractingItem().getId() + " - "
                        + player.getInteractingItem().getDefinition().getName() + "" + ")");
            } else if (player.getInteractingObject() != null) {
                World.sendStaffMessage("<col=FF0066><img=2> [A-C]<col=6600FF> " + player.getUsername()
                        + " clicked same spot @red@" + (amount) + "<col=6600FF> times (OBJ:" + player.getInteractingObject().getId() + " - "
                        + player.getInteractingObject().getDefinition().getName() + "" + ")");
            } else {
                World.sendStaffMessage("<col=FF0066><img=2> [A-C]<col=6600FF> " + player.getUsername()
                        + " clicked same spot @red@" + (amount) + "<col=6600FF> times.");
            }
        }

        player.setInactive(true);
    }
}
