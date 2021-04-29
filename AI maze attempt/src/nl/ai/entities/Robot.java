package nl.ai.entities;

import java.awt.Color;
import java.awt.Graphics;

import nl.ai.block.Block;
import nl.ai.block.Level1;
import nl.ai.interfaces.Standard;
import nl.ai.panels.Panel;
import nl.ai.util.Util;

public class Robot extends Standard {
	public int x;
	public int y;
	
	public int[][] blockValue = Level1.blockValue;
	public int[][] blockArray = Level1.blockArray;
	
	private int blockSize = Block.blockSize;

	private int finalScore;
	private int finalX;
	private int finalY;
	
	private int maxScore = blockValue.length-2 + blockValue[0].length-2;
	
	public int[][] neurons = Panel.neurons.neurons;
	private int[][] bestNetwork = new int[neurons[0].length][neurons.length];
	private int[] xArray = new int[3];
	private int[] yArray = new int[3];
	
	private int lastY;
	private int lastX;
	private int notMoving;
	private int startX;
	private int startY;
	private int startScore;
	private int bestOutcome;
	
	private boolean reachedEnd = false;
	
	public Robot() {
		startUpLeft();
		//startAt(5, 5);
		reset();
	}
	
	public void tick() {
		int up = 0,right = 0,down = 0,left = 0;
		
		
		
		for(int x = 0; x < neurons.length; x++) {
			for(int y = 0; y < neurons[x].length; y++) {
				switch(neurons[y][x]) {
					case(1):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 0) {
							up += 1;
						}
					break;
					case(2):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 0) {
							right += 1;
						}
					break;
					case(3):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 0) {
							down += 1;
						}
					break;
					case(4):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 0) {
							left += 1;
						}
					break;
					case(5):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 1) {
							up += 1;
						}
					break;
					case(6):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 1) {
							right += 1;
						}
					break;
					case(7):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 1) {
							down += 1;
						}
					break;
					case(8):
						if (blockArray[(this.y-2) + y][(this.x-2) + x] == 1) {
							left += 1;
						}
					break;
					case(9):
						if (Util.hasEnemy((this.y-2) + y, (this.x-2) + x)) {
							up += 1;
						}
					break;
					case(10):
						if (Util.hasEnemy((this.y-2) + y, (this.x-2) + x)) {
							right += 1;
						}
					break;
					case(11):
						if (Util.hasEnemy((this.y-2) + y, (this.x-2) + x)) {
							down += 1;
						}
					break;
					case(12):
						if (Util.hasEnemy((this.y-2) + y, (this.x-2) + x)) {
							left += 1;
						}
					break;
				}
			}
		}
		
		
		if (up > down) {
			up -= down;
		} else if (up == down) {
			up = 0;
			down = 0;
		} else {
			down -= up;
		}
		
		if (right > left) {
			right -= left;
		} else if (right == left) {
			right = 0;
			left = 0;
		} else {
			left -= right;
		}
		
		//System.out.println("Up: " + up + ", Right: " + right + ", Down: " + down + ", Left: " + left);
		
		if (up != 0) {
			goUp(up);
			up = 0;
		}
		if (right != 0) {
			goRight(right);
			right = 0;
		}
		if (down != 0) {
			goDown(down);
			down = 0;
		}
		if (left != 0) {
			goLeft(left);
			left = 0;
		}
		
		//Util.printArray(xArray);
		//Util.printArray(yArray);
		Util.add(x, xArray);
		Util.add(y, yArray);
		
		if (lastX == x && lastY == y) {
				notMoving += 1;
		} else if (xArray[0] == xArray[2]) {
			if (xArray[0] != xArray[1]) {
				notMoving += 1;
			}
		} else if (yArray[0] == yArray[2]) {
			if (yArray[0] != yArray[1]) {
				notMoving += 1;
			}
		} else {
			notMoving = 0;
		}
		if (notMoving >= 3 || Util.hasEnemy(x, y)) {
			finalX = lastX;
			finalY = lastY;
			finalScore = finalX+finalY;
			reset();
			Panel.nem.reset();
			notMoving = 0;
		}
		lastX = x;
		lastY = y;
	}	

	public void draw(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(x*blockSize+1, y*blockSize+1, blockSize-1, blockSize-1);
	}
	
	public void reset() {
		if (finalScore > bestOutcome) {
			for(int x = 0; x < bestNetwork.length; x++) {
				for (int y = 0; y < bestNetwork[x].length; y++) {
					bestNetwork[y][x] = neurons[y][x];
				}
			}
			bestOutcome = finalScore;
		}
		if (bestOutcome == maxScore) {
			reachedEnd = true;
		}
		//System.out.println("Score: " + finalScore + ", Best score: " + bestOutcome);
		
		if (!reachedEnd) {
			Util.resetNetwork();
			Util.newNetwork();
		}
		if (bestOutcome != 0) {
			for(int x = 0; x < bestNetwork.length; x++) {
				for (int y = 0; y < bestNetwork[x].length; y++) {
					if (bestNetwork[y][x] != 0) {
						Panel.neurons.neurons[y][x] = bestNetwork[y][x];
					}
				}
			}
		}
		
		x = startX;
		y = startY;
	}
	private void goRight(int amount) {
		boolean go = true;
		for (int i = 1; i <= amount; i++) {
			if (blockRight(i) != 0) {
				go = false;
			}
		}
		if (go) {
			x+=amount;
		}
	}
	private void goLeft(int amount) {
		boolean go = true;
		for (int i = 1; i <= amount; i++) {
			if (blockLeft(i) != 0) {
				go = false;
			}
		}
		if (go) {
			x-=amount;
		}
	}
	private void goUp(int amount) {
		boolean go = true;
		for (int i = 1; i <= amount; i++) {
			if (blockUp(i) != 0) {
				go = false;
			}
		}
		if (go) {
			y-=amount;
		}
	}
	private void goDown(int amount) {
		boolean go = true;
		for (int i = 1; i <= amount; i++) {
			if (blockDown(i) != 0) {
				go = false;
			}
		}
		if (go) {
			y+=amount;
		}
	}
	
	private int blockRight(int amount) {
		return blockArray[y][x+amount];
	}
	private int blockLeft(int amount) {
		return blockArray[y][x-amount];
	}
	private int blockUp(int amount) {
		return blockArray[y-amount][x];
	}
	private int blockDown(int amount) {
		return blockArray[y+amount][x];
	}
	private void startUpLeft() {
		boolean Break = false;
		for(int i = 0; i < blockArray.length + blockArray[0].length; i++) { 
			for(int x = 0; x < blockArray.length; x++) {
				for (int y = 0; y < blockArray[x].length; y++) {
					if (blockArray[y][x] == 0 && blockValue[y][x] == i) {
						this.startX = x;
						this.startY = y;
						startScore = startX + startY;
						bestOutcome = startScore;
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
	private void startAt(int x, int y) {
		this.startX = x;
		this.startY = y;
		startScore = startX + startY;
		bestOutcome = startScore;
	}

}
