package en.lib.setup;

import java.util.ArrayList;

import javax.swing.JFrame;

/**
 * A standard frame for any purpose
 * @author SpidurMelon
 */
public class RegularFrame extends JFrame {
	public RegularFrame() {
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		
		Tick.start();
	}
	
	/**
	 * Adds a panel to the frame and to the tickList, then packs the frame
	 * @param p
	 * Panel to add
	 */
	public void addPanel(Panel p) {
		Tick.tickList.add(p);
		add(p);
		pack();
	}
}
