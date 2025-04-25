package Network;

/**
 * An interface representing a device that has a Command Line Interface (CLI). 
 * This interface provides methods for interacting with the device's CLI, 
 * configuring IP helper addresses, and retrieving the device's name.
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public interface DeviceWithCLI {
	 /**
     * Starts the Command Line Interface (CLI) of the device.
     * This method should initiate the device's CLI for user interaction.
     */
	void startCLI();
	
	/**
     * Configures an IP helper address for a specific VLAN interface.
     * The helper address is typically used to relay certain types of traffic 
     * (such as DHCP) to a specific address.
     * 
     * @param vlanInterface - VLAN interface number to configure.
     * @param helperAddress - IP address of the helper address to set.
     */
    void configureIpHelper(int vlanInterface, String helperAddress);
    
    /**
     * Gets the name of the device.
     * This method returns a string representing the device's name, 
     * which is typically used for identification in a network.
     * 
     * @return The name of the device.
     */
	String getName();
}
