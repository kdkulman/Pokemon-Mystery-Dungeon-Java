package Views;

import CharacterSelect.CharacterSelectQuiz;
import CharacterSelect.*;
import Controller.GameManager;
import DungeonCharacter.Hero.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class CharacterSelectView extends JPanel implements Runnable, View {

    private static Hero mySelectedHero;
    private Thread myCharacterSelectViewThread;
    private String myCurrentQuestion;
    private CharacterSelectQuiz myCharacterSelectQuiz;
    private Graphics2D myGraphics;

    public CharacterSelectView() throws IOException {
        setLayout(null);
        myCharacterSelectQuiz = CharacterSelectQuiz.getMyInstance();
        createQuestion();
        myCharacterSelectViewThread = new Thread(this);
        myCharacterSelectViewThread.start();
    }

    @Override
    public void run() {
        while (myCharacterSelectViewThread != null){
            repaint();


        }
    }

    private void createQuestion() {
        removeAll();
        int x = 80*SCALE;
        int y = 90*SCALE;
        int ygap = 50 * SCALE;
        int width = 65 * SCALE;
        int height = 10 * SCALE;
        CharacterSelectQuestion questionObject = myCharacterSelectQuiz.getCurrentQuestion();
        myCurrentQuestion = questionObject.getMyQuestion();
        String answer1String = questionObject.getMyAnswer1();
        String answer2String = questionObject.getMyAnswer2();
        String answer3String = questionObject.getMyAnswer3();
        String answer4String = questionObject.getMyAnswer4();
        JButton answer1 = new JButton(answer1String);
        JButton answer2 = new JButton(answer2String);
        JButton answer3 = new JButton(answer3String);
        JButton answer4 = new JButton(answer4String);

        answer1.setBounds(x,y,width,height);
        answer2.setBounds(x*2,y,width,height);
        answer3.setBounds(x,y+ygap,width,height);
        answer4.setBounds(x*2,y+ygap,width,height);


        answer1.addActionListener(e -> {
            try {
                myCharacterSelectQuiz.addCharacterPoints(questionObject.getMyAnswer1Points());
                if (myCharacterSelectQuiz.isQuizCompleted()){
                    endCharacterSelectView();
                } else {
                    createQuestion();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        answer2.addActionListener(e -> {
            try {
                myCharacterSelectQuiz.addCharacterPoints(questionObject.getMyAnswer2Points());
                if (myCharacterSelectQuiz.isQuizCompleted()){
                    endCharacterSelectView();
                } else {
                    createQuestion();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        answer3.addActionListener(e -> {
            try {
                myCharacterSelectQuiz.addCharacterPoints(questionObject.getMyAnswer3Points());
                if (myCharacterSelectQuiz.isQuizCompleted()){
                    endCharacterSelectView();
                } else {
                    createQuestion();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        answer4.addActionListener(e -> {
            try {
                myCharacterSelectQuiz.addCharacterPoints(questionObject.getMyAnswer4Points());
                if (myCharacterSelectQuiz.isQuizCompleted()){
                    endCharacterSelectView();
                } else {
                    createQuestion();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }});

        add(answer1);
        add(answer2);
        if (answer3String != null) add(answer3);
        if (answer4String != null) add(answer4);
    }

    private void endCharacterSelectView() throws IOException {
        removeAll();
        GameManager.setMySelectedHero(myCharacterSelectQuiz.getMyPlayer());
        myCurrentQuestion = "It appears your inner Pokemon is " + myCharacterSelectQuiz.getMyPlayer().getName();
        drawQuestion(myGraphics);
        JButton proceedButton = new JButton("Go on");
        int x = (this.getWidth()/3 + this.getWidth()/2) / 2;
        int y = 90*SCALE;
        int width = 30 * SCALE;
        int height = 10 * SCALE;
        proceedButton.setBounds(x, y, width, height);
        proceedButton.addActionListener(e -> {
            try {
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
            }
        });
        add(proceedButton);
    }

    private void createBackground(final Graphics2D theGraphics) throws IOException {
        ImageIcon backgroundGif = new ImageIcon(CharacterSelectView.class.getResource("/Sprites/CharacterSelectScreen_Background.gif"));
        theGraphics.drawImage(backgroundGif.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
        theGraphics.setColor(Color.BLACK);
        theGraphics.fillRect(0, 0, this.getWidth(), 20*SCALE);
        theGraphics.fillRect(0, this.getHeight()-20*SCALE, this.getWidth(), this.getHeight()/2);
    }

    private void drawQuestion(final Graphics2D theGraphics){
        setFont(FONT);
        theGraphics.drawString(myCurrentQuestion, this.getWidth()/5, this.getHeight()/3);
    }

    protected void paintComponent(final Graphics theGraphics){
        super.paintComponent(theGraphics);
        Graphics2D g2 = (Graphics2D) theGraphics;
        myGraphics = g2;
        try {
            createBackground(g2);
            drawQuestion(g2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void manuallySelectCharacter(){
        JButton selectSnorlaxButton = new JButton("Snorlax");
        selectSnorlaxButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Snorlax());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
                System.out.println("Could not create GameView after selecting Snorlax");
            }
        });

        JButton selectJirachiButton = new JButton("Jirachi");
        selectJirachiButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Jirachi());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
                System.out.println("Could not create GameView after selecting Jirachi");
            }
        });
        JButton selectGalladeButton = new JButton("Gallade");
        selectGalladeButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Gallade());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
                System.out.println("Could not create GameView after selecting Gallade");
            }
        });
        JButton selectMagikarpButton = new JButton("Magikarp");
        selectMagikarpButton.addActionListener(e -> {
            try {
                GameManager.setMySelectedHero(new Magikarp());
                GameManager.createGameView();
            } catch (IOException | UnsupportedAudioFileException | LineUnavailableException ex) {
                ex.printStackTrace();
                System.out.println("Could not create GameView after selecting Magikarp");
            }
        });

        add(selectSnorlaxButton);
        add(selectJirachiButton);
        add(selectGalladeButton);
        add(selectMagikarpButton);
    }


}
