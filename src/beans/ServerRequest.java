package beans;

public class ServerRequest {

	private int serviceId;
	private HostLocation hostLocation;
	private long timeOfRequest;

	public ServerRequest(int serviceId, HostLocation hostLocation,
			long timeOfRequest) {
		super();
		this.serviceId = serviceId;
		this.hostLocation = hostLocation;
		this.timeOfRequest = timeOfRequest;
	}
	
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public HostLocation getHostLocation() {
		return hostLocation;
	}
	public void setHostLocation(HostLocation hostLocation) {
		this.hostLocation = hostLocation;
	}
	public long getTimeOfRequest() {
		return timeOfRequest;
	}
	public void setTimeOfRequest(long timeOfRequest) {
		this.timeOfRequest = timeOfRequest;
	}
}
