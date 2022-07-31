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
            InputStream input = classLoader.getResourceAsStream("grassTexture.png");
        } catch (IOException e) {
            System.out.println("grass file could not be found");
        }
    }
}