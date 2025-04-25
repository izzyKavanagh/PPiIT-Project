package Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import DeviceComponents.VLANInterface;
import Network.DeviceWithCLI;

/**
 * Represents a Router capable of static IP allocation, port IP assignments, and VLAN interface configuration.
 * Also supports CLI-based IP helper setup and routing table management.
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public class Router extends Device implements DeviceWithCLI{
	
    // HashMap to store devices' MAC addresses and associated static IPs 
    private Map<String, String> staticIpAllocations = new HashMap<>();
    
    private Map<Integer, VLANInterface> vlanInterfaces = new HashMap<>();
    
    private Map<String, String> portIpAssignments = new HashMap<>();
    
    public String ipAddress;
        
	public final int totalPorts = 8;
	private String staticIpPrefix = "266.444.1.";
	private int nextIP = 2;
	private String assignedStaticIp;
	
	 /**
     * Constructs a Router with the specified name and MAC address.
     *
     * @param name       the name of the router
     * @param macAddress the MAC address of the router
     */
	public Router(String name, String macAddress) {
		super(name, macAddress);
		this.portIpAssignments = new HashMap<>();
		
		for (int i = 0; i < getTotalPorts(); i++) {
			portIpAssignments.put("Fa0/" + i, "Empty");  // Default all ports to VLAN 1
        }
	}
	
	/**
     * Gets the router's main IP address.
     *
     * @return the IP address
     */
	public String getIpAddress() {
		return ipAddress;
	}

	 /**
     * Sets the main IP address of the router.
     *
     * @param ipAddress - the IP address to set
     */
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	 /**
     * Returns the total number of ports available on the router.
     *
     * @return the total number of ports
     */
	@Override
	public int getTotalPorts()
	{
		return totalPorts;
	}
	
	/**
     * Assigns a static IP to a device based on its MAC address.
     *
     * @param macAddress - the MAC address of the device
     * @return the assigned IP address
     */
	public String assignStaticIP(String macAddress) {
		
		assignedStaticIp = staticIpPrefix.concat(Integer.toString(nextIP));
	    staticIpAllocations.put(macAddress, assignedStaticIp);
	    nextIP++;
	    
	    return assignedStaticIp;
	}
	
	 /**
     * Assigns an IP address to a specific port.
     *
     * @param port - the port identifier (e.g., "Fa0/0")
     * @param ipAddress - the IP address to assign
     */
	public void assignIpToPort(String port, String ipAddress) {
        portIpAssignments.put(port, ipAddress);
        System.out.println("Assigned IP " + ipAddress + " to port " + port);
    }
	
	/**
     * Retrieves the map of port-to-IP assignments.
     *
     * @return the port-IP map
     */
	public Map<String, String> getPortIpAssignments() 
	{
		return portIpAssignments;
	}

	/**
     * Displays all current port-to-IP assignments.
     */
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
    
    /**
     * Configures a VLAN interface with a specific VLAN ID and IP address.
     * 
     * <p>This method creates a new {@link VLANInterface} instance and associates it with the provided VLAN ID and IP address.
     * It then stores the VLAN interface in a map for further use. The configuration is confirmed with a printed message.
     * 
     * @param vlanId the VLAN ID for which the interface is to be configured.
     * @param ipAddress the IP address to assign to the VLAN interface.
     */
    public void configureVLANInterface(int vlanId, String ipAddress) {
        VLANInterface vlanInterface = new VLANInterface(ipAddress);
        vlanInterfaces.put(vlanId, vlanInterface);
        System.out.println("Configured interface VLAN " + vlanId + " with IP " + ipAddress);
    }

    /**
     * Displays all configured VLAN interfaces.
     */
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
    
    /**
     * Returns the VLAN interface configurations.
     *
     * @return the VLAN interfaces map
     */
    public Map<Integer, VLANInterface> getVLANInterfaces()
	{
		return vlanInterfaces;
	}
    
    /**
     * Displays the current static IP allocations.
     */
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

    /**
     * Configures an IP helper address on a VLAN interface.
     *
     * @param vlanId - the VLAN ID
     * @param helperAddress - the IP helper address
     */
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

    /**
     * Starts a simple CLI interface for IP helper configuration
     */
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
