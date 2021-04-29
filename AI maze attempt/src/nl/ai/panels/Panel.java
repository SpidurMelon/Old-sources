package nl.ai.panels;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import nl.ai.block.Block;
import nl.ai.block.Level1;
import nl.ai.block.Network;
import nl.ai.entities.Enemy;
import nl.ai.entities.Robot;

public class Panel extends JPanel {
	private static final long serialVersionUID = 1L;

	public Robot rob = new Robot();
	public static Enemy nem = new Enemy(9, 9); 
	
	private Level1 level = new Level1();
	public static Network neurons = new Network();
	
	private static int blockSize = Block.blockSize;
	
	public static int screenWidth = Level1.blockArray[0].length*blockSize+7 + neurons.neurons[0].length*Network.neuronSize;
	public static int screenHeight = Level1.blockArray.length*blockSize+29;
	
	private Color backGroundColor = Color.white;
	public static Color lineColor = Color.green;
	public static Color stringColor = Color.black;
	
	
	
	public Panel() {
		setSize(screenWidth, screenHeight);
	}
	
	public void tick() {
		rob.tick();
		//nem.tick();
	}

	public void paintComponent(Graphics g) {
		g.setColor(backGroundColor);
		g.fillRect(0, 0, screenWidth, screenHeight);
		level.draw(g);
		neurons.draw(g);
		rob.draw(g);
		//nem.draw(g);
	}
	
}
