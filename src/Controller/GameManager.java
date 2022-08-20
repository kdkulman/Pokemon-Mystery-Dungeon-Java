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

public class GameManager {
    public static JFrame myJFrame;
    private static GameManager myInstance;
    private static Hero mySelectedHero;
    private static JPanel myJPanelCharacterSelectView;
    private static JPanel myJPanelGameView;
    private static JPanel myJPanelTitleScreenView;

    private GameManager() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        createJFrame();
        createTitleScreenView();
    }

    public static GameManager getMyInstance() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (myInstance == null){
            myInstance = new GameManager();
        }
        return myInstance;
    }

    public static void setMySelectedHero(final Hero theHero){
        mySelectedHero = theHero;
    }


    private static void createTitleScreenView() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        myJPanelTitleScreenView = new TitleScreenView();
        myJFrame.add(myJPanelTitleScreenView);
        Music.playMusic(Music.Song.TITLE_SCREEN);
        myJFrame.pack();
        myJFrame.setLocationRelativeTo(null);
    }

    public static void createCharacterSelectView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        myJFrame.remove(myJPanelTitleScreenView);
        myJPanelCharacterSelectView = new CharacterSelectView();
        myJFrame.add(myJPanelCharacterSelectView);
        Music.playMusic(Music.Song.CHARACTER_SELECT_QUIZ);
        myJFrame.pack();
        myJFrame.setLocationRelativeTo(null);
    }

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