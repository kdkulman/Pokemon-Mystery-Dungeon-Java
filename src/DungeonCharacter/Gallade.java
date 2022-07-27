package DungeonCharacter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Gallade extends Hero{
    private final double SELF_INFLICTION = 12;

    public Gallade() throws IOException {
        super("Gallade", 100, 100,30, 20, 30,
                3, 15, ImageIO.read(new File("./src/DungeonCharacter/GalladePlayer.png")));
    }

    @Override
    public void specialAttack() {
        this.getTarget().takeDamage(this.getMySpecialAttack());
        this.takeDamage((int)SELF_INFLICTION);
    }
}
