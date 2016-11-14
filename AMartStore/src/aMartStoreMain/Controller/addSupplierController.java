package aMartStoreMain.Controller;

import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.beans.binding.StringExpression;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import aMartStoreMain.Main;
import aMartStoreMain.DAO.CustomerDAO;
import aMartStoreMain.DAO.addSupplierDAO;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.addSupplier;

import com.mysql.jdbc.Connection;

public class addSupplierController
{
	//This is the parent stage
			private Stage dialogStage;
			
			//This is the Text box element in the view for Supplier ID
			@FXML
			private TextField txtSupplierID;
			//This is the Text box element in the view for Name of the Supplier
			@FXML
			private TextField txtSupplierName;
			//This is the Text box element in the view for City of the Supplier
			@FXML
			private TextField txtSupplierCity;
			//This is the Text box element in the view for Contact Number of the Supplier
			@FXML
			private TextField txtContactNumber;
			//This is the Text box element in the view for deleting the supplier ID
			@FXML
			private TextField txtSupplierID1;
			//This is the Button element in the view for saving the information	
			@FXML
			private Button save;
			//This is the Button element in the view for deleting the information	
			@FXML
			private Button delete;
			//This is the Button element in the view for viewing the information
			@FXML
			private Button view;
			//This is the Button element in the view for returning to the main menu
			@FXML
			private Button MainView;
			@FXML
			private Button MainView1;
			@FXML
			private Button MainView2;
			@FXML 
			private RadioButton Admin;
			@FXML 
			private RadioButton Student;
			
			private ToggleGroup Group;
			
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
		
			//Method to save the form to database
			public void save() {
				try{
				//Extract the data from the view elements
				String ID = this.txtSupplierID.getText();
				String name = this.txtSupplierName.getText();
				String city = this.txtSupplierCity.getText();
				String contactnumber = this.txtContactNumber.getText();	
			//Validate the data
				if(ID == null ||ID.trim().equals("")) {
					return;
				}
				if(city == null || city.trim().equals("")||!city.matches("[A-Za-z]*")) {
					return;
				}
				if(name == null || name.trim().equals("")||!name.matches("[A-Za-z]*")) {
					return;
				}
				if(contactnumber == null || contactnumber.trim().equals("")) {
					return;
				}	
				//Create the model object
				addSupplier supp = new addSupplier();
				//Set the values from the input form
				supp.setId(Integer.parseInt(ID));
				supp.setName(name);
				supp.setCity(city);
				supp.setContactnumber(Integer.parseInt(contactnumber));
				//Create a DAO instance of the model
				addSupplierDAO s = new addSupplierDAO ();
				//Use the DAO to persist the model to database
				s.create(supp);
				}catch (NumberFormatException e) {
					
					JOptionPane.showMessageDialog(null, "Value entered must be an number!");
			}	
				
			}
			
			public void update() {
				try{
				//Extract the data from the view elements
				String ID = this.txtSupplierID.getText();
				String name = this.txtSupplierName.getText();
				String city = this.txtSupplierCity.getText();
				String contactnumber = this.txtContactNumber.getText();
				//Validate the data
				if(ID == null ||ID.trim().equals("")) {
					return;
				}
				if(city == null || city.trim().equals("")||!city.matches("[A-Za-z]*")) {
					return;
				}
				if(name == null || name.trim().equals("")||!name.matches("[A-Za-z]*")) {
					return;
				}
				if(contactnumber == null || contactnumber.trim().equals("")) {
					return;
				}	
				//Create the model object
				addSupplier supp2 = new addSupplier();
				//Set the values from the input form
				supp2.setId(Integer.parseInt(ID));
				supp2.setName(name);
				supp2.setCity(city);
				supp2.setContactnumber(Integer.parseInt(contactnumber));
				//Create a DAO instance of the model
				addSupplierDAO s2 = new addSupplierDAO();
				//Use the DAO to persist the model to database
				s2.updates(supp2);
				}
				catch (NumberFormatException e) {
					
					JOptionPane.showMessageDialog(null, "Value entered must be an number!");
			}	
			}
			public void delete() {
				
			try{
				//Extract the data from the view elements
				String ID1 = this.txtSupplierID1.getText();
			
				//Validate the data
				if(ID1 == null || ID1.trim().equals("")) {
					return;
				}	
				//Create the model object
				addSupplier supp1 = new addSupplier();
				//Set the values from the input form
				supp1.setId1(Integer.parseInt(ID1));
				//Create a DAO instance of the model
				addSupplierDAO s1 = new addSupplierDAO();
				//Use the DAO to persist the model to database
				s1.delete(supp1);
			}
			catch (NumberFormatException e) {
				
				JOptionPane.showMessageDialog(null, "Value entered must be an number!");
		}	
			}
			
			
			
		// View from the table for Supplier Details
			 private java.sql.Connection connection;
				//Database connection parameters
			    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
			    private String username = "fpuser";
			    private String password = "510";
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void btnSuppView(ActionEvent event) throws Exception {  
				  
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
					            String SQL = "SELECT * from amachira_tblSuppliers";
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

					// called the objects of the customer Controller for the 
					//variour products 
					@SuppressWarnings({ "unchecked", "rawtypes" })
		public void SuppElecView(ActionEvent event) throws Exception {

						@SuppressWarnings("rawtypes")
						TableView tableview = new TableView();
						@SuppressWarnings("rawtypes")
						ObservableList<ObservableList> data;
			CustomerController elec = new CustomerController();
						elec.btnElectronicView(event);
					}
					
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void SuppApparelView(ActionEvent event) throws Exception {

									@SuppressWarnings("rawtypes")
									TableView tableview = new TableView();
									@SuppressWarnings("rawtypes")
									ObservableList<ObservableList> data;
						CustomerController elec = new CustomerController();
									elec.btnApparelsView(event);
								}
					
					
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void SuppGeneralView(ActionEvent event) throws Exception {

									@SuppressWarnings("rawtypes")
									TableView tableview = new TableView();
									@SuppressWarnings("rawtypes")
									ObservableList<ObservableList> data;
						CustomerController elec = new CustomerController();
									elec.ProdView(event);
								}
					
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void SuppMedicalView(ActionEvent event) throws Exception {

									@SuppressWarnings("rawtypes")
									TableView tableview = new TableView();
									@SuppressWarnings("rawtypes")
									ObservableList<ObservableList> data;
						CustomerController elec = new CustomerController();
									elec.MedicalView(event);
								}
					
			//This is required as stage.close() in the program will not trigger any events.
			//To have callback listeners on the close event, we trigger the external close event
			private void close() {
				dialogStage.fireEvent(new WindowEvent(dialogStage,WindowEvent.WINDOW_CLOSE_REQUEST));
			}

}
