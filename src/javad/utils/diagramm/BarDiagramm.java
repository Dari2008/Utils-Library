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

public class BarDiagramm extends JPanel implements Diagramm, ComponentAdapter, MouseAdapter {

	/**
	 * Create the panel.
	 */

	private int MAX = 0;
	private int maxStringWidth = 0;
	private JLabel unterschrift = new JLabel("");
	private ListOf3<String, Category, JLabel> categories = new ListOf3<>();
	private int spaceY = -1;
	private Rectangle subtitleBounds = new Rectangle(-1, -1, getWidth() - 10, 14);

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
//		f.getContentPane().add(new BarDiagramm());
//		f.pack();
//		f.setVisible(true);
//		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	}

	public BarDiagramm() {
		setLayout(null);
		unterschrift.setHorizontalAlignment(SwingConstants.CENTER);
		update();
		add(unterschrift);
		addMouseListener(this);
		addMouseMotionListener(this);
		addMouseWheelListener(this);
		addComponentListener(this);

	}

	@Override
	protected void paintComponent(Graphics gg) {
		super.paintComponent(gg);

		Graphics2D g = (Graphics2D) gg;

		onePerPixel = Util.map(1, 0, MAX, 0, getWidth()-maxStringWidth-20);
		
		onePerPixel = onePerPixel - (onePerPixel/MAX);

		g.setColor(gridColor);
		if (drawGrid) {
			if(!(onePerPixel <= 0)){
				for (int x = maxStringWidth + 20; x < getWidth() - 20; x += onePerPixel) {
					g.drawLine(x, 30, x, getHeight() - unterschrift.getHeight() - 20);
				}
			}
		}

		if (drawMarkers) {
			g.setColor(markedColor);
			if(!(onePerPixel <= 0)){
				boolean first = true;
				for (int x = maxStringWidth + 20; x < getWidth() - 20; x += onePerPixel) {
					if (((x-maxStringWidth + 20)/onePerPixel) % markerDistance == 0) {
						if(first) {
							first = false;
							continue;
						}
						g.drawLine(x, getHeight() - unterschrift.getHeight() - 20 - markerSize/2, x, getHeight() - unterschrift.getHeight() - 20 + markerSize/2);
					}
				}
			}
		}

		for (int i = 0; i < categories.size(); i++) {
			Category c = categories.getListB().get(i);

			int x = maxStringWidth + 20, y = 0, w = 0, h = 0;

			if (spaceY == -1) {
				y = ((getHeight() / categories.size()) - 14) * (i);
			} else {
				y = ((14 + spaceY) - 14) * (i);
			}
			y = y + 80;

			w = onePerPixel * c.getValue();
			h = 14;

			g.setColor(c.getCategoryColor());
			g.fillRect(x, y, w, h);
			g.setColor(c.getBorderColor());
			g.drawRect(x, y, w, h);
		}

		g.setColor(lineColor);
		javad.utils.graphics.GraphicsUtils.drawArrow(g, maxStringWidth + 20, getHeight() - unterschrift.getHeight() - 20, maxStringWidth + 20, 20, 1,
				true);
		javad.utils.graphics.GraphicsUtils.drawArrow(g, maxStringWidth + 20, getHeight() - unterschrift.getHeight() - 20,
				getWidth()-20, getHeight() - unterschrift.getHeight() - 20, 1, true);

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
		
		for (int i = 0; i < categories.size(); i++) {
			Category c = categories.getListB().get(i);

			int x = maxStringWidth + 20, y = 0, w = 0, h = 0;

			if (spaceY == -1) {
				y = ((getHeight() / categories.size()) - 14) * (i);
			} else {
				y = ((14 + spaceY) - 14) * (i);
			}
			y = y + 80;

			w = onePerPixel * c.getValue();
			h = 14;
			
			if(new Rectangle(e.getX(), e.getY(), 1, 1).intersects(new Rectangle(x, y, w, h))) {
				setToolTipText(c.getName() + ": " + c.getValue());
				setToolTipEnabled(true);
				return;
			}else {
				setToolTipText("");
				setToolTipEnabled(false);
			}

		}
		


		if (drawMarkers) {
			int s = 1;
			boolean first = true;
			for (int x = maxStringWidth + 20; x < getWidth() - 20; x += onePerPixel) {
				if (((x-maxStringWidth + 20)/onePerPixel) % markerDistance == 0) {
					if(first) {
						first = false;
						continue;
					}
					if(new Rectangle(e.getX(), e.getY(), 1, 1).intersects(new Rectangle(x, getHeight() - unterschrift.getHeight() - 20 - markerSize/2, 3, markerSize))) {
						setToolTipText(s*markerDistance + "");
						setToolTipEnabled(true);
						return;
					}else {
						setToolTipText("");
						setToolTipEnabled(false);
						s++;
					}
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
			if (Utils.getStringDimension(l.getText(), l.getFont()).getWidth() > maxStringWidth) {
				maxStringWidth = (int) Utils.getStringDimension(l.getText(), l.getFont()).getWidth();
			}
		}

		categories.getListC().forEach(e -> e.setSize(maxStringWidth, 14));

		i = 0;

		if (spaceY == -1) {
			categories.getListC().forEach(e -> {
				e.setLocation(e.getX(), ((getHeight() / categories.size()) - 14) * (i) + 80);
				i++;
			});
		} else {
			categories.getListC().forEach(e -> {
				e.setLocation(e.getX(), ((14 + spaceY) - 14) * (i) + 80);
				i++;
			});
		}

		subtitleBounds = new Rectangle((int) subtitleBounds.getX(), (int) subtitleBounds.getY(), getWidth() - 10, 14);

		if (subtitleBounds.getX() < 0 && subtitleBounds.getY() < 0) {
			unterschrift.setBounds(0, getHeight() - 14, getWidth() - 10, 14);
		} else {
			unterschrift.setBounds((Rectangle) subtitleBounds);
		}
		setMinimumSize(new Dimension(maxStringWidth + 30, categories.size() * 14 + unterschrift.getHeight()));
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

		JLabel lbl = new JLabel(name);
		lbl.setBounds(10, 10 + categories.size() * 14, maxStringWidth, 14);
		lbl.setToolTipText(name + ": " + value);
		add(lbl);
		categories.put(name, new Category(name, value, CategoryBorderColor, categoryColor), lbl);

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
