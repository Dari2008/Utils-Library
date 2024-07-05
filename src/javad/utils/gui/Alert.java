package javad.utils.gui;

import java.awt.Container;
import java.awt.LayoutManager;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

public class Alert {

	private Container parent;
	private Container panel;
	private Container back;
	private int animationTime = 500;
	private int height;
	private LayoutManager m;
	private String text;
	
	
	
	public Alert(Container parent, Container panel, Container back, int animationTime, int height, LayoutManager m, String text) {
		super();
		this.parent = parent;
		this.panel = panel;
		this.back = back;
		this.animationTime = animationTime;
		this.height = height;
		this.m = m;
		this.text = text;
		back.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				beep();
			}
			
		});
	}

	public void beep() {
		Toolkit.getDefaultToolkit().beep();
	}
	
	public void animationOut() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int y = 0;
			@Override
			public void run() {
				if(y <= -(height+10)) {
					y = -(height+10);
					panel.setLocation(panel.getX(), y);
					parent.remove(back);
					parent.repaint();
					parent.revalidate();
					parent.validate();
					parent.repaint();
					parent.setLayout(m);
					t.cancel();
					return;
				}
				panel.setLocation(panel.getX(), y);
				back.repaint();
				y--;
			}
		}, (int)animationTime/height, (int)animationTime/height);
	}
	
	public void animationIn() {
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			int y = -(height+10);
			@Override
			public void run() {
				if(y >= 0) {
					y = 0;
					panel.setLocation(panel.getX(), y);
					t.cancel();
					return;
				}
				panel.setLocation(panel.getX(), y);
				y++;
			}
		}, (int)animationTime/height, (int)animationTime/height);
	}
	
	public void addToParent() {
		parent.setLayout(null);
		back.add(panel);
		parent.add(back);
	}
	
}
