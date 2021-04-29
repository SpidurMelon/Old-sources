package nl.world.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class Miscelaneous extends Panel {
	public Miscelaneous() {
		WIDTH = 200;
		HEIGHT = 800;
		x = 800;
		setBounds(x, y, WIDTH, HEIGHT);
	}
	
	public void tick() {
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.RED);
		g.setFont(new Font("Arial", Font.PLAIN, 12)); 
		drawText(g, "Left click to add a small river.", 0, 0, 24);
	}
	
	public void drawText(Graphics g, String text, int x, int y, int cutOffPoint) {
		ArrayList<String> lines = new ArrayList<String>();
		int begin = 0;
		int end = begin+cutOffPoint;
		while (true) {
			if (text.indexOf(" ", end) != -1) {
				lines.add(text.substring(begin, text.indexOf(" ", end)));
				begin = text.indexOf(" ", end);
				end = begin+cutOffPoint;
			} else {
				lines.add(text.substring(begin));
				break;
			}
			
		}
		
		for (int i = 0; i < lines.size(); i++) {
			if (i == 0) {
				g.drawString(lines.get(i), x+4, y+12*i+12);
			} else {
				g.drawString(lines.get(i), x, y+12*i+12);
			}
		}
	}
}
