package TileObjects;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class TileObject implements Serializable{
    private boolean isSolid;
    private String name;
    private String message;
    private String stringRepresentation;
    private boolean isVisibleOnMiniMap;
    private Direction myDirection;
    protected BufferedImage sprite, up, down, left, right;
    private boolean myEnemyStatus;

    public boolean getSolid() {
        return isSolid;
    }

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public TileObject(final String name, final String stringRepresentation, final boolean isSolid){
        this.name = name;
        this.stringRepresentation = stringRepresentation;
        this.isSolid = isSolid;
        this.myDirection = Direction.DOWN;
        this.isVisibleOnMiniMap = false;
        this.myEnemyStatus = false;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    abstract protected void setSprite();

    //Methods for custom serialization of TileObjects, allows Textures to reload
    private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException 
    {   
        isSolid = ois.readBoolean();
        name = ois.readUTF();
        stringRepresentation = ois.readUTF();
        isVisibleOnMiniMap = ois.readBoolean();
        myDirection = Direction.values()[ois.readInt()];
        setSprite();
    }
   
    private void writeObject(ObjectOutputStream oos) throws IOException 
    {
        oos.writeBoolean(isSolid);
        oos.writeUTF(name);
        oos.writeUTF(stringRepresentation);
        oos.writeBoolean(isVisibleOnMiniMap);
        oos.writeInt(myDirection.ordinal());
    }

    public void setMyEnemyStatus(final boolean theStatus) {
        this.myEnemyStatus = theStatus;
    }

    public boolean getMyEnemyStatus() {
        return this.myEnemyStatus;
    }

    public void setDirection(final Direction direction){
        if(this instanceof DungeonCharacter) this.myDirection = direction;
    }

    public Direction getMyDirection() {
        return this.myDirection;
    }

    public String getName() {
        return name;
    }

    public String getMessage() {
        return message;
    }

    public boolean getIsVisibleOnDungeonMap(){
        return isVisibleOnMiniMap;
    }
    public void setVisibleOnMiniMap(){
        this.isVisibleOnMiniMap = true;
    }

    @Override
    public String toString(){
        return stringRepresentation;
    }
}