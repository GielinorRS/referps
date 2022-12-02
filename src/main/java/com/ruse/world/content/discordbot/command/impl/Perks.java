package com.ruse.world.content.discordbot.command.impl;

import com.ruse.util.Misc;
import com.ruse.world.content.serverperks.ServerPerks;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

/*
 * @project Vanity-Server
 * @author Patrity - https://github.com/Patrity
 * Created on - 4/13/2020
 */
public class Perks extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Perks");
        eb.setDescription("Active Perk: " + (ServerPerks.getInstance().getActivePerk() == null ? "N/A" : ServerPerks.getInstance().getActivePerk().getName()));

            eb.addField("Currently Contributed:", Misc.formatNumber(ServerPerks.getInstance().contributed)
                    + "/" + Misc.formatNumber(ServerPerks.getInstance().COST), true);

        eb.setThumbnail(e.getJDA().getSelfUser().getAvatarUrl());
        eb.setColor(new Color(0x296d98));
        e.getChannel().sendMessage(eb.build()).queue();
    }
}
