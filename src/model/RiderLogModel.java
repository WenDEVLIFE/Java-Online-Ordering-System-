package model;

public class RiderLogModel {

	String riderId;
	
	String riderName;
	
	String riderStatus;
	
	public RiderLogModel(String riderId, String riderName, String riderStatus) {
		this.riderId = riderId;
		this.riderName = riderName;
		this.riderStatus = riderStatus;
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
	
	public String getRiderStatus() {
		return riderStatus;
	}
	
	public void setRiderStatus(String riderStatus) {
		this.riderStatus = riderStatus;
	}
	
	@Override
	public String toString() {
		return "RiderLogModel [riderId=" + riderId + ", riderName=" + riderName + ", riderStatus=" + riderStatus + "]";
	}
}
