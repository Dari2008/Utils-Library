package javad.utils.security.cryptology;

public class UTF8Cäsar {

	private Cäsar c;
	private UTF8 utf;
	
	public UTF8Cäsar(int verschiebung) {
		utf  = new UTF8();
		c = new Cäsar(verschiebung);
		
	}

	public String encrypt(String text) {
			return utf.encrypt(c.encrypt(text));
		}

	public String decrypt(String text) {

	
		return c.decrypt(utf.decrypt(text));
	}
	
}
