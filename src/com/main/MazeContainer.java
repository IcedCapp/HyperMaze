
package com.main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class MazeContainer {
    
    private static final double SCALE = 40;
    
    private Vector pos;
    private Maze maze;
    
    public MazeContainer(Vector pos, Maze maze){
        this.pos = pos;
        this.maze = maze;
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
        maze.render(g);
        g.setColor(Color.red);
        g.setStroke(new BasicStroke(0.04f));
        g.draw(new Rectangle2D.Double(-0.1, -0.1, maze.getSize().x+0.2, maze.getSize().y+0.2));
        g.setTransform(save);
    }
}
