package com.gantzgulch.openclock.swt.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import com.gantzgulch.logging.core.GGLogger;
import com.gantzgulch.openclock.swt.app.clock.AbstractClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockFace;
import com.gantzgulch.openclock.swt.app.clock.ClockFaceFactory;
import com.gantzgulch.openclock.swt.app.clock.ClockTimerTask;
import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;
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
		shell.setBackground( config.getDisplayConfig().getBackground(display.getSystemColor(SWT.COLOR_BLACK)));

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = config.getDisplayConfig().getColumns();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		
		shell.setLayout(gridLayout);
		shell.addControlListener( new ControlAdapter() {
			@Override
			public void controlResized(final ControlEvent e) {
				shell.layout();
			}
		});

		for(final ClockFaceConfig clockConfig : config.getClockConfigs()) {

			LOG.info("Building: %s", clockConfig);
			
			final GridData clockGridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
			
			final AbstractClockFace clock = ClockFaceFactory.createClockFace(shell, clockConfig);
			
			clock.setLayoutData(clockGridData);
			
			clocks.add(clock);
		}
		
		shell.pack();
	}

	private Monitor findLastMonitor() {

		Monitor lastMonitor = null;
		
		final Monitor[] monitors = display.getMonitors();
		
		for(final Monitor monitor : monitors) {
			System.out.println("Mon: " + ToStringBuilder.reflectionToString(monitor));
			lastMonitor = monitor;
		}
		
		return lastMonitor;
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

		ToStringBuilder.setDefaultStyle(ToStringStyle.SHORT_PREFIX_STYLE);
		
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
