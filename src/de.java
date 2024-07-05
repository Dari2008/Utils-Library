import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javad.utils.colorChooser.TextPrompt;
import javad.utils.gui.GuiUtils;
import javad.utils.gui.SwitchPanel;
import javad.utils.style.Style;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.JScrollPane;

public class de extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					de frame = new de();
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
	public de() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 807, 526);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		Style.setSystemLookAndFeel();
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 296, 59);
//		contentPane.add(scrollPane);
		
		JList<String> list = new JList<>();
		scrollPane.setViewportView(list);
		list.setModel(new DefaultListModel<>());
		((DefaultListModel<String>)list.getModel()).addElement("Test");
		((DefaultListModel<String>)list.getModel()).addElement("Tesedfst");
		((DefaultListModel<String>)list.getModel()).addElement("Terest");
		((DefaultListModel<String>)list.getModel()).addElement("Tesdfqwerst");
		((DefaultListModel<String>)list.getModel()).addElement("Tsdfest");
		
		JButton btnNewButton = new JButton("Ok");
//		btnNewButton.add
		btnNewButton.setActionCommand("Ok");
		btnNewButton.setBounds(207, 70, 89, 23);
//		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuiUtils.alertOwn(de.this, 250, btnNewButton.getWidth() + btnNewButton.getX(), btnNewButton.getY() + btnNewButton.getHeight(), new Object[] {scrollPane, btnNewButton}, s->{
					System.out.println(list.getSelectedValue());
					return list.getSelectedValue()!=null;
				});

			}
		});
		btnNewButton_1.setBounds(173, 167, 89, 23);
		contentPane.add(btnNewButton_1);
		

		
		
	}
}
