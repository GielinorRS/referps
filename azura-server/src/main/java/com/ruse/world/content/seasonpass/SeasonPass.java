package com.ruse.world.content.seasonpass;


import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.awt.*;
import java.util.Calendar;

public class SeasonPass {

    public static int SEASON;
    public static int SEASONEND;

    @Getter
    private Player player;
    @Getter
    @Setter
    private int tier = 1;
    @Getter
    @Setter
    private int xp = 0;
    @Getter
    @Setter
    private boolean member;
    @Getter
    @Setter
    public int currentSeason;

    @Getter
    @Setter
    private boolean received500KCXP;

    public SeasonPass(Player player) {
        this.player = player;
        this.currentSeason = SEASON;
    }

    public void openInterface() {

        Calendar cal = Calendar.getInstance();
        int day = cal.get(Calendar.DAY_OF_YEAR);
        int daysLeft = SEASONEND - day;

        int xpNeeded = 10 + ((this.tier / 5) * 2);

        player.getPacketSender().sendString(105007, "XP: " + getXp() + "/" + xpNeeded + "");
        player.getPacketSender().sendString(105008, "" + (getTier() > 50 ? "Complete" : getTier()));
        player.getPacketSender().sendString(105009, "SEASON " + SEASON);
        player.getPacketSender().sendString(105010, "Season Ends: " + daysLeft + " days");


        double number = (double) getXp() / (double) xpNeeded * 100;
        if (number >= 100)
            number = 100;

        player.getPacketSender().sendProgressBar(105012, 0, (int) number, 0);

        int index = 0;
        int interfaceId = 105105;
        for (int i = 0; i < 50; i++) {
            if (PassRewards.defaultRewards.length > index) {
                player.getPacketSender().sendItemOnInterface(interfaceId++, PassRewards.defaultRewards[index].getId(),
                        PassRewards.defaultRewards[index].getAmount());
            } else {
                player.getPacketSender().sendItemOnInterface(interfaceId++, -1, 1);
            }

            if (PassRewards.memberRewards.length > index) {
                player.getPacketSender().sendItemOnInterface(interfaceId++, PassRewards.memberRewards[index].getId(),
                        PassRewards.memberRewards[index].getAmount());
            } else {
                player.getPacketSender().sendItemOnInterface(interfaceId++, -1, 1);
            }

            player.getPacketSender().sendConfig(1714 + index, tier > i + 1 ? 1 : 0);
            if (member)
                player.getPacketSender().sendConfig(1814 + index, tier > i + 1 ? 1 : 0);
            else
                player.getPacketSender().sendConfig(1814 + index, 0);

            interfaceId += 6;
            index++;
        }

        player.getPacketSender().sendInterface(105000);

    }

    public void addExperience(int exp) {
        if (this.tier > 50)
            return;
        this.xp += exp;

        int xpNeeded = 10 + ((this.tier / 5) * 2);

        while (this.xp >= xpNeeded) {
            this.xp -= xpNeeded;
            levelUp();
        }
    }

    public void levelUp() {
        if (this.tier <= 0)
            this.tier = 1;

        if (this.tier < 51) {
            grantRewards();
            this.tier += 1;

            if (this.tier == 51) {
                player.sendMessage("<col=660000>[Season pass]<col=100666>You have completed the Season pass!");
                World.sendMessage1("<col=660000>[Season pass]<col=100666>" + player.getUsername() + " has just completed the Season pass!");

                if (player != null && player.getMac() != null && player.getHostAddress() != null && player.getGameMode() != null && player.getGameMode().toString() != null)
                    DiscordMessager.sendWebhook(player.getUsername() + " - " + member + " - " + player.getGameMode().toString() + " - " + player.getHostAddress() + " - " + player.getMac(), Color.CYAN,
                            "https://discord.com/api/webhooks/958883095300435991/z3fD0bXFgILF1R98aYM9buDoZD7PTwRegRSSZijQNzEtptu7i8euILau29pmqMQel7Jc");
                return;
            }

        }

        if (this.tier == 51) {
            return;
        }

        player.sendMessage("<col=660000>[Season pass]<col=100666>You are now Tier " + this.tier);

        if (this.tier == 25) {
            World.sendMessage1("<col=660000>[Season pass]<col=100666>" + player.getUsername() + " has just reached tier " + this.tier + "!");
        }

    }

    public void grantRewards() {
        player.depositItemBank(PassRewards.defaultRewards[tier - 1]);
        if (isMember())
            player.depositItemBank(PassRewards.memberRewards[tier - 1]);
        player.sendMessage("Your rewards have been sent to your bank");
    }


    public void grantMembership() {
        if (isMember()) {
            player.sendMessage("<col=660000>[Season pass]<col=100666>You are already a Season pass gold member.");
            return;
        }
        player.sendMessage("<col=660000>[Season pass]<col=100666>You are now a Season pass gold member.");
        setMember(true);

        if (tier > 1) {
            for (int i = 0; i < getTier() - 1; i++) {
                player.depositItemBank(PassRewards.memberRewards[i]);
            }

            player.sendMessage("Your gold pass rewards have been sent to your bank");
        }
    }

    public void handleLogin() {
        player.getSeasonPassPlaytime().reset();
        if (SEASON != currentSeason) {
            player.sendMessage("<col=660000>[Season pass]<col=100666>A new Season pass has begun!");
            tier = 0;
            xp = 0;
            member = false;
            currentSeason = SEASON;
        }
        if (this.tier <= 0)
            this.tier = 1;
    }

    public void information() {
        for (int i = 8145; i < 8196; i++)
            player.getPacketSender().sendString(i, "");

        player.getPacketSender().sendInterface(8134);

        player.getPacketSender().sendString(8136, "Close window");
        player.getPacketSender().sendString(8144, "Season Pass information");
        player.getPacketSender().sendString(8145, "");
        int index = 8147;
        String color = "@dre@";
        String color1 = "@red@";

        player.getPacketSender().sendString(index++, color1 + "Ways to gain Season pass EXP:");
        player.getPacketSender().sendString(index++, color + "Claiming vote scroll - 3xp");
        player.getPacketSender().sendString(index++, color + "Every 500 npc kills - 1xp");
        player.getPacketSender().sendString(index++, color + "1hr playtime (Not in AFK Zone) - 1xp");
        player.getPacketSender().sendString(index++, color + "Easy Slayer task - 1xp");
        player.getPacketSender().sendString(index++, color + "Medium Slayer task - 2xp");
        player.getPacketSender().sendString(index++, color + "Hard Slayer task - 3xp");
        player.getPacketSender().sendString(index++, color + "Boss Slayer task - 4xp");
        player.getPacketSender().sendString(index++, color + "Daily achievement - 2xp");
        player.getPacketSender().sendString(index++, color + "Lvl 120 non-combat skill - 3xp");
        player.getPacketSender().sendString(index++, color + "Completing a Raid - 2xp");
        player.getPacketSender().sendString(index++, color + "Pest Control run - 2xp");
    }

}
