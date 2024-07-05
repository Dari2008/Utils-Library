package javad.utils.graphics.panel.objects;

import java.awt.Point;

public class Sqaure extends Rectangle{

	public Sqaure(int sideLenght, Point location, int layer) {
		super(sideLenght, sideLenght, location, layer);
	}

	public Sqaure(int sideLenght, Point location) {
		super(sideLenght, sideLenght, location);
	}

}
