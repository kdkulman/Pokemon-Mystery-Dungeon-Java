package TileObjects;

import java.awt.image.BufferedImage;
import java.io.File;

public abstract class TileObject {
    protected BufferedImage sprite;
    private String name;
    private String message;
    public String string;

    public TileObject(final BufferedImage sprite, final String name, final String string){
        //this.sprite = setSprite();
        this.name = name;
        this.string = string;
    }

    public TileObject(){
        this.name = name;
        this.string = string;
        this.sprite = sprite;
    }

    public TileObject(String the_name, BufferedImage the_sprite) {

    }

    public BufferedImage getSprite() {
        return sprite;
    }


    public String getName() {
        return name;
    }


    public String getMessage() {
        return message;
    }

    private BufferedImage setSprite(){
        return null;
    }

    @Override
    public String toString(){
        return string;
    }


}