package com.gantzgulch.openclock.swt.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

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

	private Label timeLabel;
	
	private DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss Z");
	
	public Main(final String[] args) {
		this.args = args;

		createUi3();
	}

	private void update() {
	
		final String dateString = sdf.format(new Date());
		
		display.asyncExec(new Runnable() {
			   public void run() {
			      timeLabel.setText(dateString);
			   }
			});
		
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
				
		final GridData lData = new GridData(GridData.FILL_BOTH);
		
		positioningLabel = new Label(shell, SWT.BORDER);
		positioningLabel.setText("Temp");
		positioningLabel.setLayoutData( lData);

		final GridData bData = new GridData(GridData.FILL_BOTH);
		
		final Button b1 = new Button(shell, SWT.PUSH);
		b1.setText("B1");
		b1.addMouseMoveListener(this::showSize);
		b1.setLayoutData(bData);

		final GridData tData = new GridData(GridData.FILL_BOTH);
		
		timeLabel = new Label(shell, SWT.BORDER);
		timeLabel.setText("The time.");
		timeLabel.setLayoutData(tData);
		
		
		
		shell.addMouseMoveListener( this::showSize);
		positioningLabel.addMouseMoveListener(this::showSize);
		

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
		
		timer.scheduleAtFixedRate(new TimerTask() {
			@Override
			public void run() {
				update();
			}
		}, 1000, 500);
		
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
