package javad.utils.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import javad.utils.math.Random;
import javad.utils.style.Utils;

public abstract class Graphics extends Graphics2D{

	public Dimension getStringDimensions(String text) {
		Rectangle2D r = Utils.getStringDimension(text, getFont());
		return new Dimension((int)r.getWidth(), (int)r.getHeight());
	}
	
	public static Dimension getStringDimensions(String text, Font f) {
		Rectangle2D r = Utils.getStringDimension(text, f);
		return new Dimension((int)r.getWidth(), (int)r.getHeight());
	}
	
	public static BufferedImage rotateImage(BufferedImage img, double deg) {
		return javad.utils.graphics.GraphicsUtils.rotateImageByDegrees(img, deg);
	}
	
	public static BufferedImage resizeImage(BufferedImage img, int width, int height) {
		return Utils.resizeImage(img, width, height);
	}
	
	public static BufferedImage flip(BufferedImage img, int dir) {
		return Utils.flip(img, dir);
	}
	
	public void drawCenteredString(String s, int w, int h, Font font) {
		int wT = (int) getStringDimension(s, getFont()).getWidth();
		int hT = (int) getStringDimension(s, getFont()).getHeight();
		drawString(s, (w/2)-(wT/2), (h/2)-(hT/2));
	}
	
	public void drawXCenteredString(String s, int w, Font font, int y) {
		int wT = (int) getStringDimension(s, getFont()).getWidth();
		drawString(s, (w/2)-(wT/2), y);
	}
	
	public void drawYCenteredString(String s, int h, Font font, int x) {
		int hT = (int) getStringDimension(s, getFont()).getHeight();
		drawString(s, x, (h/2)-(hT/2));
	}


	public static final int FLIP_HORIZONTAL = 1;
	public static final int FLIP_VERTICAL = 0;
	
	public static void drawCenteredString(Graphics2D g, String s, int w, int h, Font font) {
		FontMetrics m = g.getFontMetrics();
		int wT = (int) m.getStringBounds(s, g).getWidth();
		int hT = (int) m.getStringBounds(s, g).getHeight();
		
		g.drawString(s, (w/2)-(wT/2), (h/2)-(hT/2));
	}
	
	public static void drawXCenteredString(Graphics2D g, String s, int w, Font font, int y) {
		FontMetrics m = g.getFontMetrics();
		int wT = (int) m.getStringBounds(s, g).getWidth();

		g.drawString(s, (w/2)-(wT/2), y);
	}
	
	public static Rectangle2D getStringDimension(String text, Font f) {
		return (new FontMetrics(f) {
		}).getStringBounds(text, null);
	}
	
	public static void drawYCenteredString(Graphics2D g, String s, int h, Font font, int x) {
		FontMetrics m = g.getFontMetrics();
		int hT = (int) m.getStringBounds(s, g).getHeight();
		
		g.drawString(s, x, (h/2)-(hT/2));
	}
	
	public static int getCenter(int w, int tw) {
		return ((w/2)-(tw/2));
	}
	
	public static Color brighter(Color c, float factor)
	{
	    int r = c.getRed();
	    int g = c.getGreen();
	    int b = c.getBlue();
	    r = (int)(r * factor);
	    g = (int)(g * factor);
	    b = (int)(b * factor);
	    r = Math.min(255,r);
	    g = Math.min(255,g);
	    b = Math.min(255,b);
	    return new Color(checkColorRandge(r),checkColorRandge(g),checkColorRandge(b));
	}
	
	 public static Color darker(Color c, float f) {
		 	
		 	Color cc = new Color(checkColorRandge(Math.max((int)(c.getRed()  *f), 0)),
		 			checkColorRandge(Math.max((int)(c.getGreen()*f), 0)),
		 			checkColorRandge(Math.max((int)(c.getBlue() *f), 0)),
		 			checkColorRandge(c.getAlpha()));
	        return cc;
	    }
	 
	 public static int checkColorRandge(int s) {
		 if(s > 255) {
			 return 255;
		 }else if(s < 0) {
			 return 0;
		 }else {
			 return s;
		 }
	 }
	 
	 public static Color setBrightness(Color color, float brightness) {
	        if (brightness < 0f || brightness > 1f) {
	            throw new IllegalArgumentException("invalid brightness value");
	        }

	        int alpha = color.getAlpha();

	        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(),
	                color.getBlue(), null);
	        Color c = Color.getHSBColor(hsb[0], hsb[1], brightness);

	        return setAlpha(c, alpha);
	    }
	 
	 public static Color setAlpha(Color color, int alpha) {
	        if (alpha < 0 || alpha > 255) {
	            throw new IllegalArgumentException("invalid alpha value");
	        }

	        return new Color(color.getRed(), color.getGreen(), color.getBlue(),
	                alpha);
	    }
	 
	 public static int getBrightness(Color color) {
	        // original coefficients
	        final double cr = 0.241;
	        final double cg = 0.691;
	        final double cb = 0.068;
	        // another set of coefficients
	        //      final double cr = 0.299;
	        //      final double cg = 0.587;
	        //      final double cb = 0.114;

	        double r, g, b;
	        r = color.getRed();
	        g = color.getGreen();
	        b = color.getBlue();

	        // compute the weighted distance
	        double result = Math.sqrt(cr * r * r + cg * g * g + cb * b * b);

	        return (int) result;
	    }

	public static Color generateRandomColor(boolean widthRandomAlpha) {
		// TODO Auto-generated method stub
		if(widthRandomAlpha) {
			return new Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255));
		}
		return new Color(Random.nextInt(0, 255), Random.nextInt(0, 255), Random.nextInt(0, 255));
	}
	
	
	
	
	


}
