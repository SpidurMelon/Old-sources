package nl.sock.sock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
	public static void main(String[] args) throws IOException {
		final ServerSocket SS = new ServerSocket(7777);
		final Socket SS_accept = SS.accept();;
		
		Runnable r1 = new Runnable() {
			public void run() {
				
				
					try {
						while (true) {
					BufferedReader SS_BR = new BufferedReader(new InputStreamReader(SS_accept.getInputStream()));
					String t = SS_BR.readLine();
					System.out.println(t);
						}
					} catch(Exception e) {
						
					}
					
					
				
				
			}
			
		};
		
		
		Runnable r2 = new Runnable() {
			
			public void run() {
			
		
		

		
		
		try {
			while (true) {
		PrintStream SSPS = new PrintStream(SS_accept.getOutputStream());
		Scanner scan = new Scanner(System.in);
		SSPS.println(scan.nextLine());
			}
		} catch(Exception e) {
			
		}
		
		}
		};
		
		
		
		
		Thread thr1 = new Thread(r1);
		Thread thr2 = new Thread(r2);
		
		thr1.start();
		thr2.start();
		
		
		
		
		
		
		
		
	}

}
