package com.ruse.world.content;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.*;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.npc.NPCMovementCoordinator;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class WaveMinigame {

    private Player player;
    private int currentWave;
    private int height;
    private ArrayList<NPC> npcs = new ArrayList<>();

    public static int COUNTDOWN = 18;

    @Getter
    @Setter
    private int amountToComplete;
    @Getter
    @Setter
    private int amountDone;
    @Getter
    @Setter
    private int tokens;
    private int timer;

    private boolean usedBooster;

    public WaveMinigame(Player player) {
        this.player = player;
    }

    public void startMinigame() {
        usedBooster = false;
        player.getPacketSender().sendWalkableInterface(150000, true);
        height = player.getIndex() * 4;
        currentWave = 0;
        tokens = 0;
        player.moveTo(new Position(2384, 4706, height));
        npcs.clear();
        player.getPacketSender().sendInterfaceRemoval();
        sequence();
        start();
        if (player.getInventory().contains(23835)) {
            player.getInventory().delete(23835, 1);
            player.sendMessage("@blu@You will receive x2 Upgrade tokens for this Gold Grinder run.");
            usedBooster = true;
        }
    }

    public void start() {
        task = null;
        TaskManager.submit(new Task(1, player, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (tick == COUNTDOWN) {
                    randomWave();
                    stop();
                    return;
                }
                tick++;
                timer = tick;
            }
        });
    }

    public static Position[] spots = new Position[]{new Position(2378, 4702),
            new Position(2386, 4702),
            new Position(2378, 4709),
            new Position(2386, 4709)};

    @Getter
    public Tasks task;

    public enum Tasks {
        CHOP_WOOD,
        MINE_ORE,
        KILL_MOBS
    }

    public void randomWave() {
        if (player.getLocation() != Locations.Location.WAVE_MINIGAME){
            handleDeath();
            return;
        }
        if (currentWave >= 1) {
            double formula = (currentWave / 3 + 1) * 20000  + (2000 * currentWave);
           /* if (currentWave >= 2)
                formula += (5000 * (currentWave - 1));*/

            if (formula >= 300000)
                formula = 300000;

            tokens += formula;
        }
        if (!(player.getPosition().getX() >= 2377 && player.getPosition().getX() <= 2390
        && player.getPosition().getY() >= 4698 && player.getPosition().getY() <= 4714)){
            player.moveTo(new Position(2384, 4706, height));
            player.sendMessage("You have been moved back to the middle.");
        }

        currentWave++;
        amountDone = 0;
        timer = -1;
        if (currentWave % 3 == 0 && currentWave != 0) {
            amountToComplete = 10 + Misc.getRandom(15);


            if (Misc.getRandom(1) == 0)
                task = Tasks.CHOP_WOOD;
            else
                task = Tasks.MINE_ORE;

            player.sendMessage("@red@Wave " + currentWave + " task: x" + amountToComplete + " " +(task == Tasks.CHOP_WOOD ? " Cut wood" : "Mine ore" ));

        } else {
            task = Tasks.KILL_MOBS;
            amountToComplete = (currentWave / 3) + 1;
            for (int i = 0; i < ((currentWave / 3) + 1); i++) {
                spawnNpc(new NPC(10019 + Misc.getRandom(2), spots[i % 4].copy().setZ(height)));
            }

            player.sendMessage("@red@Wave " + currentWave + " task: x" + amountToComplete + " Kill monsters");
        }
    }

    public void progress() {
        amountDone += 1;
        if (amountDone >= amountToComplete){
            start();
        }
    }

    public void updateInterface() {
        player.getPacketSender().sendString(150002, "Wave " + (currentWave <= 1 ? 1 : currentWave));
        player.getPacketSender().sendString(150003, "Tokens Earned: @whi@" + Misc.insertCommasToNumber(getTokens()));

        if (timer != -1) {
            player.getPacketSender().sendString(150004, "Next Wave in: @whi@" + ticksToSeconds(COUNTDOWN - timer) + " secs");
        } else {
            if (task == Tasks.CHOP_WOOD){
                player.getPacketSender().sendString(150004, "Task: @whi@Chop logs ("+amountDone+"/"+amountToComplete+")");
            }else  if (task == Tasks.MINE_ORE){
                player.getPacketSender().sendString(150004, "Task: @whi@Mine ores ("+amountDone+"/"+amountToComplete+")");
            } else if (task == Tasks.KILL_MOBS){
                player.getPacketSender().sendString(150004, "Task: @whi@Kill monsters ("+amountDone+"/"+amountToComplete+")");
            }
        }
    }

    public static int ticksToSeconds(int ticks) {
        return (int) (ticks * 0.6);
    }


    public int getTokens() {
        return tokens * (usedBooster ? 2 : 1);
    }


    public void spawnNpc(NPC npc) {
        npc.getPosition().setZ(height);
        World.register(npc);
        npc.getMovementQueue().setFollowCharacter(player);
        npc.getCombatBuilder().attack(player);
        npc.getMovementCoordinator().setCoordinator(new NPCMovementCoordinator.Coordinator(true, 25));
        npcs.add(npc);
    }

    public void sequence() {
        TaskManager.submit(new Task(1, player, false) {
            int tick = 0;

            @Override
            public void execute() {
                if (tick > 2 && (player == null || player.isDying() || player.getConstitution() <= 0
                        || player.getLocation() != Locations.Location.WAVE_MINIGAME)) {
                    stop();
                    return;
                }
                updateInterface();
                tick++;
            }
        });
    }

    public void finish() {
        if (currentWave > 1) {
            player.sendMessage("@blu@You have concluded the Gold Grinder at wave " + currentWave + " and received " + Misc.insertCommasToNumber(getTokens()) + " Upgrade tokens.");
            player.moveTo(new Position(2420, 4681, 0));
            player.getInventory().add(12855, getTokens());

            if (usedBooster){
                player.sendMessage("@blu@You received x2 Upgrade tokens for this run.");
            }
            usedBooster = false;

            if (currentWave >= 15) {
                String message = "@red@" + player.getUsername() + " <col=ff812f>has just completed @red@"
                        + currentWave + "<col=ff812f> waves of Gold Grinder!";
                World.sendNewsMessage(message);
            }
            if (currentWave >= 10) {
                player.getDailyTaskManager().submitProgressToIdentifier(15, 1);
            }
            currentWave = 0;
        }
    }

    public void killNPC(NPC npc) {
        if (npcs.contains(npc)) {
            npcs.remove(npc);
            amountDone ++;
        }

        if (npcs.size() <= 0) {
            start();
        }
    }

    public void handleDeath() {
        player.getPacketSender().sendWalkableInterface(150000, false);
        for (NPC npc : npcs) {
            if (npc != null)
                World.deregister(npc);
        }
        player.moveTo(new Position(2420, 4681, 0));
        finish();
    }


}
