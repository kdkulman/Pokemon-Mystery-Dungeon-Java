import Controller.GameManager;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class Main {


    public static void main(String[] args) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        GameManager gameManager = GameManager.getMyInstance();
    }
}