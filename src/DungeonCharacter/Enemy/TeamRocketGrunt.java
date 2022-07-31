package DungeonCharacter.Enemy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TeamRocketGrunt extends Enemy{

    public TeamRocketGrunt() throws IOException {
        super("Team Rocket Grunt", 100, 100, 10,
                10, 0, 10, 5,
                ImageIO.read(new File("./src/DungeonCharacter/TeamRocketGrunt.png")));


    }


}
