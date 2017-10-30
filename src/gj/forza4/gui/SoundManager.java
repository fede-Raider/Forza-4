package gj.forza4.gui;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

public class SoundManager {

	AudioInputStream stream;
	AudioFormat format;
	DataLine.Info info;
	Clip clip;

	public void play(String suono, boolean loop) {
		try {
			File yourFile = new File(suono);
			stream = AudioSystem.getAudioInputStream(yourFile);
			format = stream.getFormat();
			info = new DataLine.Info(Clip.class, format);
			clip = (Clip) AudioSystem.getLine(info);
			clip.open(stream);
			if (loop) {
				clip.loop(Clip.LOOP_CONTINUOUSLY);
			}
			clip.start();
		} catch (Exception e) {
			System.out.println("Errore sonoro!");
		}
	}
}
