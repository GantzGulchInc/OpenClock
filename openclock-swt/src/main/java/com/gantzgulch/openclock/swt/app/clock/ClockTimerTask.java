package com.gantzgulch.openclock.swt.app.clock;

import java.util.List;
import java.util.TimerTask;

public class ClockTimerTask extends TimerTask {

	private final List<Clock> clocks;

	public ClockTimerTask(final List<Clock> clocks) {
		this.clocks = clocks;
	}

	@Override
	public void run() {
		for (final Clock clock : clocks) {
			clock.update();
		}
	}

}
