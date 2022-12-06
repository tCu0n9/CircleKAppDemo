package Model;

public class Category {
	private int categoryID;
	private String categoryName;
	
	public Category() {
		
	}
	public Category(int categoryID, String categoryName) {
		this.categoryID = categoryID;
		this.categoryName = categoryName;
	}
	
	public int getCategoryID() {
		return this.categoryID;
	}
	public String getCategoryName() {
		return this.categoryName;
	}
	
	public void setCategoryID(int categoryID) {
		this.categoryID = categoryID;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	
	public void display() {
		System.out.printf("%-20s%s\n",categoryID,categoryName);
	}
}
class EntryCategory{
	public static void main(String[] args) {
		Category k = new Category(10001,"Foods");
		Category h = new Category(10002,"Drinks");
		System.out.printf("%-20s%s\n","Category ID","Category Name");
		k.display();
		h.display();
	}
}
