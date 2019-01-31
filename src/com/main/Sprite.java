package com.main;

import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;

public class Sprite {

    public static BufferedImage colorize(BufferedImage img, Color color){
        BufferedImage nimg = new BufferedImage(img.getWidth(), img.getHeight(), img.getType());
        for(int y = 0; y < img.getHeight(); y++){
            for(int x = 0; x < img.getWidth(); x++){
                int r = (int)(((img.getRGB(x, y) >> 16) & 0xFF) * color.getRed() / 255f);
                int g = (int)(((img.getRGB(x, y) >> 8) & 0xFF) * color.getGreen() / 255f);
                int b = (int)((img.getRGB(x, y) & 0xFF) * color.getBlue() / 255f);
                nimg.setRGB(x, y, (r<<16) + (g<<8) + b);
            }
        }
        return nimg;
    }
    
    private BufferedImage[] frames;
    private Point size;
    
    public Sprite(SpriteSheet sheet, Point pos, int frameCount){
        this.size = sheet.spriteSize;
        
        frames = new BufferedImage[frameCount];
        for(int i = 0; i < frameCount; i++){
            frames[i] = sheet.get(new Point(i+pos.x, 0+pos.y));
        }
    }
    
    public Sprite(BufferedImage img){
        frames = new BufferedImage[]{img};
        size = new Point(img.getWidth(), img.getHeight());
    }
    
    public int frameCount(){ return frames.length; }
    public BufferedImage getImage(){ return frames[0]; }
    public BufferedImage getImage(int frame){ return frames[frame]; }
    public Point getSize(){ return size; }
}