package com.ruse.mysql;

import com.ruse.GameSettings;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.content.AchievementsOLD;
import com.ruse.world.content.Cases;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.content.raffle.VotingRaffle;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.VoteBoss;

import java.awt.*;
import java.sql.*;
import java.util.Calendar;

import static com.ruse.world.content.AchievementsOLD.AchievementDataOLD.VOTE_100_TIMES;

public class Voting implements Runnable {

    private static int voteCount = 0;
    public static final String HOST = "necrotic.org";
    public static final String USER = "voting_user";
    public static final String PASS = "whatnibbahoemadedispassw1rd32";
    public static final String DATABASE = "necrotic_voting";

    private Player player;
    private Connection conn;
    private Statement stmt;

    public Voting(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!connect("162.241.194.107", "lunite96_vote", "lunite96_vote", "u48v5Cu@Re!pkRBq&97aQMUVvs$c&uks")) {
                System.out.println("The connection to the voting database has failed!");
                return;
            }

            String name = player.getUsername();

            ResultSet rs = executeQuery("SELECT * FROM votes WHERE username='" + name + "' AND claimed=0 AND voted_on != -1");
            int amount = 0;

            while (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                int siteId = rs.getInt("site_id");
                amount+= 2;

                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }

            name = player.getUsername().replace(" ", "_");
            rs = executeQuery("SELECT * FROM votes WHERE username='" + name + "' AND claimed=0 AND voted_on != -1");

            while (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                int siteId = rs.getInt("site_id");
                amount+= 2;


                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }


            name = player.getUsername().replace(" ", "+");
            rs = executeQuery("SELECT * FROM votes WHERE username='" + name + "' AND claimed=0 AND voted_on != -1");

            while (rs.next()) {
                String ipAddress = rs.getString("ip_address");
                int siteId = rs.getInt("site_id");
                amount+= 2;


                rs.updateInt("claimed", 1); // do not delete otherwise they can reclaim!
                rs.updateRow();
            }

            if (amount > 0) {
                if (amount >= 100){
                    World.sendStaffMessage("<col=FF0066><img=2> [VOTE ABUSE]<col=6600FF> " + player.getUsername()
                            + " tried to claim @red@" + amount + " votes <col=6600FF>- Message Nucky on Discord");
                    DiscordMessager.sendWebhook(player.getUsername() + " - tried to claim " + amount + " votes <@782919638291447819>",
                            Color.RED, "https://discord.com/api/webhooks/982793751053352970/mgBDJJ-q6sHm-gl98Ixk5kS_CVTvSfYRjUkDJEJGHplVfIn_Qpd8WHq9YBaNwR_yBd7Q");
                }else {
                    for (int i = 0; i < amount / 2; i++) {
                        Cases.grantCasket(player, 2);
                    }
                    VotingRaffle.addEntry(player, amount);

                    if (player.getSummoning() != null && player.getSummoning().getFamiliar() != null
                            && player.getSummoning().getFamiliar().getSummonNpc() != null
                            && player.getSummoning().getFamiliar().getSummonNpc().getId() == 8802) {
                        player.getInventory().add(23020, amount);
                        player.getPacketSender()
                                .sendMessage("<shad=1>@yel@You get an extra vote scroll because of your pet!");
                    }
                    if (GameSettings.DOUBLEVOTE) {
                        player.getInventory().add(23020, amount);
                        player.getPacketSender()
                                .sendMessage("<shad=1>@yel@You get an extra vote scroll because of the current event!");
                    }

                    PlayerLogs.logPlayerVotes(player.getUsername(), "Player claimed votes: " + amount + ", IP: " + player.getHostAddress());

                    player.getInventory().add(23020, amount);
                    player.getPacketSender().sendMessage("Thank you for voting!");

                    player.lastVoteTime = System.currentTimeMillis();

                    VoteBoss.setVoteCount(VoteBoss.getVoteCount() + amount);

                    Calendar cal = Calendar.getInstance();
                    int day = cal.get(Calendar.DAY_OF_YEAR);

                    player.setLastVotedDay(day);

                    World.sendMessage("<img=11>" + player.getUsername() + " has voted for " + amount
                            + " points. ::vote now to support the server.");
                    AchievementsOLD.doProgress(player, VOTE_100_TIMES, amount);
                    Achievements.doProgress(player, Achievements.Achievement.SUPPORT_LUNITE);
                    Achievements.doProgress(player, Achievements.Achievement.SUPPORTER);
                    Achievements.doProgress(player, Achievements.Achievement.MEGA_SUPPORTER);
                    player.getProgressionManager().getProgressionTask(11).incrementProgress(1, 1);


                    VoteBoss.spawn();
                    player.getVotingStreak().vote();
                }
            }


            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean connect(String host, String database, String user, String pass) {
        try {
            this.conn = DriverManager.getConnection("jdbc:mysql://" + host + ":3306/" + database, user, pass);
            return true;
        } catch (SQLException e) {
            // System.out.println("Failing connecting to database!");
            return false;
        }
    }

    public void destroy() {
        try {
            conn.close();
            conn = null;
            if (stmt != null) {
                stmt.close();
                stmt = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            int results = stmt.executeUpdate(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public ResultSet executeQuery(String query) {
        try {
            this.stmt = this.conn.createStatement(1005, 1008);
            ResultSet results = stmt.executeQuery(query);
            return results;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

}
