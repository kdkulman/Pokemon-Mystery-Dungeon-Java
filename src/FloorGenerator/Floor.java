package FloorGenerator;

import DungeonCharacter.Hero.Hero;
import TileObjects.TileObject;

import java.io.IOException;
import java.io.Serializable;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class Floor implements Serializable {

    private TileObject[][] myFloor;
    private int myPlayerRow;
    private int myPlayerColumn;
    public Hero myPlayer;

    /**
     * Constructor for objects of class Floor
     * @param thePlayer The player object
     * @throws IOException
     */
    public Floor(final Hero thePlayer) throws IOException {
        this.myPlayer = thePlayer;
        createFloor();
    }

    /**
     * Creates the floor
     * @param theFloorArray The array of tileobjects
     * @param thePlayerRow The row of the player
     * @param thePlayerCol The column of the player
     * @param thePlayer The player object
     */
    public Floor(final TileObject[][] theFloorArray, final int thePlayerRow, final int thePlayerCol, final Hero thePlayer) {
        this.myFloor = theFloorArray;
        this.myPlayerRow = thePlayerRow;
        this.myPlayerColumn = thePlayerCol;
        this.myPlayer = thePlayer;
    }

    /**
     * Creates the floor
     * @throws IOException
     */
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

    /**
     * getter for player row
     * @return the player row
     */
    public int getMyPlayerRow(){
        return myPlayerRow;
    }

    /**
     * getter for player column
     * @return the player column
     */
    public int getMyPlayerColumn(){
        return myPlayerColumn;
    }

    /**
     * setter for player row
     * @param thePlayerRow the player row
     */
    public void setMyPlayerRow(final int thePlayerRow){
        this.myPlayerRow = thePlayerRow;
    }

    /**
     * setter for player column
     * @param thePlayerColumn the player column
     */
    public void setMyPlayerColumn(final int thePlayerColumn){
        this.myPlayerColumn = thePlayerColumn;
    }

    /**
     * setter for floor array
     * @param theFloor the floor array
     */
    public void setFloorArray(final TileObject[][] theFloor){
        this.myFloor = theFloor;
    }

    /**
     * getter for floor array
     * @return the floor array
     */
    public TileObject[][] getFloorArray(){
        return myFloor;
    }
}

