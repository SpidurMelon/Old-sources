package nl.br.panels;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import en.lib.math.Vector;
import en.lib.setup.Panel;
import en.lib.util.DoublePoint;
import nl.br.arrows.Arrow;
import nl.br.entities.Enemy;
import nl.br.entities.EnemyUtils;
import nl.br.entities.MoveableEntity;
import nl.br.entities.Player;
import nl.br.map.Block;
import nl.br.map.MapUtils;
import nl.br.map.Room;
import nl.br.map.TestFloor;
import nl.br.map.TestFloorSingleRoom;
import nl.br.objects.BRGameObject;
import nl.br.props.Prop;

public class DrawPanel extends Panel {
	public static int WIDTH = 1600, HEIGHT = 800;
	public static double SCALE = 20;
	public static int translationX = 0, translationY = 0;
	public static boolean DEBUG = false, stressTest = true;
	private static Color bgColor = new Color(240, 240, 255);
	
	public static TestFloor currentFloor;
	
	private static ArrayList<BRGameObject> objects = new ArrayList<BRGameObject>();
	public static Player player;
	public static ArrayList<Arrow> friendlyArrows = new ArrayList<Arrow>(); 
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private static ArrayList<Arrow> enemyArrows = new ArrayList<Arrow>();
	private static ArrayList<Prop> props = new ArrayList<Prop>();
	
	private static Rectangle screenCamera = new Rectangle(0, 0, WIDTH-1, HEIGHT-1);
	private static double renderBoxFactor = 1+(0.2*(SCALE/MapUtils.blockResolution));
	private static Rectangle renderBox = new Rectangle((int)((-WIDTH/2)*(renderBoxFactor-1)), (int)((-HEIGHT/2)*(renderBoxFactor-1)), (int)(WIDTH*renderBoxFactor), (int)(HEIGHT*renderBoxFactor));
	private static double propRenderBoxFactor = 3;
	private static Rectangle propRenderBox = new Rectangle((int)((-WIDTH/2)*(propRenderBoxFactor-1)), (int)((-HEIGHT/2)*(propRenderBoxFactor-1)), (int)(WIDTH*propRenderBoxFactor), (int)(HEIGHT*propRenderBoxFactor));
	
	private static boolean stillPainting = false;
	
	public static Font tinyFont = new Font("Arial", 0, 8);
	
	private static int maxEnemyArrows = 500;
	private static int maxFriendlyArrows = 500;
	
	public DrawPanel() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		currentFloor = new TestFloor();
		for (int y = 0; y < currentFloor.blocks.length; y++) {
			for (int x = 0; x < currentFloor.blocks[0].length; x++) {
				objects.add(currentFloor.blocks[y][x]);
			}
		}
		
		for (Prop p:currentFloor.props) {
			props.add(p);
			objects.add(p);
		}
		
		for (int i = 0; i < maxEnemyArrows; i++) {
			Arrow newArrow = new Arrow(false);
			enemyArrows.add(newArrow);
			addObject(newArrow);
		}
		
		for (int i = 0; i < maxFriendlyArrows; i++) {
			Arrow newArrow = new Arrow(true);
			friendlyArrows.add(newArrow);
			addObject(newArrow);
		}
		
		for (int i = 0; i < 3; i++) {
			enemies.add(new Enemy(500, 500, EnemyUtils.getEnemyByType(1)));
		}
		
		for (Enemy e:enemies) {
			objects.add(e);
		}
		
