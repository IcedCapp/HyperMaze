package com.main;

import com.main.struct.Vector;
import com.main.player.Trackable;
import java.awt.Graphics2D;

public class Camera {

    private Trackable trackable;
    private Vector center;
    
    public Camera(Trackable trackable){
        center = new Vector();
        this.trackable = trackable;
    }
    
    public void worldToScreen(Graphics2D g){
        g.translate(Main.WIDTH/2, Main.HEIGHT/2);
        double scale = 1.7;
        g.scale(scale, scale);
        g.translate(-center.x, -center.y);
    }
    
    public void update(){
        Vector pos = trackable.getPos();
        center = center.multiply(0.95).add(pos.multiply(0.05));
    }
}