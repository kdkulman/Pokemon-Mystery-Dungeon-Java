package FloorGenerator;/*
 * This class will procedurally generate a floor
 *
 */

import java.util.Random;

public class FloorGenerator {
    //UNCOMMENT ONCE TILOBJECT IS MADE
    //private TileObject[][] floor
    private char[][] floor; //where the magic happens
    private final int FLOOR_WIDTH = 56; //56
    private final int FLOOR_HEIGHT = 32; //32
    private Room[] rooms;
    private final int numberOfRooms;
    private final int numberOfRoomsHorizontally;
    private final int numberOfRoomsVertically;
    private final int maxRoomWidth;
    private final int maxRoomHeight;
    private final Random r;

    public FloorGenerator(){
        r = new Random();
        floor = new char[FLOOR_HEIGHT][FLOOR_WIDTH];
        numberOfRoomsHorizontally = generateFloorLayout();
        numberOfRoomsVertically = generateFloorLayout();
        numberOfRooms = generateNumberOfRooms();
        rooms = new Room[numberOfRooms];
        maxRoomWidth = (FLOOR_WIDTH / numberOfRoomsHorizontally);
        maxRoomHeight = (FLOOR_HEIGHT / numberOfRoomsVertically);
        createListOfRooms();
        putRoomsInFloor();
        placeTileObject(r.nextInt(5), 'i'); //place items
        placeTileObject(r.nextInt(8), 'e');//place enemies
        placeTileObject(r.nextInt(5), 't');//place trap
        placeTileObject(1, 's');//place staircase
        placeTileObject(1, 'p');//place player
        fillRestOfFloorWithWalls();
    }

    private int generateFloorLayout(){
        return 2 + r.nextInt(4);
    }

    private int generateNumberOfRooms(){
        return numberOfRoomsHorizontally * numberOfRoomsVertically;
    }

    private void createListOfRooms() {
        for(int i = 0; i < numberOfRooms; i++) {
            rooms[i] = new Room(maxRoomHeight, maxRoomWidth);
        }
    }

    private void putRoomsInFloor(){
        int roomCounter = 0;
        int isStaircasePlaced = 1 + r.nextInt(numberOfRooms);
        for(int i = 0; i < numberOfRoomsVertically; i++) { //columns/height
            for (int j = 0; j < numberOfRoomsHorizontally; j++) {
                Room room = rooms[roomCounter];
                Hallway hallway = room.getRightHallway();
                int roomHeight = room.getHeight();
                int roomWidth = room.getWidth();
                placeHorizontalTileObjects((maxRoomWidth*j), (maxRoomWidth*j)+roomWidth, maxRoomHeight * i, 'W');
                placeHorizontalTileObjects((maxRoomWidth*j), (maxRoomWidth*j)+roomWidth, (maxRoomHeight * i) + roomHeight-1, 'W');
                placeVerticalTileObjects((maxRoomHeight*i), (maxRoomHeight*i)+roomHeight, maxRoomWidth * j, 'W');
                placeVerticalTileObjects((maxRoomHeight*i), (maxRoomHeight*i)+roomHeight, (maxRoomWidth * j) + roomWidth-1, 'W');
                placeTexturesInRoom((maxRoomWidth*j)+1, (maxRoomWidth*j)+roomWidth-1, maxRoomHeight * i + 1,
                        (maxRoomHeight * i) + roomHeight - 1, 'T');
                placeHallways(roomWidth, roomHeight, hallway.getWidth(), hallway.getHeight(), i, j);
                roomCounter++;
            }
        }
    }

