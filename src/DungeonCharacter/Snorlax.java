package DungeonCharacter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Snorlax extends Hero{
    private int mySleepCounter;

    //I made it public because he cannot be accessed out of package unless public -Kevin
    public Snorlax() throws IOException {
        super("Snorlax", 200, 200,15, 20, 0,
                10, 5, ImageIO.read(new File("./src/DungeonCharacter/SnorlaxPlayer.png")));
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

    public int getSleepCounter() {
        return this.mySleepCounter;
    }
}