package nl.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import nl.game.GamePanel;
import nl.gamestate.GameState;
import nl.gamestate.Level2State;
import nl.objects.Block;
import nl.physics.Collision;

public class Player {
	public boolean right = false, left = false, jumping = false, falling = false;
	public boolean bottomCollision = false;
	public boolean Ghost = false;
	public static boolean hasToSpawn = true;
	public static boolean canGoThru = false;
	public static boolean down = false;
	public static boolean shift = false;
	public static boolean fire = false;
	public static boolean lastLeft = false;
	public static boolean lastRight = true;
	public static boolean ll = false;
	public static boolean lr = false;
	
	private double x, y;
	private int width, height;
	private int bulletX;
	private int bulletY;
	private int startBulletX;
	
	private double moveSpeed = 2.5;
	
	private double jumpSpeed = 5.5;
	private double currentJumpSpeed = jumpSpeed;
	
	private double maxFallSpeed = 5;
	private double currentFallSpeed = 0.1;
	private boolean rightcollision;
	private boolean leftcollision;
	
	public Player(int width, int height) {
		x = GamePanel.WIDTH / 2;
		y = GamePanel.HEIGHT / 2;
		this.height = height;
		this.width = width;
		bulletX = (int)x + 15;
		
		startBulletX = (int)x + 15;
	}
	
