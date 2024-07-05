package javad.utils.graphics.panel;

import java.awt.Graphics2D;
import java.awt.Point;

import javax.swing.JPanel;

import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;

public abstract class PaintableObject extends JPanel{
	private static final long serialVersionUID = 3471051909946991365L;

	public abstract void paintObject(Graphics2D g, Setting setting);
	public abstract Size getObjectSize();
	public abstract Point getLocation();
	public abstract Layer getLayer();

	public int getX() {
		return getLocation().x;
	}

	public int getY() {
		return getLocation().y;
	}
	
	public int getWidth() {
		return getObjectSize().getWidth();
	}
	
	public int getHeight() {
		return getObjectSize().getHeight();
	}
	
	public static <T> T getOrDefault(T obj, T defaultValue) {
		if(obj == null) {
			return defaultValue;
		}
		return obj;
	}
	
}
