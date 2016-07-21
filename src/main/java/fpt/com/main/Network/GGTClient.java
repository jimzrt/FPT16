package fpt.com.main.Network;

import java.io.*;
import java.net.*;

public class GGTClient {

	public static void main(String[] args) {

		// Verbindungsanfrage/Verbindungsaufbau
		// Streams anlegen
		try (Socket serverCon = new Socket("localhost", 6666);
				InputStream in = serverCon.getInputStream();
				OutputStream out = serverCon.getOutputStream()) {
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Zahlenschreiben schreiben
			out.write(170);
			out.write(52);
			out.flush();

			// Ergebnis entgegennehmen
			int result = in.read();

			System.out.println(result);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}