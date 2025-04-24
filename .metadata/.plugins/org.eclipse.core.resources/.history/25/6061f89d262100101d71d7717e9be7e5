package Devices;

import java.util.Map;
import Network.Topology;

// Class for representing computer
public class Computer extends Device{
	
	private final int totalPorts = 1;
	
	public Computer(String name, String macAddress) {
		super(name,macAddress);
	}
	
	@Override
	public int getTotalPorts()
	{
		return totalPorts;
	}
	
	public void sendMessage(String destinationIP, String message, Topology topology) {
		String destinationMac = lookupMac(destinationIP);
		
		System.out.println("Destination IP: " + destinationIP);
	    if (destinationMac == null) 
	    {
	        System.out.println(name + " doesn’t know MAC for " + destinationIP + ". Sending ARP request...");
	        arpRequest(destinationIP, topology);
	        destinationMac = lookupMac(destinationIP);
	        
	        if (destinationMac == null) 
	        {
	            System.out.println("Could not resolve MAC. Message dropped.");
	            return;
	        }
	        
	        System.out.println(name + " sending message to " + destinationIP + " (" + destinationMac + "): " + message);
	        
	        Map<String, Device> registeredDevices = topology.getRegisteredDevices();
	        
	        for (Device device : registeredDevices.values()) {
	            if (ipAddress.equals(device.getIpAddress())) {
	            	if (device instanceof Computer) 
	            	{
	    	            ((Computer) device).receiveMessage(this.getIpAddress(), message);
	    	        } 
	            	else 
	    	        {
	    	            System.out.println("Destination device not found or not a Computer.");
	    	        }
	            }
	        }
	    }
	}

	private void receiveMessage(String sourceIP, String message) {
		System.out.println(name + " received message from " + sourceIP + ": " + message);
		
	}

	private void arpRequest(String destinationIP, Topology topology) {
		
		Map<String, Device> registeredDevices = topology.getRegisteredDevices();

	    for (Device connectedDevice : registeredDevices.values()) {
	    	//System.out.println("Device: " + connectedDevice);
	        if (connectedDevice.getIpAddress() != null && connectedDevice.getIpAddress().equals(destinationIP)) {
	            addArpEntry(destinationIP, connectedDevice.getMacAddress());
	            System.out.println("Received ARP reply: " + connectedDevice.getMacAddress());
	            return;
	        }
	    }
	}
	
	public void visitDomain(Topology topology, String domain, Computer pc) {
        
		Device device1 = topology.findConnectedDeviceByIP(pc, pc.getDnsServerIP());
	    
	    if (!(device1 instanceof DNSServer)) 
	    {
	    	System.out.println("ERROR: The specified DNS server IP does not belong to a DNS server device");
	    	return;
	    } 
	    
	    DNSServer dnsServer = (DNSServer) device1;
        String webServerIP = dnsServer.resolveDomain(domain);
        
        Device device2 = topology.findConnectedDeviceByIP(pc, webServerIP);
	    
        if (!(device2 instanceof WebServer)) 
	    {
	    	System.out.println("ERROR: The specified Web server IP does not belong to a Web server device");
	    	return;
	    } 
        
        WebServer webServer = (WebServer) device2;
        
        webServer.serveWebsite();
	}
}

