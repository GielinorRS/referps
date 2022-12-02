package com.ruse.world.content.raids;


import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.GroundItem;
import com.ruse.model.Position;
import com.ruse.util.Stopwatch;
import com.ruse.util.area.Rectangle;
import com.ruse.world.content.combat.prayer.CurseHandler;
import com.ruse.world.content.combat.prayer.PrayerHandler;
import com.ruse.world.content.raids.impl.gods.GodsRaidsData;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.util.concurrent.CopyOnWriteArrayList;

public abstract class RaidParty extends Party {

    /**
     * All players who are currently active in the raid.
     */
    @Getter
    @Setter
    private CopyOnWriteArrayList<Player> playersInRaid;

    /**
     * All players who have been killed during the raid
     * and are either spectating or waiting to respawn after the current phase.
     */
    @Getter
    @Setter
    private CopyOnWriteArrayList<Player> deadPlayers;

    /**
     * Any ground item spawned during a raid needs to be added to this List.
     */
    @Getter
    @Setter
    private CopyOnWriteArrayList<GroundItem> groundItems;

    /**
     * All Npc's spawned in a room or during a raid/phase should be added to this list.
     */
    @Getter
    @Setter
    private CopyOnWriteArrayList<NPC> npc_members = new CopyOnWriteArrayList<NPC>();

    /**
     * The amount of NPC kills the party has accumulated.
     */
    @Getter
    @Setter
    private int npcKills;

    /**
     * The party's height instance.
     */
    @Getter
    @Setter
    private int height;

    /**
     * The party's current phase.
     */
    @Getter
    @Setter
    private int currentPhase;

    /**
     * Whether or not the party has enetered the raid.
     */
    @Getter
    @Setter
    private boolean enteredRaid;

    /**
     * total number of deaths the party has accumulated.
     */
    @Getter
    @Setter
    private int deathCount;

    /**
     * The consecutive amount of raids completed by a party (streak).
     */
    @Getter
    @Setter
    private int streak;

    /**
     * When the party has finished the raid and not yet left the final room.
     */
    @Getter
    @Setter
    private boolean finishedRaid;

    /**
     * Stopwatch that can be used to time completion of a wave or raid.
     */
    @Getter
    @Setter
    private Stopwatch timer;

    /**
     * Difficulty of the raid.
     */
    @Getter
    @Setter
    private RaidDifficulty difficulty;

    /**
     * Position used to teleport players to the lobby of the raid.
     */
    @Getter
    @Setter
    private Position lobbyPosition;

    /**
     * Position used to teleport players to the start position of the raid.
     */
    @Getter
    @Setter
    private Position startPosition;

    /**
     * Rectangular shape containing the lobby map.
     */
    @Getter
    @Setter
    private Rectangle lobbyArea;

    /**
     * Rectangular shape containing the entire raid map.
     */
    @Getter
    @Setter
    private Rectangle raidArea;

    /**
     * Total number of NPC's killed and damage dealt by the team.
     */
    @Getter
    @Setter
    private int totalKills, totalDamage;

    /**
     * Points that can be used for various things such as determining reward.
     */
    @Getter
    @Setter
    private int points;
    
    @Getter
    @Setter
    private long lastCompletionTime;


    /**
     * Reset variables related to the party.
     */
    public void resetVariables(boolean finished) {
        setPoints(0);
        playersInRaid.clear();
        setTotalKills(0);
        setTotalDamage(0);
        //  timer.reset();
        setFinishedRaid(finished);
        setEnteredRaid(false);
        setDeathCount(0);
        setNpcKills(0);
    }

    /**
     * @param owner Player creating the party.
     */
    public RaidParty(Player owner) {
        super(owner);
        timer = new Stopwatch();
        playersInRaid = new CopyOnWriteArrayList<Player>();
        deadPlayers = new CopyOnWriteArrayList<Player>();
        groundItems = new CopyOnWriteArrayList<GroundItem>();
        setDescription("Raiding");
    }


    /**
     * If the player is in the designated lobby area of the raid
     *
     * @return
     */
    public boolean inLobby(Player player) {
        return lobbyArea.inside(player.getPosition());
    }

    /**
     * If the player is in the designated lobby area of the raid
     *
     * @return
     */
    public boolean inRaidArea(Player player) {
        return !GodsRaidsData.LOBBY_AREA.inside(player.getPosition(), player.getPosition().getZ()) && raidArea.inside(player.getPosition());
    }


    /**
     * Handles all logic for adding a player to the party (once he's accepted the invite)
     */
    protected abstract void add(Player inviter, Player invitee);

    /**
     * Handles all logic for creating the raid party.
     */
    protected abstract void create();

    /**
     * Sets the players party to null. Needs to be overwritten for every new party class.
     *
     * @param player
     */
    @Override
    protected abstract void setNull(Player player);

    /**
     * The logic behind sending an invite request
     *
     * @param player
     */
    @Override
    protected abstract boolean isNull(Player player);


    /**
     * Handles all logic for removing a player from the party
     */
    @Override
    public void leave(Player player) {
        super.leave(player);
        leftPartyInterface(player);

    }


    /**
     * @param player
     */
    @Override
    public void remove(Player player) {
        if (!playersInRaid.isEmpty() && playersInRaid.contains(player))
            playersInRaid.remove(player);

        if (!deadPlayers.isEmpty() && deadPlayers.contains(player))
            deadPlayers.remove(player);

        leftPartyInterface(player);

        super.remove(player);
    }


