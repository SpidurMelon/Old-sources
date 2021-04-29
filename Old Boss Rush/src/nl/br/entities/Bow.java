package nl.br.entities;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.io.File;
import java.util.ArrayList;

import en.lib.drawing.Animation;
import en.lib.drawing.SpriteMap;
import en.lib.math.Vector;
import en.lib.objects.GameObject;
import en.lib.util.ShapeUtils;
import nl.br.arrows.Arrow;
import nl.br.objects.BRGameObject;
import nl.br.panels.DrawPanel;

public class Bow extends BRGameObject {
	private Player player;
	private SpriteMap bow = new SpriteMap(15, 20, 1, new File("resources/textures/characters/Player/Bow.png"));
	private Animation restAnimation = new Animation(bow, 0, 0, 0, 0, 9999);
	private Animation chargeAnimation = new Animation(bow, 0, 1, 2, 1, 60);
	private Animation chargedAnimation = new Animation(bow, 0, 2, 4, 2, 10);
	private Animation currentBowAnimation = restAnimation;
	private boolean charging = false;
	private int chargeTime = 180, chargeTimer = 0;
	
	private double arrowSpeed = 10;
	
	public Point mousePoint = new Point();
	private Point relMousePoint = new Point();
	
	public boolean bowFollowMouse = true;
	public double rotation = 0;
	
	private int xShift = -35, yShift = -20;
	
	private Point bowAnchor;
	private Vector newArrowVector;
	
	public AffineTransform bowTransform = new AffineTransform();
	public Path2D.Double initialSpriteBox = new Path2D.Double();
	
	public Bow(Point bowAnchor, Player player) {
		move(bowAnchor);
		this.player = player;
		
		Rectangle initialBox = new Rectangle(4, 0, bow.spriteWidth-8, bow.spriteHeight);
		initialSpriteBox.append(initialBox, false);
		
		newArrowVector = new Vector(Vector.getVectorDirection(bowAnchor, relMousePoint), arrowSpeed);
	}
	
	public void move(Point newBowAnchor) {
		bowAnchor = newBowAnchor;
		x = newBowAnchor.x;
		y = newBowAnchor.y;
	}
	
	public void tick() {
		currentBowAnimation.tick();
		relMousePoint = DrawPanel.makeRelative(mousePoint);
		
		if (bowFollowMouse) {
			rotation = Vector.getVectorDirection(bowAnchor, relMousePoint);
			newArrowVector.setDirection(rotation);
			
			bowTransform.setToIdentity();
			bowTransform.rotate(Math.toRadians(rotation+180), bowAnchor.x, bowAnchor.y);
			bowTransform.translate(bowAnchor.x+xShift, bowAnchor.y+yShift);
			bowTransform.scale(2, 2);
		}
	
		this.z = ShapeUtils.getMaxY(initialSpriteBox, bowTransform);
		
		if (charging) {
			chargeTimer++;
			if (chargeTimer >= chargeTime) {
				switchBowAnimation(chargedAnimation);
				charging = false;
				chargeTimer = 0;
			}
		}
	}
	
	public void draw(Graphics2D g2) {
		currentBowAnimation.draw(bowTransform, g2);
		
		if (DrawPanel.DEBUG) {
			g2.setColor(Color.BLACK);
			g2.draw(initialSpriteBox.createTransformedShape(bowTransform));
		}
	}
	
	public void chargeArrow() {
		charging = true;
		switchBowAnimation(chargeAnimation);
	}
	
	public void releaseArrow() {
		charging = false;
		chargeTimer = 0;
		switchBowAnimation(restAnimation);
		DrawPanel.addArrow(bowAnchor.x, bowAnchor.y, player.escalation, newArrowVector.getDirection(), newArrowVector.getSize(), true);
	}
	
	private void switchBowAnimation(Animation bowAnimation) {
		if (currentBowAnimation != bowAnimation) {
			currentBowAnimation.reset();
			currentBowAnimation = bowAnimation;
		}
	}
}
