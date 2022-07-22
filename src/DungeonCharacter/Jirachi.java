package DungeonCharacter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Jirachi extends Hero{
    Jirachi() throws IOException {
        super("Jirachi", 75, 75,20, 50, 0,
                2, 5, ImageIO.read(new File("JirachiPlayer.png")));
    }

    @Override
    public void specialAttack() {
        this.getTarget().takeDamage(this.getTarget().getHP() / 2);
    }
}