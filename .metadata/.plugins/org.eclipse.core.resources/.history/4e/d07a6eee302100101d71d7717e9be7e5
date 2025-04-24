package Menus;

import java.util.List;
import java.util.Scanner;
import Devices.DNSServer;
import Network.Topology;

public class DNSServerMenu {
	
	public static void manageDNSServers(Scanner scanner, List<DNSServer> dnsServers, Topology topology, int mode) {
        if (dnsServers.isEmpty()) {
            System.out.println("No DNS servers available.");
            return;
        }

        int serverChoice;
        
        do
        {
            System.out.println("\n--- DNS Server List ---");
            for (int i = 0; i < dnsServers.size(); i++) {
                System.out.println((i + 1) + ". " + dnsServers.get(i).getName());
            }
            System.out.println((dnsServers.size() + 1) + ". Return to Main Menu");
            System.out.print("Select a DNS server to manage: ");

            serverChoice = Integer.parseInt(scanner.nextLine());

            if (serverChoice == dnsServers.size() + 1) {
                return;
            }

            if (serverChoice < 1 || serverChoice > dnsServers.size()) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }

            DNSServer selectedServer = dnsServers.get(serverChoice - 1);
            manageSingleDNSServer(scanner, selectedServer, topology, mode);
            
        }while(serverChoice != (dnsServers.size()+1) && mode != 2);
    }

    private static void manageSingleDNSServer(Scanner scanner, DNSServer dnsServer, Topology topology, int mode) {
    	int choice;
    	
    	do
    	{
            System.out.println("\n--- Managing DNS Server: " + dnsServer.getName() + " ---");
            System.out.println("1. Configure Server IP Address");
            System.out.println("2. Add DNS Record");
            System.out.println("3. View All Records");
            System.out.println("4. View port connections");
            System.out.println("5. Return to DNS Server List");
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            	case 1:
            		configureDNSServerIpAddress(scanner, dnsServer);
            		break;
                case 2:
                	dnsServer.addDnsRecord(scanner,dnsServer);
                    break;
                case 3:
                    dnsServer.printDnsRecords();
                    break;
                case 4:
                	topology.printPortConnections(dnsServer);
                case 5:
                	System.out.println("Exiting DNS Server Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while(choice!=5 && mode != 2);
		
	}
    
    private static void configureDNSServerIpAddress(Scanner scanner, DNSServer dnsServer) {
	    String currentIp = dnsServer.getIpAddress();
	    if (currentIp == null || currentIp.isEmpty()) {
	        System.out.println("Current IP Address: IP not set");
	    } else {
	        System.out.println("Current IP Address: " + currentIp);
	    }

	    System.out.print("Would you like to set a new IP address? (Y/N): ");
	    String response = scanner.nextLine().trim().toLowerCase();
	    if (response.equalsIgnoreCase("Y")) {
	        System.out.print("Enter new IP address: ");
	        String newIp = scanner.nextLine();
	        dnsServer.setIpAddress(newIp);
	        System.out.println("IP address updated to: " + newIp);
	    }
	}
}
