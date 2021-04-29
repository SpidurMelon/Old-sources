package nl.br.map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import en.lib.io.IO;
import nl.br.panels.DrawPanel;
import nl.br.props.Prop;

public class Room {
	public Block[][] blocks;
	public ArrayList<Prop> props = new ArrayList<Prop>();
	public String config = "";
	public int iXOffset, iYOffset;
	public int mapWidth, mapHeight;
	public Room(int iXOffset, int iYOffset, String path) {
		this.iXOffset = iXOffset;
		this.iYOffset = iYOffset;
		
		config = IO.readFile(path);
		ArrayList<Integer> layout = IO.readPositiveArray(IO.getPropertyValue("Layout", config));
		ArrayList<Integer> heights = IO.readPosNegArray(IO.getPropertyValue("Heights", config));
		ArrayList<Integer> props = IO.readPositiveArray(IO.getPropertyValue("Props", config));
		
		mapWidth = Integer.valueOf(IO.getPropertyValue("Map_width", config));
		mapHeight = Integer.valueOf(IO.getPropertyValue("Map_height", config));
		
		int[][] blockTypes = new int[mapHeight][mapWidth];
		int[][] blockHeights = new int[mapHeight][mapWidth];
		
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				blockTypes[y][x] = layout.get(x+y*mapWidth);
				blockHeights[y][x] = heights.get(x+y*mapWidth);
			}
		}
		
		blocks = new Block[blockTypes.length][blockTypes[0].length];
		for (int y = 0; y < blockTypes.length; y++) {
			for (int x = 0; x < blockTypes[0].length; x++) {
				blocks[y][x] = new Block(x, y, blockHeights[y][x], blockTypes[y][x]);
			}
		}
		
		/*
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				if (y > 0) {
					blocks[y][x].connections[0] = blocks[y-1][x];
				}
				if (x < blocks[0].length-1) {
					blocks[y][x].connections[1] = blocks[y][x+1];
				}
				if (y < blocks.length-1) {
					blocks[y][x].connections[2] = blocks[y+1][x];
				}
				if (x > 0) {
					blocks[y][x].connections[3] = blocks[y][x-1];
				}
			}
		}
		*/
		for (int y = 0; y < mapHeight; y++) {
			for (int x = 0; x < mapWidth; x++) {
				if (props.get(x+y*mapWidth) != 0) {
					this.props.add(MapUtils.getPropByType((int)(x*DrawPanel.SCALE), (int)(y*DrawPanel.SCALE), props.get(x+y*mapWidth)));
				}
			}
		}
	}
	
	public void tick() {
		for (Prop p:props) {
			p.tick();
		}
	}
	
	public boolean contains(Point p) {
		if (p.x > iXOffset*DrawPanel.SCALE && p.x < iXOffset*DrawPanel.SCALE+mapWidth*DrawPanel.SCALE && p.y > iYOffset*DrawPanel.SCALE && p.y < iYOffset*DrawPanel.SCALE+mapHeight*DrawPanel.SCALE) {
			return true;
		} else {
			return false;
		}
	}
	
	public Point getStart() {
		return new Point((int)(Integer.valueOf(IO.getPropertyValue("Start_blockX", config))*DrawPanel.SCALE), (int)(Integer.valueOf(IO.getPropertyValue("Start_blockY", config))*DrawPanel.SCALE));
	}
}
