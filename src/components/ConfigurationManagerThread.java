package components;

import java.util.ArrayList;
import java.util.List;

import modules.ConfigurationManagerModule;
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
		
		// TODO Spawn new health monitoring thread for each ServiceGroup
		for (int i = 0 ; i < ListOfServiceGroups.size(); i++){
			
		}
		
		try{
			while (true){
				Thread.sleep(10000);
				NewListOfServiceGroups = ConfigurationManagerModule.ReadFromConfigurationFile("services.cfg");
				
				if (NewListOfServiceGroups.size() != ListOfServiceGroups.size()){
					// TODO If there are new Service Groups added to the configuration files
					// Add new Threads for each service group.
					for (int i = ListOfServiceGroups.size(); i< NewListOfServiceGroups.size() ; i++){
						
					}
				}
			}
		}catch (InterruptedException e){
			System.out.println("Configuration Manager Thread interrupted ");
		}
		
	}

}
