package com.main.maze;

import com.main.player.UnitRoomListener;
import com.main.Vector;
import java.util.Iterator;

public class MazeLoader implements UnitRoomListener{

    private MazeHandler mazeHandler;
    private MazeBank bank;
    
    public MazeLoader(MazeHandler mazeHandler, MazeBank bank){
        this.mazeHandler = mazeHandler;
        this.bank = bank;
    }
    
    public void onRoomSwitch(MazeContainer newRoom) {
        mazeHandler.clear();
        if(newRoom != null){
            mazeHandler.addContainer(newRoom);
            Iterator<MazeNeighbour> neighbours = bank.getNeighbours(newRoom.getMaze());
            while(neighbours.hasNext()){
                MazeNeighbour mn = neighbours.next();
                MazeContainer cont = new MazeContainer(newRoom.getPos().add(new Vector(mn.getOffset()).multiply(MazeContainer.SCALE)), mn.getMaze());
                mazeHandler.addContainer(cont);
            }
        }
    }
}