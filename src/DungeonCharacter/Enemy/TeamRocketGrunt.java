package DungeonCharacter.Enemy;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;
/**
 * @author Stephen VanLuven, Kevin Kulman, and Anthony Owens
 * @Version 1.0
 */
public class TeamRocketGrunt extends Enemy{
    /**
     * Constructor for the TeamRocketGrunt class
     */
    public TeamRocketGrunt() throws IOException {
        super("Team Rocket Grunt");
        setSprite();
    }
    /**
     * Sets the sprite for the TeamRocketGrunt class
     */
    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Enemy/TeamRocketGrunt/TeamRocketGrunt_Down_Idle.png");
            mySprite = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("TEAM ROCKET GRUNT file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("TEAM ROCKET GRUNT input MUST BE NULL");
        }
    }

}
