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

    public void setHiveboard(HashMap<Hexagon, TileStack> hiveboard) {
        this.hiveboard = hiveboard;
    }

    public void placeTile(Hive.Tile tile, Hive.Player owner, int q, int r){
        TileStack tiles = this.hiveboard.get(new Hexagon(q, r));

        if (tiles != null) tiles.add(new HiveTile(owner, tile)); // Plaats bovenop de stack
        else hiveboard.put(new Hexagon(q, r), new TileStack(new HiveTile(owner, tile))); // Maak een nieuwe hexagon met de tile

    }

    public void moveTile(int fromQ, int fromR, int toQ, int toR) {
        HiveTile tile = this.hiveboard.get(new Hexagon(fromQ, fromR)).getTiles().pop();
        this.placeTile(tile.getInsect(), tile.getOwner(), toQ, toR);
    }

    public boolean givenCoordinateHasNeighbours(int q, int r){
        Hexagon position = new Hexagon(q, r);

        Iterator neighbours = position.getAllNeighBours().iterator();

        while(neighbours.hasNext()){
            TileStack neighbour = getHiveboard().get(neighbours.next());
            if (neighbour != null && !neighbour.getTiles().isEmpty()){
                return true;
            }
        }
        return false;
    }

    public boolean givenCoordinateHasTiles(int q, int r){
        return getHiveboard().get(new Hexagon(q, r)) != null && !getHiveboard().get(new Hexagon(q, r)).getTiles().isEmpty();
    }
}
