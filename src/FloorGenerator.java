/*
 * This class will procedurally generate a floor
 *
 */

public class FloorGenerator {
    private int rooms;
    private char[][] floor;
    private static final int FLOOR_WIDTH = 28; //56
    private static final int FLOOR_HEIGHT = 16; //32

    public FloorGenerator(){
        floor = new char[FLOOR_HEIGHT][FLOOR_WIDTH];
        //Create floor perimeter

        //createPerimeter(0, floor.length,0, floor[0].length, 'W');
        generateRooms();
    }

    private void generateNumberOfRooms(){
        int cols = generateColumns();
        int rows = generateRows();
        int numberOfRooms = rows*cols;
    }
    //Create the initial rooms
    //A room is defined as a rectangle enclosed area of walls
    private void generateRooms() {

        int colsOffset = numberOfRooms / cols;
        int rowsOffset = numberOfRooms / rows;

        //Set the location parameters for rooms
        for(int i = 0; i < numberOfRooms; i++){
            int north = rowsOffset*i - rowsOffset;
            int south = rowsOffset*i;
            int west = colsOffset*i - colsOffset;
            int east = colsOffset*i;

            //Create single room
            createSingleRoom(north, south, west, east);
        }
    }

    private void createSingleRoom(int north, int south, int west, int east){
        int roomCols = generateColumns();
        int roomRows = generateRows();

        createPerimeter(north, south, east, west, 'R');


    }

    //Set entire perimeter to wall tile objects
    //W = wall
    private void createPerimeter(int north, int south, int west, int east, char tile){
        //Create north and south perimeter
        for(int i = north; i < south; i++){
            floor[i][0] = tile;
            floor[i][FLOOR_WIDTH-1] = tile;
        }

        //Create east and west perimeter
        for(int i = east; i < west; i++){
            floor[0][i] = tile;
            floor[FLOOR_HEIGHT-1][i] = tile;
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < floor.length; i++){
            for(int j = 0; j < floor[0].length; j++){
                sb.append(Character.toString(floor[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
