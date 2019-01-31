package com.main.maze;

import com.main.TextDisplay;
import com.main.player.UnitRoomListener;
import com.main.struct.Vector;
import java.util.Iterator;

public class MazeLoader implements UnitRoomListener{

    private MazeHandler mazeHandler;
    private MazeBank bank;
    private TextDisplay textDisplay;
    
    public MazeLoader(MazeHandler mazeHandler, MazeBank bank, TextDisplay textDisplay){
        this.mazeHandler = mazeHandler;
        this.bank = bank;
        this.textDisplay = textDisplay;
    }
    
    public void onRoomSwitch(MazeContainer newRoom) {
        mazeHandler.clear();
        if(newRoom != null){
            textDisplay.setText(newRoom.getMaze().getName());
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