package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;
import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;

public class FilledCircle extends PaintableObject{

	private int radius = 0;
	private int layer = 0;
	private Point location;

	public FilledCircle(int diameter, Point location, int layer) {
		radius = diameter/2;
		this.layer = layer;
		this.location = location;
	}

	public FilledCircle(Point location, int radius, int layer) {
		this.radius = (int) radius;
		this.layer = layer;
		this.location = location;
	}

	public FilledCircle(int radius, Point location) {
		this.radius = (int) radius;
		this.layer = 0;
		this.location = location;
	}

	public FilledCircle(float radius, Point location) {
		this.radius = (int) radius;
		this.layer = 0;
		this.location = location;
	}

	public int getRadius() {
		return radius;
	}
	
	public int getDiameter() {
		return radius*2;
	}
	
	public void setDiameter(int di) {
		radius = di/2;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public void setLayer(int layer) {
		this.layer = layer;
	}
	
	public void setLocation(Point p) {
		location = p;
	}
	

	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		g.fillOval(location.x, location.y, radius*2, radius*2);
	}

	@Override
	public Size getObjectSize() {
		return new Size(radius*2, radius*2);
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
