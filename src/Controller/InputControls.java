package Controller;
import DungeonCharacter.DungeonCharacter;
import DungeonCharacter.Enemy.Enemy;
import DungeonCharacter.Hero.Hero;
import DungeonCharacter.Hero.Snorlax;
import FloorGenerator.Floor;

import Views.Message;
import TileObjects.*;
import TileObjects.Items.OranBerry;
import TileObjects.Items.VisionSeed;

import java.awt.event.KeyAdapter;
import java.io.IOException;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public final class InputControls extends KeyAdapter {
    private static int myPlayerRow;
    private static int myPlayerColumn;
    private static TileObject[][] myFloor;

    /**
     * regular attack input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor useRegularAttack(final Floor theFloor) throws IOException {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        updateFloorFields(theFloor);
        Hero player = (Hero) tempFloor[myPlayerRow][myPlayerColumn];
        Enemy foe = null;
        int foeRow = 0;
        int foeCol = 0;
        if (checkAttackDirection(theFloor)) {
            if (player.getMyDirection() == TileObject.Direction.UP) {
                System.out.println("Attack UP chosen.");
                foeRow = myPlayerRow - 1;
                foeCol = myPlayerColumn;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.RIGHT) {
                System.out.println("Attack RIGHT chosen.");
                foeRow = myPlayerRow;
                foeCol = myPlayerColumn + 1;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.DOWN) {
                System.out.println("Attack DOWN chosen.");
                foeRow = myPlayerRow + 1;
                foeCol = myPlayerColumn;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.LEFT) {
                System.out.println("Attack LEFT chosen.");
                foeRow = myPlayerRow;
                foeCol = myPlayerColumn - 1;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performAttack(player, foe);
            }
        } else {
            Message.setMessage("Nothing in that direction to attack!");
        }
        if(foe != null) {
            if(foe.getHP() < 1) {
                tempFloor[foeRow][foeCol] = new Texture();
            }
        }

        return new Floor(tempFloor, myPlayerRow, myPlayerColumn, player);
    }

    /**
     * perform attack method
     * @param thePlayer the hero attacking
     * @param theFoe the enemy to be attacked
     */
    private static void performAttack(final Hero thePlayer, final Enemy theFoe) {
        theFoe.setTarget(thePlayer);
        thePlayer.setTarget(theFoe);
        Message.setMessage(thePlayer.attack());
        Message.setMessage(theFoe.enemyDecision());
        thePlayer.clearTarget();
        theFoe.clearTarget();
    }

    /**
     * special attack method
     * @param thePlayer the hero attacking
     * @param theFoe the enemy to be attacked
     */
    private static void performSpecialAttack(final Hero thePlayer, final Enemy theFoe) {
        theFoe.setTarget(thePlayer);
        thePlayer.setTarget(theFoe);
        Message.setMessage(thePlayer.specialAttack());
        Message.setMessage(theFoe.enemyDecision());
        thePlayer.clearTarget();
        theFoe.clearTarget();
    }

    /**
     * use special attack input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor useSpecialAttack(final Floor theFloor) throws IOException {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        updateFloorFields(theFloor);
        Hero player = (Hero) tempFloor[myPlayerRow][myPlayerColumn];
        Enemy foe = null;
        int foeRow = 0;
        int foeCol = 0;
        if (checkAttackDirection(theFloor)) {
            if (player.getMyDirection() == TileObject.Direction.UP) {
                System.out.println("Attack UP chosen.");
                foeRow = myPlayerRow - 1;
                foeCol = myPlayerColumn;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performSpecialAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.RIGHT) {
                System.out.println("Attack RIGHT chosen.");
                foeRow = myPlayerRow;
                foeCol = myPlayerColumn + 1;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performSpecialAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.DOWN) {
                System.out.println("Attack DOWN chosen.");
                foeRow = myPlayerRow + 1;
                foeCol = myPlayerColumn;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performSpecialAttack(player, foe);
            } else if (player.getMyDirection() == TileObject.Direction.LEFT) {
                System.out.println("Attack LEFT chosen.");
                foeRow = myPlayerRow;
                foeCol = myPlayerColumn - 1;
                foe = (Enemy) tempFloor[foeRow][foeCol];
                performSpecialAttack(player, foe);
            }
        } else {
            Message.setMessage("Nothing in that direction to attack!");
        }
        if(foe != null) {
            if(foe.getHP() < 1) {
                tempFloor[foeRow][foeCol] = new Texture();
            }
        }

        return new Floor(tempFloor, myPlayerRow, myPlayerColumn, player);
    }

    /**
     * Method checking if player direction is valid attack direction
     * @param theFloor The floor layout to be checked
     * @return true if valid attack direction, false otherwise
     */
    private static boolean checkAttackDirection(final Floor theFloor) {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        Hero player = (Hero) tempFloor[myPlayerRow][myPlayerColumn];
        updateFloorFields(theFloor);
        if(player.getMyDirection() == TileObject.Direction.UP &&
                tempFloor[myPlayerRow - 1][myPlayerColumn] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.RIGHT &&
                    tempFloor[myPlayerRow][myPlayerColumn + 1] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.DOWN &&
                    tempFloor[myPlayerRow + 1][myPlayerColumn] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.LEFT &&
                    tempFloor[myPlayerRow][myPlayerColumn - 1] instanceof Enemy) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Use oranberry input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     */
    public static Floor useOranBerry(final Floor theFloor) {
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        Hero player = (Hero) myFloor[myPlayerRow][myPlayerColumn];
        if(player.getBerryCount() > 0) {
            Message.setMessage(player.getName() + " used an Oran Berry!");
            player.heal(20);
            player.setBerryCount(player.getBerryCount() - 1);
        } else {
            Message.setMessage("You don't have any Oran Berries!");
        }
        return returnFloor;
    }

    /**
     * use vision seed input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     */
    public static Floor useVisionSeed(final Floor theFloor) {
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        Hero player = (Hero) myFloor[myPlayerRow][myPlayerColumn];
        if (player.getSeedCount() > 0){
            Message.setMessage(player.getName() + " used the Vision Seed.");
            Message.setMessage("The whole floor was revealed!");
            player.setSeedCount(player.getSeedCount()-1);
            for (int row = 0; row < myFloor.length; row++){
                for (int column = 0; column < myFloor[0].length; column++){
                    myFloor[row][column].setVisibleOnMiniMap();
                }
            }
            returnFloor.setFloorArray(myFloor);
            return returnFloor;
        }
        return returnFloor;
    }

    /**
     * Quit game input method
     */
    public static void quitGame() {
        System.exit(0);
    }

    /**
     * This method exists for readability - shorthanding these fields
     * And elminates some duplicate lines of code
     * @param theFloor The floor to be manipulated
     */
    private static void updateFloorFields(final Floor theFloor){
        myPlayerRow = theFloor.getMyPlayerRow();
        myPlayerColumn = theFloor.getMyPlayerColumn();
        myFloor = theFloor.getFloorArray();
    }

    /**
     * Method that calls floor generate method and returns new floor
     * @param theHero The hero to be placed on the floor
     * @return new floor
     * @throws IOException
     */
    public static Floor getGoodMaze(final Hero theHero) throws IOException {
        Floor returnFloor = new Floor(theHero);
        return returnFloor;
    }

    public void collectOranBerry() throws IOException {
        Hero updateOranBerry = new Snorlax();
        updateOranBerry.collectOranBerry();
    }

    /**
     * Method that updates minimap visibility
     * @param theFloor The floor to be manipulated
     * @return new floor
     */
    private static Floor updateTileObjectVisibility(final Floor theFloor){
        updateFloorFields(theFloor);
        myFloor[myPlayerRow -1][myPlayerColumn -1].setVisibleOnMiniMap();
        myFloor[myPlayerRow -1][myPlayerColumn].setVisibleOnMiniMap();
        myFloor[myPlayerRow -1][myPlayerColumn +1].setVisibleOnMiniMap();
        myFloor[myPlayerRow][myPlayerColumn -1].setVisibleOnMiniMap();
        myFloor[myPlayerRow][myPlayerColumn].setVisibleOnMiniMap();
        myFloor[myPlayerRow][myPlayerColumn +1].setVisibleOnMiniMap();
        myFloor[myPlayerRow +1][myPlayerColumn -1].setVisibleOnMiniMap();
        myFloor[myPlayerRow +1][myPlayerColumn].setVisibleOnMiniMap();
        myFloor[myPlayerRow +1][myPlayerColumn +1].setVisibleOnMiniMap();
        Floor returnFloor = theFloor;
        returnFloor.setFloorArray(myFloor);
        return returnFloor;
    }

    /**
     * Move up input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor moveUp(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow -1, myPlayerColumn,
                "Cannot move myUp!", DungeonCharacter.Direction.UP);
    }

    /**
     * Move down input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor moveDown(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow +1, myPlayerColumn,
                "Cannot move myDown!", DungeonCharacter.Direction.DOWN);
    }

    /**
     * Move left input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor moveLeft(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow, myPlayerColumn -1,
                "Cannot move myLeft!", DungeonCharacter.Direction.LEFT);
    }

    /**
     * Move right input method
     * @param theFloor The floor to be manipulated
     * @return new floor
     * @throws IOException
     */
    public static Floor moveRight(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow, myPlayerColumn +1,
                "Cannot move myRight!", DungeonCharacter.Direction.RIGHT);
    }

    /**
     * Move method
     * @param theFloor The floor to be manipulated
     * @param theDestinationRow The row to move to
     * @param theDestinationColumn The column to move to
     * @param theErrorMessage The error message to be displayed
     * @param theDirection The direction to face
     * @return new floor
     * @throws IOException
     */
    public static Floor move(final Floor theFloor, final int theDestinationRow,
                             final int theDestinationColumn, final String theErrorMessage,
                             final DungeonCharacter.Direction theDirection) throws IOException {
        Message.setMyIsVisible(false);
        Floor returnFloor = theFloor;
        Hero hero = theFloor.myPlayer;
        returnFloor.getFloorArray()[myPlayerRow][myPlayerColumn].setDirection(theDirection);
        if (myFloor[theDestinationRow][theDestinationColumn].getMySolidTest() == false) {
            TileObject playerTemp = myFloor[myPlayerRow][myPlayerColumn];
            if (myFloor[theDestinationRow][theDestinationColumn] instanceof OranBerry) {
                hero.collectOranBerry();
                System.out.println("berry count: " + hero.getBerryCount());
            }
            if (myFloor[theDestinationRow][theDestinationColumn] instanceof VisionSeed) {
                hero.collectVisionSeeds();
                System.out.println("seed count: " + hero.getSeedCount());
            }
            if (myFloor[theDestinationRow][theDestinationColumn] instanceof Staircase) {
                hero.updateMyCurrentLevel();
                System.out.println("current level: " + hero.getMyFloorLevel());
                returnFloor = getGoodMaze(hero);
                return returnFloor;
            }
            try {
                myFloor[myPlayerRow][myPlayerColumn] = new Texture();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            myFloor[theDestinationRow][theDestinationColumn] = playerTemp;
            returnFloor.setMyPlayerRow(theDestinationRow);
            returnFloor.setMyPlayerColumn(theDestinationColumn);
            returnFloor.setFloorArray(myFloor);
            returnFloor = updateTileObjectVisibility(returnFloor);
            return returnFloor;
        }
        System.out.println("Cannot move myUp!");
        return theFloor;
    }

    /**
     * save game method
     * @param theFloor The floor to be saved
     */
    public static void saveGame(final Floor theFloor){
        try{
            SaveManager.saveCurrentGameState(theFloor);
            System.out.println("Saved game");
            Message.setMessage("Game saved.");
        }
        catch(IOException err){
            err.printStackTrace();
        }
    }
}