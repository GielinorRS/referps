package com.ruse.world.content.deathsanctum;


import com.ruse.model.Locations;
import com.ruse.model.PlayerRights;
import com.ruse.model.Position;
import com.ruse.util.Stopwatch;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;

@Getter
@Setter
public class DeathSanctumParty {

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
    private int wave;
    public boolean completedCurrent;
    public SanctumDifficulty difficulty;
    @Getter
    @Setter
    private Stopwatch timer;

    public DeathSanctumParty(Player owner) {
        this.owner = owner;
        players = new CopyOnWriteArrayList<>();
        players.add(owner);
        difficulty = SanctumDifficulty.EASY;
        timer = new Stopwatch();
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
        if (p.getLocation() != Locations.Location.DEATH_SANCTUM_LOBBY || p.isTeleporting()) {
            getOwner().getPacketSender().sendMessage("That player is not in Raids.");
            return;
        }
        if (players.contains(p)) {
            getOwner().getPacketSender().sendMessage("That player is already in your party.");
            return;
        }
        if (p.getSanctumOfDeathParty() != null) {
            getOwner().getPacketSender().sendMessage("That player is currently in another party.");
            return;
        }
        if (p.getRights() != PlayerRights.DEVELOPER && System.currentTimeMillis()
                - getOwner().getMinigameAttributes().getDeathSanctumAttributes().getLastInvitation() < 2000) {
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

        getOwner().getMinigameAttributes().getDeathSanctumAttributes().setLastInvitation(System.currentTimeMillis());
        DialogueManager.start(p, new DeathSanctumPartyInvitation(getOwner(), p));
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
        if (p.getLocation() != Locations.Location.DEATH_SANCTUM_LOBBY || p.isTeleporting()) {
            return;
        }


        sendMessage(p.getUsername() + " has joined the party.");
        p.getPacketSender().sendMessage("You've joined " + getOwner().getUsername() + "'s party.");
        players.add(p);
        p.setSanctumOfDeathParty(this);
        owner.setSanctumOfDeathParty(this);

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
            p.setSanctumOfDeathParty(null);
        }
        p.getPacketSender().sendInterfaceRemoval();

        boolean destruct = true;
        if (isHasEnteredRaids()) {
            for (Player player : getPlayers()) {
                if (player.getLocation() == Locations.Location.DEATH_SANCTUM)
                    destruct = false;
            }
        } else {
            destruct = false;
        }
        if (getPlayers().size() <= 0 || destruct) {
            DeathSanctum.destroyInstance(this);
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

        if (owner.getSanctumOfDeathParty() != null) {
            owner.getPacketSender().sendMessage("You are already in a Raids party.");
            return;
        }

        if (owner.getSanctumOfDeathParty() == null)
            owner.setSanctumOfDeathParty(new DeathSanctumParty(owner));

        owner.getPacketSender().sendMessage("<col=660000>You've created a Raids party.");

        owner.setSanctumOfDeathParty(this);

    }

    public void refreshInterface() {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.getPacketSender().sendString(111709, "Invite");

                int start = 111716;
                for (int i = 0; i < getPlayers().size(); i++) {
                    member.getPacketSender().sendString(start++, "" + getPlayers().get(i).getUsername());
                    member.getPacketSender().sendString(start++,
                            "" + getPlayers().get(i).getSkillManager().getTotalLevel());
                    member.getPacketSender().sendString(start++,
                            "" + getPlayers().get(i).getSkillManager().getCombatLevel());
                }

                for (int i = start; i < 111737; i++) {
                    member.getPacketSender().sendString(start++, "---");
                    member.getPacketSender().sendString(start++, "--");
                    member.getPacketSender().sendString(start++, "-");
                }

                member.getPacketSender().sendString(111702, "Raiding Party: @whi@" + getPlayers().size());
            }
        }
    }

    public enum SanctumDifficulty{
        EASY,HARD;
        SanctumDifficulty(){

        }
    }


}
