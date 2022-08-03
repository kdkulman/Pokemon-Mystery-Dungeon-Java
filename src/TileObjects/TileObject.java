package TileObjects;

import java.awt.image.BufferedImage;

public abstract class TileObject {
    protected BufferedImage sprite;
    private String name;
    private String message;
    private String stringRepresentation;

    public TileObject(final String name, final String stringRepresentation){
        this.name = name;
        this.stringRepresentation = stringRepresentation;
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

    @Override
    public String toString(){
        return stringRepresentation;
    }


}