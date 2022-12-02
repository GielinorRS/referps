package com.ruse.world.content.raids.impl.gods;


import com.ruse.model.Locations.Location;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.raids.RaidParty;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

public class GodsRaidsParty extends RaidParty {


    @Getter
    @Setter
    private NPC saradomin;
    @Getter
    @Setter
    private NPC zamorak;

    @Getter
    @Setter
    private int currentPhase;

    @Getter
    @Setter
    private int interval;

    @Getter
    @Setter
    private boolean saradominAttacking;

    @Getter
    @Setter
    private int saradominHP;

    @Getter
    @Setter
    private int zamorakHP;

    @Getter
    @Setter
    private boolean saradominAttackable;

    @Getter
    @Setter
    private boolean saradominMinionsSpawned;

    @Getter
    @Setter
    private boolean zamorakMinionsSpawned;

    @Getter
    @Setter
    private int attack;


    /**
     * @param owner Player creating the party.
     */
    public GodsRaidsParty(Player owner) {
        super(owner);
        setDescription("Isle of the Gods");
        setPartySizeLimit(6);
        setLobbyPosition(GodsRaidsData.LOBBY_POSITION);
        setLobbyArea(GodsRaidsData.LOBBY_AREA);
        setStartPosition(GodsRaidsData.START_POSITION);
        setRaidArea(GodsRaidsData.RAID_AREA);
    }


    @Override
    protected void inviteRequest(Player player) {
        DialogueManager.start(player, new GodsRaidsInvitation(getOwner(), player));
    }

    /**
     * Handles all logic for removing a player from the party
     */
    @Override
    public void leave(Player player) {
        super.leave(player);
        //player.getCombatBuilder().getHitQueue().getPendingDamage().clear();

        if (!inLobby(player)) {
            player.moveTo(GodsRaidsData.LOBBY_POSITION);
        }

        if (GodsRaidsData.LOBBY_AREA.inside(player.getPosition(), player.getPosition().getZ())){
            player.getPacketSender().sendInteractionOption("Invite", 2, false);
        }
    }

    @Override
    public boolean inLobby(Player player) {
        return getLobbyArea().inside(player.getPosition(), player.getPosition().getZ());
    }

    /**
     * Sets the raid part
     *
     * @param player
     */
    @Override
    public void setNull(Player player) {
        player.setGodsRaidsParty(null);
    }

    @Override
    public boolean isNull(Player player) {
        return player.getGodsRaidsParty() == null;
    }

    @Override
    protected void add(Player inviter, Player invitee) {
        if (canJoin(inviter, invitee)) {
            sendMessage("" + invitee.getUsername() + " has joined the party.");
            invitee.getPacketSender().sendMessage("You've joined " + getOwner().getUsername() + "'s party.");
            getPlayers().add(invitee);
            invitee.setGodsRaidsParty(this);
            invitee.setGodsRaidsParty(getOwner().getGodsRaidsParty());
        }
    }

    /**
     * Handles all logic for creating the raid party.
     */
    @Override
    public void create() {
        if (getOwner().getGodsRaidsParty() != null) {
            getOwner().getPacketSender().sendMessage("You are already in a Raids party.");
            return;
        }

        if (getOwner().getGodsRaidsParty() == null)
            getOwner().setGodsRaidsParty(new GodsRaidsParty(getOwner()));

        getOwner().getPacketSender().sendMessage("<col=660000>You've created a " + getDescription() + " party.");

        getOwner().setGodsRaidsParty(this);
    }

    /**
     * Reset variables related to the party.
     */
    @Override
    public void resetVariables(boolean finished) {
        super.resetVariables(finished);
        this.interval = 0;
    }


    /**
     * This is an important static method that should be given functionality
     * to every party that has an invite function (which is most likely all).
     * This handles the action of pressing the button for inviting/creating on the raids interface.
     *
     * @param player Player that pressed the button.
     */
    public static void createOrInviteButton(Player player) {

        if (player.getLocation() == Location.GODS_LOBBY) {
            if (player.getGodsRaidsParty() != null) {
                if (player.getGodsRaidsParty().getOwner() != player) {
                    player.getPacketSender().sendMessage("Only the party leader can invite other players.");
                } else {
                    player.setInputHandling(new GodsRaidsInviteInput());
                    player.getPacketSender().sendEnterInputPrompt("Invite Player");
                }
            } else {
                new GodsRaidsParty(player).create();
            }
        }else{
            player.getPacketSender().sendMessage("You can only do this in the lobby.");
        }
    }

    /**
     * This is an important static method that should be given functionality
     * for every party that has an invite function (which is most likely all).
     *
     * @param player   invited player.
     * @param accepted whether the player chose to accept or decline the invitation.
     */
    public static void handleInviteDialogueAction(Player player, boolean accepted) {

        if (player.getLocation() == Location.ISLE_GODS)
            return;

        if (player.getGodsRaidsParty() != null)
            return;


        if (player.getGodsPartyInvitation() == null)
            return;


        if (player.getGodsPartyInvitation().getOwner() == null)
            return;

        if (accepted)
            player.getGodsPartyInvitation().add(player);
        else
            player.getGodsPartyInvitation().getOwner().getPacketSender().sendMessage("" + player.getUsername()
                    + " has declined your invitation.");

        player.setGodsPartyInvitation(null);

    }


    /**
     * This is an important static method that should be given functionality
     * to every party that has the default entrance dialogue (which is most likely all).
     * This handles the dialogue actions for entering the raid.
     *
     * @param player Player that pressed the button.
     */
    public static void handleEntranceDialogue(Player player) {
        if (player.getGodsRaidsParty() != null) {
            player.getGodsRaidsParty().begin(player);
        } else
            player.sendMessage("You are not in raid party.");
    }

    /**
     * Starts the raid
     */
    public void begin(Player player) {
        GodsRaids.start(this, player);
    }


    /**
     * Starts the raid that utilizes this party.
     */
    public void startRaid() {
        GodsRaids.startTask(this);
    }

    public void incrInterval(int amt) {
        this.interval = interval + amt;
    }


}
