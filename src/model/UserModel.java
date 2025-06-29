package model;

public class UserModel {
	
	String userId;
	
	String userName;
	
	String password;
	
	String role;
	
	public UserModel(String userId, String userName, String password, String role) {
		this.userId = userId;
		this.userName = userName;
		this.password = password;
		this.role = role;
	}
	
	public String getUserId() {
		return userId;
	}
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
		
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	
	@Override
	public String toString() {
		return "UserModel [userId=" + userId + ", userName=" + userName + ", password=" + password + ", role=" + role + "]";
	}

}
