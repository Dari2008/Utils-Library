package javad.utils.scrollPane;

import java.awt.Component;
import java.awt.ScrollPane;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JScrollPane;

import javad.utils.listeners.MouseAdapter;
import javad.utils.math.Util;

public class DraggableScrollPane extends JScrollPane implements MouseAdapter, MouseMotionListener{

	private boolean draggable = true;
	
	public DraggableScrollPane() {
		super();
	}

	public DraggableScrollPane(Component view, int vsbPolicy, int hsbPolicy) {
		super(view, vsbPolicy, hsbPolicy);
	}

	public DraggableScrollPane(Component view) {
		super(view);
	}

	public DraggableScrollPane(int vsbPolicy, int hsbPolicy) {
		super(vsbPolicy, hsbPolicy);
	}
	
	@Override
	public void setViewportView(Component view) {
		if(getViewport() != null) {
			if(getViewport().getView() != null) {
				getViewport().getView().removeMouseListener(this);
				getViewport().getView().removeMouseMotionListener(this);
			}
		}
		super.setViewportView(view);
		if(view != null) {
			view.addMouseListener(this);
			view.addMouseMotionListener(this);
		}
	}

	public void setDragEnabled(boolean b) {
		draggable = b;
	}
	
	public boolean isDragEnabled() {
		return draggable;
	}


//	int dragXStart = -1;
//	int dragYStart = -1;
//	float valueHorizontal = getHorizontalScrollBar().getValue();
//	float valueVertical = getVerticalScrollBar().getValue();

    private float mouseX, mouseY;
    private float scrollBarX, scrollBarY;
	
	@Override
	public void mouseDragged(MouseEvent e) {
        float deltaX = e.getX() - mouseX;
        float deltaY = e.getY() - mouseY;

        scrollBarX = scrollBarX - deltaX;
        scrollBarY = scrollBarY - deltaY;

        if(scrollBarX < getHorizontalScrollBar().getMinimum()) {
        	scrollBarX = getHorizontalScrollBar().getMinimum();
        }

        if(scrollBarY < getVerticalScrollBar().getMinimum()) {
        	scrollBarY = getVerticalScrollBar().getMinimum();
        }
        
        if(scrollBarY > getVerticalScrollBar().getMaximum()) {
        	scrollBarY = getVerticalScrollBar().getMaximum();
        }
        
        if(scrollBarX > getHorizontalScrollBar().getMaximum()) {
        	scrollBarX = getHorizontalScrollBar().getMaximum();
        }
        if(draggable) {
            if(getHorizontalScrollBarPolicy() != HORIZONTAL_SCROLLBAR_NEVER)getHorizontalScrollBar().setValue(Math.round(scrollBarX));
            if(getVerticalScrollBarPolicy() != VERTICAL_SCROLLBAR_NEVER)getVerticalScrollBar().setValue(Math.round(scrollBarY));
        }
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
        mouseX = e.getX();
        mouseY = e.getY();
        scrollBarX = getHorizontalScrollBar().getValue();
        scrollBarY = getVerticalScrollBar().getValue();
	}
	
	@Override
	public void mouseReleased(MouseEvent e) {
	}
	
}
