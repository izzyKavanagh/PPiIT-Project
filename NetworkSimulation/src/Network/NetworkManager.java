package Network;

public class NetworkManager {
	private Topology topology;
	
	public NetworkManager(Topology topology) {
        this.topology = topology;
    }

    public void useStaticIpAllocation(Device device) {
    	
    	topology.updateAdjacencyList();
    	
    	Device connectedRouter = topology.checkIfConnected(device, Router.class);

    	if (connectedRouter != null && !(device instanceof Layer2Switch)) {
    	    Router router = (Router) connectedRouter;
    	    String ip = router.assignStaticIP(device.getMacAddress());
    	    device.ipAddress = ip;
    	    System.out.println(device.getName() + " assigned IP: " + ip);
    	}
    	
    	if(connectedRouter == null) 
    	{
    		System.out.println("ERROR: No router found on " + device.getName() + "'s network.");
    	}
    	
    }

    
    public void useDynamicIpAllocation(Device device, String poolName) {
    	
    	topology.updateAdjacencyList();
    	
    	Device connectedDHCP = topology.checkIfConnected(device, DHCPServer.class);

    	if (connectedDHCP != null && !(device instanceof Layer2Switch) && !(device instanceof Router)) 
    	{
    	    DHCPServer dhcpServer = (DHCPServer) connectedDHCP;
    	    String ip = dhcpServer.assignIp(poolName, device.getMacAddress());
    	    device.ipAddress = ip;
    	    System.out.println(device.getName() + " assigned IP: " + ip);
    	}
    	
    	if(connectedDHCP == null) 
    	{
    		System.out.println("ERROR: No DHCP server found on" + device.getName() + "'s network.");
    	}
    }

    public Topology getTopology() {
        return topology;
    }
}

