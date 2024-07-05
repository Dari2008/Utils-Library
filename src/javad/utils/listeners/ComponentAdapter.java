package javad.utils.listeners;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public interface ComponentAdapter extends ComponentListener {

	@Override
	default void componentHidden(ComponentEvent e) {}
	
	@Override
	default void componentMoved(ComponentEvent e) {}
	
	@Override
	default void componentResized(ComponentEvent e) {}
	
	@Override
	default void componentShown(ComponentEvent e) {}
	
}
