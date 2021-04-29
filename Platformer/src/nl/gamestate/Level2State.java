package nl.gamestate;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.io.IOException;

import nl.entities.Enemy;
import nl.entities.Player;
import nl.mapping.Map;

public class Level2State extends GameState {
	
	public static Player player;
	public static Enemy enemy;
	private Map map2;
	
	public Level2State(GameStateManager gsm) {
		super(gsm);
	}

	
	public void init() {
		player = new Player(30, 30);
		//enemy = new Enemy(30, 30, 1100, 500);
		map2 = new Map("/map2.map");
		
		
	}

	
	public void tick() {
		
		
		
		player.tick(map2.getBlocks());
		//enemy.tick(map2.getBlocks());
		
	}

	
	public void draw(Graphics g) {
		try {
			player.draw(g);
			//enemy.draw(g);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map2.draw(g);
		
	}

	
	public void keyPressed(int k) {
		player.keyPressed(k);
		if (k == KeyEvent.VK_R) {
			Player.hasToSpawn = true;
			map2 = new Map("/map2.map");
		}
		if (k == KeyEvent.VK_W && Player.canGoThru) {
			gsm.states.push(new Level1State(gsm));
			Player.hasToSpawn = true;
			Player.canGoThru = false;
		}
	}

	
	public void keyReleased(int k) {
		player.keyReleased(k);
		
	}
}

