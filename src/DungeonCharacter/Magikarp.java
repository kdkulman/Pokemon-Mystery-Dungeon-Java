package DungeonCharacter;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    public Magikarp() throws IOException {
        super();
        this.myHP = 60;
        this.myAttack = 1;
        this.myDefense = 1;
        this.myEvasion = 0.05;
        this.myBigAttackChance = 0.1;
        setSprite();
    }

    @Override
    public void specialAttack() {
        if(Math.random() < this.myBigAttackChance) {
            this.myTarget.takeDamage(this.myTarget.getHP());
            if(this.myBigAttackChance < 1) {
                this.myBigAttackChance += 0.05;
            }
            this.myHP = 60;
        }
    }

    private void setSprite(){
        try {
            sprite = ImageIO.read(new File("C:\\Users\\Reset\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\TileObjects\\MagikarpPlayer.png"));
        } catch (IOException e) {
            System.out.println("sprite file could not be found");
        }
    }
}
