package TileObjects;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Texture extends TileObject{

    public Texture() throws IOException {
        //super(ImageIO.read(new File("grassTexture.png")), "Texture", "T");
        setSprite();
    }

    private void setSprite(){
        try {
            sprite = ImageIO.read(new File("C:\\Users\\Reset\\OneDrive\\Documents\\GitHub\\Pokemon-Mystery-Dungeon-Java\\src\\TileObjects\\grassTexture.png"));
        } catch (IOException e) {
            System.out.println("sprite file could not be found");
        }
    }
}