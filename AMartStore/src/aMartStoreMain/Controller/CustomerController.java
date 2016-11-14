package aMartStoreMain.Controller;

import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.swing.JOptionPane;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import aMartStoreMain.Main;
import aMartStoreMain.DAO.CustomerViewDAO;
import aMartStoreMain.DAO.addProductDAO;
import aMartStoreMain.Model.Cust;
import aMartStoreMain.Model.CustomerViewModel;
import aMartStoreMain.Model.addProduct;

public class CustomerController extends LoginController implements Initializable {
	//This is the Label element in the view for  Customer User Name
	@FXML
	public Label lblCustUserName;
	//This is the Label element in the view for  Customer User ID
	@FXML
	public Label lblCustUserId;
	//This is the text element in the view for product ID
	@FXML
	public TextField txtProductId;
	//This is the text element in the view for product quantity
	@FXML
	public TextField txtProductQty;
	//This is the text element in the view for low range
	@FXML
	public TextField lowRange;
	//This is the text element in the view for high range
	@FXML
	public Label ItemAdded;
	@FXML
	public TextField hiRange;
	private Integer hival;
	private Integer loval;
	@FXML
	private javafx.scene.control.Button closeButton;
	private static Integer custid;
	private Stage stage;
	@FXML
	public Label Start;


	@FXML
	private ObservableList<CustomerViewModel> list1 = FXCollections.observableArrayList();

