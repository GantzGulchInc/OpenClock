package com.gantzgulch.openclock.swt.app.util;

import org.eclipse.swt.graphics.Drawable;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Display;

public class FontUtil {

	public static Font updateFont(final Font font, final int newSize) {
		
		final FontData[] fontData = font.getFontData();
		
		for(int i=0; i < fontData.length; i++) {
			fontData[i].setHeight(newSize);
		}
		
		return new Font(Display.getCurrent(), fontData);
	}
	
	public static Point computeExtents(final Drawable drawable, final String text) {
		
		final GC gc = new GC(drawable);
		
		final Point p = gc.textExtent(text);

		gc.dispose();
		
		return p;
		
	}
}
