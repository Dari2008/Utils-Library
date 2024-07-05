package javad.utils.JDTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Comparator;

public class Sort implements Comparator<Method>{

	@Override
	public int compare(Method o1, Method o2) {
		

		System.out.println(o1.getName() + " : " + o2.getName());
		//methodstmp.get(0).getDeclaredAnnotationsByType(JDTestWithIportanceLevel.class)
		System.out.println(o1.getAnnotationsByType(JDTestWithIportanceLevel.class)[0].importance_Level().toString());

		JDTestWithIportanceLevel lvl1 = o1.getAnnotationsByType(JDTestWithIportanceLevel.class)[0];
		JDTestWithIportanceLevel lvl2 = o1.getAnnotationsByType(JDTestWithIportanceLevel.class)[0];

		System.out.println(Integer.valueOf(lvl1.importance_Level().toString().split("_")[2]) < Integer.valueOf(lvl2.importance_Level().toString().split("_")[2]));
		
		if(Integer.valueOf(lvl1.importance_Level().toString().split("_")[2]) < Integer.valueOf(lvl2.importance_Level().toString().split("_")[2])) {
			return -1;
		}else if(Integer.valueOf(lvl1.importance_Level().toString().split("_")[2]) > Integer.valueOf(lvl2.importance_Level().toString().split("_")[2])){
			return 1;
		}else {
			return 0;
		}
		
		
	}

	

}
