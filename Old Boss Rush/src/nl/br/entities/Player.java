package nl.br.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.util.ArrayList;

import en.lib.drawing.Animation;
import en.lib.drawing.SpriteMap;
import en.lib.input.KeyBinding;
import en.lib.math.Vector;
import en.lib.setup.Panel;
import en.lib.setup.Tick;
import nl.br.panels.DrawPanel;
import nl.br.util.CutScene;

public class Player extends MoveableEntity implements MouseListener, MouseMotionListener {
	private Panel parent;
	private ArrayList<String> keysDown = new ArrayList<String>();
	
	public static int playerSpriteWidth = 30, playerSpriteHeight = 40;
	public static int playerCollideHeight = 10;
	
	private int animationDelay = 15;
	private SpriteMap animations = new SpriteMap(15, 20, 1, new File("resources/textures/characters/Player/Standard.png"));
	public Animation idleAnimation = new Animation(animations, 0, 0, animations.spriteMapWidth-1, 0, animationDelay);
	private Animation upAnimation = new Animation(animations, 0, 1, animations.spriteMapWidth-1, 1, animationDelay);
	private Animation rightAnimation = new Animation(animations, 0, 2, animations.spriteMapWidth-1, 2, animationDelay);
	private Animation leftAnimation = new Animation(animations, 0, 3, animations.spriteMapWidth-1, 3, animationDelay);
	private Animation downAnimation = new Animation(animations, 0, 4, animations.spriteMapWidth-1, 4, animationDelay);
	private Animation upRightAnimation = new Animation(animations, 0, 5, animations.spriteMapWidth-1, 5, animationDelay);
	private Animation upLeftAnimation = new Animation(animations, 0, 6, animations.spriteMapWidth-1, 6, animationDelay);
	private Animation downRightAnimation = new Animation(animations, 0, 7, animations.spriteMapWidth-1, 7, animationDelay);
	private Animation downLeftAnimation = new Animation(animations, 0, 8, animations.spriteMapWidth-1, 8, animationDelay);
	private Animation currentAnimation = idleAnimation; 
	
	public Bow bow;
	
	private CutScene spawningCutscene = new CutScene(this, new boolean[][]{	{false, false, false, false}, {false, true, false, false}, {true, true, false, false},
																			{true, false, false, false}, {true, false, false, true}, {false, false, false, true},
																			{false, false, true, true}, {false, false, true, false}, {false, true, true, false}}, 3.5, 9, 1000);;
	
	
	public boolean cameraFollowPlayer = true, inCutscene = false, canShoot = true;
	
	public Player(int startX, int startY, Panel parent) {
		super(startX, startY, playerSpriteWidth, playerSpriteHeight, playerCollideHeight);
		this.parent = parent;
		DrawPanel.moveCamera((int)spriteBox.getCenterX(), (int)spriteBox.getCenterY());
		
		bow = new Bow(spriteCenter, this);
		DrawPanel.addObject(bow);
		
		initKeys();
		
		//moveTo(0, 500);
		//walkTo(500, 0);
		//spawningCutscene.start();
	}
	
	public void tick() {
		super.tick();
		currentAnimation.tick();
		checkMovement();
		
		bow.move(spriteCenter);
		bow.tick();
		
		z = y+height+escalation*DrawPanel.SCALE;
		
		//System.out.println(ShapeUtils.getCoords(bow.initialSpriteBox, bow.bowTransform));
	}
	
	public void draw(Graphics2D g2) {
		currentAnimation.draw(spriteBox.x, spriteBox.y, spriteBox.width, spriteBox.height, g2);
		
		if (DrawPanel.DEBUG) {
			g2.setColor(Color.BLUE);
			g2.draw(this);
			g2.setColor(Color.CYAN);
			g2.drawString(String.valueOf((int)z), x+10, y);
		}
	}
	
