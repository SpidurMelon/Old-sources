package nl.br.main;

import en.lib.setup.RegularFrame;
import en.lib.setup.Tick;
import nl.br.panels.DrawPanel;

public class BossRush {
	public static DrawPanel drawPanel;
	public static void main(String[] args) {
		RegularFrame frame = new RegularFrame();
		drawPanel = new DrawPanel();
		frame.addPanel(drawPanel);
	}
}
