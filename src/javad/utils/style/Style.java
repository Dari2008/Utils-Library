package javad.utils.style;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;

public class Style {

	
	private static String oldSystemLookAndFeel = null;
	
	public static void setSystemLookAndFeel() {
		
		if(oldSystemLookAndFeel == null) oldSystemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void resetLookAndFeel() {
		try {
			UIManager.setLookAndFeel(oldSystemLookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void flatDarkLaf() {
		if(oldSystemLookAndFeel == null) oldSystemLookAndFeel = UIManager.getSystemLookAndFeelClassName();
		
		FlatDarkLaf.setup();
		
	}
	
}
