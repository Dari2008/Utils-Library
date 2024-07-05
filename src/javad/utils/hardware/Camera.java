package javad.utils.hardware;

import javax.swing.Icon;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamMotionDetector;
import com.github.sarxos.webcam.WebcamMotionEvent;
import com.github.sarxos.webcam.WebcamMotionListener;

public class Camera{

	public static boolean isMotion(int timeInMillis, int checksPerSecond, Webcam w) {
		w.open();
		
		WebcamMotionDetector detector = new WebcamMotionDetector(w);
		detector.setInterval(100);
		detector.addMotionListener(new WebcamMotionListener() {
			
			@Override
			public void motionDetected(WebcamMotionEvent arg0) {
				
				
			}
		});
		detector.start();
		
		while(timeInMillis > 0) {
			if(detector.isMotion()) {
				detector.stop();
				w.close();
				return true;
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			timeInMillis--;
		}
		
		detector.stop();
		w.close();
	
		return false;
		
	}
	
public static int getImageHeight(Webcam w) {
		
		Icon i = (Icon)w.getImage();
		return i.getIconHeight();
		
	}


public static int getImageWidth(Webcam w) {
	
	Icon i = (Icon)w.getImage();
	return i.getIconWidth();
	
}

	
	
}
