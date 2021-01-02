package entity.db;
import java.sql.Connection;
import java.sql.DriverManager;
public class ConnectionDB {
	private static String DB_URL="jdbc:mysql://localhost:3306/rentalbike";
	private static String USER_NAME="root";
	private static String PASSWORD="";
	public static Connection ConnectionDB() {
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn=DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
			
		}catch(Exception ex) {
			System.out.print("fail");

			ex.printStackTrace();
		}
		return conn;
	
	}
	
	
}
