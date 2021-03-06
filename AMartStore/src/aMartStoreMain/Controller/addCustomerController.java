package aMartStoreMain.Controller;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import com.mysql.jdbc.Connection;

import aMartStoreMain.Main;
import aMartStoreMain.DAO.CustomerDAO;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.User;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

public class addCustomerController {
	//This is the parent stage
		private Stage dialogStage;
		ObservableList<String> isAdmin = FXCollections.observableArrayList("Yes","No");
		
		//This is the Text box element in the view for the customer ID
		@FXML
		private TextField txtCustomerID;
		//This is the Text box element in the view for the customer name
		@FXML
		private TextField txtCustomerName;
		//This is the Text box element in the view for the customer city
		@FXML
		private TextField txtCustomerCity;
		//This is the choice box element in the view for the customer usertype i.e. customer or admin
		@FXML
		private ChoiceBox usertype;
		
		@FXML
		private void initialize()
		{
			usertype.setItems(isAdmin);
		}
		//This is the Text box element in the view for the customer contact number
		@FXML
		private TextField txtContactNumber;
		//This is the Text box element in the view for the customer id for deletion
		@FXML
		private TextField txtCustomerID1;
		//This is the Button element in the view for adding the customer details
		@FXML
		private Button save;
		//This is the Button element in the view for deleting the customer details
		@FXML
		private Button delete;
		//This is the Button element in the view for viewing the customer details
		@FXML
		private Button view;
		//This is the Button element in the view for returning to the main admin screen
		@FXML
		private Button MainView;
		@FXML
		private Button MainView1;
		@FXML
		private Button MainView2;
		//This is the Button element in the view for closing the current stage
		@FXML private javafx.scene.control.Button closeButton;
		@FXML private javafx.scene.control.Button closeButton1;
		@FXML private javafx.scene.control.Button closeButton2;
		//Method to set the parent stage of the current view	
		public void setDialogStage(Stage dialogStage) {
			this.dialogStage = dialogStage;	
		}
		 
		// to return to Main Menu
		public void btnMainView() {
			try{
			  Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/AdminView.fxml"));
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root, 700, 550);
				//Scene scene = new Scene(root,400,400);
				//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
				primaryStage.setScene(scene);
				primaryStage.setTitle("Main View");
				primaryStage.show();
				Stage stage = (Stage) closeButton.getScene().getWindow();
			    stage.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
		public void btnMainView1() {
			try{
			  Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/AdminView.fxml"));
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root, 700, 550);
				primaryStage.setScene(scene);
			  primaryStage.setTitle("Main View");
				primaryStage.show();
				Stage stage = (Stage) closeButton1.getScene().getWindow();
			    stage.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}	
		public void btnMainView2() {
			try{
			  Parent root = (Parent)FXMLLoader.load(Main.class.getResource("view/AdminView.fxml"));
				Stage primaryStage = new Stage();
				Scene scene = new Scene(root, 700, 550);
				primaryStage.setScene(scene);
				primaryStage.setTitle("Main View");
				primaryStage.show();
				Stage stage = (Stage) closeButton2.getScene().getWindow();
			    stage.close();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		//Method to save the form to database
		public void save() {
			try{
			//Extract the data from the view elements
			String ID = this.txtCustomerID.getText();
			String name = this.txtCustomerName.getText();
			String city = this.txtCustomerCity.getText();
			String contactnumber = this.txtContactNumber.getText();
			//Validate the data
			if(ID == null || ID.trim().equals("")) {
				return;
			}
			if(name == null || name.trim().equals("")||!name.matches("[A-Za-z]*")) {
				return;
			}
			if(city == null || city.trim().equals("")||!city.matches("[A-Za-z]*")) {
				return;
			}
			if(contactnumber == null || contactnumber.trim().equals("")) {
				return;
			}
			//Create the model object
			Customer cust = new Customer();
			//Set the values from the input form
			cust.setId(Integer.parseInt(ID));
			cust.setName(name);
			cust.setCity(city);
			cust.setContactnumber(Integer.parseInt(contactnumber));
			cust.setUserType((String) usertype.getValue());
			//Create a DAO instance of the model
			CustomerDAO c = new CustomerDAO();
			//Use the DAO to persist the model to database
			c.create(cust);
		
			}
			catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(null, "Value entered must be an number!");
		}	
		}
		
		public void update() {
			try{
			//Extract the data from the view elements
			String ID = this.txtCustomerID.getText();
			String name = this.txtCustomerName.getText();
			String city = this.txtCustomerCity.getText();
			String contactnumber = this.txtContactNumber.getText();
			
			//Validate the data
			if(ID == null || ID.trim().equals("")) {
				return;
			}
			if(name == null || name.trim().equals("")||!name.matches("[A-Za-z]*")) {
				return;
			}
			if(city == null || city.trim().equals("")||!city.matches("[A-Za-z]*")) {
				return;
			}
			if(contactnumber == null || contactnumber.trim().equals("")) {
				return;
			}
			//Create the model object
			Customer cust2 = new Customer();
			//Set the values from the input form
			cust2.setId(Integer.parseInt(ID));
			cust2.setName(name);
			cust2.setCity(city);
			cust2.setContactnumber(Integer.parseInt(contactnumber));
			
			//Create a DAO instance of the model
			CustomerDAO c2 = new CustomerDAO();
			//Use the DAO to persist the model to database
			c2.updatec(cust2);
				}	catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(null, "Value entered must be an number!");
		}	
		}
		
	
		
		public void delete() {
			try{
			
			//Extract the data from the view elements
			String ID1 = this.txtCustomerID1.getText();
		
			//Validate the data
			if(ID1 == null || ID1.trim().equals("")) {
				return;
			}
			
			//Create the model object
			Customer cust1 = new Customer();
			//Set the values from the input form
			cust1.setId1(Integer.parseInt(ID1));
			
			//Create a DAO instance of the model
			CustomerDAO c1 = new CustomerDAO();
			//Use the DAO to persist the model to database
			c1.create1(cust1);
			}
			catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(null, "Value entered must be an number!");
		}	
		}
		
		
		
