package javad.utils.graphics.panel;

public class Size {

	private int w = 0,h = 0;
	
	public Size() {}
	
	public Size(int w, int h) {
		this.w = w;
		this.h = h;
	}
	
	public int getWidth() {
		return w;
	}

	public int getHeight() {
		return h;
	}
	
	public void setWidth(int w) {
		this.w = w;
	}
	
	public void setHeight(int h) {
		this.h = h;
	}
	
}
