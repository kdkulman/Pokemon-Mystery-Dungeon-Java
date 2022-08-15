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
    public static JFrame jFrame;
    private static GameManager instance;
    private static Hero selectedHero;
    private static JPanel jPanelCharacterSelectView;
    private static JPanel jPanelGameView;
    private static JPanel jPanelTitleScreenView;

    private GameManager() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        createJFrame();
        createTitleScreenView();
    }

    public static GameManager getInstance() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        if (instance == null){
            instance = new GameManager();
        }
        return instance;
    }

    public static void setSelectedHero(final Hero hero){
        selectedHero = hero;
    }



    private static void createTitleScreenView() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        jPanelTitleScreenView = new TitleScreenView();
        jFrame.add(jPanelTitleScreenView);
        Music.playMusic(Music.Song.TITLE_SCREEN);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }

    public static void createCharacterSelectView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        jFrame.remove(jPanelTitleScreenView);
        jPanelCharacterSelectView = new CharacterSelectView();
        jFrame.add(jPanelCharacterSelectView);
        Music.playMusic(Music.Song.CHARACTER_SELECT_QUIZ);
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }

    public static void createGameView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        jFrame.remove(jPanelTitleScreenView);
        jPanelGameView = new GameView(selectedHero);
        jFrame.add(jPanelGameView);
        Music.playMusic(Music.Song.GAMEPLAY);
        jFrame.pack();
    }

    public static void loadGame(SaveState state) throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //If this is needed to load a game, just call GameManager.loadGame()
        //from he TitleScreenView loadButton ActionListener
        jFrame.remove(jPanelTitleScreenView);
        selectedHero = state.hero;
        jPanelGameView = new GameView(state.hero, state.floor);
        jFrame.add(jPanelGameView);
        Music.playMusic(Music.Song.GAMEPLAY);
        jFrame.pack();
    }

    private static void createJFrame(){
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("Pokemon Mystery Dungeon Java");
        jFrame.setBackground(Color.BLACK);
        jFrame.isDoubleBuffered();
        jFrame.setVisible(true);
        jFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));}
}
