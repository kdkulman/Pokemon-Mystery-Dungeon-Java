import java.util.Random;

public class Room {
    private Hallway rightHallway;
    private final int MIN_ROOM_SIZE = 4;
    private final int maxWidth;
    private final int maxHeight;
    private int height;
    private int width;
    private Random r;

    public Room(final int maxHeight, final int maxWidth){
        this.maxHeight = maxHeight;
        this.maxWidth = maxWidth;
        setFields();
        generateHallway();
        while(!isRoomRatioGood(height, width)){
            height = createRoomHeight(maxHeight);
            width = createRoomWidth(maxWidth);
        }
    }

    private void setFields(){
        r = new Random();
        height = createRoomHeight(maxHeight);
        width = createRoomWidth(maxWidth);
        rightHallway = generateHallway();
    }

    private Hallway generateHallway() {
        Hallway hallway = new Hallway(maxHeight, maxWidth, width, height);
        return hallway;
    }

    private boolean isRoomRatioGood(int height, int width){
        //We want to maintain rooms of a minimum of a 3:2 ratio
        //To prevent funky looking rooms from being created
        if (height == width) return true;
        double max = Math.max(height, width);
        double min = Math.min(height, width);
        if(max/min <= 7) return true;
        return false;
    }

    private int createRoomWidth(final int maxWidth){
        int width = MIN_ROOM_SIZE + r.nextInt(maxWidth);
        if (width >= maxWidth){
            return createRoomWidth(maxWidth);
        }
        return width;
    }

    private int createRoomHeight(final int maxHeight){
        int height = MIN_ROOM_SIZE + r.nextInt(maxHeight);
        if (height >= maxHeight){
            return createRoomHeight(maxHeight);
        }
        return height;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Hallway getRightHallway() {
        return rightHallway;
    }

    @Override //prints out the dimensions of the room
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < width; i++){
            for(int j = 0; j < height; j++){
                sb.append("R");
            }
            sb.append("\n");
        }
        sb.append("\n");
        sb.append("Room size: " + width + " x " + height);
        return sb.toString();
    }
}
