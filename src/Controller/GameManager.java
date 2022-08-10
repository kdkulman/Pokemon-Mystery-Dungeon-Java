package Controller;

import DungeonCharacter.Hero.Hero;
import DungeonCharacter.Hero.Snorlax;
import Music.Music;
import TileObjects.TileObject;
import Views.CharacterSelectView;
import Views.GameView;

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


    private GameManager() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        createJFrame();
        createTitleScreenView();
        createCharacterSelectView();
//        selectedHero = new Snorlax(); //FOR TESTING PURPOSES
//        createGameView();
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



    private static void createTitleScreenView() {

    }

    public static void createCharacterSelectView() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
        //jFrame.remove(jPanel);
        jPanelCharacterSelectView = new CharacterSelectView();
        jFrame.add(jPanelCharacterSelectView);
        Music.playCharacterSelectViewMusic();
        jFrame.pack();
        jFrame.setLocationRelativeTo(null);
    }

    public static void createGameView() throws IOException {
        jFrame.remove(jPanelCharacterSelectView);
        jPanelGameView = new GameView(selectedHero);
        jFrame.add(jPanelGameView);
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
