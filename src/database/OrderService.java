package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderService {

	
	private static volatile OrderService instance;
	
	public static OrderService getInstance() {
		if (instance == null) {
			synchronized (OrderService.class) {
				if (instance == null) {
					instance = new OrderService();
				}
			}
		}
		return instance;
	}

	public boolean addOrder(int userId, String menuItemId, int value, int totalPrice) {
		String sql = "INSERT INTO orders_items (user_id, menu_id, quantity, total_amount, status) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			stmt.setString(2, menuItemId);
			stmt.setInt(3, value);
			stmt.setInt(4, totalPrice);
			stmt.setString(5, "Pending"); 
			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
