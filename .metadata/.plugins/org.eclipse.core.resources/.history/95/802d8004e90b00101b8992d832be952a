package Network;

public abstract class Device {

	protected String name;
    protected String macAddress;
    protected String ipAddress;
    protected Router connectedRouter;

    public Device(String name, String macAddress) {
        this.name = name;
        this.macAddress = macAddress;
        this.ipAddress = null; // Assigned by DHCP
    }

    public String getName() {
        return name;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void connectToRouter(Router router) {
        this.connectedRouter = router;
        router.connectDevice(this);
    }

    public void requestIpAddress(DHCPServer dhcpServer) {
        this.ipAddress = dhcpServer.assignIp(macAddress);
        System.out.println(name + " assigned IP: " + ipAddress);
    }
}
