package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.RiderModel;

public class RiderService {

	
	private static volatile RiderService instance;

	public static RiderService getInstance() {
		if (instance == null) {
			synchronized (RiderService.class) {
				if (instance == null) {
					instance = new RiderService();
				}
			}
		}
		return instance;
	}

	public boolean AddRider(String riderName, String phoneNumber) {
		String sql = "INSERT INTO rider (rider_name, rider_phone_number, rider_status) VALUES (?, ?, ?)";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, riderName);
			stmt.setString(2, phoneNumber);
			stmt.setString(3, "Available"); // Default status
			stmt.executeUpdate();
			
			return stmt.getUpdateCount() > 0; // Check if any rows were affected
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean RiderExists(String riderName) {
		String sql = "SELECT * FROM rider WHERE rider_name = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, riderName);
			ResultSet rs = stmt.executeQuery();
			return rs.next(); // If a record exists, return true
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<RiderModel> getAllRiders() {
		List<RiderModel> riders = new ArrayList<>();
		String sql = "SELECT * FROM rider";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String riderId = rs.getString("rider_id");
				String riderName = rs.getString("rider_name");
				String phoneNumber = rs.getString("rider_phone_number");
				String status = rs.getString("rider_status");
				RiderModel rider = new RiderModel(riderId, riderName, phoneNumber, status);
				riders.add(rider);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return riders;
	}

	public String getRiderNameById(String driverNamed) {
		String sql = "SELECT rider_name FROM rider WHERE rider_name = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, driverNamed);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("rider_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public String getRiderPhoneById(String driverNamed) {
		String sql = "SELECT rider_phone_number FROM rider WHERE rider_name = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, driverNamed);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getString("rider_phone_number");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int getRiderIdByName(String driverName) {
		String sql = "SELECT rider_id FROM rider WHERE rider_name = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, driverName);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt("rider_id");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1; // Return -1 if not found
	}

	public RiderModel getRiderById(int riderId) {
		String sql = "SELECT * FROM rider WHERE rider_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, riderId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				String id = rs.getString("rider_id");
				String name = rs.getString("rider_name");
				String phoneNumber = rs.getString("rider_phone_number");
				String status = rs.getString("rider_status");
				return new RiderModel(id, name, phoneNumber, status);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteRider(int riderId) {
		 String sql = "DELETE FROM rider WHERE rider_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, riderId);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public List<String> getReviewsByRiderId(int riderId) {
	    List<String> reviews = new ArrayList<>();
	    String sql = "SELECT feedback, rate, date FROM riderlogs WHERE rider_id = ?";
	    try (Connection conn = MYSQLInit.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, riderId);
	        ResultSet rs = stmt.executeQuery();
	        while (rs.next()) {
	            String feedback = rs.getString("feedback");
	            int rate = rs.getInt("rate");
	            String date = rs.getString("date");
	            reviews.add("Rate: " + rate + " | Feedback: " + feedback + " | Date: " + date);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return reviews;
	}
}
