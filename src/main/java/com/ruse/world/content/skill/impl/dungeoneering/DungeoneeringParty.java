package com.ruse.world.content.skill.impl.dungeoneering;

import java.util.concurrent.CopyOnWriteArrayList;

import com.ruse.GameSettings;
import com.ruse.model.*;
import com.ruse.model.Locations.Location;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.Cases;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.content.dialogue.DialogueManager;
import com.ruse.world.content.dialogue.impl.DungPartyInvitation;
import com.ruse.world.content.startertasks.StarterTasks;
import com.ruse.world.content.transportation.TeleportHandler;
import com.ruse.world.entity.impl.GroundItemManager;
import com.ruse.world.entity.impl.npc.NPC;
import com.ruse.world.entity.impl.player.Player;

/**
 * @author Gabriel Hannason
 */
public class DungeoneeringParty {

	public DungeoneeringParty(Player owner) {
		this.owner = owner;
		player_members = new CopyOnWriteArrayList<Player>();
		player_members.add(owner);
	}

	private Player owner;
	private DungeoneeringFloor floor;
	private int complexity = -1;
	private CopyOnWriteArrayList<Player> player_members;
	private CopyOnWriteArrayList<NPC> npc_members = new CopyOnWriteArrayList<NPC>();
	private CopyOnWriteArrayList<GroundItem> ground_items = new CopyOnWriteArrayList<GroundItem>();
	private Position gatestonePosition;
	private boolean hasEnteredDungeon;
	private int kills, deaths, height;
	private boolean killedBoss;

	public void invite(Player p) {
		if (getOwner() == null || p == getOwner())
			return;
		if (hasEnteredDungeon) {
			getOwner().getPacketSender().sendMessage("You cannot invite anyone right now.");
			return;
		}

		if (player_members.contains(p)) {
			getOwner().getPacketSender().sendMessage("That player is already in your party.");
			return;
		}
		if (p.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
			getOwner().getPacketSender().sendMessage("That player is currently in another party.");
			return;
		}
		if (p.getRights() != PlayerRights.DEVELOPER && System.currentTimeMillis()
				- getOwner().getMinigameAttributes().getDungeoneeringAttributes().getLastInvitation() < 2000) {
			getOwner().getPacketSender().sendMessage("You must wait 2 seconds between each party invitation.");
			return;
		}
		if (p.busy()) {
			getOwner().getPacketSender().sendMessage("That player is currently busy.");
			return;
		}


		if (getOwner().getGameMode() == GameMode.GROUP_IRONMAN && !getOwner().getIronmanGroup().equals(p.getIronmanGroup())){
			p.getPacketSender().sendMessage("You are not a part of this players ironman group.");
			return;
		}
		if (p.getGameMode() == GameMode.GROUP_IRONMAN && !p.getIronmanGroup().equals(getOwner().getIronmanGroup())){
			p.getPacketSender().sendMessage("You are not a part of this players ironman group.");
			return;
		}
		getOwner().getMinigameAttributes().getDungeoneeringAttributes().setLastInvitation(System.currentTimeMillis());
		p.getMinigameAttributes().getDungeoneeringAttributes()
				.setPartyInvitation(owner.getMinigameAttributes().getDungeoneeringAttributes().getParty());
		// DialogueManager.start(p, new DungPartyInvitation(getOwner(), p));
		p.getPacketSender().sendMessage(owner.getUsername() + ":raidreq:");
		getOwner().getPacketSender().sendMessage("An invitation has been sent to " + p.getUsername() + ".");
	}

	public void add(Player p) {

		sendMessage("" + p.getUsername() + " has joined the party.");
		p.getPacketSender().sendMessage("You've joined " + getOwner().getUsername() + "'s party.");
		player_members.add(p);
		p.getMinigameAttributes().getDungeoneeringAttributes()
				.setParty(owner.getMinigameAttributes().getDungeoneeringAttributes().getParty());
		refreshInterface();
	}

	private void resetUI(Player player) {
		player.getPacketSender().sendString(29053, "").sendString(29054, "");

		for (int i = 0; i < 10; i++) {
			player.getPacketSender().sendString(29095 + i, "");
		}
	}

	public void remove(Player p, boolean resetTab, boolean fromParty) {
		if (fromParty) {
			player_members.remove(p);

		}
		p.getPacketSender().sendInterfaceRemoval();
		if (p == owner) {
			for (Player member : player_members) {
				if (member != null && member.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null
						&& member.getMinigameAttributes().getDungeoneeringAttributes().getParty() == this) {
					if (member == owner)
						continue;
					if (fromParty) {
						member.getPacketSender().sendMessage("Your Raid party has been deleted by the party's leader.");
						resetUI(member);
						remove(member, false, true);
					} else {
						resetUI(member);
						remove(member, false, false);
					}
				}
			}
		}

		if (fromParty) {
			// System.out.println("kkkkkk");
			if (p.getMinigameAttributes().getDungeoneeringAttributes().getParty().getPlayers().size() <= 0) {
				// System.out.println("kksdsdkkkk");
				for (NPC npc : p.getMinigameAttributes().getDungeoneeringAttributes().getParty().getNpcs()) {
					if (npc == null || !World.getNpcs().contains(npc))
						continue;
					World.deregister(npc);
				}
			}
			p.getMinigameAttributes().getDungeoneeringAttributes().setParty(null);
			resetUI(p);
			refreshInterface();
		}
		p.getPacketSender().sendInterfaceRemoval();
	}

