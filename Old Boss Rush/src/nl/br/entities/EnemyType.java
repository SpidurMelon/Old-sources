package nl.br.entities;

import en.lib.drawing.SpriteMap;

public abstract class EnemyType {
	public int spriteWidth, spriteHeight, collideHeight, actionDelay;
	public SpriteMap animations;
	
	public EnemyType(int spriteWidth, int spriteHeight, int collideHeight, SpriteMap animations, int actionDelay) {
		this.spriteWidth = spriteWidth;
		this.spriteHeight = spriteHeight;
		this.collideHeight = collideHeight;
		this.animations = animations;
		this.actionDelay = actionDelay;
	}
	
	public abstract void init(Enemy enemy);
	
	public abstract void actionStart(Enemy enemy);
	public abstract void actionTick(Enemy enemy);
	public abstract boolean actionFinishCondition(Enemy enemy);
	public abstract void actionEnd(Enemy enemy);
	
	public abstract void onHit(Enemy enemy);
	public abstract void onDeath(Enemy enemy);
}
