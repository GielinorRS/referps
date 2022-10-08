package com.ruse.mysql;

import com.ruse.GameSettings;
import com.ruse.world.World;
import com.ruse.world.content.AchievementsOLD;
import com.ruse.world.content.PlayerLogs;
import com.ruse.world.content.achievement.Achievements;
import com.ruse.world.entity.impl.player.Player;
import com.world.content.globalBoss.VoteBoss;

import java.sql.*;
import java.sql.Date;
import java.util.*;

import static com.ruse.world.content.AchievementsOLD.AchievementDataOLD.VOTE_100_TIMES;

public class VotingTopList implements Runnable {

    private Player player;
    private Connection conn;
    private Statement stmt;

    public VotingTopList(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        try {
            if (!connect("107.178.78.242", "pilfirgk_vote", "pilfirgk_vote", "5VErR@Y*pYco")) {
                System.out.println("The connection to the voting database has failed!");
                return;
            }
            //"SELECT * FROM payments WHERE YEAR(dateline) = YEAR(CURDATE()) AND MONTH(dateline) = MONTH(CURDATE()) AND status='Completed'"
            ResultSet rs = executeQuery("SELECT * FROM votes WHERE voted_on > 0 AND claimed = 1");
            int amount = 0;


            HashMap<String, Integer> votess = new HashMap<>();

            while (rs.next()) {
                String username = rs.getString("username").toLowerCase();
                long voted_on = rs.getLong("voted_on");
                amount++;
                Date date = new Date(voted_on * 1000);
                if (date.getMonth() == 7 && date.getDay() >= 1){
                    if (votess.containsKey(username))
                        votess.put(username, votess.get(username) + 1);
                    else
                        votess.put(username, 1);
                }

               /* if (username != null) {
                    if (username.equalsIgnoreCase("itappedthat")
                            || username.equalsIgnoreCase("kage1ka")
                            || username.equalsIgnoreCase("disguy13")
                            || username.equalsIgnoreCase("cylo")
                            || username.equalsIgnoreCase("gotbaconeh")
                            || username.equalsIgnoreCase("lil_dex")
                            || username.equalsIgnoreCase("king pr0d")
                            || username.equalsIgnoreCase("guthan121")
                            || username.equalsIgnoreCase("fishing")
                            || username.equalsIgnoreCase("real_staker")
                            || username.equalsIgnoreCase("farmer_john")
                            || username.equalsIgnoreCase("callmerocc")
                            || username.equalsIgnoreCase(" ")
                            || username.equalsIgnoreCase("")) {
                        rs.updateInt("claimed", 1);
                        rs.updateRow();
                    }
                }
*/

            }
            System.out.println("amount : " + amount);

            votess = sortByValue(votess);

            for (String name :  votess.keySet()){
                System.out.println(name + " : " + votess.get(name));
            }

            destroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static HashMap<String, Integer>    sortByValue(HashMap<String, Integer> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<String, Integer> > list
                = new LinkedList<Map.Entry<String, Integer> >(
                hm.entrySet());

        // Sort the list using lambda expression
        Collections.sort(
                list,
                (i1,
                 i2) -> i1.getValue().compareTo(i2.getValue()));

        // put data from sorted list to hashmap
        HashMap<String, Integer> temp
                = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
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
