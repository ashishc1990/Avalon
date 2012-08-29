package beans;

import java.util.List;

public class ServiceGroup {
	
	private int serviceId;
	private String serviceGroupName;
	private String protocol;
	
	private long cpuUsageThreshold;
	private long cpuUsageFatal;
	
	private long memUsageThreshold;
	private long memUsageFatal;
	
	private long heartbeat;
	private long heartbeatFrequency;
	private long maxHeartbeatFail;
	
	private long maxRequestsAllowedInBuffer;
	
	// Repeat Timer
	private long repeat;
	
	private boolean restart;
	
	private List<HostLocation> hostLocations;
	private List<ActionStep> actionSteps;
	private List<Contacts> contact;
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
	public long getCpuUsageThreshold() {
		return cpuUsageThreshold;
	}
	public void setCpuUsageThreshold(long cpuUsageThreshold) {
		this.cpuUsageThreshold = cpuUsageThreshold;
	}
	public long getCpuUsageFatal() {
		return cpuUsageFatal;
	}
	public void setCpuUsageFatal(long cpuUsageFatal) {
		this.cpuUsageFatal = cpuUsageFatal;
	}
	public long getMemUsageThreshold() {
		return memUsageThreshold;
	}
	public void setMemUsageThreshold(long memUsageThreshold) {
		this.memUsageThreshold = memUsageThreshold;
	}
	public long getMemUsageFatal() {
		return memUsageFatal;
	}
	public void setMemUsageFatal(long memUsageFatal) {
		this.memUsageFatal = memUsageFatal;
	}
	public long getHeartbeat() {
		return heartbeat;
	}
	public void setHeartbeat(long heartbeat) {
		this.heartbeat = heartbeat;
	}
	public long getHeartbeatFrequency() {
		return heartbeatFrequency;
	}
	public void setHeartbeatFrequency(long heartbeatFrequency) {
		this.heartbeatFrequency = heartbeatFrequency;
	}
	public long getMaxHeartbeatFail() {
		return maxHeartbeatFail;
	}
	public void setMaxHeartbeatFail(long maxHeartbeatFail) {
		this.maxHeartbeatFail = maxHeartbeatFail;
	}
	public long getMaxRequestsAllowedInBuffer() {
		return maxRequestsAllowedInBuffer;
	}
	public void setMaxRequestsAllowedInBuffer(long maxRequestsAllowedInBuffer) {
		this.maxRequestsAllowedInBuffer = maxRequestsAllowedInBuffer;
	}
	public long getRepeat() {
		return repeat;
	}
	public void setRepeat(long repeat) {
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
	public List<Contacts> getContact() {
		return contact;
	}
	public void setContact(List<Contacts> contact) {
		this.contact = contact;
	}
}
