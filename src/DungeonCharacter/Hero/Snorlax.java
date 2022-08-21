package DungeonCharacter.Hero;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class Snorlax extends Hero{
    //Snorlax sleep time is not implemented yet. Future iteration?
    private int mySleepCounter;

    //I made it public because he cannot be accessed out of package unless public -Kevin
    public Snorlax() throws IOException {
        super("Snorlax", 200, 200,15, 20, 0,
                10, 5);
        this.mySleepCounter = 1;
        setSprite();
    }

    /**
     * Sets sprite for Snorlax class
     */
    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Hero/Snorlax/Snorlax_Up_Idle.png");
            myUp = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Snorlax/Snorlax_Down_Idle.png");
            myDown = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Snorlax/Snorlax_Left_Idle.png");
            myLeft = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Snorlax/Snorlax_Right_Idle.png");
            myRight = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("SNORLAX file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("SNORLAX input MUST BE NULL");
        }
    }

    /**
     * Snorlax override special attack
     * @return string damage
     */
    @Override
    public String specialAttack() {
        if(mySleepCounter > 0) {
            String heal = heal(this.getMaxHP());
            mySleepCounter--;
            return "Snorlax snoozed and healed for " + heal;
        } else {
            return "Snorlax is no longer tired!";
        }

    }

    /**
     * Snorlax sleep counter. NYI
     */
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

    /**
     * Sleep counter getter. NYI
     * @return mySleepCounter
     */
    public int getSleepCounter() {
        return this.mySleepCounter;
    }
}