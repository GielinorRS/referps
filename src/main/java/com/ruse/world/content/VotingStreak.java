package com.ruse.world.content;

import com.ruse.model.Item;
import com.ruse.util.Misc;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Calendar;

public class VotingStreak {

    public static Item[] rewards = new Item[30];

    public static final String REWARDS_FILE_PATH = "./data/saves/dailyrewards.txt";
    @Getter
    @Setter
    private static long dayInYear = 0;

    public static boolean updatingTask;

    public static void sequence() {
        if (dayInYear <= 0) {
            Calendar cal = Calendar.getInstance();
            int day = cal.get(Calendar.DAY_OF_YEAR);
            dayInYear = day;
        }
        if (ZonedDateTime.now().getHour() == 0 && ZonedDateTime.now().getMinute() == 0) {
            if (ZonedDateTime.now().getSecond() == 1 && !updatingTask) {
                updatingTask = true;
                dayInYear += 1;
            } else if (ZonedDateTime.now().getSecond() == 2 && updatingTask) {
                updatingTask = false;
            }
        }
    }
    public static void loadRewards() {
        rewards = new Item[30];
        try {
            BufferedReader r = new BufferedReader(new FileReader(REWARDS_FILE_PATH));
            int index = 0;
            while (true) {
                String line = r.readLine();
                if (line == null) {
                    break;
                } else {
                    line = line.trim();
                }
                String[] code = line.split(" : ");

                rewards[index] = new Item(Integer.parseInt(code[0]), Integer.parseInt(code[1]));
                index++;
            }
            r.close();
        } catch (IOException e) {
            System.err.println("Did not load '" + REWARDS_FILE_PATH + "'");
        }
    }


    private Player player;

    public VotingStreak(Player player) {
        this.player = player;
    }

    @Getter
    @Setter
    private boolean[] daysVoted = new boolean[30];
    @Getter
    @Setter
    private int currentDay = 0;
    @Getter
    @Setter
    private long epochDay = 0;


    public void openInterface() {
        check();

        player.getPacketSender().sendString(149006, "Time till reset: " + Misc.getTimeTillReset());

        int inter = 149008;

        for (int i = 0; i < 30; i++) {
            player.getPacketSender().sendSpriteChange(inter++, daysVoted[i] ? 1607 : currentDay == i ? 1606 : 1563);
            inter++;
            player.getPacketSender().sendItemOnInterface(inter++, rewards[i].getId(), rewards[i].getAmount());
        }

        player.getPacketSender().sendInterface(149000);

        player.sendMessage("@blu@Day " + (currentDay + 1) + " voting streak status: "
                + (daysVoted[currentDay] ? "<col=02940e>You already claimed the daily reward today" : "@red@You must Vote to claim the reward!"));
    }

    public void login() {
        check();

        //openInterface();
        player.sendMessage("@blu@Day " + (currentDay + 1) + " voting streak status: "
                + (daysVoted[currentDay] ? "<col=02940e>You already claimed the daily reward today" : "@red@You must Vote to claim the reward!"));
    }

    public void vote() {
        check();

        if (!daysVoted[currentDay]){
            daysVoted[currentDay] = true;
            openInterface();
            handleReward();
        }else{
            openInterface();
        }
    }

    public void check() {

        if (currentDay <= 5 && daysVoted[currentDay + 3]) {
            currentDay = 0;
            daysVoted = new boolean[30];
        }

        if ((dayInYear - epochDay) > 1) {
            reset();
        }
        if ((dayInYear - epochDay) == 1 && currentDay >= 0 && !daysVoted[currentDay]) {
            reset();
        }

        if (dayInYear != epochDay) {
            epochDay = epochDay + (dayInYear - epochDay);
            currentDay += 1;
        }
        if (currentDay >= 30) {
            currentDay = 0;
            daysVoted = new boolean[30];
        }
    }


    public void handleReward() {
        Item reward = rewards[currentDay];
        player.depositItemBank(reward);
        player.sendMessage("Your Voting Streak rewards have been added to your bank.");
    }

    public void reset() {
        daysVoted = new boolean[30];
        currentDay = 0;
        epochDay = dayInYear;
    }

}