	public void refreshInterface() {
		// System.out.println("Players: " + getPlayers().size());
		for (Player member : getPlayers()) {

			if (member != null) {
				member.getPacketSender().sendString(29053, owner.getUsername() + "'s Group");
				member.getPacketSender().sendString(29054, "Host: " + owner.getUsername());

				for (int i = 0; i < getPlayers().size(); i++) {
					Player p = getPlayers().get(i);

					if (p != null) {
						member.getPacketSender().sendString(29095 + i, p.getUsername());
					}
				}
			}
		}
	}

	public void sendMessage(String message) {
		for (Player member : getPlayers()) {
			if (member != null) {
				member.getPacketSender().sendMessage("<img=5> <col=660000>" + message);
			}
		}
	}

	public void teleportAndGiveKey() {
		int size = getPlayers().size();
		for (Player member : getPlayers()) {
			if (member != null) {
				member.getSeasonPass().addExperience(2);
				Cases.grantCasket(member, 5);

				TeleportHandler.teleportPlayer(member, new Position(2708, 2737, member.getPosition().getZ()),
						member.getSpellbook().getTeleportType());
				
				member.getInventory().add(13591, size > 1 ? 1 : 2); // enjoy.
				Dungeoneering.raidCount = 0;
				
			}
		}
	}

	public void sendFrame(int frame, String string) {
		for (Player member : getPlayers()) {
			if (member != null) {
				member.getPacketSender().sendString(frame, string);
			}
		}
	}

	public static void create(Player p) {
		/*
		 * if(p.getLocation() != Location.DUNGEONEERING) { p.getPacketSender().
		 * sendMessage("You must be in Daemonheim to create a party."); return; }
		 */
		if (p.getMinigameAttributes().getDungeoneeringAttributes().getParty() != null) {
			p.getPacketSender().sendMessage("You are already in a Raid party.");
			return;
		}
		if (p.getMinigameAttributes().getDungeoneeringAttributes().getParty() == null)
			p.getMinigameAttributes().getDungeoneeringAttributes().setParty(new DungeoneeringParty(p));
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty()
				.setDungeoneeringFloor(DungeoneeringFloor.FIRST_FLOOR);
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty().setComplexity(1);
		p.getPacketSender().sendMessage(
				"<img=5> <col=660000>You've created a Raid party. Perhaps you should invite a few players?");
		p.getMinigameAttributes().getDungeoneeringAttributes().getParty().refreshInterface();
	}

	public DungeoneeringFloor getDungeoneeringFloor() {
		return floor;
	}

	public void setDungeoneeringFloor(DungeoneeringFloor floor) {
		this.floor = floor;
	}

	public Player getOwner() {
		return owner;
	}
	
	private int wave = 1;
	
	public int getWave() {
		return wave;
	}
	
	public void incrementWave(int amount) {
		this.wave += amount;
	}
	
	public void setWave(int amount) {
		this.wave = amount;
	}

	public CopyOnWriteArrayList<Player> getPlayers() {
		return player_members;
	}

	public boolean hasEnteredDungeon() {
		return hasEnteredDungeon;
	}

	public void enteredDungeon(boolean hasEnteredDungeon) {
		this.hasEnteredDungeon = hasEnteredDungeon;
	}

	public int getKills() {
		return kills;
	}
	
	public int getHeight() {
		return height;
	}
	
	public void setHeight(int height) {
		this.height = height;
	}

	public void setKills(int kills) {
		this.kills = kills;
	}

	public int getDeaths() {
		return deaths;
	}

	public void setDeaths(int deaths) {
		this.deaths = deaths;
	}

	public CopyOnWriteArrayList<NPC> getNpcs() {
		return npc_members;
	}

	public CopyOnWriteArrayList<GroundItem> getGroundItems() {
		return ground_items;
	}

	public Position getGatestonePosition() {
		return gatestonePosition;
	}

	public void setGatestonePosition(Position gatestonePosition) {
		this.gatestonePosition = gatestonePosition;
	}

	public int getComplexity() {
		return complexity;
	}

	public void setComplexity(int complexity) {
		this.complexity = complexity;
	}

	public void setKilledBoss(boolean killedBoss) {
		this.killedBoss = killedBoss;
	}
}
