package components;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import modules.ContactModule;
import modules.LoggingModule;

import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;

import beans.ActionStep;
import beans.Contact;
import beans.HostLocation;
import beans.ServerRequest;
import beans.ServerResponse;
import beans.ServiceGroup;

public class HealthCheckThread implements Runnable {

	ServiceGroup s;
	Queue<ServerRequest> requestQueue ;
	HashMap<Integer,List<ServerResponse>> responseHashTable;
	
	public HealthCheckThread (ServiceGroup param, Queue<ServerRequest> requestQueue, HashMap<Integer,List<ServerResponse>> responseHashTable){
		this.s = param;
		this.requestQueue = requestQueue;
		this.responseHashTable = responseHashTable;
	}
	
	@Override
	public void run() {
		Sigar systemInfo = new Sigar();
		try{
			while(true){
				// Perform CPU and Memory checks 
				// INFO These checks only work if Service is hosted locally.
				try {
					if (systemInfo.getProcMem(s.getProcessId()).getSize() > s.getMemUsageThreshold()){
						// Log entry as WARNING and NOTICE
						LoggingModule.log(LoggingModule.WARNING, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Threshold Memory usage" );
						LoggingModule.log(LoggingModule.NOTICE, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Threshold Memory usage" );
						// Send email to contact personnel
						for (Contact c: s.getContact()){
							ContactModule.sendEmail(c.getEmail(), "info@avalon", "WARNING:LOG UPDATE", "Service Group ID " + s.getServiceId() + 
									" has exceeded Threshold memory usage.");
						}
					}
					if (systemInfo.getProcMem(s.getProcessId()).getSize() > s.getMemUsageFatal()){
						// Log entry as CRITICAL and EMERGENCY
						LoggingModule.log(LoggingModule.CRITICAL, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Fatal Memory usage" );
						LoggingModule.log(LoggingModule.EMERGENCY, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Fatal Memory usage" );
						// Restart service, and send email to contact personnel
						for (Contact c : s.getContact()){
							ContactModule.sendEmail(c.getEmail(), "info@avalon", "EMERGENCY:LOG UPDATE", "Service Group ID " + s.getServiceId() +
									" has exceeded Fatal Memory Usage." );
						}
					}
					
					if (systemInfo.getProcCpu(s.getProcessId()).getPercent() > s.getCpuUsageThreshold()){
						// Log entry as WARNING and NOTICE
						LoggingModule.log(LoggingModule.WARNING, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Threshold CPU usage" );
						LoggingModule.log(LoggingModule.NOTICE, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Threshold CPU usage" );
						// Send email to contact personnel
						for (Contact c: s.getContact()){
							ContactModule.sendEmail(c.getEmail(), "info@avalon", "WARNING:LOG UPDATE", "Service Group ID " + s.getServiceId() + 
									" has exceeded Threshold CPU usage.");
						}
					}
					if (systemInfo.getProcCpu(s.getProcessId()).getPercent() > s.getCpuUsageFatal()){
						// Log entry as CRITICAL and EMERGENCY
						LoggingModule.log(LoggingModule.CRITICAL, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Fatal CPU usage" );
						LoggingModule.log(LoggingModule.EMERGENCY, "Service Group " + s.getServiceGroupName() + ":" + s.getServiceId() + ":" + s.getProcessId()
								+" crossed Fatal CPU usage" );
						// Restart Service, and send email to contact personnel 
						for (Contact c : s.getContact()){
							ContactModule.sendEmail(c.getEmail(), "info@avalon", "EMERGENCY:LOG UPDATE", "Service Group ID " + s.getServiceId() +
									" has exceeded Fatal CPU Usage." );
						}
					}
				} catch (SigarException e) {
					System.out.println("The error is" + e.getMessage());
				}
				
				// For each host location, perform Heartbeat checks
				// write HTTP Request to Queue.
				for (HostLocation hl : s.getHostLocations()){
					for (int i = 0 ; i< s.getMaxHeartbeatFail(); i++){
						System.out.println("New Server Request added for Service Id" + s.getServiceId());
						requestQueue.add(new ServerRequest(s.getServiceId(),hl, System.currentTimeMillis()));
					}
				}
				Thread.sleep(s.getHeartbeatFrequency());
				
				// Check for HTTP responses from the Hashtable.
				int failcount = 0;
				List<ServerResponse> listOfServerResponse = responseHashTable.remove(s.getServiceId());
				for(ServerResponse sr : listOfServerResponse){
					// Perform checks on Heartbeat
					if ((sr.getTimeOfResponse() - sr.getTimeOfRequest())> (long) s.getHeartbeat())
						failcount++;
					
					if (failcount == s.getMaxHeartbeatFail()){
						// The service is declared dead - perform appropriate action
						actionServiceFailed();
					}
					
				}
			}
		}catch(InterruptedException e){
			System.out.println("Thread for ServiceGroup " + s.getServiceId() + " interrupted ! ");
		}
	}
	
	private void actionServiceFailed(){
		for (ActionStep as : s.getActionSteps()){
			if (as.getServiceStatus() == "dead"){
				if (as.getAction() == "restart"){
					// perform server restart here.
				}
			}
		}
	}

}
