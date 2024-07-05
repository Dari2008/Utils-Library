package javad.utils.JDTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import javad.utils.JDTest.Exception.JDTestException;
import javad.utils.lists.ListOf2;

@SuppressWarnings("rawtypes")
public class JDTests {

	private static ArrayList<Method> methods = new ArrayList<>();
	private static ArrayList<Method> methodstmp = new ArrayList<>();
	private static ArrayList<Class> classes = new ArrayList<>();
	private static ArrayList<Class> classestmp = new ArrayList<>();
	
	public static void runJDTestWithImportance(Class...clases) throws JDTestException{

		methods = new ArrayList<>();
		methodstmp = new ArrayList<>();
		classes = new ArrayList<>();
		classestmp = new ArrayList<>();
		
		ArrayList<JDTestWithImportance> i = new ArrayList<>();
		
		for(Class c : clases) {
			
			for(Method meth : c.getMethods()) {
				
				for(JDTestWithImportance JDTest : meth.getDeclaredAnnotationsByType(JDTestWithImportance.class)) {
					
					methodstmp.add(meth);
					classestmp.add(c);
					i.add(JDTest);
				}
				
			}
			
		}
		
		sortImportance(methodstmp, classestmp, i);
		
		for(int i1 = 0; i1 < methods.size(); i1++) {
			
			try {
				methods.get(i1).invoke(classes.get(i1).newInstance(), null);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				e.printStackTrace();
			}catch(IllegalArgumentException e) {
				throw new JDTestException("The Methode " + i.get(i1).name() + " ( " + methods.get(i1).getName() + " ) has a parameter");
			}
			
		}
		
	}

