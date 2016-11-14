package aMartStoreMain.Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import aMartStoreMain.Billing;
import aMartStoreMain.Main;
import aMartStoreMain.DAO.CustomerDAO;
import aMartStoreMain.DAO.addBillingDAO;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.addBilling;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.stage.Stage;
import javafx.util.Callback;

public class addBillingController implements Billing
{
	private Stage dialogStage;
	
	//This is the Text box element in the view for  Customer ID
	@FXML
	private TextField txtCustomerID;
	
	//This is the Text box element in the view for deleting the  Customer ID
	@FXML
	private TextField txtCustomerID1;
	
	//This is the Text box element in the view for product id
	@FXML
	private TextField txtProductID;
	
	//This is the Text box element in the view for order id
	@FXML
	private TextField txtOrderID;
	
	//This is the Text box element in the view for product name
	@FXML
	private TextField txtProductName;
	
	//This is the Text box element in the view for product quantity
	@FXML
	private TextField txtProdQty;
	
	//This is the Text box element in the view for product Amount
	@FXML
	private TextField txtProdAmount;
	
	//This is the Label element in the view for customer id
	@FXML
	public Label lblCustUserId;
	
	//This is the Button  in the view for Saving the records
	@FXML
	private Button btnsave;
	
	//This is the Button  in the view for deleting the records
	@FXML
	private Button btndelete;
	
	//This is the Button  in the view for updating the records
	@FXML
	private Button btnupdate;
	
	//This is the Button  in the view for picking the billing date
	@FXML
	private DatePicker Billdate;
	
	//This is the Button  in the view for closing the screen
	@FXML private javafx.scene.control.Button closeButton;
	
