package com.ruse.webhooks.discord;

import java.awt.Color;
import java.io.File;
import java.io.FileWriter;
import java.net.URI;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import ca.momoperes.canarywebhooks.embed.EmbedField;
import com.ruse.GameSettings;
import com.ruse.world.World;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.entity.impl.player.Player;
import com.ruse.world.entity.impl.player.PlayerHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Message;
import org.json.JSONObject;

import com.ruse.util.Misc;

import ca.momoperes.canarywebhooks.DiscordMessage;
import ca.momoperes.canarywebhooks.WebhookClient;
import ca.momoperes.canarywebhooks.WebhookClientBuilder;
import ca.momoperes.canarywebhooks.embed.DiscordEmbed;

public class DiscordMessager extends JSONObject {

	@AllArgsConstructor
	@Getter
	@Setter
	public static class DiscordObject{
		private String webhook;
		private DiscordMessage message;
	}

	public static Queue<DiscordObject> discordMessages = new ConcurrentLinkedQueue<>();

	public static void discordThread() {
		new Thread(() -> {
			try {
				while (true){
					DiscordObject disc = discordMessages.poll();
					if (disc == null) {
						Thread.sleep(100);
						continue;
					}
					WebhookClient client = new WebhookClientBuilder().withURI(new URI(disc.getWebhook())).build();
					client.sendPayload(disc.getMessage());
					Thread.sleep(1000);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();
	}

	public static boolean active = false;

	private static String testhook = "https://discordapp.com/api/webhooks/749432787479560212/LdggLt4y-t6L1t20Ni7-508DfES0H-4IdyLsngxE3k_x9w92r4LgSZ-Wa5MzN-tyiSsn";
	private static String announcementhook = "https://discordapp.com/api/webhooks/264978407408795648/xEBWbkd51PIrM_Bv8xuYCWnTWonLAbVGcz_mNH62m0xfwSFnc62To2u_Q82vcp2G_oEo";
	private static String staffhook = "https://discordapp.com/api/webhooks/264978499528294420/XXkZF8s6k3f5MTgvJ8mAiok3W_ushBbSVaFfbo1UULqvmFtYfw0KNJFSpA85gh3Y7gws";
	private static String ingamehook = "https://discordapp.com/api/webhooks/264978586488930304/09Ondbuw6zvWtH9dltOZq30nLnRYp-y9xMPkPDa1xs4MyELl3kmftdiUFnyHzsx-ciGt";
	private static String debughook = "https://discordapp.com/api/webhooks/264978075513651221/4Qk7idRA2NbAmno_FOSmN8PH9D3_H4Un09eV3sg9Wx2TPOJjumuIFTi4dOprX_7cWmGa";
	private static String yellhook = "https://discordapp.com/api/webhooks/265081936165339137/NisWUHGJ8gR-tINeMHMBjQ7H-EJ8b6DznSx75f_NokM5DbIUuo7S874Ah9u3r4FRPYEx";
	private static String pmhook = "https://discordapp.com/api/webhooks/299735829218066432/TuAXNO5mgU93wRMEjnCsDiUZD3uco0AlrpPMYWt3yXt4px-X9FvbbxDgTERqFWUBA19l";
	private static String chathook = "https://discordapp.com/api/webhooks/299740022821879811/73pNXpfq3kMJSR6UNgMZqyWwPebyaYT4A_WAdwihXJNooKzPwdwgbAs8eDb3S2Jf5HAY";
	private static String clanhook = "https://discordapp.com/api/webhooks/299784483404644353/3M1Z_qzhen3C9FwDQXJeJ5NTzGTIYGkJBUo4jIucIJogJaLmXC8ukLhbloziNisZmiaN";

	private static String serverPerks = "https://discord.com/api/webhooks/914696862597021707/_dx5O5_CqO_39iV7a16n_SkrAas8-8Jzr02DT4zZ6sGthcIBvLQE5tqv2d0xmqq20Fc3";
	private static String staffTimes = "https://discord.com/api/webhooks/946332480078741605/KEVFtPbiYQI71IEHC52-qIbhXlXb8ifPba7Y-6jmQKpgWjJ2f8hHyh0JMOWiraWiqFVZ";
	private static String stpat = "https://discord.com/api/webhooks/952465740466909184/SxyPg5IZha3v-OaHtc6FJY5p9c7xJE8BocBUwzkduq_rbqmPUQ4Z70SIvLd3S1-2gWCW";

	public static void sendDiscordIntegrations(String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/952534505279856701/34n0bIX90MG1F8xZXLxqeqj0LWgBzZTMpG_HC1fzRHCan7-fXGZ25eDsoMQZXbEaL2YI";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withTitle(msg) // The title of the embed element
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withEmbed(embed) // Add our embed object
					.withUsername("Discord Integration") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendSaintPatricks(String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/952465740466909184/SxyPg5IZha3v-OaHtc6FJY5p9c7xJE8BocBUwzkduq_rbqmPUQ4Z70SIvLd3S1-2gWCW";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withTitle(msg) // The title of the embed element
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withEmbed(embed) // Add our embed object
					.withUsername("St Pat") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendStaffTimes() {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = staffTimes;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withTitle("asd") // The title of the embed element
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withEmbed(embed) // Add our embed object
					.withUsername("Server Perks") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void setServerPerks(String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = serverPerks;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withTitle(msg) // The title of the embed element
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withEmbed(embed) // Add our embed object
					.withUsername("Server Perks") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void test(String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String[] messages = msg.split("==");

			String webhook = "https://discord.com/api/webhooks/918013446820347925/sttuNNZRVhwF5xhZJYL5kgCoriWk8hJkQzP1iogGky0gdJ6dnEzYZmC9Ghz4hYqJT7jY";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					.withDescription(messages[2]) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder(messages[1]) // The content of the
					// message
					.withEmbed(embed) // Add our embed object
					.withUsername(messages[0]) // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendAFKJail(String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;
			String[] messages = msg.split("==");

			String webhook = "https://discord.com/api/webhooks/923013131679186975/1bFQ10xZfaBhZfM2vXzRmrPB219nhGRB9Vfn_ASV3Rr6zkDcDMRVIYbbV7crVgobB0E0";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(Color.RED) // The color of the embed. You can leave this at null for no color
					.withDescription(messages[2]) // The description of the embed object
					.build(); // Build the embed element

			DiscordEmbed embed1 = new DiscordEmbed.Builder()
					.withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
					.withDescription(messages[3]) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder(messages[1]) // The content of the
					// message
					.withUsername(messages[0]) // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.withEmbed(embed1) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendHighTierExchange(String msg, Color color) {
		try {
			if (GameSettings.LOCALHOST)
				return;
			String[] messages = msg.split("==");

			String webhook = "https://discord.com/api/webhooks/936409466860077106/QItix0qsJ35ezYn-FT1qP5W3Si_Z0x3wCnOMTk6s0PJtcvmc67s4Ju1UZFJXWAmntTB4";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(color) // The color of the embed. You can leave this at null for no color
					.withDescription(messages[1]) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("**" + messages[0] + "**") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void sendGlobal(String msg, Color color) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/942560803943489626/mMdcHvt59TZ7DBuJsJ-L1AE3XLFS9c0jewaaiRAjbXNGwNp6F_KXdYNSVdxYkMe6ckBG";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(color) // The color of the embed. You can leave this at null for no color
					.withDescription(msg) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	public static void sendWebhook(String msg, Color color, String webhook) {
		if (GameSettings.LOCALHOST)
			return;
		if (msg == null)
			return;
		try {
			System.out.println("sendWebhook: " + msg);

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(color) // The color of the embed. You can leave this at null for no color
					.withDescription(msg) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder(msg.contains("<@782919638291447819>") ? "<@782919638291447819>" : "") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message


			discordMessages.add(new DiscordObject(webhook, message));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendSacrifice(String msg, Color color) {
		try {
			if (GameSettings.LOCALHOST)
				return;
			String[] messages = msg.split("==");

			String webhook = "https://discord.com/api/webhooks/928575351855149066/7wD2g81yG-sR1S3q1aO4MkK34Qhf00gkR9JdjFC0md1OlDbH2kNSfOJlGA9XpAVi94gI";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(color) // The color of the embed. You can leave this at null for no color
					.withDescription(messages[1]) // The description of the embed object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("**" + messages[0] + "**") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendDonation(String name, String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/923730297319743569/Z1SbfRUAF5QXYuvgyUEUjZBdCgvtVCyiW04YIdNLKhhcJK74v-cLMmP8nLEGKxgkub6V";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(Color.BLUE) // The color of the embed. You can leave this at null for no color
					//.withDescription(msg) // The description of the embed object
					.withField(new EmbedField(name, msg, false))
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendGiveItem(String name, String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/923742332468232192/rbeLuXFk7BU8pVhf1L4Ebd_GIMJ8Zq__5qANzHBGAwxroF7XV1ps3_toSHtjf3y4gzrz";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(Color.BLUE) // The color of the embed. You can leave this at null for no color
					//.withDescription(msg) // The description of the embed object
					.withField(new EmbedField(name, msg, false))
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public static void sendSpawnItem(String name, String msg) {
		try {
			if (GameSettings.LOCALHOST)
				return;

			String webhook = "https://discord.com/api/webhooks/923744691244109824/R9spN8ZL35P6RWG-lTnW2zKXHGivcYwnZKcX02dkQ9IJBGSx9JBer0mReYfc7f9F7lOx";

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
			// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					.withColor(Color.BLUE) // The color of the embed. You can leave this at null for no color
					//.withDescription(msg) // The description of the embed object
					.withField(new EmbedField(name, msg, false))
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder("") // The content of the
					// message
					.withUsername("") // Override the username of the bot
					.withEmbed(embed) // Add our embed object
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendStaffMessage(String msg) {
	/*	try {
			if (GameSettings.LOCALHOST)
				return;

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = staffhook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.ORANGE) // The color of the embed. You can leave this at null for no color
					.withDescription(
							"Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The
																																// description
																																// of
																																// the
																																// embed
																																// object
					.build(); // Build the embed element

			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the
																								// message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Staff Bot") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public static void sendChatMessage(String msg) {
		try {

			if (GameSettings.LOCALHOST)
				return;
			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = chathook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.YELLOW) // The color of the embed. You can leave this at null for no color
					// .withDescription("Remember, you can mute any specific channel by clicking the
					// bell in the top right of Discord.") // The description of the embed object
					.build(); // Build the embed element

			String msgToSend = Misc.stripIngameFormat(msg);

			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Chat Bot") // Override the username of the bot
					.build(); // Build the message

			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendChatMessage, \n" + msgToSend);
			} else {
				client.sendPayload(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendClanMessage(String msg) {
		try {

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = clanhook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.GREEN) // The color of the embed. You can leave this at null for no color
					// .withDescription("Remember, you can mute any specific channel by clicking the
					// bell in the top right of Discord.") // The description of the embed object
					.build(); // Build the embed element

			String msgToSend = Misc.stripIngameFormat(msg);

			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Clan Bot") // Override the username of the bot
					.build(); // Build the message

			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendClanMessage, \n" + msgToSend);
			} else {
				client.sendPayload(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendPrivateMessage(String msg) {
		try {

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = pmhook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.MAGENTA) // The color of the embed. You can leave this at null for no color
					// .withDescription("Remember, you can mute any specific channel by clicking the
					// bell in the top right of Discord.") // The description of the embed object
					.build(); // Build the embed element

			String msgToSend = Misc.stripIngameFormat(msg);

			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Privacy Bot") // Override the username of the bot
					.build(); // Build the message

			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendPrivateMessage, \n" + msgToSend);
			} else {
				client.sendPayload(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendInGameMessage(String msg) {
		try {

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = ingamehook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.BLUE) // The color of the embed. You can leave this at null for no color
					.withDescription(
							"Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The
																																// description
																																// of
																																// the
																																// embed
																																// object
					.build(); // Build the embed element

			String msgToSend = Misc.stripIngameFormat(msg);

			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
					// .withEmbed(embed) // Add our embed object
					.withUsername("In-Game Bot") // Override the username of the bot
					.build(); // Build the message

			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendInGameMessage, \n" + msgToSend);
			} else {
				client.sendPayload(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendDebugMessage(String msg) {
		try {

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = debughook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder().withTitle("Necrotic - RSPS") // The title of the embed
																							// element
					.withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.MAGENTA) // The color of the embed. You can leave this at null for no color
					.withDescription(
							"Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The
																																// description
																																// of
																																// the
																																// embed
																																// object
					.build(); // Build the embed element

			// DiscordMessage message = new DiscordMessage.Builder(msg)
			DiscordMessage message = new DiscordMessage.Builder(Misc.stripIngameFormat(msg)) // The content of the
																								// message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Debug Bot") // Override the username of the bot
					.build(); // Build the message

			client.sendPayload(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void sendYellMessage(String msg) {
		try {

			if (Misc.checkForOwner() || !active) {
				return;
			}

			String webhook = yellhook;

			WebhookClient client = new WebhookClientBuilder().withURI(new URI(webhook)).build(); // Create the webhook
																									// client

			DiscordEmbed embed = new DiscordEmbed.Builder()
					// .withTitle("Necrotic - RSPS") // The title of the embed element
					// .withURL("http://necrotic.org/") // The URL of the embed element
					.withColor(Color.WHITE) // The color of the embed. You can leave this at null for no color
					.withDescription(
							"Remember, you can mute any specific channel by clicking the bell in the top right of Discord.") // The
																																// description
																																// of
																																// the
																																// embed
																																// object
					.build(); // Build the embed element

			String msgToSend = Misc.stripIngameFormat(msg);

			DiscordMessage message = new DiscordMessage.Builder(msgToSend) // The content of the message
					// .withEmbed(embed) // Add our embed object
					.withUsername("Yell Bot") // Override the username of the bot
					.build(); // Build the message

			if (msgToSend.equalsIgnoreCase(":information_source:!")) {
				sendDebugMessage("Bad message from sendYellMessage, \n" + msgToSend);
			} else {
				client.sendPayload(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * // System.out.println("test"); URL url = new URL(
	 * "https://discordapp.com/api/webhooks/264884075129470976/NvJNe980SYO3DKjOPxoJRx9ew6Y9T6jYxteG_HOZ9zPNewCUj2vskZZMsjtzBiiOn75J"
	 * ); HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	 * conn.setDoOutput(true); conn.setRequestMethod("POST");
	 * conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	 * conn.setRequestProperty("Accept", "application/json; charset=UTF-8");
	 * 
	 * JSONObject discord = new JSONObject();
	 * 
	 * //ARGS: username, content, avatar_url, tts discord.put("username", "test");
	 * discord.put("content",
	 * "@everyone hallo it is me a fRIENDD!@@#!@#!@#!@#!@#!@#!@#!@#!@#");
	 * 
	 * //discord.put("avatar_url", "http://i.imgur.com/4Da0jRZ.png");
	 * //discord.put("tts", false);
	 * 
	 * OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
	 * discord.write(wr); wr.flush();
	 * 
	 * StringBuilder sb = new StringBuilder(); int HttpResult =
	 * conn.getResponseCode(); if (HttpResult == HttpURLConnection.HTTP_OK) {
	 * // System.out.println("HTTP = OK"); BufferedReader br = new BufferedReader( new
	 * InputStreamReader(conn.getInputStream(), "utf-8")); String line = null; while
	 * ((line = br.readLine()) != null) { sb.append(line + "\n"); } br.close();
	 * // System.out.println("" + sb.toString()); } else {
	 * // System.out.println("HTTP = NOT OK");
	 * // System.out.println(conn.getResponseMessage()); }
	 */

}
