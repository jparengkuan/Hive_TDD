package nl.hanze.hive;

import java.util.Stack;

public class TileStack {

    private Stack<Hive.Tile> tiles;

    public TileStack(){
        this.tiles = new Stack<>();
    }

    public TileStack(Hive.Tile tile){
        this.tiles = new Stack<>();
        this.tiles.push(tile);
    }

    public void add(Hive.Tile tile){
        this.tiles.add(tile);
    }

    public Hive.Tile remove(){
        return this.tiles.pop();
    }

    public Stack<Hive.Tile> getTiles()
    {
        return this.tiles;
    }
}
