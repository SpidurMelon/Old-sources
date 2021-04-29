package nl.world.panel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import nl.world.objects.Chunk;
import nl.world.util.Util;

public class World extends Panel implements MouseListener, MouseMotionListener, MouseWheelListener {
	private static final long serialVersionUID = 1L;
	
	
	public static Chunk[][] world = new Chunk[32][32];
	public static int chunkSize;
	public static int vision = world.length;
	public static int maxVision = world.length;
	public static int minVision = 10;
	
	public World() {
		WIDTH = 800;
		HEIGHT = 800;
		chunkSize = WIDTH/vision;
		setBounds(x, y, WIDTH, HEIGHT);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		createWorld();
	}
	
	public void tick() {
		repaint();
		updateChunks();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawWorld(g);
	}
	public void createWorld() {
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				world[x][y] = new Chunk(x*chunkSize, y*chunkSize, chunkSize, chunkSize);
			}
		}
		createBigRiver(0, 0);
	}
	public void createRiver(int startingX, int startingY) {
		boolean creatingRiver = true;
		
		while (creatingRiver) {
			world[startingX][startingY].status = "water";
			if (startingX == world.length-1 || startingY == world[0].length-1) {
				creatingRiver = false;
			}
			int direction = Util.randomNumber(0, 1);
			if (direction == 0) {
				startingX++;
			} else {
				startingY++;
			}
		}
	}
	public void createBigRiver(int startingX, int startingY) {
		createRiver(startingX, startingY);
		updateChunks();
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				if (world[x][y].nextToWater && Util.randomNumber(1, (100/100)) == 1) {
					world[x][y].status = "water";
				}
			}
		}
	}
	
	public void updateChunks() {
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				world[x][y].tick();
			}
		}
	}
	
	public void turnInto(String current, String to, int percent) {
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				if (world[x][y].status == current && Util.randomNumber(1, (100/percent)) == 1) {
					world[x][y].status = to;
				}
			}
		}
	}
	
	public void drawWorld(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				if (world[x][y].status.equals("grass")) {
					if (world[x][y].nextToWater) {
						g.setColor(Color.RED);
					} else {
						g.setColor(Color.GREEN);
					}
				}
				if (world[x][y].status.equals("water")) {
					g.setColor(Color.BLUE);
				}
				g2.fill(world[x][y]);
				g.setColor(Color.black);
				g2.draw(world[x][y]);
			}
		}
	}
	
	public void mouseDragged(MouseEvent m) {
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				if (world[x][y].contains(m.getPoint())) {
					createRiver(x, y);
				}
			}
		}
	}
	public void mouseMoved(MouseEvent m) {
		
	}
	public void mouseClicked(MouseEvent m) {
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				if (world[x][y].contains(m.getPoint())) {
					createRiver(x, y);
				}
			}
		}
	}
	public void mouseEntered(MouseEvent m) {
		
	}
	public void mouseExited(MouseEvent m) {
		
	}
	public void mousePressed(MouseEvent m) {
		
	}
	public void mouseReleased(MouseEvent m) {
		
	}

	public void mouseWheelMoved(MouseWheelEvent m) {
		System.out.println(m.getWheelRotation());
		if (m.getWheelRotation() > 0 && vision < maxVision) {
			vision+=m.getWheelRotation();
		} else if (m.getWheelRotation() < 0 && vision > minVision) {
			vision+=m.getWheelRotation();
		}
		int differenceChunkSize = WIDTH/vision-chunkSize;
		chunkSize = WIDTH/vision;
		for (int x = 0; x < world.length; x++) {
			for (int y = 0; y < world[0].length; y++) {
				world[x][y].width = chunkSize;
				world[x][y].height = chunkSize;
				world[x][y].x += differenceChunkSize*x;
				world[x][y].y += differenceChunkSize*y;
				
			}
		}
	}
}
