package nl.ww.main;

import java.awt.Rectangle;

public class Rect extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public int status = 0;
	
	Rect(int x, int y, int height, int width) {
		this.height = height;
		this.width = width;
		this.x = x;
		this.y = y;
	}
}
