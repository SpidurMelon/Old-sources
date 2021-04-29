package nl.KeyJF.client;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class KeyJF extends JFrame implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Double bob = 0.0;
	private static ArrayList<Integer> KeysDown;
	private static JLabel label;
	
	
	
	public static void main(String[] args) {
		new KeyJF().setVisible(true);
		KeysDown = new ArrayList<Integer>();
		afterkey();
	}

	public void keyPressed(KeyEvent e) {
		if (!KeysDown.contains(e.getKeyCode()))
			KeysDown.add(new Integer(e.getKeyCode()));
			
			afterkey();
	}

	public void keyReleased(KeyEvent e) {
		KeysDown.remove(new Integer(e.getKeyCode()));

	}

	public void keyTyped(KeyEvent e) {
		

	}

	public KeyJF() {
		super ("DOT DOT DOT");
		setSize(500, 500);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		this.addKeyListener(this);
		label = new JLabel(bob.toString());
		label.setBounds(0, 0, 200, 20);
		label.setToolTipText("Press up/down to increase/decrease the value.");
		add(label);
		
	}
	
	public static void afterkey() {
		if (KeysDown.contains(KeyEvent.VK_UP)) {
			bob += 1;
		}
		
		if (KeysDown.contains(KeyEvent.VK_DOWN)) {
			bob -= 1;
		}
		
		if (KeysDown.contains(KeyEvent.VK_ESCAPE)) {
			System.exit(0); 
		}
		
		label.setText(bob.toString());
	}
}
