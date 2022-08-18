package TileObjects;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class TileObject implements Serializable {
    private boolean mySolidTest;
    private String myName;
    private String myMessage;
    private String myStringRepresentation;
    private boolean myVisibleOnMiniMapTest;
    private Direction myDirection;
    protected BufferedImage mySprite, myUp, myDown, myLeft, myRight;
    private boolean myEnemyStatus;

    public boolean getMySolidTest() {
        return mySolidTest;
    }

    public enum Direction{
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public TileObject(final String theName, final String myStringRepresentation, final boolean mySolidTest){
        this.myName = theName;
        this.myStringRepresentation = myStringRepresentation;
        this.mySolidTest = mySolidTest;
        this.myDirection = Direction.DOWN;
        this.myVisibleOnMiniMapTest = false;
        this.myEnemyStatus = false;
    }

    public BufferedImage getMySprite() {
        return mySprite;
    }

    abstract protected void setSprite();

    //Methods for custom serialization of TileObjects, allows Textures to reload
    private void readObject(final ObjectInputStream theOIS) throws ClassNotFoundException, IOException {
        mySolidTest = theOIS.readBoolean();
        myName  = theOIS.readUTF();
        myStringRepresentation = theOIS.readUTF();
        myVisibleOnMiniMapTest = theOIS.readBoolean();
        myDirection = Direction.values()[theOIS.readInt()];
        setSprite();
    }

    private void writeObject(final ObjectOutputStream theOOS) throws IOException {
        theOOS.writeBoolean(mySolidTest);
        theOOS.writeUTF(myName);
        theOOS.writeUTF(myStringRepresentation);
        theOOS.writeBoolean(myVisibleOnMiniMapTest);
        theOOS.writeInt(myDirection.ordinal());
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
        return myName;
    }

    public String getMyMessage() {
        return myMessage;
    }

    public boolean getIsVisibleOnDungeonMap(){
        return myVisibleOnMiniMapTest;
    }
    public void setVisibleOnMiniMap(){
        this.myVisibleOnMiniMapTest = true;
    }

    @Override
    public String toString(){
        return myStringRepresentation;
    }
}