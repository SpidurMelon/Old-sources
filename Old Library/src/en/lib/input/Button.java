package en.lib.input;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import en.lib.drawing.DrawUtils;

public abstract class Button extends Rectangle implements MouseListener {
	private boolean pressedInside = false;
	private String label;
	private int fontSize;
	public int startX, startY;
	
	public Button(int x, int y, int width, int height, String label, int fontSize) {
		super(x, y, width, height);
		this.startX = x;
		this.startY = y;
		this.label = label;
		this.fontSize = fontSize;
	}
	
	public abstract void onClick();

	public void mousePressed(MouseEvent m) {
		if (this.contains(m.getPoint())) {
			pressedInside = true;
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (pressedInside && this.contains(m.getPoint())) {
			onClick();
			pressedInside = false;
		} else {
			pressedInside = false;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (pressedInside) {
			g2.setColor(Color.GRAY);
		} else {
			g2.setColor(Color.WHITE);
		}
		g2.fillRect(x, y, width, height);
		g2.setColor(Color.BLACK);
		g2.drawRect(x, y, width, height);
		
		g2.setFont(new Font("Arial", 0, fontSize));
		DrawUtils.drawStringInBox(label, this, g2);
	}

	public void mouseEntered(MouseEvent m) {}
	public void mouseExited(MouseEvent m) {}
	public void mouseClicked(MouseEvent m) {}
	
	
}
