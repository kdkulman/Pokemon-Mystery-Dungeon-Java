package TileObjects.Items;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class VisionSeed extends Item {

    public VisionSeed() {

        super("Vision Seed", "v");
        setSprite();
    }

    private void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/TileObjects/Items/Vision_Seed.png");
            sprite = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("VISION SEED file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("VISION SEED input MUST BE NULL");
        }
    }

}
