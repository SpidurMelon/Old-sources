package nl.world.main;

import nl.world.frame.Frame;
import nl.world.time.Tick;

public class Main {
	public static Frame frame = new Frame();
	public static void main(String[] args) {
		frame.setVisible(true);
		Tick.startTicking();
	}
}
