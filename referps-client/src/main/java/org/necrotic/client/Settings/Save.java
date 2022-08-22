package org.necrotic.client.Settings;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.necrotic.Configuration;
import org.necrotic.client.Client;
import org.necrotic.client.Signlink;
import org.necrotic.client.constants.GameFrameConstants;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by Brandon on 7/28/2017.
 */
public class Save {

	public static void settings(Client client) {
//    File file = new File(Signlink.getSettingsDirectory() + "/settings.json");
		Path path = Paths.get(Signlink.getSettingsDirectory(), "/settings.json");
		File file = path.toFile();
		file.getParentFile().setWritable(true);

		if (!file.exists()) {
			try {
				file.getParentFile().mkdirs();
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}
		try (FileWriter writer = new FileWriter(file)) {
			Gson builder = new GsonBuilder().setPrettyPrinting().create();
			JsonObject object = new JsonObject();

			object.addProperty("username", Client.instance.myUsername == null || !Configuration.SAVE_ACCOUNTS ? "" : Client.instance.myUsername);
			object.addProperty("password", Client.instance.password == null || !Configuration.SAVE_ACCOUNTS ? "" : Client.instance.password);


			object.addProperty("save-accounts", Configuration.SAVE_ACCOUNTS);
			object.addProperty("new-f-keys", Configuration.NEW_FUNCTION_KEYS);
			object.addProperty("new-hp-bars", Configuration.NEW_HEALTH_BARS);
			object.addProperty("new-hitmark", Configuration.NEW_HITMARKS);
			object.addProperty("constitution", Configuration.CONSTITUTION_ENABLED);
			object.addProperty("new-cursor", Configuration.NEW_CURSORS);
			object.addProperty("display-hp-above-head", Configuration.DISPLAY_HP_ABOVE_HEAD);
			object.addProperty("display-username-above-head", Configuration.DISPLAY_USERNAMES_ABOVE_HEAD);
			object.addProperty("gameframe", GameFrameConstants.gameframeType == GameFrameConstants.GameFrameType.FRAME_525 ? false : false);
			object.addProperty("highlight-username", Configuration.HIGHLIGHT_USERNAME);
			object.addProperty("high-detail", Configuration.HIGH_DETAIL);
			object.addProperty("split-chat-color", client.splitChatColor);
			object.addProperty("clan-chat-color", client.clanChatColor);
			object.addProperty("split-chat", client.variousSettings[502]);
			object.addProperty("ground-text", Configuration.GROUND_TEXT);
			object.addProperty("particles", Configuration.PARTICLES_ENABLED);
			object.addProperty("fog-enabled", Configuration.FOG_ENABLED);


			String stringSave = "";
			for (int i = 0; i < client.quickPrayers.length; i++) {
				stringSave = stringSave + client.quickPrayers[i];
			}
			object.add("quick-prayers", builder.toJsonTree(stringSave));

			stringSave = "";
			for (int i = 0; i < client.quickCurses.length; i++) {
				stringSave = stringSave + client.quickCurses[i];
			}
			object.add("quick-curses", builder.toJsonTree(stringSave));

			writer.write(builder.toJson(object));
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}
}

