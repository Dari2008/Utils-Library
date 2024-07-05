package javad.utils.console;

import java.util.ArrayList;

import javax.resource.spi.IllegalStateException;

import javad.utils.console.listeners.SystemOutputListener;
import javad.utils.console.listeners.event.SystemOutputEvent;

public class Console {
	// Farben
	public static final String ANSI_RESET = "\u001B[0m";     // zurücksetzen auf Standardfarbe
	public static final String ANSI_BLACK = "\u001B[30m";    // schwarze Farbe
	public static final String ANSI_RED = "\u001B[31m";      // rote Farbe
	public static final String ANSI_GREEN = "\u001B[32m";    // grüne Farbe
	public static final String ANSI_YELLOW = "\u001B[33m";   // gelbe Farbe
	public static final String ANSI_BLUE = "\u001B[34m";     // blaue Farbe
	public static final String ANSI_PURPLE = "\u001B[35m";   // violette Farbe
	public static final String ANSI_CYAN = "\u001B[36m";     // türkise Farbe
	public static final String ANSI_WHITE = "\u001B[37m";    // weiße Farbe

	// Hintergrundfarben
	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";   // schwarzer Hintergrund
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";     // roter Hintergrund
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";   // grüner Hintergrund
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";  // gelber Hintergrund
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";    // blauer Hintergrund
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";  // violetter Hintergrund
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";    // türkiser Hintergrund
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";   // weißer Hintergrund

	// Textstile
	public static final String ANSI_BOLD = "\u001B[1m";      // fettgedruckt
	public static final String ANSI_UNDERLINE = "\u001B[4m"; // unterstrichen
	public static final String ANSI_ITALIC = "\u001B[3m";    // kursiv
	public static final String ANSI_BLINK = "\u001B[5m";     // blinkend

	private static ArrayList<SystemOutputListener> systemOutputListeners = new ArrayList<>();
	
	private Console() {
		
	}
	
	public static void setOutAndErrStream() {
		CustomOutputStream.setStartStringGenerator((hour, minute, second, startString, endString, threadName) -> "");
		CustomErrorOutputStream.setStartStringGenerator((hour, minute, second, startString, endString, threadName) -> "");
		CustomOutputStream.setStopString("");
		CustomErrorOutputStream.setStopString("");
		System.setOut(new CustomOutputStream(System.out));
		System.setErr(new CustomErrorOutputStream(System.err));
	}
	
	// Farben
	public static void printlnBlack(String s) {
		System.out.println(ANSI_BLACK + s + ANSI_RESET);
	}

	public static void printlnRed(String s) {
		System.out.println(ANSI_RED + s + ANSI_RESET);
	}

	public static void printlnGreen(String s) {
		System.out.println(ANSI_GREEN + s + ANSI_RESET);
	}

	public static void printlnYellow(String s) {
		System.out.println(ANSI_YELLOW + s + ANSI_RESET);
	}

	public static void printlnBlue(String s) {
		System.out.println(ANSI_BLUE + s + ANSI_RESET);
	}

	public static void printlnPurple(String s) {
		System.out.println(ANSI_PURPLE + s + ANSI_RESET);
	}

	public static void printlnCyan(String s) {
		System.out.println(ANSI_CYAN + s + ANSI_RESET);
	}

	public static void printlnWhite(String s) {
		System.out.println(ANSI_WHITE + s + ANSI_RESET);
	}

