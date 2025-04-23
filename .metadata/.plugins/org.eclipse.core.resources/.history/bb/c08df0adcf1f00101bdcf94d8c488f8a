package Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import DeviceComponents.VLANInterface;
import Network.DeviceWithCLI;

// Simulates a Router
public class Router extends Device implements DeviceWithCLI{
	
	// HashMap to store routing table (connected devices and their IP addresses)
    private Map<String, Device> routingTable = new HashMap<>();
    // HashMap to store devices' MAC addresses and associated static IPs 
    private Map<String, String> staticIpAllocations = new HashMap<>();
    
    private Map<Integer, VLANInterface> vlanInterfaces = new HashMap<>();
    
    private Map<String, String> portIpAssignments = new HashMap<>();
        
	public final int totalPorts = 8;
	private String staticIpPrefix = "266.444.1.";
	private int nextIP = 2;
	private String assignedStaticIp;
	
	public Router(String name, String macAddress, String ipAddress) {
		super(name, macAddress);
		this.ipAddress = ipAddress;
		this.portIpAssignments = new HashMap<>();
		
		for (int i = 0; i < getTotalPorts(); i++) {
			portIpAssignments.put("Fa0/" + i, "Empty");  // Default all ports to VLAN 1
        }
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
	
	public void assignIpToPort(String port, String ipAddress) {
        portIpAssignments.put(port, ipAddress);
        System.out.println("Assigned IP " + ipAddress + " to port " + port);
    }
	
	public Map<String, String> getPortIpAssignments() 
	{
		return portIpAssignments;
	}

    public void showPortIPs() {
    	if (portIpAssignments.isEmpty()) 
    	{
            System.out.println("No IPs assigned to ports.");
        } 
    	else 
    	{
            System.out.println("\n+----------------+--------------------+");
            System.out.printf("| %-14s | %-18s |\n", "Port", "IP Address");
            System.out.println("+----------------+--------------------+");
            
            for (Map.Entry<String, String> entry : portIpAssignments.entrySet()) 
            {
                System.out.printf("| %-14s | %-18s |\n", entry.getKey(), entry.getValue());
            }
            
            System.out.println("+----------------+--------------------+");
        }
    }
    
    public void updateRoutingTable(Device device) 
    {
    	if (device.getIpAddress() == null) 
        {
            System.out.println("ERROR: Device does not have an IP address.");
        } 
        else 
        {
            routingTable.put(device.getIpAddress(), device);
        }
    }
    
    public void configureVLANInterface(int vlanId, String ipAddress) {
        VLANInterface vlanInterface = new VLANInterface(ipAddress);
        vlanInterfaces.put(vlanId, vlanInterface);
        System.out.println("Configured interface VLAN " + vlanId + " with IP " + ipAddress);
    }

    public void showVLANInterfaces() {
        if (vlanInterfaces.isEmpty()) {
            System.out.println("No VLAN interfaces configured.");
        } else {
            System.out.println("\nVLAN Interfaces:");
            for (Map.Entry<Integer, VLANInterface> entry : vlanInterfaces.entrySet()) {
                System.out.println("VLAN " + entry.getKey() + " - " + entry.getValue());
            }
        }
    }
    
    public Map<Integer, VLANInterface> getVLANInterfaces()
	{
		return vlanInterfaces;
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

    @Override
    public void configureIpHelper(int vlanId, String helperAddress) {
    	VLANInterface vlanInterface = vlanInterfaces.get(vlanId);
        if (vlanInterface != null) {
            vlanInterface.setHelperAddress(helperAddress);
            System.out.println("Added helper address " + helperAddress + " to VLAN " + vlanId);
        } else {
            System.out.println("VLAN interface " + vlanId + " not configured.");
        }
    }

    @Override
    public void startCLI() {
    	Scanner sc = new Scanner(System.in);
        System.out.println("Starting CLI for " + name);

        while (true) {
            System.out.print("Enter VLAN interface number (or '-1' to quit): ");
            int vlanId = sc.nextInt();
            if (vlanId == -1) break;

            System.out.print("Enter IP helper address: ");
            String helperAddress = sc.nextLine();

            configureIpHelper(vlanId, helperAddress);
        }
        sc.close();
    }
}
