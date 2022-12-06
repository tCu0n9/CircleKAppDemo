package Model;

public class Account {
	private int ID;
	private String pW;
	private boolean position;
	
	public Account() {
		
	}
	public Account(int ID, String pW, Boolean position) {
		this.ID = ID;
		this.pW = pW;
		this.position = position;
	}
	public void setID(int ID) {
		this.ID = ID;
	}
	public void setpW(String pW) {
		this.pW = pW;
	}
	public void setPosition(boolean position) {
		this.position = position;
	}
	
	public int getID() {
		return this.ID;
	}
	public String getPW() {
		return this.pW;
	}
	public boolean getPosition() {
		return this.position;
	}
	
	/*public String toString() {
		return ID + "       " +pW + "       "+ position;
	}*/
	
	public void display() {
		System.out.printf("%-20s%-20s%s\n",ID,pW,position);
	}
}


class EntryAccount{
	public static void main(String[] args) {
		Account k = new Account(16112002,"TaCuong02",true);
		Account h = new Account(16112001,"TaCuong01",false);
		Account l = new Account(1234,"Tacuong16112002",true);
		Account i = new Account(1,"ta",false);
		Account m = new Account(1236723482,"tadsfsas",true);
		System.out.printf("%-20s%-20s%s\n","User Name","Password","Position");
		k.display();
		h.display();
		l.display();
		i.display();
		m.display();
	}
}