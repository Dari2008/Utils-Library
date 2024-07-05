package javad.utils.math;

import java.awt.Rectangle;

public class Util {

	public static float map(float x, float in_min, float in_max, float out_min, float out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	public static long map(long x, long in_min, long in_max, long out_min, long out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	public static double map(double x, double in_min, double in_max, double out_min, double out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	public static int map(int x, int in_min, int in_max, int out_min, int out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}

	
	public static boolean circleCollides(Circle c1, Rectangle r1) {
	    float closestX = clamp(c1.x, r1.x, r1.x + r1.width);
	    float closestY = clamp(c1.y, r1.y - r1.height, r1.y);
	 
	    float distanceX = c1.x - closestX;
	    float distanceY = c1.y - closestY;
	 
	    return java.lang.Math.pow(distanceX, 2) + java.lang.Math.pow(distanceY, 2) < java.lang.Math.pow(c1.radius, 2);
	}
	 
	public static float clamp(float value, float min, float max) {
	    float x = value;
	    if (x < min) {
	        x = min;
	    } else if (x > max) {
	        x = max;
	    }
	    return x;
	}
	
	
    public static double[][] calculateTrianglePoints(double[] sides) {
        double a = sides[0];
        double b = sides[1];
        double c = sides[2];
        
        double[] pointA = {0, 0};
        double[] pointB = {c, 0};
        double[] pointC = calculatePointC(a, b, c);
        
        double[][] trianglePoints = {pointA, pointB, pointC};
        return trianglePoints;
    }
    
    public static double[] calculatePointC(double a, double b, double c) {
        double angleC = java.lang.Math.acos((a * a + b * b - c * c) / (2 * a * b));
        double x = a * java.lang.Math.cos(angleC);
        double y = a * java.lang.Math.sin(angleC);
        
        double[] pointC = {x, y};
        return pointC;
    }
	
	public static class Circle{
		
		public int x,y,radius;
		
		public Circle(int x, int y, int radius) {
			this.x = x;
			this.y = y;
			this.radius = radius;
		}
		
	}
	
}
