package com.ruse.world.content;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream; // something is fked up w mouse wheel scroll its like 10x slower lol

import com.ruse.model.Item;
import com.ruse.world.entity.impl.player.Player;

/**
 * 
 * @Author Suic
 * @Date 11.23.2019
 */

public class ProgressionManager {

	private Player player;

	public ProgressionManager(Player player) {
		this.player = player;
	}

	private List<ProgressionTask> progressions = new ArrayList<>();

	public List<ProgressionTask> getProgressions() {
		return progressions;
	}

	public void setProgressions(List<ProgressionTask> progressions) {
		this.progressions = progressions;
	}

	public void setProgression(int index, ProgressionTask progressionTask) {
		progressions.set(index, progressionTask);
	}

	public ProgressionTask getProgressionTask(int index) {
	//	// System.out.println("Progression tasks: " + progressions.toString());
		return progressions.get(index);
	}

	private Item[][] rewards = new Item[][] { 
			{ new Item(12855, 5000), new Item(21018, 1), new Item(5500, 1) },//Finishing Touch
			{ new Item(18834, 2), new Item(18801, 2), new Item(18800, 2) }, //Hidden Valley
			{ new Item(15290, 2), new Item(7956, 10), new Item(19116, 1) }, //Upgradrable
			{ new Item(6199, 120), new Item(19116, 3), new Item(12855, 7420) }, //Cursed floor
			{ new Item(19115,1), new Item(19116, 1), new Item(7120, 2) }, //store manager
			{ new Item(19114, 1), new Item(21058, 1), new Item(19670, 1) }, //young master
			{ new Item(11001, 1), new Item(11002, 1), new Item(11003, 1) }, //advanced master
			{ new Item(7120, 3), new Item(5500, 5), new Item(21218, 10) }, //multiplier
			{ new Item(21050, 1), new Item(21051, 1), new Item(21052, 1) }, //Summoner
			{ new Item(15401, 1), new Item(12855, 25000), new Item(7120, 4) }, //Might master
			{ new Item(19670, 25), new Item(6769, 1), new Item(18418, 1) }, //Spree master
			{ new Item(7120, 3), new Item(19116, 3), new Item(19115, 2) }, //Permanent
			{ new Item(10835, 69), new Item(19114, 3), new Item(20488, 2) }, //legion master
			{ new Item(21218, 20), new Item(19115, 5), new Item(7120, 6) }, //looterbooter
			{ new Item(19994, 1), new Item(20488, 2), new Item(19989, 1) }, //supreme master
			{ new Item(7120, 5), new Item(19988, 1), new Item(19992, 1) }, //dissolver
			{ new Item(19984, 1), new Item(19985, 1), new Item(19986, 1) } //Grand Master
	};
	public void open() {
		player.getPacketSender().sendInterface(19720);
		sendData();
		int slot = 0;
		for (int i = 0; i < rewards.length; i++) {
			player.getPacketSender().sendItemOnInterface(20125, rewards[i][0].getId(), slot++,
					rewards[i][0].getAmount());
			player.getPacketSender().sendItemOnInterface(20125, rewards[i][1].getId(), slot++,
					rewards[i][1].getAmount());
			player.getPacketSender().sendItemOnInterface(20125, rewards[i][2].getId(), slot++,
					rewards[i][2].getAmount());
		}
	}

	private void sendData() {
		for (int i = 0; i < progressions.size(); i++) {
			ProgressionTask task = getProgressionTask(i);
			player.getPacketSender().sendString(19785 + i, task.getProgressionName());

			player.getPacketSender().sendString(19830 + i, task.getName(0));
			player.getPacketSender().sendString(19860 + i, task.getName(1));
			player.getPacketSender().sendString(19890 + i, task.getName(2));

			player.getPacketSender().sendString(20030 + i,
					String.valueOf(getPercent(task.getProgress(0), task.getAmount(0))) + "%");

			player.getPacketSender().sendString(20060 + i,
					String.valueOf(getPercent(task.getProgress(1), task.getAmount(1))) + "%");

			player.getPacketSender().sendString(20090 + i,
					String.valueOf(getPercent(task.getProgress(2), task.getAmount(2))) + "%");
		}

	}

	private int getPercent(int currentNumber, int maxNumber) {
		return (currentNumber * 100) / maxNumber > 100 ? 100 : (currentNumber * 100) / maxNumber;
		// amount is the current progress multiplied by 100, then divided by the max amount eg 5000
		// if % is greater than 100, it'll still return 100 which is what this part does
		// so yes it works perfectly, in fact i tested it.
		//nice so this is the part of the code i use to enter the progression right 
	}

	public void claim(int index) {
		ProgressionTask task = getProgressionTask(index);
		if (task.hasCompleted) {
			player.sendMessage("You've already claimed the reward for this one.");
			return;
		}
		boolean isCompleted = task.allCompleted();

		if (isCompleted) {
			giveReward(index);
			task.hasCompleted = true;
		} else {
			player.sendMessage("You haven't completed all the tasks for this reward yet");
		}
	}

	public boolean handleButton(int id) {

		if (!(id >= 19960 && id <= 19989)) {
			return false;
		}

		int index = -1;

		if (id >= -19960) {
			index = -19960 + id;
		}

		claim(index);

		return true;

	}

	private void giveReward(int index) {
		player.getInventory().addItemSet(new Item[] { (rewards[index][0]), rewards[index][1], rewards[index][2] });
	}

	public void loadData() {

		Path dataPath = Paths.get("data", "progressiontasks.txt");
		try (Stream<String> lines = Files.lines(dataPath)) {
			lines.forEach(line -> {
				String[] data = line.split(",");
				String progressionName = data[0];
				String[] taskNames = new String[] { data[1], data[2], data[3] };
				int[] taskAmounts = new int[] { Integer.parseInt(data[4]), Integer.parseInt(data[5]),
						Integer.parseInt(data[6]) };
				progressions.add(new ProgressionTask(progressionName, taskNames, taskAmounts));
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
