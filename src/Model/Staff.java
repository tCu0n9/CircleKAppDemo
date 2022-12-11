package Model;

import java.sql.Array;
import java.util.ArrayList;

public class Staff {
	private int ID;
	private String name;
	private String BoD;
	private String address;
	private String phoneNumb;
	private String userName, pwd, position;
	
	
	
	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Staff(int iD, String name, String boD, String address, String phoneNumb, String userName, String pwd,
			String position) {
		super();
		ID = iD;
		this.name = name;
		BoD = boD;
		this.address = address;
		this.phoneNumb = phoneNumb;
		this.userName = userName;
		this.pwd = pwd;
		this.position = position;
	}






	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPwd() {
		return pwd;
	}


	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getPosition() {
		return position;
	}


	public void setPosition(String position) {
		this.position = position;
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


