package aMartStoreMain.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import aMartStoreMain.Main;
import aMartStoreMain.Model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class AdminController extends LoginController implements Initializable {
	@FXML private javafx.scene.control.Button closeButton1;
	
	//This is the label element in the view for entering the Username
	@FXML
	public Label lblAdminUserName;
	//This is the label element in the view for entering the user ID
	@FXML
	public Label lblAdminUserId;
	@FXML private javafx.scene.control.Button closeButton;
	public void initialize(URL location, ResourceBundle resources) {
		User us = new User();
        lblAdminUserName.setText((String)us.getName1());
        String str = String.valueOf(us.getId1());
        lblAdminUserId.setText(str);
	}
	//This is the Button element in the view for logging out the user
	@FXML protected void processLogout() {
		 
		try {
		
			Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/LoginView.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root, 700, 550);
			scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
			primaryStage.setScene(scene);
		  primaryStage.setTitle("Main View");
			primaryStage.show();
			Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
	//This is the Button element in the view for routing to the customer details page
	public void btnCustDetails(ActionEvent event) throws Exception
	{
		try{
		
			Stage CustDetStage = new Stage();
			BorderPane root = new BorderPane();
			AnchorPane CustDetPage = (AnchorPane) FXMLLoader.load(Main.class.getResource("View/CustomerDetails.fxml"));
			Scene CustDetScene = new Scene(CustDetPage);
			//Scene scene = new Scene(root,400,400);
			Scene scene = new Scene(root, 700, 550);
			scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
			CustDetStage.setScene(scene);
			CustDetStage.setTitle("Customers");
			CustDetStage.show();
			CustDetStage.setScene(CustDetScene);
	        Stage AdminStage = (Stage) closeButton1.getScene().getWindow();
	        // do what you have to do
	        AdminStage.close();
	        CustDetStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	//This is the Button element in the view for routing to the products details page

	@FXML private javafx.scene.control.Button closeButton2;
		public void btnProdDetails(ActionEvent event) throws Exception
		{
			try{
			
				Stage ProdDetStage = new Stage();
				BorderPane root = new BorderPane();
				AnchorPane ProdDetPage = (AnchorPane) FXMLLoader.load(Main.class.getResource("View/ProductDetails.fxml"));
				Scene ProdDetScene = new Scene(ProdDetPage);
				Scene scene = new Scene(root, 700, 550);
				scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
				ProdDetStage.setScene(scene);
				ProdDetStage.setTitle("Products");
				ProdDetStage.show();
				ProdDetStage.setScene(ProdDetScene);
		        Stage AdminStage = (Stage) closeButton2.getScene().getWindow();
		        // do what you have to do
		        AdminStage.close();
		        ProdDetStage.show();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//This is the Button element in the view for routing to the Supplier details page
			@FXML private javafx.scene.control.Button closeButton3;
			public void btnSuppDetails(ActionEvent event) throws Exception
			{
				try{
				
					Stage SuppDetStage = new Stage();
					BorderPane root = new BorderPane();
					AnchorPane SuppDetPage = (AnchorPane) FXMLLoader.load(Main.class.getResource("View/SupplierDetails.fxml"));
					Scene SuppDetScene = new Scene(SuppDetPage);
					//Scene scene = new Scene(root,400,400);
					Scene scene = new Scene(root, 700, 550);
				scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
					SuppDetStage.setScene(scene);
					SuppDetStage.setTitle("Suppliers");
					SuppDetStage.show();
					SuppDetStage.setScene(SuppDetScene);
			        Stage AdminStage = (Stage) closeButton2.getScene().getWindow();
			        // do what you have to do
			        AdminStage.close();
			        SuppDetStage.show();
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			@FXML private javafx.scene.control.Button closeButton4;
			
			//This is the Button element in the view for routing to the Billing Page
				public void btnCustBilling(ActionEvent event) throws Exception
				{
					try{
					
						Stage	BillDetStage = new Stage();
						BorderPane root = new BorderPane();
						AnchorPane BillDetPage = (AnchorPane) FXMLLoader.load(Main.class.getResource("View/BillingView.fxml"));
						Scene SuppDetScene = new Scene(BillDetPage);
						//Scene scene = new Scene(root,400,400);
						Scene scene = new Scene(root, 700, 550);
					scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
						BillDetStage.setScene(scene);
						BillDetStage.setTitle("Customer Billing");
						BillDetStage.show();
						BillDetStage.setScene(SuppDetScene);
				        Stage AdminStage = (Stage) closeButton4.getScene().getWindow();
				        // do what you have to do
				        AdminStage.close();
				        BillDetStage.show();
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
	}

}



