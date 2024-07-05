package javad.utils.diagramm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;

import javad.utils.listeners.ComponentAdapter;
import javad.utils.listeners.MouseAdapter;
import javad.utils.lists.ListOf3;
import javad.utils.math.Random;
import javad.utils.math.Util;
import javad.utils.style.Utils;

public class ColumnDiagramm extends JPanel implements Diagramm, ComponentAdapter, MouseAdapter {

	/**
	 * Create the panel.
	 */

	private int MAX = 0;
	private int maxStringHeight = 0;
	private JLabel unterschrift = new JLabel("");
	private ListOf3<String, Category, RotatedLabel> categories = new ListOf3<>();
	private int spaceY = -1;
	private Rectangle subtitleBounds = new Rectangle(-1, -1, getWidth() - 10, 14);
	
	private int distance = 50;

	private boolean drawGrid = true;
	private boolean drawMarkers = true;
	private int markerDistance = 3;
	private Color markedColor = Color.BLACK;
	private Color lineColor = Color.RED;
	private Color gridColor = Color.LIGHT_GRAY;
	private int markerSize = 3;
	private int onePerPixel;

//	public static void main(String[] args) {
//		JFrame f = new JFrame();
//		f.getContentPane().add(new ColumnDiagramm());
//		f.pack();
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	}

	public ColumnDiagramm() {
		setLayout(null);
		unterschrift.setHorizontalAlignment(SwingConstants.CENTER);
		add(unterschrift);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addComponentListener(this);
		update();
	}

	@Override
	protected void paintComponent(Graphics gg) {
		super.paintComponent(gg);

		Graphics2D g = (Graphics2D) gg;

		
		onePerPixel = Util.map(1, 0, MAX, 0, getHeight()-maxStringHeight-distance);
		if(drawGrid) {
			if(!(onePerPixel <= 0 || getHeight()-maxStringHeight <= 0)){
				for(int y = getHeight()-maxStringHeight; y >= 0 ; y-=onePerPixel) {
					g.setColor(gridColor);
					if(y < distance)continue;
					g.drawLine(distance, y, getWidth() - distance, y);
				}
			}
		}
		
		if(drawMarkers) {
			if(!(onePerPixel <= 0 || getHeight()-maxStringHeight <= 0)){
				int pos = 0;
				for(int y = getHeight()-maxStringHeight; y >= 0 ; y-=onePerPixel) {
					g.setColor(markedColor);
					if(y < distance || pos%markerDistance != 0) {
						pos++;
						continue;
					}
					pos++;
					g.drawLine(distance - markerSize/2, y, distance + markerSize/2, y);
				}
			}
		}

		if(!(onePerPixel <= 0 || getHeight()-maxStringHeight <= 0)){
			for(int i = 0; i < categories.size(); i++) {
				Category c = categories.getListB().get(i);
				RotatedLabel l = categories.getListC().get(i);
				
				int x,y,w,h;
				w = l.getWidth();
				h = onePerPixel*c.getValue();
				x = l.getX();
				y = getHeight() - maxStringHeight - h;
				g.setColor(c.getCategoryColor());
				g.fillRect(x, y, w, h);
				g.setColor(c.getBorderColor());
				g.drawRect(x, y, w, h);
			}
		}
		
		g.setColor(lineColor);
		javad.utils.graphics.GraphicsUtils.drawArrow(g, distance, getHeight() - maxStringHeight, distance, maxStringHeight, 0, true);
		javad.utils.graphics.GraphicsUtils.drawArrow(g, distance, getHeight() - maxStringHeight, getWidth() - distance, getHeight() - maxStringHeight, 0, true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		mouseMove(e);
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {
		mouseMove(e);
	}
	
	@Override
	public void mouseExited(MouseEvent e) {
		setToolTipEnabled(true);
	}
	
	private void mouseMove(MouseEvent e) {
		
		if(!(onePerPixel <= 0 || getHeight()-maxStringHeight <= 0)){
			int pos = 0;
			for(int y = getHeight()-maxStringHeight; y >= 0 ; y-=onePerPixel) {
				if(y < distance || pos%markerDistance != 0) {
					pos++;
					continue;
				}
				pos++;
				if(new Rectangle(e.getX(), e.getY(), 1, 1).intersects(new Rectangle(distance - markerSize/2, y, markerSize, 1))) {
					setToolTipEnabled(true);
					setToolTipText(pos + "");
					return;
				}else {
					setToolTipEnabled(false);
				}
			}
		}
		
		for(int i = 0; i < categories.size(); i++) {
			if(!(onePerPixel <= 0 || getHeight()-maxStringHeight <= 0)){
					Category c = categories.getListB().get(i);
					RotatedLabel l = categories.getListC().get(i);
					int x,y,w,h;
					w = l.getWidth();
					h = onePerPixel*c.getValue();
					x = l.getX();
					y = getHeight() - maxStringHeight - h;
					
					if(new Rectangle(e.getX(), e.getY(), 1, 1).intersects(new Rectangle(x, y, w, h))) {
						setToolTipEnabled(true);
						setToolTipText(c.getName() + ": " + c.getValue());
						return;
					}else {
						setToolTipEnabled(false);
					}
					
			}
		}
		
	}
	
	public void setToolTipEnabled(boolean b) {
		if(ToolTipManager.sharedInstance().isEnabled() == b)return;
		ToolTipManager.sharedInstance().setEnabled(b);
		ToolTipManager.sharedInstance().setDismissDelay(2000);
		ToolTipManager.sharedInstance().setInitialDelay(10);
		ToolTipManager.sharedInstance().setReshowDelay(100);
	}
	
	public void setMarkerSize(int size) {
		markerSize = size;
	}
	
	public void setDrawMarkers(boolean b) {
		drawMarkers = b;
	}

	public void setMax(int max) {
		this.MAX = max;
	}

	@Override
	public void componentResized(ComponentEvent e) {
		update();
		repaint();
	}

	private int i = 0;

	public void update() {

		for (JLabel l : categories.getListC()) {
			if (Utils.getStringDimension(l.getText(), l.getFont()).getWidth() > maxStringHeight) {
				maxStringHeight = (int) Utils.getStringDimension(l.getText(), l.getFont()).getWidth();
			}
		}

		categories.getListC().forEach(e -> e.setSize(14, maxStringHeight));

		i = 0;

		if (spaceY == -1) {
			categories.getListC().forEach(e -> {
				e.setLocation(((getWidth() / categories.size()) - 14) * (i) + distance + markerSize/2 + distance, getHeight() - maxStringHeight + 4);
				i++;
			});
		} else {
			categories.getListC().forEach(e -> {
				e.setLocation(((14 + spaceY) - 14) * (i) + distance + markerSize/2 + distance, getHeight() - maxStringHeight + 4);
				i++;
			});
		}

		subtitleBounds = new Rectangle((int) subtitleBounds.getX(), (int) subtitleBounds.getY(), getWidth() - 10, 14);

		unterschrift.setVisible(false);
		
		if (subtitleBounds.getX() < 0 && subtitleBounds.getY() < 0) {
			unterschrift.setBounds(0, getHeight() - 14, getWidth() - 10, 14);
		} else {
			unterschrift.setBounds((Rectangle) subtitleBounds);
		}
		setMinimumSize(new Dimension(categories.size() * 14 + unterschrift.getHeight(), maxStringHeight + 30));
	}

	@Override
	public void setSize(Dimension d) {
		super.setSize(d);
		update();
	}

	@Override
	public void setSize(int width, int height) {
		super.setSize(width, height);
		update();
	}

	public void setSubtitleLocation(int x, int y) {
		subtitleBounds = new Rectangle(x, y, (int) subtitleBounds.getWidth(), (int) subtitleBounds.getWidth());
	}

	public void setSpacing(int space) {
		this.spaceY = space;
	}

	public void setSubtitle(String s) {
		unterschrift.setText(s);
	}

	public String getSubtitle() {
		return unterschrift.getText();
	}

	public JLabel getSubtitleLabel() {
		return unterschrift;
	}

	@Override
	public boolean addCategory(String name, int value) {
		return addCategory(name, value, new Color(0, 0, 0));
	}

	@Override
	public boolean addCategory(String name, int value, Color categoryColor) {
		return addCategory(name, value, categoryColor, categoryColor);
	}

	@Override
	public boolean addCategory(String name, int value, Color CategoryBorderColor, Color categoryColor) {

		if (categories.containsA(name))
			return false;

		RotatedLabel lbl = new RotatedLabel(name);
		lbl.setBounds(0, 0, 14, (int) Utils.getStringDimension(lbl.getText(), lbl.getFont()).getWidth()+distance/2);
		lbl.setToolTipText(name + ": " + value);
		add(lbl);
		categories.put(name, new Category(name, value, CategoryBorderColor, categoryColor), lbl);

		if(Utils.getStringDimension(lbl.getText(), lbl.getFont()).getWidth() > maxStringHeight+distance/2) {
			maxStringHeight = (int) Utils.getStringDimension(lbl.getText(), lbl.getFont()).getWidth()+distance/2;
		}
		
		if (value > MAX)
			MAX = value;

		update();

		return true;
	}

	@Override
	public Category getCategory(int index) {

		return categories.getListB().get(index);
	}

	@Override
	public JLabel getLabelFor(int index) {

		return categories.getC(index);
	}

	@Override
	public String getNameOf(int index) {

		return categories.get(index).getA();
	}

	@Override
	public int indexOf(String name) {

		return categories.getListA().indexOf(name);
	}

	@Override
	public int indexOf(Category c) {

		return categories.getListB().indexOf(c);
	}

	@Override
	public int indexOfLabel(Object l) {

		return categories.getListC().indexOf(l);
	}

	@Override
	public boolean addCategory(Category c) {
		return addCategory(c.getName(), c.getValue(), c.getBorderColor(), c.getCategoryColor());
	}
}
