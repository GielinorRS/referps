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

public class AccountsCreated {

    public static HashMap<Long, AccountInformation> DATA = new HashMap<>();

    public static ArrayList<AccountInformation> infoList = new ArrayList<>();

    public static void addData(String name) {
        long epoch = LocalDate.now().toEpochDay();
        if (!DATA.containsKey(epoch)) {
            DATA.put(epoch, new AccountInformation(epoch, name));
        } else {
            if (!DATA.get(epoch).getAccounts().contains(name)) {
                DATA.get(epoch).add(name);
            }
        }
        run();
    }

    public static void run() {
        infoList.clear();
        for (long epoch : DATA.keySet()) {
            AccountInformation price = DATA.get(epoch);

            if (price == null)
                return;
            infoList.add(price);
        }
        save();
    }

    public static void save() {
        Path path = Paths.get("./data/saves/info/accounts.json");
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
                AccountInformation price = DATA.get(epoch);
                if (price == null)
                    return;
                object.addProperty(price.getDay(), price.getTotal());
            }

            object.add("data", builder.toJsonTree(infoList));

            writer.write(builder.toJson(object));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void load() {
        DATA.clear();
        try (FileReader fileReader = new FileReader("./data/saves/info/accounts.json")) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);

            if (reader.has("data")) {
                AccountInformation[] pricesData = builder.fromJson(reader.get("data"), AccountInformation[].class);
                for (AccountInformation data : pricesData) {
                    DATA.put(data.getEpochDay(), data);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Getter
    @Setter
    public static class AccountInformation {
        private long epochDay;
        private String day;
        private int total;
        private ArrayList<String> accounts = new ArrayList<>();

        public AccountInformation(long epochDay, String host) {
            this.epochDay = epochDay;
            this.accounts.add(host);
            this.day = LocalDate.ofEpochDay(epochDay).toString();
            this.total = 1;
        }

        public void add(String host) {
            this.accounts.add(host);
            this.total = this.total + 1;
        }
    }


}