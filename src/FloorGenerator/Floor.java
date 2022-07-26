package FloorGenerator;

import TileObjects.TileObject;

import java.io.IOException;

public class Floor {

    private TileObject[][] floor;

    public Floor() throws IOException {
        FloorGenerator floor = new FloorGenerator();
        this.floor = floor.getFloor();
        //uncomment when FloorTraversal is finished

        //FloorTraversal traversal = new FloorTraversal(floor.getFloor());
        //if (traversal.getTraversable == true) return floor.getFloor();
        //return createFloor();
    }

    public TileObject[][] getFloor(){
        return floor;
    }


}