		Point playerStart = currentFloor.getStart();
		player = new Player(playerStart.x, playerStart.y, this);
		objects.add(player);
		addMouseListener(player);
		addMouseMotionListener(player);
		
	}
	
	public void tick() {
		if (!stillPainting) {
			long timeBeforeTick = System.currentTimeMillis();
			player.tick();
			
			for (Prop p:props) {
				p.tick();
			}
			
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).tick();
			}
			
			
			for (int i = 0; i < friendlyArrows.size(); i++) {
				friendlyArrows.get(i).tick();
			}
			for (int i = 0; i < enemyArrows.size(); i++) {
				enemyArrows.get(i).tick();
			}
			
			long timeBeforeArrows = System.currentTimeMillis();
			for (int i = 0; i < friendlyArrows.size(); i++) {
				testArrow(friendlyArrows.get(i));
			}
			for (int i = 0; i < enemyArrows.size(); i++) {
				testArrow(enemyArrows.get(i));
			}
			if (stressTest && System.currentTimeMillis() - timeBeforeArrows > 0) {
				System.out.println("ArrowLandTime: " + (System.currentTimeMillis() - timeBeforeArrows));
			}
			
			screenCamera.setLocation(-translationX, -translationY);
			renderBox.setLocation((int)(-translationX+(-WIDTH/2)*(renderBoxFactor-1)), (int)(-translationY+(-HEIGHT/2)*(renderBoxFactor-1)));
			propRenderBox.setLocation((int)(-translationX+(-WIDTH/2)*(propRenderBoxFactor-1)), (int)(-translationY+(-HEIGHT/2)*(propRenderBoxFactor-1)));
			
			if (stressTest && System.currentTimeMillis() - timeBeforeTick > 10) {
				System.out.println("TickTime:" + (System.currentTimeMillis()-timeBeforeTick));
			}
			repaint();
		}
	}
	
	public void paintComponent(Graphics g) {
		//super.paintComponent(g);
		stillPainting = true;
		long timeBeforePaint = System.currentTimeMillis();
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setColor(bgColor);
		g2.fillRect(0, 0, WIDTH, HEIGHT);
		g2.setFont(DrawPanel.tinyFont);
		
		g2.translate(translationX, translationY);
		drawObjects(g2);
		g2.translate(-translationX, -translationY);

		drawUI(g2);
		
		if (stressTest && System.currentTimeMillis() - timeBeforePaint >= 8) {
			System.out.println("PaintTime: " + (System.currentTimeMillis() - timeBeforePaint));
		}
		stillPainting = false;
	}
	
	private static int crossHairSize = 5;
	private static Point centerScreen = new Point(WIDTH/2-crossHairSize/2, HEIGHT/2-crossHairSize/2);
	public void drawUI(Graphics2D g2) {
		if (DEBUG) {
			g2.setColor(Color.RED);
			centerScreen.setLocation(WIDTH/2-crossHairSize/2, HEIGHT/2-crossHairSize/2);
			g2.fillOval(centerScreen.x, centerScreen.y, crossHairSize, crossHairSize);
		}
	}
	
	private static Point currentObjectTop = new Point(), currentObjectBottom = new Point();
	public void drawObjects(Graphics2D g2) {
		currentFloor.drawFloor(g2);
		synchronized(objects) {
			sortObjects();
		}
		long timeBeforeObjectDraw = System.currentTimeMillis();
		for (int i = 0; i < objects.size(); i++) {
			currentObjectTop.setLocation(objects.get(i).x, objects.get(i).y);
			currentObjectBottom.setLocation(objects.get(i).x, (int)(objects.get(i).y+objects.get(i).escalation*SCALE));
			if (!(objects.get(i) instanceof Prop) && renderBox.contains(currentObjectTop) | renderBox.contains(currentObjectBottom)) {
				objects.get(i).draw(g2);
			} else if (objects.get(i) instanceof Prop && propRenderBox.contains(currentObjectTop) | propRenderBox.contains(currentObjectBottom)) {
				objects.get(i).draw(g2);
			}
		}
		
		if (System.currentTimeMillis() - timeBeforeObjectDraw >= 7) {
			//System.out.println("Objects Draw Time: " + (System.currentTimeMillis() - timeBeforeObjectDraw));
		}
		
		if (DEBUG) {
			for (int i = 0; i < player.surroundingBlocks.size(); i++) {
				if (player.surroundingBlocks.get(i).escalation == player.escalation) {
					if (!player.isBarrierForMe(player.surroundingBlocks.get(i))) {
						g2.setColor(Color.GREEN);
					} else {
						g2.setColor(Color.RED);
					}
					g2.draw(player.surroundingBlocks.get(i));
					g2.drawString("x:" + String.valueOf(player.surroundingBlocks.get(i).ix), player.surroundingBlocks.get(i).getEscalatedX()+2, player.surroundingBlocks.get(i).getEscalatedY()+8);
					g2.drawString("y:" + String.valueOf(player.surroundingBlocks.get(i).iy), player.surroundingBlocks.get(i).getEscalatedX()+2, player.surroundingBlocks.get(i).getEscalatedY()+16);
				}
			}
			for (int i = 0; i < player.surroundingBlocks.size(); i++) {
				if (player.surroundingBlocks.get(i).escalation != player.escalation) {
					g2.setColor(Color.BLUE);
					g2.draw(player.surroundingBlocks.get(i));
					g2.drawString("x:" + String.valueOf(player.surroundingBlocks.get(i).ix), player.surroundingBlocks.get(i).getEscalatedX()+2, player.surroundingBlocks.get(i).getEscalatedY()+8);
					g2.drawString("y:" + String.valueOf(player.surroundingBlocks.get(i).iy), player.surroundingBlocks.get(i).getEscalatedX()+2, player.surroundingBlocks.get(i).getEscalatedY()+16);
				}
			}
			/*
			for (int i = 0; i < player.barriers.size(); i++) {
				g2.setColor(Color.RED);
				player.barriers.get(i).draw(g2);
			*/
			g2.setColor(Color.GREEN);
			g2.draw(screenCamera);
			g2.setColor(Color.RED);
			g2.draw(currentFloor.floorBoundingBox);
		}
	}
	
	public static Block getBlock(int ix, int iy) {
		int positiveIX = ix-currentFloor.minIXOffset, positiveIY = iy-currentFloor.minIYOffset;
		if (positiveIX > 0 && positiveIY > 0 && positiveIX < currentFloor.blocks[0].length-1 && positiveIY < currentFloor.blocks.length-1) {
			return currentFloor.blocks[positiveIY][positiveIX];
		} else {
			return null;
		}
	}
	
	private static Point entityCenter = new Point(), entityTop = new Point(), entityRight = new Point(), entityBottom = new Point(), entityLeft = new Point();
	public static void getSurroundingBlocks(MoveableEntity entity, boolean includeCorners) {
		entity.surroundingBlocks.clear();
		entityCenter.setLocation(entity.feetCenter);
		entityTop.setLocation(entity.getCenterX(), entity.y);
		entityRight.setLocation(entity.x+entity.width, entity.getCenterY());
		entityBottom.setLocation(entity.getCenterX(), entity.y+entity.height);
		entityLeft.setLocation(entity.x, entity.getCenterY());
		for (Room r:currentFloor.rooms) {
			if (r.contains(entityCenter) || r.contains(entityTop) || r.contains(entityRight) || r.contains(entityBottom) || r.contains(entityLeft)) {
				for (int y = 0; y < r.blocks.length; y++) {
					for (int x = 0; x < r.blocks[0].length; x++) {
						if (r.blocks[y][x].escalation == entity.escalation && r.blocks[y][x].intersects(entity)) {
							int actualX = x+r.iXOffset-currentFloor.minIXOffset, actualY = y+r.iYOffset-currentFloor.minIYOffset;
							//Center
							if (!entity.surroundingBlocks.contains(currentFloor.blocks[actualY][actualX])) {
								entity.surroundingBlocks.add(currentFloor.blocks[actualY][actualX]);
							}
							
							//Top and topleft
							if (actualY > 0) {
								if (!entity.surroundingBlocks.contains(currentFloor.blocks[actualY-1][actualX])) {
									entity.surroundingBlocks.add(currentFloor.blocks[actualY-1][actualX]);
								}
								if (actualX > 0) {
									if (includeCorners && !entity.surroundingBlocks.contains(currentFloor.blocks[actualY-1][actualX-1])) {
										entity.surroundingBlocks.add(currentFloor.blocks[actualY-1][actualX-1]);
									}
								}
							}
							
							//Right and bottomright and topright
							if (actualX < currentFloor.blocks[0].length-1) {
								if (!entity.surroundingBlocks.contains(currentFloor.blocks[actualY][actualX+1])) {
									entity.surroundingBlocks.add(currentFloor.blocks[actualY][actualX+1]);
								}
								if (actualY < currentFloor.blocks.length-1) {
									if (includeCorners && !entity.surroundingBlocks.contains(currentFloor.blocks[actualY+1][actualX+1])) {
										entity.surroundingBlocks.add(currentFloor.blocks[actualY+1][actualX+1]);
									}
								}
								if (actualY > 0) {
									if (includeCorners && !entity.surroundingBlocks.contains(currentFloor.blocks[actualY-1][actualX+1])) {
										entity.surroundingBlocks.add(currentFloor.blocks[actualY-1][actualX+1]);
									}
								}
							}
							
							//Bottom and bottomleft 
							if (actualY < currentFloor.blocks.length-1) {
								if (!entity.surroundingBlocks.contains(currentFloor.blocks[actualY+1][actualX])) {
									entity.surroundingBlocks.add(currentFloor.blocks[actualY+1][actualX]);
								}
								if (actualX > 0) {
									if (includeCorners && !entity.surroundingBlocks.contains(currentFloor.blocks[actualY+1][actualX-1])) {
										entity.surroundingBlocks.add(currentFloor.blocks[actualY+1][actualX-1]);
									}
								}
							}
							
							//Left
							if (actualX > 0) {
								if (!entity.surroundingBlocks.contains(currentFloor.blocks[actualY][actualX-1])) {
									entity.surroundingBlocks.add(currentFloor.blocks[actualY][actualX-1]);
								}
							}
						}
					}
				}
			}
		}
	}
	
	private static Comparator<BRGameObject> sortByZ = new Comparator<BRGameObject>() {
		public int compare(BRGameObject o1, BRGameObject o2) {
			return (int)Math.signum((o1.z)-(o2.z));
		}
	};
	private void sortObjects() {
		Collections.sort(objects, sortByZ);
	}
	
	public static void addObject(BRGameObject object) {
		synchronized(objects) {
			objects.add(object);
		}
	}
	
	private static int oldestEnemyArrow = 0, oldestFriendlyArrow = 0;
	public static void addArrow(int x, int y, int escalation, double direction, double speed, boolean friendly) {
		if (friendly) {
			friendlyArrows.get(oldestFriendlyArrow).fire(x, y, escalation, direction, speed);
			if (oldestFriendlyArrow < friendlyArrows.size()-1) {
				oldestFriendlyArrow++;
			} else {
				oldestFriendlyArrow = 0;
			}
		} else {
			enemyArrows.get(oldestEnemyArrow).fire(x, y, escalation, direction, speed);
			if (oldestEnemyArrow < enemyArrows.size()-1) {
				oldestEnemyArrow++;
			} else {
				oldestEnemyArrow = 0;
			}
		}
	}
	
	public void testArrow(Arrow arrow) {
		if (arrow.inAir) {
			if (!currentFloor.floorBoundingBox.contains(arrow.x, arrow.y)) {
				arrow.land();
			}
			for (Room r:currentFloor.rooms) {
				if (r.contains(arrow.getLocation())) {
					for (int y = 0; y < r.blocks.length; y++) {
						for (int x = 0; x < r.blocks[0].length; x++) {
							if (r.blocks[y][x].escalation > arrow.escalation || r.blocks[y][x].type == 0) {
								if (r.blocks[y][x].getFloorRectangle().contains(arrow.x, arrow.y)) {
									arrow.land();
								}
							} 
						}
						if (!arrow.inAir) {
							break;
						}
					}
				}
				if (!arrow.inAir) {
					break;
				}
			}
		}
	}
	
	public static void addEnemy(Enemy enemy) {
		enemies.add(enemy);
		addObject(enemy);
	}

	public static void moveCamera(int x, int y) {
		translationX = -x+WIDTH/2;
		translationY = -y+HEIGHT/2;
	}
	
	public static void panCamera(int x, int y, double speed) {
		int lastTranslationX = translationX, lastTranslationY = translationY, newTranslationX = -x+WIDTH/2, newTranslationY = -y+HEIGHT/2;
		Thread panningThread = new Thread(new Runnable() {
			public void run() {
				double smoothNess = 10;
				Vector panDirection = new Vector(Vector.getVectorDirection(new Point(lastTranslationX, lastTranslationY), new Point(newTranslationX, newTranslationY)), speed/smoothNess);
				double dTranslationX = translationX, dTranslationY = translationY;
				while(true) {
					boolean stillPanning = false;
					if (Math.abs(dTranslationX - newTranslationX) > 1) {
						dTranslationX += panDirection.getXComp();
						translationX = (int)dTranslationX;
						stillPanning = true;
					}
					if (Math.abs(dTranslationY - newTranslationY) > 1) {
						dTranslationY += panDirection.getYComp();
						translationY = (int)dTranslationY;
						stillPanning = true;
					}
					if (!stillPanning) {
						translationX = -x+WIDTH/2;
						translationY = -y+HEIGHT/2;
						break;
					}
					try {
						Thread.sleep((long)(10/smoothNess));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		panningThread.start();
	}
	
	public static DoublePoint makeRelative(DoublePoint p) {
		return new DoublePoint(p.x-translationX, p.y-translationY);
	}
	
	public static Point makeRelative(Point p) {
		return new Point(p.x-translationX, p.y-translationY);
	}
	
	public static DoublePoint makeAbsolute(DoublePoint p) {
		return new DoublePoint(p.x+translationX, p.y+translationY);
	}
	
	public static Point makeAbsolute(Point p) {
		return new Point(p.x+translationX, p.y+translationY);
	}

	
}
