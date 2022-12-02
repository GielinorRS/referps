package com.ruse.world.content.discordbot.command.impl;

import com.ruse.world.content.AddedCommands;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


public class AddCommand extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] params = e.getMessage().getContentRaw().split("==");
        if (params == null || params.length != 3) {
            e.getMessage().getTextChannel().sendMessage("Invalid entry").queue();
            return;
        }
        String command = params[1];
        String link = params[2];

        AddedCommands.addCommand(command, link);
        e.getMessage().getTextChannel().sendMessage("Added command ::" + command + " - Directs to: https://www.youtube.com/" + link).queue();
    }

}
