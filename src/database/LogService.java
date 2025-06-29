package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.LogModel;

public class LogService {

	
	private static volatile LogService instance;

	public static LogService getInstance() {
		if (instance == null) {
			synchronized (LogService.class) {
				if (instance == null) {
					instance = new LogService();
				}
			}
		}
		return instance;
	}

	public void addLog(String description) {
       		String insertLogSQL = "INSERT INTO logs (description, timestamp) VALUES (?, NOW())";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(insertLogSQL)) {
			pstmt.setString(1, description);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<LogModel> getAllLogs() {
		List<LogModel> logs = new ArrayList<>();
		String selectLogsSQL = "SELECT * FROM logs ORDER BY timestamp DESC";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement pstmt = conn.prepareStatement(selectLogsSQL);
			 ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				String logId = rs.getString("logId");
				String description = rs.getString("description");
				String timestamp = rs.getString("timestamp");
				LogModel log = new LogModel(logId, description, timestamp);
				logs.add(log);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logs;
	}
}
