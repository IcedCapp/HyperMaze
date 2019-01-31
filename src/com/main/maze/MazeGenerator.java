
package com.main.maze;

import com.main.Res;
import com.main.Sprite;
import com.main.struct.DisjointSet;
import java.awt.Color;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class PDPair{
    public Point point;
    public Direction dir;
    
    public PDPair(Point point, Direction dir){
        this.point = point;
        this.dir = dir;
    }
}

class MRoomContainer{
    public Maze maze;
    public int[] doorsOpen;
    
    public MRoomContainer(Maze maze, int[] doorsOpen){
        this.maze = maze;
        this.doorsOpen = doorsOpen;
    }
    
    public ArrayList<Direction> getOpen(){
        ArrayList<Direction> out = new ArrayList<>();
        for(Direction d : Direction.values()){ if(isOpen(d)){ out.add(d); } }
        return out;
    }
    
    public boolean isOpen(Direction dir){
        return doorsOpen[dir.ordinal()] > 0;
    }
    
    public Direction getRandOpen(Random random){
        ArrayList<Direction> open = getOpen();
        if(open.isEmpty()){ return null; }
        return open.get(random.nextInt(open.size()));
    }
}

public class MazeGenerator {
    
    private static final Random random = new Random();
    
    public static BufferedImage getRandomBG(float hue){
        //int[] hues = new int[]{0, 55, 125, 180, 230, 300};
        Color c = new Color(Color.HSBtoRGB(hue, 0.5f, 1));
        return Sprite.colorize(Res.PATTERNS.getImage(random.nextInt(Res.PATTERNS.frameCount())), c);
    }
    
    public static BufferedImage getSolidBG(Color c){
        BufferedImage img = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
        img.setRGB(0, 0, c.getRGB());
        return img;
    }
    
    public static MRoomContainer generate(Point size, String name, float hue){
        MazeBuilder b = new MazeBuilder(size, getRandomBG(hue), name);
        DisjointSet set = new DisjointSet(size.x*size.y);
        ArrayList<PDPair> pds = new ArrayList<>();
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x; x++){
                if(x < size.x-1){ pds.add(new PDPair(new Point(x, y), Direction.RIGHT)); }
                if(y < size.y-1){ pds.add(new PDPair(new Point(x, y), Direction.DOWN)); }
            }
        }
        Collections.shuffle(pds);
        
        for(PDPair pd : pds){
            Point p2 = pd.dir.move(pd.point);
            if(set.union(pd.point.x + pd.point.y*size.x, p2.x + p2.y*size.x)){
                b.carveWall(pd.point, pd.dir);
            }
        }
        
        /*ArrayList<Point> deadEnds = new ArrayList<>();
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x; x++){
                if(b.wallCount(new Point(x, y))==3){ deadEnds.add(new Point(x, y)); }
            }
        }
        Collections.shuffle(deadEnds);
        for(Point p : deadEnds){
            if(b.wallCount(p)==3){
                while(true){
                    Direction d = Direction.values()[random.nextInt(4)];
                    if(b.withinBounds(d.move(p))){
                        b.carveWall(p, d);
                        break;
                    }
                }
            }
        }*/
        
        int[] wc = new int[]{0, 0, 0, 0};
        boolean a = true;
        if(random.nextBoolean() || a){ b.carveWall(new Point(size.x/2, 0), Direction.UP); wc[0]++; }
        if(random.nextBoolean() || a){ b.carveWall(new Point(size.x-1, size.y/2), Direction.RIGHT); wc[1]++; }
        if(random.nextBoolean() || a){ b.carveWall(new Point(size.x/2, size.y-1), Direction.DOWN); wc[2]++; }
        if(random.nextBoolean() || a){ b.carveWall(new Point(0, size.y/2), Direction.LEFT); wc[3]++; }
        
        return new MRoomContainer(b.buildMaze(), wc);
    }
    
    public static MRoomContainer generateBlank(Point size, Color color, String name){
        MazeBuilder mb = new MazeBuilder(size, getSolidBG(color), name);
        for(int y = 0; y < size.y-1; y++){
            for(int x = 0; x < size.x; x++){
                mb.carveWall(new Point(x, y), Direction.DOWN);
            }
        }
        for(int y = 0; y < size.y; y++){
            for(int x = 0; x < size.x-1; x++){
                mb.carveWall(new Point(x, y), Direction.RIGHT);
            }
        }
        
        mb.carveHwalls(new int[][]{{size.x/2, 0}, {size.x/2, size.y}});
        mb.carveVwalls(new int[][]{{0, size.y/2}, {size.x, size.y/2}});
        
        return new MRoomContainer(mb.buildMaze(), new int[]{1, 1, 1, 1});
    }
    
    public static MazeBank genBank(){
        ArrayList<MRoomContainer> mrcs = new ArrayList<>();
        
        Point size = new Point(10, 10);
        
        mrcs.add(MazeGenerator.generateBlank(size, new Color(0.6f, 0.6f, 0.6f), "Start Room"));
        for(int i = 0; i < 10; i++){
            mrcs.add(MazeGenerator.generate(size, "Room #"+(i+1), i/10f));
        }
        mrcs.add(MazeGenerator.generateBlank(size, new Color(1f, 1f, 1f), "End Room"));
        
        MazeBank bank = new MazeBank();
        for(MRoomContainer mrc : mrcs){ bank.addMaze(mrc.maze); }
        
        long start = System.currentTimeMillis();
        while(mrcs.size() > 1 && System.currentTimeMillis() < start+2000){
            int i1 = random.nextInt(mrcs.size());
            Direction d = mrcs.get(i1).getRandOpen(random);
            if(d==null){ mrcs.remove(i1); continue; }
            int i2 = random.nextInt(mrcs.size()-1);
            if(i2 >= i1){ i2++; }
            if(mrcs.get(i2).isOpen(d.opposite())){
                Point offset = d.move(new Point());
                offset = new Point(offset.x * size.x, offset.y * size.y);
                bank.addNeighbourRelation(mrcs.get(i1).maze, mrcs.get(i2).maze, offset);
                
                mrcs.get(i1).doorsOpen[d.ordinal()]--;
                mrcs.get(i2).doorsOpen[d.opposite().ordinal()]--;
            }
        }
        
        return bank;
    }
}
