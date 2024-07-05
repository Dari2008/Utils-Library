package javad.utils.gui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LayoutManager;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.function.Consumer;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javad.utils.style.Style;
import javad.utils.style.Utils;

public class GuiUtils {

	private static Alert alert = null;
	
	public static Alert alert(Container parent, String message, int animationTime, boolean useHtml, boolean paintFocus, boolean centered, Consumer<String> action) {
		
		Style.setSystemLookAndFeel();
		
		LayoutManager m = parent.getLayout();
		
		
		Rectangle2D r = Utils.getStringDimension(message, new JLabel().getFont());
		
		int times = message.split("\n").length;
		int width = 0;
		int height = (int) r.getHeight() * times;
		if(height > parent.getHeight()/3) {
			height = parent.getHeight()/3;
		}else {
			if(height < 100) {
				height = 100;
			}
		}
		
		
		for(String s : message.split("\n")) {
			Rectangle2D re = Utils.getStringDimension(s, new JLabel().getFont());
			if(re.getWidth() > width) {
				width = (int) re.getWidth();
			}
		}
		
		if(width < 210) {
			width = 210;
		}else {
			width = width + 50;
		}
		
		if(width >= parent.getWidth()-40) {
			width = parent.getWidth()-40;
		}
		
		JPanel back = new JPanel();
		back.setLayout(null);
		back.setBounds(0, 0, parent.getWidth(), parent.getHeight());
		back.setBackground(new Color(81, 81, 81, 170));
		back.setForeground(new Color(81, 81, 81, 170));
		
		JPanel panel = new JPanel() {
			
			protected void paintComponent(Graphics g) {

			    Graphics2D g2 = (Graphics2D) g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			            RenderingHints.VALUE_ANTIALIAS_ON);
			    
			    if(!isVisible())return;
			    
			    g2.setColor(Color.WHITE);
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); 

				super.paintComponent(g2);
			}
		};
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(null);
		panel.setSize(width, height);
		panel.setLocation(back.getWidth()/2 - panel.getWidth()/2 - 7, 0);
		
		JButton btnNewButton = new JButton("Ok");
		btnNewButton.setFocusPainted(paintFocus);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.accept("Ok");
				alert.animationOut();
			}
		});
		btnNewButton.setBounds(width-102, height-38, 89, 23);
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.setFocusPainted(paintFocus);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action.accept("Cancel");
				alert.animationOut();
			}
		});
		btnNewButton_1.setBounds(width-201, height-38, 89, 23);
		panel.add(btnNewButton_1);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(null);
		scrollPane.setBackground(new Color(0, 0, 0, 0));
		scrollPane.setBounds(10, 10, width-20, height-60);
		panel.add(scrollPane);
		
		String text;
		
		if(useHtml) {
			message = message.replaceAll("\n", "<br>");
			text = "<html>" + message + "</html>";
		}else {
			text = message;
		}
		

		JLabel lblNewLabel = new JLabel(text);
		if(centered)lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		if(centered)lblNewLabel.setVerticalAlignment(SwingConstants.CENTER);
		scrollPane.setViewportView(lblNewLabel);
		
		alert = new Alert(parent, panel, back, animationTime, height, m, text);
		alert.addToParent();
		alert.animationIn();
		
		Style.resetLookAndFeel();
		return alert;
	}

	public static Alert alert(Container parent, String message, int animationTime, boolean paintFocus, boolean centered, Consumer<String> action) {
		return alert(parent, message, animationTime, true, paintFocus, centered, action);
	}

	public static Alert alert(Container parent, String message, int animationTime, boolean centered, Consumer<String> action) {
		return alert(parent, message, animationTime, true, false, centered, action);
	}

	public static Alert alert(Container parent, String message, Consumer<String> action) {
		return alert(parent, message, 250, true, false, true, action);
	}

	public static Alert alert(Container parent, String message, int animationTime, Consumer<String> action) {
		return alert(parent, message, animationTime, true, false, true, action);
	}
	
	public static Alert alertOwn(Container parent, int animationTime, int width, int height, Object[] obj, Function<String, Boolean> action) {
		
		Style.setSystemLookAndFeel();
		
		JPanel back = new JPanel();
		back.setLayout(null);
		back.setBounds(0, 0, parent.getWidth(), parent.getHeight());
		back.setBackground(new Color(81, 81, 81, 170));
		back.setForeground(new Color(81, 81, 81, 170));
		
		JPanel panel = new JPanel() {
			
			protected void paintComponent(Graphics g) {

			    Graphics2D g2 = (Graphics2D) g;
			    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
			            RenderingHints.VALUE_ANTIALIAS_ON);
			    
			    if(!isVisible())return;
			    
			    g2.setColor(Color.WHITE);
			    g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); 

				super.paintComponent(g2);
			}
		};
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setLayout(null);
		panel.setSize(width, height);
		panel.setLocation(back.getWidth()/2 - panel.getWidth()/2 - 7, 0);
		
		for(Object o : obj) {
			if(o instanceof JButton) {
				JButton btn = (JButton)o;
				btn.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						boolean close = action.apply(btn.getActionCommand());
						if(close) {
							alert.animationOut();
						}else {
							btn.repaint();
							btn.revalidate();
							btn.validate();
							btn.repaint();
						}
					}
				});
				panel.add(btn);
			}else if(o instanceof Component) {
				Component c = (Component)o;
				panel.add(c);
			}
		}
		alert = new Alert(parent, panel, back, animationTime, height, panel.getLayout(), null);
		alert.addToParent();
		alert.animationIn();
		
		Style.resetLookAndFeel();
		
		return alert;
	}
	
}
