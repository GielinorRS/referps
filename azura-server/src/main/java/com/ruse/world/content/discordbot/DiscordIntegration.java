package com.ruse.world.content.discordbot;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.util.Misc;
import com.ruse.util.infologs.AccountsCreated;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.PlayerCompletions;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.entity.impl.player.Player;
import mysql.impl.Donation;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorHandler;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.ErrorResponse;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class DiscordIntegration {

    public static Map<String, Long> connectedAccounts = new HashMap<>();
    public static ArrayList<Long> disableMessage = new ArrayList<>();

    public static Map<String, Long> idForCode = new HashMap<>();

    public static String generateCode(int length) {
        String capitalCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String specialCharacters = "!@#$";
        String numbers = "1234567890";
        String combinedChars = capitalCaseLetters + lowerCaseLetters + specialCharacters + numbers;
        Random random = new Random();
        char[] password = new char[length];

        password[0] = lowerCaseLetters.charAt(random.nextInt(lowerCaseLetters.length()));
        password[1] = capitalCaseLetters.charAt(random.nextInt(capitalCaseLetters.length()));
        password[2] = specialCharacters.charAt(random.nextInt(specialCharacters.length()));
        password[3] = numbers.charAt(random.nextInt(numbers.length()));

        for (int i = 4; i < length; i++) {
            password[i] = combinedChars.charAt(random.nextInt(combinedChars.length()));
        }
        return new String(password);
    }

    public static void sendPrivateMessage(User user, TextChannel c, String content) {
        if (GameSettings.LOCALHOST)
            return;
        ErrorHandler handler = new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER, (error) -> {
            c.sendMessage(user.getAsMention() + " You must enable your private messages first!").queue();
        });

        user.openPrivateChannel().queue((channel) -> {
            channel.sendMessage(content).queue(null, handler);
        });
    }


    public static void sendPMS(String content) {
        if (GameSettings.LOCALHOST)
            return;
        System.out.println("sending discord pms");

        Guild guild = Bot.discord.getGuildById(707331223374266378L);
        for (Entry<String, Long> entry : DiscordIntegration.connectedAccounts.entrySet()) {
            Player player = World.getPlayerByName(entry.getKey());
            if (player == null)
                continue;
            Member member = guild.getMemberById(entry.getValue());
            if (member == null) {
                continue;
            }
            if (disableMessage.contains(entry.getValue()))
                continue;

            User user = member.getUser();

            if (user != null) {
                ErrorHandler handler = new ErrorHandler().handle(ErrorResponse.CANNOT_SEND_TO_USER, (error) -> {
                    // c.sendMessage(user.getAsMention() + " You must enable your private messages first!").queue();
                });

                user.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("A new update has just released on Lunite!").queue(null, handler);
                });
                user.openPrivateChannel().queue((channel) -> {
                    channel.sendMessage("https://lunite.io/updates/33.php").queue(null, handler);
                });
            }
        }
    }

    public static void integrateAccount(Player player, String code) {
        if (GameSettings.LOCALHOST)
            return;
        if (player.getDiscordUser() > 0) {
            player.sendMessage("You already have a connected discord account!");
        }

        if (!idForCode.containsKey(code)) {
            player.sendMessage("You have entered an invalid code! Try again.");
            return;
        }

        long userId = idForCode.get(code);

        idForCode.remove(code);

        if (connectedAccounts.containsValue(userId) &&
                connectedAccounts.get(player.getUsername()) !=  userId) {
            player.sendMessage("This discord account is already linked to another player!");
            return;
        }

        String name = Bot.discord.getUserById(userId).getAsTag();

        player.sendMessage("You have connected the discord account '" + name + "'.");
        connectedAccounts.put(player.getUsername(), userId);
        player.setDiscordUser(userId);
        player.setDiscordTag(name);
        updateDiscordInterface(player);

        DiscordMessager.sendDiscordIntegrations(player.getUsername()  + " : " + name + " : " + player.getHostAddress() + " : " + player.getMac());

        if (!PlayerCompletions.discordConnected.contains(player.getUsername())){
            player.incrementAmountDonated(10);
            Donation.checkForRankUpdate(player);
            PlayerPanel.refreshPanel(player);
            player.sendMessage("@mag@You received $10 to your total donated amount for linking your Discord account!");
            PlayerCompletions.addDiscordConnect(player);
        }

    }


    public static void setIntegration(Player player) {
        if (GameSettings.LOCALHOST)
            return;
        if (player.getDiscordUser() > 0
        && player.getDiscordTag() != null) {
            connectedAccounts.put(player.getUsername(), player.getDiscordUser());
            player.setDiscordUser(player.getDiscordUser());
            player.setDiscordTag(player.getDiscordTag());
        }
    }

    public static void loadConnectedAccounts() {
        if (GameSettings.LOCALHOST)
            return;
        File file = new File("./data/saves/discord/discordConnectedAccounts.json");

        try (FileReader fileReader = new FileReader(file)) {
            JsonParser fileParser = new JsonParser();
            Gson builder = new GsonBuilder().create();
            JsonObject reader = (JsonObject) fileParser.parse(fileReader);
            if (reader.has("connectedAccounts")) {
                Map<String, Long> accounts = builder.fromJson(reader.get("connectedAccounts"),
                        new TypeToken<Map<String, Long>>() {
                        }.getType());

                connectedAccounts = accounts;
            }

            if (reader.has("disableMessage")) {
                Long[] pricesData = builder.fromJson(reader.get("disableMessage"), Long[].class);
                for (Long data : pricesData) {
                    disableMessage.add(data);
                }
            }

            System.out.println("Loaded Discord Connected Accounts!");
        } catch (Exception e) {
            System.out.println("Error Loading Discord Connected Accounts!");
        }
    }

    public static void saveConnectedAccounts() {//when does this get called? atm im calling on saveall, shutdownhook etc. Just put it wherever u normally save everything like POS etckkits all setup?
        if (GameSettings.LOCALHOST)
            return;
        File file = new File("./data/saves/discord/discordConnectedAccounts.json");
        try (FileWriter writer = new FileWriter(file)) {
            Gson builder = new GsonBuilder().setPrettyPrinting().create();
            JsonObject object = new JsonObject();

            object.add("connectedAccounts", builder.toJsonTree(connectedAccounts));

            object.add("disableMessage", builder.toJsonTree(disableMessage));


            writer.write(builder.toJson(object));
            writer.close();
            System.out.println("Saved Discord Connected Accounts!");
        } catch (Exception e) {
            System.out.println("Error Saving Discord Connected Accounts!");
        }
    }

    public static void updateDiscordInterface(Player player) {
      /*  if (GameSettings.LOCALHOST)
            return;*/

        if (player.getDiscordUser() <= 0) {
            player.getPA().sendString(19122, "Link Account");
            player.getPA().sendString(19133, "@whi@N/A");
        } else {
            player.getPA().sendString(19122, "Unlink");
            player.getPA().sendString(19133,  "@whi@" + player.getDiscordTag());
        }

        if (disableMessage.contains(player.getDiscordUser())){
            player.getPA().sendConfig(3244,  0);
        }else {
            player.getPA().sendConfig(3244, 1);
        }

        player.sendMessage( "You currently have " + player.getDiscordPoints() + " Discord points");

    }

    public static void buttonClick(Player player) {
        if (player.getDiscordTag() != null && player.getDiscordUser() > 0){
            if (disableMessage.contains(player.getDiscordUser())){
                disableMessage.remove(player.getDiscordUser());
            }else {
                disableMessage.add(player.getDiscordUser());
            }
        }
        updateDiscordInterface(player);
    }

    public static void sendMessage(String message, long channel) {
        if (GameSettings.LOCALHOST)
            return;
        Bot.discord.getTextChannelById(channel).sendMessage(message).queue();
    }

    public static void discordPoint() {
        TaskManager.submit(new Task(500) {
            @Override
            protected void execute() {
               givePoints();
            }
        });

    }



    public static void givePoints() {
                if (Bot.discord != null){
                    Guild guild = Bot.discord.getGuildById(707331223374266378L);

                    for (Entry<String, Long> entry : DiscordIntegration.connectedAccounts.entrySet()) {
                        Player player = World.getPlayerByName(entry.getKey());
                        if (player == null)
                            continue;
                        Member member = guild.getMemberById(entry.getValue());
                        if (member == null) {
                            continue;
                        }
                        if (member.getActivities() == null) {
                            player.increaseDiscordPoints(1);
                            continue;
                        }
                        boolean containsStatus = false;
                        for (Activity a : member.getActivities()) {
                            String status = a.getName().toLowerCase();
                            if (status.contains("lunite")) {
                                containsStatus = true;
                                break;
                            }
                        }

                        if (containsStatus) {
                            player.increaseDiscordPoints(3);
                        } else {
                            player.increaseDiscordPoints(1);
                        }
                    }
                }
    }

}
