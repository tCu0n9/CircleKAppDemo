package Model;
public class Orders {
	private int orderID;
	private int customerID;
	private int staffID;
	private String orderDate;
	
	public Orders() {
		
	}
	public Orders(int orderID,int customerID,int staffID, String orderDate) {
		this.orderID = orderID;
		this.customerID = customerID;
		this.staffID = staffID;
		this.orderDate = orderDate;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getCustomerID() {
		return customerID;
	}
	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}
	public int getStaffID() {
		return staffID;
	}
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	public String getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}
	
	public void display() {
		System.out.printf("%-20s%-20s%-20s%s\n",orderID,customerID,staffID,orderDate);
	}
}

class EntryOrders{
	public static void main(String[] args) {
		Orders k = new Orders(12312,5675,789689,"10/10/2022");
		System.out.printf("%-20s%-20s%-20s%s\n","Order I","Customer ID","Staff ID","Order Date");
		k.display();

	}
}
