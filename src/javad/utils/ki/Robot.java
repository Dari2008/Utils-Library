package javad.utils.ki;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.MultiResolutionImage;

public class Robot {
	
public static void moveMouse(int x, int y) {
		
		try {
			java.awt.Robot r = new java.awt.Robot();
			r.mouseMove(x, y);
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

public static void pressMouseButton(int button) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		r.mousePress(button);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void releaseMouseButton(int button) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		r.mouseRelease(button);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public static void releaseKey(int button) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		r.keyRelease(button);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	public static void pressKey(int button) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		r.keyPress(button);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

	public static Color getPixelAt(int x, int y) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		return r.getPixelColor(x, y);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return null;
	
}

	public static void delay(int delay) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		r.delay(delay);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
	

	public static MultiResolutionImage createMultiResolutionScreenCapture(Rectangle screen) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		
	
	return r.createMultiResolutionScreenCapture(screen);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
	}
	


	public static BufferedImage createScreenCapture(Rectangle screen) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		
	
	return r.createScreenCapture(screen);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	return null;
	
	}
	

	public static void wheelMouse(int wheel) {
	
	try {
		java.awt.Robot r = new java.awt.Robot();
		
	
	r.mouseWheel(wheel);
	} catch (AWTException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}

	public static Point getMouse(){
		return MouseInfo.getPointerInfo().getLocation();
	}

	public static int getMouseButtons(){
		return MouseInfo.getNumberOfButtons();
	}
	


}
