package fpt.com.main;

import fpt.com.main.Controller.ControllerCustomer;
import fpt.com.main.Controller.ControllerShop;
import fpt.com.main.Views.ViewCustomer;
import fpt.com.main.Views.ViewShop;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {

		Application.launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		ModelShop model = new ModelShop();

		ViewShop view = new ViewShop();
		ViewCustomer view2 = new ViewCustomer();

		ControllerShop control = new ControllerShop();
		ControllerCustomer control2 = new ControllerCustomer();

		control.link(model, view);
		control2.link(model, view2);

		// JavaFX new
		Scene scene = new Scene(view);
		primaryStage.setTitle("Shop");
		primaryStage.setScene(scene);
		primaryStage.setOnCloseRequest(e -> {
			Platform.exit();
		});

		// JavaFX new

		Stage stage2 = new Stage();
		stage2.setTitle("Customer");
		Scene scene2 = new Scene(view2);
		stage2.setScene(scene2);
		stage2.setX(primaryStage.getX() - stage2.getWidth());
		stage2.setY(primaryStage.getY() - stage2.getHeight());
		stage2.setOnCloseRequest(e -> {
			Platform.exit();
		});
		stage2.show();

		primaryStage.show();
	}

}
