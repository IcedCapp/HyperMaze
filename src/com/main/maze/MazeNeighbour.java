package com.main.maze;

import java.awt.Point;

public class MazeNeighbour {

    private Maze maze;
    private Point offset;
    
    public MazeNeighbour(Maze maze, Point offset){
        this.maze = maze;
        this.offset = offset;
    }
    
    public Maze getMaze(){ return maze; }
    public Point getOffset(){ return offset; }
}