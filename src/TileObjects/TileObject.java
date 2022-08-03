package TileObjects;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;

public abstract class TileObject {
    private String name;
    private String message;
    private String stringRepresentation;
    protected Direction direction;
    protected BufferedImage sprite, up, down, left, right;

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public TileObject(final String name, final String stringRepresentation){
        this.name = name;
        this.stringRepresentation = stringRepresentation;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setDirection(final Direction direction){
        if(this instanceof DungeonCharacter) this.direction = direction;
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