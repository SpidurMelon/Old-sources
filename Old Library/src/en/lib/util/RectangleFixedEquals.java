package en.lib.util;

import java.awt.Rectangle;

public class RectangleFixedEquals extends Rectangle {
	public RectangleFixedEquals(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	public boolean equals(Object obj) {
		return this == obj;
	}
}
