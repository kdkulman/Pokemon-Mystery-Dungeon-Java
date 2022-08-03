import DungeonCharacter.Hero.Jirachi;
import DungeonCharacter.Hero.Snorlax;
import GameView.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {
    private static final int SCALE = 3;
    private static final int BASE_TILE_SIZE = 24;
    private static final int TILE_SIZE = BASE_TILE_SIZE * SCALE;
    private static final int SCREEN_TILE_WIDTH = 13;    //View will be 9 tiles x 13 tiles
    private static final int SCREEN_TILE_HEIGHT = 9;
    private static final int SCREEN_WIDTH = SCREEN_TILE_WIDTH * TILE_SIZE;
    private static final int SCREEN_HEIGHT = SCREEN_TILE_HEIGHT * TILE_SIZE;

    public static void main(String[] args) throws IOException {
        JFrame jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setResizable(false);
        jFrame.setTitle("Pokemon Mystery Dungeon Java");
        jFrame.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        jFrame.setBackground(Color.BLACK);
        jFrame.isDoubleBuffered();
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        //Character Quiz
        //CharacterQuizView characterQuizView = new CharacterQuizView(jFrame);

        //FOR NOW, PASS THE PLAYER INTO THE VIEW
        GameView gameView = new GameView(jFrame, new Jirachi());
    }


}