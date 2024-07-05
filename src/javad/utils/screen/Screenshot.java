package javad.utils.screen;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;

import com.sun.jna.platform.win32.User32;

import oshi.jna.platform.windows.WinNT;

public class Screenshot {

	public Screenshot() {
	}

	public static BufferedImage createScreenshot() {
		try {
			return new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height));
		} catch (HeadlessException | AWTException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage createScreenshot(int w, int h) {
		try {
			return new Robot().createScreenCapture(new Rectangle(w, h));
		} catch (HeadlessException | AWTException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static BufferedImage createScreenshot(Window w) {
		return w.getGraphicsConfiguration().createCompatibleImage(w.getWidth(), w.getHeight());
	}
	
	public static BufferedImage getScreenshotOfWindowWithTitle(String title) throws WindowNotFount{
        try {
            WinNT.HWND windowHandle = User32.INSTANCE.FindWindow(null, title);

            if (windowHandle != null) {
                User32.INSTANCE.SetForegroundWindow(windowHandle);

                WinNT.RECT windowRect = new WinNT.RECT();
                User32.INSTANCE.GetWindowRect(windowHandle, windowRect);

                int windowWidth = windowRect.right - windowRect.left;
                int windowHeight = windowRect.bottom - windowRect.top;

                return new Robot().createScreenCapture(new Rectangle(windowRect.left, windowRect.top, windowWidth, windowHeight));
            } else {
            	throw new WindowNotFount("The Window with the title: " + title + " could not be found!");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
		return null;
    }
	
	
}
