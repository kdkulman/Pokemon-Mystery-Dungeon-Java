package Controller;
import DungeonCharacter.Enemy;
import FloorGenerator.Floor;
import TileObjects.Texture;
import TileObjects.TileObject;
import TileObjects.Wall;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class InputControls extends KeyAdapter {

    private static int playerRow;
    private static int playerColumn;
    private static TileObject[][] floor;

    public InputControls() throws IOException {

    }

    public static void useRegularAttack() {
    }

    public static void useSpecialAttack() {
    }

    public static void useOranBerry() {
    }

    public static void quitGame() {
    }

    public static Floor updateFloor() {
        return null; //placeholder
    }

    //This method exists for readability - shorthanding these fields
    //And elminates some duplicate lines of code
    private static void updateFloorFields(final Floor theFloor){
        playerRow = theFloor.getPlayerRow();
        playerColumn = theFloor.getPlayerColumn();
        floor = theFloor.getFloorArray();
    }

<<<<<<< Updated upstream
    public static Floor moveUp(final Floor theFloor){
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        if(playerRow != 0 && !(floor[playerRow-1][playerColumn] instanceof Wall) &&
                !(floor[playerRow-1][playerColumn] instanceof Enemy)) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[playerRow-1][playerColumn] = playerTemp;
            returnFloor.setPlayerRow(playerRow - 1);
            returnFloor.setFloorArray(floor);
            return returnFloor;
        }
        System.out.println("Cannot move up!");
        return theFloor;
=======
 public static Floor getGoodMaze(final Hero hero) throws IOException {
        Floor returnFloor = new Floor(hero);
        return returnFloor;
    }

    public void collectOranBerry() throws IOException {
        Hero updateOranBerry = new Snorlax();
        updateOranBerry.collectOranBerry();
>>>>>>> Stashed changes
    }

    public static Floor moveDown(final Floor theFloor){
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        if(playerRow < floor.length-1 && !(floor[playerRow+1][playerColumn] instanceof Wall) &&
                !(floor[playerRow+1][playerColumn] instanceof Enemy)) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[playerRow+1][playerColumn] = playerTemp;
            returnFloor.setPlayerRow(playerRow + 1);
            returnFloor.setFloorArray(floor);
            return returnFloor;
        }
        System.out.println("Cannot move down!");
        return theFloor;
    }

    public static Floor moveLeft(final Floor theFloor){
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        if(playerColumn != 0 && !(floor[playerRow][playerColumn-1] instanceof Wall) &&
                !(floor[playerRow-1][playerColumn] instanceof Enemy)) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[playerRow][playerColumn-1] = playerTemp;
            returnFloor.setPlayerColumn(playerColumn - 1);
            returnFloor.setFloorArray(floor);
            return returnFloor;
        }
        System.out.println("Cannot move left!");
        return theFloor;

    }

    public static Floor moveRight(final Floor theFloor){
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        if(playerColumn < floor[0].length-1 && !(floor[playerRow][playerColumn+1] instanceof Wall) &&
                !(floor[playerRow+1][playerColumn] instanceof Enemy)) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[playerRow][playerColumn + 1] = playerTemp;
            returnFloor.setPlayerColumn(playerColumn + 1);
            returnFloor.setFloorArray(floor);
            return returnFloor;
        }
        System.out.println("Cannot move right!");
        return theFloor;
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
    public static Floor move(final Floor theFloor, final int destinationRow,
                             final int destinationColumn, final String errorMessage,
                             final DungeonCharacter.Direction direction) throws IOException {
        Floor returnFloor = theFloor;
        Hero hero = theFloor.player;
        if(floor[destinationRow][destinationColumn].getSolid() == false) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            if(floor[destinationRow][destinationColumn] instanceof OranBerry){
                hero.collectOranBerry();
                System.out.println("berry count: " + hero.getBerryCount());
            }
            if(floor[destinationRow][destinationColumn] instanceof VisionSeed){
                hero.collectVisionSeeds();
                System.out.println("seed count: " + hero.getSeedCount());
            }
            if(floor[destinationRow][destinationColumn] instanceof Staircase){
                hero.updateMyCurrentLevel();
                System.out.println("current level: " + hero.getMyFloorLevel());
                returnFloor = getGoodMaze(hero);
                return returnFloor;
            }
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[destinationRow][destinationColumn] = playerTemp;
            returnFloor.setPlayerRow(destinationRow);
            returnFloor.setPlayerColumn(destinationColumn);
            returnFloor.setFloorArray(floor);
            returnFloor = updateTileObjectVisibility(returnFloor);
            returnFloor.getFloorArray()[destinationRow][destinationColumn].setDirection(direction);
            return returnFloor;
        }
        System.out.println(errorMessage);
        returnFloor.getFloorArray()[playerRow][playerColumn].setDirection(direction);
        returnFloor = updateTileObjectVisibility(returnFloor);
        return returnFloor;
    }

}