    /**
     * @param player
     */
    public void leftPartyInterface(Player player) {
        player.getPacketSender().sendCameraNeutrality();
        player.getPacketSender().sendInterfaceRemoval();
        player.getPacketSender().sendString(111709, "Create");
        int id = 111716;
        for (int i = 111716; i < 111737; i++) {
            player.getPacketSender().sendString(id++, "---");
            player.getPacketSender().sendString(id++, "--");
            player.getPacketSender().sendString(id++, "-");
        }
        player.getPacketSender().sendString(111702, getDescription() + " Party: @whi@0");
    }


    /**
     * Refreshes the raiding tab
     */
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

                member.getPacketSender().sendString(111702, getDescription() + " Party: @whi@" + getPlayers().size());
            }
        }
    }

    /**
     * This is an important static method that should be given functionality
     * to every party that has an invite function (which is most likely all).
     * This handles the action of pressing the button for inviting/creating on the raids interface.
     *
     * @param player Player that pressed the button.
     */
    public static void createOrInviteButton(Player player) {
        // NOOP
    }


    /**
     * This is an important static method that should be given functionality
     * to every party that has the default entrance dialogue (which is most likely all).
     * This handles the dialogue actions for entering the raid.
     *
     * @param player Player that pressed the button.
     */
    public static void handleEntranceDialogue(Player player, RaidDifficulty difficulty) {
        // NOOP
    }


    /**
     * This is an important static method that should be given functionality
     * to every party that has an invite function (which is most likely all).
     * This handles the action of whether or not a player accepted an invitation request.
     *
     * @param player   invited player.
     * @param accepted whether the player chose to accept or decline the invitation.
     */
    public static void handleInvitationAction(Player player, boolean accepted) {
        // NOOP
    }

    public void kickPlayerButton(Player player, int buttonId) {
        if ((inRaidArea(player) || inLobby(player)) && player.equals(getOwner())) {
            if (getPlayers().size() >= ((buttonId - 111716) / 3) + 1) {

                Player playerToKick = getPlayers().get((buttonId - 111716) / 3);

                if (playerToKick == player)
                    player.sendMessage("You cannot kick yourself!");
                 else {
                    remove(playerToKick);
                    if(inRaidArea(player))
                        playerToKick.moveTo(lobbyPosition);
                }


            }
        } else {
            player.sendMessage("Only the leader of the party can kick players!");
        }
    }


    public int dropBonus() {
        int rate = 1;

        if (getStreak() >= 5) {
            rate *= Math.min(1.8, (double) ((double) getStreak() / 100) + 1);
        }

        return rate;
    }

    @Override
    protected boolean canJoin(Player inviter, Player invitee) {
        if (getPlayers().size() >= getPartySizeLimit()) {
            invitee.getPacketSender().sendMessage("That party is already full.");
            return false;
        }
        if (isEnteredRaid() || inRaidArea(invitee)) {
            invitee.getPacketSender().sendMessage("This party has already entered a raid.");
            return false;
        }

        if (!inLobby(invitee) || invitee.isTeleporting()) {
            return false;
        }
        return true;
    }

    public void incrStreak(int num) {
        setStreak(getStreak() + num);
    }


    /**
     * Deactives prayers from everyone in the party.
     *
     */
    public void deactivePrayers() {
        for (Player player : getPlayers()) {
            PrayerHandler.deactivateAll(player);
            CurseHandler.deactivateAll(player);
            TaskManager.submit(new Task(3, false) {

                @Override
                public void execute() {
                    PrayerHandler.deactivateAll(player);
                    CurseHandler.deactivateAll(player);
                    stop();
                }
            });
        }
    }

    /**
     * Checks to see whether or not a member of the team has a familiar active.
     *
     * @return
     */
    public boolean hasFamiliar() {
        for (Player member : getPlayers()) {
            if (member != null) {
                member.getPacketSender().sendInterfaceRemoval();
                if (member.getSummoning().getFamiliar() != null) {
                    member.getPacketSender()
                            .sendMessage("You must dismiss your familiar before being allowed to enter the raid.");
                    sendMessage(
                            "" + member.getUsername() + " has to dismiss their familiar before you can enter the raid.");
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Checks to see if players have trade open
     * @return
     */
    public boolean hasTradeOpen() {
        for(Player playerInParty : getPlayers()) {//Check for smuggling owner items into raid
            if(playerInParty.getTrading().inTrade()) {
                sendMessage(("Can't start the raid while " + playerInParty.getUsername().toLowerCase() + " is in a trade"));
                return true;
            }
        }
        return false;
    }

    public void removeCagedPlayers() {
        for (Player player : getDeadPlayers()) {
            player.moveTo(new Position(getStartPosition()));
            getPlayersInRaid().add(player);
        }
        getDeadPlayers().clear();
    }


    public boolean hasOwnerItem() {
        // TODO Owner item check
        /*for (Player player : getPlayers())
            if (!player.isOwnerAllowedInRaids() && player.hasOwnerItem()) {
                sendMessage(player.getUsername() + " has an owner item.");
                return true;
            }*/
        return false;
    }

    /**
     * Determines the player with the lowest defensive stats
     * @return
     */
    public Player lowestDefensePlayer() {



        Player lowestPlayer = getOwner();
       /* int lowestAmt = DesolaceFormulas.getMeleeDefence(lowestPlayer);

        for(Player member : getPlayersInRaid()) {
            if (DesolaceFormulas.getMeleeDefence(member) < lowestAmt) {
                lowestPlayer = member;
                lowestAmt = DesolaceFormulas.getMeleeDefence(member);
            }
        }*/
        return lowestPlayer;
    }

}
