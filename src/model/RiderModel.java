package model;

public class RiderModel {
	
	String riderId;
	
	String riderName;
	
	String Status;
	
	public RiderModel(String riderId, String riderName, String status) {
		this.riderId = riderId;
		this.riderName = riderName;
		Status = status;
	}
	
	public String getRiderId() {
		return riderId;
	}
	
	public void setRiderId(String riderId) {
		this.riderId = riderId;
	}
	
	public String getRiderName() {
		return riderName;
	}
	
	public void setRiderName(String riderName) {
		this.riderName = riderName;
	}
	
	public String getStatus() {
		return Status;
	}
	
	public void setStatus(String status) {
		Status = status;
	}
	
	@Override
	public String toString() {
		return "RiderModel [riderId=" + riderId + ", riderName=" + riderName + ", Status=" + Status + "]";
	}
	

}
