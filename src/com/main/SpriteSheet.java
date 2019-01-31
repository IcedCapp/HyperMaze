package com.main;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    public Point spriteSize;
    public BufferedImage img;
    
    public SpriteSheet(File file, Point spriteSize){
        try {
            img = ImageIO.read(file);
        } catch (IOException ex) {}
        this.spriteSize = spriteSize;
    }
    
    public BufferedImage get(Point pos){
        return img.getSubimage(pos.x * (spriteSize.x+1), pos.y * (spriteSize.y+1), spriteSize.x, spriteSize.y);
    }
    
    public Point getSize(){ return spriteSize; }
}