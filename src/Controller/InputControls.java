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

public final class InputControls extends KeyAdapter {
    private static int myPlayerRow;
    private static int myPlayerColumn;
    private static TileObject[][] myFloor;

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

    private static void performAttack(final Hero thePlayer, final Enemy theFoe) {
        theFoe.setTarget(thePlayer);
        thePlayer.setTarget(theFoe);
        Message.setMessage(thePlayer.attack());
        Message.setMessage(theFoe.enemyDecision());
        thePlayer.clearTarget();
        theFoe.clearTarget();
    }

    private static void performSpecialAttack(final Hero thePlayer, final Enemy theFoe) {
        theFoe.setTarget(thePlayer);
        thePlayer.setTarget(theFoe);
        Message.setMessage(thePlayer.specialAttack());
        Message.setMessage(theFoe.enemyDecision());
        thePlayer.clearTarget();
        theFoe.clearTarget();
    }


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

    public static void quitGame() {
        System.exit(0);
    }

    //This method exists for readability - shorthanding these fields
    //And elminates some duplicate lines of code
    private static void updateFloorFields(final Floor theFloor){
        myPlayerRow = theFloor.getMyPlayerRow();
        myPlayerColumn = theFloor.getMyPlayerColumn();
        myFloor = theFloor.getFloorArray();
    }

    public static Floor getGoodMaze(final Hero theHero) throws IOException {
        Floor returnFloor = new Floor(theHero);
        return returnFloor;
    }

    public void collectOranBerry() throws IOException {
        Hero updateOranBerry = new Snorlax();
        updateOranBerry.collectOranBerry();
    }

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

    public static Floor moveUp(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow -1, myPlayerColumn,
                "Cannot move myUp!", DungeonCharacter.Direction.UP);
    }

    public static Floor moveDown(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow +1, myPlayerColumn,
                "Cannot move myDown!", DungeonCharacter.Direction.DOWN);
    }

    public static Floor moveLeft(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow, myPlayerColumn -1,
                "Cannot move myLeft!", DungeonCharacter.Direction.LEFT);
    }

    public static Floor moveRight(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, myPlayerRow, myPlayerColumn +1,
                "Cannot move myRight!", DungeonCharacter.Direction.RIGHT);
    }

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