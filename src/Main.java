import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Point;
import java.awt.Toolkit;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;

import javad.utils.graphics.panel.GraphicsPane;
import javad.utils.graphics.panel.objects.Circle;
import javad.utils.graphics.panel.objects.FilledRectangle;
import javad.utils.graphics.panel.objects.Line;

public class Main extends JFrame {

	private JPanel contentPane;
	private Circle ball;
	private Point last = null;
	
	static{
		FlatDarkLaf.setup();
	}
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, Toolkit.getDefaultToolkit().getScreenSize().width, Toolkit.getDefaultToolkit().getScreenSize().height);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
		GraphicsPane panel = new GraphicsPane();
		
		ball = new Circle(4, new Point(getWidth()/2, getHeight()/2), 1);
		
		panel.addObject(ball);
		
		FilledRectangle rect = new FilledRectangle(panel.getWidth()-1, panel.getHeight()-1, new Point(1, 1), 0);
		panel.setSetting(rect, "COLOR", Color.RED);
		panel.addObject(rect);
		
		
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {

			
			private float x = ball.getLocation().x;
			private float y = ball.getLocation().y;
			
			private float dirX = 1;
			private float dirY = 1;
			
			private final int SPEED = 6;
			
			@Override
			public void run() {
				while(true) {
					
					last = new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2);
					Line tmp = new Line(last, new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2), 1);
					panel.addObject(tmp);
					
					x += SPEED * dirX;
					y += SPEED * dirY;
					tmp.setP1(last);
					tmp.setP2(new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2));
					
					ball.setLocation(new Point((int)x, (int)y));
					
					float tmpX = x + ball.getObjectSize().getWidth();
					float tmpY = y + ball.getObjectSize().getHeight();
					
					if(tmpX > panel.getWidth()) {
						dirX = -1;
						Point current = new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2);
						panel.addObject(new Line(current, last, 0));
						last = current;
					}else if(x < 0) {
						dirX = +1;
						Point current = new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2);
						panel.addObject(new Line(current, last, 0));
						last = current;
					}
					
					if(tmpY > panel.getHeight()) {
						dirY = -1;
						Point current = new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2);
						panel.addObject(new Line(current, last, 0));
						last = current;
					}else if(y < 0) {
						dirY = +1;
						Point current = new Point((int)x + ball.getWidth()/2, (int)y + ball.getHeight()/2);
						panel.addObject(new Line(current, last, 0));
						last = current;
					}

						panel.repaint();
					try {
						Thread.sleep(5);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				
				
				
				}
				
			}
		}, 1, 1);
		
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1059, Short.MAX_VALUE)
					.addGap(1))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(1)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 617, Short.MAX_VALUE)
					.addGap(1))
		);
		contentPane.setLayout(gl_contentPane);
		
	}
}