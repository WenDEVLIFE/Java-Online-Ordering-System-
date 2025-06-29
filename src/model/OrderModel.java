package model;

public class OrderModel {

	String orderId;
	
	String MenuName;
	
	String MenuPrice;
	
	String totalAmount;
	
	
	String quantity;
	
	String orderStatus;
	
	String riderName;
	
	public OrderModel(String orderId, String menuName, String menuPrice, String totalAmount, String quantity,
			String orderStatus, String riderName) {
		this.orderId = orderId;
		MenuName = menuName;
		MenuPrice = menuPrice;
		this.totalAmount = totalAmount;
		this.quantity = quantity;
		this.orderStatus = orderStatus;
		this.riderName = riderName;
	}
	
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getMenuName() {
		return MenuName;
	}
	
	public void setMenuName(String menuName) {
		MenuName = menuName;
	}
	
	public String getMenuPrice() {
		return MenuPrice;
	}
	
	public void setMenuPrice(String menuPrice) {
		MenuPrice = menuPrice;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
	
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	public String getRiderName() {
		return riderName;
	}
	
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	
	@Override
	public String toString() {
		return "OrderModel [orderId=" + orderId + ", MenuName=" + MenuName + ", MenuPrice=" + MenuPrice
				+ ", totalAmount=" + totalAmount + ", quantity=" + quantity + ", orderStatus=" + orderStatus
				+ ", riderName=" + riderName + "]";
	}
	
}
