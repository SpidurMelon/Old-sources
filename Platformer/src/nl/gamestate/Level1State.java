package nl.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import nl.entities.Player;
import nl.mapping.Map;

public class Level1State extends GameState {
	
	public static Player player;
	private Map map;
	
	
	
	public Level1State(GameStateManager gsm) {
		super(gsm);
		
	}

	
	public void init() {
		player = new Player(30, 30);
		
		map = new Map("/map1.map");
		
		
	}

	
	public void tick() {
		
		
		
		player.tick(map.getBlocks());
		
		
		
	}

	
	public void draw(Graphics g) {
		try {
			player.draw(g);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		map.draw(g);
		
	}

	
	public void keyPressed(int k) {
		player.keyPressed(k);
		if (k == KeyEvent.VK_R) {
			Player.hasToSpawn = true;
		}
		if (k == KeyEvent.VK_W && Player.canGoThru) {
			gsm.states.push(new Level2State(gsm));
			Player.hasToSpawn = true;
			Player.canGoThru = false;
		}
		
		
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);
		
	}
}
