package TileObjects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class SpikeTip extends TileObject{

    public SpikeTip(){
        //super(null, "SpikeTip", "t");
        setSprite();
    }

    private void setSprite(){
        try {
            this.sprite = ImageIO.read(new File("C:\\Users\\Reset\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\TileObjects\\trap.png"));
        } catch (IOException e) {
            System.out.println("sprite file could not be found");
        }
    }

    private void inflictDamage() {}
}
