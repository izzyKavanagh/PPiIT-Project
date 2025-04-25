package Menus;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class for displaying formatted text boxes in the console.
 * Provides methods for displaying steps and tips with text wrapping and box drawing.
 * 
 * 
 * @author Izzy Kavanagh
 * @version 1.0
 */
public class GuidedModeUtils {

	/**
     * Prints a step box with a title and multi-line content.
     * The content is wrapped to fit within the box and is displayed with a title.
     *
     * @param title - The title of the step.
     * @param lines - The content (steps) to be displayed inside the box.
     */
	public static void printStepBox(String title, String... lines) {
        int indent = 4;
        String padding = " ".repeat(indent);
        int maxLineLength = 50; // Max line length before wrapping

        // Wrap each input line and collect all wrapped lines
        List<String> wrappedLines = new ArrayList<>();
        for (String line : lines) {
            wrappedLines.addAll(wrapLine(line, maxLineLength));
        }

        // Determine box width
        int width = Math.max(title.length(), getMaxLineLength(wrappedLines)) + 6;

        // Build box
        String horizontalBorder = padding + "╔" + "═".repeat(width - 2) + "╗";
        String bottomBorder = padding + "╚" + "═".repeat(width - 2) + "╝";

        System.out.println();
        System.out.println(horizontalBorder);
        System.out.printf(padding + "║ %-"+(width-4)+"s ║\n", title);
        System.out.println(padding + "╟" + "─".repeat(width - 2) + "╢");

        for (String line : wrappedLines) {
            System.out.printf(padding + "║ %-"+(width-4)+"s ║\n", line);
        }

        System.out.println(bottomBorder);
        System.out.println(); // Bottom spacing
    }

	/**
     * Prints a tip box with a given message.
     * The message is wrapped to fit within the box, which has a fixed width.
     *
     * @param message - The tip message to be displayed inside the box.
     */
	public static void printTipBox(String message) {
		int boxWidth = 30;
        int indent = 8;
        String padding = " ".repeat(indent);
        int contentWidth = boxWidth - 4;

        List<String> lines = wrapLine(message, contentWidth);

        String top = padding + "╭" + "─".repeat(boxWidth - 2) + "╮";
        String bottom = padding + "╰" + "─".repeat(boxWidth - 2) + "╯";

        System.out.println();
        System.out.println(top);
        System.out.printf(padding + "│ %-"+ contentWidth +"s │\n", "TIP:");
        for (String line : lines) {
            System.out.printf(padding + "│ %-"+ contentWidth +"s │\n", line);
        }
        System.out.println(bottom);
        System.out.println();
	}
	
	/**
     * Wraps a given line into multiple lines if it exceeds the specified maximum line length.
     * The line is wrapped at spaces to avoid splitting words.
     *
     * @param line - The input line to be wrapped.
     * @param maxLineLength - The maximum length of each wrapped line.
     * @return A list of wrapped lines.
     */
    private static List<String> wrapLine(String line, int maxLineLength) {
        List<String> wrapped = new ArrayList<>();
        while (line.length() > maxLineLength) {
            int wrapAt = line.lastIndexOf(' ', maxLineLength);
            if (wrapAt == -1) wrapAt = maxLineLength;
            wrapped.add(line.substring(0, wrapAt).trim());
            line = line.substring(wrapAt).trim();
        }
        wrapped.add(line);
        return wrapped;
    }

    /**
     * Returns the maximum line length from a list of lines.
     * This is used to calculate the width of the box for the step box.
     *
     * @param lines - The list of lines to check for the longest line.
     * @return The length of the longest line.
     */
    private static int getMaxLineLength(List<String> lines) {
        int max = 0;
        for (String line : lines) {
            max = Math.max(max, line.length());
        }
        return max;
    }
}
