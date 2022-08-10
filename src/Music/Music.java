package Music;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Music {

    public Music(){
        
    }
    
    public static void playMusic() throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        // create AudioInputStream object
        AudioInputStream audioInputStream =
                AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(
                        "/Music/Character_Select_Music.wav"));

        // create clip reference
        Clip clip = AudioSystem.getClip();

        // open audioInputStream to the clip
        clip.open(audioInputStream);

        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void playCharacterSelectViewMusic() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        playMusic();
    }
}
