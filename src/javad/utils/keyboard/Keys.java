package javad.utils.keyboard;

import java.awt.Window;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

public class Keys implements KeyListener {

	private static boolean defaultValue = false;
	
	private static Keys INSTANCE = new Keys();
	
	private static boolean[] keys = new boolean[2000];
	
	private static ArrayList<KeyListener> keyListeners = new ArrayList<>();
	
	private Keys() {}
	
	/*
	 * WARNING: The key will be disabled so if you use <code>KeyEvent.VK_B</code> Then you can't type b.
	 */
	private static ArrayList<HotKey> hotKeys = new ArrayList<>();
	private static ArrayList<Thread> hotKeyThreads = new ArrayList<>();
	public static HotKey registerHotKey(int keyCode, int modifiers, HotKeyListener li) {
		HotKey h = new HotKey(keyCode, modifiers, li);
		Thread t = new Thread(h);
		t.setName("" + h.getID());
		t.start();
		hotKeyThreads.add(t);
		hotKeys.add(h);
		return h;
	}
	
	public static void addListenerTo(Window w) {
		w.addKeyListener(INSTANCE);
		w.requestFocus();
		w.requestFocusInWindow();
		w.setAutoRequestFocus(false);
		w.addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				w.requestFocus();
			}
			
			@Override
			public void focusGained(FocusEvent e) {
			}
		});
	}
	
	public static void removeListenerFrom(Window w) {
		w.removeKeyListener(INSTANCE);
	}
	
	public static void addKeyListener(KeyListener li) {
		keyListeners.add(li);
	}
	
	public static boolean removeKeyListener(KeyListener li) {
		return keyListeners.remove(li);
	}
	
	public static KeyListener removeKeyListener(int i) {
		return keyListeners.remove(i);
	}
	
	public static void addKeyListener(String keys, JComponent c, AbstractAction li) {
		KeyStroke action = KeyStroke.getKeyStroke(keys);

		ActionMap actionMap = c.getActionMap();
        actionMap.put(keys, li);
        
		
        InputMap inputMap = c.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(action, keys);
	}
	
	
	
	public static void setDefaultValue(boolean defaultValue) {
		Keys.defaultValue = defaultValue;
	}
	
	public static boolean getDefaultValue() {
		return defaultValue;
	}
	
	public static String getKeyString(boolean shift, int... keyCodes) {
		return getKeyString(shift, defaultValue, defaultValue, defaultValue, keyCodes);
	}

	public static String getKeyString(boolean shift, boolean alt, int... keyCodes) {
		return getKeyString(shift, defaultValue, defaultValue, alt, keyCodes);
	}

	public static String getKeyString(boolean control, boolean meta, boolean alt, int... keyCodes) {
		return getKeyString(defaultValue, control, meta, alt, keyCodes);
	}

	public static String getKeyString(int... keyCodes) {
		return getKeyString(defaultValue, defaultValue, defaultValue, defaultValue, keyCodes);
	}
	
    public static String getKeyString(boolean shift, boolean control, boolean meta, boolean alt, int... keyCodes) {
        StringBuilder sb = new StringBuilder();

        if (shift) {
            sb.append("shift ");
        }
        if (control) {
            sb.append("control");
        }
        if (meta) {
            sb.append("meta ");
        }
        if (alt) {
            sb.append("alt ");
        }

        boolean isFirst = true;
        
        for(int keyCode : keyCodes) {
        	if(isFirst) {
        		sb.append(KeyEvent.getKeyText(keyCode));
        	}else {
        		sb.append(" " + KeyEvent.getKeyText(keyCode));
        	}
        }

        return sb.toString();
    }

	@Override
	public void keyTyped(KeyEvent e) {
		for(KeyListener li : keyListeners) {
			li.keyTyped(e);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
		for(KeyListener li : keyListeners) {
			li.keyPressed(e);
		}
	
	}

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		for(KeyListener li : keyListeners) {
			li.keyReleased(e);
		}
	
	}
	
}
