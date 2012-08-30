package components;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import beans.ActionStep;
import beans.Contact;
import beans.HostLocation;
import beans.ServiceGroup;

public class ConfigurationModule {
	
	public static List<ServiceGroup> ReadFromConfigurationFile(String filename){
		
		File configurationFile = new File(filename);
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			
			Document dom = db.parse(configurationFile);
			
			return parseDocument(dom);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	private static List<ServiceGroup> parseDocument(Document dom) {
		
		List<ServiceGroup> serviceGroupList = new ArrayList<ServiceGroup>();
		
		//Root element
		Element rootElement = dom.getDocumentElement();
		
		//Get NodeList of Elements 
		NodeList nl = rootElement.getElementsByTagName("ServiceGroup");
		
		if (nl!=null && nl.getLength() > 0){
			for (int i = 0 ;i<nl.getLength(); i++){
				
				// Get the ServiceGroup Element
				Element e = (Element) nl.item(i);
				
				// Get the ServiceGroup Object
				ServiceGroup s = getServiceGroup(e);
				
				// add to List 
				serviceGroupList.add(s);
			}
		}
		
		return serviceGroupList;
	}

	/**
	 * @param e
	 * @return
	 */
	private static ServiceGroup getServiceGroup(Element e) {
		ServiceGroup s = new ServiceGroup();
		
		s.setServiceGroupName(getTextValue(e, "ServiceName"));
		s.setProtocol(getTextValue(e, "protocol"));
		s.setHeartbeat(getIntValue(e, "heartbeat"));
		s.setMaxHeartbeatFail(getIntValue(e, "maxHeartbeatFail"));
		s.setMaxRequestsAllowedInBuffer(getIntValue(e, "maxRequests"));
		s.setCpuUsageThreshold(getIntValue(e, "cpuUsageThreshold"));
		s.setCpuUsageFatal(getIntValue(e, "cpuUsageFatal"));
		s.setMemUsageThreshold(getIntValue(e, "memUsageThreshold"));
		s.setMemUsageFatal(getIntValue(e, "memUsageFatal"));
		s.setHeartbeatFrequency(getIntValue(e, "heartbeatFrequency"));
		s.setMaxHeartbeatFail(getIntValue(e, "maxHeartbeatFail"));
		
		s.setContact(getListOfContacts(e));
		s.setHostLocations(getListOfHostLocations(e));
		s.setActionSteps(getListOfActionSteps(e));
		return s;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}
	
	private static List<ActionStep> getListOfActionSteps(Element ele) {

		List<ActionStep> listOfActionSteps = new ArrayList<ActionStep>();
		NodeList nl = ele.getElementsByTagName("ActionStep");
		
		if (nl!=null && nl.getLength() > 0){
			for (int i = 0 ;i<nl.getLength(); i++){
				
				// Get the ServiceGroup Element
				Element e = (Element) nl.item(i);
				
				// Get the ServiceGroup Object
				ActionStep as = getActionStep(e);
				
				// add to List 
				listOfActionSteps.add(as);
			}
		}
		
		return listOfActionSteps;

	}


	private static List<HostLocation> getListOfHostLocations(Element ele){
		List<HostLocation> listOfHostLocations = new ArrayList<HostLocation>();
		NodeList nl = ele.getElementsByTagName("HostLocations");
		
		if (nl!=null && nl.getLength() > 0){
			for (int i = 0 ;i<nl.getLength(); i++){
				
				// Get the ServiceGroup Element
				Element e = (Element) nl.item(i);
				
				// Get the ServiceGroup Object
				HostLocation hl = getHostLocation(e);
				
				// add to List 
				listOfHostLocations.add(hl);
			}
		}
		
		return listOfHostLocations;
	}
	

	private static List<Contact> getListOfContacts (Element ele){
		List<Contact> listOfContacts = new ArrayList<Contact>();
		
		NodeList nl = ele.getElementsByTagName("Contacts");
		
		if (nl!=null && nl.getLength() > 0){
			for (int i = 0 ;i<nl.getLength(); i++){
				
				// Get the ServiceGroup Element
				Element e = (Element) nl.item(i);
				
				// Get the ServiceGroup Object
				Contact c = getContact(e);
				
				// add to List 
				listOfContacts.add(c);
			}
		}
		
		return listOfContacts;
	}

	private static ActionStep getActionStep(Element e) {
		ActionStep as = new ActionStep();
		
		as.setAction(getTextValue(e, "Action"));
		as.setLogging(getTextValue(e, "Logging"));
		as.setSendEmail(getTextValue(e, "SendEmail"));
		as.setServiceStatus(getTextValue(e, "ServiceStatus"));
		
		return as;
	}
	private static Contact getContact(Element e) {
		Contact c = new Contact();
		
		c.setEmail(getTextValue(e, "Email"));
		c.setAlias(getTextValue(e, "Alias"));
		c.setName(getTextValue(e, "Name"));
		
		return c;
	}

	private static HostLocation getHostLocation(Element e) {
		HostLocation hl = new HostLocation();
		
		hl.setHostName(getTextValue(e, "HostName"));
		hl.setIpAddress(getTextValue(e, "IpAddress"));
		hl.setPortNumber(getTextValue(e, "PortNumber"));
		
		return hl;
	}

	/**
	 * Calls getTextValue and returns a int value
	 */
	private static int getIntValue(Element ele, String tagName) {
		return Integer.parseInt(getTextValue(ele,tagName));
	}

}
