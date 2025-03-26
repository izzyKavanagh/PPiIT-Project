package Network;


// Class for representing computer
public class Computer {
	
	// computer's information
	String name, macAddress, ipAddress;
	Router connectedRouter;
	
	public Computer(String name, String macAddress) {
		this.name = name;
	    this.macAddress = macAddress;
	    // Initialise to null because IP assigned by DHCP
	    this.ipAddress = null;
	}
	
	public void connectToRouter(Router router) {
		//store the router
		this.connectedRouter = router;
		//connect to the router 
		router.connectDevice(this);
	 }
	
	public void sendMessage(String message, String destinationIp) {
		// Method for sending messages to other devices (through router)
	}
	
	public void receiveMessage(Packet packet) {
		// method for receiving messages from other devices
	}
	
	// method to get ip address from DHCPServer
	public void requestIpAddress(DHCPServer dhcpServer) {
		this.ipAddress = dhcpServer.assignIp(macAddress);
        System.out.println(name + " assigned IP: " + ipAddress);
	}

	// method to return computer's ip
	public String getIpAddress() { 
		return ipAddress;
	}
}

