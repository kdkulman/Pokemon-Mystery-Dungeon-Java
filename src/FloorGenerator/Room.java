package FloorGenerator;

import java.util.Random;

public class Room {
    private Hallway myHallway;
    private final int MIN_ROOM_WIDTH = 4;
    private final int MIN_ROOM_HEIGHT = 4;
    private final int myMaxWidth;
    private final int myMaxHeight;
    private int myHeight;
    private int myWidth;
    private Random myRandom;

    public Room(final int theMaxHeight, final int theMaxWidth){
        this.myMaxHeight = theMaxHeight;
        this.myMaxWidth = theMaxWidth;
        this.myRandom = new Random();
        this.myHeight = createRoomHeight(theMaxHeight);
        this.myWidth = createRoomWidth(theMaxWidth);
        this.myHallway = generateHallway();
        generateHallway();
        while(isRoomRatioAtLeast3By2(myHeight, myWidth) == false){
            myHeight = createRoomHeight(theMaxHeight);
            myWidth = createRoomWidth(theMaxWidth);
        }
    }

    private Hallway generateHallway() {
        Hallway hallway = new Hallway(myMaxHeight, myMaxWidth, myHeight, myWidth);
        return hallway;
    }

    private boolean isRoomRatioAtLeast3By2(final int theHeight, final int theWidth){
        //We want to maintain rooms of a minimum of a 3:2 ratio
        //To prevent funky (weird long) looking rooms from being created
        if (theHeight == theWidth) return true;
        double max = Math.max(theHeight, theWidth);
        double min = Math.min(theHeight, theWidth);
        if(max/min <= 3) return true;
        return false;
    }

    private int createRoomWidth(final int theMaxWidth){
        int width = MIN_ROOM_WIDTH + myRandom.nextInt(theMaxWidth);
        if (width >= theMaxWidth){
            return createRoomWidth(theMaxWidth);
        }
        return width;
    }

    private int createRoomHeight(final int theMaxHeight){
        int height = MIN_ROOM_HEIGHT + myRandom.nextInt(theMaxHeight);
        if (height >= theMaxHeight){
            return createRoomHeight(theMaxHeight);
        }
        return height;
    }

    @Override //prints out the dimensions of the room
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < myWidth; i++){
            for(int j = 0; j < myHeight; j++){
                sb.append("R");
            }
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("FloorGenerator.Room size: " + myWidth + " x " + myHeight);
        return sb.toString();
    }

    public int getMyHeight() {
        return myHeight;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public Hallway getMyHallway() {
        return myHallway;
    }

    //Getter for Junit Test
    public boolean testIsRoomRatioAtLeast3By2(final int theHeight, final int theWidth){
        return isRoomRatioAtLeast3By2(theHeight, theWidth);
    }

    //Getter for Junit Test
    public int testCreateRoomHeight(final int theMaxHeight){
        return createRoomHeight(theMaxHeight);
    }
    //Getter for Junit Test
    public int getMIN_ROOM_HEIGHT(){
        return MIN_ROOM_HEIGHT;
    }

    //Getter for Junit Test
    public int testCreateRoomWidth(final int theMaxWidth){
        return createRoomWidth(theMaxWidth);
    }
    //Getter for Junit Test
    public int getMIN_ROOM_WIDTH(){
        return MIN_ROOM_WIDTH;
    }
}
