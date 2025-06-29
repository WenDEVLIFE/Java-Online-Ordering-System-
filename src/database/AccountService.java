package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import model.UserModel;

public class AccountService {

	
	private static volatile AccountService instance;
	
	public static AccountService getInstance() {
		if (instance == null) {
			synchronized (AccountService.class) {
				if (instance == null) {
					instance = new AccountService();
				}
			}
		}
		return instance;
	}

	public boolean registerUser(Map<String, String> userDetails) {
	    String insertUserSQL = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
	    String insertProfileSQL = "INSERT INTO customer_profile (user_id, fullname, email, contact_number, delivery_address) VALUES (?, ?, ?, ?, ?)";
	    Connection conn = null;
	    PreparedStatement userStmt = null;
	    PreparedStatement profileStmt = null;
	    ResultSet rs = null;

	    try {
	        conn = MYSQLInit.getConnection(); // Replace with your DB connection method
	        conn.setAutoCommit(false);

	        // Insert into users table
	        userStmt = conn.prepareStatement(insertUserSQL, Statement.RETURN_GENERATED_KEYS);
	        userStmt.setString(1, userDetails.get("username"));
	        userStmt.setString(2, userDetails.get("password"));
	        userStmt.setString(3, "customer");
	        int affectedRows = userStmt.executeUpdate();

	        if (affectedRows == 0) {
	            conn.rollback();
	            return false;
	        }

	        rs = userStmt.getGeneratedKeys();
	        if (rs.next()) {
	            int userId = rs.getInt(1);

	            // Insert into customer_profile table
	            profileStmt = conn.prepareStatement(insertProfileSQL);
	            profileStmt.setInt(1, userId);
	            profileStmt.setString(2, userDetails.get("fullName"));
	            profileStmt.setString(3, userDetails.get("email"));
	            profileStmt.setString(4, userDetails.get("contactNumber"));
	            profileStmt.setString(5, userDetails.get("deliveryAddress"));
	            profileStmt.executeUpdate();

	            LogService.getInstance().addLog("New user registered: " + userDetails.get("username"));
	            conn.commit();
	            return true;
	        } else {
	            conn.rollback();
	            return false;
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        try { if (conn != null) conn.rollback(); } catch (Exception ex) {}
	        return false;
	    } finally {
	        try { if (rs != null) rs.close(); } catch (Exception e) {}
	        try { if (userStmt != null) userStmt.close(); } catch (Exception e) {}
	        try { if (profileStmt != null) profileStmt.close(); } catch (Exception e) {}
	        try { if (conn != null) conn.close(); } catch (Exception e) {}
	    }
	}
	
	public boolean InsertAccount(String username, String password, String role) {
		String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, role);
	
			LogService.getInstance().addLog("New user registered: " + username);
			
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			
			return false;
		}
	}

	public List<UserModel> getAllUsers() {
		List<UserModel> users = new ArrayList<>();
		String sql = "SELECT * FROM users";
		try (Connection conn = MYSQLInit.getConnection();
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				String userId = rs.getString("user_id");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String role = rs.getString("role");
				UserModel user = new UserModel(userId, username, password, role);
				users.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	public boolean updateUser(String userId, String username, String password, String role) {
		String sql = "UPDATE users SET username = ?, password = ?, role = ? WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, role);
			pstmt.setString(4, userId);
			
			LogService.getInstance().addLog("User updated: " + username);
			
			return pstmt.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void deleteUser(String userId) {
		String sql = "DELETE FROM users WHERE user_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			
			LogService.getInstance().addLog("User deleted: " + userId);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
