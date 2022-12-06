package Model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import DBConnection.DBConnection;

public class StaffDAO {
	Connection cn = null;
	PreparedStatement ps = null;
	public int add(Staff st) {
		try {
			String query = "insert into Employees(EmpID,EmpName,BoD,EmpAddress,PhoneNum) "
					+ "values (?,?,?,?,?)";
			  
			cn = DBConnection.getConnection();
			ps = cn.prepareStatement(query);
			ps.setInt(1, st.getID());
			ps.setString(2, st.getName());
			ps.setString(3, st.getBoD());
			ps.setString(4, st.getAddress());
			ps.setString(5, st.getPhoneNumb());
			if(ps.executeUpdate()>0) {
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.toString());
		}
		return -1;
	}
	
	
	public int update(Staff st) {
		try {
			String query = "UPDATE Employees\r\n"
					+ "SET EmpName = ?, BoD = ?, EmpAddress = ?, PhoneNum = ?\r\n"
					+ "WHERE EmpID = ?";
			  
			cn = DBConnection.getConnection();
			ps = cn.prepareStatement(query);
			ps.setInt(5, st.getID());
			ps.setString(1, st.getName());
			ps.setString(2, st.getBoD());
			ps.setString(3, st.getAddress());
			ps.setString(4, st.getPhoneNumb());
			if(ps.executeUpdate()>0) {
				System.out.println("Update completed!");
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.toString());
		}
		return -1;
	}
	
	public int delete(Staff st) {
		try {
			String query = "delete Employees where EmpId = ?";
			  
			cn = DBConnection.getConnection();
			ps = cn.prepareStatement(query);
			ps.setInt(1, st.getID());
			if(ps.executeUpdate()>0) {
				System.out.println("Delete completed!");
				return 1;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: "+e.toString());
		}
		return -1;
	}
	
	public Staff findStaff(String ID, String name){
		ResultSet rs = null;
		java.sql.Statement sttm = null;
		try {
			String sSQL = "select * from Employees where EmpID = "+ID+" or EmpName like N'%"+name+"%'";
			cn = DBConnection.getConnection();
			sttm = cn.createStatement();
			rs = sttm.executeQuery(sSQL);
			while(rs.next()) {
				Staff st = new Staff();
				st.setID(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setBoD(rs.getString(3));
				st.setAddress(rs.getString(4));
				st.setPhoneNumb(rs.getString(5));
				return st;
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR: "+e.toString());
		}
		finally {
			try {
				rs.close();	sttm.close(); cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return null;
	}
	
	public List<Staff> getAll(){
		List<Staff> staffs = new ArrayList<>();
		ResultSet rs = null;
		java.sql.Statement sttm = null;
		try {
			String sSQL = "select * from Employees";
			cn = DBConnection.getConnection();
			sttm = cn.createStatement();
			rs = sttm.executeQuery(sSQL);
			while(rs.next()) {
				Staff st = new Staff();
				st.setID(rs.getInt(1));
				st.setName(rs.getString(2));
				st.setBoD(rs.getString(3));
				st.setAddress(rs.getString(4));
				st.setPhoneNumb(rs.getString(5));
				staffs.add(st);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR: "+e.toString());
		}
		finally {
			try {
				rs.close();	sttm.close(); cn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return staffs;
	}
	
}
