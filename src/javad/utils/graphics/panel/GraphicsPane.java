package javad.utils.graphics.panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GraphicsPane extends JPanel{

	private ArrayList<PaintableObject> paintableObjects = new ArrayList<>();
	private Color background;
	private GraphicsPaneSettings settings;

	public GraphicsPane() {
		this(new GraphicsPaneSettings());
	}
	
	public GraphicsPane(GraphicsPaneSettings settings) {
		this.settings = settings;
	}
	
	public GraphicsPaneSettings getSettings() {
		return settings;
	}
	
	public void setSettings(GraphicsPaneSettings s) {
		settings = s;
	}
	
	public void setSetting(PaintableObject pObj, String key, Object obj) {
		settings.set(pObj, key, obj);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintObjects(((Graphics2D)g));
	}
	
	private void paintObjects(Graphics2D g) {
		
		ArrayList<PaintableObject> objs = new ArrayList<>(paintableObjects);
		
		objs.sort((o1, o2) -> Integer.compare(o1==null?Integer.MIN_VALUE:o1.getLayer().getLayerNumber(), o2==null?Integer.MIN_VALUE:o2.getLayer().getLayerNumber()));

		Color defaultColor = g.getColor();
		Font defaultFont = g.getFont();
		Stroke defaultStroke = g.getStroke();
		
		objs.forEach((obj)->{
			if(obj == null)return;
			
			g.setColor(defaultColor);
			g.setFont(defaultFont);
			g.setStroke(defaultStroke);
			
			obj.paintObject(g, settings.getSetting(obj));
		});
		
	}

	public void setBackgroundColor(Color c) {
		this.background = c;
	}
	
	public Color getBackgroundColor() {
		return background;
	}

	public void addObject(PaintableObject obj) {
		paintableObjects.add(obj);
	}

	public void removeObject(PaintableObject obj) {
		paintableObjects.remove(obj);
	}

	public void removeObject(int obj) {
		paintableObjects.remove(obj);
	}
	
	public void removeAllObjects(Class<?> c) {
		for(PaintableObject obj : paintableObjects) {
			if(obj.getClass().toString().equals(c.toString())) {
				removeObject(obj);
			}
		}
	}
	
	
}
