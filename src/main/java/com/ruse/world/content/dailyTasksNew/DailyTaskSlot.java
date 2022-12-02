package com.ruse.world.content.dailyTasksNew;

import com.ruse.model.Item;
import com.ruse.world.content.Cases;
import com.ruse.world.entity.impl.player.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyTaskSlot {

    DailyTaskSlot() {
        task = null;
        progressOnTask = 0;
        completed = false;
    }

    private DailyTask task;
    private int progressOnTask;
    private boolean completed;
    private boolean claimedReward;


    public void assignTask(DailyTask task) {
        this.task = task;
        this.progressOnTask = 0;
        this.completed = false;
    }

    public void addProgress(int progress) {
        if (completed)
            return;
        progressOnTask += progress;
    }

    public void checkForCompletion(Player p) {
        if (completed)
            return;
        if (progressOnTask >= task.getAmountNeeded()) {
            p.sendMessage("<col=0264ea>You have completed the Daily Task: " + task.getName().replace("\\n", "") + ".");
            p.sendMessage("<col=0264ea>You can claim the rewards for this task via ::daily!");

            p.setDailyTasksCompleted(p.getDailyTasksCompleted() + 1);

            p.getSeasonPass().addExperience(2);
            Cases.grantCasket(p, 3);

            for (Item item : task.getRewards()) {
                p.giveItem(item.getId(), item.getAmount());
            }
            completed = true;
            claimedReward = true;
        }
    }

}