	// private User loggedUser;
	public void initialize(URL location, ResourceBundle resources) {
		Cust c = new Cust();
		// System.out.println((String)c.getName2());
		lblCustUserName.setText((String) c.getName2());
		String str = String.valueOf(c.getId2());
		lblCustUserId.setText(str);
	}
	//This is the button to log out the user
	@FXML
	protected void processLogout() {

		try {

			Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/LoginView.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root, 700, 550);
			// Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
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

	public void btnProdSelection() {
		try{
		// Extract the data from the view elements
		String qty = this.txtProductQty.getText();
		String prod_id = this.txtProductId.getText();
		String cust_id = this.lblCustUserId.getText();

		// Validate the data
		if (prod_id == null || prod_id.trim().equals("")) {
			return;
		}
		if (qty == null || qty.trim().equals("")) {
			return;
		}
		// Create the model object
		CustomerViewModel custview = new CustomerViewModel();
		// Set the values from the input form
		custview.setProdQty(Integer.parseInt(qty));
		System.out.println(qty);
		custview.setProd_id(Integer.parseInt(prod_id));
		System.out.println(prod_id);
		custview.setCust_id(Integer.parseInt(cust_id));
		System.out.println(cust_id);

		// Create a DAO instance of the model
		CustomerViewDAO cv = new CustomerViewDAO();
		// Use the DAO to persist the model to database
		
		cv.updatep(custview);
		DropShadow ds = new DropShadow();
		ds.setOffsetY(3.0f);
		ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
		 
		//Text t = new Text();
		ItemAdded.setEffect(ds);
		ItemAdded.setCache(true);
	//	ItemAdded.setX(10.0f);
		//ItemAdded.setY(270.0f);
		ItemAdded.setTextFill(Color.LIME);
		ItemAdded.setText("Product Selected");
		ItemAdded.setFont(Font.font(null, FontWeight.BOLD, 32));
		}catch (NumberFormatException e) {
			
			JOptionPane.showMessageDialog(null, "Value entered must be an number!");
	}	
		
		
	}

	// General products View

	private java.sql.Connection connection;
	// Database connection parameters
	private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	private String username = "fpuser";
	private String password = "510";

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnMainView() {
		try {
			Parent root = (Parent) FXMLLoader.load(Main.class.getResource("view/AdminView.fxml"));
			Stage primaryStage = new Stage();
			Scene scene = new Scene(root, 700, 550);
			// Scene scene = new Scene(root,400,400);
			// scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.setTitle("Main View");
			primaryStage.show();
			Stage stage = (Stage) closeButton.getScene().getWindow();
			stage.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// to view the customer selected range

	public void RangeSelect (ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		String custID1 = lblCustUserId.getText();
		String lovalue = lowRange.getText();
		String hivalue = hiRange.getText();
		
		CustomerController custcon  = new CustomerController();
		custcon.setLowVal(Integer.parseInt(lovalue));
		custcon.setHiVal(Integer.parseInt(hivalue));
		// CONNECTION DATABASE

		CustomerController rep1 = new CustomerController();
		// Set the values from the input form

		// rep1.setCust_id(Integer.parseInt(custID1));
		System.out.println(custID1);

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
			// c = DBConnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select prod_id,prod_name,prodStock_Qty,prod_amount from amachira_tblProducts where prod_amount between 1 and 50";
			
			try{
				PreparedStatement statement = connection.prepareStatement(SQL, Statement.RETURN_GENERATED_KEYS);
			
		
				ResultSet rs = connection.createStatement().executeQuery(SQL);
				
			
				

			// ResultSet
			//ResultSet rs = connection.createStatement().executeQuery(SQL);

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
				
			}
		 catch (Exception e) {
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
		catch(Exception e)
		{
			
		}
		
				

	}

	public Integer getHiVal() {

		return hival;
	}

	private void setHiVal(Integer hival) {
		// TODO Auto-generated method stub
		this.hival = hival;
	}

	public Integer getLowVal() {

		return loval;
	}

	private void setLowVal(Integer loval) {
		// TODO Auto-generated method stub
		this.loval = loval;
	}

	// To view Customer history
	public void btnCustHistory(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		String custID1 = lblCustUserId.getText();
		// CONNECTION DATABASE

		CustomerController rep1 = new CustomerController();
		// Set the values from the input form

		// rep1.setCust_id(Integer.parseInt(custID1));
		System.out.println(custID1);

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
			// c = DBConnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select prod_name,prod_qty,prod_id,prod_amount,Total_Amount from amachira_tblCustOrder where cust_id= "
					+ custID1;

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

	// to view the table of all the products
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void ProdView(ActionEvent event) throws Exception {

		@SuppressWarnings({ "rawtypes" })
		// TABLE VIEW AND DATA
		TableView tableview = new TableView();
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
			String SQL = "SELECT Prod_id,Prod_name,ProdStock_Qty,Prod_Amount from amachira_tblProducts where Category = 'General'";
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

	// General products View

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void MedicalView(ActionEvent event) throws Exception {

		@SuppressWarnings({ "rawtypes" })
		// TABLE VIEW AND DATA
		TableView tableview = new TableView();
		ObservableList<ObservableList> data;

		// CONNECTION DATABASE Connection c ;
		data = FXCollections.observableArrayList();
		try {
			try {
				connection = DriverManager.getConnection(url, username, password);
			} catch (SQLException e) {
				System.out.println("Error creating connection to database: " + e);
				System.exit(-1);
			}
			// c = DBConnect.connect();
			// SQL FOR SELECTING ALL OFCUSTOMER
			String SQL = "SELECT Prod_id,Prod_name,ProdStock_Qty,Prod_Amount from amachira_tblProducts where Category = 'Medical'";
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

	// table view of all the customers billed history

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnbilledOrder(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		String custID1 = lblCustUserId.getText();
		// CONNECTION DATABASE

		CustomerController rep1 = new CustomerController();
		// Set the values from the input form

		// rep1.setCustid(Integer.parseInt(custID1));
		System.out.println(custID1);

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
			// c = DBConnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "select * from amachira_tblInvoice where cust_id= " + custID1;

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

	// Table view for Electronic View
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnElectronicView(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
		//String custID1 = lblCustUserId.getText();
		// CONNECTION DATABASE

		CustomerController rep1 = new CustomerController();
		// Set the values from the input form
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
			// c = DBConnect.connect();
			// SQL FOR SELECTING ALL OF CUSTOMER
			String SQL = "SELECT Prod_id,Prod_name,ProdStock_Qty,Prod_Amount from amachira_tblProducts where Category = 'Electronic'";

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

	// Table View for Apparels
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void btnApparelsView(ActionEvent event) throws Exception {

		// TABLE VIEW AND DATA

		@SuppressWarnings("rawtypes")
		TableView tableview = new TableView();
		@SuppressWarnings("rawtypes")
		ObservableList<ObservableList> data;
	//	String custID1 = lblCustUserId.getText();
		// CONNECTION DATABASE

		CustomerController rep1 = new CustomerController();
	

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
			String SQL = "SELECT Prod_id,Prod_name,ProdStock_Qty,Prod_Amount from amachira_tblProducts where Category = 'Apparel'";

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