package javad.utils.update;

import java.awt.BorderLayout;
import java.awt.geom.RoundRectangle2D;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JDialog;
import javax.swing.border.EmptyBorder;

import res.TMP;

public class Copy extends JDialog {

	private final ImagePanel contentPanel = new ImagePanel(TMP.getStream("/res/copy.gif"), size->{setSize(size[0], size[1]);});

	public static void main(String[] args) {
		try {
			Copy dialog = new Copy();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Copy() {
		setUndecorated(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		setShape(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 50, 50));
		
		Timer t = new Timer();
		t.schedule(new TimerTask() {
			
			@Override
			public void run() {
				contentPanel.updateFrame();
			}
		}, 100, 100);
		
	}

}