	// Hintergrundfarben
	public static void printlnBlackBackground(String s) {
		System.out.println(ANSI_BLACK_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnRedBackground(String s) {
		System.out.println(ANSI_RED_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnGreenBackground(String s) {
		System.out.println(ANSI_GREEN_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnYellowBackground(String s) {
		System.out.println(ANSI_YELLOW_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnBlueBackground(String s) {
		System.out.println(ANSI_BLUE_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnPurpleBackground(String s) {
		System.out.println(ANSI_PURPLE_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnCyanBackground(String s) {
		System.out.println(ANSI_CYAN_BACKGROUND + s + ANSI_RESET);
	}

	public static void printlnWhiteBackground(String s) {
		System.out.println(ANSI_WHITE_BACKGROUND + s + ANSI_RESET);
	}
	
	// Textstile
	public static void printlnBold(String s) {
		System.out.println(ANSI_BOLD + s + ANSI_RESET);
	}

	public static void printlnUnderline(String s) {
		System.out.println(ANSI_UNDERLINE + s + ANSI_RESET);
	}

	public static void printlnItalic(String s) {
		System.out.println(ANSI_ITALIC + s + ANSI_RESET);
	}

	public static void printlnBlink(String s) {
		System.out.println(ANSI_BLINK + s + ANSI_RESET);
	}
	
	// Text in Farbstil und Hintergrundfarbe ausgeben
	public static void printlnStyled(String s, Ansi.Style style, Ansi.Color backgroundColor) {
		System.out.println(style.toString() + backgroundColor.toString() + s + Ansi.RESET.toString());
	}

	public static void printStyled(String s, Ansi.Style style, Ansi.Color backgroundColor) {
	    System.out.println(style.toString() + backgroundColor.toString() + s + Ansi.RESET.toString());
	}
	
	public static void setCursorPosition(int row, int col) {
	    onlyPrint("\033[" + row + ";" + col + "H");
	}
	
	public static void moveCurserUp(int row) {
	    onlyPrint("\u001B[" + row + "A");
	}
	
	public static void moveCurserDown(int row) {
	    onlyPrint("\u001B[" + row + "B");
	}
	
	public static void setCursorToLineBefore() {
		onlyPrint("\033[1F\033[0J");
	}

	private static void onlyPrintln(String string) {
		if(System.out instanceof CustomOutputStream) {
			((CustomOutputStream)System.out).printlnOnly(string);
		}else {
			System.out.println(string);
		}
	}

	private static void onlyPrint(String string) {
		if(System.out instanceof CustomOutputStream) {
			((CustomOutputStream)System.out).printOnly(string);
		}else {
			System.out.println(string);
		}
	}
	
	public static void callSystemOutputListeners(SystemOutputEvent e, boolean returnOnCancel) {
		for(SystemOutputListener li : systemOutputListeners) {
			li.systemOutput(e);
			if(e.isCanceled() && returnOnCancel)return;
		}
	}
	
	public static void addListener(SystemOutputListener li) {
		systemOutputListeners.add(li);
	}
	
	public static void removeListener(SystemOutputListener li) {
		systemOutputListeners.remove(li);
	}
	
	public static void removeListener(int index) {
		systemOutputListeners.remove(index);
	}
	
	public static ArrayList<SystemOutputListener> getListener() {
		return systemOutputListeners;
	}
	
	public static long getRows() throws IllegalStateException {
		if(!(System.out instanceof CustomOutputStream) || !(System.err instanceof CustomErrorOutputStream)) {
			throw new IllegalStateException("Der System.out muss vom typ CustomOutputStream und der System.err muss vom typ CustomErrorOutputStream nutze Console.setOutAndErrStream() um sie zu setzten!");
		}
		CustomOutputStream out = (CustomOutputStream)System.out;
		CustomErrorOutputStream err = (CustomErrorOutputStream)System.err;
		return out.getPrintedRow() + err.getPrintedRows();
	}

	public static class ConsoleStringBuilder{
		
		private String string = "";

		public ConsoleStringBuilder() {
		}
		
		public ConsoleStringBuilder(String text) {
			string = text;
		}
		
		public ConsoleStringBuilder backgroundColor(Ansi.Background back) {
			string += back;
			return this;
		}
		
		public ConsoleStringBuilder foregroundColor(Ansi.Color color) {
			string += color;
			return this;
		}
		
		public ConsoleStringBuilder style(Ansi.Style style) {
			string += style;
			return this;
		}
		
		public ConsoleStringBuilder text(String text) {
			string += text;
			return this;
		}
		
		public ConsoleStringBuilder reset() {
			string += Ansi.RESET;
			return this;
		}
		
		public String build() {
			return string + Ansi.RESET;
		}
		
		@Override
		public String toString() {
			return build();
		}
		
	}
	
	public static enum Ansi{
	    RESET("\u001B[0m");
	    
		public enum Color{
		    // Farben
		    BLACK("\u001B[30m"),
		    RED("\u001B[31m"),
		    GREEN("\u001B[32m"),
		    YELLOW("\u001B[33m"),
		    BLUE("\u001B[34m"),
		    PURPLE("\u001B[35m"),
		    CYAN("\u001B[36m"),
		    WHITE("\u001B[37m");
			
		    private final String code;

		    Color(String code) {
		        this.code = code;
		    }

		    @Override
		    public String toString() {
		        return code;
		    }
			
		}
		
		public enum Background{
		    // Hintergrundfarben
		    BLACK_BACKGROUND("\u001B[40m"),
		    RED_BACKGROUND("\u001B[41m"),
		    GREEN_BACKGROUND("\u001B[42m"),
		    YELLOW_BACKGROUND("\u001B[43m"),
		    BLUE_BACKGROUND("\u001B[44m"),
		    PURPLE_BACKGROUND("\u001B[45m"),
		    CYAN_BACKGROUND("\u001B[46m"),
		    WHITE_BACKGROUND("\u001B[47m");
			
		    private final String code;

		    Background(String code) {
		        this.code = code;
		    }

		    @Override
		    public String toString() {
		        return code;
		    }
		}
		
		public enum Style{
		    // Textstile
		    BOLD("\u001B[1m"),
		    ITALIC("\u001B[3m"),
		    UNDERLINE("\u001B[4m"),
		    BLINK("\u001B[5m");
			
		    private final String code;

		    Style(String code) {
		        this.code = code;
		    }

		    @Override
		    public String toString() {
		        return code;
		    }
		}

	    private final String code;

	    Ansi(String code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return code;
	    }
		
	}

	
}
