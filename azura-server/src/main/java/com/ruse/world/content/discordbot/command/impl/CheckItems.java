package com.ruse.world.content.discordbot.command.impl;

import com.ruse.model.definitions.ItemDefinition;
import com.ruse.util.Misc;
import com.ruse.world.content.AccountAccess;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;


public class CheckItems extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        User user = e.getMessage().getAuthor();
        String[] string = e.getMessage().getContentRaw().toLowerCase().split("=");
        if (string == null || string.length != 3) {
            user.openPrivateChannel().queue((channel) -> channel.sendMessage("Invalid entry").queue());
            return;
        }
        String item = string[1];
        int amount = Integer.parseInt(string[2]);
        if (amount >= 20)
            amount = 20;
        if (amount <= 0)
            amount = 1;

        int finalAmount = amount;
        new Thread(() -> {
            e.getMessage().getTextChannel().sendMessage(ItemDefinition.forId(Integer.parseInt(item)).getName() + ": Running a scan through inventories, equipments, and banks").queue();

            ArrayList<AccountAccess.ItemThing> listts = AccountAccess.check(Integer.parseInt(item));

            if (listts != null) {
                long total = 0;
                int top = 0;
                for (AccountAccess.ItemThing d : listts) {
                    if (d != null && d.getItem() != null && d.getItem().getAmount() >= 1) {
                        total += d.getItem().getAmount();
                        if (top < finalAmount) {
                            e.getMessage().getTextChannel().sendMessage( d.getName() + " - Name: " + ItemDefinition.forId(d.getItem().getId()).getName() + ", Id: " + d.getItem().getId() +
                                    ", Amount: " + Misc.insertCommasToNumber(d.getItem().getAmount() + "")).queue();
                            top++;
                        }
                    }
                }

                e.getMessage().getTextChannel().sendMessage("Finished scan - Total items:" + total).queue();
            }else{
                e.getMessage().getTextChannel().sendMessage("Null scan").queue();
            }

        }).start();
    }

}
