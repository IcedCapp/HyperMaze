
package com.main;

import java.util.Iterator;

public class MazeCollider {
    
    private MazeHandler mazeHandler;
    
    public MazeCollider(MazeHandler mazeHandler){
        this.mazeHandler = mazeHandler;
    }
    
    public boolean isColliding(Vector pos, double radius){
        Iterator<MazeContainer> containers = mazeHandler.getContainers();
        while(containers.hasNext()){
            if(containers.next().isColliding(pos, radius)){ return true; }
        }
        return false;
    }
}
