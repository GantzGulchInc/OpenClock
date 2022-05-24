package com.gantzgulch.openclock.swt.app;

import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Display;

public class FontLister {

	public static void run() {

		final Display display = new Display();
		
		final FontData[] fontData = display.getFontList(null, true);
		
		for(FontData fd : fontData) {
			
			// System.out.println("font: name: " + fd.name);
		}
		
	}

}
