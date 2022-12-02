package com.ruse.world.content.leaderboards;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruse.model.Item;
import com.ruse.world.content.pos.POSItemPrice;
import com.ruse.world.content.raffle.PerksRaffle;
import com.ruse.world.content.raffle.UniversalRaffle;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class Leaderboard {
    private String name;
    private long value;

    public Leaderboard(String name, long value) {
        this.name = name;
        this.value = value;
    }

    public static HashMap<LeaderboardData, HashMap<String, Long>> DATA = new HashMap<>();

    public static void init() {
        for (LeaderboardData data : LeaderboardData.values()) {
            DATA.put(data, new HashMap<>());
        }
        load();
    }

    public static void save() {
        ArrayList<Leaderboard> boards = new ArrayList<>();

        for (LeaderboardData data : LeaderboardData.values()) {
            Path path = Paths.get("./data/saves/leaderboards/" + (data.getType().toString().toLowerCase()) + "/" + data.name().toLowerCase() + ".json");
            File file = path.toFile();
            file.getParentFile().setWritable(true);

            boards.clear();
            for (String name : DATA.get(data).keySet()) {
                if (name == null || !DATA.get(data).containsKey(name))
                    return;
                boards.add(new Leaderboard(name, DATA.get(data).get(name)));
            }

            if (!file.getParentFile().exists()) {
                try {
                    file.getParentFile().mkdirs();
                } catch (SecurityException e) {
                }
            }
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

                object.add("data", builder.toJsonTree(boards));

                writer.write(builder.toJson(object));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void load() {
        for (LeaderboardData data : LeaderboardData.values()) {
            try (FileReader fileReader = new FileReader("./data/saves/leaderboards/" + (data.getType().toString().toLowerCase()) + "/" + data.name().toLowerCase() + ".json")) {
                JsonParser fileParser = new JsonParser();
                Gson builder = new GsonBuilder().create();
                JsonObject reader = (JsonObject) fileParser.parse(fileReader);
                if (reader.has("data")) {
                    Leaderboard[] pricesData = builder.fromJson(reader.get("data"), Leaderboard[].class);
                    for (Leaderboard price : pricesData) {
                        DATA.get(data).put(price.name, price.value);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
