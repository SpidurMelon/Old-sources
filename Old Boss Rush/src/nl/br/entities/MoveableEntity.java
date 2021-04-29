package nl.br.entities;

import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

import en.lib.math.Vector;
import en.lib.objects.GameObject;
import nl.br.map.Block;
import nl.br.objects.BRGameObject;
import nl.br.panels.DrawPanel;

public class MoveableEntity extends BRGameObject {
	public double dX, dY;
	
	public double speed = 3.5;
	public Vector movement = new Vector(0, speed);
	public boolean up = false, down = false, left = false, right = false;
	
	public Rectangle spriteBox = new Rectangle();
	public Point spriteCenter = new Point();
	public Point feetCenter = new Point();
	
	public ArrayList<Block> surroundingBlocks = new ArrayList<Block>();
	
	public boolean walking = false;
	public Point walkToPoint = new Point();

	private int walkingSmoothness = 1;
	
	public int HP = 10;
	
	public MoveableEntity(int startX, int startY, int spriteWidth, int spriteHeight, int collideHeight) {
		super(startX, startY+spriteHeight-collideHeight, spriteWidth, collideHeight);
		dX = startX;
		dY = startY;
		
		spriteBox.setBounds((int)dX, (int)dY, spriteWidth, spriteHeight);
		spriteCenter.setLocation(spriteBox.getCenterX(), spriteBox.getCenterY());
		
		feetCenter.setLocation(getCenterX(), getCenterY());
	}
	
	public void tick() {
		DrawPanel.getSurroundingBlocks(this, true);
		
		if (walking) {
			movement.setDirection(Vector.getVectorDirection(feetCenter, walkToPoint));
			movement.setSize(speed/walkingSmoothness);
			for (int i = 0; i < walkingSmoothness; i++) {
				movement.setDirection(Vector.getVectorDirection(feetCenter, walkToPoint));
				applyMovement(movement);
				if (Math.abs(walkToPoint.x-getCenterX()) < 3 && Math.abs(walkToPoint.y-getCenterY()) < 3) {
					walking = false;
					break;
				}
			}
		}
		
		z = y+height+escalation*DrawPanel.SCALE;
	}
	
	public void walkTo(int x, int y) {
		walkToPoint.setLocation(x, y);
		walking = true;
	}
	
	public void moveTo(int x, int y) {
		dX = x-width/2;
		dY = y+height/2-spriteBox.height;
		this.x = (int)dX;
		this.y = (int)(dY+spriteBox.getHeight()-height);
		
		spriteBox.setLocation((int)dX, (int)dY);
		spriteCenter.setLocation(spriteBox.getCenterX(), spriteBox.getCenterY());
		
		feetCenter.setLocation(getCenterX(), getCenterY());
	}
	
	public void applyMovement(Vector v) {
		dX += v.getXComp();
		dY += v.getYComp();
		x = (int)dX;
		y = (int)(dY+spriteBox.getHeight()-height);
		collide();
		
		spriteBox.setLocation((int)dX, (int)dY);
		spriteCenter.setLocation(spriteBox.getCenterX(), spriteBox.getCenterY());
		
		feetCenter.setLocation(getCenterX(), getCenterY());
	}
	
	public void collide() {
		for (int i = 0; i < surroundingBlocks.size(); i++) {
			if (isBarrierForMe(surroundingBlocks.get(i))) {
				GameObject barrier;
				if (surroundingBlocks.get(i).escalation == 0) {
					barrier = surroundingBlocks.get(i);
				} else {
					barrier = surroundingBlocks.get(i).floorCollision;
				}
				
				Vector collisionVector = this.pathOfLeastResistance(barrier);
				
				if (collisionVector.getSize() > 0) {
					//Check for blocks in the way of pushing start
					boolean blockUp = false, blockRight = false, blockDown = false, blockLeft = false;
					int blockIX = surroundingBlocks.get(i).ix, blockIY = surroundingBlocks.get(i).iy;
					if (isBarrierForMe(blockIX, blockIY+1)) {
						blockDown = true;
					} 
					if (isBarrierForMe(blockIX, blockIY-1)) {
						blockUp = true;
					} 
					if (isBarrierForMe(blockIX+1, blockIY)) {
						blockRight = true;
					} 
					if (isBarrierForMe(blockIX-1, blockIY)) {
						blockLeft = true;
					}
					boolean cantPushThatWay = false;
					for (int j = 0; j < 3; j++) {
						cantPushThatWay = false;
						
						if (collisionVector.getDirection() == 90 && blockDown) {
							cantPushThatWay = true;
						}
						if (collisionVector.getDirection() == 270 && blockUp) {
							cantPushThatWay = true;
						}
						if (collisionVector.getDirection() == 0 && blockRight) {
							cantPushThatWay = true;
						}
						if (collisionVector.getDirection() == 180 && blockLeft) {
							cantPushThatWay = true;
						}
						
						if (cantPushThatWay) {
							collisionVector = this.pathOfNthResistance(barrier, j);
						} else {
							break;
						}
					}
					//Check for blocks in the way of pushing end
					
					if (!cantPushThatWay) {
						applyMovement(collisionVector);
					}
				}
			}
		}
	}
	
	public boolean isBarrierForMe(Block b) {
		if (b == null) {
			return true;
		}
		if (b.escalation != escalation) {
			return true;
		}
		if (b.type == 0) {
			return true;
		}
		return false;
	}
	
	public boolean isBarrierForMe(int ix, int iy) {
		return isBarrierForMe(DrawPanel.getBlock(ix, iy));
	}
	/*
	private boolean isBarrierAt(int ix, int iy) {
		for (int i = 0; i < barriers.size(); i++) {
			if (barriers.get(i).ix == ix && barriers.get(i).iy == iy) {
				return true;
			}
		}
		return false;
	*/
}
