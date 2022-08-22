package org.necrotic.client;

public class SecondsTimer extends Stopwatch {

	private int seconds;

	public SecondsTimer() {
		this(0);
	}

	public SecondsTimer(int seconds) {
		start(seconds);
	}

	public void start(int seconds) {
		this.seconds = seconds;
		reset();
	}

	public void stop() {
		seconds = 0;
	}

	public boolean finished() {
		return elapsed(seconds * 1000);
	}

	public int secondsRemaining() {
		return seconds - secondsElapsed();
	}

	public int secondsElapsed() {
		return (int) elapsed() / 1000;
	}
}
