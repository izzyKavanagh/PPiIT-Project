package Devices;

import java.util.HashMap;  
import java.util.Map;

import Network.Topology;

// simulates a layer 2 unmanaged switch
public abstract class Switch extends Device{
	
	private Map<String, String> macTable; // MAC address table
	private final int totalPorts = 24; //number of ports the switch has
	private Map<String, Integer> vlanPortMap;
	private Map<Integer, String> vlanTable; // VLAN table

    public Switch(String name, String macAddress) {
        super(name, macAddress);
        this.macTable = new HashMap<>();
        this.vlanPortMap = new HashMap<>();
        this.vlanTable = new HashMap<>();

        for (int i = 0; i < getTotalPorts(); i++) {
        	vlanPortMap.put("Fa0/" + i, 1);  // Default all ports to VLAN 1
        }
    }
    
    public void setVlanPortMap(Map<String, Integer> vlanPortMap) {
		this.vlanPortMap = vlanPortMap;
	}
    
    public Map<String, Integer> getVlanPortMap() {
		return vlanPortMap;
	}

	public void setVlanTable(Map<Integer, String> vlanTable) {
		this.vlanTable = vlanTable;
	}
	
	public Map<Integer, String> getVlanTable() {
		return vlanTable;
	}

	@Override 
	public int getTotalPorts()
	{
		return totalPorts;
	}
    
    public void updateMacTable(Device device, String port) {
    	macTable.put(device.getMacAddress(), port);
    }
    
    public abstract void assignPortToVLAN(String port, int vlanId, Topology topology);
    	
    public abstract void configureVLAN(int vlanId, String vlanName);
    
    public void printMacTable() {
    	System.out.println("\nMAC Address Tabe of " + name + ":");
        if (macTable.isEmpty()) {
        	System.out.println("No Devices Connected.");
            return;
        }

        System.out.println("+-------------------+------------+");
        System.out.println("| MAC Address       | Port       |");
        System.out.println("+-------------------+------------+");

        for (Map.Entry<String, String> entry : macTable.entrySet()) {
            System.out.printf("| %-17s | %-10s |\n", entry.getKey(), entry.getValue());
        }

        System.out.println("+-------------------+------------+\n");
    }
    
    public void printPortVLANAssignments() {
		System.out.println("\nPort to VLAN Assignments on " + name + ":");
        System.out.println("+--------+--------+");
        System.out.println("| Port   | VLAN   |");
        System.out.println("+--------+--------+");
        for (Map.Entry<String, Integer> entry : getVlanPortMap().entrySet()) {
            System.out.printf("| %-6s | %-6d |\n", entry.getKey(), entry.getValue());
        }
        System.out.println("+--------+--------+\n");
		
	}
    
    public void printVLANs(){
    	System.out.println("\nVLAN Table of " + name + ":");
        if (vlanTable.isEmpty()) {
            System.out.println("No VLANs configured.");
            return;
        }

        System.out.println("+-------+------------+");
        System.out.println("| VLAN  | Name       |");
        System.out.println("+-------+------------+");

        for (Map.Entry<Integer, String> entry : vlanTable.entrySet()) {
            System.out.printf("| %-5d | %-10s |\n", entry.getKey(), entry.getValue());
        }

        System.out.println("+-------+------------+\n");
    }
    
}
