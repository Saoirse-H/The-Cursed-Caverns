import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/*
 * Taken from:
 * https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-multiple-sounds-in-a-game
 */
public class Sound {
	private Clip clip;
	
	public Sound(String fileName) {
		try {
			File file = new File(fileName);
	        if (file.exists()) {
	            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
	            // load the sound into memory (a Clip)
	            clip = AudioSystem.getClip();
	            clip.open(sound);
	        }
	        else {
	            throw new RuntimeException("Sound: file not found: " + fileName);
	        }
	    } catch (Exception e) { e.printStackTrace(); }
	}
	
	public void play() {
	    clip.setFramePosition(0);  // Must always rewind!
	    clip.start();
	}
	
	public void loop() {
	    clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
        clip.stop();
    }
}
