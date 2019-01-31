package com.main.maze;

import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class MazeBank {

    //private ArrayList<Maze> mazes;
    private Maze startMaze;
    private HashMap<Maze, ArrayList<MazeNeighbour>> neighbourData;
    
    public MazeBank(){
        neighbourData = new HashMap<>();
    }
    
    public void addMaze(Maze m){
        neighbourData.put(m, new ArrayList<>());
        if(startMaze==null){ startMaze = m; }
    }
    
    public Maze getStartMaze(){ return startMaze; }
    
    public void addNeighbourRelation(Maze m1, Maze m2, Point offset){
        neighbourData.get(m1).add(new MazeNeighbour(m2, offset));
        neighbourData.get(m2).add(new MazeNeighbour(m1, new Point(-offset.x, -offset.y)));
    }
    
    public void addOneWayNeighbourRelation(Maze m1, Maze m2, Point offset){
        neighbourData.get(m1).add(new MazeNeighbour(m2, offset));
    }
    
    public Iterator<MazeNeighbour> getNeighbours(Maze m){
        return neighbourData.get(m).iterator();
    }
}