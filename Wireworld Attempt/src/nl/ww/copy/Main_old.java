package nl.ww.copy;
/*package nl.ww.main;

import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class Main_old extends Applet {
	private static final long serialVersionUID = 1L;

	private int x,y = 0;
	private int gridSize = 10;
	private Image i;
	private Graphics doubleG;
	
	@Override
	public void update(Graphics g) {
		if (i == null) {
			i = createImage(this.getSize().width, this.getSize().height);
			doubleG = i.getGraphics();
		}
		
		doubleG.setColor(getBackground());
		doubleG.fillRect(0, 0, this.getSize().width, this.getSize().height);
		doubleG.setColor(getForeground());
		paint(doubleG);
		
		g.drawImage(i, 0, 0, this);
	}
	
	
	public void init() {
		this.setSize(501, 501);
	}

	public void paint(Graphics g) {
		drawGrid(g);
	}

	public void drawGrid(Graphics g) {
		for (y = 0; y < getHeight(); y+=10) {
			for (x = 0; x < getWidth(); x+=10) {
				g.drawRect(x, y, gridSize, gridSize);
			}
			x = 0;
		}
	}
}*/
