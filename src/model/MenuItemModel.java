package model;

public class MenuItemModel {
	
	String menuItemId;
	
	String menuItemName;
	
	String menuprice;
	
	String categorName;
	
	
	public MenuItemModel(String menuItemId, String menuItemName, String menuprice, String categorName) {
		this.menuItemId = menuItemId;
		this.menuItemName = menuItemName;
		this.menuprice = menuprice;
		this.categorName = categorName;
	}
	
	public String getMenuItemId() {
		return menuItemId;
	}
	
	public void setMenuItemId(String menuItemId) {
		this.menuItemId = menuItemId;
	}
	
	public String getMenuItemName() {
		return menuItemName;
	}
	
	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}
	
	public String getMenuprice() {
		return menuprice;
	}
	
	public void setMenuprice(String menuprice) {
		this.menuprice = menuprice;
	}
	
	public String getCategorName() {
		return categorName;
	}
	
	public void setCategorName(String categorName) {
		this.categorName = categorName;
	}
	
	@Override
	public String toString() {
		return "MenuItemModel [menuItemId=" + menuItemId + ", menuItemName=" + menuItemName + ", menuprice=" + menuprice
				+ ", categorName=" + categorName + "]";
	}

}
