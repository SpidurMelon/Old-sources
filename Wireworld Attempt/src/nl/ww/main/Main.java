package nl.ww.main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main extends Applet implements MouseListener, MouseMotionListener,
		KeyListener {
	private static final long serialVersionUID = 1L;

	private Rect[][] grid;
	private Graphics2D g2;
	private boolean rightMousePressed = false;
	private boolean leftMousePressed = false;
	private boolean middleMousePressed = false;
	private boolean alternateMiddleMousePressed = false;
	private boolean started = false;
	private boolean cycle = false;
	private Point mousePoint = new Point();
	private int countSurroundingElektrons = 0;
	private Image i;
	
	private Graphics doubleG;

	private boolean alt;

	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}

		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);

		g.drawImage(i, 0, 0, this);
	}

	public void init() {
		this.setSize(501, 501);
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.addKeyListener(this);
		assignRects();

		Runnable tick = new Runnable() {

			public void run() {
				while (true) {
					try {Thread.sleep(1);} catch (InterruptedException e) {e.printStackTrace();}
					if (started || cycle) {
						
						for (int x = 0; x < grid.length; x++) {
							for (int y = 0; y < grid[x].length; y++) {
								countSurroundingElektrons = 0;
								
								if (grid[y][x].status == 3) {
									grid[y][x].status = 1;
								}
								if (grid[y][x].status == 2) {
									grid[y][x].status = 3;
								}
								
								if (grid[y][x].status == 1 && cycle || grid[y][x].status == 1 && started) {
									if (grid[y + 1][x].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y - 1][x].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y][x + 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y][x - 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y + 1][x + 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y + 1][x - 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y - 1][x + 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									if (grid[y - 1][x - 1].status == 2) {
										countSurroundingElektrons += 1;
									}
									
									if (countSurroundingElektrons < 3 && countSurroundingElektrons > 0) {
										grid[y][x].status = 2;
										try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}
									}
									
							}
							
						}
					}
					cycle = false;

				}
				}
			}
		};

		Runnable refresh = new Runnable() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		};

		Runnable r3 = new Runnable() {
			public void run() {
				while (true) {
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[i].length; j++) {
							if (grid[i][j].contains(mousePoint)) {
								try {
									Thread.sleep(1);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}
								if (leftMousePressed) {
									grid[i][j].status = 1;
								}
								if (middleMousePressed) {
									grid[i][j].status = 2;
								}
								if (rightMousePressed) {
									grid[i][j].status = 0;
								}
								if (alternateMiddleMousePressed) {
									grid[i][j].status = 3;
								}
							}
						}
					}
				}
			}
		};

		Thread tickT = new Thread(tick);
		tickT.start();
		Thread refreshT = new Thread(refresh);
		refreshT.start();
		Thread t3 = new Thread(r3);
		t3.start();
	}

	public void paint(Graphics g) {
		drawBackground(g);
		drawGrid(g);
	}

	public void drawGrid(Graphics g) {
		g2 = (Graphics2D) g;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j].status == 0) {
					g2.setColor(new Color(190, 190, 190, 50));
					g2.draw(grid[i][j]);
				}

				if (grid[i][j].status == 1) {
					g2.setColor(new Color(255, 193, 37));
					g2.fill(grid[i][j]);
				}

				if (grid[i][j].status == 2) {
					g2.setColor(Color.BLUE);
					g2.fill(grid[i][j]);
				}

				if (grid[i][j].status == 3) {
					g2.setColor(Color.RED);
					g2.fill(grid[i][j]);
				}
			}
		}
	}

	public void assignRects() {
		grid = new Rect[getWidth() / 10][getHeight() / 10];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Rect(i * 10, j * 10, 10, 10);

			}
		}
	}

	public void drawBackground(Graphics g) {
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	public void mouseClicked(MouseEvent m) {

	}

	public void mouseEntered(MouseEvent arg0) {

	}

	public void mouseExited(MouseEvent arg0) {

	}

	public void mousePressed(MouseEvent m) {
		if (m.getButton() == MouseEvent.BUTTON3) {
			rightMousePressed = true;
		}
		if (m.getButton() == MouseEvent.BUTTON1) {
			leftMousePressed = true;
		}
		if (m.getButton() == MouseEvent.BUTTON2) {
			middleMousePressed = true;
		}
		if (m.getButton() == MouseEvent.BUTTON2 && alt) {
			alternateMiddleMousePressed = true;	
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (m.getButton() == MouseEvent.BUTTON3) {
			rightMousePressed = false;
		}
		if (m.getButton() == MouseEvent.BUTTON1) {
			leftMousePressed = false;
		}
		if (m.getButton() == MouseEvent.BUTTON2) {
			middleMousePressed = false;
		}
		if (m.getButton() == MouseEvent.BUTTON2) {
			alternateMiddleMousePressed = false;
		}
	}

	public void mouseDragged(MouseEvent m) {
		mousePoint = m.getPoint();
	}

	public void mouseMoved(MouseEvent m) {
		mousePoint = m.getPoint();

	}

	public void keyPressed(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_S) {
			if (started) {
				started = false;
			} else {
				started = true;
			}
		}
		if (k.getKeyCode() == KeyEvent.VK_C) {
			cycle = true;
		}
		if (k.getKeyCode() == KeyEvent.VK_ALT) {
			alt = true;
		}
	}

	public void keyReleased(KeyEvent k) {
		if (k.getKeyCode() == KeyEvent.VK_ALT) {
			alt = false;
		}
	}

	public void keyTyped(KeyEvent k) {
		

	}
}