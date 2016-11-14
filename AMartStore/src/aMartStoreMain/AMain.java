package aMartStoreMain;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class AMain extends Application {

	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = new BorderPane();
			AnchorPane page = (AnchorPane) FXMLLoader.load(AMain.class.getResource("View/LoginView.fxml"));
			Scene scenelogin = new Scene(page);
		Scene scene = new Scene(root,400,400);
			primaryStage.setScene(scene);
			primaryStage.show();
            primaryStage.setScene(scenelogin);
           primaryStage.setTitle("AMartStore");
            primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
			//Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
