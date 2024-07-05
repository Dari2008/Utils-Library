package javad.utils.audio;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import javad.utils.math.Util;

public class Audio implements Closeable{
	
	private Clip clip = null;
	private AudioInputStream in = null;
	private Timer t = null;
	private boolean isRunning = false, isRes = false;
	private int speed = 6;
	private File f;
	private int loop = 1;
	
	public Audio(File wav) throws UnsupportedAudioFileException{
		f = wav;
		try {
			in = AudioSystem.getAudioInputStream(wav);
			clip = AudioSystem.getClip();
			clip.open(in);
			t = new Timer();
			t.schedule(new TimerTask() {
				
				@Override
				public void run() {
					
					if(isRunning) {
						clip.start();
						clip.loop(loop);
					}else {
						clip.stop();
						loop = 1;
					}
					
					if(isRes) {
						clip.setMicrosecondPosition(0);
						isRes = false;
					}
					
					if(clip.getFramePosition() > clip.getFrameLength()) {
						isRunning = false;
					}
					
				}
			}, speed, speed);
			
		} catch (IOException | LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getVolume() {
	    FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);        
	    return (int)Util.map((float) Math.pow(10f, gainControl.getValue() / 20f), 0f, 1f, 0f, 100f);
	}

	public void setVolume(int procent) {
	    if (procent < 0 || procent > 100)
	        throw new IllegalArgumentException("Volume not valid: " + procent);
	    
	    FloatControl control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        double gain = Util.map(procent, 0f, 100f, 0f, 1f);   
        float dB = (float) (Math.log(gain) / Math.log(10.0) * 20.0);
        control.setValue(dB);
	}
	
	public File getFile() {
		return f;
	}
	
	public void setFile(File f) {
		try {
			if(in != null)in.close();
			if(clip != null)clip.close();
			this.f = f;
			clip = AudioSystem.getClip();
			in = AudioSystem.getAudioInputStream(f);
			clip.open(in);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public AudioInputStream getAudioInputStream() {
		return in;
	}
	
	public double getMaxDurationInSeconds() {
		if(in == null)return 0;
		long frames = in.getFrameLength();
		return ((frames+0.0) / in.getFormat().getFrameRate()); 
	}
	
	public double getMaxDurationInMillis() {
		if(in == null)return 0;
		long frames = in.getFrameLength();
		return ((frames+0.0) / in.getFormat().getFrameRate())*1000; 
	}
	
	public int getSeconds() {
		return (int) (getMaxDurationInSeconds()%60);
	}
	
	public int getMinutes() {
		return (int) ((getMaxDurationInSeconds()/60)%60);
	}	
	
	public int getHours() {
		return (int) ((getMaxDurationInSeconds()/60)/60);
	}
	
	public Clip getClip() {
		return clip;
	}
	
	public void start() {
		isRunning = true;
	}
	
	
	public void stop() {
		isRunning = false;
	}
	public void reset() {
		isRes = true;
	}

	public void setPosition(long posMilis) {
		clip.setMicrosecondPosition(posMilis);
	}
	
	public boolean isPlaying() {
		return isRunning;
	}
	
	public static long toSeconds(long seconds) {
		return seconds%60;
	}
	
	public static long toMinutes(long seconds) {
		return seconds/60%60;
	}
	
	public static long toHours(long seconds) {
		return seconds/60/60;
	}
	
	public static long secondsToMicroseconds(long sec) {
		return sec*1000000;
	}
	
	public static long fromMicrosecondsToSeconds(long micro) {
		return micro/1000000;
	}

	public long currentPos() {
		return clip.getMicrosecondPosition();
	}
	
	
	@Override
	public void close(){
		
		if(t != null)t.cancel();
		if(clip != null)clip.close();
		try {
			if(in != null)in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void loop(int loop) {
		this.loop = loop;
	}
}
