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
            this.sprite = ImageIO.read(new File("./src/TileObjects/trap.png"));
        } catch (IOException e) {
            System.out.println("trap file could not be found");
        }
    }

    private void inflictDamage() {}
}
