package Controller;

import DungeonCharacter.Hero.Hero;
import Music.Music;
import Views.CharacterSelectView;
import Views.GameView;
import Views.TitleScreenView;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static Views.View.SCREEN_HEIGHT;
import static Views.View.SCREEN_WIDTH;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class GameManager {
    public static JFrame myJFrame;
    private static GameManager myInstance;
    private static Hero mySelectedHero;
    private static JPanel myJPanelCharacterSelectView;
    private static JPanel myJPanelGameView;
    private static JPanel myJPanelTitleScreenView;

    /**
     * Constructor for objects of class GameManager
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    private GameManager() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        createJFrame();
        createTitleScreenView();
    }

    /**
     * Singleton method to get the instance of the GameManager
     * @return the instance of the GameManager
     *
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static GameManager getMyInstance() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (myInstance == null){
            myInstance = new GameManager();
        }
        return myInstance;
    }

    /**
     * Selected hero setter
     * @param theHero the selected hero
     */
    public static void setMySelectedHero(final Hero theHero){
        mySelectedHero = theHero;
    }

    /**
     * Method that creates title screen view
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     * @throws IOException
     */
    private static void createTitleScreenView() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        myJPanelTitleScreenView = new TitleScreenView();
        myJFrame.add(myJPanelTitleScreenView);
        Music.playMusic(Music.Song.TITLE_SCREEN);
        myJFrame.pack();
        myJFrame.setLocationRelativeTo(null);
    }

    /**
     * Method that creates character select view
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static void createCharacterSelectView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        myJFrame.remove(myJPanelTitleScreenView);
        myJPanelCharacterSelectView = new CharacterSelectView();
        myJFrame.add(myJPanelCharacterSelectView);
        Music.playMusic(Music.Song.CHARACTER_SELECT_QUIZ);
        myJFrame.pack();
        myJFrame.setLocationRelativeTo(null);
    }

    /**
     * Method that creates game view
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static void createGameView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (myJPanelCharacterSelectView == null) {
            myJFrame.remove(myJPanelTitleScreenView);
        } else {
            myJFrame.remove(myJPanelCharacterSelectView);
        }
        myJPanelGameView = new GameView(mySelectedHero);
        myJFrame.add(myJPanelGameView);
        Music.playMusic(Music.Song.GAMEPLAY);
        myJFrame.pack();
    }

    /**
     * Method that loads saved game
     * @param theState the state of the game
     * @throws IOException
     * @throws UnsupportedAudioFileException
     * @throws LineUnavailableException
     */
    public static void loadGame(final SaveState theState) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //If this is needed to load a game, just call GameManager.loadGame()
        //from he TitleScreenView loadButton ActionListener
        myJFrame.remove(myJPanelTitleScreenView);
        mySelectedHero = theState.hero;
        myJPanelGameView = new GameView(theState.hero, theState.floor);
        myJFrame.add(myJPanelGameView);
        Music.playMusic(Music.Song.GAMEPLAY);
        myJFrame.pack();
    }

    /**
     * Method that creates the JFrame
     */
    private static void createJFrame(){
        myJFrame = new JFrame();
        myJFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myJFrame.setResizable(false);
        myJFrame.setTitle("Pokemon Mystery Dungeon Java");
        myJFrame.setBackground(Color.BLACK);
        myJFrame.isDoubleBuffered();
        myJFrame.setVisible(true);
        myJFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));}
}