package javad.utils.security.cryptology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Pattern;

import javad.utils.security.util.LetterFilter;

public class UTF8 {

	private HashMap<Character, String> utfver = new HashMap<>();
	private HashMap<String, Character> utfent = new HashMap<>();
	
	private ArrayList<LetterFilter> filter = new ArrayList<>();
	
	public UTF8() {
		
		Scanner sc;
		try {
			sc = new Scanner(new File(System.getProperty("user.dir") + "\\res\\cryptologie\\UTF8.txt"));
		
		
		while (sc.hasNextLine()) {
			String s = sc.nextLine();
			
			String tmp = "";
			Character c = 'c';
			
			if(s.contains("---2d")) {
				tmp = "2d";
				c = '-';
			}else {
				String[] so = s.split("--");
				if(so[1].length() > 3) {
					so[1] = so[1].split(" ")[0] + "\\x" + so[1].split(" ")[1];
				}
				tmp = so[1];
				c = so[0].toCharArray()[0];
			}
			
			
			
			utfent.put(tmp, c);
			utfver.put(c, tmp);
			
			
		}
		
		sc.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void addLetterFilter(LetterFilter filter) {
		this.filter.add(filter);
	}
	
	private boolean callFilter(char c) {
		
		if(filter.isEmpty()) {
			return true;
		}
		
		boolean b = true;
		
		for(LetterFilter l : filter) {
			if(!b) {
				break;
			}else {
			b = l.filter(c);
			}
		}
		return b;
		
	}
	
	
public String encrypt(String text) {
		
		//verschlüsseln
		
		String txt = text;
		
		char[] c = txt.toCharArray();
		
		
		
		String tmp = "";
		
		for(char cs : c) {
			if(utfver.containsKey(cs) && callFilter(cs)) {
				
				tmp = tmp + "\\x" + utfver.get(cs);
				
			}else if(!callFilter(cs)){
				continue;
			}else {
				throw new IllegalArgumentException();
			}
			
		}
		
		
		
		return tmp;
		
	}

public boolean saveString(File file, String save, boolean flush) {
	
	if(!file.exists()) {
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			PrintWriter pw = new PrintWriter(file);
			if(flush) {
				pw.flush();
			}
			pw.println(save);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	return true;
		
	}else {


		try {
			PrintWriter pw = new PrintWriter(file);
			if(flush) {
				pw.flush();
			}
			pw.println(save);
			pw.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	return false;
	}
	
}

public String getSave(File file, int line) {
	String s = null ;
	try {
		Scanner sc = new Scanner(file);
		while(line >= 0) {
			line--;
		s = sc.nextLine();
		}
		sc.close();
	} catch (FileNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return s;
	
}

public String decrypt(String text) {
	
//	verschlüsseln

	text = text.replaceFirst(Pattern.quote("\\"), "");
	text = text.replaceFirst("x", "");
	
	String tmp = replaceAll("\\x", "x", text);
	
	String[] tmp2 = tmp.split("x");
	
	
	ArrayList<String> arg = new ArrayList<>();
	
	for(int i = 0; i < tmp2.length; i++) {
		arg.add(tmp2[i]);
	}
	
	
	
	boolean is = false;
	ArrayList<String> arg2 = new ArrayList<>();
	String a = "";
	for(int i = 0; i < arg.size(); i++) {
		if(arg.get(i).toCharArray()[0] == 'c' && !is) {
			a = arg.get(i);
			is = true;
			continue;
		}else if(arg.get(i).toCharArray()[0] != 'c' && !is){
			arg2.add(arg.get(i));
			continue;
		}else if(is) {
			a = a + "\\x" + arg.get(i);
			arg2.add(a);
			is = false;
		}
		
		
	}
	
	String ergebnis = "";
	
	for(int i = 0; i < arg2.size(); i++) {
		
		if(utfent.containsKey(arg2.get(i))) {
			
			ergebnis = ergebnis + utfent.get(arg2.get(i));
			
		}else {
			throw new IllegalArgumentException();
		}
		
	}
	
	return ergebnis;
	
}
	
private String replaceAll(String replacement, String mitWas, String was) {
	String tmp = "";
	boolean is = false;
	for(char c : was.toCharArray()) {
		
		if(c == replacement.toCharArray()[0] && is == false) {
			is = true;
			continue;
		}else if(is == false){
			tmp = tmp + c + "";
			continue;
		}
		if(c == replacement.toCharArray()[1] && is == true) {
			is = false;
			tmp = tmp + mitWas;
			continue;
		}else {
			is = false;
		}
		
	}
	
	return tmp;
}
	
}
