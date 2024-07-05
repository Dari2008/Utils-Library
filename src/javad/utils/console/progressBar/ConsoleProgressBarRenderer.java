package javad.utils.console.progressBar;

import javad.utils.console.progressBar.ConsoleProgressBar.Position;

public interface ConsoleProgressBarRenderer {
	public abstract String render(char progressSymbol, char topSymbol, char bottomSymbol, Position pos, String label, int value, ConsoleProgressBarModel model,
			ConsoleProgressBar progressBar, boolean isTripple, boolean jumpOverLabel, boolean showLabel, int row);
}
