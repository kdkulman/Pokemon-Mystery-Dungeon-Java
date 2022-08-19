package DungeonCharacter.Hero;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class Magikarp extends Hero {
    private double myBigAttackChance;

    public Magikarp() throws IOException {
        super("Magikarp", 60, 60,
                0, 1, 0,
                2, 5);
        this.myBigAttackChance = 0.1;
        setSprite();
    }

    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Up_Idle.png");
            myUp = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Down_Idle.png");
            myDown = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Left_Idle.png");
            myLeft = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Magikarp/Magikarp_Right_Idle.png");
            myRight = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("MAGIKARP file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("MAGIKARP input MUST BE NULL");
        }
    }


    @Override
    public String specialAttack() {
        if(this.myBigAttackChance > Math.random()) {
            this.getTarget().takeDamage(100000);
            this.myBigAttackChance += 0.05;
            this.heal(this.getMaxHP());
            return "Your target is defeated! You feel yourself growing stronger!";
        } else {
            return "Your special attack missed!";
        }
    }

    public double getMyBigAttackChance() {
        return this.myBigAttackChance;
    }
}
