package javad.utils.security.cryptology;

public class UTF8C�sar {

	private C�sar c;
	private UTF8 utf;
	
	public UTF8C�sar(int verschiebung) {
		utf  = new UTF8();
		c = new C�sar(verschiebung);
		
	}

	public String encrypt(String text) {
			return utf.encrypt(c.encrypt(text));
		}

	public String decrypt(String text) {

	
		return c.decrypt(utf.decrypt(text));
	}
	
}
