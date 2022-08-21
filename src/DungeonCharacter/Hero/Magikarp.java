package DungeonCharacter.Hero;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class Magikarp extends Hero {
    private double MY_BIG_ATTACK_CHANCE;

    /**
     * Constructor for the Magikarp class.
     * @throws IOException
     */
    public Magikarp() throws IOException {
        super("Magikarp", 60, 60,
                0, 1, 0,
                2, 5);
        this.MY_BIG_ATTACK_CHANCE = 0.1;
        setSprite();
    }

    /**
     * Sets the sprite for the Magikarp class.
     * @throws IOException
     * @throws IllegalArgumentException
     */
    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Up_Idle.png");
            myUp = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Down_Idle.png");
            myDown = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Left_Idle.png");
            myLeft = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Right_Idle.png");
            myRight = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("MAGIKARP file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("MAGIKARP input MUST BE NULL");
        }
    }

    /**
     * Magikarp overrides the special attack method
     * @return string damage
     */
    @Override
    public String specialAttack() {
        if(this.MY_BIG_ATTACK_CHANCE > Math.random()) {
            this.getTarget().takeDamage(100000);
            this.MY_BIG_ATTACK_CHANCE += 0.05;
            this.heal(this.getMaxHP());
            return "Your target is defeated! You feel yourself growing stronger!";
        } else {
            return "Your special attack missed!";
        }
    }

    /**
     * myBigAttackChance is a getter for the private variable MY_BIG_ATTACK_CHANCE.
     * @return double MY_BIG_ATTACK_CHANCE
     */
    public double getMyBigAttackChance() {
        return this.MY_BIG_ATTACK_CHANCE;
    }
}
