package com.ruse.world.content.raids;

import com.ruse.model.Position;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Party {

    @Getter
    @Setter
    private Player owner;

    @Getter
    @Setter
    private CopyOnWriteArrayList<Player> players;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private int partySizeLimit;


    public Party(Player owner) {
        this.owner = owner;
        players = new CopyOnWriteArrayList<Player>();
        players.add(owner);
    }

    /**
     * Checks for whether or not the party is null
     *
     * @param player
     */
    protected abstract boolean isNull(Player player);

    /**
     * Sets the players party to null. Needs to be overwritten for every new party class.
     * @param player
     */
    protected abstract void setNull(Player player);

    /**
     * Handles all logic for inviting a player to the party.
     */
    public void invite(Player player) {
        if (getOwner() == null || player == getOwner())
            return;
        if (getPlayers().size() >= partySizeLimit) {
            getOwner().getPacketSender().sendMessage("Your party is full.");
            return;
        }
        if (getPlayers().contains(player)) {
            getOwner().getPacketSender().sendMessage("That player is already in your party.");
            return;
        }
        if (!isNull(player)) {
            getOwner().getPacketSender().sendMessage("That player is currently in another party.");
            return;
        }

        if (player.busy()) {
            getOwner().getPacketSender().sendMessage("That player is currently busy.");
            return;
        }


        inviteRequest(player);


    }

    /**
     * The logic behind sending an invite request.
     *
     * @param player
     */
    protected abstract void inviteRequest(Player player);



    /**
     * Handles all logic for adding a player to the party (once he's accepted the invite).
     */
    protected abstract void add(Player inviter, Player invitee);

    /**
     * Handles all logic for adding a player to the party (once he's accepted the invite).
     */
    public void add(Player invitee) {
        add(getOwner(), invitee);
    }

    /**
     * @return true if the player meets all the requirements to join the party.
     */
    protected abstract boolean canJoin(Player inviter, Player invitee);

    public void remove(Player player) {
        getPlayers().remove(player);
        setNull(player);
        player.sendMessage("You leave the party.");
        sendMessage(player.getUsername() + " has left the party.");
    }

    /**
     * Sends a message to all players in the party.
     *
     * @param message message to send.
     */
    public void sendMessage(String message) {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.sendMessage(message);
            }
        }
    }

    /**
     * Moves all players in the party to a certain position.
     */
    public void moveTo(Position position) {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.moveTo(position);
            }
        }
    }

    /**
     * @param player
     * @return
     */
    public Player getPartner(Player player) {
        for (Player p : getPlayers()) {
            if (p != null && p.equals(player)) {
                continue;
            } else {
                return p;
            }
        }
        return null;
    }

    /**
     * Handles all logic for removing a player from the party
     */
    public void leave(Player player) {


        if (!player.equals(getOwner()))
            remove(player);

        else {
            if (getPlayers().size() > 1) {

                setOwner(getPlayers().get(1));

                remove(player);

                sendMessage("The new party owner is @red@" + getOwner().getUsername());

            } else {

                remove(player);

            }

        }


        setNull(player);
        player.getPacketSender().sendInterfaceRemoval();
    }



}
