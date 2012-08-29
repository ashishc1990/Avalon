package beans;

public class HostLocation {

	private String hostName;
	private String ipAddress;
	private long portNumber;
	
	public String getHostName() {
		return hostName;
	}
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public long getPortNumber() {
		return portNumber;
	}
	public void setPortNumber(long portNumber) {
		this.portNumber = portNumber;
	}
}
