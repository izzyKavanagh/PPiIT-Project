package Devices;

/**
 * Represents a basic Web Server device that can serve content to users
 */
public class WebServer extends Device{

	private String websiteContent;
	private final int totalPorts = 1;

	/**
     * Constructs a WebServer with a name and MAC address.
     * 
     * @param name - the server name
     * @param macAddress - the MAC address
     */
    public WebServer(String name, String macAddress) {
        super(name, macAddress);
        this.websiteContent = "";
    }
    
    /**
     * Returns the number of ports (always 1 for a basic web server).
     */
    @Override
   	public int getTotalPorts()
   	{
   		return totalPorts;
   	}
    
    /**
     * Sets the website content to be served.
     *
     * @param websiteContent - the HTML/text content
     */
    public void setWebsiteContent(String websiteContent) {
        this.websiteContent = websiteContent;
    }

    /**
     * Simulates serving a website by printing a formatted box around the content.
     */
    public void serveWebsite() {
    	String[] lines = websiteContent.split("\n");

        // Configuration
        int horizontalPadding = 4; // spaces on left and right of content
        int verticalPadding = 1;   // blank lines above and below content

        // Calculate max width
        int maxLineLength = 0;
        for (String line : lines) {
            maxLineLength = Math.max(maxLineLength, line.length());
        }
        int contentWidth = maxLineLength + horizontalPadding * 2;
        String horizontalBorder = "+" + "-".repeat(contentWidth + 2) + "+"; 

        // Print header
        System.out.println();
        System.out.println();
        System.out.println(horizontalBorder);
        String title = " " + this.getName() + " - Web Page ";
        int titlePadding = (contentWidth - title.length()) / 2;
        System.out.printf("| %" + (titlePadding + title.length()) + "s%" + (contentWidth - titlePadding - title.length() + 1) + "s|\n", title, "");
        System.out.println(horizontalBorder);

        // Vertical padding (top)
        for (int i = 0; i < verticalPadding; i++) {
            System.out.printf("| %" + (contentWidth + 1) + "s|\n", ""); // +1 to match spacing
        }

        // Content lines with horizontal padding
        for (String line : lines) {
            String paddedLine = " ".repeat(horizontalPadding) + line + " ".repeat(contentWidth - line.length() - horizontalPadding);
            System.out.printf("| %s |\n", paddedLine);
        }

        // Vertical padding (bottom)
        for (int i = 0; i < verticalPadding; i++) {
            System.out.printf("| %" + (contentWidth + 1) + "s|\n", "");
        }

        System.out.println(horizontalBorder);
        System.out.println();
        System.out.println();
    }
    
}
