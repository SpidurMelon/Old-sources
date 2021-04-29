package nl.br.props;

import java.awt.Point;

import en.lib.drawing.Animation;

public class AbstractProp {
	public int width, height, z;
	public Animation animation;
	public Point[] lightningPoints;
	
	public AbstractProp(int width, int height, int z, Animation animation, Point[] lightningPoints) {
		this.width = width;
		this.height = height;
		this.z = z;
		this.animation = animation;
		this.lightningPoints = lightningPoints;
	}
	public AbstractProp(int width, int height, int z, Animation animation) {
		this(width, height, z, animation, null);
	}
}
