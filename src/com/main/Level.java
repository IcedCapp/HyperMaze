package com.main;

import com.main.player.Player;
import com.main.player.UnitRoomWatcher;
import com.main.maze.Maze;
import com.main.maze.MazeHandler;
import com.main.maze.MazeLoader;
import com.main.maze.MazeBank;
import com.main.maze.MazeContainer;
import com.main.maze.MazeBuilder;
import com.main.maze.MazeCollider;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class Level {
    
    private MazeHandler mazeHandler;
    private Player player;
    private Keyboard keyboard;
    //private MazeLoader loader;
    private UnitRoomWatcher urw;
    private Camera cam;
    
    public Level(Keyboard keyboard){
        this.keyboard = keyboard;
        mazeHandler = new MazeHandler();
        player = new Player(new Vector(600, 400), keyboard, new MazeCollider(mazeHandler));
        
        MazeBuilder mb1 = new MazeBuilder(new Point(4, 4));
        mb1.carveHwalls(new int[][]{{1, 0}, {0, 1}, {2, 1}, {3, 1}, {1, 2}, {2, 2}, {0, 3}, {1, 3}, {3, 3}, {2, 4}});
        mb1.carveVwalls(new int[][]{{0, 2}, {1, 0}, {1, 1}, {1, 3}, {2, 1}, {2, 2}, {3, 0}, {3, 2}, {3, 3}, {4, 1}});
        Maze m1a = mb1.buildMaze();
        //Maze m1b = mb1.buildMaze();
        
        MazeBuilder mb2 = new MazeBuilder(new Point(4, 1));
        mb2.carveHwalls(new int[][]{{0, 1}, {3, 1}});
        mb2.carveVwalls(new int[][]{{1, 0}, {2, 0}, {3, 0}});
        Maze m2 = mb2.buildMaze();
        
        MazeBuilder mb3 = new MazeBuilder(new Point(4, 1));
        mb3.carveHwalls(new int[][]{{0, 0}, {3, 0}});
        mb3.carveVwalls(new int[][]{{1, 0}, {2, 0}, {3, 0}});
        Maze m3 = mb3.buildMaze();
        
        mazeHandler.addContainer(new MazeContainer(new Vector(500, 200), m1a));
        
        ArrayList<Maze> ms = new ArrayList<>();
        ms.add(m1a);
        //ms.add(m1b);
        ms.add(m2);
        ms.add(m3);
        MazeBank bank = new MazeBank(ms);
        bank.addNeighbourRelation(m1a, m1a, new Point(4, -1));
        bank.addNeighbourRelation(m1a, m2, new Point(-2, -1));
        //bank.addOneWayNeighbourRelation(m2, m1a, new Point(-1, 1));
        bank.addNeighbourRelation(m1a, m3, new Point(2, 4));
        //bank.addOneWayNeighbourRelation(m3, m1a, new Point(3, -4));
        bank.addNeighbourRelation(m2, m3, new Point(-3, 1));
        
        MazeLoader loader = new MazeLoader(mazeHandler, bank);
        urw = new UnitRoomWatcher(mazeHandler, player);
        urw.addListener(loader);
        
        cam = new Camera(player);
    }
    
    public void update(){
        player.update();
        urw.update();
        cam.update();
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        cam.worldToScreen(g);
        mazeHandler.render(g);
        player.render(g);
        g.setTransform(save);
    }
}
