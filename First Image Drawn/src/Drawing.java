import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.Scanner;


public class Drawing extends Applet {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void paint(Graphics g) {
		setSize(1000, 900);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setPaint(Color.red);
		Image myImage = getImage(getCodeBase(), "http://img0.joyreactor.com/pics/post/portal-games-error-sandbox-909295.jpeg");
		g.drawImage(myImage, 0, 0, 750, 600, this);
	}
}
