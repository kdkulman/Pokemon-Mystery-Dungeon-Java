package TileObjects;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Staircase extends TileObject{

     public Staircase(){
         //super(null, "Staircase", "s");
         setSprite();

     }

        private void setSprite(){
            try {
                this.sprite = ImageIO.read(new File("./src/TileObjects/staircase.png"));
            } catch (IOException e) {
                System.out.println("stair file could not be found");
            }
        }
    }
