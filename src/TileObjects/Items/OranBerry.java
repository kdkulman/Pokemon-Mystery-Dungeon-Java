package TileObjects.Items;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class OranBerry extends Item {

    public OranBerry(){

        //super(null, "Texture", "T");
        setSprite();
    }

    private void setSprite(){
        try {
            sprite = ImageIO.read(new File("C:\\Users\\Reset\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\TileObjects\\oranBerry.png"));
        } catch (IOException e) {
            System.out.println("sprite file could not be found");
        }
    }

    private void heal() {}
}
