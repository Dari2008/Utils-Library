package javad.utils.autosave;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;

import javad.utils.lists.ListOf3;
import javad.utils.onlyList.OnlyListOf3;
import javad.utils.security.cryptology.AES;
import javad.utils.security.cryptology.Cäsar;
import javad.utils.security.cryptology.EncryptionType;

public class AutoSaves implements Runnable{
	
	private ArrayList<Class<?>> classes = new ArrayList<>();
	
	private ID id;
	
	
	public AutoSaves(ID id) {
		this.id = id;
	}
	
	public void addAutoSave(Class<?>...classes) {
		for(Class<?> c: classes) {
		this.classes.add(c);
		}
	}
	
	public void removeAutoSave(Class<?>...classes) {
		for(Class<?> c : classes) {
			
			this.classes.remove(c);
			
		}
	}
	
	public static void saveAutoSaves(ID id, Class<?>...classes) {
		
		ListOf3<Object, Type, String> ob = new ListOf3<>();
		
		for(Class<?> c : classes) {
			
			for(Method m : c.getMethods()) {
				
				for(AutoSave g : m.getAnnotationsByType(AutoSave.class)) {
					
					try {
						if(g.getterOrSetter() == GetSet.Getter) {
						ob.put(m.invoke(c.newInstance(), null), g.type(), g.name());
						}
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
							| InstantiationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
			
		}
		
		File tmpfile = new File(System.getProperty("java.io.tmpdir") + id.getId() + ".txt");
		System.out.println(tmpfile);
		try {
			tmpfile.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

			try {
				PrintWriter pw = new PrintWriter(tmpfile);
				for(int i = 0; i < ob.size(); i++) {
					pw.println(ob.getB(i));
				}
				pw.println("*************************************");
				for(int i = 0; i < ob.size(); i++) {
					if(ob.getA(i) instanceof String) {
						pw.print((String)ob.getA(i));
						pw.println(" " + ob.getC(i));
					}else if(ob.getA(i) instanceof Integer) {
						pw.print((Integer)ob.getA(i));
						pw.println(" " + ob.getC(i));
					}else if(ob.getA(i) instanceof Float) {
						pw.print((Float)ob.getA(i));
						pw.println(" " + ob.getC(i));
					}else if(ob.getA(i) instanceof Double) {
						pw.print((Double)ob.getA(i));
						pw.println(" " + ob.getC(i));
					}
				}
				pw.close();
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		
	}

	@Override
	public void run() {
		
		for(Class<?> c : classes) {
			saveAutoSaves(id, c);
		}
		
		
		
	}
	
	public void setEncryption(boolean isEncryption, EncryptionType type, String key) {
		
		switch(type) {
		case AES:
			break;
		case Cäser:
			break;
		case UTF_8:
			break;
		default:
			break;
		
		}
		
	}
	
	
	public static void loadAutoSaves(ID id, Class<?>...classes) {
			ListOf3<Object, Type, String> ob = new ListOf3<>();
			ArrayList<Type> tmp = new ArrayList<>();
			ArrayList<Object> otmp = new ArrayList<>();
			ArrayList<String> stmp = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(System.getProperty("java.io.tmpdir") + id.getId() + ".txt"));
			
			while(sc.hasNextLine()) {
				String s = sc.nextLine();
				if(s.equals("*************************************")) {
					break;
				}
				tmp.add(Type.valueOf(s));
			}
			
			
			for(Type t : tmp) {
				switch(t) {
				case Double:
					otmp.add(Double.parseDouble(sc.next()));
					stmp.add(sc.next());
					break;
				case Float:
					otmp.add(Float.parseFloat(sc.next()));
					stmp.add(sc.next());
					break;
				case Integer:
					otmp.add(Integer.parseInt(sc.next()));
					stmp.add(sc.next());
					break;
				case String:
					otmp.add(sc.next()); 
					stmp.add(sc.next());
					break;
				default:
					break;
				
				}
				
			}
			

			
			sc.close();
			for(int i = 0; i < otmp.size(); i++) {
				ob.put(otmp.get(i), tmp.get(i), stmp.get(i));
			}
			
			
//			for(int i = 0; i < ob.size(); i++) {
//				System.out.println(ob.getA(i));
//				System.out.println(ob.getB(i));
//				System.out.println(ob.getC(i));
//			}
			
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		for(Class<?> c : classes) {
			
			for(Method m : c.getDeclaredMethods()) {
				
				for(AutoSave s : m.getDeclaredAnnotationsByType(AutoSave.class)) {
					
					if(s.getterOrSetter() == GetSet.Setter) {
						
						
						
						OnlyListOf3<Object, Type, String> as = ob.get(s.name());
						
						try {
							m.invoke(c.newInstance(), as.getA());
						} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
								| InstantiationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						
					}
					
				}
				
			}
			
		
		
		}
		
		int i = 0;
		
		
	}
}
