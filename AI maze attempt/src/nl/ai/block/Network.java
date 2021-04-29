package nl.ai.block;

import java.awt.Color;
import java.awt.Graphics;

import nl.ai.interfaces.Standard;
import nl.ai.panels.Panel;

public class Network extends Standard {
	public int[][] neurons = {{0, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0},
							  {0, 0, 0, 0, 0}};

	public static int neuronSize = 30;
	private int blockSize = Block.blockSize;
	
	private static int x = 10;
	private static int y = 0;
	
	public void tick() {
		
	}

	public void draw(Graphics g) {
		
		for(int x = 0; x < neurons.length; x++) {
			for (int y = 0; y < neurons[x].length; y++) {
				g.setColor(Color.black);
				if (neurons[y][x] > 8) {
					g.setColor(Color.orange);
					g.fillRect(x*neuronSize + Network.x*blockSize, y*neuronSize + Network.y*blockSize, neuronSize, neuronSize);
					g.setColor(Panel.stringColor);
					switch(neurons[y][x]) {
						case(9):
							g.drawString(String.valueOf("U"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(10):
							g.drawString(String.valueOf("R"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(11):
							g.drawString(String.valueOf("D"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(12):
							g.drawString(String.valueOf("L"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
					}
						
				} else if (neurons[y][x] > 4) {
					g.setColor(Color.green);
					g.fillRect(x*neuronSize + Network.x*blockSize, y*neuronSize + Network.y*blockSize, neuronSize, neuronSize);
					g.setColor(Panel.stringColor);
					switch(neurons[y][x]) {
						case(5):
							g.drawString(String.valueOf("U"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(6):
							g.drawString(String.valueOf("R"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(7):
							g.drawString(String.valueOf("D"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
						case(8):
							g.drawString(String.valueOf("L"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
						break;
					}
						
				} else {
					
					g.drawRect(x*neuronSize + Network.x*blockSize, y*neuronSize + Network.y*blockSize, neuronSize, neuronSize);
					if (neurons[y][x] != 0) {
						g.setColor(Panel.stringColor);
						switch(neurons[y][x]) {
							case(1):
								g.drawString(String.valueOf("U"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
							break;
							case(2):
								g.drawString(String.valueOf("R"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
							break;
							case(3):
								g.drawString(String.valueOf("D"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
							break;
							case(4):
								g.drawString(String.valueOf("L"), x*neuronSize + Network.x*blockSize + neuronSize/3, y*neuronSize + Network.y*blockSize + neuronSize/2 + neuronSize/5);
							break;
						}
						
					}
					
				}
			}
		}
		
	}
	
}
