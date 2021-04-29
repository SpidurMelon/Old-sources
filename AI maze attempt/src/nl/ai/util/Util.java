package nl.ai.util;

import nl.ai.main.Initialize;
import nl.ai.panels.Panel;

public class Util {
	public static void wait(int milliSeconds) {
		try{
			Thread.sleep(milliSeconds);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Panel currentScreen() {
		return Initialize.frame.panel;
	}
	
	public static void repaint() {
		Initialize.frame.validate();
		Initialize.frame.repaint();
	}
	
	public static int randomNumber(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
	
	public static boolean hasEnemy(int x, int y) {
		boolean result = false;
		if(Panel.nem.x == x && Panel.nem.y == y) {
			result = true;
		}
		return result;
	}

	private static int randY;
	private static int randX;
	
	public static void newNetwork() {
		boolean running = true;
		
		do {
			randX = Util.randomNumber(0, Panel.neurons.neurons[0].length-3);
			randY = Util.randomNumber(0, Panel.neurons.neurons.length-3);
		
			if (Panel.neurons.neurons[randX+1][randY+1] == 0) {
				Panel.neurons.neurons[randX+1][randY+1] = Util.randomNumber(1, 8);
				running = false;
			}
		} while(running);
	}
	public static void resetNetwork() {
		for (int x = 0; x < Panel.neurons.neurons.length; x++) {
			for (int y = 0; y < Panel.neurons.neurons[x].length; y++) {
				Panel.neurons.neurons[y][x] = 0;
			}
		}
	}
	public static void print2DArray(int[][] array) {
		System.out.print("[");
		for (int x = 0; x < array.length; x++) {
			if (x != 0) {
				System.out.println();
			} else {
				System.out.print(array[0][0] + ",");
			}
			for (int y = 0; y < array[x].length; y++) {
				if (x != array[array.length-1].length-1 || y != array.length-1) {
					if (x != 0 || y != 0) {
						System.out.print(" " + array[x][y] + ",");
					}
				}
			}
			
		}
		System.out.print(" " + array[array.length-1][array[array.length-1].length-1]);
		System.out.println("]");
	}
	public static void printArray(int[] array) {
		System.out.print("[");
		for (int x = 0; x < array.length; x++) {
			if (x == 0) {
				System.out.print(array[0] + ",");
			}
			if (x != array.length-1) {
				if (x != 0) {
					System.out.print(" " + array[x] + ",");
				}
			}
		}
			
		
		System.out.print(" " + array[array.length-1]);
		System.out.println("]");
	}
	public static void add(int number, int[] array) {
		for (int x = array.length-1; x > 0; x--) {
				array[x] = array[x-1];
		}
		array[0] = number;
	}
}
