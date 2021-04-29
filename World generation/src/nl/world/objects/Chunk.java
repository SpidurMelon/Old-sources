package nl.world.objects;

import java.awt.Rectangle;

import nl.world.panel.World;

public class Chunk extends Rectangle {
	private String[] statuses = {"grass", "water"};
	public String status = "grass";
	
	public boolean inhabited = false;
	public boolean nextToWater = false;
	public boolean flooded = false;
	
	public int floodedTimer = 10*1000;
	private int xChunk;
	private int yChunk;
	
	private static Chunk[][] world = World.world;
	private static int chunkSize = World.chunkSize;
	
	public Chunk(int x, int y, int width, int height) {
		this.setBounds(x, y, width, height);
		xChunk = x/chunkSize;
		yChunk = y/chunkSize;
	}
	
	public void tick() {
		if (this.isNextTo("water")) {
			nextToWater = true;
		}
	}
	
	public boolean hasAbove(String status) {
		//return yChunk != 0 && world[xChunk][yChunk-1].status == status;
		return hasRelative(status, 0, -1);
	}
	public boolean hasRight(String status) {
		//return xChunk != world.length-1 && world[xChunk+1][yChunk].status == status;
		return hasRelative(status, 1, 0);
	}
	public boolean hasBelow(String status) {
		//return yChunk != world[0].length-1 && world[xChunk][yChunk+1].status == status;
		return hasRelative(status, 0, 1);
	}
	public boolean hasLeft(String status) {
		//return xChunk != 0 && world[xChunk-1][yChunk].status == status;
		return hasRelative(status, -1, 0);
	}
	public boolean hasRelative(String status, int xR, int yR) {
		return getRelative(xR, yR) == status;
	}
	public String getRelative(int xR, int yR) {
		try {
			return world[xChunk+xR][yChunk+yR].status;
		} catch(Exception e) {
			return "Error";
		}
	}
	public boolean isNextTo(String status) {
		return (hasAbove(status)||
				hasRight(status)||
				hasBelow(status)||
				hasLeft(status));
	}

}
