package com.ruse.world.content.startertasks;

import com.ruse.world.entity.impl.player.Player;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

public class StarterTaskHandler {
    private static final int MAXIMUM_TIER_ACHIEVEMENTS = 20;
    private final Player player;
    private int[] amountRemaining = new int[MAXIMUM_TIER_ACHIEVEMENTS];
    private boolean[] completed = new boolean[MAXIMUM_TIER_ACHIEVEMENTS];

    public StarterTaskHandler(Player player) {
        this.player = player;
    }

    /**
     * Gets tomorrow's date
     */
    public static String getTimeLeft() {
        final Calendar cal = Calendar.getInstance();
        cal.setTimeZone(TimeZone.getTimeZone("EST"));
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        cal.add(Calendar.DATE, 1);

        final long millis = cal.getTimeInMillis() - System.currentTimeMillis();

        return String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis),
                TimeUnit.MILLISECONDS.toMinutes(millis)
                        - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)),
                TimeUnit.MILLISECONDS.toSeconds(millis)
                        - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
    }

    public void openInterface() {
        int index = 148052;
        for (StarterTasks.StarterTask data : StarterTasks.StarterTask.values()) {

            player.getPacketSender().updateInterfaceVisibility(index++, completed[data.ordinal()] ? false : true);
            player.getPacketSender().sendString(index++, completed[data.ordinal()] ? "Claimed" : "");

            player.getPacketSender().sendString(index++, data.getDescription());

            int amount = getAmountRemaining(data.ordinal());//Grabbing the amount completed
            int percentage = (int) ((100 * (double) amount) / (double) data.getAmount());//percentage calculator
            if (amount > data.getAmount()) {
                amount = data.getAmount();
                percentage = 100;
            }


            player.getPacketSender().sendProgressBar(index++, 0, percentage, 0);
            player.getPacketSender().sendString(index++, "(" + amount + "/" + data.getAmount() + ")");
            player.getPacketSender().sendItemOnInterface(index++, data.getRewards()[0].getId(), data.getRewards()[0].getAmount());
            player.getPacketSender().sendItemOnInterface(index++, data.getRewards()[1].getId(), data.getRewards()[1].getAmount());
            player.getPacketSender().sendItemOnInterface(index++, data.getRewards()[2].getId(), data.getRewards()[2].getAmount());
            index += 1;
        }
        player.getPacketSender().setScrollBar(148050, 2 + (StarterTasks.StarterTask.values().length * 60));
        player.getPacketSender().sendInterface(148000);

    }

    public boolean hasCompletedAll() {
        int amount = 0;
        for (StarterTasks.StarterTask starterTask : StarterTasks.StarterTask.values()) {
            if (isComplete(starterTask.ordinal()))
                amount++;
        }
        return amount == StarterTasks.getMaxTasks();
    }

    public boolean isComplete(int index) {
        return completed[index];
    }

    public void setComplete(int index, boolean state) {
        this.completed[index] = state;
    }

    public boolean[] getCompleted() {
        return completed;
    }

    public void setCompleted(boolean[] completed) {
        this.completed = completed;
    }

    public int getAmountRemaining(int index) {
        return amountRemaining[index];
    }

    public int[] getAmountRemaining() {
        return amountRemaining;
    }

    public void setAmountRemaining(int[] amountRemaining) {
        this.amountRemaining = amountRemaining;
    }

    public void setAmountRemaining(int index, int amountRemaining) {
        this.amountRemaining[index] = amountRemaining;
    }

    public int getTodayDate() {
        Calendar cal = new GregorianCalendar();
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        return (month * 100 + day);
    }


    public boolean handleButtonClick(int buttonId) {
        if (buttonId >= 148052 && buttonId <= 148200) {
            complete((buttonId - 148052) / 9);
            return true;
        }
        return false;
    }

    private void complete(int starterTask) {
        for (StarterTasks.StarterTask ach : StarterTasks.StarterTask.values()) {
            if (starterTask == ach.ordinal()) {
                StarterTasks.claimReward(player, ach);
            }
        }

    }

}
