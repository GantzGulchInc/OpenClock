package com.gantzgulch.openclock.swt.app.clock;

import java.util.TimerTask;

import com.gantzgulch.openclock.swt.app.Application;

public class ClockTimerTask extends TimerTask {

	private final Application app;

	public ClockTimerTask(final Application app) {
		this.app = app;
	}

	@Override
	public void run() {
		app.updateClocks();
	}

}
