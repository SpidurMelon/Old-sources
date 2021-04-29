package nl.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;



import nl.gamestate.GameState;
import nl.recources.Images;

public class Block extends Rectangle {
	private static final long serialVersionUID = 1L;
	
	public static final int BlockSize = 64;
	private int id;
	public Block(int x, int y, int id) {
		setBounds(x, y, BlockSize, BlockSize);
		this.id = id;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		if (id == 1) {
			g.setColor(Color.cyan);
		g.drawImage(Images.blocks[id - 1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, null);
		}
		if (id == 2) {
			g.setColor(Color.white);
			g.fillRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
		}
		if (id == 4) {
			g.setColor(Color.cyan);
			g.drawImage(Images.blocks[id - 4], x - (int)GameState.xOffset, y - (int)GameState.yOffset, null);
		}
		if (id == 9) {
			g.setColor(Color.cyan);
			g.drawRect(x - (int)GameState.xOffset, y - (int)GameState.yOffset, width, height);
		}
		if (id == 7) {
			g.drawImage(Images.blocks[1], x - (int)GameState.xOffset, y - (int)GameState.yOffset, null);
		}
	}
	
	public void setID(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
}