	// This will route the screen to the admin view
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
			public void btnsave() {
				//Extract the data from the view elements
			try{
				//
				String custID = this.txtCustomerID.getText();
				String prodID = this.txtProductID.getText();
				String orderID = this.txtOrderID.getText();
				String prodName = this.txtProductName.getText();
				String prodQty = this.txtProdQty.getText();
				String prodAmt = this.txtProdAmount.getText();
				//Validate the data
				if(custID == null || custID.trim().equals("")) {
					return;
				}
				if(prodID == null || prodID.trim().equals("")) {
					return;
				}
				if(orderID == null || orderID.trim().equals("")) {
					return;
				}
				if(prodName == null || prodName.trim().equals("")||!prodName.matches("[A-Za-z]*")) {
					return;
				}
				if(prodQty == null || prodQty.trim().equals("")) {
					return;
				}
				if(prodAmt== null || prodAmt.trim().equals("")) {
					return;
				}
				//Create the model object
				addBilling bill = new addBilling();
				//Set the values from the input form
				bill.setcustId(Integer.parseInt(custID));
				bill.setprodId(Integer.parseInt(prodID));
				bill.setorderId(Integer.parseInt(orderID));
				bill.setprodName(prodName);
				bill.setprodQty(Integer.parseInt(prodQty));
				bill.setprodAmt(Integer.parseInt(prodAmt));
				bill.setBilldate(Billdate.getValue());
				//bill.setContactnumber(Integer.parseInt(contactnumber));
				//Create a DAO instance of the model
				addBillingDAO b = new addBillingDAO();
				//Use the DAO to persist the model to database
				b.create(bill);
			}
			
				catch (NumberFormatException e) {
					
						JOptionPane.showMessageDialog(null, "Value entered must be an number!");
				}			
			}
			// method to update order 
			public void btnupdate() {
		try{		
				//Extract the data from the view elements
				String custID = this.txtCustomerID.getText();
				String prodID = this.txtProductID.getText();
				String orderID = this.txtOrderID.getText();
				String prodName = this.txtProductName.getText();
				String prodQty = this.txtProdQty.getText();
				String prodAmt = this.txtProdAmount.getText();;
				//Validate the data
				if(custID == null || custID.trim().equals("")) {
					return;
				}
				if(prodID == null || prodID.trim().equals("")) {
					return;
				}
				if(orderID == null || orderID.trim().equals("")) {
					return;
				}
				if(prodName == null || prodName.trim().equals("")||!prodName.matches("[A-Za-z]*")) {
					return;
				}
				if(prodQty == null || prodQty.trim().equals("")) {
					return;
				}
				if(prodAmt== null || prodAmt.trim().equals("")) {
					return;
				}
				//Create the model object
				addBilling bill2 = new addBilling();;
				//Set the values from the input form
				bill2.setcustId(Integer.parseInt(custID));
				bill2.setprodId(Integer.parseInt(prodID));
				bill2.setorderId(Integer.parseInt(orderID));
				bill2.setprodName(prodName);
				bill2.setprodQty(Integer.parseInt(prodQty));
				bill2.setprodAmt(Integer.parseInt(prodAmt));
				//Create a DAO instance of the model
				addBillingDAO b2 = new addBillingDAO();
				//Use the DAO to persist the model to database
				b2.updateb(bill2);
		}	catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Value entered must be an number!");
	}	


			}

			// method to delete order 
			public void btndelete() {
		try{		
				//Extract the data from the view elements
				String ID1 = this.txtCustomerID1.getText();
			
				//Validate the data
				if(ID1 == null || ID1.trim().equals("")) {
					return;
				}
				
				//Create the model object
				addBilling bill1 = new addBilling();
				//Set the values from the input form
				bill1.setcustId1(Integer.parseInt(ID1));
				
				//Create a DAO instance of the model
				addBillingDAO b1 = new addBillingDAO();
				//Use the DAO to persist the model to database
				b1.delete(bill1);
			}
		catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Value entered must be an number!");
	}	
			}
	
	// to view the customer orders 
	private java.sql.Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnCustOrder(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		// CONNECTION DATABASE

		Connection c;
		data = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select cust_id,prod_id,order_id,prod_name,prod_qty,Total_amount from amachira_tblCustOrder"; 

			// ResultSet
			ResultSet rs = connection.createStatement().executeQuery(SQL);

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			/********************************
			 * Data added to ObservableList *
			 ********************************/
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}

			// FINALLY ADD TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

		// Create Main Scene (pop up)
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(tableview);
		Stage stage = new Stage();
		stage.setWidth(500);
		stage.setScene(scene);
		stage.show();

	}
	
	// Table view of all the customer billed transactions
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnallCustOrder(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
	// CONNECTION DATABASE
		Connection c;
		data = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select bill_date,Bill_id,Cust_Id,Prod_id,prod_name,prod_qty,prod_totalamount from amachira_tblInvoice"; 

			// ResultSet
			ResultSet rs = connection.createStatement().executeQuery(SQL);

			/**********************************
			 * TABLE COLUMN ADDED DYNAMICALLY *
			 **********************************/
			for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
				// We are using non property style for making dynamic table
				final int j = i;
				TableColumn col = new TableColumn(rs.getMetaData().getColumnName(i + 1));
				col.setCellValueFactory(new Callback<CellDataFeatures<ObservableList, String>, ObservableValue<String>>() {
					public ObservableValue<String> call(CellDataFeatures<ObservableList, String> param) {
						return new SimpleStringProperty(param.getValue().get(j).toString());
					}
				});

				tableview.getColumns().addAll(col);
				System.out.println("Column [" + i + "] ");
			}

			/********************************
			 * Data added to ObservableList *
			 ********************************/
			while (rs.next()) {
				// Iterate Row
				ObservableList<String> row = FXCollections.observableArrayList();
				for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
					// Iterate Column
					row.add(rs.getString(i));
				}
				System.out.println("Row [1] added " + row);
				data.add(row);
			}

			// FINALLY ADD TO TableView
			tableview.setItems(data);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error on Building Data");
		}

		// Create Main Scene (pop up)
		tableview.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
		Scene scene = new Scene(tableview);
		Stage stage = new Stage();
		stage.setWidth(500);
		stage.setScene(scene);
		stage.show();

	}
}
