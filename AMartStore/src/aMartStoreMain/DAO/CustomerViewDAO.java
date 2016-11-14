package aMartStoreMain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import aMartStoreMain.Model.CustomerViewModel;
import aMartStoreMain.Model.addProduct;

public class CustomerViewDAO {

	//Connection object
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";
    
	public CustomerViewModel updatep(CustomerViewModel custview) {
		//Get a connection
		try {
		    connection = DriverManager.getConnection(url, username, password);
		} catch(SQLException e) {
		    System.out.println("Error creating connection to database: " + e);
		    System.exit(-1);
		}
		//Query to insert a record to the bank table
		String query = "Update amachira_tblCustOrder set prod_qty = ?,cust_id = ?, Total_Amount = Prod_qty*prod_amount where prod_id = ?";
		String query1 = "update amachira_tblProducts set ProdStock_qty= ProdStock_qty- ? where prod_ID = ?";

		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
		   statement.setInt(1, custview.getProdQty());
    	statement.setInt(2, custview.getCust_id()); 
 		  statement.setInt(3, custview.getProd_id());
		    //Execute the insert
		    statement.executeUpdate();
		    //To get the primary key (id) of the newly inserted record
		    ResultSet resultSet = statement.getGeneratedKeys();
		    if(resultSet.next()) {
		    	//Set the id field of the database to the model
		    	custview.setProd_id(resultSet.getInt(1));
		    }
		}
		    catch(SQLException e){
				custview = null;
			    System.out.println("Error Inserting record: " + e);
			}
		  //Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
				
			  //  System.out.println("DAO" +custview.getProdQty());
			   statement1.setInt(1, custview.getProdQty());
			  statement1.setInt(2, custview.getProd_id());
			    //Execute the insert
			    statement1.executeUpdate();
			    //To get the primary key (id) of the newly inserted record
			    ResultSet resultSet1 = statement1.getGeneratedKeys();
			    if(resultSet1.next()) {
			    	//Set the id field of the database to the model
			    	custview.setProd_id(resultSet1.getInt(1));
			    }
		    
		} catch(SQLException e){
			custview = null;
		    System.out.println("Error Inserting record: " + e);
		}
		
		//Close the connection to the database - Very important!!!
		
		//Return the bank object that was inserted with the id field set.
		return custview;
		}
	// Table View
	
public ObservableList<CustomerViewModel> MProds() throws SQLException{

  	    try{ 
  	    	
  	    	connection = DriverManager.getConnection(url, username, password);
  	    }
  	    catch(SQLException e) {
  	        System.out.println("Error creating connection to database: " + e);
  	        System.exit(-1);
  	    }
  	    System.out.println("In DAO");
  	    ObservableList<CustomerViewModel> list1 =FXCollections.observableArrayList();
  	    Statement statement = connection.createStatement();
  	    String query = "SELECT Prod_id,Prod_name,ProdStock_Qty,Prod_Amount from amachira_tblProducts where Supplier_ID = 1000";   
  	    try{
  			//Set the parameters to the query
  	        //To get the primary key (id) of the newly inserted record
  	    	
  	        ResultSet resultSet = statement.executeQuery(query);
  	        while( resultSet.next()){
  	        	CustomerViewModel mcust = new CustomerViewModel();
  	            mcust.setProd_id(resultSet.getInt("Prod_id"));                         	                   
  	            mcust.setProd_name(resultSet.getString("Prod_name"));
  	           mcust.setProdQty(resultSet.getInt("ProdStock_Qty"));
  	         mcust.setAmount(resultSet.getInt("Prod_Amount"));
  	     
  	            System.out.println(resultSet.getInt(1));
  	            list1.add(mcust); 
  	          //  System.out.println(list1);
  	        }
  	      }
  	    catch(Exception e){
  	          e.printStackTrace();
  	          System.out.println("Error on Building Data");            
  	    }
  		return list1;
  	}
		}


