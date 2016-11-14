package aMartStoreMain.DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import aMartStoreMain.Model.Customer;

public class CustomerDAO {
	//Connection object
		private Connection connection;	
		//Database connection parameters
	    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
	    private String username = "fpuser";
	    private String password = "510";

	    //Method to save a Customer model to database
		public Customer create(Customer cust) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
String query1 = "INSERT INTO amachira_users (Username,password,isAdmin,User_id) VALUES (?, 'cust',?, ?) ;";
			
			//Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
	           
	            statement1.setString(1, cust.getName());
	           // statement.setString(3, cust.setUserData("Yes"));
	           statement1.setString(2, cust.getUserType());
	            statement1.setInt(3, cust.getId());
	            
	           
			     //Execute the insert
	            statement1.executeUpdate();
	            //To get the primary key (id) of the newly inserted record
	            ResultSet resultSet1 = statement1.getGeneratedKeys();
	            if(resultSet1.next()) {
	            	//Set the id field of the database to the model
	            	cust.setId(resultSet1.getInt(1));
	            }
	        } catch(SQLException e){
	        	cust = null;
	            System.out.println("Error Inserting record: " + e);
	        }
			
			
			//Query to insert a record to the customer table
			String query = "INSERT INTO amachira_tblCustomer (Cust_ID, Cust_name,Cust_city,Cust_contact,User_ID) VALUES (?, ?, ?, ?,?) ;";
			//Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
	            statement.setInt(1, cust.getId());
	            statement.setString(2, cust.getName());
	            statement.setString(3, cust.getCity());
	            statement.setInt(4, cust.getContactnumber());
	            statement.setInt(5, cust.getId());
	           
			     //Execute the insert
	            statement.executeUpdate();
	            //To get the primary key (id) of the newly inserted record
	            ResultSet resultSet = statement.getGeneratedKeys();
	            if(resultSet.next()) {
	            	//Set the id field of the database to the model
	            	cust.setId(resultSet.getInt(1));
	            }
	        } catch(SQLException e){
	        	cust = null;
	            System.out.println("Error Inserting record: " + e);
	        }

			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			//Return the customer object that was inserted with the id field set.
			return cust;
		}
		
		//Method to delete customer from database
		public Customer create1(Customer cust1) {
			//Get a connection
			try {
	            connection = DriverManager.getConnection(url, username, password);
	        } catch(SQLException e) {
	            System.out.println("Error creating connection to database: " + e);
	            System.exit(-1);
	        }
			//Query to delete record from  the Customer table
			String query = "Delete from amachira_tblCustomer where cust_id = ? ;";
			//Use prepared statements to avoid SQL injection attacks
			try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
				//Set the parameters to the query
	            statement.setInt(1, cust1.getId1());
	            //Execute the insert
	            statement.executeUpdate();
	            //To get the primary key (id) of the newly inserted record
	            ResultSet resultSet = statement.getGeneratedKeys();
	            if(resultSet.next()) {
	            	//Set the id field of the database to the model
	            	cust1.setId1(resultSet.getInt(1));
	            }
	            
	            // delete reecord from user table 
	            
	          //Query to delete a record to the users table
				String query1 = "Delete from amachira_Users where User_id = ? ;";
				//Use prepared statements to avoid SQL injection attacks
				try(PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
					//Set the parameters to the query
		            statement1.setInt(1, cust1.getId1());
		            //Execute the insert
		            statement1.executeUpdate();
		            //To get the primary key (id) of the newly inserted record
		            ResultSet resultSet1 = statement.getGeneratedKeys();
		            if(resultSet1.next()) {
		            	//Set the id field of the database to the model
		            	cust1.setId1(resultSet1.getInt(1));
		            }
		        } catch(SQLException e){
		        	cust1 = null;
		            System.out.println("Error Inserting record: " + e);
		        }
	        } catch(SQLException e){
	        	cust1 = null;
	            System.out.println("Error Inserting record: " + e);
	        }
			//Close the connection to the database - Very important!!!
			try {
	            connection.close();
	            connection = null;
	        } catch(SQLException e) {
	            System.out.println("Error closing connection: " + e);
	        }
			//Return the customer object that was inserted with the id field set.
			return cust1;
		}
		// to update the values in the database 

public Customer updatec(Customer cust2) {
	//Get a connection
	try {
        connection = DriverManager.getConnection(url, username, password);
    } catch(SQLException e) {
        System.out.println("Error creating connection to database: " + e);
        System.exit(-1);
    }
	//Query to update a record to the customer table
	String query = "update amachira_tblCustomer set Cust_name = ?, Cust_city=?, Cust_contact =? where cust_id = ?;";
	
	//Use prepared statements to avoid SQL injection attacks
	try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
		//Set the parameters to the query
		
        statement.setString(1, cust2.getName());
        statement.setString(2, cust2.getCity());
        statement.setInt(3, cust2.getContactnumber());
        statement.setInt(4, cust2.getId());
        //Execute the insert
        statement.executeUpdate();
        //To get the primary key (id) of the newly inserted record
        ResultSet resultSet = statement.getGeneratedKeys();
        if(resultSet.next()) {
        	//Set the id field of the database to the model
        	cust2.setId1(resultSet.getInt(1));
        }
    } catch(SQLException e){
    	cust2 = null;
        System.out.println("Error Inserting record: " + e);
    }
	//Close the connection to the database - Very important!!!
	try {
        connection.close();
        connection = null;
    } catch(SQLException e) {
        System.out.println("Error closing connection: " + e);
    }
	//Return the customer object that was inserted with the id field set.
	return cust2;
}
}