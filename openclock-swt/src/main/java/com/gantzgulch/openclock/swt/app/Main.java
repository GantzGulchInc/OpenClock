package com.gantzgulch.openclock.swt.app;

import java.util.TimeZone;
import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import com.gantzgulch.openclock.swt.app.clock.BaseClock;
import com.gantzgulch.openclock.swt.app.clock.Clock12Hour14Segment;
import com.gantzgulch.openclock.swt.app.clock.Clock24Hour7Segment;
import com.gantzgulch.openclock.swt.app.clock.ClockTimerTask;

public class Main implements Runnable {

	private final String[] args;

	private Display display;
	private Shell shell;

	private Label positioningLabel;

	private BaseClock clock0;
	private BaseClock clock1;
	private BaseClock clock2;
	private BaseClock clock3;
	
	public Main(final String[] args) {
		this.args = args;

		createUi3();
	}


	private void createUi3() {
		
		display = new Display();
		
		shell = new Shell(display);
		shell.setBackground( display.getSystemColor(SWT.COLOR_BLACK));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		
		shell.setLayout(gridLayout);
		shell.addControlListener( new ControlListener() {
			@Override
			public void controlResized(ControlEvent e) {
				shell.layout();
			}
			@Override
			public void controlMoved(ControlEvent e) {
			}
		});

		final GridData clock0GridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		clock0 = new Clock12Hour14Segment(shell, TimeZone.getTimeZone("utc"));
		clock0.setLayoutData(clock0GridData);

		final GridData clock1GridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		clock1 = new Clock24Hour7Segment(shell, TimeZone.getTimeZone("utc"));
		clock1.setLayoutData(clock1GridData);

		final GridData clock2GridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		clock2 = new Clock12Hour14Segment(shell, TimeZone.getTimeZone("America/Detroit"));
		clock2.setLayoutData(clock2GridData);
		
		final GridData clock3GridData = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		clock3 = new Clock24Hour7Segment(shell, TimeZone.getTimeZone("America/Detroit"));
		clock3.setLayoutData(clock3GridData);
		
		
		
		// shell.addMouseMoveListener( this::showSize);
		// positioningLabel.addMouseMoveListener(this::showSize);
		

	}

	public void showSize(MouseEvent e) {
        int x = e.x;
        int y = e.y;
        String s = "Bounds for Label: " + positioningLabel.getBounds() + "\n";
        s+= "Bounds for Shell: " + shell.getBounds()  + "\n";
        s+= "Mouse pointer: " + x + " " + y;
        positioningLabel.setText(s);

    }
	
	
	@Override
	public void run() {

		System.out.println("Running...");

		final Timer timer = new Timer();
		
		final ClockTimerTask timerTask = new ClockTimerTask(clock0, clock1, clock2, clock3);
		timer.scheduleAtFixedRate( timerTask, 1000, 500);
		
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

		final Main main = new Main(args);

		main.run();

	}

}
