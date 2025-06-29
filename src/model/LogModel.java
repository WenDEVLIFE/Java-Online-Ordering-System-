package model;

public class LogModel {

	
	String logId;
	
	String description;
	
	String timestamp;
	
	public LogModel(String logId, String description, String timestamp) {
		this.logId = logId;
		this.description = description;
		this.timestamp = timestamp;
	}
	
	public String getLogId() {
		return logId;
	}
	
	public void setLogId(String logId) {
		this.logId = logId;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getTimestamp() {
		return timestamp;
	}
	
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() {
		return "LogModel [logId=" + logId + ", description=" + description + ", timestamp=" + timestamp + "]";
	}
}
