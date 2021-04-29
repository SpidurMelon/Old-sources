package nl.br.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import en.lib.drawing.SpriteMap;
import en.lib.math.MathUtils;
import en.lib.objects.GameObject;
import nl.br.objects.BRGameObject;
import nl.br.panels.DrawPanel;

public class Block extends BRGameObject {
	public final int type;
	public int ix, iy;
	/**
	 * An array to indicate whether this block is connected to other blocks, going clockwise from the top. 
	 */
	private SpriteMap spriteMap;
	public BufferedImage floorTexture;
	private BufferedImage roofTexture, pillarTexture;
	
	private GradientPaint upGrad, rightGrad, downGrad, leftGrad;	
	
	public GameObject floorCollision;
	
	public Block(int x, int y, int escalation, int type) {
		super(x*getScale(), (y-escalation)*getScale(), 0, getScale(), getScale());
		this.type = type;
		this.escalation = escalation;
		this.ix = x;
		this.iy = y;
		this.z = this.y+escalation*getScale();
		
		if (type != 0) {
			spriteMap = MapUtils.getSpriteMapByType(type);
			floorTexture = spriteMap.getSprite(MathUtils.randInt(0, spriteMap.getWidth()-1), 2);
			roofTexture = spriteMap.getSprite(MathUtils.randInt(0, spriteMap.getWidth()-1), 0);
			pillarTexture = getPillar();
			
			upGrad = new GradientPaint(getEscalatedX(), getEscalatedY(), new Color(0,0,0,50), getEscalatedX(), getEscalatedY()+getScale()/4, new Color(0,0,0,0));
			rightGrad = new GradientPaint(getEscalatedX()+(getScale()/4)*3, getEscalatedY(), new Color(0,0,0,0), getEscalatedX()+getScale(), getEscalatedY(), new Color(0,0,0,50));
			downGrad = new GradientPaint(getEscalatedX(), getEscalatedY()+(getScale()/4)*3, new Color(0,0,0,0), getEscalatedX(), getEscalatedY()+getScale(), new Color(0,0,0,50));
			leftGrad = new GradientPaint(getEscalatedX(), getEscalatedY(), new Color(0,0,0,50), getEscalatedX()+getScale()/4, getEscalatedY(), new Color(0,0,0,0));
		}
		
		if (escalation != 0) {
			floorCollision = new GameObject((int)(ix*DrawPanel.SCALE), (int)(iy*DrawPanel.SCALE), (int)DrawPanel.SCALE, (int)DrawPanel.SCALE);
		}
	}
	
	public void moveTo(int ix, int iy) {
		this.ix = ix;
		this.iy = iy;
		x = ix*getScale();
		y = (iy-escalation)*getScale();
		z = y+escalation*getScale();
		
		if (type != 0) {
			upGrad = new GradientPaint(getEscalatedX(), getEscalatedY(), new Color(0,0,0,50), getEscalatedX(), getEscalatedY()+getScale()/4, new Color(0,0,0,0));
			rightGrad = new GradientPaint(getEscalatedX()+(getScale()/4)*3, getEscalatedY(), new Color(0,0,0,0), getEscalatedX()+getScale(), getEscalatedY(), new Color(0,0,0,50));
			downGrad = new GradientPaint(getEscalatedX(), getEscalatedY()+(getScale()/4)*3, new Color(0,0,0,0), getEscalatedX(), getEscalatedY()+getScale(), new Color(0,0,0,50));
			leftGrad = new GradientPaint(getEscalatedX(), getEscalatedY(), new Color(0,0,0,50), getEscalatedX()+getScale()/4, getEscalatedY(), new Color(0,0,0,0));
		}
		
		if (escalation != 0) {
			floorCollision = new GameObject((int)(ix*DrawPanel.SCALE), (int)(iy*DrawPanel.SCALE), (int)DrawPanel.SCALE, (int)DrawPanel.SCALE);
		}
	}
	
	public static int getScale() {
		return (int)DrawPanel.SCALE;
	}
	
	public int getEscalatedX() {
		return (int)(x);
	}
	
	public int getEscalatedY() {
		return (int)(y);
	}
	
	public int getFloorX() {
		return (int)(ix*getScale());
	}
	
	public int getFloorY() {
		return (int)(iy*getScale());
	}
	
	public Rectangle getFloorRectangle() {
		if (escalation == 0) {
			return this;
		} else {
			return floorCollision;
		}
	}
	
