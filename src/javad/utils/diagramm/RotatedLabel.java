package javad.utils.diagramm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.JLabel;

import javad.utils.style.Utils;

public class RotatedLabel extends JLabel{

    public RotatedLabel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RotatedLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public RotatedLabel(Icon image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	public RotatedLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public RotatedLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		// TODO Auto-generated constructor stub
	}

	public RotatedLabel(String text) {
		super(text);
		
	}
	
    @Override
    protected void paintComponent(Graphics gg) {
        Graphics2D g = (Graphics2D) gg;
    	javad.utils.graphics.GraphicsUtils.drawRotate(g, 0, 0, 90, getText());
    }
    
  
}
