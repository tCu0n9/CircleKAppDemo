package Model;

public class Products {
	private int productID;
	private String productName;
	private int supplierID;
	private int categoryID;
	private String productMFG;
	private String productBB;
	private String unit;
	private Long price;
	
	public Products() {
		
	}
	public Products(int productID,String productName,int supplierID, int categoryID, String productMFG, String productBB,String unit,long price) {
		this.productID = productID;
		this.productName = productName;
		this.supplierID = supplierID;
		this.categoryID = categoryID;
		this.productMFG = productMFG;
		this.productBB = productBB;
		this.unit = unit;
		this.price = price;
	}
	
	public int getProductID() {
		return this.productID;
	}
	public String gettProductName() {
		return this.productName;
	}
	public int getSupplierID() {
		return this.supplierID;
	}
	public int getCategoryID() {
		return this.categoryID;
	}
	public String getProductMFG() {
		return this.productMFG;
	}
	public String getProductBB() {
		return this.productBB;
	}
	public String getUnit() {
		return this.unit;
	}
	public long getPrice() {
		return this.price;
	}
	
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setSupplierID(int supplierID) {
		this.supplierID = supplierID;
	}
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public void setProductMFG(String productMFG) {
		this.productMFG = productMFG;
	}
	public void setProductBB(String productBB) {
		this.productBB = productBB;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public void setPrice(long price) {
		this.price = price;
	}
	
}
