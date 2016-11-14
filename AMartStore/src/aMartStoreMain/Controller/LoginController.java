package aMartStoreMain.Controller;


import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import aMartStoreMain.Main;
import aMartStoreMain.Model.Cust;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.User;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

public class LoginController {

	@FXML
	private Label lblStatus;
	//This is the text element in the view for user name
	@FXML
	private TextField txtUserName;
	//This is the text element in the view for password
	@FXML
	private PasswordField txtPassword;
	@FXML
	public Label lblUserName;
	
	@FXML private javafx.scene.control.Button closeButton;
	private java.sql.Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";
	@FXML 
	public Button btnLogin;
	// login button for entering in to the system
	public void btnLogin(ActionEvent event) throws Exception
	{
		
		try{
			int cnt = 0;
			int cnt1 = 0;
			String  uname = txtUserName.getText();
			String password1 = txtPassword.getText();
			
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			
			java.sql.PreparedStatement pst=connection.prepareStatement("Select username,isAdmin,User_ID from amachira_users where username = ? and password = ? ");
			
			pst.setString(1, uname);
			pst.setString(2, password1);

			ResultSet rst=pst.executeQuery();
			if(rst.next()){
			       String type=rst.getString("isAdmin");
			       String type1=rst.getString("Username");
			       String type2=rst.getString("User_ID");
			       User us = new User();
					us.setName1(type1);
					us.setId1(Integer.parseInt(type2));
					Cust c = new Cust();
					c.setName2(type1);
					c.setId2(Integer.parseInt(type2));
					
			       try {
			            connection = DriverManager.getConnection(url, username, password);
			        } catch(SQLException e) {
			            System.out.println("Error creating connection to database: " + e);
			            System.exit(-1);
			        }

			       if("No".equals(type)){
			           //redirect to customer page
		    	   
			    	   Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/CustomerView.fxml"));
						Stage primaryStage = new Stage();
					//	Scene scene = new Scene(root,400,400);
						Scene scene = new Scene(root, 700, 550);
						scene.getStylesheets().add(getClass().getResource("application2.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setTitle("Hello " +type1);
						primaryStage.show();
						//btnLogin.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
						Stage stage = (Stage) closeButton.getScene().getWindow();
						stage.close();
			      } 
			      else if("Yes".equals(type)){
			          //redirect to seller page
			    	  
			    	  Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/AdminView.fxml"));
						Stage primaryStage = new Stage();
						Scene scene = new Scene(root, 700, 550);
						//Scene scene = new Scene(root,400,400);
						scene.getStylesheets().add(getClass().getResource("application1.css").toExternalForm());
						primaryStage.setScene(scene);
						primaryStage.setTitle("Hello " +type1);
						primaryStage.show();
					//	btnLogin.setStyle("-fx-font: 22 arial; -fx-base: #b6e7c9;");
						Stage stage = (Stage) closeButton.getScene().getWindow();
					    stage.close();
			      }
			}
		else
		{
			// error message if the student enters incorrect user id and password
				DropShadow ds = new DropShadow();
				ds.setOffsetY(3.0f);
				ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
				lblStatus.setEffect(ds);
				lblStatus.setCache(true);
				lblStatus.setTextFill(Color.SKYBLUE);
				lblStatus.setText("Incorrect username/password");
				lblStatus.setFont(Font.font(null, FontWeight.BOLD, 32));
			
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	@FXML
	private void bindToTime() {
		final Label start = new Label();
		final DateFormat format = DateFormat.getInstance();  
		final Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {  
		     @Override  
		     public void handle(ActionEvent event) {  
		          final Calendar cal = Calendar.getInstance();  
		          start.setText(format.format(cal.getTime()));  
		     }  

		}));
		  
		timeline.setCycleCount(Animation.INDEFINITE);  
		timeline.play();  
		}
	}


