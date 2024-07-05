package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;
import javad.utils.style.Utils;

public class Text extends PaintableObject {

	private String text;
	private Point location;
	private int layer = 0;

	public Text(String text, Point location) {
		this.text = text;
		this.location = location;
	}
	
	public Text(String text, Point location, int layer) {
		this.text = text;
		this.location = location;
		this.layer = layer;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setLocation(Point location) {
		this.location = location;
	}
	
	public void setLayer(Layer l) {
		layer = l.getLayerNumber();
	}
	
	public void setLayer(int lay) {
		layer = lay;
	}

	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("FOREGROUND_COLOR"), g.getColor()));
		g.setFont(PaintableObject.getOrDefault((Font)setting.get("FONT"), g.getFont()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		g.drawString(text, location.x, location.y);
	}
	
	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public Size getObjectSize() {
		return getObjectSize(getFont());
	}

	public Size getObjectSize(Font font) {
		return new Size((int)Utils.getStringDimension(text, font).getWidth(), (int)Utils.getStringDimension(text, font).getHeight());
	}
	
	@Override
	public Layer getLayer() {
		return new Layer(layer);
	}

}
