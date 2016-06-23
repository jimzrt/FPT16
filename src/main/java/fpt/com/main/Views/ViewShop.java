package fpt.com.main.Views;

import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.SwingUtilities;

import fpt.com.main.Base.Product;
import javafx.application.Platform;
import javafx.collections.FXCollections;

import javafx.scene.control.TextArea;
import javafx.scene.control.*;

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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ViewShop extends BorderPane {
	
	Timer timer = new Timer();


	private Button button = new Button("Add");
	private Button button2 = new Button("Delete");
	private TextField name = new TextField();
	private TextField price = new TextField();
	private TextField count = new TextField();
	private Label nameL = new Label("Name:");
	private Label priceL = new Label("Price:");
	private Label countL = new Label("Count:");
	private Label errorText = new Label("");

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
		button.setLayoutX(18);
		button.setLayoutY(150);
		button2.setLayoutX(125);
		button2.setLayoutY(150);
		
		//Error Text
		errorText.setLayoutX(18);
		errorText.setLayoutY(200);
		errorText.setTextFill(Color.web("#bf4f51"));
		//errorText.setFont(Font.font("Cambria", 16));


		box.getChildren().addAll(name, price, count, nameL, priceL, countL, button, button2, errorText);

		setRight(box);

		setCenter(list);



		// Console
/*		
 * 
 		TextArea ta = new TextArea();
		ta.prefHeight(50);
		ta.setWrapText(true);Console console = new Console(ta);
		PrintStream ps = new PrintStream(console, true);
		System.setOut(ps);
		System.setErr(ps);

		setBottom(ta);
*/
		strategy.setItems(FXCollections.observableArrayList("Binary", "Beans XML", "XStream XML", "JDBC Database","OpenJPA Database"));
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

	public void update() {
		list.fireEvent(null);
	}
	
	public void setErrorText(String error){
		errorText.setText(error);
		
		timer.cancel();
		timer = new Timer();
	    timer.schedule(new TimerTask() {
	        @Override
	        public void run() {
	            Platform.runLater(new Runnable() {
	                @Override
	                public void run() {
	                	errorText.setText("");
	                }
	            });
	        }
	    }, 3000);
	}

}
