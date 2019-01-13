package com.main.player;

import com.main.maze.MazeHandler;
import com.main.maze.MazeContainer;
import java.util.ArrayList;
import java.util.Iterator;

public class UnitRoomWatcher {

    private MazeHandler mazeHandler;
    private Player player;
    private ArrayList<UnitRoomListener> listeners;
    private MazeContainer lastContainer;
    
    public UnitRoomWatcher(MazeHandler mazeHandler, Player player){
        this.mazeHandler = mazeHandler;
        this.player = player;
        listeners = new ArrayList<>();
    }
    
    public void addListener(UnitRoomListener url){ listeners.add(url); }
    
    public MazeContainer getCurrentContainer(){
        Iterator<MazeContainer> containers = mazeHandler.getContainers();
        while(containers.hasNext()){
            MazeContainer mc = containers.next();
            if(mc.containsPoint(player.getPos())){ return mc; }
        }
        return null;
    }
    
    public void update(){
        MazeContainer container = getCurrentContainer();
        if(container != lastContainer){
            lastContainer = container;
            for(UnitRoomListener url : listeners){
                url.onRoomSwitch(container);
            }
        }
    }
}