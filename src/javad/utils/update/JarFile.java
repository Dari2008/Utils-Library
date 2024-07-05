package javad.utils.update;

import java.io.File;
import java.io.IOException;

public class JarFile {

	private File jarFile;

	public JarFile(File jarFile) {
		this.jarFile = jarFile;
	}

	public JarFile(String jarFile) {
		this.jarFile = new File(jarFile);
	}
	
	public File getFile() {
		return jarFile;
	}
	
	public String getFileString() {
		return jarFile.toString();
	}
	
	public void setFile(File file) {
		jarFile = file;
	}
	
	public void setFileString(String file) {
		jarFile = new File(file);
	}
	
	public void run() {
		if(System.getProperty("os.name").contains("Windows")) {
			try {
				Runtime.getRuntime().exec("cmd /c java -jar \"" + jarFile.toString() + "\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				Runtime.getRuntime().exec("java -jar \"" + jarFile.toString() + "\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Process runAndGetProcess() {
		if(System.getProperty("os.name").contains("Windows")) {
			try {
				return Runtime.getRuntime().exec("cmd /c java -jar \"" + jarFile.toString() + "\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			try {
				return Runtime.getRuntime().exec("java -jar \"" + jarFile.toString() + "\"");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
}
