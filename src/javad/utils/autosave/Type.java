package javad.utils.autosave;

public enum Type {
Integer, String, Float, Double;
	
	private Object o = null;

public void setObject(Object o) {
	this.o = o;
}

public Object getObject() {
	return o;
}

}