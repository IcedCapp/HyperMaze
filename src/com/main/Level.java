package com.main;

import java.awt.Graphics2D;
import java.awt.Point;

public class Level {
    
    private MazeHandler mazeHandler;
    private Player player;
    private Keyboard keyboard;
    
    public Level(Keyboard keyboard){
        this.keyboard = keyboard;
        mazeHandler = new MazeHandler();
        player = new Player(new Vector(50, 50), keyboard, new MazeCollider(mazeHandler));
        
        boolean[][] hw = new boolean[3][4];
        boolean[][] vw = new boolean[4][3];
        for(int y = 0; y < 4; y++){ for(int x = 0; x < 3; x++){ hw[x][y] = true; } }
        for(int y = 0; y < 3; y++){ for(int x = 0; x < 4; x++){ vw[x][y] = true; } }
        hw[0][1] = false;
        hw[0][2] = false;
        hw[2][2] = false;
        vw[1][0] = false;
        vw[2][0] = false;
        vw[1][2] = false;
        vw[2][2] = false;
        vw[2][1] = false;
        vw[3][0] = false;
        vw[1][1] = false;
        hw[1][3] = false;
        Maze m = new Maze(new Point(3, 3), hw, vw);
        mazeHandler.addContainer(new MazeContainer(new Vector(100, 100), m));
        mazeHandler.addContainer(new MazeContainer(new Vector(500, 200), m));
    }
    
    public void update(){
        player.update();
    }
    
    public void render(Graphics2D g){
        mazeHandler.render(g);
        player.render(g);
    }
}
