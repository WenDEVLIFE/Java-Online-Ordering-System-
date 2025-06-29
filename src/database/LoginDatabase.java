package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JOptionPane;

import ui.AdminFrame;
import ui.CustomerFrame;
import ui.LoginFrame;
import ui.ResturantOwnerFrame;

public class LoginDatabase {

	
	private static volatile LoginDatabase instance;

	public static LoginDatabase getInstance() {
		if (instance == null) {
			synchronized (LoginDatabase.class) {
				if (instance == null) {
					instance = new LoginDatabase();
				}
			}
		}
		return instance;
	}

	public void Login(String username, String password, LoginFrame login) {
		
		String query = "SELECT * FROM users WHERE username = ? AND password = ?";
		try (Connection conn = MYSQLInit.getConnection();
				PreparedStatement stmt = conn.prepareStatement(query)) {
			
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				String role = rs.getString("role");
				int userId = rs.getInt("user_id");
				
				JOptionPane.showMessageDialog(login, "Login successful! Welcome " + username + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
				if ("admin".equals(role)) {
					// Assuming you have an AdminFrame class
					AdminFrame adminFrame = new AdminFrame();
					adminFrame.setVisible(true);
					adminFrame.setUserId(userId); // Assuming you have a method to set user ID
					login.dispose(); // Close the login frame
					
				} else if ("restaurant_admin".equals(role)) {
					// Assuming you have a RestaurantOwnerFrame class
					ResturantOwnerFrame restaurantOwnerFrame = new ResturantOwnerFrame();
					restaurantOwnerFrame.setVisible(true);
					restaurantOwnerFrame.setUserId(userId); // Assuming you have a method to set user ID
					login.dispose(); // Close the login frame
					
				} else if ("customer".equals(role)) {
					CustomerFrame customerFrame = new CustomerFrame();
					customerFrame.setVisible(true);
					customerFrame.setUserId(userId); // Assuming you have a method to set user ID
					customerFrame.LoadProfile(); // Load user profile data
					login.dispose(); // Close the login frame
			
				}
			} else {
				 JOptionPane.showMessageDialog(login, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(login, "An error occurred while trying to log in.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
