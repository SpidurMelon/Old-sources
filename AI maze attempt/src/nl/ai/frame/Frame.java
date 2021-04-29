package nl.ai.frame;

import javax.swing.JFrame;

import nl.ai.panels.Panel;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static Panel panel = new Panel();
	public Frame() {
		super("AI Inverted");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		add(panel);
		setSize(panel.getWidth(), panel.getHeight());
	}
}
