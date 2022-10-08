package com.ruse.world.content.discordbot;

import com.ruse.GameSettings;
import com.ruse.world.World;
import com.ruse.world.content.discordbot.command.Commands;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.util.Map;
import java.util.Objects;

public class Bot extends ListenerAdapter {

    private static String TOKEN = "OTI4MTczNTkzNzI3MjA5NDcy.YdU7BQ.3gDt66MJMRef-8v6ZtgIks6ayzU";
    public static String PREFIX = "::";
    public static String OWNER_ROLE = "707331223382523932";
    public static String MANAGER_ROLE = "829070710243983400";
    public static String HEAD_ADMIN_ROLE = "915773183985078292";
    public static String DEVELOPER_ROLE = "845959139984670742";
    public static String ADMIN_ROLE = "800841633188937739";
    public static String GLOBAL_MOD_ROLE = "824740072058257449";

    private static int TIMER = 60;

    public static JDA discord;

    public void init() {
        try {
            discord = JDABuilder.create(TOKEN, GatewayIntent.getIntents(GatewayIntent.ALL_INTENTS))
                    .setToken(TOKEN)
                    .setActivity(Activity.watching("Lunite.io"))
                    .build()
                    .awaitReady(); // Blocking guarantees that JDA will be completely loaded.
            discord.addEventListener(this);
            discord.getGuilds().forEach(g -> g.loadMembers());
        } catch (LoginException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onGuildMessageReceived(GuildMessageReceivedEvent e) {
        if (GameSettings.LOCALHOST)
            return;

        if (e.getAuthor().isBot()) {
            return;
        }

        if (e.getChannel().getIdLong() == 1025413262428340294L) {

            if (e.getMessage().getContentDisplay().equalsIgnoreCase("!connect")) {
                User user = e.getAuthor();

                if (DiscordIntegration.connectedAccounts.containsValue(user.getIdLong())) {
                    DiscordIntegration.sendPrivateMessage(user, e.getChannel(), "This discord account is already connected to another in-game account!");
                }

                if (DiscordIntegration.idForCode.containsValue(user.getIdLong())) {
                    String code = null;
                    for (Map.Entry<String, Long> entry : DiscordIntegration.idForCode.entrySet()) {
                        if (entry.getValue() == user.getIdLong()) {
                            code = entry.getKey();
                        }
                    }

                    if (code == null) {
                        code = DiscordIntegration.generateCode(4);
                    }

                    DiscordIntegration.sendPrivateMessage(user, e.getChannel(), "Hello! You have already generated a code! Enter the following in the discord integration prompt:\n"
                            + code);
                    return;
                }

                String code = DiscordIntegration.generateCode(4);

                while (DiscordIntegration.idForCode.containsKey(code)) {
                    code = DiscordIntegration.generateCode(4);
                }

                DiscordIntegration.idForCode.put(code, e.getAuthor().getIdLong());

                DiscordIntegration.sendPrivateMessage(user, e.getChannel(),
                        "Hello! To connect your discord account to your in-game account, enter the following in the discord integration prompt:\n"
                                + code);

              //  e.getMessage().delete().queue();
            }

        }

        Commands command = Commands.isCommand(e);

        if (Objects.isNull(command)) {
            return;
        }

        command.getAdapter().onGuildMessageReceived(e);
    }

    private static int countDown = 0;

    public static void updatePlayers() {
        if (GameSettings.LOCALHOST)
            return;
        if (countDown == TIMER) {
            int players = (int) World.getPlayers().size() + GameSettings.players;
            discord.getPresence().setActivity(Activity.watching((players) + " Players!"));
            countDown = 0;
        } else {
            countDown++;
        }
    }
}
