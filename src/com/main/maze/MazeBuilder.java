package com.main.maze;

import java.awt.Point;
import java.awt.image.BufferedImage;

public class MazeBuilder {

    private Point size;
    private String name;
    private BufferedImage img;
    private boolean[][] hwalls;
    private boolean[][] vwalls;
    
    public MazeBuilder(Point size, BufferedImage img, String name){
        this.size = size;
        this.img = img;
        this.name = name;
        
        hwalls = new boolean[size.x][size.y+1];
        vwalls = new boolean[size.x+1][size.y];
        
        for(int y = 0; y < size.y+1; y++){ for(int x = 0; x < size.x; x++){ hwalls[x][y] = true; } }
        for(int y = 0; y < size.y; y++){ for(int x = 0; x < size.x+1; x++){ vwalls[x][y] = true; } }
    }
    
    public void carveWall(Point p, Direction dir){
        if(dir.vertical){
            hwalls[p.x][p.y + (dir == Direction.UP ? 0 : 1)] = false;
        }
        else{
            vwalls[p.x + (dir == Direction.LEFT ? 0 : 1)][p.y] = false;
        }
    }
    
    public boolean withinBounds(Point pos){
        return pos.x>=0 && pos.x<size.x && pos.y>=0 && pos.y<size.y;
    }
    
    public int wallCount(Point pos){
        int count = 0;
        if(vwalls[pos.x][pos.y]){ count++; }
        if(vwalls[pos.x+1][pos.y]){ count++; }
        if(hwalls[pos.x][pos.y]){ count++; }
        if(hwalls[pos.x][pos.y+1]){ count++; }
        return count;
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
        return new Maze(size, hwalls, vwalls, img, name);
    }
}