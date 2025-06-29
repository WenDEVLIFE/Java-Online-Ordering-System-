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

	public static boolean updateProfile(String userId, String fullName1, String email1, String contact1,
			String address1) {
		String sql = "UPDATE customer_profile SET fullname = ?, email = ?, contact_number = ?, delivery_address = ? WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, fullName1);
			stmt.setString(2, email1);
			stmt.setString(3, contact1);
			stmt.setString(4, address1);
			stmt.setString(5, userId);
			int rows = stmt.executeUpdate();
			return rows > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
