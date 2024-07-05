package javad.utils.ki;

import java.io.IOException;

import com.darkprograms.speech.recognizer.Languages;
import com.darkprograms.speech.synthesiser.SynthesiserV2;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.advanced.AdvancedPlayer;

public class Speech {
	public static String API_KEY = "AIzaSyBOti4mM-6x9WDnZIjIeyEU21OpBXqWBgw";

	private SynthesiserV2 synthesizer;
	
	public Speech(String apikey) {
		
		synthesizer = new SynthesiserV2(apikey);
		
	}
	
	
	
	public SynthesiserV2 getSynthesiser() {
		return synthesizer;
	}
	
	public void speak(String text) {
		Thread thread = new Thread(() -> {
			try {
				
				//Create a JLayer instance
				AdvancedPlayer player = new AdvancedPlayer(synthesizer.getMP3Data(text));
				player.play();
				
				
			} catch (IOException | JavaLayerException e) {
				e.printStackTrace();
			}
		});
		
		thread.setDaemon(false);
		thread.start();
	}
	
	
	

}
