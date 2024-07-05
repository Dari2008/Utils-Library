package javad.utils.graphics.panel.objects.ui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

import javad.utils.graphics.panel.Layer;
import javad.utils.graphics.panel.PaintableObject;
import javad.utils.graphics.panel.Size;
import javad.utils.graphics.panel.GraphicsPaneSettings.Setting;

public class Button extends PaintableObject{

	private Point location;
	private int w, h, layer;
	private Color pressedColor, disabledColor;
	private boolean isPressed = false;
	private boolean isEnabled = true;
	
	public Button(int w, int h, int layer, Point location, Color pressedColor, Color disabledColor) {
		this.w = w;
		this.h = h;
		this.location = location;
		this.layer = layer;
		this.pressedColor = pressedColor;
		this.disabledColor = disabledColor;
	}
	
	public Button(int w, int h, int layer, Point location) {
		this(w, h, layer, location, Color.YELLOW, Color.RED);
	}

	@Override
	public void paintObject(Graphics2D g, Setting setting) {
		if(isPressed && isEnabled) {
			g.setColor(pressedColor);
			g.fillRect(location.x, location.y, w, h);
		}else if(isEnabled) {
			g.fillRect(location.x, location.y, w, h);
		}else {
			g.setColor(disabledColor);
			g.fillRect(location.x, location.y, w, h);
		}
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
