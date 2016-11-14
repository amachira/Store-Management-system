package aMartStoreMain.Controller;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Callback;
import aMartStoreMain.Main;
import aMartStoreMain.DAO.CustomerDAO;
import aMartStoreMain.DAO.addProductDAO;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.addProduct;

import com.mysql.jdbc.Connection;

public class addProductController {
	//This is the parent stage
			private Stage dialogStage;
			ObservableList<String> cate = FXCollections.observableArrayList("General","Apparel","Electronic","Medical");
			
			//This is the Text box element in the view for product ID
			@FXML
			private TextField txtProductID;
			//This is the Text box element in the view for Name of the product
			@FXML
			private TextField txtProductName;
			//This is the Text box element in the view for Name of the product Quantity
			@FXML
			private TextField txtProductStockQty;
			//This is the Text box element in the view for Name of the product Amount
			@FXML
			private TextField txtProductAmount;
			//This is the Text box element in the view for Supplier ID
			@FXML
			private TextField txtSupplierID;
			//This is the Text box element in the view for Product ID to be deleted
			@FXML
			private TextField  txtProductID1;
			//This is the button element in the view for adding the products
			@FXML
			private Button btnProdAdd;
			//This is the button element in the view for updating the products
			@FXML
			private Button btnProdUpdate;
			//This is the button element in the view for deleting the products
			@FXML
			private Button btnProdDelete;
			//This is the button element in the view for viewing the product		
			@FXML
			private Button btnProdView;
			//This is the button element in the view for  viewing the main menu
			@FXML
			private Button MainView;
			//This is the button element in the view for  viewing the main menu
			@FXML
			private Button MainView1;
			@FXML
			private Button MainView2;
			@FXML
			private ChoiceBox Category;
			
			@FXML
			private void initialize()
			{
				Category.setItems(cate);
			}
			//This is the button element in the view for  closing the screen 
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
					//Scene scene = new Scene(root,400,400);
					//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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
			//Method to save the product information to the database
			public void btnProdAdd() {
				try{
				//Extract the data from the view elements
				String ID = this.txtProductID.getText();
				String name = this.txtProductName.getText();
				String qty = this.txtProductStockQty.getText();
				String amount = this.txtProductAmount.getText();
				String supplierid = this.txtSupplierID.getText();
				//Validate the data
				if(ID== null || ID.trim().equals("")) {
					return;
				}
				if(name == null || name.trim().equals("")|| !name.matches("[A-Za-z]*")) {
					return;
				}
				if(qty == null || qty.trim().equals("")) {
					return;
				}
				if(amount == null ||amount.trim().equals("")) {
					return;
				}
				if(supplierid == null || supplierid.trim().equals("")) {
					return;
				}
				//Create the model object
				addProduct prod = new addProduct();
				//Set the values from the input form
				prod.setId(Integer.parseInt(ID));
				prod.setName(name);
				prod.setStockQty(Integer.parseInt(qty));
				prod.setAmount(Integer.parseInt(amount));
				prod.setSupplierId(Integer.parseInt(supplierid));
				prod.setCategory((String)Category.getValue());
				
				//Create a DAO instance of the model
				addProductDAO p = new addProductDAO();
				//Use the DAO to persist the model to database
				p.create(prod);
				}
				catch (NumberFormatException e) {
					
					JOptionPane.showMessageDialog(null, "Value entered must be an number!");
			}	
			}
			
			
			public void btnProdUpdate() {
			
				try{
					//Extract the data from the view elements
				String ID = this.txtProductID.getText();
				String name = this.txtProductName.getText();
				String qty = this.txtProductStockQty.getText();
				String amount = this.txtProductAmount.getText();
				String supplierid = this.txtSupplierID.getText();
				//Validate the data
				if(ID== null || ID.trim().equals("")) {
					return;
				}
				if(name == null || name.trim().equals("")|| !name.matches("[A-Za-z]*")) {
					return;
				}
				if(qty == null || qty.trim().equals("")) {
					return;
				}
				if(amount == null ||amount.trim().equals("")) {
					return;
				}
				if(supplierid == null || supplierid.trim().equals("")) {
					return;
				}
				//Create the model object
				addProduct  prod2 = new addProduct();
				//Set the values from the input form
				prod2.setId(Integer.parseInt(ID));
				prod2.setName(name);
				prod2.setStockQty(Integer.parseInt(qty));
				prod2.setAmount(Integer.parseInt(amount));
				prod2.setSupplierId(Integer.parseInt(supplierid));
				prod2.setCategory((String)Category.getValue());
				//Create a DAO instance of the model
				addProductDAO p2 = new addProductDAO();
				//Use the DAO to persist the model to database
				p2.updatep(prod2);
				}
				catch (NumberFormatException e) {
					
					JOptionPane.showMessageDialog(null, "Value entered must be an number!");
			}	
			}
			public void btnProdDelete() {
				try{
				//Extract the data from the view elements
				String ID1 = this.txtProductID1.getText();
			
				//Validate the data
				if(ID1 == null || ID1.trim().equals("")) {
					return;
				}
				
				//Create the model object
				addProduct prod1 = new addProduct();
				//Set the values from the input form
				prod1.setId1(Integer.parseInt(ID1));
				
				//Create a DAO instance of the model
				addProductDAO p1 = new addProductDAO();
				//Use the DAO to persist the model to database
				p1.delete(prod1);
				}
				catch (NumberFormatException e) {
					
					JOptionPane.showMessageDialog(null, "Value entered must be an number!");
			}	
		
			}
			
		// View from the table from the products table
			 private java.sql.Connection connection;
				//Database connection parameters
			    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
			    private String username = "fpuser";
			    private String password = "510";
					@SuppressWarnings({ "unchecked", "rawtypes" })
					public void btnProdView(ActionEvent event) throws Exception {  
					    
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
					        
					            //SQL FOR SELECTING ALL OF CUSTOMER
					            String SQL = "SELECT * from amachira_tblProducts";
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
