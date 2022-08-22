package org.necrotic.client;

public class EffectTimer {

	private final int itemId;
	private SecondsTimer secondsTimer;
	private int time;

	public EffectTimer(int seconds, int itemId) {
		this.itemId = itemId;
		this.time = seconds;
		secondsTimer = new SecondsTimer(seconds);
	}

	public int getItemId() {
		return itemId;
	}

	public void setSeconds(int seconds) {
		secondsTimer.start(seconds);
	}

	public SecondsTimer getSecondsTimer() {
		return secondsTimer;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}
}