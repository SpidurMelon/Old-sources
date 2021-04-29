package nl.threedee.main.graphics;

import java.util.Random;

import nl.threedee.main.Game;

public class Screen extends Render {

	private Render test;
	private Render3D r3d;

	public Screen(int width, int height) {
		super(width, height);
		Random rand = new Random();
		test = new Render(256, 256);
		r3d = new Render3D(width, height);
		for (int i = 0; i < 256 * 256; i++) {
			test.pixels[i] = rand.nextInt() * (rand.nextInt(5)/4);
		}
	}

	public void render(Game game) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = 0;
		}
		for (int i = 0; i < 50; i++) {
			int anim = (int) (Math.sin((game.time+i*2)%1000.0/100) * 100);
			int anim2 = (int) (Math.cos((game.time+i*2)%1000.0/100) * 100);

		}
		r3d.floor();
		draw(r3d, 0, 0);
	}

}
