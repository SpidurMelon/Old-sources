import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    
	private ImageIcon Open = new ImageIcon(getClass().getResource("Open.png"));
	private static String dub;
	private static long dubdub = 0;
	private static long DPS = 0;
	
	private static JLabel label1 = new JLabel("Ducks: " + dubdub);
	private static JLabel label2 = new JLabel("" + DPS);

	
	static String ePath = System.getProperty("user.home");
	
	private static String strFilePath = ePath.replace("\\", "/") + "\\Ints.txt";
	private static String CPSF = ePath.replace("\\", "/") + "\\CPS.txt";
	
	
	
	
	public void actionPerformed(ActionEvent e) {
		dub = e.getActionCommand();
		
			
		
		
		if (dub.equalsIgnoreCase("Reset")) {
			dubdub = 0;
			DPS = 0;
			FT();
		}
		
		
		if (dub.equalsIgnoreCase("Duck")) {
			dubdub += DPS/20 + 1;
			
			FT();
			
			
			PlaySound("Swek.wav");
			
			
			
			
			
			
			
		}
		
		if (dub.equalsIgnoreCase("Duckegg   DPS: 1   Cost: 50")) {
			if (dubdub >= 50) {
			DPS += 1;
			dubdub -= 50;
			FT();
			
			
			PlaySound("Buy.wav");
			
			}
		}
		
		
		if (dub.equalsIgnoreCase("Box of duckeggs   DPS: 5   Cost: 200")) {
			if (dubdub >= 200) {
				DPS += 5;
				dubdub -= 200;
				FT();
				PlaySound("Buy.wav");
			}
		}


		if (dub.equalsIgnoreCase("Mother duck   DPS: 20   Cost: 500")) {
			if (dubdub >= 500) {
				DPS += 20;
				dubdub -= 500;
				FT();
				PlaySound("Buy.wav");
			}
		}
		if (dub.equalsIgnoreCase("Duck trap   DPS: 50   Cost: 1000")) {
				if (dubdub >= 1000) {
				DPS += 50;
				dubdub -= 1000;
				FT();
				PlaySound("Buy.wav");
				}
			}
			
			
		if (dub.equalsIgnoreCase("Duck hunter   DPS: 200   Cost: 3000")) {
				if (dubdub >= 3000) {
					DPS += 200;
					dubdub -= 3000;
					FT();
					PlaySound("Buy.wav");
				}
			}


		if (dub.equalsIgnoreCase("Duck farm   DPS: 1000   Cost: 10000")) {
				if (dubdub >= 10000) {
					DPS += 1000;
					dubdub -= 10000;
					FT();
					PlaySound("Buy.wav");
				}
		}
		if (dub.equalsIgnoreCase("Duck cloner   DPS: 5000   Cost: 40000")) {
					if (dubdub >= 40000) {
					DPS += 5000;
					dubdub -= 40000;
					FT();
					PlaySound("Buy.wav");
					}
				}
				
				
		if (dub.equalsIgnoreCase("Duck volcano   DPS: 10000   Cost: 60000")) {
					if (dubdub >= 60000) {
						DPS += 10000;
						dubdub -= 60000;
						FT();
						PlaySound("Buy.wav");
					}
				}


		if (dub.equalsIgnoreCase("Rainbow duck   DPS: 100000   Cost: 500000")) {
					if (dubdub >= 500000) {
						DPS += 100000;
						dubdub -= 500000;
						FT();
						PlaySound("Buy.wav");
					}
				}
		if (dub.equalsIgnoreCase("Duck of destiny   DPS: 500000   Cost: 2000000")) {
						if (dubdub >= 2000000) {
						DPS += 500000;
						dubdub -= 2000000;
						FT();
						PlaySound("Buy.wav");
						}
					}
					
					
		if (dub.equalsIgnoreCase("???   DPS: 2000000   Cost: 5000000")) {
						if (dubdub >= 5000000) {
							DPS += 2000000;
							dubdub -= 5000000;
							FT();
							PlaySound("Buy.wav");
						}
					}


		
		}
			
		
		
		
		
		

		
		
		
		
		
		
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public static void main(String[] args) throws IOException {
		new App().setVisible(true);
		
		
		
		
		String line = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(strFilePath);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                dubdub = Long.valueOf(line);
                
				FTS();
            }	

            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();				
        }
        catch(IOException ex) {
            ex.printStackTrace();			
            
        }
    
		
		
		String line2 = null;
		try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(CPSF);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line2 = bufferedReader.readLine()) != null) {
                DPS = Long.valueOf(line2);
                FTS();
            }	

            // Always close files.
            bufferedReader.close();			
        }
        catch(FileNotFoundException ex) {
            ex.printStackTrace();				
        }
        catch(IOException ex) {
            ex.printStackTrace();			
            
        }
		
		
		
		
		
		
		
        
		
		
		
		
		
		
		
		
		boolean Always = true;
		 while(Always) {
			 try {
				    Thread.sleep(1000);
				} catch(InterruptedException ex) {
				    Thread.currentThread().interrupt();
				}
			 dubdub += DPS;
			 FTS();
			 
			 
			    
			 try {
		            // Assume default encoding.
		            FileWriter fileWriter =
		                new FileWriter(strFilePath);

		            // Always wrap FileWriter in BufferedWriter.
		            BufferedWriter bufferedWriter =
		                new BufferedWriter(fileWriter);

		            // Note that write() does not automatically
		            // append a newline character.
		            bufferedWriter.write("" + dubdub);

		            // Always close files.
		            bufferedWriter.close();
		            
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error writing to file '"
		                + strFilePath + "'");
		            // Or we could just do this:
		            // ex.printStackTrace();
		        }
				
				
				
				
				try {
		            // Assume default encoding.
		            FileWriter fileWriter2 =
		                new FileWriter(CPSF);

		            // Always wrap FileWriter in BufferedWriter.
		            BufferedWriter bufferedWriter2 =
		                new BufferedWriter(fileWriter2);

		            // Note that write() does not automatically
		            // append a newline character.
		            bufferedWriter2.write("" + DPS);

		            // Always close files.
		            bufferedWriter2.close();
		            
		        }
		        catch(IOException ex) {
		            System.out.println(
		                "Error writing to file '"
		                + strFilePath + "'");
		            // Or we could just do this:
		            // ex.printStackTrace();
		        }
		 }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public App() {
		super ("Duck Clicker");
		setSize(560, 800);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(null);
		
		
		
		
		
		JButton DPS = new JButton();
		createIcon(DPS, 1, 32, 70, 20, "DPS.png");
		JButton Ducks = new JButton();
		createIcon(Ducks, 0, 52, 102, 15, "Ducks.png");
		label1.setFont(new Font("Serif", Font.PLAIN, 20));
		label2.setFont(new Font("Serif", Font.PLAIN, 20));
		
		
		JButton Duckt = new JButton();
		JButton button = new JButton("Shop");
		JButton button2 = new JButton("");
		JButton Shop1 = new JButton("Duckegg   DPS: 1   Cost: 50");
		JButton Shop2 = new JButton("Box of duckeggs   DPS: 5   Cost: 200");
		JButton Shop3 = new JButton("Mother duck   DPS: 20   Cost: 500");
		JButton Shop4 = new JButton("Duck trap   DPS: 50   Cost: 1000");
		JButton Shop5 = new JButton("Duck hunter   DPS: 200   Cost: 3000");
		JButton Shop6 = new JButton("Duck farm   DPS: 1000   Cost: 10000");
		JButton Shop7 = new JButton("Duck cloner   DPS: 5000   Cost: 40000");
		JButton Shop8 = new JButton("Duck volcano   DPS: 10000   Cost: 60000");
		JButton Shop9 = new JButton("Rainbow duck   DPS: 100000   Cost: 500000");
		JButton Shop10 = new JButton("Duck of destiny   DPS: 500000   Cost: 2000000");
		JButton Shop11 = new JButton("???   DPS: 2000000   Cost: 5000000");
		JButton Shop12 = new JButton("???   DPS: 99999999999   Cost: 999999999999999999");
		
		JButton BShop1 = new JButton();
		JButton BShop2 = new JButton();
		JButton BShop3 = new JButton();
		JButton BShop4 = new JButton();
		JButton BShop5 = new JButton();
		JButton BShop6 = new JButton();
		JButton BShop7 = new JButton();
		JButton BShop8 = new JButton();
		JButton BShop9 = new JButton();
		JButton BShop10 = new JButton();
		JButton BShop11 = new JButton();
		JButton BShop12 = new JButton();
		
		JButton GifStopper = new JButton();
		JButton Reset = new JButton("Reset");
		createJButtonA(Reset, "", "Reset", 460, 0, 100, 46, "Reset2.png");
		ImageIcon DuckBG = new ImageIcon(getClass().getResource("gif.gif"));
		ImageIcon ButtonI = new ImageIcon(getClass().getResource("Button2.png"));
		ImageIcon Duck = new ImageIcon(getClass().getResource("Dicht.png"));
		
		ImageIcon Buy = new ImageIcon(getClass().getResource("Buy.png"));
		Duckt.setIcon(Duck);
		button2.setIcon(DuckBG);
		
		label1.setForeground(Color.YELLOW);
		label2.setForeground(Color.YELLOW);
		
		// Making the shop buttons Working Right
		Shop1.setActionCommand("");
		Shop2.setActionCommand("");
		Shop3.setActionCommand("");
		Shop4.setActionCommand("");
		Shop5.setActionCommand("");
		Shop6.setActionCommand("");
		Shop7.setActionCommand("");
		Shop8.setActionCommand("");
		Shop9.setActionCommand("");
		Shop10.setActionCommand("");
		Shop11.setActionCommand("");
		Shop12.setActionCommand("");
		
		BShop1.setActionCommand("Duckegg   DPS: 1   Cost: 50");
		BShop2.setActionCommand("Box of duckeggs   DPS: 5   Cost: 200");
		BShop3.setActionCommand("Mother duck   DPS: 20   Cost: 500");
		BShop4.setActionCommand("Duck trap   DPS: 50   Cost: 1000");
		BShop5.setActionCommand("Duck hunter   DPS: 200   Cost: 3000");
		BShop6.setActionCommand("Duck farm   DPS: 1000   Cost: 10000");
		BShop7.setActionCommand("Duck cloner   DPS: 5000   Cost: 40000");
		BShop8.setActionCommand("Duck volcano   DPS: 10000   Cost: 60000");
		BShop9.setActionCommand("Rainbow duck   DPS: 100000   Cost: 500000");
		BShop10.setActionCommand("Duck of destiny   DPS: 500000   Cost: 2000000");
		BShop11.setActionCommand("???   DPS: 2000000   Cost: 5000000");
		BShop12.setActionCommand("???   DPS: 99999999999   Cost: 999999999999999999");
		
		
		
		
		
		
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
		
		
		Shop7.setIcon(ButtonI);
		Shop7.setHorizontalTextPosition(0);
		Shop8.setIcon(ButtonI);
		Shop8.setHorizontalTextPosition(0);
		Shop9.setIcon(ButtonI);
		Shop9.setHorizontalTextPosition(0);
		Shop10.setIcon(ButtonI);
		Shop10.setHorizontalTextPosition(0);
		Shop11.setIcon(ButtonI);
		Shop11.setHorizontalTextPosition(0);
		Shop12.setIcon(ButtonI);
		Shop12.setHorizontalTextPosition(0);
		Shop7.setOpaque(false);
		Shop7.setContentAreaFilled(false);
		Shop7.setBorderPainted(false);
		Shop8.setOpaque(false);
		Shop8.setContentAreaFilled(false);
		Shop8.setBorderPainted(false);
		Shop9.setOpaque(false);
		Shop9.setContentAreaFilled(false);
		Shop9.setBorderPainted(false);
		Shop10.setOpaque(false);
		Shop10.setContentAreaFilled(false);
		Shop10.setBorderPainted(false);
		Shop11.setOpaque(false);
		Shop11.setContentAreaFilled(false);
		Shop11.setBorderPainted(false);
		Shop12.setOpaque(false);
		Shop12.setContentAreaFilled(false);
		Shop12.setBorderPainted(false);
		
		// Buy Buttons
		BShop1.setIcon(Buy);
		BShop1.setHorizontalTextPosition(0);
		BShop2.setIcon(Buy);
		BShop2.setHorizontalTextPosition(0);
		BShop3.setIcon(Buy);
		BShop3.setHorizontalTextPosition(0);
		BShop4.setIcon(Buy);
		BShop4.setHorizontalTextPosition(0);
		BShop5.setIcon(Buy);
		BShop5.setHorizontalTextPosition(0);
		BShop6.setIcon(Buy);
		BShop6.setHorizontalTextPosition(0);
		BShop1.setOpaque(false);
		BShop1.setContentAreaFilled(false);
		BShop1.setBorderPainted(false);
		BShop2.setOpaque(false);
		BShop2.setContentAreaFilled(false);
		BShop2.setBorderPainted(false);
		BShop3.setOpaque(false);
		BShop3.setContentAreaFilled(false);
		BShop3.setBorderPainted(false);
		BShop4.setOpaque(false);
		BShop4.setContentAreaFilled(false);
		BShop4.setBorderPainted(false);
		BShop5.setOpaque(false);
		BShop5.setContentAreaFilled(false);
		BShop5.setBorderPainted(false);
		BShop6.setOpaque(false);
		BShop6.setContentAreaFilled(false);
		BShop6.setBorderPainted(false);
		
		
		BShop7.setIcon(Buy);
		BShop7.setHorizontalTextPosition(0);
		BShop8.setIcon(Buy);
		BShop8.setHorizontalTextPosition(0);
		BShop9.setIcon(Buy);
		BShop9.setHorizontalTextPosition(0);
		BShop10.setIcon(Buy);
		BShop10.setHorizontalTextPosition(0);
		BShop11.setIcon(Buy);
		BShop11.setHorizontalTextPosition(0);
		BShop12.setIcon(Buy);
		BShop12.setHorizontalTextPosition(0);
		BShop7.setOpaque(false);
		BShop7.setContentAreaFilled(false);
		BShop7.setBorderPainted(false);
		BShop8.setOpaque(false);
		BShop8.setContentAreaFilled(false);
		BShop8.setBorderPainted(false);
		BShop9.setOpaque(false);
		BShop9.setContentAreaFilled(false);
		BShop9.setBorderPainted(false);
		BShop10.setOpaque(false);
		BShop10.setContentAreaFilled(false);
		BShop10.setBorderPainted(false);
		BShop11.setOpaque(false);
		BShop11.setContentAreaFilled(false);
		BShop11.setBorderPainted(false);
		BShop12.setOpaque(false);
		BShop12.setContentAreaFilled(false);
		BShop12.setBorderPainted(false);
		// Done
		
		button.addActionListener(this);
		button2.addActionListener(this);
		
		button2.setOpaque(false);
		button2.setContentAreaFilled(false);
		button2.setBorderPainted(false);
		
		GifStopper.setOpaque(false);
		GifStopper.setContentAreaFilled(false);
		GifStopper.setBorderPainted(false);
		
		Duckt.setOpaque(false);
		Duckt.setContentAreaFilled(false);
		Duckt.setBorderPainted(false);
		Duckt.setActionCommand("Duck");
		Duckt.setPressedIcon(Open);
		
		BShop1.addActionListener(this);
		BShop2.addActionListener(this);
		BShop3.addActionListener(this);
		BShop4.addActionListener(this);
		BShop5.addActionListener(this);
		BShop6.addActionListener(this);
		BShop7.addActionListener(this);
		BShop8.addActionListener(this);
		BShop9.addActionListener(this);
		BShop10.addActionListener(this);
		BShop11.addActionListener(this);
		BShop12.addActionListener(this);
		Duckt.addActionListener(this);
		
		
		Reset.addActionListener(this);
		
		
		add(BShop1);
		add(BShop2);
		add(BShop3);
		add(BShop4);
		add(BShop5);
		add(BShop6);
		add(BShop7);
		add(BShop8);
		add(BShop9);
		add(BShop10);
		add(BShop11);
		
		add(Duckt);
		add(Shop1);
		add(Shop2);
		add(Shop3);
		add(Shop4);
		add(Shop5);
		add(Shop6);
		add(Shop7);
		add(Shop8);
		add(Shop9);
		add(Shop10);
		add(Shop11);
		
		
		
		
		
		
		
		
		
		
		
		add(GifStopper);
		
		
		add(button2);
		add(button);
		add(label1);
		add(label2);
		
		Duckt.setBounds(180, 150, 168, 144);
		BShop1.setBounds(0, 344, 38, 20);
		BShop2.setBounds(0, 364, 38, 20);
		BShop3.setBounds(0, 384, 38, 20);
		BShop4.setBounds(0, 404, 38, 20);
		BShop5.setBounds(0, 424, 38, 20);
		BShop6.setBounds(0, 444, 38, 20);
		BShop7.setBounds(0, 464, 38, 20);
		BShop8.setBounds(0, 484, 38, 20);
		BShop9.setBounds(0, 504, 38, 20);
		BShop10.setBounds(0, 524, 38, 20);
		BShop11.setBounds(0, 544, 38, 20);
		BShop12.setBounds(0, 564, 38, 20);
		Shop1.setBounds(20, 344, 350, 20);
		Shop2.setBounds(20, 364, 350, 20);
		Shop3.setBounds(20, 384, 350, 20);
		Shop4.setBounds(20, 404, 350, 20);
		Shop5.setBounds(20, 424, 350, 20);
		Shop6.setBounds(20, 444, 350, 20);
		Shop7.setBounds(20, 464, 350, 20);
		Shop8.setBounds(20, 484, 350, 20);
		Shop9.setBounds(20, 504, 350, 20);
		Shop10.setBounds(20, 524, 350, 20);
		Shop11.setBounds(20, 544, 350, 20);
		Shop12.setBounds(20, 564, 350, 20);
		
		GifStopper.setBounds(0, 72, 560, 272);
		button2.setBounds(0, 72, 560, 272);
		label1.setBounds(0, 37, 700, 40);
		label2.setBounds(0, 18, 700, 40);
		
		getContentPane().setBackground(Color.DARK_GRAY);
		
	}
	
	
	
	/*
	 * CREATING VOIDS
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	public void createJButton(JButton J, String Name, int x, int y, int width, int height) {
		J = new JButton(Name);
		J.setBounds(x, y, width, height);
		J.addActionListener(this);
		add(J);
		
	}
	
	public void createJButtonA(JButton J, String Name, String Action, int x, int y, int width, int height, String Path) {
		J = new JButton(Name);
		J.setBounds(x, y, width, height);
		J.setActionCommand(Action);
		ImageIcon Thing = new ImageIcon(getClass().getResource(Path));
		
		J.addActionListener(this);
		J.setIcon(Thing);
		J.setHorizontalTextPosition(0);
		J.setOpaque(false);
		J.setContentAreaFilled(false);
		J.setBorderPainted(false);
		add(J);
	}
	
	public void createIcon(JButton J, int x, int y, int width, int height, String Path) {
		J = new JButton();
		
		ImageIcon Thing = new ImageIcon(getClass().getResource(Path));
		
		J.setBounds(x, y, width, height);
		J.addActionListener(this);
		J.setIcon(Thing);
		J.setHorizontalTextPosition(0);
		J.setOpaque(false);
		J.setContentAreaFilled(false);
		J.setBorderPainted(false);
		add(J);
		
	}
	
	public void FT() {
		label1.setText("                      " + dubdub);
		label2.setText("                 " + DPS);
	}
	
	public static void FTS() {
		label1.setText("                      " + dubdub);
		label2.setText("                 " + DPS);
	}
	
	public void PlaySound(String wav) {
		try {
	         // Open an audio input stream.
	         java.net.URL url = this.getClass().getClassLoader().getResource(wav);
	         AudioInputStream audioIn = AudioSystem.getAudioInputStream(url);
	         // Get a sound clip resource.
	         Clip clip2 = AudioSystem.getClip();
	         // Open audio clip and load samples from the audio input stream.
	         clip2.open(audioIn);
	         clip2.start();
	      } catch (UnsupportedAudioFileException l) {
	         l.printStackTrace();
	      } catch (IOException a) {
	         a.printStackTrace();
	      } catch (LineUnavailableException p) {
	         p.printStackTrace();
	      }
	}
	
	/*
	 * 
	 * ENDING CREATING VOIDS
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	
}
	

