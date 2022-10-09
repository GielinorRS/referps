package com.ruse.net.packet;

import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

import java.awt.*;

public class ScratchCardEndPacketListener implements PacketListener {

	@Override
	public void handleMessage(Player player, Packet packet) {

		if (packet.readByte() != 1)
			return;

		/*player.getScratchCard().scratching = false;

		player.getScratchCard().getWinnings();*/


		/*World.sendStaffMessage("<col=FF0066><img=2> [ATTEMPT-ABUSE]<col=6600FF> " + player.getUsername()
				+ " tried to do a dupe (BAN him) - Message Nucky on Discord");

		DiscordMessager.sendWebhook( "[ATTEMPT-ABUSE]"+ player.getUsername()
						+ " tried to do a dupe (BAN him) - Message Nucky on Discord",
				Color.BLUE, "https://discord.com/api/webhooks/1028736550755909642/7mtff90Se6VHJV6d50l-6yqjh93ft1tFfWaN7qv7VJWRWcOnxzsFH3nAz6JUDMAhqpcs");*/
	}

}
