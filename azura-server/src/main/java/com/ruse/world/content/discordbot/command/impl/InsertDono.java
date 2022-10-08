package com.ruse.world.content.discordbot.command.impl;

import com.ruse.util.Misc;
import com.ruse.world.content.AddedCommands;
import com.ruse.world.content.serverperks.ServerPerks;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;


public class InsertDono extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        String[] params = e.getMessage().getContentRaw().split("=");
        if (params == null || params.length != 6) {
            e.getMessage().getTextChannel().sendMessage("Invalid entry").queue();
            return;
        }
        int id = Integer.parseInt(params[1]);
        int amount = Integer.parseInt(params[2]);
        String name = params[3];
        String discord = params[4];
        String method = params[5];

        new Thread(new mysql.impl.InsertDono(id, amount, name, discord, method)).start();
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Inserted Donation");
        eb.addField("ID","" +  id, true);
        eb.addField("Amount", "" +  amount, true);
        eb.addField("IGN:",name, true);
        eb.addField("Discord", discord, true);
        eb.addField("Method", method, true);
        eb.setThumbnail(e.getJDA().getSelfUser().getAvatarUrl());
        eb.setColor(new Color(0x296d98));
        e.getChannel().sendMessage(eb.build()).queue();

    }

}
