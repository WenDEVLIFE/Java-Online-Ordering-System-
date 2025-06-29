package model;

public class RiderModel {
	
	String riderId;
	
	String riderName;
	
	String riderPhoneNumber;
	
	String Status;
	
	public RiderModel(String riderId, String riderName, String riderPhoneNumber, String status) {
		super();
		this.riderId = riderId;
		this.riderName = riderName;
		this.riderPhoneNumber = riderPhoneNumber;
		Status = status;
	}
	
	public String getRiderId() {
		return riderId;
	}
	
	public String getRiderName() {
		return riderName;
	}
	
	
	public String getRiderPhoneNumber() {
		return riderPhoneNumber;
	}
		
	public String getStatus() {
		return Status;
	}
		
		
    @Override
    public String toString() {
        return riderName;
    }

}
