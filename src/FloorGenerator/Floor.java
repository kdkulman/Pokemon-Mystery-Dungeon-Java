package FloorGenerator;

import TileObjects.TileObject;

import java.io.IOException;

public class Floor {

    private TileObject[][] floor;
    private int playerRow;
    private int playerColumn;
    //private enum playerFaceDirection {UP, DOWN, LEFT, RIGHT}
    //not sure if this should be enum or if we will have time to implement


    public Floor(FloorGenerator floorGenerator) throws IOException {
        this.floor = floorGenerator.getFloor();
        this.playerColumn = floorGenerator.getPlayerColumn();
        this.playerRow = floorGenerator.getPlayerRow();
    }

    public TileObject[][] createFloor() throws IOException {
        //uncomment when FloorTraversal is finished
        FloorGenerator floor = new FloorGenerator();
        playerRow = floor.getPlayerRow();
        playerColumn = floor.getPlayerColumn();
        //FloorTraversal traversal = new FloorTraversal(floor.getFloor());
        //if (traversal.getTraversable == true) return floor.getFloor();
        //return createFloor();
        return floor.getFloor();
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
