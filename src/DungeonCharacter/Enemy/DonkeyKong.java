package DungeonCharacter.Enemy;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class DonkeyKong extends Enemy{

    /**
     * Constructor for DonkeyKong
     */
    public DonkeyKong() throws IOException {
        super("Donkey Kong");
        setSprite();
    }

    /**
     * Sets the sprite for Donkey Kong
     */
    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Enemy/DonkeyKong/DonkeyKong_Down_Idle.png");
            mySprite = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("DONKEY KONG file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("DONKEY KONG input MUST BE NULL");
        }
    }

}
