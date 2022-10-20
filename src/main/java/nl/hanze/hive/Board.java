package nl.hanze.hive;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;
import nl.hanze.hive.Hive.Tile;

public class Board {

    // A HashMap with the board cells as keys and a stack of tiles as values.
    private HashMap<Cell, Stack<Tile>> cells;

    /**
     * Initialize the game board with cells.
     * @param size The x^2 size of a game board.
     */
    public Board(int size){
        this.cells = new HashMap<>();
        createBoard(size);
    }

    /**
     * Create the board of a given size and put cells in the HashMap.
     * @param size The x^2 size of a game board.
     */
    public void createBoard(int size){
        for(int i = -size; i < size; i++){
            for(int j = -size; j < size; j++){
                cells.put(new Cell(i, j), new Stack<>());
            }
        }
    }

    /**
     * Returns the cells on a game board.
     * @return the HashMap with cells and tiles.
     */
    public HashMap<Cell, Stack<Tile>> getCells(){
        return cells;
    }

    /**
     * Returns a cell with the given coordinates.
     * @param q the q coordinate.
     * @param r the r coordinate.
     * @return the cell if a cell with given coordinates exists, null if the cell does not exist.
     */
    public Stack<Tile> getCell(int q, int r){
        for(Cell cell : cells.keySet()){
            if(cell.q == q && cell.r == r){
                return cells.get(cell);
            }
        }
        return null;
    }
}
