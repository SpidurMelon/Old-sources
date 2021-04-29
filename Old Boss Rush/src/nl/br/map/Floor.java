package nl.br.map;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import en.lib.io.IO;
import nl.br.panels.DrawPanel;
import nl.br.props.Prop;

public class Floor {
	public Block[][] blocks;
	public ArrayList<Prop> props = new ArrayList<Prop>();
	public ArrayList<Room> rooms = new ArrayList<Room>();
	public Room spawnRoom;
	private int standardRoomWidth = 50, standardRoomHeight = 50;
	public int minIXOffset = 0, minIYOffset = 0, maxIX = standardRoomWidth, maxIY = standardRoomHeight;
	
	private BufferedImage floorTexture;
	
	public Rectangle floorBoundingBoxI;
	public Rectangle floorBoundingBox;
	public Floor() {
		stitchRooms();
		
		for (Room r:rooms) {
			if (r.iXOffset < minIXOffset) {
				minIXOffset = r.iXOffset;
			}
			if (r.iYOffset < minIYOffset) {
				minIYOffset = r.iYOffset;
			}
			if (r.iXOffset+standardRoomWidth > maxIX) {
				maxIX = r.iXOffset+standardRoomWidth;
			}
			if (r.iYOffset+standardRoomHeight > maxIY) {
				maxIY = r.iYOffset+standardRoomHeight;
			}
		}
		//Create layer of blocks surrounding the floor
		minIXOffset -= 1;
		minIYOffset -= 1;
		maxIX += 2;
		maxIY += 2;
		
		
		floorBoundingBoxI = new Rectangle(minIXOffset, minIYOffset, maxIX-minIXOffset, maxIY-minIYOffset);
		floorBoundingBox = new Rectangle((int)(minIXOffset*DrawPanel.SCALE), (int)(minIYOffset*DrawPanel.SCALE), (int)((maxIX-minIXOffset)*DrawPanel.SCALE), (int)((maxIY-minIYOffset)*DrawPanel.SCALE));
		
		blocks = new Block[floorBoundingBoxI.height][floorBoundingBoxI.width];
		
		for (Room r:rooms) {
			for (int y = 0; y < r.blocks.length; y++) {
				for (int x = 0; x < r.blocks[0].length; x++) {
					r.blocks[y][x].moveTo(x+r.iXOffset, y+r.iYOffset);
					blocks[y+r.iYOffset-minIYOffset][x+r.iXOffset-minIXOffset] = r.blocks[y][x];
				}
			}
		}
		
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				if (blocks[y][x] == null) {
					blocks[y][x] = new Block(x+minIXOffset, y+minIYOffset, 0, 0);
				}
			}
		}
		
		for (Room r:rooms) {
			for (Prop p:r.props) {
				p.moveTo((int)(p.x+r.iXOffset*DrawPanel.SCALE), (int)(p.y+r.iYOffset*DrawPanel.SCALE));
				props.add(p);
			}
		}
		
		floorTexture = createFloor();
	}
	
	public BufferedImage createFloor() {
		BufferedImage floorTexture = new BufferedImage((int)(blocks[0].length*MapUtils.blockResolution), (int)(blocks.length*MapUtils.blockResolution), BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D floorGraphics = floorTexture.createGraphics();
		for (int y = 0; y < blocks.length; y++) {
			for (int x = 0; x < blocks[0].length; x++) {
				if (blocks[y][x].floorTexture != null) {
					floorGraphics.drawImage(blocks[y][x].floorTexture, (int)(x*MapUtils.blockResolution), (int)(y*MapUtils.blockResolution), (int)MapUtils.blockResolution, (int)MapUtils.blockResolution, null);
				}
			}
		}
		floorGraphics.dispose();
		
		return floorTexture;
	}
	
	public void drawFloor(Graphics2D g2) {
		g2.drawImage(floorTexture, (int)(minIXOffset*DrawPanel.SCALE), (int)(minIYOffset*DrawPanel.SCALE), (int)(blocks[0].length*DrawPanel.SCALE), (int)(blocks.length*DrawPanel.SCALE), null);
	}
	
	public void stitchRooms() {
		
	}
	
	public void tick() {
		for (Room r:rooms) {
			for (Prop p:r.props) {
				p.tick();
			}
		}
	}
	
	public Point getStart() {
		return new Point((int)(Integer.valueOf(IO.getPropertyValue("Start_blockX", spawnRoom.config))*DrawPanel.SCALE), (int)(Integer.valueOf(IO.getPropertyValue("Start_blockY", spawnRoom.config))*DrawPanel.SCALE));
	}
}
