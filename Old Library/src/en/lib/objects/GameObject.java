package en.lib.objects;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;

import en.lib.math.Vector;
import en.lib.util.DoublePoint;
import en.lib.util.RectangleFixedEquals;

public class GameObject extends RectangleFixedEquals {
	public double z;
	public GameObject() {
		this(0,0,0,0);
	}
	
	public GameObject(int width, int height) {
		this(0,0,width,height);
	}
	
	public GameObject(int x, int y, int width, int height) {
		super(x, y, width, height);
	}
	
	public GameObject(int x, int y, double z, int width, int height) {
		super(x, y, width, height);
		this.z = z;
	}
	
	private Point getCenter() {
		return new Point((int)(x+width/2),(int)(y+height/2));
	}
	
	public Vector pathOfLeastResistance(GameObject staticObj) {
		if (intersects(staticObj)) {
			double maxAllowedDistanceX = this.width/2.0+staticObj.width/2.0;
			double currentDistanceX = Math.abs(staticObj.getCenter().getX()-this.getCenter().getX());
			double separationDX = maxAllowedDistanceX-currentDistanceX;
			
			double maxAllowedDistanceY = this.height/2.0+staticObj.height/2.0;
			double currentDistanceY = Math.abs(staticObj.getCenter().getY()-this.getCenter().getY());
			double separationDY = maxAllowedDistanceY-currentDistanceY;
			
			if (separationDX < separationDY) {
				if (getCenter().getX() > staticObj.getCenter().getX()) {
					return new Vector(0, separationDX);
				} else {
					return new Vector(180, separationDX);
				}
			} else {
				if (getCenter().getY() > staticObj.getCenter().getY()) {
					return new Vector(90, separationDY);
				} else {
					return new Vector(270, separationDY);
				}
			}
		}
		return new Vector(0, 0);
	}
	
	public Vector pathOfNthResistance(GameObject staticObj, int N) {
		/*
		if (intersects(staticObj)) {
			double maxAllowedDistanceX = this.width/2.0+staticObj.width/2.0;
			double currentDistanceX = Math.abs(staticObj.getCenter().getX()-this.getCenter().getX());
			double separationDX = maxAllowedDistanceX-currentDistanceX;
			
			double maxAllowedDistanceY = this.height/2.0+staticObj.height/2.0;
			double currentDistanceY = Math.abs(staticObj.getCenter().getY()-this.getCenter().getY());
			double separationDY = maxAllowedDistanceY-currentDistanceY;
			
			Vector result = new Vector();
			boolean upDisabled = false, rightDisabled = false, downDisabled = false, leftDisabled = false;
			for (int i = 0; i < N; i++) {
				if (separationDX < separationDY) {
					if (getCenter().getX() > staticObj.getCenter().getX() && !rightDisabled) {
						result = new Vector(0, separationDX);
						if (!leftDisabled) {
							separationDX = maxAllowedDistanceX-separationDX;
						} else {
							separationDX = 1000000;
						}
						rightDisabled = true;
					} else if (!leftDisabled) {
						result = new Vector(180, separationDX);
						if (!rightDisabled) {
							separationDX = maxAllowedDistanceX-separationDX;
						} else {
							separationDX = 1000000;
						}
						leftDisabled = true;
					}
				} else {
					if (getCenter().getY() > staticObj.getCenter().getY() && !downDisabled) {
						result = new Vector(90, separationDY);
						if (!upDisabled) {
							separationDY = maxAllowedDistanceY-separationDY;
						} else {
							separationDY = 1000000;
						}
						downDisabled = true;
					} else if (!upDisabled) {
						result = new Vector(270, separationDY);
						if (!downDisabled) {
							separationDY = maxAllowedDistanceY-separationDY;
						} else {
							separationDY = 1000000;
						}
						upDisabled = true;
					}
				}
			}
			return result;
		}
		return new Vector(0, 0);
		*/
		return pathOfResistances(staticObj)[N];
	}
	
	public Vector[] pathOfResistances(GameObject staticObj) {
		if (intersects(staticObj)) {
			double maxAllowedDistanceX = this.width/2.0+staticObj.width/2.0;
			double currentDistanceX = Math.abs(staticObj.getCenter().getX()-this.getCenter().getX());
			double separationDX = maxAllowedDistanceX-currentDistanceX;
			
			double maxAllowedDistanceY = this.height/2.0+staticObj.height/2.0;
			double currentDistanceY = Math.abs(staticObj.getCenter().getY()-this.getCenter().getY());
			double separationDY = maxAllowedDistanceY-currentDistanceY;
			
			Vector[] result = new Vector[4];
			boolean upDisabled = false, rightDisabled = false, downDisabled = false, leftDisabled = false;
			for (int i = 0; i < 4; i++) {
				if (separationDX < separationDY) {
					if (getCenter().getX() > staticObj.getCenter().getX() && !rightDisabled || leftDisabled) {
						result[i] = new Vector(0, separationDX);
						if (!leftDisabled) {
							separationDX = staticObj.width+this.width-separationDX;
						} else {
							separationDX = 1000000;
						}
						rightDisabled = true;
					} else if (!leftDisabled) {
						result[i] = new Vector(180, separationDX);
						if (!rightDisabled) {
							separationDX = staticObj.width+this.width-separationDX;
						} else {
							separationDX = 1000000;
						}
						leftDisabled = true;
					}
				} else {
					if (getCenter().getY() > staticObj.getCenter().getY() && !downDisabled || upDisabled) {
						result[i] = new Vector(90, separationDY);
						if (!upDisabled) {
							separationDY = staticObj.height+this.height-separationDY;
						} else {
							separationDY = 1000000;
						}
						downDisabled = true;
					} else if (!upDisabled) {
						result[i] = new Vector(270, separationDY);
						if (!downDisabled) {
							separationDY = staticObj.height+this.height-separationDY;
						} else {
							separationDY = 1000000;
						}
						upDisabled = true;
					}
				}
			}
			return result;
		}
		return new Vector[]{new Vector(), new Vector(), new Vector(), new Vector()};
	}
	
	
	public void draw(Graphics2D g2) {
		g2.draw(this);
	}
}