	public void checkMovement() {
		if (!keysDown.isEmpty() || inCutscene) {
			if (!inCutscene) {
				if (keysDown.contains("W")) {
					up = true;
				} else {
					up = false;
				}
				if (keysDown.contains("S")) {
					down = true;
				} else {
					down = false;
				}
				if (keysDown.contains("A")) {
					left = true;
				} else {
					left = false;
				}
				if (keysDown.contains("D")) {
					right = true;
				} else {
					right = false;
				}
			}
			
			
			if (up || down || left || right) {
				if (up && !down && !left && !right) {
					movement.setDirection(-90);
					switchAnimation(upAnimation);
				} else if (!up && down && !left && !right) {
					movement.setDirection(90);
					switchAnimation(downAnimation);
				} else if (!up && !down && left && !right) {
					movement.setDirection(180);
					switchAnimation(leftAnimation);
				} else if (!up & !down & !left & right) {
					movement.setDirection(0);
					switchAnimation(rightAnimation);
				} else if (up && !down && left && !right) {
					movement.setDirection(-135);
					switchAnimation(upLeftAnimation);
				} else if (up && !down && !left && right) {
					movement.setDirection(-45);
					switchAnimation(upRightAnimation);
				} else if (!up && down && left && !right) {
					movement.setDirection(135);
					switchAnimation(downLeftAnimation);
				} else if (!up && down && !left && right) {
					movement.setDirection(45);
					switchAnimation(downRightAnimation);
				} 
				
				applyMovement(movement);
			} 
		} else {
			switchAnimation(idleAnimation);
		}
	}
	
	public void applyMovement(Vector v) {
		super.applyMovement(v);
		if (cameraFollowPlayer) {
			DrawPanel.moveCamera((int)spriteBox.getCenterX(), (int)spriteBox.getCenterY());
		}
	}
	
	public void moveTo(int x, int y) {
		super.moveTo(x, y);
		if (cameraFollowPlayer) {
			DrawPanel.moveCamera((int)spriteBox.getCenterX(), (int)spriteBox.getCenterY());
		}
	}
	
	public void switchAnimation(Animation animation) {
		if (currentAnimation != animation) {
			currentAnimation.reset();
			currentAnimation = animation;
		}
	}
	
	private void initKeys() {
		KeyBinding upPressed = new KeyBinding(KeyEvent.VK_W, parent, false) {
			public void onAction() {
				if (!keysDown.contains("W")) {
					keysDown.add("W");
				}
			}
		};
		KeyBinding upReleased = new KeyBinding(KeyEvent.VK_W, parent, true) {
			public void onAction() {
				keysDown.remove("W");
			}
		};
		KeyBinding rightPressed = new KeyBinding(KeyEvent.VK_D, parent, false) {
			public void onAction() {
				if (!keysDown.contains("D")) {
					keysDown.add("D");
				}
			}
		};
		KeyBinding rightReleased = new KeyBinding(KeyEvent.VK_D, parent, true) {
			public void onAction() {
				keysDown.remove("D");
			}
		};
		KeyBinding downPressed = new KeyBinding(KeyEvent.VK_S, parent, false) {
			public void onAction() {
				if (!keysDown.contains("S")) {
					keysDown.add("S");
				}
			}
		};
		KeyBinding downReleased = new KeyBinding(KeyEvent.VK_S, parent, true) {
			public void onAction() {
				keysDown.remove("S");
			}
		};
		KeyBinding leftPressed = new KeyBinding(KeyEvent.VK_A, parent, false) {
			public void onAction() {
				if (!keysDown.contains("A")) {
					keysDown.add("A");
				}
			}
		};
		KeyBinding leftReleased = new KeyBinding(KeyEvent.VK_A, parent, true) {
			public void onAction() {
				keysDown.remove("A");
			}
		};
		KeyBinding Shift = new KeyBinding(KeyEvent.VK_SHIFT, parent, false) {
			public void onAction() {
				movement.setSize(10);
			}
		};
		KeyBinding Control = new KeyBinding(KeyEvent.VK_CONTROL, parent, false) {
			public void onAction() {
				movement.setSize(100);
			}
		};
		KeyBinding R = new KeyBinding(KeyEvent.VK_R, parent, false) {
			public void onAction() {
				movement.setSize(speed);
			}
		};
		KeyBinding E = new KeyBinding(KeyEvent.VK_E, parent, false) {
			public void onAction() {
				DrawPanel.panCamera(x, y, 10);
			}
		};
		KeyBinding P = new KeyBinding(KeyEvent.VK_P, parent, false) {
			public void onAction() {
				Tick.pauseUnpause();
			}
		};
		KeyBinding debug = new KeyBinding(KeyEvent.VK_SUBTRACT, parent, false) {
			public void onAction() {
				DrawPanel.DEBUG = !DrawPanel.DEBUG;
			}
		};
		
	}

	public void mouseDragged(MouseEvent m) {
		bow.mousePoint = m.getPoint();
	}

	public void mouseMoved(MouseEvent m) {
		bow.mousePoint = m.getPoint();
	}

	public void mousePressed(MouseEvent m) {
		if (canShoot) {
			bow.chargeArrow();
		}
	}

	public void mouseReleased(MouseEvent m) {
		if (canShoot) {
			bow.releaseArrow();
		}
	}

	public void mouseClicked(MouseEvent e) {}
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e) {}
}
