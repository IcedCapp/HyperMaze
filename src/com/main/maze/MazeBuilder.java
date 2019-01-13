package com.main.maze;

import java.awt.Point;

public class MazeBuilder {

    private Point size;
    private boolean[][] hwalls;
    private boolean[][] vwalls;
    
    public MazeBuilder(Point size){
        this.size = size;
        
        hwalls = new boolean[size.x][size.y+1];
        vwalls = new boolean[size.x+1][size.y];
        
        for(int y = 0; y < size.y+1; y++){ for(int x = 0; x < size.x; x++){ hwalls[x][y] = true; } }
        for(int y = 0; y < size.y; y++){ for(int x = 0; x < size.x+1; x++){ vwalls[x][y] = true; } }
    }
    
    public void carveHwalls(int[][] ps){
        for(int[] p : ps){
            hwalls[p[0]][p[1]] = false;
        }
    }
    public void carveVwalls(int[][] ps){
        for(int[] p : ps){
            vwalls[p[0]][p[1]] = false;
        }
    }
    
    public Maze buildMaze(){
        return new Maze(size, hwalls, vwalls);
    }
}