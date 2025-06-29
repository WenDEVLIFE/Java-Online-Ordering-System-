// In file: Online_Food_Ordering_System/src/database/MenuService.java
package database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.MenuItemModel;

public class MenuService {
    private static volatile MenuService instance;

    public static MenuService getInstance() {
        if (instance == null) {
            synchronized (MenuService.class) {
                if (instance == null) {
                    instance = new MenuService();
                }
            }
        }
        return instance;
    }

    public boolean addMenuItem(String menuName, int price, String categoryId) {
        String sql = "INSERT INTO menu_items (menu_name, price, category_id) VALUES (?, ?, ?)";
        try (Connection conn = MYSQLInit.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, menuName);
            stmt.setInt(2, price);
            stmt.setString(3, categoryId);
            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public List<MenuItemModel> getAllMenuItems() {
				String sql = "SELECT * FROM menu_items";
				String getCategorySql = "SELECT category_name FROM category WHERE category_id = ?";
		try (Connection conn = MYSQLInit.getConnection();
			 PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			List<MenuItemModel> menuItems = new ArrayList<>();
			while (rs.next()) {
				String menuId = rs.getString("menu_id");
				String menuName = rs.getString("menu_name");
				String price = rs.getString("price");
				String categoryId = rs.getString("category_id");
				String categoryName = null;
				try (PreparedStatement categoryStmt = conn.prepareStatement(getCategorySql)) {
					categoryStmt.setString(1, categoryId);
					ResultSet categoryRs = categoryStmt.executeQuery();
					if (categoryRs.next()) {
						categoryName = categoryRs.getString("category_name");
					}

			
					MenuItemModel menuItem = new MenuItemModel(menuId, menuName, price, categoryName);
					menuItems.add(menuItem);
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return menuItems;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteMenuItem(String menuItemId) {
			String sql = "DELETE FROM menu_items WHERE menu_id = ?";
	try (Connection conn = MYSQLInit.getConnection();
		 PreparedStatement stmt = conn.prepareStatement(sql)) {
		stmt.setString(1, menuItemId);
		int rows = stmt.executeUpdate();
		return rows > 0;
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	}

	public boolean updateMenuItem(String menuItemId, String menuName, int price, String selectedCategoryId) {
		String sql = "UPDATE menu_items SET menu_name = ?, price = ?, category_id = ? WHERE menu_id = ?";
	try (Connection conn = MYSQLInit.getConnection();
		 PreparedStatement stmt = conn.prepareStatement(sql)) {
		stmt.setString(1, menuName);
		stmt.setInt(2, price);
		stmt.setString(3, selectedCategoryId);
		stmt.setString(4, menuItemId);
		int rows = stmt.executeUpdate();
		return rows > 0;
	} catch (SQLException e) {
		e.printStackTrace();
		return false;
	}
	
	}
}
