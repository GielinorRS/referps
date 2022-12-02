package com.ruse.net;

import java.awt.*;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import org.jboss.netty.channel.Channel;

import com.ruse.GameSettings;
import com.ruse.net.packet.Packet;
import com.ruse.net.packet.PacketBuilder;
import com.ruse.net.packet.PacketConstants;
import com.ruse.net.packet.PacketListener;
import com.ruse.net.packet.codec.PacketDecoder;
import com.ruse.net.packet.impl.ButtonClickPacketListener;
import com.ruse.net.packet.impl.EquipPacketListener;
import com.ruse.net.packet.impl.ItemActionPacketListener;
import com.ruse.world.entity.impl.player.Player;

/**
 * The session handler dedicated to a player that will handle input and output
 * operations.
 *
 * @author lare96 <http://github.com/lare96>
 * @author blakeman8192
 */
public final class PlayerSession {

	/**
	 * The queue of messages that will be handled on the next sequence.
	 */
	private final Queue<Packet> prioritizedMessageQueue = new ConcurrentLinkedQueue<>();
	private final Queue<Packet> messageQueue = new ConcurrentLinkedQueue<>();

	/**
	 * The channel that will manage the connection for this player.
	 */
	private final Channel channel;

	/**
	 * The player I/O operations will be executed for.
	 */
	private Player player;

	/**
	 * The current state of this I/O session.
	 */
	private SessionState state = SessionState.CONNECTED;

	/**
	 * The amount of packets added in this cycle
	 */
	private int addedPackets;

	/**
	 * Creates a new {@link PlayerSession}.
	 *
	 * @param key      the selection key registered to the selector.
	 * @param response the current login response for this session.
	 */
	public PlayerSession(Channel channel) {
		this.channel = channel;
	}

	/**
	 * Queues the {@code msg} for this session to be encoded and sent to the client.
	 *
	 * @param msg the message to queue.
	 */
	public void queueMessage(PacketBuilder msg) {
		try {
			if (player == null || player.isBot()) {
				return;
			}
			if (!channel.isOpen())
				return;
			channel.write(msg.toPacket());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Processes all of the queued messages from the {@link PacketDecoder} by
	 * polling the internal queue, and then handling them via the
	 * handleInputMessage.
	 */
	public void handleQueuedMessages() {
		long lastTime = System.currentTimeMillis();


		Packet msg;
		if (prioritizedMessageQueue.size() == 0 && messageQueue.size() == 0) {
			player.afkTicks++;
		}
		int packets = 0;
		int packet = -1;

		while ((msg = prioritizedMessageQueue.poll()) != null) {
			handleInputMessage(msg);
			packets++;
			packet = msg.getOpcode();
		}
		while ((msg = messageQueue.poll()) != null) {
			handleInputMessage(msg);
			packets++;
			packet = msg.getOpcode();
		}
		addedPackets = 0;


		if (getPlayer() != null && System.currentTimeMillis() - lastTime >= 200) {
			System.out.println("[PLAYER-LAG]" + getPlayer().getUsername()
					+ " created packet lag " + (System.currentTimeMillis() - lastTime) + "ms - packets "+packets+ " ID: " + packet);

				World.sendStaffMessage("<col=FF0066><img=2> [PLAYER-LAG]<col=6600FF> " + getPlayer().getUsername()
						+ " lag @red@" + (System.currentTimeMillis() - lastTime) + "ms <col=6600FF>- amt: @red@"+packets+ "<col=6600FF> ID: @red@" + packet +"<col=6600FF> - Message Nucky on Discord");

			DiscordMessager.sendWebhook( "[PLAYER-LAG]" + getPlayer().getUsername()
							+ " created packet lag " + (System.currentTimeMillis() - lastTime) + "ms - packets "+packets+ " ID: " + packet + " <@782919638291447819>",
					Color.RED, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
		}
	}

	/**
	 * Handles an incoming message.
	 *
	 * @param msg The message to handle.
	 */
	public void handleInputMessage(Packet msg) {
		int op = msg.getOpcode();
		PacketListener listener = PacketConstants.PACKETS[op];

		if (op != 0 && op != 77 && op != 3 && op != 3&& op != 202) {
			player.setHouseServant(0);
		}
		listener.handleMessage(player, msg);
	}

	/**
	 * Uses state-machine to handle upstream messages from Netty.
	 *
	 * @param msg the message to handle.
	 */
	public void handleIncomingMessage(Packet msg) {
		if (addedPackets <= GameSettings.DECODE_LIMIT) {

			if (prioritizedPacket(msg.getOpcode())) {
				prioritizedMessageQueue.add(msg);
			} else {
				messageQueue.add(msg);
			}

			addedPackets++;
		} else {
			// System.out.println("Refuse to add more packets to queue for " + player.getUsername() + ". Already added "
			//	+ addedPackets + " this cycle!!!");
			clearMessages();
		}
	}

	public void clearMessages() {
		prioritizedMessageQueue.clear();
		messageQueue.clear();
		addedPackets = 0;
	}

	public static boolean prioritizedPacket(int op) {
		return op == EquipPacketListener.OPCODE || op == ItemActionPacketListener.FIRST_ITEM_ACTION_OPCODE
				|| op == ButtonClickPacketListener.OPCODE;
	}

	/**
	 * Gets the player I/O operations will be executed for.
	 *
	 * @return the player I/O operations.
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * Gets the current state of this I/O session.
	 *
	 * @return the current state.
	 */
	public SessionState getState() {
		return state;
	}

	/**
	 * Sets the value for {@link PlayerSession#state}.
	 *
	 * @param state the new value to set.
	 */
	public void setState(SessionState state) {
		this.state = state;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
}
