import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;



public class Game extends Applet implements KeyListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Rectangle rect;
	private Rectangle rect2;
	private Rectangle rect3;
	private Rectangle rect4;
	private Rectangle rect5;
	private Rectangle rect6;
	private Rectangle rect7;
	private Rectangle rect8;
	private Rectangle rect9;
	private Rectangle rect10;
	private Rectangle rect11;
	private Rectangle rect12;
	private Rectangle rect13;
	private Rectangle rect14;
	private Rectangle rect15;
	private Rectangle rect16;
	private Rectangle rect17;
	private Rectangle rect18;
	private Rectangle rect19;
	private Rectangle rect20;
	private Rectangle rect21;
	private Rectangle rect22;
	private Rectangle rect23;
	private Rectangle rect24;
	private Rectangle rect25;
	private Rectangle portal1;
	private Rectangle portal2;
	
	private Image i;
	private Graphics doubleG;
	private static int Fif1 = 50;
	private static int Fif2 = 50;
	private static int Fif3 = 50;
	private static int Fif4 = 50;
	private static boolean DrawOpen = false;
	private static boolean Locked = true;
	private static boolean InYellow = false;
	private static boolean Finish = false;
	private static boolean NextToBlock = false;
	private static int dot = 50;
	private static int dot2 = 50;
	private static boolean Dot = true;
	private static boolean TT = true;
	private static boolean nexttoportal1 = false;
	private static boolean nexttoportal2 = false;
	private ArrayList<Integer> KeysDown;
	private boolean didgotruportal2;
	private boolean FirstImage = true;
	private boolean SecondImage = false;
	private static boolean didgotruportal1 = false;
	
	
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
	
	
	
	public void paint(Graphics g) {
		Image background = getImage(getCodeBase(), "imgres.jpg");	
		
		
		if (NextToBlock == false) {
			Fif1 = 50;
			Fif2 = 50;
			Fif3 = 50;
			Fif4 = 50;
		}
		
		setSize(1000, 900);
		
		Graphics2D g2 = (Graphics2D)g;
		g2.setFont(new Font("Arial", Font.PLAIN, 20));
		g.drawString("Inventory", 10, 20);
		g2.setPaint(Color.gray);
		g2.fill(rect21);
		g2.fill(rect22);
		g2.setPaint(Color.white);
		
		g2.setPaint(Color.gray);
		g2.fill(rect2);
		g2.fill(rect3);
		g2.fill(rect4);
		g2.fill(rect5);
		g2.fill(rect6);
		g2.fill(rect7);
		g2.fill(rect8);
		g2.fill(rect9);
		g2.fill(rect10);
		g2.fill(rect11);
		g2.fill(rect12);
		g2.fill(rect13);
		g2.fill(rect14);
		g2.fill(rect15);
		g2.fill(rect16);
		g2.fill(rect17);
		g2.fill(rect18);
		g2.fill(rect19);
		g2.fill(rect20);
		g2.fill(rect23);
		g2.setPaint(Color.white);
		if (InYellow) {
			g2.setPaint(Color.white);
		}
		
		
		g2.setPaint(Color.red);
		if (InYellow) {
			g2.setPaint(Color.green);
			Locked = false;
		}
		
		
		Image brick = getImage(getCodeBase(), "index.jpg");	
		Image character = getImage(getCodeBase(), "Hi.png");
		Image character2 = getImage(getCodeBase(), "Hi2.png");
		Image myImage = getImage(getCodeBase(), "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTIyqruMAFsCFoHN0C9R08QAMr7cp3Qeg0B40IM0bFm45pB11D1");                
		Image Smiley = getImage(getCodeBase(), "http://techtalkafrica.com/wp-content/uploads/2012/06/smileys.jpg");
		Image Key = getImage(getCodeBase(), "http://icons.iconarchive.com/icons/aha-soft/security/256/key-icon.png");
		Image Open = getImage(getCodeBase(), "https://encrypted-tbn1.gstatic.com/images?q=tbn:ANd9GcT5y8AJfXAhcxyFsJUIONNZ8WDMzX_fHibfpoEGPga1I43GuRbw");
		Image Closed = getImage(getCodeBase(), "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcQu-mUOfwnWwYAsT69QWu1ykbGRQaWg7ACWxw6GNvoBkjZ1Z2c2");
		int IX = 500;
		int IY = 400;
		int DoorX = 500;
		int DoorY = 650;
		if (InYellow) {
			IX = 10;
			IY = 25;
		}
		if (didgotruportal1){
			
		}
		if (DrawOpen) {
			IX = 1200;
			g.drawImage(Open, 500, 650, 50, 50, this);
			DoorX = 1200;
			DoorY = 1200;
		}
		if (FirstImage) {
		g.fillRect(rect.x, rect.y, dot, dot2);
		g.drawImage(character2, rect.x, rect.y, dot, dot2, this);
		}

		if (SecondImage) {
		g.fillRect(rect.x, rect.y, dot, dot2);
		g.drawImage(character, rect.x, rect.y, 50, 50, this);
		}
		g.setColor(Color.YELLOW);
		g.fillRect(DoorX, DoorY, 50, 50);
		g.drawImage(Closed, DoorX, DoorY, 50, 50, this);
		g.drawImage(Key, IX, IY, 50, 50, this);
		g.drawImage(myImage, 400, 600, 50, 50, this);
		g.drawImage(brick, rect2.x, rect2.y, 50, 50, this);
		g.drawImage(brick, rect3.x, rect3.y, 50, 50, this);
		g.drawImage(brick, rect4.x, rect4.y, 50, 50, this);
		g.drawImage(brick, rect5.x, rect5.y, 50, 50, this);
		g.drawImage(brick, rect6.x, rect6.y, 50, 50, this);
		g.drawImage(brick, rect7.x, rect7.y, 50, 50, this);
		g.drawImage(brick, rect8.x, rect8.y, 50, 50, this);
		g.drawImage(brick, rect9.x, rect9.y, 50, 50, this);
		g.drawImage(brick, rect10.x, rect10.y, 50, 50, this);
		g.drawImage(brick, rect11.x, rect11.y, 50, 50, this);
		g.drawImage(brick, rect12.x, rect12.y, 50, 50, this);
		g.drawImage(brick, rect13.x, rect13.y, 50, 50, this);
		g.drawImage(brick, rect14.x, rect14.y, 50, 50, this);
		g.drawImage(brick, rect15.x, rect15.y, 50, 50, this);
		g.drawImage(brick, rect16.x, rect16.y, 50, 50, this);
		g.drawImage(brick, rect17.x, rect17.y, 50, 50, this);
		g.drawImage(brick, rect18.x, rect18.y, 50, 50, this);
		g.drawImage(brick, rect19.x, rect19.y, 50, 50, this);
		g.drawImage(brick, rect20.x, rect20.y, 50, 50, this);
		g.drawImage(brick, rect21.x, rect21.y, 50, 50, this);
		g.drawImage(brick, rect22.x, rect22.y, 50, 50, this);
		g.drawImage(brick, rect23.x, rect23.y, 50, 50, this);
		g2.setPaint(Color.red);
		if (Finish) {
			g2.setFont(new Font("Arial", Font.PLAIN, 100));
			g.drawString("En Klaar!", 250, 250);
			
			//g.drawImage(Smiley, 660, 100, 300, 200, this);
			g.drawRect(660, 100, 300, 200);
			
		}
		g2.setPaint(Color.gray);
		g2.fill(rect21);
		g2.fill(rect22);
		g.drawImage(brick, rect21.x, rect21.y, 50, 50, this);
		g.drawImage(brick, rect22.x, rect22.y, 50, 50, this);
		g2.setPaint(Color.red);
		g2.fill(portal1);
		g2.setPaint(Color.blue);
		g2.fill(portal2);
		}
		
			

	public void keyPressed(KeyEvent e) {
		if (!KeysDown.contains(e.getKeyCode()))
		KeysDown.add(new Integer(e.getKeyCode()));
			
		moveRect();
		}

	public void keyReleased(KeyEvent e) {
		KeysDown.remove(new Integer(e.getKeyCode()));
		
	}

	public void moveRect() {
		
		int x = rect.x;
		int y = rect.y;
		
		// first block
		if (rect2.x + 50 == rect.x && rect2.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect2.x - 50 == rect.x && rect2.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect2.y + 50 == rect.y && rect2.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect2.y - 50 == rect.y && rect2.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		// second block
		
		
		if (rect3.x + 50 == rect.x && rect3.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect3.x - 50 == rect.x && rect3.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect3.y + 50 == rect.y && rect3.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect3.y - 50 == rect.y && rect3.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		// third block
		
		
		if (rect4.x + 50 == rect.x && rect4.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect4.x - 50 == rect.x && rect4.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect4.y + 50 == rect.y && rect4.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect4.y - 50 == rect.y && rect4.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		// fourth block
		
		if (rect5.x + 50 == rect.x && rect5.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect5.x - 50 == rect.x && rect5.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect5.y + 50 == rect.y && rect5.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect5.y - 50 == rect.y && rect5.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect6.x + 50 == rect.x && rect6.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect6.x - 50 == rect.x && rect6.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect6.y + 50 == rect.y && rect6.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect6.y - 50 == rect.y && rect6.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect7.x + 50 == rect.x && rect7.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect7.x - 50 == rect.x && rect7.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect7.y + 50 == rect.y && rect7.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect7.y - 50 == rect.y && rect7.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect7.x + 50 == rect.x && rect7.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect7.x - 50 == rect.x && rect7.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect7.y + 50 == rect.y && rect7.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect7.y - 50 == rect.y && rect7.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect8.x + 50 == rect.x && rect8.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect8.x - 50 == rect.x && rect8.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect8.y + 50 == rect.y && rect8.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect8.y - 50 == rect.y && rect8.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect9.x + 50 == rect.x && rect9.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect9.x - 50 == rect.x && rect9.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect9.y + 50 == rect.y && rect9.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect9.y - 50 == rect.y && rect9.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect9.x + 50 == rect.x && rect9.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect9.x - 50 == rect.x && rect9.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect9.y + 50 == rect.y && rect9.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect9.y - 50 == rect.y && rect9.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect10.x + 50 == rect.x && rect10.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect10.x - 50 == rect.x && rect10.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect10.y + 50 == rect.y && rect10.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect10.y - 50 == rect.y && rect10.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect11.x + 50 == rect.x && rect11.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect11.x - 50 == rect.x && rect11.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect11.y + 50 == rect.y && rect11.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect11.y - 50 == rect.y && rect11.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect12.x + 50 == rect.x && rect12.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect12.x - 50 == rect.x && rect12.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect12.y + 50 == rect.y && rect12.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect12.y - 50 == rect.y && rect12.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect13.x + 50 == rect.x && rect13.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect13.x - 50 == rect.x && rect13.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect13.y + 50 == rect.y && rect13.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect13.y - 50 == rect.y && rect13.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect14.x + 50 == rect.x && rect14.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect14.x - 50 == rect.x && rect14.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect14.y + 50 == rect.y && rect14.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect14.y - 50 == rect.y && rect14.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect15.x + 50 == rect.x && rect15.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect15.x - 50 == rect.x && rect15.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect15.y + 50 == rect.y && rect15.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect15.y - 50 == rect.y && rect15.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect16.x + 50 == rect.x && rect16.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect16.x - 50 == rect.x && rect16.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect16.y + 50 == rect.y && rect16.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect16.y - 50 == rect.y && rect16.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect17.x + 50 == rect.x && rect17.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect17.x - 50 == rect.x && rect17.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect17.y + 50 == rect.y && rect17.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect17.y - 50 == rect.y && rect17.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect18.x + 50 == rect.x && rect18.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect18.x - 50 == rect.x && rect18.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect18.y + 50 == rect.y && rect18.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect18.y - 50 == rect.y && rect18.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect19.x + 50 == rect.x && rect19.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect19.x - 50 == rect.x && rect19.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect19.y + 50 == rect.y && rect19.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect19.y - 50 == rect.y && rect19.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect20.x + 50 == rect.x && rect20.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect20.x - 50 == rect.x && rect20.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect20.y + 50 == rect.y && rect20.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect20.y - 50 == rect.y && rect20.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect21.x + 50 == rect.x && rect21.y == rect.y) {
			
			NextToBlock = true;
		}
		
		if (rect21.x - 50 == rect.x && rect21.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect21.y + 50 == rect.y && rect21.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect21.y - 50 == rect.y && rect21.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		
		if (rect22.x + 50 == rect.x && rect22.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect22.x - 50 == rect.x && rect22.y == rect.y) {
			nexttoportal1 = true;
			
			NextToBlock = true;
		}
		
		if (rect22.y + 50 == rect.y && rect22.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect22.y - 50 == rect.y && rect22.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		
		if (rect23.x + 50 == rect.x && rect23.y == rect.y) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect23.x - 50 == rect.x && rect23.y == rect.y) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect23.y + 50 == rect.y && rect23.x == rect.x) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect23.y - 50 == rect.y && rect23.x == rect.x) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect25.x + 50 == rect.x && rect25.y == rect.y && Locked) {
			Fif3 = 0;
			NextToBlock = true;
		}
		
		if (rect25.x - 50 == rect.x && rect25.y == rect.y && Locked) {
			Fif4 = 0;
			NextToBlock = true;
		}
		
		if (rect25.y + 50 == rect.y && rect25.x == rect.x && Locked) {
			Fif1 = 0;
			NextToBlock = true;
		}
		
		if (rect25.y - 50 == rect.y && rect25.x == rect.x && Locked) {
			Fif2 = 0;
			NextToBlock = true;
		}
		
		
		
		if (rect.x == 400 && rect.y == 600 && Dot) {
			Finish = true;
			Dot = false;
		}
		
		if (rect.x == 500 && rect.y == 400 ) {
			InYellow = true;
		}
		
		if (rect.x == 550 && rect.y == 650 && !Locked) {
			DrawOpen = true;
		}
		
		if (rect22.x == rect.x && rect22.y == rect.y) {
			x = 450;
			y = 400;
			Fif3 = 0;
			Fif1 = 0;
			Fif2 = 0;
			didgotruportal1  = true;
		}
		
		if (rect21.x == rect.x && rect21.y == rect.y) {
			x = 700;
			y = 550;
			Fif4 = 0;
			Fif1 = 0;
			Fif2 = 0;
			didgotruportal2  = true;
		}
		
		
		else { 
			NextToBlock = false;
			nexttoportal1 = false;
			nexttoportal2 = false;
			didgotruportal1 = false;
			didgotruportal2 = false;
		}
		repaint();
		
		
		
		
		// To Move
		
		if (KeysDown.contains(KeyEvent.VK_UP)) {
			y = y - Fif1;
			if (FirstImage) {
				FirstImage = false;
				SecondImage = true;
			}
			else {
				SecondImage = false;
				FirstImage = true;
			}
		}
		if (KeysDown.contains(KeyEvent.VK_DOWN)) {
			y = y + Fif2;
			if (FirstImage) {
				FirstImage = false;
				SecondImage = true;
				
			}
			else {
				SecondImage = false;
				FirstImage = true;
				
			}
		}
		if (KeysDown.contains(KeyEvent.VK_LEFT)){
			x = x - Fif3;
			if (FirstImage) {
				FirstImage = false;
				SecondImage = true;
				
			}
			else {
				SecondImage = false;
				FirstImage = true;
				
			}
		}
		if (KeysDown.contains(KeyEvent.VK_RIGHT)){
			x = x + Fif4;
			if (FirstImage) {
				FirstImage = false;
				SecondImage = true;
				
			}
			else {
				SecondImage = false;
				FirstImage = true;
				
			}
		}
		
		
		if (KeysDown.contains(KeyEvent.VK_ESCAPE))
			System.exit(0); 
		
		
		rect.setLocation(x,y);
		repaint();
		}
	
	
	

	public void keyTyped(KeyEvent arg0) {
		
	}

	

	public void init() {
		if (TT) {
		this.addKeyListener(this);
		KeysDown = new ArrayList<Integer>();
		rect = new Rectangle(500, 500, 50, 50);
		TT = false;
		}
		
		rect2 = new Rectangle(550, 500, 50, 50);
		rect3 = new Rectangle(450, 500, 50, 50);
		rect4 = new Rectangle(500, 600, 50, 50);
		rect5 = new Rectangle(450, 550, 50, 50);
		rect6 = new Rectangle(550, 600, 50, 50);
		rect7 = new Rectangle(650, 500, 50, 50);
		rect8 = new Rectangle(650, 600, 50, 50);
		rect9 = new Rectangle(650, 450, 50, 50);
		rect10 = new Rectangle(600, 400, 50, 50);
		rect11 = new Rectangle(650, 650, 50, 50);
		rect12 = new Rectangle(600, 700, 50, 50);
		rect13 = new Rectangle(550, 700, 50, 50);
		rect14 = new Rectangle(500, 700, 50, 50);
		rect15 = new Rectangle(450, 700, 50, 50);
		rect16 = new Rectangle(400, 650, 50, 50);
		rect17 = new Rectangle(350, 600, 50, 50);
		rect18 = new Rectangle(400, 550, 50, 50);
		rect19 = new Rectangle(550, 350, 50, 50);
		rect20 = new Rectangle(500, 350, 50, 50);
		rect21 = new Rectangle(450, 400, 50, 50);
		rect22 = new Rectangle(700, 550, 50, 50);
		rect23 = new Rectangle(500, 450, 50, 50);
		rect24 = new Rectangle(500, 400, 50, 50);
		rect25 = new Rectangle(500, 650, 50, 50);
		portal1 = new Rectangle(700, 550, 10, 50);
		portal2 = new Rectangle(490, 400, 10, 50);
		
		
		
		
		moveRect();
	}
	
}