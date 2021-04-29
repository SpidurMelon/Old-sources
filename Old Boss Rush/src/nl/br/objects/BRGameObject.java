package nl.br.objects;

import en.lib.objects.GameObject;

public class BRGameObject extends GameObject {
	public int escalation;
	
	public BRGameObject(int x, int y, int width, int height) {
		super(x,y,width,height);
	}
	
	public BRGameObject(int x, int y, double z, int width, int height) {
		super(x,y,z,width,height);
	}

	public BRGameObject() {
		super();
	}
}
