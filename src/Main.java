import FloorGenerator.FloorGenerator;
import GameView.GameView;

import javax.swing.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
//        DungeonCharacter test = new Snorlax();
//        System.out.println(test.toString());
//        System.out.println(Math.random());
        createGameView();
//        FloorGenerator fg = new FloorGenerator();
//        System.out.println(fg.debugToString());
    }

    private static void createGameView() throws IOException {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pokemon Mystery Dungeon Java");
        GameView g = new GameView();
        window.add(g);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}