package Controller;
import GameView.GameView;
import TileObjects.Texture;
import TileObjects.TileObject;
import TileObjects.Wall;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class InputControls extends KeyAdapter {

    private GameView gameView;
    private TileObject[][] floor;
    private int playerRow;
    private int playerColumn;

    public InputControls(GameView gameView) throws IOException {
        this.gameView = gameView;
        this.floor = this.gameView.getFloor();
        this.playerColumn = this.gameView.getPlayerColumn();
        this.playerRow = this.gameView.getPlayerRow();
    }

    public static void useRegularAttack() {
    }

    public static void useSpecialAttack() {
    }

    public static void useOranBerry() {
    }

    public static void quitGame() {
    }

    private void updateView(){
        gameView.setFloor(floor);
        gameView.setPlayerColumn(playerColumn);
        gameView.setPlayerRow(playerRow);
    }

    public static void moveUp(){
//        //if(playerRow != 0 && !(floor[playerRow-1][playerColumn] instanceof Wall)) {
//        setPlayerRow(playerRow - 1);
//        //move player to destination tile
//        TileObject playerTemp = floor[playerRow][playerColumn];
//        try {
//            floor[playerRow][playerColumn] = new Texture();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//        floor[playerRow-1][playerColumn] = playerTemp;
//        //}
    }

    public static void moveDown(){

    }

    public static void moveLeft(){

    }

    public static void moveRight(){

    }


    public void keyPressed(KeyEvent e){
        // New key press
        int key = e.getKeyCode();
        // If we press right
        if (key == KeyEvent.VK_RIGHT) {
            System.out.println("The right arrow key is pressed");
            // Or, if we press left
        } else if (key == KeyEvent.VK_LEFT) {
            System.out.println("The left arrow key is pressed");
        }
    }

    public void keyReleased(KeyEvent e){
        // New key has been released
        int key = e.getKeyCode();
        // If we release the right key
        if(key == KeyEvent.VK_RIGHT){
            System.out.println("The right arrow key is released");
        }
        // If we release the left key
        else if(key == KeyEvent.VK_LEFT){
            System.out.println("The left arrow key is released");
        }
    }
}