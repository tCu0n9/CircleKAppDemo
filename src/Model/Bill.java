package Model;

public class Bill {
	
	private int productID;
	private int quantity;
	private long price;
	private String discount;
	
	
	public Bill() {
		super();
		
	}
	

	public Bill(int productID, int quantity, long price, String discount) {
		super();
		this.productID = productID;
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	
	

	public int getProductID() {
		return productID;
	}


	public void setProductID(int productID) {
		this.productID = productID;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public long getPrice() {
		return price;
	}


	public void setPrice(long price) {
		this.price = price;
	}


	public String getDiscount() {
		return discount;
	}


	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public long getTotal() {
		return quantity*price;
	}
	
	public void display() {
		System.out.printf("%-20s%-20s%-20s%-20s%s\n",productID,quantity,price,discount,getTotal());
	}
}

class EntryBILL{
	public static void main(String[] args) {
		Bill k = new Bill(1234,4,1000000,"M2T1COCA");
		System.out.printf("%-20s%-20s%-20s%-20s%s\n","Product ID","Quantity","Price","Discount","Total");
		k.display();
	}
}