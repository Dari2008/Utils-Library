package javad.utils.console;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.time.LocalTime;
import java.util.Locale;

import javad.utils.console.Console.Ansi;
import javad.utils.console.listeners.event.SystemOutputEvent;

public class CustomErrorOutputStream extends PrintStream{

	
	private long rows = 0;

	public CustomErrorOutputStream(OutputStream out) {
		super(out);
	}

	public CustomErrorOutputStream(File file, Charset charset) throws IOException {
		super(file, charset);
	}

	public CustomErrorOutputStream(File file, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(file, csn);
	}

	public CustomErrorOutputStream(File file) throws FileNotFoundException {
		super(file);
	}

	public CustomErrorOutputStream(OutputStream out, boolean autoFlush, Charset charset) {
		super(out, autoFlush, charset);
	}

	public CustomErrorOutputStream(OutputStream out, boolean autoFlush, String encoding)
			throws UnsupportedEncodingException {
		super(out, autoFlush, encoding);
	}

	public CustomErrorOutputStream(OutputStream out, boolean autoFlush) {
		super(out, autoFlush);
	}

	public CustomErrorOutputStream(String fileName, Charset charset) throws IOException {
		super(fileName, charset);
	}

	public CustomErrorOutputStream(String fileName, String csn) throws FileNotFoundException, UnsupportedEncodingException {
		super(fileName, csn);
	}

	public CustomErrorOutputStream(String fileName) throws FileNotFoundException {
		super(fileName);
	}



	private static PrintStream out;

	static {
		set();
	}
	
	private static String start = "", stop = "", afterDate = "";
	private static StartStringGenerator startStringGenerator; 

	public static void setStartString(String start) {
		CustomErrorOutputStream.start = start;
	}
	
	public static void setStopString(String stop) {
		CustomErrorOutputStream.stop = stop;
	}
	
	public static void setStartStringGenerator(StartStringGenerator startStringGenerator) {
		CustomErrorOutputStream.startStringGenerator = startStringGenerator;
	}
	
	public static void setString(String start, String stop) {
		setStartString(start);
		setStopString(stop);
	}
	
	public static void set(){
		if(out == null)out = System.err;
		setStartString(Color(204, 223, 50));
		System.setErr(new CustomErrorOutputStream(out));
	}
	
	@Override
	public void flush() {
		out.flush();
	}

	@Override
	public void write(int b) {
		if(callEvents(b))return;
		check(b);
		out.write(b);
	}

	@Override
	public void write(byte[] buf, int off, int len) {
		byte[] tmp = new byte[len];
		System.arraycopy(buf, off, tmp, 0, len);
		if(callEvents(tmp))return;
		check(buf, off, len);
		out.write(buf, off, len);
	}

	@Override
	public void write(byte[] buf) throws IOException {
		if(callEvents(buf))return;
		check(buf, 0, buf.length);
		out.write(buf);
	}

	@Override
	public void writeBytes(byte[] buf) {
		if(callEvents(buf))return;
		check(buf, 0, buf.length);
		out.writeBytes(buf);
	}

	@Override
	public void print(boolean b) {
		if(callEvents(b))return;
		check(getCurrentThreadString() + b + RESET);
		out.print(getCurrentThreadString() + b + RESET);
	}

	@Override
	public void print(char c) {
		if(callEvents(c))return;
		check(getCurrentThreadString() + c + RESET);
		out.print(getCurrentThreadString() + c + RESET);
	}

	@Override
	public void print(int i) {
		if(callEvents(i))return;
		check(getCurrentThreadString() + i + RESET);
		out.print(getCurrentThreadString() + i + RESET);
	}

	@Override
	public void print(long l) {
		if(callEvents(l))return;
		check(l);
		out.print(getCurrentThreadString() + l + RESET);
	}

	@Override
	public void print(float f) {
		if(callEvents(f))return;
		check(getCurrentThreadString() + f + RESET);
		out.print(getCurrentThreadString() + f + RESET);
	}

	@Override
	public void print(double d) {
		if(callEvents(d))return;
		check(getCurrentThreadString() + d + RESET);
		out.print(getCurrentThreadString() + d + RESET);
	}

	@Override
	public void print(char[] s) {
		if(callEvents(s))return;
		check(s, getCurrentThreadString() + RESET);
		out.print(getCurrentThreadString());
		out.print(s);
		out.print(RESET);
	}

	@Override
	public void print(String s) {
		if(callEvents(s))return;
		check(getCurrentThreadString() + s + RESET);
		out.print(getCurrentThreadString() + s + RESET);
	}

	@Override
	public void print(Object obj) {
		if(callEvents(obj))return;
		check(getCurrentThreadString() + obj + RESET);
		out.print(getCurrentThreadString() + obj + RESET);
	}

