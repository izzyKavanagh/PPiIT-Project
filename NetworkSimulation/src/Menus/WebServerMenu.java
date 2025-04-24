package Menus;

import java.util.List;
import java.util.Scanner;
import Devices.WebServer;
import Network.Topology;

public class WebServerMenu {
	
	public static void manageWebServers(Scanner scanner, List<WebServer> webServers, Topology topology, int mode) {
        if (webServers.isEmpty()) {
            System.out.println("No Web servers available.");
            return;
        }
        
        int serverChoice;

        do
        {
        	System.out.println("\n╔═══════════════════════════════════╗");
            System.out.println("║          Web Server List          ║");
            System.out.println("╠═══════════════════════════════════╣");

            for (int i = 0; i < webServers.size(); i++) {
                System.out.printf("║ %2d. %-29s ║\n", (i + 1), webServers.get(i).getName());
            }

            System.out.printf("║ %2d. %-29s ║\n", (webServers.size() + 1), "Return to Main Menu");
            System.out.println("╚═══════════════════════════════════╝");
            System.out.print("Select a Web server to manage: ");

            serverChoice = Integer.parseInt(scanner.nextLine());

            if (serverChoice == webServers.size() + 1) {
                return;
            }

            if (serverChoice < 1 || serverChoice > webServers.size()) {
                System.out.println("Invalid selection. Please try again.");
                continue;
            }

            WebServer selectedServer = webServers.get(serverChoice - 1);
            manageSingleWebServer(scanner, selectedServer, topology, mode);
        }while (serverChoice != (webServers.size() + 1) && mode != 2);
    }

    private static void manageSingleWebServer(Scanner scanner, WebServer webServer, Topology topology, int mode) {
    	int choice;
    	
    	do
    	{
    		System.out.println("\n╔════════════════════════════════════╗");
            System.out.printf("║   Managing Web Server: " + webServer.getName());
            System.out.println("╠════════════════════════════════════╣");
            System.out.println("║ 1. Configure Server IP Address     ║");
            System.out.println("║ 2. Set Website Content             ║");
            System.out.println("║ 3. View Website Content            ║");
            System.out.println("║ 4. View port connections           ║");
            System.out.println("║ 5. Return to Web Server List       ║");
            System.out.println("╚════════════════════════════════════╝");
            System.out.print("Select an option: ");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
            	case 1:
            		configureWebServerIpAddress(scanner, webServer);
            		break;
                case 2:
                	System.out.println("Enter website content for " + webServer.getName() + ":");
                    String websiteContent = scanner.nextLine();
                    webServer.setWebsiteContent(websiteContent);
                    System.out.println("Website content set successfully!");
                    break;
                case 3:
                	webServer.serveWebsite();
                    break;
                case 4:
                	topology.printPortConnections(webServer);
                case 5:
                	System.out.println("Exiting Web Server Menu...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }while(choice!=5 && mode != 2);
		
	}
    
    private static void configureWebServerIpAddress(Scanner scanner, WebServer webServer) {
	    String currentIp = webServer.getIpAddress();
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
	        webServer.setIpAddress(newIp);
	        System.out.println("IP address updated to: " + newIp);
	    }
	}
}
