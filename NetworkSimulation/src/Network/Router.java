package Network;

import java.util.HashMap;
import java.util.Map;

// Simulates a Router
public class Router {
	
	// HashMap to store routing table (connected devices and their IP addresses)
    private Map<String, Computer> routingTable = new HashMap<>();

    public void connectDevice(Computer computer) {
        if (computer.getIpAddress() == null) 
        {
            System.out.println("ERROR: Device does not have an IP address.");
        } 
        else 
        {
            routingTable.put(computer.getIpAddress(), computer);
            System.out.println("IP Address " + computer.getIpAddress());
        }
    }
    
}
