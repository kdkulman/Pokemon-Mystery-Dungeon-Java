package FloorGenerator;
import DungeonCharacter.Enemy.EnemyFactory;
import DungeonCharacter.Hero.Hero;
import TileObjects.*;
import TileObjects.Items.OranBerry;
import TileObjects.Items.VisionSeed;

import java.io.IOException;
import java.util.Random;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class FloorGenerator {
    private TileObject[][] myFloor;
    private final int FLOOR_WIDTH = 32; //56 IS DEFAULT
    private final int FLOOR_HEIGHT = 32; //32 IS DEFAULT
    private final int MIN_NUMBER_OF_ROOMS_PER_ROW_OR_COLUMN = 2; //2 IS DEFAULT
    private final int MAX_NUMBER_OF_ROOMS_PER_ROW_OR_COLUMN = 6; //6 IS DEFAULT
    private Room[] myRooms;
    private final int myNumberOfRooms;
    private final int myNumberOfRoomsHorizontally;
    private final int myNumberOfRoomsVertically;
    private final int myMaxRoomWidth;
    private final int myMaxRoomHeight;
    private int myPlayerRow;
    private int myPlayerColumn;
    private Hero myPlayer;
    private final Random myRandom;
    private static EnemyFactory myEnemyFactory;

    /**
     * Constructor for objects of class FloorGenerator
     * @param thePlayer The player object
     * @throws IOException
     */
    public FloorGenerator(final Hero thePlayer) throws IOException {
        myEnemyFactory = EnemyFactory.getInstance();
        this.myPlayer = thePlayer;
        myRandom = new Random();
        myFloor = new TileObject[FLOOR_HEIGHT][FLOOR_WIDTH];
        myNumberOfRoomsHorizontally = generateFloorLayout();
        myNumberOfRoomsVertically = generateFloorLayout();
        myNumberOfRooms = generateNumberOfRooms();
        myRooms = new Room[myNumberOfRooms];
        myMaxRoomWidth = (FLOOR_WIDTH / myNumberOfRoomsHorizontally);
        myMaxRoomHeight = (FLOOR_HEIGHT / myNumberOfRoomsVertically);
        createListOfRooms();
        putRoomsInFloor();
        placeEnemies();
        placeItems();
        placeTileObject(new Staircase(), FLOOR_HEIGHT, FLOOR_WIDTH);//place staircase
        placePlayer(thePlayer, FLOOR_HEIGHT, FLOOR_WIDTH);
        fillNullTilesWithWalls();
    }

    /**
     * JUNIT test constructor
     * @param thePlayer The player object
     * @param theFloor The floor object
     * @param theFloorHeight The floor height
     * @param theFloorWidth The floor width
     * @throws IOException
     */
    private FloorGenerator(final Hero thePlayer, final TileObject[][] theFloor, final int theFloorHeight,
                           final int theFloorWidth) throws IOException {
        myEnemyFactory = EnemyFactory.getInstance();
        myPlayer = thePlayer;
        myRandom = new Random();
        myFloor = new TileObject[FLOOR_HEIGHT][FLOOR_WIDTH];
        myNumberOfRoomsHorizontally = generateFloorLayout();
        myNumberOfRoomsVertically = generateFloorLayout();
        myNumberOfRooms = generateNumberOfRooms();
        myRooms = new Room[myNumberOfRooms];
        myMaxRoomWidth = (FLOOR_WIDTH / myNumberOfRoomsHorizontally);
        myMaxRoomHeight = (FLOOR_HEIGHT / myNumberOfRoomsVertically);
        createListOfRooms();
        putRoomsInFloor();
        placeEnemies();
        placeItems();
        placeTileObject(new Staircase(), FLOOR_HEIGHT, FLOOR_WIDTH);//place staircase
        placePlayer(myPlayer, FLOOR_HEIGHT, FLOOR_WIDTH);
        fillNullTilesWithWalls();
    }

    /**
     * method that places player in viable floor position
     * @param thePlayer The player object
     * @param theHeight The floor height
     * @param theWidth The floor width
     * @throws IOException
     */
    private void placePlayer(final Hero thePlayer, final int theHeight, final int theWidth) throws IOException {
        int row = myRandom.nextInt(theHeight);
        int column = myRandom.nextInt(theWidth);
        if (myFloor[row][column] instanceof Texture){
            myFloor[row][column] = thePlayer;
            myPlayerRow = row;
            myPlayerColumn = column;
        } else {
            placePlayer(thePlayer, theHeight, theWidth);
        }
    }

    /**
     * method for random floor layout
     * @return random number
     */
    private int generateFloorLayout(){
        int range = MAX_NUMBER_OF_ROOMS_PER_ROW_OR_COLUMN - MIN_NUMBER_OF_ROOMS_PER_ROW_OR_COLUMN;
        if (range < 1) range = 1;
        return MIN_NUMBER_OF_ROOMS_PER_ROW_OR_COLUMN + myRandom.nextInt(range);
    }

    /**
     * method for random number of rooms
     * @return int room quantity
     */
    private int generateNumberOfRooms(){
        return myNumberOfRoomsHorizontally * myNumberOfRoomsVertically;
    }

    /**
     * method for creating list of rooms
     */
    private void createListOfRooms() {
        for(int i = 0; i < myNumberOfRooms; i++) {
            myRooms[i] = new Room(myMaxRoomHeight, myMaxRoomWidth);
        }
    }

    /**
     * method for placing rooms in floor
     * @throws IOException
     */
    private void putRoomsInFloor() throws IOException {
        int roomCounter = 0;
        int isStaircasePlaced = 1 + myRandom.nextInt(myNumberOfRooms);
        for(int i = 0; i < myNumberOfRoomsVertically; i++) {
            for (int j = 0; j < myNumberOfRoomsHorizontally; j++) {
                Room room = myRooms[roomCounter];
                Hallway hallway = room.getMyHallway();
                int roomHeight = room.getMyHeight();
                int roomWidth = room.getMyWidth();
                placeHorizontalTileObjects((myMaxRoomWidth *j), (myMaxRoomWidth *j)+roomWidth, myMaxRoomHeight * i, new Wall());
                placeHorizontalTileObjects((myMaxRoomWidth *j), (myMaxRoomWidth *j)+roomWidth, (myMaxRoomHeight * i) + roomHeight-1, new Wall());
                placeVerticalTileObjects((myMaxRoomHeight *i), (myMaxRoomHeight *i)+roomHeight, myMaxRoomWidth * j, new Wall());
                placeVerticalTileObjects((myMaxRoomHeight *i), (myMaxRoomHeight *i)+roomHeight, (myMaxRoomWidth * j) + roomWidth-1, new Wall());
                placeTexturesInRoom((myMaxRoomWidth *j)+1, (myMaxRoomWidth *j)+roomWidth-1, myMaxRoomHeight * i + 1,
                        (myMaxRoomHeight * i) + roomHeight - 1);
                placeHallways(roomWidth, roomHeight, hallway.getMyWidth(), hallway.getMyHeight(), i, j);
                roomCounter++;
            }
        }
    }

    /**
     * method for placing hallways in floor; switch is used to facilitate random chance of a hallway spawning
     * @param theRoomWidth The room width
     * @param theRoomHeight The room height
     * @param theHallwayWidth The hallway width
     * @param theHallwayHeight The hallway height
     * @param theI The row
     * @param theJ The column
     * @throws IOException
     */
    private void placeHallways(final int theRoomWidth, final int theRoomHeight, final int theHallwayWidth,
                               final int theHallwayHeight, final int theI, final int theJ) throws IOException {
        int chanceOfHallway = myRandom.nextInt(5); //0-4
        switch (chanceOfHallway){
            case 1: chanceOfHallway = 0;
                placeEastHallway((myMaxRoomWidth * theJ) + theRoomWidth,
                        myMaxRoomWidth * theJ + theRoomWidth + theHallwayWidth, (myMaxRoomHeight * theI) + (theRoomHeight / 2),
                        new Wall());
                break;
            case 2: chanceOfHallway = 1;
                placeSouthHallway((myMaxRoomHeight * theI) + theRoomHeight,
                        myMaxRoomHeight * theI + theRoomHeight + theHallwayHeight, (myMaxRoomWidth * theJ) + (theRoomWidth / 2),
                        new Wall());
                break;
            default:
                placeEastHallway((myMaxRoomWidth * theJ) + theRoomWidth,
                        myMaxRoomWidth * theJ + theRoomWidth + theHallwayWidth, (myMaxRoomHeight * theI) + (theRoomHeight / 2),
                        new Wall());
                placeSouthHallway((myMaxRoomHeight * theI) + theRoomHeight,
                        myMaxRoomHeight * theI + theRoomHeight + theHallwayHeight, (myMaxRoomWidth * theJ) + (theRoomWidth / 2),
                        new Wall());
                break;
        }
    }

    /**
     * method for placing textures in room
     * @param theTile The tile
     * @param theHeight The height
     * @param theWidth The width
     */
    private void placeTileObject(final TileObject theTile, final int theHeight, final int theWidth){
        int row = myRandom.nextInt(theHeight);
        int column = myRandom.nextInt(theWidth);
        if (myFloor[row][column] instanceof Texture) {
            myFloor[row][column] = theTile;
        } else {
            placeTileObject(theTile, theHeight, theWidth);
        }
    }

    /**
     * method for placing enemies in room
     */
    private void placeEnemies() {
        int enemiesToCreate = myRandom.nextInt(8);
        for(int i = 0; i < enemiesToCreate; i++){
            TileObject enemy = EnemyFactory.createEnemy();
            placeTileObject(enemy, FLOOR_HEIGHT, FLOOR_WIDTH);
        }
    }

    /**
     * method for placing items in room
     */
    private void placeItems(){
        int berriesToCreate = myRandom.nextInt(3);
        for(int i = 0; i < berriesToCreate; i++){
            TileObject oranBerry = new OranBerry();
            placeTileObject(oranBerry, FLOOR_HEIGHT, FLOOR_WIDTH);
        }
        int seedsToCreate = myRandom.nextInt(1);
        for(int i = 0; i < seedsToCreate; i++){
            TileObject seed = new VisionSeed();
            placeTileObject(seed, FLOOR_HEIGHT, FLOOR_WIDTH);
        }
    }

    /**
     * ensuring specific hallway locations
     * @param theStart The start
     * @param theEnd The end
     * @param theColumn The column
     * @param theTile The tile
     * @throws IOException
     */
    private void placeSouthHallway(final int theStart, final int theEnd, final int theColumn,
                                   final TileObject theTile) throws IOException {
        if(theEnd < FLOOR_HEIGHT) placeVerticalTileObjects(theStart, theEnd-1, theColumn-1, theTile);
        if(theEnd < FLOOR_HEIGHT) placeVerticalTileObjects(theStart, theEnd, theColumn+1, theTile);
        if(theEnd >= FLOOR_HEIGHT){
            placeVerticalTileObjects(theStart-1, theEnd-2, theColumn, new Texture()); //place textures
            placeVerticalTileObjects(theEnd-2, theEnd-1, theColumn, new Wall());

        } else {
            placeVerticalTileObjects(theStart-1, theEnd+1, theColumn, new Texture()); //place textures
        }
    }

    /**
     * ensuring specific hallway locations
     * @param theStart The start
     * @param theEnd The end
     * @param theRow The row
     * @param theTile The tile
     * @throws IOException
     */
    private void placeEastHallway(final int theStart, final int theEnd, final int theRow,
                                  final TileObject theTile) throws IOException {
        placeHorizontalTileObjects(theStart, theEnd, theRow-1, theTile);
        placeHorizontalTileObjects(theStart, theEnd, theRow+1, theTile);
        if(theEnd >= FLOOR_WIDTH){
            placeHorizontalTileObjects(theStart-1, theEnd-2, theRow, new Texture()); //place textures
            placeHorizontalTileObjects(theEnd-2, theEnd-1, theRow, new Wall()); //place wall

        } else {
            placeHorizontalTileObjects(theStart-1, theEnd+1, theRow, new Texture()); //place textures
        }
    }

    /**
     * Fills room with textures
     * @param theStart The start
     * @param theEnd The end
     * @param theTopRow The top row
     * @param theBottomRow The bottom row
     * @throws IOException
     */
    private void placeTexturesInRoom(final int theStart, final int theEnd, final int theTopRow,
                                     final int theBottomRow) throws IOException {
        for (int i = theTopRow; i < theBottomRow; i++){
            placeHorizontalTileObjects(theStart, theEnd, i, new Texture());
        }
    }

    /**
     *
     * @param theStart
     * @param theEnd
     * @param theRow
     * @param theTile
     * @throws IOException
     */
    private void placeHorizontalTileObjects(final int theStart, final int theEnd, final int theRow,
                                            final TileObject theTile) throws IOException {
        int newEnd = 0;
        if (theEnd >= FLOOR_WIDTH){
            newEnd = FLOOR_WIDTH -1;
        } else {
            newEnd = theEnd;
        }
        for(int i = theStart; i < newEnd; i++){
            TileObject newTile = tileObjectFactory(theTile);
            if( !(myFloor[theRow][i] instanceof Texture) ) myFloor[theRow][i] = newTile;
        }
    }

    /**
     * Tile object factory
     * @param theTile The tile
     * @return The tile object to be placed
     * @throws IOException
     */
    private TileObject tileObjectFactory(final TileObject theTile) throws IOException {
        if (theTile instanceof Texture) return new Texture();
        if (theTile instanceof Wall) return new Wall();
        return new Wall();
    }

    private void placeVerticalTileObjects(final int theStart, final int theEnd, final int theColumn,
                                          final TileObject theTile) throws IOException {
        int newEnd = 0;
        if (theEnd >= FLOOR_HEIGHT){
            newEnd = FLOOR_HEIGHT -1;
        } else {
            newEnd = theEnd;
        }
        for(int i = theStart; i < newEnd; i++){
            TileObject newTile = tileObjectFactory(theTile);
            if( !(myFloor[i][theColumn] instanceof Texture) ) myFloor[i][theColumn] = newTile;
        }
    }

    /**
     * Fills null tiles with wall textures
     */
    private void fillNullTilesWithWalls(){
        for(int i = 0; i < myFloor.length; i++){
            for (int j = 0; j < myFloor[0].length; j++){
                if(myFloor[i][j] == null) myFloor[i][j] = new Wall();
            }
        }
    }

    /**
     * getter for current floor tileobject array
     * @return the floor
     */
    public TileObject[][] getMyFloor(){
        return myFloor;
    }

    /**
     * setter for current floor tileobject array
     * @param theFloor the floor to set
     */
    public void setMyFloor(final TileObject[][] theFloor) {
        myFloor = theFloor;
    }

    /**
     * toString method Override
     * @return the string representation of the floor
     */
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < myFloor.length; i++){
            for(int j = 0; j < myFloor[0].length; j++){
                sb.append(myFloor[i][j].toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }
    public String debugToString(){
        StringBuilder sb = new StringBuilder();
        sb.append(toString());
        sb.append("\n");
        sb.append("Floor Layout size: " + myNumberOfRoomsHorizontally + " x " + myNumberOfRoomsVertically);
        sb.append("\n");
        sb.append("Number of Rooms: " + myNumberOfRooms);
        sb.append("\n");
        sb.append("Max Room Height: " + myMaxRoomHeight);
        sb.append("\n");
        sb.append("Max Room Width: " + myMaxRoomWidth);
        return sb.toString();
    }

    /**
     * getter for player row
     * @return the player row
     */
    public int getMyPlayerRow() {
        return myPlayerRow;
    }

    /**
     * getter for player column
     * @return the player column
     */
    public int getMyPlayerColumn() {
        return myPlayerColumn;
    }

    /**
     *
     * @param theHero
     * @param theHeight
     * @param theWidth
     * @throws IOException
     */
    public void placePlayerTest(final Hero theHero, final int theHeight, final int theWidth) throws IOException {
        placePlayer(myPlayer, theHeight, theWidth);
    }

    /**
     *
     * @param theTile
     * @param theHeight
     * @param theWidth
     * @throws IOException
     */
    public void placeTileObjectTest(final TileObject theTile, final int theHeight, final int theWidth) throws IOException {
        placeTileObject(theTile, theHeight, theWidth);
    }

}