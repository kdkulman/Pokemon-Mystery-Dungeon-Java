package FloorGenerator;

import TileObjects.TileObject;

import java.io.IOException;

public class Floor {

    private TileObject[][] floor;
    private int playerRow;
    private int playerColumn;
    private int playerFaceDirection;

    public Floor() throws IOException {
        this.floor = getFloor();
    }

    public TileObject[][] getFloor() throws IOException {
        //uncomment when FloorTraversal is finished
        FloorGenerator floor = new FloorGenerator();
        playerRow = floor.getPlayerRow();
        playerColumn = floor.getPlayerColumn();
        //FloorTraversal traversal = new FloorTraversal(floor.getFloor());
        //if (traversal.getTraversable == true) return floor.getFloor();
        //return getFloor();
        return floor.getFloor();
    }


}
