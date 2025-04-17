package Network;

import java.util.HashMap;
import java.util.Map;

public class Layer2Switch extends Switch{
	
	private Map<Integer, String> vlanTable; // VLAN table

	public Layer2Switch(String name, String macAddress) {
		super(name, macAddress);
		this.vlanTable = new HashMap<>();
	}

	@Override
	public void configureVLAN(int vlanId, String vlanName) {
		vlanTable.put(vlanId, vlanName);
        System.out.println("VLAN " + vlanId + " (" + vlanName + ") configured on Layer 2 Switch: " + name);
	}
	
	public void printVLANs() {
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
