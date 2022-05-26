package com.gantzgulch.openclock.swt.app.clock.digital;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import com.gantzgulch.openclock.swt.app.config.ClockFaceConfig;
import com.gantzgulch.openclock.swt.app.util.FontUtil;

public class LedDisplay extends Canvas implements PaintListener {

	// private static final GGLogger LOG = GGLogger.getLogger(DigitalSegments.class);
	
	private Color backgroundColor;
	
	private Font font;
	private Color color;
	private Color shadowColor;

	private String text;
	private String shadowText;

	public LedDisplay(final Composite parent, //
			final ClockFaceConfig clockFaceConfig, //
			final String labelText, //
			final String shadowLabelText) {

		super(parent, SWT.NO_REDRAW_RESIZE);
		
		this.backgroundColor = clockFaceConfig.getParameters().getBackground();
		this.font = clockFaceConfig.getParameters().getClockFont().getFont();
		this.color = clockFaceConfig.getParameters().getClockFont().getColor();
		this.shadowColor = clockFaceConfig.getParameters().getClockFont().getShadowColor();

		this.text = labelText;
		this.shadowText = shadowLabelText;
		
		this.setFont(this.font);
		
		final Point p = FontUtil.computeExtents(this, this.text);
		p.x += 10;
		p.y += 10;
		
		this.setSize(p);
		
		this.addPaintListener(this);
	}

	public void setText(final String text) {
		
		this.text = text;
		
		this.redraw();
	}

	@Override
	public Point computeSize(final int wHint, final int hHint, final boolean changed) {

		final Point p = FontUtil.computeExtents(this, this.text);

		p.x = p.x + 10;
		p.y = p.y + 10;
		
		return p;
	}
	
	@Override
	public void paintControl(final PaintEvent e) {
		
		final Rectangle clientArea = this.getClientArea();
		
		e.gc.setBackground( this.backgroundColor);
		e.gc.fillRectangle(clientArea);

		e.gc.setForeground(this.shadowColor);
		e.gc.drawText(this.shadowText, 5, 5, true);

		e.gc.setForeground(this.color);
		e.gc.drawText(this.text, 5, 5, true);
	}
}
