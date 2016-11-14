package aMartStoreMain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.event.ActionEvent;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.addSupplier;

public class addSupplierDAO {
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";

    //Method to save a supplier details to database
	public addSupplier create(addSupplier supp) {
		//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
	
		//Query to insert a record to the Supplier table
		String query = "INSERT INTO amachira_tblsuppliers (Supplier_ID,Supplier_name,Supplier_city,Supplier_contact) VALUES (?, ?, ?,?) ;";
		
		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
            statement.setInt(1,supp.getId());
            statement.setString(2, supp.getName());
            statement.setString(3, supp.getCity());
            statement.setInt(4, supp.getContactnumber());
            
		     //Execute the insert
            statement.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
            	//Set the id field of the database to the model
            	supp.setId(resultSet.getInt(1));
            }
        } catch(SQLException e){
        	supp = null;
            System.out.println("Error Inserting record: " + e);
        }
		
		
		
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		//Return the Supplier object that was inserted with the id field set.
		return supp;
	}
	
	// Method to delete a supplier details to database
	public addSupplier delete(addSupplier supp1) {
		//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
		//Query to delete a record from the supplier  table
		String query = "Delete from amachira_tblsuppliers where Supplier_id = ? ;";
		
		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
            statement.setInt(1, supp1.getId1());
            //Execute the insert
            statement.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
            	//Set the id field of the database to the model
            	supp1.setId1(resultSet.getInt(1));
            }
        } catch(SQLException e){
        	supp1 = null;
            System.out.println("Error Inserting record: " + e);
        }
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		//Return the supplier object that was inserted with the id field set.
		return supp1;
	}


	//Method to update supplier details to database

public addSupplier updates(addSupplier supp2) {
//Get a connection
try {
    connection = DriverManager.getConnection(url, username, password);
} catch(SQLException e) {
    System.out.println("Error creating connection to database: " + e);
    System.exit(-1);
}
//Query to update a record to the Supplier table
String query = "update amachira_tblsuppliers set Supplier_name = ?, Supplier_city=?, Supplier_contact =? where Supplier_id = ?;";

//Use prepared statements to avoid SQL injection attacks
try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
	//Set the parameters to the query
	
    statement.setString(1, supp2.getName());
    statement.setString(2, supp2.getCity());
    statement.setInt(3, supp2.getContactnumber());
    statement.setInt(4, supp2.getId());
    //Execute the insert
    statement.executeUpdate();
    //To get the primary key (id) of the newly inserted record
    ResultSet resultSet = statement.getGeneratedKeys();
    if(resultSet.next()) {
    	//Set the id field of the database to the model
    	supp2.setId1(resultSet.getInt(1));
    }
} catch(SQLException e){
	supp2 = null;
    System.out.println("Error Inserting record: " + e);
}
//Close the connection to the database - Very important!!!
try {
    connection.close();
    connection = null;
} catch(SQLException e) {
    System.out.println("Error closing connection: " + e);
}
//Return the Supplier object that was inserted with the id field set.
return supp2;
}

}
