package nl.game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import nl.gamestate.GameStateManager;
import nl.recources.Images;

public class GamePanel extends JPanel implements Runnable, KeyListener {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	public static int WIDTH = 900;
	public static int HEIGHT = 550;
	private Thread thread;
	private boolean isRunning = false;
	public static int FPS = 60;
	private long targetTime = 1000 / FPS;
	private GameStateManager gsm;
	
	
	public GamePanel() {
		Toolkit tk = Toolkit.getDefaultToolkit();  
		int xSize = ((int) tk.getScreenSize().getWidth());  
		int ySize = ((int) tk.getScreenSize().getHeight());  
		WIDTH = xSize;
		HEIGHT = ySize;
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		setFocusable(true);
		
		new Images();
		
		start();
	}

	private void start() {
		isRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public void run() {
		long start, elapsed, wait;
		
		gsm = new GameStateManager();
		
		while (isRunning) {
			start = System.nanoTime();

			tick();
			repaint();

			elapsed = System.nanoTime() - start;
			wait = targetTime - elapsed / 1000000;

			if (wait < 0) {
				wait = 5;
			}

			try {
				Thread.sleep(wait);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public void tick() {
		gsm.tick();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.white);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		gsm.draw(g);
	}

	public void keyPressed(KeyEvent e) {
		gsm.KeyPressed(e.getKeyCode());
	}

	public void keyReleased(KeyEvent e) {
		gsm.KeyReleased(e.getKeyCode());
	}

	public void keyTyped(KeyEvent e) {

	}

}
