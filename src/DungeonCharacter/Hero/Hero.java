package DungeonCharacter.Hero;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;

public abstract class Hero extends DungeonCharacter {
    private int myOranBerryCount;
    private int myVisionSeeds;
    private int myFloorLevel = 1;

    public Hero(final String theName, final int theHP, final int theMaxHP, final int theDamageRange, final int theAttack,
         final int theSpecialAttack, final int theDefense, final int theEvasion){

            super(theName, theHP, theMaxHP, theDamageRange, theAttack, theSpecialAttack, theDefense, theEvasion);
            this.myOranBerryCount = 0;
            this.myVisionSeeds = 0;
    }

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

    public String toString() {
        return "P";
    }

    public void collectOranBerry() {
        myOranBerryCount++;
    }

    public void collectVisionSeeds() {
        myVisionSeeds++;
    }

    public int getBerryCount() {
        return myOranBerryCount;
    }

    public int getSeedCount() {
        return myVisionSeeds;
    }

    public void setBerryCount(final int count) {
        myOranBerryCount = count;
    }

    public void setSeedCount(final int count) {
        myVisionSeeds = count;
    }

    public void updateMyCurrentLevel() { this.myFloorLevel++; }

    public int getMyFloorLevel() { return this.myFloorLevel; }
}
