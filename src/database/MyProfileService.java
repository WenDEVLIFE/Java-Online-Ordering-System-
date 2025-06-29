package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MyProfileService {

	public static volatile MyProfileService instance;
	
	public static MyProfileService getInstance() {
		if (instance == null) {
			synchronized (MyProfileService.class) {
				if (instance == null) {
					instance = new MyProfileService();
				}
			}
		}
		return instance;
	}

	public String getFullName(int userId) {
		String sql = "SELECT fullname FROM customer_profile WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("fullname");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getEmail(int userId) {
		String sql = "SELECT email FROM customer_profile WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("email");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getAddress(int userId) {
		String sql = "SELECT delivery_address FROM customer_profile WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("delivery_address");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getContactNumber(int userId) {
		String sql = "SELECT contact_number FROM customer_profile WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, userId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("contact_number");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
