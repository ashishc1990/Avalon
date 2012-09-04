package beans;

public class ServerResponse {

	private long timeOfRequest;
	private long timeOfResponse;
	private String status;
	
	public long getTimeOfRequest() {
		return timeOfRequest;
	}
	public void setTimeOfRequest(long timeOfRequest) {
		this.timeOfRequest = timeOfRequest;
	}
	public long getTimeOfResponse() {
		return timeOfResponse;
	}
	public void setTimeOfResponse(long timeOfResponse) {
		this.timeOfResponse = timeOfResponse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
