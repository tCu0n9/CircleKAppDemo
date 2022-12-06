package Model;
public class Discount {
	private int productID;
	private String discount;
	
	public Discount() {
		
	}
	public Discount(int productID, String discount) {
		this.productID = productID;
		this.discount = discount;
	}
	
	public int getProductID() {
		return this.productID;
	}
	public String getDiscount() {
		return this.discount;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setDiscount(String discount) {
		this.discount=discount;
	}
	
	public void display() {
		System.out.printf("%-20s%s\n", productID,discount);
	}
}

class EntryDiscount{
	public static void main(String[] args) {
		Discount k = new Discount(1231,"M2T1COCA");
		System.out.printf("%-20s%s\n","Product ID","Discount");
		k.display();
	}
}
