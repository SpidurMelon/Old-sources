package nl.ai.entities;

import java.awt.Color;
import java.awt.Graphics;

import nl.ai.block.Block;
import nl.ai.block.Level1;
import nl.ai.interfaces.Standard;

public class BaseRobot extends Standard {
	public int x;
	public int y;
	
	public int[][] blockValue = Level1.blockValue;
	public int[][] blockArray = Level1.blockArray;
	
	private int blockSize = Block.blockSize;
	
	private int bestOutcome = 0;
	
	private int robX;
	private int robY;
	
	private int finalScore;
	private int finalX;
	private int finalY;
	
	private int maxScore = blockValue.length + blockValue[0].length;
	
	public BaseRobot() {
		reset();
	}
	
	public void tick() {
		
		
		
		if(robX == x && robY == y) {
			finalX = robX;
			finalY = robY;
			finalScore = finalX+finalY;
			//System.out.println(finalScore);
			reset();
		}
		robX = x;
		robY = y;
	}	

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x*blockSize+1, y*blockSize+1, blockSize-1, blockSize-1);
	}
	
	public void reset() {
		if (finalScore > bestOutcome) {
			bestOutcome = finalScore;
		}
		
		
		boolean Break = false;
		for(int i = 0; i < blockArray.length + blockArray[0].length; i++) { 
			for(int y = 0; y < blockArray.length; y++) {
				for (int x = 0; x < blockArray[y].length; x++) {
					if (blockArray[y][x] == 0 && blockValue[y][x] == i) {
						this.x = x;
						this.y = y;
						Break = true;
						break;
					}
				}
				if (Break) {
					break;
				}
			}
			if (Break) {
				break;
			}
		}
		
		
		
		
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
