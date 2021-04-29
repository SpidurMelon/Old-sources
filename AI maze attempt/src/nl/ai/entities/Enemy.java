package nl.ai.entities;

import java.awt.Color;
import java.awt.Graphics;

import nl.ai.block.Block;
import nl.ai.block.Level1;
import nl.ai.interfaces.Standard;
import nl.ai.util.Util;

public class Enemy extends Standard {
	public int startX;
	public int startY;
	public int x;
	public int y;
	
	public int[][] blockValue = Level1.blockValue;
	public int[][] blockArray = Level1.blockArray;
	
	private int blockSize = Block.blockSize;
	
	private int maxScore = blockValue.length + blockValue[0].length;
	
	private int random1;
	private boolean justReset = false;
	
	public Enemy(int x, int y) {
		startX = x;
		startY = y;
		reset();
	}
	
	public void tick() {
		random1 = Util.randomNumber(1, 4);
		
		if (!justReset) {
			switch(random1) {
				case(1):
					goUp();
				break;
				case(2):
					goRight();
				break;
				case(3):
					goDown();
				break;
				case(4):
					goLeft();
				break;
			}
		}
		
		justReset = false;
	}	

	public void draw(Graphics g) {
		g.setColor(Color.orange);
		g.fillRect(x*blockSize+1, y*blockSize+1, blockSize-1, blockSize-1);
	}
	
	public void reset() {
		x=startX;
		y=startY;
		justReset = true;
	}
	private void goRight() {
		if (blockRight() == 0) {
			x+=1;
		}
	}
	private void goLeft() {
		if (blockLeft() == 0) {
			x-=1;
		}
	}
	private void goUp() {
		if (blockUp() == 0) {
			y-=1;
		}
	}
	private void goDown() {
		if (blockDown() == 0) {
			y+=1;
		}
	}
	
	private int blockRight() {
		return blockArray[y][x+1];
	}
	private int blockLeft() {
		return blockArray[y][x-1];
	}
	private int blockUp() {
		return blockArray[y-1][x];
	}
	private int blockDown() {
		return blockArray[y+1][x];
	}
}
