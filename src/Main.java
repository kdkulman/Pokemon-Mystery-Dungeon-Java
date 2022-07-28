import GameView.GameView;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        createGameView();
    }

    private static void createGameView() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pokemon Mystery Dungeon Java");
        try {
            window.add(new GameView());
        } catch (IOException e){
            System.out.println("could not create gameview not sure why bro");
        }
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}