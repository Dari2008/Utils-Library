import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

import javad.utils.security.cryptology.AES;

public class CryptoGraphy {

	private static String[] aesKeys = new String[]{};
	
	public static String verschluesseln(String s) {
		String kurrent = s;
		for(String key : aesKeys) {
			try {
				AES a = new AES(key);
				kurrent = a.encrypt(kurrent);
			}catch(Exception e) {
				System.out.println(key);
			}
		}
		return kurrent;
	}

	
	public static String entschluesseln(String s) {
		String kurrent = s;
		for(int i = aesKeys.length-1; i >= 0; i--) {
			AES a = new AES(aesKeys[i]);
			try {
				kurrent = a.decrypt(kurrent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kurrent;
	}
	
	private static boolean wasSet = false;
	
	public static void setAESKeys(String[] aes) {
		if(!wasSet) {
			aesKeys = aes;
		}
	}
	
	public static byte[] verschluesseln(byte[] s) {
		byte[] kurrent = s;
		for(String key : aesKeys) {
			try {
				AES a = new AES(key);
				kurrent = a.encrypt(kurrent);
			}catch(Exception e) {
				System.out.println(key);
			}
		}
		return kurrent;
	}
	
	public static byte[] entschluesseln(byte[] s) {
		byte[] kurrent = s;
		for(int i = aesKeys.length-1; i >= 0; i--) {
			AES a = new AES(aesKeys[i]);
			try {
				kurrent = a.decrypt(kurrent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return kurrent;
	}
	
	
    public static void entschluesseln(File inputFile, File outputFile){
    	try {
        	FileInputStream in = new FileInputStream(inputFile);
			FileOutputStream out = new FileOutputStream(outputFile);
			byte[] bytes = new byte[0];
			while(in.available() > 0) {
				byte[] input = new byte[in.available()];
				in.read(input);
				int start = bytes.length;
				bytes = Arrays.copyOf(bytes, bytes.length + input.length);
				
				for(int i = start; i < bytes.length; i++) {
					bytes[i] = input[i-start];
				}
				
				
			}

			bytes = entschluesseln(bytes); 
			out.write(bytes);
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void verschluesseln(File inputFile, File outputFile){
    	try {
        	FileInputStream in = new FileInputStream(inputFile);
			FileOutputStream out = new FileOutputStream(outputFile);
			
			byte[] bytes = new byte[0];
			while(in.available() > 0) {
				byte[] input = new byte[in.available()];
				in.read(input);
				int start = bytes.length;
				bytes = Arrays.copyOf(bytes, bytes.length + input.length);
				
				for(int i = start; i < bytes.length; i++) {
					bytes[i] = input[i-start];
				}
				
				
			}
			
			bytes = verschluesseln(bytes); 
			out.write(bytes);
			
			in.close();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
   
}
