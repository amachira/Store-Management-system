package aMartStoreMain.Model;

public class CustomerViewModel {
	private Integer prod_id1; //product id 
	private Integer qty; //quantity of the products
	private Integer cust_id;//customers id
	private String prod_name; // name of the products
	private Integer prod_amt;// amount of the products
	private Integer prod_qty;// quantity of the products
	private Integer prod_id;// id of the products
	
	
	//Empty Default constructor
	public CustomerViewModel () {
	}
	//Fully parameterized constructor
	public CustomerViewModel(Integer qty,Integer prod_id1,Integer cust_id,String prod_name, Integer prod_amt) {
		this.prod_name= prod_name;
	//	this.prod_qty =prod_qty;
		this.prod_amt = prod_amt;
		this.qty = qty;
		this.prod_id1 = prod_id1;
		this.cust_id= cust_id;
			
	}
	//Getter / Setter methods for each attribute of the class

		public Integer getProdQty() {
			return qty;
		}
		public void setProdQty(Integer qty) {
			this.qty = qty;
		}
		
		
		public Integer getProd_id() {
			System.out.println("class" +prod_id1);
			return prod_id1;
		}

		public void setProd_id(Integer prod_id1) {
			this.prod_id1 = prod_id1;
			System.out.println(prod_id1);
		}
		public Integer getCust_id() {
			return cust_id;
		}

		public void setCust_id(Integer cust_id) {
			this.cust_id = cust_id;
		}
		
	
		public String getProd_name() {
			return prod_name;
		}
		public void setProd_name(String prod_name) {
			
			// TODO Auto-generated method stub
			this.prod_name = prod_name;
		}
	
		public Integer getAmount() {
			//System.out.println("class" +prod_amt);
			return prod_amt;
		}
		public void setAmount(Integer prod_amt) {
			// TODO Auto-generated method stub
			System.out.println("Amt" +prod_amt);
			this.prod_amt = prod_amt;
		}

		
		
}

