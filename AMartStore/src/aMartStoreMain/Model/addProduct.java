package aMartStoreMain.Model;

public class addProduct {
	private Integer id; //Primary column of the product table
	private String name; //Name of the product
	private Integer qty; //quantity of the product
	private Integer amount; //amount of the product
	private Integer supplierid;// id of the supplier
	private Integer id1;// id for delete
	private String category;// category of the product

	//Empty Default constructor
	public addProduct() {
	}
	//Fully parameterized constructor
	public addProduct(Integer id, String name, Integer qty,Integer amount,Integer supplierid,String category) {
		this.id = id;
		this.name = name;
		this.qty = qty;
		this.amount = amount;
		this.supplierid= supplierid;
		this.category= category;
			
	}
	
	// for delete
	public addProduct(Integer id1) {
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

	public Integer getStockQty() {
		return qty;
	}
	public void setStockQty(Integer qty) {
		this.qty = qty;
	}
	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public Integer getSupplierId() {
		return supplierid;
	}

	public void setSupplierId(Integer supplierid) {
		this.supplierid = supplierid;
	}
	
	public String getCategory() {
		
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	// delete 
	
	public Integer getId1() {
		return id1;
	}

	public void setId1(Integer id1) {
		this.id1 = id1;
	}
     
	
}
