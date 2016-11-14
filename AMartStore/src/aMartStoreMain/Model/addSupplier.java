package aMartStoreMain.Model;

import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

public class addSupplier {
	private Integer id; //Primary column of the Suppliertable
	private String name; //Name of the Supplier
	private String city; //City of the Supplier
	private Integer contactnumber; //Contact number of the Supplier
	private Integer id1;// id of the supplier for deletion

	
	
	//Empty Default constructor
	public addSupplier() {
	}
	//Fully parameterized constructor
	public addSupplier(Integer id, String name, String city,Integer contactnumber) {
		this.id = id;
		this.name = name;
		this.city = city;
		this.contactnumber = contactnumber;
		}
	
	// constructor for delete
	public addSupplier(Integer id1) {
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
