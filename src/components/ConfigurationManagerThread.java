package components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import modules.ConfigurationManagerModule;
import beans.ServerRequest;
import beans.ServerResponse;
import beans.ServiceGroup;


/**
 * @author Ashish
 * 
 * The Thread that reads the Configuration file and creates
 * Health montioring threads for each service group.
 *
 */
public class ConfigurationManagerThread implements Runnable {

	@Override
	public void run() {

		System.out.println("In Configuration Manager thread");
		List<ServiceGroup> ListOfServiceGroups = ConfigurationManagerModule.ReadFromConfigurationFile("services.cfg");
		List<ServiceGroup> NewListOfServiceGroups = new ArrayList<ServiceGroup>();

		Queue<ServerRequest> requestQueue = new LinkedList<ServerRequest>();
		HashMap<Integer,List<ServerResponse>> responseHashTable = new HashMap<Integer, List<ServerResponse>>();

		// Initialize a Transceiver thread.
		new TransceiverThread(requestQueue, responseHashTable);
		// Spawn new health monitoring thread for each ServiceGroup
		for (int i = 0 ; i < ListOfServiceGroups.size(); i++){
			new HealthCheckThread(ListOfServiceGroups.get(i), requestQueue, responseHashTable);
		}

		try{
			while (true){
				Thread.sleep(10000);
				NewListOfServiceGroups = ConfigurationManagerModule.ReadFromConfigurationFile("services.cfg");

				if (NewListOfServiceGroups.size() != ListOfServiceGroups.size()){
					// If there are new Service Groups added to the configuration files
					// Add new Threads for each service group.
					for (int i = ListOfServiceGroups.size(); i< NewListOfServiceGroups.size() ; i++){
						new HealthCheckThread(NewListOfServiceGroups.get(i), requestQueue, responseHashTable);
					}
				}
			}
		}catch (InterruptedException e){
			System.out.println("Configuration Manager Thread interrupted ");
		}
	}

}
