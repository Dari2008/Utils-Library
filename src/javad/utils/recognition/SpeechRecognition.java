package javad.utils.recognition;

import java.util.ArrayList;

import com.darkprograms.speech.microphone.Microphone;
import com.darkprograms.speech.recognizer.GSpeechDuplex;
import com.darkprograms.speech.recognizer.GSpeechResponseListener;
import com.darkprograms.speech.recognizer.GoogleResponse;
import com.darkprograms.speech.recognizer.Languages;

import net.sourceforge.javaflacencoder.FLACFileWriter;

public class SpeechRecognition implements GSpeechResponseListener{


	
	private final Microphone mic;
	private GSpeechDuplex duplex;
	
	private ArrayList<SpeechRecognition.Listener> lis = new ArrayList<>();
	
	public SpeechRecognition(String apiKey) {
		mic = new Microphone(FLACFileWriter.FLAC);
		duplex = new GSpeechDuplex(apiKey);
		duplex.setLanguage(Languages.GERMAN.getLanguageCode());
		duplex.addResponseListener(this);
		
	}
	
	public final Microphone getMicrophone() {
		return mic;
	}
	
	public GSpeechDuplex getDuplex() {
		return duplex;
	}
	
	public void openMic() {
		openMic(duplex, mic);
	}
	
	public void closeMic() {
		closeMic(duplex, mic);
	}
	
	public void addResponseListener(Listener li) {
		this.lis.add(li);
	}
	
	public boolean removeResponseListener(Listener li) {
		return this.lis.remove(li);
	}
	
	public Listener removeResponseListener(int index) {
		return this.lis.remove(index);
	}
	
	@Override
	public final void onResponse(GoogleResponse gr) {
		
	}

	public interface Listener {

		public void onResponse(GoogleResponse gr);
		
	}

	private void openMic(GSpeechDuplex duplex, Microphone mic) {
		new Thread(() -> {
			try {
				duplex.recognize(mic.getTargetDataLine(), mic.getAudioFormat());
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
		}).start();
	}
	
	private void closeMic(GSpeechDuplex duplex, Microphone mic) {
		mic.close();
		duplex.stopSpeechRecognition();
	}
	
	
}
