package FloorGenerator;

import DungeonCharacter.Hero.Hero;
import TileObjects.TileObject;

import java.io.IOException;
import java.io.Serializable;

public class Floor implements Serializable {

    private TileObject[][] myFloor;
    private int myPlayerRow;
    private int myPlayerColumn;
    public Hero myPlayer;

    public Floor(final Hero thePlayer) throws IOException {
        this.myPlayer = thePlayer;
        createFloor();
    }

    public Floor(final TileObject[][] theFloorArray, final int thePlayerRow, final int thePlayerCol, final Hero thePlayer) {
        this.myFloor = theFloorArray;
        this.myPlayerRow = thePlayerRow;
        this.myPlayerColumn = thePlayerCol;
        this.myPlayer = thePlayer;
    }

    public void createFloor() throws IOException {
        FloorGenerator floorGenerator = new FloorGenerator(myPlayer);
        myPlayerRow = floorGenerator.getMyPlayerRow();
        myPlayerColumn = floorGenerator.getMyPlayerColumn();
        myFloor = floorGenerator.getMyFloor();
        FloorTraversal testNewFloor = new FloorTraversal(myFloor, myPlayerRow, myPlayerColumn);
        boolean status = testNewFloor.getMyTraversableBoolean();
        //System.out.println("is the floor traversable: " + status);
        if (!status){
            createFloor();
        }
    }

    public int getMyPlayerRow(){
        return myPlayerRow;
    }

    public int getMyPlayerColumn(){
        return myPlayerColumn;
    }

    public void setMyPlayerRow(final int thePlayerRow){
        this.myPlayerRow = thePlayerRow;
    }

    public void setMyPlayerColumn(final int thePlayerColumn){
        this.myPlayerColumn = thePlayerColumn;
    }

    public void setFloorArray(final TileObject[][] theFloor){
        this.myFloor = theFloor;
    }

    public TileObject[][] getFloorArray(){
        return myFloor;
    }
}

