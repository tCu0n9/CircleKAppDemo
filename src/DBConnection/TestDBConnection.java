package DBConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDBConnection {
	public static void main(String[] args) throws SQLException {
		Connection conn = DBConnection.getConnection();
		System.out.println(conn);
		DBConnection.closeConnection(conn);
	}
}