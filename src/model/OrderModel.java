package model;

public class OrderModel {

	String orderId;
	
	String customerName;
	
	String MenuName;
	
	String MenuPrice;
	
	String totalAmount;
	
	
	String quantity;
	
	String orderStatus;
	
	String riderName;
	
	public OrderModel(String orderId, String customerName, String menuName, String menuPrice, String totalAmount,
			String quantity, String orderStatus, String riderName) {
		super();
		this.orderId = orderId;
		this.customerName = customerName;
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
	
	public String getCustomerName() {
		return customerName;
	}
	
	public String getMenuName() {
		return MenuName;
	}
	
	public String getMenuPrice() {
		return MenuPrice;
	}
	
	public String getTotalAmount() {
		return totalAmount;
	}
	
	public String getQuantity() {
		return quantity;
	}
	
	public String getOrderStatus() {
		return orderStatus;
	}
	
	public String getRiderName() {
		return riderName;
	}
	
	
	
	
}
