
package com.main.maze;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Maze {
    
    private Point size;
    private boolean[][] hwalls, vwalls;
    
    public Maze(Point size, boolean[][] hwalls, boolean[][] vwalls){
        this.size = size;
        this.hwalls = hwalls;
        this.vwalls = vwalls;
    }
    
    public Point getSize(){ return size; }
    
    public boolean containsWall(Point startPos, Point endPos){
        if(containsHwall(new Point(startPos.x, startPos.y+1), endPos)){ return true; }
        if(containsVwall(new Point(startPos.x+1, startPos.y), endPos)){ return true; }
        return false;
    }
    
    public boolean containsHwall(Point startPos, Point endPos){
        for(int y = Math.max(0,startPos.y); y < Math.min(size.y+1,endPos.y+1); y++){
            for(int x = Math.max(0,startPos.x); x < Math.min(size.x,endPos.x+1); x++){
                if(hwalls[x][y]){ return true; }
            }
        }
        return false;
    }
    
    public boolean containsVwall(Point startPos, Point endPos){
        for(int y = Math.max(0,startPos.y); y < Math.min(size.y,endPos.y+1); y++){
            for(int x = Math.max(0,startPos.x); x < Math.min(size.x+1,endPos.x+1); x++){
                if(vwalls[x][y]){ return true; }
            }
        }
        return false;
    }
    
    public void render(Graphics2D g){
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(0.1f));
        for(int y = 0; y < size.y+1; y++){
            for(int x = 0; x < size.x; x++){
                if(hwalls[x][y]){
                    g.drawLine(x, y, x+1, y);
                }
            }
        }
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x+1; x++){
                if(vwalls[x][y]){
                    g.drawLine(x, y, x, y+1);
                }
            }
        }
    }
}
