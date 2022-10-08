package com.ruse.world.content.discordbot.command.impl;

import com.ruse.GameSettings;
import com.ruse.motivote3.doMotivote;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.content.AfkStallSystem;
import com.ruse.world.content.GokuSystem;
import com.ruse.world.content.skeletalhorror.Prime;
import com.world.content.globalBoss.*;
import com.world.content.globalBoss.exoden.GoldenOozau;
import com.world.content.globalBoss.hulk.Zamasu;
import com.world.content.globalBoss.iron.Iron;
import com.world.content.globalBoss.onyx.OnyxSpawn;
import com.world.content.globalBoss.zenyte.ZenyteSpawn;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;

/*
 * @project Vanity-Server
 * @author Patrity - https://github.com/Patrity
 * Created on - 4/13/2020
 */
public class Globals extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        EmbedBuilder eb = new EmbedBuilder();
        eb.setTitle("Globals");

        eb.addField("Vote Boss:", (VoteBoss.getVoteCount() <= 59 ? VoteBoss.getVoteCount() + "/60" : "Alive"), true);
        eb.addField("EarthQuake:", AfkStallSystem.getLeft() + " steals left", true);
        eb.addField("Goku:", Misc.insertCommasToNumber(String.valueOf(GokuSystem.getKillsLeft())) + " kills left", true);

        eb.addField("Prime:", (!Prime.alive ? Prime.getTimeLeft() : "Alive"), true);
        eb.addField("Ooazu:", (!GoldenOozau.alive ? GoldenOozau.getTimeLeft() : "Alive"), true);
        eb.addField("Zamasu:", (!Zamasu.alive ? Zamasu.getTimeLeft() : "Alive"), true);
        eb.addField("Guardian:", LuniteGuardian.getRemaining(), true);
        eb.addField("Hanto:", HantoWarrior.getRemaining(), true);
        eb.addField("Iron:", (!Iron.bossAlive ? Iron.getTimeLeft() : "Alive"), true);
        eb.addField("Onyx:", (!OnyxSpawn.wyrmAlive ? OnyxSpawn.getTimeLeft() : "Alive"), true);
        eb.addField("Zenyte:", (!ZenyteSpawn.bossAlive ? ZenyteSpawn.getTimeLeft() : "Alive"), true);
        eb.addField("Tanzanite:", (!TanzaniteSpawn.bossAlive ? TanzaniteSpawn.getTimeLeft() : "Alive"), true);
        eb.addField("Alchemical Hydra:", (!HydraSpawn.bossAlive ? HydraSpawn.getTimeLeft() : "Alive"), true);

       // eb.addField("Uncle Sam:", (!UncleSamSpawn.bossAlive ? UncleSamSpawn.getTimeLeft() : "Alive"), true);

        eb.setThumbnail(e.getJDA().getSelfUser().getAvatarUrl());
        eb.setColor(new Color(0x296d98));
        e.getChannel().sendMessage(eb.build()).queue();
    }
}
