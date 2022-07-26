package GameManager;

import FloorGenerator.FloorGenerator;
import GameView.GameView;
import TileObjects.TileObject;
import javax.swing.*;
import java.io.IOException;

public class GameManager {

    private int state;
    private final int DUNGEON_SIZE = 4;

    private TileObject[][] floor; //model
    private GameView gameView; //view
    private int playerRow;
    private int playerColumn;


    public GameManager() throws IOException {
        createFloor();
        createGameView();
        updateGameView();
    }

    private void updateGameView(){
        gameView.setFloor(floor);
        gameView.setPlayerColumn(playerColumn);
        gameView.setPlayerRow(playerRow);
    }

    private void createGameView() {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Pokemon Mystery Dungeon Java");
        try {
            gameView = new GameView(playerColumn, playerColumn);
            window.add(gameView);

        } catch (IOException e){
            System.out.println("could not create gameview not sure why bro");
        }
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }

    private void createFloor() throws IOException {
        FloorGenerator floor = new FloorGenerator();
        playerRow = floor.getPlayerRow();
        playerColumn = floor.getPlayerColumn();
        this.floor = floor.getFloor();
    }

    private void updateGameState(int state){
        this.state = state;
    }

    public TileObject[][] getFloor(){
        return floor;
    }

}
