package com.ruse.world.content.pos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruse.GameServer;
import lombok.Getter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;

public class POSItemPrice {

    public static final HashMap<Integer, POSItemPrice> PRICES = new HashMap<>();

    private int itemId;
    @Getter
    private HashMap<Integer, Integer> soldPrices = new HashMap<>();

    public POSItemPrice(int itemId, int price) {
        this.itemId = itemId;
        this.soldPrices.put(price, 1);
    }

    private int calculateAverage() {
        long sum = 0;
        int size = 0;

        for (Integer key : soldPrices.keySet()) {
            if (key > 1) {
                sum += (long)key * (long)soldPrices.get(key);
                size += soldPrices.get(key);
            }
        }
        if (sum == 0 || size == 0)
            return 0;

        return (int)(sum / size);
    }

    public static int getPrice(int itemID) {
        if (PRICES.containsKey(itemID)) {
            return PRICES.get(itemID).calculateAverage();
        }
        return 0;
    }

    public static void addPrice(int itemID, int price, int amount) {
        if (!PRICES.containsKey(itemID)) {
            POSItemPrice.PRICES.put(itemID, new POSItemPrice(itemID, price));
        } else {
            if (POSItemPrice.PRICES.get(itemID).getSoldPrices().containsKey(price))
                POSItemPrice.PRICES.get(itemID).getSoldPrices().put(price, POSItemPrice.PRICES.get(itemID).getSoldPrices().get(price) + amount);
            else
                POSItemPrice.PRICES.get(itemID).getSoldPrices().put(price, +amount);
        }
        POSItemPrice.run();
    }

    public static void addPrice(int itemID, int price) {
        addPrice(itemID, price, 1);
    }

    public static ArrayList<POSItemPrice> pricesList = new ArrayList<>();

    public static void run() {
        pricesList.clear();
        for (int itemId : PRICES.keySet()) {
            POSItemPrice price = PRICES.get(itemId);

            if (price == null)
                return;
            pricesList.add(price);
        }
        save();
    }

    public static void save() {
        Path path = Paths.get("./data/saves/prices.json");
        File file = path.toFile();
        file.getParentFile().setWritable(true);

        if (!file.getParentFile().exists()) {
            try {
                file.getParentFile().mkdirs();
            } catch (SecurityException e) {
            }
        }

        try (FileWriter writer = new FileWriter(file)) {
            Gson builder = new GsonBuilder().setPrettyPrinting().create();
            JsonObject object = new JsonObject();

            object.add("prices", builder.toJsonTree(pricesList));

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            GameServer.getLogger().log(Level.WARNING, "An error has occured while saving a character file!", e);
        }
    }


    public static void load() {
        PRICES.clear();
        try (FileReader fileReader = new FileReader("./data/saves/prices.json")) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            if (reader.has("prices")) {
                POSItemPrice[] pricesData = builder.fromJson(reader.get("prices"), POSItemPrice[].class);
                for (POSItemPrice price : pricesData) {
                    PRICES.put(price.itemId, price);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
