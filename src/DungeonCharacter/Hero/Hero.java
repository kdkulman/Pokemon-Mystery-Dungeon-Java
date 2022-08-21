package DungeonCharacter.Hero;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public abstract class Hero extends DungeonCharacter {
    private int myOranBerryCount;
    private int myVisionSeeds;
    private int myFloorLevel = 1;

    /**
     * Constructor for the Hero class.
     * @param theName
     * @param theHP
     * @param theMaxHP
     * @param theDamageRange
     * @param theAttack
     * @param theSpecialAttack
     * @param theDefense
     * @param theEvasion
     */
    public Hero(final String theName, final int theHP, final int theMaxHP, final int theDamageRange, final int theAttack,
         final int theSpecialAttack, final int theDefense, final int theEvasion){

            super(theName, theHP, theMaxHP, theDamageRange, theAttack, theSpecialAttack, theDefense, theEvasion);
            this.myOranBerryCount = 0;
            this.myVisionSeeds = 0;
    }

    /**
     * Sprite image getter based on character direction.
     * @return sprite image
     */
    public BufferedImage getMySprite() {
        switch (this.getMyDirection()){
            case UP -> {
                return myUp;
            }
            case DOWN -> {
                return myDown;
            }
            case LEFT -> {
                return myLeft;
            }
            case RIGHT -> {
                return myRight;
            }
        }
        return myDown;
    }

    /**
     *
     * @return hero's string value
     */
    public String toString() {
        return "P";
    }

    /**
     * Collect oran berry method
     */
    public void collectOranBerry() {
        myOranBerryCount++;
    }

    /**
     * Collect vision seeds method
     */
    public void collectVisionSeeds() {
        myVisionSeeds++;
    }

    /**
     * @return oran berry count
     */
    public int getBerryCount() {
        return myOranBerryCount;
    }

    /**
     *
     * @return vision seeds count
     */
    public int getSeedCount() {
        return myVisionSeeds;
    }

    /**
     * Oran berry count setter
     * @param count
     */
    public void setBerryCount(final int count) {
        myOranBerryCount = count;
    }
    /**
     * Vision seeds count setter
     * @param count
     */
    public void setSeedCount(final int count) {
        myVisionSeeds = count;
    }
    /**
     * Floor level incrementer
     */
    public void updateMyCurrentLevel() { this.myFloorLevel++; }
    /**
     * Floor level getter
     * @return floor level
     */
    public int getMyFloorLevel() { return this.myFloorLevel; }
}
