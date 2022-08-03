package Controller;
import DungeonCharacter.Enemy.Enemy;
import FloorGenerator.Floor;
import TileObjects.*;
import java.awt.event.KeyAdapter;
import java.io.IOException;

public final class InputControls extends KeyAdapter {
    private static int playerRow;
    private static int playerColumn;
    private static TileObject[][] floor;

    public static void useRegularAttack() {
    }

    public static void useSpecialAttack() {
    }

    public static void useOranBerry() {
    }

    public static void quitGame() {
    }

    //This method exists for readability - shorthanding these fields
    //And elminates some duplicate lines of code
    private static void updateFloorFields(final Floor theFloor){
        playerRow = theFloor.getPlayerRow();
        playerColumn = theFloor.getPlayerColumn();
        floor = theFloor.getFloorArray();
    }

    public static Floor move(final Floor theFloor, final int destinationRow,
                             final int destinationColumn, final String errorMessage){
        Floor returnFloor = theFloor;
        if(playerRow != 0 && !(floor[destinationRow][destinationColumn] instanceof Wall) &&
                !(floor[destinationRow][destinationColumn] instanceof Enemy)) {
            TileObject playerTemp = floor[playerRow][playerColumn];
            try {
                floor[playerRow][playerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            floor[destinationRow][destinationColumn] = playerTemp;
            returnFloor.setPlayerRow(destinationRow);
            returnFloor.setPlayerColumn(destinationColumn);
            returnFloor.setFloorArray(floor);
            return returnFloor;
        }
        System.out.println(errorMessage);
        return theFloor;
    }

    public static Floor moveUp(final Floor theFloor){
        updateFloorFields(theFloor);
        return move(theFloor, playerRow-1, playerColumn, "Cannot move up!");
    }

    public static Floor moveDown(final Floor theFloor){
        updateFloorFields(theFloor);
        return move(theFloor, playerRow+1, playerColumn, "Cannot move down!");
    }

    public static Floor moveLeft(final Floor theFloor){
        updateFloorFields(theFloor);
        return move(theFloor, playerRow, playerColumn-1, "Cannot move left!");
    }

    public static Floor moveRight(final Floor theFloor){
        updateFloorFields(theFloor);
        return move(theFloor, playerRow, playerColumn+1, "Cannot move right!");
    }
}