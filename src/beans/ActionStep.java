package beans;

public class ActionStep {

	private String serviceStatus;
	private String action;
	private String logging;
	private String sendEmail;
	
	public String getServiceStatus() {
		return serviceStatus;
	}
	public void setServiceStatus(String serviceStatus) {
		this.serviceStatus = serviceStatus;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getLogging() {
		return logging;
	}
	public void setLogging(String logging) {
		this.logging = logging;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	
}
