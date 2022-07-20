package DungeonCharacter;

import java.awt.image.BufferedImage;

public class Hero extends DungeonCharacter{
    private int myOranBerryCount;
    private int myVisionSeeds;

    Hero(final String THE_NAME, final int THE_HP, final int THE_MAX_HP, final int THE_DAMAGE_RANGE, final int THE_ATTACK,
         final int THE_SPECIAL_ATTACK, final int THE_DEFENSE, final int THE_EVASION,
         final BufferedImage THE_SPRITE){

            super(THE_NAME, THE_HP, THE_MAX_HP, THE_DAMAGE_RANGE, THE_ATTACK, THE_SPECIAL_ATTACK, THE_DEFENSE, THE_EVASION,
                    THE_SPRITE);
            this.myOranBerryCount = 0;
            this.myVisionSeeds = 0;
    }

    public String toString() {
        String result = "";
        result += "Name: " + super.getName() + "\n";
        result += "HP: " + super.getHP() + "\n";
        result += "Oran Berries: " + this.myOranBerryCount;
        result += "Vision Seeds: " + this.myVisionSeeds;
        return result;
    }
}
