package aMartStoreMain.Model;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
public class customerTable {
	


	private  final IntegerProperty  id2; //Primary column of the table
	private final StringProperty name2; //Name of the bank
	private final StringProperty city2; //Address of the bank
	private final IntegerProperty contactnumber2; //Address of t;
	   
	    /**
	     * Default constructor.
	     */
	    public customerTable() {
	        this(null, null,null,null);
	    }

	   
	    public customerTable(Integer id2, String name2, String city2,Integer contactnumber2) {
	   //     this.firstName = new SimpleStringProperty(firstName);
	    	this.id2 = new SimpleIntegerProperty(id2);
			this.name2 = new SimpleStringProperty(name2);
			this.city2 = new SimpleStringProperty(city2);
			this.contactnumber2 = new SimpleIntegerProperty(contactnumber2);
	    }

	    public int getId2() {
			return id2.get();
		}

		public void setId(int id2) {
			this.id2.set(id2);
		}

		public IntegerProperty id2Property() {
	        return id2;
	    }
		public String getName2() {
			return name2.get();
		}

		public void setName2(String name2) {
			this.name2.set(name2);
		}
		public StringProperty name2Property() {
	        return name2;
	    }

		public String getCity2() {
			return city2.get();
		}
		public void setCity2(String city2) {
			this.city2.set(city2);
		}
		public StringProperty city2Property() {
	        return city2;
	    }
		public int getContactnumber2() {
			return contactnumber2.get();
		}

		public void setContactnumber2(int contactnumber2) {
			this.contactnumber2.set(contactnumber2);
		}
	
		public IntegerProperty contactnumber2Property() {
	        return contactnumber2;
	    }

		
	   
	}
