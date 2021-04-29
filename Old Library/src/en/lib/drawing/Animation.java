package en.lib.drawing;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Animation {
	private ArrayList<BufferedImage> sprites = new ArrayList<BufferedImage>();
	public int spriteWidth = 0, spriteHeight = 0;
	private int timer = 1, frameDelay = 1, spriteNumber = 0;
	public Animation(SpriteMap sm, int startX, int startY, int endX, int endY, int frameDelay) {
		this.frameDelay = frameDelay;
		spriteWidth = sm.spriteWidth;
		spriteHeight = sm.spriteHeight;
		for (int y = startY; y <= endY; y++) {
			for (int x = startX; x <= endX; x++) {
				sprites.add(sm.getSprite(x, y));
			}
		}
	}
	
	public void tick() {
		if (timer % frameDelay == 0) {
			switchSprite();
			timer = 0;
		}
		timer++;
	}
	
	private void switchSprite() {
		if (spriteNumber < sprites.size()-1) {
			spriteNumber += 1;
		} else {
			spriteNumber = 0;
		}
	}
	
	public void reset() {
		timer = 1;
		spriteNumber = 0;
	}

	public void draw(int x, int y, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), x, y, spriteWidth, spriteHeight, null);
		}
	}
	
	public void drawPart(int partX, int partY, int partWidth, int partHeight, int drawX, int drawY, int drawWidth, int drawHeight, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber).getSubimage(partX, partY, partWidth, partHeight), drawX, drawY, drawWidth, drawHeight, null);
		}
	}
	
	public void draw(AffineTransform at, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), at, null);
		}
	}
	
	public void draw(int x, int y, int width, int height, Graphics2D g2) {
		if (!sprites.isEmpty()) {
			g2.drawImage(sprites.get(spriteNumber), x, y, width, height, null);
		}
	}
}
