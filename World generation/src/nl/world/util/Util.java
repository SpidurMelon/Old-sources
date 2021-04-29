package nl.world.util;

public class Util {
	public static void sleep(int milliSeconds) {
		try {
			Thread.sleep(milliSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public static void print2DArray(int[][] array) {
		System.out.print("[");
		for (int y = 0; y < array.length; y++) {
			if (y != 0) {
				System.out.println();
			} else {
				System.out.print(array[0][0] + ",");
			}
			for (int x = 0; x < array[y].length; x++) {
				if (x != array.length-1 || y != array[array.length-1].length-1) {
					if (x != 0 || y != 0) {
						System.out.print(" " + array[x][y] + ",");
					}
				}
			}
			
		}
		System.out.print(" " + array[array.length-1][array[array.length-1].length-1]);
		System.out.println("]");
	}
	
	public static int randomNumber(int min, int max) {
		return (int) (Math.floor(Math.random() * (max - min + 1)) + min);
	}
}
