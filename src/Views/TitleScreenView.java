package Views;

import Controller.GameManager;
import Controller.SaveManager;
import Controller.SaveState;
import DungeonCharacter.Hero.Gallade;
import DungeonCharacter.Hero.Jirachi;
import DungeonCharacter.Hero.Magikarp;
import DungeonCharacter.Hero.Snorlax;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TitleScreenView extends JPanel implements Runnable, View {
    private Thread myTitleScreenViewThread;

    public TitleScreenView(){
        setLayout(null);
        createMenuOptions();
        myTitleScreenViewThread = new Thread(this);
        myTitleScreenViewThread.start();
    }

    private void createMenuOptions() {
        JButton loadButton = new JButton("Load game");
        JButton chooseJirachiButton = new JButton("Jirachi");
        JButton chooseSnorlaxButton = new JButton("Snorlax");
        JButton chooseGalladeButton = new JButton("Gallade");
        JButton chooseMagikarpButton = new JButton("Magikarp");
        JButton takeCharacterQuizButton = new JButton("Let fate decide your Pokemon");
        chooseJirachiButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Jirachi());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseGalladeButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Gallade());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseMagikarpButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Magikarp());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseSnorlaxButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Snorlax());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        takeCharacterQuizButton.addActionListener(e -> {
            try {
                GameManager.createCharacterSelectView();
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (UnsupportedAudioFileException ex) {
                ex.printStackTrace();
            } catch (LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        loadButton.addActionListener(e -> {
            SaveState loadedState = null;
            try{
                loadedState = SaveManager.getSavedGameState();
            }
            catch(IOException | ClassNotFoundException  err) {err.printStackTrace();}

            if(loadedState != null){
                try {
                    GameManager.loadGame(loadedState);
                } catch (IOException | UnsupportedAudioFileException | LineUnavailableException err) {
                    err.printStackTrace();
                }
            }
        });
        int x = 10*SCALE;
        int y = 90*SCALE;
        int ygap = 15 * SCALE;
        int width = 45 * SCALE;
        int height = 10 * SCALE;

        chooseJirachiButton.setBounds(x,y,width,height);
        chooseSnorlaxButton.setBounds(x*2+width,y,width,height);
        chooseGalladeButton.setBounds(x,y+ygap,width,height);
        chooseMagikarpButton.setBounds(x*2+width,y+ygap,width,height);
        takeCharacterQuizButton.setBounds(x, y+ygap*2, width*2+x, height);
        loadButton.setBounds(x, (int) Math.round(y+ygap*5.5), width*2+x, height);

        add(chooseJirachiButton);
        add(chooseGalladeButton);
        add(chooseMagikarpButton);
        add(chooseSnorlaxButton);
        add(takeCharacterQuizButton);
        add(loadButton);
    }

    @Override
    public void run() {

    }
    protected void paintComponent(final Graphics theGraphics){
        super.paintComponent(theGraphics);
        Graphics2D g2 = (Graphics2D) theGraphics;
        try {
            createBackground(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBackground(final Graphics2D theGraphics) throws IOException {
        ImageIcon background = new ImageIcon(CharacterSelectView.class.getResource("/Sprites/Title_Screen_Background.png"));
        theGraphics.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        int yPadding = SCALE * 15;
        theGraphics.setColor(Color.BLACK);
        theGraphics.setFont(new Font(Font.SERIF, Font.BOLD, 12*SCALE));
        drawStringWithOutline(theGraphics,"Developed by", yPadding/2, this.getHeight()/15);
        drawStringWithOutline(theGraphics,"Select your Pokemon", yPadding/2, this.getHeight()/2 - yPadding);
        drawStringWithOutline(theGraphics,"Load game", yPadding/2, this.getHeight()/2 + this.getHeight()/3);
        theGraphics.setFont(new Font(Font.SERIF, Font.PLAIN, 10*SCALE));
        drawStringWithOutline(theGraphics,"Kevin Kulman", yPadding, this.getHeight()/8);
        drawStringWithOutline(theGraphics,"Stephen Vanluven", yPadding, this.getHeight()/8 + yPadding);
        drawStringWithOutline(theGraphics,"Anthony Owens", yPadding, this.getHeight()/8 + yPadding*2);
    }

    @Override
    public void drawStringWithOutline(final Graphics theGraphics, final String theString,
                                      final int theX, final int theY) {
        theGraphics.setColor(Color.WHITE);
        theGraphics.drawString(theString,theX-1, theY-1);
        theGraphics.drawString(theString,theX-1, theY+1);
        theGraphics.drawString(theString,theX+1, theY-1);
        theGraphics.drawString(theString,theX+1, theY+1);
        theGraphics.setColor(Color.BLACK);
        theGraphics.drawString(theString, theX, theY);
    }
}