package com.gantzgulch.openclock.swt.app.clock;

import java.util.TimerTask;

public class ClockTimerTask extends TimerTask {

	private final Clock[] clocks;

	public ClockTimerTask(final Clock ... clocks) {
		this.clocks = clocks;

	}

	@Override
	public void run() {
		for(final Clock clock : clocks) {
			clock.update();
		}
	}

}
