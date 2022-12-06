package Model;

public class OrderDetail {
	private int odDetailID;
	private int orderID;
	private int productID;
	private int quantity;

	public OrderDetail() {
		
	}
	public OrderDetail(int odDetailID,int orderID,int productID,int quantity) {
		this.odDetailID=odDetailID;
		this.orderID=orderID;
		this.productID=productID;
		this.quantity=quantity;
	}
	
	public int getOdDetailID() {
		return this.odDetailID;
	}
	public int getOrderID() {
		return this.orderID;
	}
	public int getProductID() {
		return this.productID;
	}
	public int getQuanity() {
		return this.quantity;
	}
	
	public void setOdDetailID(int odDetailID) {
		this.odDetailID = odDetailID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public void display() {
		System.out.printf("%-20s%-20s%-20s%s\n", odDetailID,orderID,productID,quantity);
	}
}

class EntryOrderDetail{
	public static void main(String[] args) {
		OrderDetail k = new OrderDetail(12324,123124,5346,56745);
		System.out.printf("%-20s%-20s%-20s%s\n","Detail ID","Order ID","Product ID","Quantity");
		k.display();
	}
}