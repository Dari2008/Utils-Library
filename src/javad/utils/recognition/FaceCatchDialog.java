package javad.utils.recognition;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Scanner;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_core.Scalar;
import org.bytedeco.javacpp.opencv_core.Size;
import org.bytedeco.javacpp.opencv_imgcodecs;
import org.bytedeco.javacpp.opencv_imgproc;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;
import org.bytedeco.javacv.CanvasFrame;
import org.bytedeco.javacv.Frame;
import org.bytedeco.javacv.FrameGrabber.Exception;
import org.bytedeco.javacv.OpenCVFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

public class FaceCatchDialog {
	private OpenCVFrameGrabber camera;
	private char lastkey = 0;
	
	public static void main(String[] args) {
		new FaceCatchDialog(0, 0, new File("src/javad/utils/recognition/resources/photos/"));
	}
	
	public FaceCatchDialog(int device, int faceId, File fileToSave) {
		
		try {
		
		KeyEvent keyboardKey = null; // catch keyborad key
		OpenCVFrameConverter.ToMat convertMat = new OpenCVFrameConverter.ToMat(); // convert image to Mat
		OpenCVFrameGrabber camera1 = new OpenCVFrameGrabber(device); // capturing webcam images
		
		camera1.start();

		CascadeClassifier faceDetector = new CascadeClassifier(
				"src/javad/utils/recognition/resources/haarcascade_frontalface_alt.xml");
		CanvasFrame cFrame = new CanvasFrame("Preview", CanvasFrame.getDefaultGamma() / camera1.getGamma()); // drawing
		cFrame.requestFocus();
		cFrame.setExtendedState(CanvasFrame.MAXIMIZED_BOTH);
		cFrame.setAutoRequestFocus(true);
		cFrame.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				lastkey = e.getKeyChar();
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				lastkey = e.getKeyChar();
			}
		});																					// a
																												// window
		cFrame.setSize(1200, 1200);
		Frame capturedFrame = null; // object to the captured frame
		Mat colorImage = new Mat(); // transfer from frame to color image for face detection
		int sampleNumber = 50;
		int sample = 1;

		if(faceId < 0) {
			System.out.println("Enter your ID:");
			Scanner register = new Scanner(System.in);
			faceId = register.nextInt();
		}
		
		
		while ((capturedFrame = camera1.grab()) != null) {
			colorImage = convertMat.convert(capturedFrame);
			Mat grayImage = new Mat();
			opencv_imgproc.cvtColor(colorImage, grayImage, opencv_imgproc.COLOR_BGRA2GRAY); // convert image to gray for better detection
			RectVector detectedFaces = new RectVector(); // store detected faces
			faceDetector.detectMultiScale(grayImage, detectedFaces, 1.1, 1, 0, new Size(150, 150), new Size(500, 500));

			for (int i = 0; i < detectedFaces.size(); i++) { // cycle detected faces vector
				Rect faceData = detectedFaces.get(0);
				opencv_imgproc.rectangle(colorImage, faceData, new Scalar(0, 0, 255, 0)); // insert rectangle in color
																							// image
				Mat capturedface = new Mat(grayImage, faceData);
				opencv_imgproc.resize(capturedface, capturedface, new Size(160, 160));

				if(lastkey == 'r') {
					System.out.println(keyboardKey.getKeyCode());
					System.out.println(keyboardKey.getKeyChar());
					if (keyboardKey.getKeyChar() == 'r') {
						if (sample <= sampleNumber) {
							opencv_imgcodecs.imwrite(   //recording face image
									fileToSave + "//person." + faceId + "." + sample + ".jpg",
									capturedface);
							System.out.println("Photo " + sample + " captured\n");
							sample++;
						}
					}
					lastkey = ' ';
				}
			}

			if (keyboardKey == null) {
				keyboardKey = cFrame.waitKey(20);
			}

			if (cFrame.isVisible()) {
				cFrame.showImage(capturedFrame);
			}

			if (sample > sampleNumber) {
				break;
			}
		}
		cFrame.dispose(); // free memory
		camera1.stop();
		camera1.close();
		colorImage.close();
		faceDetector.close();
		
		}catch(Exception | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	

}
