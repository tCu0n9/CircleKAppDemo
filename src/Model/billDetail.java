package Model;

public class billDetail {
	private int id, quantity, price, cateID, totalPrice;
	private String prName;
	
	public billDetail(int id, int quantity, int price, int cateID, int totalPrice, String prName) {
		super();
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.cateID = cateID;
		this.totalPrice = totalPrice;
		this.prName = prName;
	}
	public billDetail() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getCateID() {
		return cateID;
	}
	public void setCateID(int cateID) {
		this.cateID = cateID;
	}
	public String getPrName() {
		return prName;
	}
	public void setPrName(String prName) {
		this.prName = prName;
	}
	public int getTotalPrice() {
		return price*quantity;
	}
	public void setTotalPrice(int price, int quanity) {
		this.totalPrice = quantity*price;
	}
	
}
