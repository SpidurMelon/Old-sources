package nl.gamestate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import nl.game.GamePanel;

public class MenuState extends GameState {
	
	private String[] options = {"Play", "Help" , "Quit"};
	private int currentSelection = 0;

	
	public MenuState(GameStateManager gsm) {
		super(gsm);
		
	}

	
	public void init() {
		
		
	}

	
	public void tick() {
		
		
	}

	
	public void draw(Graphics g) {
		
		g.setColor(Color.CYAN);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		
		for (int i = 0; i < options.length; i++) {
			if (i == currentSelection) {
				g.setColor(Color.BLUE);
			} else {
				g.setColor(Color.WHITE);
			}
			
			g.setFont(new Font("Arial", Font.PLAIN, 72));
			g.drawString(options[i], GamePanel.WIDTH / 2 - 75, 150 + i * 150);
		}
	}

	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_DOWN || k == KeyEvent.VK_S) {
			currentSelection++;
			if (currentSelection >= options.length) {
				currentSelection = 0;
			}
		} 
		else if (k == KeyEvent.VK_UP || k == KeyEvent.VK_W) {
			currentSelection--;
			if (currentSelection < 0) {
				currentSelection = options.length - 1;
			}
		}
		else if (k == KeyEvent.VK_ENTER) {
			if (currentSelection == 0) {
				
				
				
				
				//Pushes the first Level
				gsm.states.push(new Level2State(gsm));
				
				
				
				
				
				
			}else if (currentSelection == 1) {
				//help
			}else if (currentSelection == 2) {
				//exit
				System.exit(0);
			}
		}
		
		
	}

	
	public void keyReleased(int k) {
		
		
	}

}
