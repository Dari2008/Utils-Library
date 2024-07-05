package javad.utils.tabbedPane;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DragSource;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.dnd.DropTargetEvent;
import java.awt.dnd.DropTargetListener;
import java.io.IOException;
import java.util.TooManyListenersException;

import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JTabbedPane;
import javax.swing.TransferHandler;

public class DraggableTabbedPane extends JTabbedPane{

	public DraggableTabbedPane() {
		super();
		init();
	}

	public DraggableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
		init();
	}

	public DraggableTabbedPane(int tabPlacement) {
		super(tabPlacement);
		init();
	}

	private void init() {
//		setDropTarget(this);
	}
}
