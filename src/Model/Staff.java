package Model;

import java.sql.Array;
import java.util.ArrayList;

public class Staff {
	private int ID;
	private String name;
	private String BoD;
	private String address;
	private String phoneNumb;
	
	
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Staff(int iD, String name, String BoD, String address, String phoneNumb) {
		super();
		ID = iD;
		this.name = name;
		this.BoD = BoD;
		this.address = address;
		this.phoneNumb = phoneNumb;
	}



	public int getID() {
		return ID;
	}



	public void setID(int iD) {
		ID = iD;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getBoD() {
		return BoD;
	}



	public void setBoD(String BoD) {
		this.BoD = BoD;
	}



	public String getAddress() {
		return address;
	}



	public void setAddress(String address) {
		this.address = address;
	}



	public String getPhoneNumb() {
		return phoneNumb;
	}



	public void setPhoneNumb(String phoneNumb) {
		this.phoneNumb = phoneNumb;
	}
	
}
class staffList{
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		ArrayList<Staff> list = new ArrayList<>();
	}
}


