package javad.utils.security.cryptology;

public class C�sarUTF8 {

	private C�sar c;
	private UTF8 utf;
	
	public C�sarUTF8(int verschiebung) {
		utf  = new UTF8();
		c = new C�sar(verschiebung);
		
	}

	public String encrypt(String text) {
			return c.encrypt(utf.encrypt(text));
		}

	public String decrypt(String text) {

		
		return utf.decrypt(c.decrypt(text));
	}
	
}
