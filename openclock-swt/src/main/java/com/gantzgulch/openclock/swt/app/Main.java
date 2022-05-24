package com.gantzgulch.openclock.swt.app;

import java.util.ArrayList;
import java.util.List;
import java.util.TimeZone;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.openclock.swt.app.clock.AbstractClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockTimerTask;
import com.gantzgulch.openclock.swt.app.clock.digital.Clock12Hour14Segment;
import com.gantzgulch.openclock.swt.app.clock.digital.Clock24Hour7Segment;
import com.gantzgulch.openclock.swt.app.config.ClockConfig;
import com.gantzgulch.openclock.swt.app.config.Config;

public class Main implements Runnable, ControlListener {

	private static final GGLogger LOG = GGLogger.getLogger(Main.class);
	
	private final Config config;
	
	private Display display;
	private Shell shell;

	private List<ClockFace> clocks = new ArrayList<>();
	
	public Main(final Config config) {

		this.config = config;
		
		createUi3(this.config);
	}


	private void createUi3(final Config config) {
		
		display = new Display();
		
		shell = new Shell(display);
		shell.setText("OpenClock");
		shell.setBackground( display.getSystemColor(SWT.COLOR_BLACK));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = config.getDisplayConfig().getColumns();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		
		shell.setLayout(gridLayout);
		shell.addControlListener( this);
		shell.addDisposeListener( new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				// System.exit(0);;
			}
		});

		for(final ClockConfig clockConfig : config.getClockConfigs()) {

			LOG.info("Building: %s", clockConfig);
			
			final GridData clockGridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
			final AbstractClockFace clock = createClock(shell, clockConfig);
			clock.setLayoutData(clockGridData);
			
			clocks.add(clock);
		}
		
	}
	
	private AbstractClockFace createClock(final Shell shell, final ClockConfig clockConfig) {
		
		final TimeZone timezone = TimeZone.getTimeZone(clockConfig.getTimeZone());
		final String title = clockConfig.getTitle();
		
		if(  "Digital24Hour".equals(clockConfig.getType()) ){
			
			return new Clock24Hour7Segment(shell, title, timezone);
			
		}

		if(  "Digital12Hour".equals(clockConfig.getType()) ){
			
			return new Clock12Hour14Segment(shell, title, timezone);
			
		}
		
		return null;
	}


	@Override
	public void controlResized(final ControlEvent e) {
		shell.layout();
	}
	
	@Override
	public void controlMoved(final ControlEvent e) {
	}
	
	@Override
	public void run() {

		LOG.info("run: Running...");

		final Timer timer = new Timer(true);
		
		final ClockTimerTask timerTask = new ClockTimerTask(clocks);
		timer.scheduleAtFixedRate( timerTask, 0, 500);
		
		shell.pack();
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public static void main(final String[] args) {

		final Config config = Config.load();
		
		final Main main = new Main(config);

		main.run();

	}

}