	@Override
	public void println() {
		if(callEvents("\n"))return;
		check("\n");
		out.println();
	}

	@Override
	public void println(boolean x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(char x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(int x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(long x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(float x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(double x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(char[] x) {
		String res = "";
		for(char c : x)res += c;
		if(callEvents(res + "\n"))return;
		check(x, getCurrentThreadString() + RESET + "\n");
		out.print(getCurrentThreadString());
		out.print(x);
		out.println(RESET);
	}

	@Override
	public void println(String x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public void println(Object x) {
		if(callEvents(x + "\n"))return;
		check(getCurrentThreadString() + x + RESET + "\n");
		out.println(getCurrentThreadString() + x + RESET);
	}

	@Override
	public PrintStream printf(String format, Object... args) {
		if(callEvents(String.format(format, args) + "\n"))return this;
		check(String.format(getCurrentThreadString() + format + RESET, args));
		return out.printf(getCurrentThreadString() + format + RESET, args);
	}

	@Override
	public PrintStream printf(Locale l, String format, Object... args) {
		if(callEvents(String.format(l, format, args) + "\n"))return this;
		check(String.format(l, getCurrentThreadString() + format + RESET, args));
		return out.printf(l, getCurrentThreadString() + format + RESET, args);
	}

	@Override
	public PrintStream format(String format, Object... args) {
		if(callEvents(String.format(format, args) + "\n"))return this;
		check(String.format(getCurrentThreadString() + format + RESET, args));
		return out.format(getCurrentThreadString() + format + RESET, args);
	}

	@Override
	public PrintStream format(Locale l, String format, Object... args) {
		if(callEvents(String.format(l, format, args) + "\n"))return this;
		check(String.format(l, getCurrentThreadString() + format + RESET, args));
		return out.format(l, getCurrentThreadString() + format + RESET, args);
	}

	@Override
	public PrintStream append(CharSequence csq) {
		if(callEvents(csq.toString() + "\n"))return this;
		check(getCurrentThreadString() + csq + RESET);
		return out.append(getCurrentThreadString() + csq + RESET);
	}

	@Override
	public PrintStream append(CharSequence csq, int start, int end) {
		if(callEvents(csq.subSequence(start, end) + "\n"))return this;
		check(getCurrentThreadString() + csq.subSequence(start, end) + RESET);
		return out.append(getCurrentThreadString() + csq + RESET, start, end);
	}

	@Override
	public PrintStream append(char c) {
		if(callEvents(c + "\n"))return this;
		check(getCurrentThreadString() + c + RESET);
		return out.append(getCurrentThreadString() + c + RESET);
	}
	
	
	private boolean canceled = false;
	private Object output = null;
	private boolean callEvents(Object obj) {
		output = obj;
		canceled = false;
		SystemOutputEvent e = new SystemOutputEvent(()->output, ()->this, ()->canceled, (t)->output = t, (t)->{}, (t)->canceled = t);
		Console.callSystemOutputListeners(e, true);
		return canceled;
	}
	
	private void check(Object obj) {
		String s = obj.toString();
		long count = s.lines().count();
		rows += count;
	}
	
	private void check(char[] x, String string) {
		String tmp = "";
		for(char c : x) {
			tmp += c;
		}
		tmp += string;
		long count = tmp.lines().count();
		rows += count;
	}

	private void check(byte[] buf, int i, int length) {
		String tmp = "";
		for(int ii = i; ii < length; i++) {
			tmp += buf[ii];
		}
		long count = tmp.lines().count();
		rows += count;
	}

	// Farben
	public static final String RESET = "\u001B[0m";     // zurücksetzen auf Standardfarbe
	public static final String BLACK = "\u001B[30m";    // schwarze Farbe
	public static final String RED = "\u001B[31m";      // rote Farbe
	public static final String GREEN = "\u001B[32m";    // grüne Farbe
	public static final String YELLOW = "\u001B[33m";   // gelbe Farbe
	public static final String BLUE = "\u001B[34m";     // blaue Farbe
	public static final String PURPLE = "\u001B[35m";   // violette Farbe
	public static final String CYAN = "\u001B[36m";     // türkise Farbe
	public static final String WHITE = "\u001B[37m";    // weiße Farbe

	// Hintergrundfarben
	public static final String BLACK_BACKGROUND = "\u001B[40m";   // schwarzer Hintergrund
	public static final String RED_BACKGROUND = "\u001B[41m";     // roter Hintergrund
	public static final String GREEN_BACKGROUND = "\u001B[42m";   // grüner Hintergrund
	public static final String YELLOW_BACKGROUND = "\u001B[43m";  // gelber Hintergrund
	public static final String BLUE_BACKGROUND = "\u001B[44m";    // blauer Hintergrund
	public static final String PURPLE_BACKGROUND = "\u001B[45m";  // violetter Hintergrund
	public static final String CYAN_BACKGROUND = "\u001B[46m";    // türkiser Hintergrund
	public static final String WHITE_BACKGROUND = "\u001B[47m";   // weißer Hintergrund

	// Textstile
	public static final String BOLD = "\u001B[1m";      // fettgedruckt
	public static final String UNDERLINE = "\u001B[4m"; // unterstrichen
	public static final String ITALIC = "\u001B[3m";    // kursiv
	public static final String BLINK = "\u001B[5m";     // blinkend

	// Farben
	public static void Black(String s) {
	    System.err.println(BLACK + s + RESET);
	}

	public static void Red(String s) {
	    System.err.println(RED + s + RESET);
	}

	public static void Green(String s) {
	    System.err.println(GREEN + s + RESET);
	}

	public static void Yellow(String s) {
	    System.err.println(YELLOW + s + RESET);
	}

	public static void Blue(String s) {
	    System.err.println(BLUE + s + RESET);
	}

	public static void Purple(String s) {
	    System.err.println(PURPLE + s + RESET);
	}

	public static void Cyan(String s) {
	    System.err.println(CYAN + s + RESET);
	}

	public static void White(String s) {
	    System.err.println(WHITE + s + RESET);
	}

	// Hintergrundfarben
	public static void BlackBackground(String s) {
	    System.err.println(BLACK_BACKGROUND + s + RESET);
	}

	public static void RedBackground(String s) {
	    System.err.println(RED_BACKGROUND + s + RESET);
	}

	public static void GreenBackground(String s) {
	    System.err.println(GREEN_BACKGROUND + s + RESET);
	}

	public static void YellowBackground(String s) {
	    System.err.println(YELLOW_BACKGROUND + s + RESET);
	}

	public static void BlueBackground(String s) {
	    System.err.println(BLUE_BACKGROUND + s + RESET);
	}

	public static void PurpleBackground(String s) {
	    System.err.println(PURPLE_BACKGROUND + s + RESET);
	}

	public static void CyanBackground(String s) {
	    System.err.println(CYAN_BACKGROUND + s + RESET);
	}

	public static void WhiteBackground(String s) {
	    System.err.println(WHITE_BACKGROUND + s + RESET);
	}
	
	// Textstile
	public static void Bold(String s) {
	    System.err.println(BOLD + s + RESET);
	}

	public static void Underline(String s) {
	    System.err.println(UNDERLINE + s + RESET);
	}

	public static void Italic(String s) {
	    System.err.println(ITALIC + s + RESET);
	}

	public static void Blink(String s) {
	    System.err.println(BLINK + s + RESET);
	}
	
	// Text in Farbstil und Hintergrundfarbe ausgeben
	public static void Styled(String s, Ansi.Style style, Ansi.Color backgroundColor) {
	    System.err.println(style.toString() + backgroundColor.toString() + s + Ansi.RESET.toString());
	}

	public static void printStyled(String s, Ansi.Style style, Ansi.Color backgroundColor) {
	    System.err.print(style.toString() + backgroundColor.toString() + s + Ansi.RESET.toString());
	}
	
	public static void setCursorPosition(int row, int col) {
	    System.err.print("\033[" + row + ";" + col + "H");
	}
	
	public static void setCursorToLineBefore() {
		System.err.print("\033[1F\033[0J");
	}
	
	public static String Color(int red, int green, int blue) {
	    // Calculate the closest ANSI color code based on RGB values
	    int code = 16 + 36 * (red / 51) + 6 * (green / 51) + (blue / 51);

	    // Build and return the ANSI color code
	    return "\u001b[38;5;" + code + "m";
	}

	
	private String getCurrentThreadString() {
		LocalTime now = LocalTime.now();
		if(startStringGenerator != null) {
			return startStringGenerator.generate(now.getHour()+1, now.getMinute(), now.getSecond(), start, stop, Thread.currentThread().getName());
		}
		StringBuilder b = new StringBuilder();
		//[12:48:59] [Worker-Main-16/INFO]:
		b.append(start);
		b.append("[");
		b.append(now.getHour()+1);
		b.append(":");
		b.append(now.getMinute());
		b.append(":");
		b.append(now.getSecond());
		b.append("] [");
		b.append(Thread.currentThread().getName());
		b.append("]: ");
		b.append(afterDate);
		return b.toString();
	}
	
	public interface StartStringGenerator{
		public String generate(int hour, int minute, int second, String startString, String endString, String threadName);
	}

	public long getPrintedRows() {
		return rows;
	}

}
