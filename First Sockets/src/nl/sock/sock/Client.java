package nl.sock.sock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		final Socket mySkt = new Socket("77.173.135.166", 7777);;
		
		
		
		
		
		Runnable r1 = new Runnable() {
			public void run() {
				
			try {
				while (true) {
			PrintStream myPS = new PrintStream(mySkt.getOutputStream());
			Scanner scan = new Scanner(System.in);
			myPS.println(scan.nextLine());
				}
			} catch (Exception e) {
				
			}
			
			}
			};
			
			Runnable r2 = new Runnable() {
				public void run() {
					
				try {
					while (true) {
			BufferedReader myBR = new BufferedReader(new InputStreamReader(mySkt.getInputStream()));
			String t = myBR.readLine();
			System.out.println(t);
					}
				} catch (Exception e) {
					
				}
				
				}
				};
		
		Thread thr1 = new Thread(r1);
		Thread thr2 = new Thread(r2);
		thr1.start();
		thr2.start();
		
		
		
		
	}
	
	
	
	
	
}
