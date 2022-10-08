package com.ruse.model.input.impl;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.input.EnterAmount;
import com.ruse.world.entity.impl.player.Player;

public class EnterAmountOfLampsToUse extends EnterAmount {

    public EnterAmountOfLampsToUse() {
    }

    @Override
    public void handleAmount(Player player, int lampAmount) {
        if (lampAmount > 0) {
            TaskManager.submit(new Task(2, false) {
                @Override
                protected void execute() {
                    player.setLampsToUse(lampAmount);
                    player.setInputHandling(new ExperienceLampConfirmation());
                    player.getPA().sendEnterInputPrompt("Are you sure you want to use " + lampAmount + " experience lamps? Enter 'Yes' or 'No':");
               		stop();
                }
            });
        }
    }

}
