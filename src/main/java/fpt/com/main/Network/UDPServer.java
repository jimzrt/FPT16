package fpt.com.main.Network;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPServer {

	public static void main(String[] args) {
		
		System.out.println("Starte Server - Port 6667");
		System.out.println("=========================");
		// Socket erstellen unter dem der Server erreichbar ist
		try (DatagramSocket socket =  new DatagramSocket(6667);){
			while (true) {

				// Neues Paket anlegen
				DatagramPacket packet = new DatagramPacket(new byte[4], 4);
				// Auf Paket warten
				try {
					socket.receive(packet);
				} catch (IOException e) {
					e.printStackTrace();
				}

				// Daten auslesen
				InetAddress address = packet.getAddress();
				int port = packet.getPort();
				int len = packet.getLength();
				byte[] data = packet.getData();

				System.out.printf(
						"Anfrage von %s:%d :%n%s%n",
						address, port, new String(data, 0, len));

				// Nutzdaten in ein Stringobjekt übergeben

					String keyword = new String(packet.getData());
					if (keyword.startsWith("time")) {

						Date dt = new Date();
						
						SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy HH:mm:ss"); //Or whatever format fits best your needs.
						String dateStr = sdf.format(dt);
						
						
						byte[] myDate =  dateStr.getBytes();

						// Paket mit neuen Daten (Datum) als Antwort vorbereiten
						packet = new DatagramPacket(myDate, myDate.length,
								address, port);
						// Paket versenden
						socket.send(packet);

					} else {
						byte[] myDate = null;
						myDate = new String("Command unknown").getBytes();

						// Paket mit Information, dass das Schlüsselwort
						// ungültig
						// ist
						// als Antwort vorbereiten
						packet = new DatagramPacket(myDate, myDate.length,
								address, port);
						// Paket versenden
						socket.send(packet);
					}
				
			}

		} catch (IOException e) {
			e.printStackTrace();
		} 

	}
}