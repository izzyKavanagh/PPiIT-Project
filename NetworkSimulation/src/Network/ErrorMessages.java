package Network;

/**
 * An enumeration of error messages used in network configurations.
 * This enum contains predefined error messages that are useful in various scenarios
 * such as when configuring IP addresses, subnets, or ports.
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public enum ErrorMessages {
	// IP Pool Errors
    INVALID_IP_START_ZERO("IP pool cannot start with 0."),
    INVALID_IP_START_ONE("IP pool cannot start with 1 (router uses 192.168.x.1)."),
    INVALID_IP_OUT_OF_BOUNDS("IP addresses must be between 2 and 254."),
    INVALID_IP_RANGE("End IP cannot be smaller than the start IP."),
    INVALID_SUB_NETWORK_OUT_OF_BOUNDS("IP sub-networks must be between 0 and 254."),
	
	// Port-related errors (switches/routers)
	NO_MORE_PORTS("ERROR: Cannot connect device as there are no available ports."); 
    
    private final String message;

    /**
     * Constructor for the ErrorMessages enum.
     * Initializes each error message with a specific error string.
     * 
     * @param message - error message string to associate with this enum constant.
     */
    ErrorMessages(String message) {
        this.message = message;
    }

    /**
     * Retrieves the error message associated with this enum constant.
     * 
     * @return The error message string.
     */
    public String getMessage() {
        return message;
    }
}
