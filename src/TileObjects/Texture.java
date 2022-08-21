package TileObjects;


import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class Texture extends TileObject {

    public Texture() throws IOException {
        super("Texture", "T", false);
        setSprite();
    }

    protected void setSprite(){
        try {
            URL url = this.getClass().getResource("/Sprites/TileObjects/Textures/Texture_1.png");
            mySprite = ImageIO.read(url);
        } catch (IOException e) {
            System.out.println("TEXTURE file NOT FOUND");
        } catch (IllegalArgumentException e){
            System.out.println("TEXTURE input MUST BE NULL");
        }
    }
}