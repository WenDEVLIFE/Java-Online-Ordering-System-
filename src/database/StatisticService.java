package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class StatisticService {

	
	private static volatile StatisticService instance;
	
	
	public static StatisticService getInstance() {
		if (instance == null) {
			synchronized (StatisticService.class) {
				if (instance == null) {
					instance = new StatisticService();
				}
			}
		}
		return instance;
	}
	
	
	public  int getAdminCount() {
		String sql = "SELECT COUNT(*) FROM users WHERE role = 'Admin'";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getRestaurantAdminCount() {
		String sql = "SELECT COUNT(*) FROM users WHERE role = 'Restaurant-Admin'";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getCustomerCount() {
		String sql = "SELECT COUNT(*) FROM users WHERE role = 'Customer'";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getLogCount() {
		String sql = "SELECT COUNT(*) FROM logs";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getOrderCount() {
		String sql = "SELECT COUNT(*) FROM orders_items";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getMenuItemCount() {
		String sql = "SELECT COUNT(*) FROM menu_items";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getCategoryCount() {
		String sql = "SELECT COUNT(*) FROM category";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getTotalRiders() {
		String sql = "SELECT COUNT(*) FROM rider";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getAvailaleRiders() {
		String sql = "SELECT COUNT(*) FROM rider WHERE rider_status = 'Available'";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public int getPendingOrders() {
		String sql = "SELECT COUNT(*) FROM orders_items WHERE status = 'Pending'";
		int count = 0;
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return count;
	}
}
