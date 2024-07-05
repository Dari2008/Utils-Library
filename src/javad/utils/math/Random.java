package javad.utils.math;

public class Random{

	public static int nextInt(int min, int max) {
		java.util.Random r = new java.util.Random();
		int i = 0;
		while((i = r.nextInt(max + 1)) < min) {}
		return i;
		
	}


	
	public static Double nextDouble(Double min, Double max) {
		
		if(min >= 1.0 || max >= 1.0 || min <= 0.0 || max <= 0.0) {
			throw new IllegalArgumentException("min oder max dürfen nicht kleiner oder größer als 1.0 oder 0.0 sein max: " + max + " min: " + min);
		}
		
		java.util.Random r = new java.util.Random();
		Double i = 0.0;
		while((i = r.nextDouble()) < min || i > max) {}
		
		return i;
		
}

	public static float nextFloat(float min, float max) {
		
		if(min >= 1.0 || max >= 1.0 || min <= 0.0 || max <= 0.0) {
			throw new IllegalArgumentException("min oder max dürfen nicht kleiner oder größer als 1.0 oder 0.0 sein max: " + max + " min: " + min);
		}
		
		java.util.Random r = new java.util.Random();
		float i = 0.0f;
		while((i = r.nextFloat()) < min || i > max) {}
		
		return i;
		
	}


	
	public static String nextString(int lenght, String lettersAndSymbols) {
		String result = "";
		for(int i = 0; i < lenght; i++) {
			result += lettersAndSymbols.charAt(nextInt(0, lettersAndSymbols.length()-1));
		}
		return result;
	}
	
	public static String nextString(int lenght) {
		String result = "";
		for(int i = 0; i < lenght; i++) {
			result += (char)nextInt(0, 1000);
		}
		return result;
	}

}
