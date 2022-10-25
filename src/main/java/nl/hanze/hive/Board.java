package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import nl.hanze.hive.Hive.Tile;

public class Board {

    // A HashMap with the board cells as keys and a stack of tiles as values.
    private ArrayList<Cell> cells;

    /**
     * Initialize the game board with cells.
     */
    public Board(){
        this.cells = new ArrayList<>();
    }

    /**
     * Returns the cells on a game board.
     *
     * @return the HashMap with cells and tiles.
     */
    public ArrayList<Cell> getCells(){
        return cells;
    }

    /**
     * Returns a cell with the given coordinates.
     *
     * @param q the q coordinate.
     * @param r the r coordinate.
     * @return the cell if a cell with given coordinates exists, null if the cell does not exist.
     */
    public Cell getCell(int q, int r){
        for(Cell cell : cells){
            if(cell.q == q && cell.r == r){
                return cell;
            }
        }
        return null;
    }

    public boolean cellExists(int q, int r){
        for(Cell cell : cells){
            if(cell.q == q && cell.r == r){
                return true;
            }
        }
        return false;
    }

    public void addCell(int q, int r){
        cells.add(new Cell(q, r));
    }

    /**
     * Returns a boolean if the board is empty
     *
     * @return boolean True/False
     */
    public boolean isEmpty(){
        return getCells().size() == 0;
    }
}