    //Switch is used to facilitate random chance of a hallway spawning
    private void placeHallways(int roomWidth, int roomHeight, int hallwayWidth, int hallwayHeight, int i, int j){
        int chanceOfHallway = r.nextInt(5); //0-4
        switch (chanceOfHallway){
            case 1: chanceOfHallway = 0;
                placeEastHallway((maxRoomWidth * j) + roomWidth,
                        maxRoomWidth * j + roomWidth + hallwayWidth, (maxRoomHeight * i) + (roomHeight / 2),
                        'W');
                break;
            case 2: chanceOfHallway = 1;
                placeSouthHallway((maxRoomHeight * i) + roomHeight,
                        maxRoomHeight * i + roomHeight + hallwayHeight, (maxRoomWidth * j) + (roomWidth / 2),
                        'W');
                break;
            default:
                placeEastHallway((maxRoomWidth * j) + roomWidth,
                        maxRoomWidth * j + roomWidth + hallwayWidth, (maxRoomHeight * i) + (roomHeight / 2),
                        'W');
                placeSouthHallway((maxRoomHeight * i) + roomHeight,
                        maxRoomHeight * i + roomHeight + hallwayHeight, (maxRoomWidth * j) + (roomWidth / 2),
                        'W');
                break;
        }
    }

    private void placeTileObject(final int number, final char c){
        int count = number;
        while(count > 0) {
            int row = r.nextInt(FLOOR_HEIGHT);
            int column = r.nextInt(FLOOR_WIDTH);
            if (floor[row][column] == 'T'){
                floor[row][column] = c;
                count--;
            }
        }
    }

    private void placeSouthHallway(final int start, final int end, final int column, final char c) {
        if(end < FLOOR_HEIGHT) placeVerticalTileObjects(start, end-1, column-1, c);
        if(end < FLOOR_HEIGHT) placeVerticalTileObjects(start, end, column+1, c);
        if(end >= FLOOR_HEIGHT){
            placeVerticalTileObjects(start-1, end-2, column, 'T'); //place textures
            placeVerticalTileObjects(end-2, end-1, column, 'W');

        } else {
            placeVerticalTileObjects(start-1, end+1, column, 'T'); //place textures
        }
    }

    private void placeEastHallway(final int start, final int end, final int row, final char c) {
        placeHorizontalTileObjects(start, end, row-1, c);
        placeHorizontalTileObjects(start, end, row+1, c);
        if(end >= FLOOR_WIDTH){
            placeHorizontalTileObjects(start-1, end-2, row, 'T'); //place textures
            placeHorizontalTileObjects(end-2, end-1, row, 'W'); //place textures

        } else {
            placeHorizontalTileObjects(start-1, end+1, row, 'T'); //place textures
        }
    }

    private void placeTexturesInRoom(final int start, final int end, final int topRow, final int bottomRow, final char c){
        for (int i = topRow; i < bottomRow; i++){
            placeHorizontalTileObjects(start, end, i, c);
        }
    }

    private void placeHorizontalTileObjects(final int start, final int end, final int row, final char c){
        for(int i = start; i < end; i++){
            if(floor[row][i] != 'T') floor[row][i] = c;
        }
    }

    private void placeVerticalTileObjects(final int start, final int end, final int column, final char c){
        for(int i = start; i < end; i++){
            if(floor[i][column] != 'T') floor[i][column] = c;
        }
    }

    private void fillRestOfFloorWithWalls(){
        for(int i = 0; i < floor.length; i++){
            for (int j = 0; j < floor[0].length; j++){
                if(floor[i][j] != 'T' && floor[i][j] != 's' && floor[i][j] != 'i'
                        && floor[i][j] != 'p' && floor[i][j] != 'e' && floor[i][j] != 't'){
                    floor[i][j] = 'W';
                }
            }
        }
    }

    public char[][] getFloor(){
        return floor;
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
        sb.append("Floor Layout size: " + numberOfRoomsHorizontally + " x " + numberOfRoomsVertically);
        sb.append("\n");
        sb.append("Number of Rooms: " + numberOfRooms);
        sb.append("\n");
        sb.append("Max FloorGenerator.Room Height: " + maxRoomHeight);
        sb.append("\n");
        sb.append("Max FloorGenerator.Room Width: " + maxRoomWidth);
        return sb.toString();
    }

}