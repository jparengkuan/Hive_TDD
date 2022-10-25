package nl.hanze.hive;

import java.util.Stack;

public class Cell {
    int q;
    int r;
    Stack<Gametile> tiles;

    /**
     * The constructor of a cell with (q, r) coordinates.
     * @param q the q coordinate
     * @param r the r coordinate
     */
    public Cell(int q, int r){
        this.q = q;
        this.r = r;
        this.tiles = new Stack<>();
    }

    public Stack<Gametile> getTiles(){
        return tiles;
    }

    boolean isEmpty() {

    }
}
