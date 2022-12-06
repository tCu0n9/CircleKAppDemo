package Model;

public class Supplier {
	protected int supID;
	private String supName;
	private String contactName;
	private String supAddress;
	private String supPhoneNum;
	
	public Supplier() {
		
	}
	public Supplier(int supID,String supName,String contactName, String supAddress, String supPhoneNum) {
		this.supID = supID;
		this.supName = supName;
		this.contactName = contactName;
		this.supAddress = supAddress;
		this.supPhoneNum = supPhoneNum;
	}
	
	public void setSupID(int supID) {
		this.supID = supID;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	public void setSupPhoneNum(String supPhoneNum) {
		this.supPhoneNum = supPhoneNum;
	}
	
	public int getSupID() {
		return this.supID;
	}
	public String getSupName() {
		return this.supName;
	}
	public String getcontactName() {
		return this.contactName;
	}
	public String getSupAddress() {
		return this.supAddress;
	}
	public String getSupPhoneNum() {
		return this.supPhoneNum;
	}
	
	public void display() {
		System.out.printf("%-20s%-20s%-20s%-20s%s\n",supID,supName,contactName,supAddress,supPhoneNum);
	}
}

class EntrySupplier {
	public static void main(String[] args) {
		Supplier k = new Supplier(16112002,"Cty TNHH Coca-Cola","Tạ Cường","Hà Nội","0773393670");
		Supplier l = new Supplier(1611200212,"Cty TNHH Lavie","Công Minh","Hà Nội","0773393671");
		Supplier h = new Supplier(16112002,"Cty TNHH Chin-Su","Thái Long","Sài Gòn","0773393672");
		System.out.printf("%-20s%-20s%-20s%-20s%s\n","Supplier ID","Supplier Name","Contact Name","Supplier Address","Supplier Phone Number");
		k.display();
		l.display();
		h.display();
	}
}
