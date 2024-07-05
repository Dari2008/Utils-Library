package javad.utils.security.cryptology;

public class CäsarUTF8 {

	private Cäsar c;
	private UTF8 utf;
	
	public CäsarUTF8(int verschiebung) {
		utf  = new UTF8();
		c = new Cäsar(verschiebung);
		
	}

	public String encrypt(String text) {
			return c.encrypt(utf.encrypt(text));
		}

	public String decrypt(String text) {

		
		return utf.decrypt(c.decrypt(text));
	}
	
}
