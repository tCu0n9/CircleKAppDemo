package Model;
public class Accounts {
	private int ID;
	private String pW;
	private boolean position;
	
	public Accounts() {
		
	}
	public Accounts(int ID, String pW, Boolean position) {
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


class EntryAccounts{
	public static void main(String[] args) {
		Accounts k = new Accounts(16112002,"TaCuong02",true);
		Accounts h = new Accounts(16112001,"TaCuong01",false);
		Accounts l = new Accounts(1234,"Tacuong16112002",true);
		Accounts i = new Accounts(1,"ta",false);
		Accounts m = new Accounts(1236723482,"tadsfsas",true);
		System.out.printf("%-20s%-20s%s\n","User Name","Password","Position");
		k.display();
		h.display();
		l.display();
		i.display();
		m.display();
	}
}
