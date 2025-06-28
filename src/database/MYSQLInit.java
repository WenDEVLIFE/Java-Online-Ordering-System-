package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MYSQLInit {

	private static volatile MYSQLInit instance;
	
	public static MYSQLInit getInstance() {
		if (instance == null) {
			synchronized (MYSQLInit.class) {
				if (instance == null) {
					instance = new MYSQLInit();
				}
			}
		}
		return instance;
	}
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/ordering_db", "root", "");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void main(String[] args) {
		MYSQLInit dbInit = MYSQLInit.getInstance();
		Connection connection = MYSQLInit.getConnection();
		if (connection != null) {
			System.out.println("Database connection established successfully.");
		} else {
			System.out.println("Failed to establish database connection.");
		}
	}
}
