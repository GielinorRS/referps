package com.ruse.world.content.quests;

import com.ruse.model.Position;
import com.ruse.model.Skill;
import com.ruse.net.packet.PacketListener;
import com.ruse.world.World;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

public class CurseOfArrav {


    @Getter
    @Setter
    private int questProgress = 0;

    private Player player;

    public CurseOfArrav(Player player) {
        this.player = player;
    }

    public void openConfirmQuest(){

        player.getPacketSender().sendString(151007, "@yel@Curse of Arrav");

        player.getPacketSender().sendString(151009, "Retrieve Arrav's stolen heart from");
        player.getPacketSender().sendString(151010, "the necromancer, Zemouregal");
        player.getPacketSender().sendString(151011, "");
        player.getPacketSender().sendString(151012, "Requirements:");
        player.getPacketSender().sendString(151013,( player.getStarterTasks().hasCompletedAll() ? "@gre@" : "@red@" ) + "- Complete Starter Tasks");
        player.getPacketSender().sendString(151014, ( player.getSkillManager().getCurrentLevel(Skill.SLAYER) >= 99 ? "@gre@" : "@red@" ) + "- 99 Slayer");

        player.getPacketSender().sendString(151015, "- Arrav Costume");
        player.getPacketSender().sendString(151016, "- Arrav Sword");
        player.getPacketSender().sendString(151017, "- Arrav Shield");

        player.getPacketSender().sendInterface(151000);

    }

    public void acceptQuest(){
        if (questProgress == 0){
            if (!player.getStarterTasks().hasCompletedAll()){
                player.sendMessage("You must complete all starter tasks to start this quest..");
                return;
            }
            if (player.getSkillManager().getCurrentLevel(Skill.SLAYER) < 99){
                player.sendMessage("You must have at least 99 slayer to start this quest..");
                return;
            }
            questProgress = 1;
            DialogueManager.start(player, 20004);
            player.setDialogueActionId(20004);
        }
    }


    public void openGate(){
        if (questProgress == 2) {
            if (player.getInventory().contains(432)) {
                player.getInventory().delete(432, 1);
                player.moveTo(new Position(3112, 9708, player.getIndex() * 4));
            } else {
                player.sendMessage("You need a Zemouregal key to do this.");
            }
        }
    }

    public void openInterface(){

        player.getPacketSender().sendInterface(151500);
        player.getPacketSender().sendString(151506, "Curse of Arrav" );

        int interfaceID = 151601;

        for (int i = 0 ; i < strings.length ; i ++){
            for (int z = 0 ; z < strings[i].length ; z ++) {
                if (questProgress > i) {
                    player.getPacketSender().sendString(interfaceID++, "@gre@" + strings[i][z]);
                } else  if (questProgress == i) {
                    player.getPacketSender().sendString(interfaceID++, "@yel@" + strings[i][z]);
                } else {
                    player.getPacketSender().sendString(interfaceID++, strings[i][z]);
                }
            }
            player.getPacketSender().sendString(interfaceID++, "");
        }

    }


    public static String[][] strings = new String[][]{
            {"1. ", "Speak to Arrav and accept the quest."},
            {"2. ", "Obtain a full canopic jar"},
            {"3. ", "Kill Zemouregal and obtain Arrav's heart"},
            {"4. ", "Return the heart to Arrav"},
    };


}
