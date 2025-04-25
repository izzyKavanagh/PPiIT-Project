package Devices;

import java.util.Map;

import Network.Topology;

/**
 * Represents a Layer 2 Switch in the network.
 * This type of switch handles VLAN configuration and port assignments based on VLAN ID
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public class Layer2Switch extends Switch{
	
	/**
     * Constructs a Layer 2 Switch with the given name and MAC address.
     *
     * @param name - the name of the switch
     * @param macAddress - the MAC address of the switch
     */
	public Layer2Switch(String name, String macAddress) {
		super(name, macAddress);
	}

	/**
     * Configures a VLAN on the switch with the specified number and name.
     *
     * @param vlanNumber - the VLAN ID
     * @param vlanName - the name or label for the VLAN
     */
	@Override
	public void configureVLAN(int vlanNumber, String vlanName) {
		getVlanTable().put(vlanNumber, vlanName);
        System.out.println("VLAN " + vlanNumber + " (" + vlanName + ") configured on Layer 2 Switch: " + name);
	}

	/**
     * Assigns a switch port to a specific VLAN and updates the connected device's VLAN assignment
     * in the network topology
     *
     * @param port - the port identifier on the switch
     * @param vlanId - the VLAN ID to assign the port to
     * @param topology - the network topology object
     */
	@Override
	public void assignPortToVLAN(String port, int vlanId, Topology topology) {
		getVlanPortMap().put(port, vlanId);
	    System.out.println("Port " + port + " assigned to VLAN " + vlanId + " on " + name);
	    
	    // Retrieve the full topology mapping from the topology object
	    Map<String, Map<String, Device>> fullTopology = topology.getTopology();
	    Map<String, Device> portMap = fullTopology.get(this.name);

	    if (portMap != null) {
	        Device connectedDevice = portMap.get(port);
	        if (connectedDevice != null) {
	            connectedDevice.setVlanId(vlanId);
	            System.out.println("Device " + connectedDevice.getName() + " now assigned to VLAN " + vlanId);
	        }
	    }
	}

}
