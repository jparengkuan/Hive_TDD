package nl.hanze.hive;

import java.util.HashMap;
import java.util.Stack;

public class Cell {
    int q;
    int r;
    Stack<Gametile> tiles;

    /**
     * The constructor of a cell with (q, r) coordinates.
     *
     * @param q the q coordinate
     * @param r the r coordinate
     */
    public Cell(int q, int r) {
        this.q = q;
        this.r = r;
        this.tiles = new Stack<>();
    }

    public HashMap<String, Cell> getCoordinatesHashmap() {
        HashMap<String, Cell> directionsHashMap  = new HashMap<String, Cell>();
        directionsHashMap.put("NORTH_EAST", new Cell(this.q+1, this.r-1));
        directionsHashMap.put("NORTH_WEST", new Cell(this.q+0, this.r-1));
        directionsHashMap.put("SOUTH_EAST", new Cell(this.q+0, this.r+1));
        directionsHashMap.put("SOUTH_WEST", new Cell(this.q-1, this.r+1));
        directionsHashMap.put("EAST", new Cell(this.q+1, this.r-0));
        directionsHashMap.put("WEST", new Cell(this.q-1, this.r+0));

        return directionsHashMap;

    }

    public Stack<Gametile> getTiles() {
        return tiles;
    }

    public Hive.Tile getTopTileTypeFromStack() {
        if (this.tiles.isEmpty()){
            return null;
        }

        return this.tiles.peek().getTileName();

    }

    /**
     * @returns a boolean if the cell has a tile or tiles stacked on top
     */
    boolean isEmpty() {
        return tiles.isEmpty();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Cell cell = (Cell) obj;

        if (this.r == cell.r && this.q == cell.q) {
            return true;
        }

        return false;

    }
}
