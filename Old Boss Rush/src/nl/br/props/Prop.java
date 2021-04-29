package nl.br.props;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import en.lib.drawing.Animation;
import en.lib.math.MathUtils;
import en.lib.objects.GameObject;
import nl.br.objects.BRGameObject;
import nl.br.panels.DrawPanel;

public class Prop extends BRGameObject {
	
	private Animation animation;
	
	private Point[] lightningPoints;
	private int lightRadius = 30;
	
	public Prop(int x, int y, int z, int width, int height, Animation animation, Point[] lightningPoints) {
		super(x, y, z, width, height);
		this.animation = animation;
		this.lightningPoints = lightningPoints;
	}
	
	public Prop(int x, int y, int z, int width, int height, Animation animation) {
		this(x, y, z, width, height, animation, null);
	}
	
	public Prop(int x, int y, AbstractProp prop) {
		this(x, y, prop.z, prop.width, prop.height, prop.animation, prop.lightningPoints);
	}
	
	public void moveTo(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void tick() {
		animation.tick();
	}
	
	public void draw(Graphics2D g2) {
		animation.draw((int)x, (int)y, width, height, g2);
	
		if (lightningPoints != null) {
			g2.setColor(new Color(242,125,12,50));
			int xShift = MathUtils.randInt(-1, 1), yShift = MathUtils.randInt(-1, 1);
			for (Point p:lightningPoints) {
				g2.fillOval((int)(x+p.getX()-lightRadius/2.0+xShift), (int)(y+p.getY()-lightRadius/2.0+yShift), lightRadius, lightRadius);
			}
			g2.setColor(new Color(253,207,88,50));
			for (Point p:lightningPoints) {
				g2.fillOval((int)(x+p.getX()-lightRadius/3.0+xShift), (int)(y+p.getY()-lightRadius/3.0+yShift), (int)(lightRadius/1.5), (int)(lightRadius/1.5));
			}
		}
	}
}
