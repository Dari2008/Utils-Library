package javad.utils.security.cryptology;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;


public class AES {

	private final String methode = "AES";
	private byte[] keyValue;
	public static final String key = "QueZxsweqqER39zZ";

	
	public AES(String key) {
		this.keyValue = key.getBytes();
	}
	
	public String encrypt(String text){
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(methode);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(text.getBytes());
			String encryptedVal = Base64.getEncoder().encodeToString(encVal);
			return encryptedVal;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}
	
	public byte[] encrypt(byte[] text){
		try {
			Key key = generateKey();
			Cipher c = Cipher.getInstance(methode);
			c.init(Cipher.ENCRYPT_MODE, key);
			byte[] encVal = c.doFinal(text);
			String encryptedVal = Base64.getEncoder().encodeToString(encVal);
			return encryptedVal.getBytes();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
		
	}

public byte[] decrypt(byte[] text) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(methode);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(text);
		byte[] encVal = c.doFinal(decodedValue);
		
		return encVal;
}


public String decrypt(String text) throws Exception{
		Key key = generateKey();
		Cipher c = Cipher.getInstance(methode);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decodedValue = Base64.getDecoder().decode(text);
		byte[] encVal = c.doFinal(decodedValue);
		
		return new String(encVal);
}
	
public Key generateKey() {
	return new SecretKeySpec(keyValue, methode);
}

}
