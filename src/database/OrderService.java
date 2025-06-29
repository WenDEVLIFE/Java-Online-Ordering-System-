package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderModel;

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

	/**
	 * Retrieves all orders placed by a specific user.
	 *
	 * @param userId The ID of the user whose orders are to be retrieved.
	 * @return A list of OrderModel objects representing the user's orders, or null if an error occurs.
	 */

	public List<OrderModel> getOrdersByUserId(int userId) {
	    String sql = "SELECT oi.order_id, cp.fullname, m.menu_name, m.price, oi.quantity, oi.total_amount, oi.status AS order_status, " +
	                 "r.rider_name, p.payment_method, p.status AS payment_status " +
	                 "FROM orders_items oi " +
	                 "JOIN menu_items m ON oi.menu_id = m.menu_id " +
	                 "JOIN users u ON oi.user_id = u.user_id " +
	                 "JOIN customer_profile cp ON u.user_id = cp.user_id " +
	                 "LEFT JOIN payments p ON oi.order_id = p.order_id " +
	                 "LEFT JOIN rider r ON p.rider_id = r.rider_id " +
	                 "WHERE oi.user_id = ?";
	    try (Connection conn = MYSQLInit.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, userId);
	        ResultSet rs = stmt.executeQuery();
	        List<OrderModel> orders = new ArrayList<>();
	        while (rs.next()) {
	            String orderId = rs.getString("order_id");
	            String customerName = rs.getString("fullname");
	            String menuName = rs.getString("menu_name");
	            String menuPrice = rs.getString("price");
	            String totalAmount = rs.getString("total_amount");
	            String quantity = rs.getString("quantity");
	            String orderStatus = rs.getString("order_status");
	            String riderName = rs.getString("rider_name") != null ? rs.getString("rider_name") : "Not Assigned";
	            String paymentMethod = rs.getString("payment_method") != null ? rs.getString("payment_method") : "Not Paid";
	            String paymentStatus = rs.getString("payment_status") != null ? rs.getString("payment_status") : "Not Paid";
	            OrderModel order = new OrderModel(orderId, customerName, menuName, menuPrice, totalAmount, quantity, orderStatus, riderName);
	            orders.add(order);
	        }
	        return orders;
	    } catch (SQLException e) {
	        e.printStackTrace();
	        return null;
	    }
	}

	




}
