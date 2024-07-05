package javad.utils.keyboard;

public class HotKeyEvent {

	private int keyCode=-1, modifiers=-1, ID=-1;
	
	public HotKeyEvent(int keyCode, int modifiers, int ID) {
		this.keyCode = keyCode;
		this.modifiers = modifiers;
		this.ID = ID;
	}

	public int getKeyCode() {
		return keyCode;
	}

	public int getModifiers() {
		return modifiers;
	}

	public int getID() {
		return ID;
	}
}
