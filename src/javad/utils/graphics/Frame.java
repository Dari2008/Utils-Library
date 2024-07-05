package javad.utils.graphics;

import javax.swing.JFrame;

public class Frame extends JFrame{


	public Frame(String title, boolean isVisible, boolean isFullScreen, int x, int y, int w, int h) {
		
		new JFrame(title);
		setVisible(isVisible);
		if(isFullScreen) {
			setBounds(x, y, w, h);
			setExtendedState(MAXIMIZED_BOTH);
		}else {
			
			setBounds(x, y, w, h);
			
		}
		
	}
	
	public Frame(String title, boolean isVisible) {
		new JFrame(title);
		setVisible(isVisible);
		setTitle(title);
	}
	
	
	
}
