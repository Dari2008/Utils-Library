package javad.utils.console.progressBar;

import javax.resource.spi.IllegalStateException;

import org.jline.terminal.TerminalBuilder;
import org.jline.terminal.TerminalBuilder.SystemOutput;

import javad.utils.console.Console;
import javad.utils.console.CustomOutputStream;
import javad.utils.math.Util;

public class ConsoleProgressBar {

	private ConsoleProgressBarModel model = new ConsoleProgressBarModel(0, 100, 1);

	private int value = model.getMin();
	private boolean visible = true;
	private long row = -1;
	private char symbol = '\u25A0'; // quadrad
	private char bottomSymbol = (char)183; // upper dot
	private char topSymbol = '.'; // punkt
	private boolean jumpOverLabel = false;
	private ConsoleProgressBarRenderer renderer = new DefaultConsoleProgressBarRenderer();
	private String label = null;
	private boolean showLabel = false;
	private final Position labelPos;
	private final boolean showTrippleLine;

	
	public ConsoleProgressBar() {
		this.labelPos = Position.CENTER;
		showTrippleLine = true;
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	}

	
	public ConsoleProgressBar(boolean showTrippleLine) {
		this.labelPos = Position.CENTER;
		this.showTrippleLine = showTrippleLine;
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	}


	public ConsoleProgressBar(Position labelPos, boolean showTrippleLine) {
		this.labelPos = labelPos;
		this.showTrippleLine = showTrippleLine;
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	}
	
	public ConsoleProgressBar(Position labelPos, boolean showTrippleLine, int min, int max) {
		this.labelPos = labelPos;
		this.showTrippleLine = showTrippleLine;
		model.setMin(min);
		model.setMax(max);
		value = min;
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	
	}
	
	public ConsoleProgressBar(Position labelPos, boolean showTrippleLine, int min, int max, int inc) {
		this.labelPos = labelPos;
		this.showTrippleLine = showTrippleLine;
		model.setInc(inc);
		model.setMin(min);
		model.setMax(max);
		value = min;
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	
	}
	
	public ConsoleProgressBar(Position labelPos, boolean showTrippleLine, ConsoleProgressBarModel model) {
		this.labelPos = labelPos;
		this.showTrippleLine = showTrippleLine;
		this.model = model;
		value = model.getMin();
		try {
			row = Console.getRows();
		} catch (IllegalStateException e) {
			Console.setOutAndErrStream();
			try {
				row = Console.getRows();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			}
		}
		init();
		update();
	}
	
	private void init() {
		if(showTrippleLine) {
			System.out.println();
			System.out.println();
			System.out.println();
		}else {
			System.out.println();
		}
	}
	
	public void setJumOverLabel(boolean jump) {
		jumpOverLabel = jump;
	}
	
	public boolean isJumpOverLabel() {
		return jumpOverLabel;
	}
	
	public void setProgressChar(char symbol) {
		this.symbol = symbol;
		update();
	}
	
	public void setRenderer(ConsoleProgressBarRenderer renderer) {
		this.renderer = renderer;
		update();
	}
	
	public char getProgressChar() {
		return symbol;
	}
	
	public ConsoleProgressBarRenderer getRenderer() {
		return renderer;
	}
	
	public void increase() {
		if(value + model.getInc() > model.getMax())return;
		value += model.getInc();
		update();
	}
	
	public void setModel(ConsoleProgressBarModel model) {
		this.model = model;
		update();
	}
	
	public ConsoleProgressBarModel getModel() {
		return model;
	}
	
	public void setVisible(boolean is) {
		visible = is;
		update();
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public void setValue(int val) {
		value = val;
		update();
	}
	
	public int getValue() {
		return value;
	}
	
	public void setLabel(String label) {
		this.label = label;
		update();
	}
	
	public void setLabelVisible(boolean showLabel) {
		this.showLabel = showLabel;
		update();
	}
	
	public String getlabel() {
		return label;
	}
	
	public boolean isLabelVisible() {
		return showLabel;
	}
	
	public void update() {
		if(showTrippleLine) {
			try {
				Console.moveCurserUp((int) (Console.getRows() - row));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			for(int i = 0; i < 3; i++) {
//				System.out.println(i);
				((CustomOutputStream)System.out).printlnOnly(renderer.render(symbol, topSymbol, bottomSymbol, labelPos, label, value, model,
						this, showTrippleLine, jumpOverLabel, showLabel, i));
			}
			try {
				Console.moveCurserDown((int) (Console.getRows() - row+3));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}else {
			try {
				Console.moveCurserUp((int) (Console.getRows() - row));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
			((CustomOutputStream)System.out).printlnOnly(renderer.render(symbol, topSymbol, bottomSymbol, labelPos, label, value, model,
					this, showTrippleLine, jumpOverLabel, showLabel, 0));
			try {
				Console.moveCurserDown((int) (Console.getRows() - row+3));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public enum Position{
		LEFT, CENTER, RIGHT;
	}

	public long getRow() {
		return row;
	}
	
}
