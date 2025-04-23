package Devices;

public class WebServer extends Device{

	private String websiteContent;
	private final int totalPorts = 8;

    public WebServer(String name, String macAddress, String websiteContent) {
        super(name, macAddress);
        this.websiteContent = websiteContent;
    }
    
    @Override
   	public int getTotalPorts()
   	{
   		return totalPorts;
   	}

    public void serveWebsite() {
        System.out.println("===== " + this.getName() + " Web Page =====");
        System.out.println(websiteContent);
        System.out.println("=============================");
    }
    
}
