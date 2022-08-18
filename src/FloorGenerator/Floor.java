package FloorGenerator;

import DungeonCharacter.Hero.Hero;
import TileObjects.TileObject;

import java.io.IOException;
import java.io.Serializable;

public class Floor implements Serializable {

    private TileObject[][] floor;
    private int playerRow;
    private int playerColumn;
    public Hero player;

    public Floor(final Hero player) throws IOException {
        this.player = player;
        createFloor();
    }

    public Floor(final TileObject[][] theFloorArray, final int thePlayerRow, final int thePlayerCol, final Hero thePlayer) {
        this.floor = theFloorArray;
        this.playerRow = thePlayerRow;
        this.playerColumn = thePlayerCol;
        this.player = thePlayer;
    }

    public void createFloor() throws IOException {
        FloorGenerator floorGenerator = new FloorGenerator(player);
        playerRow = floorGenerator.getPlayerRow();
        playerColumn = floorGenerator.getPlayerColumn();
        floor = floorGenerator.getFloor();
        FloorTraversal testNewFloor = new FloorTraversal(floor, playerRow, playerColumn);
        boolean status = testNewFloor.getMyTraversableBoolean();
        //System.out.println("is the floor traversable: " + status);
        if (!status){
            createFloor();
        }
    }

    public int getPlayerRow(){
        return playerRow;
    }

    public int getPlayerColumn(){
        return playerColumn;
    }

    public void setPlayerRow(final int playerRow){
        this.playerRow = playerRow;
    }

    public void setPlayerColumn(final int playerColumn){
        this.playerColumn = playerColumn;
    }

    public void setFloorArray(TileObject[][] floor){
        this.floor = floor;
    }

    public TileObject[][] getFloorArray(){
        return floor;
    }
}

