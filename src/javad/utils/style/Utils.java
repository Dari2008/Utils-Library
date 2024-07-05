package javad.utils.style;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class Utils {


	public static final int FLIP_HORIZONTAL = 1;
	public static final int FLIP_VERTICAL = 0;
	
	public void drawCenteredString(Graphics2D g, String s, int w, int h, Font font) {
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
			return new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
		}
		return new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
	}
	
	
	public static BufferedImage flip(BufferedImage image, int dir){
		BufferedImage b = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
	   
		for(int y = 0; y < b.getHeight(); y++) {
			for(int x = 0; x < b.getWidth(); x++) {
				switch(dir) {
					case FLIP_HORIZONTAL:
						b.setRGB(image.getWidth()-1-x, y, image.getRGB(x, y));
						break;
					case FLIP_VERTICAL:
						b.setRGB(x, image.getHeight()-1-y, image.getRGB(x, y));
						break;
					default:
						throw new IllegalArgumentException("Direction: " + dir);
				}
			}
		}
		
	    return b;
	}
	
	public static BufferedImage resizeImage(BufferedImage originalImage, int targetWidth, int targetHeight){
	    BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
	    Graphics2D graphics2D = resizedImage.createGraphics();
	    graphics2D.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
	    graphics2D.dispose();
	    return resizedImage;
	}
	
	
}
