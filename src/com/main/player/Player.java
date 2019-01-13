
package com.main.player;

import com.main.Key;
import com.main.Keyboard;
import com.main.Vector;
import com.main.maze.MazeCollider;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class Player implements Trackable{
    
    public static final double SPEED = 2.5;
    
    private Keyboard keyboard;
    private MazeCollider collider;
    private Vector pos;
    private double radius;
    
    public Player(Vector pos, Keyboard keyboard, MazeCollider collider){
        this.pos = pos;
        this.radius = 10;
        this.keyboard = keyboard;
        this.collider = collider;
    }
    
    public Vector getPos(){ return pos; }
    
    public void update(){
        Vector nPos = new Vector(pos);
        if(keyboard.isKeyDown(Key.UP)){ nPos.y -= SPEED; }
        if(keyboard.isKeyDown(Key.DOWN)){ nPos.y += SPEED; }
        if(keyboard.isKeyDown(Key.RIGHT)){ nPos.x += SPEED; }
        if(keyboard.isKeyDown(Key.LEFT)){ nPos.x -= SPEED; }
        
        tryMove(nPos);
    }
    
    public void tryMove(Vector nPos){
        if(!collider.isColliding(new Vector(nPos.x, pos.y), radius)){
            pos.x = nPos.x;
        }
        if(!collider.isColliding(new Vector(pos.x, nPos.y), radius)){
            pos.y = nPos.y;
        }
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(1));
        g.fillOval((int)(pos.x-radius), (int)(pos.y-radius), (int)(radius*2), (int)(radius*2));
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(2));
        g.drawOval((int)(pos.x-radius), (int)(pos.y-radius), (int)(radius*2), (int)(radius*2));
    }
}
