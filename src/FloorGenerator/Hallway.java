package FloorGenerator;

public class Hallway {

    private final int myWidth;
    private final int myHeight;

    public Hallway(final int theMaxHeight, final int theMaxWidth, final int theRoomHeight,
                   final int theRoomWidth){
        myWidth = theMaxWidth - theRoomWidth;
        myHeight = theMaxHeight - theRoomHeight;
    }

    public int getMyWidth() {
        return myWidth;
    }

    public int getMyHeight() {
        return myHeight;
    }
}
