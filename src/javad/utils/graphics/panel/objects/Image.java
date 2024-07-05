package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.awt.image.BufferedImage;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;

public class Image extends PaintableObject{

	private int w, h, layer;
	private BufferedImage img;
	private Point location;
	
	public Image(int w, int h, BufferedImage img, Point location, int layer) {
		this.w = w;
		this.h = h;
		this.layer = layer;
		this.img = img;
		this.location = location;
	}
	
	public Image(int w, int h, BufferedImage img, Point location) {
		this(w, h, img, location, 0);
	}
	
	public Image(BufferedImage img, Point location, int layer) {
		this(img.getWidth(), img.getHeight(), img, location, layer);
	}
	
	public Image(BufferedImage img, Point location) {
		this(img.getWidth(), img.getHeight(), img, location, 0);
	}
	
	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		Color bg = PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor());
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		if(bg == null)g.drawImage(img, location.x, location.y, w, h, null);
		else g.drawImage(img, location.x, location.y, w, h, bg, null);
	}

	@Override
	public Size getObjectSize() {
		return new Size(w, h);
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public Layer getLayer() {
		return new Layer(layer);
	}

}
