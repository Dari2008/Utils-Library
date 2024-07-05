package javad.utils.diagramm;

import java.awt.Color;

public interface Diagramm{


	public boolean addCategory(String name, int value);

	public boolean addCategory(String name, int value, Color categoryColor);

	public boolean addCategory(String name, int value, Color borderColor, Color categoryColor);

	public Object getCategory(int index);

	public Object getLabelFor(int index);

	public String getNameOf(int index);

	public int indexOf(String name);

	public int indexOf(Category c);

	public int indexOfLabel(Object l);

	public boolean addCategory(Category c);

	public class Category {

		private String name = "";
		private int value = 0;
		private Color borderColor, categoryColor;

		public Category(String name) {
			this.name = name;
		}

		public Category(String name, int value) {
			this.name = name;
			this.value = value;
		}

		public Category(String name, int value, Color borderColor) {
			this.name = name;
			this.value = value;
			this.borderColor = borderColor;
		}

		public Category(String name, int value, Color borderColor, Color categoryColor) {
			this.name = name;
			this.value = value;
			this.borderColor = borderColor;
			this.categoryColor = categoryColor;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public Color getBorderColor() {
			return borderColor;
		}

		public void setBorderColor(Color borderColor) {
			this.borderColor = borderColor;
		}

		public Color getCategoryColor() {
			return categoryColor;
		}

		public void setCategoryColor(Color categoryColor) {
			this.categoryColor = categoryColor;
		}

	}
	
}
