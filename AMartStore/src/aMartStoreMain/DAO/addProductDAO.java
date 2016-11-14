package aMartStoreMain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.addProduct;

public class addProductDAO {
	//Connection object
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";

    //Method to save a products details to database
	public addProduct create(addProduct prod) {
		//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
		//Query to insert a record to the product table
		String query = "INSERT INTO amachira_tblProducts (Prod_ID,Prod_name,ProdStock_qty,Prod_Amount,Supplier_ID,Category) VALUES (?, ?, ?, ?, ?,?) ;";
		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
            statement.setInt(1, prod.getId());
            statement.setString(2, prod.getName());
            statement.setInt(3, prod.getStockQty());
            statement.setInt(4, prod.getAmount());
            statement.setInt(5, prod.getSupplierId());
            statement.setString(6, prod.getCategory());
             
            //Execute the insert
            statement.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
            	//Set the id field of the database to the model
            	prod.setId(resultSet.getInt(1));
            }
        } catch(SQLException e){
        	prod = null;
            System.out.println("Error Inserting record: " + e);
        }
		
			String query1 = "insert into amachira_tblCustorder (Prod_name,Prod_qty,Prod_amount,Prod_id,Cust_id)  values(?,0,?,?,0) ;";
		
			try(PreparedStatement statement1 = connection.prepareStatement(query1, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
			statement1.setString(1, prod.getName());
			statement1.setInt(2, prod.getAmount());
			statement1.setInt(3, prod.getId());
            
                //Execute the insert
            statement1.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet1 = statement1.getGeneratedKeys();
            if(resultSet1.next()) {
            	//Set the id field of the database to the model
            	prod.setId(resultSet1.getInt(1));
            }
        } catch(SQLException e){
        	prod = null;
            System.out.println("Error Inserting record: " + e);
        }
        
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		//Return the product object that was inserted with the id field set.
		return prod;
	}
	
	// //Method to delete products details from the database
	public addProduct delete(addProduct prod1) {
		//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
		//Query to delete products from the product table
		String query = "Delete from amachira_tblProducts where prod_id = ? ;";
		
		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
            statement.setInt(1, prod1.getId1());
            //Execute the insert
            statement.executeUpdate();
            //To get the primary key (id) of the newly inserted record
            ResultSet resultSet = statement.getGeneratedKeys();
            if(resultSet.next()) {
            	//Set the id field of the database to the model
            	prod1.setId1(resultSet.getInt(1));
            }
        } catch(SQLException e){
        	prod1 = null;
            System.out.println("Error Inserting record: " + e);
        }
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		//Return the products object that was inserted with the id field set.
		return prod1;
	}


	// Method to update products details to database

public addProduct updatep(addProduct prod2) {
//Get a connection
try {
    connection = DriverManager.getConnection(url, username, password);
} catch(SQLException e) {
    System.out.println("Error creating connection to database: " + e);
    System.exit(-1);
}
//Query to update a record to the products table
String query = "update amachira_tblProducts set Prod_name = ?, ProdStock_qty=?, Prod_Amount =?, Supplier_id = ?, category= ? where Prod_id = ?;";

//Use prepared statements to avoid SQL injection attacks
try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
	//Set the parameters to the query
	
    statement.setString(1, prod2.getName());
    statement.setInt(2, prod2.getStockQty());
    statement.setInt(3, prod2.getAmount());
    statement.setInt(4, prod2.getSupplierId());
    statement.setString(5, prod2.getCategory());
    statement.setInt(6, prod2.getId());
    
    
    //Execute the insert
    statement.executeUpdate();
    //To get the primary key (id) of the newly inserted record
    ResultSet resultSet = statement.getGeneratedKeys();
    if(resultSet.next()) {
    	//Set the id field of the database to the model
    	prod2.setId1(resultSet.getInt(1));
    }
} catch(SQLException e){
	prod2 = null;
    System.out.println("Error Inserting record: " + e);
}
//Close the connection to the database - Very important!!!
try {
    connection.close();
    connection = null;
} catch(SQLException e) {
    System.out.println("Error closing connection: " + e);
}
//Return the products object that was inserted with the id field set.
return prod2;
}
}
