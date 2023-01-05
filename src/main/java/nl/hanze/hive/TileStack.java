package nl.hanze.hive;

import java.util.Stack;

public class TileStack {

    private Stack<HiveTile> tiles;

    public TileStack(){
        this.tiles = new Stack<>();
    }

    public TileStack(HiveTile tile){
        this.tiles = new Stack<>();
        this.tiles.push(tile);
    }

    public void add(HiveTile tile){
        this.tiles.add(tile);
    }

    public HiveTile remove(){
        return this.tiles.pop();
    }

    public Stack<HiveTile> getTiles()
    {
        return this.tiles;
    }
}
