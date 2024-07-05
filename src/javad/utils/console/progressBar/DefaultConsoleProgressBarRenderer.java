package javad.utils.console.progressBar;

import javad.utils.console.progressBar.ConsoleProgressBar.Position;
import javad.utils.math.Util;

public class DefaultConsoleProgressBarRenderer implements ConsoleProgressBarRenderer{
	
	@Override
	public String render(char progressSymbol, char topSymbol, char bottomSymbol, Position pos, String label, int value, ConsoleProgressBarModel model,
			ConsoleProgressBar progressBar, boolean isTripple, boolean jumpOverLabel, boolean showLabel, int row) {

		int max = model.getMax();
		int min = model.getMin();
		int mappedValue = Util.map(value, min, max, 0, 100);

		if(isTripple) {
			if(pos == Position.CENTER) {
				if((row == 0 || row == 2)) {
					if(jumpOverLabel) {
						String result = "";
						if(mappedValue > max/2 && (showLabel && label != null)) {
							if(row == 0) {
								for(int i = 0; i < mappedValue+label.length(); i++) {
									result += topSymbol;
								}
							}else {
								for(int i = 0; i < mappedValue+label.length(); i++) {
									result += bottomSymbol;
								}
							}
						}else {
							if(row == 0) {
								for(int i = 0; i < mappedValue; i++) {
									result += topSymbol;
								}
							}else {
								for(int i = 0; i < mappedValue; i++) {
									result += bottomSymbol;
								}
							}
						}
						return result;
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							if(row == 0)result += topSymbol;
							if(row == 2)result += bottomSymbol;
						}
						return result;
					}
				}else {
					if((showLabel && label != null)) {
						if(max/2 < mappedValue) {
							if(jumpOverLabel) {
								String result = "";
								int rest = mappedValue - (max/2);
								for(int i = 0; i < max/2; i++) {
									result += progressSymbol;
								}
								result += label;
								for(int i = 0; i < rest; i++) {
									result += progressSymbol;
								}
								return result;
							}else {
								String result = "";
								int rest = mappedValue - (max/2) - label.length()/2;
								for(int i = 0; i < max/2 - label.length()/2; i++) {
									result += progressSymbol;
								}
								result += label;
								if(rest <= 0)return result;
								for(int i = 0; i < rest; i++) {
									result += progressSymbol;
								}
								return result;
							}
						}else {
							String result = "";
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							int rest = max/2 - mappedValue;
							for(int i = 0; i < rest; i++) {
								result += " ";
							}
							result += label;
							return result;
						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
				}
			}else if(pos == Position.LEFT) {
				if((row == 0 || row == 2)) {
					if(jumpOverLabel) {
						String result = " ".repeat(label!=null?label.length():0);
						for(int i = 0; i < mappedValue; i++) {
							if(row == 0)result += topSymbol;
							if(row == 2)result += bottomSymbol;
						}
						return result;
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							if(row == 0)result += topSymbol;
							if(row == 2)result += bottomSymbol;
						}
						return result;
					}
				}else {
					if((showLabel && label != null)) {
						if(jumpOverLabel) {
							String result = label;
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							return result;
						}else {
							String result = label;
							if((mappedValue - label.length()) <= 0)return result;
							for(int i = 0; i < mappedValue - label.length(); i++) {
								result += progressSymbol;
							}
							return result;
						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
				}
			}else if(pos == Position.RIGHT) {
				if((row == 0 || row == 2)) {
					String result = "";
					for(int i = 0; i < mappedValue; i++) {
						if(row == 0)result += topSymbol;
						if(row == 2)result += bottomSymbol;
					}
					return result;
				}else {
					if((showLabel && label != null)) {
						if(jumpOverLabel) {
							String result = "";
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							
							for(int i = 0; i < max-mappedValue; i++) {
								result += " ";
							}
							
							result += label;
							return result;
						}else {
							if(mappedValue > max-label.length()) {
								String result = "";
								for(int i = 0; i < max-label.length(); i++) {
									result += progressSymbol;
								}
								result += label;
								return result;
							}else {
								String result = "";
								for(int i = 0; i < mappedValue; i++) {
									result += progressSymbol;
								}
								
								for(int i = 0; i < max-mappedValue-label.length(); i++) {
									result += " ";
								}
								
								result += label;
								return result;
							}

						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
				}
			}
		}else {
			
			//////////////////////////////////////////////////////////////////
			
			
			
			if(pos == Position.CENTER) {
					if((showLabel && label != null)) {
						if(max/2 < mappedValue) {
							if(jumpOverLabel) {
								String result = "";
								int rest = mappedValue - (max/2);
								for(int i = 0; i < max/2; i++) {
									result += progressSymbol;
								}
								result += label;
								for(int i = 0; i < rest; i++) {
									result += progressSymbol;
								}
								return result;
							}else {
								String result = "";
								int rest = mappedValue - (max/2) - label.length()/2;
								for(int i = 0; i < max/2 - label.length()/2; i++) {
									result += progressSymbol;
								}
								result += label;
								if(rest <= 0)return result;
								for(int i = 0; i < rest; i++) {
									result += progressSymbol;
								}
								return result;
							}
						}else {
							String result = "";
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							int rest = max/2 - mappedValue;
							for(int i = 0; i < rest; i++) {
								result += " ";
							}
							result += label;
							return result;
						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
			}else if(pos == Position.LEFT) {
					if((showLabel && label != null)) {
						if(jumpOverLabel) {
							String result = label;
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							return result;
						}else {
							String result = label;
							if((mappedValue - label.length()) <= 0)return result;
							for(int i = 0; i < mappedValue - label.length(); i++) {
								result += progressSymbol;
							}
							return result;
						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
			}else if(pos == Position.RIGHT) {
					if((showLabel && label != null)) {
						if(jumpOverLabel) {
							String result = "";
							for(int i = 0; i < mappedValue; i++) {
								result += progressSymbol;
							}
							
							for(int i = 0; i < max-mappedValue; i++) {
								result += " ";
							}
							
							result += label;
							return result;
						}else {
							if(mappedValue > max-label.length()) {
								String result = "";
								for(int i = 0; i < max-label.length(); i++) {
									result += progressSymbol;
								}
								result += label;
								return result;
							}else {
								String result = "";
								for(int i = 0; i < mappedValue; i++) {
									result += progressSymbol;
								}
								
								for(int i = 0; i < max-mappedValue-label.length(); i++) {
									result += " ";
								}
								
								result += label;
								return result;
							}

						}
					}else {
						String result = "";
						for(int i = 0; i < mappedValue; i++) {
							result += progressSymbol;
						}
						return result;
					}
			}
		}
		
		
		return null;
	}
}
