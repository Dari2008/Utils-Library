package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;

public class PointConnector extends PaintableObject{

	
	private int layer = 0;
	private Point location;
	private HashMap<Character, Point> points = new HashMap<>();
	private ArrayList<Character[]> connections = new ArrayList<>();

	public PointConnector(Point location, int layer) {
		this.layer = layer;
		this.location = location;
	}

	public void addPoint(char key, Point point) {
		points.put(key, point);
	}
	
	public void addConnection(char p1, char p2) {
		connections.add(new Character[] {p1, p2});
	}
	
	public Point getPoint(char key) {
		return points.get(key);
	}
	
	public Point getPoint(Point p) {
		for(Entry<Character, Point> e : points.entrySet()) {
			if(e.getValue().x == p.x && e.getValue().y == p.y)return e.getValue();
		}
		return null;
	}
	
	public Character getConnections(char c) {
		for(Character[] chars : connections) {
			if(chars[0] == c)return chars[1];
			if(chars[1] == c)return chars[0];
		}
		return null;
	}
	
	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		for(Character[] chars : connections) {
			char c1 = chars[0];
			char c2 = chars[1];
			g.drawLine(location.x + points.get(c1).x, location.y + points.get(c1).y, location.x + points.get(c2).x, location.y + points.get(c2).y);
		}
	}

	@Override
	public Size getObjectSize() {
		return new Size(getWidthMax(), getHeightMax());
	}

	@Override
	public Point getLocation() {
		return location;
	}

	@Override
	public Layer getLayer() {
		return new Layer(layer);
	}

	private int getWidthMax() {
		int max = 0;
		for(Entry<Character, Point> e : points.entrySet()) {
			max = Integer.max(max, e.getValue().x);
		}
		return max+1;
	}

	private int getHeightMax() {
		int max = 0;
		for(Entry<Character, Point> e : points.entrySet()) {
			max = Integer.max(max, e.getValue().y);
		}
		return max+1;
	}

}
