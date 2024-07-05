package javad.utils.graphics;

import java.awt.Graphics;

import javax.swing.JLabel;

public abstract class PaintComponent extends JLabel{

	@Override
	protected void paintComponent(Graphics g) {
		
		paint(g);
		
		repaint();
		super.paintComponent(g);
	}

	public PaintComponent(JLabel l) {
		 this.ui =  l.getUI();
		 this.labelFor =  l.getLabelFor();
		 this.accessibleContext =  l.getAccessibleContext();
		 this.setText(l.getText());
		 this.setBounds(l.getBounds());
	}

	public PaintComponent() {
		new JLabel();
		setVisible(true);
	}
	
	public abstract void paint(Graphics g);

}
