package com.ruse.world.content.raffle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruse.model.Item;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;

import java.awt.*;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public class DonationRaffle {

    public static int entriesSize = 0;
    public static String winner;
    public static ArrayList<Item> winnerRewards = new ArrayList<>();
    public static String previousWinner = "N/A";

    @Getter
    public static final HashMap<String, Integer> ENTRIES = new HashMap<>();
    @Getter
    public static ArrayList<DonationRaffle> entriesList = new ArrayList<>();

    @Getter
    public static ArrayList<Item> REWARDS = new ArrayList<>();
    public static ArrayList<String> entriesRanked = new ArrayList<>();


    public static void setRewards(Player player) {
        REWARDS.clear();
        for (Item item : player.getInventory().getItems()){
            if (item != null && item.getId() > 0)
                REWARDS.add(new Item(item.getId(), item.getAmount()));
        }
        save();
    }

    public static int MAXIMUM_ENTRIES = 1_000;
    public static void addEntry(Player player, int amount) {
        String name = player.getUsername();
        if (!ENTRIES.containsKey(name)) {
            if (amount >= MAXIMUM_ENTRIES)
                amount = MAXIMUM_ENTRIES;
            ENTRIES.put(name, amount);
        } else {
            if (ENTRIES.get(name) + amount >= MAXIMUM_ENTRIES)
                amount = MAXIMUM_ENTRIES - ENTRIES.get(name);
            ENTRIES.put(name, ENTRIES.get(name) + amount);
        }

        entriesRanked = UniversalRaffle.sortByValue(ENTRIES);

        if (amount > 0) {
            player.sendMessage("<col=7F1425>[Donation]You have received " + Misc.insertCommasToNumber(amount) + " Raffle entries!");
        }
        entriesSize += amount;
        save();
    }



    public static void drawRaffle() {
        if (ENTRIES.size() == 0) {
            return;
        }
        long looped = 0;
        long picked = Misc.getRandom(entriesSize);

        String winnerName = null;
        for (String name : ENTRIES.keySet()) {
            looped += ENTRIES.get(name);
            if (picked < looped){
                winnerName = name;
                break;
            }
        }

        if ( winnerName == null) {
            ENTRIES.clear();
            entriesList.clear();
            entriesSize = 0;
            entriesRanked.clear();
            return;
        }

        Player player = World.getPlayerByName(winnerName);

        World.sendMessage(
                "<ico=854><col=4422FF><shad>Raffle </shad><col=7F1425><ico=854> [Donation]" +
                        " Weekly Raffle Winner: @red@" + winnerName );


        DiscordMessager.sendWebhook( "[" + UniversalRaffle.getDate() + "] Donation Raffle Winner: " + winnerName,
                Color.YELLOW, "https://discordapp.com/api/webhooks/992012586801451170/1RcURJgeUDt8bdZUGmlxxwJlmEP6crp-bIFYiiwy-4tHKI4jwMeo8m1uHJ9WaHdZumCO");

        previousWinner = winnerName;

        winner = winnerName;
        winnerRewards = (ArrayList<Item>) REWARDS.clone();

        if (player != null) {
            claimReward(player);
        }

        REWARDS.clear();
        REWARDS.add(new Item(23478, 1));
        REWARDS.add(new Item(10943, 1));
        ENTRIES.clear();
        entriesList.clear();
        entriesSize = 0;
        entriesRanked.clear();

        save();
    }

    public static void claimReward(Player player) {
        player.sendMessage("@blu@You have won the Donation Weekly Raffle!");

        if (player.getInventory().hasFreeSlots(winnerRewards.size())) {
            player.getInventory().add(winnerRewards);
        } else {
            for (Item item : winnerRewards) {
                if (Objects.nonNull(item)) {
                    player.depositItemBank(item);
                }
            }
        }

        winner = null;
        winnerRewards = new ArrayList<>();
        save();
    }

    public static void handleLogin(Player player) {
        if (winner != null) {
            if (player.getUsername().equalsIgnoreCase(winner)) {
                claimReward(player);
            }
        }
    }

    public static ArrayList<DonationRaffle> getEntriesList() {
        entriesList.clear();
        for (String name : ENTRIES.keySet()) {
            DonationRaffle drop = new DonationRaffle(name, ENTRIES.get(name));

            if (drop == null)
                continue;
            entriesList.add(drop);
        }
        return entriesList;
    }

    private String name;
    private int entries = 0;

    public DonationRaffle(String name, int entries) {
        this.name = name;
        this.entries += entries;
    }

    public static void save() {
        Path path = Paths.get("./data/saves/raffle/donations.json");
        File file = path.toFile();
        file.getParentFile().setWritable(true);

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

            object.addProperty("raffle-end", UniversalRaffle.endDay);
            object.add("rewards", builder.toJsonTree(REWARDS));
            object.add("entries", builder.toJsonTree(getEntriesList()));

            object.addProperty("winner", winner);
            object.add("winner-rewards", builder.toJsonTree(winnerRewards));
            object.addProperty("previousWinner", previousWinner);

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        ENTRIES.clear();
        try (FileReader fileReader = new FileReader("./data/saves/raffle/donations.json")) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);


            if (reader.has("raffle-end")) {
                UniversalRaffle.endDay = reader.get("raffle-end").getAsInt();
            }

            if (reader.has("rewards")) {
                Item[] data = builder.fromJson(reader.get("rewards"), Item[].class);
                for (Item item : data) {
                    REWARDS.add(item);
                }
            }

            if (reader.has("entries")) {
                DonationRaffle[] data = builder.fromJson(reader.get("entries"), DonationRaffle[].class);
                for (DonationRaffle price : data) {
                    ENTRIES.put(price.name, price.entries);
                    entriesSize += price.entries;
                }
                entriesRanked = UniversalRaffle.sortByValue(ENTRIES);
            }


            if (reader.has("winner")) {
                winner = reader.get("winner").getAsString();
            }

            if (reader.has("winner-rewards")) {
                Item[] data = builder.fromJson(reader.get("winner-rewards"), Item[].class);
                for (Item item : data) {
                    winnerRewards.add(item);
                }
            }

            if (reader.has("previousWinner"))
                previousWinner = reader.get("previousWinner").getAsString();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
