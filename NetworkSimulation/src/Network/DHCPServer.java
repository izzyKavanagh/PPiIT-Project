package Network;

import java.util.HashMap;
import java.util.Map;

// Class to simulate DHCP Server 
public class DHCPServer {
	
	private Map<String, String> ipAllocations = new HashMap<>();
    private int nextIP = 1; // Start assigning IPs from 192.168.10.1

    public void assignIp(String macAddress) {
    	// add functionality to assign ip to a device
    }
}
