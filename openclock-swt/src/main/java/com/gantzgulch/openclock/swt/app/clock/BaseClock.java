package com.gantzgulch.openclock.swt.app.clock;

import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

public abstract class BaseClock extends Composite implements Clock {

	public BaseClock(final Composite parent) {
		super(parent, SWT.TRANSPARENT | SWT.NO_BACKGROUND);
	}

	@Override
	public void update() {
		try {
			getDisplay().asyncExec(this::updateInternal);
		}catch(final SWTException e ) {
			// This happens when we close.
		}
	}
	
	protected abstract void updateInternal();

	protected Font updateFont(final Font font, final int newSize) {
		
		final FontData[] fontData = font.getFontData();
		
		for(int i=0; i < fontData.length; i++) {
			fontData[i].setHeight(newSize);
		}
		
		return new Font(Display.getCurrent(), fontData);
	}
}
