package javad.utils.console.progressBar;

public class ConsoleProgressBarModel {
	
	private int max, min, inc;
	
	public ConsoleProgressBarModel(int min, int max, int inc) {
		this.max = max;
		this.min = min;
		this.inc = inc;
	}

	public int getMax() {
		return max;
	}

	public void setMax(int max) {
		this.max = max;
	}

	public int getMin() {
		return min;
	}

	public void setMin(int min) {
		this.min = min;
	}

	public int getInc() {
		return inc;
	}

	public void setInc(int inc) {
		this.inc = inc;
	}
	
	
	
}
