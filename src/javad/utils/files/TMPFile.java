package javad.utils.files;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringBufferInputStream;

public class TMPFile {

	private int time = 100;
	private File file = null;
	
	public TMPFile(File f, int ZeitBisZurZerstörunginSeconds) {
		file = f;
		time = ZeitBisZurZerstörunginSeconds;
	}
	
	public void create(boolean debug) {
		try {
			Process p = Runtime.getRuntime().exec("cmd /c java -jar " + System.getProperty("java.io.tmpdir") + "Dir.jar " + file.getPath() + " " + time);
			InputStream out = p.getInputStream();
			
			
			
			if(debug) {
				String tmp = null;
				
				java.util.Scanner sc = new java.util.Scanner(out);
				
				while((tmp = sc.nextLine()) != null) {
					System.out.println(tmp);
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
}
