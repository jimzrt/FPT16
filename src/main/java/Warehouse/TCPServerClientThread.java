package Warehouse;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import fpt.com.main.Base.Product;

public class TCPServerClientThread implements Runnable{
	private int name;
	private Socket socket;
	private OrderLog orderLog;

	public TCPServerClientThread(int name, Socket socket, OrderLog orderLog) {
		this.name = name;
		this.socket = socket;
		this.orderLog = orderLog;
	}

	public void run() {
		String msg = "EchoServer: Verbindung " + name;
		System.out.println(msg + " hergestellt");
		try (InputStream in = socket.getInputStream();
				OutputStream out = socket.getOutputStream()) {
			try {
				Thread.sleep((long) (Math.random() * 10000));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			int a = in.read();
			int b = in.read();

			int result = ggT(a, b);
			
			Order order = new Order();
			Product product = new Product();
			product.setName("Lon John");
			product.setPrice(22);
			product.setQuantity(100);
			order.add(product);
			Product product2 = new Product();
			product2.setName("Lon John");
			product2.setPrice(212);
			product2.setQuantity(12);
			order.add(product2);
			
			orderLog.addOrder(order);

			

			// Ergebnis auf Outputstream schreiben
			out.write(result);
			out.flush();

			System.out.println("Verbindung " + name + " wird beendet");

			socket.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static int ggT(int a, int b) {

		if (b == 0) {
			return a;
		} else {
			return ggT(b, a % b);
		}

	}
}
