package nl.br.arrows;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.io.File;

import en.lib.drawing.SpriteMap;
import en.lib.math.Vector;
import nl.br.objects.BRGameObject;
import nl.br.panels.DrawPanel;

public class Arrow extends BRGameObject {
	public double dX, dY;
	public Vector movement = new Vector();
	public boolean inAir = false, friendly;
	private SpriteMap spriteMap;
	
	private AffineTransform arrowTransform = new AffineTransform();
	private AffineTransform arrowTranslate = new AffineTransform();
	private AffineTransform arrowRotate = new AffineTransform();
	private AffineTransform arrowScale = new AffineTransform();
	
	public Point location = new Point();
	
	
	public Arrow(boolean friendly) {
		arrowScale.setToScale(2, 2);
		
		this.friendly = friendly;
		if (friendly) {
			spriteMap = new SpriteMap(13, 3, 0, new File("resources/textures/entities/FriendlyArrow.png"));
		} else {
			spriteMap = new SpriteMap(13, 3, 0, new File("resources/textures/entities/EnemyArrow.png"));
		}
	}
	
	public Arrow(int x, int y, int escalation, double direction, double speed, boolean friendly) {
		this(friendly);
		fire(x, y, escalation, direction, speed);
	}
	
	public void tick() {
		if (inAir) {
			dX+=movement.getXComp();
			dY+=movement.getYComp();
			x = (int)dX;
			y = (int)dY;
			location.setLocation(x, y);
			z = dY+(Math.abs(movement.getYComp())/movement.getSize())*spriteMap.spriteWidth+escalation*DrawPanel.SCALE;
			
			arrowTranslate.setToTranslation(dX-spriteMap.spriteWidth, dY-spriteMap.spriteHeight);
		}
	}
	
	public void draw(Graphics2D g2) {
		
		arrowTransform.setTransform(arrowTranslate);
		arrowTransform.concatenate(arrowRotate);
		arrowTransform.concatenate(arrowScale);
		
		g2.drawImage(spriteMap.getSprite(0, 0), arrowTransform, null);
		if (DrawPanel.DEBUG) {
			g2.setColor(Color.RED);
			g2.drawString(String.valueOf((int)z), x, y);
		}
	}
	
	public void fire(int x, int y, int escalation, double direction, double speed) {
		this.x = x;
		this.y = y;
		dX = x;
		dY = y;
		location.setLocation(x, y);
		this.escalation = escalation;
		movement.setDirection(direction);
		movement.setSize(speed);
		
		arrowRotate.setToRotation(Math.toRadians(direction+180), spriteMap.spriteWidth, spriteMap.spriteHeight);
		
		inAir = true;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public void land() {
		inAir = false;
	}
}