	// View from the data from the customer table
		 private java.sql.Connection connection;
			//Database connection parameters
		    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
		    private String username = "fpuser";
		    private String password = "510";
				@SuppressWarnings({ "unchecked", "rawtypes" })
				public void open(ActionEvent event) throws Exception {  
				    
				         @SuppressWarnings({ "rawtypes" })
				    	
				         //TABLE VIEW AND DATA

				    	TableView tableview = new TableView();
				    	ObservableList<ObservableList> data;
				
				   	//CONNECTION DATABASE
				    	  Connection c ;
				          data = FXCollections.observableArrayList();
				          try{
				        	  try {
				    	            connection = DriverManager.getConnection(url, username, password);
				    	        } catch(SQLException e) {
				    	            System.out.println("Error creating connection to database: " + e);
				    	            System.exit(-1);
				    	        }
				         //   c = DBConnect.connect();
				            //SQL FOR SELECTING ALL OF CUSTOMER
				            String SQL = "SELECT Cust_Id,Cust_name,Cust_city,Cust_contact from amachira_tblCustomer";
				            //ResultSet
				            ResultSet rs = connection.createStatement().executeQuery(SQL);

				            /**********************************
				             * TABLE COLUMN ADDED DYNAMICALLY *
				             **********************************/
				            for(int i=0 ; i<rs.getMetaData().getColumnCount(); i++){
				                //We are using non property style for making dynamic table
				                final int j = i;                
				                TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i+1));
				                col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList,String>,ObservableValue<String>>(){                    
				                    public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {                                                                                              
				                        return new SimpleStringProperty(param.getValue().get(j).toString());                        
				                    }                    
				                });
				               
				                tableview.getColumns().addAll(col); 
				                System.out.println("Column ["+i+"] ");
				            }

				            /********************************
				             * Data added to ObservableList *
				             ********************************/
				            while(rs.next()){
				                //Iterate Row
				                ObservableList<String> row = FXCollections.observableArrayList();
				                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){
				                    //Iterate Column
				                    row.add(rs.getString(i));
				                }
				                System.out.println("Row [1] added "+row );
				                data.add(row);
				            }

				            //FINALLY ADD TO TableView
				            tableview.setItems(data);
				          }catch(Exception e){
				              e.printStackTrace();
				              System.out.println("Error on Building Data");             
				          }
				       
				        //Create Main Scene (pop up)
				        tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
				        Scene scene = new Scene(tableview);        
				        Stage stage = new Stage();
				        stage.setWidth(500);
				        stage.setScene(scene);
				        stage.show();
			  }
				
		//This is required as stage.close() in the program will not trigger any events.
		//To have callback listeners on the close event, we trigger the external close event
		private void close() {
			dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
		}

	}
