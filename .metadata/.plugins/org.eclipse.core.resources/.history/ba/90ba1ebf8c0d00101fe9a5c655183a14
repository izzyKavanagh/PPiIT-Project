package Network;


// Class for representing computer
public class Computer extends Device{
	
Router connectedRouter;
	
	public Computer(String name, String macAddress) {
		super(name,macAddress);
	}
	
	// method to get IP address from DHCPServer
	public void useDhcp(DHCPServer dhcpServer) {
		//pass MAC address to DHCP so IP can be assigned to specific computer
		//this.ipAddress = dhcpServer.assignIp(macAddress);
        System.out.println(name + " assigned IP: " + ipAddress);
	}
}

