package DungeonCharacter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    public Magikarp() throws IOException {
        super("Magikarp", 60, 60,
                0, 1, 0,
                2, 5,
                ImageIO.read(new File("./src/DungeonCharacter/MagikarpPlayer.png")));
        this.myBigAttackChance = 0.1;
    }


    @Override
    public void specialAttack() {
        if(this.myBigAttackChance < Math.random()) {
            this.getTarget().takeDamage(this.getTarget().getMaxHP());
            this.myBigAttackChance += 0.05;
            this.heal(this.getMaxHP());
        }
    }

    public double getMyBigAttackChance() {
        return this.myBigAttackChance;
    }
}
