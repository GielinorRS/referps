package com.ruse.util.infologs;

import com.ruse.GameServer;
import com.ruse.GameSettings;
import com.ruse.util.Stopwatch;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerInfoLogs {

    private static final int TIME = 600_000; // 10 minutes
    private static final String FILE_PATH = "./data/saves/info/";
    public static Stopwatch timer = new Stopwatch().reset();

    public static void sequence() {
        if (timer.elapsed(TIME)) {
            timer.reset();
            GameServer.getLoader().getEngine().submit(() -> {
                try {
                    FileWriter fw = new FileWriter(FILE_PATH + "uniqueips.txt", true);
                    if (fw != null) {
                        fw.write(getTime() + getPlayers() + " - " + getUniqueIPS());
                        fw.write(System.lineSeparator());
                        fw.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public static int getUniqueIPS() {
        ArrayList<String> ip = new ArrayList<String>();

        for (Player p : World.getPlayers()) {
            if (p != null) {
                if (p.getHostAddress() != null) {
                    if (!ip.contains(p.getHostAddress()))
                        ip.add(p.getHostAddress());
                }
            }
        }
        return ip.size();
    }

    public static int getPlayers() {
        return World.getPlayers().size() + GameSettings.players;
    }

    public static String getTime() {
        Date getDate = new Date();
        String timeFormat = "M/d/yy hh:mma";
        SimpleDateFormat sdf = new SimpleDateFormat(timeFormat);
        return "[" + sdf.format(getDate) + "] ";
    }
}