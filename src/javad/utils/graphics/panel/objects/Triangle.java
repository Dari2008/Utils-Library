package javad.utils.graphics.panel.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.PathIterator;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;
import javad.utils.math.Util;

public class Triangle extends PaintableObject{

	private Point point1, point2, point3;
	private Point position;
	private int layer = 0;

	public Triangle(Point point1, Point point2, Point point3, Point position, int layer) {
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.position = position;
		this.layer = layer;
	}

	public Triangle(Point point1, Point point2, Point point3, Point position) {
		this.point1 = point1;
		this.point2 = point2;
		this.point3 = point3;
		this.position = position;
	}

	public Triangle(int w1, int w2, int w3, Point position) {
		double[][] points = Util.calculateTrianglePoints(new double[] {w1, w2, w3});
		this.point1 = new Point((int)points[0][0], (int)points[0][1]);
		this.point2 = new Point((int)points[1][0], (int)points[1][1]);
		this.point3 = new Point((int)points[2][0], (int)points[2][1]);
		this.position = position;
	}

	public Point getPoint1() {
		return point1;
	}

	public void setPoint1(Point point1) {
		this.point1 = point1;
	}

	public Point getPoint2() {
		return point2;
	}

	public void setPoint2(Point point2) {
		this.point2 = point2;
	}

	public Point getPoint3() {
		return point3;
	}

	public void setPoint3(Point point3) {
		this.point3 = point3;
	}
	
	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		g.setColor(PaintableObject.getOrDefault((Color)setting.get("COLOR"), g.getColor()));
		g.setStroke(PaintableObject.getOrDefault((Stroke)setting.get("STROKE"), g.getStroke()));
		g.drawPolygon(new int[] {position.x + point1.x, position.x + point2.x, position.x + point3.x}, new int[] {position.y + point1.y, position.y + point2.y, position.y + point3.y}, 3);
	}

	@Override
	public Size getObjectSize() {
		return new Size(getWidthMax(), getHeightMax());
	}

	@Override
	public Point getLocation() {
		return position;
	}

	@Override
	public Layer getLayer() {
		return new Layer(layer);
	}

	private int getWidthMax() {
		int[] pointsX = new int[3];
		pointsX[0] = point1.x;
		pointsX[1] = point2.x;
		pointsX[2] = point3.x;
		return Integer.max(Integer.max(pointsX[0], pointsX[1]), pointsX[2])+1;
	}

	private int getHeightMax() {
		int[] pointsY = new int[3];
		pointsY[0] = point1.y;
		pointsY[1] = point2.y;
		pointsY[2] = point3.y;
		return Integer.max(Integer.max(pointsY[0], pointsY[1]), pointsY[2])+1;
	}

}
