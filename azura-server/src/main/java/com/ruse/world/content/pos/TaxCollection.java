package com.ruse.world.content.pos;

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

public class TaxCollection {

    public static long TAX_COLLECTION;
    public static double TAX_PERCENT = 0.05;

    public static void increaseTax(long amount, boolean serverPerk) {
        TAX_COLLECTION += amount;
        if (serverPerk)
            ServerPerks.getInstance().contribute((int) (amount / 10));
        save();
    }


    public static void save() {
        Path path = Paths.get("./data/saves/taxcollected.json");
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

            object.addProperty("pos", TAX_COLLECTION);

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        try (FileReader fileReader = new FileReader("./data/saves/taxcollected.json")) {
            JsonParser fileParser = new JsonParser();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            if (reader.has("pos")) {
                TAX_COLLECTION = (reader.get("pos").getAsLong());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
