package DungeonCharacter.Enemy;

import DungeonCharacter.DungeonCharacter;

import java.awt.image.BufferedImage;

public class Enemy extends DungeonCharacter {

    public Enemy(final String THE_NAME, final int THE_HP, final int THE_MAX_HP, final int THE_DAMAGE_RANGE, final int THE_ATTACK,
                 final int THE_SPECIAL_ATTACK, final int THE_DEFENSE, final int THE_EVASION,
                 final BufferedImage THE_SPRITE) {

            super(THE_NAME, THE_HP, THE_MAX_HP, THE_DAMAGE_RANGE, THE_ATTACK, THE_SPECIAL_ATTACK, THE_DEFENSE, THE_EVASION,
                    THE_SPRITE);
    }

}
