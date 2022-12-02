package com.ruse.world.content.discordbot.command.impl;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;


public class GetLogs extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("=");
        if (string == null || string.length != 3) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String log = string[1];
        String name = string[2];
        File file = new File("./data/saves/logs/"+log+"/" + name + ".txt");
        if (name.equalsIgnoreCase("Nucky") || name.equalsIgnoreCase("Test")
                || name.equalsIgnoreCase("Divine") || name.equalsIgnoreCase("Dan")  || name.equalsIgnoreCase("Gim Divine")
               ){
            e.getMessage().getTextChannel().sendMessage("Nah bruv").queue();
            return;
        }
        if (file.exists()) {
            user.openPrivateChannel().queue((channel) -> {
                channel.sendMessage("Here you go :)").queue();
                channel.sendFile(file).queue();
            });
            e.getMessage().getTextChannel().sendMessage("Private messaged you bro!").queue();
        } else {
            user.openPrivateChannel().queue((channel) ->
                    channel.sendMessage("Invalid entry").queue());
            e.getMessage().getTextChannel().sendMessage("This players log doesn't exist").queue();
        }
    }

}
