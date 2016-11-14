package aMartStoreMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	
		public void start(Stage primaryStage) {
		try{
			// Loads the Login Page
		BorderPane root = new BorderPane();
		AnchorPane page = (AnchorPane) FXMLLoader.load(Main.class.getResource("View/LoginView.fxml"));
		Scene scenelogin = new Scene(page);
		Scene scene = new Scene(root, 700, 550);
		// applys the style sheets
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// displays the screen and applies the scene
		primaryStage.setScene(scene);
		primaryStage.show();
        primaryStage.setScene(scenelogin);
        // sets the title
       primaryStage.setTitle("AMartStore");
        primaryStage.show();
	} catch(Exception e) {
		e.printStackTrace();
	}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