	public void tick(Block[][] b) {
		
		
		int iX = (int)x;
		int iY = (int)y;
		
		
		if (!Ghost) {
			
		for (int i = 0; i < b.length; i++) {
			for(int j = 0; j < b[0].length; j++) {
						if (b[i][j].getID() == 8) {
							
							if (hasToSpawn) {
								GameState.xOffset = -950;
								GameState.yOffset = -530; 
								GameState.xOffset += b[i][j].getX();
								GameState.yOffset += b[i][j].getY();
								hasToSpawn = false;
							}
							
						}
						if (b[i][j].getID() == 9) {
							
							if (Collision.playerBlock(new Point((int)x + (int)GameState.xOffset + 15, (int)y + (int)GameState.yOffset + 15), b[i][j])) {
								canGoThru = true;
							}
								
							if (!Collision.playerBlock(new Point((int)x + (int)GameState.xOffset + 15, (int)y + (int)GameState.yOffset + 15), b[i][j])) {
								canGoThru = false;
							}
							
						}
				
				
				
				
						if (b[i][j].getID() != 0 && b[i][j].getID() != 4 && b[i][j].getID() != 9 && b[i][j].getID() != 8 && b[i][j].getID() != 2) {
			
							
							
							
							
							//BulletCollision
							if(Collision.playerBlock(new Point(bulletX + 2 + (int)GameState.xOffset, bulletY + (int)GameState.yOffset), b[i][j])) {
								fire = false;
								
							}

							//BulletCollision over
							
							
							
							
							
						// right
						if(Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset + 2, iY + (int)GameState.yOffset + 2), b[i][j]) 
								|| Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset - 1), b[i][j])) {
							if (b[i][j].getID() != 7) {
							right = false;
							rightcollision = true;
							} else if (b[i][j].getID() == 7) {
								hasToSpawn = true;
							} 
							
						} else {
							rightcollision = false;
							
						}

						// left
						if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 2, iY + (int)GameState.yOffset + 2), b[i][j])
								|| Collision.playerBlock(new Point(iX + (int)GameState.xOffset - 2, iY + height + (int)GameState.yOffset - 1), b[i][j])) {
							if (b[i][j].getID() != 7) {
								
							left = false;
							
							} else if (b[i][j].getID() == 7) {
								hasToSpawn = true;
							}
							leftcollision = true;
						} else {
							leftcollision = false;
						}

						// top
						if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 1, iY + (int)GameState.yOffset - 4), b[i][j]) ||
								Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 1, iY + (int)GameState.yOffset - 4), b[i][j])) {
							if (b[i][j].getID() != 7) {
							jumping = false;
							falling = true;
							currentJumpSpeed = jumpSpeed;
							} else if (b[i][j].getID() == 7) {
								hasToSpawn = true;
							}
						}

						// bottom
						if(Collision.playerBlock(new Point(iX + (int)GameState.xOffset + 2, iY + height + (int)GameState.yOffset + 4), b[i][j]) ||
								Collision.playerBlock(new Point(iX + width + (int)GameState.xOffset - 1,  iY + height + (int)GameState.yOffset + 4), b[i][j])) {
							if (b[i][j].getID() != 7) {
							y = b[i][j].getY() - (int)GameState.yOffset - height;
							
							falling = false;
							bottomCollision = true;
							} else {
								hasToSpawn = true;
							}
						} else {
						if(!bottomCollision && !jumping) {
								falling = true;
							}
							
						}
						
						
						
						
						}
						
						
						
						
						
						
						
						
						
						
						
						}
					}
					bottomCollision = false;
			}
		
		
	
		
		
		if (left) {
			GameState.xOffset -= moveSpeed;
		}
		if (right) {
			GameState.xOffset += moveSpeed;
		}
		
		if (jumping) {
			GameState.yOffset -= currentJumpSpeed;
			bulletY += currentJumpSpeed;
			currentJumpSpeed -= .1;
			
			
			if (currentJumpSpeed <= 0) {
				currentJumpSpeed = jumpSpeed;
				jumping = false;
				falling = true;
			}
			
			
			
			
		}
		if (falling) {
			GameState.yOffset += currentFallSpeed;
			bulletY -= currentFallSpeed;
			
			if (currentFallSpeed < maxFallSpeed) {
				currentFallSpeed += .1;
			}
		}
		
		if (!falling) {
			currentFallSpeed = .1;
		
		
		}
		if (x != GamePanel.WIDTH / 2 || y != GamePanel.HEIGHT / 2 && !bottomCollision) {
			x = GamePanel.WIDTH / 2;
			y = GamePanel.HEIGHT / 2;
		}
		
		if (down) {
			currentFallSpeed = 0.2;
		}
		if (!fire) {
			bulletX = startBulletX;
			bulletY = (int)y + 15;
		}
		
	}
	
	public void draw(Graphics g) throws IOException {
		if (down && !shift) {
			BufferedImage Para = ImageIO.read(getClass().getResourceAsStream("para.jpg"));
			g.drawImage(Para, (int)x, (int)y - height, null);
		}
		if (down && shift) {
			BufferedImage Para = ImageIO.read(getClass().getResourceAsStream("parasmall.jpg"));
			g.drawImage(Para, (int)x, (int)y - height, null);
		}
		if (fire) {
			g.setColor(Color.BLUE);
			if (lastRight) {
			g.fillRect(bulletX, bulletY, 5, 5);
			bulletX += 10;
			}
			if (lastLeft) {
				g.fillRect(bulletX, bulletY, 5, 5);
				bulletX -= 10;
			}
		}
		g.setColor(Color.RED);
		g.fillRect((int)x, (int)y, width, height);
		g.setColor(Color.GREEN);
		g.drawRect((int)x, (int)y, width, height);
		g.setColor(Color.CYAN);
		
		
	}
	
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_A && !leftcollision){
			left = true;
			ll = true;
			if (!fire) {
			lastLeft = true;
			lastRight = false;
			}
		} 
		if (k == KeyEvent.VK_D && !rightcollision){
			right = true;
			lr = true;
			if (!fire) {
			lastLeft = false;
			lastRight = true;
			} 
		}
		if (k == KeyEvent.VK_SPACE && !jumping && !falling) {
			
			jumping = true;
			
		}
		
		if (k == KeyEvent.VK_SHIFT && !jumping && !falling) {
			Level2State.player.setBounds(10, 10);
			shift = true;
		}
		if (k == KeyEvent.VK_G) {
			Ghost = true;
			
		}
		
		if (k == KeyEvent.VK_P) {
			System.out.println("xOffset: " + GameState.xOffset + "     yOffset: " + GameState.yOffset);
			System.out.println("rc: " + rightcollision + "  lc: " + leftcollision);
			
		}
		if (k == KeyEvent.VK_S) {
			down = true;
			
		}
		if (k == KeyEvent.VK_F) {
			fire = true;
			
		}
		
	}
	
	public void keyReleased(int k) {
		if (k == KeyEvent.VK_A){
			left = false;
			ll = false;
		}
		if (k == KeyEvent.VK_D){
			right = false;
			lr = false;
		}
		if (k == KeyEvent.VK_SHIFT) {
			if (!falling  && !jumping && !down) {
			GameState.yOffset -= 20;
			
			}
			Level2State.player.setBounds(30, 30);
			shift = false;
		}
		if (k == KeyEvent.VK_G) {
			Ghost = false;
		}
		if (k == KeyEvent.VK_S) {
			down = false;
			
		}
		if (k == KeyEvent.VK_F) {
			
			
		}
		
	}
	
	public void setBounds(int height, int width) {
		this.width = width;
		this.height = height;
	}
}
