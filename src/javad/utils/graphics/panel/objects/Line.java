package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;
import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;

public class Line extends PaintableObject{

	private Point p1, p2, location;
	private boolean isOnly12 = false;
	private int layer;

	public Line(Point p1, Point p2, Point location, int layer) {
		this.p1 = p1;
		this.p2 = p2;
		this.location = location;
		this.layer = layer;
	}
	

	public Line(Point p1, Point p2, int layer) {
		this(p1, p2, new Point(Math.min(p1.x, p2.x), Math.min(p1.y, p2.y)), layer);
		isOnly12 = true;
	}

	
	
	public Point getP1() {
		return p1;
	}


	public void setP1(Point p1) {
		this.p1 = p1;
	}


	public Point getP2() {
		return p2;
	}


	public void setP2(Point p2) {
		this.p2 = p2;
	}


	public void setLocation(Point location) {
		this.location = location;
	}


	public void setLayer(int layer) {
		this.layer = layer;
	}


	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		if(isOnly12) {
			g.drawLine(p1.x, p1.y, p2.x, p2.y);
		}else {
			g.drawLine(location.x + p1.x, location.y + p1.y, location.x + p2.x, location.y + p2.y);
		}
	}

	@Override
	public Size getObjectSize() {
		return new Size(Math.max(p1.x, p2.x), Math.max(p1.y, p2.y));
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
