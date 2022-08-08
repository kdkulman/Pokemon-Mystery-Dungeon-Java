package DungeonCharacter.Hero;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;

public abstract class Hero extends DungeonCharacter {
    private static int myOranBerryCount;
    private static int myVisionSeeds;
    private int myFloorLevel = 1;

    public Hero(final String THE_NAME, final int THE_HP, final int THE_MAX_HP, final int THE_DAMAGE_RANGE, final int THE_ATTACK,
         final int THE_SPECIAL_ATTACK, final int THE_DEFENSE, final int THE_EVASION){

            super(THE_NAME, THE_HP, THE_MAX_HP, THE_DAMAGE_RANGE, THE_ATTACK, THE_SPECIAL_ATTACK, THE_DEFENSE, THE_EVASION);
            this.myOranBerryCount = 0;
            this.myVisionSeeds = 0;
    }

    public BufferedImage getSprite() {
        switch (direction){
            case UP -> {
                return up;
            }
            case DOWN -> {
                return down;
            }
            case LEFT -> {
                return left;
            }
            case RIGHT -> {
                return right;
            }
        }
        return down;
    }

    public String toString() {
        return "P";
    }

    public static void collectOranBerry() {
        myOranBerryCount++;
    }

    public static void collectVisionSeeds() {
        myVisionSeeds++;
    }

    public static int getBerryCount() {
        return myOranBerryCount;
    }

    public static int getSeedCount() {
        return myVisionSeeds;
    }

    public void updateMyCurrentLevel() { this.myFloorLevel++; }

    public int getMyFloorLevel() { return this.myFloorLevel; }
}
