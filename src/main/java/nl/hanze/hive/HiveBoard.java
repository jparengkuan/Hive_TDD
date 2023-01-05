package nl.hanze.hive;

import java.util.HashMap;
import java.util.Iterator;

public class HiveBoard {

    /**
     * Deze klasse representeert Hive board
     */

    private HashMap<Hexagon, TileStack> hiveboard;

    public HiveBoard(){
        hiveboard = new HashMap<>();
    }

    public HashMap<Hexagon, TileStack> getHiveboard() {
        return hiveboard;
    }

    public void placeTile(Hive.Tile tile, int q, int r){
        TileStack tiles = this.hiveboard.get(new Hexagon(q, r));

        if (tiles != null) tiles.add(tile); // Plaats bovenop de stack
        else hiveboard.put(new Hexagon(q, r), new TileStack(tile)); // Maak een nieuwe hexagon met de tile

    }

    public boolean givenCoordinateHasNeighbours(int q, int r){
        Hexagon position = new Hexagon(q, r);

        Iterator neighbours = position.getAllNeighBours().iterator();

        while(neighbours.hasNext()){
            TileStack neighbour = getHiveboard().get(neighbours.next());
            if (neighbour != null && !neighbour.getTiles().empty()){
                return true;
            }
        }
        return false;
    }
}
