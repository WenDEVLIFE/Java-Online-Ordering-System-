package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderModel;
import model.PaymentModel;

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

	public boolean confirmOrder(String orderId, String selectedRiderId, String paymentMethod, int userId) {
	    String updateOrderSql = "UPDATE orders_items SET status = 'Confirmed' WHERE order_id = ?";
	    String insertPaymentSql = "INSERT INTO payments (order_id, payment_method, status, user_id, rider_id) VALUES (?, ?, ?, ?, ?)";
	    Connection conn = null;
	    try {
	        conn = MYSQLInit.getConnection();
	        conn.setAutoCommit(false);

	        try (PreparedStatement updateStmt = conn.prepareStatement(updateOrderSql);
	             PreparedStatement insertStmt = conn.prepareStatement(insertPaymentSql)) {

	            // Update order
	            updateStmt.setString(1, orderId);
	            int orderRows = updateStmt.executeUpdate();

	            // Insert payment
	            insertStmt.setString(1, orderId);
	            insertStmt.setString(2, paymentMethod);
	            insertStmt.setString(3, "Paid"); // or "Pending" as needed
	            insertStmt.setInt(4, userId);
	            insertStmt.setString(5, selectedRiderId);
	            int paymentRows = insertStmt.executeUpdate();

	            if (orderRows > 0 && paymentRows > 0) {
	                conn.commit();
	                return true;
	            } else {
	                conn.rollback();
	                return false;
	            }
	        }
	    } catch (SQLException e) {
	        if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
	        e.printStackTrace();
	        return false;
	    } finally {
	        if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
	    }
	}

	public List<PaymentModel> getPaymentHistoryByUserId(int userId) {
	 		String sql = "SELECT p.payment_id, m.menu_name, m.price AS menu_price, p.payment_method, p.status, cp.fullname " +
				"FROM payments p " +
				"JOIN orders_items oi ON p.order_id = oi.order_id " +
				"JOIN menu_items m ON oi.menu_id = m.menu_id " +
				"JOIN users u ON oi.user_id = u.user_id " +
				"JOIN customer_profile cp ON u.user_id = cp.user_id " +
				"WHERE u.user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			List<PaymentModel> payments = new ArrayList<>();
			while (rs.next()) {
				String paymentId = rs.getString("payment_id");
				String menuName = rs.getString("menu_name");
				String menuPrice = rs.getString("menu_price");
				String paymentMethod = rs.getString("payment_method");
				String status = rs.getString("status");
				String customerName = rs.getString("fullname");
				PaymentModel payment = new PaymentModel(paymentId, menuName, menuPrice, paymentMethod, status, customerName);
				payments.add(payment);
			}
			return payments;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean setOrderAsReceived(String orderId, int rating, String feedback, String riderId, String userId) {
		String sql = "UPDATE orders_items SET status = 'Received' WHERE order_id = ?";
		String insertReviewSql = "INSERT INTO riderlogs (user_id, rate, date, feedback, rider_id) VALUES (?, ?, ?, ?, ?)";
		Connection conn = null;
		try {
			conn = MYSQLInit.getConnection();
			conn.setAutoCommit(false);

			try (PreparedStatement updateStmt = conn.prepareStatement(sql);
				 PreparedStatement insertStmt = conn.prepareStatement(insertReviewSql)) {

				// Update order status
				updateStmt.setString(1, orderId);
				int orderRows = updateStmt.executeUpdate();

				// Insert review
				insertStmt.setString(1, userId);
				insertStmt.setInt(2, rating);
				insertStmt.setString(3, java.time.LocalDate.now().toString()); // Use current date
				insertStmt.setString(4, feedback);
				insertStmt.setString(5, riderId); // Replace with actual rider ID if available
				int reviewRows = insertStmt.executeUpdate();

				if (orderRows > 0 && reviewRows > 0) {
					conn.commit();
					return true;
				} else {
					conn.rollback();
					return false;
				}
			}
		} catch (SQLException e) {
			if (conn != null) try { conn.rollback(); } catch (SQLException ex) { ex.printStackTrace(); }
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) try { conn.setAutoCommit(true); conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}


	




}
