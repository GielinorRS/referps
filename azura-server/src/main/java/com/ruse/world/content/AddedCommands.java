package com.ruse.world.content;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruse.world.content.serverperks.ServerPerks;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class AddedCommands {

    public static HashMap<String, String> COMMANDS = new HashMap<>();

    public static void addCommand(String command, String link) {
        COMMANDS.put(command, link);
        save();
    }


    public static void save() {
        Path path = Paths.get("./data/saves/added-commands.json");
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

            for (String command : COMMANDS.keySet()){
                object.addProperty(command, COMMANDS.get(command));
            }

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        try (FileReader fileReader = new FileReader("./data/saves/added-commands.json")) {
            JsonParser fileParser = new JsonParser();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            for (String ok : COMMANDS.keySet()){
                COMMANDS.put(ok, reader.get(ok).getAsString());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