	public static void runJDTestWithImportanceLevel(Class...clases) throws JDTestException {
		methods = new ArrayList<>();
		methodstmp = new ArrayList<>();
		classes = new ArrayList<>();
		classestmp = new ArrayList<>();
		
		ArrayList<JDTestWithIportanceLevel> i = new ArrayList<>();
		
		for(Class c : clases) {
			
			for(Method meth : c.getMethods()) {
				
				for(JDTestWithIportanceLevel JDTest : meth.getDeclaredAnnotationsByType(JDTestWithIportanceLevel.class)) {
					
					methodstmp.add(meth);
					classestmp.add(c);
					i.add(JDTest);
				}
				
			}
			
		}
		
		
		sortImportanceLevel(methodstmp, classestmp, i);
		
		
		methods = methodstmp;
		
		for(int i1 = 0; i1 < methods.size(); i1++) {
			
			try {
				methods.get(i1).invoke(classes.get(i1).newInstance(), null);
			} catch (IllegalAccessException | InvocationTargetException e) {
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(IllegalArgumentException e) {
				throw new JDTestException("The Methode " + i.get(i1).name() + " ( " + methods.get(i1).getName() + " ) has a parameter");
			}
			
		}
	
	}
	
	public static void runJDTest(Class...classes) {

		ArrayList<Method> ms = new ArrayList<>();
		ArrayList<Class> mc = new ArrayList<>();
		
		for(Class c : classes) {
			
			for(Method m : c.getMethods()) {
				
				for(JDTest t : m.getAnnotationsByType(JDTest.class)) {
					
					ms.add(m);
					mc.add(c);
					
				}
				
			}
			
		}

		
		for(int i = 0; i < ms.size(); i++) {
			try {
				ms.get(i).invoke(mc.get(i).newInstance(), null);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}	
	
	@SuppressWarnings("deprecation")
	public static void runJDTestWithParameter(ListOf2<String, Object[]> parameter, Class...classes) throws JDTestException {
		ArrayList<Method> ms = new ArrayList<>();
		ArrayList<Class> mc = new ArrayList<>();
		ArrayList<JDTestWithParameter> pa = new ArrayList<>();
		
		for(Class c : classes) {
			
			for(Method m : c.getMethods()) {
				
				for(Annotation t : m.getAnnotationsByType(JDTestWithParameter.class)) {
					if(t instanceof JDTestWithParameter) {
					pa.add((JDTestWithParameter)t);
					ms.add(m);
					mc.add(c);
					}
					
				}
				
			}
			
		}

		
		for(int i = 0; i < ms.size(); i++) {
			if(ms.get(i).getName().equals(parameter.getA(i))) {
			try {
				
				switch(parameter.getB(i).length) {
				case 1:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0]);
					break;
				case 2:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0], parameter.getB(i)[1]);;
					break;
				case 3:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0], parameter.getB(i)[1], parameter.getB(i)[2]);
					break;
				case 4:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0], parameter.getB(i)[1], parameter.getB(i)[2], parameter.getB(i)[3]);
					break;
				case 5:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0], parameter.getB(i)[1], parameter.getB(i)[2], parameter.getB(i)[3], parameter.getB(i)[4]);
					break;
				case 6:
					ms.get(i).invoke(mc.get(i).newInstance(), parameter.getB(i)[0], parameter.getB(i)[1], parameter.getB(i)[2], parameter.getB(i)[3], parameter.getB(i)[4], parameter.getB(i)[5]);
					break;
					default: 
						break;
				}
				
				
				
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}else {
				System.out.println(ms.get(i).getName());
				System.out.println(parameter.getA(i));
			}
		}		
	}

	
	private static void sortImportanceLevel(ArrayList<Method> methodstmp2, ArrayList<Class> classestmp2, ArrayList<JDTestWithIportanceLevel> JDTest) {


		
		ArrayList<Method> importance1 = new ArrayList<>();
		ArrayList<Method> importance2 = new ArrayList<>();
		ArrayList<Method> importance3 = new ArrayList<>();
		ArrayList<Method> importance4 = new ArrayList<>();
		ArrayList<Method> importance5 = new ArrayList<>();
		ArrayList<Method> importance6 = new ArrayList<>();
		ArrayList<Method> importance7 = new ArrayList<>();
		ArrayList<Method> importance8 = new ArrayList<>();
		ArrayList<Method> importance9 = new ArrayList<>();
		ArrayList<Method> importance10 = new ArrayList<>();
		ArrayList<Method> importance11 = new ArrayList<>();
		
		ArrayList<Class> cimportance1 = new ArrayList<>();
		ArrayList<Class> cimportance2 = new ArrayList<>();
		ArrayList<Class> cimportance3 = new ArrayList<>();
		ArrayList<Class> cimportance4 = new ArrayList<>();
		ArrayList<Class> cimportance5 = new ArrayList<>();
		ArrayList<Class> cimportance6 = new ArrayList<>();
		ArrayList<Class> cimportance7 = new ArrayList<>();
		ArrayList<Class> cimportance8 = new ArrayList<>();
		ArrayList<Class> cimportance9 = new ArrayList<>();
		ArrayList<Class> cimportance10 = new ArrayList<>();
		ArrayList<Class> cimportance11 = new ArrayList<>();
		

		
		for(int i = 0; i < methodstmp2.size(); i++) {
			
			if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_1) {
				importance1.add(methodstmp2.get(i));
				cimportance1.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_2) {
				importance2.add(methodstmp2.get(i));
				cimportance2.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_3) {
				importance3.add(methodstmp2.get(i));
				cimportance3.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_4) {
				importance4.add(methodstmp2.get(i));
				cimportance4.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_5) {
				importance5.add(methodstmp2.get(i));
				cimportance5.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_6) {
				importance6.add(methodstmp2.get(i));
				cimportance6.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_7) {
				importance7.add(methodstmp2.get(i));
				cimportance7.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_8) {
				importance8.add(methodstmp2.get(i));
				cimportance8.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_9) {
				importance9.add(methodstmp2.get(i));
				cimportance9.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_10) {
				importance10.add(methodstmp2.get(i));
				cimportance10.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance_Level() == ImportanceLevel.Importance_Level_11) {
				importance11.add(methodstmp2.get(i));
				cimportance11.add(classestmp2.get(i));
			}
			
			
		}
		for(Method m : importance1) {
			methods.add(m);
			}
		for(Method m : importance2) {
			methods.add(m);
			}
		for(Method m : importance3) {
			methods.add(m);
			}
		for(Method m : importance4) {
			methods.add(m);
			}
		for(Method m : importance5) {
			methods.add(m);
			}
		for(Method m : importance6) {
			methods.add(m);
			}
		for(Method m : importance7) {
			methods.add(m);
			}
		for(Method m : importance8) {
			methods.add(m);
			}
		for(Method m : importance9) {
			methods.add(m);
			}
		for(Method m : importance10) {
			methods.add(m);
			}
		for(Method m : importance11) {
			methods.add(m);
			}
		
		
		for(Class m : cimportance1) {
			classes.add(m);
			}
		for(Class m : cimportance2) {
			classes.add(m);
			}
		for(Class m : cimportance3) {
			classes.add(m);
			}
		for(Class m : cimportance4) {
			classes.add(m);
			}
		for(Class m : cimportance5) {
			classes.add(m);
			}
		for(Class m : cimportance6) {
			classes.add(m);
			}
		for(Class m : cimportance7) {
			classes.add(m);
			}
		for(Class m : cimportance8) {
			classes.add(m);
			}
		for(Class m : cimportance9) {
			classes.add(m);
			}
		for(Class m : cimportance10) {
			classes.add(m);
			}
		for(Class m : cimportance11) {
			classes.add(m);
			}
		
		System.out.println(methods);
	}
	
	
	private static void sortImportance(ArrayList<Method> methodstmp2, ArrayList<Class> classestmp2, ArrayList<JDTestWithImportance> JDTest) {

		ArrayList<Method> importanceVerryLow = new ArrayList<>();
		ArrayList<Method> importanceLow = new ArrayList<>();
		ArrayList<Method> importanceMedium = new ArrayList<>();
		ArrayList<Method> importanceHigh = new ArrayList<>();
		ArrayList<Method> importanceVerry_High = new ArrayList<>();
		
		ArrayList<Class> ClassimportanceVerryLow = new ArrayList<>();
		ArrayList<Class> ClassimportanceLow = new ArrayList<>();
		ArrayList<Class> ClassimportanceMedium = new ArrayList<>();
		ArrayList<Class> ClassimportanceHigh = new ArrayList<>();
		ArrayList<Class> ClassimportanceVerry_High = new ArrayList<>();
		
		for(int i = 0; i < methodstmp2.size(); i++) {
			
			if(JDTest.get(i).importance() == Importance.VERRY_LOW) {
				importanceVerryLow.add(methodstmp2.get(i));
				ClassimportanceVerryLow.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance() == Importance.LOW) {
				importanceLow.add(methodstmp2.get(i));
				ClassimportanceLow.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance() == Importance.MEDIUM) {
				importanceMedium.add(methodstmp2.get(i));
				ClassimportanceMedium.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance() == Importance.HIGHT) {
				importanceHigh.add(methodstmp2.get(i));
				ClassimportanceHigh.add(classestmp2.get(i));
			}else if(JDTest.get(i).importance() == Importance.VERRY_HIGHT) {
				importanceVerry_High.add(methodstmp2.get(i));
				ClassimportanceVerry_High.add(classestmp2.get(i));
			}
			
		}
		for(Method m : importanceVerry_High) {
			methods.add(m);
			}
		for(Method m : importanceHigh) {
			methods.add(m);
			}
		for(Method m : importanceMedium) {
			methods.add(m);
			}
		for(Method m : importanceLow) {
			methods.add(m);
			}
		for(Method m : importanceVerryLow) {
			methods.add(m);
			}
		
		for(Class m : ClassimportanceVerry_High) {
			classes.add(m);
			}
		for(Class m : ClassimportanceHigh) {
			classes.add(m);
			}
		for(Class m : ClassimportanceMedium) {
			classes.add(m);
			}
		for(Class m : ClassimportanceLow) {
			classes.add(m);
			}
		for(Class m : ClassimportanceVerryLow) {
			classes.add(m);
			}
		
	}
	
}
