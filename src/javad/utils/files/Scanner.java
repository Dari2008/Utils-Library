package javad.utils.files;

import java.io.File;
import java.io.FileNotFoundException;

public class Scanner {

public static String nextLine(File file, int line) {
		
		try {
			java.util.Scanner sc = new java.util.Scanner(file);
			String s = null;
			while(line > 0) {
				line--;
				s = sc.nextLine();
			}
			sc.close();
			return s;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

public static String nextWord(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		String s = null;
		while(word > 0) {
			word--;
			s = sc.next();
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	return null;
}

public static boolean nextBoolean(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		boolean s = false;
		while(word > 0) {
			word--;
			s = sc.nextBoolean();
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}

public static int nextInt(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		int s = 0;
		while(word > 0) {
			word--;
			s = sc.nextInt();
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}

public static double nextDouble(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		double s = 0;
		while(word > 0) {
			word--;
			s = sc.nextDouble();
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}

public static byte nextByte(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		byte s = 0;
		while(word > 0) {
			word--;
			s = sc.nextByte();
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}

public static byte nextByte(File file, int word, int radix) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		byte s = 0;
		while(word > 0) {
			word--;
			s = sc.nextByte(radix);
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}

public static float nextFloat(File file, int word) {
	
	try {
		java.util.Scanner sc = new java.util.Scanner(file);
		float s = 0;
		while(word > 0) {
			word--;
			if(sc.hasNextFloat()) {
			s = sc.nextFloat();
			}
			
		}
		sc.close();
		return s;
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	}
	throw new Error();
}





}
