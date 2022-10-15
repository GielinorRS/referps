package com.ruse.world.entity.impl.player;

import com.ruse.GameSettings;
import com.ruse.model.Locations;
import com.ruse.model.Locations.Location;
import com.ruse.model.Position;
import com.ruse.model.Prayerbook;
import com.ruse.model.RegionInstance.RegionInstanceType;
import com.ruse.model.Skill;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.content.Cases;
import com.ruse.world.content.LoyaltyProgramme;
import com.ruse.world.content.PlayerPanel;
import com.ruse.world.content.PlayerPunishment;
import com.ruse.world.content.achievement.AchievementHandler;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.clan.ClanChatManager;
import com.ruse.world.content.combat.pvp.BountyHunter;
import com.ruse.world.content.serverperks.ServerPerks;
import com.ruse.world.content.skill.impl.construction.House;
import com.ruse.world.entity.impl.GroundItemManager;

import java.awt.*;

public class PlayerProcess {

    /*
     * The player (owner) of this instance
     */
    private Player player;

    /*
     * The loyalty tick, once this reaches 6, the player will be given loyalty
     * points. 6 equals 3.6 seconds.
     */
    private int loyaltyTick;

    /*
     * The timer tick, once this reaches 2, the player's total play time will be
     * updated. 2 equals 1.2 seconds.
     */
    private int timerTick;

    /*
     * Makes sure ground items are spawned on height change
     */
    private int previousHeight;

    public PlayerProcess(Player player) {
        this.player = player;
        this.previousHeight = player.getPosition().getZ();
    }

    public void sequence() {
        long firstTime = System.currentTimeMillis();

        /** COMBAT **/
        player.getCombatBuilder().process();

        if (player!= null && System.currentTimeMillis() - firstTime > 75) {
            DiscordMessager.sendWebhook(player.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - (COMBAT)",
                    Color.GREEN, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
            firstTime = System.currentTimeMillis();
        }

        /** SKILLS **/
        if (player.shouldProcessFarming()) {
            player.getFarming().sequence();
        }

        if (player!= null && System.currentTimeMillis() - firstTime > 75) {
            DiscordMessager.sendWebhook(player.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - (FARMING)",
                    Color.GREEN, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
            firstTime = System.currentTimeMillis();
        }

        /** MISC **/

        if (previousHeight != player.getPosition().getZ()) {
            GroundItemManager.handleRegionChange(player);
            previousHeight = player.getPosition().getZ();
        }


        if (player!= null && System.currentTimeMillis() - firstTime > 75) {
            DiscordMessager.sendWebhook(player.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - (GroundItemManager)",
                    Color.GREEN, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
            firstTime = System.currentTimeMillis();
        }


        if (player.getInterfaceId() == 36000 && player.getAchievements().currentInterface == 3) {
            player.getPA().sendString(36503, "Time Left: " + AchievementHandler.getTimeLeft());
        }
        if (player.getAchievements().getDailyAchievementsDate() != player.getAchievements().getTodayDate()) {
            player.getDailyTaskManager().refresh();
            player.getAchievements().setDailyTaskDate(player.getAchievements().getTodayDate());
//            Achievements.resetDailys(player);
        }

        if (player.getSeasonPassPlaytime().elapsed(60000 * 60)) {//1 hr
            int x = player.getPosition().getX();
            int y = player.getPosition().getY();
            if (player.getLocation() == Location.AFK
                    || player.getLocation() == Location.ZENYTE
                    || player.getLocation() == Location.TANZANITE || player.getLocation() == Location.HOME_BANK) {
                player.getSeasonPassPlaytime().reset();
            } else {
                player.getSeasonPassPlaytime().reset();
                player.getSeasonPass().addExperience(1);
            }
        }

        if (!player.isInActive()) {
            if (loyaltyTick >= 6) {
                LoyaltyProgramme.incrementPoints(player);
                loyaltyTick = 0;
                ClanChatManager.updateClans(player);
                PlayerPanel.refreshPanel(player);
            }
            loyaltyTick++;
        }


        if (player!= null && System.currentTimeMillis() - firstTime > 75) {
            DiscordMessager.sendWebhook(player.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - (Other)",
                    Color.GREEN, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
            firstTime = System.currentTimeMillis();
        }

        /*
         * if(timerTick >= 1) { HANDLED BY PlayerPanel
         * player.getPacketSender().sendString(39166,
         * "@or2@Time played:  @yel@"+Misc.getTimePlayed((player.getTotalPlayTime() +
         * player.getRecordedLogin().elapsed()))); timerTick = 0; }
         */
        timerTick++;
        final Position location = player.getPosition().copy();

        BountyHunter.sequence(player);
        if (location.getX() == 2553 && location.getY() == 3717 && player.getSkillManager().getMaxLevel(Skill.SLAYER) < 98 && player.getSkillManager().getMaxLevel(Skill.SLAYER) < 79) {
            player.moveTo(GameSettings.HOME_CORDS);
            player.getPacketSender().sendMessage(
                    "<shad=1>@or2@You must be 99+ Slayer to do Island Raids.");
            player.getPacketSender().sendMessage("<shad=1>@or1@You must be 80+ Invention to do Island Raids.");
            return;
        }
        if (player.getRegionInstance() != null
                && (player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_HOUSE
                || player.getRegionInstance().getType() == RegionInstanceType.CONSTRUCTION_DUNGEON)) {
            ((House) player.getRegionInstance()).process();
        }

        if (player.getPrayerbook() == Prayerbook.CURSES && player.getLocation() == Location.ZOMBIE_LOBBY || player.getLocation() == Location.ZOMBIE) {
            player.setPrayerbook(Prayerbook.NORMAL);
            player.getPacketSender().sendTabInterface(GameSettings.PRAYER_TAB, player.getPrayerbook().getInterfaceId());
        }

        if (PlayerPunishment.Jail.isJailed(player.getUsername()) && !Locations.Location.inLocation(player, Location.JAIL)) {
            player.moveTo(new Position(2510, 9326));
        }

        if (!player.getCombatBuilder().isAttacking() && !player.getCombatBuilder().isBeingAttacked()) {
            if (!player.getAfkCombatChecker().isAnsweringQuestions())
                player.getAfkCombatChecker().getAfkCombatTimer().reset();
        }

        if (player.getInterfaceId() == 42050) {
            ServerPerks.getInstance().updateInterface(player);
        }

        player.getAfkCombatChecker().check();


        if (player!= null && System.currentTimeMillis() - firstTime > 75) {
            DiscordMessager.sendWebhook(player.getUsername() + " - " + (System.currentTimeMillis() - firstTime) + " ms - (Last)",
                    Color.GREEN, "https://discord.com/api/webhooks/968167481862684723/dUA3FcKteTGjmuG3EgHEIquHVyLL4cxtF3Ee8cAyCCqmg3larFWO_Wtk3raVi4JrztwP");
            firstTime = System.currentTimeMillis();
        }

		/*if (player.afkTicks >= 500 && !player.afk) {
			player.moveTo(new Position(2658, 3987, 0));
			player.afk = true;
		}*/
    }
}
