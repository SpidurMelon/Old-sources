import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class App extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static ImageIcon img = new ImageIcon("Duck.jpg");
	private static String dub;
	private static int dubdub = 0;
	private static int DPS = 0;
	private static String dubdubS = String.valueOf(dubdub);
	private static String CPSS = String.valueOf(DPS);
	private static JLabel label1 = new JLabel("Ducks: " + dubdubS + "   DPS: "
			+ DPS);

	static String ePath = System.getProperty("user.home");

	private static String strFilePath = ePath.replace("\\", "/") + "\\Ints.txt";
	private static String CPSF = ePath.replace("\\", "/") + "\\CPS.txt";

	public void actionPerformed(ActionEvent e) {
		dub = e.getActionCommand();

		if (dub.equalsIgnoreCase("Reset")) {
			dubdub = 0;
			DPS = 0;
			dubdubS = String.valueOf(dubdub);
			label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
		}

		if (dub.equalsIgnoreCase("Duck")) {
			dubdub += DPS / 20 + 1;
			dubdubS = String.valueOf(dubdub);
			label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);

			try {
				// Open an audio input stream.
				java.net.URL url = this.getClass().getClassLoader()
						.getResource("Swek.wav");
				AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
				// Get a sound clip resource.
				Clip clip = AudioSystem.getClip();
				// Open audio clip and load samples from the audio input stream.
				clip.open(audioIn);
				clip.start();
			} catch (UnsupportedAudioFileException l) {
				l.printStackTrace();
			} catch (IOException a) {
				a.printStackTrace();
			} catch (LineUnavailableException p) {
				p.printStackTrace();
			}

		}

		if (dub.equalsIgnoreCase("DuckEgg   DPS: 1   Cost: 50")) {
			if (dubdub >= 50) {
				DPS += 1;
				dubdub -= 50;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}

		if (dub.equalsIgnoreCase("Mother Duck   DPS: 10   Cost: 1000")) {
			if (dubdub >= 1000) {
				DPS += 10;
				dubdub -= 1000;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}

		if (dub.equalsIgnoreCase("Duck Hunter   DPS: 100   Cost: 50000")) {
			if (dubdub >= 5000) {
				DPS += 100;
				dubdub -= 5000;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}
		if (dub.equalsIgnoreCase("Duck Farm   DPS: 1000   Cost: 100000")) {
			if (dubdub >= 100000) {
				DPS += 1000;
				dubdub -= 100000;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}

		if (dub.equalsIgnoreCase("Robot Duck   DPS: 10000   Cost: 5000000")) {
			if (dubdub >= 5000000) {
				DPS += 10000;
				dubdub -= 5000000;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}

		if (dub.equalsIgnoreCase("Duck Cloner   DPS: 100000   Cost: 10000000")) {
			if (dubdub >= 10000000) {
				DPS += 100000;
				dubdub -= 10000000;
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		new App().setVisible(true);

		String line = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(strFilePath);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {
				dubdub = Integer.valueOf(line);
				CPSS = String.valueOf(DPS);
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();

		}

		String line2 = null;
		try {
			// FileReader reads text files in the default encoding.
			FileReader fileReader = new FileReader(CPSF);

			// Always wrap FileReader in BufferedReader.
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line2 = bufferedReader.readLine()) != null) {
				DPS = Integer.valueOf(line2);
				CPSS = String.valueOf(DPS);
				dubdubS = String.valueOf(dubdub);
				label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			}

			// Always close files.
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();

		}

		final boolean Always = true;
		while (Always) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				Thread.currentThread().interrupt();
			}
			dubdub += DPS;
			dubdubS = String.valueOf(dubdub);
			label1.setText("Ducks: " + dubdubS + "   DPS: " + DPS);
			CPSS = String.valueOf(DPS);

		}
	}

	public App() {
		super("Duck Clicker");
		setSize(700, 440);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);

		JButton Duckt = new JButton();
		JButton button = new JButton("Shop");
		JButton button2 = new JButton("");
		JButton Shop1 = new JButton("DuckEgg   DPS: 1   Cost: 50");
		JButton Shop2 = new JButton("Mother Duck   DPS: 10   Cost: 1000");
		JButton Shop3 = new JButton("Duck Hunter   DPS: 100   Cost: 50000");
		JButton Shop4 = new JButton("Duck Farm   DPS: 1000   Cost: 100000");
		JButton Shop5 = new JButton("Robot Duck   DPS: 10000   Cost: 5000000");
		JButton Shop6 = new JButton(
				"Duck Cloner   DPS: 100000   Cost: 10000000");
		JButton Reset = new JButton("Reset");
		ImageIcon DuckBG = new ImageIcon(getClass().getResource("Duck.png"));
		ImageIcon ButtonI = new ImageIcon(getClass().getResource("KButton.png"));
		ImageIcon Duck = new ImageIcon(getClass().getResource("Dicht.png"));
		button2.setIcon(DuckBG);
		Duckt.setIcon(Duck);

		// Making the shop buttons Working Right
		Shop1.setIcon(ButtonI);
		Shop1.setHorizontalTextPosition(0);
		Shop2.setIcon(ButtonI);
		Shop2.setHorizontalTextPosition(0);
		Shop3.setIcon(ButtonI);
		Shop3.setHorizontalTextPosition(0);
		Shop4.setIcon(ButtonI);
		Shop4.setHorizontalTextPosition(0);
		Shop5.setIcon(ButtonI);
		Shop5.setHorizontalTextPosition(0);
		Shop6.setIcon(ButtonI);
		Shop6.setHorizontalTextPosition(0);
		Shop1.setOpaque(false);
		Shop1.setContentAreaFilled(false);
		Shop1.setBorderPainted(false);
		Shop2.setOpaque(false);
		Shop2.setContentAreaFilled(false);
		Shop2.setBorderPainted(false);
		Shop3.setOpaque(false);
		Shop3.setContentAreaFilled(false);
		Shop3.setBorderPainted(false);
		Shop4.setOpaque(false);
		Shop4.setContentAreaFilled(false);
		Shop4.setBorderPainted(false);
		Shop5.setOpaque(false);
		Shop5.setContentAreaFilled(false);
		Shop5.setBorderPainted(false);
		Shop6.setOpaque(false);
		Shop6.setContentAreaFilled(false);
		Shop6.setBorderPainted(false);
		// Done

		button.addActionListener(this);
		button2.addActionListener(this);

		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);

		Duckt.setOpaque(false);
		Duckt.setContentAreaFilled(false);
		Duckt.setBorderPainted(false);
		Duckt.setActionCommand("Duck");

		Duckt.addActionListener(this);
		Shop1.addActionListener(this);
		Shop2.addActionListener(this);
		Shop3.addActionListener(this);
		Shop4.addActionListener(this);
		Shop5.addActionListener(this);
		Shop6.addActionListener(this);
		Reset.addActionListener(this);

		add(Duckt);
		add(Shop1);
		add(Shop2);
		add(Shop3);
		add(Shop4);
		add(Shop5);
		add(Shop6);

		add(Reset);
		add(button2);
		add(button);
		add(label1);
		Reset.setBounds(390, 390, 305, 20);
		Duckt.setBounds(100, 152, 168, 144);
		Shop1.setBounds(390, 0, 305, 20);
		Shop2.setBounds(390, 25, 305, 20);
		Shop3.setBounds(390, 50, 305, 20);
		Shop4.setBounds(390, 75, 305, 20);
		Shop5.setBounds(390, 100, 305, 20);
		Shop6.setBounds(390, 125, 305, 20);
		button2.setBounds(0, 0, 390, 400);
		label1.setBounds(0, 400, 400, 10);

	}

}
