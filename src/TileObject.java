package com;

import java.awt.image.BufferedImage;

public abstract class TileObject {
    private BufferedImage sprite;
    private String name;
    private String message;


    public BufferedImage getSprite() {
        return sprite;
    }


    public String getName() {
        return name;
    }


    public String getMessage() {
        return message;
    }


}