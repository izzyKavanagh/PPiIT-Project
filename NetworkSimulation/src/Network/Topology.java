package Network;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Topology {
	
	private Map<String, Map<String, Device>> networkTopology = new HashMap<>();
	private Map<String, Device> registeredDevices = new HashMap<String, Device>();
	private Map<String, List<String>> adjacencyList = new HashMap<String, List<String>>();
	private NetworkManager manager;

	public Topology(){}
	
	public void setNetworkManager(NetworkManager manager) {
		this.manager = manager;
	}
	
	public void registerDevice(Device device) {
		if (!networkTopology.containsKey(device.getName())) {
	        Map<String, Device> portMap = new HashMap<>();
	        for (int i = 0; i < device.getTotalPorts(); i++) {
	            portMap.put("Fa0/" + i, null);
	        }
	        networkTopology.put(device.getName(), portMap);
	        registeredDevices.put(device.getName(), device);
	    }
	}

	public boolean connectDevices(Device source, Device target) {
		Map<String, Device> sourcePorts = networkTopology.get(source.getName());
	    Map<String, Device> targetPorts = networkTopology.get(target.getName());
	    
	    if (sourcePorts == null || targetPorts == null) {
	        System.out.println("One or both devices are not registered.");
	        return false;
	    }
	    
	    String availableSourcePort = null;
	    String availableTargetPort = null;
	    
	    for (Map.Entry<String, Device> entry : sourcePorts.entrySet()) {
	        if (entry.getValue() == null) {
	        	availableSourcePort = entry.getKey();
	            break;
	        }
	    }

	    for (Map.Entry<String, Device> entry : targetPorts.entrySet()) {
	        if (entry.getValue() == null) {
	            availableTargetPort = entry.getKey();
	            break;
	        }
	    }
	    
	    if (availableSourcePort == null) {
	        System.out.println(source.getName() + " has no available ports.");
	        return false;
	    }
	    
	    if (availableTargetPort == null) {
	        System.out.println(target.getName() + " has no available ports.");
	        return false;
	    }
	    

	    if (source instanceof Switch sw) {
	        sw.updateMacTable(target, availableSourcePort);
	    }
	    
	    sourcePorts.put(availableSourcePort, target);
	    targetPorts.put(availableTargetPort, source);
	    
	    updateAdjacencyList();
	    
	    manager.useStaticIpAllocation(source);
	    
	    System.out.println(target.getName() + " connected to " + source.getName() + " on Port " + availableSourcePort);
	    
	    return true;
	}
	
	public boolean disconnectDevices(Device source, Device target) {
		Map<String, Device> sourcePorts = networkTopology.get(source.getName());
	    Map<String, Device> targetPorts = networkTopology.get(target.getName());
	    
	    if (sourcePorts == null || targetPorts == null) {
	        System.out.println("One or both devices are not registered.");
	        return false;
	    }
	    
	    boolean disconnected = false;
	    
	    for (Map.Entry<String, Device> entry : sourcePorts.entrySet()) {
	        if (entry.getValue() != null && entry.getValue().equals(target)) {
	        	entry.setValue(null);
	        	disconnected = true;
	            System.out.println("Disconnected " + target.getName() + " from " + source.getName() + " at " + entry.getKey());
	            break;
	        }
	    }

	    for (Map.Entry<String, Device> entry : targetPorts.entrySet()) {
	        if (entry.getValue() != null && entry.getValue().equals(source)) {
	        	entry.setValue(null);
	        	disconnected = true;
	            System.out.println("Disconnected " + target.getName() + " from " + source.getName() + " at " + entry.getKey());
	            break;
	        }
	    }
	    
	    updateAdjacencyList();

	    /* add functionality to delete connections from mac table 
	    if (source instanceof Switch sw) {
	        sw.updateMacTable(target, availableSourcePort);
	    }
	    */
	    
	    if (!disconnected) {
	        System.out.println("No connection found between these devices.");
	    }
	    
	    return true;
	}
	
	public Map<String, List<String>> updateAdjacencyList(){
		// loop over key/value pairs in the topology map
		for (Map.Entry<String, Map<String, Device>> entry : networkTopology.entrySet()) {
			// get key AKA device's name
	        String deviceName = entry.getKey();
	        // extract value AKA connected device's name and ports and store it in a map for easier access
	        Map<String, Device> portMap = entry.getValue();

	        // create list to store adjacent devices in
	        List<String> adjacentDevices = new ArrayList<>();
	        
	        //loop through map storing topology map's value
	        for (Device connectedDevice : portMap.values()) {
	            if (connectedDevice != null) {
	            	// get name of connected device and store it in adjacency list
	            	adjacentDevices.add(connectedDevice.getName());
	            }
	        }
	        // add device and and its adjacent devices as key/values pair to adjacency list
	        adjacencyList.put(deviceName, adjacentDevices);
	    }
		
		return adjacencyList;
	}
	
	public void printAdjacencyList()
	{
		System.out.println("\nNetwork Topology Adjacency List:");
	    for (Map.Entry<String, List<String>> entry : adjacencyList.entrySet()) {
	        System.out.println(entry.getKey() + " -> " + entry.getValue());
	    }
	}
	
	public Device checkIfConnected(Device startDevice, Class<?> targetType) {
		// set to track visited devices -> to ensure same device isn't revisited multiple times
	    Set<String> visitedDevices = new HashSet<>();
	    // queue to store next devices to visit
	    Queue<String> nextDevices = new LinkedList<>();
	    
	    // add startDevice to queue and mark it as visited (so its not revisited)
	    nextDevices.add(startDevice.getName());
	    visitedDevices.add(startDevice.getName());

	    // loop through devicesToVisit until all devices have been visited
	    while (!nextDevices.isEmpty()) {
	    	
	    	// remove first device from nextDevices queue and place into currentDevice variable 
	        String currentDeviceName = nextDevices.poll();
	        // get current device object from map containing all connected devices
	        Device currentDeviceType = registeredDevices.get(currentDeviceName);
	        
	        // check if the current device is the targetType device (and is not the start device)
	        if (targetType.isInstance(currentDeviceType) && !currentDeviceType.equals(startDevice)) {
	        	// if yes, return the currentDevice
	            return currentDeviceType; 
	        }
	        
	        // get currentDevice's adjacency list, i.e., all the devices that its directly connected to
	        List<String> connectedDevicesList = adjacencyList.get(currentDeviceName);
	        
	        // check that currentDevice is connected to the network
	        if (connectedDevicesList != null) {
	        	// loop through every device in currentDevice's list of connected devices
	            for (String connectedDevice : connectedDevicesList) {
	            	
	            	// check that the connected device hasn't already been visited
	                if (!visitedDevices.contains(connectedDevice)) {
	                	// if not already visited, add it to visited
	                	visitedDevices.add(connectedDevice);
	                	// also add it to nextDevices -> it is added to the queue to have its own connected devices checked 
	                	nextDevices.add(connectedDevice);
	                }
	            }
	        }
	    }
	    // if nothing was found, return null
	    return null;
	}

	public void printNetworkTopology() {
        System.out.println("\nNetwork Topology (Port Connections):");
        for (Map.Entry<String, Map<String, Device>> entry : networkTopology.entrySet()) {
            String deviceName = entry.getKey();
            Map<String, Device> ports = entry.getValue();
            System.out.println("\nDevice: " + deviceName);
            System.out.println("+--------+-------------------+");
            System.out.println("| Port   | Connected Device   |");
            System.out.println("+--------+-------------------+");
            for (Map.Entry<String, Device> portEntry : ports.entrySet()) {
                String port = portEntry.getKey();
                Device connectedDevice = portEntry.getValue();
                String connectedName = (connectedDevice != null) ? connectedDevice.getName() : "Empty";
                System.out.printf("| %-6s | %-17s |\n", port, connectedName);
            }
            System.out.println("+--------+-------------------+\n");
        }
    }

	public Map<String, Device> getTopology() {
		return registeredDevices;
	}
}
