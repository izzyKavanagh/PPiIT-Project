package DeviceComponents;

/**
 * Represents a VLAN interface on a network device.
 * Stores an IP address and an optional IP helper address (used for forwarding DHCP requests)
 */
public class VLANInterface {
	
	private String ipAddress;
    private String helperAddress;

    /**
     * Constructs a VLANInterface with the specified IP address.
     *
     * @param ipAddress the IP address to assign to the VLAN interface
     */
    public VLANInterface(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
     * Sets the IP helper address for this VLAN interface.
     * The helper address is typically used to forward DHCP or broadcast requests to another network.
     *
     * @param helperAddress the IP helper address to configure
     */
    public void setHelperAddress(String helperAddress) {
        this.helperAddress = helperAddress;
    }

    /**
     * Gets the IP helper address configured for this VLAN interface.
     *
     * @return the helper address
     */
    public String getHelperAddress() {
        return helperAddress;
    }
    
    /**
     * Gets the IP address assigned to this VLAN interface.
     *
     * @return the IP address of the VLAN interface
     */
    public String getIpAddress() {
        return ipAddress;
    }
    
    /**
     * Returns a string representation of the VLAN interface, showing both IP and helper address.
     *
     * @return a formatted string with IP and helper information
     */
    @Override
    public String toString() {
        return "IP: " + ipAddress + ", Helper: " + helperAddress;
    }
}
