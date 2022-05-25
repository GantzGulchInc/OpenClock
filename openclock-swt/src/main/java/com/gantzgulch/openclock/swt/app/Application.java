package com.gantzgulch.openclock.swt.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.openclock.swt.app.clock.AbstractClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockFaceFactory;
import com.gantzgulch.openclock.swt.app.clock.ClockTimerTask;
import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;
import com.gantzgulch.openclock.swt.app.config.Config;

public class Application implements ControlListener, Runnable {

	private static final GGLogger LOG = GGLogger.getLogger(Application.class);

	private final Display display;
	
	private final Shell shell;
	
	private final Config config;

	private List<ClockFace> clocks = new ArrayList<>();

	public Application(final Config config) {

		this.display = new Display();
		
		this.shell = new Shell(this.display);

		this.config = config;

		createUi3(this.config);
	}

	private void createUi3(final Config config) {

		this.shell.setText("OpenClock");
		this.shell.setBackground(config.getDisplayConfig().getBackground(Display.getCurrent().getSystemColor(SWT.COLOR_BLACK)));

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = config.getDisplayConfig().getColumns();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;

		this.shell.setLayout(gridLayout);
		this.shell.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(final ControlEvent e) {
				shell.layout();
			}
		});

		for (final ClockFaceConfig clockConfig : config.getClockConfigs()) {

			LOG.info("Building: %s", clockConfig);

			final GridData clockGridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);

			final AbstractClockFace clock = ClockFaceFactory.createClockFace(this.shell, clockConfig);

			clock.setLayoutData(clockGridData);

			clocks.add(clock);
		}

		this.shell.pack();
	}

	@Override
	public void run() {
		
		LOG.info("run: Running...");

		final Timer timer = new Timer(true);
		final ClockTimerTask timerTask = new ClockTimerTask(this);
		timer.scheduleAtFixedRate( timerTask, 0, 500);
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();

	}
	
	@Override
	public void controlResized(final ControlEvent e) {
		this.shell.layout();
	}

	@Override
	public void controlMoved(final ControlEvent e) {
	}

	public void updateClocks() {
		for(final ClockFace clockFace : clocks) {
			clockFace.update();
		}
	}

}
