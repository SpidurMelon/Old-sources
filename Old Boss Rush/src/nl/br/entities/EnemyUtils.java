package nl.br.entities;

import java.awt.Point;
import java.io.File;
import java.util.HashMap;

import en.lib.drawing.SpriteMap;
import en.lib.math.MathUtils;
import en.lib.math.Vector;
import nl.br.arrows.Arrow;
import nl.br.panels.DrawPanel;

public class EnemyUtils {
	private static final String enemyTextureFolder = "resources/textures/characters/Enemies/";
	public static EnemyType getEnemyByType(int type) {
		if (type == 1) {
			return new EnemyType(20, 20, 5, new SpriteMap(10, 10, 1, new File(enemyTextureFolder + "Blob.png")), 0) {
				private int ticksWalked = 0, walkTicks = 60;
				
				public void init(Enemy enemy) {
					enemy.movement.setSize(1);
					enemy.HP = 1;
				}
				
				public void actionStart(Enemy enemy) {
					
					enemy.movement.setDirection(MathUtils.randInt(0, 360));
				}

				public void actionTick(Enemy enemy) {
					enemy.applyMovement(enemy.movement);
					if (ticksWalked % 2 == 0) {
						DrawPanel.addArrow((int)enemy.getCenterX(), (int)enemy.getCenterY(), enemy.escalation, Vector.getVectorDirection(enemy.spriteCenter, DrawPanel.player.spriteCenter), 10, false);
					}
					ticksWalked++;
				}
				
				public boolean actionFinishCondition(Enemy enemy) {
					return ticksWalked >= walkTicks;
				}
					
				public void actionEnd(Enemy enemy) {
					//DrawPanel.addEnemy(new Enemy(enemy.x, enemy.y, getEnemyByType(1)));
					
					ticksWalked = 0;
				}
				
				public void onHit(Enemy enemy) {
					enemy.HP -= 1;
				}
				
				public void onDeath(Enemy enemy) {
					for (int i = 0; i < 360; i+=5) {
						DrawPanel.addArrow((int)enemy.getCenterX(), (int)enemy.getCenterY(), enemy.escalation, Vector.getVectorDirection(enemy.spriteCenter, DrawPanel.player.spriteCenter)+i, 10, false);
					}
				}
			};
		} else {
			return null;
		}
	 }
}
