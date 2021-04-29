package en.lib.setup;

import java.util.ArrayList;


public class Tick {
	public static boolean running = false;
	public static double ticksPerSecond = 60.0;
	public static ArrayList<Panel> tickList = new ArrayList<Panel>();
	
	public static Runnable r1 = new Runnable() {
		public void run() {
			while (true) {
				long timeBeforeTick = System.currentTimeMillis();
				if (running) {
					for (int i = 0; i < tickList.size(); i++) {
						tickList.get(i).tick();
					}
				}
				long tickTime = System.currentTimeMillis() - timeBeforeTick;
				try {
					if ((1000.0/ticksPerSecond)-tickTime > 0) {
						Thread.sleep((long)(1000.0/ticksPerSecond)-tickTime);
					}
					//Thread.sleep((long)(1000.0/ticksPerSecond));
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};
	public static Thread tick = new Thread(r1);;
	
	public static void start() {
		tick.start();
		running = true;
	}
	
	public static void pauseUnpause() {
		running = !running;
	}
}
