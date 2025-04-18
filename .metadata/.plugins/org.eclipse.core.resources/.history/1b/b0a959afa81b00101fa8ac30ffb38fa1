package Network;

import java.util.HashMap;
import java.util.Map;

// Simulates a Router
public class Router extends Device{
	
	// HashMap to store routing table (connected devices and their IP addresses)
    private Map<String, Device> routingTable = new HashMap<>();
    // HashMap to store devices' MAC addresses and associated static IPs 
    private Map<String, String> staticIpAllocations = new HashMap<>();
	public final int totalPorts = 8;
	private String staticIpPrefix = "266.444.1.";
	private int nextIP = 2;
	private String assignedStaticIp;
	
	public Router(String name, String macAddress, String ipAddress) {
		super(name, macAddress);
		this.ipAddress = ipAddress;
	}
	
	@Override
	public int getTotalPorts()
	{
		return totalPorts;
	}
	
	public String assignStaticIP(String macAddress) {
		
		assignedStaticIp = staticIpPrefix.concat(Integer.toString(nextIP));
	    staticIpAllocations.put(macAddress, assignedStaticIp);
	    nextIP++;
	    
	    return assignedStaticIp;
	}
    
    public void updateRoutingTable(Device device) {
 
    	if (device.getIpAddress() == null) 
        {
            System.out.println("ERROR: Device does not have an IP address.");
        } 
        else 
        {
            routingTable.put(device.getIpAddress(), device);
        }
    }
    
    public void printAllocations() {
        System.out.println("\nCurrent Static IP Allocations:");
        if (staticIpAllocations.isEmpty()) {
            System.out.println("No allocated IPs.");
            return;
        }

        System.out.println("+----------------+-------------------+");
        System.out.println("|   IP Address   |    MAC Address    |");
        System.out.println("+----------------+-------------------+");

        for (Map.Entry<String, String> entry : staticIpAllocations.entrySet()) {
            System.out.printf("| %-14s | %-17s |\n", entry.getValue(), entry.getKey());
        }

        System.out.println("+----------------+-------------------+\n");
    }
}
