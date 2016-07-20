package fpt.com.main.Views;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import fpt.com.main.Base.Order;
import fpt.com.main.Base.Product;
import fpt.com.main.Network.Client;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;

public class ViewCustomer extends BorderPane {

	private TableView<Product> table = new TableView<Product>();
	TableColumn<Product, Integer> buyCol;
	TableColumn<Product, String> nameCol;
	private Button button = new Button("Buy");
	private ListView<Order> list = new ListView<Order>();

	public ViewCustomer() {
		table.setEditable(true);

		nameCol = new TableColumn<Product, String>("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
			@Override
			public void handle(CellEditEvent<Product, String> t) {
				((Product) t.getTableView().getItems().get(t.getTablePosition().getRow())).setName(t.getNewValue());
			}
		});

		TableColumn<Product, Number> priceCol = new TableColumn<Product, Number>("Price");
		priceCol.setMinWidth(80);
		priceCol.setCellValueFactory(data -> data.getValue().priceProperty());

		TableColumn<Product, Number> quantCol = new TableColumn<Product, Number>("Quantity");
		quantCol.setMinWidth(80);
		quantCol.setCellValueFactory(data -> data.getValue().quantityProperty());

		buyCol = new TableColumn<Product, Integer>("Buy Count");
		buyCol.setMinWidth(80);
		// buyCol.setCellValueFactory(data ->new
		// SimpleStringProperty(data.getValue().quantityProperty().getValue().toString()));

		buyCol.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
		/*
		 * buyCol.setCellValueFactory(new
		 * javafx.util.Callback<CellDataFeatures<Product, Integer>,
		 * ObservableValue<Integer>>() {
		 * 
		 * @Override public ObservableValue<Integer>
		 * call(CellDataFeatures<Product, Integer> c) {
		 * 
		 * return new SimpleIntegerProperty(0).asObject(); } });
		 */
		/*
		 * buyCol.setOnEditCommit( new EventHandler<CellEditEvent<Product,
		 * Integer>>() {
		 * 
		 * @Override public void handle(CellEditEvent<Product, Integer> t) {
		 * 
		 * 
		 * ((Product) t.getTableView().getItems().get(
		 * t.getTablePosition().getRow()) ).setQuantity(((Product)
		 * t.getTableView().getItems().get(
		 * t.getTablePosition().getRow())).getQuantity() - t.getNewValue()); } }
		 * );
		 * 
		 * 
		 * table.getColumns().addAll(nameCol, priceCol, quantCol, buyCol);
		 */
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table, button);
		VBox.setVgrow(table, Priority.ALWAYS);

		setRight(vbox);

		list.setPrefWidth(400);
		setCenter(list);

		Label time = new Label();
		time.setText("Serverzeit:");

		setBottom(time);

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

								Platform.runLater(() -> time.setText(text));
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
					}

				}

			}

		};

		Thread th = new Thread(sendTask);
		th.setDaemon(true);
		th.start();

	}

	public void setList(ObservableList<Product> list) {
		table.setItems(list);
	}

	public void addEventHandler(EventHandler<CellEditEvent<Product, String>> eventHandler) {

		nameCol.setOnEditCommit(eventHandler);

	}

	// public void update(ModelShop model){
	// table.setItems(model);
	// }
}
