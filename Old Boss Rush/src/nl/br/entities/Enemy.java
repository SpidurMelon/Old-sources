package nl.br.entities;

import java.awt.Graphics2D;

import en.lib.drawing.Animation;
import en.lib.drawing.SpriteMap;
import nl.br.panels.DrawPanel;

public class Enemy extends MoveableEntity {
	private int actionDelay = 60, actionTimer = 0;
	private boolean inAction;
	
	private int animationDelay = 15;
	private SpriteMap animations;
	public Animation idleAnimation;
	public Animation currentAnimation;
	
	public EnemyType enemyType;
	
	public boolean living = true;
	
	public Enemy(int startX, int startY, int spriteWidth, int spriteHeight, int collideHeight, SpriteMap animations) {
		super(startX, startY, spriteWidth, spriteHeight, collideHeight);
		this.animations = animations;
		idleAnimation = new Animation(animations, 0, 0, 1, 0, animationDelay);
		currentAnimation = idleAnimation;
	}
	
	public Enemy(int startX, int startY, EnemyType enemyType) {
		this(startX, startY, enemyType.spriteWidth, enemyType.spriteHeight, enemyType.collideHeight, enemyType.animations);
		this.actionDelay = enemyType.actionDelay;
		this.enemyType = enemyType;
		
		enemyType.init(this);
	}
	
	public void tick() {
		if (living) {
			super.tick();
			currentAnimation.tick();
			
			if (inAction) {
				actionTick();
			} else if (!inAction && actionTimer >= actionDelay) {
				actionStart();
			} else if (!inAction) {
				actionTimer++;
			}
			
			for (int i = 0; i < DrawPanel.friendlyArrows.size(); i++) {
				if (spriteBox.contains(DrawPanel.friendlyArrows.get(i).getLocation())) {
					onHit();
				}
			}
			
			if (HP <= 0) {
				onDeath();
			}
			
			z = y+height+escalation*DrawPanel.SCALE;
		}
	}

	public void draw(Graphics2D g2) {
		if (living) {
			currentAnimation.draw(spriteBox.x, spriteBox.y, spriteBox.width, spriteBox.height, g2);
		}
	}
	
	public void actionStart() {
		inAction = true;
		enemyType.actionStart(this);
	}
	
	public void actionTick() {
		enemyType.actionTick(this);
		if (enemyType.actionFinishCondition(this)) {
			actionEnd();
		}
	}
	
	public void actionEnd() {
		enemyType.actionEnd(this);
		actionTimer = 0;
		inAction = false;
	}
	
	public void onHit() {
		enemyType.onHit(this);
	}
	
	public void onDeath() {
		enemyType.onDeath(this);
		living = false;
	}
}
