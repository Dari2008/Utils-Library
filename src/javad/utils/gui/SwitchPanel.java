package javad.utils.gui;

import java.awt.Container;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.validation.constraints.NotNull;

public class SwitchPanel {

	private ArrayList<Container> panels = new ArrayList<>();
	private ArrayList<String> names = new ArrayList<>();

	private String currentName = null;
	private Container currentContainer = null;
	private Container before = null;
	private Container parent;
	
	private Consumer<Container> add;
	private Consumer<Container> remove;
	
	public SwitchPanel() {
		super();
	}

	public void addPanel(Container c, String name) {
		panels.add(c);
		names.add(name);
		if(currentContainer == null) {
			currentContainer = c;
			currentName = name;
		}
	}

	public int indexOfPanel(Container c) {
		return panels.indexOf(c);
	}

	public int indexOfPanel(String name) {
		return names.indexOf(name);
	}
	
	public ArrayList<Container> getPanels() {
		return panels;
	}
	
	public Container getPanel(int index) {
		if(index >= panels.size())return null;
		return panels.get(index);
	}
	
	public Container getPanel(String name) {
		if(!names.contains(name))return null;
		return panels.get(names.indexOf(name));
	}
	
	public String getNameOf(Container c) {
		if(!panels.contains(c))return null;
		return names.get(panels.indexOf(c));
	}
	
	public void switchToPanel(int index) {
		Container currentContainer = panels.get(index);
		if(currentContainer == null)return;
		before = this.currentContainer;
		this.currentContainer = currentContainer;
		currentName = getNameOf(currentContainer);
		revalidate();
	}
	
	public void switchToPanel(String name) {
		Container currentContainer = getPanel(name);
		if(currentContainer == null)return;
		currentName = getNameOf(currentContainer);
		before = this.currentContainer;
		this.currentContainer = currentContainer;
		revalidate();
	}
	
	public void switchToPanel(Container c) {
		before = currentContainer;
		currentContainer = c;
		revalidate();
	}
	
	public void removePanel(String name) {
		if(getPanel(name) == currentContainer) {
			before = currentContainer;
			currentContainer = null;
		}
		panels.remove(indexOfPanel(name));
		names.remove(name);
		revalidate();
	}
	
	public void removePanel(int index) {
		if(getPanel(index) == currentContainer) {
			before = currentContainer;
			currentContainer = null;
		}
		panels.remove(index);
		names.remove(index);
		revalidate();
	}
	
	public void removePanel(Container c) {
		if(panels.contains(c)) {
			names.remove(indexOfPanel(c));
			panels.remove(c);
		}else {
			if(currentContainer != null && currentContainer == c) {
				before = currentContainer;
				currentContainer = null;
			}
		}
		revalidate();
	}

	
	public Container getCurrentPanel() {
		return currentContainer;
	}
	
	public String getCurrentName() {
		return currentName;
	}
	
	public void setParentContainer(Container c) {
		if(c == null)return;
		remove = c::remove;
		add = c::add;
		parent = c;
	}
	
	
	private void revalidate(){
		if(currentContainer != null && before != null && remove != null) {
			remove.accept(before);
		}
		if(currentContainer != null && add != null) {
			add.accept(currentContainer);
		}
		if(parent != null)parent.revalidate();
		if(parent != null)parent.repaint();
		if(parent != null)parent.validate();
	}
	
}
