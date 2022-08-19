package DungeonCharacter.Hero;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class Gallade extends Hero{
    private final int SELF_INFLICTION = 12;

    public Gallade() throws IOException {
        super("Gallade", 100, 100,30, 20, 30,
                3, 15);
        setSprite();
    }

    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/Hero/Gallade/Gallade_Up_Idle.png");
            myUp = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Gallade/Gallade_Down_Idle.png");
            myDown = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Gallade/Gallade_Left_Idle.png");
            myLeft = ImageIO.read(url);
            url = this.getClass().getResource("/Sprites/Hero/Gallade/Gallade_Right_Idle.png");
            myRight = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("GALLADE file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("GALLADE input MUST BE NULL");
        }
    }

    @Override
    public String specialAttack() {
        String damage = this.getTarget().takeDamage((int) this.getAttack());
        this.takeDamage(SELF_INFLICTION);
        String message = "Gallade did " + damage + " damage to enemy " + this.getTarget().getName() + "! \n";
        message += "(" + this.getTarget().getHP() + " HP myLeft)";
        return message;
    }
}
