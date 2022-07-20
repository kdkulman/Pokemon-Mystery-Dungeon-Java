package DungeonCharacter;

import java.awt.image.BufferedImage;

public class Snorlax extends Hero{
    private int mySleepCounter;
    private BufferedImage mySprite = null;

    //I made it public because he cannot be accessed out of package unless public -Kevin
    public Snorlax() {
        super("Snorlax", 200, 200,15, 20, 0,
                10, 5, null);
        this.mySleepCounter = 0;
    }

    @Override
    public void specialAttack() {
        heal(this.getMaxHP());
        this.setBattleStatus(false);
    }

    public void sleepCounter() {
        if(!this.getBattleStatus()) {
            if(this.mySleepCounter == 2) {
                this.mySleepCounter = 0;
                this.setBattleStatus(true);
            } else {
                this.mySleepCounter++;
            }
        }
    }
}