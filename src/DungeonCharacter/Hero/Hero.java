package DungeonCharacter.Hero;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;

public abstract class Hero extends DungeonCharacter {
    private int myOranBerryCount;
    private int myVisionSeeds;

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
        String result = "";
        result += "Name: " + super.getName() + "\n";
        result += "HP: " + super.getHP() + "\n";
        result += "Oran Berries: " + this.myOranBerryCount + "\n";
        result += "Vision Seeds: " + this.myVisionSeeds;
        return result;
    }

    public void collectOranBerry() {
        this.myOranBerryCount++;
    }

    public void collectVisionSeeds() {
        this.myVisionSeeds++;
    }

    public int getBerryCount() {
        return this.myOranBerryCount;
    }

    public int getSeedCount() {
        return this.myVisionSeeds;
    }
}
