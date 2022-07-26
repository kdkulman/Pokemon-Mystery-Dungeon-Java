package Item;

import java.awt.image.BufferedImage;

public abstract class TileObject {
    private BufferedImage sprite;
    private String name;
    private String message;

    public TileObject(final String THE_NAME, final BufferedImage THE_SPRITE) {
        this.name = THE_NAME;
        this.sprite = THE_SPRITE;
    }


    public BufferedImage getSprite() {
        return sprite;
    }


    public String getName() {
        return name;
    }


    public String getMessage() {
        return message;
    }


}