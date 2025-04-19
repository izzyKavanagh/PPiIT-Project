package Network;

import java.util.Map;

public class Layer2Switch extends Switch{
	

	public Layer2Switch(String name, String macAddress) {
		super(name, macAddress);
	}

	@Override
	public void configureVLAN(int vlanNumber, String vlanName) {
		getVlanTable().put(vlanNumber, vlanName);
        System.out.println("VLAN " + vlanNumber + " (" + vlanName + ") configured on Layer 2 Switch: " + name);
	}

	@Override
	public void assignPortToVLAN(String port, int vlanId, Topology topology) {
		getVlanPortMap().put(port, vlanId);
	    System.out.println("Port " + port + " assigned to VLAN " + vlanId + " on " + name);
	    
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
