package Views;

import Controller.GameManager;
import DungeonCharacter.Hero.Gallade;
import DungeonCharacter.Hero.Jirachi;
import DungeonCharacter.Hero.Magikarp;
import DungeonCharacter.Hero.Snorlax;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TitleScreenView extends JPanel implements Runnable, View {
    private Thread titleScreenViewThread;

    public TitleScreenView(){
        setLayout(null);
        createMenuOptions();
        titleScreenViewThread = new Thread(this);
        titleScreenViewThread.start();
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
                GameManager.setSelectedHero(new Jirachi());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseGalladeButton.addActionListener(e -> {
            try {
                GameManager.setSelectedHero(new Gallade());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseMagikarpButton.addActionListener(e -> {
            try {
                GameManager.setSelectedHero(new Magikarp());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        chooseSnorlaxButton.addActionListener(e -> {
            try {
                GameManager.setSelectedHero(new Snorlax());
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
            //LOAD GAME WOO
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
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        try {
            createBackground(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createBackground(Graphics2D g2) throws IOException {
        ImageIcon background = new ImageIcon(CharacterSelectView.class.getResource("/Sprites/Title_Screen_Background.png"));
        g2.drawImage(background.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        int yPadding = SCALE * 15;
        g2.setColor(Color.BLACK);
        g2.setFont(new Font(Font.SERIF, Font.BOLD, 12*SCALE));
        g2.drawString("Developed by", yPadding/2, this.getHeight()/15);
        g2.drawString("Select your Pokemon", yPadding/2, this.getHeight()/2 - yPadding);
        g2.drawString("Load game", yPadding/2, this.getHeight()/2 + this.getHeight()/3);
        g2.setFont(new Font(Font.SERIF, Font.PLAIN, 10*SCALE));
        g2.drawString("Kevin Kulman", yPadding, this.getHeight()/8);
        g2.drawString("Stephen Vanluven", yPadding, this.getHeight()/8 + yPadding);
        g2.drawString("Anthony Owens", yPadding, this.getHeight()/8 + yPadding*2);
    }
}
