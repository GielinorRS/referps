package com.ruse.world.content;

import com.ruse.GameSettings;
import com.ruse.model.Item;
import com.ruse.model.Position;
import com.ruse.model.definitions.ItemDefinition;
import com.ruse.world.World;
import com.ruse.world.content.casketopening.Box;
import com.ruse.world.content.casketopening.BoxLoot;
import com.ruse.world.content.collectionlogs.CollectionLogs;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Calendar;

public class VotingMinigame {
    public static ArrayList<String> ips = new ArrayList<>();

    private Player player;
    private int currentWave;
    private int height;
    private ArrayList<NPC> npcs = new ArrayList<>();
    @Getter
    @Setter
    private int dayCompleted;

    public VotingMinigame(Player player) {
        this.player = player;
    }

    public void startMinigame() {
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_YEAR);

        if (player.getLastVotedDay() != day) {
            DialogueManager.sendStatement(player, "You need to vote today before doing this.");
            return;
        }

        if (day == dayCompleted) {
            DialogueManager.sendStatement(player, "You've already done the Voting Minigame today.");
            return;
        }

       /* if (ips.contains(player.getHostAddress())) {
            DialogueManager.sendStatement(player, "You've already done the Voting Minigame today with another account.");
            return;
        }*/

        height = player.getIndex() * 4;
        currentWave = 1;
        player.moveTo(new Position(2550, 9953, height));
        npcs.clear();
        player.sendMessage("You must create a Patronum Blade to start the minigame.");
        //startWave();
        player.getPacketSender().sendInterfaceRemoval();

        player.getInventory().delete(23460, player.getInventory().getAmount(23460));
        player.getInventory().delete(23461, player.getInventory().getAmount(23461));
        player.getInventory().delete(23462, player.getInventory().getAmount(23462));
    }

    public void startWave() {
        spawnNpc(new NPC(9839, new Position(2550, 9955, height)));
        player.sendMessage("@red@The Mutated Hound has spawned.");
    }

    public void spawnNpc(NPC npc) {
        npc.getPosition().setZ(height);
        World.register(npc);
        npc.getMovementQueue().setFollowCharacter(player);
        npc.getCombatBuilder().attack(player);
        npcs.add(npc);
    }

    public void killNPC(NPC npc) {
        if (npcs.contains(npc))
            npcs.remove(npc);

        if (npcs.size() <= 0) {
            currentWave++;
            finish();
        }
    }

    public void handleDeath() {
        player.moveTo(GameSettings.DEFAULT_POSITION);
    }

    public void finish() {
        player.sendMessage("@red@You have finished the Voting Minigame!");
        player.moveTo(GameSettings.DEFAULT_POSITION);

        player.getInventory().delete(23460, player.getInventory().getAmount(23460));
        player.getInventory().delete(23461, player.getInventory().getAmount(23461));
        player.getInventory().delete(23462, player.getInventory().getAmount(23462));

        player.getInventory().add(23020, 1);

        Box SlotPrize = BoxLoot.getLootDropTables(loot);
        player.getInventory().add(SlotPrize.getId(), SlotPrize.getMax());
        player.sendMessage(
                "@red@You won x" + SlotPrize.getMax() + " " + ItemDefinition.forId(SlotPrize.getId()).getName());

        player.getCollectionLogManager().addItem(CollectionLogs.VOTING_MINIGAME, new Item(SlotPrize.getId(), SlotPrize.getMax()));

        if (SlotPrize.isAnnounce()) {
            String message = "@red@" + player.getUsername() + " <col=ff812f>has just received @red@"
                    + (SlotPrize.getMax() > 1 ? "x" + SlotPrize.getMax() : "") + " "
                    + ItemDefinition.forId(SlotPrize.getId()).getName() + "<col=ff812f> from the @red@Voting Minigame!";
            World.sendNewsMessage(message);
        }
        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_YEAR);
        dayCompleted = day;

        player.getDailyTaskManager().submitProgressToIdentifier(11, 1);
        ips.add(player.getHostAddress());
    }

    public static Box[] loot = { //
            new Box(23020, 1, 100),
            new Box(15501, 1, 100),
            new Box(19114, 5, 100),

            new Box(15358, 1, 2),
            new Box(15359, 1, 2),
            new Box(15288, 1, 2),

            new Box(20488, 10, 1, true),
            new Box(6769, 1, 1, true),
            new Box(15003, 1, 1, true),
    };


}
