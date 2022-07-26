package DungeonCharacter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    public Magikarp() throws IOException {
        super("Magikarp", 60, 60,0, 1, 0,
                2, 5, ImageIO.read(new File("MagikarpPlayer.png")));
        this.myBigAttackChance = 0.1;
    }


    @Override
    public void specialAttack() {
        if(Math.random() < this.myBigAttackChance) {
            this.getTarget().takeDamage(this.getTarget().getHP());
            if(this.myBigAttackChance < 1) {
                this.myBigAttackChance += 0.05;
            }
            this.heal(this.getMaxHP());
        }
    }
}
