package javad.utils.files;

import java.io.File;
import java.io.FileNotFoundException;

public class PrintWriter {

	public static void printLine(File file, String line) {
		try {
			java.io.PrintWriter pw = new java.io.PrintWriter(file);
			pw.println(line);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
