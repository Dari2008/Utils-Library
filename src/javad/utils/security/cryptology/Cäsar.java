package javad.utils.security.cryptology;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javad.utils.security.util.LetterFilter;

public class Cäsar {
    private HashMap<Character, Character> verschluesseln;
    private HashMap<Character, Character> entschluesseln;
    
    private ArrayList<LetterFilter> filter = new ArrayList<>();

    public Cäsar(int verschiebung) {
        super();
        verschluesseln = new HashMap<Character, Character>();
        entschluesseln = new HashMap<Character, Character>();
 
        initCaesar(verschiebung);
    }
 

    public String encrypt(String text){
    	text = callFilter(text);
        return uebersetze(text, verschluesseln);
    }
 

    public String decrypt(String text){
        return uebersetze(text,entschluesseln);
    }
 
    public void addLetterFilter(LetterFilter filter) {
		this.filter.add(filter);
	}
	
	private String callFilter(String text) {
		
		if(filter.isEmpty()) {
			return text;
		}
		
		boolean b = true;
		
		String texts = "";
		
		for(LetterFilter l : filter) {
			for(char c : text.toCharArray()) {
			
			b = l.filter(c);
			if(b == true) {
				texts = texts + c;
			}
			
			}
			text = texts;
		}
		return texts;
		
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
    
    private String uebersetze(String text, HashMap<Character, Character> alphabet){
        String keineKleinbuchstaben = text.toUpperCase().replace(" ", "");;
        
        String ausgabe = "";
 
        for(int i=0; i < keineKleinbuchstaben.length(); i++){
        	if(!alphabet.containsKey(keineKleinbuchstaben.charAt(i))) {
        		ausgabe += keineKleinbuchstaben.charAt(i);
        		continue;
        	}
            ausgabe += alphabet.get(keineKleinbuchstaben.charAt(i));
        }
 
        return ausgabe;
    }
 

    private void initCaesar(int verschiebung) {
        for(char schleife='A'; schleife<='Z';schleife++){
            if((schleife+verschiebung) <= 'Z'){
                verschluesseln.put(schleife, (char) (schleife+verschiebung));
                entschluesseln.put((char) (schleife+verschiebung), schleife);
            }else{
                verschluesseln.put(schleife, (char) (schleife+verschiebung-26));
                entschluesseln.put((char) (schleife+verschiebung-26), schleife);
            }
        }
    }
 
}