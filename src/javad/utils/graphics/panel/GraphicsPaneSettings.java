package javad.utils.graphics.panel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class GraphicsPaneSettings {

	private HashMap<PaintableObject, Setting> settings = new HashMap<>();
	
	public GraphicsPaneSettings() {
	}
	
	public void set(PaintableObject pobj, String String, Object obj) {
		if(settings.containsKey(pobj)) {
			settings.get(pobj).getSettings().put(String, obj);
		}else {
			settings.put(pobj, new Setting());
			settings.get(pobj).getSettings().put(String, obj);
		}
	}
	
	public ArrayList<String> getSetStrings(PaintableObject obj) {
		ArrayList<String> vs = new ArrayList<>();
		if(!settings.containsKey(obj))return null;
		vs.addAll(settings.get(obj).getSettings().keySet());
		return vs;
	}
	
	public HashMap<String, Object> getAllSettingsFor(PaintableObject obj){
		if(!settings.containsKey(obj))return null;
		return settings.get(obj).getSettings();
	}
	
	public void resetSetting(PaintableObject obj, String String) {
		if(!settings.containsKey(obj))return;
		settings.get(obj).getSettings().remove(String);
	}
	
	public void resetAllSettings(PaintableObject obj) {
		if(!settings.containsKey(obj))return;
		settings.put(obj, new Setting());
	}
	
	public void removeAllSettings(PaintableObject obj) {
		settings.remove(obj);
	}
	
	public void removeSetting(PaintableObject obj, String String) {
		resetSetting(obj, String);
	}
	
	public class Setting{
		private HashMap<String, Object> objs = new HashMap<>();
		public Setting() {
		}
		public HashMap<String, Object> getSettings(){
			return objs;
		}
		
		public Object get(String String) {
			return objs.get(String);
		}
	}

	public Setting getSetting(PaintableObject obj) {
		Setting s = settings.get(obj);
		return s==null?new Setting():s;
	}
	
}
