import java.applet.Applet;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import java.awt.Color;


public class Move extends Applet implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle rect;
	private Rectangle rect2;
	
	private static boolean run = true;
	private static boolean run2 = true;
	private static boolean TT = true;
	private static boolean TTT = false;
	private static int Score = 0;
	private static int x = 0;
	private static int y = 0;
	private static String Woot = "Good Job!";
	private ArrayList<Integer> KeysDown;
	
	
	public void paint(Graphics g) {
		setSize(1000, 900);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.red);
		g2.fill(rect);
		g2.draw(rect2);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 100)); 
		if (TTT) {
			g2.setPaint(Color.blue);
			g.drawString(Woot, 350, 500);
			
			TTT = false;
			}
		}
		
			

	public void keyPressed(KeyEvent e) {
		if (!KeysDown.contains(e.getKeyCode()))
		KeysDown.add(new Integer(e.getKeyCode()));
			
		moveRect();
		if (Score == 5) {
			TTT = true;
		}
		if (Score == 10) {
			Woot = "WOW";
			TTT = true;
		}
		if (rect.x == rect2.x && rect.y == rect2.y ) {
			Move.run = true;
			Move.run2 = true;
			rect2.setLocation(Move.x, Move.y);
			init();
			Score = Score + 1;
			System.out.println("Score " + Score);
			
		}
		}

	public void keyReleased(KeyEvent e) {
		KeysDown.remove(new Integer(e.getKeyCode()));
		
	}

	public void moveRect() {
		
		int x = rect.x;
		int y = rect.y;
		if (KeysDown.contains(KeyEvent.VK_UP))
			y = y - 5;
		
		if (KeysDown.contains(KeyEvent.VK_DOWN))
			y = y + 5;
		
		if (KeysDown.contains(KeyEvent.VK_LEFT))
			x = x - 5;
		
		if (KeysDown.contains(KeyEvent.VK_RIGHT))
			x = x + 5;
		
		if (KeysDown.contains(KeyEvent.VK_ESCAPE))
			System.exit(0); 
		
		
		rect.setLocation(x,y);
		repaint();
		}
	
	
	

	public void keyTyped(KeyEvent arg0) {
		
	}

	

	public void init() {
		if (TT) {
		this.addKeyListener(this);
		KeysDown = new ArrayList<Integer>();
		rect = new Rectangle(0, 0, 50, 50);
		TT = false;
		}
		Random rand = new Random();
		while (Move.run) {
		int yo = rand.nextInt(850);
		if (yo%5 == 0) {
			run = false;
			Move.y = yo;
		
		}
		}
		
		Random rand2 = new Random();
		while (Move.run2) {
		int xo = rand2.nextInt(950);
		if (xo%5 == 0) {
			run2 = false;
			Move.x = xo;
		}
		}
		
		rect2 = new Rectangle(Move.x, Move.y, 50, 50);
		moveRect();
	}
}


