import java.io.File;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

import javad.utils.console.CustomOutputStream;

public class EqualizerExample {
    public static void main(String[] args) {
        try {
        	System.out.println();
        	System.out.println();
        	System.out.println();
        	
        	if(args.length != 2) {
        		return;
        	}

        	int heigh = Integer.valueOf(args[0]);
        	int low = Integer.valueOf(args[1]);
        	
        	// ÷ffnen Sie den Audiokanal

            // ÷ffnen Sie die WAV-Audiodatei
            File audioFile = new File("C:\\Users\\Darius\\Downloads\\Peter-Fox-EIN-AUGE-BLAU-_prod.-Ghanaian-Stallion_-P.Fox-_-Krauts_-_152kbit_Opus_.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audioFile);

            // Holen Sie sich das Audioformat der Datei
            AudioFormat format = audioInputStream.getFormat();
            System.out.println(format.getSampleSizeInBits());
        	
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
            line.open(format);
            line.start();

            // Erzeugen Sie den Equalizer (Beispiel: Anhebung der B‰sse)
            Equalizer equalizer = new Equalizer();
            equalizer.setLowGain(200); // Anhebung der B‰sse um 6 dB

            // Schleife zum Lesen und Verarbeiten des Audiostreams
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = audioInputStream.read(buffer)) != -1) {
                // Anwenden der Equalizer-Einstellungen auf den Audiostream
//                equalizer.process(buffer, bytesRead);
            	
            	double[] v = getHighsMiddlesLows(buffer, heigh, low);

            	CustomOutputStream.setCursorToLineBefore();
            	CustomOutputStream.setCursorToLineBefore();
            	CustomOutputStream.setCursorToLineBefore();
            	
            	System.out.println("Highs: " + getStringFor(v[0]));
            	System.out.println("Middles: " + getStringFor(v[1]));
            	System.out.println("Lows: " + getStringFor(v[2]));
            	
                // Wiedergeben des verarbeiteten Audiostreams
                line.write(buffer, 0, bytesRead);
            }

            // Schlieﬂen des Audiokanals
            line.drain();
            line.stop();
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getStringFor(double d) {
    	int i = (int)d;
    	String res = "";
    	for(int ii = 0; ii < i; ii++) {
    		res += "#";
    	}
    	return res;
    }

public static double[] getHighsMiddlesLows(byte[] buff, int high, int low) {

    // Anzahl der Hˆhen, Mitten und Tiefen z‰hlen
    int highCount = 0;
    int midCount = 0;
    int lowCount = 0;
    int totalCount = buff.length / 2; // Annahme: 16-Bit-Signed-Integer-Sample-Werte

    for (int i = 0; i < buff.length - 1; i += 2) {
        int sample = (buff[i + 1] << 8) | (buff[i] & 0xFF);
        // Hier kˆnnen Sie Ihre Kriterien f¸r Hˆhen, Mitten und Tiefen definieren
        if (sample > high) {
            highCount++;
        } else if (sample < low) {
            lowCount++;
        } else {
            midCount++;
        }
    }

    // Berechnen Sie den Prozentsatz von Hˆhen, Mitten und Tiefen
    double highPercentage = (double) highCount / totalCount * 100;
    double midPercentage = (double) midCount / totalCount * 100;
    double lowPercentage = (double) lowCount / totalCount * 100;

    return new double[] {highPercentage, midPercentage, lowPercentage};
}
}


class Equalizer {
    private double lowGain;

    public Equalizer() {
        // Setzen Sie die Anfangswerte f¸r die Equalizer-Einstellungen
        lowGain = 0;
    }

    public void setLowGain(double gain) {
        lowGain = gain;
    }

    public void process(byte[] buffer, int bytesRead) {
    	for(byte b : buffer) {
//    		if(b < -100) {
//        		System.out.println(b);
//    		}
    	}
    }

}
