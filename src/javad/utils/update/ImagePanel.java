package javad.utils.update;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javad.utils.style.Utils;

public class ImagePanel extends JPanel {

	private ArrayList<Image> frames = new ArrayList<>();
	private int index = 0;
	private Consumer<int[]> setSize;
	private int plus = 300;
	private String text = "Copying";
	private JLabel lblNewLabel = new JLabel("Copying...");
	
	public ImagePanel(Object f, Consumer<int[]> setSize) {
		this.setSize = setSize;
		setSize(640, 180);
		setLayout(null);
		

		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 37));
		lblNewLabel.setBackground(new Color(0, 0, 0, 0));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		
		Rectangle2D r = Utils.getStringDimension(text, lblNewLabel.getFont());
		
		setBackground(new Color(0, 0, 0, 0));
		
		try(ImageInputStream in = ImageIO.createImageInputStream(f)){
			ImageReader reader = ImageIO.getImageReaders(in).next();
            reader.setInput(in);
            int numFrames = reader.getNumImages(true);
            for (int i = 0; i < numFrames; i++) {
                BufferedImage image = reader.read(i);
                frames.add(image);
            }
    		setSize(frames.get(0).getWidth(this), frames.get(0).getHeight(this)-plus);
            setSize.accept(new int[] {getWidth(), getHeight()});
		}catch(Exception e) {
			e.printStackTrace();
		}
		lblNewLabel.setSize(265, 85);
		lblNewLabel.setLocation((int) (getWidth()/2 - r.getWidth()/2), 11);
		add(lblNewLabel);
	}
	
	public void updateFrame() {
		if(index >= frames.size()-1) {
			index = 0;
		}else {
			index++;
		}
		if(index%2 == 0) {
			if(text.equals("Copying...")) {
				text = "Copying";
			}else {
				text += ".";
			}
		}
		
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		setSize(frames.get(0).getWidth(this), frames.get(0).getHeight(this)-plus);
        setSize.accept(new int[] {getWidth(), getHeight()});
		g.drawImage(frames.get(index), 0, -150, frames.get(index).getWidth(this), frames.get(index).getHeight(this), this);
		
		lblNewLabel.setText(text);
		super.paintComponent(g);
		
		repaint();
	}
}
