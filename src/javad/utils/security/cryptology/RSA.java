package javad.utils.security.cryptology;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

import javad.utils.files.Scanner;

public class RSA {

	private KeyPair key;
	
	public static final int intialize = 1024;
	
	public RSA(int initialize) {
		try {
			KeyPairGenerator gener = KeyPairGenerator.getInstance("RSA");
			gener.initialize(initialize);
			
			key = gener.generateKeyPair();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public PublicKey getPublicKeyPair() {
		return key.getPublic();
	}
	
	public PrivateKey getPrivateKeyPair() {
		return key.getPrivate();
	}
	
	public void SaveKeyPair(KeyPair keypair, File PrivateKey, File publicKey) {
		try {
			PrivateKey.createNewFile();
			publicKey.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			ObjectOutputStream outputPublic = new ObjectOutputStream(new FileOutputStream(publicKey));
			ObjectOutputStream outputPrivate = new ObjectOutputStream(new FileOutputStream(PrivateKey));
			outputPrivate.writeObject(PrivateKey);
			outputPublic.writeObject(publicKey);
			outputPrivate.close();
			outputPublic.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public KeyPair getKeyPair(File privateKey, File publicKey) {
		
		KeyPair k = null;
		
		try {
			ObjectInputStream InputPublic = new ObjectInputStream(new FileInputStream(publicKey));
			ObjectInputStream outputPrivate = new ObjectInputStream(new FileInputStream(privateKey));
			PrivateKey kP = (PrivateKey)outputPrivate.readObject();
			PublicKey kPub = (PublicKey)InputPublic.readObject();
			outputPrivate.close();
			InputPublic.close();
			k = new KeyPair(kPub, kP);
					
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return k;
	}

	

	
	    public String encrypt(String text){
	    	try {
				Cipher c = Cipher.getInstance("RSA");
				c.init(c.ENCRYPT_MODE, key.getPublic());
				byte[] ver = c.doFinal(text.getBytes());
				
				return new String(ver);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	        
	    }

	    public String decrypt(String text){
	    	try {
				Cipher c = Cipher.getInstance("RSA");
				c.init(c.DECRYPT_MODE, key.getPrivate());
				byte[] ver = c.doFinal(text.getBytes());
				return new String(ver);
			} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvalidKeyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalBlockSizeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BadPaddingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	return null;
	    }

	
}
