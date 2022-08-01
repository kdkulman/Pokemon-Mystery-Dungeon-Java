package DungeonCharacter.Enemy;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class DonkeyKong extends Enemy{

        public DonkeyKong() throws IOException {
            super("Donkey Kong", 100, 100, 10,
                    10, 0, 10, 5,
                    ImageIO.read(new File("./src/DungeonCharacter/DonkeyKong.png")));


        }
}
