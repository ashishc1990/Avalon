package beans;

public class ServerResponse {

	private long timeOfRequest;
	private long timeOfResponse;
	private int status;
	
	public ServerResponse(long timeOfRequest, long timeOfResponse, int status) {
		super();
		this.timeOfRequest = timeOfRequest;
		this.timeOfResponse = timeOfResponse;
		this.status = status;
	}

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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
}
