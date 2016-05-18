package fpt.com.main;

import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
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

	ViewCustomer() {
		table.setEditable(true);

		nameCol = new TableColumn<Product, String>("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(data -> data.getValue().nameProperty());

		nameCol.setCellFactory(TextFieldTableCell.forTableColumn());
		nameCol.setOnEditCommit(new EventHandler<CellEditEvent<Product, String>>() {
			@Override
			public void handle(CellEditEvent<Product, String> t) {
				((Product) t.getTableView().getItems()
						.get(t.getTablePosition().getRow())).setName(t
						.getNewValue());
			}
		});

		TableColumn<Product, Number> priceCol = new TableColumn<Product, Number>(
				"Price");
		priceCol.setMinWidth(80);
		priceCol.setCellValueFactory(data -> data.getValue().priceProperty());

		TableColumn<Product, Number> quantCol = new TableColumn<Product, Number>(
				"Quantity");
		quantCol.setMinWidth(80);
		quantCol.setCellValueFactory(data -> data.getValue().quantityProperty());

		buyCol = new TableColumn<Product, Integer>("Buy Count");
		buyCol.setMinWidth(80);
		// buyCol.setCellValueFactory(data ->new
		// SimpleStringProperty(data.getValue().quantityProperty().getValue().toString()));

		buyCol.setCellFactory(TextFieldTableCell
				.forTableColumn(new IntegerStringConverter()));
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
		

		table.getColumns().addAll(nameCol, priceCol, quantCol, buyCol);
 */
		VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 10, 10, 10));
		vbox.getChildren().addAll(table, button);
		VBox.setVgrow(table, Priority.ALWAYS);

		setRight(vbox);

		list.setPrefWidth(400);
		setCenter(list);

	}

	public void setList(ObservableList<Product> list) {
		table.setItems(list);
	}

	public void addEventHandler(
			EventHandler<CellEditEvent<Product, String>> eventHandler) {

		nameCol.setOnEditCommit(eventHandler);

	}

	// public void update(ModelShop model){
	// table.setItems(model);
	// }
}
