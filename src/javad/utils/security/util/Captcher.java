package javad.utils.security.util;

import java.awt.Graphics;
import java.text.AttributedCharacterIterator;

import javad.utils.graphics.PaintComponent;
import javad.utils.math.Random;
import javad.utils.security.cryptology.Cäsar;

public class Captcher extends PaintComponent{

	private String text = null;
	
	public Captcher(String text) {
		if(text == null) {
			Cäsar c = new Cäsar(Random.nextInt(1, 26));
			this.text = c.encrypt("wertzuzz");
		}else {
			Cäsar c = new Cäsar(Random.nextInt(1, 26));
			this.text = c.encrypt(text);
		}
		System.out.println(this.text);
		
		
	}	

	public PaintComponent getPaintComponenet() {
		return this;
	}

	
	
	@Override
	public void paint(Graphics g) {
		
//		g.drawString(, NEXT, ABORT);
		
	}
	
}
