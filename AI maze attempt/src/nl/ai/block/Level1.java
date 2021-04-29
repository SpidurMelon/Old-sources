package nl.ai.block;

import java.awt.Color;
import java.awt.Graphics;

import nl.ai.interfaces.Standard;
import nl.ai.panels.Panel;

public class Level1 extends Standard {
	public static int[][] blockArray = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 0, 0, 0, 0, 0, 0, 0, 0, 1},
								 		{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}};
	
	public static int[][] blockValue = new int[10][10];
	
	private int blockSize = Block.blockSize;
	
	private Color lineColor = Panel.lineColor;
	private Color stringColor = Panel.stringColor;

	public Level1() {
		
		for(int x = 0; x < blockArray.length; x++) {
			for (int y = 0; y < blockArray[x].length; y++) {
				blockValue[y][x] = x + y;
			}
		}
		
	}
	
	public void tick() {
		
		
	}

	public void draw(Graphics g) {
		g.setColor(lineColor);
		
		for(int x = 0; x < blockArray.length; x++) {
			for (int y = 0; y < blockArray[x].length; y++) {
				if (blockArray[y][x] == 1) {
					g.fillRect(x*blockSize, y*blockSize, blockSize, blockSize);
				} else {
					g.drawRect(x*blockSize, y*blockSize, blockSize, blockSize);
				}
			}
		}
		
		g.setColor(stringColor);
		for(int x = 0; x < blockArray.length; x++) {
			for (int y = 0; y < blockArray[x].length; y++) {
					g.drawString(String.valueOf(blockValue[y][x]), x*blockSize+22, y*blockSize+30);
			}
		}
		
		
	}
}
