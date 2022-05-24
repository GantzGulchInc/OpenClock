package com.gantzgulch.openclock.swt.app.clock;

import java.util.List;
import java.util.TimerTask;

public class ClockTimerTask extends TimerTask {

	private final List<ClockFace> clocks;

	public ClockTimerTask(final List<ClockFace> clocks) {
		this.clocks = clocks;
	}

	@Override
	public void run() {
		for (final ClockFace clock : clocks) {
			clock.update();
		}
	}

}
