package com.main;

import com.main.struct.Vector;
import com.main.player.Player;
import com.main.player.UnitRoomWatcher;
import com.main.maze.Maze;
import com.main.maze.MazeHandler;
import com.main.maze.MazeLoader;
import com.main.maze.MazeBank;
import com.main.maze.MazeContainer;
import com.main.maze.MazeBuilder;
import com.main.maze.MazeCollider;
import com.main.maze.MazeGenerator;
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
    private TextDisplay textDisplay;
    
    public Level(Keyboard keyboard){
        this.keyboard = keyboard;
        mazeHandler = new MazeHandler();
        player = new Player(new Vector(600, 400), keyboard, new MazeCollider(mazeHandler));
        textDisplay = new TextDisplay();
        
        
        MazeBank bank = MazeGenerator.genBank();
        
        mazeHandler.addContainer(new MazeContainer(new Vector(500, 200), bank.getStartMaze()));
        
        
        MazeLoader loader = new MazeLoader(mazeHandler, bank, textDisplay);
        urw = new UnitRoomWatcher(mazeHandler, player);
        urw.addListener(loader);
        
        cam = new Camera(player);
    }
    
    public void update(){
        player.update();
        urw.update();
        cam.update();
        textDisplay.update();
    }
    
    public void render(Graphics2D g){
        AffineTransform save = g.getTransform();
        cam.worldToScreen(g);
        mazeHandler.render(g);
        player.render(g);
        g.setTransform(save);
        textDisplay.render(g);
    }
}
