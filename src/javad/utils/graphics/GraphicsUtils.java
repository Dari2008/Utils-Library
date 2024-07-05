package javad.utils.graphics;

import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class GraphicsUtils {

	public static void drawArrow(Graphics2D g1, double x1, double y1, double x2, double y2, double distanceToEnd, boolean endObenLinks) {
	    Graphics2D ga = (Graphics2D) g1.create();
	    ga.drawLine((int)x1, (int)y1, (int)x2, (int)y2);

	    double l = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));//  line length
	    double d = 0;
	    if(endObenLinks) {
	    	d = l / l;
	    }else {
	    	d = l - distanceToEnd;
	    }
	     // arrowhead distance from end of line. you can use your own value.
	    
	    double newX = ((x2 + (((x1 - x2) / (l) * d)))); // new x of arrowhead position on the line with d distance from end of the line.
	    double newY = ((y2 + (((y1 - y2) / (l) * d)))); // new y of arrowhead position on the line with d distance from end of the line.

	    double dx = x2 - x1, dy = y2 - y1;
	    double angle = (Math.atan2(dy, dx)); //get angle (Radians) between ours line and x vectors line. (counter clockwise)
	    angle = (-1) * Math.toDegrees(angle);// cconvert to degree and reverse it to round clock for better understand what we need to do.
	    if (angle < 0) {
	        angle = 360 + angle; // convert negative degrees to posative degree
	    }
	    angle = (-1) * angle; // convert to counter clockwise mode
	    angle = Math.toRadians(angle);//  convert Degree to Radians
	    AffineTransform at = new AffineTransform();
	    at.translate(newX, newY);// transport cursor to draw arrowhead position.
	    at.rotate(angle);
	    ga.transform(at);

	    Polygon arrowHead = new Polygon();
	    arrowHead.addPoint(5, 0);
	    arrowHead.addPoint(-5, 5);
	    arrowHead.addPoint(-2, -0);
	    arrowHead.addPoint(-5, -5);
	    ga.fill(arrowHead);
	    ga.drawPolygon(arrowHead);
	}
	
	public static void drawRotate(Graphics2D g2d, double x, double y, int angle, String text) 
	{    
	    g2d.translate((float)x,(float)y);
	    g2d.rotate(Math.toRadians(angle));
	    g2d.drawString(text,0,0);
	    g2d.rotate(-Math.toRadians(angle));
	    g2d.translate(-(float)x,-(float)y);
	}   
	
	  public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
	        double rads = Math.toRadians(angle);
	        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
	        int w = img.getWidth();
	        int h = img.getHeight();
	        int newWidth = (int) Math.floor(w * cos + h * sin);
	        int newHeight = (int) Math.floor(h * cos + w * sin);

	        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = rotated.createGraphics();
	        AffineTransform at = new AffineTransform();
	        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

	        int x = w / 2;
	        int y = h / 2;

	        at.rotate(rads, x, y);
	        g2d.setTransform(at);
	        g2d.drawImage(img, 0, 0, null);
	        g2d.dispose();

	        return rotated;
	    }
		
	
}
