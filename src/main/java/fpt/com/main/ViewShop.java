package fpt.com.main;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

public class ViewShop extends BorderPane {

	// private SimpleStringProperty buttonText = new
	// SimpleStringProperty("Enter");
	private Button button = new Button("Add");
	private Button button2 = new Button("Delete");
	private TextField name = new TextField();
	private TextField price = new TextField();
	private TextField count = new TextField();
	private Label nameL = new Label("Name:");
	private Label priceL = new Label("Price:");
	private Label countL = new Label("Count:");
	private ChoiceBox<String> strategy = new ChoiceBox<String>();
	private Button buttonL = new Button("Load...");
	private Button buttonS = new Button("Save");

	private ListView<Product> list = new ListView<Product>();

	public ViewShop() {

		Pane box = new Pane();
		box.setPrefSize(200, 400);

		// Labels
		nameL.setLayoutX(18);
		nameL.setLayoutY(15);
		priceL.setLayoutX(18);
		priceL.setLayoutY(55);
		countL.setLayoutX(18);
		countL.setLayoutY(95);

		// Textfields
		name.setLayoutX(18);
		name.setLayoutY(30);
		price.setLayoutX(18);
		price.setLayoutY(70);
		count.setLayoutX(18);
		count.setLayoutY(110);

		// Buttons
		button.setLayoutX(14);
		button.setLayoutY(150);
		button2.setLayoutX(125);
		button2.setLayoutY(150);

		box.getChildren().addAll(name, price, count, nameL, priceL, countL,
				button, button2);

		setRight(box);

		setCenter(list);

		strategy.setItems(FXCollections.observableArrayList("Binary",
				"Beans XML", "XStream XML", "JDBC Database", "OpenJPA Database"));
		strategy.getSelectionModel().select(0);
		HBox hbox = new HBox();
		hbox.setSpacing(5);
		hbox.setPadding(new Insets(10, 10, 10, 10));
		hbox.getChildren().addAll(strategy, buttonL, buttonS);
		setTop(hbox);

		// rerender ListCell
		list.setCellFactory(c -> {

			ListCell<Product> cell = new ListCell<Product>() {
				@Override
				protected void updateItem(Product myObject, boolean b) {
					super.updateItem(myObject, myObject == null || b);
					if (myObject != null) {
						setText(myObject.toString());
					} else {
						setText("");
					}
				}

			};
			return cell;

		});

	}

	public void addEventHandler(EventHandler<ActionEvent> eventHandler) {
		button.addEventHandler(ActionEvent.ACTION, eventHandler);
		button2.addEventHandler(ActionEvent.ACTION, eventHandler);
		buttonL.addEventHandler(ActionEvent.ACTION, eventHandler);
		buttonS.addEventHandler(ActionEvent.ACTION, eventHandler);
		strategy.addEventHandler(ActionEvent.ACTION, eventHandler);
	}

	public ListView<Product> getList() {
		return list;
	}

	public void setList(ObservableList<Product> list) {
		this.list.setItems(list);
	}

	public String getNameInput() {
		return name.getText();
	}

	public String getPriceInput() {
		return price.getText();
	}

	public String getCountInput() {
		return count.getText();
	}

	public int getSelectedIndex() {
		return list.getSelectionModel().getSelectedIndex();
	}

	public int getChoice() {
		return strategy.getSelectionModel().getSelectedIndex();
	}
	
	public void update(){
		list.fireEvent(null);
	}

}
