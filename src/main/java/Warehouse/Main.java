package Warehouse;

import fpt.com.main.Network.UDPClient;

public class Main {
	
	public static void main (String[] args){
		UDPServer udpServer = new UDPServer();
		Thread thUDP = new Thread(udpServer);
		//th.setDaemon(true);
		thUDP.start();
		
		
		TCPServer tcpServer = new TCPServer();
		Thread thTCP = new Thread(tcpServer);
		//th.setDaemon(true);
		thTCP.start();
		
	}

}
