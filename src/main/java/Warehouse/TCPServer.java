package Warehouse;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TCPServer implements Runnable {

	OrderLog orderLog = new OrderLog();


	@Override
	public void run() {
		System.out.println("Starte TCP Server - Port 6666");
		System.out.println("=========================");
		// ServerSocket erstellen
				try (ServerSocket server = new ServerSocket(6666);) {
					int connections = 0;
					// Timeout nach 1 Minute
					 server.setSoTimeout(600000);
					while (true) {
						try {
							Socket socket = server.accept();
							connections++;
						//	System.out.println("Neuer Client verbunden!");
						//	System.out.println("Insgesamt " + connections + " Clienten verbunden.");
							TCPServerClientThread workerThread = new TCPServerClientThread(connections, socket, orderLog); 
							Thread thread = new Thread(workerThread);
							thread.start();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}

			}

	
}