package nl.world.frame;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;

import nl.world.panel.Miscelaneous;
import nl.world.panel.World;
import nl.world.panel.Panel;

public class Frame extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public static World world = new World();
	public static Miscelaneous misc = new Miscelaneous();
	
	public static ArrayList<Panel> panels = new ArrayList<Panel>();
	public Frame() {
		super("Name");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(null);
		
		addPanel(world);
		addPanel(misc);
		resize();
	}
	
	public void tick() {
		for (int i = 0; i < panels.size(); i++) {
			panels.get(i).tick();
		}
	}
	
	public void addPanel(Panel panel) {
		this.add(panel);
		panels.add(panel);
	}
	
	public void resize() {
		int width = 0;
		int height = 0;
		for (int i = 0; i < panels.size(); i++) {
			width += panels.get(i).WIDTH-width+panels.get(i).x;
			height += panels.get(i).HEIGHT-height+panels.get(i).y;
		}
		setSize(width+7, height+30);
	}
}
