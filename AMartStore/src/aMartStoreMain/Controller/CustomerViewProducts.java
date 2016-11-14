package aMartStoreMain.Controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import aMartStoreMain.DAO.CustomerViewDAO;
import aMartStoreMain.Model.CustomerViewModel;

public class CustomerViewProducts implements Initializable{
	@FXML
	private TableView<CustomerViewModel> data;
	@FXML
	private TableColumn<CustomerViewModel, Integer> prod_id;
	@FXML
	private TableColumn<CustomerViewModel, String> prod_name;
	@FXML
	private TableColumn<CustomerViewModel, Integer> prod_StockQty;
	@FXML
	private TableColumn<CustomerViewModel, Integer> prod_Amount;
	@FXML
	private ObservableList<CustomerViewModel> list1 = FXCollections.observableArrayList();
	@FXML
	//public void MedicalView(ActionEvent event) throws Exception {
    private ObservableList<CustomerViewModel> list = FXCollections.observableArrayList() ;
   // BookDAO b = new BookDAO();
  
    public void initialize (URL fxmlFileLocation, ResourceBundle resources) {
		CustomerViewDAO b = new CustomerViewDAO();

		// View.setOnAction(new EventHandler<ActionEvent>() {

		// public void View(ActionEvent event) {
		prod_id.setCellValueFactory(new PropertyValueFactory<CustomerViewModel, Integer>("Prod_id"));
		prod_name.setCellValueFactory(new PropertyValueFactory<CustomerViewModel, String>("Prod_name"));
		prod_StockQty.setCellValueFactory(new PropertyValueFactory<CustomerViewModel, Integer>("ProdStock_Qty"));
		prod_Amount.setCellValueFactory(new PropertyValueFactory<CustomerViewModel, Integer>("Prod_Amount"));

		data.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		try {
			list1 = b.MProds();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		data.setItems(list1);
	}
}
