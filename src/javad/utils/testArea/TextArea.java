package javad.utils.testArea;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

public class TextArea extends JTextArea {
	private static final long serialVersionUID = 4320261393986606591L;

	public TextArea() {
		super();
		init();
	}

	public TextArea(Document doc, String text, int rows, int columns) {
		super(doc, text, rows, columns);
		init();
	}

	public TextArea(Document doc) {
		super(doc);
		init();
	}

	public TextArea(int rows, int columns) {
		super(rows, columns);
		init();
	}

	public TextArea(String text, int rows, int columns) {
		super(text, rows, columns);
		init();
	}

	public TextArea(String text) {
		super(text);
		init();
	}

	private UndoManager manager = new UndoManager();
	
	private void init() {
		
		getDocument().addUndoableEditListener(manager);

		KeyStroke undo = KeyStroke.getKeyStroke("control Z");
		KeyStroke redo = KeyStroke.getKeyStroke("control Y");

		AbstractAction actionUndo = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
            	if(manager.canUndo()) {
            		manager.undo();
            	}else {
            		Toolkit.getDefaultToolkit().beep();
            	}
            }
        };

        AbstractAction actionRedo = new AbstractAction() {
			private static final long serialVersionUID = 1L;

			@Override
            public void actionPerformed(ActionEvent e) {
            	if(manager.canRedo()) {
            		manager.redo();
            	}else {
            		Toolkit.getDefaultToolkit().beep();
            	}
            }
        };

		ActionMap actionMap = getActionMap();
        actionMap.put("undos", actionUndo);
        actionMap.put("redos", actionRedo);
        
		
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        inputMap.put(undo, "undos");
        inputMap.put(redo, "redos");
        
	}

}
