package javad.utils.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javad.utils.string.Paterns;
@Deprecated
public class ZipArchiv {

	private File zipPath = null;
	private File descDirPath = null;
	private File[] files;
	private  ZipOutputStream zos = null;
	private  String dirToZip;
	private String zipName;
	
	public ZipArchiv(File zipPath, File descDir){
        this.zipPath = zipPath;
        this.descDirPath = descDir;
        this.files = new File[1];
        files[0] = descDir;
        
        gen(descDir.getPath().split(Paterns.üebersetztze("\\"))[descDir.getPath().split(Paterns.üebersetztze("\\")).length-1], descDir);
      
    }
	
	public ZipArchiv(File descDir, File...files){
        this.files = files;
        this.descDirPath = descDir;

     
    }
 
	private void gen(String name, File f) {
		 
	        
	        dirToZip = f.getPath();
	        zipName = name;
	       
	        try {
	        	
	        	File file = null;
	        	
	        	if(zipName.endsWith(".zip")) {
	        		file = new File(zipName);
	        	}else {
	        		file = new File(zipName + ".zip");
	        	}
	            
	            System.out.println("Erzeuge Archiv " + file.getCanonicalPath());
	            zos = new ZipOutputStream(new FileOutputStream(
	                    file.getCanonicalPath()));
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (zos != null) zos.close();
	            } catch (IOException ioe) {}
	        } 
	        
	}
    
    public boolean unZipAll(boolean overwrite) {
		return unZipFiles(zipPath, descDirPath, overwrite);
    }
    
    public boolean extract() {
    	return extract(zipName, dirToZip, new File(dirToZip), zos);
    }
    
    private boolean extract(String zipName, String dirToZip, File file, ZipOutputStream zos2) {
        if (zipName == null || dirToZip == null || file == null
                || zos == null || !file.isDirectory())
            return false;

        FileInputStream fis = null;
        try {
            File[] fileArr = file.listFiles();
            String path;
            for (File f : fileArr) {
                if (f.isDirectory()) {
                    extract(zipName, dirToZip, f, zos2);
                    continue;
                }
                fis = new FileInputStream(f);
                path = f.getCanonicalPath();
                String name = path.substring(dirToZip.length(), path.length());
                System.out.println("Packe " + name);
                zos.putNextEntry(new ZipEntry(name));
                int len;
                byte[] buffer = new byte[2048];
                while ((len = fis.read(buffer, 0, buffer.length)) > 0) {
                    zos.write(buffer, 0, len);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if(fis != null) fis.close();
            } catch (IOException ioe) {}
        } 
    	return false;
    }
    
    
    @SuppressWarnings({"resource", "unused" })
    private boolean unZipFiles(File zipFile, File descDir, boolean overwrite){
    	boolean tmp = false;
 try {
	 
	 
                 ZipFile zip;
				
					zip = new ZipFile (zipFile, Charset.forName ("GBK"));
				
        String name = zip.getName().substring(zip.getName().lastIndexOf('\\')+1, zip.getName().lastIndexOf('.'));
 
        File pathFile = descDir;
        if (!pathFile.exists()) {
            pathFile.mkdirs();
        }else if(overwrite && pathFile.exists()) {
            File pathFile2 = descDir;
            pathFile2.delete();
      		pathFile2.mkdirs();
        }else if(!overwrite && descDir.exists()) {
        	int i = 1;
        	
        	File descDirtmp = descDir;
        	
        	while(descDir.exists()) {
        		descDir = new File(descDirtmp.getPath() + "(" + i + ")");
        		i++;
        		tmp = true;
        	}
        	
        }
        
        
        
 
        for (Enumeration<? extends ZipEntry> entries = zip.entries(); entries.hasMoreElements();) {
            ZipEntry entry = (ZipEntry) entries.nextElement();
            String zipEntryName = entry.getName();
            InputStream in = zip.getInputStream(entry);
            String outPath = (descDir+"/"+ zipEntryName).replaceAll("\\*", "/");
                         
            File file = new File(outPath.substring(0, outPath.lastIndexOf('/')));
            if (!file.exists()) {
                file.mkdirs();
            }
                        
            if (new File(outPath).isDirectory()) {
                continue;
            }
                        
			System.out.println(outPath);
 
            FileOutputStream out = new FileOutputStream(outPath);
            byte[] buf1 = new byte[1024];
            int len;
            while ((len = in.read(buf1)) > 0) {
                out.write(buf1, 0, len);
            }
            in.close();
            out.close();
        }
        
 } catch (IOException e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
        
        return tmp;
    }
 

   
}
