package aMartStoreMain.Model;

public class Customer {
	private Integer id; //Primary column of the table
	private String name; //Name of the Customer
	private String city; //City of the Customer
	private Integer contactnumber; //Contact number of the Customer
	private Integer id1;// Customer id for deletion
	private String usert;//user type of the customer admin or customer
	
	//Empty Default constructor
	public Customer() {
	}
	//Fully parameterized constructor
	public Customer(Integer id, String name, String city,Integer contactnumber, String usert) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.contactnumber = contactnumber;
		this.usert = usert;
		
	}
	
	// constructor for delete
	public Customer(Integer id1) {
		this.id1 = id1;
		}

	
	//Getter / Setter methods for each attribute of the class
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getUserType() {
		return usert;
	}
	public void setUserType(String usert) {
		this.usert = usert;
	}
	
	public Integer getContactnumber() {
		return contactnumber;
	}

	public void setContactnumber(Integer contactnumber) {
		this.contactnumber = contactnumber;
	}
	
	// delete 
	
	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}
	public String setUserData(String string) {
		// TODO Auto-generated method stub
		return null;
	}
	
     
}
