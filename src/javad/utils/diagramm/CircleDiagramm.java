package javad.utils.diagramm;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;

import javad.utils.diagramm.Diagramm.Category;
import javad.utils.lists.ListOf3;
import javad.utils.math.Util;
import javad.utils.style.Utils;

public class CircleDiagramm extends JPanel implements Diagramm {

	private ListOf3<String, Category, ColorLabel> categories = new ListOf3<>();
	private boolean drawOutborder = false;
	private int height = 10;
	private int width = 10;
	private int MAX = 0;
	private boolean onlyCircle = true;
	private boolean drawInsideCircle = false;
	private Color insideCircleColor = Color.WHITE;
	private int insideCircleDiameter = 5;
	private int maxWidth = -1;
	private int maxHeight = -1;
	private int ww = 0;
	private int hh = 0;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.add(new CircleDiagramm());
		f.pack();
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

	/**
	 * Create the panel.
	 */
	public CircleDiagramm() {

		addCategory("Ziegen", 50, Color.GREEN, Color.green);
		addCategory("Schafe", 10, Color.YELLOW, Color.YELLOW);
		addCategory("Kühe", 3, Color.RED, Color.RED);
		addCategory("Küsdfhe", 3, Color.RED, Color.RED);
		addCategory("Kühsdfe", 6, Color.YELLOW, Color.YELLOW);
		addCategory("Kühstdfe", 3, Color.RED, Color.RED);
		addCategory("Küsdwerfhe", 3, Color.YELLOW, Color.YELLOW);
		addCategory("Kühwere", 3, Color.RED, Color.RED);
//		setDrawInsideCircle(true);
		setInsideCircleDiameter(300);
		setInsideCircleColor(Color.white);

	}

	protected void paintComponent(Graphics gg) {
		super.paintComponent(gg);
		Graphics2D g = (Graphics2D) gg;

		if (ww == 0) {
			ww = getWidth();
		}
		if (hh == 0) {
			hh = getHeight();
		}

		if (onlyCircle) {

			int rows = 1;

			if (getWidth() / width != categories.size()) {
				int first = getWidth() / width;
				rows = 0;
				for (int i = 0; i < categories.size(); i = i + first) {
					rows++;
				}
			}

			int x = 0;
			int y = rows * height;

			int w = getHeight() < getWidth() ? (getHeight() - rows * height) : getWidth();

			if (maxWidth > 0 || maxHeight > 0) {
				if (w > maxWidth)
					w = maxWidth;
				if (w > maxHeight)
					w = maxHeight;
			}

			x = (getWidth() - w) / 2;

			if (drawOutborder) {
				g.drawOval(x, y, w, w);
			}

			int punkt = 360;
			for (Category c : categories.getListB()) {
				g.setColor(c.getCategoryColor());
				g.fillArc(x, y, w, w, punkt + 90, 0 - Util.map(c.getValue(), 0, MAX, 0, 360));
				punkt = punkt -  Util.map(c.getValue(), 0, MAX, 0, 360);
			}

			if (drawInsideCircle && insideCircleColor != null) {
				g.setColor(insideCircleColor);

				int s = ww > hh ? ww : hh;

				s = s - insideCircleDiameter;

				g.fillOval(x + w / 2 - (w - s) / 2, y + w / 2 - (w - s) / 2, (w - s), (w - s));
			}

		} else {

			int rows = 1;

			if (getWidth() / width != categories.size()) {
				int first = getWidth() / width;
				rows = 0;
				for (int i = 0; i < categories.size(); i = i + first) {
					rows++;
				}
			}

			int x = 0;
			int y = rows * height;

			int width = getWidth();
			int height = (getHeight() - rows * this.height);

			if (maxWidth > 0 || maxHeight > 0) {
				if (width > maxWidth)
					width = maxWidth;
				if (height > maxHeight)
					height = maxHeight;
			}

			x = (getWidth() - width) / 2;

			if (drawOutborder) {
				g.drawOval(x, y, width, height);
			}

			int punkt = 360;

			for (Category c : categories.getListB()) {
				g.setColor(c.getCategoryColor());
				g.fillArc(x, y, width, height, punkt + 90, 0 - Util.map(c.getValue(), 0, MAX, 0, 360));
				punkt -= Util.map(c.getValue(), 0, MAX, 0, 360);
			}

			if (drawInsideCircle && insideCircleColor != null) {
				g.setColor(insideCircleColor);

				int wi = getWidth() - Math.abs(ww - insideCircleDiameter);
				int he = getHeight() - Math.abs(hh - insideCircleDiameter);

				g.fillOval(x + getWidth() / 2 - wi / 2, y + (getHeight() - this.height) / 2 - he / 2, wi, he);
			}

		}

	};

	public ListOf3<String, Category, ColorLabel> getCategories() {
		return categories;
	}

	public void setCategories(ListOf3<String, Category, ColorLabel> categories) {
		this.categories = categories;
	}

	public boolean isDrawOutborder() {
		return drawOutborder;
	}

	public boolean isOnlyCircle() {
		return onlyCircle;
	}

	public boolean isDrawInsideCircle() {
		return drawInsideCircle;
	}

	public Color getInsideCircleColor() {
		return insideCircleColor;
	}

	public int getInsideCircleDiameter() {
		return insideCircleDiameter;
	}

	public void setInsideCircleDiameter(int diam) {
		insideCircleDiameter = diam;
	}

	public void setInsideCircleColor(Color c) {
		insideCircleColor = c;
	}

	public void setDrawInsideCircle(boolean b) {
		drawInsideCircle = b;
	}

	public void setOnlyCircle(boolean b) {
		onlyCircle = b;
	}

	public void setDrawOutborder(boolean draw) {
		this.drawOutborder = draw;
	}

	public boolean addCategory(String name, int value) {
		return addCategory(name, value, new Color(categories.size()));
	}

	public boolean addCategory(String name, int value, Color categoryColor) {
		return addCategory(name, value, new Color(categories.size()), categoryColor);
	}

	public boolean addCategory(String name, int value, Color borderColor, Color categoryColor) {

		if (categories.containsA(name)) {
			return false;
		}

		ColorLabel lblNewLabel = new ColorLabel(name);
		lblNewLabel.setBorder(BorderFactory.createLineBorder(borderColor));
		lblNewLabel.setColor(categoryColor);
		lblNewLabel
				.setPreferredSize(new Dimension((int) Utils.getStringDimension("Test", lblNewLabel.getFont()).getWidth()
						+ lblNewLabel.getPreferredSize().height + 10, lblNewLabel.getPreferredSize().height));

		width = (int) Utils.getStringDimension("Test", lblNewLabel.getFont()).getWidth()
				+ lblNewLabel.getPreferredSize().height + 10 + 5;
		height = lblNewLabel.getPreferredSize().height + 10;

		lblNewLabel.setValue(value);

		MAX = MAX + value;

		categories.put(name, new Category(name, value, borderColor, categoryColor), lblNewLabel);

		add(lblNewLabel);
		return true;

	}

	public Category getCategory(int index) {
		return categories.get(index).getB();
	}

	public ColorLabel getLabelFor(int index) {
		return categories.getC(index);
	}

	public String getNameOf(int index) {
		return categories.getA(index);
	}

	public int indexOf(String name) {
		return categories.indexAOf(name);
	}

	public int indexOf(Category c) {
		return categories.getListB().indexOf(c);
	}

	public int indexOfLabel(Object l) {
		return categories.getListC().indexOf(l);
	}

	public boolean addCategory(Category c) {
		return addCategory(c.getName(), c.getValue(), c.getBorderColor(), c.getCategoryColor());
	}



}
