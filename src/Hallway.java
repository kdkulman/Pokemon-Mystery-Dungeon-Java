public class Hallway {

    private final int width;
    private final int height;

    public Hallway(final int maxHeight, final int maxWidth, final int roomWidth, final int roomHeight){
        width = maxWidth - roomWidth;
        height = maxHeight - roomHeight; //unverfied formula
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
