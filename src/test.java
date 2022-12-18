import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DBConnection.DBConnection;

public class test {
	public static void main(String[] args) throws SQLException {
		try {
			Connection cn = DBConnection.getConnection();
			String query2 = "select * from details where billId = 991663238";
			PreparedStatement ps1 = cn.prepareStatement(query2);
			ResultSet rs = ps1.executeQuery();
			while(rs.next()) {
				int ItemId = rs.getInt("id");
				int quantitySold = rs.getInt("quantity");
			
				String query3 = "EXec dbo.Quantity_cal @ItemID = "+ ItemId +", @quantitySold = "+ quantitySold  ;
				
				PreparedStatement ps = cn.prepareStatement(query3);
				ps.executeUpdate();
			}
		} catch (Exception e2) {
			// TODO: handle exception
		}
	}
}


