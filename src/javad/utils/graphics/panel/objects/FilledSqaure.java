package javad.utils.graphics.panel.objects;

import java.awt.Point;

public class FilledSqaure extends FilledRectangle{

	public FilledSqaure(int sideLenght, Point location, int layer) {
		super(sideLenght, sideLenght, location, layer);
	}

	public FilledSqaure(int sideLenght, Point location) {
		super(sideLenght, sideLenght, location);
	}

}
