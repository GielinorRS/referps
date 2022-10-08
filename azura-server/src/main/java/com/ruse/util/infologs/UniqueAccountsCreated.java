package com.ruse.util.infologs;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class UniqueAccountsCreated {

    public static ArrayList<String> loggedIPS = new ArrayList<>();

    public static HashMap<Long, IPInformation> DATA = new HashMap<>();

    public static ArrayList<IPInformation> infoList = new ArrayList<>();

    public static void addData(String host) {
        long epoch = LocalDate.now().toEpochDay();

        if (!loggedIPS.contains(host)) {
            loggedIPS.add(host);
            if (!DATA.containsKey(epoch)) {
                DATA.put(epoch, new IPInformation(epoch, host));
            } else {
                DATA.get(epoch).add(host);
            }
        }
        run();
    }

    public static void run() {
        infoList.clear();
        for (long epoch : DATA.keySet()) {
            IPInformation price = DATA.get(epoch);

            if (price == null)
                return;
            infoList.add(price);
        }
        save();
    }

    public static void save() {
        Path path = Paths.get("./data/saves/info/unique_accounts.json");
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

            for (long epoch : DATA.keySet()) {
                IPInformation price = DATA.get(epoch);
                if (price == null)
                    return;
                object.addProperty( price.getDay(), price.getTotalUnique());
            }

            object.add("data", builder.toJsonTree(infoList));
            object.add("logged-ips", builder.toJsonTree(loggedIPS));

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        loggedIPS.clear();
        DATA.clear();
        try (FileReader fileReader = new FileReader("./data/saves/info/unique_accounts.json")) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            if (reader.has("data")) {
                IPInformation[] pricesData = builder.fromJson(reader.get("data"), IPInformation[].class);
                for (IPInformation price : pricesData) {
                    DATA.put(price.getEpochDay(), price);
                }
            }

            if (reader.has("logged-ips")) {
                String[] data = builder.fromJson(reader.get("logged-ips"), String[].class);
                for (String host : data) {
                    loggedIPS.add(host);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Getter
    @Setter
    public static class IPInformation {
        private long epochDay;
        private String day;
        private int totalUnique;
        private ArrayList<String> ips = new ArrayList<>();

        public IPInformation(long epochDay, String host) {
            this.epochDay = epochDay;
            this.ips.add(host);
            this.day = LocalDate.ofEpochDay(epochDay).toString();
            this.totalUnique = 1;
        }
        public void add(String host) {
            this.ips.add(host);
            this.totalUnique =  this.totalUnique + 1;
        }
    }


}