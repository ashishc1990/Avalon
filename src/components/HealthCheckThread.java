package components;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import beans.ActionStep;
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
		try{
			while(true){
				// Perform CPU and Memory checks 
				// INFO These checks only work if Service is hosted locally.
				
				
				// For each host location, perform Heartbeat checks
				// write HTTP Request to Queue.
				for (HostLocation hl : s.getHostLocations()){
					for (int i = 0 ; i< s.getMaxHeartbeatFail(); i++){
						System.out.println("New Server Request added for Service Id" + s.getServiceId());
						requestQueue.add(new ServerRequest(s.getServiceId(),hl, System.currentTimeMillis()));
					}
				}
				Thread.sleep(500);
				
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
