package javad.utils.chooser;

import java.awt.Window;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import jnafilechooser.api.JnaFileChooser;

public class FileChooser extends JnaFileChooser{

	public FileChooser() {
		super();
	}

	public FileChooser(File currentDirectory) {
		super(currentDirectory);
	}

	public FileChooser(String currentDirectoryPath) {
		super(currentDirectoryPath);
	}

	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection, String saveButtonText) {
		return showSaveDialog(parent, filters, currentDir, m, multiSelection, saveButtonText, null);
	}
	
	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection) {
		return showSaveDialog(parent, filters, currentDir, m, multiSelection, null, null);
	}
	
	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m) {
		return showSaveDialog(parent, filters, currentDir, m, false, null, null);
	}
	
	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters, File currentDir) {
		return showSaveDialog(parent, filters, currentDir, null, false, null, null);
	}
	
	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters) {
		return showSaveDialog(parent, filters, null, null, false, null, null);
	}
	
	public static File showSaveDialOg(Window parent) {
		return showSaveDialog(parent, null, null, null, false, null, null);
	}
	
	public static File showSaveDialog() {
		return showSaveDialog(null, null, null, null, false, null, null);
	}
	
	
	public static File showSaveDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection, String saveButtonText, String title) {
		
		FileChooser ch = new FileChooser(currentDir);
		
		ch.setMultiSelectionEnabled(multiSelection);
		
		if(m != null) {
			ch.setMode(m);
		}
		
		if(saveButtonText != null) {
			ch.setSaveButtonText(saveButtonText);
		}
		if(title != null) {
			ch.setTitle(title);
		
		}

		if(filters != null) {
			for(Map.Entry<String, String[]> s : filters.entrySet()) {
				ch.addFilter(s.getKey(), s.getValue());
			}
		}
		
		ch.showOpenDialog(parent);
		return ch.getSelectedFile();
	}
	

	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection, String openButtonText) {
		return showOpenDialog(parent, filters, currentDir, m, multiSelection, openButtonText, null);
	}
	
	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection) {
		return showOpenDialog(parent, filters, currentDir, m, multiSelection, null, null);
	}
	
	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m) {
		return showOpenDialog(parent, filters, currentDir, m, false, null, null);
	}
	
	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters, File currentDir) {
		return showOpenDialog(parent, filters, currentDir, null, false, null, null);
	}
	
	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters) {
		return showOpenDialog(parent, filters, null, null, false, null, null);
	}
	
	public static File showOpenDialOg(Window parent) {
		return showOpenDialog(parent, null, null, null, false, null, null);
	}
	
	public static File showOpenDialog() {
		return showOpenDialog(null, null, null, null, false, null, null);
	}
	
	
	
	public static File showOpenDialog(Window parent, HashMap<String, String[]> filters, File currentDir, Mode m, boolean multiSelection, String openButtonText, String title) {
		
		FileChooser ch = new FileChooser(currentDir);
		ch.setMultiSelectionEnabled(multiSelection);
		if(openButtonText != null) {
			ch.setOpenButtonText(openButtonText);
		}
		if(title != null) {
			ch.setTitle(title);
		}
		
		if(filters != null) {
			for(Map.Entry<String, String[]> s : filters.entrySet()) {
				ch.addFilter(s.getKey(), s.getValue());
			}
		}

		if(m != null) {
			ch.setMode(m);
		}
		ch.showOpenDialog(parent);
		return ch.getSelectedFile();
	}
	
	
	
}
