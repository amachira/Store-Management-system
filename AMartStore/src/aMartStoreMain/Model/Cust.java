package aMartStoreMain.Model;

public class Cust {
	public static String name2; // for displaying values after login
	public static Integer Id2;// for displaying the ID
	
	//Empty Default constructor
			public Cust() {
			}
			
	public void Cust(String name2) {
		this.name2 = name2;
			}
	
	// for displaying cust name
	
		public String getName2() {
			return name2;
		}

		public void setName2(String name2) {
			
			this.name2 = name2;
		}
		
		public Integer getId2() {
			return Id2;
		}

		public void setId2(Integer type2) {
			
			this.Id2 = type2;
		}
		
}
