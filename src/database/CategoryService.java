package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.CategoryModel;


public class CategoryService {

	
	private static CategoryService instance;
	
	public static CategoryService getInstance() {
		if (instance == null) {
			synchronized (CategoryService.class) {
				if (instance == null) {
					instance = new CategoryService();
				}
			}
		}
		return instance;
	}
	
	public boolean InsertCategory(String categoryName) {
		String sql = "INSERT INTO category (category_name) VALUES (?)";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, categoryName);
			stmt.executeUpdate();
			 
			return stmt.getUpdateCount() > 0; // Check if any rows were affected
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<CategoryModel> getAllCategories() {
		List<CategoryModel> categories = new ArrayList<>();
		String sql = "SELECT * FROM category";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql);
			 ResultSet rs = stmt.executeQuery()) {
			while (rs.next()) {
				String categoryId = rs.getString("category_id");
				String categoryName = rs.getString("category_name");
				CategoryModel category = new CategoryModel(categoryId, categoryName);
				categories.add(category);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return categories;
	}

	public boolean deleteCategory(String categoryId) {
		String sql = "DELETE FROM category WHERE category_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, categoryId);
			int rowsAffected = stmt.executeUpdate();
			
			if (rowsAffected > 0) {
				LogService.getInstance().addLog("Category deleted: " + categoryId);
				return true;
			} else {
				return false; // No rows were deleted
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
