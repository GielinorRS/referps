package com.ruse.world.content.chambersOfAnima;


import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Locations;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.util.Misc;
import com.ruse.util.Stopwatch;
import com.ruse.world.World;
import com.ruse.world.content.combat.CombatType;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.chambersOfAnima.boss.RaidBoss;
import com.ruse.world.content.chambersOfAnima.boss.impl.RaidsFinalBoss;
import com.ruse.world.content.chambersOfAnima.boss.impl.RaidsFirstBoss;
import com.ruse.world.content.chambersOfAnima.boss.impl.RaidsSecondBoss;
import com.ruse.world.content.chambersOfAnima.boss.impl.RaidsThirdBoss;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class ChambersOfAnimaParty {

    private Player owner;
    private CopyOnWriteArrayList<Player> players;
    private CopyOnWriteArrayList<NPC> npcs = new CopyOnWriteArrayList<>();
    private boolean hasEnteredRaids;
    private int entered;
    private int kills;
    private int height;
    private int currentPhase;
    private int phaseAmount;
    private int deathCount;

    @Getter
    @Setter
    private long totalDamage;

    @Getter
    @Setter
    private long damageCounter;

    private int wave;
    public boolean completedCurrent;
    public ChambersDifficulty difficulty;
    @Getter
    @Setter
    private Stopwatch timer;
    @Getter
    @Setter
    private RaidBoss currentBoss;
    @Getter
    private CombatType acceptableType = CombatType.MELEE;

    public ChambersOfAnimaParty(Player owner) {
        this.owner = owner;
        players = new CopyOnWriteArrayList<>();
        players.add(owner);
        difficulty = ChambersDifficulty.EASY;
        timer = new Stopwatch();
    }

    public void incrementDamage(int damage) {
        totalDamage += damage;
        damageCounter += damage;

        double hp = (50000000 + ((getPlayers().size() - 1) * 50000000)) / 4;
        if (getDifficulty() == ChambersDifficulty.HARD)
            hp *= 2.5D;

        if (damageCounter >= hp){
            randomType();
            damageCounter = 0;
        }
    }

    public void randomType() {
        CombatType random = CombatType.values()[Misc.getRandom(2)];
        if (random == acceptableType){
            randomType();
        }else {
            acceptableType = random;
            if (getNpcs().size() > 0){
               getNpcs().forEach(n -> n.forceChat(acceptableType.name()  + "!"));
            }
            sendMessage("You can now only use @blu@" + acceptableType.name() + "</col> to deal damage!");
        }
    }

    public void proceed() {
        if (currentBoss instanceof RaidsFirstBoss) {
            currentBoss = new RaidsSecondBoss(this);
            for (Player player : players) {
                player.sendMessage("You can move on to the next boss!");
            }
            setWave(1);
        } else if (currentBoss instanceof RaidsSecondBoss) {
            currentBoss = new RaidsThirdBoss(this);
            for (Player player : players) {
                player.sendMessage("You can move on to the next boss!");
            }
            setWave(2);
        } else if (currentBoss instanceof RaidsThirdBoss) {
            currentBoss = new RaidsFinalBoss(this);
            for (Player player : players) {
                player.sendMessage("You can move on to the next boss!");
            }
            setWave(3);
        } else if (currentBoss instanceof RaidsFinalBoss) {
            currentBoss = null;
            ChambersOfAnima.finishRaid(this);
        }
        if (currentBoss != null) {
            TaskManager.submit(new Task(5, false) {
                @Override
                protected void execute() {
                    currentBoss.spawn();
                    stop();
                }
            });
        }

    }

    public void invite(Player p) {
        if (getOwner() == null || p == getOwner())
            return;
        if (hasEnteredRaids) {
            getOwner().getPacketSender().sendMessage("You cannot invite anyone right now.");
            return;
        }
        if (players.size() >= 5) {
            getOwner().getPacketSender().sendMessage("Your party is full.");
            return;
        }
        if (p.getLocation() != Locations.Location.ANIMA_CHAMBERS_LOBBY || p.isTeleporting()) {
            getOwner().getPacketSender().sendMessage("That player is not in Raids.");
            return;
        }
        if (players.contains(p)) {
            getOwner().getPacketSender().sendMessage("That player is already in your party.");
            return;
        }
        if (p.getChambersOfAnimaParty() != null) {
            getOwner().getPacketSender().sendMessage("That player is currently in another party.");
            return;
        }
        if (p.getRights() != PlayerRights.DEVELOPER && System.currentTimeMillis()
                - getOwner().getMinigameAttributes().getAnimaChamberAttributes().getLastInvitation() < 2000) {
            getOwner().getPacketSender().sendMessage("You must wait 2 seconds between each party invitation.");
            return;
        }
        if (p.busy()) {
            getOwner().getPacketSender().sendMessage("That player is currently busy.");
            return;
        }


       /* if (getOwner().getGameMode() == GameMode.GROUP_IRONMAN && !getOwner().getIronmanGroup().equals(p.getIronmanGroup())){
            p.getPacketSender().sendMessage("You are not a part of this players ironman group.");
            return;
        }
        if (p.getGameMode() == GameMode.GROUP_IRONMAN && !p.getIronmanGroup().equals(getOwner().getIronmanGroup())){
            p.getPacketSender().sendMessage("You are not a part of this players ironman group.");
            return;
        }*/

        getOwner().getMinigameAttributes().getAnimaChamberAttributes().setLastInvitation(System.currentTimeMillis());
        DialogueManager.start(p, new ChambersOfAnimaInvitation(getOwner(), p));
        getOwner().getPacketSender().sendMessage("An invitation has been sent to " + p.getUsername() + ".");
    }

    public void add(Player p) {
        if (players.size() >= 5) {
            p.getPacketSender().sendMessage("That party is already full.");
            return;
        }
        if (hasEnteredRaids) {
            p.getPacketSender().sendMessage("This party has already entered a dungeon.");
            return;
        }
        if (p.getLocation() != Locations.Location.ANIMA_CHAMBERS_LOBBY || p.isTeleporting()) {
            return;
        }


        sendMessage(p.getUsername() + " has joined the party.");
        p.getPacketSender().sendMessage("You've joined " + getOwner().getUsername() + "'s party.");
        players.add(p);
        p.setChambersOfAnimaParty(this);
        owner.setChambersOfAnimaParty(this);

    }
    public void onNpcDeath(NPC npc) {
        currentBoss.onDeath(npc);
    }
    public void remove(Player p, boolean fromParty) {
        if (fromParty) {
            players.remove(p);
        }
        p.getPacketSender().sendCameraNeutrality();
        p.getPacketSender().sendInterfaceRemoval();
        p.getPacketSender().sendString(111709, "Create");
        int id = 111716;
        for (int i = 111716; i < 111737; i++) {
            p.getPacketSender().sendString(id++, "---");
            p.getPacketSender().sendString(id++, "--");
            p.getPacketSender().sendString(id++, "-");
        }
        p.getPacketSender().sendString(111702, "Raiding Party: @whi@0");

        if (p.getPosition().getY() >= 5709) {
            if (fromParty) {
                // System.out.println("move: ");
            }
        }

        if (p == owner) {
            if (fromParty) {
                if (getPlayers().size() >= 1) {
                    Player newOwner = getPlayers().get(0);
                    sendMessage(p.getUsername() + " has left the party.");
                    sendMessage("The party owner has been transfered to: " + newOwner.getUsername());
                    setOwner(newOwner);
                } else {
                    sendMessage("The raids party has been destroyed.");
                }
            }

        } else {
            // System.out.println("else: ");
            if (fromParty) {
                sendMessage(p.getUsername() + " has left the party.");
            }
        }
        if (fromParty) {
            p.setChambersOfAnimaParty(null);
        }
        p.getPacketSender().sendInterfaceRemoval();

        boolean destruct = true;
        if (isHasEnteredRaids()) {
            for (Player player : getPlayers()) {
                if (player.getLocation() == Locations.Location.ANIMA_CHAMBERS)
                    destruct = false;
            }
        } else {
            destruct = false;
        }
        if (getPlayers().size() <= 0 || destruct) {
            ChambersOfAnima.destroyInstance(this);
        }
    }

    public void sendMessage(String message) {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.getPacketSender().sendMessage(message);
            }
        }
    }

    public void moveTo(Position position) {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.moveTo(position);
            }
        }
    }

    public void create() {

        if (owner.getChambersOfAnimaParty() != null) {
            owner.getPacketSender().sendMessage("You are already in a Raids party.");
            return;
        }

        if (owner.getChambersOfAnimaParty() == null)
            owner.setChambersOfAnimaParty(new ChambersOfAnimaParty(owner));

        owner.getPacketSender().sendMessage("<col=660000>You've created a Raids party.");

        owner.setChambersOfAnimaParty(this);

    }


    public enum ChambersDifficulty {
        EASY,HARD;
        ChambersDifficulty(){

        }
    }


}
