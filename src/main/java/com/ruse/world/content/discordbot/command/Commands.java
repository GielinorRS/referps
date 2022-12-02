package com.ruse.world.content.discordbot.command;

import com.ruse.world.content.discordbot.Bot;
import com.ruse.world.content.discordbot.command.impl.*;
import lombok.Getter;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;


/*
 * @project Vanity-Server
 * @author Patrity - https://github.com/Patrity
 * Created on - 4/13/2020
 */
@Getter
public enum Commands {

    //User commands
    GLOBALS("globals", "Displays globals", new Globals(), null),
    PERKS("perks", "Displays perk progress", new Perks(), null),
    PLAYERS("players", "Displays online player count", new Players(), null),

    INSERTDONO("insert", "adds donation", new InsertDono(), new String[]{Bot.OWNER_ROLE}),
    ADDCOMMAND("addcommand", "adds command", new AddCommand(), new String[]{Bot.OWNER_ROLE, Bot.MANAGER_ROLE}),
    STORE("store", "Links to the store", new Store(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE}),
    PLAYER_LOGS("getlogs", "Gets a players logs", new Logs(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE, Bot.HEAD_ADMIN_ROLE, Bot.ADMIN_ROLE}),
    PLAYER_PASS("getpass", "Gets a player file", new PlayerFile(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE, Bot.HEAD_ADMIN_ROLE}),
    GIVE_ITEM("giveitem", "Give an item to a player", new GiveItem(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE}),

    GET_REFS("getrefs", "Gives referrals", new GetRefs(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE}),

    WORLD_LOGS("getworldlog", "Gets world logs", new GetWorldLogs(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE}),
    GET_UNIQUE("getunique", "Gets world logs", new GetUnique(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE}),

    LOGS("getlog", "Gets a players logs", new GetLogs(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE, Bot.HEAD_ADMIN_ROLE, Bot.ADMIN_ROLE}),

    PARTY_CHEST("getpartychest", "Gives party chest", new GetPartyChest(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE, Bot.ADMIN_ROLE}),

    BAN("ban", "Gives party chest", new Ban(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE}),
    UNBAN("unban", "Gives party chest", new UnBan(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE}),
    SEARCH("search", "Gives party chest", new Search(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE}),
    CHECK_ITEMS("checkitems", "Gives party chest", new CheckItems(), new String[]{Bot.OWNER_ROLE, Bot.DEVELOPER_ROLE, Bot.MANAGER_ROLE}),
    ;

    private final String command, description;
    private final ListenerAdapter adapter;
    private final String[] rolesCanUse;

    public static final Commands[] VALUES = Commands.values();

    public static String prefix = Bot.PREFIX;

    Commands(String command, String description, ListenerAdapter adapter, String[] rolesCanUse) {
        this.command = command;
        this.description = description;
        this.adapter = adapter;
        this.rolesCanUse = rolesCanUse;
    }

    public static Commands isCommand(GuildMessageReceivedEvent e) {
        String text = e.getMessage().getContentRaw().toLowerCase();
        for (Commands command : Commands.VALUES) {
            if (text.contains(prefix + command.getCommand())) {
                for (Role roles : e.getMember().getRoles()) {
                    if (command.getRolesCanUse() == null)
                        return command;

                    if (command == PLAYER_PASS) {
                        if (roles.getId().contains("945869673306656829")
                        || roles.getId().contains("945871923605287002")) {
                            return command;
                        }
                    }

                    for (String role : command.getRolesCanUse()) {
                        if (roles.getId().contains(role == "802140111332311042" ? "824740072058257449" : role)) {
                            return command;
                        }
                    }
                }
            }
        }
        return null;
    }
}
