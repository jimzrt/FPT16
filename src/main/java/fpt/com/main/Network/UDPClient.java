package fpt.com.main.Network;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class UDPClient implements Runnable {

	public String text = "";
	public DatagramPacket packetReq;
	public DatagramPacket packetResp;
	public String lastTime = "";

	public UDPClient() { 
		InetAddress ia = null;
		try {
			ia = InetAddress.getByName("localhost");
		} catch (UnknownHostException e2) {
			e2.printStackTrace();
		}
		String command = "time";
		byte buffer[] = null;
		buffer = command.getBytes();

		// Paket mit der Anfrage vorbereiten
		packetReq = new DatagramPacket(buffer, buffer.length, ia, 6667);

		byte answer[] = new byte[1024];
		packetResp = new DatagramPacket(answer, answer.length);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}
}
