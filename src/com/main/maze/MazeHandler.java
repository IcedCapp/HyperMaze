
package com.main.maze;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class MazeHandler {
    
    private ArrayList<MazeContainer> containers;
    
    public MazeHandler(){
        containers = new ArrayList<>();
    }
    
    public void clear(){
        containers.clear();
    }
    
    public void addContainer(MazeContainer mc){
        containers.add(mc);
    }
    
    public Iterator<MazeContainer> getContainers(){ return containers.iterator(); }
    
    public void render(Graphics2D g){
        for(MazeContainer mc : containers){
            mc.render(g);
        }
    }
}
