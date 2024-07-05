package javad.utils.diagramm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.ToolTipManager;
import javax.swing.border.Border;

public class ColorLabel extends JLabel{

	public ColorLabel() {
		super();
		registerKeyListener();
	}

	public ColorLabel(Icon image, int horizontalAlignment) {
		super(image, horizontalAlignment);
		registerKeyListener();
		
	}

	public ColorLabel(Icon image) {
		super(image);
		registerKeyListener();
		
	}

	public ColorLabel(String text, Icon icon, int horizontalAlignment) {
		super(text, icon, horizontalAlignment);
		registerKeyListener();
		
	}

	public ColorLabel(String text, int horizontalAlignment) {
		super(text, horizontalAlignment);
		registerKeyListener();
		
	}

	public ColorLabel(String text) {
		super(text);
		registerKeyListener();
	}
	
	private void registerKeyListener() {
		setShowToolTip(showToolTiop);
		addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseExited(MouseEvent e) {
				ToolTipManager.sharedInstance().setEnabled(true);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				ToolTipManager.sharedInstance().setDismissDelay(2000);
				ToolTipManager.sharedInstance().setReshowDelay(100);
				ToolTipManager.sharedInstance().setInitialDelay(100);
				setToolTipText(start + value + end);
				ToolTipManager.sharedInstance().setEnabled(showToolTiop);
			}
			
		});
	}

	private Color c;
	private Border b;
	private boolean showToolTiop = true;
	private String start = "";
	private String end = "";
	private Object value = 0;
	
	public void setShowToolTip(boolean b) {
		showToolTiop = b;
		ToolTipManager.sharedInstance().setEnabled(b);
	}
	
	
	
	public Color getColor() {
		return c;
	}

	public Border getBorder() {
		return b;
	}

	public boolean isShowToolTiop() {
		return showToolTiop;
	}

	public String getStartToolTipText() {
		return start;
	}

	public String getEndToolTipText() {
		return end;
	}

	public Object getValue() {
		return this.value;
	}

	public void setValue(Object o) {
		this.value = o;
	}
	
	public void setStartToolTipText(String text) {
		start = text;
	}
	public void setEndToolTipText(String text) {
		end = start;
	}
	
	public void setColorBorder(Border b) {
		this.b = b;
	}
	
	public void setBorderColor(Color c) {
		b = BorderFactory.createLineBorder(c, 1, false);
	}
	
	public void setColor(Color c) {
		this.c = c;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(c);
		

		int w = getHeight()<getWidth()?getHeight():getWidth();
		
		g.fillRect(getWidth()-getHeight(), 0, w, w);
		
		if(b != null) {
			b.paintBorder(labelFor, g, getWidth()-getHeight(), 0, getHeight(), getHeight());
		}else if(c != null){
			b = BorderFactory.createLineBorder(c, 1, false);
			b.paintBorder(labelFor, g, getWidth()-getHeight(), 0, getHeight(), getHeight());
		}
		
	}
	
}
