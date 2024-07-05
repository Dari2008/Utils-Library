package javad.utils.keyboard;

import java.lang.annotation.Native;
import java.util.ArrayList;
import java.util.Random;

import com.sun.jna.Pointer;

import com.sun.jna.platform.win32.User32;

public class HotKey implements Runnable{


	private static ArrayList<Integer> keys = new ArrayList<>();
	
	private boolean success = false;
	private final int id;
	private final int keyCode;
	private final int modifiers;
	private HotKeyListener li;
	private Object other = null;
	
	protected HotKey(int keyCode, int modifiers, HotKeyListener li) {
    	this.keyCode = keyCode;
    	this.modifiers = modifiers;
    	this.li = li;
        id = generateRandomID();
	}

	private boolean register() {
		User32.HWND hWnd = new User32.HWND();
		other = hWnd;
        return success = User32.INSTANCE.RegisterHotKey(hWnd, id, modifiers, keyCode);
	}
	
	public boolean unregister() {
		Pointer p = Pointer.NULL;
		other = p;
		success = !User32.INSTANCE.UnregisterHotKey(p, id);
        return !success;
	}
	
	public Object getOther() {
		return other;
	}
	
	public boolean isRegistered() {
		return success;
	}
	
	public static ArrayList<Integer> getUsedKeys() {
		return keys;
	}

	public boolean isSuccess() {
		return success;
	}

	public int getID() {
		return id;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getModifiers() {
		return modifiers;
	}

	public HotKeyListener getLi() {
		return li;
	}

	@Override
	public void run() {
		register();
		User32 user32 = User32.INSTANCE;
		
        User32.MSG msg = new User32.MSG();
        while (user32.GetMessage(msg, null, 0, 0) != 0) {
        	if (msg.message == User32.WM_HOTKEY && msg.wParam.intValue() == id) {
	            li.hotKeyPressed(new HotKeyEvent(keyCode, modifiers, id));
	        }
	        user32.TranslateMessage(msg);
	        user32.DispatchMessage(msg);
	   }
    }

	private int generateRandomID() {
		int k = 0;
		
		while(keys.contains(k) && k == 0) {
			k = new Random().nextInt();
		}
		
		keys.add(k);
		return k;
	}
}
