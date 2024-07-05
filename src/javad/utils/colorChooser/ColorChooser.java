package javad.utils.colorChooser;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

public class ColorChooser extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private static BufferedImage imgCircle;
	private static BufferedImage imgPointer;
	private static BufferedImage imgH;
	private static BufferedImage imgVis;
	private int circleX = 0;
	private int circleY = 0;
	private int x = 0;
	private int y = 0;
	
	private static ArrayList<Color> colors = new ArrayList<>();
	private static boolean isMore = false;
	private static int frameX = isMore?Toolkit.getDefaultToolkit().getScreenSize().width/2:Toolkit.getDefaultToolkit().getScreenSize().width/2-219/2;
	private static int frameY = isMore?Toolkit.getDefaultToolkit().getScreenSize().height/2:Toolkit.getDefaultToolkit().getScreenSize().height/2-308/2;

	private int xSelected = -1;
	private int ySelected = -1;
	
	private int Rx = 0;
	private int Ry = 0;
	private int Gx = 0;
	private int Gy = 0;
	private int Bx = 0;
	private int By = 0;

	private int RVal = 0;
	private int GVal = 0;
	private int BVal = 0;
	private int AVal = 255;

	private int HVal = 0;
	private int SVal = 0;
	private int VVal = 0;

	private int xHover = 0;
	private int yHover = 0;
	
	private boolean toolTipEnabled = true;
	
	private JTextField textField;

	private boolean isClosed = false;
	
	static {
		try {
			imgCircle = ImageIO.read(ColorChooser.class.getClassLoader().getResource("javad/utils/colorChooser/ColorChooserUI.png"));
			imgPointer = ImageIO.read(ColorChooser.class.getClassLoader().getResource("javad/utils/colorChooser/Pointer.png"));
			imgH = ImageIO.read(ColorChooser.class.getClassLoader().getResource("javad/utils/colorChooser/H.png"));
			imgVis = ImageIO.read(ColorChooser.class.getClassLoader().getResource("javad/utils/colorChooser/Visible.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
//		try {
//			ColorChooser dialog = new ColorChooser(new Listener() {
//				
//				@Override
//				public void onChoose(Color c) {
//					// TODO Auto-generated method stub
//					
//				}
//			});
//			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//			dialog.setVisible(true);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		ColorChooser.showDialog(Color.white, true, new Listener() {
			
			@Override
			public void onChoose(Color c) {
				System.out.println(c);
			}
		});

	}

	/**
	 * Create the dialog.
	 */

	public ColorChooser(boolean toolTipEnabled, Listener li) {
		this(Color.WHITE, li);
		setToolTipEnabled(toolTipEnabled);
	}


	public ColorChooser(Color initColor, boolean toolTipEnabled, Listener li) {
		this(initColor, li);
		setToolTipEnabled(toolTipEnabled);
	}
	
	public ColorChooser(Color initColor, Listener li) {
		this(li);
		setColor(initColor);
	}
	
	public ColorChooser(Listener li) {

		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		setResizable(false);
		setSize(!isMore?219:527, !isMore?308:396);
		setLocation(frameX, frameY);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		addComponentListener(new ComponentAdapter() {
			
			@Override
			public void componentMoved(ComponentEvent e) {
				frameX = getX();
				frameY = getY();
			}
			
		});
		
		
		JSpinner bspinner = new JSpinner();
		bspinner.setBounds(377, 93, 60, 20);
		contentPanel.add(bspinner);
		
		JSpinner gspinner = new JSpinner();
		gspinner.setBounds(377, 68, 60, 20);
		contentPanel.add(gspinner);
		
		JSpinner rspinner = new JSpinner();
		rspinner.setBounds(377, 43, 60, 20);
		contentPanel.add(rspinner);

		
			JPanel currentColor = new JPanel() {
				
				@Override
				protected void paintComponent(Graphics gg) {
					Graphics2D g = (Graphics2D)gg;
					
					for(int x = 0; x < getWidth()/imgVis.getWidth(); x++) {
						for(int y = 0; y < getHeight()/imgVis.getHeight()+1; y++) {
							g.drawImage(imgVis, x*imgVis.getWidth(), y*imgVis.getHeight(), null);
						}
					}
					
					g.setColor(new Color(RVal, GVal, BVal, AVal));
					g.fillRect(0, 0, getWidth(), getHeight());
					
					repaint();
				}
				
			};
			currentColor.setBounds(10, 11, 24, 24);
			contentPanel.add(currentColor);
			
			JPanel colorHistory = new JPanel() {
				//16*6
				@Override
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					
					Graphics2D g = (Graphics2D)gg;
					
					if(colors.size() > 16*6) {
						colors.remove(colors.size()-1);
					}
					
					int pW = getWidth()/16;
					int pH = getHeight()/6;
					int index = 0;
					
					for(Color c : colors) {
						
						int x = index%16;
						int y = (index/16);
						g.setColor(c);
						g.fillRect(x*pW, y*pH, pW, pH);
						
						index++;
					}
					 
					
					if(!(xSelected <= -1 || ySelected <= -1)) {
						g.setStroke(new BasicStroke(1));
						g.setColor(new Color(0, 0, 0));
						g.drawRect(xSelected*pW, ySelected*pH, pW, pH);
						g.setColor(new Color(255, 255, 255));
						g.drawRect(xSelected*pW+1, ySelected*pH+1, pW-2, pH-2);
					}
					g.setColor(Color.BLACK);
					
					g.drawRect(0, 0, getWidth()-1, getHeight()-1);
					
					repaint();
				}
				
			};
			colorHistory.setBounds(10, 201, 12*16, 12*6);
			colorHistory.addMouseMotionListener(new MouseAdapter() {

				@Override
				public void mouseMoved(MouseEvent e) {
					set(e);
				}
				
				@Override
				public void mouseDragged(MouseEvent e) {
					set(e);
				}

				private void set(MouseEvent e) {
					if(e.getX() > colorHistory.getWidth())return;
					if(e.getX() < 0)return;
					if(e.getY() > colorHistory.getHeight())return;
					if(e.getY() < 0)return;

					int pW = colorHistory.getWidth()/16;
					int pH = colorHistory.getHeight()/6;


					xSelected = e.getX()/pW;
					ySelected = e.getY()/pH;
					
					if(colors.size() > (xSelected+(ySelected*16))) {
						Color c = colors.get(xSelected+(ySelected*16));
						if(toolTipEnabled) {
							colorHistory.setToolTipText(c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ", " + c.getAlpha());
							ToolTipManager.sharedInstance().setReshowDelay(20);
							ToolTipManager.sharedInstance().setDismissDelay(2000);
						}
					}else {
						if(toolTipEnabled) {
							ToolTipManager.sharedInstance().setReshowDelay(0);
							ToolTipManager.sharedInstance().setDismissDelay(0);
							colorHistory.setToolTipText("");
						}
					}
					

				}
				
			});
			
			
			colorHistory.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					if(e.getX() > colorHistory.getWidth())return;
					if(e.getX() < 0)return;
					if(e.getY() > colorHistory.getHeight())return;
					if(e.getY() < 0)return;
					if(xSelected <= -1 || ySelected <= -1)return;
					if(colors.size() > (xSelected+(ySelected*16))) {
						Color c = colors.get(xSelected+(ySelected*16));
						RVal = c.getRed();
						GVal = c.getGreen();
						BVal = c.getBlue();
						AVal = c.getAlpha();
						
						float[] f = new float[3];
						f = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), f);
						
						HVal = (int) map(f[0], 0f, 1f, 0, 360);
						SVal = (int) map(f[1], 0f, 1f, 0, 100);
						VVal = (int) map(f[2], 0f, 1f, 0, 100);
					}
					
				}

				
			});
			contentPanel.add(colorHistory);

			JPanel circleChooser = new JPanel() {
				
				
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					
					g.drawImage(imgCircle, 0, 0, getWidth(), getHeight(), null);
					Color before = g.getColor();
					g.setColor(currentColor.getBackground());
					g.fillOval(circleX-3, circleY-3, 6, 6);
					g.setColor(Color.BLACK);
					g.drawOval(circleX-3, circleY-3, 6, 6);
					
					if(xHover > 0 && yHover > 0) {
						try {
							g.setColor(new Color(imgCircle.getRGB(map(xHover, 0, getWidth(), 0, imgCircle.getWidth()), map(yHover, 0, getHeight(), 0, imgCircle.getHeight()))));
							g.setColor(Color.DARK_GRAY);
							g.drawOval(xHover-3, yHover-3, 6, 6);
						}catch(Exception e) {
							
						}						
					}
					
					
					g.setColor(before);
//					g.drawLine(getWidth()/2, getHeight()/2, x, y);
					repaint();
				}
				
			};
			
			circleChooser.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					if(toolTipEnabled) {
						ToolTipManager.sharedInstance().setReshowDelay(20);
						ToolTipManager.sharedInstance().setDismissDelay(2000);
					}
					xHover = -1;
					yHover = -1;
				}
				
			});
			
			circleChooser.addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseMoved(MouseEvent e) {
					try {
						if(e.getX() > circleChooser.getWidth() || e.getY() > circleChooser.getHeight()) {
							return;
						}
						Color color = new Color(imgCircle.getRGB(map(e.getX(), 0, circleChooser.getWidth(), 0, imgCircle.getWidth()), map(e.getY(), 0, circleChooser.getHeight(), 0, imgCircle.getHeight())));
						if(color.getRGB() != -16777216) {
							if(toolTipEnabled) {
								ToolTipManager.sharedInstance().setReshowDelay(20);
								ToolTipManager.sharedInstance().setDismissDelay(2000);
							}
							xHover = e.getX();
							yHover = e.getY();
							circleChooser.setToolTipText(color.getRed() + ", " + color.getGreen() + ", " + color.getBlue() + ", " + color.getAlpha());
						}else {
							xHover = -1;
							yHover = -1;
						}
					}catch(Exception ex) {}

				}
				
				
				
				@Override
				public void mouseDragged(MouseEvent e) {
					xHover = -1;
					yHover = -1;
					try {
						if(e.getX() > circleChooser.getWidth() || e.getY() > circleChooser.getHeight()) {
							setPos(e);
							return;
						}
						int color = imgCircle.getRGB(map(e.getX(), 0, circleChooser.getWidth(), 0, imgCircle.getWidth()), map(e.getY(), 0, circleChooser.getHeight(), 0, imgCircle.getHeight()));
						
						if(color != 0) {
							circleX = e.getX();
							circleY = e.getY();
							currentColor.setBackground(new Color(color));
							RVal = new Color(color).getRed();
							GVal = new Color(color).getGreen();
							BVal = new Color(color).getBlue();
							rspinner.setValue(RVal);
							gspinner.setValue(GVal);
							bspinner.setValue(BVal);
							textField.setText(toHex(color));
						}else {
							setPos(e);
						}
					}catch(Exception ex) {setPos(e);}

				}

				private void setPos(MouseEvent e) {
					int xMit = circleChooser.getWidth()/2;
					int yMit = circleChooser.getHeight()/2;
					
					x = e.getX();
					y = e.getY();
				}
				
			});
			circleChooser.setBounds(20, 46, 150, 150);
			contentPanel.add(circleChooser);
			
			JButton btnNewButton = new JButton("Mehr >>");
			btnNewButton.setFocusPainted(false);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(btnNewButton.getText().equals("Mehr >>")) {
						setSize(527, 396);
						btnNewButton.setText("Weniger <<");
						isMore = true;
					}else {
						setSize(219, 308);
						btnNewButton.setText("Mehr >>");
						isMore = false;
					}
					
				}
			});
			btnNewButton.setBounds(99, 12, 103, 23);
			contentPanel.add(btnNewButton);
			
			JLabel lblNewLabel = new JLabel("RGB") {
				@Override
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawLine(35, getHeight()/2+2, getWidth(), getHeight()/2+2);
				}
			};
			lblNewLabel.setBounds(226, 15, 211, 14);
			contentPanel.add(lblNewLabel);
			
			JLabel lblNewLabel_2 = new JLabel("R:");
			lblNewLabel_2.setBounds(226, 46, 16, 14);
			contentPanel.add(lblNewLabel_2);
			
			JPanel panel = new JPanel() {
				
				@Override
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					g.setPaint(new GradientPaint(new Point(0, 0), new Color(0, GVal, BVal), new Point(getWidth(), getHeight()), new Color(255, GVal, BVal)));
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(RVal, 0, 255, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
				
			};
			panel.addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel.getWidth()-7)return;
					if(e.getX() < 0)return;
					RVal = map(e.getX(), 0, panel.getWidth()-7, 0, 255);
					rspinner.setValue(RVal);
					

					
					float[] f = new float[3];
					
					f = Color.RGBtoHSB(RVal, GVal, BVal, f);

					HVal = (int)map(f[0], 0f, 1f, 0f, 360f);
					SVal = (int)map(f[1], 0f, 1f, 0f, 100f);
					VVal = (int)map(f[2], 0f, 1f, 0f, 100f);
					
					
					textField.setText(toHex(new Color(RVal, GVal, BVal).getRGB()));
				}
				
			});
			panel.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel.setBounds(252, 46, 124, 24);
			contentPanel.add(panel);
			
			JLabel lblNewLabel_2_1 = new JLabel("G:");
			lblNewLabel_2_1.setBounds(226, 71, 16, 14);
			contentPanel.add(lblNewLabel_2_1);
			
			JPanel panel_1 = new JPanel() {
				
				@Override
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					g.setPaint(new GradientPaint(new Point(0, 0), new Color(RVal, 0, BVal), new Point(getWidth(), getHeight()), new Color(RVal, 255, BVal)));
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(GVal, 0, 255, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
				
			};
			panel_1.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_1.addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel_1.getWidth()-7)return;
					if(e.getX() < 0)return;
					GVal = map(e.getX(), 0, panel_1.getWidth()-7, 0, 255);
					gspinner.setValue(GVal);
					
					float[] f = new float[3];
					
					f = Color.RGBtoHSB(RVal, GVal, BVal, f);

					HVal = (int)map(f[0], 0f, 1f, 0f, 360f);
					SVal = (int)map(f[1], 0f, 1f, 0f, 100f);
					VVal = (int)map(f[2], 0f, 1f, 0f, 100f);
					
					textField.setText(toHex(new Color(RVal, GVal, BVal).getRGB()));
				}
				
			});
			panel_1.setBounds(252, 71, 124, 24);
			contentPanel.add(panel_1);
			
			JLabel lblNewLabel_2_2 = new JLabel("B:");
			lblNewLabel_2_2.setBounds(226, 96, 16, 14);
			contentPanel.add(lblNewLabel_2_2);
			
			JPanel panel_2 = new JPanel() {
				
				@Override
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					g.setPaint(new GradientPaint(new Point(0, 0), new Color(RVal, GVal, 0), new Point(getWidth(), getHeight()), new Color(RVal, GVal, 255)));
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(BVal, 0, 255, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
				
			};
			panel_2.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_2.addMouseMotionListener(new MouseAdapter() {
				
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel_2.getWidth()-7)return;
					if(e.getX() < 0)return;
					BVal = map(e.getX(), 0, panel_2.getWidth()-7, 0, 255);
					bspinner.setValue(BVal);
					
					float[] f = new float[3];
					
					f = Color.RGBtoHSB(RVal, GVal, BVal, f);

					HVal = (int)map(f[0], 0f, 1f, 0f, 360f);
					SVal = (int)map(f[1], 0f, 1f, 0f, 100f);
					VVal = (int)map(f[2], 0f, 1f, 0f, 100f);
					
					textField.setText(toHex(new Color(RVal, GVal, BVal).getRGB()));
				}
				
			});
			panel_2.setBounds(252, 96, 124, 24);
			contentPanel.add(panel_2);
			
			JLabel lblNewLabel_2_2_1 = new JLabel("Hex.:");
			lblNewLabel_2_2_1.setBounds(226, 121, 150, 14);
			contentPanel.add(lblNewLabel_2_2_1);
			
			textField = new JTextField();
			textField.setHorizontalAlignment(SwingConstants.RIGHT);
			textField.setBounds(377, 118, 60, 20);
			textField.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						Color result = Color.decode("#" + textField.getText());
						RVal = result.getRed();
						GVal = result.getGreen();
						BVal = result.getBlue();

						rspinner.setValue(RVal);
						gspinner.setValue(GVal);
						bspinner.setValue(BVal);
						colors.add(0, new Color(RVal, GVal, BVal, AVal));
					}catch(Exception ex) {
						textField.setText("");
					}
					
					
				}
			});
			contentPanel.add(textField);
			textField.setColumns(10);
			textField.setText(toHex(new Color(RVal, GVal, BVal).getRGB()));
			TextPrompt p = new TextPrompt("ffffff", textField);
			
			JLabel lblHsv = new JLabel("HSV") {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawLine(35, getHeight()/2+2, getWidth(), getHeight()/2+2);
				}
			};
			lblHsv.setBounds(226, 146, 211, 14);
			contentPanel.add(lblHsv);
			
			JSpinner sspinner = new JSpinner();
			sspinner.setBounds(377, 196, 60, 20);
			contentPanel.add(sspinner);
			
			
			JSpinner hspinner = new JSpinner();
			hspinner.setBounds(377, 171, 60, 20);
			contentPanel.add(hspinner);

			
			JPanel panel_3 = new JPanel() {
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					g.drawImage(imgH, 0, 0, getWidth(), getHeight()-10, null);
					g.drawImage(imgPointer, map(HVal, 0, 360, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
			};
			panel_3.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_3.addMouseMotionListener(new MouseAdapter() {
					
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel.getWidth()-7)return;
					if(e.getX() < 0)return;
					HVal = map(e.getX(), 0, panel_1.getWidth()-7, 0, 360);
					hspinner.setValue(HVal);
					Color c = Color.getHSBColor(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f));
					RVal = c.getRed();
					GVal = c.getGreen();
					BVal = c.getBlue();
					rspinner.setValue(RVal);
					gspinner.setValue(GVal);
					bspinner.setValue(BVal);
					textField.setText(toHex(Color.HSBtoRGB(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f))));
				}
					
			});
			panel_3.setBounds(252, 174, 124, 24);
			contentPanel.add(panel_3);

			
			JPanel panel_1_1 = new JPanel() {
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					
					float b = map(RVal + GVal + BVal, 0f, 100f, 0f, 1f);
					
					if(b > 1f)b = 1f;
					
					g.setPaint(new GradientPaint(new Point(0, 0), Color.getHSBColor(0f, 0f, b), new Point(getWidth(), getHeight()), new Color(imgH.getRGB(map(HVal, 0, 360, 0, imgH.getWidth()-1), 1))));
					
					
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(SVal, 0, 100, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
			};
			panel_1_1.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_1_1.addMouseMotionListener(new MouseAdapter() {
					
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel.getWidth()-7)return;
					if(e.getX() < 0)return;
					SVal = map(e.getX(), 0, panel_1_1.getWidth()-7, 0, 100);
					sspinner.setValue(SVal);
					Color c = Color.getHSBColor(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f));
					RVal = c.getRed();
					GVal = c.getGreen();
					BVal = c.getBlue();
					rspinner.setValue(RVal);
					gspinner.setValue(GVal);
					bspinner.setValue(BVal);
					textField.setText(toHex(Color.HSBtoRGB(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f))));
				}
					
			});
			panel_1_1.setBounds(252, 199, 124, 24);
			contentPanel.add(panel_1_1);
			
			JSpinner vspinner = new JSpinner();
			vspinner.setBounds(377, 221, 60, 20);
			contentPanel.add(vspinner);
			
			JPanel panel_2_1 = new JPanel() {
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					
					g.setPaint(new GradientPaint(new Point(0, 0), new Color(0, 0, 0), 
							new Point(getWidth(), getHeight()), Color.getHSBColor(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), 1f)));
					
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(VVal, 0, 100, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
			};
			panel_2_1.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_2_1.addMouseMotionListener(new MouseAdapter() {
					
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel.getWidth()-7)return;
					if(e.getX() < 0)return;
					VVal = map(e.getX(), 0, panel_2_1.getWidth()-7, 0, 100);
					vspinner.setValue(VVal);
					Color c = Color.getHSBColor(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f));
					RVal = c.getRed();
					GVal = c.getGreen();
					BVal = c.getBlue();
					rspinner.setValue(RVal);
					gspinner.setValue(GVal);
					bspinner.setValue(BVal);
					textField.setText(toHex(Color.HSBtoRGB(map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f))));
				}
					
			});
			panel_2_1.setBounds(252, 224, 124, 24);
			contentPanel.add(panel_2_1);
			
			JLabel lblNewLabel_2_2_2 = new JLabel("V:");
			lblNewLabel_2_2_2.setBounds(226, 224, 16, 14);
			contentPanel.add(lblNewLabel_2_2_2);
			
			JLabel lblNewLabel_2_1_1 = new JLabel("S:");
			lblNewLabel_2_1_1.setBounds(226, 199, 16, 14);
			contentPanel.add(lblNewLabel_2_1_1);
			
			JLabel lblNewLabel_2_3 = new JLabel("H:");
			lblNewLabel_2_3.setBounds(226, 174, 16, 14);
			contentPanel.add(lblNewLabel_2_3);
			
			JLabel lblTransparenzAlpha = new JLabel("Transparenz - Alpha") {
				protected void paintComponent(Graphics g) {
					super.paintComponent(g);
					g.drawLine(110, getHeight()/2+2, getWidth(), getHeight()/2+2);
				}
			};
			lblTransparenzAlpha.setBounds(226, 259, 211, 14);
			contentPanel.add(lblTransparenzAlpha);
			
			
			JSpinner aspinner = new JSpinner();
			aspinner.setBounds(377, 284, 60, 20);
			contentPanel.add(aspinner);
			
			JPanel panel_2_1_1 = new JPanel() {
				protected void paintComponent(Graphics gg) {
					super.paintComponent(gg);
					
					Graphics2D g = (Graphics2D)gg;
					
					g.setPaint(new GradientPaint(new Point(0, 0), new Color(RVal, GVal, BVal, 0), new Point(getWidth(), getHeight()-10), new Color(RVal, GVal, BVal, 255)));
					
					g.fillRect(0, 0, getWidth(), getHeight()-10);
					g.drawImage(imgPointer, map(AVal, 0, 255, 0, getWidth()-7), getHeight()-10-3, 7, 7, null);
					repaint();
				}
			};
			panel_2_1_1.addMouseListener(new MouseAdapter() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					colors.add(0, new Color(RVal, GVal, BVal, AVal));
				}
				
			});
			panel_2_1_1.addMouseMotionListener(new MouseAdapter() {
					
				@Override
				public void mouseDragged(MouseEvent e) {
					if(e.getX() > panel.getWidth()-7)return;
					if(e.getX() < 0)return;
					AVal = map(e.getX(), 0, panel_2_1_1.getWidth()-7, 0, 255);
					aspinner.setValue(AVal);
					textField.setText(toHex(new Color(RVal, GVal, BVal, AVal).getRGB()));
				}
					
			});
			panel_2_1_1.setBounds(252, 287, 124, 24);
			contentPanel.add(panel_2_1_1);

			p.setHorizontalAlignment(SwingConstants.RIGHT);
			p.changeAlpha(0.5f);
