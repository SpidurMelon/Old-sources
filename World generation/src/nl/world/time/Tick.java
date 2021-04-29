package nl.world.time;

import nl.world.main.Main;
import nl.world.util.Util;


public class Tick {
	public static void startTicking() {
		Thread ticking = new Thread(r1);
		ticking.start();
	}
	

	private static Runnable r1 = new Runnable() {
		public void run() {
			boolean isRunning = true;
			while(isRunning) {
				Main.frame.tick();
				Util.sleep(1);
			}
		}
	};
	
}
