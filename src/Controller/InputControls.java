package Controller;
import DungeonCharacter.DungeonCharacter;
import DungeonCharacter.Enemy.Enemy;
import DungeonCharacter.Hero.Hero;
import DungeonCharacter.Hero.Snorlax;
import FloorGenerator.Floor;

import GameView.Message;
import TileObjects.*;
import TileObjects.Items.OranBerry;
import TileObjects.Items.VisionSeed;

import java.awt.event.KeyAdapter;
import java.io.IOException;

public final class InputControls extends KeyAdapter {
    private static int playerRow;
    private static int playerColumn;
    private static TileObject[][] floor;

    public static String useRegularAttack(final Floor theFloor) {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        updateFloorFields(theFloor);
        Hero player = (Hero) tempFloor[playerRow][playerColumn];
        String message = "";
        if (checkAttackDirection(theFloor)) {
            if (player.getMyDirection() == TileObject.Direction.UP) {
                System.out.println("Attack UP chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow - 1][playerColumn];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.attack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.RIGHT) {
                System.out.println("Attack RIGHT chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow][playerColumn + 1];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.attack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.DOWN) {
                System.out.println("Attack DOWN chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow + 1][playerColumn];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.attack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.LEFT) {
                System.out.println("Attack LEFT chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow][playerColumn - 1];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.attack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            }
        } else {
            message = "You can't attack that direction.";
        }

        return message;
    }

    public static String useSpecialAttack(final Floor theFloor) {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        updateFloorFields(theFloor);
        Hero player = (Hero) tempFloor[playerRow][playerColumn];
        String message = "";
        if (checkAttackDirection(theFloor)) {
            if (player.getMyDirection() == TileObject.Direction.UP) {
                System.out.println("Attack UP chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow - 1][playerColumn];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.specialAttack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.RIGHT) {
                System.out.println("Attack RIGHT chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow][playerColumn + 1];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.specialAttack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.DOWN) {
                System.out.println("Attack DOWN chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow + 1][playerColumn];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.specialAttack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            } else if (player.getMyDirection() == TileObject.Direction.LEFT) {
                System.out.println("Attack LEFT chosen.");
                Enemy foe = (Enemy) tempFloor[playerRow][playerColumn - 1];
                foe.setTarget(player);
                player.setTarget(foe);
                message += player.specialAttack() + " " + foe.enemyDecision();
                player.clearTarget();
                foe.clearTarget();
            }
        } else {
            message = "You can't attack that direction.";
        }

        return message;
    }

    private static boolean checkAttackDirection(final Floor theFloor) {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        Hero player = (Hero) tempFloor[playerRow][playerColumn];
        updateFloorFields(theFloor);
        if(player.getMyDirection() == TileObject.Direction.UP &&
                tempFloor[playerRow - 1][playerColumn] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.RIGHT &&
                    tempFloor[playerRow][playerColumn + 1] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.DOWN &&
                    tempFloor[playerRow + 1][playerColumn] instanceof Enemy) {
            return true;
        } else if(player.getMyDirection() == TileObject.Direction.LEFT &&
                    tempFloor[playerRow][playerColumn - 1] instanceof Enemy) {
            return true;
        } else {
            return false;
        }
    }

    //I think we may need to return an updated version of Floor - Kevin
    //see useVisionSeed method
    //also check if theres an oran berry in inventory to use
    public static Floor useOranBerry(final Floor theFloor) {
        TileObject[][] tempFloor = theFloor.getFloorArray();
        updateFloorFields(theFloor);
        Hero player = (Hero) tempFloor[playerRow][playerColumn];
        String message = player.heal(20);
        Message.setMessage(message);
        return theFloor;
    }

    public static Floor useVisionSeed(final Floor theFloor) {
        updateFloorFields(theFloor);
        Floor returnFloor = theFloor;
        Hero player = (Hero) floor[playerRow][playerColumn];
        if (player.getSeedCount() > 0){
            Message.setMessage(player.getName() + " used the Vision Seed.");
            Message.setMessage("The whole floor was revealed!");
            player.setSeedCount(player.getSeedCount()-1);
            for (int row = 0; row < floor.length; row++){
                for (int column = 0; column < floor[0].length; column++){
                    floor[row][column].setVisibleOnMiniMap();
                }
            }
            returnFloor.setFloorArray(floor);
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
        playerRow = theFloor.getPlayerRow();
        playerColumn = theFloor.getPlayerColumn();
        floor = theFloor.getFloorArray();
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
            System.out.println("Player now facing " + playerTemp.getMyDirection() + " direction.");
            return returnFloor;
        }
        System.out.println(errorMessage);
        returnFloor.getFloorArray()[playerRow][playerColumn].setDirection(direction);
        returnFloor = updateTileObjectVisibility(returnFloor);
        return returnFloor;
    }
    public static Floor getGoodMaze(final Hero hero) throws IOException {
        Floor returnFloor = new Floor(hero);
        return returnFloor;
    }

    public void collectOranBerry() throws IOException {
        Hero updateOranBerry = new Snorlax();
        updateOranBerry.collectOranBerry();
    }

    private static Floor updateTileObjectVisibility(final Floor theFloor){
        updateFloorFields(theFloor);
        floor[playerRow-1][playerColumn-1].setVisibleOnMiniMap();
        floor[playerRow-1][playerColumn].setVisibleOnMiniMap();
        floor[playerRow-1][playerColumn+1].setVisibleOnMiniMap();
        floor[playerRow][playerColumn-1].setVisibleOnMiniMap();
        floor[playerRow][playerColumn].setVisibleOnMiniMap();

        floor[playerRow][playerColumn+1].setVisibleOnMiniMap();
        floor[playerRow+1][playerColumn-1].setVisibleOnMiniMap();
        floor[playerRow+1][playerColumn].setVisibleOnMiniMap();
        floor[playerRow+1][playerColumn+1].setVisibleOnMiniMap();
        Floor returnFloor = theFloor;
        returnFloor.setFloorArray(floor);
        return returnFloor;
    }

    public static Floor moveUp(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, playerRow-1, playerColumn,
                "Cannot move up!", DungeonCharacter.Direction.UP);
    }

    public static Floor moveDown(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, playerRow+1, playerColumn,
                "Cannot move down!", DungeonCharacter.Direction.DOWN);
    }

    public static Floor moveLeft(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, playerRow, playerColumn-1,
                "Cannot move left!", DungeonCharacter.Direction.LEFT);
    }

    public static Floor moveRight(final Floor theFloor) throws IOException {
        updateFloorFields(theFloor);
        return move(theFloor, playerRow, playerColumn+1,
                "Cannot move right!", DungeonCharacter.Direction.RIGHT);
    }
}