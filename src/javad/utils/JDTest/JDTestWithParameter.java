package javad.utils.JDTest;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.text.Annotation;


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface JDTestWithParameter{
String name();

}
