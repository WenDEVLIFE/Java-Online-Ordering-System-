package model;

public class PaymentModel {

	String paymentId;
	
	String MenuName;
	
	String MenuPrice;
	
	String paymentMethod;
	
	String status;
	
	String customerName;
	
	public PaymentModel(String paymentId, String menuName, String menuPrice, String paymentMethod, String status,
			String customerName) {
		super();
		this.paymentId = paymentId;
		MenuName = menuName;
		MenuPrice = menuPrice;
		this.paymentMethod = paymentMethod;
		this.status = status;
		this.customerName = customerName;
	}
	
	public String getPaymentId() {
		return paymentId;
	}
	
	public String getMenuName() {
		return MenuName;
	}
	
	public String getMenuPrice() {
		return MenuPrice;
	}
	
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	public String getStatus() {
		return status;
	}
	
	public String getCustomerName() {
		return customerName;
	}
	
	
}