	private BufferedImage getPillar() {
		
		BufferedImage pillarTexture = new BufferedImage(MapUtils.blockResolution, (Math.abs(escalation)+1)*MapUtils.blockResolution, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D pillarGraphics = pillarTexture.createGraphics();
		if (escalation > 0) {
			pillarGraphics.drawImage(roofTexture, 0, 0, MapUtils.blockResolution, MapUtils.blockResolution, null);
		}
		
		for (int i = 0; i < Math.abs(escalation); i++) {
			if (escalation > 0) {
				pillarGraphics.drawImage(spriteMap.getSprite(MathUtils.randInt(0, spriteMap.getWidth()-1), 1), 0, (i+1)*MapUtils.blockResolution, MapUtils.blockResolution, MapUtils.blockResolution, null);
			} else {
				pillarGraphics.drawImage(spriteMap.getSprite(MathUtils.randInt(0, spriteMap.getWidth()-1), 1), 0, (i)*MapUtils.blockResolution, MapUtils.blockResolution, MapUtils.blockResolution, null);
			}
		}
		
		if (escalation > 0) {
			GradientPaint gPaint = new GradientPaint(0, MapUtils.blockResolution, new Color(0,0,0,0), 0, escalation*MapUtils.blockResolution, new Color(0,0,0,150));
			pillarGraphics.setPaint(gPaint);
			pillarGraphics.fillRect(0, MapUtils.blockResolution, MapUtils.blockResolution, escalation*MapUtils.blockResolution);
		} else {
			GradientPaint gPaint = new GradientPaint(0, 0, new Color(0,0,0,50), 0, Math.abs(escalation)*MapUtils.blockResolution, new Color(0,0,0,255));
			pillarGraphics.setPaint(gPaint);
			pillarGraphics.fillRect(0, 0, MapUtils.blockResolution, Math.abs(escalation)*MapUtils.blockResolution);
		}
		
		pillarGraphics.dispose();
		return pillarTexture;
	}
	
	public void draw(Graphics2D g2) {
		if (type != 0) {
			
			if (escalation > 0) {
				if (DrawPanel.getBlock(ix, iy+1) == null || DrawPanel.getBlock(ix, iy+1).escalation < this.escalation) {
					g2.drawImage(pillarTexture, getEscalatedX(), getEscalatedY(), getScale(), getScale()*(escalation+1), null);
				} else {
					g2.drawImage(roofTexture, getEscalatedX(), getEscalatedY(), getScale(), getScale(), null);
				}
			} else if (escalation < 0) {
				if (DrawPanel.getBlock(ix, iy+Math.abs(escalation)) != null && DrawPanel.getBlock(ix, iy+Math.abs(escalation)).escalation == this.escalation) {
					g2.setColor(Color.BLACK);
					g2.fillRect(getEscalatedX(), getEscalatedY(), getScale(), getScale());
				}
				
				if (DrawPanel.getBlock(ix, iy-1).escalation > this.escalation) {
					g2.drawImage(pillarTexture, getFloorX(), getFloorY(), getScale(), getScale()*(Math.abs(escalation)+1), null);
				}
			}
			
			//Shadow at edges start
			if (escalation >= 0) {
				if (DrawPanel.getBlock(ix, iy-1) != null && DrawPanel.getBlock(ix, iy-1).type != type | DrawPanel.getBlock(ix, iy-1).escalation != escalation) {
					g2.setPaint(upGrad);
					g2.fillRect(getEscalatedX(), getEscalatedY(), getScale(), getScale()/4);
				}
				
				if (DrawPanel.getBlock(ix+1, iy) != null && DrawPanel.getBlock(ix+1, iy).type != type | DrawPanel.getBlock(ix+1, iy).escalation != escalation ) {
					g2.setPaint(rightGrad);
					g2.fillRect(getEscalatedX()+(getScale()/4)*3, getEscalatedY(), getScale()/4, getScale());
				}
				
				if (DrawPanel.getBlock(ix, iy+1) != null && DrawPanel.getBlock(ix, iy+1).type != type | DrawPanel.getBlock(ix, iy+1).escalation != escalation) {
					g2.setPaint(downGrad);
					g2.fillRect(getEscalatedX(), getEscalatedY()+(getScale()/4)*3, getScale(), getScale()/4);
				}
				
				if (DrawPanel.getBlock(ix-1, iy) != null && DrawPanel.getBlock(ix-1, iy).type != type | DrawPanel.getBlock(ix-1, iy).escalation != escalation) {
					g2.setPaint(leftGrad);
					g2.fillRect(getEscalatedX(), getEscalatedY(), getScale()/4, getScale());
				}
			}
			//Shadow at edges end
			
			
		}
		if (DrawPanel.DEBUG) {
			/*
			g2.setColor(Color.CYAN);
			g2.setFont(new Font("Arial", 0, 8));
			g2.drawString(String.valueOf((int)z), x, y);
			*/
		}
	}
	
	
}
