/*
 * This class will procedurally generate a floor
 *
 */

import java.util.Random;

public class FloorGenerator {
    private int[] floorLayout; //example: [2, 4] means 2 rooms by 4 rooms setup
    private final int numberOfRooms;
    private final int FLOOR_WIDTH = 28; //56
    private final int FLOOR_HEIGHT = 16; //32
//    private final int FLOOR_WIDTH = 56; //56
//    private final int FLOOR_HEIGHT = 32; //32
    private final Random r;
    private char[][] floor;

    public FloorGenerator(){
        r = new Random();
        floor = new char[FLOOR_HEIGHT][FLOOR_WIDTH];
        floorLayout = generateFloorLayout();
        numberOfRooms = generateNumberOfRooms();
        roomGenerator();
    }

    private int[] generateFloorLayout(){
        floorLayout = new int[2];
        floorLayout[0] = 2 + r.nextInt(4);
        floorLayout[1] = 2 + r.nextInt(4);
        return floorLayout;
    }

    private int generateNumberOfRooms(){
        return floorLayout[0] * floorLayout[1];
    }

    //Create the initial rooms
    //A room is defined as a rectangle enclosed area of walls
    private void roomGenerator() {
        int maxRoomWidth = FLOOR_WIDTH/floorLayout[0];
        int maxRoomHeight = FLOOR_HEIGHT/floorLayout[1];

        //Set the location parameters for rooms
        //east and west iterate height
        //north and south iterate width
        for(int i = 0; i < floorLayout[1]; i++){ //columns/height
            int roomHeight = createRoomHeight(maxRoomHeight);
            int westWall = maxRoomHeight * i;
            int eastWall = maxRoomHeight * i + maxRoomHeight;
            for(int j = 0; j < floorLayout[0]; j++) { //rows/width
                int roomWidth = createRoomWidth(maxRoomWidth);
                int northWall = maxRoomWidth * j;
                int southWall = maxRoomWidth * j + maxRoomWidth;
                createRoomWalls(northWall, southWall, westWall, eastWall, 'W', roomHeight, roomWidth);
            }
        }
    }

    private int createRoomWidth(int maxRoomWidth){
        return Math.min(maxRoomWidth, 3 + r.nextInt(maxRoomWidth));
    }

    private int createRoomHeight(int maxRoomHeight){
        return Math.min(maxRoomHeight, 3 + r.nextInt(maxRoomHeight));
    }

    private void createRoomWalls(int northWall, int southWall, int westWall, int eastWall, char tile, int roomHeight, int roomWidth){
       //Make Left and Right Walls
        for(int i = northWall; i < southWall; i++){
            floor[0][i] = tile;
            floor[eastWall - 1][i] = tile;
        }
        //tell program to bring the bottom wall up 1 element
        //Make Top and Bottom Walls
        for(int j = westWall; j < eastWall; j++){
            floor[j][0] = tile;
            floor[j][southWall-1] = tile;
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

    public String debugToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        sb.append("\n");
        sb.append("Floor Layout size: " + floorLayout[0] + " x " + floorLayout[1]);
        sb.append("\n");
        sb.append("Number of Rooms: " + numberOfRooms);
        return sb.toString();
    }
}