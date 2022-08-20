package Music;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
public class Music {
    private static Clip myClip;

    private Music(){
        
    }

    public enum Song {
        TITLE_SCREEN,
        CHARACTER_SELECT_QUIZ,
        GAMEPLAY
    }
    
    public static void playMusic(final Song theSong) throws UnsupportedAudioFileException,
            IOException, LineUnavailableException
    {
        AudioInputStream audioInputStream = null;
        switch (theSong){
            case TITLE_SCREEN ->  {
                audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(
                        "/Music/Title_Screen_Music.wav"));
                break;
            }
            case CHARACTER_SELECT_QUIZ -> {
                audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(
                        "/Music/Character_Select_Music.wav"));
                break;
            }
            case GAMEPLAY -> {
                audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(
                        "/Music/Gameplay_Music.wav"));
                break;
            }
            default -> {
                audioInputStream = AudioSystem.getAudioInputStream(Music.class.getResourceAsStream(
                        "/Music/Character_Select_Music.wav"));
                break;
            }

        }
        if (myClip != null) myClip.stop();
        myClip = AudioSystem.getClip();
        myClip.open(audioInputStream);
        myClip.loop(Clip.LOOP_CONTINUOUSLY);
    }
}