//			p.changeStyle(Font.ITALIC + Font.PLAIN);

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
						li.onChoose(getRGBColor());
						isClosed = true;
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						setVisible(false);
						dispose();
						li.onChoose(getRGBColor());
						isClosed = true;
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
	}



	public void setToolTipEnabled(boolean enabled) {
		toolTipEnabled = enabled;
	}
	
	public boolean isToolTipEnabled() {
		return toolTipEnabled;
	}
	
	public float[] getHSVColor() {
		return new float[] {map(HVal, 0f, 360f, 0f, 1f), map(SVal, 0f, 100f, 0f, 1f), map(VVal, 0f, 100f, 0f, 1f)};
	}
	
	public Color getRGBColor() {
		return new Color(RVal, GVal, BVal, AVal);
	}
	
	public int getRed() {
		return RVal;
	}
	
	public int getGreen() {
		return GVal;
	}
	
	public int getBlue() {
		return BVal;
	}
	
	public int getAlpha() {
		return AVal;
	}
	
	public boolean isClosed() {
		return !isVisible() || isClosed;
	}
	
	public void setColor(Color c) {
		RVal = c.getRed();
		GVal = c.getGreen();
		BVal = c.getBlue();
		AVal = c.getAlpha();
		float[] f = new float[3];
		f = Color.RGBtoHSB(c.getRed(), c.getGreen(), c.getBlue(), f);
		HVal = (int)map(f[0], 0, 1, 0, 360);
		SVal = (int)map(f[0], 0, 1, 0, 100);
		VVal = (int)map(f[0], 0, 1, 0, 100);
	}
	
	public static void showDialog(Listener li) {
		showDialog(Color.WHITE, li);
	}
	
	public static void showDialog(Color init, Listener li) {
		showDialog(init, true, li);
	}
	
	public static void showDialog(Color initColor, boolean toolTipEnabled, Listener li) {
		ColorChooser ch = new ColorChooser(initColor, toolTipEnabled, li);
		ch.setVisible(true);
	}
	
	public interface Listener{
		public void onChoose(Color c);
	}
	
	public static String toHex(int color) {
		return Integer.toHexString(color);
	}
	
	private static int map(int x, int in_min, int in_max, int out_min, int out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
	
	private static float map(float x, float in_min, float in_max, float out_min, float out_max)
	{
	  return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;
	}
}
