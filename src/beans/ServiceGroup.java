package beans;

import java.util.List;

public class ServiceGroup {
	
	private int serviceId;
	private String serviceGroupName;
	private String protocol;
	
	private int cpuUsageThreshold;
	private int cpuUsageFatal;
	
	private int memUsageThreshold;
	private int memUsageFatal;
	
	private int heartbeat;
	private int heartbeatFrequency;
	private int maxHeartbeatFail;
	
	private int maxRequestsAllowedInBuffer;
	
	// Repeat Timer
	private int repeat;
	
	private boolean restart;
	
	private List<HostLocation> hostLocations;
	private List<ActionStep> actionSteps;
	private List<Contact> contact;
	public int getServiceId() {
		return serviceId;
	}
	public void setServiceId(int serviceId) {
		this.serviceId = serviceId;
	}
	public String getServiceGroupName() {
		return serviceGroupName;
	}
	public void setServiceGroupName(String serviceGroupName) {
		this.serviceGroupName = serviceGroupName;
	}
	public String getProtocol() {
		return protocol;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
	public int getCpuUsageThreshold() {
		return cpuUsageThreshold;
	}
	public void setCpuUsageThreshold(int cpuUsageThreshold) {
		this.cpuUsageThreshold = cpuUsageThreshold;
	}
	public int getCpuUsageFatal() {
		return cpuUsageFatal;
	}
	public void setCpuUsageFatal(int cpuUsageFatal) {
		this.cpuUsageFatal = cpuUsageFatal;
	}
	public int getMemUsageThreshold() {
		return memUsageThreshold;
	}
	public void setMemUsageThreshold(int memUsageThreshold) {
		this.memUsageThreshold = memUsageThreshold;
	}
	public int getMemUsageFatal() {
		return memUsageFatal;
	}
	public void setMemUsageFatal(int memUsageFatal) {
		this.memUsageFatal = memUsageFatal;
	}
	public int getHeartbeat() {
		return heartbeat;
	}
	public void setHeartbeat(int heartbeat) {
		this.heartbeat = heartbeat;
	}
	public int getHeartbeatFrequency() {
		return heartbeatFrequency;
	}
	public void setHeartbeatFrequency(int heartbeatFrequency) {
		this.heartbeatFrequency = heartbeatFrequency;
	}
	public int getMaxHeartbeatFail() {
		return maxHeartbeatFail;
	}
	public void setMaxHeartbeatFail(int maxHeartbeatFail) {
		this.maxHeartbeatFail = maxHeartbeatFail;
	}
	public int getMaxRequestsAllowedInBuffer() {
		return maxRequestsAllowedInBuffer;
	}
	public void setMaxRequestsAllowedInBuffer(int maxRequestsAllowedInBuffer) {
		this.maxRequestsAllowedInBuffer = maxRequestsAllowedInBuffer;
	}
	public int getRepeat() {
		return repeat;
	}
	public void setRepeat(int repeat) {
		this.repeat = repeat;
	}
	public boolean isRestart() {
		return restart;
	}
	public void setRestart(boolean restart) {
		this.restart = restart;
	}
	public List<HostLocation> getHostLocations() {
		return hostLocations;
	}
	public void setHostLocations(List<HostLocation> hostLocations) {
		this.hostLocations = hostLocations;
	}
	public List<ActionStep> getActionSteps() {
		return actionSteps;
	}
	public void setActionSteps(List<ActionStep> actionSteps) {
		this.actionSteps = actionSteps;
	}
	public List<Contact> getContact() {
		return contact;
	}
	public void setContact(List<Contact> contact) {
		this.contact = contact;
	}
	
}
