package aMartStoreMain.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import aMartStoreMain.Model.Customer;
import aMartStoreMain.Model.customerTable;

public class customerTableDAO {
	private ObservableList<customerTable> customerData = FXCollections.observableArrayList();
	private Connection connection;
	//Database connection parameters
    private String url = "jdbc:mysql://www.papademas.net:3306/dbfp";
    private String username = "fpuser";
    private String password = "510";

    //Method to view a Customer model to database
	public customerTable cTable(customerTable custTab) {
		customerData = FXCollections.observableArrayList();
		//Get a connection
		try {
            connection = DriverManager.getConnection(url, username, password);
        } catch(SQLException e) {
            System.out.println("Error creating connection to database: " + e);
            System.exit(-1);
        }
		//Query to insert a record to the bank table
		String query = "select * from amachira_tblcustomer ;";
		//Use prepared statements to avoid SQL injection attacks
		try(PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)){
			//Set the parameters to the query
            
            //Execute the insert
            statement.executeQuery();
            //To get the primary key (id) of the newly inserted record
          //  ResultSet resultSet = statement.getGeneratedKeys();
            ResultSet rs = connection.createStatement().executeQuery(query);
            while (rs.next()) {
                String id2 = rs.getString( "id2" );                                
                String name2 = rs.getString( "name2" );
                String city2 = rs.getString( "city2" );
                String contactnumber2 = rs.getString( "contactnumber2" );
              
                ObservableList<String> row = FXCollections.observableArrayList();
                for(int i=1 ; i<=rs.getMetaData().getColumnCount(); i++){                    
                row.add(rs.getString(i));
                }                        
                 
                customerData.add((customerTable) row); 
                 
                System.out.println("Data "+ customerData );                                                    
         
        }} catch(SQLException e){
        	custTab = null;
            System.out.println("Error Inserting record: " + e);
        }
		//Close the connection to the database - Very important!!!
		try {
            connection.close();
            connection = null;
        } catch(SQLException e) {
            System.out.println("Error closing connection: " + e);
        }
		//Return the bank object that was inserted with the id field set.
		return custTab;
	}
}

