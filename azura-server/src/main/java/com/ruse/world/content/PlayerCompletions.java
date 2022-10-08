package com.ruse.world.content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruse.world.entity.impl.player.Player;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class PlayerCompletions {

    public static ArrayList<String> discordConnected = new ArrayList<>();
    public static ArrayList<String> stPatricksIPS = new ArrayList<>();
    public static ArrayList<String> stPatricksMacs = new ArrayList<>();
    public static ArrayList<String> stPatricksSerials = new ArrayList<>();


    public static void addDiscordConnect(Player player){
        if (!discordConnected.contains(player.getUsername()))
            discordConnected.add(player.getUsername());
        save();
    }



    public static void save() {
        Path path = Paths.get("./data/saves/player-completions.json");
        File file = path.toFile();
        file.getParentFile().setWritable(true);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            Gson builder = new GsonBuilder().setPrettyPrinting().create();
            JsonObject object = new JsonObject();

            object.add("discordConnected", builder.toJsonTree(discordConnected));
            object.add("stPatricksIPS", builder.toJsonTree(stPatricksIPS));
            object.add("stPatricksMacs", builder.toJsonTree(stPatricksMacs));
            object.add("stPatricksSerials", builder.toJsonTree(stPatricksSerials));

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        try (FileReader fileReader = new FileReader("./data/saves/player-completions.json")) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            if (reader.has("discordConnected")) {
                discordConnected.clear();
                for (String item : builder.fromJson(reader.get("discordConnected").getAsJsonArray(), String[].class)) {
                    discordConnected.add(item);
                }
            }
            if (reader.has("stPatricksIPS")) {
                stPatricksIPS.clear();
                for (String item : builder.fromJson(reader.get("stPatricksIPS").getAsJsonArray(), String[].class)) {
                    stPatricksIPS.add(item);
                }
            }
            if (reader.has("stPatricksMacs")) {
                stPatricksMacs.clear();
                for (String item : builder.fromJson(reader.get("stPatricksMacs").getAsJsonArray(), String[].class)) {
                    stPatricksMacs.add(item);
                }
            }
            if (reader.has("stPatricksSerials")) {
                stPatricksSerials.clear();
                for (String item : builder.fromJson(reader.get("stPatricksSerials").getAsJsonArray(), String[].class)) {
                    stPatricksSerials.add(item);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
