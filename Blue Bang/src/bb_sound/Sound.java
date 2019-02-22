package bb_sound;

import sun.audio.*;

import java.applet.AudioClip;
import java.io.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	

	
	public void sound1(File sound){
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(sound));
			clip.start();
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
