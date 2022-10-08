package com.ruse.world.content;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import com.ruse.engine.task.Task;
import com.ruse.engine.task.TaskManager;
import com.ruse.model.Skill;
import com.ruse.util.Misc;
import com.ruse.util.StringUtils;
import com.ruse.world.World;

public class DoubleXPSkillEvent {

	public static Skill currentSkill = pickRandomSkill();

	private static int timeLeft = 7200;//6h in sec

	public static void pickNext() {
		TaskManager.submit(new Task(12000) { // 6h
			@Override
			protected void execute() {
				currentSkill = pickRandomSkill();
				World.sendMessage(
						"@blu@<img=5>[DOUBLE XP]<img=5>@red@ " + StringUtils.capitalizeFirst(currentSkill.toString())
								+ " Has been picked as double xp skill for the next 2 hours.");
			}
		});
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				timeLeft--;
				if (timeLeft <= 0) {
					timer.cancel();
					timeLeft = 21600;
				}
			}
		}, 0, 1000);

	}

	public static String getTimeLeft() {
		long ms = timeLeft * 1000L;
		return String.format("%dhr %dm %ds", TimeUnit.MILLISECONDS.toHours(ms),
				TimeUnit.MILLISECONDS.toMinutes(ms) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(ms)),
				TimeUnit.MILLISECONDS.toSeconds(ms) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(ms)));
	}
	
	
	private static Skill pickRandomSkill() {
		int random = Misc.random(7, 23);
		return Skill.values()[random];
	}

}
