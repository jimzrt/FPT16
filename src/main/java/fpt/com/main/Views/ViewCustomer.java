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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fpt.com.main.ModelShop;
import fpt.com.main.Base.Order;
import fpt.com.main.Base.Product;
import fpt.com.main.Network.UDPClient;
import javafx.application.Platform;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.*;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;

public class ViewCustomer extends BorderPane {

	private TableView<Product> table = new TableView<Product>();
	TableColumn<Product, Number> buyCol;
	TableColumn<Product, String> nameCol;
	private Button button = new Button("Buy");
	private ListView<Order> list = new ListView<Order>();
	private Label timeLabel = new Label();
	
	private Map<Product, Integer> order = new HashMap<Product, Integer>();
	
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

		buyCol = new TableColumn<Product, Number>("Buy Count");
		buyCol.setMinWidth(80);
		// buyCol.setCellValueFactory(data ->new
		// SimpleStringProperty(data.getValue().quantityProperty().getValue().toString()));
		
		buyCol.setOnEditCommit(new EventHandler<CellEditEvent<Product, Number>>() {
			@Override
			public void handle(CellEditEvent<Product, Number> t) {
				order.put((Product) t.getTableView().getItems().get(t.getTablePosition().getRow()), t.getNewValue().intValue());
			}
		});
		
		buyCol.setCellValueFactory(data -> {
            if(order.containsKey(data.getValue()))
            	return new SimpleIntegerProperty(order.get(data.getValue()));
            return null;
        });

		buyCol.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
		
		

		
		
		 
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
		 */
		  table.getColumns().addAll(nameCol, priceCol, quantCol, buyCol);
		 
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table, button);
		VBox.setVgrow(table, Priority.ALWAYS);

		setRight(vbox);

		list.setPrefWidth(400);
		setCenter(list);

		timeLabel.setText("Serverzeit:");

		setBottom(timeLabel);

	

	}
	
	public void setTimeLabelText(String text){
		timeLabel.setText(text);
	}

	public void setList(ObservableList<Product> list) {
		table.setItems(list);
	}
	
	public Order getOrder(){
	//	System.out.println(table.getColumns().get(3).getCellData(1));
	//	return table.getSelectionModel().getSelectedIndex();
	//	return table.getSelectionModel().getSelectedItem();
		Order ord = new Order();
		for (Map.Entry<Product, Integer> entry : order.entrySet())
		{
			ord.add(new Product(entry.getKey().getName(),entry.getKey().getPrice(), entry.getValue()));
		//    System.out.println(entry.getKey() + "/" + entry.getValue());
		}
		ord.print();
	//	order.get(table.getSelectionModel().getSelectedItem());

		return null;
		//return table.getColumns().get(table.getSelectionModel().getSelectedIndex());
	}
	
	public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
		button.addEventHandler(ActionEvent.ACTION, eventHandler);

	}


}
