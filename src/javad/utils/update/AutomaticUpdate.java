package javad.utils.update;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class AutomaticUpdate {

	private URL downloadUrl;
	private File localDir;
	private Copy c = new Copy();
	private String newestVersion;
	private String currentVersion;
	
	public AutomaticUpdate(URL downloadUrl, String newestVersion, String currentVersion, File localDir) {
		this.downloadUrl = downloadUrl;
		this.localDir = localDir; 
		this.currentVersion = currentVersion;
		this.newestVersion = newestVersion;
	}
	
	public void checkForUpdatesAndUpdate() {
		Thread t = new Thread(()-> {
			c.setVisible(true);
		});
		t.start();
		
		try {
			File tmpFile = new File(System.getProperty("java.io.tmpdir") + File.separator + "TMP" + downloadUrl.getFile().substring(downloadUrl.getFile().lastIndexOf("."), downloadUrl.getFile().length()));
			tmpFile.createNewFile();
			Files.copy(downloadUrl.openStream(), tmpFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
			if(newestVersion.equals(currentVersion)) {
				c.dispose();
				t.interrupt();
				return;
			}
			
			File tmpDowoadReplacer = new File(System.getProperty("java.io.tmpdir") + File.separator + "Updater.exe");
			tmpDowoadReplacer.createNewFile();
			Files.copy(new URL("http://www.frobeen.com/programms/manager/Replacer.exe").openStream(), tmpDowoadReplacer.toPath(), StandardCopyOption.REPLACE_EXISTING);
			
			new File(tmpDowoadReplacer.getParent() + File.separator + "update.xml").createNewFile();
			PrintWriter pw = new PrintWriter(new File(tmpDowoadReplacer.getParent() + File.separator + "update.xml"));
			pw.flush();
			pw.print("<update>\n"
					+ "<fileToCopy>" + tmpFile.toString() + "</fileToCopy>\n"
					+ "<fileToCopyTo>" + localDir.toString() + "</fileToCopyTo>\n"
					+ "</update>");
			pw.close();
			
			Runtime.getRuntime().exec("cmd /c " + tmpDowoadReplacer.toString());
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fehler: " + e.getMessage(), "Exception aufgetreten", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		c.dispose();
		t.interrupt();
		
	}
	
	public static void updateIfnneded(URL downloadUrl, URL currentNewestVersion, String VERSION, File thisFile) {
		try {
			if(!isEclipse()) {
				AutomaticUpdate update = new AutomaticUpdate(
						downloadUrl, 
						getNewestVersion(currentNewestVersion),
						VERSION,
						thisFile
						);
				update.checkForUpdatesAndUpdate();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public static boolean isEclipse() {
		String s = new File(AutomaticUpdate.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath()).toString();
		return s.contains("target") && s.contains("classes");
	}

	public static String getNewestVersion(URL url) {
		try {
			Scanner sc = new Scanner(url.openStream());
			String version = null;
			if(sc.hasNextLine()) {
				version = sc.nextLine();
			}
			sc.close();
			return version;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static File getThisFile() {
		return new File(System.getProperty("user.dir") + File.separator + getFileNameOfThisProgramm());
	}
	
	public static String getFileNameOfThisProgramm() {
		return new File(AutomaticUpdate.class.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath())
        .getName();
	}
	
}
