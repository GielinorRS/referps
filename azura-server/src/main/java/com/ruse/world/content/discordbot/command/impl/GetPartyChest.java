package com.ruse.world.content.discordbot.command.impl;

import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;


public class GetPartyChest extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        if (e.getMessage().getContentRaw().toLowerCase() == null ) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        File file = new File("./data/saves/worldlogs/partychest.txt");

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
