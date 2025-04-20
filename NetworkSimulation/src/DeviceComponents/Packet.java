package DeviceComponents;

public class Packet {
	
	private String sourceIP, destinationIP, message;
	
	public Packet(String sourceIP, String destinationIP, String message){
		this.sourceIP = sourceIP;
		this.destinationIP = destinationIP;
		this.message = message;
	}

	public String getSourceIP() {
		return sourceIP;
	}

	public String getDestinationIP() {
		return destinationIP;
	}

	public String getMessage() {
		return message;
	}
	
}
