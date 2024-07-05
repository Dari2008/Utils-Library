package javad.utils.console.listeners.event;

import java.io.PrintStream;
import java.io.PrintWriter;

import javad.utils.methods.Getter;
import javad.utils.methods.Setter;

public class SystemOutputEvent {

	private Getter<Boolean> isCanceled;
	private Getter<Object> getOutput;
	private Getter<PrintStream> getSource;
	private Setter<PrintStream> setSource;
	private Setter<Object> setOutput;
	private Setter<Boolean> setCanceled;
	
	public SystemOutputEvent(final Getter<Object> getOutput, 
			final Getter<PrintStream> getter, 
			final Getter<Boolean> isCanceled, 
			final Setter<Object> setOutput, 
			final Setter<PrintStream> setter, 
			final Setter<Boolean> setCanceled) {
		this.setOutput = setOutput;
		this.setSource = setter;
		this.isCanceled = isCanceled;
		this.getOutput = getOutput;
		this.getSource = getter;
		this.setCanceled = setCanceled;
	}


	public boolean isCanceled() {
		return isCanceled.get();
	}

	public void setCanceled(boolean isCanceled) {
		setCanceled.set(isCanceled);
	}

	public Object getOutput() {
		return getOutput.get();
	}

	public void setOutput(Object output) {
		setOutput.set(output);
	}

	public PrintStream getSource() {
		return getSource.get();
	}

	public void setSource(PrintStream source) {
		setSource.set(source);
	}

	
	
}
