package nl.ww.copy;
/*package nl.ww.main;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Main extends Applet implements MouseListener, MouseMotionListener {
	private static final long serialVersionUID = 1L;

	private Rect[][] grid;
	private Graphics2D g2;
	private boolean rightMousePressed = false;
	private boolean leftMousePressed = false;
	private boolean middleMousePressed = false;
	private Point mousePoint = new Point();
	
	private Image i;
	private Graphics doubleG;

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
		assignRects();

		Runnable r1 = new Runnable() {
			public void run() {
				while (true) {
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[i].length; j++) {
							if (grid[i][j].status == 3) {
								grid[i][j].status = 1;
							}
							if (grid[i][j].status == 2) {
								if (grid[i + 1][j].status == 1) {
									grid[i + 1][j].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i - 1][j].status == 1) {
									grid[i - 1][j].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i][j + 1].status == 1) {
									grid[i][j + 1].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i][j - 1].status == 1) {
									grid[i][j - 1].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i + 1][j + 1].status == 1) {
									grid[i + 1][j + 1].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i + 1][j - 1].status == 1) {
									grid[i + 1][j - 1].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i - 1][j + 1].status == 1) {
									grid[i - 1][j + 1].status = 2;
									grid[i][j].status = 3;
								}
								if (grid[i - 1][j - 1].status == 1) {
									grid[i - 1][j - 1].status = 2;
									grid[i][j].status = 3;
								}
							}
						}
					}
					try {Thread.sleep(100);} catch (InterruptedException e) {e.printStackTrace();}

				}
			}
		};
		
		Runnable r2 = new Runnable() {
			public void run() {
				while (true) {
					try {Thread.sleep(10);} catch (InterruptedException e) {e.printStackTrace();}
					repaint();
				}
			}
		};
		
		Runnable r3 = new Runnable() {
			public void run() {
				while (true) {
					for (int i = 0; i < grid.length; i++) {
						for (int j = 0; j < grid[i].length; j++) {
							if (grid[i][j].contains(mousePoint)){
								try {Thread.sleep(2);} catch (InterruptedException e) {e.printStackTrace();}
								if (leftMousePressed) {
									grid[i][j].status = 1;
								}
								if (middleMousePressed) {
									grid[i][j].status = 2;
								}
								if (rightMousePressed) {
									grid[i][j].status = 0;
								}
							}
						}
					}
				}
			}
		};
		
		

		Thread t1 = new Thread(r1);
		t1.start();
		Thread t2 = new Thread(r2);
		t2.start();
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
	}

	public void mouseDragged(MouseEvent m) {
		mousePoint = m.getPoint();
	}

	public void mouseMoved(MouseEvent m) {
		mousePoint = m.getPoint();
		
	}
}*/