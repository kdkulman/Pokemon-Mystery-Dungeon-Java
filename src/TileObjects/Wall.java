package TileObjects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Wall extends TileObject{

    public Wall(){
        //super(null, "Wall", "W");
        setSprite();
    }

    private void setSprite(){
        try {
            sprite = ImageIO.read(new File("./src/TileObjects/grass_northWall.png"));
        } catch (IOException e) {
            System.out.println("wall file could not be found");
        }
    }
}
