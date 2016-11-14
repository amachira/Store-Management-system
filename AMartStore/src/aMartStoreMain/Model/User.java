package aMartStoreMain.Model;

public class User {

	public static String name1; // for displaying values after login
	public static Integer Id1; 
	
	//Empty Default constructor
		public User() {
		}
		
		// for displaying Admin username 
		public void User(String name1) {
		this.name1 = name1;
			}
	
		
	
	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		
		this.name1 = name1;
	}
	
	public Integer getId1() {
		return Id1;
	}

	public void setId1(Integer type2) {
		
		this.Id1 = type2;
	}
	
	
}
