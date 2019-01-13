
package com.main.maze;

import com.main.Vector;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class MazeContainer {
    
    public static final double SCALE = 80;
    
    private Vector pos;
    private Maze maze;
    
    public MazeContainer(Vector pos, Maze maze){
        this.pos = pos;
        this.maze = maze;
    }
    
    public Maze getMaze(){ return maze; }
    public Vector getPos(){ return pos; }
    
    public boolean containsPoint(Vector pos){
        return pos.greaterThan(this.pos) && pos.lessThan(this.pos.add(new Vector(maze.getSize()).multiply(SCALE)));
    }
    
    public boolean isColliding(Vector pos, double radius){
        Vector transform = pos.subtract(this.pos).divide(SCALE);
        Vector start = transform.subtract(radius/SCALE).floor(), end = transform.add(radius/SCALE).floor();
        return maze.containsWall(new Point((int)start.x, (int)start.y), new Point((int)end.x, (int)end.y));
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        g.translate(pos.x, pos.y);
        g.scale(SCALE, SCALE);
        g.setColor(new Color(230, 230, 230));
        g.setStroke(new BasicStroke(1));
        g.fill(new Rectangle2D.Double(0, 0, maze.getSize().x, maze.getSize().y));
        maze.render(g);
        g.setTransform(save);
    }
}
