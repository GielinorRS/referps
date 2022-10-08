package com.ruse.world.content.discordbot.command.impl;

import com.ruse.model.definitions.NPCDrops;
import com.ruse.util.Misc;
import com.ruse.world.content.pos.PlayerOwnedShop;
import com.ruse.world.entity.impl.player.Player;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class Search extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("=");
        if (string == null || string.length != 2) {
            e.getMessage().getTextChannel().sendMessage("Invalid Entry").queue();
            return;
        }
        String entry = string[1].toLowerCase();

        if (entry.equalsIgnoreCase("Nucky") || entry.equalsIgnoreCase("Test")
                || entry.equalsIgnoreCase("Divine") || entry.equalsIgnoreCase("100.36.119")
                || entry.equalsIgnoreCase("D0-AB-D5-93-43") || entry.equalsIgnoreCase("O.E.M.20210720214620")
                || entry.equalsIgnoreCase("135.148.171.93")
                || entry.equalsIgnoreCase("D0-50-99-FB-24-78") || entry.equalsIgnoreCase("O.E.M.20211008210212")) {
            e.getMessage().getTextChannel().sendMessage("Nah bruv").queue();
            return;
        }

        new Thread(() -> {
            Map<String, Integer> NAMES = new HashMap<>();
            Path path = Paths.get("./data/saves/worldlogs/", "loginsWIP.txt");
            int total = 0;
            if (path != null) {
                try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.toLowerCase().contains(entry)) {
                            total++;
                            String newName = line.substring(line.indexOf(" [ ") + 2, line.indexOf(" ]"));
                            if (NAMES.containsKey(newName))
                                NAMES.put(newName, NAMES.get(newName) + 1);
                            else
                                NAMES.put(newName, 1);
                        }
                    }
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            e.getMessage().getTextChannel().sendMessage("Total Found: " + total).queue();
            e.getMessage().getTextChannel().sendMessage("Unique Accounts: " + NAMES.size()).queue();

            List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<>(NAMES.entrySet());

            Collections.sort(sortedEntries, (e1, e2) -> e2.getValue().compareTo(e1.getValue()));

            int counted = 0;
            for (Iterator<Map.Entry<String, Integer>> iterator = sortedEntries.iterator(); iterator.hasNext(); ) {
                Map.Entry<String, Integer> data = iterator.next();
                String name1 = data.getKey();
                int amt = data.getValue();
                e.getMessage().getTextChannel().sendMessage(name1 + " : " + amt).queue();
                counted++;
                if (counted >= 50){
                    return;
                }
            }
        }).start();


    }

}
