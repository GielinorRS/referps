package com.ruse.world.content;


import com.ruse.model.definitions.NpcDefinition;
import com.ruse.util.Misc;
import com.ruse.world.entity.impl.player.Player;

import java.util.Collections;

public class BossLog {

    public static void open(Player player) {

        int id = 142417;

        player.getPacketSender().sendString(142405, "NPC Kill Log");

        player.getPacketSender().sendString(id++, "Monster");
        player.getPacketSender().sendString(id++, "Kills");
        player.getPacketSender().sendString(id++, "Best Time");

        int count = player.getKillsTracker().size();

        Collections.sort(player.getKillsTracker(), (kill1, kill2) -> {
            if (kill1.getAmount() > kill2.getAmount()) {
                return -1;
            } else if (kill2.getAmount() > kill1.getAmount()) {
                return 1;
            } else {
                if (kill1.getNpcName().compareTo(kill2.getNpcName()) > 0) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });

        if (count <= 13)
            count = 13;

        for (int i = 0; i < count; i++) {
            if (player.getKillsTracker().size() > i) {
                KillsTracker.KillsEntry entry = player.getKillsTracker().get(i);
                player.getPacketSender().sendString(id++, NpcDefinition.forId(entry.getNpcId()) == null
                        ? "Invalid (" + entry.getNpcId() + ")" : NpcDefinition.forId(entry.getNpcId()).getName());
                player.getPacketSender().sendString(id++, entry.getAmount() + "");
            } else {
                player.getPacketSender().sendString(id++, "");
                player.getPacketSender().sendString(id++, "");
            }
        }
        player.getPacketSender().sendString(142406, "Total Npc Kills: @whi@" + Misc.insertCommasToNumber(String.valueOf(KillsTracker.getTotalKills(player))));

        player.getPacketSender().setScrollBar(142415, 22 + (count * 18));

        player.getPacketSender().sendInterface(142400);

    }

}
