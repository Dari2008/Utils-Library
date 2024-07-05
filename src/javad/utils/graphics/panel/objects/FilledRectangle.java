package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;

public class FilledRectangle extends PaintableObject{

	private int w, h;
	private Point location;
	private int layer = 0;
	
	public FilledRectangle(int w, int h, Point location, int layer) {
		this.location = location;
		this.w = w;
		this.h = h;
		this.layer = layer;
	}
	
	public FilledRectangle(int w, int h, Point location) {
		this(w, h, location, 0);
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void setWidth(int w) {
		this.w = w;
	}
	
	public void setHeight(int h) {
		this.h = h;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		g.fillRect(location.x, location.y, w, h);
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
