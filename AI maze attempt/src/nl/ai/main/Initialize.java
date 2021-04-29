package nl.ai.main;

import nl.ai.frame.Frame;
import nl.ai.mechanics.Tick;

public class Initialize {
	public static Frame frame;

	
	public static void init() {
		frame = new Frame();
		frame.setVisible(true);
		
		Tick.tick();
	}
}
