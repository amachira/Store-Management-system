package aMartStoreMain.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aMartStoreMain.Model.addBilling;

public class addBillingDAO {
	//Connection object
		private Connection connection;
		//Database connection parameters
	    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	    private String username = "fpuser";
	    private String password = "510";

	    //Method to save Billing details in to the database 
		public addBilling create(addBilling bill) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to insert a record to the Billing table
			String query = "insert into amachira_tblInvoice (Cust_ID,prod_ID,Order_Id,prod_name,prod_Qty,prod_totalamount,bill_date)values (?,?,?,?,?,?,?) ;";
			//Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
	            statement.setInt(1, bill.getcustId());
	            statement.setInt(2, bill.getprodId());
	            statement.setInt(3, bill.getorderId());
	            statement.setString(4, bill.getprodName());
	            statement.setInt(5, bill.getprodQty());
	            statement.setInt(6, bill.getprodAmt());
	            Date billdate = Date.valueOf(bill.getBilldate());
	            statement.setDate(7, billdate);
	            //Execute the insert
	            statement.executeUpdate();
	            //To get the primary key (id) of the newly inserted record
	            ResultSet resultSet = statement.getGeneratedKeys();
	            if(resultSet.next()) {
	            	//Set the id field of the database to the model
	            	bill.setcustId(resultSet.getInt(1));
	            }
	        } catch(SQLException e){
	        	bill = null;
	            System.out.println("Error Inserting record: " + e);
	        }
			
				
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			//Return the Billing  object that was inserted with the id field set.
			return bill;
		}
		
		//Method to delete Billing data from the database 
		public addBilling delete(addBilling bill1) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to delete a record from the invoice table
			String query = "Delete from amachira_tblInvoice where cust_id = ? ;";
			
			//Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement = connection.prepareStatement
					(query, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
	            statement.setInt(1, bill1.getcustId1());
	            //Execute the insert
	            statement.executeUpdate();
	            //To get the primary key (id) of the newly inserted record
	            ResultSet resultSet = statement.getGeneratedKeys();
	            if(resultSet.next()) {
	            	//Set the id field of the database to the model
	            	bill1.setcustId1(resultSet.getInt(1));
	            }
	        } catch(SQLException e){
	        	bill1 = null;
	            System.out.println("Error Inserting record: " + e);
	        }
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			//Return the billing object that was inserted with the id field set.
			return bill1;
		}


		// Method to update Billing details in to the database 

	public addBilling updateb(addBilling bill2) {
	//Get a connection
	try {
	    connection = DriverManager.getConnection(url, username, password);
	} catch(SQLException e) {
	    System.out.println("Error creating connection to database: " + e);
	    System.exit(-1);
	}
	//Query to update a record to the invoice table
	String query = "update amachira_tblInvoice set prod_ID = ?,order_Id = ?,Prod_name = ?, Prod_qty=?, Prod_TotalAmount =? where Cust_id = ?;";

	//Use prepared statements to avoid SQL injection attacks
	try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
		//Set the parameters to the query
		
         statement.setInt(1, bill2.getprodId());
         statement.setInt(2, bill2.getorderId());
         statement.setString(3, bill2.getprodName());
         statement.setInt(4, bill2.getprodQty());
         statement.setInt(5, bill2.getprodAmt());
         statement.setInt(6, bill2.getcustId());
	    //Execute the insert
	    statement.executeUpdate();
	    //To get the primary key (id) of the newly inserted record
	    ResultSet resultSet = statement.getGeneratedKeys();
	    if(resultSet.next()) {
	    	//Set the id field of the database to the model
	    	bill2.setcustId(resultSet.getInt(1));
	    }
	} catch(SQLException e){
		bill2 = null;
	    System.out.println("Error Inserting record: " + e);
	}
	//Close the connection to the database - Very important!!!
	try {
	    connection.close();
	    connection = null;
	} catch(SQLException e) {
	    System.out.println("Error closing connection: " + e);
	}
	//Return the billing  object that was inserted with the id field set.
	return bill2;
	}
}
