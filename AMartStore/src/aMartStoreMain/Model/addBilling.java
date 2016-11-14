package aMartStoreMain.Model;

import java.time.LocalDate;

public class addBilling {
	private Integer custid; // id the customer
	private Integer prodid; //id of the products
	private Integer orderid;// id of the customer order
	private String prodname; //Name of the products
	private Integer prodqty; //quantity of the products
	private Integer prodtamt; //amount for each products
	private LocalDate billdate;// date for the customer billing date
	private Integer custid1;// Primary Key for delete

	//Empty Default constructor
	public addBilling() {
	}
	//Fully parameterized constructor
	public addBilling(Integer custid,Integer prodid,Integer orderid, String prodname, Integer prodqty,Integer prodtamt, LocalDate billdate) {
		this.custid = custid;
		this.prodid = prodid;
		this.orderid = orderid;
		this.prodname = prodname;
		this.prodqty = prodqty;
		this.prodtamt = prodtamt;
		this.billdate = billdate; 
		}
	
	// for delete
	public addBilling(Integer custid1) {
		this.custid1 = custid1;
		}

	
	//Getter / Setter methods for each attribute of the class
	
	public Integer getcustId() {
		return custid;
	}

	public void setcustId(Integer custid) {
		this.custid = custid;
	}
	
	public void setprodId(Integer prodid) {
		this.prodid = prodid;
	}
	public Integer getprodId() {
		return prodid;
	}
	
	public Integer getorderId() {
		return orderid;
	}
	public void setorderId(Integer orderid) {
		this.orderid = orderid;
	}

	public String getprodName() {
		return prodname;
	}

	public void setprodName(String prodname) {
		this.prodname = prodname;
	}

	public Integer getprodQty() {
		return prodqty;
	}
	public void setprodQty(Integer prodqty) {
		this.prodqty = prodqty;
	}
	public Integer getprodAmt() {
		return prodtamt;
	}

	public void setprodAmt(Integer prodtamt) {
		this.prodtamt = prodtamt;
	}
	public LocalDate getBilldate() {
		return billdate;
	}

	public void setBilldate(LocalDate billdate) {
		this.billdate = billdate;
	}
	
	
	// delete 
	
	public void setcustId1(int custid1) {
		// TODO Auto-generated method stub
		this.custid1 = custid1;
		
	}
	public int getcustId1() {
		// TODO Auto-generated method stub
		return custid1;
	}
     
}
