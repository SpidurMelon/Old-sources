package nl.ai.mechanics;

import nl.ai.main.Main;
import nl.ai.util.Util;

public class Tick {
	
	
	
	private static Runnable r1 = new Runnable() {
		public void run() {
			while(Main.running) {
				
				Util.wait(200);
				Util.currentScreen().tick();
				
				Util.repaint();
			}
		}
	};
	
	public static void tick() {
		Thread t1 = new Thread(r1);
		t1.start();
	}
	
}
