package Menus;

import java.util.List; 
import java.util.Scanner;
import Devices.Computer;
import Devices.DHCPServer;
import Devices.DNSServer;
import Devices.Router;
import Devices.Switch;
import Devices.WebServer;
import Network.NetworkManager;
import Network.Topology;

public class FreeModeMenu {

	public static void mainMenu(Scanner scanner, Topology topology, NetworkManager manager, List<Router> routers, List<Computer> computers,
			List<Switch> switches, List<DHCPServer> dhcpServers, List<DNSServer> dnsServers, List<WebServer> webServers, int mode) 
	{
		int choice;
        
        do{
        	MainMenu.printMainMenu();
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                	DeviceCreationMenu.createDevice(scanner, topology, routers, computers, switches, dhcpServers, dnsServers, webServers);
                    break;
                case 2:
                	PCMenu.managePCs(scanner, computers, manager, mode);
                	break;
                case 3:
                	SwitchMenu.manageSwitches(switches, scanner, topology, mode);
                	break; 
                case 4:
                	RouterMenu.manageRouters(routers, scanner, topology, mode); 
                	break;
                case 5:
                	DNSServerMenu.manageDNSServers(scanner, dnsServers, topology, mode);
                	break;
                case 6:
                	DHCPServerMenu.manageDHCPServers(scanner, dhcpServers, topology, mode);
                	break;
                case 7:
                	DNSServerMenu.manageDNSServers(scanner, dnsServers, topology, mode);
                	break;
                case 8:
                	TopologyMenu.addConnection(scanner,topology);
                	break; 
                case 9:
                	TopologyMenu.removeConnection(scanner,topology); 
                	break; 
                case 10:
                	break;
            }
            
        }while(choice !=10);
	}
}
