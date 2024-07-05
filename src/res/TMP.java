package res;

import java.io.InputStream;
import java.net.URL;

public class TMP {

	public static InputStream getStream(String s) {
		return TMP.class.getResourceAsStream(s);
	}
	
}
