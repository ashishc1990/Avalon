package components;

import java.util.HashMap;
import java.util.List;
import java.util.Queue;

import beans.ServerRequest;
import beans.ServerResponse;
import modules.Transceiver;

public class TransceiverThread implements Runnable {

	Queue<ServerRequest> requestQueue ;
	HashMap<Integer,List<ServerResponse>> responseHashTable;
	
	
	
	public TransceiverThread(Queue<ServerRequest> requestQueue,
			HashMap<Integer, List<ServerResponse>> responseHashTable) {
		super();
		this.requestQueue = requestQueue;
		this.responseHashTable = responseHashTable;
	}



	@Override
	public void run() {
		ServerRequest serviceRequest = null;
		ServerResponse serviceResponse ;
		Transceiver t = new Transceiver(); 
		
		while (!requestQueue.isEmpty()){
			serviceRequest = requestQueue.remove();
			serviceResponse = t.serviceRequest(serviceRequest);
			
			if (responseHashTable.containsKey(serviceRequest.getServiceId())){
				List<ServerResponse> tempList = responseHashTable.remove(serviceRequest.getServiceId());
				tempList.add(serviceResponse);
				responseHashTable.put(serviceRequest.getServiceId(), tempList);
			}
		}
		
	}

}
