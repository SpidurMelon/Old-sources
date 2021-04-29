package nl.br.util;

import java.awt.Point;

import nl.br.entities.Player;
import nl.br.panels.DrawPanel;

public class CutScene {
	
	private Player player;
	private Point[] cameraPoints;
	private boolean[][] playerMovement;
	private double playerSpeed;
	private double[] bowDirections;
	
	private int length;
	private int delay;
	
	
	private boolean cameraFollowPlayer, bowFollowMovement;
	
	
	public CutScene(Player player, Point[] cameraPoints, boolean[][] playerMovement, double playerSpeed, double[] bowDirections, int length, int delay) {
		this.player = player;
		this.cameraPoints = cameraPoints;
		this.playerMovement = playerMovement;
		this.playerSpeed = playerSpeed;
		this.bowDirections = bowDirections;
		this.length = length;
		this.delay = delay;
		
		
		cameraFollowPlayer = false;
		bowFollowMovement = false;
	}
	
	public CutScene(Player player, Point[] cameraPoints, boolean[][] playerMovement, double playerSpeed, int length, int delay) {
		this.player = player;
		this.cameraPoints = cameraPoints;
		this.playerMovement = playerMovement;
		this.playerSpeed = playerSpeed;
		this.length = length;
		this.delay = delay;
		
		cameraFollowPlayer = true;
		bowFollowMovement = true;
	}
	
	public CutScene(Player player, boolean[][] playerMovement, double playerSpeed, double[] bowDirections, int length, int delay) {
		this.player = player;
		this.playerMovement = playerMovement;
		this.playerSpeed = playerSpeed;
		this.bowDirections = bowDirections;
		this.length = length;
		this.delay = delay;
		
		cameraFollowPlayer = true;
		bowFollowMovement = false;
	}
	
	public CutScene(Player player, boolean[][] playerMovement, double playerSpeed, int length, int delay) {
		this.player = player;
		this.playerMovement = playerMovement;
		this.playerSpeed = playerSpeed;
		this.length = length;
		this.delay = delay;
		
		cameraFollowPlayer = true;
		bowFollowMovement = true;
	}
	
	public void start() {
		cutSceneThread.start();
	}
	
	private Thread cutSceneThread = new Thread(new Runnable() {
		public void run() {
			player.inCutscene = true;
			player.cameraFollowPlayer = cameraFollowPlayer;
			player.bow.bowFollowMouse = false;
			player.canShoot = false;
			player.movement.setSize(playerSpeed);
			
			for (int i = 0; i < length; i++) {
				if (!cameraFollowPlayer) {
					DrawPanel.panCamera(cameraPoints[i].x, cameraPoints[i].y, 10);
				}
				
				player.up = playerMovement[i][0];
				player.right = playerMovement[i][1];
				player.down = playerMovement[i][2];
				player.left = playerMovement[i][3];
				
				if (bowFollowMovement) {
					if (player.up && !player.down && !player.left && !player.right) {
						player.bow.rotation = 90;
					} else if (!player.up && player.down && !player.left && !player.right) {
						player.bow.rotation = -90;
					} else if (!player.up && !player.down && player.left && !player.right) {
						player.bow.rotation = 0;
					} else if (!player.up & !player.down & !player.left & player.right) {
						player.bow.rotation = 180;
					} else if (player.up && !player.down && player.left && !player.right) {
						player.bow.rotation = 45;
					} else if (player.up && !player.down && !player.left && player.right) {
						player.bow.rotation = 135;
					} else if (!player.up && player.down && player.left && !player.right) {
						player.bow.rotation = -45;
					} else if (!player.up && player.down && !player.left && player.right) {
						player.bow.rotation = -135;
					} 
				} else {
					player.bow.rotation = bowDirections[i];
				}
				
				try {
					Thread.sleep(delay);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			player.up = false;
			player.right = false;
			player.down = false;
			player.left = false;
			
			player.switchAnimation(player.idleAnimation);
			
			player.movement.setSize(player.speed);
			player.cameraFollowPlayer = true;
			player.bow.bowFollowMouse = true;
			player.canShoot = true;
			player.inCutscene = false;
		}
	});
	
}
