package com.ruse.engine.task.impl.globalevents;

import com.ruse.GameSettings;
import com.ruse.engine.task.Task;
import com.ruse.util.Misc;
import com.ruse.webhooks.discord.DiscordMessager;
import com.ruse.world.World;
import com.ruse.world.entity.impl.player.Player;

public class GlobalDoubleVPTask extends Task {

    public GlobalDoubleVPTask() {
        super(1, 0, true);
    }

    int timer = 0;

    @Override
    protected void execute() {
        if (timer < 1) {
            timer = 6000;
            World.sendMessage("2X Voting event has started for 60 minutes, make sure to vote!");
            GameSettings.DOUBLE_VOTE = true;
        } else if(timer == 1) {
            World.sendMessage("2X Voting event has ended!");
            GameSettings.DOUBLE_VOTE = false;
            stop();
        } else {
            timer--;
        }
    }
}