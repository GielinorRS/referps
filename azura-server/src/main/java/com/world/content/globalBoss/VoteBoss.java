package com.world.content.globalBoss;

import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.youtube.YoutubeMgr;
import com.ruse.world.entity.impl.npc.NPC;
import lombok.Getter;
import lombok.Setter;
import java.awt.*;

public class VoteBoss {

    @Getter
    @Setter
    private static int voteCount = 0;

    public static boolean alive = false;

    public static void handleDrop(NPC npc) {
        alive = false;
    }
    public static void spawn() {

        if (VoteBoss.getVoteCount() >= 60 && !VoteBoss.alive) {
            YoutubeMgr.load();
            DiscordMessager.sendGlobal("The Vote Boss has spawned at ::Vboss", Color.BLUE);
            World.sendBroadcastMessage("The Vote Boss has spawned at ::vboss ", 100);
            World.sendNewsMessage("<col=047a9c>Voting in the last 12 hours provides double drops for Vote Boss!");
            alive = true;
        }
    }

}