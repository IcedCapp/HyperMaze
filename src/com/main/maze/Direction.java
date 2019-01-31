
package com.main.maze;

import java.awt.Point;

public enum Direction {
    UP (new Point(0, -1), true),
    RIGHT (new Point(1, 0), false),
    DOWN (new Point(0, 1), true),
    LEFT (new Point(-1, 0), false);
    
    public Point offset;
    public boolean vertical;
    
    Direction(Point offset, boolean vertical){
        this.offset = offset;
        this.vertical = vertical;
    }
    
    public Point move(Point pos){
        return new Point(pos.x + offset.x, pos.y + offset.y);
    }
    
    public static Direction getDir(Point start, Point end){
        if(start.x==end.x){ return end.y<start.y ? Direction.UP : Direction.DOWN; }
        return end.x<start.x ? Direction.LEFT : Direction.RIGHT;
    }
    
    public Direction opposite(){
        if(this == UP){ return DOWN; }
        if(this == RIGHT){ return LEFT; }
        if(this == DOWN){ return UP; }
        if(this == LEFT){ return RIGHT; }
        return null;
    }
}
