package fpt.com.main.Controller;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.net.SocketTimeoutException;

import fpt.com.main.ModelShop;
import fpt.com.main.Base.Product;
import fpt.com.main.Network.Client;
import fpt.com.main.Views.ViewCustomer;
import javafx.application.Platform;

public class ControllerCustomer {
	
	ViewCustomer view;

	public void link(ModelShop model, ViewCustomer view) {
		
		this.view = view;
		view.setList(model);

		view.addEventHandler(e -> {

			int selectedIndex = e.getTableView().getSelectionModel()
					.getSelectedIndex();
			Product p = e.getRowValue();
			p.setName(e.getNewValue());
			model.set(selectedIndex, p);

		});
		
		Client sendTask = new Client() {
			@Override
			public void run() {

				while (true) {

					// Socket für den Klienten anlegen
					try (DatagramSocket dSocket = new DatagramSocket(55555);) {

						try {
							while (true) {

								dSocket.setSoTimeout(5000);
								dSocket.send(packetReq);

								try {
									dSocket.receive(packetResp);

									text = new String(packetResp.getData(), 0, packetResp.getLength());
									lastTime = " - Letzte Verbindung: " + text;
								} catch (SocketTimeoutException e) {
									text = "Kein Server erreichbar" + lastTime;
								}

							//	Platform.runLater(() -> view.setTimeLabelText(text));
								try {
									Thread.sleep(1000);
								} catch (InterruptedException e) {
									e.printStackTrace();
								}

							}
						} catch (IOException e1) {
							e1.printStackTrace();
						}

					} catch (SocketException e1) {
						e1.printStackTrace();
						try {
							Thread.sleep(10000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}					}

				}

			}

		};

	//	Thread th = new Thread(sendTask);
	//	th.setDaemon(true);
		//th.start();

	}
	
	

}
