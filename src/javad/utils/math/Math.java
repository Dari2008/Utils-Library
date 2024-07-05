package javad.utils.math;

public final class Math {

	public static final double PI = 3.141592653589793d;
	
	public static float getSurfaceAreaTriangle(float g, float h) {
		return (g*h)/2;
	}
	
	public static float getSurfaceAreaParallelogramm(float g, float h) {
		return g*h;
	}
	
	public static float getSurfaceAreaTrapez(float a, float c, float h) {
		return (a+c)/2*h;
	}
	
	public static float getSurfaceAreaDrachen(float e, float f) {
		return (float) (0.5f * e * f);
	}
	
	public static float getSurfaceAreaKreis(float r) {
		return (float) (PI * java.lang.Math.pow(r, 2));
	}
	
	public static float getSurfaceAreaKreissection(float r, float alpha) {
		return getSurfaceAreaKreis(r) * (alpha/360);
	}
	
	public static float getSurfaceAreaKreisring(float r1, float r2) {
		return (float) (PI * (pow(r2, 2) - pow(r1, 2)));
	}
	
	public static float getVolumeWuerfel(float a) {
		return a*a*a;
	}
	
	public static float getVolumeQuader(float a, float b, float c) {
		return a*b*c;
	}
	
	public static float getVolumePrisma(float G, float h) {
		return G*h;
	}
	
	public static float getVolumeZylinder(float r, float h) {
		return getSurfaceAreaKreis(r)*h;
	}
	
	public static float getVolumePyramide(float G, float h) {
		return 1/3*G*h;
	}
	
	public static float getVolumeQuaderPyramide(float a, float h) {
		return 1/3*a*a*h;
	}
	
	public static float getVolumeKegel(float r, float h) {
		return (float) (1/3*PI*pow(r, 2)*h);
	}
	
	public static float getVolumeKugel(float r) {
		return (float) (4/3*PI*pow(r, 3));
	}
	
	public static double pow(float x, float h) {
		return java.lang.Math.pow(x, h);
	}
	
	public static void main(String[] args) {
		System.out.println(java.lang.Math.PI);
	}
	
}
