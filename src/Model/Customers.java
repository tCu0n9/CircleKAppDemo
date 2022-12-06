package Model;
public class Customers {
	private int customerID;
	private String name;
	private String address;
	private String phoneNumber;
	
	
	
	public Customers() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Customers(int customerID, String name, String address, String phoneNumber) {
		super();
		this.customerID = customerID;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}



	public int getCustomerID() {
		return customerID;
	}



	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	} 
	
	
	
}
