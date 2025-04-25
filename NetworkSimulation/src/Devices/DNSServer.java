package Devices;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Represents a DNS server in the network simulation.
 * Responsible for resolving domain names to IP addresses and managing DNS records.
 * 
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public class DNSServer extends Device{
	
    // Stores DNS records where the key is the domain name and the value is the corresponding IP address
	private Map<String, String> dnsRecords;
	
	private final int totalPorts = 1;
	
	/**
     * Constructs a DNS server with the given name and MAC address.
     *
     * @param name - the name of the DNS server
     * @param macAddress - the MAC address of the server
     */
    public DNSServer(String name, String macAddress) {
        super(name, macAddress);
        dnsRecords = new HashMap<>();
    }
    
    /**
     * Returns the number of ports available on this DNS server.
     *
     * @return the number of ports (always 1)
     */
    @Override
	public int getTotalPorts()
	{
		return totalPorts;
	}

    /**
     * Resolves a domain name to its corresponding IP address using the DNS records
     *
     * @param domainName - the domain name to resolve
     * @return the corresponding IP address, or null if not found
     */
    public String resolveDomain(String domainName) {
        return dnsRecords.get(domainName);
    }

    /**
     * Prints all DNS records stored in this server
     */
	public void printDnsRecords() {
		System.out.println("\nDNS Records:");
        for (Map.Entry<String, String> entry : dnsRecords.entrySet()) 
        {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
	}
	
	 /**
     * Adds a new DNS record by accepting user input through the console
     * Associates a domain name with a web server's IP address
     *
     * @param scanner - a Scanner object used to read user input
     * @param dnsServer - the DNS server to which the record is being added (not strictly necessary)
     */
	public void addDnsRecord(Scanner scanner, DNSServer dnsServer) {
        System.out.println("Enter domain name:");
        String domainName = scanner.nextLine();
        
        System.out.println("Enter IP address of web server domain is hosted on:");
        String webServerIp = scanner.nextLine();

        dnsRecords.put(domainName, webServerIp);
        System.out.println("Record added: " + domainName + " -> " + webServerIp);
    }
}
