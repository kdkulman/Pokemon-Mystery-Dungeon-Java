package DungeonCharacter.Hero;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class Jirachi extends Hero{
    public Jirachi() throws IOException {
        super("Jirachi", 75, 75,20, 50, 0,
                2, 5);
        setSprite();
    }
    /**
     * Sets the sprite for the Jirachi
     * @throws IOException
     * @throws IllegalArgumentException
     */
    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Hero/Jirachi/Jirachi_Up_Idle.png");
            myUp = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Jirachi/Jirachi_Down_Idle.png");
            myDown = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Jirachi/Jirachi_Left_Idle.png");
            myLeft = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Jirachi/Jirachi_Right_Idle.png");
            myRight = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("JIRACHI file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("JIRACHI input MUST BE NULL");
        }
    }

    /**
     * Jirachi override of special attack method
     * @return string damage
     */
    @Override
    public String specialAttack() {
        String damage = this.getTarget().takeDamage(this.getTarget().getHP() / 2);
        String message = "You did " + damage + " damage to enemy " + this.getTarget().getName() + "!";
        return message;
    }
}