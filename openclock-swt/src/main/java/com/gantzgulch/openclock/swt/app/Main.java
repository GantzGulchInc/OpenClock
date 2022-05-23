package com.gantzgulch.openclock.swt.app;

import java.util.Timer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class Main implements Runnable, SelectionListener {

	private final String[] args;

	private Display display;
	private Shell shell;

	private Label positioningLabel;

	private Clock clock0;
	private Clock clock1;
	private Clock clock2;
	private Clock clock3;
	
	public Main(final String[] args) {
		this.args = args;

		createUi3();
	}

	private void createUi1() {

		display = new Display();

		shell = new Shell(display);

		shell.setLayout(new FillLayout());

		final Label label = new Label(shell, SWT.BORDER);
		label.setText("This is a label:");
		label.setToolTipText("This is the tooltip of this label.");

		final Text text = new Text(shell, SWT.NONE);
		text.setText("This is the texst in the text widget");
		text.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		text.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

		final Button button = new Button(shell, SWT.PUSH);
		button.setText("Press me.");
		button.setData("My data object.");
		button.addSelectionListener(this);

		text.pack();
		label.pack();
		shell.pack();

	}

	private void createUi2() {
		
		display = new Display();
		
		shell = new Shell(display);

		positioningLabel = new Label(shell, SWT.BORDER);
		
		int x = 60;
		int y = 20;
		int width = 400;
		int height = 200;
		
		positioningLabel.setBounds(x, y, width, height);
		int toolbarSize = 30;
		
		shell.setBounds(200, 400, width+2*x, height + 2*y + toolbarSize);

		shell.addMouseMoveListener( this::showSize);
		positioningLabel.addMouseMoveListener(this::showSize);
		

	}

	private void createUi3() {
		
		display = new Display();
		
		shell = new Shell(display);

		final GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 2;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.marginHeight = 20;
		gridLayout.marginWidth = 20;
		
		shell.setLayout(gridLayout);

		final GridData clock0GridData = new GridData(GridData.FILL_BOTH);
		clock0 = new Clock(shell, "America/Detroit");
		clock0.setLayoutData(clock0GridData);

		final GridData clock1GridData = new GridData(GridData.FILL_BOTH);
		clock1 = new Clock(shell, "utc");
		clock1.setLayoutData(clock1GridData);

		final GridData clock2GridData = new GridData(GridData.FILL_BOTH);
		clock2 = new Clock(shell, "America/Detroit");
		clock2.setLayoutData(clock2GridData);
		
		final GridData clock3GridData = new GridData(GridData.FILL_BOTH);
		clock3 = new Clock(shell, "America/Detroit");
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

	@Override
	public void widgetSelected(final SelectionEvent e) {

		final Object data = e.widget.getData();

		System.out.println("widgetSelected: " + e);

		if (data != null) {
			System.out.println("widgetSelected: Data: " + data);
		} else {
			System.out.println("widgetSelected: Data: Widget: null");
		}
	}

	@Override
	public void widgetDefaultSelected(final SelectionEvent e) {
		System.out.println("widgetDefaultSelected: " + e);
	}

	public static void main(final String[] args) {

		final Main main = new Main(args);

		main.run();

	}

}
